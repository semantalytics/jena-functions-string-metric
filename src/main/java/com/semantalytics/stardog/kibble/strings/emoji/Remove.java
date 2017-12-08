package com.semantalytics.stardog.kibble.strings.emoji;

import com.complexible.stardog.plan.filter.ExpressionEvaluationException;
import com.complexible.stardog.plan.filter.ExpressionVisitor;
import com.complexible.stardog.plan.filter.functions.AbstractFunction;
import com.complexible.stardog.plan.filter.functions.string.StringFunction;
import emoji4j.EmojiUtils;
import org.openrdf.model.Value;

import static com.complexible.common.rdf.model.Values.literal;

public final class Remove extends AbstractFunction implements StringFunction {

    protected Remove() {
        super(1, "http://semantalytics.com/2017/11/ns/stardog/strings/emoji/remove");
    }

    private Remove(final Remove remove) {
        super(remove);
    }

    @Override
    protected Value internalEvaluate(final Value... values) throws ExpressionEvaluationException {

        final String string = assertStringLiteral(values[0]).stringValue();

        return literal(EmojiUtils.removeAllEmojis(string));
    }

    @Override
    public Remove copy() {
        return new Remove(this);
    }

    @Override
    public void accept(final ExpressionVisitor expressionVisitor) {
        expressionVisitor.visit(this);
    }

    @Override
    public String toString() {
        return "remove";
    }
}
