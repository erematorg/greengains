package com.google.android.gms.internal.mlkit_common;

import java.util.concurrent.Executor;

enum zzau implements Executor {
    INSTANCE;

    public final void execute(Runnable runnable) {
        runnable.run();
    }

    public final String toString() {
        return "MoreExecutors.directExecutor()";
    }
}
