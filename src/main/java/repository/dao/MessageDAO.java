package repository.dao;

import dto.message.Message;
import java.util.List;

public interface MessageDAO {

    boolean save(Message message);

    public List<Message> getAll();

}
