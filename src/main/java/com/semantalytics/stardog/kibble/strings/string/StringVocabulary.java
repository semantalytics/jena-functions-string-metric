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
    compareIgnoreCase,
    commonPrefix,
    containsAny,
    contains,
    containsIgnoreCase,
    containsNone,
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
    indexOf,
    indexOfAny,
    indexOfAnyBut,
    indexOfDifference,
    indexOfIgnoreCase,
    isAlpha,
    isAsciiPrintable,
    isAlphaSpace,
    isAlphanumeric,
    isAnyEmpty,
    isAnyBlank,
    isAlphanumericSpace,
    isAllLowerCase,
    isAllUpperCase,
    isBlank,
    isEmpty,
    isMixedCase,
    isNoneBlank,
    isNotEmpty,
    isNumericSpace,
    isAllBlank,
    isAllEmpty,
    isNotBlank,
    isNumeric,
    isWhitespace,
    join,
    joinWith,
    left,
    length,
    leftPad,
    lastIndexOfAny,
    lastOrdinalIndexOf,
    lastIndexOfIgnoreCase,
    lastIndexOf,
    lowerCaseFully,
    lowerCase,
    ordinalIndexOf,
    overlay,
    padEnd,
    padStart,
    prependIfMissing,
    prependIfMissingIgnoreCase,
    reverseDelimited,
    removeEndIgnoreSpace,
    repeat,
    random,
    remove,
    rotate,
    reverse,
    removeEnd,
    removeAll,
    removeFirst,
    stripAccents,
    truncate,
    uncapitalize,
    unwrap,
    wrap,
    wrapIfMissing,
    mid,
    normalizeSpace,
    upperCaseFully;

    public static final String NAMESPACE = "http://semantalytics.com/2017/09/ns/stardog/kibble/strings/string/";
    public final IRI iri;

    StringVocabulary() {
        iri = StardogValueFactory.instance().createIRI(NAMESPACE, name());
    }

    public static String sparqlPrefix(String prefixName) {
        return "PREFIX " + prefixName + ": <" + NAMESPACE + "> ";
    }

    public String stringValue() {
        return iri.stringValue();
    }
}
