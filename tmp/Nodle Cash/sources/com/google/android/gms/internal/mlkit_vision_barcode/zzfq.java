package com.google.android.gms.internal.mlkit_vision_barcode;

public final class zzfq {
    /* access modifiers changed from: private */
    public zzft zza;
    /* access modifiers changed from: private */
    public Integer zzb;
    /* access modifiers changed from: private */
    public zzqd zzc;

    public final zzfq zza(Integer num) {
        this.zzb = Integer.valueOf(num.intValue() & Integer.MAX_VALUE);
        return this;
    }

    public final zzfq zzb(zzqd zzqd) {
        this.zzc = zzqd;
        return this;
    }

    public final zzfq zzc(zzft zzft) {
        this.zza = zzft;
        return this;
    }

    public final zzfv zze() {
        return new zzfv(this, (zzfu) null);
    }
}
