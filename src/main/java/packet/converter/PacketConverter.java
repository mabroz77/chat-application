package packet.converter;

import network.UdpPacket;
import packet.message.MessageType;
import java.io.ByteArrayOutputStream;
import java.nio.ByteBuffer;
import java.util.Arrays;

public class PacketConverter {

	/**
	 * Metoda konwertuje tablicę byte[] do obiektu UdpPacket
	 *
	 * @param data
	 * 		Tablica byte[] do konwersji
	 *
	 * @return Zwraca obiekt UdpPacket
	 */
	public UdpPacket deserialize(byte[] data) {

		UdpPacket udpPacket = new UdpPacket();
		udpPacket.setType(MessageType.deserialize(data[0]));
		byte [] packetLength = Arrays.copyOfRange(data, 1,3 );
		ByteBuffer wrapped = ByteBuffer.wrap(packetLength);
		udpPacket.setLength(wrapped.getShort());
		byte [] packetData = Arrays.copyOfRange(data, 3, data.length );
		udpPacket.setValue(packetData);

		/**
		 * Przekonwertuj tablicę byte[] do obiektu UdpPacket
		 * 1. na pierwszym bajcie masz informację o typie pakietu
		 * 2. na drugim i trzecim bajcie masz informację o długości danych
		 * 3. na 3 -> n bajtów masz właściwe dane (values)
		 * Korzystając z Arrays.copyOfRange pobierz właściwe bajty z tablicy i utwórz pola w klasie UdpPacket
		 * Przykładowo dla ilości danych UdpPacket.length pobranie powinno wyglądać następująco
		 * byte[] dat = Arrays.copyOfRange(data, 1, 3);
		 */
		return udpPacket;
	}

	/**
	 * Metoda konwertuje klasę UdpPacket do tablicy byte[] możliwej do wysłania przez sieć.
	 *
	 * @param message
	 * 		Wiadomość, która ma zostać przekonwertowana
	 *
	 * @return Tablica byte[]
	 */

	public byte[] serialize(UdpPacket message) {
		ByteArrayOutputStream s = new ByteArrayOutputStream();
		byte messageType = message.getType().serialize();
		ByteBuffer debuff = ByteBuffer.allocate(2);
		debuff.putShort((short)message.getLength());
		byte [] messageLength = debuff.array();
		s.write(messageType);
		s.write(messageLength, 0, 2);
		s.write(message.getValue(), 0, message.getValue().length);

		/**
		 * 1. Przekonwertuj klasę UdpPacket z postaci klasy to tablicy bajtów
		 * 2. Wykonaj konwersję do tablicy byte[] za pomocą ByteArrayOutputStream
		 * 3. Do tablicy wynikowej dodaj kolejno pola: type, length, value.
		 * 4. Pola które nie są tablicą byte[] muszą najpierw zostać przekonwertowane do niej.
		 *
		 */
		return s.toByteArray();
	}
}