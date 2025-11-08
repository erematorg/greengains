package com.fasterxml.jackson.core.util;

import java.io.Serializable;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;

public class Separators implements Serializable {
    private static final long serialVersionUID = 1;
    private final char arrayValueSeparator;
    private final char objectEntrySeparator;
    private final char objectFieldValueSeparator;

    public Separators() {
        this(AbstractJsonLexerKt.COLON, AbstractJsonLexerKt.COMMA, AbstractJsonLexerKt.COMMA);
    }

    public static Separators createDefaultInstance() {
        return new Separators();
    }

    public char getArrayValueSeparator() {
        return this.arrayValueSeparator;
    }

    public char getObjectEntrySeparator() {
        return this.objectEntrySeparator;
    }

    public char getObjectFieldValueSeparator() {
        return this.objectFieldValueSeparator;
    }

    public Separators withArrayValueSeparator(char c3) {
        return this.arrayValueSeparator == c3 ? this : new Separators(this.objectFieldValueSeparator, this.objectEntrySeparator, c3);
    }

    public Separators withObjectEntrySeparator(char c3) {
        return this.objectEntrySeparator == c3 ? this : new Separators(this.objectFieldValueSeparator, c3, this.arrayValueSeparator);
    }

    public Separators withObjectFieldValueSeparator(char c3) {
        return this.objectFieldValueSeparator == c3 ? this : new Separators(c3, this.objectEntrySeparator, this.arrayValueSeparator);
    }

    public Separators(char c3, char c4, char c5) {
        this.objectFieldValueSeparator = c3;
        this.objectEntrySeparator = c4;
        this.arrayValueSeparator = c5;
    }
}
