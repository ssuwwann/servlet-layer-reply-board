package reply;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ReplyRequestDTO {
  private long memberFk;
  private long boardFk;
  private String content;
}
