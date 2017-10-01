package com.semantalytics.stardog.plan.filter.functions.string.comparison;

import com.complexible.common.rdf.model.Values;
import org.openrdf.model.IRI;
import org.openrdf.model.Namespace;
import org.openrdf.model.impl.SimpleNamespace;

public enum StringSimilarityVocab {
    SOUNDEX("soundex"),
    COSINE("cosine"),
    DAMERAU("damerau"),
    JACCARD("jaccard"),
    GENERALIZED_JACCARD("generalizedJaccard"),
    JARO_WINKLER("jaroWinkler"),
    LEVENSHTEIN("levenshtein"),
    NORMALIZED_LEVENSHTEIN("normalizedLevenshtein"),
    LONGEST_COMMON_SUBSEQUENCE("longestCommonSubsequence"),
    LONGEST_COMMON_SUBSTRING("longestCommonSubstring"),
    METRIC_LONGEST_COMMON_SUBSEQUENCE("metricLongestCommonSubsequence"),
    N_GRAM("ngram"),
    Q_GRAM("qgram"),
    SORENSEN_DICE("sorensenDice"),
    WEIGHTED_LEVENSHTEIN("weightedLevenshtein"),
    ISUB("iSub"),
    CAVERPHONE2("caverphone2"),
    DAITCHMOKOTOFF_SOUNDEX("daitchMokotoffSoundex"),
    DOUBLE_METAPHONE("doubleMetaphone"),
    METAPHONE("metaphone"),
    REFINED_SOUNDEX("refinedSoundex"),
    COLOGNE_PHONETIC("colognePhonetic"),
    SMITH_WATERMAN("smithWaterman"),
    SMITH_WATERMAN_GOTOH("smithWatermanGotoh"),
    MONGE_ELKAN("mongeElkan"),
    NEEDLEMAN_WUNCH("needlemanWunch"),
    SIMON_WHITE("simonWhite"),
    OVERLAP_COEFFICIENT("overlapCoefficient"),
    HAMMING_DISTANCE("hammingDistance"),
    NYSIIS("nysiis");

    public static final String NAMESPACE ="http://semantalytics.com/2016/03/ns/stardog/function/string/comparison/";
    public static final String PREFIX ="ss";
    public static final Namespace NS = new SimpleNamespace(NAMESPACE, NAMESPACE);
    private final IRI iri;

    StringSimilarityVocab(final String localName) {
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
