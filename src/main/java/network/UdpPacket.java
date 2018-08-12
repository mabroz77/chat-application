package network;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import packet.message.MessageType;

/*
create TLV to send
 */

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class UdpPacket {
	private MessageType type;
	private int length;
	private byte[] value;
}
