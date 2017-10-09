package com.semantalytics.stardog.plan.filter.functions.geohash;

import com.complexible.common.rdf.model.Values;
import com.complexible.stardog.plan.filter.ExpressionEvaluationException;
import com.complexible.stardog.plan.filter.ExpressionVisitor;
import com.complexible.stardog.plan.filter.functions.AbstractFunction;
import com.complexible.stardog.plan.filter.functions.Function;
import com.complexible.stardog.plan.filter.functions.string.StringFunction;
import com.github.davidmoten.geo.GeoHash;
import org.openrdf.model.Value;


public final class Longitude extends AbstractFunction implements StringFunction {

    protected Longitude() {
        super(1, GeohashVocab.DECODE.iri().stringValue());
    }

    private Longitude(final Longitude caseFormat) {
        super(caseFormat);
    }

    @Override
    protected Value internalEvaluate(final Value... values) throws ExpressionEvaluationException {
        assertLiteral(values[0]);
        return Values.literal(GeoHash.decodeHash(values[0].stringValue()).getLon());
    }

    @Override
    public Function copy() {
        return new Longitude(this);
    }

    @Override
    public void accept(final ExpressionVisitor expressionVisitor) {
        expressionVisitor.visit(this);
    }

    @Override
    public String toString() {
        return "Latitude";
    }

}
