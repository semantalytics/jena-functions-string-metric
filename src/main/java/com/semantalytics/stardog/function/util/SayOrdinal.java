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

public class SayOrdinal extends AbstractFunction implements UserDefinedFunction {

    public SayOrdinal() {
        super(1, "http://semantalytics.com/2016/03/ns/stardog/udf/util/sayTime");
    }

    private SayOrdinal(final SayOrdinal sayOrdinal) {
        super(sayOrdinal);
    }

    @Override
    protected Value internalEvaluate(Value... values) throws ExpressionEvaluationException {
        int ordinal = assertIntegerLiteral(values[0]).intValue();
        
        RuleBasedNumberFormat nf = new RuleBasedNumberFormat(Locale.US, RuleBasedNumberFormat.SPELLOUT);



        return Values.literal(nf.format(ordinal, "%spellout-ordinal"));
    }

    @Override
    public SayOrdinal copy() {
        return new SayOrdinal(this);
    }

    @Override
    public void accept(final ExpressionVisitor expressionVisitor) {
        expressionVisitor.visit(this);
    }

    @Override
    public String toString() {
        return "Convert ordinal integer into spoken equivalent";
    }
}
