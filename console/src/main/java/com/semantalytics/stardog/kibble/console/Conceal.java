package com.semantalytics.stardog.kibble.console;

import com.complexible.stardog.plan.filter.ExpressionEvaluationException;
import com.complexible.stardog.plan.filter.ExpressionVisitor;
import com.complexible.stardog.plan.filter.functions.AbstractFunction;
import com.complexible.stardog.plan.filter.functions.UserDefinedFunction;
import com.google.common.collect.Range;
import org.fusesource.jansi.Ansi;
import org.openrdf.model.Value;

import static com.complexible.common.rdf.model.Values.literal;
import static org.fusesource.jansi.Ansi.ansi;

public class Conceal extends AbstractFunction implements UserDefinedFunction {

    public Conceal() {
        super(Range.all(), "http://semantalytics.com/2017/11/ns/stardog/kibble/console/conceal");
    }

    public Conceal(final Conceal conceal) {
        super(conceal);
    }

    @Override
    protected Value internalEvaluate(final Value... values) throws ExpressionEvaluationException {
        final Ansi ansi = ansi();
        ansi.a(Ansi.Attribute.CONCEAL_ON);
        for (final Value value : values) {
            ansi.a(value.stringValue());
        }
        if (values.length != 0) {
            ansi.a(Ansi.Attribute.CONCEAL_OFF);
        }
        return literal(ansi.toString());
    }

    @Override
    public Conceal copy() {
        return new Conceal(this);
    }

    @Override
    public void accept(final ExpressionVisitor expressionVisitor) {
        expressionVisitor.visit(this);
    }

    @Override
    public String toString() {
        return "conceal";
    }
}
