package com.semantalytics.jena.function.string.metric;

import com.google.common.collect.Range;
import org.apache.jena.sparql.expr.NodeValue;
import org.apache.jena.sparql.function.FunctionBase;

import java.util.List;

import static org.apache.jena.sparql.expr.NodeValue.*;

public final class ISub extends FunctionBase {

    private static final I_Sub iSub = new I_Sub();

    private ISub(ISub iSub) {
        super(iSub);
    }

    protected ISub() {
        super(Range.closed(2, 3), StringMetricVocabulary.isub.stringValue());
    }

    @Override
    public NodeValue exec(final List<NodeValue> args) {
        final String firstString = args.get(0).getString();
        final String secondString = args.get(1).getString();

        boolean normalizeStrings = false;

        if(args.size() == 3) {
            if(!(getThirdArg() instanceof Constant)) {
                throw new ExpressionEvaluationException("Parameter must be a constant expression");
            }
            normalizeStrings = args.get(2).getBoolean();
        }

        return makeDouble(iSub.score(firstString, secondString, normalizeStrings));
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
