package com.semantalytics.stardog.kibble.strings.emoji;

import com.semantalytics.stardog.kibble.AbstractStardogTest;
import org.junit.*;
import org.openrdf.query.BindingSet;
import org.openrdf.query.TupleQueryResult;

import static org.junit.Assert.*;


public class EmojifyTest  extends AbstractStardogTest {

  

    @Test
    public void testEmojify() throws Exception {

     

            final String aQuery = "prefix emoji: <http://semantalytics.com/2017/11/ns/stardog/strings/emoji/>" +
                    "select ?result where { bind(emoji:emojify(\"A :cat:, :dog: and a :mouse: became friends<3. For :dog:'s birthday party, they all had :hamburger:s, :fries:s, :cookie:s and :cake:.\") as ?result) }";

            try (final TupleQueryResult aResult = connection.select(aQuery).execute()) {

                assertTrue("Should have a result", aResult.hasNext());

                final String aValue = aResult.next().getValue("result").stringValue();

                assertEquals("A \uD83D\uDC31, \uD83D\uDC36 and a \uD83D\uDC2D became friends❤️. For \uD83D\uDC36's birthday party, they all had \uD83C\uDF54s, \uD83C\uDF5Fs, \uD83C\uDF6As and \uD83C\uDF70.", aValue);

                assertFalse("Should have no more results", aResult.hasNext());
            }
    }

    @Test
    public void testIsEmojiTooFewArgs() throws Exception {

        final String aQuery = "prefix emoji: <http://semantalytics.com/2017/11/ns/stardog/strings/emoji/>" +
                "select ?result where { bind(emoji:isEmoji() as ?result) }";

        final TupleQueryResult aResult = connection.select(aQuery).execute();
    
            // there should be a result because implicit in the query is the singleton set, so because the bind
            // should fail due to the value error, we expect a single empty binding
            assertTrue("Should have a result", aResult.hasNext());

            final BindingSet aBindingSet = aResult.next();

            assertTrue("Should have no bindings", aBindingSet.getBindingNames().isEmpty());

            assertFalse("Should have no more results", aResult.hasNext());
   
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

    @Test
    public void testIsEmojiWrongTypeOfArgIri() throws Exception {

        final String aQuery = "prefix emoji: <http://semantalytics.com/2017/11/ns/stardog/strings/emoji/>" +
                "select ?result where { bind(emoji:isEmoji(<http://example.com>) as ?result) }";

        final TupleQueryResult aResult = connection.select(aQuery).execute();
    
            // there should be a result because implicit in the query is the singleton set, so because the bind
            // should fail due to the value error, we expect a single empty binding
            assertTrue("Should have a result", aResult.hasNext());

            final BindingSet aBindingSet = aResult.next();

            assertTrue("Should have no bindings", aBindingSet.getBindingNames().isEmpty());

            assertFalse("Should have no more results", aResult.hasNext());
      
    }

    @Test
    public void testIsEmojiWrongTypeOfArgInt() throws Exception {

        final String aQuery = "prefix emoji: <http://semantalytics.com/2017/11/ns/stardog/strings/emoji/>" +
                "select ?result where { bind(emoji:isEmoji(1) as ?result) }";

        final TupleQueryResult aResult = connection.select(aQuery).execute();
     
            // there should be a result because implicit in the query is the singleton set, so because the bind
            // should fail due to the value error, we expect a single empty binding
            assertTrue("Should have a result", aResult.hasNext());

            final BindingSet aBindingSet = aResult.next();

            assertTrue("Should have no bindings", aBindingSet.getBindingNames().isEmpty());

            assertFalse("Should have no more results", aResult.hasNext());
      
    }

}
