package board.category;

public class CategorySQL {
  public static final String SELECT_CATEGORY_BY_BOARDFK = "select * from category where board_fk = ?";
  public static final String INSERT_CATEGORY = "insert into category(board_fk, category) values(?,?)";
  public static final String UPDATE_CATEGORY = "update category set category=? where board_fk=?";
}
