package member;

import board.BoardDAO;
import db.BaseDAO;

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

public class MemberDAO extends BaseDAO {
  private Connection con;

  public MemberDAO() {
    try {
      con = getConnection();
      con.setAutoCommit(false);
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  public int insertMember(final Member member) {
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
      if (result > 0) {
        rs = pstmt.getGeneratedKeys();
        if (rs.next()) pk = rs.getInt(1);
        int addAuthResult = insertAuthority(pk);
        if (addAuthResult == 0) con.rollback();
      }
      con.commit();
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      BoardDAO.closeAll(null, pstmt);
    }
    return pk;
  }

  private int insertAuthority(int pk) {
    PreparedStatement pstmt = null;
    String sql = "insert into authority(member_fk) values(?)";

    int result = 0;
    try {
      pstmt = con.prepareStatement(sql);
      pstmt.setInt(1, pk);

      result = pstmt.executeUpdate();
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      BoardDAO.closeAll(null, pstmt);
    }
    return result;
  }

  private List<String> selectAuthorityByMemberPk(long pk) {
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    String sql = "select role from authority where member_fk = ?";

    List<String> list = new ArrayList<String>();
    try {
      pstmt = con.prepareStatement(sql);
      pstmt.setLong(1, pk);

      rs = pstmt.executeQuery();
      while (rs.next()) {
        list.add(rs.getString("role"));
      }
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      BoardDAO.closeAll(rs, pstmt);
    }

    return list;
  }

  public Optional<Member> selectMemberByLogindid(String loginid) {
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    String sql = "select * from member where loginid = ?";
    Member member = null;

    try {
      pstmt = con.prepareStatement(sql);
      pstmt.setString(1, loginid);

      rs = pstmt.executeQuery();
      if (rs.next()) {
        member = new Member(rs.getLong("id"), rs.getString("loginid"), rs.getString("password"),
                rs.getString("nickname"), rs.getDate("join_date"), rs.getDate("update_date"),
                selectAuthorityByMemberPk(rs.getLong("id")));
      }
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      BoardDAO.closeAll(rs, pstmt);
    }

    return Optional.ofNullable(member);
  }

  public Member selectMemberByMemberId(long id) {
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    String sql = "select * from member where id = ?";
    Member member = null;

    try {
      pstmt = con.prepareStatement(sql);
      pstmt.setLong(1, id);

      rs = pstmt.executeQuery();
      if (rs.next()) {
        member = new Member(rs.getLong("id"), rs.getString("loginid"), rs.getString("password"),
                rs.getString("nickname"), rs.getDate("join_date"), rs.getDate("update_date"),
                selectAuthorityByMemberPk(rs.getLong("id")));
      }
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      BoardDAO.closeAll(rs, pstmt);
    }
    return member;
  }
}
