package scopes.customScope.LearningStartSuspendScope;

import javax.enterprise.context.spi.Contextual;
import javax.enterprise.context.spi.CreationalContext;

public class BeanInstance<T> {
  private T instance;
  private Contextual<T> contextual;
  private CreationalContext<T> creationalContext;

  public BeanInstance(T instance, Contextual<T> contextual, CreationalContext<T> creationalContext) {
    this.instance = instance;
    this.contextual = contextual;
    this.creationalContext = creationalContext;
  }

  public T getBean() {
    return this.instance;
  }

  public void destroy() {
    contextual.destroy(instance, creationalContext);
  }
}
