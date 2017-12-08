package com.semantalytics.stardog.kibble.console;

import com.complexible.stardog.plan.filter.ExpressionEvaluationException;
import com.complexible.stardog.plan.filter.ExpressionVisitor;
import com.complexible.stardog.plan.filter.functions.AbstractFunction;
import com.complexible.stardog.plan.filter.functions.UserDefinedFunction;
import org.openrdf.model.Value;

import static com.complexible.common.rdf.model.Values.literal;
import static org.fusesource.jansi.Ansi.Color;
import static org.fusesource.jansi.Ansi.ansi;

public class IntensityBoldOn extends AbstractFunction implements UserDefinedFunction {

    public IntensityBoldOn() {
        super(1, "http://semantalytics.com/2017/11/ns/stardog/kibble/console/fg");
    }

    public IntensityBoldOn(final IntensityBoldOn console) {
        super(console);
    }

    @Override
    protected Value internalEvaluate(final Value... values) throws ExpressionEvaluationException {
        final String color = assertStringLiteral(values[0]).stringValue();
        return literal(ansi().bg(Color.valueOf(color)).toString());
    }

    @Override
    public IntensityBoldOn copy() {
        return new IntensityBoldOn(this);
    }

    @Override
    public void accept(final ExpressionVisitor expressionVisitor) {
        expressionVisitor.visit(this);
    }

    @Override
    public String toString() {
        return "background";
    }
}
