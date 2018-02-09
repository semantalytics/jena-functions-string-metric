
package com.semantalytics.stardog.kibble.strings.string;

import com.semantalytics.stardog.kibble.AbstractStardogTest;
import org.junit.Test;
import org.openrdf.query.BindingSet;
import org.openrdf.query.TupleQueryResult;

import static org.junit.Assert.*;

public class TestEqualsIgnoreCase extends AbstractStardogTest {

    @Test
    public void testIndexOfAny() {
   
            final String aQuery = StringVocabulary.sparqlPrefix("string") +
                    "select ?result where { bind(string:equalsIgnoreCase(\"stardog\" \"dog\") AS ?result) }";

            try (final TupleQueryResult aResult = connection.select(aQuery).execute()) {

                assertTrue("Should have a result", aResult.hasNext());

                final int aValue = Integer.parseInt(aResult.next().getValue("result").stringValue());

                assertEquals(5, aValue);

                assertFalse("Should have no more results", aResult.hasNext());
            }
    }
  
    @Test
    public void testEmptyString() {
       
            final String aQuery = StringVocabulary.sparqlPrefix("string") +
                    "select ?result where { bind(string:equalsIgnoreCase(\"\", \"\") as ?result) }";

            try(final TupleQueryResult aResult = connection.select(aQuery).execute()) {
        
                assertTrue("Should have a result", aResult.hasNext());

                final int aValue = Integer.parseInt(aResult.next().getValue("result").stringValue());

                assertEquals(-1, aValue);
                assertFalse("Should have no more results", aResult.hasNext());
            }
    }

    @Test
    public void testTooFewArgs() {

            final String aQuery = StringVocabulary.sparqlPrefix("string") +
                    "select ?result where { bind(string:equalsIgnoreCase(\"one\") as ?result) }";

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
                    "select ?result where { bind(string:equalsIgnoreCase(\"one\", \"two\", \"three\") as ?result) }";

            try(final TupleQueryResult aResult = connection.select(aQuery).execute()) {
         
                assertTrue("Should have a result", aResult.hasNext());

                final BindingSet aBindingSet = aResult.next();

                assertTrue("Should have no bindings", aBindingSet.getBindingNames().isEmpty());
                assertFalse("Should have no more results", aResult.hasNext());
            }
    }

    @Test
    public void testWrongTypeFirstArg() {
       
            final String aQuery = StringVocabulary.sparqlPrefix("string") +
                    "select ?result where { bind(string:equalsIgnoreCase(1, \"two\") as ?result) }";

            try(final TupleQueryResult aResult = connection.select(aQuery).execute()) {
       
                assertTrue("Should have a result", aResult.hasNext());

                final BindingSet aBindingSet = aResult.next();

                assertTrue("Should have no bindings", aBindingSet.getBindingNames().isEmpty());
                assertFalse("Should have no more results", aResult.hasNext());
            }
    }
  
  
    @Test
    public void testWrongTypeSecondArg() {
       
            final String aQuery = StringVocabulary.sparqlPrefix("string") +
                    "select ?result where { bind(string:equalsIgnoreCase(\"one\", 2) as ?result) }";

            try(final TupleQueryResult aResult = connection.select(aQuery).execute()) {
       
                assertTrue("Should have a result", aResult.hasNext());

                final BindingSet aBindingSet = aResult.next();

                assertTrue("Should have no bindings", aBindingSet.getBindingNames().isEmpty());
                assertFalse("Should have no more results", aResult.hasNext());
            }
    }
}
