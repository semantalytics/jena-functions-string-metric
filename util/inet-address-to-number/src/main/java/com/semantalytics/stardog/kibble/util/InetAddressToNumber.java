package com.semantalytics.stardog.function.util;


import com.complexible.common.rdf.model.Values;
import com.complexible.stardog.plan.filter.ExpressionEvaluationException;
import com.complexible.stardog.plan.filter.ExpressionVisitor;
import com.complexible.stardog.plan.filter.functions.AbstractFunction;
import com.complexible.stardog.plan.filter.functions.Function;
import com.complexible.stardog.plan.filter.functions.UserDefinedFunction;
import com.google.common.base.Splitter;
import org.openrdf.model.Value;

import java.util.List;

/**
 * Given the dotted-quad representation of an IPv4 network address as a string, returns an
 * integer that represents the numeric value of the address in network byte order (big endian)
 */
public class InetAddressToNumber extends AbstractFunction implements UserDefinedFunction {

    private static final long FIRST_OCTET_BASE = 16777216;
    private static final long SECOND_OCTET_BASE = 65536;
    private static final long THIRD_OCTET_BASE = 256;
    private static final long FOURTH_OCTET_BASE = 1;

    public InetAddressToNumber() {
        super(1, "http://semantalytics.com/2016/03/ns/stardog/udf/util/inetToNum");
    }

    private InetAddressToNumber(final InetAddressToNumber inetAddressToNumber) {
        super(inetAddressToNumber);
    }

    @Override
    protected Value internalEvaluate(final Value... values) throws ExpressionEvaluationException {

        final String ip = assertStringLiteral(values[0]).stringValue();

        final List<String> ipQuads = Splitter.on('.').splitToList(ip);

        if(ipQuads.size() != 4) {
            throw new ExpressionEvaluationException("Argument must be full ip quad. Found " + values[0].stringValue());
        }

        return Values.literal(FIRST_OCTET_BASE * Long.valueOf(ipQuads.get(0)) +
                              SECOND_OCTET_BASE * Long.valueOf(ipQuads.get(1)) +
                              THIRD_OCTET_BASE * Long.valueOf(ipQuads.get(2)) +
                              FOURTH_OCTET_BASE * Long.valueOf(ipQuads.get(3)));
    }

    @Override
    public Function copy() {
        return new InetAddressToNumber(this);
    }

    @Override
    public void accept(final ExpressionVisitor expressionVisitor) {
        expressionVisitor.visit(this);
    }

}
