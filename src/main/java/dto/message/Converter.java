package dto.message;

import packet.message.TextMessage;
import java.util.Date;
import java.util.UUID;

public class Converter {

    public Message convertTextToMessage(TextMessage textMessage){
        Message message = new Message();
        message.setIdentifier(UUID.fromString(textMessage.getId()));
        message.setText(textMessage.getMessage());
        message.setFrom(textMessage.getFrom());
        message.setTo(textMessage.getTo());
        message.setDate(new Date());
        return message;
    }

    public TextMessage convertMessageToText(Message message){
        TextMessage textMessage = new TextMessage();
        textMessage.setId(String.valueOf(message.getIdentifier()));
        textMessage.setMessage(message.getText());
        textMessage.setFrom(message.getFrom());
        textMessage.setTo(message.getTo());
        return textMessage;
    }
}
