package repository.dao;

import com.lordofthejars.nosqlunit.annotation.UsingDataSet;
import com.lordofthejars.nosqlunit.core.LoadStrategyEnum;
import com.lordofthejars.nosqlunit.mongodb.MongoDbRule;
import de.flapdoodle.embed.mongo.MongodExecutable;
import de.flapdoodle.embed.mongo.MongodProcess;
import de.flapdoodle.embed.mongo.MongodStarter;
import de.flapdoodle.embed.mongo.config.IMongodConfig;
import de.flapdoodle.embed.mongo.config.MongodConfigBuilder;
import de.flapdoodle.embed.mongo.config.Net;
import de.flapdoodle.embed.process.runtime.Network;
import dto.message.Converter;
import dto.message.Message;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import packet.message.TextMessage;

import java.io.IOException;
import java.util.List;

import static com.lordofthejars.nosqlunit.mongodb.MongoDbConfigurationBuilder.mongoDb;
import static de.flapdoodle.embed.mongo.distribution.Version.Main.PRODUCTION;

public class MessageDAOTest {

    private static MongodExecutable mongodExecutable;
    private static MongodProcess mongodProcess;
    private static String host = "localhost";
    private static Integer port = 12345;
    private static String dbName = "chatAppDB";

    @Rule
    public MongoDbRule mongoDbRule = new MongoDbRule(mongoDb().databaseName(dbName).port(port).host(host).build());

    @BeforeClass
    public static void before() throws IOException {
        MongodStarter mongodStarter = MongodStarter.getDefaultInstance();
        IMongodConfig mongodConfig = new MongodConfigBuilder().version(PRODUCTION).net(new Net(host, port, Network.localhostIsIPv6())).build();
        mongodExecutable = mongodStarter.prepare(mongodConfig);
        mongodProcess = mongodExecutable.start();
    }

    @AfterClass
    public static void after() {
        mongodExecutable.stop();
        mongodProcess.stop();
    }

    @Test
    @UsingDataSet(locations = "/db/mongo/message.json", loadStrategy = LoadStrategyEnum.CLEAN_INSERT)
    public void shouldGetAllMessage() {
        Converter converter = new Converter();
        MessageDAOImpl messageDAO = new MessageDAOImpl(host, port, dbName);
        TextMessage textMessage = new TextMessage("123e4567-e89b-12d3-a456-426655440000", "from", "to", "msg");
        messageDAO.save(converter.convertTextToMessage(textMessage));
        List<Message> messageList = messageDAO.getAll();
        Assert.assertEquals(1, messageList.size());
        Message msg = messageList.get(0);
        Assert.assertEquals(converter.convertMessageToText(msg), textMessage);
    }
}
