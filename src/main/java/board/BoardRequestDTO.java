package board;

import file.AttachFile;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
@Getter
public class BoardRequestDTO {
  private long id;
  private final long memberFk;
  private final String title;
  private final String content;

  public void setId(long id) {
    this.id = id;
  }
}
