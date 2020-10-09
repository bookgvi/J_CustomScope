package beans;

import scopes.customScope.likeAppScoped.CustomAppScope;

@CustomAppScope
public class GreetingBean2 {
  public String greeting() {
    return "Hello, from bean with CustomAppScope";
  }
}
