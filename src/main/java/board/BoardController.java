package board;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/board/*")
public class BoardController extends HttpServlet {
  private BoardService boardService;

  public BoardController() {
    boardService = BoardService.getInstance();
  }

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
    int size = req.getParameter("size") == null ? 10 : Integer.parseInt(req.getParameter("size"));
    int page = req.getParameter("page") == null ? 1 : Integer.parseInt(req.getParameter("page"));
    String uri = req.getRequestURI();

    System.out.println("요청 URI:  " + uri);

    if (uri.equals("/board")) res.sendRedirect("/board/list?size=" + size + "&page=" + page);
    if (uri.equals("/board/write")) res.sendRedirect("/board/write");
    if (uri.equals("/board/all")) {
      getBoardList(req, res, page, size);
    }
  }

  private void getBoardList(HttpServletRequest req, HttpServletResponse res, int page, int size) throws ServletException, IOException {
    System.out.println("얼라리");
    List<BoardResponseDTO> allBoard = boardService.findAllBoard(size, page);
    System.out.println(allBoard);
  }
}
