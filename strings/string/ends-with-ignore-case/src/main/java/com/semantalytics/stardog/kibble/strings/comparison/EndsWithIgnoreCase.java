package com.semantalytics.stardog.kibble.strings.comparison;

import com.complexible.common.rdf.model.Values;
import com.complexible.stardog.plan.filter.ExpressionEvaluationException;
import com.complexible.stardog.plan.filter.ExpressionVisitor;
import com.complexible.stardog.plan.filter.functions.AbstractFunction;
import com.complexible.stardog.plan.filter.functions.string.StringFunction;
import com.google.common.collect.Range;
import org.apache.commons.lang3.StringUtils;
import org.openrdf.model.Value;

public final class EndsWithIgnoreCase extends AbstractFunction implements StringFunction {

    protected EndsWithIgnoreCase() {
        super(Range.closed(2, 3), StringVocabulary.endsWithIgnoreCase.toString());
    }

    private EndsWithIgnoreCase(final EndsWithIgnoreCase endsWithIgnoreCase) {
        super(endsWithIgnoreCase);
    }

    @Override
    protected Value internalEvaluate(final Value... values) throws ExpressionEvaluationException {

        final String string = assertStringLiteral(values[0]).stringValue();
        final String suffix = assertStringLiteral(values[1]).stringValue();

        //TODO include endsWithIgnoreCaseAny


        return Values.literal(StringUtils.endsWithIgnoreCase(string, suffix));

    }

    @Override
    public EndsWithIgnoreCase copy() {
        return new EndsWithIgnoreCase(this);
    }

    @Override
    public void accept(final ExpressionVisitor expressionVisitor) {
        expressionVisitor.visit(this);
    }

    @Override
    public String toString() {
        return StringVocabulary.endsWithIgnoreCase.name();
    }
}
