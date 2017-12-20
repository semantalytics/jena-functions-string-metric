package com.semantalytics.stardog.kibble.javascript;

import com.complexible.common.rdf.model.StardogValueFactory;
import org.openrdf.model.IRI;

public enum StatisticsVocabulary {

    exec;

    public static final String NAMESPACE = "http://semantalytics.com/2017/09/ns/stardog/kibble/statistics/";
    public final IRI iri;

    StatisticsVocabulary() {
        iri = StardogValueFactory.instance().createIRI(NAMESPACE, name());
    }

    public String stringValue() {
        return iri.stringValue();
    }
}
