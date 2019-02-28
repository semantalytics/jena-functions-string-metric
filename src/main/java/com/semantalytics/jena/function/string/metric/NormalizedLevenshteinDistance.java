package com.semantalytics.jena.function.string.metric;

import org.apache.jena.sparql.expr.NodeValue;
import org.apache.jena.sparql.function.FunctionBase;

public final class NormalizedLevenshteinDistance extends FunctionBase {

    private static final info.debatty.java.stringsimilarity.NormalizedLevenshtein normalizedLevenshtein;

    static {
        normalizedLevenshtein = new info.debatty.java.stringsimilarity.NormalizedLevenshtein();
    }

    protected NormalizedLevenshteinDistance() {
        super(2, StringMetricVocabulary.normalizedLevenshteinDistance.stringValue());
    }

    private NormalizedLevenshteinDistance(final NormalizedLevenshteinDistance normalizedLevenshteinDistance) {
        super(normalizedLevenshteinDistance);
    }

    @Override
    public NodeValue internalEvaluate(final Value... values) throws ExpressionEvaluationException {

        final String firstString = assertStringLiteral(values[0]).stringValue();
        final String secondString = assertStringLiteral(values[1]).stringValue();

        return literal(normalizedLevenshtein.distance(firstString, secondString));
    }

    public Function copy() {
        return new NormalizedLevenshteinDistance(this);
    }

    public void accept(final ExpressionVisitor expressionVisitor) {
        expressionVisitor.visit(this);
    }

    @Override
    public String toString() {
        return StringMetricVocabulary.normalizedLevenshteinDistance.name();
    }
}
