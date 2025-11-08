package com.google.android.gms.internal.p002firebaseauthapi;

import android.support.v4.media.session.a;
import java.security.GeneralSecurityException;
import java.security.InvalidAlgorithmParameterException;
import java.util.Objects;
import javax.annotation.Nullable;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzdz  reason: invalid package */
public final class zzdz extends zzcw {
    private final int zza;
    private final int zzb;
    private final int zzc;
    private final zzb zzd;

    /* renamed from: com.google.android.gms.internal.firebase-auth-api.zzdz$zza */
    public static final class zza {
        @Nullable
        private Integer zza;
        @Nullable
        private Integer zzb;
        @Nullable
        private Integer zzc;
        private zzb zzd;

        public final zza zza(int i3) throws GeneralSecurityException {
            this.zzb = 12;
            return this;
        }

        public final zza zzb(int i3) throws GeneralSecurityException {
            if (i3 == 16 || i3 == 24 || i3 == 32) {
                this.zza = Integer.valueOf(i3);
                return this;
            }
            throw new InvalidAlgorithmParameterException(String.format("Invalid key size %d; only 16-byte, 24-byte and 32-byte AES keys are supported", new Object[]{Integer.valueOf(i3)}));
        }

        public final zza zzc(int i3) throws GeneralSecurityException {
            this.zzc = 16;
            return this;
        }

        private zza() {
            this.zza = null;
            this.zzb = null;
            this.zzc = null;
            this.zzd = zzb.zzc;
        }

        public final zza zza(zzb zzb2) {
            this.zzd = zzb2;
            return this;
        }

        public final zzdz zza() throws GeneralSecurityException {
            Integer num = this.zza;
            if (num == null) {
                throw new GeneralSecurityException("Key size is not set");
            } else if (this.zzd == null) {
                throw new GeneralSecurityException("Variant is not set");
            } else if (this.zzb == null) {
                throw new GeneralSecurityException("IV size is not set");
            } else if (this.zzc != null) {
                return new zzdz(num.intValue(), this.zzb.intValue(), this.zzc.intValue(), this.zzd);
            } else {
                throw new GeneralSecurityException("Tag size is not set");
            }
        }
    }

    /* renamed from: com.google.android.gms.internal.firebase-auth-api.zzdz$zzb */
    public static final class zzb {
        public static final zzb zza = new zzb("TINK");
        public static final zzb zzb = new zzb("CRUNCHY");
        public static final zzb zzc = new zzb("NO_PREFIX");
        private final String zzd;

        private zzb(String str) {
            this.zzd = str;
        }

        public final String toString() {
            return this.zzd;
        }
    }

    public static zza zze() {
        return new zza();
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof zzdz)) {
            return false;
        }
        zzdz zzdz = (zzdz) obj;
        return zzdz.zza == this.zza && zzdz.zzb == this.zzb && zzdz.zzc == this.zzc && zzdz.zzd == this.zzd;
    }

    public final int hashCode() {
        return Objects.hash(new Object[]{zzdz.class, Integer.valueOf(this.zza), Integer.valueOf(this.zzb), Integer.valueOf(this.zzc), this.zzd});
    }

    public final String toString() {
        String valueOf = String.valueOf(this.zzd);
        int i3 = this.zzb;
        int i4 = this.zzc;
        int i5 = this.zza;
        StringBuilder x2 = a.x("AesGcm Parameters (variant: ", valueOf, ", ", i3, "-byte IV, ");
        x2.append(i4);
        x2.append("-byte tag, and ");
        x2.append(i5);
        x2.append("-byte key)");
        return x2.toString();
    }

    public final boolean zza() {
        return this.zzd != zzb.zzc;
    }

    public final int zzb() {
        return this.zzb;
    }

    public final int zzc() {
        return this.zza;
    }

    public final int zzd() {
        return this.zzc;
    }

    public final zzb zzf() {
        return this.zzd;
    }

    private zzdz(int i3, int i4, int i5, zzb zzb2) {
        this.zza = i3;
        this.zzb = i4;
        this.zzc = i5;
        this.zzd = zzb2;
    }
}
