package repository.dao;

/*class to save dto.message to database*/

import com.mongodb.*;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import dto.message.Message;
import dto.user.User;
import org.bson.Document;
import org.bson.conversions.Bson;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class MessageDAOImpl implements MessageDAO {

    private String host;
    private int port;
    private String dbName;
    private String mongoConection;

    public MessageDAOImpl(String host, int port, String dbName) {
        this.host = host;
        this.port = port;
        this.dbName = dbName;
        this.mongoConection = "mongodb://"+host+":"+port;
    }

    public boolean save(Message message) {
        try {
            Document msg = new Document("identifier", message.getIdentifier())
                    .append("from", message.getFrom())
                    .append("to", message.getTo())
                    .append("text", message.getText())
                    .append("date", message.getDate());
            MongoClient mongoClient = new MongoClient(new MongoClientURI(mongoConection));
            MongoDatabase database = mongoClient.getDatabase(dbName);
            MongoCollection collection = database.getCollection("message_col");
            collection.insertOne(msg);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }


    public List<Message> getAll() {
        List<Message> result = new ArrayList<>();
        try {
            MongoClient mongoClient = new MongoClient(new MongoClientURI(mongoConection));
            MongoDatabase database = mongoClient.getDatabase(dbName);
            MongoCollection<Document> collection = database.getCollection("message_col");

            List<Document> documents = collection.find().into(new ArrayList<>());
            for (Document doc : documents) {
                Message message = new Message();
                message.setFrom(doc.getString("from"));
                message.setTo(doc.getString("to"));
                message.setDate(doc.getDate("date"));
                message.setIdentifier((UUID)doc.get("identifier"));
                message.setText(doc.getString("text"));

                result.add(message);
            }
        } catch (MongoException ex) {

        }
        return result;
    }

    public List<Message> getMsg(User user) {
        List<Message> result = new ArrayList<>();
        try {
            MongoClient mongoClient = new MongoClient(new MongoClientURI(mongoConection));
            MongoDatabase database = mongoClient.getDatabase(dbName);
            MongoCollection<Document> collection = database.getCollection("message_col");

            Bson query = Filters.eq("to", user.getNick());
            List<Document> documents = collection.find(query).into(new ArrayList<>());
            for (Document doc : documents) {
                Message message = new Message();
                message.setFrom(doc.getString("from"));
                message.setTo(doc.getString("to"));
                message.setDate(doc.getDate("date"));
                message.setIdentifier(UUID.fromString(doc.getString("identifier")));
                message.setText(doc.getString("text"));
                result.add(message);
            }
        } catch (MongoException ex) {
        }
        return result;
    }
}