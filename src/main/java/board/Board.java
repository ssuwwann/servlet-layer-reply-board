package board;

import file.AttachFile;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.sql.Date;
import java.util.List;

@Getter
@AllArgsConstructor
public class Board {
  private long id;
  private long memberFk;
  private String title;
  private String content;
  private int viewCount;
  private int likeCount;
  private Date writeDate;
  private Date updateDate;

  private List<String> categoryList;
  //private List<AttachFile> attachFileList;
}
