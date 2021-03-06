package beans;

import beans.interceptors.LoggerInterceptor;
import beans.interceptors.LoggerInterceptorWithAnnotation;
import scopes.customScope.LearningStartSuspendScope.IPInfo;
import scopes.customScope.LearningStartSuspendScope.LSSScope;

import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;
import javax.interceptor.Interceptors;

@LSSScope
public class GreetingBean implements Greeting {
  private int testID = 0;
  private String msg = "This is message from LSSScopeBean";

  public void setTestID(int value) {
    this.testID = value;
  }

  @Interceptors({LoggerInterceptorWithAnnotation.class})
  public int getTestID() {
    return this.testID;
  }

  public String greeting() {
    return msg;
  }

//  @Produces
//  @IPInfo
//  public String getInjectionPointInfo(InjectionPoint injectionPoint) {
//    return injectionPoint.getMember().getDeclaringClass().getName();
//  }
}
