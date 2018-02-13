package com.semantalytics.stardog.kibble.strings.string;

import com.complexible.common.rdf.model.Values;
import com.complexible.stardog.plan.filter.ExpressionEvaluationException;
import com.complexible.stardog.plan.filter.ExpressionVisitor;
import com.complexible.stardog.plan.filter.functions.AbstractFunction;
import com.complexible.stardog.plan.filter.functions.string.StringFunction;
import com.google.common.base.Joiner;
import org.apache.commons.lang3.StringUtils;
import org.openrdf.model.Value;

import java.util.Arrays;
import java.util.List;
import com.google.common.collect.Range;

import static com.complexible.common.rdf.model.Values.literal;
import static java.util.stream.Collectors.toList;

public final class JoinWith extends AbstractFunction implements StringFunction {

    protected JoinWith() {
        super(Range.atLeast(1), StringVocabulary.joinWith.stringValue());
    }

    private JoinWith(final JoinWith join) {
        super(join);
    }

    @Override
    protected Value internalEvaluate(final Value... values) throws ExpressionEvaluationException {

        for (final Value value : values) {
            assertStringLiteral(value);
        }

        final String separator = assertStringLiteral(values[0]).stringValue();
        final List<String> pieces = Arrays.stream(values).skip(1).map(Value::stringValue).collect(toList());

        return literal(StringUtils.joinWith(separator, pieces));
    }

    @Override
    public JoinWith copy() {
        return new JoinWith(this);
    }

    @Override
    public void accept(final ExpressionVisitor expressionVisitor) {
        expressionVisitor.visit(this);
    }

    @Override
    public String toString() {
        return StringVocabulary.join.name();
    }
}
