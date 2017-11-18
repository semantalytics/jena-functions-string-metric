package com.semantalytics.stardog.kibble.console;

import com.complexible.stardog.plan.filter.ExpressionEvaluationException;
import com.complexible.stardog.plan.filter.ExpressionVisitor;
import com.complexible.stardog.plan.filter.functions.AbstractFunction;
import com.complexible.stardog.plan.filter.functions.UserDefinedFunction;
import com.google.common.collect.Range;
import org.fusesource.jansi.Ansi;
import org.openrdf.model.Value;
import org.w3c.dom.Attr;

import static com.complexible.common.rdf.model.Values.literal;
import static org.fusesource.jansi.Ansi.Color;
import static org.fusesource.jansi.Ansi.ansi;

public class Underline extends AbstractFunction implements UserDefinedFunction {

    public Underline() {
        super(Range.all(), "http://semantalytics.com/2017/11/ns/stardog/kibble/console/underline");
    }

    public Underline(final Underline console) {
        super(console);
    }

    @Override
    protected Value internalEvaluate(final Value... values) throws ExpressionEvaluationException {
        final Ansi ansi = ansi();
        ansi.a(Ansi.Attribute.UNDERLINE);
        for (final Value value : values) {
            ansi.a(value.stringValue());
        }
        if(values.length != 0) {
            ansi.a(Ansi.Attribute.UNDERLINE_OFF);
        }
        return literal(ansi.toString());
    }

    @Override
    public Underline copy() {
        return new Underline(this);
    }

    @Override
    public void accept(final ExpressionVisitor expressionVisitor) {
        expressionVisitor.visit(this);
    }

    @Override
    public String toString() {
        return "underline";
    }
}
