package packet.message;

import java.util.HashMap;
import java.util.Map;

public enum MessageType {
	ingoing((byte) 0),
	outgoing((byte) 1),
	message((byte) 2),
	confirmation((byte) 3);

	private static Map<Byte, MessageType> map = new HashMap<>();

	static {
		map.put((byte)0, ingoing);
		map.put((byte)1, outgoing);
		map.put((byte)2, message);
		map.put((byte)3, confirmation);
	}

	private byte type;

	MessageType(byte type) {
		this.type = type;
	}

	public static MessageType deserialize(byte byteCode) {
		return map.get(byteCode);
	}

	public byte serialize() {
		return type;
	}
}
