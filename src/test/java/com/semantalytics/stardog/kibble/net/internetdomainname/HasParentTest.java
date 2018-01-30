package com.semantalytics.stardog.kibble.net.internetdomainname;

import com.semantalytics.stardog.kibble.AbstractStardogTest;
import com.semantalytics.stardog.kibble.net.inetaddress.InetAddressVocabulary;
import org.junit.Test;
import org.openrdf.query.TupleQueryResult;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class HasParentTest extends AbstractStardogTest {

    @Test
    public void testInetAddressToNumber() {

            final String aQuery = "prefix dn: <" + InternetDomainNameVocabulary.NAMESPACE + "> " +
                    "select ?result where { bind(dn:hasParent(\"stardog.com\") as ?result) }";

            try (final TupleQueryResult aResult = connection.select(aQuery).execute()) {

                assertTrue("Should have a result", aResult.hasNext());

                final boolean aValue = Boolean.parseBoolean(aResult.next().getValue("result").stringValue());

                assertEquals(true, aValue);

                assertFalse("Should have no more results", aResult.hasNext());
            }
    }
}
