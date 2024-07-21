package file;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
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

  public void insertFile(List<AttachFile> files) {
  }
}
