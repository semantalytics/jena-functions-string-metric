package com.semantalytics.stardog.kibble.strings.string;

import com.complexible.stardog.plan.filter.ExpressionEvaluationException;
import com.complexible.stardog.plan.filter.ExpressionVisitor;
import com.complexible.stardog.plan.filter.functions.AbstractFunction;
import com.complexible.stardog.plan.filter.functions.string.StringFunction;
import org.apache.commons.lang3.StringUtils;
import org.openrdf.model.Value;

import static com.complexible.common.rdf.model.Values.*;

public final class IndexOfAnyBut extends AbstractFunction implements StringFunction {

    protected IndexOfAnyBut() {
        super(2, StringVocabulary.indexOfAnyBut.stringValue());
    }

    private IndexOfAnyBut(final IndexOfAnyBut indexOfAnyBut) {
        super(indexOfAnyBut);
    }

    @Override
    protected Value internalEvaluate(final Value... values) throws ExpressionEvaluationException {

        final String string = assertStringLiteral(values[0]).stringValue();
        final String searchChars = assertStringLiteral(values[1]).stringValue();

        return literal(StringUtils.indexOfAnyBut(string, searchChars));
    }

    @Override
    public IndexOfAnyBut copy() {
        return new IndexOfAnyBut(this);
    }

    @Override
    public void accept(final ExpressionVisitor expressionVisitor) {
        expressionVisitor.visit(this);
    }

    @Override
    public String toString() {
        return StringVocabulary.indexOfAnyBut.name();
    }
}
