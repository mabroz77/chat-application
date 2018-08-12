package repository.dao;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import dto.user.User;
import dto.user.UserStatus;
import org.bson.Document;
import org.junit.Before;
import org.junit.Test;
import java.util.List;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class UserDAOTest {

    private final UserDAO objTest = new UserDAOImpl("localhost", 27017, "chatAppDB");
    MongoClient mongoClient;
    MongoDatabase database;
    MongoCollection<Document> collection;

    @Before
    public void setUp() {
        mongoClient = new MongoClient(new MongoClientURI("mongodb://localhost:27017"));
        database = mongoClient.getDatabase("chatAppDB");
        collection = database.getCollection("users_col");
    }

    @Test
    public void shouldSaveCorrectly() {
        User userTest = new User("UserTest", "StatusTest", UserStatus.available);
        assertEquals(true, objTest.save(userTest));
    }

    @Test
    public void shouldGetUsers() {
        List<User> result;
        result = objTest.getUsers();
        assertTrue(result.size() != 0);
    }

    @Test
    public void shouldNotGetUsers() {
        List<User> result;
        result = objTest.getUsers();
        assertFalse(result.size() == 0);
    }
}

