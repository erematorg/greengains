package com.fasterxml.jackson.core;

import androidx.constraintlayout.core.state.b;
import org.web3j.utils.RevertReasonExtractor;

public class JsonProcessingException extends JacksonException {
    private static final long serialVersionUID = 123;
    protected JsonLocation _location;

    public JsonProcessingException(String str, JsonLocation jsonLocation, Throwable th) {
        super(str, th);
        this._location = jsonLocation;
    }

    public void clearLocation() {
        this._location = null;
    }

    public JsonLocation getLocation() {
        return this._location;
    }

    public String getMessage() {
        String message = super.getMessage();
        if (message == null) {
            message = RevertReasonExtractor.MISSING_REASON;
        }
        JsonLocation location = getLocation();
        String messageSuffix = getMessageSuffix();
        if (location == null && messageSuffix == null) {
            return message;
        }
        StringBuilder o3 = b.o(100, message);
        if (messageSuffix != null) {
            o3.append(messageSuffix);
        }
        if (location != null) {
            o3.append("\n at ");
            o3.append(location.toString());
        }
        return o3.toString();
    }

    public String getMessageSuffix() {
        return null;
    }

    public String getOriginalMessage() {
        return super.getMessage();
    }

    public Object getProcessor() {
        return null;
    }

    public String toString() {
        return getClass().getName() + ": " + getMessage();
    }

    public JsonProcessingException(String str) {
        super(str);
    }

    public JsonProcessingException(String str, JsonLocation jsonLocation) {
        this(str, jsonLocation, (Throwable) null);
    }

    public JsonProcessingException(String str, Throwable th) {
        this(str, (JsonLocation) null, th);
    }

    public JsonProcessingException(Throwable th) {
        this((String) null, (JsonLocation) null, th);
    }
}
