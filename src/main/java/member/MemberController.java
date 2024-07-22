package member;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

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
    if (uri.equals("/member/logout")) logout(req, res);

  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
    String m = req.getParameter("m");
    if (m != null) m = m.trim();

    // 회원가입
    if (m.startsWith("join")) join(req, res);

    // 로그인
    if (m.startsWith("login")) login(req, res);

  }

  private void logout(HttpServletRequest req, HttpServletResponse res) throws IOException {
    HttpSession session = req.getSession(false);
    session.invalidate();
    res.sendRedirect("/");
  }

  private void login(HttpServletRequest req, HttpServletResponse res) throws IOException {
    HttpSession session = req.getSession();
    String loginid = req.getParameter("loginid");
    String password = req.getParameter("password");

    MemberResponseDTO member = memberService.findMemberByLoginid(loginid, password);
    if (member != null) {
      session.setAttribute("member", member);
      res.sendRedirect("/");
    }
  }

  private void join(HttpServletRequest req, HttpServletResponse res) throws IOException {
    String loginid = req.getParameter("loginid");
    String password = req.getParameter("password");
    String nickname = req.getParameter("nickname");

    MemberRequestDTO member = new MemberRequestDTO(loginid, password, nickname);
    int i = memberService.addMember(member);
    res.sendRedirect("/");
  }
}
