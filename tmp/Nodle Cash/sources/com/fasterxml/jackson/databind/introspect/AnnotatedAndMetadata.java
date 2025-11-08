package com.fasterxml.jackson.databind.introspect;

import com.fasterxml.jackson.databind.introspect.Annotated;

public class AnnotatedAndMetadata<A extends Annotated, M> {
    public final A annotated;
    public final M metadata;

    public AnnotatedAndMetadata(A a2, M m3) {
        this.annotated = a2;
        this.metadata = m3;
    }

    public static <A extends Annotated, M> AnnotatedAndMetadata<A, M> of(A a2, M m3) {
        return new AnnotatedAndMetadata<>(a2, m3);
    }
}
