package com.semantalytics.stardog.kibble.strings.string;

import com.complexible.common.rdf.model.Values;
import com.complexible.stardog.plan.filter.ExpressionEvaluationException;
import com.complexible.stardog.plan.filter.ExpressionVisitor;
import com.complexible.stardog.plan.filter.functions.AbstractFunction;
import com.complexible.stardog.plan.filter.functions.string.StringFunction;
import org.apache.commons.lang3.text.WordUtils;
import org.openrdf.model.Value;

public final class Initials extends AbstractFunction implements StringFunction {

    protected Initials() {
        super(Range.closed(1,2), StringVocabulary.initials.stringValue());
    }

    private Initials(final Initials initials) {
        super(initials);
    }

    @Override
    protected Value internalEvaluate(final Value... values) throws ExpressionEvaluationException {
      
      final String string = assertStringLiteral(values[0]).stringValue();
        
      if(values.length = 1) {  
          return Values.literal(WordUtils.initials(string));
      }
      if(values.length = 2) {
          final String delimiters = assertStringLiteral(values[1]).stringValue();
          return Values.literal(WordUtils.initials(string, delimiters.toCharArray()));
      }
    }

    @Override
    public Initials copy() {
        return new Initials(this);
    }

    @Override
    public void accept(final ExpressionVisitor expressionVisitor) {
        expressionVisitor.visit(this);
    }

    @Override
    public String toString() {
        return StringVocabulary.initials.name();
    }
}
