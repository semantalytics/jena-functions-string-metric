package com.semantalytics.stardog.kibble.strings.comparison;

import com.complexible.common.rdf.model.StardogValueFactory;
import org.openrdf.model.IRI;

public enum StringMetricVocabulary {

    cosineDistance,
    cosineSimilarity,
    damerauDistance,
    hammingDistance,
    isub,
    jaroWinklerSimilarity,
    jaroWinklerDistance,
    levenshteinDistance,
    longestCommonSubsequence,
    longestCommonSubstring,
    metricLongestCommonSubsequence,
    needlemanWunch,
    ngram,
    normalizedLevenshteinDistance,
    normalizedLevenshteinSimarity,
    overlapCoefficient,
    qgram,
    sift4,
    smithWaterman,
    smithWatermanGotoh,
    sorensenDiceSimilarity,
    sorensenDiceDistance,
    weightedLevenshteinDistance;

    public static final String NAMESPACE = "http://semantalytics.com/2017/09/ns/stardog/kibble/string/metric/";
    public final IRI iri;

    StringMetricVocabulary() {
        iri = StardogValueFactory.instance().createIRI(NAMESPACE, name());
    }

    public static String sparqlPrefix(String prefixName) {
        return "PREFIX " + prefixName + ": <" + NAMESPACE + "> ";
    }

    public String stringValue() {
        return iri.stringValue();
    }
}
