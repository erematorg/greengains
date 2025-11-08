package com.google.android.gms.internal.p002firebaseauthapi;

import A.a;
import com.google.android.gms.internal.p002firebaseauthapi.zzdg;
import com.google.android.gms.internal.p002firebaseauthapi.zzdz;
import com.google.android.gms.internal.p002firebaseauthapi.zzis;
import java.security.GeneralSecurityException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import javax.annotation.Nullable;
import org.bouncycastle.jcajce.spec.XDHParameterSpec;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzjo  reason: invalid package */
public final class zzjo extends zzkp {
    /* access modifiers changed from: private */
    public static final Set<zzci> zza = ((Set) zzpf.zza(new zzjq()));
    private final zzb zzb;
    private final zzd zzc;
    @Nullable
    private final zzc zzd;
    private final zze zze;
    private final zzci zzf;
    @Nullable
    private final zzxv zzg;

    /* renamed from: com.google.android.gms.internal.firebase-auth-api.zzjo$zza */
    public static final class zza {
        private zzb zza;
        private zzd zzb;
        private zzc zzc;
        private zzci zzd;
        private zze zze;
        @Nullable
        private zzxv zzf;

        public final zza zza(zzb zzb2) {
            this.zza = zzb2;
            return this;
        }

        private zza() {
            this.zza = null;
            this.zzb = null;
            this.zzc = null;
            this.zzd = null;
            this.zze = zze.zzc;
            this.zzf = null;
        }

        public final zza zza(zzci zzci) throws GeneralSecurityException {
            if (zzjo.zza.contains(zzci)) {
                this.zzd = zzci;
                return this;
            }
            throw new GeneralSecurityException(a.l("Invalid DEM parameters ", String.valueOf(zzci), "; only AES128_GCM_RAW, AES256_GCM_RAW, AES128_CTR_HMAC_SHA256_RAW, AES256_CTR_HMAC_SHA256_RAW XCHACHA20_POLY1305_RAW and AES256_SIV_RAW are currently supported."));
        }

        public final zza zza(zzd zzd2) {
            this.zzb = zzd2;
            return this;
        }

        public final zza zza(zzc zzc2) {
            this.zzc = zzc2;
            return this;
        }

        public final zza zza(zzxv zzxv) {
            if (zzxv.zza() == 0) {
                this.zzf = null;
                return this;
            }
            this.zzf = zzxv;
            return this;
        }

        public final zza zza(zze zze2) {
            this.zze = zze2;
            return this;
        }

        public final zzjo zza() throws GeneralSecurityException {
            zzb zzb2 = this.zza;
            if (zzb2 == null) {
                throw new GeneralSecurityException("Elliptic curve type is not set");
            } else if (this.zzb == null) {
                throw new GeneralSecurityException("Hash type is not set");
            } else if (this.zzd == null) {
                throw new GeneralSecurityException("DEM parameters are not set");
            } else if (this.zze != null) {
                zzb zzb3 = zzb.zzd;
                if (zzb2 != zzb3 && this.zzc == null) {
                    throw new GeneralSecurityException("Point format is not set");
                } else if (zzb2 != zzb3 || this.zzc == null) {
                    return new zzjo(this.zza, this.zzb, this.zzc, this.zzd, this.zze, this.zzf);
                } else {
                    throw new GeneralSecurityException("For Curve25519 point format must not be set");
                }
            } else {
                throw new GeneralSecurityException("Variant is not set");
            }
        }
    }

    /* renamed from: com.google.android.gms.internal.firebase-auth-api.zzjo$zzb */
    public static final class zzb {
        public static final zzb zza = new zzb("NIST_P256");
        public static final zzb zzb = new zzb("NIST_P384");
        public static final zzb zzc = new zzb("NIST_P521");
        public static final zzb zzd = new zzb(XDHParameterSpec.X25519);
        private final String zze;

        private zzb(String str) {
            this.zze = str;
        }

        public final String toString() {
            return this.zze;
        }
    }

    /* renamed from: com.google.android.gms.internal.firebase-auth-api.zzjo$zzc */
    public static final class zzc {
        public static final zzc zza = new zzc("COMPRESSED");
        public static final zzc zzb = new zzc("UNCOMPRESSED");
        public static final zzc zzc = new zzc("LEGACY_UNCOMPRESSED");
        private final String zzd;

