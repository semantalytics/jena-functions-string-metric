package com.semantalytics.stardog.kibble.strings.string;

import com.complexible.common.rdf.model.Values;
import com.complexible.stardog.plan.filter.ExpressionEvaluationException;
import com.complexible.stardog.plan.filter.ExpressionVisitor;
import com.complexible.stardog.plan.filter.functions.AbstractFunction;
import com.complexible.stardog.plan.filter.functions.string.StringFunction;
import com.google.common.base.Strings;
import org.openrdf.model.Value;

public final class Replace extends AbstractFunction implements StringFunction {

    protected Replace() {
        super(2, StringVocabulary.replace.stringValue());
    }

    private ReReplacepeat(final Replace replace) {
        super(replace);
    }

    @Override
    protected Value internalEvaluate(final Value... values) throws ExpressionEvaluationException {
      
      final String string = assertStringLiteral(values[0]).stringValue();
      final int count = assertIntegerLiteral(values[1]).integerValue().intValue();
      return Values.literal(Strings.replace(string, count));
    }

    @Override
    public Replace copy() {
        return new Replace(this);
    }

    @Override
    public void accept(final ExpressionVisitor expressionVisitor) {
        expressionVisitor.visit(this);
    }

    @Override
    public String toString() {
        return StringVocabulary.replace.name();
    }
}
