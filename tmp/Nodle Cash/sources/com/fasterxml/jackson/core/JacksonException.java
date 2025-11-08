package com.fasterxml.jackson.core;

import java.io.IOException;

public abstract class JacksonException extends IOException {
    private static final long serialVersionUID = 123;

    public JacksonException(String str) {
        super(str);
    }

    public abstract JsonLocation getLocation();

    public abstract String getOriginalMessage();

    public abstract Object getProcessor();

    public JacksonException(Throwable th) {
        super(th);
    }

    public JacksonException(String str, Throwable th) {
        super(str, th);
    }
}
