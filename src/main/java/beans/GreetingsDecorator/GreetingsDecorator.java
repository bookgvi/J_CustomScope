package beans.GreetingsDecorator;

import beans.Greeting;
import scopes.customScope.LearningStartSuspendScope.LSSScope;

import javax.decorator.Decorator;
import javax.decorator.Delegate;
import javax.enterprise.inject.Any;
import javax.inject.Inject;

@Decorator
public abstract class GreetingsDecorator implements Greeting {

  @LSSScope
  @Inject
  @Any
  @Delegate
  Greeting greeting;


  @Override
  public String greeting() {
    return greeting.greeting() + " QQQ1";
  }
}
