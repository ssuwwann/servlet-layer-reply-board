package reply;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/reply/*")
public class ReplyController extends HttpServlet {
  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
    System.out.println("리플 두 겟");

  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
    System.out.println("리플 두 포스트");
    String userid = req.getParameter("userid");
    String boardpk = req.getParameter("boardpk");
    String content = req.getParameter("content");

    System.out.println(userid + " " + boardpk + " " + content);

  }
}
