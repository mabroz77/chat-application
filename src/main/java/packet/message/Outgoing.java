package packet.message;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
@EqualsAndHashCode

public class Outgoing extends BaseMessage {
    String nick;
    public Outgoing()
    {
        super.msgType = MessageType.outgoing;
    }

    public Outgoing(String nick) {
        this.nick = nick;
        super.msgType = MessageType.outgoing;
    }
}
