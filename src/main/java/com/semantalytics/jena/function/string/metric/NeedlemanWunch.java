package com.semantalytics.jena.function.string.metric;

import com.google.common.collect.Range;
import org.apache.jena.sparql.expr.NodeValue;
import org.apache.jena.sparql.function.FunctionBase;
import org.simmetrics.metrics.functions.MatchMismatch;

import java.util.List;

public final class NeedlemanWunch extends FunctionBase {

    private static org.simmetrics.metrics.NeedlemanWunch needlemanWunch;

    protected NeedlemanWunch() {
        super(Range.closed(2, 5), StringMetricVocabulary.needlemanWunch.stringValue());
    }

    private NeedlemanWunch(final NeedlemanWunch needlemanWunch) {
        super(needlemanWunch);
    }

    @Override
    public NodeValue exec(final List<NodeValue> values) {

        final String firstString = assertStringLiteral(values[0]).stringValue();
        final String secondString = assertStringLiteral(values[1]).stringValue();

        if(values.length == 5) {
            for(final Expression expression : getArgs()) {
                // FIXME this should only check args 2-4 not all args
                if(!(expression instanceof Constant)) {
                    throw new ExpressionEvaluationException("Parameters 2 through 5 must be constant expressions");
                }
            }
        }

        return Values.literal(getNeedlemanWunchFunction(values).compare(firstString, secondString));
    }


    private org.simmetrics.metrics.NeedlemanWunch getNeedlemanWunchFunction(final Value... values) throws ExpressionEvaluationException {
        if (needlemanWunch == null) {
            if (values.length == 5) {
                
                final float gapValue = assertNumericLiteral(values[2]).floatValue();
                final float subPenaltyA = assertNumericLiteral(values[3]).floatValue();
                final float subPenaltyB = assertNumericLiteral(values[4]).floatValue();
                
                needlemanWunch = new org.simmetrics.metrics.NeedlemanWunch(gapValue, new MatchMismatch(subPenaltyA, subPenaltyB));
            } else {
                needlemanWunch = new org.simmetrics.metrics.NeedlemanWunch();
            }
        }
        return needlemanWunch;
    }
}
