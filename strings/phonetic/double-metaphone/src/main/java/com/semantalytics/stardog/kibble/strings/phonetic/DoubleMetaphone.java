package com.semantalytics.stardog.kibble.strings.phonetic;

import com.complexible.stardog.plan.filter.ExpressionEvaluationException;
import com.complexible.stardog.plan.filter.ExpressionVisitor;
import com.complexible.stardog.plan.filter.functions.AbstractFunction;
import com.complexible.stardog.plan.filter.functions.Function;
import com.complexible.stardog.plan.filter.functions.string.StringFunction;
import org.openrdf.model.Value;

import static com.complexible.common.rdf.model.Values.literal;

public class DoubleMetaphone extends AbstractFunction implements StringFunction {

    private static final org.apache.commons.codec.language.DoubleMetaphone doubleMetaphone;

    static {
        doubleMetaphone = new org.apache.commons.codec.language.DoubleMetaphone();
    }

    protected DoubleMetaphone() {
        super(1, PhoneticVocabulary.doubleMetaphone.stringValue());
    }

    private DoubleMetaphone(DoubleMetaphone doubleMetaphone) {
        super(doubleMetaphone);
    }

    @Override
    public Function copy() {
        return new DoubleMetaphone(this);
    }

    @Override
    public Value internalEvaluate(final Value... values) throws ExpressionEvaluationException {
        final String string = assertStringLiteral(values[0]).stringValue();

        return literal(doubleMetaphone.doubleMetaphone(string));
    }

    @Override
    public void accept(final ExpressionVisitor expressionVisitor) {
        expressionVisitor.visit(this);
    }

    @Override
    public String toString() {
        return PhoneticVocabulary.doubleMetaphone.name();
    }
}
