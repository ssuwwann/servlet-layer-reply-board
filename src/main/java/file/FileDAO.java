package file;

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

public class FileDAO {
  private DataSource dataSource;
  private Connection con;

  public FileDAO() {
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

  public List<AttachFile> selectFileByBoardFk(long boardfk) {
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    String sql = "select * from file where board_fk = ?";

    List<AttachFile> list = new ArrayList();
    try {
      pstmt = con.prepareStatement(sql);
      pstmt.setLong(1, boardfk);

      rs = pstmt.executeQuery();
      while (rs.next()) {
        list.add(new AttachFile(rs.getString("original_name"), rs.getString("save_name"), rs.getString("file_path")));
      }
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
    return list;
  }

  public void insertFile(AttachFile file) {
    PreparedStatement pstmt = null;
    String sql = "insert into file(board_fk, original_name, save_name, file_path) values(?,?,?,?)";

    try {
      pstmt = con.prepareStatement(sql);
      pstmt.setLong(1, file.getBoardFk());
      pstmt.setString(2, file.getOriginalName());
      pstmt.setString(3, file.getSaveName());
      pstmt.setString(4, file.getFilePath());

      if (pstmt.executeUpdate() > 0) con.commit();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }
}
