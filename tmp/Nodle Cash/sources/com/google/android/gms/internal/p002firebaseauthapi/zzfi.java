package com.google.android.gms.internal.p002firebaseauthapi;

import android.support.v4.media.session.a;
import androidx.camera.camera2.internal.C0118y;
import com.reown.foundation.util.jwt.JwtUtilsKt;
import java.security.GeneralSecurityException;
import java.util.Objects;
import javax.annotation.Nullable;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzfi  reason: invalid package */
public final class zzfi extends zzcw {
    private final zzc zza;
    private final String zzb;
    private final zzb zzc;
    private final zzcw zzd;

    /* renamed from: com.google.android.gms.internal.firebase-auth-api.zzfi$zza */
    public static class zza {
        @Nullable
        private zzc zza;
        @Nullable
        private String zzb;
        @Nullable
        private zzb zzc;
        @Nullable
        private zzcw zzd;

        private zza() {
        }

        public final zza zza(zzcw zzcw) {
            this.zzd = zzcw;
            return this;
        }

        public final zza zza(zzb zzb2) {
            this.zzc = zzb2;
            return this;
        }

        public final zza zza(String str) {
            this.zzb = str;
            return this;
        }

        public final zza zza(zzc zzc2) {
            this.zza = zzc2;
            return this;
        }

        public final zzfi zza() throws GeneralSecurityException {
            if (this.zza == null) {
                this.zza = zzc.zzb;
            }
            if (this.zzb == null) {
                throw new GeneralSecurityException("kekUri must be set");
            } else if (this.zzc != null) {
                zzcw zzcw = this.zzd;
                if (zzcw == null) {
                    throw new GeneralSecurityException("dekParametersForNewKeys must be set");
                } else if (!zzcw.zza()) {
                    zzb zzb2 = this.zzc;
                    zzcw zzcw2 = this.zzd;
                    if ((zzb2.equals(zzb.zza) && (zzcw2 instanceof zzdz)) || ((zzb2.equals(zzb.zzc) && (zzcw2 instanceof zzeq)) || ((zzb2.equals(zzb.zzb) && (zzcw2 instanceof zzgd)) || ((zzb2.equals(zzb.zzd) && (zzcw2 instanceof zzdg)) || ((zzb2.equals(zzb.zze) && (zzcw2 instanceof zzdq)) || (zzb2.equals(zzb.zzf) && (zzcw2 instanceof zzek))))))) {
                        return new zzfi(this.zza, this.zzb, this.zzc, this.zzd);
                    }
                    throw new GeneralSecurityException(C0118y.g("Cannot use parsing strategy ", this.zzc.toString(), " when new keys are picked according to ", String.valueOf(this.zzd), JwtUtilsKt.JWT_DELIMITER));
                } else {
                    throw new GeneralSecurityException("dekParametersForNewKeys must not have ID Requirements");
                }
            } else {
                throw new GeneralSecurityException("dekParsingStrategy must be set");
            }
        }
    }

    /* renamed from: com.google.android.gms.internal.firebase-auth-api.zzfi$zzb */
    public static final class zzb {
        public static final zzb zza = new zzb("ASSUME_AES_GCM");
        public static final zzb zzb = new zzb("ASSUME_XCHACHA20POLY1305");
        public static final zzb zzc = new zzb("ASSUME_CHACHA20POLY1305");
        public static final zzb zzd = new zzb("ASSUME_AES_CTR_HMAC");
        public static final zzb zze = new zzb("ASSUME_AES_EAX");
        public static final zzb zzf = new zzb("ASSUME_AES_GCM_SIV");
        private final String zzg;

        private zzb(String str) {
            this.zzg = str;
        }

        public final String toString() {
            return this.zzg;
        }
    }

    /* renamed from: com.google.android.gms.internal.firebase-auth-api.zzfi$zzc */
    public static final class zzc {
        public static final zzc zza = new zzc("TINK");
        public static final zzc zzb = new zzc("NO_PREFIX");
        private final String zzc;

        private zzc(String str) {
            this.zzc = str;
        }

        public final String toString() {
            return this.zzc;
        }
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof zzfi)) {
            return false;
        }
        zzfi zzfi = (zzfi) obj;
        return zzfi.zzc.equals(this.zzc) && zzfi.zzd.equals(this.zzd) && zzfi.zzb.equals(this.zzb) && zzfi.zza.equals(this.zza);
    }

    public final int hashCode() {
        return Objects.hash(new Object[]{zzfi.class, this.zzb, this.zzc, this.zzd, this.zza});
    }

    public final String toString() {
        String str = this.zzb;
        String valueOf = String.valueOf(this.zzc);
        return a.r(C0118y.l("LegacyKmsEnvelopeAead Parameters (kekUri: ", str, ", dekParsingStrategy: ", valueOf, ", dekParametersForNewKeys: "), String.valueOf(this.zzd), ", variant: ", String.valueOf(this.zza), ")");
    }

    public final boolean zza() {
        return this.zza != zzc.zzb;
    }

    public final zzcw zzb() {
        return this.zzd;
    }

    public final zzc zzc() {
        return this.zza;
    }

    public final String zzd() {
        return this.zzb;
    }

    private zzfi(zzc zzc2, String str, zzb zzb2, zzcw zzcw) {
        this.zza = zzc2;
        this.zzb = str;
        this.zzc = zzb2;
        this.zzd = zzcw;
    }
}
