package com.semantalytics.stardog.function.util;

import com.complexible.stardog.api.Connection;
import com.complexible.stardog.api.ConnectionConfiguration;
import org.joda.time.Days;
import org.joda.time.Period;
import org.junit.Test;
import org.openrdf.query.TupleQueryResult;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAmount;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;


public class TestSpokenTime extends AbstractStardogTest {

    @Test
    public void testBindTime() throws Exception {
        final Connection aConn = ConnectionConfiguration.to(DB)
                .credentials("admin", "admin")
                .connect();

        try {

            aConn.begin();

            final String aQuery = "prefix util: <http://semantalytics.com/2016/03/ns/stardog/udf/util/> " +
                    "select ?result where { bind(util:sayTime(\"since yesterday\") as ?result) } ";

            try (final TupleQueryResult aResult = aConn.select(aQuery).execute()) {

                assertTrue("Should have a result", aResult.hasNext());

                final String aValue = aResult.next().getValue("result").stringValue();

                assertEquals(LocalDateTime.now().minusDays(1), aValue);

                assertFalse("Should have no more results", aResult.hasNext());

            }
        } finally {
            aConn.close();
        }
    }
}
