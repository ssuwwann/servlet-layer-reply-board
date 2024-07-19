package member;

import org.mariadb.jdbc.export.Prepare;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MemberDAO {
  private DataSource dataSource;
  private Connection con;

  public MemberDAO() {
    try {
      Context initContext = new InitialContext();
      Context envContext = (Context) initContext.lookup("java:/comp/env");
      dataSource = (DataSource) envContext.lookup("dbcp");
      con = dataSource.getConnection();
      con.setAutoCommit(false);
      System.out.println("db 연결");
    } catch (NamingException | SQLException e) {
      e.printStackTrace();
    }
  }

  public int insertMember(final Member member) {
    System.out.println("member: " + member);
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    String sql = "insert into member(loginid, password, nickname) values(?,?,?)";

    int pk = 0;
    try {
      pstmt = con.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
      pstmt.setString(1, member.getLoginid());
      pstmt.setString(2, member.getPassword());
      pstmt.setString(3, member.getNickname());
      int result = pstmt.executeUpdate();
      System.out.println("result:  " + result);
      if (result > 0) {
        rs = pstmt.getGeneratedKeys();
        if (rs.next()) {
          pk = rs.getInt(1);
          int addAuthResult = addAuthority(pk);
          System.exit(0);
          if (addAuthResult == 0) con.rollback();
        }
      }
      con.commit();
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return pk;
  }

  private int addAuthority(int pk) {
    PreparedStatement pstmt = null;
    String sql = "insert into authority(member_fk) values(?)";

    int result = 0;
    try {
      pstmt = con.prepareStatement(sql);
      pstmt.setInt(1, pk);

      result = pstmt.executeUpdate();
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return result;
  }
}
