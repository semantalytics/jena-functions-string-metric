package com.semantalytics.jena.function.string.metric;

import com.google.common.base.Objects;
import com.google.common.collect.Range;
import org.apache.jena.sparql.expr.ExprEvalException;
import org.apache.jena.sparql.expr.NodeValue;
import org.apache.jena.sparql.function.FunctionBase;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.apache.jena.sparql.expr.NodeValue.*;

public final class WeightedLevenshteinDistance extends FunctionBase {

    private info.debatty.java.stringsimilarity.WeightedLevenshtein weightedLevenshtein;

    protected WeightedLevenshteinDistance() {
        super(Range.atLeast(2), StringMetricVocabulary.weightedLevenshteinDistance.stringValue());
    }

    private info.debatty.java.stringsimilarity.WeightedLevenshtein getWeightedLevenshteinFunction(List<NodeValue> args) {
        if(weightedLevenshtein == null) {
            Map<SubstitutionPair, Double> characterSubstitutionMap = new HashMap<>();

            for (int i = 2; i < args.size(); i += 3) {

                final Character character1 = args.get(i).getString().charAt(0);
                final Character character2 = args.get(i + 1).getString().charAt(0);
                final SubstitutionPair substitutionPair = new SubstitutionPair(character1, character2);
                final Double weight = Double.parseDouble(args.get(i + 2).getString());

                characterSubstitutionMap.put(substitutionPair, weight);
            }

            weightedLevenshtein = new info.debatty.java.stringsimilarity.WeightedLevenshtein(
                    (char c1, char c2) -> {
                        SubstitutionPair substitutionPair = new SubstitutionPair(c1, c2);
                        if (characterSubstitutionMap.containsKey(substitutionPair)) {
                            return characterSubstitutionMap.get(substitutionPair);
                        } else {
                            return 1.0;
                        }
                    }
            );

        }
        return weightedLevenshtein;
    }

    @Override
    public NodeValue exec(final List<NodeValue> args) {

        if ((args.size() - 2) % 3 != 0) {
            throw new ExprEvalException("Incorrect parameter count");
        }

        for (int i = 2; i < args.size(); i += 3) {
            assertStringLiteral(values[i]);
            assertStringLiteral(values[i + 1]);
            assertNumericLiteral(values[i + 2]);
        }

        return makeDouble(getWeightedLevenshteinFunction(args).distance(args.get(0).getString(), args.get(1).getString()));
    }

    private class SubstitutionPair {

        private final char c1;
        private final char c2;

        SubstitutionPair(final char c1, final char c2) {
            this.c1 = c1;
            this.c2 = c2;
        }

        @Override
        public int hashCode() {
            return Objects.hashCode(c1, c2);
        }

        @Override
        public boolean equals(Object object) {
            if (object == this) {
                return true;
            }

            if (object instanceof SubstitutionPair) {
                SubstitutionPair other = (SubstitutionPair) object;

                return Objects.equal(c1, other.c1) && Objects.equal(c2, other.c2);
            }
            return false;
        }
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
