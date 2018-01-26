package com.semantalytics.stardog.kibble.strings.comparison;

import com.complexible.stardog.Stardog;
import com.complexible.stardog.api.Connection;
import com.complexible.stardog.api.ConnectionConfiguration;
import com.complexible.stardog.api.admin.AdminConnection;
import com.complexible.stardog.api.admin.AdminConnectionConfiguration;
import org.junit.*;
import org.openrdf.query.BindingSet;
import org.openrdf.query.TupleQueryResult;

import static org.junit.Assert.*;

public class TestSmithWatermanGotoh  extends AbstractStardogTest {

  
    @Test
    public void testSmithWatermanGotohTwoArg() throws Exception {

    

            final String aQuery = "prefix ss: <" + StringComparisonVocabulary.NAMESPACE + "> " +
                    "select ?result where { bind(ss:smithWatermanGotoh(\"Stardog\", \"Starman\") as ?result) }";

            final TupleQueryResult aResult = aConn.select(aQuery).execute();

       
                assertTrue("Should have a result", aResult.hasNext());

                final String aValue = aResult.next().getValue("result").stringValue();

                assertEquals(0.5714286, Double.parseDouble(aValue), 0.00001);

                assertFalse("Should have no more results", aResult.hasNext());
          
    }

    @Test
    public void testSmithWatermanGotohFiveArg() throws Exception {

     

            final String aQuery = "prefix ss: <" + StringComparisonVocabulary.NAMESPACE + "> " +
                    "select ?result where { bind(ss:smithWatermanGotoh(\"Stardog\", \"Starman\", -0.5, 1.0, -2.0) as ?result) }";

            final TupleQueryResult aResult = aConn.select(aQuery).execute();

          
                assertTrue("Should have a result", aResult.hasNext());

                final String aValue = aResult.next().getValue("result").stringValue();

                assertEquals(0.5714286, Double.parseDouble(aValue), 0.00001);

                assertFalse("Should have no more results", aResult.hasNext());
           
    }
    
    @Test
    public void testSmithWatermanGotohTooManyArgs() throws Exception {

      
            final String aQuery = "prefix ss: <" + StringComparisonVocabulary.NAMESPACE + "> " +
                    "select ?dist where { bind(ss:smithWatermanGotoh(\"one\", \"two\", \"three\", \"four\") as ?dist) }";

            final TupleQueryResult aResult = aConn.select(aQuery).execute();
        
                // there should be a result because implicit in the query is the singleton set, so because the bind
                // should fail due to the value error, we expect a single empty binding
                assertTrue("Should have a result", aResult.hasNext());

                final BindingSet aBindingSet = aResult.next();

                assertTrue("Should have no bindings", aBindingSet.getBindingNames().isEmpty());

                assertFalse("Should have no more results", aResult.hasNext());
           
    }

    @Test
    public void testSmithWatermanGotohWrongType() throws Exception {

     

            final String aQuery = "prefix ss: <" + StringComparisonVocabulary.NAMESPACE + "> " +
                    "select ?dist where { bind(ss:smithWatermanGotoh(7) as ?dist) }";

            final TupleQueryResult aResult = aConn.select(aQuery).execute();
          
                // there should be a result because implicit in the query is the singleton set, so because the bind
                // should fail due to the value error, we expect a single empty binding
                assertTrue("Should have a result", aResult.hasNext());

                final BindingSet aBindingSet = aResult.next();

                assertTrue("Should have no bindings", aBindingSet.getBindingNames().isEmpty());

                assertFalse("Should have no more results", aResult.hasNext());
           
    }
}
