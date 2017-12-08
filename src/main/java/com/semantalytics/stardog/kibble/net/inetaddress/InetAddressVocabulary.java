package com.semantalytics.stardog.kibble.net.inetaddress;

import com.complexible.common.rdf.model.StardogValueFactory;
import org.openrdf.model.IRI;

public enum InetAddressVocabulary {

    inetAddressToNumber,
    inetNumberToAddress;

    public static final String NAMESPACE = "http://semantalytics.com/2017/09/ns/stardog/kibble/net/";
    public final IRI iri;

    InetAddressVocabulary() {
        iri = StardogValueFactory.instance().createIRI(NAMESPACE, name());
    }

    public String stringValue() {
        return iri.stringValue();
    }
}
