package member.auth;

public enum Authority {
  MEMBER("MEMBER"), MANAGER("MANAGER"), ADMIN("ADMIN");

  private String role;

  Authority(String role) {
    this.role = role;
  }

}
