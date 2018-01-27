package com.semantalytics.stardog.kibble.strings.comparison;

import com.semantalytics.stardog.kibble.AbstractStardogTest;
import org.junit.*;
import org.openrdf.query.BindingSet;
import org.openrdf.query.TupleQueryResult;

import static org.junit.Assert.*;

public class TestCosineDistance extends AbstractStardogTest {

    private final String SPARQL_PREFIX = StringComparisonVocabulary.sparqlPrefix("stringcomparison");

    @Test
    public void testCosineTwoArg() {


        final String aQuery = SPARQL_PREFIX
                + "select ?result where { bind(stringcomparison:cosineDistance(\"ABC\", \"ABCE\") as ?result) }";

        try(final TupleQueryResult aResult = connection.select(aQuery).execute()) {

            assertTrue("Should have a result", aResult.hasNext());

            final String aValue = aResult.next().getValue("result").stringValue();

            assertEquals(0.29289, Double.parseDouble(aValue), 0.0001);

            assertFalse("Should have no more results", aResult.hasNext());
        }
    }

    @Test
    public void testCosineThreeArg() {

        final String aQuery = SPARQL_PREFIX
                + "select ?result where { bind(stringcomparison:cosineDistance(\"ABC\", \"ABCE\", 3) as ?result) }";

        try(final TupleQueryResult aResult = connection.select(aQuery).execute()) {

            assertTrue("Should have a result", aResult.hasNext());

            final String aValue = aResult.next().getValue("result").stringValue();

            assertEquals(0.29289, Double.parseDouble(aValue), 0.0001);

            assertFalse("Should have no more results", aResult.hasNext());
        }
    }

    @Test
    public void testCosineTooManyArgs() {

        final String aQuery = SPARQL_PREFIX
                + "select ?result where { bind(stringcomparison:cosineDistance(\"one\", \"two\", \"three\", \"four\") as ?result) }";

        try(final TupleQueryResult aResult = connection.select(aQuery).execute()) {
            // there should be a result because implicit in the query is the singleton set, so because the bind
            // should fail due to the value error, we expect a single empty binding
            assertTrue("Should have a result", aResult.hasNext());

            final BindingSet aBindingSet = aResult.next();

            assertTrue("Should have no bindings", aBindingSet.getBindingNames().isEmpty());

            assertFalse("Should have no more results", aResult.hasNext());
        }
    }

    @Test
    public void testCosineWrongTypeFirstArg() {

        final String aQuery = SPARQL_PREFIX
                + "select ?result where { bind(stringcomparison:cosineDistance(7) as ?result) }";

        try(final TupleQueryResult aResult = connection.select(aQuery).execute()) {
            // there should be a result because implicit in the query is the singleton set, so because the bind
            // should fail due to the value error, we expect a single empty binding
            assertTrue("Should have a result", aResult.hasNext());

            final BindingSet aBindingSet = aResult.next();

            assertTrue("Should have no bindings", aBindingSet.getBindingNames().isEmpty());

            assertFalse("Should have no more results", aResult.hasNext());
        }
    }

    @Test
    public void testCosineWrongTypeSecondArg() {

        final String aQuery = SPARQL_PREFIX
                + "select ?result where { bind(stringcomparison:cosineDistance(\"Stardog\", 2) as ?result) }";

        try(final TupleQueryResult aResult = connection.select(aQuery).execute()) {
            // there should be a result because implicit in the query is the singleton set, so because the bind
            // should fail due to the value error, we expect a single empty binding
            assertTrue("Should have a result", aResult.hasNext());

            final BindingSet aBindingSet = aResult.next();

            assertTrue("Should have no bindings", aBindingSet.getBindingNames().isEmpty());

            assertFalse("Should have no more results", aResult.hasNext());
        }
    }

    @Test
    public void testCosineWrongTypeThirdArg() {

        final String aQuery = SPARQL_PREFIX
                + "select ?result where { bind(stringcomparison:cosineDistance(\"Stardog\", \"Starlight\", \"Starship\") as ?result) }";

        try(final TupleQueryResult aResult = connection.select(aQuery).execute()) {
            // there should be a result because implicit in the query is the singleton set, so because the bind
            // should fail due to the value error, we expect a single empty binding
            assertTrue("Should have a result", aResult.hasNext());

            final BindingSet aBindingSet = aResult.next();

            assertTrue("Should have no bindings", aBindingSet.getBindingNames().isEmpty());

            assertFalse("Should have no more results", aResult.hasNext());
        }
    }

    @Test
    public void testCosineThirdArgNotConstant() {

        final String aQuery = SPARQL_PREFIX
                + "select ?result where { bind(stringcomparison:cosineDistance(\"Stardog\", \"Starlight\", ?thirdArg) as ?result) }";

        try (final TupleQueryResult aResult = connection.select(aQuery).execute()) {
            // there should be a result because implicit in the query is the singleton set, so because the bind
            // should fail due to the value error, we expect a single empty binding
            assertTrue("Should have a result", aResult.hasNext());

            final BindingSet aBindingSet = aResult.next();

            assertTrue("Should have no bindings", aBindingSet.getBindingNames().isEmpty());

            assertFalse("Should have no more results", aResult.hasNext());
        }
    }
}
