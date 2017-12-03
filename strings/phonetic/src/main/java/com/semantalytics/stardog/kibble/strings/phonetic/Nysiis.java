package com.semantalytics.stardog.kibble.strings.phonetic;

import com.complexible.stardog.plan.filter.ExpressionEvaluationException;
import com.complexible.stardog.plan.filter.ExpressionVisitor;
import com.complexible.stardog.plan.filter.functions.AbstractFunction;
import com.complexible.stardog.plan.filter.functions.Function;
import com.complexible.stardog.plan.filter.functions.string.StringFunction;
import org.openrdf.model.Value;

import static com.complexible.common.rdf.model.Values.literal;

public final class Nysiis extends AbstractFunction implements StringFunction {

    private static final org.apache.commons.codec.language.Nysiis nysiis;

    static {
        nysiis = new org.apache.commons.codec.language.Nysiis();
    }

    protected Nysiis() {
        super(1, PhoneticVocabulary.nysiis.stringValue());
    }

    private Nysiis(final Nysiis caverphone2) {
        super(caverphone2);
    }

    @Override
    protected Value internalEvaluate(final Value... values) throws ExpressionEvaluationException {
        final String string = assertStringLiteral(values[0]).stringValue();
        return literal(nysiis.encode(string));
    }

    @Override
    public void accept(final ExpressionVisitor expressionVisitor) {
        expressionVisitor.visit(this);
    }

    @Override
    public Function copy() {
        return new Nysiis(this);
    }

    @Override
    public String toString() {
        return PhoneticVocabulary.nysiis.name();
    }
}
