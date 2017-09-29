package com.semantalytics.stardog.function.util;

import com.complexible.common.rdf.model.Values;
import com.complexible.stardog.plan.filter.ExpressionEvaluationException;
import com.complexible.stardog.plan.filter.ExpressionVisitor;
import com.complexible.stardog.plan.filter.functions.AbstractFunction;
import com.complexible.stardog.plan.filter.functions.UserDefinedFunction;
import com.joestelmach.natty.DateGroup;
import com.joestelmach.natty.Parser;
import org.openrdf.model.Value;
import org.ocpsoft.prettytime.PrettyTime;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

public class ToSpokenTime extends AbstractFunction implements UserDefinedFunction {

    private static final prettyTime prettyTime = new PrettyTime();

    public SayTime() {
        super(1, "http://semantalytics.com/2016/03/ns/stardog/udf/util/toSpokenTime");
    }

    private ToSpokenTime(final ToSpokenTime toSpokenTime) {
        super(toSpokenTime);
    }

    @Override
    protected Value internalEvaluate(Value... values) throws ExpressionEvaluationException {
        final Date date = new Date(assertLiteral(values[0]).calendarValue().getMillisecond());
        return literal(prettyTime.format(date));
    }

    @Override
    public SayTime copy() {
        return new ToSpokenTime(this);
    }

    @Override
    public void accept(final ExpressionVisitor expressionVisitor) {
        expressionVisitor.visit(this);
    }

    @Override
    public String toString() {
        return "toSpokenTime";
    }
}
