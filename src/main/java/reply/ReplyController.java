package reply;

import com.google.gson.Gson;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import member.MemberResponseDTO;

import java.io.*;
import java.util.List;

@WebServlet("/reply/*")
@MultipartConfig()
public class ReplyController extends HttpServlet {
  private ReplyService replyService;

  public ReplyController() {
    replyService = ReplyService.getInstance();
  }

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
    String uri = req.getRequestURI();
    Gson gson = new Gson();
    PrintWriter out = res.getWriter();
    long boardPk = Long.parseLong(uri.substring(uri.lastIndexOf("/") + 1));
    List<ReplyResponseDTO> replyByBoardPk = replyService.getReplyByBoardPk(boardPk);
    out.print(gson.toJson(replyByBoardPk));
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
    addReply(req, res);
  }

  private void addReply(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
    HttpSession session = req.getSession(false);
    MemberResponseDTO member = (MemberResponseDTO) session.getAttribute("member");
    Gson gson = new Gson();
    PrintWriter out = res.getWriter();
    BufferedReader reader = req.getReader();
    StringBuilder sb = new StringBuilder();
    String line;
    while ((line = reader.readLine()) != null) {
      sb.append(line);
    }
    String jsonData = sb.toString();
    ReplyRequestDTO reply = gson.fromJson(jsonData, ReplyRequestDTO.class);
    reply.setMemberFk(member.getId());

    int result = replyService.addReply(reply);
    if (result > 0) out.print("{\"result\":\"1\"}");
    else out.print("{\"result\":\"0\"}");
  }

}
