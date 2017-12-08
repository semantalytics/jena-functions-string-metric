package com.semantalytics.stardog.kibble.strings.comparison;

import com.complexible.stardog.Stardog;
import com.complexible.stardog.api.Connection;
import com.complexible.stardog.api.ConnectionConfiguration;
import com.complexible.stardog.api.admin.AdminConnection;
import com.complexible.stardog.api.admin.AdminConnectionConfiguration;
import org.junit.*;
import org.openrdf.query.BindingSet;
import org.openrdf.query.TupleQueryResult;

import static org.junit.Assert.*;

public class TestJaroWinklerDistance {

    protected static Stardog SERVER = null;
    protected static final String DB = "test";
    Connection connection;

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
        connection = ConnectionConfiguration.to(DB)
                .credentials("admin", "admin")
                .connect();
    }

    @After
    public void tearDown() {
        connection.close();
    }
    @Test
    public void testTwoArg() throws Exception {
        final Connection aConn = ConnectionConfiguration.to(DB)
                .credentials("admin", "admin")
                .connect();

        try {

            final String aQuery = "prefix stringcomparision: <" + StringComparisonVocabulary.NAMESPACE + "> " +
                    "select ?dist where { bind(stringcomparision:jaroWinklerDistance(\"My string\", \"My tsring\") as ?dist) }";

            final TupleQueryResult aResult = aConn.select(aQuery).execute();

            try {
                assertTrue("Should have a result", aResult.hasNext());

                final String aValue = aResult.next().getValue("dist").stringValue();

                assertEquals(0.025925, Double.parseDouble(aValue), 0.000001);

                assertFalse("Should have no more results", aResult.hasNext());
            }
            finally {
                aResult.close();
            }
        }
        finally {
            aConn.close();
        }
    }

    @Test
    public void testThreeArg() throws Exception {
        final Connection aConn = ConnectionConfiguration.to(DB)
                .credentials("admin", "admin")
                .connect();

        try {

            final String aQuery = "prefix stringcomparision: <" + StringComparisonVocabulary.NAMESPACE + "> " +
                    "select ?distance where { bind(stringcomparision:jaroWinklerDistance(\"My string\", \"My tsring\", 0.1) as ?distance) }";

            final TupleQueryResult aResult = aConn.select(aQuery).execute();

            try {
                assertTrue("Should have a result", aResult.hasNext());

                final String aValue = aResult.next().getValue("distance").stringValue();

                assertEquals(0.025925, Double.parseDouble(aValue), 0.000001);

                assertFalse("Should have no more results", aResult.hasNext());
            }
            finally {
                aResult.close();
            }
        }
        finally {
            aConn.close();
        }
    }

    @Test
    public void testThreeArgIncorrectTypeThirdArg() throws Exception {
        final Connection aConn = ConnectionConfiguration.to(DB)
                .credentials("admin", "admin")
                .connect();

        try {

            final String aQuery = "prefix stringcomparision: <" + StringComparisonVocabulary.NAMESPACE + "> " +
                    "select ?distance where { bind(stringcomparision:jaroWinklerDistance(\"My string\", \"My tsring\", \"x\") as ?distance) }";

            final TupleQueryResult aResult = aConn.select(aQuery).execute();
            try {
                // there should be a result because implicit in the query is the singleton set, so because the bind
                // should fail due to the value error, we expect a single empty binding
                assertTrue("Should have a result", aResult.hasNext());

                final BindingSet aBindingSet = aResult.next();

                assertTrue("Should have no bindings", aBindingSet.getBindingNames().isEmpty());

                assertFalse("Should have no more results", aResult.hasNext());
            }
            finally {
                aResult.close();
            }
        }
        finally {
            aConn.close();
        }
    }

    @Test
    public void testTooManyArgs() throws Exception {

        final Connection aConn = ConnectionConfiguration.to(DB)
                .credentials("admin", "admin")
                .connect();

        try {
            final String aQuery = "prefix stringcomparision: <" + StringComparisonVocabulary.NAMESPACE + "> " +
                    "select ?str where { bind(stringcomparision:jaroWinklerDistance(\"one\", \"two\", 0.7, \"four\") as ?str) }";

            final TupleQueryResult aResult = aConn.select(aQuery).execute();
            try {
                // there should be a result because implicit in the query is the singleton set, so because the bind
                // should fail due to the value error, we expect a single empty binding
                assertTrue("Should have a result", aResult.hasNext());

                final BindingSet aBindingSet = aResult.next();

                assertTrue("Should have no bindings", aBindingSet.getBindingNames().isEmpty());

                assertFalse("Should have no more results", aResult.hasNext());
            }
            finally {
                aResult.close();
            }
        }
        finally {
            aConn.close();
        }
    }

    @Test
    public void testTooFewArgs() throws Exception {
        final Connection aConn = ConnectionConfiguration.to(DB)
                .credentials("admin", "admin")
                .connect();

        try {

            final String aQuery = "prefix stringcomparision: <" + StringComparisonVocabulary.NAMESPACE + "> " +
                    "select ?str where { bind(stringcomparision:jaroWinklerDistance(7) as ?str) }";

            final TupleQueryResult aResult = aConn.select(aQuery).execute();
            try {
                // there should be a result because implicit in the query is the singleton set, so because the bind
                // should fail due to the value error, we expect a single empty binding
                assertTrue("Should have a result", aResult.hasNext());

                final BindingSet aBindingSet = aResult.next();

                assertTrue("Should have no bindings", aBindingSet.getBindingNames().isEmpty());

                assertFalse("Should have no more results", aResult.hasNext());
            }
            finally {
                aResult.close();
            }
        }
        finally {
            aConn.close();
        }
    }
}
