package com.semantalytics.stardog.kibble.strings.emoji;

import com.complexible.stardog.Stardog;
import com.complexible.stardog.api.Connection;
import com.complexible.stardog.api.ConnectionConfiguration;
import com.complexible.stardog.api.admin.AdminConnection;
import com.complexible.stardog.api.admin.AdminConnectionConfiguration;
import org.junit.*;
import org.openrdf.query.BindingSet;
import org.openrdf.query.TupleQueryResult;

import static org.junit.Assert.*;


public class EmojifyTest {

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
    public void testEmojify() throws Exception {

            aConn.begin();

            final String aQuery = "prefix emoji: <http://semantalytics.com/2017/11/ns/stardog/strings/emoji/>" +
                    "select ?result where { bind(emoji:emojify(\"A :cat:, :dog: and a :mouse: became friends<3. For :dog:'s birthday party, they all had :hamburger:s, :fries:s, :cookie:s and :cake:.\") as ?result) }";

            try (final TupleQueryResult aResult = aConn.select(aQuery).execute()) {

                assertTrue("Should have a result", aResult.hasNext());

                final String aValue = aResult.next().getValue("result").stringValue();

                assertEquals("A \uD83D\uDC31, \uD83D\uDC36 and a \uD83D\uDC2D became friends❤️. For \uD83D\uDC36's birthday party, they all had \uD83C\uDF54s, \uD83C\uDF5Fs, \uD83C\uDF6As and \uD83C\uDF70.", aValue);

                assertFalse("Should have no more results", aResult.hasNext());
            }
    }

    @Test
    public void testIsEmojiTooFewArgs() throws Exception {

        final String aQuery = "prefix emoji: <http://semantalytics.com/2017/11/ns/stardog/strings/emoji/>" +
                "select ?result where { bind(emoji:isEmoji() as ?result) }";

        final TupleQueryResult aResult = aConn.select(aQuery).execute();
        try {
            // there should be a result because implicit in the query is the singleton set, so because the bind
            // should fail due to the value error, we expect a single empty binding
            assertTrue("Should have a result", aResult.hasNext());

            final BindingSet aBindingSet = aResult.next();

            assertTrue("Should have no bindings", aBindingSet.getBindingNames().isEmpty());

            assertFalse("Should have no more results", aResult.hasNext());
        } finally {
            aResult.close();
        }
    }

    @Test
    public void testIsEmojiTooManyArgs() throws Exception {

        final String aQuery = "prefix emoji: <http://semantalytics.com/2017/11/ns/stardog/strings/emoji/>" +
                "select ?result where { bind(emoji:isEmoji(\"star\", \"dog\") as ?result) }";

        final TupleQueryResult aResult = aConn.select(aQuery).execute();
        try {
            // there should be a result because implicit in the query is the singleton set, so because the bind
            // should fail due to the value error, we expect a single empty binding
            assertTrue("Should have a result", aResult.hasNext());

            final BindingSet aBindingSet = aResult.next();

            assertTrue("Should have no bindings", aBindingSet.getBindingNames().isEmpty());

            assertFalse("Should have no more results", aResult.hasNext());
        } finally {
            aResult.close();
        }
    }

    @Test
    public void testIsEmojiWrongTypeOfArgIri() throws Exception {

        final String aQuery = "prefix emoji: <http://semantalytics.com/2017/11/ns/stardog/strings/emoji/>" +
                "select ?result where { bind(emoji:isEmoji(<http://example.com>) as ?result) }";

        final TupleQueryResult aResult = aConn.select(aQuery).execute();
        try {
            // there should be a result because implicit in the query is the singleton set, so because the bind
            // should fail due to the value error, we expect a single empty binding
            assertTrue("Should have a result", aResult.hasNext());

            final BindingSet aBindingSet = aResult.next();

            assertTrue("Should have no bindings", aBindingSet.getBindingNames().isEmpty());

            assertFalse("Should have no more results", aResult.hasNext());
        } finally {
            aResult.close();
        }
    }

    @Test
    public void testIsEmojiWrongTypeOfArgInt() throws Exception {

        final String aQuery = "prefix emoji: <http://semantalytics.com/2017/11/ns/stardog/strings/emoji/>" +
                "select ?result where { bind(emoji:isEmoji(1) as ?result) }";

        final TupleQueryResult aResult = aConn.select(aQuery).execute();
        try {
            // there should be a result because implicit in the query is the singleton set, so because the bind
            // should fail due to the value error, we expect a single empty binding
            assertTrue("Should have a result", aResult.hasNext());

            final BindingSet aBindingSet = aResult.next();

            assertTrue("Should have no bindings", aBindingSet.getBindingNames().isEmpty());

            assertFalse("Should have no more results", aResult.hasNext());
        } finally {
            aResult.close();
        }
    }

}
