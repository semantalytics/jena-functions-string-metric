package com.semantalytics.stardog.kibble.util;

import com.complexible.stardog.plan.filter.ExpressionEvaluationException;
import com.complexible.stardog.plan.filter.ExpressionVisitor;
import com.complexible.stardog.plan.filter.functions.AbstractFunction;
import com.complexible.stardog.plan.filter.functions.Function;
import com.complexible.stardog.plan.filter.functions.UserDefinedFunction;
import org.openrdf.model.Value;

public class BindPrevious extends AbstractFunction implements UserDefinedFunction {

    private Value prev = null;

    protected BindPrevious() {
        super(1, UtilVocabulary.bindPrev.stringValue());
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
        return UtilVocabulary.bindPrev.name();
    }
}
