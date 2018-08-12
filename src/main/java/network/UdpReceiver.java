package network;

import com.fasterxml.jackson.databind.exc.UnrecognizedPropertyException;
import packet.converter.MessageConverter;
import packet.converter.PacketConverter;
import packet.message.BaseMessage;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.util.function.Consumer;

public class UdpReceiver extends Thread {
    MessageConverter messageConverter = new MessageConverter();
    Consumer<BaseMessage> consumer;
    protected MulticastSocket socket = null;
    protected byte[] array = new byte[256];
    PacketConverter packetConverter;
    UdpPacket udpPacket;

    public UdpReceiver(Consumer<BaseMessage> consumer) {
        super();
        this.consumer = consumer;
        packetConverter = new PacketConverter();
    }

    @Override
    public void run() {

        try {
            socket = new MulticastSocket(9999);
            InetAddress group = InetAddress.getByName("239.255.255.255");
            socket.joinGroup(group);
            while (true) {
                try {
                    DatagramPacket packet = new DatagramPacket(array, array.length);
                    socket.receive(packet);
                    udpPacket = packetConverter.deserialize(packet.getData());
                    BaseMessage message = messageConverter.deserialize(udpPacket);
                    consumer.accept(message);
                } catch (UnrecognizedPropertyException f) {
                    f.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (NullPointerException g) {
                    g.printStackTrace();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}


