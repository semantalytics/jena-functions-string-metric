package com.semantalytics.stardog.kibble.file;

import com.complexible.stardog.plan.filter.ExpressionEvaluationException;
import com.complexible.stardog.plan.filter.ExpressionVisitor;
import com.complexible.stardog.plan.filter.functions.AbstractFunction;
import com.complexible.stardog.plan.filter.functions.Function;
import com.complexible.stardog.plan.filter.functions.UserDefinedFunction;
import com.semantalytics.stardog.kibble.date.FileVocabulary;
import org.openrdf.model.Value;
import java.nio.file.Files;

import java.io.IOException;
import java.nio.file.Paths;

import static com.complexible.common.rdf.model.Values.*;

public class ContentType extends AbstractFunction implements UserDefinedFunction {

    ContentType() {
        super(1, FileVocabulary.contentType.stringValue());
    }

    private ContentType(final ContentType contentType) {
        super(contentType);
    }

    @Override
    protected Value internalEvaluate(final Value... values) throws ExpressionEvaluationException {

        final String file = assertStringLiteral(values[0]).stringValue();

        try {
            return literal(Files.probeContentType(Paths.get(file)));
        } catch (IOException e) {
            throw new ExpressionEvaluationException(e);
        }
    }

    @Override
    public Function copy() {
        return new ContentType(this);
    }

    @Override
    public void accept(final ExpressionVisitor expressionVisitor) {
        expressionVisitor.visit(this);
    }

    @Override
    public String toString() {
        return FileVocabulary.contentType.name();
    }
}
