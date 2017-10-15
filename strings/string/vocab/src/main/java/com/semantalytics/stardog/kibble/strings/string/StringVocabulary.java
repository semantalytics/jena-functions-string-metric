package com.semantalytics.stardog.kibble.strings.string;

import com.complexible.common.rdf.model.StardogValueFactory;
import org.openrdf.model.IRI;

public enum StringVocabulary {


    abbreviate,
    abbreviateWithMarker,
    abbreviateMiddle,
    appendIfMissing,
    appendIfMissingIgnoreCase,
    capitalize,
    caseFormat,
    center,
    chomp,
    commonSuffix,
    chop,
    compare,
    commonPrefix,
    containsAny,
    contains,
    containsWhitespace,
    containsOnly,
    countMatches,
    defaultIfBlank,
    defaultIfEmpty,
    deleteWhitespace,
    difference,
    endsWith,
    endsWithIgnoreCase,
    equalsAny,
    equalsIgnoreCase,
    getDigits,
    initials,
    isAllLowerCase,
    isAllUpperCase,
    isBlank,
    isEmpty,
    isMixedCase,
    isNoneBlank,
    join,
    lowerCaseFully,
    upperCaseFully;

    public static final String NAMESPACE = "http://semantalytics.com/2017/09/ns/stardog/kibble/strings/string/";
    public final IRI iri;

    StringVocabulary() {
        iri = StardogValueFactory.instance().createIRI(NAMESPACE, name());
    }

    public String stringValue() {
        return iri.stringValue();
    }
}
