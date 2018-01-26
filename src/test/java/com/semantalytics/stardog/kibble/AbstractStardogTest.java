package com.semantalytics.stardog.kibble;

import com.complexible.common.protocols.server.Server;
import com.complexible.stardog.Stardog;
import com.complexible.stardog.api.Connection;
import com.complexible.stardog.api.ConnectionConfiguration;
import com.complexible.stardog.api.admin.AdminConnection;
import com.complexible.stardog.api.admin.AdminConnectionConfiguration;
import com.google.common.io.Files;
import org.junit.*;

import java.io.File;
import java.io.IOException;
import java.net.InetSocketAddress;


public class AbstractStardogTest {

    private static Stardog STARDOG;
    private static Server SERVER;
    private static final String DB = "test";
    private static File TEST_HOME;
    private static int TEST_PORT = 5888;
    private static final String STARDOG_HOME = System.getenv("STARDOG_HOME");
    protected Connection aConn;
    private static final String STARDOG_LICENCE_KEY_FILE_NAME = "stardog-license-key.bin";

    @BeforeClass
    public static void beforeClass() throws Exception {

        TEST_HOME = Files.createTempDir();
        TEST_HOME.deleteOnExit();

        Files.copy(new File(STARDOG_HOME + "/" + STARDOG_LICENCE_KEY_FILE_NAME),
                new File(TEST_HOME, STARDOG_LICENCE_KEY_FILE_NAME));

        STARDOG = Stardog.builder().home(TEST_HOME).create();

        SERVER = STARDOG.newServer()
                //.set(ServerOptions.SECURITY_DISABLED, true)
                .bind(new InetSocketAddress("localhost", TEST_PORT)).start();

        final AdminConnection aConn = AdminConnectionConfiguration.toEmbeddedServer()
                .credentials("admin", "admin")
                .connect();

        if (aConn.list().contains(DB)) {
            aConn.drop(DB);
        }

        aConn.newDatabase(DB).create();
    }

    @AfterClass
    public static void afterClass() throws IOException {
        if (SERVER != null) {
            SERVER.stop();
        }
        STARDOG.shutdown();
    }

    @Before
    public void setUp() {
        aConn = ConnectionConfiguration.to(DB)
                .credentials("admin", "admin")
                .connect();
    }

    @After
    public void tearDown() {
        aConn.close();
    }
}