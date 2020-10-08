package scopes.customScope.LearningStartSuspendScope;

import javax.enterprise.event.Observes;
import javax.enterprise.inject.spi.AfterBeanDiscovery;
import javax.enterprise.inject.spi.BeforeBeanDiscovery;
import javax.enterprise.inject.spi.Extension;

public class LSSScopeExtension implements Extension {
  private LSSScopeContext ctx;

  public void beforeBeanDiscovery(@Observes BeforeBeanDiscovery bbd) {
    bbd.addScope(LSSScope.class, true, false);
  }

  public void afterBeanDiscovery(@Observes AfterBeanDiscovery abd) {
    ctx = new LSSScopeContext();
    abd.addContext(ctx);
  }

  public LSSScopeContext getContext() {
    return ctx;
  }
}
