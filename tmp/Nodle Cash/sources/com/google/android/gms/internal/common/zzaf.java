package com.google.android.gms.internal.common;

import com.google.firebase.analytics.FirebaseAnalytics;
import javax.annotation.CheckForNull;

final class zzaf extends zzag {
    final transient int zza;
    final transient int zzb;
    final /* synthetic */ zzag zzc;

    public zzaf(zzag zzag, int i3, int i4) {
        this.zzc = zzag;
        this.zza = i3;
        this.zzb = i4;
    }

    public final Object get(int i3) {
        zzs.zza(i3, this.zzb, FirebaseAnalytics.Param.INDEX);
        return this.zzc.get(i3 + this.zza);
    }

    public final int size() {
        return this.zzb;
    }

    public final int zzb() {
        return this.zzc.zzc() + this.zza + this.zzb;
    }

    public final int zzc() {
        return this.zzc.zzc() + this.zza;
    }

    public final boolean zzf() {
        return true;
    }

    @CheckForNull
    public final Object[] zzg() {
        return this.zzc.zzg();
    }

    /* renamed from: zzh */
    public final zzag subList(int i3, int i4) {
        zzs.zzc(i3, i4, this.zzb);
        int i5 = this.zza;
        return this.zzc.subList(i3 + i5, i4 + i5);
    }
}
