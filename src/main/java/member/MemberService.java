package member;

import exception.login.MemberNotFoundException;

public class MemberService {
  private static MemberService instance = new MemberService();
  private static MemberDAO dao;

  private MemberService() {
    dao = new MemberDAO();
  }

  public static MemberService getInstance() {
    return instance;
  }

  public int addMember(MemberRequestDTO member) {
    Member m = Member.builder()
            .loginid(member.getLoginid())
            .password(member.getPassword())
            .nickname(member.getNickname()).build();
    return dao.insertMember(m);
  }

  public MemberResponseDTO findMemberByLoginid(String loginid, String password) {
    Member member = dao.selectMemberByLogindid(loginid)
            .orElseThrow(() -> MemberNotFoundException.memberNotFoundException("MEMBER_NOT_FOUND"));

    if (!password.equals(member.getPassword())) {
      throw MemberNotFoundException.wrongPasswordException("WRONG_PASSWORD");
    }

    return new MemberResponseDTO(member);
  }
}
