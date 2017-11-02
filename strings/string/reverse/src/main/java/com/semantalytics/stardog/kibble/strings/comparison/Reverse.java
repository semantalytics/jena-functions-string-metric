package com.semantalytics.stardog.kibble.strings.comparison;

import com.complexible.common.rdf.model.Values;
import com.complexible.stardog.plan.filter.ExpressionEvaluationException;
import com.complexible.stardog.plan.filter.ExpressionVisitor;
import com.complexible.stardog.plan.filter.functions.AbstractFunction;
import com.complexible.stardog.plan.filter.functions.string.StringFunction;
import org.apache.commons.lang3.StringUtils;
import org.openrdf.model.Value;

public final class Reverse extends AbstractFunction implements StringFunction {

    protected Reverse() {
        super(1, ComparisonVocabulary.reverse.toString());
    }

    private Reverse(final Reverse reverse) {
        super(reverse);
    }

    @Override
    protected Value internalEvaluate(final Value... values) throws ExpressionEvaluationException {
      
      final String string = assertStringLiteral(values[0]).stringValue();
            
      return Values.literal(StringUtils.reverse(string));
    }

    @Override
    public Reverse copy() {
        return new Reverse(this);
    }

    @Override
    public void accept(final ExpressionVisitor expressionVisitor) {
        expressionVisitor.visit(this);
    }

    @Override
    public String toString() {
        return ComparisonVocabulary.reverse.name();
    }
}
