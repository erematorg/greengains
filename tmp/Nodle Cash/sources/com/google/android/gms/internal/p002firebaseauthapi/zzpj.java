package com.google.android.gms.internal.p002firebaseauthapi;

import com.google.android.gms.internal.p002firebaseauthapi.zzpq;
import java.security.GeneralSecurityException;
import javax.annotation.Nullable;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzpj  reason: invalid package */
public final class zzpj extends zzqi {
    private final zzpq zza;
    private final zzxw zzb;
    private final zzxv zzc;
    @Nullable
    private final Integer zzd;

    /* renamed from: com.google.android.gms.internal.firebase-auth-api.zzpj$zza */
    public static class zza {
        @Nullable
        private zzpq zza;
        @Nullable
        private zzxw zzb;
        @Nullable
        private Integer zzc;

        public final zza zza(zzxw zzxw) throws GeneralSecurityException {
            this.zzb = zzxw;
            return this;
        }

        private zza() {
            this.zza = null;
            this.zzb = null;
            this.zzc = null;
        }

        public final zza zza(@Nullable Integer num) {
            this.zzc = num;
            return this;
        }

        public final zza zza(zzpq zzpq) {
            this.zza = zzpq;
            return this;
        }

        public final zzpj zza() throws GeneralSecurityException {
            zzxv zza2;
            zzpq zzpq = this.zza;
            if (zzpq == null || this.zzb == null) {
                throw new GeneralSecurityException("Cannot build without parameters and/or key material");
            } else if (zzpq.zzc() != this.zzb.zza()) {
                throw new GeneralSecurityException("Key size mismatch");
            } else if (this.zza.zza() && this.zzc == null) {
                throw new GeneralSecurityException("Cannot create key without ID requirement with parameters with ID requirement");
            } else if (this.zza.zza() || this.zzc == null) {
                if (this.zza.zze() == zzpq.zzb.zzd) {
                    zza2 = zznt.zza;
                } else if (this.zza.zze() == zzpq.zzb.zzc || this.zza.zze() == zzpq.zzb.zzb) {
                    zza2 = zznt.zza(this.zzc.intValue());
                } else if (this.zza.zze() == zzpq.zzb.zza) {
                    zza2 = zznt.zzb(this.zzc.intValue());
                } else {
                    throw new IllegalStateException("Unknown AesCmacParametersParameters.Variant: ".concat(String.valueOf(this.zza.zze())));
                }
                return new zzpj(this.zza, this.zzb, zza2, this.zzc);
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
        return this.zzd;
    }

    public final zzpq zzc() {
        return this.zza;
    }

    public final zzxv zzd() {
        return this.zzc;
    }

    public final zzxw zze() {
        return this.zzb;
    }

    private zzpj(zzpq zzpq, zzxw zzxw, zzxv zzxv, @Nullable Integer num) {
        this.zza = zzpq;
        this.zzb = zzxw;
        this.zzc = zzxv;
        this.zzd = num;
    }
}
