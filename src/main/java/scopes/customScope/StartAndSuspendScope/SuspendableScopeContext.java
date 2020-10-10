package scopes.customScope.StartAndSuspendScope;

import javax.enterprise.context.ContextNotActiveException;
import javax.enterprise.context.spi.Context;
import javax.enterprise.context.spi.Contextual;
import javax.enterprise.context.spi.CreationalContext;
import java.lang.annotation.Annotation;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicReference;

public class SuspendableScopeContext implements Context {
  private static final ThreadLocal<AtomicReference<String>> ACTIVE_SCOPE_THREAD_LOCAL = ThreadLocal.withInitial(AtomicReference::new);
  private final ConcurrentHashMap<String, Map<Contextual<?>, BeanInstance<?>>> cache = new ConcurrentHashMap<>();

  @Override
  public Class<? extends Annotation> getScope() {
    return SuspendableAppScoped.class;
  }

  @Override
  public <T> T get(Contextual<T> contextual, CreationalContext<T> creationalContext) {
    String scopeId = ACTIVE_SCOPE_THREAD_LOCAL.get().get();
    if (scopeId == null) {
      throw new ContextNotActiveException();
    }
    @SuppressWarnings("unchecked")
    T instance = (T) cache
      .computeIfAbsent(scopeId, s -> new ConcurrentHashMap<>())
      .computeIfAbsent(contextual, c -> new BeanInstance<>(contextual.create(creationalContext), contextual, creationalContext))
      .getInstance();
    return instance;
  }

  @Override
  public <T> T get(Contextual<T> contextual) {
    String scopeId = ACTIVE_SCOPE_THREAD_LOCAL.get().get();
    if (scopeId == null) {
      throw new ContextNotActiveException();
    } else {
      // В JDK 9 можно использовать Map.of();
      @SuppressWarnings("unchecked")
      BeanInstance<T> instance = (BeanInstance<T>) cache.getOrDefault(scopeId, new ConcurrentHashMap<>()).get(contextual);
      if (instance != null) return instance.getInstance();
    }
    return null;
  }

  @Override
  public boolean isActive() {
    return ACTIVE_SCOPE_THREAD_LOCAL.get().get() != null;
  }

  public void destroy(String scopeId) {
    Map<Contextual<?>, BeanInstance<?>> removedInstances = cache.remove(scopeId);
    if (removedInstances != null) {
      removedInstances.values().forEach(BeanInstance::destroy);
    }
  }

  public void start(String scopeId) {
    AtomicReference<String> activeScope = ACTIVE_SCOPE_THREAD_LOCAL.get();
    if (activeScope.get() != null) {
      throw new IllegalStateException("Scope instance already active");
    }
    activeScope.set(scopeId);
  }

  public void suspend() {
    AtomicReference<String> activeScope = ACTIVE_SCOPE_THREAD_LOCAL.get();
    if (activeScope.get() == null) {
      throw new IllegalStateException("Scope instance already suspend");
    }
    activeScope.set(null);
  }
}
