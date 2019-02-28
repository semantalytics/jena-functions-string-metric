package com.semantalytics.jena.function.string.metric;

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

    public static String sparqlPrefix(String prefixName) {
        return "PREFIX " + prefixName + ": <" + NAMESPACE + "> ";
    }

    public String stringValue() {
        return NAMESPACE + name();
    }
}
