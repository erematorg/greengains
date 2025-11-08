package com.google.android.gms.internal.p002firebaseauthapi;

import A.a;
import java.util.Objects;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzgd  reason: invalid package */
public final class zzgd extends zzcw {
    private final zza zza;

    /* renamed from: com.google.android.gms.internal.firebase-auth-api.zzgd$zza */
    public static final class zza {
        public static final zza zza = new zza("TINK");
        public static final zza zzb = new zza("CRUNCHY");
        public static final zza zzc = new zza("NO_PREFIX");
        private final String zzd;

        private zza(String str) {
            this.zzd = str;
        }

        public final String toString() {
            return this.zzd;
        }
    }

    private zzgd(zza zza2) {
        this.zza = zza2;
    }

    public static zzgd zza(zza zza2) {
        return new zzgd(zza2);
    }

    public static zzgd zzc() {
        return new zzgd(zza.zzc);
    }

    public final boolean equals(Object obj) {
        return (obj instanceof zzgd) && ((zzgd) obj).zza == this.zza;
    }

    public final int hashCode() {
        return Objects.hash(new Object[]{zzgd.class, this.zza});
    }

    public final String toString() {
        return a.l("XChaCha20Poly1305 Parameters (variant: ", String.valueOf(this.zza), ")");
    }

    public final zza zzb() {
        return this.zza;
    }

    public final boolean zza() {
        return this.zza != zza.zzc;
    }
}
