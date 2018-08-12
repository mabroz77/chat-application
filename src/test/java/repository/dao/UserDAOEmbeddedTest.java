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
import dto.user.User;
import dto.user.UserStatus;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

import static com.lordofthejars.nosqlunit.mongodb.MongoDbConfigurationBuilder.mongoDb;
import static de.flapdoodle.embed.mongo.distribution.Version.Main.PRODUCTION;

public class UserDAOEmbeddedTest {

    private static MongodExecutable mongodExecutable;
    private static MongodProcess mongod;
    private static String host = "localhost";
    private static Integer port = 12345;
    private static String dbName = "chatAppDB";

    @Rule
    public MongoDbRule remoteMongoDbRule = new MongoDbRule(mongoDb().databaseName("czat").port(port).host(host).build());

    @BeforeClass
    public static void before() throws IOException {
        MongodStarter starter = MongodStarter.getDefaultInstance();
        IMongodConfig mongodConfig = new MongodConfigBuilder().version(PRODUCTION).net(new Net(host, port, Network.localhostIsIPv6())).build();
        mongodExecutable = starter.prepare(mongodConfig);
        mongod = mongodExecutable.start();
    }

    @AfterClass
    public static void after() {
        mongodExecutable.stop();
        mongod.stop();
    }

    @Test
    @UsingDataSet(locations = "/db/mongo/user.json", loadStrategy = LoadStrategyEnum.CLEAN_INSERT)
    public void shouldGetUsers() {
        UserDAOImpl dao = new UserDAOImpl(host, port, dbName);
        User user = new User("lll", "aktywny", UserStatus.available);
        dao.save(user);
        List<User> userFromDb = dao.getUsers();
        Assert.assertEquals(1, userFromDb.size());
        User userFromDB = userFromDb.get(0);
        Assert.assertEquals(user, userFromDB);
    }
}
