package com.semantalytics.jena.function.string.metric;

import com.google.common.collect.Range;
import org.apache.jena.sparql.expr.ExprEvalException;
import org.apache.jena.sparql.expr.NodeValue;
import org.apache.jena.sparql.function.FunctionBase;
import org.simmetrics.metrics.functions.MatchMismatch;

import java.util.List;

import static org.apache.jena.sparql.expr.NodeValue.*;

public final class SmithWatermanGotoh extends FunctionBase {

    private org.simmetrics.metrics.SmithWatermanGotoh smithWatermanGotoh;

    protected SmithWatermanGotoh() {
        super(Range.closed(2, 5), StringMetricVocabulary.smithWatermanGotoh.stringValue());
    }

    @Override
    public NodeValue exec(final List<NodeValue> args) {

        final String firstString = args.get(0).getString();
        final String secondString = args.get(1).getString();

        return makeDouble(getSmithWatermanGotohFunction().compare(firstString, secondString));
    }

    private org.simmetrics.metrics.SmithWatermanGotoh getSmithWatermanGotohFunction(final List<NodeValue> args) {
        if (smithWatermanGotoh == null) {
            if (values.length == 5) {
                for (final Expression expression : getArgs()) {
                    //FIXME check only 2 through 7 not all params
                    if (!(expression instanceof Constant)) {
                        throw new ExprEvalException("Parameters 2 through 7 must be constant expressions");
                    }
                }
                
                final float gapValue = args.get(2).getFloat();
                final float matchPenalty = args.get(3).getFloat();
                final float mismatchPenalty = args.get(4).getFloat();
                
                smithWatermanGotoh = new org.simmetrics.metrics.SmithWatermanGotoh(gapValue, new MatchMismatch(matchPenalty, mismatchPenalty));
            } else {
                smithWatermanGotoh = new org.simmetrics.metrics.SmithWatermanGotoh();
            }
        }
        return smithWatermanGotoh;
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
