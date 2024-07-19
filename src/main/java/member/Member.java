package member;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.Date;
import java.util.List;

@Getter
@ToString
@Builder
public class Member {
  private int id;
  private String loginid;
  private String password;
  private String nickname;
  private Date joinDate;
  private Date updateDate;

  private List<String> role;

}
