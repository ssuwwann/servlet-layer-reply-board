package board;

import file.AttachFile;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
@Getter
public class BoardRequestDTO {
  private long memberFk;
  private String title;
  private String content;
}
