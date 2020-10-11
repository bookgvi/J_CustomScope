package beans;

import scopes.customScope.LearningStartSuspendScope.LSSScope;
import scopes.customScope.StartAndSuspendScope.SuspendableAppScoped;

import javax.enterprise.event.Event;
import javax.enterprise.event.Observes;
import javax.enterprise.event.Reception;
import javax.enterprise.inject.spi.BeanManager;
import javax.inject.Inject;

@LSSScope
public class GreetingBean implements Greeting {
  private int testID = 0;
  private String msg = "This is message from LSSScopeBean";
  @Inject
  Event<String> event;
  @Inject
  BeanManager beanManager;

  public void setTestID(int value) {
    this.testID = value;
  }

  public int getTestID() {
    return this.testID;
  }

  public String greeting() {
//    beanManager.getEvent().select(String.class).fire(msg);
    return msg;
  }

}
