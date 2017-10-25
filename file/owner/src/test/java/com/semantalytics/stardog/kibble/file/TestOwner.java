package com.semantalytics.stardog.kibble.file;

import com.complexible.stardog.Stardog;
import com.complexible.stardog.api.Connection;
import com.complexible.stardog.api.ConnectionConfiguration;
import com.complexible.stardog.api.admin.AdminConnection;
import com.complexible.stardog.api.admin.AdminConnectionConfiguration;
import com.google.common.io.Resources;
import org.junit.*;
import org.openrdf.query.BindingSet;
import org.openrdf.query.TupleQueryResult;

import java.net.URI;

import static org.junit.Assert.*;

public class TestOwner {

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
    public void testOwner() throws Exception {
        final Connection aConn = ConnectionConfiguration.to(DB)
                .credentials("admin", "admin")
                .connect();

        try {

            URI file = Resources.getResource("test-target.txt").toURI();

            final String aQuery = "prefix file: <" + FileVocabulary.NAMESPACE + "> " +
                    "select ?result where { bind(file:owner(<" + file + ">) as ?result) }";

            final TupleQueryResult aResult = aConn.select(aQuery).execute();

            try {
                assertTrue("Should have a result", aResult.hasNext());

                final String aValue = aResult.next().getValue("result").stringValue();

                assertEquals("zacharywhitley", aValue);

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
    public void testCreationTimeTooManyArgs() throws Exception {

        final Connection aConn = ConnectionConfiguration.to(DB)
                .credentials("admin", "admin")
                .connect();

        try {

            URI file = Resources.getResource("test-target.txt").toURI();

            final String aQuery = "prefix file: <http://semantalytics.com/2016/04/ns/stardog/udf/file/> " +
                    "select ?creationTime where { bind(file:creationTime(<" + file.toString() + ">, 1) as ?creationTime) }";

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
    public void testCreationTimeWrongType() throws Exception {
        final Connection aConn = ConnectionConfiguration.to(DB)
                .credentials("admin", "admin")
                .connect();

        try {

            URI file = Resources.getResource("test-target.txt").toURI();

            final String aQuery = "prefix file: <http://semantalytics.com/2016/04/ns/stardog/udf/file/> " +
                    "select ?creationTime where { bind(file:creationTime(1) as ?creationTime) }";

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
