package com.google.android.gms.internal.maps;

import com.google.firebase.analytics.FirebaseAnalytics;
import javax.annotation.CheckForNull;

final class zzbh extends zzbi {
    final transient int zza;
    final transient int zzb;
    final /* synthetic */ zzbi zzc;

    public zzbh(zzbi zzbi, int i3, int i4) {
        this.zzc = zzbi;
        this.zza = i3;
        this.zzb = i4;
    }

    public final Object get(int i3) {
        zzba.zza(i3, this.zzb, FirebaseAnalytics.Param.INDEX);
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
    public final zzbi subList(int i3, int i4) {
        zzba.zzc(i3, i4, this.zzb);
        int i5 = this.zza;
        return this.zzc.subList(i3 + i5, i4 + i5);
    }
}
