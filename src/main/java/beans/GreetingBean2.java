package beans;

import scopes.customScope.LearningStartSuspendScope.LSSScopeContext;
import scopes.customScope.likeAppScoped.CustomAppScope;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.enterprise.event.ObservesAsync;
import javax.enterprise.event.Reception;

//@ApplicationScoped // ApplicationScoped(!) - because it is always active in all thread - optimal for synchronous events
@CustomAppScope
public class GreetingBean2 {
  private LSSScopeContext lssScopeContext;

  public LSSScopeContext getLssScopeContext() {
    return lssScopeContext;
  }

//  public void getCtx(@Observes(notifyObserver = Reception.ALWAYS) LSSScopeContext ctx) {
//    lssScopeContext = ctx;
//  }

  public void asyncGetCtx(@ObservesAsync LSSScopeContext ctx) {
    lssScopeContext = ctx;
  }
}
