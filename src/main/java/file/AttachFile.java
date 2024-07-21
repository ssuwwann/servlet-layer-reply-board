package file;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Getter
@RequiredArgsConstructor
@ToString
public class AttachFile {
  private long boardFk;
  private final String originalName;
  private final String saveName;
  private final String filePath;

  public void setBoardFk(long boardFk) {
    this.boardFk = boardFk;
  }
}
