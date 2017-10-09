package com.semantalytics.stardog.plan.filter.functions.geohash;

import com.complexible.common.rdf.model.Values;
import org.openrdf.model.IRI;
import org.openrdf.model.Namespace;
import org.openrdf.model.impl.SimpleNamespace;

public enum GeohashVocab {
    ENCODE("encode"),
    DECODE("decode");

    public static final String NAMESPACE ="http://semantalytics.com/2016/03/ns/stardog/function/geohash/";
    public static final String PREFIX ="gh";
    public static final Namespace NS = new SimpleNamespace(NAMESPACE, NAMESPACE);
    private final IRI iri;

    GeohashVocab(final String localName) {
        this.iri = Values.iri(NAMESPACE, localName);
    }

    public IRI iri() {
        return iri;
    }

    @Override
    public String toString() {
        return iri.stringValue();
    }
}
