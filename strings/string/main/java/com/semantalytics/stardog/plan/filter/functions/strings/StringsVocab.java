package com.semantalytics.stardog.plan.filter.functions.strings;

import com.complexible.common.openrdf.vocabulary.Vocabulary;
import com.complexible.common.rdf.model.StardogValueFactory;
import org.openrdf.model.IRI;

public class StringsVocab extends Vocabulary {

    public static final String NS = "http://semantalytics.com/2016/03/ns/stardog/function/strings/";

    public static final IRI abbreviate;
    public static final IRI abbreviateMiddle;
    public static final IRI abbreviateWithMarker;
    public static final IRI appendIfMissing;
    public static final IRI appendIfMissingIgnoreCase;
    public static final IRI capitalize;
    public static final IRI caseFormat;
    public static final IRI center;
    public static final IRI chomp;
    public static final IRI chop;
    public static final IRI commonPrefix;
    public static final IRI commonSuffix;
    public static final IRI compare;
    public static final IRI compareIgnoreCase;
    public static final IRI contains;
    public static final IRI containsAny;
    public static final IRI containsNone;
    public static final IRI containsOnly;
    public static final IRI containsWhitespace;
    public static final IRI countMatches;
    public static final IRI defaultIfBlank;
    public static final IRI difference;
    public static final IRI deleteWhitespace;
    public static final IRI defaultIfEmpty;
    public static final IRI endsWith;
    public static final IRI equalsAny;
    public static final IRI equalsIgnoreCase;
    public static final IRI endsWithIgnoreCase;
    public static final IRI getDigits;
    public static final IRI initials;
    public static final IRI isAllLowerCase;
    public static final IRI isAllUpperCase;
    public static final IRI isBlank;
    public static final IRI isEmpty;
    public static final IRI isMixedCase;
    public static final IRI join;
    public static final IRI mid;
    public static final IRI overlay;
    public static final IRI padEnd;
    public static final IRI padStart;
    public static final IRI prependIfMissing;
    public static final IRI prependIfMissingIgnoreCase;
    public static final IRI random;
    public static final IRI repeat;
    public static final IRI reverse;
    public static final IRI reverseDelimited;
    public static final IRI rotate;
    public static final IRI stripAccents;
    public static final IRI truncate;
    public static final IRI uncapitalize;
    public static final IRI unwrap;
    public static final IRI wrap;
    public static final IRI wrapIfMissing;

    private  static final StringsVocab INSTANCE = new StringsVocab();

    private StringsVocab() {
        super("http://semantalytics.com/2016/03/ns/stardog/function/strings/", StardogValueFactory.instance());
    }

    public static StringsVocab ontology() {
        return INSTANCE;
    }

    static {
        abbreviate = INSTANCE.term("abbreviate");
        abbreviateMiddle = INSTANCE.term("abbreviateMiddle");
        abbreviateWithMarker = INSTANCE.term("abbreviateWithMarker");
        appendIfMissing = INSTANCE.term("appendIfMissing");
        appendIfMissingIgnoreCase = INSTANCE.term("appendIfMissingIgnoreCase");
        capitalize = INSTANCE.term("capitalize");
        caseFormat = INSTANCE.term("caseFormat");
        center = INSTANCE.term("center");
        chomp = INSTANCE.term("chomp");
        chop = INSTANCE.term("chop");
        commonPrefix = INSTANCE.term("commonPrefix");
        commonSuffix = INSTANCE.term("commonSuffix");
        compare = INSTANCE.term("compare");
        compareIgnoreCase = INSTANCE.term("compareIgnoreCase");
        contains = INSTANCE.term("contains");
        containsAny = INSTANCE.term("containsAny");
        containsNone = INSTANCE.term("continsNone");
        containsOnly = INSTANCE.term("continsOnly");
        containsWhitespace = INSTANCE.term("containsWhitespace");
        countMatches = INSTANCE.term("countMatches");
        defaultIfBlank = INSTANCE.term("defaultIfBlank");
        difference = INSTANCE.term("difference");
        deleteWhitespace = INSTANCE.term("deleteWhitespace");
        defaultIfEmpty = INSTANCE.term("defaultIfEmpty");
        endsWith = INSTANCE.term("endsWith");
        equalsAny = INSTANCE.term("equalsAny");
        equalsIgnoreCase = INSTANCE.term("equalsIgnoreCase");
        endsWithIgnoreCase = INSTANCE.term("endsWithIgnoreCase");
        getDigits = INSTANCE.term("getDigits");
        initials = INSTANCE.term("initials");
        isAllLowerCase = INSTANCE.term("isAllLowerCase");
        isAllUpperCase = INSTANCE.term("isAllUpperCase");
        isBlank = INSTANCE.term("isBlank");
        isEmpty = INSTANCE.term("isEmpty");
        isMixedCase = INSTANCE.term("isMixedCase");
        join = INSTANCE.term("join");
        mid = INSTANCE.term("mid");
        overlay = INSTANCE.term("overlay");
        padEnd = INSTANCE.term("padEnd");
        padStart = INSTANCE.term("padStart");
        prependIfMissing = INSTANCE.term("prependIfMissing");
        prependIfMissingIgnoreCase = INSTANCE.term("prependIfMissingIgnoreCase");
        random = INSTANCE.term("random");
        repeat = INSTANCE.term("repeat");
        reverse = INSTANCE.term("reverse");
        reverseDelimited = INSTANCE.term("reverseDelimited");
        rotate = INSTANCE.term("rotate");
        stripAccents = INSTANCE.term("stripAccents");
        truncate = INSTANCE.term("truncate");
        uncapitalize = INSTANCE.term("uncapitalize");
        unwrap = INSTANCE.term("unwrap");
        wrap = INSTANCE.term("wrap");
        wrapIfMissing = INSTANCE.term("wrapIfMissing");
    }

}
