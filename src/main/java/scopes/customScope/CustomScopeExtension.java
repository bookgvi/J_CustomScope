package scopes.customScope;

import javax.enterprise.event.Observes;
import javax.enterprise.inject.spi.AfterBeanDiscovery;
import javax.enterprise.inject.spi.BeforeBeanDiscovery;
import javax.enterprise.inject.spi.Extension;

public class CustomScopeExtension implements Extension {
  public void beforeBeanDiscovery(@Observes BeforeBeanDiscovery bbd) {
    bbd.addScope(CustomAppScope.class, true, false);
  }

  public void afterBeanDiscovery(@Observes AfterBeanDiscovery abd) {
    abd.addContext(new CustomScopeContext());
  }
}
