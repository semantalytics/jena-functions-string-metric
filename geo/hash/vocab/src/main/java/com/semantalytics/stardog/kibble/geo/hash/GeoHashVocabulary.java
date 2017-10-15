package com.semantalytics.stardog.kibble.geo.hash;

import com.complexible.common.rdf.model.StardogValueFactory;
import org.openrdf.model.IRI;

public enum GeoHashVocabulary {

    right,
    left,
    top,
    bottom,
    decode,
    encode,
    latitude,
    longitude;

    public static final String namespace = "http://semantalytics.com/2017/09/ns/stardog/kibble/geo/hash/";
    public final IRI iri;

    GeoHashVocabulary() {
        iri = StardogValueFactory.instance().createIRI(namespace, name());
    }

    public String stringValue() {
        return iri.stringValue();
    }
}
