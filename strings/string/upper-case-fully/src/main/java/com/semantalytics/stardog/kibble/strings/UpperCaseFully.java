package com.semantalytics.stardog.kibble.strings;

import com.complexible.stardog.plan.filter.ExpressionEvaluationException;
import com.complexible.stardog.plan.filter.ExpressionVisitor;
import com.complexible.stardog.plan.filter.functions.AbstractFunction;
import com.complexible.stardog.plan.filter.functions.UserDefinedFunction;
import com.semantalytics.stardog.kibble.strings.string.StringVocabulary;
import org.openrdf.model.Value;

import static com.complexible.common.rdf.model.Values.literal;

public class UpperCaseFully extends AbstractFunction implements UserDefinedFunction {

    public UpperCaseFully() {
        super(1, StringVocabulary.upperCaseFully.stringValue());
    }

    public UpperCaseFully(final UpperCaseFully upperCaseFully) {
        super(upperCaseFully);
    }

    @Override
    protected Value internalEvaluate(final Value... values) throws ExpressionEvaluationException {

        final String string = assertStringLiteral(values[0]).stringValue();

        return literal(string.toUpperCase());
    }

    @Override
    public UpperCaseFully copy() {
        return new UpperCaseFully(this);
    }

    @Override
    public void accept(final ExpressionVisitor expressionVisitor) {
        expressionVisitor.visit(this);
    }

    @Override
    public String toString() {
        return StringVocabulary.upperCaseFully.name();
    }
}
