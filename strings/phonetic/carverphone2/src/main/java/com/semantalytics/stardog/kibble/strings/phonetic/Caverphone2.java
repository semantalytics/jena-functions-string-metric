package com.semantalytics.stardog.kibble.strings.phonetic;

import com.complexible.stardog.plan.filter.ExpressionEvaluationException;
import com.complexible.stardog.plan.filter.ExpressionVisitor;
import com.complexible.stardog.plan.filter.functions.AbstractFunction;
import com.complexible.stardog.plan.filter.functions.Function;
import com.complexible.stardog.plan.filter.functions.string.StringFunction;
import org.openrdf.model.Value;

import static com.complexible.common.rdf.model.Values.*;

public final class Caverphone2 extends AbstractFunction implements StringFunction {

    private static final org.apache.commons.codec.language.Caverphone2 caverphone2;

    static {
        caverphone2 = new org.apache.commons.codec.language.Caverphone2();
    }

    protected Caverphone2() {
        super(1, PhoneticVocabulary.carverphone2.stringValue());
    }

    private Caverphone2(final Caverphone2 caverphone2) {
        super(caverphone2);
    }

    @Override
    protected Value internalEvaluate(final Value... values) throws ExpressionEvaluationException {
        final String string = assertStringLiteral(values[0]).stringValue();
        //TODO Checkj language tag for english. What do for string literal without language tag?
        return literal(caverphone2.encode(string));
    }

    @Override
    public void accept(final ExpressionVisitor expressionVisitor) {
        expressionVisitor.visit(this);
    }

    @Override
    public Function copy() {
        return new Caverphone2(this);
    }

    @Override
    public String toString() {
        return PhoneticVocabulary.carverphone2.name();
    }
}
