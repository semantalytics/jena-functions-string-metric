package com.semantalytics.stardog.kibble.strings.comparison;

import com.complexible.common.rdf.model.Values;
import com.complexible.stardog.plan.filter.ExpressionEvaluationException;
import com.complexible.stardog.plan.filter.ExpressionVisitor;
import com.complexible.stardog.plan.filter.functions.AbstractFunction;
import com.complexible.stardog.plan.filter.functions.string.StringFunction;
import com.google.common.collect.Range;
import org.apache.commons.lang3.StringUtils;
import org.openrdf.model.Value;

public final class Center extends AbstractFunction implements StringFunction {

    protected Center() {
        super(Range.closed(2, 3), StringVocabulary.center.toString());
    }

    private Center(final Center center) {
        super(center);
    }

    @Override
    protected Value internalEvaluate(final Value... values) throws ExpressionEvaluationException {

        final String string = assertStringLiteral(values[0]).stringValue();
        final int size = assertIntegerLiteral(values[1]).intValue();

        switch(values.length) {
            case 2:
                return Values.literal(StringUtils.center(string, size));
            case 3:
                char padChar = assertStringLiteral(values[2]).stringValue().charAt(0);
                return Values.literal(StringUtils.center(string, size, padChar));
            default:
                throw new ExpressionEvaluationException("Function requires 2 or 3 arguments. Found " + values.length);
        }

    }

    @Override
    public Center copy() {
        return new Center(this);
    }

    @Override
    public void accept(final ExpressionVisitor expressionVisitor) {
        expressionVisitor.visit(this);
    }

    @Override
    public String toString() {
        return StringVocabulary.center.name();
    }
}
