package member;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.sql.Date;
import java.util.List;

@Getter
@Builder
@ToString
public class Member {
  private long id;
  private String loginid;
  private String password;
  private String nickname;
  private Date joinDate;
  private Date updateDate;

  private List<String> role;
}
