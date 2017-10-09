package com.semantalytics.stardog.plan.filter.functions.strings;

import com.complexible.common.rdf.model.Values;
import com.complexible.stardog.plan.filter.ExpressionEvaluationException;
import com.complexible.stardog.plan.filter.ExpressionVisitor;
import com.complexible.stardog.plan.filter.functions.AbstractFunction;
import com.complexible.stardog.plan.filter.functions.string.StringFunction;
import org.apache.commons.lang3.StringUtils;
import org.openrdf.model.Value;

public final class PrependIfMissingIgnoreCase extends AbstractFunction implements StringFunction {

    protected PrependIfMissingIgnoreCase() {
        super(3, StringsVocab.ontology().prependIfMissingIgnoreCase.toString());
    }

    private PrependIfMissingIgnoreCase(final PrependIfMissingIgnoreCase prependIfMissingIgnoreCase) {
        super(prependIfMissingIgnoreCase);
    }

    @Override
    protected Value internalEvaluate(final Value... values) throws ExpressionEvaluationException {
      
      final String string = assertStringLiteral(values[0]).stringValue();
      final String suffix = assertStringLiteral(values[1]).stringValue();
      final String suffixes = assertIntegerLiteral(values[2]).stringValue();
      
      return Values.literal(StringUtils.appendIfMissingIgnoreCase(string, suffix, suffixes));
    }

    @Override
    public PrependIfMissingIgnoreCase copy() {
        return new PrependIfMissingIgnoreCase(this);
    }

    @Override
    public void accept(final ExpressionVisitor expressionVisitor) {
        expressionVisitor.visit(this);
    }

    @Override
    public String toString() {
        return "Prepends the prefix to the start of the string if the string does not already start with any of the prefixes ignoring case. ";
    }
}
