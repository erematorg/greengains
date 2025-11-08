package com.google.android.gms.internal.p002firebaseauthapi;

import com.google.android.gms.internal.p002firebaseauthapi.zzqf;
import java.security.GeneralSecurityException;
import javax.annotation.Nullable;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzpw  reason: invalid package */
public final class zzpw extends zzqi {
    private final zzqf zza;
    private final zzxw zzb;
    private final zzxv zzc;
    @Nullable
    private final Integer zzd;

    /* renamed from: com.google.android.gms.internal.firebase-auth-api.zzpw$zza */
    public static class zza {
        @Nullable
        private zzqf zza;
        @Nullable
        private zzxw zzb;
        @Nullable
        private Integer zzc;

        public final zza zza(@Nullable Integer num) {
            this.zzc = num;
            return this;
        }

        private zza() {
            this.zza = null;
            this.zzb = null;
            this.zzc = null;
        }

        public final zza zza(zzxw zzxw) {
            this.zzb = zzxw;
            return this;
        }

        public final zza zza(zzqf zzqf) {
            this.zza = zzqf;
            return this;
        }

        public final zzpw zza() throws GeneralSecurityException {
            zzxv zza2;
            zzqf zzqf = this.zza;
            if (zzqf == null || this.zzb == null) {
                throw new GeneralSecurityException("Cannot build without parameters and/or key material");
            } else if (zzqf.zzc() != this.zzb.zza()) {
                throw new GeneralSecurityException("Key size mismatch");
            } else if (this.zza.zza() && this.zzc == null) {
                throw new GeneralSecurityException("Cannot create key without ID requirement with parameters with ID requirement");
            } else if (this.zza.zza() || this.zzc == null) {
                if (this.zza.zzf() == zzqf.zzc.zzd) {
                    zza2 = zznt.zza;
                } else if (this.zza.zzf() == zzqf.zzc.zzc || this.zza.zzf() == zzqf.zzc.zzb) {
                    zza2 = zznt.zza(this.zzc.intValue());
                } else if (this.zza.zzf() == zzqf.zzc.zza) {
                    zza2 = zznt.zzb(this.zzc.intValue());
                } else {
                    throw new IllegalStateException("Unknown HmacParameters.Variant: ".concat(String.valueOf(this.zza.zzf())));
                }
                return new zzpw(this.zza, this.zzb, zza2, this.zzc);
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

    public final zzqf zzc() {
        return this.zza;
    }

    public final zzxv zzd() {
        return this.zzc;
    }

    public final zzxw zze() {
        return this.zzb;
    }

    private zzpw(zzqf zzqf, zzxw zzxw, zzxv zzxv, @Nullable Integer num) {
        this.zza = zzqf;
        this.zzb = zzxw;
        this.zzc = zzxv;
        this.zzd = num;
    }
}
