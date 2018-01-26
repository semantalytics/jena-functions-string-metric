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

public class IsAllUpperCaseTest  extends AbstractStardogTest {

    @Test
    public void testAbbreviateMiddle() throws Exception {
       

            final String aQuery = "prefix string: <" + StringVocabulary.NAMESPACE + "> " +
                    "select ?abbreviation where { bind(string:isAllUpperCase(\"Stardog graph database\", \"...\", 8) AS ?abbreviation) }";


            try (final TupleQueryResult aResult = aConn.select(aQuery).execute()) {

                assertTrue("Should have a result", aResult.hasNext());

                final String aValue = aResult.next().getValue("abbreviation").stringValue();

                assertEquals("Stard...", aValue);

                assertFalse("Should have no more results", aResult.hasNext());
            }
       
    }

    @Test
    public void testEmptyString() throws Exception {
      

            final String aQuery = "prefix string: <" + StringVocabulary.NAMESPACE + "> " +
                    "select ?abbreviation where { bind(string:isAllUpperCase(\"\", 5) as ?abbreviation) }";

            final TupleQueryResult aResult = aConn.select(aQuery).execute();

          
                assertTrue("Should have a result", aResult.hasNext());

                final String aValue = aResult.next().getValue("abbreviation").stringValue();

                assertEquals("", aValue);

                assertFalse("Should have no more results", aResult.hasNext());
            
    }

    @Test
    public void testTooFewArgs() throws Exception {

       
            final String aQuery = "prefix string: <" + StringVocabulary.NAMESPACE + "> " +
                    "select ?abbreviation where { bind(string:isAllUpperCase(\"one\") as ?abbreviation) }";

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
                    "select ?abbreviation where { bind(string:isAllUpperCase(\"one\", 2, \"three\") as ?abbreviation) }";

            final TupleQueryResult aResult = aConn.select(aQuery).execute();
          
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
                    "select ?abbreviation where { bind(string:isAllUpperCase(4, 5) as ?abbreviation) }";

            final TupleQueryResult aResult = aConn.select(aQuery).execute();
           
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
                    "select ?abbreviation where { bind(string:isAllUpperCase(\"one\", \"two\") as ?abbreviation) }";

            final TupleQueryResult aResult = aConn.select(aQuery).execute();
         
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
                    "select ?abbreviation where { bind(string:isAllUpperCase(\"Stardog\", 3) as ?abbreviation) }";

            final TupleQueryResult aResult = aConn.select(aQuery).execute();
           
                // there should be a result because implicit in the query is the singleton set, so because the bind
                // should fail due to the value error, we expect a single empty binding
                assertTrue("Should have a result", aResult.hasNext());

                final BindingSet aBindingSet = aResult.next();

                assertTrue("Should have no bindings", aBindingSet.getBindingNames().isEmpty());

                assertFalse("Should have no more results", aResult.hasNext());
           
    }
}
