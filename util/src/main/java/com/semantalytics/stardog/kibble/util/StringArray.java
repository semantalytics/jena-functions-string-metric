package com.semantalytics.stardog.function.util;

import com.complexible.stardog.plan.filter.ExpressionEvaluationException;
import com.complexible.stardog.plan.filter.ExpressionVisitor;
import com.complexible.stardog.plan.filter.functions.AbstractFunction;
import com.complexible.stardog.plan.filter.functions.Function;
import com.complexible.stardog.plan.filter.functions.UserDefinedFunction;
import org.openrdf.model.Value;

public class StringArray extends AbstractFunction implements UserDefinedFunction {

    protected StringArray() {
        super(1, "http://semantalytics.com/2016/03/ns/stardog/udf/util/stringArray");
    }

    public StringArray(final StringArray stringArray) {
        super(stringArray);
    }

    @Override
    protected Value internalEvaluate(final Value... values) throws ExpressionEvaluationException {

        return null;
    }

    @Override
    public Function copy() {
        return new StringArray(this);
    }

    @Override
    public void accept(final ExpressionVisitor expressionVisitor) {
        expressionVisitor.visit(this);
    }

    @Override
    public String toString() {
        return "stringArray";
    }
}