package member;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class RequestMember {
  private String loginid;
  private String password;
  private String nickname;
}
