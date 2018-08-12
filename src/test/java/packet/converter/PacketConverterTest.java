package packet.converter;

import network.UdpPacket;
import org.junit.Assert;
import org.junit.Test;
import packet.message.MessageType;


public class PacketConverterTest {

    @Test
    public void shouldDeserialize() {
        byte[] array = {0, 2, 0, 1, 2};
        byte[] value = {1, 2};
        PacketConverter packetConverter = new PacketConverter();
        UdpPacket udpPacket = packetConverter.deserialize(array);
        Assert.assertEquals(MessageType.ingoing, udpPacket.getType());
        Assert.assertEquals(512, udpPacket.getLength());
        Assert.assertArrayEquals(value, udpPacket.getValue());
    }

    @Test
    public void serializeToDeserialize() {
        byte[] value = {1, 2};
        UdpPacket udpPacket = new UdpPacket(MessageType.outgoing, 2, value);
        PacketConverter packetConverter = new PacketConverter();
        UdpPacket udpPacket2 = packetConverter.deserialize(packetConverter.serialize(udpPacket));
        Assert.assertEquals(udpPacket, udpPacket2);
    }
}
