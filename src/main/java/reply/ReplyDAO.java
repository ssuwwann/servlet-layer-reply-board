package reply;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

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

  public void insertReply(Reply reply) {
    PreparedStatement pstmt = null;
    String sql = "insert into reply(member_fk, board_fk, content) values(?,?,?)";

    try {
      pstmt = con.prepareStatement(sql);
      pstmt.setLong(1, reply.getMemberFk());
      pstmt.setLong(2, reply.getBoardFk());
      pstmt.setString(3, reply.getContent());

      if (pstmt.executeUpdate() > 0) con.commit();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }
}
