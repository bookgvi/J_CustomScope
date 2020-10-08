package scopes.customScope.LearningStartSuspendScope;

import javax.enterprise.context.ContextNotActiveException;
import javax.enterprise.context.spi.Context;
import javax.enterprise.context.spi.Contextual;
import javax.enterprise.context.spi.CreationalContext;
import java.lang.annotation.Annotation;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicReference;

public class LSSScopeContext implements Context {
  private final ThreadLocal<AtomicReference<String>> LOCAL_SCOPE_THREAD = ThreadLocal.withInitial(AtomicReference::new);
  private ConcurrentHashMap<String, Map<Contextual<?>, BeanInstance<?>>> cache = new ConcurrentHashMap<>();

  @Override
  public Class<? extends Annotation> getScope() {
    return LSSScope.class;
  }

  @Override
  public <T> T get(Contextual<T> contextual, CreationalContext<T> creationalContext) {
    AtomicReference<String> activeScope = LOCAL_SCOPE_THREAD.get();
    if (activeScope.get() == null) {
      throw new ContextNotActiveException();
    }
    String scopeId = activeScope.get();
    @SuppressWarnings("unchecked")
    T instance = (T) cache
      .computeIfAbsent(scopeId, s -> new ConcurrentHashMap<>())
      .computeIfAbsent(contextual, c -> new BeanInstance<>(contextual.create(creationalContext), contextual, creationalContext))
      .getBean();
    return instance;
  }

  @Override
  public <T> T get(Contextual<T> contextual) {
    AtomicReference<String> activeScope = LOCAL_SCOPE_THREAD.get();
    if (activeScope.get() == null) {
      throw new ContextNotActiveException();
    } else {
      String scopeId = activeScope.get();
      Map<Contextual<?>, BeanInstance<?>> defaultMap = new HashMap<Contextual<?>, BeanInstance<?>>() {{
        put(contextual, null);
      }};
      @SuppressWarnings("unchecked")
      BeanInstance<T> instance = (BeanInstance<T>) cache.getOrDefault(scopeId, defaultMap).get(contextual);
      if (instance != null) {
        return instance.getBean();
      }
    }
    return null;
  }

  @Override
  public boolean isActive() {
    return LOCAL_SCOPE_THREAD.get().get() != null;
  }

  public void start(String scopeId) {
    AtomicReference<String> activeScope = LOCAL_SCOPE_THREAD.get();
    if (activeScope.get() != null) {
      throw new IllegalStateException("Scope is already active");
    }
    activeScope.set(scopeId);
  }

  public void suspend() {
    AtomicReference<String> activeScope = LOCAL_SCOPE_THREAD.get();
    if (activeScope.get() == null) {
      throw new IllegalStateException("Scope is already suspend");
    }
    activeScope.set(null);
  }

  public void destroy(String scopeId) {
    Map<Contextual<?>, BeanInstance<?>> deletedBeans = cache.remove(scopeId);
    if (deletedBeans != null) {
      deletedBeans.values().forEach(BeanInstance::destroy);
    }
  }
}
