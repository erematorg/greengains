package com.google.crypto.tink.internal;

public final class TinkBugException extends RuntimeException {
    public TinkBugException(String str) {
        super(str);
    }

    public TinkBugException(String str, Throwable th) {
        super(str, th);
    }

    public TinkBugException(Throwable th) {
        super(th);
    }
}
