package scopes.customScope.StartAndSuspendScope;

import javax.enterprise.event.Observes;
import javax.enterprise.inject.spi.AfterBeanDiscovery;
import javax.enterprise.inject.spi.BeforeBeanDiscovery;
import javax.enterprise.inject.spi.Extension;

public class SuspendableScopeExtension implements Extension {
  private SuspendableScopeContext ctx;

  public SuspendableScopeContext getContext() {
    return ctx;
  }

  public void beforeBeanDiscovery(@Observes BeforeBeanDiscovery bbd) {
    bbd.addScope(SuspendableAppScoped.class, true, false);
  }

  public void afterBeanDiscovery(@Observes AfterBeanDiscovery abd) {
    ctx = new SuspendableScopeContext();
    abd.addContext(ctx);
  }
}
