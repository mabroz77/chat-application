package network;

import packet.converter.PacketConverter;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UdpSender {

    private DatagramSocket socket;
    private InetAddress group;
    private byte[] byteArray;

    public boolean sendMessage(UdpPacket packet) throws IOException { //convert UdpPacket to byte[]
        PacketConverter packetConverter = new PacketConverter();
        byteArray = packetConverter.serialize(packet);
        UdpSender udpSender = new UdpSender();
        return udpSender.sendMessage(byteArray);
    }

    private boolean sendMessage(byte[] data) throws IOException {
        socket = new DatagramSocket();
        group = InetAddress.getByName("239.255.255.255");
        DatagramPacket datagramPacket = new DatagramPacket(data,data.length,group,9999);
        socket.send(datagramPacket);
        socket.close();
        return true;
    }
}
