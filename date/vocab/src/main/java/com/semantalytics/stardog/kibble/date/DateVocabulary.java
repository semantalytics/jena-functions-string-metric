package com.semantalytics.stardog.kibble.date;

import com.complexible.common.openrdf.vocabulary.Vocabulary;
import com.complexible.common.rdf.model.StardogValueFactory;
import org.openrdf.model.IRI;

public class DateVocabulary extends Vocabulary {

    public static final String NS = "http://semantalytics.com/2017/09/ns/stardog/kibble/date/";

    public static final IRI epochTime;

    private  static final DateVocabulary INSTANCE = new DateVocabulary();

    private DateVocabulary() {
        super(NS, StardogValueFactory.instance());
    }

    public static DateVocabulary ontology() {
        return INSTANCE;
    }

    static {
        epochTime = INSTANCE.term("epochTime");
    }

}
