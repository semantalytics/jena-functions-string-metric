package com.semantalytics.stardog.kibble.strings.comparison;

import com.complexible.stardog.plan.filter.ExpressionEvaluationException;
import com.complexible.stardog.plan.filter.ExpressionVisitor;
import com.complexible.stardog.plan.filter.functions.AbstractFunction;
import com.complexible.stardog.plan.filter.functions.string.StringFunction;
import org.apache.commons.lang3.StringUtils;
import org.openrdf.model.Value;

import static com.complexible.common.rdf.model.Values.*;

public final class IsNoneBlank extends AbstractFunction implements StringFunction {

    protected IsNoneBlank() {
        super(1, ComparisonVocabulary.isNoneBlank.toString());
    }

    private IsNoneBlank(final IsNoneBlank isNoneBlank) {
        super(isNoneBlank);
    }

    @Override
    protected Value internalEvaluate(final Value... values) throws ExpressionEvaluationException {

        final String string = assertStringLiteral(values[0]).stringValue();

        return literal(StringUtils.isNoneBlank(string));
    }

    @Override
    public IsNoneBlank copy() {
        return new IsNoneBlank(this);
    }

    @Override
    public void accept(final ExpressionVisitor expressionVisitor) {
        expressionVisitor.visit(this);
    }

    @Override
    public String toString() {
        return ComparisonVocabulary.isNoneBlank.name();
    }
}
