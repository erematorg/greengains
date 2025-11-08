package com.google.android.gms.internal.p002firebaseauthapi;

import A.a;
import java.util.Objects;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzeq  reason: invalid package */
public final class zzeq extends zzcw {
    private final zza zza;

    /* renamed from: com.google.android.gms.internal.firebase-auth-api.zzeq$zza */
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

    private zzeq(zza zza2) {
        this.zza = zza2;
    }

    public static zzeq zza(zza zza2) {
        return new zzeq(zza2);
    }

    public final boolean equals(Object obj) {
        return (obj instanceof zzeq) && ((zzeq) obj).zza == this.zza;
    }

    public final int hashCode() {
        return Objects.hash(new Object[]{zzeq.class, this.zza});
    }

    public final String toString() {
        return a.l("ChaCha20Poly1305 Parameters (variant: ", String.valueOf(this.zza), ")");
    }

    public final zza zzb() {
        return this.zza;
    }

    public final boolean zza() {
        return this.zza != zza.zzc;
    }
}
