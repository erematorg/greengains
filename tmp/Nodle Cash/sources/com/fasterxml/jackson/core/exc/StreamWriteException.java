package com.fasterxml.jackson.core.exc;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonLocation;
import com.fasterxml.jackson.core.JsonProcessingException;

public abstract class StreamWriteException extends JsonProcessingException {
    private static final long serialVersionUID = 2;
    protected transient JsonGenerator _processor;

    public StreamWriteException(Throwable th, JsonGenerator jsonGenerator) {
        super(th);
        this._processor = jsonGenerator;
    }

    public abstract StreamWriteException withGenerator(JsonGenerator jsonGenerator);

    public JsonGenerator getProcessor() {
        return this._processor;
    }

    public StreamWriteException(String str, JsonGenerator jsonGenerator) {
        super(str, (JsonLocation) null);
        this._processor = jsonGenerator;
    }

    public StreamWriteException(String str, Throwable th, JsonGenerator jsonGenerator) {
        super(str, (JsonLocation) null, th);
        this._processor = jsonGenerator;
    }
}
