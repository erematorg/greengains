package com.google.android.gms.internal.p002firebaseauthapi;

import A.a;
import com.google.android.gms.internal.p002firebaseauthapi.zzjo;
import java.security.GeneralSecurityException;
import java.security.spec.ECPoint;
import java.security.spec.EllipticCurve;
import javax.annotation.Nullable;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzjt  reason: invalid package */
public final class zzjt extends zzkr {
    private final zzjo zza;
    @Nullable
    private final ECPoint zzb;
    @Nullable
    private final zzxv zzc;
    private final zzxv zzd;
    @Nullable
    private final Integer zze;

    private zzjt(zzjo zzjo, @Nullable ECPoint eCPoint, @Nullable zzxv zzxv, zzxv zzxv2, @Nullable Integer num) {
        this.zza = zzjo;
        this.zzb = eCPoint;
        this.zzc = zzxv;
        this.zzd = zzxv2;
        this.zze = num;
    }

    public static zzjt zza(zzjo zzjo, zzxv zzxv, @Nullable Integer num) throws GeneralSecurityException {
        if (zzjo.zzd().equals(zzjo.zzb.zzd)) {
            zzb(zzjo.zzg(), num);
            if (zzxv.zza() == 32) {
                return new zzjt(zzjo, (ECPoint) null, zzxv, zza(zzjo.zzg(), num), num);
            }
            throw new GeneralSecurityException("Encoded public point byte length for X25519 curve must be 32");
        }
        throw new GeneralSecurityException("createForCurveX25519 may only be called with parameters for curve X25519");
    }

    public final zzjo zzb() {
        return this.zza;
    }

    public final zzxv zzc() {
        return this.zzd;
    }

    @Nullable
    public final zzxv zzd() {
        return this.zzc;
    }

    @Nullable
    public final ECPoint zze() {
        return this.zzb;
    }

    private static void zzb(zzjo.zze zze2, @Nullable Integer num) throws GeneralSecurityException {
        zzjo.zze zze3 = zzjo.zze.zzc;
        if (!zze2.equals(zze3) && num == null) {
            throw new GeneralSecurityException(a.l("'idRequirement' must be non-null for ", String.valueOf(zze2), " variant."));
        } else if (zze2.equals(zze3) && num != null) {
            throw new GeneralSecurityException("'idRequirement' must be null for NO_PREFIX variant.");
        }
    }

    public static zzjt zza(zzjo zzjo, ECPoint eCPoint, @Nullable Integer num) throws GeneralSecurityException {
        EllipticCurve ellipticCurve;
        if (!zzjo.zzd().equals(zzjo.zzb.zzd)) {
            zzb(zzjo.zzg(), num);
            zzjo.zzb zzd2 = zzjo.zzd();
            if (zzd2 == zzjo.zzb.zza) {
                ellipticCurve = zzmf.zza.getCurve();
            } else if (zzd2 == zzjo.zzb.zzb) {
                ellipticCurve = zzmf.zzb.getCurve();
            } else if (zzd2 == zzjo.zzb.zzc) {
                ellipticCurve = zzmf.zzc.getCurve();
            } else {
                throw new IllegalArgumentException("Unable to determine NIST curve type for ".concat(String.valueOf(zzd2)));
            }
            zzmf.zza(eCPoint, ellipticCurve);
            return new zzjt(zzjo, eCPoint, (zzxv) null, zza(zzjo.zzg(), num), num);
        }
        throw new GeneralSecurityException("createForNistCurve may only be called with parameters for NIST curve");
    }

    private static zzxv zza(zzjo.zze zze2, @Nullable Integer num) {
        if (zze2 == zzjo.zze.zzc) {
            return zznt.zza;
        }
        if (num == null) {
            throw new IllegalStateException("idRequirement must be non-null for EciesParameters.Variant: ".concat(String.valueOf(zze2)));
        } else if (zze2 == zzjo.zze.zzb) {
            return zznt.zza(num.intValue());
        } else {
            if (zze2 == zzjo.zze.zza) {
                return zznt.zzb(num.intValue());
            }
            throw new IllegalStateException("Unknown EciesParameters.Variant: ".concat(String.valueOf(zze2)));
        }
    }

    @Nullable
    public final Integer zza() {
        return this.zze;
    }
}
