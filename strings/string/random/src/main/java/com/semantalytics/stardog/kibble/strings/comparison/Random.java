package com.semantalytics.stardog.kibble.strings.comparison;

import com.complexible.common.rdf.model.Values;
import com.complexible.stardog.plan.filter.ExpressionEvaluationException;
import com.complexible.stardog.plan.filter.ExpressionVisitor;
import com.complexible.stardog.plan.filter.functions.AbstractFunction;
import com.complexible.stardog.plan.filter.functions.string.StringFunction;
import org.apache.commons.lang3.RandomStringUtils;
import org.openrdf.model.Value;

public final class Random extends AbstractFunction implements StringFunction {

    protected Random() {
        super(2, StringVocabulary.random.toString());
    }

    private Random(final Random random) {
        super(random);
    }

    @Override
    protected Value internalEvaluate(final Value... values) throws ExpressionEvaluationException {

      final int count = assertStringLiteral(values[0]).intValue();
      final String chars = assertStringLiteral(values[1]).stringValue();

      return Values.literal(RandomStringUtils.random(count, chars.toCharArray()));
    }

    @Override
    public Random copy() {
        return new Random(this);
    }

    @Override
    public void accept(final ExpressionVisitor expressionVisitor) {
        expressionVisitor.visit(this);
    }

    @Override
    public String toString() {
        return StringVocabulary.random.name();
    }
}
