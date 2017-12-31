package com.semantalytics.stardog.kibble.phonenumber;

import com.complexible.stardog.plan.filter.ExpressionEvaluationException;
import com.complexible.stardog.plan.filter.ExpressionVisitor;
import com.complexible.stardog.plan.filter.functions.AbstractFunction;
import com.complexible.stardog.plan.filter.functions.UserDefinedFunction;
import com.google.i18n.phonenumbers.NumberParseException;
import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.google.i18n.phonenumbers.Phonenumber;
import org.openrdf.model.Value;

import static com.complexible.common.rdf.model.Values.literal;

public class Format extends AbstractFunction implements UserDefinedFunction {

    private Phonenumber.PhoneNumber phoneNumber = new Phonenumber.PhoneNumber();

    protected Format() {
        super(1, PhoneNumberVocabulary.format.stringValue());
    }

    public Format(final Format next) {
        super(next);
    }

    @Override
    protected Value internalEvaluate(final Value... values) throws ExpressionEvaluationException {

        final String number = assertStringLiteral(values[0]).stringValue();
        final String regionCode = assertStringLiteral(values[1]).stringValue();

        PhoneNumberUtil phoneNumberUtil = PhoneNumberUtil.getInstance();

        try {
            phoneNumberUtil.parse(number, regionCode, phoneNumber);
            return literal(phoneNumberUtil.format(phoneNumber, PhoneNumberUtil.PhoneNumberFormat.INTERNATIONAL));

        } catch (NumberParseException e) {
            throw new ExpressionEvaluationException(e);
        }
    }

    @Override
    public Format copy() {
        return new Format(this);
    }

    @Override
    public void accept(final ExpressionVisitor expressionVisitor) {
        expressionVisitor.visit(this);
    }

    @Override
    public String toString() {
        return PhoneNumberVocabulary.format.name();
    }
}
