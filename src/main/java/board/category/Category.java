package board.category;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
public class Category {
  private final static String[] cateArr = {"질문", "일상", "카리나"};
  private long id;
  private long board_fk;
  private String category;

  
  public Category(String category) {
    for (String str : cateArr) {
      if (category.equals(str)) this.category = str;
    }
  }
}
