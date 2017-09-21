package com.semantalytics.stardog.function.util;

import com.complexible.stardog.plan.filter.ExpressionEvaluationException;
import com.complexible.stardog.plan.filter.ExpressionVisitor;
import com.complexible.stardog.plan.filter.functions.AbstractFunction;
import com.complexible.stardog.plan.filter.functions.Function;
import com.complexible.stardog.plan.filter.functions.UserDefinedFunction;
import org.openrdf.model.Value;

public class IsValidXsdType extends AbstractFunction implements UserDefinedFunction {

    public IsValidXsdType() {
        super(1, "http://semantaltyics.com/2016/03/ns/stardog/udf/util/isValidXsdType");
    }

    public IsValidXsdType(final IsValidXsdType isValidXsdType) {
        super(isValidXsdType);
    }

    @Override
    protected Value internalEvaluate(final Value... values) throws ExpressionEvaluationException {
        return null;
    }

    @Override
    public Function copy() {
        return new IsValidXsdType(this);
    }

    @Override
    public void accept(ExpressionVisitor expressionVisitor) {
        expressionVisitor.visit(this);
    }

    @Override
    public String toString() {
        return "IsValidXsdType";
    }
}
