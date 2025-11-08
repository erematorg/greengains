package com.google.zxing;

public abstract class ReaderException extends Exception {
    protected static final StackTraceElement[] NO_TRACE = new StackTraceElement[0];
    protected static boolean isStackTrace = (System.getProperty("surefire.test.class.path") != null);

    public ReaderException() {
    }

    public static void setStackTrace(boolean z2) {
        isStackTrace = z2;
    }

    public final synchronized Throwable fillInStackTrace() {
        return null;
    }

    public ReaderException(Throwable th) {
        super(th);
    }
}
