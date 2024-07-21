package board;

import file.AttachFile;
import file.FileDAO;

import java.util.ArrayList;
import java.util.List;

public class BoardService {
  private BoardDAO boardDAO;
  private FileDAO fileDAO;
  private static final BoardService boardService = new BoardService();

  private BoardService() {
    boardDAO = new BoardDAO();
    fileDAO = new FileDAO();
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
      list.add(new BoardResponseDTO(board.getId(), board.getMemberFk(), board.getNickname(), board.getTitle(), board.getContent(), board.getViewCount(), board.getLikeCount()
              , board.getWriteDate(), board.getUpdateDate(), board.getCategoryList()));
    }

    return ResponseDTO.<BoardResponseDTO>withAll()
            .list(list)
            .pageSize(size)
            .currentPage(page)
            .totalElements(getCount())
            .build();
  }


  public BoardResponseDTO getBoardById(long id) {
    Board board = boardDAO.selectBoardById(id).orElseThrow();
    List<AttachFile> attachFiles = fileDAO.selectFileByBoardFk(board.getId());
    System.out.println("boarad service 왕 시발 " + attachFiles);
    BoardResponseDTO dto = new BoardResponseDTO(board.getId(), board.getMemberFk(), board.getNickname(), board.getTitle(), board.getContent(), board.getViewCount(),
            board.getLikeCount(), board.getWriteDate(), board.getUpdateDate(), board.getCategoryList());
    dto.setAttachFileList(attachFiles);
    System.out.println(dto);
    return dto;
  }

  public long addBoard(BoardRequestDTO board) {
    return boardDAO.insertBoard(board);
  }

  public int getCount() {
    return boardDAO.selectCount();
  }
}
