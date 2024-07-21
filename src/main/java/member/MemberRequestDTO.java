package member;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class MemberRequestDTO {
  private String loginid;
  private String password;
  private String nickname;
}
