package com.google.android.gms.internal.mlkit_vision_barcode;

import com.google.firebase.analytics.FirebaseAnalytics;
import javax.annotation.CheckForNull;

final class zzcr extends zzcs {
    final transient int zza;
    final transient int zzb;
    final /* synthetic */ zzcs zzc;

    public zzcr(zzcs zzcs, int i3, int i4) {
        this.zzc = zzcs;
        this.zza = i3;
        this.zzb = i4;
    }

    public final Object get(int i3) {
        zzaz.zza(i3, this.zzb, FirebaseAnalytics.Param.INDEX);
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
    public final zzcs subList(int i3, int i4) {
        zzaz.zze(i3, i4, this.zzb);
        int i5 = this.zza;
        return this.zzc.subList(i3 + i5, i4 + i5);
    }
}
