package com.google.android.gms.internal.p002firebaseauthapi;

import com.google.android.gms.internal.p002firebaseauthapi.zzdg;
import java.security.GeneralSecurityException;
import javax.annotation.Nullable;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzcz  reason: invalid package */
public final class zzcz extends zzcu {
    private final zzdg zza;
    private final zzxw zzb;
    private final zzxw zzc;
    private final zzxv zzd;
    @Nullable
    private final Integer zze;

    /* renamed from: com.google.android.gms.internal.firebase-auth-api.zzcz$zza */
    public static class zza {
        @Nullable
        private zzdg zza;
        @Nullable
        private zzxw zzb;
        @Nullable
        private zzxw zzc;
        @Nullable
        private Integer zzd;

        public final zza zza(zzxw zzxw) {
            this.zzb = zzxw;
            return this;
        }

        public final zza zzb(zzxw zzxw) {
            this.zzc = zzxw;
            return this;
        }

        private zza() {
            this.zza = null;
            this.zzb = null;
            this.zzc = null;
            this.zzd = null;
        }

        public final zza zza(@Nullable Integer num) {
            this.zzd = num;
            return this;
        }

        public final zza zza(zzdg zzdg) {
            this.zza = zzdg;
            return this;
        }

        public final zzcz zza() throws GeneralSecurityException {
            zzxv zzb2;
            zzdg zzdg = this.zza;
            if (zzdg == null) {
                throw new GeneralSecurityException("Cannot build without parameters");
            } else if (this.zzb == null || this.zzc == null) {
                throw new GeneralSecurityException("Cannot build without key material");
            } else if (zzdg.zzb() != this.zzb.zza()) {
                throw new GeneralSecurityException("AES key size mismatch");
            } else if (this.zza.zzc() != this.zzc.zza()) {
                throw new GeneralSecurityException("HMAC key size mismatch");
            } else if (this.zza.zza() && this.zzd == null) {
                throw new GeneralSecurityException("Cannot create key without ID requirement with parameters with ID requirement");
            } else if (this.zza.zza() || this.zzd == null) {
                if (this.zza.zzh() == zzdg.zzb.zzc) {
                    zzb2 = zznt.zza;
                } else if (this.zza.zzh() == zzdg.zzb.zzb) {
                    zzb2 = zznt.zza(this.zzd.intValue());
                } else if (this.zza.zzh() == zzdg.zzb.zza) {
                    zzb2 = zznt.zzb(this.zzd.intValue());
                } else {
                    throw new IllegalStateException("Unknown AesCtrHmacAeadParameters.Variant: ".concat(String.valueOf(this.zza.zzh())));
                }
                return new zzcz(this.zza, this.zzb, this.zzc, zzb2, this.zzd);
            } else {
                throw new GeneralSecurityException("Cannot create key with ID requirement with parameters without ID requirement");
            }
        }
    }

    public static zza zzb() {
        return new zza();
    }

    @Nullable
    public final Integer zza() {
        return this.zze;
    }

    public final zzdg zzc() {
        return this.zza;
    }

    public final zzxv zzd() {
        return this.zzd;
    }

    public final zzxw zze() {
        return this.zzb;
    }

    public final zzxw zzf() {
        return this.zzc;
    }

    private zzcz(zzdg zzdg, zzxw zzxw, zzxw zzxw2, zzxv zzxv, @Nullable Integer num) {
        this.zza = zzdg;
        this.zzb = zzxw;
        this.zzc = zzxw2;
        this.zzd = zzxv;
        this.zze = num;
    }
}
