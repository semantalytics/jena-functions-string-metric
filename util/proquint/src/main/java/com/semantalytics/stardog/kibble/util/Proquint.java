package com.semantalytics.stardog.kibble.util;

import com.complexible.common.rdf.model.Values;
import com.complexible.stardog.plan.filter.ExpressionEvaluationException;
import com.complexible.stardog.plan.filter.ExpressionVisitor;
import com.complexible.stardog.plan.filter.functions.AbstractFunction;
import com.complexible.stardog.plan.filter.functions.string.StringFunction;
import org.openrdf.model.Value;

public final class Proquint extends AbstractFunction implements StringFunction {

    protected Proquint() {
        super(2, UtilVocabulary.proquint.toString());
    }

    private Proquint(final Proquint proquint) {
        super(proquint);
    }

    @Override
    protected Value internalEvaluate(final Value... values) throws ExpressionEvaluationException {

        final long source = assertNumericLiteral(values[0]).longValue();

        return Values.literal(com.github.dsw.proquint.Proquint.encode(source));
    }

    @Override
    public Proquint copy() {
        return new Proquint(this);
    }

    @Override
    public void accept(final ExpressionVisitor expressionVisitor) {
        expressionVisitor.visit(this);
    }

    @Override
    public String toString() {
        return UtilVocabulary.proquint.name();
    }
}
