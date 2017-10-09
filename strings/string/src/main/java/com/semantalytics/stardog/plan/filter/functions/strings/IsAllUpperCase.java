package com.semantalytics.stardog.plan.filter.functions.strings;

import com.complexible.common.rdf.model.Values;
import com.complexible.stardog.plan.filter.ExpressionEvaluationException;
import com.complexible.stardog.plan.filter.ExpressionVisitor;
import com.complexible.stardog.plan.filter.functions.AbstractFunction;
import com.complexible.stardog.plan.filter.functions.string.StringFunction;
import org.apache.commons.lang3.StringUtils;
import org.openrdf.model.Value;

public final class IsAllUpperCase extends AbstractFunction implements StringFunction {

    protected IsAllUpperCase() {
        super(1, StringsVocab.ontology().isAllUpperCase.toString());
    }

    private IsAllUpperCase(final IsAllUpperCase isUpperCase) {
        super(isUpperCase);
    }

    @Override
    protected Value internalEvaluate(final Value... values) throws ExpressionEvaluationException {
      
      final String string = assertStringLiteral(values[0]).stringValue();

      return Values.literal(StringUtils.isAllUpperCase(string));
    }

    @Override
    public IsAllUpperCase copy() {
        return new IsAllUpperCase(this);
    }

    @Override
    public void accept(final ExpressionVisitor expressionVisitor) {
        expressionVisitor.visit(this);
    }

    @Override
    public String toString() {
        return "IsAllUpperCase a String using ellipses";
    }
}
