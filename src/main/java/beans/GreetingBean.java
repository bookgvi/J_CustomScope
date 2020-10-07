package beans;

import scopes.customScope.StartAndSuspendScope.SuspendableAppScoped;

@SuspendableAppScoped
public class GreetingBean implements Greeting {
  public String greeting() {
    return "Hello, from bean with Suspendable scope";
  }
}
