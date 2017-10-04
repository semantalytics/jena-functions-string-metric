package com.semantalytics.stardog.plan.filter.functions.string.comparison;

import com.complexible.stardog.api.Connection;
import com.complexible.stardog.api.ConnectionConfiguration;
import org.junit.*;
import org.junit.rules.ExternalResource;
import org.openrdf.query.BindingSet;
import org.openrdf.query.TupleQueryResult;

import static org.junit.Assert.*;

public class TestCosine extends AbstractStardogTest {

    Connection connection;

    @Rule
    public final ExternalResource resource = new ExternalResource() {

        @Before
        public void before() throws Throwable {

            connection = ConnectionConfiguration.to(DB)
                    .credentials("admin", "admin")
                    .connect();
        }


        @After
        public void after() {
            connection.close();
        }
    };

    @Test
    public void testCosineTwoArg() throws Exception {


            final String aQuery = "prefix ss: <" + StringSimilarityVocab.NAMESPACE + "> " +
                    "select ?result where { bind(ss:cosine(\"ABC\", \"ABCE\") as ?result) }";

            final TupleQueryResult aResult = connection.select(aQuery).execute();

            try {
                assertTrue("Should have a result", aResult.hasNext());

                final String aValue = aResult.next().getValue("result").stringValue();

                assertEquals(0.29289, Double.parseDouble(aValue), 0.0001);

                assertFalse("Should have no more results", aResult.hasNext());
            }
            finally {
                aResult.close();
            }
    }

    @Test
    public void testCosineThreeArg() throws Exception {

            final String aQuery = "prefix ss: <" + StringSimilarityVocab.NAMESPACE + "> " +
                    "select ?result where { bind(ss:cosine(\"ABC\", \"ABCE\", 3) as ?result) }";

            final TupleQueryResult aResult = connection.select(aQuery).execute();

            try {
                assertTrue("Should have a result", aResult.hasNext());

                final String aValue = aResult.next().getValue("result").stringValue();

                assertEquals(0.29289, Double.parseDouble(aValue), 0.0001);

                assertFalse("Should have no more results", aResult.hasNext());
            }
            finally {
                aResult.close();
            }
    }

    @Test
    public void testCosineTooManyArgs() throws Exception {

            final String aQuery = "prefix ss: <" + StringSimilarityVocab.NAMESPACE + "> " +
                    "select ?result where { bind(ss:cosine(\"one\", \"two\", \"three\", \"four\") as ?result) }";

            final TupleQueryResult aResult = connection.select(aQuery).execute();
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

            final String aQuery = "prefix ss: <" + StringSimilarityVocab.NAMESPACE + ">" +
                    "select ?result where { bind(ss:cosine(7) as ?result) }";

            final TupleQueryResult aResult = connection.select(aQuery).execute();
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

            final String aQuery = "prefix ss: <" + StringSimilarityVocab.NAMESPACE + ">" +
                    "select ?result where { bind(ss:cosine(\"Stardog\", 2) as ?result) }";

            final TupleQueryResult aResult = connection.select(aQuery).execute();
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

            final String aQuery = "prefix ss: <" + StringSimilarityVocab.NAMESPACE + ">" +
                    "select ?result where { bind(ss:cosine(\"Stardog\", \"Starlight\", \"Starship\") as ?result) }";

            final TupleQueryResult aResult = connection.select(aQuery).execute();
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

            final String aQuery = "prefix ss: <" + StringSimilarityVocab.NAMESPACE + ">" +
                    "select ?result where { bind(ss:cosine(\"Stardog\", \"Starlight\", ?thirdArg) as ?result) }";

            final TupleQueryResult aResult = connection.select(aQuery).execute();
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
}
