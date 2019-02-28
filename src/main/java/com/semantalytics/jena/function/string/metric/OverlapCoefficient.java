package com.semantalytics.jena.function.string.metric;

import org.apache.jena.sparql.function.FunctionBase;
import org.simmetrics.metrics.StringMetrics;

public final class OverlapCoefficient extends FunctionBase {

    protected OverlapCoefficient() {
        super(2, StringMetricVocabulary.overlapCoefficient.stringValue());
    }

    private OverlapCoefficient(final OverlapCoefficient overlapCoefficient) {
        super(overlapCoefficient);
    }

    @Override
    protected Value internalEvaluate(final Value... values) throws ExpressionEvaluationException {

        final String firstString = assertStringLiteral(values[0]).stringValue();
        final String secondString = assertStringLiteral(values[1]).stringValue();

        return literal(StringMetrics.overlapCoefficient().compare(firstString, secondString));
    }

    @Override
    public OverlapCoefficient copy() {
        return new OverlapCoefficient(this);
    }

    @Override
    public void accept(final ExpressionVisitor expressionVisitor) {
        expressionVisitor.visit(this);
    }

    @Override
    public String toString() {
        return StringMetricVocabulary.overlapCoefficient.name();
    }
}
