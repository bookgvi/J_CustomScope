package beans;

import scopes.customScope.likeAppScoped.CustomAppScope;

@CustomAppScope
public class GreetingBean2 implements Greeting {
  public String greeting() {
    return "Hello, from bean";
  }
}
