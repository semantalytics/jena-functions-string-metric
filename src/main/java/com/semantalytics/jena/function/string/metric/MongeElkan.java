package com.semantalytics.jena.function.string.metric;

import com.google.common.collect.Range;
import org.apache.jena.sparql.expr.NodeValue;
import org.apache.jena.sparql.function.FunctionBase;

import java.util.List;

public final class MongeElkan extends FunctionBase {

    private info.debatty.java.stringsimilarity.Cosine cosine;

    protected MongeElkan() {
        super(Range.closed(2, 3), StringMetricVocabulary.cosineDistance.stringValue());
    }

    private MongeElkan(final MongeElkan mongeElkan) {
        super(mongeElkan);
        this.cosine = mongeElkan.cosine;
    }

    public info.debatty.java.stringsimilarity.Cosine getCosineFunction(final Value... values) throws ExpressionEvaluationException {
        if(cosine == null) {
            if (values.length == 3) {
                 assertNumericLiteral(values[2]);
                 if(!(getThirdArg() instanceof Constant)) {
                    throw new ExpressionEvaluationException("Parameter must be constant expression");
                }
                final int n = assertNumericLiteral(values[2]).intValue();
                cosine = new info.debatty.java.stringsimilarity.Cosine(n);
            } else {
                cosine = new info.debatty.java.stringsimilarity.Cosine();
            }
        }
        return cosine;
    }

    @Override
    public void initialize() {
        cosine = null;
    }

    @Override
    public NodeValue exec(final List<NodeValue> args) {

        final String string1 = assertStringLiteral(values[0]).stringValue();
        final String string2 = assertStringLiteral(values[1]).stringValue();

        return literal(getCosineFunction(values).distance(string1, string2));
    }
}
