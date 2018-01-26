package com.semantalytics.stardog.kibble.strings.comparison;

import com.complexible.stardog.api.Connection;
import com.complexible.stardog.api.ConnectionConfiguration;
import com.semantalytics.stardog.kibble.AbstractStardogTest;
import org.junit.*;
import org.openrdf.query.BindingSet;
import org.openrdf.query.TupleQueryResult;

import static org.junit.Assert.*;

public class TestNeedlemanWunch extends AbstractStardogTest {

    @Test
    public void testNeedlemanWunch() throws Exception {
        final String aQuery = "prefix ss: <" + StringComparisonVocabulary.NAMESPACE + "> " +
                "select ?dist where { bind(ss:needlemanWunch(\"Stardog\", \"Starman\") as ?dist) }";

        final TupleQueryResult aResult = aConn.select(aQuery).execute();

        assertTrue("Should have a result", aResult.hasNext());

        final String aValue = aResult.next().getValue("dist").stringValue();

        assertEquals(0.78571427, Double.parseDouble(aValue), 0.00001);

        assertFalse("Should have no more results", aResult.hasNext());
    }

    @Test
    public void testNeedlemanWunchTooManyArgs() throws Exception {

        final String aQuery = "prefix ss: <" + StringComparisonVocabulary.NAMESPACE + "> " +
                "select ?dist where { bind(ss:needlemanWunch(\"one\", \"two\", \"three\", \"four\") as ?dist) }";

        final TupleQueryResult aResult = aConn.select(aQuery).execute();
        // there should be a result because implicit in the query is the singleton set, so because the bind
        // should fail due to the value error, we expect a single empty binding
        assertTrue("Should have a result", aResult.hasNext());

        final BindingSet aBindingSet = aResult.next();

        assertTrue("Should have no bindings", aBindingSet.getBindingNames().isEmpty());

        assertFalse("Should have no more results", aResult.hasNext());
    }

    @Test
    public void testNeedlemanWunchWrongType() throws Exception {

        final String aQuery = "prefix ss: <" + StringComparisonVocabulary.NAMESPACE + "> " +
                "select ?dist where { bind(ss:needlemanWunch(7) as ?dist) }";

        final TupleQueryResult aResult = aConn.select(aQuery).execute();

        // there should be a result because implicit in the query is the singleton set, so because the bind
        // should fail due to the value error, we expect a single empty binding
        assertTrue("Should have a result", aResult.hasNext());

        final BindingSet aBindingSet = aResult.next();

        assertTrue("Should have no bindings", aBindingSet.getBindingNames().isEmpty());

        assertFalse("Should have no more results", aResult.hasNext());
    }
}
