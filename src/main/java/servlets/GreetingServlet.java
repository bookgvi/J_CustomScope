package servlets;

import beans.Greeting;
import beans.GreetingBean;
import beans.GreetingBean2;
import scopes.customScope.StartAndSuspendScope.SuspendableScopeContext;
import scopes.customScope.StartAndSuspendScope.SuspendableScopeExtension;

import javax.enterprise.inject.spi.CDI;
import javax.inject.Inject;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "Get greeting", urlPatterns = "/greeting")

public class GreetingServlet extends HttpServlet {
  private int scopeId = 0;
  @Inject
  SuspendableScopeExtension cdiExt;

  @Inject
  GreetingBean greetingBean;

  private Greeting greetingBean2 = CDI.current().select(GreetingBean2.class).get();

  public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
    String msg = "QQQ";
    SuspendableScopeContext ctx = cdiExt.getContext();
    ctx.start(String.valueOf(scopeId++));
    try {
      msg = greetingBean.greeting();
      ctx.suspend();
    } finally {
      ctx.destroy(String.valueOf(scopeId));
    }
    resp.getWriter().printf("%s%n", msg);
    resp.getWriter().printf("%s%n", greetingBean2.greeting());
  }
}
