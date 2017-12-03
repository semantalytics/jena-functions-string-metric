package com.semantalytics.stardog.kibble.geo.hash;

import com.complexible.common.rdf.model.Values;
import com.complexible.stardog.plan.filter.ExpressionEvaluationException;
import com.complexible.stardog.plan.filter.ExpressionVisitor;
import com.complexible.stardog.plan.filter.functions.AbstractFunction;
import com.complexible.stardog.plan.filter.functions.string.StringFunction;
import com.github.davidmoten.geo.GeoHash;
import org.openrdf.model.Value;


public final class Latitude extends AbstractFunction implements StringFunction {

    protected Latitude() {
        super(1, GeoHashVocabulary.latitude.iri.stringValue());
    }

    private Latitude(final Latitude caseFormat) {
        super(caseFormat);
    }

    @Override
    protected Value internalEvaluate(final Value... values) throws ExpressionEvaluationException {
        final String hash = assertLiteral(values[0]).stringValue();
        return Values.literal(GeoHash.decodeHash(hash).getLat());
    }

    @Override
    public Latitude copy() {
        return new Latitude(this);
    }

    @Override
    public void accept(final ExpressionVisitor expressionVisitor) {
        expressionVisitor.visit(this);
    }

    @Override
    public String toString() {
        return GeoHashVocabulary.latitude.name();
    }

}
