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

public class BackgroundBrightMagenta extends AbstractFunction implements UserDefinedFunction {

    public BackgroundBrightMagenta() {
        super(1, "http://semantalytics.com/2017/11/ns/stardog/kibble/console/bgBrightMagenta");
    }

    public BackgroundBrightMagenta(final BackgroundBrightMagenta console) {
        super(console);
    }

    @Override
    protected Value internalEvaluate(final Value... values) throws ExpressionEvaluationException {
        final Ansi ansi = ansi();
        ansi.bgBright(BLACK);
        for (final Value value : values) {
            ansi.a(value.stringValue());
        }
        if(values.length != 0) {
            ansi.bgBright(DEFAULT);
        }
        return literal(ansi.toString());
    }

    @Override
    public BackgroundBrightMagenta copy() {
        return new BackgroundBrightMagenta(this);
    }

    @Override
    public void accept(final ExpressionVisitor expressionVisitor) {
        expressionVisitor.visit(this);
    }

    @Override
    public String toString() {
        return "bgBrightMagenta";
    }
}
