package com.semantalytics.jena.function.string.metric;

import org.apache.jena.sparql.expr.NodeValue;
import org.apache.jena.sparql.function.FunctionBase2;

import static org.apache.jena.sparql.expr.NodeValue.*;

public final class NormalizedLevenshteinSimilarity extends FunctionBase2 {

    private static final info.debatty.java.stringsimilarity.NormalizedLevenshtein normalizedLevenshtein;

    static {
        normalizedLevenshtein = new info.debatty.java.stringsimilarity.NormalizedLevenshtein();
    }

    @Override
    public NodeValue exec(final NodeValue arg0, final NodeValue arg1) {

        final String firstString = arg0.getString();
        final String secondString = arg1.getString();

        return makeDouble(normalizedLevenshtein.similarity(firstString, secondString));
    }
}
