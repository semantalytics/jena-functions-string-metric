package com.semantalytics.jena.function.string.metric;

import com.google.common.collect.Range;
import org.apache.jena.sparql.expr.NodeValue;
import org.apache.jena.sparql.function.FunctionBase;

import java.util.List;

public final class JaroWinklerSimilarity extends FunctionBase {

    protected JaroWinklerSimilarity() {
        super(Range.closed(2, 5), StringMetricVocabulary.jaroWinklerSimilarity.stringValue());
    }

    private JaroWinklerSimilarity(final JaroWinklerSimilarity jaroWinklerSimilarity) {
        super(jaroWinklerSimilarity);
    }

    @Override
    public NodeValue exec(final List<NodeValue> values) {

        final String firstString = assertStringLiteral(values[0]).stringValue();
        final String secondString = assertStringLiteral(values[1]).stringValue();

        float boostThreshold = 0.7f;
        float prefixScale = 0.1f;
        int maxPrefixLength = 4;

        if(values.length >= 3) {
            boostThreshold = assertNumericLiteral(values[2]).floatValue();
        }
        if(values.length >= 4) {
            prefixScale = assertNumericLiteral(values[3]).floatValue();
        }
        if(values.length == 5) {
            maxPrefixLength = assertNumericLiteral(values[4]).intValue();
        }

        final org.simmetrics.metrics.JaroWinkler jaroWinkler;
        jaroWinkler = new org.simmetrics.metrics.JaroWinkler(boostThreshold, prefixScale, maxPrefixLength);

        return literal(jaroWinkler.compare(firstString, secondString));
    }
}
