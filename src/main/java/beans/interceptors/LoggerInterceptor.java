package beans.interceptors;

import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;
import java.util.Arrays;

public class LoggerInterceptor {
  @AroundInvoke
  public Object log(InvocationContext ctx) throws Exception {
    System.out.printf("Method: %s, params: %s%n", ctx.getMethod().getName(), Arrays.toString(ctx.getParameters()));
    return ctx.proceed();
  }
}
