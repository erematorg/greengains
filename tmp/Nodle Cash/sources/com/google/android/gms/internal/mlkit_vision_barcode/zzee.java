package com.google.android.gms.internal.mlkit_vision_barcode;

import java.util.concurrent.Executor;

enum zzee implements Executor {
    INSTANCE;

    public final void execute(Runnable runnable) {
        runnable.run();
    }

    public final String toString() {
        return "MoreExecutors.directExecutor()";
    }
}
