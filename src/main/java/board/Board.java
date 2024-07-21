package board;

import file.AttachFile;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.sql.Date;
import java.util.List;

@Getter
@RequiredArgsConstructor
@AllArgsConstructor
public class Board {
  private final long id, memberFk;
  private final String nickname, title, content;
  private final int viewCount, likeCount;
  private final Date writeDate, updateDate;

  private List<String> categoryList;
  //private List<AttachFile> attachFileList;


  public void setCategoryList(List<String> categoryList) {
    this.categoryList = categoryList;
  }
}
