package com.semantalytics.stardog.plan.filter.functions.strings;

import com.complexible.stardog.api.Connection;
import com.complexible.stardog.api.ConnectionConfiguration;
import org.junit.Test;
import org.openrdf.query.BindingSet;
import org.openrdf.query.TupleQueryResult;

import static org.junit.Assert.*;

public class PadEndTest extends AbstractStardogTest {

    @Test
    public void testThreeArgument() throws Exception {
        final Connection aConn = ConnectionConfiguration.to(DB)
                .credentials("admin", "admin")
                .connect();

        try {

            final String aQuery = "prefix string: <" + StringsVocab.NS + "> " +
                    "select ?result where { bind(string:padEnd(\"Stardog\", 20, \"*\") AS ?result) }";


            try (final TupleQueryResult aResult = aConn.select(aQuery).execute()) {

                assertTrue("Should have a result", aResult.hasNext());

                final String aValue = aResult.next().getValue("result").stringValue();

                assertEquals("Stardog*************", aValue);

                assertFalse("Should have no more results", aResult.hasNext());
            }
        }
        finally {
            aConn.close();
        }
    }

    @Test
    public void testEmptyString() throws Exception {
        final Connection aConn = ConnectionConfiguration.to(DB)
                .credentials("admin", "admin")
                .connect();

        try {

            final String aQuery = "prefix string: <" + StringsVocab.NS + "> " +
                    "select ?result where { bind(string:padEnd(\"\", 20, \"*\") as ?result) }";

            final TupleQueryResult aResult = aConn.select(aQuery).execute();

            try {
                assertTrue("Should have a result", aResult.hasNext());

                final String aValue = aResult.next().getValue("result").stringValue();

                assertEquals("********************", aValue);

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
    public void testEmptyPad() throws Exception {
        final Connection aConn = ConnectionConfiguration.to(DB)
                .credentials("admin", "admin")
                .connect();

        try {
            final String aQuery = "prefix string: <" + StringsVocab.NS + "> " +
                    "select ?result where { bind(string:padEnd(\"one\", 20, \"\") as ?result) }";

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
    public void testTooFewArgs() throws Exception {

        final Connection aConn = ConnectionConfiguration.to(DB)
                .credentials("admin", "admin")
                .connect();

        try {
            final String aQuery = "prefix string: <" + StringsVocab.NS + "> " +
                    "select ?result where { bind(string:padEnd(\"one\", 20) as ?result) }";

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
    public void testTooManyArgs() throws Exception {

        final Connection aConn = ConnectionConfiguration.to(DB)
                .credentials("admin", "admin")
                .connect();

        try {
            final String aQuery = "prefix string: <" + StringsVocab.NS + "> " +
                    "select ?result where { bind(string:padEnd(\"Stardog\", 20, \"*\", 3) as ?result) }";

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
    public void testWrongTypeFirstArg() throws Exception {
        final Connection aConn = ConnectionConfiguration.to(DB)
                .credentials("admin", "admin")
                .connect();

        try {

            final String aQuery = "prefix string: <" + StringsVocab.NS + "> " +
                    "select ?result where { bind(string:padEnd(1, 20, \"*\") as ?result) }";

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
    public void testWrongTypeSecondArg() throws Exception {
        final Connection aConn = ConnectionConfiguration.to(DB)
                .credentials("admin", "admin")
                .connect();

        try {

            final String aQuery = "prefix string: <" + StringsVocab.NS + "> " +
                    "select ?result where { bind(string:padEnd(\"Stardog\", \"twenty\", \"*\") as ?result) }";

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
    public void testWrongTypeThirdArg() throws Exception {
        final Connection aConn = ConnectionConfiguration.to(DB)
                .credentials("admin", "admin")
                .connect();

        try {

            final String aQuery = "prefix string: <" + StringsVocab.NS + "> " +
                    "select ?result where { bind(string:padEnd(\"Stardog\", 20, 1) as ?result) }";

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
