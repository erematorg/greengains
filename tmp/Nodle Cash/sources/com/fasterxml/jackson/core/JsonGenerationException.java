package com.fasterxml.jackson.core;

import com.fasterxml.jackson.core.exc.StreamWriteException;

public class JsonGenerationException extends StreamWriteException {
    private static final long serialVersionUID = 123;

    @Deprecated
    public JsonGenerationException(Throwable th) {
        super(th, (JsonGenerator) null);
    }

    @Deprecated
    public JsonGenerationException(String str) {
        super(str, (JsonGenerator) null);
    }

    public JsonGenerator getProcessor() {
        return this._processor;
    }

    public JsonGenerationException withGenerator(JsonGenerator jsonGenerator) {
        this._processor = jsonGenerator;
        return this;
    }

    @Deprecated
    public JsonGenerationException(String str, Throwable th) {
        super(str, th, (JsonGenerator) null);
    }

    public JsonGenerationException(Throwable th, JsonGenerator jsonGenerator) {
        super(th, jsonGenerator);
    }

    public JsonGenerationException(String str, JsonGenerator jsonGenerator) {
        super(str, jsonGenerator);
        this._processor = jsonGenerator;
    }

    public JsonGenerationException(String str, Throwable th, JsonGenerator jsonGenerator) {
        super(str, th, jsonGenerator);
        this._processor = jsonGenerator;
    }
}
