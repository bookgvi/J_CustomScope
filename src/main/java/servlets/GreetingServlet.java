package servlets;

import beans.Greeting;
import beans.GreetingBean;
import beans.GreetingBean2;
import scopes.customScope.LearningStartSuspendScope.LSSScopeContext;
import scopes.customScope.LearningStartSuspendScope.LSSScopeExtension;
import scopes.customScope.StartAndSuspendScope.SuspendableScopeContext;
import scopes.customScope.StartAndSuspendScope.SuspendableScopeExtension;

import javax.enterprise.event.Observes;
import javax.enterprise.event.ObservesAsync;
import javax.enterprise.event.Reception;
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
  private LSSScopeContext ctx;
  @Inject
  LSSScopeExtension cdiExt;

  @Inject
  GreetingBean greetingBean;

  private GreetingBean2 greetingBean2 = CDI.current().select(GreetingBean2.class).get();

  public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
    String msg = "QQQ", msg1 = "", msg2 = "", msg3;

    ctx = greetingBean2.getLssScopeContext() != null
      ? greetingBean2.getLssScopeContext() // In GreetingBean2 we have an Observe method
      : cdiExt.getContext(); // Directly Injected

    ctx.start(String.valueOf(scopeId));
    try {
      msg1 = String.valueOf(greetingBean.getTestID());
      msg3 = greetingBean.greeting();
      greetingBean.setTestID(11);

      ctx.suspend();
      ctx.start(String.valueOf(scopeId));

      msg2 = String.valueOf(greetingBean.getTestID());
    } finally {
      ctx.destroy(String.valueOf(scopeId));
    }
    resp.getWriter().printf("Before suspend: %s, After: %s%n", msg1, msg2);
    resp.getWriter().printf("%s%n", msg3);
  }


}
