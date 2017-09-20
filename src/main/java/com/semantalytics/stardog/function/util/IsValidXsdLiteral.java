
package com.semantalytics.stardog.function.util;

import com.complexible.common.rdf.model.Values;
import com.complexible.stardog.plan.filter.ExpressionEvaluationException;
import com.complexible.stardog.plan.filter.ExpressionVisitor;
import com.complexible.stardog.plan.filter.functions.AbstractFunction;
import com.complexible.stardog.plan.filter.functions.UserDefinedFunction;
import com.joestelmach.natty.DateGroup;
import com.joestelmach.natty.Parser;
import org.openrdf.model.Value;
import org.openrdf.model.datatypes.XMLDatatypeUtil;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

public class IsValidXsdLiteral extends AbstractFunction implements UserDefinedFunction {

    public IsValidXsdLiteral() {
        super(1, "http://semantalytics.com/2016/03/ns/stardog/udf/util/isValidXsdLiteral");
    }

    private IsValidXsdLiteral(final IsValidXsdLiteral isValidXsdLiteral) {
        super(isValidXsdLiteral);
    }

    @Override
    protected Value internalEvaluate(final Value... values) throws ExpressionEvaluationException {
        Literal literal = assertLiteral(values[0]);

        return Values.literal(XMLDatatypeUtil.isVAlidValue(literal.getDatatype()));
    }

    @Override
    public IsValidXsdLiteral copy() {
        return new IsValidXsdLiteral(this);
    }

    @Override
    public void accept(final ExpressionVisitor expressionVisitor) {
        expressionVisitor.visit(this);
    }

    @Override
    public String toString() {
        return "isValidXsdLiteral";
    }
}
