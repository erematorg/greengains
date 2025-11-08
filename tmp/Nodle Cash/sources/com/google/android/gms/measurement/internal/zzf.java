package com.google.android.gms.measurement.internal;

import androidx.annotation.WorkerThread;

abstract class zzf extends zzg {
    private boolean zza;

    public zzf(zzhw zzhw) {
        super(zzhw);
        this.zzu.zzaa();
    }

    public final void zzu() {
        if (!zzy()) {
            throw new IllegalStateException("Not initialized");
        }
    }

    public final void zzv() {
        if (this.zza) {
            throw new IllegalStateException("Can't initialize twice");
        } else if (!zzz()) {
            this.zzu.zzz();
            this.zza = true;
        }
    }

    public final void zzw() {
        if (!this.zza) {
            zzx();
            this.zzu.zzz();
            this.zza = true;
            return;
        }
        throw new IllegalStateException("Can't initialize twice");
    }

    @WorkerThread
    public void zzx() {
    }

    public final boolean zzy() {
        return this.zza;
    }

    public abstract boolean zzz();
}
