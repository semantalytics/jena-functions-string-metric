package com.semantalytics.jena.function.string.metric;


import org.apache.jena.sparql.expr.NodeValue;
import org.apache.jena.sparql.function.FunctionBase;

import java.util.List;

public final class LongestCommonSubstring extends FunctionBase {

    static {
        longestCommonSubstring = new org.simmetrics.metrics.LongestCommonSubstring();
    }

    private static final org.simmetrics.metrics.LongestCommonSubstring longestCommonSubstring;

    protected LongestCommonSubstring() {
        super(2, StringMetricVocabulary.longestCommonSubstring.stringValue());
    }

    private LongestCommonSubstring(final LongestCommonSubstring longestCommonSubsequence) {
        super(longestCommonSubsequence);
    }

    @Override
    public NodeValue exec(final List<NodeValue> args) {

        assertStringLiteral(values[0]);
        assertStringLiteral(values[1]);

        return literal(longestCommonSubstring.compare(values[0].toString(), values[1].toString()));
    }
}

