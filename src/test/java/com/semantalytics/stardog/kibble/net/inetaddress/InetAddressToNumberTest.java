package com.semantalytics.stardog.kibble.net.inetaddress;

import com.complexible.stardog.Stardog;
import com.complexible.stardog.api.Connection;
import com.complexible.stardog.api.ConnectionConfiguration;
import com.complexible.stardog.api.admin.AdminConnection;
import com.complexible.stardog.api.admin.AdminConnectionConfiguration;
import com.semantalytics.stardog.kibble.AbstractStardogTest;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.After;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openrdf.query.TupleQueryResult;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class InetAddressToNumberTest extends AbstractStardogTest {

    @Test
    public void testInetAddressToNumber() {

            final String aQuery = "prefix util: <" + InetAddressVocabulary.NAMESPACE + "> " +
                    "select ?result where { bind(util:inetAddressToNumber(\"192.168.0.1\") as ?result) }";

            try (final TupleQueryResult aResult = aConn.select(aQuery).execute()) {

                assertTrue("Should have a result", aResult.hasNext());

                final long aValue = Long.parseLong(aResult.next().getValue("result").stringValue());

                assertEquals(3232235521L, aValue);

                assertFalse("Should have no more results", aResult.hasNext());
            } finally {
                aConn.close();
            }
    }
}
