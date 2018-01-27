package com.semantalytics.stardog.kibble.net.inetaddress;

import com.semantalytics.stardog.kibble.AbstractStardogTest;
import org.junit.Test;
import org.openrdf.query.TupleQueryResult;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class InetNumberToAddressTest extends AbstractStardogTest {

    @Test
    public void testInetNumberToAddress() throws Exception {

            final String aQuery = "prefix util: <" + InetAddressVocabulary.NAMESPACE + "> " +
                    "select ?result where { bind(util:inetNumberToAddress(3232235521) as ?result) }";

            try (final TupleQueryResult aResult = connection.select(aQuery).execute()) {

                assertTrue("Should have a result", aResult.hasNext());

                final String aValue = aResult.next().getValue("result").stringValue();

                assertEquals("192.168.0.1", aValue);

                assertFalse("Should have no more results", aResult.hasNext());
            }
    }
}
