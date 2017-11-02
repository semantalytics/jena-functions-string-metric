package com.semantalytics.stardog.kibble.strings.comparison;

import com.complexible.common.rdf.model.Values;
import com.complexible.stardog.plan.filter.ExpressionEvaluationException;
import com.complexible.stardog.plan.filter.ExpressionVisitor;
import com.complexible.stardog.plan.filter.functions.AbstractFunction;
import com.complexible.stardog.plan.filter.functions.string.StringFunction;
import org.apache.commons.lang3.StringUtils;
import org.openrdf.model.Value;

public final class AppendIfMissing extends AbstractFunction implements StringFunction {

    protected AppendIfMissing() {
        super(3, ComparisonVocabulary.appendIfMissing.toString());
    }

    private AppendIfMissing(final AppendIfMissing appendIfMissing) {
        super(appendIfMissing);
    }

    @Override
    protected Value internalEvaluate(final Value... values) throws ExpressionEvaluationException {
      
      final String string = assertStringLiteral(values[0]).stringValue();
      final String suffix = assertStringLiteral(values[1]).stringValue();
      final String suffixes = assertIntegerLiteral(values[2]).stringValue();
      
      return Values.literal(StringUtils.appendIfMissing(string, suffix, suffixes));
    }

    @Override
    public AppendIfMissing copy() {
        return new AppendIfMissing(this);
    }

    @Override
    public void accept(final ExpressionVisitor expressionVisitor) {
        expressionVisitor.visit(this);
    }

    @Override
    public String toString() {
        return ComparisonVocabulary.appendIfMissing.name();
    }
}
