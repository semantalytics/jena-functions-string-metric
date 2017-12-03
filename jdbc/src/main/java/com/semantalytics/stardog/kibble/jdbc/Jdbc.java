package com.semantalytics.stardog.kibble.jdbc;

import com.complexible.stardog.plan.filter.ExpressionEvaluationException;
import com.complexible.stardog.plan.filter.ExpressionVisitor;
import com.complexible.stardog.plan.filter.functions.AbstractFunction;
import com.complexible.stardog.plan.filter.functions.UserDefinedFunction;
import org.apache.ddlutils.PlatformFactory;
import org.apache.ddlutils.PlatformUtils;
import org.openrdf.model.Value;

import javax.xml.datatype.XMLGregorianCalendar;

import static com.complexible.common.rdf.model.Values.literal;

public class Jdbc extends AbstractFunction implements UserDefinedFunction {

        public Jdbc() {
            super(1, JdbcVocabulary.epochTime.stringValue());
        }

        public Jdbc(final Jdbc jdbc) {
            super(jdbc);
        }

        @Override
        protected Value internalEvaluate(final Value... values) throws ExpressionEvaluationException {

            PlatformFactory.isPlatformSupported()


            PlatformUtils.JDBC_DRIVER_JTDS
            return literal(calendar.toGregorianCalendar().getTimeInMillis());
        }

        @Override
        public Jdbc copy() {
            return new Jdbc(this);
        }

        @Override
        public void accept(final ExpressionVisitor expressionVisitor) {
            expressionVisitor.visit(this);
        }

        @Override
        public String toString() {
            return JdbcVocabulary.epochTime.name();
        }
}
