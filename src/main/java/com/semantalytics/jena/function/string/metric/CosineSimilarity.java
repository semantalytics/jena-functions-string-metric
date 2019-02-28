package com.semantalytics.jena.function.string.metric;

import org.apache.jena.sparql.expr.ExprEvalException;
import org.apache.jena.sparql.expr.NodeValue;
import org.apache.jena.sparql.function.FunctionBase;

import java.util.List;

import static org.apache.jena.sparql.expr.NodeValue.*;

public final class CosineSimilarity extends FunctionBase {

    private info.debatty.java.stringsimilarity.Cosine cosine;
    private static final String name = StringMetricVocabulary.cosineSimilarity.stringValue();

    public info.debatty.java.stringsimilarity.Cosine getCosineFunction(final List<NodeValue> args) {
        if(cosine == null) {
            if (args.size() == 3) {
                 args.get(2);
                 if(!(args.get(3).isConstant())) {
                    throw new ExprEvalException("Parameter must be constant expression");
                }
                final int n = args.get(2).getInteger().intValue();
                cosine = new info.debatty.java.stringsimilarity.Cosine(n);
            } else {
                cosine = new info.debatty.java.stringsimilarity.Cosine();
            }
        }
        return cosine;
    }

    @Override
    public NodeValue exec(final List<NodeValue> args) {

        final String string1 = args.get(0).getString();
        final String string2 = args.get(1).getString();

        return makeDouble(getCosineFunction(List<NodeValue> args).similarity(string1, string2));
    }
}
