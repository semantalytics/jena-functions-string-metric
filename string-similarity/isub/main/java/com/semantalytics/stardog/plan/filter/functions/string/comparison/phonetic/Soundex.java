package com.semantalytics.stardog.plan.filter.functions.string.comparison.phonetic;

import com.complexible.stardog.plan.filter.ExpressionEvaluationException;
import com.complexible.stardog.plan.filter.ExpressionVisitor;
import com.complexible.stardog.plan.filter.functions.AbstractFunction;
import com.complexible.stardog.plan.filter.functions.Function;
import com.complexible.stardog.plan.filter.functions.string.StringFunction;
import com.semantalytics.stardog.plan.filter.functions.string.comparison.StringSimilarityVocab;
import org.openrdf.model.Value;

import static com.complexible.common.rdf.model.Values.literal;
import static org.apache.commons.codec.language.Soundex.*;

public final class Soundex extends AbstractFunction implements StringFunction {

    protected Soundex() {
        super(1, StringSimilarityVocab.SOUNDEX.iri().stringValue());
    }

    private Soundex(final Soundex soundex) {
        super(soundex);
    }

    @Override
    protected Value internalEvaluate(final Value... values) throws ExpressionEvaluationException {

        final String string = assertStringLiteral(values[0]).stringValue();
        //TODO check language
        return literal(US_ENGLISH.soundex(string));
    }

    public Function copy() {
        return new Soundex(this);
    }

    public void accept(final ExpressionVisitor expressionVisitor) {
        expressionVisitor.visit(this);
    }

    @Override
    public String toString() {
        return "soundex";
    }
}
