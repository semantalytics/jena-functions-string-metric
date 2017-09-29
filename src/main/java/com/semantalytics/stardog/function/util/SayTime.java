package com.semantalytics.stardog.function.util;

import com.complexible.common.rdf.model.Values;
import com.complexible.stardog.plan.filter.ExpressionEvaluationException;
import com.complexible.stardog.plan.filter.ExpressionVisitor;
import com.complexible.stardog.plan.filter.functions.AbstractFunction;
import com.complexible.stardog.plan.filter.functions.UserDefinedFunction;
import com.joestelmach.natty.DateGroup;
import com.joestelmach.natty.Parser;
import org.openrdf.model.Value;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

public class SayTime extends AbstractFunction implements UserDefinedFunction {

    private static final Parser parser = new Parser();

    public SayTime() {
        super(1, "http://semantalytics.com/2016/03/ns/stardog/udf/util/sayTime");
    }

    private SayTime(final SayTime sayTime) {
        super(sayTime);
    }

    @Override
    protected Value internalEvaluate(Value... values) throws ExpressionEvaluationException {
        return null;
    }

    @Override
    public SayTime copy() {
        return new SayTime(this);
    }

    @Override
    public void accept(final ExpressionVisitor expressionVisitor) {
        expressionVisitor.visit(this);
    }

    @Override
    public String toString() {
        return "sayTime";
    }
}
