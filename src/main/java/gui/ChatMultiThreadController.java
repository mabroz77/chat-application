package gui;

import javafx.application.Platform;
import packet.message.Ingoing;
import packet.message.TextMessage;

import java.time.LocalDateTime;
import java.util.Date;

/*kontroler dla watku receiver*/

public class ChatMultiThreadController {

    Controller controller;

    public void addTextToListView(TextMessage textMessage) {

        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                String msg = textMessage.getMessage();
                String nick = textMessage.getFrom();
                Date date = new Date();
                controller.addTextToListView(msg, nick, date);
                // kod który ma wykonać coś z GUI
            }

        });
    }

    public void addUserToListView(Ingoing ingoing) {

        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                String nick = ingoing.getNick();
                String status = ingoing.getStatus();

                controller.addUserToListView(nick, status);
                // kod który ma wykonać coś z GUI
            }

        });
    }

    public ChatMultiThreadController(Controller controller) {
        this.controller = controller;
    }
}