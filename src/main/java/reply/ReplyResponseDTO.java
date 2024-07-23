package reply;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.sql.Timestamp;

@Getter
@Builder
@ToString
public class ReplyResponseDTO {
  private long id;
  private long memberFk;
  private String content;
  private Timestamp writeDate;
  private Timestamp updateDate;
  private String nickname;

  public static ReplyResponseDTO from(Reply reply) {
    return ReplyResponseDTO.builder()
            .id(reply.getId())
            .memberFk(reply.getMemberFk())
            .content(reply.getContent())
            .writeDate(reply.getWriteDate())
            .updateDate(reply.getUpdateDate())
            .build();
  }

  public void setNickname(String nickname) {
    this.nickname = nickname;
  }
}
