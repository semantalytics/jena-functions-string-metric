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

public class TestCosineSimilarity extends AbstractStardogTest {

    @Test
    public void testCosineTwoArg() throws Exception {


            final String aQuery = "prefix stringcomparision: <" + StringComparisonVocabulary.NAMESPACE + "> " +
                    "select ?result where { bind(stringcomparision:cosineSimilarity(\"ABC\", \"ABCE\") as ?result) }";

            final TupleQueryResult aResult = aConn.select(aQuery).execute();

            try {
                assertTrue("Should have a result", aResult.hasNext());

                final String aValue = aResult.next().getValue("result").stringValue();

                assertEquals(0.7071, Double.parseDouble(aValue), 0.0001);

                assertFalse("Should have no more results", aResult.hasNext());
            }
            finally {
                aResult.close();
            }
    }

    @Test
    public void testCosineThreeArg() throws Exception {

            final String aQuery = "prefix stringcomparision: <" + StringComparisonVocabulary.NAMESPACE + "> " +
                    "select ?result where { bind(stringcomparision:cosineSimilarity(\"ABC\", \"ABCE\", 3) as ?result) }";

            final TupleQueryResult aResult = aConn.select(aQuery).execute();

            try {
                assertTrue("Should have a result", aResult.hasNext());

                final String aValue = aResult.next().getValue("result").stringValue();

                assertEquals(0.7071, Double.parseDouble(aValue), 0.0001);

                assertFalse("Should have no more results", aResult.hasNext());
            }
            finally {
                aResult.close();
            }
    }

    @Test
    public void testCosineTooManyArgs() throws Exception {

            final String aQuery = "prefix stringcomparision: <" + StringComparisonVocabulary.NAMESPACE + "> " +
                    "select ?result where { bind(stringcomparision:cosineSimilarity(\"one\", \"two\", \"three\", \"four\") as ?result) }";

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

    @Test
    public void testCosineWrongTypeFirstArg() throws Exception {

            final String aQuery = "prefix stringcomparision: <" + StringComparisonVocabulary.NAMESPACE + ">" +
                    "select ?result where { bind(stringcomparision:cosineSimilarity(7) as ?result) }";

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

    @Test
    public void testCosineWrongTypeSecondArg() throws Exception {

            final String aQuery = "prefix stringcomparision: <" + StringComparisonVocabulary.NAMESPACE + ">" +
                    "select ?result where { bind(stringcomparision:cosineSimilarity(\"Stardog\", 2) as ?result) }";

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

    @Test
    public void testCosineWrongTypeThirdArg() throws Exception {

            final String aQuery = "prefix stringcomparision: <" + StringComparisonVocabulary.NAMESPACE + ">" +
                    "select ?result where { bind(stringcomparision:cosineSimilarity(\"Stardog\", \"Starlight\", \"Starship\") as ?result) }";

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

    @Test
    public void testCosineThirdArgNotConstant() throws Exception {

            final String aQuery = "prefix stringcomparision: <" + StringComparisonVocabulary.NAMESPACE + ">" +
                    "select ?result where { bind(stringcomparision:cosineSimilarity(\"Stardog\", \"Starlight\", ?thirdArg) as ?result) }";

            try(final TupleQueryResult aResult = aConn.select(aQuery).execute()) {
                // there should be a result because implicit in the query is the singleton set, so because the bind
                // should fail due to the value error, we expect a single empty binding
                assertTrue("Should have a result", aResult.hasNext());

                final BindingSet aBindingSet = aResult.next();

                assertTrue("Should have no bindings", aBindingSet.getBindingNames().isEmpty());

                assertFalse("Should have no more results", aResult.hasNext());
            }
    }
}
