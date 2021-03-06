package com.semantalytics.jena.function.string.metric;


import org.apache.jena.sparql.expr.NodeValue;
import org.apache.jena.sparql.function.FunctionBase2;

import java.util.List;

import static org.apache.jena.sparql.expr.NodeValue.*;

public final class LevenshteinDistance extends FunctionBase2 {

    private static final info.debatty.java.stringsimilarity.Levenshtein levenshtein;

    static {
        levenshtein = new info.debatty.java.stringsimilarity.Levenshtein();
    }

    public static final String name = StringMetricVocabulary.levenshteinDistance.stringValue();

    @Override
    public NodeValue exec(final NodeValue arg0, final NodeValue arg1) {
        return makeDouble(levenshtein.distance(arg0.getString(), arg1.getString());
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
