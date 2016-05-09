package com.semantalytics.stardog.function.collections.util;

import com.complexible.common.rdf.model.Values;
import com.complexible.stardog.plan.filter.ExpressionEvaluationException;
import com.complexible.stardog.plan.filter.ExpressionVisitor;
import com.complexible.stardog.plan.filter.functions.AbstractFunction;
import com.complexible.stardog.plan.filter.functions.Function;
import com.complexible.stardog.plan.filter.functions.UserDefinedFunction;
import org.openrdf.model.Value;

import java.util.GregorianCalendar;

public class Sleep extends AbstractFunction implements UserDefinedFunction {

    protected Sleep() {
        super(1, "http://semantalytics.com/2016/03/ns/stardog/udf/util/sleep");
    }

    public Sleep(final Sleep sleep) {
        super(sleep);
    }

    @Override
    protected Value internalEvaluate(final Value... values) throws ExpressionEvaluationException {
        try {
            Thread.sleep(Long.parseLong(values[0].stringValue()));
        } catch(InterruptedException e) {
            throw new ExpressionEvaluationException(e);
        }
        return Values.literal((GregorianCalendar)GregorianCalendar.getInstance());
    }

    @Override
    public Function copy() {
        return new Sleep(this);
    }

    @Override
    public void accept(final ExpressionVisitor expressionVisitor) {
        expressionVisitor.visit(this);
    }

    @Override
    public String toString() {
        return "sleep";
    }
}
