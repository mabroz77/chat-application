package packet.message;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;


@Getter
public abstract class BaseMessage {
    @JsonIgnore
    protected MessageType msgType;
}
