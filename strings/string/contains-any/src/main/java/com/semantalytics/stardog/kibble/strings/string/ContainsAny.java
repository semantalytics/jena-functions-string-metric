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
        super(2, StringVocabulary.containsAny.toString());
    }

    private ContainsAny(final ContainsAny containsAny) {
        super(containsAny);
    }

    @Override
    protected Value internalEvaluate(final Value... values) throws ExpressionEvaluationException {
      
      final String string = assertStringLiteral(values[0]).stringValue();
      final int maxWidth = assertIntegerLiteral(values[1]).intValue();
      
      return Values.literal(StringUtils.contains(string, maxWidth));
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
