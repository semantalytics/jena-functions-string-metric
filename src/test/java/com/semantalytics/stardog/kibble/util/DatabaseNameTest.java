package com.semantalytics.stardog.kibble.util;

import com.complexible.stardog.Stardog;
import com.complexible.stardog.api.Connection;
import com.complexible.stardog.api.ConnectionConfiguration;
import com.complexible.stardog.api.admin.AdminConnection;
import com.complexible.stardog.api.admin.AdminConnectionConfiguration;
import com.semantalytics.stardog.kibble.util.UtilVocabulary;
import org.junit.*;
import org.openrdf.query.TupleQueryResult;

import static org.junit.Assert.*;

public class DatabaseNameTest  extends AbstractStardogTest {


    @Test
    public void testQuarter() throws Exception {

         

            final String aQuery = "prefix date: <" + UtilVocabulary.NAMESPACE + ">" +
                    "select ?result where { bind(date:user() as ?result) }";

            try (final TupleQueryResult aResult = aConn.select(aQuery).execute()) {

                assertTrue("Should have a result", aResult.hasNext());

                final long aValue = Long.parseLong(aResult.next().getValue("result").stringValue());

                assertEquals("admin", aValue);

                assertFalse("Should have no more results", aResult.hasNext());
            }
    }

}
