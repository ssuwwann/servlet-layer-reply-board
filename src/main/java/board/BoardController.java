package board;

import com.google.gson.Gson;
import file.AttachFile;
import file.FileService;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/board/*")
@MultipartConfig()
public class BoardController extends HttpServlet {
  private BoardService boardService;
  private FileService fileService;

  public BoardController() {
    boardService = BoardService.getInstance();
    fileService = FileService.getInstance();
  }

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
    String uri = req.getRequestURI();
    System.out.println("uri= " + uri);
    int size = req.getParameter("size") == null ? 10 : Integer.parseInt(req.getParameter("size"));
    int page = req.getParameter("page") == null ? 1 : Integer.parseInt(req.getParameter("page"));

    if (uri.equals("/board")) {
      System.out.println("게시글 목록 가져오기");
      getBoardList(req, res, size, page);
    }

    if (uri.startsWith("/board/content")) {
      System.out.println("게시글 가져오기");
      String idUri = uri.substring(uri.lastIndexOf("/") + 1);
      if (idUri != null) {
        long id = Long.parseLong(idUri);
        getBoardById(req, res, id);
      }
    }
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
    PrintWriter out = res.getWriter();
    Gson gson = new Gson();

    List<AttachFile> attachFile = new ArrayList<>();
    long fk = Long.parseLong(req.getParameter("fk"));
    String title = req.getParameter("title");
    String content = req.getParameter("content");
    String files = req.getParameter("files");

    // 파일이 있을 때
    if (files.length() != 2) {
      String[] jsonObjects = files.split("(?<=}),(?=\\{)");
      for (String jsonObject : jsonObjects) {
        String s = jsonObject.replaceAll("[\\[\\]]", "");
        attachFile.add(gson.fromJson(s, AttachFile.class));
      }
      long boardPk = boardService.addBoard(new BoardRequestDTO(fk, title, content));
      for (AttachFile file : attachFile) {
        file.setBoardFk(boardPk);
        fileService.addFile(file);
      }
    } else {
      long boardPk = boardService.addBoard(new BoardRequestDTO(fk, title, content));
    }
    out.print(1);
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

    out.print(gson.toJson(board));
  }
}
