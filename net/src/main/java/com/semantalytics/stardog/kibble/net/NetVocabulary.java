package com.semantalytics.stardog.kibble.net;

import com.complexible.common.rdf.model.StardogValueFactory;
import org.openrdf.model.IRI;

public enum NetVocabulary {

    inetAddressToNumber,
    inetNumberToAddress;

    public static final String NAMESPACE = "http://semantalytics.com/2017/09/ns/stardog/kibble/net/";
    public final IRI iri;

    NetVocabulary() {
        iri = StardogValueFactory.instance().createIRI(NAMESPACE, name());
    }

    public String stringValue() {
        return iri.stringValue();
    }
}
