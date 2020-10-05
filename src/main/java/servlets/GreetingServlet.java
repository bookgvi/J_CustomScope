package servlets;

import beans.Greeting;

import javax.inject.Inject;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "Get greeting", urlPatterns = "/greeting")

public class GreetingServlet extends HttpServlet {

  @Inject
  Greeting cb;

  public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
    resp.getWriter().write(cb.greeting());
  }
}
