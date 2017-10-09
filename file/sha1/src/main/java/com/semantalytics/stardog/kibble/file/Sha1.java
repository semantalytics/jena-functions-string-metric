package com.semantalytics.stardog.kibble.file;

import com.complexible.common.rdf.model.Values;
import com.complexible.stardog.plan.filter.ExpressionEvaluationException;
import com.complexible.stardog.plan.filter.ExpressionVisitor;
import com.complexible.stardog.plan.filter.functions.AbstractFunction;
import com.complexible.stardog.plan.filter.functions.Function;
import com.complexible.stardog.plan.filter.functions.UserDefinedFunction;
import com.google.common.hash.Hashing;
import com.google.common.io.Files;
import org.openrdf.model.Value;

import java.io.File;
import java.io.IOException;
import java.net.URI;

public class Sha1 extends AbstractFunction implements UserDefinedFunction {

    public Sha1() {
        super(1, "http://semantalytics.com/2016/04/ns/stardog/udf/file/sha1");
    }

    public Sha1(final Sha1 sha1) {
        super(sha1);
    }

    @Override
    protected Value internalEvaluate(final Value... values) throws ExpressionEvaluationException {

        //TODO handle IRI or literal, check for file: protocol 
        final IRI file = assertIRI(values[0]);

        final File file = new File(URI.create(file.stringValue()));
        final String hash;
        try {
            hash = Files.hash(file, Hashing.sha1()).toString();
        } catch(IOException e) {
            throw new ExpressionEvaluationException("Error while trying to hash file");
        }
        return Values.literal(hash);
    }

    private String hash(final String string) {
    }
    
    private String hash(final URI uri) {
    }
    
    @Override
    public Function copy() {
        return new Sha1(this);
    }

    @Override
    public void accept(final ExpressionVisitor expressionVisitor) {
        expressionVisitor.visit(this);
    }

    @Override
    public String toString() {
        return "Sha1";
    }
}
