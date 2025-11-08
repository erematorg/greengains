package com.google.common.util.concurrent.internal;

public abstract class InternalFutureFailureAccess {
    public abstract Throwable tryInternalFastPathGetFailure();
}
