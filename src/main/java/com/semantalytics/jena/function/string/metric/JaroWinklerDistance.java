package com.semantalytics.jena.function.string.metric;

import com.google.common.collect.Range;
import org.apache.jena.atlas.lib.Lib;
import org.apache.jena.query.QueryBuildException;
import org.apache.jena.sparql.ARQInternalErrorException;
import org.apache.jena.sparql.expr.ExprEvalException;
import org.apache.jena.sparql.expr.ExprList;
import org.apache.jena.sparql.expr.NodeValue;
import org.apache.jena.sparql.function.FunctionBase;

import java.util.List;

import static org.apache.jena.sparql.expr.NodeValue.*;

public final class JaroWinklerDistance extends FunctionBase {

    public static final String name = StringMetricVocabulary.jaroWinklerDistance.stringValue());

    @Override
    public NodeValue exec(final List<NodeValue> args) {

        if ( args == null )
            // The contract on the function interface is that this should not happen.
            throw new ARQInternalErrorException(Lib.className(this) + ": Null args list") ;

        if(!args.get(0).isString())
            throw new ExprEvalException(Lib.className(this) + " first argument must be a string");

        if(!args.get(1).isString())
            throw new ExprEvalException(Lib.className(this) + " second argument must be a string");

        if(args.size() == 3 && !args.get(2).isFloat())
            throw new ExprEvalException(Lib.className(this) + " third argument must be a float");

        if(args.size() == 4 && !args.get(3).isFloat())
            throw new ExprEvalException(Lib.className(this) + " third argument must be a float");

        if(args.size() == 5 && !args.get(4).isInteger())
            throw new ExprEvalException(Lib.className(this) + " third argument must be an integer");

        final String firstString = args.get(0).asString();
        final String secondString = args.get(1).asString();
        float boostThreshold = args.size() >=3 ? args.get(2).getFloat() : 0.7f;
        float prefixScale = args.size() >=4 ? args.get(3).getFloat() : 0.1f;
        int maxPrefixLength = args.size() == 5 ? args.get(4).getInteger().intValue() : 4;

        final org.simmetrics.metrics.JaroWinkler jaroWinkler;
        jaroWinkler = new org.simmetrics.metrics.JaroWinkler(boostThreshold, prefixScale, maxPrefixLength);

        return makeDouble(jaroWinkler.distance(firstString, secondString));
    }

    @Override
    public void checkBuild(String uri, ExprList args) {
        if(!Range.closed(2, 5).contains(args.size())) {
            throw new QueryBuildException("Function '" + Lib.className(this) + "' takes two to five arguments") ;
        }
        if(args.get(0).isConstant() && !args.get(0).getConstant().isString()) {
            throw new QueryBuildException("Function '" + Lib.className(this) + "' first argument must be a string") ;
        }
        if(args.get(1).isConstant() && !args.get(1).getConstant().isString()) {
            throw new QueryBuildException("Function '" + Lib.className(this) + "' second argument must be a string") ;
        }
        if(args.size() == 3 && args.get(2).isConstant() && !args.get(2).getConstant().isFloat()) {
            throw new QueryBuildException("Function '" + Lib.className(this) + "' third argument must be a float") ;
        }
        if(args.size() == 4 && args.get(3).isConstant() && !args.get(2).getConstant().isFloat()) {
            throw new QueryBuildException("Function '" + Lib.className(this) + "' third argument must be a float") ;
        }
        if(args.size() == 5 && args.get(4).isConstant() && !args.get(2).getConstant().isInteger()) {
            throw new QueryBuildException("Function '" + Lib.className(this) + "' third argument must be a integer literal") ;
        }
    }
}
