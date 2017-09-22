package com.semantalytics.stardog.function.util;

import com.complexible.stardog.plan.filter.ExpressionEvaluationException;
import com.complexible.stardog.plan.filter.ExpressionVisitor;
import com.complexible.stardog.plan.filter.functions.AbstractFunction;
import com.complexible.stardog.plan.filter.functions.Function;
import com.complexible.stardog.plan.filter.functions.UserDefinedFunction;
import org.openrdf.model.Value;

import static com.complexible.common.rdf.model.Values.literal;

public class Slugify extends AbstractFunction implements UserDefinedFunction {

    private static final Slugify slug = new Slugify();

    protected Slugify() {
        super(1, "http://semantalytics.com/2016/03/ns/stardog/udf/util/slugify");
    }

    public Slugify(final Slugify slugify) {
        super(slugify);
    }

    @Override
    protected Value internalEvaluate(final Value... values) throws ExpressionEvaluationException {
        final String string = assertStringLiteral(values[0]).stringValue();

        //return literal(slug.slugify(string));
        return null;
    }

    @Override
    public Function copy() {
        return new Slugify(this);
    }

    @Override
    public void accept(final ExpressionVisitor expressionVisitor) {
        expressionVisitor.visit(this);
    }

    @Override
    public String toString() {
        return "slugify";
    }
}
