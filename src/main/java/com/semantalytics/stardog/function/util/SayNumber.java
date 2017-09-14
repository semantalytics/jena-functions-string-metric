
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

public class SayNumber extends AbstractFunction implements UserDefinedFunction {

    public SayNumber() {
        super(1, "http://semantalytics.com/2016/03/ns/stardog/udf/util/sayNumber");
    }

    private SayNumber(final SayNumber sayNumber) {
        super(sayNumber);
    }

    @Override
    protected Value internalEvaluate(Value... values) throws ExpressionEvaluationException {
        int value = assertIntegerLiteral(values[0]).intValue();
        
        RuleBasedNumberFormat nf = new RuleBasedNumberFormat(Locale.US, RuleBasedNumberFormat.SPELLOUT);

        ValueConverter converter = ValueConverters.ENGLISH_INTEGER;
        String valueAsWords = converter.asWords(value);

        return Values.literal(valueAsWords);
    }

    @Override
    public SayNumber copy() {
        return new SayNumber(this);
    }

    @Override
    public void accept(final ExpressionVisitor expressionVisitor) {
        expressionVisitor.visit(this);
    }

    @Override
    public String toString() {
        return "Convert integer into spoken equivalent";
    }
}
