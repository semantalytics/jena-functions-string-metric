package com.semantalytics.stardog.plan.filter.functions.string.comparison.phonetic;

import com.complexible.stardog.api.Connection;
import com.complexible.stardog.api.ConnectionConfiguration;
import com.semantalytics.stardog.plan.filter.functions.string.comparison.AbstractStardogTest;
import com.semantalytics.stardog.plan.filter.functions.string.comparison.StringSimilarityVocab;
import org.junit.Test;
import org.openrdf.query.BindingSet;
import org.openrdf.query.TupleQueryResult;

import static org.junit.Assert.*;

public class TestRefinedSoundex extends AbstractStardogTest {

    @Test
    public void testRefinedSoundex() throws Exception {
        final Connection aConn = ConnectionConfiguration.to(DB)
                .credentials("admin", "admin")
                .connect();

        try {

            final String aQuery = "prefix ss: <" + StringSimilarityVocab.NAMESPACE + "> " +
                    "select ?refinedSoundex where { bind(ss:refinedSoundex(\"Stardog\") as ?refinedSoundex) }";

            final TupleQueryResult aResult = aConn.select(aQuery).execute();

            try {
                assertTrue("Should have a result", aResult.hasNext());

                final String aValue = aResult.next().getValue("refinedSoundex").stringValue();

                assertEquals("S3609604", aValue);

                assertFalse("Should have no more results", aResult.hasNext());
            }
            finally {
                aResult.close();
            }
        }
        finally {
            aConn.close();
        }
    }

    @Test
    public void testRefinedSoundexTooManyArgs() throws Exception {

        final Connection aConn = ConnectionConfiguration.to(DB)
                .credentials("admin", "admin")
                .connect();

        try {
            final String aQuery = "prefix ss: <" + StringSimilarityVocab.NAMESPACE + "> " +
                    "select ?refinedSoundex where { bind(ss:refinedSoundex(\"one\", \"two\", \"three\", \"four\") as ?refinedSoundex) }";

            final TupleQueryResult aResult = aConn.select(aQuery).execute();
            try {
                // there should be a result because implicit in the query is the singleton set, so because the bind
                // should fail due to the value error, we expect a single empty binding
                assertTrue("Should have a result", aResult.hasNext());

                final BindingSet aBindingSet = aResult.next();

                assertTrue("Should have no bindings", aBindingSet.getBindingNames().isEmpty());

                assertFalse("Should have no more results", aResult.hasNext());
            }
            finally {
                aResult.close();
            }
        }
        finally {
            aConn.close();
        }
    }



    @Test
    public void testRefinedSoundexWrongType() throws Exception {
        final Connection aConn = ConnectionConfiguration.to(DB)
                .credentials("admin", "admin")
                .connect();

        try {

            final String aQuery = "prefix ss: <" + StringSimilarityVocab.NAMESPACE + "> " +
                    "select ?refinedSoundex where { bind(ss:refinedSoundex(7) as ?refinedSoundex) }";

            final TupleQueryResult aResult = aConn.select(aQuery).execute();
            try {
                // there should be a result because implicit in the query is the singleton set, so because the bind
                // should fail due to the value error, we expect a single empty binding
                assertTrue("Should have a result", aResult.hasNext());

                final BindingSet aBindingSet = aResult.next();

                assertTrue("Should have no bindings", aBindingSet.getBindingNames().isEmpty());

                assertFalse("Should have no more results", aResult.hasNext());
            }
            finally {
                aResult.close();
            }
        }
        finally {
            aConn.close();
        }
    }
}
