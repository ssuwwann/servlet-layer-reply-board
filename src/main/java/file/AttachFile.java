package file;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class AttachFile {
  private long boardFk;
  private String originalName;
  private String saveName;
  private String filePath;

  public void setBoardFk(long boardFk) {
    this.boardFk = boardFk;
  }
}
