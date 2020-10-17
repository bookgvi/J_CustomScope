package scopes.customScope.LearningStartSuspendScope;

import javax.enterprise.event.Event;
import javax.enterprise.event.Observes;
import javax.enterprise.inject.spi.*;

public class LSSScopeExtension implements Extension {
  private LSSScopeContext ctx;

  private Event event;

  public void beforeBeanDiscovery(@Observes BeforeBeanDiscovery bbd) {
    bbd.addScope(LSSScope.class, true, false);
  }

  public void afterBeanDiscovery(@Observes AfterBeanDiscovery abd, BeanManager bm) {
    ctx = new LSSScopeContext();
    fireContext(bm, ctx);
    asyncFireContext(bm, ctx);
    abd.addContext(ctx);
  }

  public LSSScopeContext getContext() {
    return ctx;
  }

  private void fireContext(BeanManager beanManager, LSSScopeContext ctx) {
    beanManager.getEvent().select(LSSScopeContext.class).fire(ctx);
  }

  private void asyncFireContext(BeanManager beanManager, LSSScopeContext ctx) {
    beanManager.getEvent().select(LSSScopeContext.class).fireAsync(ctx);
  }
}
