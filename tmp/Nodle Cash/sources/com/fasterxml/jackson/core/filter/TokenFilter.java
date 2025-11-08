package com.fasterxml.jackson.core.filter;

import com.fasterxml.jackson.core.JsonParser;
import java.io.IOException;
import java.io.Reader;
import java.math.BigDecimal;
import java.math.BigInteger;

public class TokenFilter {
    public static final TokenFilter INCLUDE_ALL = new TokenFilter();

    public enum Inclusion {
        ONLY_INCLUDE_ALL,
        INCLUDE_ALL_AND_PATH,
        INCLUDE_NON_NULL
    }

    public boolean _includeScalar() {
        return true;
    }

    public void filterFinishArray() {
    }

    public void filterFinishObject() {
    }

    public TokenFilter filterStartArray() {
        return this;
    }

    public TokenFilter filterStartObject() {
        return this;
    }

    public boolean includeBinary() {
        return _includeScalar();
    }

    public boolean includeBoolean(boolean z2) {
        return _includeScalar();
    }

    public TokenFilter includeElement(int i3) {
        return this;
    }

    public boolean includeEmbeddedValue(Object obj) {
        return _includeScalar();
    }

    public boolean includeEmptyArray(boolean z2) {
        return false;
    }

    public boolean includeEmptyObject(boolean z2) {
        return false;
    }

    public boolean includeNull() {
        return _includeScalar();
    }

    public boolean includeNumber(int i3) {
        return _includeScalar();
    }

    public TokenFilter includeProperty(String str) {
        return this;
    }

    public boolean includeRawValue() {
        return _includeScalar();
    }

    public TokenFilter includeRootValue(int i3) {
        return this;
    }

    public boolean includeString(String str) {
        return _includeScalar();
    }

    public boolean includeValue(JsonParser jsonParser) throws IOException {
        return _includeScalar();
    }

    public String toString() {
        return this == INCLUDE_ALL ? "TokenFilter.INCLUDE_ALL" : super.toString();
    }

    public boolean includeNumber(long j2) {
        return _includeScalar();
    }

    public boolean includeString(Reader reader, int i3) {
        return _includeScalar();
    }

    public boolean includeNumber(float f2) {
        return _includeScalar();
    }

    public boolean includeNumber(double d2) {
        return _includeScalar();
    }

    public boolean includeNumber(BigDecimal bigDecimal) {
        return _includeScalar();
    }

    public boolean includeNumber(BigInteger bigInteger) {
        return _includeScalar();
    }
}
