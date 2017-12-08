package com.semantalytics.stardog.kibble.date;

import com.complexible.stardog.plan.filter.ExpressionEvaluationException;
import com.complexible.stardog.plan.filter.ExpressionVisitor;
import com.complexible.stardog.plan.filter.functions.AbstractFunction;
import com.complexible.stardog.plan.filter.functions.UserDefinedFunction;
import org.openrdf.model.Value;
import org.threeten.extra.Quarter;

import static com.complexible.common.rdf.model.Values.literal;


public class NextQuarter extends AbstractFunction implements UserDefinedFunction {

    public NextQuarter() {
        super(1, DateVocabulary.nextQuarter.stringValue());
    }

    public NextQuarter(final NextQuarter nextQuarter) {
        super(nextQuarter);
    }

    @Override
    protected Value internalEvaluate(final Value... values) throws ExpressionEvaluationException {

        final Quarter quarter = org.threeten.extra.Quarter.from(assertLiteral(values[0]).calendarValue().toGregorianCalendar().toZonedDateTime().toLocalDate());

        return literal(quarter.plus(1).getValue());
    }

    @Override
    public NextQuarter copy() {
                            return new NextQuarter(this);
                                                         }

    @Override
    public void accept(final ExpressionVisitor expressionVisitor) {
                                                                expressionVisitor.visit(this);
                                                                                               }

    @Override
    public String toString() {
        return DateVocabulary.nextQuarter.name();
    }
}
