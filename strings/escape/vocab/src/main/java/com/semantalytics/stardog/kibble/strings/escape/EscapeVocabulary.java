package com.semantalytics.stardog.kibble.strings.similarity;

import com.complexible.common.rdf.model.StardogValueFactory;
import org.openrdf.model.IRI;

public enum StringMetricsVocabulary {

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

    public static final String NAMESPACE = "http://semantalytics.com/2017/09/ns/stardog/kibble/strings/metrics/";
    public final IRI iri;

    StringMetricsVocabulary() {
        iri = StardogValueFactory.instance().createIRI(NAMESPACE, name());
    }

    public String stringValue() {
        return iri.stringValue();
    }
}
