package com.semantalytics.stardog.kibble.strings.comparison;

import com.complexible.stardog.plan.filter.ExpressionEvaluationException;
import com.complexible.stardog.plan.filter.ExpressionVisitor;
import com.complexible.stardog.plan.filter.functions.AbstractFunction;
import com.complexible.stardog.plan.filter.functions.Function;
import com.complexible.stardog.plan.filter.functions.string.StringFunction;
import com.google.common.collect.Range;
import org.openrdf.model.Value;

import static com.complexible.common.rdf.model.Values.literal;

public final class JaroWinkler extends AbstractFunction implements StringFunction {

    private static final info.debatty.java.stringsimilarity.JaroWinkler jaroWinkler;
    private static final double defaultThreshold;
    private char similarityOrDistance = 's';

    static {
        jaroWinkler = new info.debatty.java.stringsimilarity.JaroWinkler();
        defaultThreshold = jaroWinkler.getThreshold();
    }

    protected JaroWinkler() {
        super(Range.closed(2, 4), StringComparisonVocabulary.jaroWinkler.stringValue());
    }

    private JaroWinkler(final JaroWinkler jaroWinkler) {
        super(jaroWinkler);
    }

    @Override
    protected Value internalEvaluate(final Value... values) throws ExpressionEvaluationException {

        final String firstString = assertStringLiteral(values[0]).stringValue();
        final String secondString = assertStringLiteral(values[1]).stringValue();

        if(values.length >= 3) {
            similarityOrDistance = validateSimilarityOrDistanceParameter(values[2]);
        }

        if(values.length == 4) {
            final double threshold = assertNumericLiteral(values[3]).doubleValue();
            jaroWinkler.setThreshold(threshold);
        } else {
            jaroWinkler.setThreshold(defaultThreshold);
        }

        return literal(compare(firstString, secondString));
    }

    private double compare(final String firstString, final String secondString) throws ExpressionEvaluationException {

        switch(similarityOrDistance) {
            case 's': {
                return jaroWinkler.similarity(firstString, secondString);
            }
            case 'd': {
                return jaroWinkler.distance(firstString, secondString);
            }
            default:
                throw new ExpressionEvaluationException("Unrecognized parameter value '" + similarityOrDistance + "'. Acceptable values are 's' or 'd'");
        }

    }

    private char validateSimilarityOrDistanceParameter(final Value value) throws ExpressionEvaluationException {
        final String similarityOrDistance = assertStringLiteral(value).stringValue();
        if(similarityOrDistance.isEmpty()) {
            throw new ExpressionEvaluationException("No option found for similarity or distance. Acceptable values are 's' for similiarity or 'd' for distance");
        }
        if(similarityOrDistance.length() != 1) {
            throw new ExpressionEvaluationException("Expected a single character. Found " + similarityOrDistance);
        }
        if(!"s".equals(similarityOrDistance) && !"d".equals(similarityOrDistance)) {
            throw new ExpressionEvaluationException("Unrecognized option " + similarityOrDistance + ". Acceptable options are 's' for similarity or 'd' for distance");
        }
        return similarityOrDistance.charAt(0);
    }

    @Override
    public void initialize() {
        jaroWinkler.setThreshold(defaultThreshold);
    }

    public Function copy() {
        return new JaroWinkler(this);
    }

    public void accept(final ExpressionVisitor expressionVisitor) {
        expressionVisitor.visit(this);
    }

    @Override
    public String toString() {
        return StringComparisonVocabulary.jaroWinkler.name();
    }
}
