package com.semantalytics.stardog.kibble.net.internetdomainname;

import com.semantalytics.stardog.kibble.AbstractStardogTest;
import com.semantalytics.stardog.kibble.net.inetaddress.InetAddressVocabulary;
import org.junit.Test;
import org.openrdf.query.TupleQueryResult;

import static org.junit.Assert.*;

public class ChildTest extends AbstractStardogTest {

    @Test
    public void testInetAddressToNumber() {

        final String aQuery = InternetDomainNameVocabulary.sparqlPrefix("dn") +
                    "select ?result where { bind(dn:child(\"foo.com\", \"www.bar\") as ?result) }";

            try (final TupleQueryResult aResult = connection.select(aQuery).execute()) {

                assertTrue("Should have a result", aResult.hasNext());

                final String aValue = aResult.next().getValue("result").stringValue();

                assertEquals("www.bar.foo.com", aValue);

                assertFalse("Should have no more results", aResult.hasNext());
            }
    }
}
