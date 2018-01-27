package com.semantalytics.stardog.kibble.strings.string;

import com.semantalytics.stardog.kibble.AbstractStardogTest;
import org.junit.*;
import org.openrdf.query.BindingSet;
import org.openrdf.query.TupleQueryResult;

import static org.junit.Assert.*;

public class UncapitalizeTest  extends AbstractStardogTest {

   
    @Test
    public void testAbbreviateMiddle() throws Exception {
       
            final String aQuery = "prefix string: <" + StringVocabulary.NAMESPACE + "> " +
                    "select ?abbreviation where { bind(string:uncapitalize(\"Stardog graph database\", \"...\", 8) AS ?abbreviation) }";


            try (final TupleQueryResult aResult = connection.select(aQuery).execute()) {

                assertTrue("Should have a result", aResult.hasNext());

                final String aValue = aResult.next().getValue("abbreviation").stringValue();

                assertEquals("Stard...", aValue);

                assertFalse("Should have no more results", aResult.hasNext());
            }
        
    }

    @Test
    public void testEmptyString() throws Exception {
      
            final String aQuery = "prefix string: <" + StringVocabulary.NAMESPACE + "> " +
                    "select ?abbreviation where { bind(string:uncapitalize(\"\", 5) as ?abbreviation) }";

            final TupleQueryResult aResult = connection.select(aQuery).execute();

        
                assertTrue("Should have a result", aResult.hasNext());

                final String aValue = aResult.next().getValue("abbreviation").stringValue();

                assertEquals("", aValue);

                assertFalse("Should have no more results", aResult.hasNext());
           
    }

    @Test
    public void testTooFewArgs() throws Exception {

       
            final String aQuery = "prefix string: <" + StringVocabulary.NAMESPACE + "> " +
                    "select ?abbreviation where { bind(string:uncapitalize(\"one\") as ?abbreviation) }";

            final TupleQueryResult aResult = connection.select(aQuery).execute();
                // there should be a result because implicit in the query is the singleton set, so because the bind
                // should fail due to the value error, we expect a single empty binding
                assertTrue("Should have a result", aResult.hasNext());

                final BindingSet aBindingSet = aResult.next();

                assertTrue("Should have no bindings", aBindingSet.getBindingNames().isEmpty());

                assertFalse("Should have no more results", aResult.hasNext());
           
    }


    @Test
    public void testTooManyArgs() throws Exception {

            final String aQuery = "prefix string: <" + StringVocabulary.NAMESPACE + "> " +
                    "select ?abbreviation where { bind(string:uncapitalize(\"one\", 2, \"three\") as ?abbreviation) }";

            final TupleQueryResult aResult = connection.select(aQuery).execute();
        
                // there should be a result because implicit in the query is the singleton set, so because the bind
                // should fail due to the value error, we expect a single empty binding
                assertTrue("Should have a result", aResult.hasNext());

                final BindingSet aBindingSet = aResult.next();

                assertTrue("Should have no bindings", aBindingSet.getBindingNames().isEmpty());

                assertFalse("Should have no more results", aResult.hasNext());
           
    }



    @Test
    public void testWrongTypeFirstArg() throws Exception {
      
            final String aQuery = "prefix string: <" + StringVocabulary.NAMESPACE + "> " +
                    "select ?abbreviation where { bind(string:uncapitalize(4, 5) as ?abbreviation) }";

            final TupleQueryResult aResult = connection.select(aQuery).execute();
          
                // there should be a result because implicit in the query is the singleton set, so because the bind
                // should fail due to the value error, we expect a single empty binding
                assertTrue("Should have a result", aResult.hasNext());

                final BindingSet aBindingSet = aResult.next();

                assertTrue("Should have no bindings", aBindingSet.getBindingNames().isEmpty());

                assertFalse("Should have no more results", aResult.hasNext());
           
    }

    @Test
    public void testWrongTypeSecondArg() throws Exception {
        

            final String aQuery = "prefix string: <" + StringVocabulary.NAMESPACE + "> " +
                    "select ?abbreviation where { bind(string:uncapitalize(\"one\", \"two\") as ?abbreviation) }";

            final TupleQueryResult aResult = connection.select(aQuery).execute();
   
                // there should be a result because implicit in the query is the singleton set, so because the bind
                // should fail due to the value error, we expect a single empty binding
                assertTrue("Should have a result", aResult.hasNext());

                final BindingSet aBindingSet = aResult.next();

                assertTrue("Should have no bindings", aBindingSet.getBindingNames().isEmpty());

                assertFalse("Should have no more results", aResult.hasNext());
           
    }

    @Test
    public void testLengthTooShort() throws Exception {
       

            final String aQuery = "prefix string: <" + StringVocabulary.NAMESPACE + "> " +
                    "select ?abbreviation where { bind(string:uncapitalize(\"Stardog\", 3) as ?abbreviation) }";

            final TupleQueryResult aResult = connection.select(aQuery).execute();
           
                // there should be a result because implicit in the query is the singleton set, so because the bind
                // should fail due to the value error, we expect a single empty binding
                assertTrue("Should have a result", aResult.hasNext());

                final BindingSet aBindingSet = aResult.next();

                assertTrue("Should have no bindings", aBindingSet.getBindingNames().isEmpty());

                assertFalse("Should have no more results", aResult.hasNext());
          
    }
}
