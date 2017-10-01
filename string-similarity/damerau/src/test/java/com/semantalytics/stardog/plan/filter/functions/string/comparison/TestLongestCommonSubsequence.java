package com.semantalytics.stardog.plan.filter.functions.string.comparison;

import com.complexible.stardog.api.Connection;
import com.complexible.stardog.api.ConnectionConfiguration;
import org.junit.Before;
import org.junit.Test;
import org.openrdf.query.BindingSet;
import org.openrdf.query.TupleQueryResult;

import static org.junit.Assert.*;

public class TestLongestCommonSubsequence extends AbstractStardogTest {

    private Connection aConn;

    @Before
    public void setUp() {
        aConn = ConnectionConfiguration.to(DB)
                .credentials("admin", "admin")
                .connect();
    }
    @Test
    public void testLongestCommonSubsequence() throws Exception {

        try {

            final String aQuery = "prefix ss: <" + StringSimilarityVocab.NAMESPACE + "> " +
                    "select ?result where { bind(ss:longestCommonSubsequence(\"AGCAT\", \"GAC\") as ?result) }";

            final TupleQueryResult aResult = aConn.select(aQuery).execute();

            try {
                assertTrue("Should have a result", aResult.hasNext());

                final String aValue = aResult.next().getValue("result").stringValue();

                assertEquals(0.4, Double.parseDouble(aValue), 0.0);

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
    public void testLongestCommonSubsequenceTooManyArgs() throws Exception {

        try {
            final String aQuery = "prefix ss: <" + StringSimilarityVocab.NAMESPACE + "> " +
                    "select ?result where { bind(ss:longestCommonSubsequence(\"one\", \"two\", \"three\") as ?result) }";

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
    public void testLongestCommonSubsequenceWrongType() throws Exception {

        try {

            final String aQuery = "prefix ss: <" + StringSimilarityVocab.NAMESPACE + "> " +
                    "select ?result where { bind(ss:longestCommonSubsequence(7) as ?result) }";

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
