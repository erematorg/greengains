package com.google.android.gms.internal.mlkit_vision_common;

import com.google.firebase.analytics.FirebaseAnalytics;

final class zzu extends zzp {
    static final zzp zza = new zzu(new Object[0], 0);
    final transient Object[] zzb;
    private final transient int zzc;

    public zzu(Object[] objArr, int i3) {
        this.zzb = objArr;
        this.zzc = i3;
    }

    public final Object get(int i3) {
        zzf.zza(i3, this.zzc, FirebaseAnalytics.Param.INDEX);
        Object obj = this.zzb[i3];
        obj.getClass();
        return obj;
    }

    public final int size() {
        return this.zzc;
    }

    public final int zza(Object[] objArr, int i3) {
        System.arraycopy(this.zzb, 0, objArr, 0, this.zzc);
        return this.zzc;
    }

    public final int zzb() {
        return this.zzc;
    }

    public final int zzc() {
        return 0;
    }

    public final Object[] zze() {
        return this.zzb;
    }
}
