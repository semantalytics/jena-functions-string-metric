package com.semantalytics.stardog.kibble.strings.emoji;

import com.complexible.stardog.plan.filter.ExpressionEvaluationException;
import com.complexible.stardog.plan.filter.ExpressionVisitor;
import com.complexible.stardog.plan.filter.functions.AbstractFunction;
import com.complexible.stardog.plan.filter.functions.string.StringFunction;
import emoji4j.EmojiUtils;
import org.openrdf.model.Value;

import static com.complexible.common.rdf.model.Values.literal;

public final class Aliases extends AbstractFunction implements StringFunction {

    protected Aliases() {
        super(1, "http://semantalytics.com/2017/11/ns/stardog/strings/emoji/aliases");
    }

    private Aliases(final Aliases aliases) {
        super(aliases);
    }

    @Override
    protected Value internalEvaluate(final Value... values) throws ExpressionEvaluationException {

        final String string = assertStringLiteral(values[0]).stringValue();

        return literal(String.join(",", EmojiUtils.getEmoji(string).getAliases()));
    }

    @Override
    public Aliases copy() {
        return new Aliases(this);
    }

    @Override
    public void accept(final ExpressionVisitor expressionVisitor) {
        expressionVisitor.visit(this);
    }

    @Override
    public String toString() {
        return "aliases";
    }
}