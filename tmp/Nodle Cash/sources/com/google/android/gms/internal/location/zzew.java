package com.google.android.gms.internal.location;

import com.google.firebase.analytics.FirebaseAnalytics;
import javax.annotation.CheckForNull;

final class zzew extends zzex {
    final transient int zza;
    final transient int zzb;
    final /* synthetic */ zzex zzc;

    public zzew(zzex zzex, int i3, int i4) {
        this.zzc = zzex;
        this.zza = i3;
        this.zzb = i4;
    }

    public final Object get(int i3) {
        zzer.zzc(i3, this.zzb, FirebaseAnalytics.Param.INDEX);
        return this.zzc.get(i3 + this.zza);
    }

    public final int size() {
        return this.zzb;
    }

    @CheckForNull
    public final Object[] zzb() {
        return this.zzc.zzb();
    }

    public final int zzc() {
        return this.zzc.zzc() + this.zza;
    }

    public final int zzd() {
        return this.zzc.zzc() + this.zza + this.zzb;
    }

    public final boolean zzf() {
        return true;
    }

    /* renamed from: zzh */
    public final zzex subList(int i3, int i4) {
        zzer.zze(i3, i4, this.zzb);
        int i5 = this.zza;
        return this.zzc.subList(i3 + i5, i4 + i5);
    }
}
