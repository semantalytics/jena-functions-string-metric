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

public final class CosineSimilarity extends AbstractFunction implements StringFunction {

    private info.debatty.java.stringsimilarity.Cosine cosine;

    protected CosineSimilarity() {
        super(Range.closed(2, 3), StringMetricVocabulary.cosineSimilarity.stringValue());
    }

    private CosineSimilarity(final CosineSimilarity cosineSimilarity) {
        super(cosineSimilarity);
        this.cosine = cosineSimilarity.cosine;
    }

    public info.debatty.java.stringsimilarity.Cosine getCosineFunction(final Value... values) throws ExpressionEvaluationException {
        if(cosine == null) {
            if (values.length == 3) {
                 assertNumericLiteral(values[2]);
                 if(!(getThirdArg() instanceof Constant)) {
                    throw new ExpressionEvaluationException("Parameter must be constant expression");
                }
                final int n = assertNumericLiteral(values[2]).intValue();
                cosine = new info.debatty.java.stringsimilarity.Cosine(n);
            } else {
                cosine = new info.debatty.java.stringsimilarity.Cosine();
            }
        }
        return cosine;
    }

    @Override
    public void initialize() {
        cosine = null;
    }

    @Override
    protected Value internalEvaluate(final Value... values) throws ExpressionEvaluationException {

        final String string1 = assertStringLiteral(values[0]).stringValue();
        final String string2 = assertStringLiteral(values[1]).stringValue();

        return literal(getCosineFunction(values).similarity(string1, string2));
    }

    @Override
    public Function copy() {
        return new CosineSimilarity(this);
    }

    @Override
    public void accept(final ExpressionVisitor expressionVisitor) {
        expressionVisitor.visit(this);
    }

    @Override
    public String toString() {
        return StringMetricVocabulary.cosineSimilarity.name();
    }
}
