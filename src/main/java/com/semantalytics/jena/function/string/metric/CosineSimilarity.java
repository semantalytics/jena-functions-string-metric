package com.semantalytics.jena.function.string.metric;

import org.apache.jena.sparql.expr.ExprList;
import org.apache.jena.sparql.expr.NodeValue;
import org.apache.jena.sparql.function.FunctionBase;

import java.util.List;

import static org.apache.jena.sparql.expr.NodeValue.*;

public final class CosineSimilarity extends FunctionBase {

    private CosineDistance cosineDistance = new CosineDistance();

    @Override
    public NodeValue exec(final List<NodeValue> args) {
        return makeDouble(1.0 - cosineDistance.exec(args).getDouble());
    }

    @Override
    public void checkBuild(final String uri, final ExprList args) {
       cosineDistance.checkBuild(uri, args);
    }
}
