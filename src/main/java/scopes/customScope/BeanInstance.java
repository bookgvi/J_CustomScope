package scopes.customScope;

import javax.enterprise.context.spi.Contextual;
import javax.enterprise.context.spi.CreationalContext;

public class BeanInstance<T> {
  private final T instance;
  private final Contextual<T> contextual;
  private final CreationalContext<T> creationalContext;

  public BeanInstance(T instance, Contextual<T> contextual, CreationalContext<T> creationalContext) {
    this.instance = instance;
    this.contextual = contextual;
    this.creationalContext = creationalContext;
  }

  T getInstance() {
    return instance;
  }

  void destroy() {
    contextual.destroy(instance, creationalContext);
  }
}
