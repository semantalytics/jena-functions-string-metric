package com.semantalytics.stardog.kibble.geo;

import com.complexible.common.rdf.model.Values;
import com.complexible.stardog.plan.filter.ExpressionEvaluationException;
import com.complexible.stardog.plan.filter.ExpressionVisitor;
import com.complexible.stardog.plan.filter.functions.AbstractFunction;
import com.complexible.stardog.plan.filter.functions.string.StringFunction;
import com.github.davidmoten.geo.GeoHash;
import org.openrdf.model.Value;

public final class Decode extends AbstractFunction implements StringFunction {

    protected Decode() {
        super(1, GeoVocabulary.decode.iri.stringValue());
    }

    private Decode(final Decode decode) {
        super(decode);
    }

    @Override
    protected Value internalEvaluate(final Value... values) throws ExpressionEvaluationException {
        assertLiteral(values[0]);
        return Values.literal(GeoHash.decodeHash(values[0].stringValue()).getLat());
    }

    @Override
    public Decode copy() {
        return new Decode(this);
    }

    @Override
    public void accept(final ExpressionVisitor expressionVisitor) {
        expressionVisitor.visit(this);
    }

    @Override
    public String toString() {
        return GeoVocabulary.decode.name();
    }

}
