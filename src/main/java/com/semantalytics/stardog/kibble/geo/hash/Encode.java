package com.semantalytics.stardog.kibble.geo.hash;

import com.complexible.common.rdf.model.Values;
import com.complexible.stardog.plan.filter.ExpressionEvaluationException;
import com.complexible.stardog.plan.filter.ExpressionVisitor;
import com.complexible.stardog.plan.filter.functions.AbstractFunction;
import com.complexible.stardog.plan.filter.functions.Function;
import com.complexible.stardog.plan.filter.functions.string.StringFunction;
import com.github.davidmoten.geo.GeoHash;
import org.openrdf.model.Value;


public final class Encode extends AbstractFunction implements StringFunction {

    protected Encode() {
        super(2, GeoHashVocabulary.encode.stringValue());
    }

    private Encode(final Encode caseFormat) {
        super(caseFormat);
    }

    @Override
    protected Value internalEvaluate(final Value... values) throws ExpressionEvaluationException {
        return Values.literal(GeoHash.encodeHash(Double.valueOf(values[0].stringValue()), Double.valueOf(values[1].stringValue())));
    }

    @Override
    public Function copy() {
        return new Encode(this);
    }

    @Override
    public void accept(final ExpressionVisitor expressionVisitor) {
        expressionVisitor.visit(this);
    }

    @Override
    public String toString() {
        return GeoHashVocabulary.encode.name();
    }

}
