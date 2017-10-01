package com.semantalytics.stardog.kibble.util;

import com.complexible.stardog.plan.filter.ExpressionEvaluationException;
import com.complexible.stardog.plan.filter.ExpressionVisitor;
import com.complexible.stardog.plan.filter.functions.AbstractFunction;
import com.complexible.stardog.plan.filter.functions.Function;
import com.complexible.stardog.plan.filter.functions.UserDefinedFunction;
import org.apache.shiro.SecurityUtils;
import org.openrdf.model.Value;

import static com.complexible.common.rdf.model.Values.literal;

public final class User extends AbstractFunction implements UserDefinedFunction {

    public User() {
        super(0, UtilVocabulary.INSTANCE.user.stringValue());
    }

    private User(final User user) {
        super(user);
    }

    @Override
    protected Value internalEvaluate(final Value... values) throws ExpressionEvaluationException {
        return literal(SecurityUtils.getSubject().getPrincipal().toString());
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
