package board;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.ToString;

import java.sql.Date;
import java.util.List;

@AllArgsConstructor
@Builder
@ToString
public class BoardResponseDTO {
  private long id, memberFk;
  private String title, content;
  private int viewCount, likeCount;
  private Date writeDate, updateDate;

  private List<String> categoryList;
  //private List<AttachFile> attachFileList;
}
