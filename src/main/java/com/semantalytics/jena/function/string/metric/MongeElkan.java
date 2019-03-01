package com.semantalytics.jena.function.string.metric;

import com.google.common.collect.Range;
import org.apache.jena.sparql.expr.NodeValue;
import org.apache.jena.sparql.function.FunctionBase;

import java.util.List;

import static org.apache.jena.sparql.expr.NodeValue.*;

public final class MongeElkan extends FunctionBase {

    private info.debatty.java.stringsimilarity.Cosine cosine;

    protected MongeElkan() {
        super(Range.closed(2, 3), StringMetricVocabulary.cosineDistance.stringValue());
    }

    private MongeElkan(final MongeElkan mongeElkan) {
        super(mongeElkan);
        this.cosine = mongeElkan.cosine;
    }

    public info.debatty.java.stringsimilarity.Cosine getCosineFunction(final Value... values) throws ExpressionEvaluationException {
        if(cosine == null) {
            if (values.length == 3) {
                 assertNumericLiteral(values[2]);
                 if(!(getThirdArg() instanceof Constant)) {
                    throw new ExpressionEvaluationException("Parameter must be constant expression");
                }
                final int n = assertNumericLiteral(values[2]).intValue();
                cosine = new info.debatty.java.stringsimilarity.Cosine(n);
            } else {
                cosine = new info.debatty.java.stringsimilarity.Cosine();
            }
        }
        return cosine;
    }

    @Override
    public NodeValue exec(final List<NodeValue> args) {

        final String string1 = args.get(0).getString();
        final String string2 = args.get(1).getString();

        return makeDouble(getCosineFunction(args).distance(string1, string2));
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
