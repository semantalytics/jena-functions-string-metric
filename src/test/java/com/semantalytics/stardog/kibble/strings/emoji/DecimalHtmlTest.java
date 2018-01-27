package com.semantalytics.stardog.kibble.strings.emoji;

import com.semantalytics.stardog.kibble.AbstractStardogTest;
import org.junit.*;
import org.openrdf.query.TupleQueryResult;

import static org.junit.Assert.*;


public class DecimalHtmlTest  extends AbstractStardogTest {

  
    @Test
    public void testEmoji() throws Exception {

        

            final String aQuery = "prefix emoji: <http://semantalytics.com/2017/11/ns/stardog/strings/emoji/>" +
                    "select ?result where { bind(emoji:emoji(\"dog\") as ?result) }";

            try (final TupleQueryResult aResult = connection.select(aQuery).execute()) {

                assertTrue("Should have a result", aResult.hasNext());

                final String aValue = aResult.next().getValue("result").stringValue();

                System.out.println("'" + aValue + "'");
                assertEquals("dog", aValue);

                assertFalse("Should have no more results", aResult.hasNext());
            }
    }

}
