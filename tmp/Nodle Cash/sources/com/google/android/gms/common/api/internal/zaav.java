package com.google.android.gms.common.api.internal;

import androidx.annotation.WorkerThread;

abstract class zaav implements Runnable {
    final /* synthetic */ zaaw zab;

    public /* synthetic */ zaav(zaaw zaaw, zaau zaau) {
        this.zab = zaaw;
    }

    @WorkerThread
    public final void run() {
        this.zab.zab.lock();
        try {
            if (!Thread.interrupted()) {
                zaa();
            }
        } catch (RuntimeException e3) {
            this.zab.zaa.zam(e3);
        } catch (Throwable th) {
            this.zab.zab.unlock();
            throw th;
        }
        this.zab.zab.unlock();
    }

    @WorkerThread
    public abstract void zaa();
}
