package com.semantalytics.stardog.kibble.strings.string;

import com.complexible.stardog.plan.filter.EvalUtil;
import com.semantalytics.stardog.kibble.AbstractStardogTest;
import org.junit.*;
import org.openrdf.model.impl.BooleanLiteral;
import org.openrdf.query.BindingSet;
import org.openrdf.query.TupleQueryResult;

import static org.junit.Assert.*;

public class EndsWithIgnoreCaseTest extends AbstractStardogTest {

    @Test
    public void testEndsWithIgnoreCase() {

        final String aQuery = StringVocabulary.sparqlPrefix("string") +
                "select ?result where { bind(string:endsWithIgnoreCase(\"Stardog\", \"dog\") AS ?result) }";

        try (final TupleQueryResult aResult = connection.select(aQuery).execute()) {

            assertTrue("Should have a result", aResult.hasNext());

            final boolean aValue = ((BooleanLiteral)aResult.next().getValue("result")).booleanValue();

            assertEquals(true, aValue);

            assertFalse("Should have no more results", aResult.hasNext());
        }
    }

    @Test
    public void testOnlyDigits() {


        final String aQuery = StringVocabulary.sparqlPrefix("string") +
                "select ?result where { bind(string:endsWithIgnoreCase(\"12345\") AS ?result) }";

        try (final TupleQueryResult aResult = connection.select(aQuery).execute()) {

            assertTrue("Should have a result", aResult.hasNext());

            final String aValue = aResult.next().getValue("result").stringValue();

            assertEquals("12345", aValue);
            assertFalse("Should have no more results", aResult.hasNext());
        }
    }

    @Test
    public void testEmptyString() {

        final String aQuery = StringVocabulary.sparqlPrefix("string") +
                "select ?result where { bind(string:endsWithIgnoreCase(\"\") as ?result) }";

        try(final TupleQueryResult aResult = connection.select(aQuery).execute()) {

            assertTrue("Should have a result", aResult.hasNext());

            final String aValue = aResult.next().getValue("result").stringValue();

            assertEquals("", aValue);
            assertFalse("Should have no more results", aResult.hasNext());
        }
    }

    @Test
    public void testNonAscii() {

        final String aQuery = StringVocabulary.sparqlPrefix("string") +
                "select ?result where { bind(string:endsWithIgnoreCase(\"१२३\") as ?result) }";

        try(final TupleQueryResult aResult = connection.select(aQuery).execute()) {


            assertTrue("Should have a result", aResult.hasNext());

            final String aValue = aResult.next().getValue("result").stringValue();

            assertEquals("१२३", aValue);
            assertFalse("Should have no more results", aResult.hasNext());
        }
    }

    @Test
    public void testTooFewArgs() {


        final String aQuery = StringVocabulary.sparqlPrefix("string") +
                "select ?result where { bind(string:endsWithIgnoreCase() as ?result) }";

        try(final TupleQueryResult aResult = connection.select(aQuery).execute()) {

            assertTrue("Should have a result", aResult.hasNext());

            final BindingSet aBindingSet = aResult.next();

            assertTrue("Should have no bindings", aBindingSet.getBindingNames().isEmpty());
            assertFalse("Should have no more results", aResult.hasNext());
        }
    }

    @Test
    public void testTooManyArgs() {


        final String aQuery = StringVocabulary.sparqlPrefix("string") +
                "select ?result where { bind(string:endsWithIgnoreCase(\"Stardog\", \"one\") as ?result) }";

        try(final TupleQueryResult aResult = connection.select(aQuery).execute()) {

            assertTrue("Should have a result", aResult.hasNext());

            final BindingSet aBindingSet = aResult.next();

            assertTrue("Should have no bindings", aBindingSet.getBindingNames().isEmpty());
            assertFalse("Should have no more results", aResult.hasNext());
        }
    }

    @Test
    public void testWrongType() {

        final String aQuery = StringVocabulary.sparqlPrefix("string") +
                "select ?result where { bind(string:endsWithIgnoreCase(4) as ?result) }";

        try(final TupleQueryResult aResult = connection.select(aQuery).execute()) {

            assertTrue("Should have a result", aResult.hasNext());

            final BindingSet aBindingSet = aResult.next();

            assertTrue("Should have no bindings", aBindingSet.getBindingNames().isEmpty());
            assertFalse("Should have no more results", aResult.hasNext());
        }
    }
}
