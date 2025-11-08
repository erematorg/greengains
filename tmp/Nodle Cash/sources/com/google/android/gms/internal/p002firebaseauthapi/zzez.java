package com.google.android.gms.internal.p002firebaseauthapi;

import androidx.camera.camera2.internal.C0118y;
import java.util.Objects;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzez  reason: invalid package */
public final class zzez extends zzcw {
    private final String zza;
    private final zza zzb;

    /* renamed from: com.google.android.gms.internal.firebase-auth-api.zzez$zza */
    public static final class zza {
        public static final zza zza = new zza("TINK");
        public static final zza zzb = new zza("NO_PREFIX");
        private final String zzc;

        private zza(String str) {
            this.zzc = str;
        }

        public final String toString() {
            return this.zzc;
        }
    }

    private zzez(String str, zza zza2) {
        this.zza = str;
        this.zzb = zza2;
    }

    public static zzez zza(String str, zza zza2) {
        return new zzez(str, zza2);
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof zzez)) {
            return false;
        }
        zzez zzez = (zzez) obj;
        return zzez.zza.equals(this.zza) && zzez.zzb.equals(this.zzb);
    }

    public final int hashCode() {
        return Objects.hash(new Object[]{zzez.class, this.zza, this.zzb});
    }

    public final String toString() {
        return C0118y.g("LegacyKmsAead Parameters (keyUri: ", this.zza, ", variant: ", String.valueOf(this.zzb), ")");
    }

    public final zza zzb() {
        return this.zzb;
    }

    public final String zzc() {
        return this.zza;
    }

    public final boolean zza() {
        return this.zzb != zza.zzb;
    }
}
