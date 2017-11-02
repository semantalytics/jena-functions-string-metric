package com.semantalytics.stardog.kibble.strings.comparison;

import com.complexible.common.rdf.model.Values;
import com.complexible.stardog.plan.filter.ExpressionEvaluationException;
import com.complexible.stardog.plan.filter.ExpressionVisitor;
import com.complexible.stardog.plan.filter.functions.AbstractFunction;
import com.complexible.stardog.plan.filter.functions.string.StringFunction;
import com.google.common.collect.Range;
import org.apache.commons.lang3.StringUtils;
import org.openrdf.model.Value;

import static com.google.common.base.Preconditions.checkArgument;

public final class Abbreviate extends AbstractFunction implements StringFunction {

    protected Abbreviate() {
        super(Range.closed(2, 3), ComparisonVocabulary.abbreviate.toString());
    }

    private Abbreviate(final Abbreviate abbreviate) {
        super(abbreviate);
    }

    @Override
    protected Value internalEvaluate(final Value... values) throws ExpressionEvaluationException {
      
      final String string = assertStringLiteral(values[0]).stringValue();
      final int maxWidth = assertIntegerLiteral(values[1]).intValue();

      checkArgument(maxWidth > 3, "maxWidth must be greater than 3. Found " + maxWidth);

      switch(values.length) {
          case 2:
            return Values.literal(StringUtils.abbreviate(string, maxWidth));
          case 3:
            final int offset = assertIntegerLiteral(values[2]).intValue();
            return Values.literal(StringUtils.abbreviate(string, offset, maxWidth));
          default:
            throw new ExpressionEvaluationException("function takes 2 or 3 arguments. Found " + values.length);
      }
    }

    @Override
    public Abbreviate copy() {
        return new Abbreviate(this);
    }

    @Override
    public void accept(final ExpressionVisitor expressionVisitor) {
        expressionVisitor.visit(this);
    }

    @Override
    public String toString() {
        return ComparisonVocabulary.abbreviate.name();
    }
}
