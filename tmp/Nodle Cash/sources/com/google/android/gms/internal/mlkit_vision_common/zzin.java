package com.google.android.gms.internal.mlkit_vision_common;

public final class zzin {
    /* access modifiers changed from: private */
    public Long zza;
    /* access modifiers changed from: private */
    public zzio zzb;
    /* access modifiers changed from: private */
    public zzii zzc;
    /* access modifiers changed from: private */
    public Integer zzd;
    /* access modifiers changed from: private */
    public Integer zze;
    /* access modifiers changed from: private */
    public Integer zzf;
    /* access modifiers changed from: private */
    public Integer zzg;

    public final zzin zzb(Long l2) {
        this.zza = Long.valueOf(l2.longValue() & Long.MAX_VALUE);
        return this;
    }

    public final zzin zzc(Integer num) {
        this.zzd = Integer.valueOf(num.intValue() & Integer.MAX_VALUE);
        return this;
    }

    public final zzin zzd(zzii zzii) {
        this.zzc = zzii;
        return this;
    }

    public final zzin zze(Integer num) {
        this.zzf = Integer.valueOf(num.intValue() & Integer.MAX_VALUE);
        return this;
    }

    public final zzin zzf(zzio zzio) {
        this.zzb = zzio;
        return this;
    }

    public final zzin zzg(Integer num) {
        this.zze = Integer.valueOf(num.intValue() & Integer.MAX_VALUE);
        return this;
    }

    public final zzin zzh(Integer num) {
        this.zzg = Integer.valueOf(num.intValue() & Integer.MAX_VALUE);
        return this;
    }

    public final zziq zzj() {
        return new zziq(this, (zzip) null);
    }
}
