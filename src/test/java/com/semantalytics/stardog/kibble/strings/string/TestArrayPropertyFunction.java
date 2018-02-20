/*
 * Copyright (c) 2010-2016 Complexible, Inc <http://complexible.com>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */


package com.semantalytics.stardog.kibble.strings.string;

import java.lang.reflect.Array;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.complexible.common.openrdf.query.BindingSets;
import com.complexible.common.rdf.model.Namespaces;
import com.complexible.common.rdf.model.StardogValueFactory;
import com.complexible.stardog.index.statistics.Accuracy;
import com.complexible.stardog.index.statistics.Cardinality;
import com.complexible.stardog.plan.PlanNode;
import com.complexible.stardog.plan.PlanNodes;
import com.complexible.stardog.plan.eval.ExecutionException;
import com.complexible.stardog.plan.parser.QueryParserImpl;
import com.google.common.collect.Lists;
import com.semantalytics.stardog.kibble.AbstractStardogTest;
import info.aduna.iteration.Iterations;
import org.junit.Test;
import org.openrdf.model.Value;
import org.openrdf.query.BindingSet;
import org.openrdf.query.TupleQueryResult;

import static com.complexible.common.rdf.model.Values.literal;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

public class TestArrayPropertyFunction extends AbstractStardogTest {

    @Test(expected = ExecutionException.class)
    public void tooManyResultsThrowsError() {

        final String aQueryStr = StringVocabulary.sparqlPrefix("string") +
                " select * where { (?too ?many ?args) string:array (\"foo\") }";

        final TupleQueryResult aResult = connection.select(aQueryStr).execute();
        try {
            fail("Should not have successfully executed");
        } finally {
            aResult.close();
        }
    }


    @Test(expected = ExecutionException.class)
    public void resultTermsWhichAreNotVariablesShouldBeAnError() {
        final String aQueryStr = StringVocabulary.sparqlPrefix("string") +
                " select * where { (\"no literals allowed\") string:array (\"foo\") }";

        final TupleQueryResult aResult = connection.select(aQueryStr).execute();
        try {
            fail("Should not have successfully executed");
        } finally {
            aResult.close();
        }
    }

    @Test(expected = ExecutionException.class)
    public void tooManyInputsThrowsError() {
        final String aQueryStr = StringVocabulary.sparqlPrefix("string") +
                " select * where { ?result string:array (\"foo\" 5) }";


        final TupleQueryResult aResult = connection.select(aQueryStr).execute();

        try {
            fail("Should not have successfully executed");
        } finally {
            aResult.close();
        }
    }

    @Test(expected = ExecutionException.class)
    public void countArgCannotBeANonnumericLiteral() {
        final String aQueryStr = StringVocabulary.sparqlPrefix("string") +
                " select * where { ?result string:array (5) }";


        final TupleQueryResult aResult = connection.select(aQueryStr).execute();
        try {
            fail("Should not have successfully executed");
        } finally {
            aResult.close();
        }
    }

    @Test(expected = ExecutionException.class)
    public void countArgCannotBeAIRI() {
        final String aQueryStr = StringVocabulary.sparqlPrefix("string") +
                " select * where { ?result string:array (<http://example.com>) }";


        final TupleQueryResult aResult = connection.select(aQueryStr).execute();
        try {
            fail("Should not have successfully executed");
        } finally {
            aResult.close();
        }
    }

    @Test(expected = ExecutionException.class)
    public void countArgCannotBeABNode() {
        final String aQueryStr = StringVocabulary.sparqlPrefix("string") +
                " select * where { ?result string:array (_:bnode) }";

        final TupleQueryResult aResult = connection.select(aQueryStr).execute();
        try {
            fail("Should not have successfully executed");
        } finally {
            aResult.close();
        }
    }

    @Test(expected = ExecutionException.class)
    public void missingCountArgIsAnError() {
        final String aQueryStr = StringVocabulary.sparqlPrefix("string") +
                " select * where { ?result string:array (\"foo\") }";


        final TupleQueryResult aResult = connection.select(aQueryStr).execute();
        try {
            fail("Should not have successfully executed");
        } finally {
            aResult.close();
        }
    }

    @Test
    public void varInputWithNoResultsShouldProduceZeroResults() {
        final String aQueryStr = StringVocabulary.sparqlPrefix("string") +
                " select * where { ?result string:array (?input) }";


        final TupleQueryResult aResult = connection.select(aQueryStr).execute();
        try {
            assertFalse(aResult.hasNext());
        } finally {
            aResult.close();
        }
    }

