package com.semantalytics.stardog.kibble.strings.escape;

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


public class TestCsv  extends AbstractStardogTest {

   

    @Test
    public void testEpochTime() throws Exception {


            final String aQuery = "prefix escape: <" + EscapeVocabulary.NAMESPACE + ">" +
                    "select ?result where { bind(escape:csv(\"2017-09-01\") as ?result) }";

            try (final TupleQueryResult aResult = aConn.select(aQuery).execute()) {

                assertTrue("Should have a result", aResult.hasNext());

                final long aValue = Long.parseLong(aResult.next().getValue("result").stringValue());

                assertEquals(1504238400000L, aValue);

                assertFalse("Should have no more results", aResult.hasNext());
            }
    }

}
