package com.google.android.gms.internal.p002firebaseauthapi;

import androidx.camera.camera2.internal.C0118y;
import java.security.GeneralSecurityException;
import java.security.InvalidAlgorithmParameterException;
import java.util.Objects;
import javax.annotation.Nullable;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzqf  reason: invalid package */
public final class zzqf extends zzql {
    private final int zza;
    private final int zzb;
    private final zzc zzc;
    private final zzb zzd;

    /* renamed from: com.google.android.gms.internal.firebase-auth-api.zzqf$zza */
    public static final class zza {
        @Nullable
        private Integer zza;
        @Nullable
        private Integer zzb;
        private zzb zzc;
        private zzc zzd;

        public final zza zza(zzb zzb2) {
            this.zzc = zzb2;
            return this;
        }

        public final zza zzb(int i3) throws GeneralSecurityException {
            this.zzb = Integer.valueOf(i3);
            return this;
        }

        private zza() {
            this.zza = null;
            this.zzb = null;
            this.zzc = null;
            this.zzd = zzc.zzd;
        }

        public final zza zza(int i3) throws GeneralSecurityException {
            this.zza = Integer.valueOf(i3);
            return this;
        }

        public final zza zza(zzc zzc2) {
            this.zzd = zzc2;
            return this;
        }

        public final zzqf zza() throws GeneralSecurityException {
            Integer num = this.zza;
            if (num == null) {
                throw new GeneralSecurityException("key size is not set");
            } else if (this.zzb == null) {
                throw new GeneralSecurityException("tag size is not set");
            } else if (this.zzc == null) {
                throw new GeneralSecurityException("hash type is not set");
            } else if (this.zzd == null) {
                throw new GeneralSecurityException("variant is not set");
            } else if (num.intValue() >= 16) {
                Integer num2 = this.zzb;
                int intValue = num2.intValue();
                zzb zzb2 = this.zzc;
                if (intValue >= 10) {
                    if (zzb2 == zzb.zza) {
                        if (intValue > 20) {
                            throw new GeneralSecurityException(String.format("Invalid tag size in bytes %d; can be at most 20 bytes for SHA1", new Object[]{num2}));
                        }
                    } else if (zzb2 == zzb.zzb) {
                        if (intValue > 28) {
                            throw new GeneralSecurityException(String.format("Invalid tag size in bytes %d; can be at most 28 bytes for SHA224", new Object[]{num2}));
                        }
                    } else if (zzb2 == zzb.zzc) {
                        if (intValue > 32) {
                            throw new GeneralSecurityException(String.format("Invalid tag size in bytes %d; can be at most 32 bytes for SHA256", new Object[]{num2}));
                        }
                    } else if (zzb2 == zzb.zzd) {
                        if (intValue > 48) {
                            throw new GeneralSecurityException(String.format("Invalid tag size in bytes %d; can be at most 48 bytes for SHA384", new Object[]{num2}));
                        }
                    } else if (zzb2 != zzb.zze) {
                        throw new GeneralSecurityException("unknown hash type; must be SHA256, SHA384 or SHA512");
                    } else if (intValue > 64) {
                        throw new GeneralSecurityException(String.format("Invalid tag size in bytes %d; can be at most 64 bytes for SHA512", new Object[]{num2}));
                    }
                    return new zzqf(this.zza.intValue(), this.zzb.intValue(), this.zzd, this.zzc);
                }
                throw new GeneralSecurityException(String.format("Invalid tag size in bytes %d; must be at least 10 bytes", new Object[]{num2}));
            } else {
                throw new InvalidAlgorithmParameterException(String.format("Invalid key size in bytes %d; must be at least 16 bytes", new Object[]{this.zza}));
            }
        }
    }

    /* renamed from: com.google.android.gms.internal.firebase-auth-api.zzqf$zzb */
    public static final class zzb {
        public static final zzb zza = new zzb("SHA1");
        public static final zzb zzb = new zzb("SHA224");
        public static final zzb zzc = new zzb("SHA256");
        public static final zzb zzd = new zzb("SHA384");
        public static final zzb zze = new zzb("SHA512");
        private final String zzf;

        private zzb(String str) {
            this.zzf = str;
        }

        public final String toString() {
            return this.zzf;
        }
    }

    /* renamed from: com.google.android.gms.internal.firebase-auth-api.zzqf$zzc */
    public static final class zzc {
        public static final zzc zza = new zzc("TINK");
        public static final zzc zzb = new zzc("CRUNCHY");
        public static final zzc zzc = new zzc("LEGACY");
        public static final zzc zzd = new zzc("NO_PREFIX");
        private final String zze;

        private zzc(String str) {
            this.zze = str;
        }

        public final String toString() {
            return this.zze;
        }
    }

    public static zza zzd() {
        return new zza();
    }

    private final int zzg() {
        int i3;
        zzc zzc2 = this.zzc;
        if (zzc2 == zzc.zzd) {
            return this.zzb;
        }
        if (zzc2 == zzc.zza) {
            i3 = this.zzb;
        } else if (zzc2 == zzc.zzb) {
            i3 = this.zzb;
        } else if (zzc2 == zzc.zzc) {
            i3 = this.zzb;
        } else {
            throw new IllegalStateException("Unknown variant");
        }
        return i3 + 5;
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof zzqf)) {
            return false;
        }
        zzqf zzqf = (zzqf) obj;
        return zzqf.zza == this.zza && zzqf.zzg() == zzg() && zzqf.zzc == this.zzc && zzqf.zzd == this.zzd;
    }

    public final int hashCode() {
        return Objects.hash(new Object[]{zzqf.class, Integer.valueOf(this.zza), Integer.valueOf(this.zzb), this.zzc, this.zzd});
    }

    public final String toString() {
        String valueOf = String.valueOf(this.zzc);
        String valueOf2 = String.valueOf(this.zzd);
        int i3 = this.zzb;
        int i4 = this.zza;
        StringBuilder l2 = C0118y.l("HMAC Parameters (variant: ", valueOf, ", hashType: ", valueOf2, ", ");
        l2.append(i3);
        l2.append("-byte tags, and ");
        l2.append(i4);
        l2.append("-byte key)");
        return l2.toString();
    }

    public final boolean zza() {
        return this.zzc != zzc.zzd;
    }

    public final int zzb() {
        return this.zzb;
    }

    public final int zzc() {
        return this.zza;
    }

    public final zzb zze() {
        return this.zzd;
    }

    public final zzc zzf() {
        return this.zzc;
    }

    private zzqf(int i3, int i4, zzc zzc2, zzb zzb2) {
        this.zza = i3;
        this.zzb = i4;
        this.zzc = zzc2;
        this.zzd = zzb2;
    }
}
