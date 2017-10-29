package com.semantalytics.stardog.lab.function;

import com.complexible.stardog.plan.filter.ExpressionEvaluationException;
import com.complexible.stardog.plan.filter.ExpressionVisitor;
import com.complexible.stardog.plan.filter.functions.AbstractFunction;
import com.complexible.stardog.plan.filter.functions.UserDefinedFunction;
import org.openrdf.model.Value;

import static com.complexible.common.rdf.model.Values.literal;

public class LowercaseFully extends AbstractFunction implements UserDefinedFunction {

        public LowercaseFully() {
            super(1, "http://semantalytics.com/2016/03/ns/stardog/udf/util/lowercaseFully");
        }

        public LowercaseFully(final LowercaseFully lowercaseFully) {
            super(lowercaseFully);
        }

        @Override
        protected Value internalEvaluate(final Value... values) throws ExpressionEvaluationException {

            final String string = assertStringLiteral(values[0]).stringValue();

            return literal(string.toLowerCase());
        }

        @Override
        public LowercaseFully copy() {
            return new LowercaseFully(this);
        }

        @Override
        public void accept(final ExpressionVisitor expressionVisitor) {
            expressionVisitor.visit(this);
        }

        @Override
        public String toString() {
            return "lowercaseFully";
        }
}
