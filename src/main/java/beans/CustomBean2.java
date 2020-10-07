package beans;

import scopes.customScope.CustomAppScope;

@CustomAppScope
public class CustomBean2 implements Greeting {
  public String greeting() {
    return "Hello, from bean";
  }
}
