package com.semantalytics.stardog.kibble.file;

import com.complexible.common.rdf.model.StardogValueFactory;
import org.openrdf.model.IRI;

public enum FileVocabulary {

    key,
    md5,
    lastModifiedTime,
    jsonPath,
    isSymbolicLink,
    isRegularFile,
    isDirectory,
    isOther,
    group,
    owner,
    get,
    creationTime,
    contentType;

    public static final String NAMESPACE = "http://semantalytics.com/2017/09/ns/stardog/kibble/file/";
    public final IRI iri;

    FileVocabulary() {
        iri = StardogValueFactory.instance().createIRI(NAMESPACE, name());
    }

    public String stringValue() {
        return iri.stringValue();
    }
}
