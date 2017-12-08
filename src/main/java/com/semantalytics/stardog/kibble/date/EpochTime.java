package com.semantalytics.stardog.kibble.date;

import com.complexible.stardog.plan.filter.ExpressionEvaluationException;
import com.complexible.stardog.plan.filter.ExpressionVisitor;
import com.complexible.stardog.plan.filter.functions.AbstractFunction;
import com.complexible.stardog.plan.filter.functions.UserDefinedFunction;
import org.openrdf.model.Value;

import javax.xml.datatype.XMLGregorianCalendar;

import static com.complexible.common.rdf.model.Values.literal;

public class EpochTime extends AbstractFunction implements UserDefinedFunction {

        public EpochTime() {
            super(1, DateVocabulary.epochTime.stringValue());
        }

        public EpochTime(final EpochTime epochTime) {
            super(epochTime);
        }

        @Override
        protected Value internalEvaluate(final Value... values) throws ExpressionEvaluationException {

            final XMLGregorianCalendar calendar = assertLiteral(values[0]).calendarValue();

            return literal(calendar.toGregorianCalendar().getTimeInMillis());
        }

        @Override
        public EpochTime copy() {
            return new EpochTime(this);
        }

        @Override
        public void accept(final ExpressionVisitor expressionVisitor) {
            expressionVisitor.visit(this);
        }

        @Override
        public String toString() {
            return DateVocabulary.epochTime.name();
        }
}
