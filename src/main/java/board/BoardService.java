package board;

public class BoardService {
  private BoardDAO boardDAO;
  private static final BoardService boardService =  new BoardService();
  private BoardService() {}
  public static BoardService getInstance() {
    return boardService;
  }


}
