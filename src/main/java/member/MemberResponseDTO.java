package member;

import lombok.Getter;

import java.sql.Date;
import java.util.List;

@Getter
public class MemberResponseDTO {
  private long id;
  private String loginid;
  private String nickname;
  private Date joinDate;
  private Date updateDate;

  private List<String> role;

  public MemberResponseDTO(Member member) {
    id = member.getId();
    loginid = member.getLoginid();
    nickname = member.getNickname();
    joinDate = member.getJoinDate();
    updateDate = member.getUpdateDate();
    role = member.getRole();
  }
}
