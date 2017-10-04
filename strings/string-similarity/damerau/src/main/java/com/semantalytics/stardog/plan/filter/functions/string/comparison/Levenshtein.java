package com.semantalytics.stardog.plan.filter.functions.string.comparison;

import com.complexible.stardog.plan.filter.ExpressionEvaluationException;
import com.complexible.stardog.plan.filter.ExpressionVisitor;
import com.complexible.stardog.plan.filter.functions.AbstractFunction;
import com.complexible.stardog.plan.filter.functions.Function;
import com.complexible.stardog.plan.filter.functions.string.StringFunction;
import org.openrdf.model.Value;

import static com.complexible.common.rdf.model.Values.literal;

public final class Levenshtein extends AbstractFunction implements StringFunction {

    private static final info.debatty.java.stringsimilarity.Levenshtein levenshtein;

    static {
        levenshtein = new info.debatty.java.stringsimilarity.Levenshtein();
    }

    protected Levenshtein() {
        super(2, StringSimilarityVocab.LEVENSHTEIN.iri().stringValue());
    }

    private Levenshtein(final Levenshtein levenshtein) {
        super(levenshtein);
    }

    @Override
    protected Value internalEvaluate(final Value... values) throws ExpressionEvaluationException {

        assertStringLiteral(values[0]);
        assertStringLiteral(values[1]);

        return literal(levenshtein.distance(values[0].stringValue(), values[1].stringValue()));
    }

    public Function copy() {
        return new Levenshtein(this);
    }

    public void accept(final ExpressionVisitor expressionVisitor) {
        expressionVisitor.visit(this);
    }

    @Override
    public String toString() {
        return "Levenshtein";
    }
}
