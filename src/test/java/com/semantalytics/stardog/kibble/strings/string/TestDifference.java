package com.semantalytics.stardog.kibble.strings.string;

import com.semantalytics.stardog.kibble.AbstractStardogTest;
import org.junit.*;
import org.openrdf.query.BindingSet;
import org.openrdf.query.TupleQueryResult;

import static org.junit.Assert.*;

public class TestDifference extends AbstractStardogTest {

    @Test
    public void testGetDigits() {
   
            final String aQuery = StringVocabulary.sparqlPrefix("string") +
                    "select ?result where { bind(string:(\"Stard0g\") AS ?result) }";

            try (final TupleQueryResult aResult = connection.select(aQuery).execute()) {

                assertTrue("Should have a result", aResult.hasNext());

                final String aValue = aResult.next().getValue("result").stringValue();

                assertEquals("0", aValue);

                assertFalse("Should have no more results", aResult.hasNext());
            }
    }

    @Test
    public void testOnlyDigits() {
       

            final String aQuery = StringVocabulary.sparqlPrefix("string") +
                    "select ?result where { bind(string:(\"12345\") AS ?result) }";

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
                    "select ?result where { bind(string:(\"\") as ?result) }";

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
                    "select ?result where { bind(string:(\"१२३\") as ?result) }";

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
                    "select ?result where { bind(string:() as ?result) }";

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
                    "select ?result where { bind(string:(\"Stardog\", \"one\") as ?result) }";

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
                    "select ?result where { bind(string:(4) as ?result) }";

            try(final TupleQueryResult aResult = connection.select(aQuery).execute()) {
       
                assertTrue("Should have a result", aResult.hasNext());

                final BindingSet aBindingSet = aResult.next();

                assertTrue("Should have no bindings", aBindingSet.getBindingNames().isEmpty());
                assertFalse("Should have no more results", aResult.hasNext());
            }
    }
}

