package beans;

import scopes.customScope.LearningStartSuspendScope.LSSScopeContext;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.enterprise.event.ObservesAsync;

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
}
