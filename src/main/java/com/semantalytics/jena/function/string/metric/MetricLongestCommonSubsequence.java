package com.semantalytics.jena.function.string.metric;

import info.debatty.java.stringsimilarity.MetricLCS;
import org.apache.jena.sparql.expr.NodeValue;
import org.apache.jena.sparql.function.FunctionBase;

import java.util.List;

public class MetricLongestCommonSubsequence extends FunctionBase {

    private static final MetricLCS metricLCS = new MetricLCS();

    protected MetricLongestCommonSubsequence() {
        super(2, StringMetricVocabulary.metricLongestCommonSubsequence.stringValue());
    }

    private MetricLongestCommonSubsequence(final MetricLongestCommonSubsequence metricLongestCommonSubsequence) {
        super(metricLongestCommonSubsequence);
    }

    @Override
    public NodeValue exec(final List<NodeValue> args) {

        assertStringLiteral(values[0]);
        assertStringLiteral(values[1]);

        return literal(metricLCS.distance(values[0].stringValue(), values[1].stringValue()));
    }
}

