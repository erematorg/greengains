package com.google.android.gms.measurement.internal;

abstract class zznr extends zzno {
    private boolean zza;

    public zznr(zznv zznv) {
        super(zznv);
        this.zzg.zzu();
    }

    public final void zzal() {
        if (!zzan()) {
            throw new IllegalStateException("Not initialized");
        }
    }

    public final void zzam() {
        if (!this.zza) {
            zzc();
            this.zzg.zzt();
            this.zza = true;
            return;
        }
        throw new IllegalStateException("Can't initialize twice");
    }

    public final boolean zzan() {
        return this.zza;
    }

    public abstract boolean zzc();
}
