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
import java.nio.file.attribute.PosixFileAttributeView;
import java.nio.file.attribute.PosixFileAttributes;

import static com.complexible.common.rdf.model.Values.*;

public class Group extends AbstractFunction implements UserDefinedFunction {

    Group() {
        super(1, FileVocabulary.group.stringValue());
    }

    private Group(final Group contentType) {
        super(contentType);
    }

    @Override
    protected Value internalEvaluate(final Value... values) throws ExpressionEvaluationException {

        assertIRI(values[0]);
        Path path = Paths.get(URI.create(values[0].stringValue()));
        PosixFileAttributeView posixFileAttributes;
        try {
            posixFileAttributes = Files.readAttributes(path, PosixFileAttributes.class);
        } catch (IOException e) {
            throw new ExpressionEvaluationException("Unable to read file attributes");
        }
        posixFileAttributes.group();
        GregorianCalendar calendar = new GregorianCalendar();
        calendar.setTimeInMillis(creationTime);
        return Values.literal(calendar);

        final String file = assertStringLiteral(values[0]).stringValue();

        try {
            return iri(Files.probeGroup(Paths.get(file)));
        } catch (IOException e) {
            throw new ExpressionEvaluationException(e);
        }
    }

    @Override
    public Function copy() {
        return new Group(this);
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
