package com.semantalytics.stardog.kibble.strings.phonetic;

import com.complexible.common.rdf.model.StardogValueFactory;
import org.openrdf.model.IRI;

public enum PhoneticVocabulary {

    carverphone2,
    colognePhonetic,
    daitchMokotoffSoundex,
    doubleMetaphone,
    metaphone,
    nysiis,
    refinedSoundex,
    soundex;

    public static final String NAMESPACE = "http://semantalytics.com/2017/09/ns/stardog/kibble/strings/phonetic/";
    public final IRI iri;

    PhoneticVocabulary() {
        iri = StardogValueFactory.instance().createIRI(NAMESPACE, name());
    }

    public String stringValue() {
        return iri.stringValue();
    }
}
