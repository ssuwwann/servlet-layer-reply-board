package reply;

import member.Member;
import member.MemberDAO;

import java.util.ArrayList;
import java.util.List;

public class ReplyService {
  private static ReplyService instance = new ReplyService();
  private static ReplyDAO replyDao;
  private static MemberDAO memberDao;

  private ReplyService() {
    replyDao = new ReplyDAO();
    memberDao = new MemberDAO();
  }

  public static ReplyService getInstance() {
    return instance;
  }

  public int addReply(ReplyRequestDTO reply) {
    Reply r = Reply.builder()
            .memberFk(reply.getMemberFk())
            .boardFk(reply.getBoardFk())
            .content(reply.getContent())
            .build();
    return replyDao.insertReply(r);
  }

  public List<ReplyResponseDTO> getReplyByBoardPk(long boardPk) {
    List<Reply> reply = replyDao.selectReplyByBoardPk(boardPk).orElseThrow();
    List<ReplyResponseDTO> dtoList = new ArrayList<>();
    for (Reply r : reply) {
      Member m = memberDao.selectMemberByMemberId(r.getMemberFk());
      ReplyResponseDTO dto = ReplyResponseDTO.from(r);
      dto.setNickname(m.getNickname());
      dtoList.add(dto);
    }
    return dtoList;
  }
}
