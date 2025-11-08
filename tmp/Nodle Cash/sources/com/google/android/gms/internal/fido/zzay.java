package com.google.android.gms.internal.fido;

import com.google.firebase.analytics.FirebaseAnalytics;
import javax.annotation.CheckForNull;

final class zzay extends zzaz {
    final transient int zza;
    final transient int zzb;
    final /* synthetic */ zzaz zzc;

    public zzay(zzaz zzaz, int i3, int i4) {
        this.zzc = zzaz;
        this.zza = i3;
        this.zzb = i4;
    }

    public final Object get(int i3) {
        zzap.zza(i3, this.zzb, FirebaseAnalytics.Param.INDEX);
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

    /* renamed from: zzg */
    public final zzaz subList(int i3, int i4) {
        zzap.zze(i3, i4, this.zzb);
        zzaz zzaz = this.zzc;
        int i5 = this.zza;
        return zzaz.subList(i3 + i5, i4 + i5);
    }
}
