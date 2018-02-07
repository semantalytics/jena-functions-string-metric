package com.semantalytics.stardog.kibble.strings.string;

import com.complexible.common.rdf.model.Values;
import com.complexible.stardog.plan.filter.ExpressionEvaluationException;
import com.complexible.stardog.plan.filter.ExpressionVisitor;
import com.complexible.stardog.plan.filter.functions.AbstractFunction;
import com.complexible.stardog.plan.filter.functions.string.StringFunction;
import org.apache.commons.lang3.StringUtils;
import org.openrdf.model.Value;

public final class IndexOfAnyStringBut extends AbstractFunction implements StringFunction {

    protected IndexOfAnyStringBut() {
        super(1, StringVocabulary.indexOfAnyStringBut.stringValue());
    }

    private IndexOfAnyStringBut(final IndexOfAnyStringBut indexOfAnyStringBut) {
        super(indexOfAnyStringBut);
    }

    @Override
    protected Value internalEvaluate(final Value... values) throws ExpressionEvaluationException {

        final String string = assertStringLiteral(values[0]).stringValue();
        final String searchChars = assertStringLiteral(values[1]).stringValue();



        //TODO handle multiple searchchars

        return Values.literal(StringUtils.indexOfAnyBut(string, searchChars));
    }

    @Override
    public IndexOfAnyStringBut copy() {
        return new IndexOfAnyStringBut(this);
    }

    @Override
    public void accept(final ExpressionVisitor expressionVisitor) {
        expressionVisitor.visit(this);
    }

    @Override
    public String toString() {
        return StringVocabulary.indexOfAnyStringBut.name();
    }
}
