package com.semantalytics.stardog.kibble.strings.comparison;

import com.complexible.stardog.Stardog;
import com.complexible.stardog.api.Connection;
import com.complexible.stardog.api.ConnectionConfiguration;
import com.complexible.stardog.api.admin.AdminConnection;
import com.complexible.stardog.api.admin.AdminConnectionConfiguration;
import com.semantalytics.stardog.kibble.AbstractStardogTest;
import org.junit.*;
import org.openrdf.query.BindingSet;
import org.openrdf.query.TupleQueryResult;

import static org.junit.Assert.*;

public class TestDamerauDistance extends AbstractStardogTest {

    @Test
    public void testTwoArgument() throws Exception {


        final String aQuery = "prefix stringcomparision: <" + StringComparisonVocabulary.NAMESPACE + "> " +
                "select ?result where { bind(stringcomparision:damerauDistance(\"ABCDEF\", \"BACDFE\") as ?result) }";

        final TupleQueryResult aResult = aConn.select(aQuery).execute();

            assertTrue("Should have a result", aResult.hasNext());

            final String aValue = aResult.next().getValue("result").stringValue();

            assertEquals(2.0, Double.parseDouble(aValue), 0.0);

            assertFalse("Should have no more results", aResult.hasNext());
    }

    @Test
    public void testTooManyArgs() throws Exception {

        final String aQuery = "prefix stringcomparision: <" + StringComparisonVocabulary.NAMESPACE + "> " +
                "select ?result where { bind(stringcomparision:damerauDistance(\"one\", \"two\", \"three\") as ?result) }";

        final TupleQueryResult aResult = aConn.select(aQuery).execute();

        // there should be a result because implicit in the query is the singleton set, so because the bind
        // should fail due to the value error, we expect a single empty binding
        assertTrue("Should have a result", aResult.hasNext());

        final BindingSet aBindingSet = aResult.next();

        assertTrue("Should have no bindings", aBindingSet.getBindingNames().isEmpty());

        assertFalse("Should have no more results", aResult.hasNext());
    }

    @Test
    public void testTooFewArgs() throws Exception {

        final String aQuery = "prefix stringcomparision: <" + StringComparisonVocabulary.NAMESPACE + "> " +
                "select ?result where { bind(stringcomparision:damerauDistance(\"one\") as ?result) }";

        final TupleQueryResult aResult = aConn.select(aQuery).execute();
        // there should be a result because implicit in the query is the singleton set, so because the bind
        // should fail due to the value error, we expect a single empty binding
        assertTrue("Should have a result", aResult.hasNext());

        final BindingSet aBindingSet = aResult.next();

        assertTrue("Should have no bindings", aBindingSet.getBindingNames().isEmpty());

        assertFalse("Should have no more results", aResult.hasNext());
    }

    @Test
    public void testFirstArgumentWrongType() throws Exception {

        final String aQuery = "prefix stringcomparision: <" + StringComparisonVocabulary.NAMESPACE + "> " +
                "select ?result where { bind(stringcomparision:damerauDistance(7, \"Stardog\") as ?result) }";

        final TupleQueryResult aResult = aConn.select(aQuery).execute();
        // there should be a result because implicit in the query is the singleton set, so because the bind
        // should fail due to the value error, we expect a single empty binding
        assertTrue("Should have a result", aResult.hasNext());

        final BindingSet aBindingSet = aResult.next();

        assertTrue("Should have no bindings", aBindingSet.getBindingNames().isEmpty());

        assertFalse("Should have no more results", aResult.hasNext());
    }

    @Test
    public void testSecondArgumentWrongType() {

        final String aQuery = "prefix stringcomparision: <" + StringComparisonVocabulary.NAMESPACE + "> " +
                "select ?result where { bind(stringcomparision:damerauDistance(\"Stardog\", 7) as ?result) }";

        final TupleQueryResult aResult = aConn.select(aQuery).execute();
        // there should be a result because implicit in the query is the singleton set, so because the bind
        // should fail due to the value error, we expect a single empty binding
        assertTrue("Should have a result", aResult.hasNext());

        final BindingSet aBindingSet = aResult.next();

        assertTrue("Should have no bindings", aBindingSet.getBindingNames().isEmpty());

        assertFalse("Should have no more results", aResult.hasNext());
    }
}

