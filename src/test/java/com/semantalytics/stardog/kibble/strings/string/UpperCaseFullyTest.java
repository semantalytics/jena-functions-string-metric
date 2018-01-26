package com.semantalytics.stardog.kibble.strings.string;

import com.complexible.stardog.Stardog;
import com.complexible.stardog.api.Connection;
import com.complexible.stardog.api.ConnectionConfiguration;
import com.complexible.stardog.api.admin.AdminConnection;
import com.complexible.stardog.api.admin.AdminConnectionConfiguration;
import org.junit.*;
import org.openrdf.query.TupleQueryResult;

import static org.junit.Assert.*;

public class UpperCaseFullyTest  extends AbstractStardogTest {


    @Test
    public void testUpperCaseFully() throws Exception {

            aConn.begin();

            final String aQuery = "prefix date: <" + StringVocabulary.NAMESPACE + ">" +
                    "select ?result where { bind(date:upperCaseFully(\"stardog\") as ?result) }";

            try (final TupleQueryResult aResult = aConn.select(aQuery).execute()) {

                assertTrue("Should have a result", aResult.hasNext());

                final String aValue = aResult.next().getValue("result").stringValue();

                assertEquals("STARDOG", aValue);

                assertFalse("Should have no more results", aResult.hasNext());
            }
    }
}
