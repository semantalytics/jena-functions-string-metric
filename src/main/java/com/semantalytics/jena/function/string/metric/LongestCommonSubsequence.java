package com.semantalytics.jena.function.string.metric;

import org.apache.jena.sparql.expr.NodeValue;
import org.apache.jena.sparql.function.FunctionBase;

import java.util.List;

public final class LongestCommonSubsequence extends FunctionBase {

    static {
        longestCommonSubsequence = new org.simmetrics.metrics.LongestCommonSubsequence();
    }

    private static final org.simmetrics.metrics.LongestCommonSubsequence longestCommonSubsequence;

    protected LongestCommonSubsequence() {
        super(2, StringMetricVocabulary.longestCommonSubsequence.stringValue());
    }

    @Override
    public NodeValue exec(final List<NodeValue> values) {

        final String firstString = assertStringLiteral(values[0]).stringValue();
        final String secondString = assertStringLiteral(values[1]).stringValue();

        return literal(longestCommonSubsequence.compare(firstString, secondString));
    }
}

