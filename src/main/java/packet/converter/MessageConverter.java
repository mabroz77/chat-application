package packet.converter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import network.UdpPacket;
import packet.message.*;

import java.io.IOException;

/**
 * Message converter
 */
public class MessageConverter {

    /**
     * {@link ObjectMapper} allows to convert any object to its String representation as JSON.
     */
    private ObjectMapper objectMapper;

    /**
     * Message converter class constructor
     */
    public MessageConverter() {
        objectMapper = new ObjectMapper();
    }

    /**
     * Deserialize UdpPacket into specific message.
     *
     * @param data UpdPacket to be deserialized int message
     * @return Returns converted specific message
     */
    public BaseMessage deserialize(UdpPacket data) {

        String message = new String(data.getValue());
        BaseMessage result = null;

		/*
		1. Przekonwertuj value z klasy UdpPacket do String
		2. Pobierz typ wiadomości "type" z UdpPacket
		3. Przekonwertuj String z pierwszego kroku do wiadomości korzystając z ObjectMapper.
		   Wykorzystaj pobrany typ z poprzedniego kroku do wyboru odpowiedniej klasy
		 */

        switch (data.getType()) {

            case ingoing:
                try {
                    result = objectMapper.readValue(message, Ingoing.class);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            case outgoing:
                try {
                    result = objectMapper.readValue(message, Outgoing.class);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            case message:
                try {
                    result = objectMapper.readValue(message, TextMessage.class);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            case confirmation:
                try {
                    result = objectMapper.readValue(message, MessageConfirmation.class);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
        }
        return result;
    }

    /**
     * Serializes (convert from message to {@link UdpPacket)
     *
     * @param message Message to be converted to {@link UdpPacket}
     * @return Returns {@link UdpPacket}
     */
    public UdpPacket serialize(BaseMessage message) {

        /*
         * Korzystając z klasy ObjectMapper przekonwertuj wiadomość do postaci tekstowej zapisanej w formacie JSON
         * Strona http://www.baeldung.com/jackson-object-mapper-tutorial przedstawia przykład jak to wykonać.
         */

        // 1. Przekonwertuj wiadomość do String za pomocą ObjectMapper
        // 2. Sprawdź typ wiadomości i ustaw type w UdpPacket

        // 3. Pobierz długość tekstu z pierwszego kroku i ustaw pole length w UdpPacket.
        // 4. Przekonwertuj tekst do tablicy byte[] i ustaw pole value w UdpPacket.

        String msg = null;
        try {
            msg = objectMapper.writeValueAsString(message);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        MessageType msgType = message.getMsgType();
        short msgLength = (short) msg.length();
        byte[] value = msg.getBytes();

        // convert dto.message to json
        // convert json to byte array
        // check dto.message byte array length
		 return new UdpPacket(msgType, msgLength, value);
    }
}
