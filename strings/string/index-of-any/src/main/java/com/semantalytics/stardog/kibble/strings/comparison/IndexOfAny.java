package com.semantalytics.stardog.kibble.strings.comparison;

import com.complexible.common.rdf.model.Values;
import com.complexible.stardog.plan.filter.ExpressionEvaluationException;
import com.complexible.stardog.plan.filter.ExpressionVisitor;
import com.complexible.stardog.plan.filter.functions.AbstractFunction;
import com.complexible.stardog.plan.filter.functions.string.StringFunction;
import org.apache.commons.lang3.StringUtils;
import org.openrdf.model.Value;

public final class IndexOfAny extends AbstractFunction implements StringFunction {

    protected IndexOfAny() {
        super(1, StringVocabulary.indexOfAny.toString());
    }

    private IndexOfAny(final IndexOfAny indexOfAny) {
        super(indexOfAny);
    }

    @Override
    protected Value internalEvaluate(final Value... values) throws ExpressionEvaluationException {

        final String string = assertStringLiteral(values[0]).stringValue();
        final String searchChars = assertStringLiteral(values[1]).stringValue();


        //TODO handle multiple searchchars

        return Values.literal(StringUtils.indexOfAny(string, searchChars));
    }

    @Override
    public IndexOfAny copy() {
        return new IndexOfAny(this);
    }

    @Override
    public void accept(final ExpressionVisitor expressionVisitor) {
        expressionVisitor.visit(this);
    }

    @Override
    public String toString() {
        return StringVocabulary.indexOfAny.name();
    }
}
