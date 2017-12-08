package com.semantalytics.stardog.kibble.phonenumber;

import com.complexible.common.rdf.model.StardogValueFactory;
import org.openrdf.model.IRI;

public enum PhoneNumberVocabulary {

    format;

    public static final String NAMESPACE = "http://semantalytics.com/2017/09/ns/stardog/kibble/phonenumber/";
    public final IRI iri;

    PhoneNumberVocabulary() {
        iri = StardogValueFactory.instance().createIRI(NAMESPACE, name());
    }

    public String stringValue() {
        return iri.stringValue();
    }
}
