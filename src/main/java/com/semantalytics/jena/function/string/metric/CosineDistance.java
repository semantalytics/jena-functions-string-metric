package com.semantalytics.jena.function.string.metric;

import com.google.common.collect.Range;
import info.debatty.java.stringsimilarity.Cosine;
import org.apache.jena.atlas.lib.Lib;
import org.apache.jena.ext.com.google.common.cache.CacheBuilder;
import org.apache.jena.ext.com.google.common.cache.CacheLoader;
import org.apache.jena.ext.com.google.common.cache.LoadingCache;
import org.apache.jena.query.QueryBuildException;
import org.apache.jena.sparql.ARQInternalErrorException;
import org.apache.jena.sparql.expr.ExprEvalException;
import org.apache.jena.sparql.expr.ExprList;
import org.apache.jena.sparql.expr.NodeValue;
import org.apache.jena.sparql.function.FunctionBase;

import java.util.List;
import java.util.concurrent.ExecutionException;

import static org.apache.jena.sparql.expr.NodeValue.*;

public final class CosineDistance extends FunctionBase {

    private Cosine cosine = new info.debatty.java.stringsimilarity.Cosine();
    private LoadingCache<Integer, Cosine> cache = CacheBuilder.newBuilder().maximumSize(100).build(new CacheLoader<Integer, Cosine>() {
        @Override
        public Cosine load(Integer k) {
            return new info.debatty.java.stringsimilarity.Cosine(k);
        }
    });
    private static final String name = StringMetricVocabulary.cosineDistance.stringValue();

    public info.debatty.java.stringsimilarity.Cosine getCosineFunction(final List<NodeValue> args) throws ExecutionException {
        if (args.size() == 3) {
            final int k = args.get(2).getInteger().intValue();
            return cache.get(k);
        } else {
            return this.cosine;
        }
    }

    @Override
    public NodeValue exec(final List<NodeValue> args) {

        if ( args == null )
            // The contract on the function interface is that this should not happen.
            throw new ARQInternalErrorException(Lib.className(this) + ": Null args list") ;

        if (!Range.closed(2, 3).contains(args.size()))
            throw new ExprEvalException(Lib.className(this) + ": Wrong number of arguments: Wanted 2 or 3, got " + args.size()) ;

        if(!args.get(0).isString())
            throw new ExprEvalException(Lib.className(this) + " first argument must be a string literal");

        if(!args.get(1).isString())
            throw new ExprEvalException(Lib.className(this) + " second argument must be a string literal");

        if(args.size() == 3 && !args.get(2).isInteger())
            throw new ExprEvalException(Lib.className(this) + " third argument must be a integer literal");

        final String string1 = args.get(0).getString();
        final String string2 = args.get(1).getString();

        final Cosine cosine;

        try {
           cosine = getCosineFunction(args);
        } catch(ExecutionException e) {
            throw new ExprEvalException(e);
        }

        return makeDouble(cosine.distance(string1, string2));
    }

    @Override
    public void checkBuild(final String uri, final ExprList args) {
        if(!Range.closed(2, 3).contains(args.size())) {
            throw new QueryBuildException("Function '" + Lib.className(this) + "' takes two or three arguments") ;
        }
        if(args.get(0).isConstant() && !args.get(0).getConstant().isString()) {
            throw new QueryBuildException("Function '" + Lib.className(this) + "' first argument must be a string literal") ;
        }
        if(args.get(1).isConstant() && !args.get(1).getConstant().isString()) {
            throw new QueryBuildException("Function '" + Lib.className(this) + "' second argument must be a string literal") ;
        }
        if(args.size() == 3 && args.get(2).isConstant() && !args.get(2).getConstant().isInteger()) {
            throw new QueryBuildException("Function '" + Lib.className(this) + "' third argument must be a integer literal") ;
        }
    }
}