        private zzc(String str) {
            this.zzd = str;
        }

        public final String toString() {
            return this.zzd;
        }
    }

    /* renamed from: com.google.android.gms.internal.firebase-auth-api.zzjo$zzd */
    public static final class zzd {
        public static final zzd zza = new zzd("SHA1");
        public static final zzd zzb = new zzd("SHA224");
        public static final zzd zzc = new zzd("SHA256");
        public static final zzd zzd = new zzd("SHA384");
        public static final zzd zze = new zzd("SHA512");
        private final String zzf;

        private zzd(String str) {
            this.zzf = str;
        }

        public final String toString() {
            return this.zzf;
        }
    }

    /* renamed from: com.google.android.gms.internal.firebase-auth-api.zzjo$zze */
    public static final class zze {
        public static final zze zza = new zze("TINK");
        public static final zze zzb = new zze("CRUNCHY");
        public static final zze zzc = new zze("NO_PREFIX");
        private final String zzd;

        private zze(String str) {
            this.zzd = str;
        }

        public final String toString() {
            return this.zzd;
        }
    }

    public static zza zzc() {
        return new zza();
    }

    public static /* synthetic */ Set zzj() throws Exception {
        HashSet hashSet = new HashSet();
        zzdz.zza zzc2 = zzdz.zze().zza(12).zzb(16).zzc(16);
        zzdz.zzb zzb2 = zzdz.zzb.zzc;
        hashSet.add(zzc2.zza(zzb2).zza());
        hashSet.add(zzdz.zze().zza(12).zzb(32).zzc(16).zza(zzb2).zza());
        zzdg.zza zzc3 = zzdg.zzf().zza(16).zzb(32).zzd(16).zzc(16);
        zzdg.zzc zzc4 = zzdg.zzc.zzc;
        zzdg.zza zza2 = zzc3.zza(zzc4);
        zzdg.zzb zzb3 = zzdg.zzb.zzc;
        hashSet.add(zza2.zza(zzb3).zza());
        hashSet.add(zzdg.zzf().zza(32).zzb(32).zzd(32).zzc(16).zza(zzc4).zza(zzb3).zza());
        hashSet.add(zzgd.zzc());
        hashSet.add(zzis.zzc().zza(64).zza(zzis.zzb.zzc).zza());
        return Collections.unmodifiableSet(hashSet);
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof zzjo)) {
            return false;
        }
        zzjo zzjo = (zzjo) obj;
        return Objects.equals(zzjo.zzb, this.zzb) && Objects.equals(zzjo.zzc, this.zzc) && Objects.equals(zzjo.zzd, this.zzd) && Objects.equals(zzjo.zzf, this.zzf) && Objects.equals(zzjo.zze, this.zze) && Objects.equals(zzjo.zzg, this.zzg);
    }

    public final int hashCode() {
        return Objects.hash(new Object[]{zzjo.class, this.zzb, this.zzc, this.zzd, this.zzf, this.zze, this.zzg});
    }

    public final String toString() {
        return String.format("EciesParameters(curveType=%s, hashType=%s, pointFormat=%s, demParameters=%s, variant=%s, salt=%s)", new Object[]{this.zzb, this.zzc, this.zzd, this.zzf, this.zze, this.zzg});
    }

    public final boolean zza() {
        return this.zze != zze.zzc;
    }

    public final zzci zzb() {
        return this.zzf;
    }

    public final zzb zzd() {
        return this.zzb;
    }

    public final zzd zze() {
        return this.zzc;
    }

    @Nullable
    public final zzc zzf() {
        return this.zzd;
    }

    public final zze zzg() {
        return this.zze;
    }

    @Nullable
    public final zzxv zzh() {
        return this.zzg;
    }

    private zzjo(zzb zzb2, zzd zzd2, @Nullable zzc zzc2, zzci zzci, zze zze2, zzxv zzxv) {
        this.zzb = zzb2;
        this.zzc = zzd2;
        this.zzd = zzc2;
        this.zzf = zzci;
        this.zze = zze2;
        this.zzg = zzxv;
    }
}
