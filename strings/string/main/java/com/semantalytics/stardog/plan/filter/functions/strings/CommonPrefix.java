package com.semantalytics.stardog.plan.filter.functions.strings;

import com.complexible.stardog.plan.filter.ExpressionEvaluationException;
import com.complexible.stardog.plan.filter.ExpressionVisitor;
import com.complexible.stardog.plan.filter.functions.AbstractFunction;
import com.complexible.stardog.plan.filter.functions.string.StringFunction;
import com.google.common.base.Strings;
import org.openrdf.model.Value;

import static com.complexible.common.rdf.model.Values.*;

public final class CommonPrefix extends AbstractFunction implements StringFunction {

    protected CommonPrefix() {
        super(2, StringsVocab.ontology().commonPrefix.toString());
    }

    private CommonPrefix(final CommonPrefix commonPrefix) {
        super(commonPrefix);
    }

    @Override
    protected Value internalEvaluate(final Value... values) throws ExpressionEvaluationException {
      
      final String firstString = assertStringLiteral(values[0]).stringValue();
      final String secondString = assertStringLiteral(values[1]).stringValue();

      //TODO handle any number of arguments
      
      return literal(Strings.commonPrefix(firstString, secondString));
    }

    @Override
    public CommonPrefix copy() {
        return new CommonPrefix(this);
    }

    @Override
    public void accept(final ExpressionVisitor expressionVisitor) {
        expressionVisitor.visit(this);
    }

    @Override
    public String toString() {
        return "commonPrefix";
    }
}
