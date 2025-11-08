package com.fasterxml.jackson.databind.exc;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonMappingException;
import java.io.Closeable;

public class ValueInstantiationException extends JsonMappingException {
    protected final JavaType _type;

    public ValueInstantiationException(JsonParser jsonParser, String str, JavaType javaType, Throwable th) {
        super((Closeable) jsonParser, str, th);
        this._type = javaType;
    }

    public static ValueInstantiationException from(JsonParser jsonParser, String str, JavaType javaType) {
        return new ValueInstantiationException(jsonParser, str, javaType);
    }

    public JavaType getType() {
        return this._type;
    }

    public static ValueInstantiationException from(JsonParser jsonParser, String str, JavaType javaType, Throwable th) {
        return new ValueInstantiationException(jsonParser, str, javaType, th);
    }

    public ValueInstantiationException(JsonParser jsonParser, String str, JavaType javaType) {
        super((Closeable) jsonParser, str);
        this._type = javaType;
    }
}
