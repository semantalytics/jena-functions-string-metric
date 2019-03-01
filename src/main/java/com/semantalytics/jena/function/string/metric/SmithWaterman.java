package com.semantalytics.jena.function.string.metric;

import com.google.common.collect.Range;
import org.apache.jena.sparql.expr.NodeValue;
import org.apache.jena.sparql.function.FunctionBase;
import org.simmetrics.metrics.functions.AffineGap;
import org.simmetrics.metrics.functions.MatchMismatch;

import java.util.List;


public final class SmithWaterman extends FunctionBase {

    private org.simmetrics.metrics.SmithWaterman smithWaterman;

    protected SmithWaterman() {
        super(Range.closed(2, 7), StringMetricVocabulary.smithWaterman.stringValue());
    }

    @Override
    protected NodeValue exec(final List<NodeValue> args) {

        final String firstString = args.get(0).getString();
        final String secondString = args.get(1).getString();

        return NodeValue.makeDouble(getSmithWatermanFunction(args).compare(firstString, secondString));
    }

    private org.simmetrics.metrics.SmithWaterman getSmithWatermanFunction(final List<NodeValue> args) {
            switch(values.length) {
                case 7: {
                    for (final Expression expression : getArgs()) {
                        if (!(expression instanceof Constant)) {
                            throw new ExpressionEvaluationException("Parameters 2 through 7 must be constant expressions");
                        }
                    }
                    final float gapA = assertNumericLiteral(values[2]).floatValue();
                    final float gapB = assertNumericLiteral(values[3]).floatValue();

                    final float subPenaltyA = assertNumericLiteral(values[4]).floatValue();
                    final float subPenaltyB = assertNumericLiteral(values[5]).floatValue();

                    final int windowSize = assertNumericLiteral(values[6]).intValue();

                    return new org.simmetrics.metrics.SmithWaterman(new AffineGap(gapA, gapB), new MatchMismatch(subPenaltyA, subPenaltyB), windowSize);
                }
                case 2: {
                    return new org.simmetrics.metrics.SmithWaterman();
                }
                default: {
                    throw new ExpressionEvaluationException("Function takes either 2 or 7 arguments. Fount " + values.length);
                }
            }
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
