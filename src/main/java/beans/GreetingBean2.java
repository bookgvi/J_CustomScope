package beans;

import scopes.customScope.LearningStartSuspendScope.IPInfo;
import scopes.customScope.LearningStartSuspendScope.LSSScopeContext;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.enterprise.event.ObservesAsync;
import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;

@ApplicationScoped // ApplicationScoped(!) - because it is always active in all thread
public class GreetingBean2 {
  private LSSScopeContext lssScopeContext;

  public LSSScopeContext getLssScopeContext() {
    return lssScopeContext;
  }

  public void getCtx(@Observes LSSScopeContext ctx) {
    saveLSSScopeContext(ctx);
  }

  public void asyncGetCtx(@ObservesAsync LSSScopeContext ctx) {
    saveLSSScopeContext(ctx);
  }

  private void saveLSSScopeContext(LSSScopeContext ctx) {
    if (this.lssScopeContext == null) lssScopeContext = ctx;
  }

  @Produces
  @IPInfo
  public String getInjectionPointInfo(InjectionPoint injectionPoint) {
    return injectionPoint.getMember().getDeclaringClass().getName();
  }

}
