package com.semantalytics.stardog.kibble.string;

import com.complexible.common.openrdf.vocabulary.Vocabulary;
import com.complexible.common.rdf.model.StardogValueFactory;
import org.openrdf.model.IRI;

public class StringVocabulary extends Vocabulary {

    public static final String NS = "http://semantalytics.com/2017/09/ns/stardog/kibble/string/";

    public static final IRI lowerCaseFully;
    public static final IRI upperCaseFully;

    private  static final StringVocabulary INSTANCE = new StringVocabulary();

    private StringVocabulary() {
        super(NS, StardogValueFactory.instance());
    }

    public static StringVocabulary ontology() {
        return INSTANCE;
    }

    static {
        lowerCaseFully = INSTANCE.term("lowerCaseFully");
        upperCaseFully = INSTANCE.term("upperCaseFully");
    }

}
