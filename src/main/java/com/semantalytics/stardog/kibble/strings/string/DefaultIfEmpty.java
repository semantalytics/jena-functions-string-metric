package com.semantalytics.stardog.kibble.strings.string;

import com.complexible.common.rdf.model.Values;
import com.complexible.stardog.plan.filter.ExpressionEvaluationException;
import com.complexible.stardog.plan.filter.ExpressionVisitor;
import com.complexible.stardog.plan.filter.functions.AbstractFunction;
import com.complexible.stardog.plan.filter.functions.string.StringFunction;
import org.apache.commons.lang3.StringUtils;
import org.openrdf.model.Value;

public final class DefaultIfEmpty extends AbstractFunction implements StringFunction {

    protected DefaultIfEmpty() {
        super(2, StringVocabulary.defaultIfEmpty.stringValue());
    }

    private DefaultIfEmpty(final DefaultIfEmpty defaultIfEmpty) {
        super(defaultIfEmpty);
    }

    @Override
    protected Value internalEvaluate(final Value... values) throws ExpressionEvaluationException {
      
      final String string = assertStringLiteral(values[0]).stringValue();
      final String defaultString = assertIntegerLiteral(values[1]).stringValue();
      
      return Values.literal(StringUtils.defaultIfEmpty(string, defaultString));
    }

    @Override
    public DefaultIfEmpty copy() {
        return new DefaultIfEmpty(this);
    }

    @Override
    public void accept(final ExpressionVisitor expressionVisitor) {
        expressionVisitor.visit(this);
    }

    @Override
    public String toString() {
        return StringVocabulary.defaultIfEmpty.name();
    }
}
