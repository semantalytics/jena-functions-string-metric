package com.semantalytics.stardog.kibble.strings.string;

import com.complexible.common.rdf.model.Values;
import com.complexible.stardog.plan.filter.ExpressionEvaluationException;
import com.complexible.stardog.plan.filter.ExpressionVisitor;
import com.complexible.stardog.plan.filter.functions.AbstractFunction;
import com.complexible.stardog.plan.filter.functions.string.StringFunction;
import org.apache.commons.lang3.StringUtils;
import org.openrdf.model.Value;

public final class Rotate extends AbstractFunction implements StringFunction {

    protected Rotate() {
        super(2, StringVocabulary.rotate.stringValue());
    }

    private Rotate(final Rotate rotate) {
        super(rotate);
    }

    @Override
    protected Value internalEvaluate(final Value... values) throws ExpressionEvaluationException {
      
      final String string = assertStringLiteral(values[0]).stringValue();
      final int shift = assertIntegerLiteral(values[1]).intValue();

      return Values.literal(StringUtils.rotate(string, shift));
    }

    @Override
    public Rotate copy() {
        return new Rotate(this);
    }

    @Override
    public void accept(final ExpressionVisitor expressionVisitor) {
        expressionVisitor.visit(this);
    }

    @Override
    public String toString() {
        return StringVocabulary.rotate.name();
    }
}
