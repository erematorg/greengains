package com.google.android.gms.internal.mlkit_vision_common;

import com.google.firebase.analytics.FirebaseAnalytics;
import javax.annotation.CheckForNull;

final class zzo extends zzp {
    final transient int zza;
    final transient int zzb;
    final /* synthetic */ zzp zzc;

    public zzo(zzp zzp, int i3, int i4) {
        this.zzc = zzp;
        this.zza = i3;
        this.zzb = i4;
    }

    public final Object get(int i3) {
        zzf.zza(i3, this.zzb, FirebaseAnalytics.Param.INDEX);
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
    public final zzp subList(int i3, int i4) {
        zzf.zzc(i3, i4, this.zzb);
        zzp zzp = this.zzc;
        int i5 = this.zza;
        return zzp.subList(i3 + i5, i4 + i5);
    }
}
