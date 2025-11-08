package com.google.android.gms.internal.p002firebaseauthapi;

import javax.annotation.CheckForNull;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzar  reason: invalid package */
final class zzar extends zzaq {
    private final transient int zza;
    private final transient int zzb;
    private final /* synthetic */ zzaq zzc;

    public zzar(zzaq zzaq, int i3, int i4) {
        this.zzc = zzaq;
        this.zza = i3;
        this.zzb = i4;
    }

    public final Object get(int i3) {
        zzz.zza(i3, this.zzb);
        return this.zzc.get(i3 + this.zza);
    }

    public final int size() {
        return this.zzb;
    }

    public final int zza() {
        return this.zzc.zzb() + this.zza + this.zzb;
    }

    public final int zzb() {
        return this.zzc.zzb() + this.zza;
    }

    public final boolean zze() {
        return true;
    }

    @CheckForNull
    public final Object[] zzf() {
        return this.zzc.zzf();
    }

    /* renamed from: zza */
    public final zzaq subList(int i3, int i4) {
        zzz.zza(i3, i4, this.zzb);
        zzaq zzaq = this.zzc;
        int i5 = this.zza;
        return (zzaq) zzaq.subList(i3 + i5, i4 + i5);
    }
}
