package com.semantalytics.stardog.kibble.strings.string;

import com.complexible.common.rdf.model.Values;
import com.complexible.stardog.plan.filter.ExpressionEvaluationException;
import com.complexible.stardog.plan.filter.ExpressionVisitor;
import com.complexible.stardog.plan.filter.functions.AbstractFunction;
import com.complexible.stardog.plan.filter.functions.string.StringFunction;
import org.apache.commons.lang3.StringUtils;
import org.openrdf.model.Value;
import static com.google.common.base.Preconditions.checkElementIndex;

public final class Wrap extends AbstractFunction implements StringFunction {

    protected Wrap() {
        super(2, StringVocabulary.wrap.toString());
    }

    private Wrap(final Wrap wrap) {
        super(wrap);
    }

    @Override
    protected Value internalEvaluate(final Value... values) throws ExpressionEvaluationException {
      
      final String string = assertStringLiteral(values[0]).stringValue();
      final String wrapWith = assertIntegerLiteral(values[1]).stringValue();
      
      checkElementIndex(0, wrapWith.length());
      
      return Values.literal(StringUtils.wrap(string, wrapWith.charAt(0)));
    }

    @Override
    public Wrap copy() {
        return new Wrap(this);
    }

    @Override
    public void accept(final ExpressionVisitor expressionVisitor) {
        expressionVisitor.visit(this);
    }

    @Override
    public String toString() {
        return StringVocabulary.wrap.name();
    }
}
