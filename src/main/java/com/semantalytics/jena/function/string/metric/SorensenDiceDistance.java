package com.semantalytics.jena.function.string.metric;

import com.google.common.collect.Range;
import org.apache.jena.sparql.expr.NodeValue;
import org.apache.jena.sparql.function.FunctionBase;

import java.util.List;

import static org.apache.jena.sparql.expr.NodeValue.*;

public class SorensenDiceDistance extends FunctionBase {

    private info.debatty.java.stringsimilarity.SorensenDice sorensenDice;

    protected SorensenDiceDistance() {
        super(Range.closed(2, 3), StringMetricVocabulary.sorensenDiceDistance.stringValue());
    }

    private info.debatty.java.stringsimilarity.SorensenDice getSorensenDiceFunction(final List<NodeValue> values) {
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
    protected NodeValue exec(final List<NodeValue> args) {

        final String firstString = args.get(0).getString();
        final String secondString = args.get(1).getString();

        return makeDouble(getSorensenDiceFunction(args).distance(firstString, secondString));
    }

    @Override
    public void checkBuild(final String uri, final ExprList args) {
        if(!Range.closed(2, 3).contains(args.size())) {
            throw new QueryBuildException("Function '" + Lib.className(this) + "' takes two or three arguments") ;
        }
        if(args.get(0).isConstant() && !args.get(0).getConstant().isString()) {
            throw new QueryBuildException("Function '" + Lib.className(this) + "' first argument must be a string literal") ;
        }
        if(args.get(1).isConstant() && !args.get(1).getConstant().isString()) {
            throw new QueryBuildException("Function '" + Lib.className(this) + "' second argument must be a string literal") ;
        }
        if(args.size() == 3 && args.get(2).isConstant() && !args.get(2).getConstant().isInteger()) {
            throw new QueryBuildException("Function '" + Lib.className(this) + "' third argument must be a integer literal") ;
        }
    }
}
