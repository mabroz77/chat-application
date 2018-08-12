package gui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import lombok.Setter;
import network.UdpNetworkAccess;
import packet.message.TextMessage;
import settings.Settings;

import java.io.IOException;
import java.util.Date;
import java.util.UUID;

public class Controller {

    @Setter
    public UdpNetworkAccess udpNetworkAccess;

    @FXML
    Button sendButton;

    public void sendMessage(ActionEvent actionEvent) {
        String text = textField.getText();
        UUID textId = UUID.randomUUID();
        TextMessage textMessage = new TextMessage(textId.toString(), Settings.userDetails.getNick(), "to", text);
        try {
            udpNetworkAccess.sendMessage(textMessage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private TextField textField;

    @FXML
    private ListView messagesField;

    @FXML
    private ListView usersField;


    public ObservableList<String> items = FXCollections.observableArrayList("Welcome chat");
    public ObservableList<String> users = FXCollections.observableArrayList();

    public void  addUserToListView(String nick, String status){
        if (!users.equals(usersField.getItems())){
            usersField.setItems(users);
        }
        users.add(nick + " " + status);
    }

    public void addTextToListView(String msg, String nick, Date date) {
        if (!items.equals(messagesField.getItems())) {
            messagesField.setItems(items);
        }
        items.add(date.toString()+" "+"["+nick+"]:"+" "+ msg);
//        items.add(date.toString());
//        items.add(msg);

    }

}
