package com.semantalytics.stardog.kibble.strings.string;

import com.complexible.stardog.plan.filter.ExpressionEvaluationException;
import com.complexible.stardog.plan.filter.ExpressionVisitor;
import com.complexible.stardog.plan.filter.functions.AbstractFunction;
import com.complexible.stardog.plan.filter.functions.string.StringFunction;
import com.google.common.collect.Range;
import org.apache.commons.lang3.StringUtils;
import org.openrdf.model.Value;

import static com.complexible.common.rdf.model.Values.*;

public class IndexOf extends AbstractFunction implements StringFunction {

    protected IndexOf() {
        super(Range.closed(2, 3), StringVocabulary.indexOf.stringValue());
    }

    private IndexOf(final IndexOf indexOf) {
        super(indexOf);
    }

    @Override
    protected Value internalEvaluate(final Value... values) throws ExpressionEvaluationException {

        final String sequence = assertStringLiteral(values[0]).stringValue();
        final String searchSequence = assertStringLiteral(values[1]).stringValue();

        switch(values.length) {
            case 2: {
                return literal(StringUtils.indexOf(sequence, searchSequence));
            }
            case 3: {
                final int startPosition = assertNumericLiteral(values[2]).intValue();

                return literal(StringUtils.indexOf(sequence, searchSequence, startPosition));
            }
            default: {
                throw new ExpressionEvaluationException("Expected 2 or 3 args. Found " + values.length);
            }
        }
    }

    @Override
    public IndexOf copy() {
        return new IndexOf(this);
    }

    @Override
    public void accept(final ExpressionVisitor expressionVisitor) {
        expressionVisitor.visit(this);
    }

    @Override
    public String toString() {
        return StringVocabulary.indexOf.name();
    }
}
