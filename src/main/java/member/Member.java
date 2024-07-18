package member;

import member.auth.Authority;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Member {
  private int id;
  private String loginid;
  private String password;
  private String nickname;
  private Date joinDate;
  private Date updateDate;

  private List<String> role;

}
