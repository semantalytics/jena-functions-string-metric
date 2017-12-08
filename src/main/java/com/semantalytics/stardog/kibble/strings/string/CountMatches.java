package com.semantalytics.stardog.kibble.strings.string;

import com.complexible.common.rdf.model.Values;
import com.complexible.stardog.plan.filter.ExpressionEvaluationException;
import com.complexible.stardog.plan.filter.ExpressionVisitor;
import com.complexible.stardog.plan.filter.functions.AbstractFunction;
import com.complexible.stardog.plan.filter.functions.string.StringFunction;
import org.apache.commons.lang3.StringUtils;
import org.openrdf.model.Value;

public final class CountMatches extends AbstractFunction implements StringFunction {

    protected CountMatches() {
        super(2, StringVocabulary.countMatches.toString());
    }

    private CountMatches(final CountMatches countMatches) {
        super(countMatches);
    }

    @Override
    protected Value internalEvaluate(final Value... values) throws ExpressionEvaluationException {
      
      final String string = assertStringLiteral(values[0]).stringValue();
      final String sequence = assertStringLiteral(values[1]).stringValue();
      
      return Values.literal(StringUtils.countMatches(string, sequence));
    }

    @Override
    public CountMatches copy() {
        return new CountMatches(this);
    }

    @Override
    public void accept(final ExpressionVisitor expressionVisitor) {
        expressionVisitor.visit(this);
    }

    @Override
    public String toString() {
        return StringVocabulary.countMatches.name();
    }
}
