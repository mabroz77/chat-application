package repository.dao;

import dto.user.User;
import java.util.List;

public interface UserDAO {

    boolean save(User user);

    List<User> getUsers();
}
