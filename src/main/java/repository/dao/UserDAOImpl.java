package repository.dao;

import com.mongodb.*;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import dto.user.User;
import dto.user.UserStatus;
import org.bson.Document;
import java.util.ArrayList;
import java.util.List;

public class UserDAOImpl implements UserDAO {

   private String host;
   private int port;
   private String dbName;
   private String mongoConnection;

    public UserDAOImpl(String host, int port, String dbName) {
        this.host = host;
        this.port = port;
        this.dbName = dbName;
        this.mongoConnection = "mongodb://"+host+":"+port;
    }

    @Override
    public boolean save(User user) {
        try {
            Document personUser = new Document("nick", user.getNick())
                    .append("status", user.getUserStatus().toString())
                    .append("statusText", user.getStatusText());
            MongoClient mongoClient = new MongoClient(new MongoClientURI(mongoConnection));
            MongoDatabase database = mongoClient.getDatabase(dbName);
            MongoCollection collection = database.getCollection("users_col");
            collection.insertOne(personUser);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public List<User> getUsers() {
        List<User> result = new ArrayList<>();
        try {
            MongoClient mongoClient = new MongoClient(new MongoClientURI(mongoConnection));
            MongoDatabase database = mongoClient.getDatabase(dbName);
            MongoCollection<Document> collection = database.getCollection("users_col");

            List<Document> documents = collection.find().into(new ArrayList<Document>());
            for(Document doc : documents){
                User user = new User();
                user.setNick(doc.getString("nick"));
                user.setStatusText(doc.getString("statusText"));
                user.setUserStatus(UserStatus.valueOf(doc.getString("status")));
               result.add(user);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}
