package com.semantalytics.stardog.function.util;

import com.complexible.common.rdf.model.Values;
import com.complexible.stardog.plan.filter.ExpressionEvaluationException;
import com.complexible.stardog.plan.filter.ExpressionVisitor;
import com.complexible.stardog.plan.filter.functions.AbstractFunction;
import com.complexible.stardog.plan.filter.functions.Function;
import com.complexible.stardog.plan.filter.functions.UserDefinedFunction;
import com.complexible.stardog.plan.filter.functions.numeric.Add;
import org.openrdf.model.Value;

public final class User extends AbstractFunction implements UserDefinedFunction {

    private Value index;
    private final Add add = new Add();

    public User() {
        super(0, "http://semantalytics.com/2016/03/ns/stardog/udf/util/user");
    }

    private User(final User user) {
        super(user);
    }

    @Override
    protected Value internalEvaluate(final Value... values) throws ExpressionEvaluationException {
        return literal(SecurityUtils.getSubject().getPricipal());
    }

    @Override
    public Function copy() {
        return new User(this);
    }

    @Override
    public void accept(final ExpressionVisitor expressionVisitor) {
        expressionVisitor.visit(this);
    }

    @Override
    public String toString() {
        return "user";
    }
}
