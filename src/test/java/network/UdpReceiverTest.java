package network;

import org.junit.Assert;
import org.junit.Test;
import packet.converter.MessageConverter;
import packet.message.*;
import java.io.IOException;

public class UdpReceiverTest {

    UdpNetworkAccess udpNetworkAccess = new UdpNetworkAccess();
    MessageConverter messageConverter = new MessageConverter();
    UdpPacket udpPacket = new UdpPacket();

    @Test
    public void shouldReceiveOutgoing() throws IOException, InterruptedException {

        udpNetworkAccess.start();
        Outgoing outgoing = new Outgoing("testNickOutgoing");
        udpPacket = messageConverter.serialize(outgoing);
        udpNetworkAccess.sendMessage(outgoing);
        Thread.sleep(3000);
//        BaseMessage msg = udpNetworkAccess.getLastOutgoing(); //zmienilem sprawdzamy ostatnia otrzymana, nie zaleznie od typu
        BaseMessage msg = udpNetworkAccess.getLastMessage();
        Assert.assertEquals(outgoing, msg);
    }

    @Test
    public void shouldReceiveIngoing() throws IOException, InterruptedException {

        udpNetworkAccess.start();
        Ingoing ingoing = new Ingoing("testNickIngoing", "testStatusIngoing", MessageStatus.away);
        udpPacket = messageConverter.serialize(ingoing);
        udpNetworkAccess.sendMessage(ingoing);
        Thread.sleep(1000);
        BaseMessage msg = udpNetworkAccess.getLastMessage();
        Assert.assertEquals(ingoing, msg);
    }

    @Test
    public void shouldReceiveTextMessage() throws IOException, InterruptedException {

        udpNetworkAccess.start();
        TextMessage textMessage = new TextMessage("12345", "fromUnitTestMessage", "toUnitTestMessage", "testTextMessage");
        udpPacket = messageConverter.serialize(textMessage);
        udpNetworkAccess.sendMessage(textMessage);
        Thread.sleep(1000);
        BaseMessage msg = udpNetworkAccess.getLastMessage();
        Assert.assertEquals(textMessage, msg);
    }

    @Test
    public void shouldReceiveMessageConfirmation() throws IOException, InterruptedException {

        udpNetworkAccess.start();
        MessageConfirmation messageConfirmation = new MessageConfirmation("54321", ConfirmatonStatus.received);
        udpPacket = messageConverter.serialize(messageConfirmation);
        udpNetworkAccess.sendMessage(messageConfirmation);
        Thread.sleep(1000);
        BaseMessage msg = udpNetworkAccess.getLastMessage();
        Assert.assertEquals(messageConfirmation, msg);
    }
}