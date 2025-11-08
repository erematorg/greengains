package com.kenai.jffi;

import kotlinx.serialization.json.internal.AbstractJsonLexerKt;

public abstract class DirectObjectParameterStrategy extends ObjectParameterStrategy {
    public DirectObjectParameterStrategy(boolean z2, ObjectParameterType objectParameterType) {
        super(z2, objectParameterType);
    }

    public abstract long getAddress(Object obj);

    public final int length(Object obj) {
        StringBuilder sb = new StringBuilder("direct object ");
        sb.append(obj != null ? obj.getClass() : AbstractJsonLexerKt.NULL);
        sb.append("has no length");
        throw new RuntimeException(sb.toString());
    }

    public final Object object(Object obj) {
        StringBuilder sb = new StringBuilder("direct object ");
        sb.append(obj != null ? obj.getClass() : AbstractJsonLexerKt.NULL);
        sb.append(" has no array");
        throw new RuntimeException(sb.toString());
    }

    public final int offset(Object obj) {
        StringBuilder sb = new StringBuilder("direct object ");
        sb.append(obj != null ? obj.getClass() : AbstractJsonLexerKt.NULL);
        sb.append("has no offset");
        throw new RuntimeException(sb.toString());
    }
}
