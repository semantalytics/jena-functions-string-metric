package com.semantalytics.stardog.plan.filter.functions.strings;

import com.complexible.common.rdf.model.Values;
import com.complexible.stardog.plan.filter.ExpressionEvaluationException;
import com.complexible.stardog.plan.filter.ExpressionVisitor;
import com.complexible.stardog.plan.filter.functions.AbstractFunction;
import com.complexible.stardog.plan.filter.functions.string.StringFunction;
import org.apache.commons.text.WordUtils;
import org.openrdf.model.Value;

public final class Initials extends AbstractFunction implements StringFunction {

    protected Initials() {
        super(2, StringsVocab.ontology().initials.toString());
    }

    private Initials(final Initials initials) {
        super(initials);
    }

    @Override
    protected Value internalEvaluate(final Value... values) throws ExpressionEvaluationException {
      
      final String string = assertStringLiteral(values[0]).stringValue();
      final String delimiters = assertStringLiteral(values[1]).stringValue();

      return Values.literal(WordUtils.initials(string, delimiters.toCharArray()));
    }

    @Override
    public Initials copy() {
        return new Initials(this);
    }

    @Override
    public void accept(final ExpressionVisitor expressionVisitor) {
        expressionVisitor.visit(this);
    }

    @Override
    public String toString() {
        return "Abbreviates the words nicely. This method searches for the first space after the lower limit and abbreviates the String there. It will also append any String passed as a parameter to the end of the String. The upper limit can be specified to forcibly abbreviate a String.";
    }
}
