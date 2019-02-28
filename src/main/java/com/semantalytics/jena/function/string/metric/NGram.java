package com.semantalytics.jena.function.string.metric;

import com.google.common.collect.Range;
import org.apache.jena.sparql.expr.NodeValue;
import org.apache.jena.sparql.function.FunctionBase;

import java.util.List;

public final class NGram extends FunctionBase {

    private info.debatty.java.stringsimilarity.NGram nGram;

    {
        if (getArgs().size() == 3 && getArgs().get(2) instanceof Constant) {
            final int n = Integer.parseInt(((Constant) getArgs().get(2)).getValue().stringValue());
            nGram = new info.debatty.java.stringsimilarity.NGram(n);
        } else {
            nGram = new info.debatty.java.stringsimilarity.NGram();
        }
    }

    protected NGram() {
        super(Range.closed(2, 3), StringMetricVocabulary.ngram.stringValue());
    }

    private NGram(final NGram nGram) {
        super(nGram);
    }

    @Override
    public NodeValue exec(final List<NodeValue> args) {

        assertStringLiteral(values[0]);
        assertStringLiteral(values[1]);
        if(values.length == 3) {
            assertNumericLiteral(values[2]);
        }

        return literal(nGram.distance(values[0].stringValue(), values[1].stringValue()));
    }
}
