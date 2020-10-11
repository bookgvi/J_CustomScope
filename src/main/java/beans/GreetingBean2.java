package beans;

import scopes.customScope.LearningStartSuspendScope.LSSScopeContext;
import scopes.customScope.likeAppScoped.CustomAppScope;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.enterprise.event.ObservesAsync;
import javax.enterprise.event.Reception;

//@ApplicationScoped // ApplicationScoped(!) - because it is always active in all thread - optimal for synchronous events
@ApplicationScoped
public class GreetingBean2 {
  private LSSScopeContext lssScopeContext;

  public LSSScopeContext getLssScopeContext() {
    return lssScopeContext;
  }

  public void getCtx(@Observes LSSScopeContext ctx) {
    saveLSSScopeContext(ctx);
  }

//  public void asyncGetCtx(@ObservesAsync LSSScopeContext ctx) {
//    saveLSSScopeContext(ctx);
//  }

  private void saveLSSScopeContext(LSSScopeContext ctx) {
    if (ctx != null) lssScopeContext = ctx;
  }
}
