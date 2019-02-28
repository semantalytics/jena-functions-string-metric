package com.semantalytics.jena.function.string.metric;

import org.apache.jena.atlas.lib.Lib;
import org.apache.jena.sparql.expr.ExprEvalException;
import org.apache.jena.sparql.expr.NodeValue;
import org.apache.jena.sparql.function.FunctionBase2;

import java.util.stream.IntStream;

import static org.apache.jena.ext.com.google.common.base.Preconditions.checkArgument;

public final class HammingDistance extends FunctionBase2 {

    public static final String name = StringMetricVocabulary.hammingDistance.stringValue();

    @Override
    public NodeValue exec(final NodeValue arg0, final NodeValue arg1) {

        if(!arg0.isString())
            throw new ExprEvalException(Lib.className(this) + " first argument must be a string literal");

        if(!arg1.isString())
            throw new ExprEvalException(Lib.className(this) + " second argument must be a string literal");

        final String string1 = arg0.getString();
        final String string2 = arg1.getString();
        
        if(string1.length() != string2.length()) {
            throw new ExprEvalException("Argument lengths must be equal");
        }

        return NodeValue.makeDouble(distance(string1, string2));
    }

    private double distance(final String a, final String b) {
        checkArgument(a.length() == b.length());

        return IntStream.range(0, a.length()).filter(i -> a.charAt(i) != b.charAt(i)).sum();
    }
}
