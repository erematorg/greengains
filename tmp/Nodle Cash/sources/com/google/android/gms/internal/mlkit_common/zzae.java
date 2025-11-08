package com.google.android.gms.internal.mlkit_common;

import com.google.firebase.analytics.FirebaseAnalytics;
import javax.annotation.CheckForNull;

final class zzae extends zzaf {
    final transient int zza;
    final transient int zzb;
    final /* synthetic */ zzaf zzc;

    public zzae(zzaf zzaf, int i3, int i4) {
        this.zzc = zzaf;
        this.zza = i3;
        this.zzb = i4;
    }

    public final Object get(int i3) {
        zzt.zza(i3, this.zzb, FirebaseAnalytics.Param.INDEX);
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

    @CheckForNull
    public final Object[] zze() {
        return this.zzc.zze();
    }

    /* renamed from: zzf */
    public final zzaf subList(int i3, int i4) {
        zzt.zzd(i3, i4, this.zzb);
        int i5 = this.zza;
        return this.zzc.subList(i3 + i5, i4 + i5);
    }
}
