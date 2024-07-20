package board;

import java.util.ArrayList;
import java.util.List;

public class BoardService {
  private BoardDAO boardDAO;
  private static final BoardService boardService = new BoardService();

  private BoardService() {
    boardDAO = new BoardDAO();
  }

  public static BoardService getInstance() {
    return boardService;
  }

  public ResponseDTO<BoardResponseDTO> findAllBoard(int size, int page) {
    int startNum = (page - 1) * size;
    startNum = startNum < 0 ? 0 : startNum;
    List<Board> boards = boardDAO.selectAllBoard(size, startNum).orElseThrow();

    List<BoardResponseDTO> list = new ArrayList<>();
    for (Board board : boards) {
      list.add(new BoardResponseDTO(board.getId(), board.getMemberFk(), board.getTitle(), board.getContent(), board.getViewCount(), board.getLikeCount()
              , board.getWriteDate(), board.getUpdateDate(), board.getCategoryList()));
    }

    return ResponseDTO.<BoardResponseDTO>withAll()
            .list(list)
            .pageSize(size)
            .currentPage(page)
            .totalElements(getCount())
            .build();
  }

  public int getCount() {
    return boardDAO.selectCount();
  }

}
