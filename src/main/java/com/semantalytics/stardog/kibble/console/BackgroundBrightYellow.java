package com.semantalytics.stardog.kibble.console;

import com.complexible.stardog.plan.filter.ExpressionEvaluationException;
import com.complexible.stardog.plan.filter.ExpressionVisitor;
import com.complexible.stardog.plan.filter.functions.AbstractFunction;
import com.complexible.stardog.plan.filter.functions.UserDefinedFunction;
import org.fusesource.jansi.Ansi;
import org.openrdf.model.Value;

import static com.complexible.common.rdf.model.Values.literal;
import static org.fusesource.jansi.Ansi.Color;
import static org.fusesource.jansi.Ansi.Color.*;
import static org.fusesource.jansi.Ansi.ansi;

public class BackgroundBrightYellow extends AbstractFunction implements UserDefinedFunction {

    public BackgroundBrightYellow() {
        super(1, ConsoleVocabulary.backgroundBrightYellow.stringValue());
    }

    public BackgroundBrightYellow(final BackgroundBrightYellow console) {
        super(console);
    }

    @Override
    protected Value internalEvaluate(final Value... values) throws ExpressionEvaluationException {
        final Ansi ansi = ansi();
        ansi.bgBright(YELLOW);
        for (final Value value : values) {
            ansi.a(value.stringValue());
        }
        if(values.length != 0) {
            ansi.bgBright(DEFAULT);
        }
        return literal(ansi.toString());
    }

    @Override
    public BackgroundBrightYellow copy() {
        return new BackgroundBrightYellow(this);
    }

    @Override
    public void accept(final ExpressionVisitor expressionVisitor) {
        expressionVisitor.visit(this);
    }

    @Override
    public String toString() {
        return ConsoleVocabulary.backgroundBrightYellow.name();
    }
}
