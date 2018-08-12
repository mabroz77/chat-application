package packet.message;

/*
class to send text dto.message
 */
import lombok.*;

@Builder
@Getter
@Setter
@EqualsAndHashCode

public class TextMessage extends BaseMessage {
    String id;
    String from;
    String to;
    String message;
    public TextMessage()
    {
        super.msgType = MessageType.message;
    }

    public TextMessage(String id, String from, String to, String message) {
        this.id = id;
        this.from = from;
        this.to = to;
        this.message = message;
        super.msgType = MessageType.message;
    }
}
