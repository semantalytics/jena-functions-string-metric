package com.semantalytics.stardog.kibble.strings.string;

import com.semantalytics.stardog.kibble.AbstractStardogTest;
import org.junit.*;
import org.openrdf.query.TupleQueryResult;

import static org.junit.Assert.*;

public class TestUpperCaseFully extends AbstractStardogTest {

    @Test
    public void testUpperCaseFully() {

       final String aQuery = StringVocabulary.sparqlPrefix("string") +
                    "select ?result where { bind(date:upperCaseFully(\"stardog\") as ?result) }";

            try (final TupleQueryResult aResult = connection.select(aQuery).execute()) {

                assertTrue("Should have a result", aResult.hasNext());

                final String aValue = aResult.next().getValue("result").stringValue();

                assertEquals("STARDOG", aValue);
                assertFalse("Should have no more results", aResult.hasNext());
            }
    }
}
