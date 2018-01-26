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

public class TestHammingDistance extends AbstractStardogTest {

    @Test
    public void testHammingDistance() throws Exception {

        final String aQuery = "prefix ss: <" + StringComparisonVocabulary.NAMESPACE + "> " +
                "select ?hammingDistance where { bind(ss:hammingDistance(\"Stardog\", \"Starman\") as ?hammingDistance) }";

        final TupleQueryResult aResult = aConn.select(aQuery).execute();

        assertTrue("Should have a result", aResult.hasNext());

        final String aValue = aResult.next().getValue("hammingDistance").stringValue();

        assertEquals(3.0, Double.parseDouble(aValue), 0.0001);

        assertFalse("Should have no more results", aResult.hasNext());
    }

    @Test
    public void testCosineTooManyArgs() throws Exception {

        final String aQuery = "prefix ss: <" + StringComparisonVocabulary.NAMESPACE + "> " +
                "select ?hammingDistance where { bind(ss:hammingDistance(\"one\", \"two\", \"three\", \"four\") as ?hammingDistance) }";

        final TupleQueryResult aResult = aConn.select(aQuery).execute();
        // there should be a result because implicit in the query is the singleton set, so because the bind
        // should fail due to the value error, we expect a single empty binding
        assertTrue("Should have a result", aResult.hasNext());

        final BindingSet aBindingSet = aResult.next();

        assertTrue("Should have no bindings", aBindingSet.getBindingNames().isEmpty());

        assertFalse("Should have no more results", aResult.hasNext());
    }

    @Test
    public void testCosineWrongType() {
        final String aQuery = "prefix ss: <" + StringComparisonVocabulary.NAMESPACE + "> " +
                "select ?hammingDistance where { bind(ss:hammingDistance(7) as ?hammingDistance) }";

        final TupleQueryResult aResult = aConn.select(aQuery).execute();
        // there should be a result because implicit in the query is the singleton set, so because the bind
        // should fail due to the value error, we expect a single empty binding
        assertTrue("Should have a result", aResult.hasNext());

        final BindingSet aBindingSet = aResult.next();

        assertTrue("Should have no bindings", aBindingSet.getBindingNames().isEmpty());

        assertFalse("Should have no more results", aResult.hasNext());
    }
}
