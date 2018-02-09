package com.semantalytics.stardog.kibble.strings.string;

import com.complexible.common.rdf.model.Values;
import com.complexible.stardog.plan.filter.ExpressionEvaluationException;
import com.complexible.stardog.plan.filter.ExpressionVisitor;
import com.complexible.stardog.plan.filter.functions.AbstractFunction;
import com.complexible.stardog.plan.filter.functions.string.StringFunction;
import org.apache.commons.lang3.StringUtils;
import org.openrdf.model.Value;

public final class ContainsAny extends AbstractFunction implements StringFunction {

    protected ContainsAny() {
        super(2, StringVocabulary.containsAny.stringValue());
    }

    private ContainsAny(final ContainsAny containsAny) {
        super(containsAny);
    }

    @Override
    protected Value internalEvaluate(final Value... values) throws ExpressionEvaluationException {
      
      final String string = assertStringLiteral(values[0]).stringValue();
      final String search = assertStringLiteral(values[1]).stringValue();

      if(search.length() != 1) {
          throw new ExpressionEvaluationException("second argument must be a single character. Found " + search.length());
      }
      return Values.literal(StringUtils.contains(string, search.charAt(0)));
    }

    @Override
    public ContainsAny copy() {
        return new ContainsAny(this);
    }

    @Override
    public void accept(final ExpressionVisitor expressionVisitor) {
        expressionVisitor.visit(this);
    }

    @Override
    public String toString() {
        return StringVocabulary.containsAny.name();
    }
}
