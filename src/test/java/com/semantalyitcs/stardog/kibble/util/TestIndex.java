package com.semantalyitcs.stardog.kibble.util;

import com.complexible.stardog.Stardog;
import com.complexible.stardog.api.Connection;
import com.complexible.stardog.api.ConnectionConfiguration;
import com.complexible.stardog.api.admin.AdminConnection;
import com.complexible.stardog.api.admin.AdminConnectionConfiguration;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openrdf.query.TupleQueryResult;


public class TestIndex {
    protected static Stardog SERVER = null;
    protected static final String DB = "test";

    @BeforeClass
    public static void beforeClass() throws Exception {
        SERVER = Stardog.builder().create();

        final AdminConnection aConn = AdminConnectionConfiguration.toEmbeddedServer()
                .credentials("admin", "admin")
                .connect();

        try {
            if (aConn.list().contains(DB)) {
                aConn.drop(DB);
            }

            aConn.createMemory(DB);
        }
        finally {
            aConn.close();
        }
    }

    @AfterClass
    public static void afterClass() {
        if (SERVER != null) {
            SERVER.shutdown();
        }
    }

    @Test
    public void testBindIndex() throws Exception {
        final Connection aConn = ConnectionConfiguration.to(DB)
                .credentials("admin", "admin")
                .connect();

        try {

            aConn.begin();

            final String aQuery = "prefix util: <http://semantalytics.com/2016/03/ns/stardog/udf/util/> " +
                    "select ?result where { bind(util:index(?v) as ?result) values ?v {1 2 3 4 5} } order by ?v";

            final TupleQueryResult aResult = aConn.select(aQuery).execute();

            try {

                //final String aValue = aResult.next().getValue("result").stringValue();
                while(aResult.hasNext()) {
                    System.out.println(aResult.next().getValue("result").stringValue());
                }
            }
            finally {
                aResult.close();
            }
        }
        finally {
            aConn.close();
        }
    }

}
