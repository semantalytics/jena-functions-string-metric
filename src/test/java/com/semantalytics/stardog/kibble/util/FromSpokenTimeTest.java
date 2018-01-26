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

public class FromSpokenTimeTest  extends AbstractStardogTest {


  
    @Test
    public void testFromSpokenTime() throws Exception {

           
            final String aQuery = "prefix util: <" + UtilVocabulary.NAMESPACE + ">" +
                    "select ?result where { bind(util:fromSpokenTime(\"next week\") as ?result) }";

            try (final TupleQueryResult aResult = aConn.select(aQuery).execute()) {

                assertTrue("Should have a result", aResult.hasNext());

                final long aValue = Long.parseLong(aResult.next().getValue("result").stringValue());

                assertEquals(3, aValue);

                assertFalse("Should have no more results", aResult.hasNext());
            }
    }

}
