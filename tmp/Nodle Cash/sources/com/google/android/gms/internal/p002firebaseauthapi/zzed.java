package com.google.android.gms.internal.p002firebaseauthapi;

import com.google.android.gms.internal.p002firebaseauthapi.zzek;
import java.security.GeneralSecurityException;
import javax.annotation.Nullable;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzed  reason: invalid package */
public final class zzed extends zzcu {
    private final zzek zza;
    private final zzxw zzb;
    private final zzxv zzc;
    @Nullable
    private final Integer zzd;

    /* renamed from: com.google.android.gms.internal.firebase-auth-api.zzed$zza */
    public static class zza {
        @Nullable
        private zzek zza;
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

        public final zza zza(zzek zzek) {
            this.zza = zzek;
            return this;
        }

        public final zzed zza() throws GeneralSecurityException {
            zzxv zzb2;
            zzek zzek = this.zza;
            if (zzek == null || this.zzb == null) {
                throw new GeneralSecurityException("Cannot build without parameters and/or key material");
            } else if (zzek.zzb() != this.zzb.zza()) {
                throw new GeneralSecurityException("Key size mismatch");
            } else if (this.zza.zza() && this.zzc == null) {
                throw new GeneralSecurityException("Cannot create key without ID requirement with parameters with ID requirement");
            } else if (this.zza.zza() || this.zzc == null) {
                if (this.zza.zzd() == zzek.zzb.zzc) {
                    zzb2 = zznt.zza;
                } else if (this.zza.zzd() == zzek.zzb.zzb) {
                    zzb2 = zznt.zza(this.zzc.intValue());
                } else if (this.zza.zzd() == zzek.zzb.zza) {
                    zzb2 = zznt.zzb(this.zzc.intValue());
                } else {
                    throw new IllegalStateException("Unknown AesGcmSivParameters.Variant: ".concat(String.valueOf(this.zza.zzd())));
                }
                return new zzed(this.zza, this.zzb, zzb2, this.zzc);
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

    public final zzek zzc() {
        return this.zza;
    }

    public final zzxv zzd() {
        return this.zzc;
    }

    public final zzxw zze() {
        return this.zzb;
    }

    private zzed(zzek zzek, zzxw zzxw, zzxv zzxv, @Nullable Integer num) {
        this.zza = zzek;
        this.zzb = zzxw;
        this.zzc = zzxv;
        this.zzd = num;
    }
}
