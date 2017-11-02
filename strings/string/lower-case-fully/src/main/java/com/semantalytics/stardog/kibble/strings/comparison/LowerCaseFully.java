package com.semantalytics.stardog.kibble.strings.comparison;

import com.complexible.stardog.plan.filter.ExpressionEvaluationException;
import com.complexible.stardog.plan.filter.ExpressionVisitor;
import com.complexible.stardog.plan.filter.functions.AbstractFunction;
import com.complexible.stardog.plan.filter.functions.UserDefinedFunction;
import com.semantalytics.stardog.kibble.string.StringVocabulary;
import org.openrdf.model.Value;

import static com.complexible.common.rdf.model.Values.literal;

public class LowerCaseFully extends AbstractFunction implements UserDefinedFunction {

        public LowerCaseFully() {
            super(1, StringVocabulary.ontology().lowerCaseFully.stringValue());
        }

        public LowerCaseFully(final LowerCaseFully lowerCaseFully) {
            super(lowerCaseFully);
        }

        @Override
        protected Value internalEvaluate(final Value... values) throws ExpressionEvaluationException {

            final String string = assertStringLiteral(values[0]).stringValue();

            return literal(string.toLowerCase());
        }

        @Override
        public LowerCaseFully copy() {
            return new LowerCaseFully(this);
        }

        @Override
        public void accept(final ExpressionVisitor expressionVisitor) {
            expressionVisitor.visit(this);
        }

        @Override
        public String toString() {
            return "lowerCaseFully";
        }
}
