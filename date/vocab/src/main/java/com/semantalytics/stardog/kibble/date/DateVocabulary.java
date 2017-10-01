package com.semantalytics.stardog.kibble.date;

import com.complexible.common.openrdf.vocabulary.Vocabulary;
import com.complexible.common.rdf.model.StardogValueFactory;
import org.openrdf.model.IRI;

public class DateVocabulary extends Vocabulary {

    public static final String NS = "http://semantalytics.com/2017/09/ns/stardog/kibble/date/";

    public static final IRI epochTime;
    public static final IRI nextQuarter;
    public static final IRI previousQuarter;
    public static final IRI quarter;

    private  static final DateVocabulary INSTANCE = new DateVocabulary();

    private DateVocabulary() {
        super(NS, StardogValueFactory.instance());
    }

    public static DateVocabulary ontology() {
        return INSTANCE;
    }

    static {
        epochTime = INSTANCE.term("epochTime");
        nextQuarter = INSTANCE.term("nextQuarter");
        previousQuarter = INSTANCE.term("previousQuarter");
        quarter = INSTANCE.term("quarter");
    }

}
