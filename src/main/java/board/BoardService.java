package board;

import util.paging.Paging;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class BoardService {
  private BoardDAO boardDAO;
  private static final BoardService boardService = new BoardService();

  private BoardService() {
    boardDAO = new BoardDAO();
  }

  public static BoardService getInstance() {
    return boardService;
  }

  public List<BoardResponseDTO> findAllBoard(int size, int page) {
    System.out.println("board service");
    Paging paging = new Paging(size, page);
    List<Board> boards = boardDAO.selectAllBoard(paging).orElseThrow();
    System.out.println("board service list " + boards);

    List<BoardResponseDTO> list = new ArrayList<>();
    for (Board board : boards) {
      list.add(new BoardResponseDTO(board.getId(), board.getMemberFk(), board.getTitle(), board.getContent(), board.getViewCount(), board.getLikeCount(), board.getWriteDate(), board.getUpdateDate(), board.getCategoryList()));
    }
    return list;
  }

}
