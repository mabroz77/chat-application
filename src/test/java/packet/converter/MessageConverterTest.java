package packet.converter;

import network.UdpPacket;
import org.junit.Assert;
import org.junit.Test;
import packet.message.*;

public class MessageConverterTest {

    MessageConverter messageConverter = new MessageConverter();

    @Test
    public void shouldSerializeToDeserializeForIngoing() {

        Ingoing ingoing = new Ingoing("nick", "jestemTestem", MessageStatus.available);
        UdpPacket udpPacket = messageConverter.serialize(ingoing);
        Ingoing result = (Ingoing) messageConverter.deserialize(udpPacket);
        Assert.assertEquals(ingoing.getNick(), result.getNick());
        Assert.assertEquals(ingoing.getStatus(), result.getStatus());
        Assert.assertEquals(ingoing.getMessageStatus(), result.getMessageStatus());
    }

    @Test
    public void shouldSerializeToDeserializeForOutgoing() {
        Outgoing outgoing = new Outgoing("nick");
        UdpPacket udpPacket = messageConverter.serialize(outgoing);
        Outgoing result = (Outgoing) messageConverter.deserialize(udpPacket);
        Assert.assertEquals(outgoing.getNick(), result.getNick());
    }

    @Test
    public void shouldSerializeToDeserializeForMessageConfirmation() {
        MessageConfirmation messageConfirmation = new MessageConfirmation("msgid", ConfirmatonStatus.read);
        UdpPacket udpPacket = messageConverter.serialize(messageConfirmation);
        MessageConfirmation result = (MessageConfirmation) messageConverter.deserialize(udpPacket);
        Assert.assertEquals(messageConfirmation.getMsgid(), result.getMsgid());
        Assert.assertEquals(messageConfirmation.getConfirmationStatus(), result.getConfirmationStatus());
    }

    @Test
    public void shouldSerializeToDeserializeForTextMessage() {
        TextMessage textMessage = new TextMessage("msgid", "fromTest", "toTest", "testMessage");
        UdpPacket udpPacket = messageConverter.serialize(textMessage);
        TextMessage result = (TextMessage) messageConverter.deserialize(udpPacket);
        Assert.assertEquals(textMessage.getId(), result.getId());
        Assert.assertEquals(textMessage.getFrom(), result.getFrom());
        Assert.assertEquals(textMessage.getTo(), result.getTo());
        Assert.assertEquals(textMessage.getMessage(), result.getMessage());
    }
}
