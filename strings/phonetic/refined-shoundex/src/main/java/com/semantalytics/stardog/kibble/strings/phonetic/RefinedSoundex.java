package com.semantalytics.stardog.kibble.strings.phonetic;

import com.complexible.stardog.plan.filter.ExpressionEvaluationException;
import com.complexible.stardog.plan.filter.ExpressionVisitor;
import com.complexible.stardog.plan.filter.functions.AbstractFunction;
import com.complexible.stardog.plan.filter.functions.Function;
import com.complexible.stardog.plan.filter.functions.string.StringFunction;
import org.openrdf.model.Value;

import static com.complexible.common.rdf.model.Values.literal;

public class RefinedSoundex extends AbstractFunction implements StringFunction {

    private static final org.apache.commons.codec.language.RefinedSoundex refinedSoundex;

    static {
        refinedSoundex = new org.apache.commons.codec.language.RefinedSoundex();
    }

    protected RefinedSoundex() {
        super(1, PhoneticVocabulary.refinedSoundex.stringValue());
    }

    private RefinedSoundex(RefinedSoundex doubleMetaphone) {
        super(doubleMetaphone);
    }

    @Override
    public Function copy() {
        return new RefinedSoundex(this);
    }

    @Override
    public Value internalEvaluate(final Value... values) throws ExpressionEvaluationException {
        final String string = assertStringLiteral(values[0]).stringValue();

        //TODO check language

        return literal(refinedSoundex.soundex(string));
    }

    @Override
    public void accept(final ExpressionVisitor expressionVisitor) {
        expressionVisitor.visit(this);
    }

    @Override
    public String toString() {
        return PhoneticVocabulary.refinedSoundex.name();
    }
}
