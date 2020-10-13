package beans;

import beans.interceptors.LoggerInterceptor;
import beans.interceptors.LoggerInterceptorWithAnnotation;
import scopes.customScope.LearningStartSuspendScope.LSSScope;

import javax.interceptor.Interceptors;

@LSSScope
@Interceptors({LoggerInterceptorWithAnnotation.class})
public class GreetingBean implements Greeting {
  private int testID = 0;
  private String msg = "This is message from LSSScopeBean";

  public void setTestID(int value) {
    this.testID = value;
  }

  public int getTestID() {
    return this.testID;
  }

  public String greeting() {
    return msg;
  }

}
