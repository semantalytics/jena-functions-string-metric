package com.semantalytics.stardog.kibble.strings.similarity;

import com.complexible.stardog.plan.filter.ExpressionEvaluationException;
import com.complexible.stardog.plan.filter.ExpressionVisitor;
import com.complexible.stardog.plan.filter.expr.Constant;
import com.complexible.stardog.plan.filter.functions.AbstractFunction;
import com.complexible.stardog.plan.filter.functions.Function;
import com.complexible.stardog.plan.filter.functions.string.StringFunction;
import com.google.common.collect.Range;
import org.openrdf.model.Value;

import static com.complexible.common.rdf.model.Values.literal;

public final class QGram extends AbstractFunction implements StringFunction {

    private info.debatty.java.stringsimilarity.QGram qGram;

    {
        if (getArgs().size() == 3 && getArgs().get(2) instanceof Constant) {
            final int n = Integer.parseInt(((Constant) getArgs().get(2)).getValue().stringValue());
            qGram = new info.debatty.java.stringsimilarity.QGram(n);
        } else {
            qGram = new info.debatty.java.stringsimilarity.QGram();
        }
    }

    protected QGram() {
        super(Range.closed(2, 3), StringSimilarityVocab.Q_GRAM.iri().stringValue());
    }

    private QGram(final QGram qGram) {
        super(qGram);
    }

    @Override
    protected Value internalEvaluate(final Value... values) throws ExpressionEvaluationException {

        assertStringLiteral(values[0]);
        assertStringLiteral(values[1]);
        if(values.length == 3) {
            assertNumericLiteral(values[2]);
        }

        return literal(qGram.distance(values[1].stringValue(), values[2].stringValue()));
    }

    public Function copy() {
        QGram that = new QGram(this);
        that.qGram = this.qGram;
        return that;
    }

    public void accept(final ExpressionVisitor expressionVisitor) {
        expressionVisitor.visit(this);
    }

    @Override
    public String toString() {
        return "QGram";
    }
}

