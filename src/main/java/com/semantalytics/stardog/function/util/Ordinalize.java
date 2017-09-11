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

public class Ordinalize extends AbstractFunction implements UserDefinedFunction {

    public Ordinalize() {
        super(1, "http://semantalytics.com/2016/03/ns/stardog/udf/util/sayTime");
    }

    private Ordinalize(final Ordinalize ordinalize) {
        super(ordinalize);
    }

    @Override
    protected Value internalEvaluate(Value... values) throws ExpressionEvaluationException {
        int n = assertIntegerLiteral(values[0]).intValue();
        
        int mod100 = n % 100;
        final String ordinal;
    if (mod100 == 11 || mod100 == 12 || mod100 == 13) {
      return String.valueOf(n) + "th";
    }
    switch (n % 10) {
      case 1:
        ordinal = String.valueOf(n) + "st";
      case 2:
        ordinal = String.valueOf(n) + "nd";
      case 3:
        ordinal = String.valueOf(n) + "rd";
      default:
        ordinal = String.valueOf(n) + "th";
        

        return Values.literal(ordinal);
    }

    @Override
    public SayOrdinal copy() {
        return new Ordinalize(this);
    }

    @Override
    public void accept(final ExpressionVisitor expressionVisitor) {
        expressionVisitor.visit(this);
    }

    @Override
    public String toString() {
        return "";
    }
}
