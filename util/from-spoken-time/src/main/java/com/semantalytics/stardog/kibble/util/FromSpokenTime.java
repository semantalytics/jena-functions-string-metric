package com.semantalytics.stardog.kibble.util;

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

public class FromSpokenTime extends AbstractFunction implements UserDefinedFunction {

    private static final PrettyTimeParser parser = new PrettyTimeParser();

    public FromSpokenTime() {
        super(1, "http://semantalytics.com/2016/03/ns/stardog/udf/util/fromSpokenTime");
    }

    private FromSpokenTime(final FromSpokenTime fromSpokenTime) {
        super(fromSpokenTime);
    }

    @Override
    protected Value internalEvaluate(Value... values) throws ExpressionEvaluationException {
        
        final String time = assertStringLiteral(values[0]);
        final List<Date> dates = parser.parse(time);
        
        if(dates.isEmpty() || dates.size() > 1) {
            throw new ExpressionEvaluationException("Only a single date can be returned. Found " + dates.size());
        }
            return literal(dates[0]);
    }

    @Override
    public FromSpokenTime copy() {
        return new FromSpokenTime(this);
    }

    @Override
    public void accept(final ExpressionVisitor expressionVisitor) {
        expressionVisitor.visit(this);
    }

    @Override
    public String toString() {
        return "fromSpokenTime";
    }
}
