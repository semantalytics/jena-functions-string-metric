package com.semantalytics.stardog.kibble.net.internetdomainname;

import com.semantalytics.stardog.kibble.AbstractStardogTest;
import com.semantalytics.stardog.kibble.net.inetaddress.InetAddressVocabulary;
import org.junit.Test;
import org.openrdf.query.TupleQueryResult;

import static org.junit.Assert.*;

public class HasRegistrySuffixTest extends AbstractStardogTest {

    @Test
    public void testInetAddressToNumber() {

            final String aQuery = "prefix util: <" + InetAddressVocabulary.NAMESPACE + "> " +
                    "select ?result where { bind(util:inetAddressToNumber(\"192.168.0.1\") as ?result) }";

            try (final TupleQueryResult aResult = connection.select(aQuery).execute()) {

                assertTrue("Should have a result", aResult.hasNext());

                final long aValue = Long.parseLong(aResult.next().getValue("result").stringValue());

                assertEquals(3232235521L, aValue);

                assertFalse("Should have no more results", aResult.hasNext());
            }
    }
}
