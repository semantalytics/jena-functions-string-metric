package com.semantalytics.stardog.kibble.strings.string;

import com.semantalytics.stardog.kibble.AbstractStardogTest;
import org.junit.*;
import org.openrdf.query.BindingSet;
import org.openrdf.query.TupleQueryResult;

import static org.junit.Assert.*;

public class AbbreviateMiddleTest  extends AbstractStardogTest {

    @Test
    public void testAbbreviateMiddle() throws Exception {
      

            final String aQuery = "prefix string: <" + StringVocabulary.NAMESPACE + "> " +
                    "select ?result where { bind(string:abbreviateMiddle(\"Stardog graph database\", \"...\", 8) AS ?result) }";


            try (final TupleQueryResult aResult = connection.select(aQuery).execute()) {

                assertTrue("Should have a result", aResult.hasNext());

                final String aValue = aResult.next().getValue("result").stringValue();

                assertEquals("Sta...se", aValue);

                assertFalse("Should have no more results", aResult.hasNext());
            }
      
    }

    @Test
    public void testEmptyString() throws Exception {
      

            final String aQuery = "prefix string: <" + StringVocabulary.NAMESPACE + "> " +
                    "select ?result where { bind(string:abbreviateMiddle(\"\", , 10) as ?result) }";

            final TupleQueryResult aResult = connection.select(aQuery).execute();

           
                assertTrue("Should have a result", aResult.hasNext());

                final String aValue = aResult.next().getValue("result").stringValue();

                assertEquals("", aValue);

                assertFalse("Should have no more results", aResult.hasNext());
          
    }

    @Test
    public void testTooFewArgs() throws Exception {

      
            final String aQuery = "prefix string: <" + StringVocabulary.NAMESPACE + "> " +
                    "select ?result where { bind(string:abbreviateMiddle(\"one\") as ?result) }";

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
                    "select ?result where { bind(string:abbreviateMiddle(\"one\", 2, \"three\") as ?result) }";

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
                    "select ?result where { bind(string:abbreviateMiddle(4, 5) as ?result) }";

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
                    "select ?result where { bind(string:abbreviateMiddle(\"one\", \"two\") as ?result) }";

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
                    "select ?result where { bind(string:abbreviateMiddle(\"Stardog\", 3) as ?result) }";

            final TupleQueryResult aResult = connection.select(aQuery).execute();
         
                // there should be a result because implicit in the query is the singleton set, so because the bind
                // should fail due to the value error, we expect a single empty binding
                assertTrue("Should have a result", aResult.hasNext());

                final BindingSet aBindingSet = aResult.next();

                assertTrue("Should have no bindings", aBindingSet.getBindingNames().isEmpty());

                assertFalse("Should have no more results", aResult.hasNext());
           
    }
}
