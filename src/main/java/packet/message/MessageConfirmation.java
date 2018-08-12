package packet.message;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
@EqualsAndHashCode

public class MessageConfirmation  extends BaseMessage{
    String msgid;
    ConfirmatonStatus confirmationStatus;
    public MessageConfirmation()
    {
        super.msgType = MessageType.confirmation;
    }

    public MessageConfirmation(String msgid, ConfirmatonStatus confirmationStatus) {
        this.msgid = msgid;
        this.confirmationStatus = confirmationStatus;
        super.msgType = MessageType.confirmation;
    }
}
