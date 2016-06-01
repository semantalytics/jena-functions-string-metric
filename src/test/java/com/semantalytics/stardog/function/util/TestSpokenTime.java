package com.semantalytics.stardog.function.util;

import com.complexible.common.protocols.server.Server;
import com.complexible.stardog.Stardog;
import com.complexible.stardog.api.Connection;
import com.complexible.stardog.api.ConnectionConfiguration;
import com.complexible.stardog.api.admin.AdminConnection;
import com.complexible.stardog.api.admin.AdminConnectionConfiguration;
import com.complexible.stardog.protocols.snarl.SNARLProtocolConstants;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openrdf.query.TupleQueryResult;


public class TestSpokenTime {

    private static Server SERVER = null;

    private static final String DB = "test";

    @BeforeClass
    public static void beforeClass() throws Exception {
        SERVER = Stardog.buildServer()
                .bind(SNARLProtocolConstants.EMBEDDED_ADDRESS)
                .start();

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
            SERVER.stop();
        }
    }

    @Test
    public void testBindTime() throws Exception {
        final Connection aConn = ConnectionConfiguration.to(DB)
                .credentials("admin", "admin")
                .connect();

        try {

            aConn.begin();

            final String aQuery = "prefix util: <http://semantalytics.com/2016/03/ns/stardog/udf/util/> " +
                    "select ?result where { bind(util:sayTime(\"since yesterday\" } ";

            final TupleQueryResult aResult = aConn.select(aQuery).execute();

            aResult.next().getValue("result").stringValue();
        }
        finally {
            aConn.close();
        }
    }

}