package com.semantalytics.stardog.kibble.strings.comparison;

import com.complexible.stardog.plan.filter.ExpressionEvaluationException;
import com.complexible.stardog.plan.filter.ExpressionVisitor;
import com.complexible.stardog.plan.filter.expr.Constant;
import com.complexible.stardog.plan.filter.functions.AbstractFunction;
import com.complexible.stardog.plan.filter.functions.Function;
import com.complexible.stardog.plan.filter.functions.string.StringFunction;
import com.google.common.collect.Range;
import org.openrdf.model.Value;

import static com.complexible.common.rdf.model.Values.literal;

public final class Sift4 extends AbstractFunction implements StringFunction {

    private static final info.debatty.java.stringsimilarity.experimental.Sift4 sift4;

    static {
        sift4 = new info.debatty.java.stringsimilarity.experimental.Sift4();
    }

    protected Sift4() {
        super(Range.closed(2, 3), StringComparisonVocabulary.sift4.stringValue());
    }

    private Sift4(final Sift4 sift4) {
        super(sift4);
    }

    @Override
    protected Value internalEvaluate(final Value... values) throws ExpressionEvaluationException {

        final String string1 = assertStringLiteral(values[0]).stringValue();
        final String string2 = assertStringLiteral(values[1]).stringValue();
        final int maxOffset = assertNumericLiteral(values[2]).intValue();

        sift4.setMaxOffset(maxOffset);

        return literal(sift4.distance(string1, string2));
    }

    @Override
    public Sift4 copy() {
        return new Sift4(this);
    }

    @Override
    public void accept(final ExpressionVisitor expressionVisitor) {
        expressionVisitor.visit(this);
    }

    @Override
    public String toString() {
        return StringComparisonVocabulary.sift4.name();
    }
}
