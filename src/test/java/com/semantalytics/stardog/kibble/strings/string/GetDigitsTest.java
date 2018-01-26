package com.semantalytics.stardog.kibble.strings.string;

import com.complexible.stardog.Stardog;
import com.complexible.stardog.api.Connection;
import com.complexible.stardog.api.ConnectionConfiguration;
import com.complexible.stardog.api.admin.AdminConnection;
import com.complexible.stardog.api.admin.AdminConnectionConfiguration;
import org.junit.*;
import org.openrdf.query.BindingSet;
import org.openrdf.query.TupleQueryResult;

import static org.junit.Assert.*;

public class GetDigitsTest  extends AbstractStardogTest {

  
    @Test
    public void testGetDigits() throws Exception {
   
            final String aQuery = "prefix string: <" + StringVocabulary.NAMESPACE + "> " +
                    "select ?result where { bind(string:getDigits(\"Stard0g\") AS ?result) }";


            try (final TupleQueryResult aResult = aConn.select(aQuery).execute()) {

                assertTrue("Should have a result", aResult.hasNext());

                final String aValue = aResult.next().getValue("result").stringValue();

                assertEquals("0", aValue);

                assertFalse("Should have no more results", aResult.hasNext());
            }
     
    }

    @Test
    public void testOnlyDigits() throws Exception {
       

            final String aQuery = "prefix string: <" + StringVocabulary.NAMESPACE + "> " +
                    "select ?result where { bind(string:getDigits(\"12345\") AS ?result) }";


            try (final TupleQueryResult aResult = aConn.select(aQuery).execute()) {

                assertTrue("Should have a result", aResult.hasNext());

                final String aValue = aResult.next().getValue("result").stringValue();

                assertEquals("12345", aValue);

                assertFalse("Should have no more results", aResult.hasNext());
            }
       
    }
    @Test
    public void testEmptyString() throws Exception {
       

            final String aQuery = "prefix string: <" + StringVocabulary.NAMESPACE + "> " +
                    "select ?result where { bind(string:getDigits(\"\") as ?result) }";

            final TupleQueryResult aResult = aConn.select(aQuery).execute();

        
                assertTrue("Should have a result", aResult.hasNext());

                final String aValue = aResult.next().getValue("result").stringValue();

                assertEquals("", aValue);

                assertFalse("Should have no more results", aResult.hasNext());
            
    }

    @Test
    public void testTooFewArgs() throws Exception {

       
            final String aQuery = "prefix string: <" + StringVocabulary.NAMESPACE + "> " +
                    "select ?result where { bind(string:getDigits() as ?result) }";

            final TupleQueryResult aResult = aConn.select(aQuery).execute();
          
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
                    "select ?result where { bind(string:getDigits(\"Stardog\", \"one\") as ?result) }";

            final TupleQueryResult aResult = aConn.select(aQuery).execute();
         
                // there should be a result because implicit in the query is the singleton set, so because the bind
                // should fail due to the value error, we expect a single empty binding
                assertTrue("Should have a result", aResult.hasNext());

                final BindingSet aBindingSet = aResult.next();

                assertTrue("Should have no bindings", aBindingSet.getBindingNames().isEmpty());

                assertFalse("Should have no more results", aResult.hasNext());
           
    }

    @Test
    public void testWrongType() throws Exception {
       
            final String aQuery = "prefix string: <" + StringVocabulary.NAMESPACE + "> " +
                    "select ?result where { bind(string:getDigits(4) as ?result) }";

            final TupleQueryResult aResult = aConn.select(aQuery).execute();
       
                // there should be a result because implicit in the query is the singleton set, so because the bind
                // should fail due to the value error, we expect a single empty binding
                assertTrue("Should have a result", aResult.hasNext());

                final BindingSet aBindingSet = aResult.next();

                assertTrue("Should have no bindings", aBindingSet.getBindingNames().isEmpty());

                assertFalse("Should have no more results", aResult.hasNext());
          
    }
}
