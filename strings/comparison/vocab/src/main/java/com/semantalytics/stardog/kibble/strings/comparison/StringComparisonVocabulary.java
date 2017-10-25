package com.semantalytics.stardog.kibble.strings.comparison;

import com.complexible.common.rdf.model.StardogValueFactory;
import org.openrdf.model.IRI;

public enum StringComparisonVocabulary {

    cosine,
    damerau,
    hammingDistance,
    isub,
    jaroWinkler,
    levenschtein,
    longestCommonSubsequence,
    longestCommonSubstring,
    metricLongestCommonSubsequence,
    needlemanWunch,
    ngram,
    normalizedLevenshtein,
    overlapCoefficient,
    qgram,
    smithWaterman,
    smithWatermanGotoh,
    sorensenDice,
    weithtedLevenshtein;

    public static final String NAMESPACE = "http://semantalytics.com/2017/09/ns/stardog/kibble/strings/comparison/";
    public final IRI iri;

    StringComparisonVocabulary() {
        iri = StardogValueFactory.instance().createIRI(NAMESPACE, name());
    }

    public String stringValue() {
        return iri.stringValue();
    }
}
