package scopes.customScope;

import javax.enterprise.context.spi.Context;
import javax.enterprise.context.spi.Contextual;
import javax.enterprise.context.spi.CreationalContext;
import java.lang.annotation.Annotation;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicReference;

public class SuspendableScopeContext implements Context {
  private static final ThreadLocal<AtomicReference<String>> ACTIVE_SCOPE_THREAD_LOCAL = ThreadLocal.withInitial(AtomicReference::new);
  private final ConcurrentHashMap<String, Map<Contextual<?>, BeanInstance<?>>> cache = new ConcurrentHashMap<>();

  @Override
  public Class<? extends Annotation> getScope() {
    return CustomAppScope.class;
  }

  @Override
  public <T> T get(Contextual<T> contextual, CreationalContext<T> creationalContext) {
    return null;
  }

  @Override
  public <T> T get(Contextual<T> contextual) {
    return null;
  }

  @Override
  public boolean isActive() {
    return ACTIVE_SCOPE_THREAD_LOCAL.get().get() != null;
  }
}
