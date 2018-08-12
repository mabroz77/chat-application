package network;

import gui.ChatMultiThreadController;
import lombok.Getter;
import lombok.Setter;
import packet.converter.MessageConverter;
import packet.message.*;
import java.io.IOException;

@Setter
@Getter
public class UdpNetworkAccess {
    private UdpSender sender = new UdpSender();
    private UdpReceiver receiver;
    @Getter
    private BaseMessage lastMessage; //zmienilem na last message, poniewaz w tescie bedziemy czytac ostatnia, niezaleznie od typu wiadomosci - GR

    private ChatMultiThreadController chatMultiThreadController;

    public UdpNetworkAccess(ChatMultiThreadController chatMultiThreadController) {
        receiver = new UdpReceiver(msg -> receiveMessage((BaseMessage) msg));
        this.chatMultiThreadController = chatMultiThreadController;
    }

    public UdpNetworkAccess() {
        receiver = new UdpReceiver(msg -> receiveMessage((BaseMessage) msg));
    }   //konstruktor dla testow

    public boolean sendMessage(BaseMessage message) throws IOException {
        UdpPacket packet = new MessageConverter().serialize(message);
        return sender.sendMessage(packet);
    }

    public void start() {
        receiver.start();
    }

    public void receiveMessage(BaseMessage message) {

        lastMessage = message;  // na potrzeby testu tylko lastMessage - GR

        if (message.getMsgType().equals(MessageType.message)) {
            chatMultiThreadController.addTextToListView((TextMessage) message);
        }
        if (message.getMsgType().equals(MessageType.ingoing)){
            chatMultiThreadController.addUserToListView((Ingoing) message);
        }

        // ## Tutaj dodaj kod który ma się wykonać po odebraniu pakietu
    }

    public boolean confirmMessage(MessageConfirmation confirmation) {
        return false;
    }

    public boolean leaveChat(Outgoing message) {
        return false;
    }

    public boolean joinChat(Ingoing message) {
        return false;
    }

}