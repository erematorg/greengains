package com.google.android.gms.internal.p002firebaseauthapi;

import java.security.GeneralSecurityException;
import java.util.Objects;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzjv  reason: invalid package */
public final class zzjv extends zzkp {
    private final zzd zza;
    private final zze zzb;
    private final zza zzc;
    private final zzf zzd;

    /* renamed from: com.google.android.gms.internal.firebase-auth-api.zzjv$zza */
    public static final class zza extends zzb {
        public static final zza zza = new zza("AES_128_GCM", 1);
        public static final zza zzb = new zza("AES_256_GCM", 2);
        public static final zza zzc = new zza("CHACHA20_POLY1305", 3);

        private zza(String str, int i3) {
            super(str, i3);
        }

        public final /* bridge */ /* synthetic */ String toString() {
            return super.toString();
        }
    }

    /* renamed from: com.google.android.gms.internal.firebase-auth-api.zzjv$zzb */
    public static class zzb {
        private final String zza;
        private final int zzb;

        public String toString() {
            return String.format("%s(0x%04x)", new Object[]{this.zza, Integer.valueOf(this.zzb)});
        }

        private zzb(String str, int i3) {
            this.zza = str;
            this.zzb = i3;
        }
    }

    /* renamed from: com.google.android.gms.internal.firebase-auth-api.zzjv$zzc */
    public static final class zzc {
        private zzd zza;
        private zze zzb;
        private zza zzc;
        private zzf zzd;

        public final zzc zza(zza zza2) {
            this.zzc = zza2;
            return this;
        }

        private zzc() {
            this.zza = null;
            this.zzb = null;
            this.zzc = null;
            this.zzd = zzf.zzc;
        }

        public final zzc zza(zze zze) {
            this.zzb = zze;
            return this;
        }

        public final zzc zza(zzd zzd2) {
            this.zza = zzd2;
            return this;
        }

        public final zzc zza(zzf zzf) {
            this.zzd = zzf;
            return this;
        }

        public final zzjv zza() throws GeneralSecurityException {
            zzd zzd2 = this.zza;
            if (zzd2 != null) {
                zze zze = this.zzb;
                if (zze != null) {
                    zza zza2 = this.zzc;
                    if (zza2 != null) {
                        zzf zzf = this.zzd;
                        if (zzf != null) {
                            return new zzjv(zzd2, zze, zza2, zzf);
                        }
                        throw new GeneralSecurityException("HPKE variant is not set");
                    }
                    throw new GeneralSecurityException("HPKE AEAD parameter is not set");
                }
                throw new GeneralSecurityException("HPKE KDF parameter is not set");
            }
            throw new GeneralSecurityException("HPKE KEM parameter is not set");
        }
    }

    /* renamed from: com.google.android.gms.internal.firebase-auth-api.zzjv$zzd */
    public static final class zzd extends zzb {
        public static final zzd zza = new zzd("DHKEM_P256_HKDF_SHA256", 16);
        public static final zzd zzb = new zzd("DHKEM_P384_HKDF_SHA384", 17);
        public static final zzd zzc = new zzd("DHKEM_P521_HKDF_SHA512", 18);
        public static final zzd zzd = new zzd("DHKEM_X25519_HKDF_SHA256", 32);

        private zzd(String str, int i3) {
            super(str, i3);
        }

        public final /* bridge */ /* synthetic */ String toString() {
            return super.toString();
        }
    }

    /* renamed from: com.google.android.gms.internal.firebase-auth-api.zzjv$zze */
    public static final class zze extends zzb {
        public static final zze zza = new zze("HKDF_SHA256", 1);
        public static final zze zzb = new zze("HKDF_SHA384", 2);
        public static final zze zzc = new zze("HKDF_SHA512", 3);

        private zze(String str, int i3) {
            super(str, i3);
        }

        public final /* bridge */ /* synthetic */ String toString() {
            return super.toString();
        }
    }

    /* renamed from: com.google.android.gms.internal.firebase-auth-api.zzjv$zzf */
    public static final class zzf {
        public static final zzf zza = new zzf("TINK");
        public static final zzf zzb = new zzf("CRUNCHY");
        public static final zzf zzc = new zzf("NO_PREFIX");
        private final String zzd;

        private zzf(String str) {
            this.zzd = str;
        }

        public final String toString() {
            return this.zzd;
        }
    }

    public static zzc zzc() {
        return new zzc();
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof zzjv)) {
            return false;
        }
        zzjv zzjv = (zzjv) obj;
        return this.zza == zzjv.zza && this.zzb == zzjv.zzb && this.zzc == zzjv.zzc && this.zzd == zzjv.zzd;
    }

    public final int hashCode() {
        return Objects.hash(new Object[]{zzjv.class, this.zza, this.zzb, this.zzc, this.zzd});
    }

    public final boolean zza() {
        return this.zzd != zzf.zzc;
    }

    public final zza zzb() {
        return this.zzc;
    }

    public final zze zzd() {
        return this.zzb;
    }

    public final zzd zze() {
        return this.zza;
    }

    public final zzf zzf() {
        return this.zzd;
    }

    private zzjv(zzd zzd2, zze zze2, zza zza2, zzf zzf2) {
        this.zza = zzd2;
        this.zzb = zze2;
        this.zzc = zza2;
        this.zzd = zzf2;
    }
}
