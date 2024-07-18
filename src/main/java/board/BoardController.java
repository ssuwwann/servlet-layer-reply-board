package board;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import member.auth.Authority;

import java.io.IOException;

@WebServlet("/board")
public class BoardController extends HttpServlet {
  private BoardService boardService;
  public BoardController(){
    boardService = BoardService.getInstance();
  }

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
  }
}
