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

public class TestQGram extends AbstractStardogTest {

    @Test
    public void testQGram() throws Exception {

        final String aQuery = "prefix ss: <" + StringComparisonVocabulary.NAMESPACE + "> " +
                "select ?dist where { bind(ss:qgram(\"ABCD\", \"ABCE\", 2) as ?dist) }";

        final TupleQueryResult aResult = aConn.select(aQuery).execute();

        assertTrue("Should have a result", aResult.hasNext());

        final String aValue = aResult.next().getValue("dist").stringValue();

        assertEquals(2.0, Double.parseDouble(aValue), 0.0);

        assertFalse("Should have no more results", aResult.hasNext());
    }

    @Test
    public void testQGramTooManyArgs() throws Exception {

        final String aQuery = "prefix ss: <" + StringComparisonVocabulary.NAMESPACE + "> " +
                "select ?str where { bind(ss:qgram(\"one\", \"two\", \"three\", \"four\") as ?str) }";

        final TupleQueryResult aResult = aConn.select(aQuery).execute();
        // there should be a result because implicit in the query is the singleton set, so because the bind
        // should fail due to the value error, we expect a single empty binding
        assertTrue("Should have a result", aResult.hasNext());

        final BindingSet aBindingSet = aResult.next();

        assertTrue("Should have no bindings", aBindingSet.getBindingNames().isEmpty());

        assertFalse("Should have no more results", aResult.hasNext());
    }

    @Test
    public void testQGramWrongType() throws Exception {

        final String aQuery = "prefix ss: <" + StringComparisonVocabulary.NAMESPACE + "> " +
                "select ?str where { bind(ss:qgram(7) as ?str) }";

        final TupleQueryResult aResult = aConn.select(aQuery).execute();
        // there should be a result because implicit in the query is the singleton set, so because the bind
        // should fail due to the value error, we expect a single empty binding
        assertTrue("Should have a result", aResult.hasNext());

        final BindingSet aBindingSet = aResult.next();

        assertTrue("Should have no bindings", aBindingSet.getBindingNames().isEmpty());

        assertFalse("Should have no more results", aResult.hasNext());
    }
}
