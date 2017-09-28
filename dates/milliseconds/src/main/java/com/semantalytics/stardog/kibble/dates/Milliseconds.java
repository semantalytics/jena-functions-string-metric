package com.semantalytics.stardog.lab.function;

import com.complexible.stardog.plan.filter.ExpressionEvaluationException;
import com.complexible.stardog.plan.filter.ExpressionVisitor;
import com.complexible.stardog.plan.filter.functions.AbstractFunction;
import com.complexible.stardog.plan.filter.functions.UserDefinedFunction;
import org.openrdf.model.Value;

import static com.complexible.common.rdf.model.Values.literal;

public class Millliseconds extends AbstractFunction implements UserDefinedFunction {

        public Millliseconds() {
            super(1, "http://semantalytics.com/2016/03/ns/stardog/udf/util/millliseconds");
        }

        public Millliseconds(final Millliseconds millliseconds) {
            super(millliseconds);
        }

        @Override
        protected Value internalEvaluate(final Value... values) throws ExpressionEvaluationException {

            final String string = assertStringLiteral(values[0]).stringValue();

            return literal(string.toLowerCase());
        }

        @Override
        public Millliseconds copy() {
            return new Millliseconds(this);
        }

        @Override
        public void accept(final ExpressionVisitor expressionVisitor) {
            expressionVisitor.visit(this);
        }

        @Override
        public String toString() {
            return "millliseconds";
        }
}
