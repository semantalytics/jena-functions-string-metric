package com.semantalytics.stardog.kibble.console;

import com.semantalytics.stardog.kibble.AbstractStardogTest;
import org.junit.*;
import org.openrdf.query.BindingSet;
import org.openrdf.query.TupleQueryResult;

import static org.junit.Assert.*;


public class ConsoleTest extends AbstractStardogTest {

    @Test
    public void testEpochTime() throws Exception {

            connection.begin();

            final String aQuery = "prefix c: <http://semantalytics.com/2017/11/ns/stardog/kibble/console/>" +
                    "select ?result where { bind(c:bgRed(\"Woof!\") as ?result) }";

            try (final TupleQueryResult aResult = connection.select(aQuery).execute()) {

                assertTrue("Should have a result", aResult.hasNext());

                final String aValue = aResult.next().getValue("result").stringValue();

                //assertEquals(1504238400000L, aValue);

                //assertFalse("Should have no more results", aResult.hasNext());
            }
    }

    @Test
    public void testCreationTimeTooManyArgs() throws Exception {

        final String aQuery = "prefix c: <http://semantalytics.com/2017/11/ns/stardog/kibble/console/>" +
                "select ?result where { bind(c:bold(\"Stardog\">) as ?result) }";

        final TupleQueryResult aResult = connection.select(aQuery).execute();

            // there should be a result because implicit in the query is the singleton set, so because the bind
            // should fail due to the value error, we expect a single empty binding
            assertTrue("Should have a result", aResult.hasNext());

            final BindingSet aBindingSet = aResult.next();

            assertTrue("Should have no bindings", aBindingSet.getBindingNames().isEmpty());

            assertFalse("Should have no more results", aResult.hasNext());

    }
}
