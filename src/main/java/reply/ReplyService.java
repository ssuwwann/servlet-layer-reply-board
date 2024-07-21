package reply;

public class ReplyService {
  private static ReplyService instance = new ReplyService();
  private static ReplyDAO dao;

  private ReplyService() {
    dao = new ReplyDAO();
  }

  public static ReplyService getInstance() {
    return instance;
  }

  public void addReply(ReplyRequestDTO reply) {
    Reply r = Reply.builder()
            .memberFk(reply.getMemberFk())
            .boardFk(reply.getBoardFk())
            .content(reply.getContent())
            .build();
    dao.insertReply(r);
  }

}
