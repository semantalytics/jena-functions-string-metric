package com.semantalytics.stardog.kibble.strings.comparison;

import com.semantalytics.stardog.kibble.AbstractStardogTest;
import org.junit.*;
import org.openrdf.query.BindingSet;
import org.openrdf.query.TupleQueryResult;

import static org.junit.Assert.*;

public class TestSmithWatermanGotoh extends AbstractStardogTest {

    private static final String SPARQL_PREFIX = "PREFIX ss: <" + StringComparisonVocabulary.NAMESPACE + "> ";

    @Test
    public void testSmithWatermanGotohTwoArg() {

        final String aQuery = SPARQL_PREFIX +
                "select ?result where { bind(ss:smithWatermanGotoh(\"Stardog\", \"Starman\") as ?result) }";

        final TupleQueryResult aResult = connection.select(aQuery).execute();

        assertTrue("Should have a result", aResult.hasNext());

        final String aValue = aResult.next().getValue("result").stringValue();

        assertEquals(0.5714286, Double.parseDouble(aValue), 0.00001);

        assertFalse("Should have no more results", aResult.hasNext());
    }

    @Test
    public void testSmithWatermanGotohFiveArg() {

        final String aQuery = SPARQL_PREFIX +
                "select ?result where { bind(ss:smithWatermanGotoh(\"Stardog\", \"Starman\", -0.5, 1.0, -2.0) as ?result) }";

        final TupleQueryResult aResult = connection.select(aQuery).execute();

        assertTrue("Should have a result", aResult.hasNext());

        final String aValue = aResult.next().getValue("result").stringValue();

        assertEquals(0.5714286, Double.parseDouble(aValue), 0.00001);

        assertFalse("Should have no more results", aResult.hasNext());
    }

    @Test
    public void testSmithWatermanGotohTooManyArgs() {

        final String aQuery = SPARQL_PREFIX +
                "select ?result where { bind(ss:smithWatermanGotoh(\"one\", \"two\", \"three\", \"four\") as ?result) }";

        final TupleQueryResult aResult = connection.select(aQuery).execute();
        // there should be a result because implicit in the query is the singleton set, so because the bind
        // should fail due to the value error, we expect a single empty binding
        assertTrue("Should have a result", aResult.hasNext());

        final BindingSet aBindingSet = aResult.next();

        assertTrue("Should have no bindings", aBindingSet.getBindingNames().isEmpty());

    }
    
    @Test
    public void testSmithWatermanGotohWrongType() {

        final String aQuery = SPARQL_PREFIX +
                "select ?result where { bind(ss:smithWatermanGotoh(7) as ?result) }";

        final TupleQueryResult aResult = connection.select(aQuery).execute();
        // there should be a result because implicit in the query is the singleton set, so because the bind
        // should fail due to the value error, we expect a single empty binding
        assertTrue("Should have a result", aResult.hasNext());

        final BindingSet aBindingSet = aResult.next();

        assertTrue("Should have no bindings", aBindingSet.getBindingNames().isEmpty());
    }
}
