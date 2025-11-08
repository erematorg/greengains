package com.google.android.gms.internal.p002firebaseauthapi;

import A.a;
import com.google.android.gms.internal.p002firebaseauthapi.zzjv;
import java.security.GeneralSecurityException;
import java.security.spec.EllipticCurve;
import javax.annotation.Nullable;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzki  reason: invalid package */
public final class zzki extends zzkr {
    private final zzjv zza;
    private final zzxv zzb;
    private final zzxv zzc;
    @Nullable
    private final Integer zzd;

    private zzki(zzjv zzjv, zzxv zzxv, zzxv zzxv2, @Nullable Integer num) {
        this.zza = zzjv;
        this.zzb = zzxv;
        this.zzc = zzxv2;
        this.zzd = num;
    }

    public static zzki zza(zzjv zzjv, zzxv zzxv, @Nullable Integer num) throws GeneralSecurityException {
        zzxv zzxv2;
        EllipticCurve ellipticCurve;
        zzjv.zzf zzf = zzjv.zzf();
        zzjv.zzf zzf2 = zzjv.zzf.zzc;
        if (!zzf.equals(zzf2) && num == null) {
            throw new GeneralSecurityException(a.l("'idRequirement' must be non-null for ", String.valueOf(zzf), " variant."));
        } else if (!zzf.equals(zzf2) || num == null) {
            zzjv.zzd zze = zzjv.zze();
            int zza2 = zzxv.zza();
            String str = "Encoded public key byte length for " + String.valueOf(zze) + " must be %d, not " + zza2;
            zzjv.zzd zzd2 = zzjv.zzd.zza;
            if (zze == zzd2) {
                if (zza2 != 65) {
                    throw new GeneralSecurityException(String.format(str, new Object[]{65}));
                }
            } else if (zze == zzjv.zzd.zzb) {
                if (zza2 != 97) {
                    throw new GeneralSecurityException(String.format(str, new Object[]{97}));
                }
            } else if (zze == zzjv.zzd.zzc) {
                if (zza2 != 133) {
                    throw new GeneralSecurityException(String.format(str, new Object[]{133}));
                }
            } else if (zze != zzjv.zzd.zzd) {
                throw new GeneralSecurityException("Unable to validate public key length for ".concat(String.valueOf(zze)));
            } else if (zza2 != 32) {
                throw new GeneralSecurityException(String.format(str, new Object[]{32}));
            }
            if (zze == zzd2 || zze == zzjv.zzd.zzb || zze == zzjv.zzd.zzc) {
                if (zze == zzd2) {
                    ellipticCurve = zzmf.zza.getCurve();
                } else if (zze == zzjv.zzd.zzb) {
                    ellipticCurve = zzmf.zzb.getCurve();
                } else if (zze == zzjv.zzd.zzc) {
                    ellipticCurve = zzmf.zzc.getCurve();
                } else {
                    throw new IllegalArgumentException("Unable to determine NIST curve type for ".concat(String.valueOf(zze)));
                }
                zzmf.zza(zzwr.zza(ellipticCurve, zzwt.UNCOMPRESSED, zzxv.zzb()), ellipticCurve);
            }
            zzjv.zzf zzf3 = zzjv.zzf();
            if (zzf3 == zzf2) {
                zzxv2 = zznt.zza;
            } else if (num == null) {
                throw new IllegalStateException("idRequirement must be non-null for HpkeParameters.Variant ".concat(String.valueOf(zzf3)));
            } else if (zzf3 == zzjv.zzf.zzb) {
                zzxv2 = zznt.zza(num.intValue());
            } else if (zzf3 == zzjv.zzf.zza) {
                zzxv2 = zznt.zzb(num.intValue());
            } else {
                throw new IllegalStateException("Unknown HpkeParameters.Variant: ".concat(String.valueOf(zzf3)));
            }
            return new zzki(zzjv, zzxv, zzxv2, num);
        } else {
            throw new GeneralSecurityException("'idRequirement' must be null for NO_PREFIX variant.");
        }
    }

    public final zzjv zzb() {
        return this.zza;
    }

    public final zzxv zzc() {
        return this.zzc;
    }

    public final zzxv zzd() {
        return this.zzb;
    }

    @Nullable
    public final Integer zza() {
        return this.zzd;
    }
}
