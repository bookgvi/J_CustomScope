package beans;

import scopes.customScope.LearningStartSuspendScope.LSSScopeContext;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.enterprise.event.Reception;

@ApplicationScoped // ApplicationScoped(!) - because it is always active in all thread
public class GreetingBean2 {
  private LSSScopeContext lssScopeContext;

  public LSSScopeContext getLssScopeContext() {
    return lssScopeContext;
  }

  public void getCtx(@Observes(notifyObserver = Reception.ALWAYS) LSSScopeContext ctx) {
    lssScopeContext = ctx;
  }
}
