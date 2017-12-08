package com.semantalytics.stardog.kibble.console;

import com.complexible.stardog.plan.filter.ExpressionEvaluationException;
import com.complexible.stardog.plan.filter.ExpressionVisitor;
import com.complexible.stardog.plan.filter.functions.AbstractFunction;
import com.complexible.stardog.plan.filter.functions.UserDefinedFunction;
import com.google.common.collect.Range;
import org.fusesource.jansi.Ansi;
import org.openrdf.model.Value;

import static com.complexible.common.rdf.model.Values.literal;
import static org.fusesource.jansi.Ansi.*;

public class Console extends AbstractFunction implements UserDefinedFunction {

        public Console() {
            super(Range.all(), "http://semantalytics.com/2017/11/ns/stardog/kibble/console");
        }

        public Console(final Console console) {
            super(console);
        }

        @Override
        protected Value internalEvaluate(final Value... values) throws ExpressionEvaluationException {
            final Ansi ansi = ansi();
            for (final Value value : values) {
                ansi.a(value.stringValue());
            }
            return literal(ansi.reset().toString());
        }

        @Override
        public Console copy() {
            return new Console(this);
        }

        @Override
        public void accept(final ExpressionVisitor expressionVisitor) {
            expressionVisitor.visit(this);
        }

        @Override
        public String toString() {
            return "console";
        }
}