    @Test
    public void simpleRepeat() {
        final String aQueryStr = StringVocabulary.sparqlPrefix("string") +
                " select * where { ?result string:array (\"foo\u001fbar\") }";

        final TupleQueryResult aResult = connection.select(aQueryStr).execute();
        try {
            final List<Value> aExpected = Lists.newArrayList(literal("foo"), literal("foo"), literal("foo"), literal("foo"), literal("foo"));
            final List<Value> aResults = Iterations.stream(aResult).map(BindingSets.select("result")).collect(Collectors.toList());

            assertEquals(aExpected, aResults);
        } finally {
            aResult.close();
        }
    }

    @Test
    public void repeatWithCounter() {
        final String aQueryStr = StringVocabulary.sparqlPrefix("string") +
                " select * where { (?result ?idx) string:array (\"foo\u001fbar\") }";

        final TupleQueryResult aResult = connection.select(aQueryStr).execute();
        try {
            int aNum = 0;

            while (aResult.hasNext()) {
                BindingSet aBindingSet = aResult.next();

                assertEquals(literal("foo"), aBindingSet.getValue("result"));
                assertEquals(literal(aNum++, StardogValueFactory.Datatype.INTEGER), aBindingSet.getValue("idx"));
            }

            assertEquals(5, aNum);
        } finally {
            aResult.close();
        }
    }

    @Test
    public void repeatWithVarInput() {
        final String aQueryStr = StringVocabulary.sparqlPrefix("string") +
                " select * where { (?result ?idx) string:array (?in 5) . values ?in { \"foo\u001fbar\u001fbaz\"} }";


        final TupleQueryResult aResult = connection.select(aQueryStr).execute();
        try {
            int aCount = 0;
            int aNum = 0;

            String aValue = "foo";

            while (aResult.hasNext()) {
                BindingSet aBindingSet = aResult.next();

                assertEquals(literal(aValue), aBindingSet.getValue("result"));
                assertEquals(literal(aCount++, StardogValueFactory.Datatype.INTEGER), aBindingSet.getValue("idx"));

                if (aCount == 5) {
                    aValue = aValue.equals("foo") ? "bar" : "baz";
                    aCount = 0;
                }

                aNum++;
            }

            assertEquals(15, aNum);
        } finally {
            aResult.close();
        }
    }

    /*
    @Test
    public void costAndCardinalityShouldBeCorrect() throws Exception {
        final String aQueryStr = "select * where { (?result ?idx) <tag:stardog:api:repeat> (\"foo\" 5) }";

        Optional<PlanNode> aResult = PlanNodes.find(new QueryParserImpl().parseQuery(aQueryStr, Namespaces.STARDOG).getNode(),
                                                    PlanNodes.is(ArrayPropertyFunction.ArrayPlanNode.class));

        assertTrue(aResult.isPresent());

        ArrayPropertyFunction.ArrayPlanNode aNode = (ArrayPropertyFunction.ArrayPlanNode) aResult.get();

        new ArrayPropertyFunction().estimate(aNode);

        assertEquals(5d, aNode.getCost(), .00001);

        assertEquals(Accuracy.ACCURATE, aNode.getCardinality().accuracy());
        assertEquals(5d, aNode.getCardinality().value(), .00001);
    }

    @Test
    public void costAndCardinalityShouldBeCorrectWithArg() throws Exception {
        final String aQueryStr = "select * where { (?result ?idx) <tag:stardog:api:repeat> (?in 5) . values ?in { \"foo\" \"bar\" \"baz\"} }";

        Optional<PlanNode> aResult = PlanNodes.find(new QueryParserImpl().parseQuery(aQueryStr, Namespaces.STARDOG).getNode(),
                                                  PlanNodes.is(ArrayPropertyFunction.ArrayPlanNode.class));

        assertTrue(aResult.isPresent());

        ArrayPropertyFunction.ArrayPlanNode aNode = (ArrayPropertyFunction.ArrayPlanNode) aResult.get();

        aNode.getArg().setCardinality(Cardinality.of(3, Accuracy.ACCURATE));
        aNode.getArg().setCost(3);

        new ArrayPropertyFunction().estimate(aNode);

        assertEquals(18d, aNode.getCost(), .00001);

        assertEquals(Accuracy.ACCURATE, aNode.getCardinality().accuracy());
        assertEquals(15d, aNode.getCardinality().value(), .00001);
    }

*/
    @Test
    public void shouldRenderACustomExplanation() {
        final String aQueryStr = "select * where { (?result ?idx) <tag:stardog:api:repeat> (\"foo\" 5) }";

        assertTrue(connection.select(aQueryStr).explain().contains("Repeat("));
    }
}