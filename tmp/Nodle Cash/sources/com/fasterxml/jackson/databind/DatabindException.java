package com.fasterxml.jackson.databind;

import com.fasterxml.jackson.core.JsonLocation;
import com.fasterxml.jackson.core.JsonProcessingException;

public abstract class DatabindException extends JsonProcessingException {
    private static final long serialVersionUID = 3;

    public DatabindException(String str, JsonLocation jsonLocation, Throwable th) {
        super(str, jsonLocation, th);
    }

    public abstract void prependPath(Object obj, int i3);

    public abstract void prependPath(Object obj, String str);

    public DatabindException(String str) {
        super(str);
    }

    public DatabindException(String str, JsonLocation jsonLocation) {
        this(str, jsonLocation, (Throwable) null);
    }

    public DatabindException(String str, Throwable th) {
        this(str, (JsonLocation) null, th);
    }
}
