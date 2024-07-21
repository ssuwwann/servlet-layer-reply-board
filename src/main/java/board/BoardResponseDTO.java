package board;

import lombok.AllArgsConstructor;
import lombok.ToString;

import java.sql.Date;
import java.util.List;

@AllArgsConstructor
@ToString
public class BoardResponseDTO {
  private long id, memberFk;
  private String nickname, title, content;
  private int viewCount, likeCount;
  private Date writeDate, updateDate;

  private List<String> categoryList;
  //private List<AttachFile> attachFileList;
}
