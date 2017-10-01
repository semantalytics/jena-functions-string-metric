package com.semantalytics.stardog.plan.filter.functions.string.comparison.phonetic;

import com.complexible.stardog.plan.filter.ExpressionEvaluationException;
import com.complexible.stardog.plan.filter.ExpressionVisitor;
import com.complexible.stardog.plan.filter.functions.AbstractFunction;
import com.complexible.stardog.plan.filter.functions.Function;
import com.complexible.stardog.plan.filter.functions.string.StringFunction;
import com.semantalytics.stardog.plan.filter.functions.string.comparison.StringSimilarityVocab;
import org.openrdf.model.Value;

import static com.complexible.common.rdf.model.Values.literal;

public class Metaphone extends AbstractFunction implements StringFunction {

    private static final org.apache.commons.codec.language.Metaphone metaphone;

    static {
        metaphone = new org.apache.commons.codec.language.Metaphone();
    }

    protected Metaphone() {
        super(1, StringSimilarityVocab.METAPHONE.iri().stringValue());
    }

    private Metaphone(Metaphone doubleMetaphone) {
        super(doubleMetaphone);
    }

    @Override
    public Function copy() {
        return new Metaphone(this);
    }

    @Override
    public Value internalEvaluate(final Value... values) throws ExpressionEvaluationException {
        final String string = assertStringLiteral(values[0]).stringValue();

        return literal(metaphone.metaphone(string));
    }

    @Override
    public void accept(final ExpressionVisitor expressionVisitor) {
        expressionVisitor.visit(this);
    }

    @Override
    public String toString() {
        return "metaphone";
    }
}
