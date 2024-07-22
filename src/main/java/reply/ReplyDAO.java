package reply;

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

public class ReplyDAO {
  private DataSource dataSource;
  private Connection con;

  public ReplyDAO() {
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

  public Optional<List<Reply>> selectReplyByBoardPk(long boardPk) {
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    String sql = "select * from reply where board_fk=? order by id desc";
    List<Reply> list = new ArrayList<>();
    try {
      pstmt = con.prepareStatement(sql);
      pstmt.setLong(1, boardPk);

      rs = pstmt.executeQuery();
      while (rs.next()) {
        list.add(Reply.builder()
                .id(rs.getLong("id"))
                .memberFk(rs.getLong("member_fk"))
                .boardFk(rs.getLong("board_fk"))
                .content(rs.getString("content"))
                .writeDate(rs.getTimestamp("write_date"))
                .updateDate(rs.getTimestamp("update_date"))
                .build());
      }
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      closeAll(rs, pstmt);
    }
    return Optional.ofNullable(list);
  }

  public int insertReply(Reply reply) {
    PreparedStatement pstmt = null;
    String sql = "insert into reply(member_fk, board_fk, content) values(?,?,?)";

    int result = 0;
    try {
      pstmt = con.prepareStatement(sql);
      pstmt.setLong(1, reply.getMemberFk());
      pstmt.setLong(2, reply.getBoardFk());
      pstmt.setString(3, reply.getContent());

      result = pstmt.executeUpdate();
      if (result > 0) con.commit();
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      closeAll(null, pstmt);
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
