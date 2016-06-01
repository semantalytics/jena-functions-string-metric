package com.semantalytics.stardog.function.util;

import com.complexible.common.rdf.model.StardogValueFactory;
import com.complexible.common.rdf.model.Values;
import com.complexible.stardog.plan.filter.ExpressionEvaluationException;
import com.complexible.stardog.plan.filter.ExpressionVisitor;
import com.complexible.stardog.plan.filter.functions.AbstractFunction;
import com.complexible.stardog.plan.filter.functions.UserDefinedFunction;
import com.complexible.stardog.plan.filter.functions.datetime.CalendarUtils;
import com.complexible.stardog.plan.filter.functions.datetime.Now;
import com.complexible.stardog.plan.filter.functions.datetime.SubtractDates;
import com.joestelmach.natty.DateGroup;
import com.joestelmach.natty.Parser;
import org.openrdf.model.Literal;
import org.openrdf.model.Value;

import javax.xml.datatype.XMLGregorianCalendar;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import static com.complexible.stardog.plan.filter.functions.datetime.CalendarUtils.assertCalendarLiteral;

public class TimeFromNow extends AbstractFunction implements UserDefinedFunction {

    private static final Parser parser = new Parser();

    public TimeFromNow() {
        super(2, "http://semantalytics.com/2016/03/ns/stardog/udf/util/timeFromNow");
    }

    private TimeFromNow(final TimeFromNow spokenTime) {
        super(spokenTime);
    }

    @Override
    protected Value internalEvaluate(Value... values) throws ExpressionEvaluationException {
        assertStringLiteral(values[0]);
        assertTypedLiteral(values[1], StardogValueFactory.XSD.DATETIME);
        //TODO check second argument for date
        List<DateGroup> dateGroups = parser.parse(values[0].stringValue());
        if(dateGroups.isEmpty()) {
            throw new ExpressionEvaluationException("Unable to understand any dates from " + values[0].stringValue());
        }
        for(final DateGroup dateGroup : dateGroups) {
            if (dateGroup.getDates().size() != 1) {
                throw new ExpressionEvaluationException("Understood more than one date");
            } else {
                    XMLGregorianCalendar aSecondDate = assertCalendarLiteral(assertLiteral(values[1]));

                    if(dateGroup.getDates().get(0).getTime() - aSecondDate.toGregorianCalendar().getTimeInMillis() < 0) {
                        return  Values.literal(true);
                    } else {
                        return Values.literal(false);
                    }
            }

        }
        return null;
    }

    @Override
    public TimeFromNow copy() {
        return new TimeFromNow(this);
    }

    @Override
    public void accept(final ExpressionVisitor expressionVisitor) {
        expressionVisitor.visit(this);
    }

    @Override
    public String toString() {
        return "timeFromNow";
    }
}
