package member;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/member/*")
public class MemberController extends HttpServlet {
  private MemberService memberService;

  public MemberController() {
    memberService = MemberService.getInstance();
  }

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
    String uri = req.getRequestURI();
    if (uri.equals("/member/join")) res.sendRedirect("/member-join");
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
    String loginid = req.getParameter("loginid");
    String password = req.getParameter("password");
    String nickname = req.getParameter("nickname");

    RequestMember member = new RequestMember(loginid, password, nickname);
    int i = memberService.addMember(member);
    System.out.println(i + "번 회원가입 성공");
  }
}
