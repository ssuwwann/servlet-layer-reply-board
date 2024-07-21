package board;

import file.AttachFile;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.sql.Date;
import java.util.List;

@RequiredArgsConstructor
@ToString
@Getter
public class BoardResponseDTO {
  private final long id, memberFk;
  private final String nickname, title, content;
  private final int viewCount, likeCount;
  private final Date writeDate, updateDate;
  private final List<String> categoryList;
  private List<AttachFile> attachFileList;

  public void setAttachFileList(List<AttachFile> attachFileList) {
    this.attachFileList = attachFileList;
  }
}
