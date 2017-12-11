package com.semantalytics.stardog.kibble.javascript;

import com.complexible.stardog.Stardog;
import com.complexible.stardog.api.Connection;
import com.complexible.stardog.api.ConnectionConfiguration;
import com.complexible.stardog.api.admin.AdminConnection;
import com.complexible.stardog.api.admin.AdminConnectionConfiguration;
import org.junit.*;
import org.openrdf.query.TupleQueryResult;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;


public class ExecuteTest {

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
    public void testExecuteString() throws Exception {

            aConn.begin();

            final String aQuery = "prefix js: <" + JavascriptVocabulary.NAMESPACE + ">" +
                    "select ?result where { bind(js:exec(\"values[0].stringValue() + \' \' + values[1].stringValue()\", \"Hello\", \"world!\") as ?result) }";

            try (final TupleQueryResult aResult = aConn.select(aQuery).execute()) {

                assertTrue("Should have a result", aResult.hasNext());

                final String aValue = aResult.next().getValue("result").stringValue();

                assertEquals("Hello world!", aValue);

                assertFalse("Should have no more results", aResult.hasNext());
            }
    }

    @Test
    public void testExecuteDouble() throws Exception {

        aConn.begin();

        final String aQuery = "prefix js: <" + JavascriptVocabulary.NAMESPACE + ">" +
                "select ?result where { bind(js:exec(\"parseInt(values[0].stringValue()) + parseInt(values[1].stringValue())\", 21, 35) as ?result) }";

        try (final TupleQueryResult aResult = aConn.select(aQuery).execute()) {

            assertTrue("Should have a result", aResult.hasNext());

            final String aValue = aResult.next().getValue("result").stringValue();

            assertEquals(56.0, Double.parseDouble(aValue), 0.000001);

            assertFalse("Should have no more results", aResult.hasNext());
        }
    }

}
