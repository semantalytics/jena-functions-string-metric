package com.semantalytics.stardog.kibble.strings.comparison;

import com.complexible.common.rdf.model.Values;
import com.complexible.stardog.plan.filter.ExpressionEvaluationException;
import com.complexible.stardog.plan.filter.ExpressionVisitor;
import com.complexible.stardog.plan.filter.functions.AbstractFunction;
import com.complexible.stardog.plan.filter.functions.string.StringFunction;
import com.google.common.collect.Range;
import org.apache.commons.lang3.StringUtils;
import org.openrdf.model.Value;

public final class EqualsIgnoreCase extends AbstractFunction implements StringFunction {

    protected EqualsIgnoreCase() {
        super(Range.closed(2, 3), ComparisonVocabulary.equalsIgnoreCase.toString());
    }

    private EqualsIgnoreCase(final EqualsIgnoreCase equalsIgnoreCase) {
        super(equalsIgnoreCase);
    }

    @Override
    protected Value internalEvaluate(final Value... values) throws ExpressionEvaluationException {

        final String string = assertStringLiteral(values[0]).stringValue();
        final String suffix = assertStringLiteral(values[1]).stringValue();

        //TODO include equalsIgnoreCaseAny


        return Values.literal(StringUtils.equalsIgnoreCase(string, suffix));

    }

    @Override
    public EqualsIgnoreCase copy() {
        return new EqualsIgnoreCase(this);
    }

    @Override
    public void accept(final ExpressionVisitor expressionVisitor) {
        expressionVisitor.visit(this);
    }

    @Override
    public String toString() {
        return ComparisonVocabulary.equalsIgnoreCase.name();
    }
}
