package beans;

import scopes.customScope.CustomAppScope;

@CustomAppScope
public class CustomBean implements Greeting {
  public String greeting() {
    return "Hello, form bean";
  }
}
