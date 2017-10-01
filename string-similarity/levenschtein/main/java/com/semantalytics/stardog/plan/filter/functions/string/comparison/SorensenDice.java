package com.semantalytics.stardog.plan.filter.functions.string.comparison;

import com.complexible.stardog.plan.filter.ExpressionEvaluationException;
import com.complexible.stardog.plan.filter.ExpressionVisitor;
import com.complexible.stardog.plan.filter.expr.Constant;
import com.complexible.stardog.plan.filter.functions.AbstractFunction;
import com.complexible.stardog.plan.filter.functions.Function;
import com.complexible.stardog.plan.filter.functions.string.StringFunction;
import com.google.common.collect.Range;
import org.openrdf.model.Value;

import static com.complexible.common.rdf.model.Values.literal;

public class SorensenDice extends AbstractFunction implements StringFunction {

    private info.debatty.java.stringsimilarity.SorensenDice sorensenDice;

    protected SorensenDice() {
        super(Range.closed(2, 3), StringSimilarityVocab.SORENSEN_DICE.iri().stringValue());
    }

    private SorensenDice(final SorensenDice sorensenDice) {
        super(sorensenDice);
        this.sorensenDice = sorensenDice.sorensenDice;
    }

    private info.debatty.java.stringsimilarity.SorensenDice getSorensenDiceFunction(final Value... values) throws ExpressionEvaluationException {
        if(sorensenDice == null) {
            if (values.length == 3) {
                 if(!(getThirdArg() instanceof Constant)) {
                    throw new ExpressionEvaluationException("Parameter must be a constant expression");
                 }
                
                final int n = assertNumericLiteral(values[2]).intValue();
                sorensenDice = new info.debatty.java.stringsimilarity.SorensenDice(n);
            } else {
                sorensenDice = new info.debatty.java.stringsimilarity.SorensenDice();
            }
        }
        return sorensenDice;
    }


    @Override
    public void initialize() {
        sorensenDice = null;
    }

    @Override
    protected Value internalEvaluate(final Value... values) throws ExpressionEvaluationException {

        final String firstString = assertStringLiteral(values[0]).stringValue();
        final String secondString = assertStringLiteral(values[1]).stringValue();

        return literal(getSorensenDiceFunction(values).distance(firstString, secondString));
    }

    public Function copy() {
        return new SorensenDice(this);
    }

    public void accept(final ExpressionVisitor expressionVisitor) {
        expressionVisitor.visit(this);
    }

    @Override
    public String toString() {
        return "sorensenDice";
    }
}
