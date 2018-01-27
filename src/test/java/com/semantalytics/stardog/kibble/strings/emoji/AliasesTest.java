package com.semantalytics.stardog.kibble.strings.emoji;

import com.semantalytics.stardog.kibble.AbstractStardogTest;
import org.junit.*;
import org.openrdf.query.BindingSet;
import org.openrdf.query.TupleQueryResult;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;


public class AliasesTest  extends AbstractStardogTest {


    @Test
    public void testAliases() throws Exception {

      

            final String aQuery = "prefix emoji: <http://semantalytics.com/2017/11/ns/stardog/strings/emoji/>" +
                    "select ?result where { bind(emoji:aliases(\"clubs\") as ?result) }";

            try (final TupleQueryResult aResult = connection.select(aQuery).execute()) {

                assertTrue("Should have a result", aResult.hasNext());

                final String aValue = aResult.next().getValue("result").stringValue();
                System.out.println("'" + aValue + "'");

                assertEquals("dog", aValue);

                assertFalse("Should have no more results", aResult.hasNext());
            }
    }

    @Test
    public void testIsEmojiTooManyArgs() throws Exception {

        final String aQuery = "prefix emoji: <http://semantalytics.com/2017/11/ns/stardog/strings/emoji/>" +
                "select ?result where { bind(emoji:isEmoji(\"star\", \"dog\") as ?result) }";

        final TupleQueryResult aResult = connection.select(aQuery).execute();
     
            // there should be a result because implicit in the query is the singleton set, so because the bind
            // should fail due to the value error, we expect a single empty binding
            assertTrue("Should have a result", aResult.hasNext());

            final BindingSet aBindingSet = aResult.next();

            assertTrue("Should have no bindings", aBindingSet.getBindingNames().isEmpty());

            assertFalse("Should have no more results", aResult.hasNext());
      
    }

}
