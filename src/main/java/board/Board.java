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
  private final long id;
  private final long memberFk;
  private final String title;
  private final String content;
  private final int viewCount;
  private final int likeCount;
  private final Date writeDate;
  private final Date updateDate;

  private List<String> categoryList;
  //private List<AttachFile> attachFileList;


  public void setCategoryList(List<String> categoryList) {
    this.categoryList = categoryList;
  }
}
