package com.semantalytics.stardog.kibble.net.inetaddress;

import com.complexible.stardog.Stardog;
import com.complexible.stardog.api.Connection;
import com.complexible.stardog.api.ConnectionConfiguration;
import com.complexible.stardog.api.admin.AdminConnection;
import com.complexible.stardog.api.admin.AdminConnectionConfiguration;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.After;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openrdf.query.TupleQueryResult;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class InetNumberToAddressTest {

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
    public void testInetNumberToAddress() throws Exception {
        final Connection aConn = ConnectionConfiguration.to(DB)
                .credentials("admin", "admin")
                .connect();

            aConn.begin();

            final String aQuery = "prefix util: <" + InetAddressVocabulary.NAMESPACE + "> " +
                    "select ?result where { bind(util:inetNumberToAddress(3232235521) as ?result) }";

            try (final TupleQueryResult aResult = aConn.select(aQuery).execute()) {

                assertTrue("Should have a result", aResult.hasNext());

                final String aValue = aResult.next().getValue("result").stringValue();

                assertEquals("192.168.0.1", aValue);

                assertFalse("Should have no more results", aResult.hasNext());
            } finally {
                aConn.close();
            }
    }
}
