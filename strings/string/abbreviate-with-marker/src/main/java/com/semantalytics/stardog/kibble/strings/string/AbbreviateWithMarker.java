package com.semantalytics.stardog.kibble.strings.string;

import com.complexible.stardog.plan.filter.ExpressionEvaluationException;
import com.complexible.stardog.plan.filter.ExpressionVisitor;
import com.complexible.stardog.plan.filter.functions.AbstractFunction;
import com.complexible.stardog.plan.filter.functions.string.StringFunction;
import com.google.common.base.Strings;
import com.google.common.collect.Range;
import org.apache.commons.lang3.StringUtils;
import org.openrdf.model.Value;

import static com.complexible.common.rdf.model.Values.*;
import static com.google.common.base.Preconditions.checkArgument;

public final class AbbreviateWithMarker extends AbstractFunction implements StringFunction {

    protected AbbreviateWithMarker() {
        super(Range.closed(3, 4), StringVocabulary.abbreviateWithMarker.toString());
    }

    private AbbreviateWithMarker(final AbbreviateWithMarker abbreviateWithMarker) {
        super(abbreviateWithMarker);
    }

    @Override
    protected Value internalEvaluate(final Value... values) throws ExpressionEvaluationException {
      
      final String string = assertStringLiteral(values[0]).stringValue();
      final String abbrevMarker = assertStringLiteral(values[1]).stringValue();
      final int maxWidth = assertIntegerLiteral(values[2]).intValue();

      checkArgument(maxWidth > 3, "maxWidth must be greater than 3. Found " + maxWidth);

        switch(values.length) {
            case 3:
                return literal(StringUtils.abbreviate(string, abbrevMarker, maxWidth));
            case 4:
                final int offset = assertIntegerLiteral(values[3]).intValue();
                return literal(StringUtils.abbreviate(string, abbrevMarker, offset, maxWidth));
            default:
                throw new ExpressionEvaluationException("function takes 2 or 3 arguments. Found " + values.length);
        }
    }

    @Override
    public AbbreviateWithMarker copy() {
        return new AbbreviateWithMarker(this);
    }

    @Override
    public void accept(final ExpressionVisitor expressionVisitor) {
        expressionVisitor.visit(this);
    }

    @Override
    public String toString() {
        return StringVocabulary.abbreviateWithMarker.name();
    }
}
