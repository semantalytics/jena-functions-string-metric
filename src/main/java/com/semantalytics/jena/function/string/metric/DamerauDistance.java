package com.semantalytics.jena.function.string.metric;

import org.apache.jena.atlas.lib.Lib;
import org.apache.jena.query.QueryBuildException;
import org.apache.jena.sparql.expr.ExprEvalException;
import org.apache.jena.sparql.expr.ExprList;
import org.apache.jena.sparql.expr.NodeValue;
import org.apache.jena.sparql.function.FunctionBase2;

import static org.apache.jena.sparql.expr.NodeValue.*;

public final class DamerauDistance extends FunctionBase2 {

    private static final info.debatty.java.stringsimilarity.Damerau damerau;
    public static final String name = StringMetricVocabulary.damerauDistance.stringValue();

    static {
        damerau = new info.debatty.java.stringsimilarity.Damerau();
    }

    @Override
    public NodeValue exec(final NodeValue arg0, final NodeValue arg1) {

        if(!arg0.isString())
            throw new ExprEvalException(Lib.className(this) + " first argument must be a string literal");

        if(!arg1.isString())
            throw new ExprEvalException(Lib.className(this) + " second argument must be a string literal");

        return makeDouble(damerau.distance(arg0.getString(), arg1.getString()));
    }

    @Override
    public void checkBuild(String uri, ExprList args) {
        if(args.get(0).isConstant() && !args.get(0).getConstant().isString()) {
            throw new QueryBuildException("Function '" + Lib.className(this) + "' first argument must be a string literal") ;
        }
        if(args.get(1).isConstant() && !args.get(1).getConstant().isString()) {
            throw new QueryBuildException("Function '" + Lib.className(this) + "' second argument must be a string literal") ;
        }
    }
}
