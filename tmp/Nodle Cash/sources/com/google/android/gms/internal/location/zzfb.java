package com.google.android.gms.internal.location;

import java.util.concurrent.Executor;

enum zzfb implements Executor {
    INSTANCE;

    public final void execute(Runnable runnable) {
        runnable.run();
    }

    public final String toString() {
        return "MoreExecutors.directExecutor()";
    }
}
