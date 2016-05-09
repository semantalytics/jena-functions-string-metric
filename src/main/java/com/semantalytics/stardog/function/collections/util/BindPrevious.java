package com.semantalytics.stardog.function.collections.util;

import com.complexible.stardog.plan.filter.Expression;
import com.complexible.stardog.plan.filter.ExpressionEvaluationException;
import com.complexible.stardog.plan.filter.ExpressionVisitor;
import com.complexible.stardog.plan.filter.functions.AbstractFunction;
import com.complexible.stardog.plan.filter.functions.Function;
import com.complexible.stardog.plan.filter.functions.UserDefinedFunction;
import org.openrdf.model.Value;
import org.openrdf.model.vocabulary.RDF;

public class BindPrevious extends AbstractFunction implements UserDefinedFunction {

    private Value prev = null;

    protected BindPrevious() {
        super(1, "http://semantalytics.com/2016/03/ns/stardog/udf/util/bindPrev");
    }

    public BindPrevious(final BindPrevious next) {
        super(next);
    }

    @Override
    protected Value internalEvaluate(final Value... values) throws ExpressionEvaluationException {

        Value curr = prev;
        prev = values[0];

        if(curr == null) {
            throw ExpressionEvaluationException.notBound("No previous binding");
        } else {
            return curr;
        }
    }

    @Override
    public void initialize() {
        prev = null;
    }

    @Override
    public Function copy() {
        return new BindPrevious(this);
    }

    @Override
    public void accept(final ExpressionVisitor expressionVisitor) {
        expressionVisitor.visit(this);
    }

    @Override
    public String toString() {
        return "bindPrev";
    }
}
