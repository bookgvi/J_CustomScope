package beans;

import scopes.customScope.CustomAppScope;
import scopes.customScope.SuspendableAppScoped;

@SuspendableAppScoped
public class CustomBean implements Greeting {
  public String greeting() {
    return "Hello, from bean with Suspendable scope";
  }
}
