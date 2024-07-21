package board;

import com.google.gson.Gson;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/board/*")
public class BoardController extends HttpServlet {
  private BoardService boardService;

  public BoardController() {
    boardService = BoardService.getInstance();
  }

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
    String uri = req.getRequestURI();
    System.out.println("uri= " + uri);
    int size = req.getParameter("size") == null ? 10 : Integer.parseInt(req.getParameter("size"));
    int page = req.getParameter("page") == null ? 1 : Integer.parseInt(req.getParameter("page"));

    if (uri.equals("/board")) {
      getBoardList(req, res, size, page);
    }

    if (uri.startsWith("/board/content")) {
      String idUri = uri.substring(uri.lastIndexOf("/") + 1);
      if (idUri != null) {
        long id = Long.parseLong(idUri);
        getBoardById(req, res, id);
      }
    }
  }

  private void getBoardList(HttpServletRequest req, HttpServletResponse res, int size, int page) throws ServletException, IOException {
    PrintWriter out = res.getWriter();
    Gson gson = new Gson();
    ResponseDTO<BoardResponseDTO> allBoard = boardService.findAllBoard(size, page);

    out.print(gson.toJson(allBoard));
  }

  private void getBoardById(HttpServletRequest req, HttpServletResponse res, long id) throws ServletException, IOException {
    PrintWriter out = res.getWriter();
    Gson gson = new Gson();
    BoardResponseDTO board = boardService.getBoardById(id);
    System.out.println("board: " + gson.toJson(board));

    out.print(gson.toJson(board));
  }
}
