package com.semantalytics.stardog.kibble.strings.string;

import com.complexible.common.rdf.model.Values;
import com.complexible.stardog.plan.filter.ExpressionEvaluationException;
import com.complexible.stardog.plan.filter.ExpressionVisitor;
import com.complexible.stardog.plan.filter.functions.AbstractFunction;
import com.complexible.stardog.plan.filter.functions.string.StringFunction;
import org.apache.commons.lang3.StringUtils;
import org.openrdf.model.Value;

public final class Rotate extends AbstractFunction implements StringFunction {

    protected Split() {
        super(Range.closed(1, 3), StringVocabulary.split.stringValue());
    }

    private Split(final Split split) {
        super(split);
    }

    @Override
    protected Value internalEvaluate(final Value... values) throws ExpressionEvaluationException {
      
      final String string = assertStringLiteral(values[0]).stringValue();
      
      switch(values.length) {
        case 1: {
              return Values.literal(StringUtils.split(string, shift));
        }
        case 2: {
                final String separator = assertIntegerLiteral(values[1]).intValue();
                return Values.literal(StringUtils.split(string, shift));
        }
        case 3: {
                final int max = assertIntegerLiteral(values[2]).intValue();
                return Values.literal(StringUtils.split(string, shift));
        }
        default: {
            throw new ExpressionEvaluationException();
        }
      }
    }

    @Override
    public Split copy() {
        return new Split(this);
    }

    @Override
    public void accept(final ExpressionVisitor expressionVisitor) {
        expressionVisitor.visit(this);
    }

    @Override
    public String toString() {
        return StringVocabulary.split.name();
    }
}
