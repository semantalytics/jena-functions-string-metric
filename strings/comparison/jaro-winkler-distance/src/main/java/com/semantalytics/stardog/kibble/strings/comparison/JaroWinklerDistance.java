package com.semantalytics.stardog.kibble.strings.comparison;

import com.complexible.stardog.plan.filter.ExpressionEvaluationException;
import com.complexible.stardog.plan.filter.ExpressionVisitor;
import com.complexible.stardog.plan.filter.functions.AbstractFunction;
import com.complexible.stardog.plan.filter.functions.Function;
import com.complexible.stardog.plan.filter.functions.string.StringFunction;
import com.google.common.collect.Range;
import org.openrdf.model.Value;

import static com.complexible.common.rdf.model.Values.literal;

public final class JaroWinklerDistance extends AbstractFunction implements StringFunction {

    private info.debatty.java.stringsimilarity.JaroWinkler jaroWinkler;

    protected JaroWinklerDistance() {
        super(Range.closed(2, 3), StringComparisonVocabulary.jaroWinklerDistance.stringValue());
    }

    private JaroWinklerDistance(final JaroWinklerDistance jaroWinklerDistance) {
        super(jaroWinklerDistance);
    }

    @Override
    protected Value internalEvaluate(final Value... values) throws ExpressionEvaluationException {

        final String firstString = assertStringLiteral(values[0]).stringValue();
        final String secondString = assertStringLiteral(values[1]).stringValue();

        if(values.length == 3) {
            final double threshold = assertNumericLiteral(values[3]).doubleValue();
            jaroWinkler = new info.debatty.java.stringsimilarity.JaroWinkler(threshold);
        } else {
            jaroWinkler = new info.debatty.java.stringsimilarity.JaroWinkler();
        }

        return literal(jaroWinkler.distance(firstString, secondString));
    }

    public Function copy() {
        return new JaroWinklerDistance(this);
    }

    public void accept(final ExpressionVisitor expressionVisitor) {
        expressionVisitor.visit(this);
    }

    @Override
    public String toString() {
        return StringComparisonVocabulary.jaroWinklerDistance.name();
    }
}
