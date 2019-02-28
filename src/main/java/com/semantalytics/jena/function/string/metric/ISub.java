package com.semantalytics.jena.function.string.metric;

import com.google.common.collect.Range;
import org.apache.jena.sparql.expr.NodeValue;
import org.apache.jena.sparql.function.FunctionBase;

import java.util.List;

public final class ISub extends FunctionBase {

    private static final I_Sub iSub = new I_Sub();

    private ISub(ISub iSub) {
        super(iSub);
    }

    protected ISub() {
        super(Range.closed(2, 3), StringMetricVocabulary.isub.stringValue());
    }

    @Override
    public void accept(final ExpressionVisitor expressionVisitor) {
        expressionVisitor.visit(this);
    }

    @Override
    public NodeValue exec(final List<NodeValue> values) {
        final String firstString = assertLiteral(values[0]).stringValue();
        final String secondString = assertLiteral(values[1]).stringValue();

        boolean normalizeStrings = false;

        if(values.length == 3) {
            if(!(getThirdArg() instanceof Constant)) {
                throw new ExpressionEvaluationException("Parameter must be a constant expression");
            }
            normalizeStrings = EvalUtil.toBoolean(values[2].stringValue());
        }

        return Values.literal(iSub.score(firstString, secondString, normalizeStrings));
    }
}
