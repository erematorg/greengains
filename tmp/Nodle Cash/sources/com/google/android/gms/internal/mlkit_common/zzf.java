package com.google.android.gms.internal.mlkit_common;

public final class zzf {
    private final zzac zza = new zzac();
    private Boolean zzb;

    private zzf() {
    }

    public final zzf zza(zzk zzk) {
        zzt.zzc(this.zzb, "Must call internal() or external() before appending rules.");
        this.zza.zzb(zzk);
        return this;
    }

    public final zzf zzb() {
        zzt.zze(this.zzb == null, "A SourcePolicy can only set internal() or external() once.");
        this.zzb = Boolean.FALSE;
        return this;
    }

    public final zzf zzc() {
        zzt.zze(this.zzb == null, "A SourcePolicy can only set internal() or external() once.");
        this.zzb = Boolean.TRUE;
        return this;
    }

    public final zzh zzd() {
        zzt.zzc(this.zzb, "Must call internal() or external() when building a SourcePolicy.");
        return new zzh(this.zzb.booleanValue(), false, this.zza.zzc(), (zzg) null);
    }

    public /* synthetic */ zzf(zze zze) {
    }
}
