package reply;

import lombok.Builder;
import lombok.Getter;

import java.util.Date;

@Getter
@Builder
public class Reply {
  private long id;
  private long memberFk;
  private long boardFk;
  private String content;
  private Date writeDate;
  private Date updateDate;
}
