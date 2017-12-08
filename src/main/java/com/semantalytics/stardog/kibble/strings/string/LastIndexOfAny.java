package com.semantalytics.stardog.kibble.strings.string;

import com.complexible.stardog.plan.filter.ExpressionEvaluationException;
import com.complexible.stardog.plan.filter.ExpressionVisitor;
import com.complexible.stardog.plan.filter.functions.AbstractFunction;
import com.complexible.stardog.plan.filter.functions.string.StringFunction;
import org.apache.commons.lang3.StringUtils;
import org.openrdf.model.Value;

import static com.complexible.common.rdf.model.Values.*;

public final class LastIndexOfAny extends AbstractFunction implements StringFunction {

    protected LastIndexOfAny() {
        super(1, StringVocabulary.lastIndexOfAny.toString());
    }

    private LastIndexOfAny(final LastIndexOfAny lastIndexOfAny) {
        super(lastIndexOfAny);
    }

    @Override
    protected Value internalEvaluate(final Value... values) throws ExpressionEvaluationException {

        final String string = assertStringLiteral(values[0]).stringValue();

        return literal(StringUtils.lastIndexOfAny(string));
    }

    @Override
    public LastIndexOfAny copy() {
        return new LastIndexOfAny(this);
    }

    @Override
    public void accept(final ExpressionVisitor expressionVisitor) {
        expressionVisitor.visit(this);
    }

    @Override
    public String toString() {
        return StringVocabulary.lastIndexOfAny.name();
    }
}
