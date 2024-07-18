package board;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class BoardController extends HttpServlet {
  private BoardService boardService;

  public BoardController() {
    boardService = BoardService.getInstance();
  }

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
    String uri = req.getRequestURI();
    System.out.println("요청 URI:  " + uri);
    res.sendRedirect("/board/list");
  }
}
