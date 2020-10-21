package beans.interceptors;

import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import java.util.Arrays;

@Logger
@Interceptor
public class LoggerInterceptorWithAnnotation {
  @AroundInvoke
  public Object log(InvocationContext ctx) throws Exception {
    System.out.println("---- Interceptor with annotation --------");
    System.out.printf("Class: %s%n", ctx.getTarget());
    System.out.printf("Method: %s, params: %s%n", ctx.getMethod().getName(), Arrays.toString(ctx.getParameters()));
    return ctx.proceed();
  }
}
