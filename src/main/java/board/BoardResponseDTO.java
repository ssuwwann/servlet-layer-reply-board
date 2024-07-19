package board;

import lombok.AllArgsConstructor;

import java.sql.Date;
import java.util.List;

@AllArgsConstructor
public class BoardResponseDTO {
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
