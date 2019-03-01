package com.semantalytics.jena.function.string.metric;

import com.google.common.collect.Range;
import org.apache.jena.sparql.expr.NodeValue;
import org.apache.jena.sparql.function.FunctionBase;

import java.util.List;

import static org.apache.jena.sparql.expr.NodeValue.*;

public final class Sift4 extends FunctionBase {

    private static final info.debatty.java.stringsimilarity.experimental.Sift4 sift4;

    static {
        sift4 = new info.debatty.java.stringsimilarity.experimental.Sift4();
    }

    protected Sift4() {
        super(Range.closed(2, 3), StringMetricVocabulary.sift4.stringValue());
    }

    private Sift4(final Sift4 sift4) {
        super(sift4);
    }

    @Override
    public NodeValue exec(final List<NodeValue> args) {
        //TODO handle two arguments
        final String string1 = args.get(0).getString();
        final String string2 = args.get(1).getString();

        if(args.size() == 3) {
            sift4.setMaxOffset(args.get(2).getInteger().intValue());
        }

        return makeDouble(sift4.distance(string1, string2));
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
