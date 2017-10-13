package com.semantalytics.stardog.kibble.geo;

import com.complexible.common.rdf.model.StardogValueFactory;
import org.openrdf.model.IRI;

public enum GeoVocabulary {

    decode,
    encode,
    latitude,
    longitude;

    public static final String NAMESPACE = "http://semantalytics.com/2017/09/ns/stardog/kibble/geo/";
    public final IRI iri;

    GeoVocabulary() {
        iri = StardogValueFactory.instance().createIRI(NAMESPACE, name());
    }

    public String stringValue() {
        return iri.stringValue();
    }
}
