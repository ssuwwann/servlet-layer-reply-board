package board;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class BoardDAO {
  private DataSource dataSource;
  private Connection con;
  public BoardDAO() {
    try {
      Context initContext = new InitialContext();
      Context envContext = (Context) initContext.lookup("java:/comp/env");
      dataSource = (DataSource) envContext.lookup("dbcp");
      con = dataSource.getConnection();
      System.out.println("db 연결");
    } catch (NamingException | SQLException e) {
      e.printStackTrace();
    }
  }
}
