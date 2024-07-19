package member;

public class MemberService {
  private static MemberService instance = new MemberService();
  private static MemberDAO dao;

  private MemberService() {

    dao = new MemberDAO();
  }

  public static MemberService getInstance() {
    return instance;
  }

  public int addMember(RequestMember member) {
    Member m = Member.builder()
            .loginid(member.getLoginid())
            .password(member.getPassword())
            .nickname(member.getNickname()).build();
    return dao.insertMember(m);
  }
}
