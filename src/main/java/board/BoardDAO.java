package board;

import board.category.CategorySQL;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class BoardDAO {
  private DataSource dataSource;
  private Connection con;

  public BoardDAO() {
    try {
      Context initContext = new InitialContext();
      Context envContext = (Context) initContext.lookup("java:/comp/env");
      dataSource = (DataSource) envContext.lookup("dbcp");
      con = dataSource.getConnection();
      con.setAutoCommit(false);
    } catch (NamingException | SQLException e) {
      e.printStackTrace();
    }
  }

  public Optional<List<Board>> selectAllBoard(int size, int startNum) {
    PreparedStatement pstmtForBoard = null;
    ResultSet rsForBoard = null;

    PreparedStatement pstmtForCategory = null;
    ResultSet rsForCategory = null;

    List<Board> boardList = new ArrayList<>();
    String sql = "select b.id pk, b.member_fk member_fk, m.nickname nickname, b.title title, b.content content, b.view_count view_count,b.like_count like_count, b.write_date write_date, b.update_date update_date, c.category\n" +
            "from board b join category c on b.id = c.board_fk\n" +
            "join member m on m.id = b.member_fk\n" +
            "group by b.id\n" +
            "order by b.id desc limit ?,?";
    try {
      pstmtForBoard = con.prepareStatement(sql);
      pstmtForBoard.setInt(1, startNum);
      pstmtForBoard.setInt(2, size);

      pstmtForCategory = con.prepareStatement(CategorySQL.SELECT_CATEGORY_BY_BOARDFK);
      rsForBoard = pstmtForBoard.executeQuery();
      while (rsForBoard.next()) {
        List<String> categoryList = new ArrayList<>();
        long id = rsForBoard.getLong("pk");

        pstmtForCategory.setLong(1, id);

        rsForCategory = pstmtForCategory.executeQuery();
        while (rsForCategory.next()) {
          categoryList.add(rsForCategory.getString("category"));
        }

        boardList.add(new Board(id, rsForBoard.getLong("member_fk"), rsForBoard.getString("nickname"), rsForBoard.getString("title"),
                rsForBoard.getString("content"), rsForBoard.getInt("view_count"),
                rsForBoard.getInt("like_count"), rsForBoard.getDate("write_date"), rsForBoard.getDate("update_date"), categoryList));
      }
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      closeAll(rsForBoard, pstmtForBoard);
      closeAll(rsForCategory, pstmtForCategory);
    }
    return Optional.ofNullable(boardList);
  }

  public Optional<Board> selectBoardById(long id) {
    PreparedStatement pstmtForBoard = null;
    ResultSet rsForBoard = null;

    PreparedStatement pstmtForCategory = null;
    ResultSet rsForCategory = null;

    Board board = null;

    String sql = "select b.id pk,  b.member_fk member_fk, m.nickname nickname, b.title title, b.content content, b.view_count view_count,b.like_count like_count, " +
            " b.write_date write_date, b.update_date update_date, c.category\n" +
            "from board b join category c on b.id = c.board_fk\n" +
            "join member m on m.id = b.member_fk\n" +
            "where b.id = ?";
    try {
      pstmtForBoard = con.prepareStatement(sql);
      pstmtForBoard.setLong(1, id);

      pstmtForCategory = con.prepareStatement(CategorySQL.SELECT_CATEGORY_BY_BOARDFK);
      pstmtForCategory.setLong(1, id);
      rsForCategory = pstmtForCategory.executeQuery();

      rsForBoard = pstmtForBoard.executeQuery();
      if (rsForBoard.next()) {
        List<String> categoryList = new ArrayList<>();
        while (rsForCategory.next()) {
          categoryList.add(rsForCategory.getString("category"));
        }
        board = new Board(id, rsForBoard.getLong("member_fk"), rsForBoard.getString("nickname"), rsForBoard.getString("title"),
                rsForBoard.getString("content"), rsForBoard.getInt("view_count"),
                rsForBoard.getInt("like_count"), rsForBoard.getDate("write_date"), rsForBoard.getDate("update_date"), categoryList);
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }

    return Optional.ofNullable(board);
  }

  /**
   * 카테고리, 파일이 있는 경우 글이 저장되면 pk를 반환해야 한다.
   * 트랜잭션 1. 게시글 저장 2. 파일 저장
   */
  public long insertBoard(BoardRequestDTO board) {
    PreparedStatement pstmtForBoard = null;
    ResultSet rsForBoard = null;

    PreparedStatement pstmtForCategory = null;
    ResultSet rsForCategory = null;

    String sql = "insert into board(member_fk, title, content) values(?,?,?)";
    long pk = 0;
    int result = 0;
    try {
      pstmtForBoard = con.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
      pstmtForBoard.setLong(1, board.getMemberFk());
      pstmtForBoard.setString(2, board.getTitle());
      pstmtForBoard.setString(3, board.getContent());

      result = pstmtForBoard.executeUpdate();
      if (result > 0) {
        rsForBoard = pstmtForBoard.getGeneratedKeys();
        if (rsForBoard.next()) pk = rsForBoard.getLong(1);
        pstmtForCategory = con.prepareStatement(CategorySQL.INSERT_CATEGORY);
        pstmtForCategory.setLong(1, pk);
        pstmtForCategory.setString(2, "카리나");

        if (pstmtForCategory.executeUpdate() == 0)
          con.rollback();
      }
      con.commit();
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return pk;
  }

  public int selectCount() {
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    String sql = "select count(*) from board where delete_yn =1";

    int result = 0;
    try {
      pstmt = con.prepareStatement(sql);

      rs = pstmt.executeQuery();
      if (rs.next()) {
        result = rs.getInt(1);
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return result;
  }

  public static void closeAll(ResultSet rs, PreparedStatement pstmt) {
    if (rs != null) {
      try {
        rs.close();
        pstmt.close();
      } catch (SQLException e) {
        e.printStackTrace();
      }
    }
  }

}
