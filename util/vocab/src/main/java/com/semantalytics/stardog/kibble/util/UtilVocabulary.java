package com.semantalytics.stardog.kibble.util;

import com.complexible.common.openrdf.vocabulary.Vocabulary;
import com.complexible.common.rdf.model.StardogValueFactory;
import org.openrdf.model.IRI;

public class UtilVocabulary extends Vocabulary {

    public static final String NS = "http://semantalytics.com/2017/09/ns/stardog/kibble/util/";
    public static final UtilVocabulary INSTANCE = new UtilVocabulary();
    public final IRI fromSpokenTime = this.term("fromSpokenTime");
    public final IRI user = this.term("user");
    public final IRI databaseName = this.term("databaseName");

    private UtilVocabulary() {
        super(NS, StardogValueFactory.instance());
    }

}
