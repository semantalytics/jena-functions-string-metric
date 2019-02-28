package com.semantalytics.jena.function.string.metric;


import org.apache.jena.sparql.expr.NodeValue;
import org.apache.jena.sparql.function.FunctionBase;

import java.util.List;

public final class LevenshteinDistance extends FunctionBase {

    private static final info.debatty.java.stringsimilarity.Levenshtein levenshtein;

    static {
        levenshtein = new info.debatty.java.stringsimilarity.Levenshtein();
    }

    protected LevenshteinDistance() {
        super(2, StringMetricVocabulary.levenshteinDistance.stringValue());
    }

    @Override
    public NodeValue exec(final List<NodeValue> values) {

        assertStringLiteral(values[0]);
        assertStringLiteral(values[1]);

        return literal(levenshtein.distance(values[0].stringValue(), values[1].stringValue()));
    }
}
