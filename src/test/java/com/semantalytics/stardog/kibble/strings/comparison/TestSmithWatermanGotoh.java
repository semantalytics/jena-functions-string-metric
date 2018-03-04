package com.semantalytics.stardog.kibble.strings.comparison;

import com.semantalytics.stardog.kibble.AbstractStardogTest;
import org.junit.*;
import org.openrdf.query.BindingSet;
import org.openrdf.query.TupleQueryResult;

import static org.junit.Assert.*;

public class TestSmithWatermanGotoh extends AbstractStardogTest {

    @Test
    public void testSmithWatermanGotohTwoArg() {

        final String aQuery = StringMetricVocabulary.sparqlPrefix("stringmetric") +
                "select ?result where { bind(stringmetric:smithWatermanGotoh(\"Stardog\", \"Starman\") as ?result) }";

        final TupleQueryResult aResult = connection.select(aQuery).execute();

        assertTrue("Should have a result", aResult.hasNext());

        final String aValue = aResult.next().getValue("result").stringValue();

        assertEquals(0.5714286, Double.parseDouble(aValue), 0.00001);

        assertFalse("Should have no more results", aResult.hasNext());
    }

    @Test
    public void testSmithWatermanGotohFiveArg() {

        final String aQuery = StringMetricVocabulary.sparqlPrefix("stringmetric") +
                "select ?result where { bind(stringmetric:smithWatermanGotoh(\"Stardog\", \"Starman\", -0.5, 1.0, -2.0) as ?result) }";

        final TupleQueryResult aResult = connection.select(aQuery).execute();

        assertTrue("Should have a result", aResult.hasNext());

        final String aValue = aResult.next().getValue("result").stringValue();

        assertEquals(0.5714286, Double.parseDouble(aValue), 0.00001);

        assertFalse("Should have no more results", aResult.hasNext());
    }

    @Test
    public void testSmithWatermanGotohTooManyArgs() {

        final String aQuery = StringMetricVocabulary.sparqlPrefix("stringmetric") +
                "select ?result where { bind(stringmetric:smithWatermanGotoh(\"one\", \"two\", \"three\", \"four\", \"five\", \"six\") as ?result) }";

        final TupleQueryResult aResult = connection.select(aQuery).execute();
        assertTrue("Should have a result", aResult.hasNext());

        final BindingSet aBindingSet = aResult.next();

        assertTrue("Should have no bindings", aBindingSet.getBindingNames().isEmpty());

    }
    
    @Test
    public void testSmithWatermanGotohWrongType() {

        final String aQuery = StringMetricVocabulary.sparqlPrefix("stringmetric") +
                "select ?result where { bind(stringmetric:smithWatermanGotoh(7) as ?result) }";

        final TupleQueryResult aResult = connection.select(aQuery).execute();
        assertTrue("Should have a result", aResult.hasNext());

        final BindingSet aBindingSet = aResult.next();

        assertTrue("Should have no bindings", aBindingSet.getBindingNames().isEmpty());
    }
}
