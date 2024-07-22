package reply;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.sql.Date;
import java.sql.Timestamp;


@Getter
@Builder
@ToString
public class Reply {
  private long id;
  private long memberFk;
  private long boardFk;
  private String content;
  private Timestamp writeDate;
  private Timestamp updateDate;

}
