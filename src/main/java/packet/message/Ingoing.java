package packet.message;

/*
Class to inform dto.user is log on
 */
import lombok.*;

@Builder
@Getter
@Setter
@EqualsAndHashCode
public class Ingoing extends BaseMessage {
    String nick;
    String status;
    MessageStatus messageStatus;

    public Ingoing(String nick, String status, MessageStatus messageStatus) {
        this.nick = nick;
        this.status = status;
        this.messageStatus = messageStatus;
        super.msgType = MessageType.ingoing;
    }

    public Ingoing()
    {
        super.msgType = MessageType.ingoing;
    }


}
