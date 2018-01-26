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

public class TestSmithWaterman  extends AbstractStardogTest {

  

    @Test
    public void testSmithWatermanTwoArg() throws Exception {

    

            final String aQuery = "prefix ss: <" + StringComparisonVocabulary.NAMESPACE + "> " +
                    "select ?result where { bind(ss:smithWaterman(\"Stardog\", \"Starman\") as ?result) }";

            final TupleQueryResult aResult = aConn.select(aQuery).execute();

     
                assertTrue("Should have a result", aResult.hasNext());

                final String aValue = aResult.next().getValue("result").stringValue();

                assertEquals(0.5714286, Double.parseDouble(aValue), 0.00001);

                assertFalse("Should have no more results", aResult.hasNext());
       
    }

    @Test
    public void testSmithWatermanSevenArg() throws Exception {

    

            final String aQuery = "prefix ss: <" + StringComparisonVocabulary.NAMESPACE + "> " +
                    "select ?result where { bind(ss:smithWaterman(\"Stardog\", \"Starman\", -5.0, -1.0, 5.0, -3.0, " + Integer.MAX_VALUE + ") as ?result) }";

            final TupleQueryResult aResult = aConn.select(aQuery).execute();

        
                assertTrue("Should have a result", aResult.hasNext());

                final String aValue = aResult.next().getValue("result").stringValue();

                assertEquals(0.5714286, Double.parseDouble(aValue), 0.00001);

                assertFalse("Should have no more results", aResult.hasNext());
          
    }

    @Test
    public void testSmithWatermanWrongNumberOfArgs3() throws Exception {

    

            final String aQuery = "prefix ss: <" + StringComparisonVocabulary.NAMESPACE + "> " +
                    "select ?result where { bind(ss:smithWaterman(\"Stardog\", \"Starman\", 1.0) as ?result) }";

            final TupleQueryResult aResult = aConn.select(aQuery).execute();
       
                // there should be a result because implicit in the query is the singleton set, so because the bind
                // should fail due to the value error, we expect a single empty binding
                assertTrue("Should have a result", aResult.hasNext());

                final BindingSet aBindingSet = aResult.next();

                assertTrue("Should have no bindings", aBindingSet.getBindingNames().isEmpty());

                assertFalse("Should have no more results", aResult.hasNext());
           
    }

    @Test
    public void testSmithWatermanWrongType() throws Exception {

      

            final String aQuery = "prefix ss: <" + StringComparisonVocabulary.NAMESPACE + "> " +
                    "select ?result where { bind(ss:smithWaterman(7) as ?result) }";

            final TupleQueryResult aResult = aConn.select(aQuery).execute();
       
                // there should be a result because implicit in the query is the singleton set, so because the bind
                // should fail due to the value error, we expect a single empty binding
                assertTrue("Should have a result", aResult.hasNext());

                final BindingSet aBindingSet = aResult.next();

                assertTrue("Should have no bindings", aBindingSet.getBindingNames().isEmpty());

                assertFalse("Should have no more results", aResult.hasNext());
           
    }
}
