package com.semantalytics.stardog.kibble.strings.comparison;

import com.complexible.common.rdf.model.Values;
import com.complexible.stardog.plan.filter.ExpressionEvaluationException;
import com.complexible.stardog.plan.filter.ExpressionVisitor;
import com.complexible.stardog.plan.filter.functions.AbstractFunction;
import com.complexible.stardog.plan.filter.functions.string.StringFunction;
import com.google.common.base.Strings;
import org.openrdf.model.Value;

public final class CommonSuffix extends AbstractFunction implements StringFunction {

    protected CommonSuffix() {
        super(2, StringVocabulary.commonSuffix.toString());
    }

    private CommonSuffix(final CommonSuffix commonSuffix) {
        super(commonSuffix);
    }

    @Override
    protected Value internalEvaluate(final Value... values) throws ExpressionEvaluationException {
      
      final String firstString = assertStringLiteral(values[0]).stringValue();
      final String secondString = assertStringLiteral(values[1]).stringValue();
      
      return Values.literal(Strings.commonSuffix(firstString, secondString));
    }

    @Override
    public CommonSuffix copy() {
        return new CommonSuffix(this);
    }

    @Override
    public void accept(final ExpressionVisitor expressionVisitor) {
        expressionVisitor.visit(this);
    }

    @Override
    public String toString() {
        return StringVocabulary.commonSuffix.name();
    }
}
