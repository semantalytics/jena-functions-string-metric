package com.semantalytics.stardog.plan.filter.functions.string.comparison;

import com.complexible.stardog.api.Connection;
import com.complexible.stardog.api.ConnectionConfiguration;
import org.junit.Before;
import org.junit.Test;
import org.openrdf.query.BindingSet;
import org.openrdf.query.TupleQueryResult;

import static org.junit.Assert.*;

public class TestSmithWatermanGotoh extends AbstractStardogTest {

    private Connection aConn;

    @Before
    public void setUp() {

        aConn = ConnectionConfiguration.to(DB)
                .credentials("admin", "admin")
                .connect();
    }

    @Test
    public void testSmithWatermanGotohTwoArg() throws Exception {

        try {

            final String aQuery = "prefix ss: <" + StringSimilarityVocab.NAMESPACE + "> " +
                    "select ?result where { bind(ss:smithWatermanGotoh(\"Stardog\", \"Starman\") as ?result) }";

            final TupleQueryResult aResult = aConn.select(aQuery).execute();

            try {
                assertTrue("Should have a result", aResult.hasNext());

                final String aValue = aResult.next().getValue("result").stringValue();

                assertEquals(0.5714286, Double.parseDouble(aValue), 0.00001);

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
    public void testSmithWatermanGotohFiveArg() throws Exception {

        try {

            final String aQuery = "prefix ss: <" + StringSimilarityVocab.NAMESPACE + "> " +
                    "select ?result where { bind(ss:smithWatermanGotoh(\"Stardog\", \"Starman\", -0.5, 1.0, -2.0) as ?result) }";

            final TupleQueryResult aResult = aConn.select(aQuery).execute();

            try {
                assertTrue("Should have a result", aResult.hasNext());

                final String aValue = aResult.next().getValue("result").stringValue();

                assertEquals(0.5714286, Double.parseDouble(aValue), 0.00001);

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
    public void testSmithWatermanGotohTooManyArgs() throws Exception {

        try {
            final String aQuery = "prefix ss: <" + StringSimilarityVocab.NAMESPACE + "> " +
                    "select ?dist where { bind(ss:smithWatermanGotoh(\"one\", \"two\", \"three\", \"four\") as ?dist) }";

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
    public void testSmithWatermanGotohWrongType() throws Exception {

        try {

            final String aQuery = "prefix ss: <" + StringSimilarityVocab.NAMESPACE + "> " +
                    "select ?dist where { bind(ss:smithWatermanGotoh(7) as ?dist) }";

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
