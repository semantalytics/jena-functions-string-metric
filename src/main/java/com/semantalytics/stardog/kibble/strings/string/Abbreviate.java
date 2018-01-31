package com.semantalytics.stardog.kibble.strings.string;

import com.complexible.common.rdf.model.Values;
import com.complexible.stardog.plan.filter.ExpressionEvaluationException;
import com.complexible.stardog.plan.filter.ExpressionVisitor;
import com.complexible.stardog.plan.filter.functions.AbstractFunction;
import com.complexible.stardog.plan.filter.functions.string.StringFunction;
import com.google.common.collect.Range;
import org.apache.commons.lang3.StringUtils;
import org.openrdf.model.Value;

public final class Abbreviate extends AbstractFunction implements StringFunction {

    protected Abbreviate() {
        super(Range.closed(2, 4), StringVocabulary.abbreviate.stringValue());
    }

    private Abbreviate(final Abbreviate abbreviate) {
        super(abbreviate);
    }

    @Override
    protected Value internalEvaluate(final Value... values) throws ExpressionEvaluationException {
      
      final String string = assertStringLiteral(values[0]).stringValue();
      final int maxWidth = assertIntegerLiteral(values[1]).intValue();

      if(maxWidth <= 3) {
          throw new ExpressionEvaluationException("maxWidth must be greater than 3. Found " + maxWidth);
      }

      switch(values.length) {
          case 2:
           return Values.literal(StringUtils.abbreviate(string, maxWidth));
          case 3: {
              final int offset = assertIntegerLiteral(values[2]).intValue();
              return Values.literal(StringUtils.abbreviate(string, offset, maxWidth));
          }
          case 4: {
              final int offset = assertIntegerLiteral(values[2]).intValue();
              final String abbrevMarker = assertStringLiteral(values[3].stringValue());
              return Values.literal(StringUtils.abbreviate(string, abbrevMarker, offset, maxWidth);
          }
          default:
              throw new ExpressionEvaluationException("Incorrect number of parameters. Valid values are 1, 2, or 3. Found " + values.length);
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
        return StringVocabulary.abbreviate.name();
    }
}
