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

public class ListenTime extends AbstractFunction implements UserDefinedFunction {

    private static final Parser parser = new Parser();

    public SayTime() {
        super(1, "http://semantalytics.com/2016/03/ns/stardog/udf/util/listenTime");
    }

    private ListenTime(final ListenTime listenTime) {
        super(listenTime);
    }

    @Override
    protected Value internalEvaluate(Value... values) throws ExpressionEvaluationException {
        assertStringLiteral(values[0]);
        List<DateGroup> dateGroups = parser.parse(values[0].stringValue());
        if(dateGroups.isEmpty()) {
            throw new ExpressionEvaluationException("Unable to understand any dates from " + values[0].stringValue());
        }
        for(final DateGroup dateGroup : dateGroups) {
            if (dateGroup.getDates().size() != 1) {
                throw new ExpressionEvaluationException("Understood more than one date");
            } else {
                Calendar calendar = GregorianCalendar.getInstance();
                calendar.setTimeInMillis(dateGroup.getDates().get(0).getTime());
                return Values.literal((GregorianCalendar) calendar);
            }

        }
        return null;
    }

    @Override
    public SayTime copy() {
        return new ListenTime(this);
    }

    @Override
    public void accept(final ExpressionVisitor expressionVisitor) {
        expressionVisitor.visit(this);
    }

    @Override
    public String toString() {
        return "listenTime";
    }
}
