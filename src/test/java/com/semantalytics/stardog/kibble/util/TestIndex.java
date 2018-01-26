package com.semantalytics.stardog.kibble.util;

import com.complexible.stardog.Stardog;
import com.complexible.stardog.api.Connection;
import com.complexible.stardog.api.ConnectionConfiguration;
import com.complexible.stardog.api.admin.AdminConnection;
import com.complexible.stardog.api.admin.AdminConnectionConfiguration;
import com.semantalytics.stardog.kibble.util.UtilVocabulary;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openrdf.query.TupleQueryResult;

public class TestIndex  extends AbstractStardogTest {


    @Test
    public void testBindIndex() throws Exception {
    
     

            final String aQuery = "prefix util: + <" + UtilVocabulary.NAMESPACE + "> " +
                    "select ?result where { bind(util:index(?v) as ?result) values ?v {1 2 3 4 5} } order by ?v";

            final TupleQueryResult aResult = aConn.select(aQuery).execute();

     

                //final String aValue = aResult.next().getValue("result").stringValue();
                while(aResult.hasNext()) {
                    System.out.println(aResult.next().getValue("result").stringValue());
                }
          
    }

}
