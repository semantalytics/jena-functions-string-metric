package com.semantalytics.stardog.kibble.strings.comparison;

import com.semantalytics.stardog.kibble.AbstractStardogTest;
import org.junit.*;
import org.openrdf.query.BindingSet;
import org.openrdf.query.TupleQueryResult;

import static org.junit.Assert.*;

public class TestJaroWinklerSimilarity extends AbstractStardogTest {

    final String SPARQL_PREFIX = "PREFIX ss: <" + StringComparisonVocabulary.NAMESPACE + "> ";

    @Test
    public void testJaroWinkler() {

        final String aQuery = SPARQL_PREFIX
                + "select ?result where { bind(ss:jaroWinklerSimilarity(\"My string\", \"My tsring\") as ?result) }";

        final TupleQueryResult aResult = connection.select(aQuery).execute();

        assertTrue("Should have a result", aResult.hasNext());

        final String aValue = aResult.next().getValue("result").stringValue();

        assertEquals(0.9740740656852722, Double.parseDouble(aValue), 0.000001);

        assertFalse("Should have no more results", aResult.hasNext());
    }

    @Test
    public void testJaroWinklerThreeArgSimilarity() {

        final String aQuery = SPARQL_PREFIX
                + "select ?result where { bind(ss:jaroWinklerSimilarity(\"My string\", \"My tsring\", \"s\") as ?result) }";

        final TupleQueryResult aResult = connection.select(aQuery).execute();

        assertTrue("Should have a result", aResult.hasNext());

        final String aValue = aResult.next().getValue("result").stringValue();

        assertEquals(0.9740740656852722, Double.parseDouble(aValue), 0.000001);

        assertFalse("Should have no more results", aResult.hasNext());
    }

    @Test
    public void testJaroWinklerThreeArgDistance() {

        final String aQuery = SPARQL_PREFIX
                + "select ?result where { bind(ss:jaroWinklerSimilarity(\"My string\", \"My tsring\", \"d\") as ?result) }";

        final TupleQueryResult aResult = connection.select(aQuery).execute();

        assertTrue("Should have a result", aResult.hasNext());

        final String aValue = aResult.next().getValue("result").stringValue();

        assertEquals(0.025925934314727783, Double.parseDouble(aValue), 0.000001);

        assertFalse("Should have no more results", aResult.hasNext());
    }

    @Test
    public void testJaroWinklerThreeArgIncorrectThirdArg() {

        final String aQuery = SPARQL_PREFIX
                + "select ?result where { bind(ss:jaroWinklerSimilarity(\"My string\", \"My tsring\", \"x\") as ?result) }";

        final TupleQueryResult aResult = connection.select(aQuery).execute();

        // there should be a result because implicit in the query is the singleton set, so because the bind
        // should fail due to the value error, we expect a single empty binding
        assertTrue("Should have a result", aResult.hasNext());

        final BindingSet aBindingSet = aResult.next();

        assertTrue("Should have no bindings", aBindingSet.getBindingNames().isEmpty());

        assertFalse("Should have no more results", aResult.hasNext());
    }

    @Test
    public void testJaroWinklerTooManyArgs() {

        final String aQuery = SPARQL_PREFIX
                + "select ?result where { bind(ss:jaroWinklerSimilarity(\"one\", \"two\", \"three\") as ?result) }";

        final TupleQueryResult aResult = connection.select(aQuery).execute();
        // there should be a result because implicit in the query is the singleton set, so because the bind
        // should fail due to the value error, we expect a single empty binding
        assertTrue("Should have a result", aResult.hasNext());

        final BindingSet aBindingSet = aResult.next();

        assertTrue("Should have no bindings", aBindingSet.getBindingNames().isEmpty());

        assertFalse("Should have no more results", aResult.hasNext());
    }

    @Test
    public void testJaroWinklerWrongType() {

        final String aQuery = SPARQL_PREFIX
               + "select ?result where { bind(ss:jaroWinklerSimilarity(7) as ?result) }";

        final TupleQueryResult aResult = connection.select(aQuery).execute();
        // there should be a result because implicit in the query is the singleton set, so because the bind
        // should fail due to the value error, we expect a single empty binding
        assertTrue("Should have a result", aResult.hasNext());

        final BindingSet aBindingSet = aResult.next();

        assertTrue("Should have no bindings", aBindingSet.getBindingNames().isEmpty());

        assertFalse("Should have no more results", aResult.hasNext());
    }
}
