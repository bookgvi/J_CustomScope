package servlets;

import beans.CustomBean;
import scopes.customScope.*;

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
  CustomBean cb;

  public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
    String msg = "QQQ";
    SuspendableScopeContext ctx = cdiExt.getContext();
    ctx.start(String.valueOf(scopeId++));
    try {
      msg = cb.greeting();
      ctx.suspend();
    } finally {
      ctx.destroy(String.valueOf(scopeId));
    }
    resp.getWriter().write(msg);
  }
}
