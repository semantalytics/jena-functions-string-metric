package com.semantalytics.stardog.kibble.console;

import com.complexible.stardog.plan.filter.ExpressionEvaluationException;
import com.complexible.stardog.plan.filter.ExpressionVisitor;
import com.complexible.stardog.plan.filter.functions.AbstractFunction;
import com.complexible.stardog.plan.filter.functions.UserDefinedFunction;
import org.openrdf.model.Value;

import static com.complexible.common.rdf.model.Values.literal;
import static org.fusesource.jansi.Ansi.Color;
import static org.fusesource.jansi.Ansi.ansi;

public class IntensityBoldOff extends AbstractFunction implements UserDefinedFunction {

    public IntensityBoldOff() {
        super(1, ConsoleVocabulary.intensityBoldOff.stringValue());
    }

    public IntensityBoldOff(final IntensityBoldOff console) {
        super(console);
    }

    @Override
    protected Value internalEvaluate(final Value... values) throws ExpressionEvaluationException {
        final String color = assertStringLiteral(values[0]).stringValue();
        return literal(ansi().bg(Color.valueOf(color)).toString());
    }

    @Override
    public IntensityBoldOff copy() {
        return new IntensityBoldOff(this);
    }

    @Override
    public void accept(final ExpressionVisitor expressionVisitor) {
        expressionVisitor.visit(this);
    }

    @Override
    public String toString() {
        return ConsoleVocabulary.intensityBoldOff.name();
    }
}
