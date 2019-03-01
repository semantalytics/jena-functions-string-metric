package com.semantalytics.jena.function.string.metric;

import com.google.common.collect.Range;
import org.apache.jena.sparql.expr.NodeValue;
import org.apache.jena.sparql.function.FunctionBase;
import org.simmetrics.metrics.functions.MatchMismatch;

import java.util.List;

import static org.apache.jena.sparql.expr.NodeValue.*;

public final class NeedlemanWunch extends FunctionBase {

    private static org.simmetrics.metrics.NeedlemanWunch needlemanWunch;

    protected NeedlemanWunch() {
        super(Range.closed(2, 5), StringMetricVocabulary.needlemanWunch.stringValue());
    }

    @Override
    public NodeValue exec(final List<NodeValue> args) {

        final String firstString = args.get(0).getString();
        final String secondString = args.get(1).getString();

        if(args.size() == 5) {
            for(final Expression expression : getArgs()) {
                // FIXME this should only check args 2-4 not all args
                if(!(expression instanceof Constant)) {
                    throw new ExpressionEvaluationException("Parameters 2 through 5 must be constant expressions");
                }
            }
        }

        return makeDouble(getNeedlemanWunchFunction(args).compare(firstString, secondString));
    }


    private org.simmetrics.metrics.NeedlemanWunch getNeedlemanWunchFunction(final List<NodeValue> args) {
        if (needlemanWunch == null) {
            if (args.size() == 5) {
                
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

    @Override
    public void checkBuild(final String uri, final ExprList args) {
        if(!Range.closed(2, 3).contains(args.size())) {
            throw new QueryBuildException("Function '" + Lib.className(this) + "' takes two or three arguments") ;
        }
        if(args.get(0).isConstant() && !args.get(0).getConstant().isString()) {
            throw new QueryBuildException("Function '" + Lib.className(this) + "' first argument must be a string literal") ;
        }
        if(args.get(1).isConstant() && !args.get(1).getConstant().isString()) {
            throw new QueryBuildException("Function '" + Lib.className(this) + "' second argument must be a string literal") ;
        }
        if(args.size() == 3 && args.get(2).isConstant() && !args.get(2).getConstant().isInteger()) {
            throw new QueryBuildException("Function '" + Lib.className(this) + "' third argument must be a integer literal") ;
        }
    }
}
