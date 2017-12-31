package com.semantalytics.stardog.kibble.crypto;

import com.complexible.common.rdf.model.StardogValueFactory;
import org.openrdf.model.IRI;

public enum CryptoVocabulary {

    ;

    public static final String NAMESPACE = "http://semantalytics.com/2017/09/ns/stardog/kibble/crypto/";
    public final IRI iri;

    CryptoVocabulary() {
        iri = StardogValueFactory.instance().createIRI(NAMESPACE, name());
    }

    public String stringValue() {
        return iri.stringValue();
    }
}
