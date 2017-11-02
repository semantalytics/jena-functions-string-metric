package com.semantalytics.stardog.kibble.strings.comparison;

import com.complexible.stardog.plan.filter.ExpressionEvaluationException;
import com.complexible.stardog.plan.filter.ExpressionVisitor;
import com.complexible.stardog.plan.filter.functions.AbstractFunction;
import com.complexible.stardog.plan.filter.functions.string.StringFunction;
import org.apache.commons.lang3.StringUtils;
import org.openrdf.model.Value;

import static com.complexible.common.rdf.model.Values.*;

public final class OrdinalIndexOf extends AbstractFunction implements StringFunction {

    protected OrdinalIndexOf() {
        super(1, ComparisonVocabulary.ordinalIndexOf.toString());
    }

    private OrdinalIndexOf(final OrdinalIndexOf ordinalIndexOf) {
        super(ordinalIndexOf);
    }

    @Override
    protected Value internalEvaluate(final Value... values) throws ExpressionEvaluationException {

        final String string = assertStringLiteral(values[0]).stringValue();

        return literal(StringUtils.ordinalIndexOf(string));
    }

    @Override
    public OrdinalIndexOf copy() {
        return new OrdinalIndexOf(this);
    }

    @Override
    public void accept(final ExpressionVisitor expressionVisitor) {
        expressionVisitor.visit(this);
    }

    @Override
    public String toString() {
        return ComparisonVocabulary.ordinalIndexOf.name();
    }
}
