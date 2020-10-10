package beans;

import scopes.customScope.LearningStartSuspendScope.LSSScope;
import scopes.customScope.StartAndSuspendScope.SuspendableAppScoped;

@LSSScope
public class GreetingBean implements Greeting {
  private int testID = 0;

  public void setTestID(int value) {
    this.testID = value;
  }

  public int getTestID() {
    return this.testID;
  }
  
  public String greeting() {
    return "Hello, from bean with LSSScope";
  }
}
