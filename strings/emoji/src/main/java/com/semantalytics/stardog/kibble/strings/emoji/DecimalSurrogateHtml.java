package com.semantalytics.stardog.kibble.strings.emoji;

import com.complexible.stardog.plan.filter.ExpressionEvaluationException;
import com.complexible.stardog.plan.filter.ExpressionVisitor;
import com.complexible.stardog.plan.filter.functions.AbstractFunction;
import com.complexible.stardog.plan.filter.functions.string.StringFunction;
import emoji4j.EmojiUtils;
import org.openrdf.model.Value;

import static com.complexible.common.rdf.model.Values.literal;

public final class DecimalSurrogateHtml extends AbstractFunction implements StringFunction {

    protected DecimalSurrogateHtml() {
        super(1, "http://semantalytics.com/2017/11/ns/stardog/strings/emoji/decimalSurrogateHtml");
    }

    private DecimalSurrogateHtml(final DecimalSurrogateHtml decimalSurrogateHtml) {
        super(decimalSurrogateHtml);
    }

    @Override
    protected Value internalEvaluate(final Value... values) throws ExpressionEvaluationException {

        final String string = assertStringLiteral(values[0]).stringValue();

        return literal(EmojiUtils.getEmoji(string).getDecimalSurrogateHtml());
    }

    @Override
    public DecimalSurrogateHtml copy() {
        return new DecimalSurrogateHtml(this);
    }

    @Override
    public void accept(final ExpressionVisitor expressionVisitor) {
        expressionVisitor.visit(this);
    }

    @Override
    public String toString() {
        return "decimalSurrogateHtml";
    }
}