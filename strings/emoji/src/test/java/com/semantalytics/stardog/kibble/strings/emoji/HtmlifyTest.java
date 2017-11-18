package com.semantalytics.stardog.kibble.strings.emoji;

import com.complexible.stardog.Stardog;
import com.complexible.stardog.api.Connection;
import com.complexible.stardog.api.ConnectionConfiguration;
import com.complexible.stardog.api.admin.AdminConnection;
import com.complexible.stardog.api.admin.AdminConnectionConfiguration;
import org.junit.*;
import org.openrdf.query.TupleQueryResult;

import static org.junit.Assert.*;


public class HtmlifyTest {

    protected static Stardog SERVER = null;
    protected static final String DB = "test";
    private Connection aConn;

    @BeforeClass
    public static void beforeClass() throws Exception {
        SERVER = Stardog.builder().create();

        final AdminConnection aConn = AdminConnectionConfiguration.toEmbeddedServer()
                .credentials("admin", "admin")
                .connect();

        try {
            if (aConn.list().contains(DB)) {
                aConn.drop(DB);
            }

            aConn.newDatabase(DB).create();
        }
        finally {
            aConn.close();
        }
    }

    @AfterClass
    public static void afterClass() {
        if (SERVER != null) {
            SERVER.shutdown();
        }
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

    @Test
    public void testEmoji() throws Exception {

            aConn.begin();

            final String aQuery = "prefix emoji: <http://semantalytics.com/2017/11/ns/stardog/strings/emoji/>" +
                    "select ?result where { bind(emoji:emoji(\"dog\") as ?result) }";

            try (final TupleQueryResult aResult = aConn.select(aQuery).execute()) {

                assertTrue("Should have a result", aResult.hasNext());

                final String aValue = aResult.next().getValue("result").stringValue();

                System.out.println("'" + aValue + "'");
                assertEquals("dog", aValue);

                assertFalse("Should have no more results", aResult.hasNext());
            }
    }

}
