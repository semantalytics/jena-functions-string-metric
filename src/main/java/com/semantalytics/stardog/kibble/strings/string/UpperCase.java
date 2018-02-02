package com.semantalytics.stardog.kibble.strings.string;

import com.complexible.stardog.plan.filter.ExpressionEvaluationException;
import com.complexible.stardog.plan.filter.ExpressionVisitor;
import com.complexible.stardog.plan.filter.functions.AbstractFunction;
import com.complexible.stardog.plan.filter.functions.UserDefinedFunction;
import org.openrdf.model.Value;

import static com.complexible.common.rdf.model.Values.literal;

public class UpperCase extends AbstractFunction implements UserDefinedFunction {

    public UpperCase() {
        super(1, StringVocabulary.upperCase.stringValue());
    }

    public UpperCase(final UpperCase upperCase) {
        super(upperCase);
    }

    @Override
    protected Value internalEvaluate(final Value... values) throws ExpressionEvaluationException {

        final String string = assertStringLiteral(values[0]).stringValue();

        return literal(StringUtils.upperCase());
    }

    @Override
    public UpperCasee copy() {
        return new UpperCaseFully(this);
    }

    @Override
    public void accept(final ExpressionVisitor expressionVisitor) {
        expressionVisitor.visit(this);
    }

    @Override
    public String toString() {
        return StringVocabulary.upperCase.name();
    }
}
