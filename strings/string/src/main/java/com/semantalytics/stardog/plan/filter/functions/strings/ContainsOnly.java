package com.semantalytics.stardog.plan.filter.functions.strings;

import com.complexible.common.rdf.model.Values;
import com.complexible.stardog.plan.filter.ExpressionEvaluationException;
import com.complexible.stardog.plan.filter.ExpressionVisitor;
import com.complexible.stardog.plan.filter.functions.AbstractFunction;
import com.complexible.stardog.plan.filter.functions.string.StringFunction;
import org.apache.commons.lang3.StringUtils;
import org.openrdf.model.Value;

public final class ContainsOnly extends AbstractFunction implements StringFunction {

    protected ContainsOnly() {
        super(2, StringsVocab.ontology().containsOnly.toString());
    }

    private ContainsOnly(final ContainsOnly containsOnly) {
        super(containsOnly);
    }

    @Override
    protected Value internalEvaluate(final Value... values) throws ExpressionEvaluationException {
      
      final String string = assertStringLiteral(values[0]).stringValue();
      final String validChars = assertIntegerLiteral(values[1]).stringValue();
      
      return Values.literal(StringUtils.containsOnly(string, validChars));
    }

    @Override
    public ContainsOnly copy() {
        return new ContainsOnly(this);
    }

    @Override
    public void accept(final ExpressionVisitor expressionVisitor) {
        expressionVisitor.visit(this);
    }

    @Override
    public String toString() {
        return "ContainsOnly a String using ellipses";
    }
}
