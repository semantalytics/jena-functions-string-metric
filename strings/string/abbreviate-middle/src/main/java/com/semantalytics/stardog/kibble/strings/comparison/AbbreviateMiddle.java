package com.semantalytics.stardog.kibble.strings.comparison;

import com.complexible.common.rdf.model.Values;
import com.complexible.stardog.plan.filter.ExpressionEvaluationException;
import com.complexible.stardog.plan.filter.ExpressionVisitor;
import com.complexible.stardog.plan.filter.functions.AbstractFunction;
import com.complexible.stardog.plan.filter.functions.string.StringFunction;
import org.apache.commons.lang3.StringUtils;
import org.openrdf.model.Value;

public final class AbbreviateMiddle extends AbstractFunction implements StringFunction {

    protected AbbreviateMiddle() {
        super(3, ComparisonVocabulary.abbreviateMiddle.toString());
    }

    private AbbreviateMiddle(final AbbreviateMiddle abbreviateMiddle) {
        super(abbreviateMiddle);
    }

    @Override
    protected Value internalEvaluate(final Value... values) throws ExpressionEvaluationException {
      
      final String string = assertStringLiteral(values[0]).stringValue();
      final String middle = assertStringLiteral(values[1]).stringValue();
      final int length = assertIntegerLiteral(values[2]).intValue();
      
      return Values.literal(StringUtils.abbreviateMiddle(string, middle, length));
    }

    @Override
    public AbbreviateMiddle copy() {
        return new AbbreviateMiddle(this);
    }

    @Override
    public void accept(final ExpressionVisitor expressionVisitor) {
        expressionVisitor.visit(this);
    }

    @Override
    public String toString() {
        return ComparisonVocabulary.abbreviateMiddle.name();
    }
}
