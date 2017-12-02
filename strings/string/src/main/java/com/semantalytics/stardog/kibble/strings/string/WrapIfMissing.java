package com.semantalytics.stardog.kibble.strings.string;

import com.complexible.common.rdf.model.Values;
import com.complexible.stardog.plan.filter.ExpressionEvaluationException;
import com.complexible.stardog.plan.filter.ExpressionVisitor;
import com.complexible.stardog.plan.filter.functions.AbstractFunction;
import com.complexible.stardog.plan.filter.functions.string.StringFunction;
import org.apache.commons.lang3.StringUtils;
import org.openrdf.model.Value;

import static com.google.common.base.Preconditions.checkElementIndex;

public final class WrapIfMissing extends AbstractFunction implements StringFunction {

    protected WrapIfMissing() {
        super(2, StringVocabulary.wrapIfMissing.toString());
    }

    private WrapIfMissing(final WrapIfMissing wrapIfMissing) {
        super(wrapIfMissing);
    }

    @Override
    protected Value internalEvaluate(final Value... values) throws ExpressionEvaluationException {
      
      final String string = assertStringLiteral(values[0]).stringValue();
      final String wrapWith = assertIntegerLiteral(values[1]).stringValue();
      
      checkElementIndex(0, wrapWith.length());

      return Values.literal(StringUtils.wrapIfMissing(string, wrapWith.charAt(0)));
    }

    @Override
    public WrapIfMissing copy() {
        return new WrapIfMissing(this);
    }

    @Override
    public void accept(final ExpressionVisitor expressionVisitor) {
        expressionVisitor.visit(this);
    }

    @Override
    public String toString() {
        return StringVocabulary.wrapIfMissing.name();
    }
}
