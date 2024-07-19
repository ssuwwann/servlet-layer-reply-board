package exception.login;

public class MemberNotFoundException extends RuntimeException {
  private final ErrorCode errorCode;

  private MemberNotFoundException(String msg, ErrorCode errorCode) {
    super(msg);
    this.errorCode = errorCode;
  }

  public static MemberNotFoundException memberNotFoundException(String msg) {
    return new MemberNotFoundException(msg, ErrorCode.MEMBER_NOT_FOUND);
  }

  public static MemberNotFoundException wrongPasswordException(String msg) {
    return new MemberNotFoundException(msg, ErrorCode.WRONG_PASSWORD);
  }

  public enum ErrorCode {
    MEMBER_NOT_FOUND,
    WRONG_PASSWORD
  }
}