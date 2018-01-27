package com.semantalytics.stardog.kibble.strings.comparison;

import com.semantalytics.stardog.kibble.AbstractStardogTest;
import org.junit.*;
import org.openrdf.query.BindingSet;
import org.openrdf.query.TupleQueryResult;

import static org.junit.Assert.*;

public class TestWeightedLevenshteinDistance  extends AbstractStardogTest {

   
    @Test
    public void testLevenshtein() throws Exception {


            final String aQuery = "prefix ss: <" + StringComparisonVocabulary.NAMESPACE + "> " +
                    "select ?dist where { bind(ss:weightedLevenshtein(\"String1\", \"Srring2\", \"t\", \"r\", 0.5) as ?dist) }";

        try(final TupleQueryResult aResult = connection.select(aQuery).execute()) {

                assertTrue("Should have a result", aResult.hasNext());

                final String aValue = aResult.next().getValue("dist").stringValue();

                assertEquals(1.5, Double.parseDouble(aValue), 0.0);

                assertFalse("Should have no more results", aResult.hasNext());
        }
    }

    @Test
    public void testLevenstheinTooManyArgs() throws Exception {


    
            final String aQuery = "prefix ss: <" + StringComparisonVocabulary.NAMESPACE + "> " +
                    "select ?str where { bind(ss:weightedLevenshtein(\"one\", \"two\", \"three\") as ?str) }";

            final TupleQueryResult aResult = connection.select(aQuery).execute();
       
                // there should be a result because implicit in the query is the singleton set, so because the bind
                // should fail due to the value error, we expect a single empty binding
                assertTrue("Should have a result", aResult.hasNext());

                final BindingSet aBindingSet = aResult.next();

                assertTrue("Should have no bindings", aBindingSet.getBindingNames().isEmpty());
                assertFalse("Should have no more results", aResult.hasNext());
            
    }

    @Test
    public void testLevenshteinWrongType() throws Exception {

     

            final String aQuery = "prefix ss: <" + StringComparisonVocabulary.NAMESPACE + "> " +
                    "select ?str where { bind(ss:weightedLevenshtein(7) as ?str) }";

            final TupleQueryResult aResult = connection.select(aQuery).execute();
       
                // there should be a result because implicit in the query is the singleton set, so because the bind
                // should fail due to the value error, we expect a single empty binding
                assertTrue("Should have a result", aResult.hasNext());

                final BindingSet aBindingSet = aResult.next();

                assertTrue("Should have no bindings", aBindingSet.getBindingNames().isEmpty());

                assertFalse("Should have no more results", aResult.hasNext());
          
    }
}
