package com.semantalytics.stardog.kibble.strings.comparison;

import com.complexible.stardog.plan.filter.ExpressionEvaluationException;
import com.complexible.stardog.plan.filter.ExpressionVisitor;
import com.complexible.stardog.plan.filter.functions.AbstractFunction;
import com.complexible.stardog.plan.filter.functions.string.StringFunction;
import org.openrdf.model.Value;

import static com.complexible.common.rdf.model.Values.literal;
import static com.google.common.base.CaseFormat.*;


public final class CaseFormat extends AbstractFunction implements StringFunction {

    protected CaseFormat() {
        super(3, ComparisonVocabulary.caseFormat.toString());
    }

    private CaseFormat(final CaseFormat caseFormat) {
        super(caseFormat);
    }

    @Override
    protected Value internalEvaluate(final Value... values) throws ExpressionEvaluationException {

        final com.google.common.base.CaseFormat caseFormatFrom = getFormatType(assertStringLiteral(values[0]).stringValue());
        final com.google.common.base.CaseFormat caseFormatTo = getFormatType(assertStringLiteral(values[1]).stringValue());
        final String caseFormatString = assertStringLiteral(values[2]).stringValue();

        switch(caseFormatFrom) {
            case LOWER_CAMEL:
                return literal(caseFormatFrom.LOWER_CAMEL.to(caseFormatTo, caseFormatString));
            case UPPER_CAMEL:
                return literal(caseFormatFrom.UPPER_CAMEL.to(caseFormatTo, caseFormatString));
            case LOWER_HYPHEN:
                return literal(caseFormatFrom.LOWER_HYPHEN.to(caseFormatTo, caseFormatString));
            case LOWER_UNDERSCORE:
                return literal(caseFormatFrom.LOWER_UNDERSCORE.to(caseFormatTo, caseFormatString));
            case UPPER_UNDERSCORE:
                return literal(caseFormatFrom.UPPER_UNDERSCORE.to(caseFormatTo, caseFormatString));
            default:
                throw new ExpressionEvaluationException("Unknown format " + values[0].stringValue());
        }
    }

    @Override
    public CaseFormat copy() {
        return new CaseFormat(this);
    }

    @Override
    public void accept(final ExpressionVisitor expressionVisitor) {
        expressionVisitor.visit(this);
    }

    @Override
    public String toString() {
        return ComparisonVocabulary.caseFormat.name();
    }

    public com.google.common.base.CaseFormat getFormatType(final String formatString) throws ExpressionEvaluationException {

        switch(formatString) {
            case "caseFormat":
                return LOWER_CAMEL;
            case "CaseFormat":
                return UPPER_CAMEL;
            case "case-format":
                return LOWER_HYPHEN;
            case "case_format":
                return LOWER_UNDERSCORE;
            case "CASE_FORMAT":
                return UPPER_UNDERSCORE;
            default:
                throw new ExpressionEvaluationException("Unrecognized format type " + formatString);
        }
    }
}
