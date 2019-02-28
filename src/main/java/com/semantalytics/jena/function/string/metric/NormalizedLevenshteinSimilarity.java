package com.semantalytics.jena.function.string.metric;


import org.apache.jena.sparql.function.FunctionBase;

public final class NormalizedLevenshteinSimilarity extends FunctionBase {

    private static final info.debatty.java.stringsimilarity.NormalizedLevenshtein normalizedLevenshtein;

    static {
        normalizedLevenshtein = new info.debatty.java.stringsimilarity.NormalizedLevenshtein();
    }

    protected NormalizedLevenshteinSimilarity() {
        super(2, StringMetricVocabulary.normalizedLevenshteinSimarity.stringValue());
    }

    private NormalizedLevenshteinSimilarity(final NormalizedLevenshteinSimilarity normalizedLevenshteinSimilarity) {
        super(normalizedLevenshteinSimilarity);
    }

    @Override
    protected Value internalEvaluate(final Value... values) throws ExpressionEvaluationException {

        final String firstString = assertStringLiteral(values[0]).stringValue();
        final String secondString = assertStringLiteral(values[1]).stringValue();

        return literal(normalizedLevenshtein.similarity(firstString, secondString));
    }

    public NormalizedLevenshteinSimilarity copy() {
        return new NormalizedLevenshteinSimilarity(this);
    }

    public void accept(final ExpressionVisitor expressionVisitor) {
        expressionVisitor.visit(this);
    }

    @Override
    public String toString() {
        return StringMetricVocabulary.normalizedLevenshteinSimarity.name();
    }
}
