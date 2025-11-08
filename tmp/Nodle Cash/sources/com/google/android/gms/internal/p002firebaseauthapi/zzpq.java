package com.google.android.gms.internal.p002firebaseauthapi;

import A.a;
import java.security.GeneralSecurityException;
import java.security.InvalidAlgorithmParameterException;
import java.util.Objects;
import javax.annotation.Nullable;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzpq  reason: invalid package */
public final class zzpq extends zzql {
    private final int zza;
    private final int zzb;
    private final zzb zzc;

    /* renamed from: com.google.android.gms.internal.firebase-auth-api.zzpq$zza */
    public static final class zza {
        @Nullable
        private Integer zza;
        @Nullable
        private Integer zzb;
        private zzb zzc;

        public final zza zza(int i3) throws GeneralSecurityException {
            if (i3 == 16 || i3 == 32) {
                this.zza = Integer.valueOf(i3);
                return this;
            }
            throw new InvalidAlgorithmParameterException(String.format("Invalid key size %d; only 128-bit and 256-bit AES keys are supported", new Object[]{Integer.valueOf(i3 << 3)}));
        }

        public final zza zzb(int i3) throws GeneralSecurityException {
            if (i3 < 10 || 16 < i3) {
                throw new GeneralSecurityException(a.k("Invalid tag size for AesCmacParameters: ", i3));
            }
            this.zzb = Integer.valueOf(i3);
            return this;
        }

        private zza() {
            this.zza = null;
            this.zzb = null;
            this.zzc = zzb.zzd;
        }

        public final zza zza(zzb zzb2) {
            this.zzc = zzb2;
            return this;
        }

        public final zzpq zza() throws GeneralSecurityException {
            Integer num = this.zza;
            if (num == null) {
                throw new GeneralSecurityException("key size not set");
            } else if (this.zzb == null) {
                throw new GeneralSecurityException("tag size not set");
            } else if (this.zzc != null) {
                return new zzpq(num.intValue(), this.zzb.intValue(), this.zzc);
            } else {
                throw new GeneralSecurityException("variant not set");
            }
        }
    }

    /* renamed from: com.google.android.gms.internal.firebase-auth-api.zzpq$zzb */
    public static final class zzb {
        public static final zzb zza = new zzb("TINK");
        public static final zzb zzb = new zzb("CRUNCHY");
        public static final zzb zzc = new zzb("LEGACY");
        public static final zzb zzd = new zzb("NO_PREFIX");
        private final String zze;

        private zzb(String str) {
            this.zze = str;
        }

        public final String toString() {
            return this.zze;
        }
    }

    public static zza zzd() {
        return new zza();
    }

    private final int zzf() {
        int i3;
        zzb zzb2 = this.zzc;
        if (zzb2 == zzb.zzd) {
            return this.zzb;
        }
        if (zzb2 == zzb.zza) {
            i3 = this.zzb;
        } else if (zzb2 == zzb.zzb) {
            i3 = this.zzb;
        } else if (zzb2 == zzb.zzc) {
            i3 = this.zzb;
        } else {
            throw new IllegalStateException("Unknown variant");
        }
        return i3 + 5;
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof zzpq)) {
            return false;
        }
        zzpq zzpq = (zzpq) obj;
        return zzpq.zza == this.zza && zzpq.zzf() == zzf() && zzpq.zzc == this.zzc;
    }

    public final int hashCode() {
        return Objects.hash(new Object[]{zzpq.class, Integer.valueOf(this.zza), Integer.valueOf(this.zzb), this.zzc});
    }

    public final String toString() {
        String valueOf = String.valueOf(this.zzc);
        int i3 = this.zzb;
        return a.m(android.support.v4.media.session.a.x("AES-CMAC Parameters (variant: ", valueOf, ", ", i3, "-byte tags, and "), "-byte key)", this.zza);
    }

    public final boolean zza() {
        return this.zzc != zzb.zzd;
    }

    public final int zzb() {
        return this.zzb;
    }

    public final int zzc() {
        return this.zza;
    }

    public final zzb zze() {
        return this.zzc;
    }

    private zzpq(int i3, int i4, zzb zzb2) {
        this.zza = i3;
        this.zzb = i4;
        this.zzc = zzb2;
    }
}
