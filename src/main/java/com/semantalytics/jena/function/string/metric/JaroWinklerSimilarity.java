package com.semantalytics.jena.function.string.metric;

import com.google.common.collect.Range;
import org.apache.jena.sparql.expr.NodeValue;
import org.apache.jena.sparql.function.FunctionBase;

import java.util.List;

import static org.apache.jena.sparql.expr.NodeValue.*;

public final class JaroWinklerSimilarity extends FunctionBase {

    protected JaroWinklerSimilarity() {
        super(Range.closed(2, 5), StringMetricVocabulary.jaroWinklerSimilarity.stringValue());
    }

    private JaroWinklerSimilarity(final JaroWinklerSimilarity jaroWinklerSimilarity) {
        super(jaroWinklerSimilarity);
    }

    @Override
    public NodeValue exec(final List<NodeValue> args) {

        final String firstString = args.get(0).getString();
        final String secondString = args.get(1).getString();

        float boostThreshold = 0.7f;
        float prefixScale = 0.1f;
        int maxPrefixLength = 4;

        if(args.size() >= 3) {
            boostThreshold = assertNumericLiteral(values[2]).floatValue();
        }
        if(args.size() >= 4) {
            prefixScale = assertNumericLiteral(values[3]).floatValue();
        }
        if(args.size() == 5) {
            maxPrefixLength = assertNumericLiteral(values[4]).intValue();
        }

        final org.simmetrics.metrics.JaroWinkler jaroWinkler;
        jaroWinkler = new org.simmetrics.metrics.JaroWinkler(boostThreshold, prefixScale, maxPrefixLength);

        return makeDouble(jaroWinkler.compare(firstString, secondString));
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
