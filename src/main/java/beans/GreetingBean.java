package beans;

import scopes.customScope.LearningStartSuspendScope.LSSScope;
import scopes.customScope.StartAndSuspendScope.SuspendableAppScoped;

@LSSScope
public class GreetingBean implements Greeting {
  public String greeting() {
    return "Hello, from bean with LSSScope";
  }
}
