package com.semantalytics.stardog.kibble.strings.string;

import com.complexible.stardog.plan.filter.ExpressionEvaluationException;
import com.complexible.stardog.plan.filter.ExpressionVisitor;
import com.complexible.stardog.plan.filter.functions.AbstractFunction;
import com.complexible.stardog.plan.filter.functions.string.StringFunction;
import com.google.common.collect.Range;
import org.apache.commons.lang3.StringUtils;
import org.openrdf.model.Value;

import java.util.Arrays;

import static com.complexible.common.rdf.model.Values.literal;

public final class EqualsAny extends AbstractFunction implements StringFunction {

    protected EqualsAny() {
        super(Range.atLeast(2), StringVocabulary.equalsAny.stringValue());
    }

    private EqualsAny(final EqualsAny equalsAny) {
        super(equalsAny);
    }

    @Override
    protected Value internalEvaluate(final Value... values) throws ExpressionEvaluationException {

        for(final Value value : values) {
            assertStringLiteral(value);
        }

        final String string = assertStringLiteral(values[0]).stringValue();
        final String[] searchStrings = Arrays.stream(values).skip(1).map(Value::stringValue).toArray(String[]::new);

        return literal(StringUtils.equalsAny(string, searchStrings));
    }

    @Override
    public EqualsAny copy() {
        return new EqualsAny(this);
    }

    @Override
    public void accept(final ExpressionVisitor expressionVisitor) {
        expressionVisitor.visit(this);
    }

    @Override
    public String toString() {
        return StringVocabulary.equalsAny.name();
    }
}
