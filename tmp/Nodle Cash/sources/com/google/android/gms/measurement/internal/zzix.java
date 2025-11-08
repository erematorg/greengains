package com.google.android.gms.measurement.internal;

abstract class zzix extends zziy {
    private boolean zza;

    public zzix(zzhw zzhw) {
        super(zzhw);
        this.zzu.zzaa();
    }

    public void zzaa() {
    }

    public final void zzac() {
        if (!zzaf()) {
            throw new IllegalStateException("Not initialized");
        }
    }

    public final void zzad() {
        if (this.zza) {
            throw new IllegalStateException("Can't initialize twice");
        } else if (!zzo()) {
            this.zzu.zzz();
            this.zza = true;
        }
    }

    public final void zzae() {
        if (!this.zza) {
            zzaa();
            this.zzu.zzz();
            this.zza = true;
            return;
        }
        throw new IllegalStateException("Can't initialize twice");
    }

    public final boolean zzaf() {
        return this.zza;
    }

    public abstract boolean zzo();
}
