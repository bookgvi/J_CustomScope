package scopes.customScope;

import javax.enterprise.inject.spi.AfterBeanDiscovery;
import javax.enterprise.inject.spi.BeforeBeanDiscovery;
import javax.enterprise.inject.spi.Extension;

public class SuspendableScopeExtension implements Extension {

  public void beforeBeanDiscovery(BeforeBeanDiscovery bbd) {
    bbd.addScope(CustomAppScope.class, true, false);
  }

  public void afterBeanDiscovery(AfterBeanDiscovery abd) {
    abd.addContext(new SuspendableScopeContext());
  }
}
