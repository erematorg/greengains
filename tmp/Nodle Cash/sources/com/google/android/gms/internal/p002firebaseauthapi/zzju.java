package com.google.android.gms.internal.p002firebaseauthapi;

import com.google.android.gms.internal.p002firebaseauthapi.zzjo;
import java.math.BigInteger;
import java.security.GeneralSecurityException;
import java.security.spec.ECParameterSpec;
import java.security.spec.ECPoint;
import java.util.Arrays;
import javax.annotation.Nullable;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzju  reason: invalid package */
public final class zzju extends zzks {
    private final zzjt zza;
    @Nullable
    private final zzxu zzb;
    @Nullable
    private final zzxw zzc;

    private zzju(zzjt zzjt, @Nullable zzxu zzxu, @Nullable zzxw zzxw) {
        this.zza = zzjt;
        this.zzb = zzxu;
        this.zzc = zzxw;
    }

    public static zzju zza(zzjt zzjt, zzxw zzxw) throws GeneralSecurityException {
        if (zzjt == null) {
            throw new GeneralSecurityException("ECIES private key cannot be constructed without an ECIES public key");
        } else if (zzjt.zzd() == null) {
            throw new GeneralSecurityException("ECIES private key for X25519 curve cannot be constructed with NIST-curve public key");
        } else if (zzxw != null) {
            byte[] zza2 = zzxw.zza(zzbr.zza());
            byte[] zzb2 = zzjt.zzd().zzb();
            if (zza2.length != 32) {
                throw new GeneralSecurityException("Private key bytes length for X25519 curve must be 32");
            } else if (Arrays.equals(zzxt.zza(zza2), zzb2)) {
                return new zzju(zzjt, (zzxu) null, zzxw);
            } else {
                throw new GeneralSecurityException("Invalid private key for public key.");
            }
        } else {
            throw new GeneralSecurityException("ECIES private key cannot be constructed without secret");
        }
    }

    public final /* synthetic */ zzbu zzb() {
        return (zzjt) zzd();
    }

    public final zzjo zzc() {
        return this.zza.zzb();
    }

    public final /* synthetic */ zzkr zzd() {
        return this.zza;
    }

    @Nullable
    public final zzxu zze() {
        return this.zzb;
    }

    @Nullable
    public final zzxw zzf() {
        return this.zzc;
    }

    public static zzju zza(zzjt zzjt, zzxu zzxu) throws GeneralSecurityException {
        if (zzjt == null) {
            throw new GeneralSecurityException("ECIES private key cannot be constructed without an ECIES public key");
        } else if (zzjt.zze() == null) {
            throw new GeneralSecurityException("ECIES private key for NIST curve cannot be constructed with X25519-curve public key");
        } else if (zzxu != null) {
            BigInteger zza2 = zzxu.zza(zzbr.zza());
            ECPoint zze = zzjt.zze();
            zzjo.zzb zzd = zzjt.zzb().zzd();
            BigInteger order = zza(zzd).getOrder();
            if (zza2.signum() <= 0 || zza2.compareTo(order) >= 0) {
                throw new GeneralSecurityException("Invalid private value");
            } else if (zzmf.zza(zza2, zza(zzd)).equals(zze)) {
                return new zzju(zzjt, zzxu, (zzxw) null);
            } else {
                throw new GeneralSecurityException("Invalid private value");
            }
        } else {
            throw new GeneralSecurityException("ECIES private key cannot be constructed without secret");
        }
    }

    private static ECParameterSpec zza(zzjo.zzb zzb2) {
        if (zzb2 == zzjo.zzb.zza) {
            return zzmf.zza;
        }
        if (zzb2 == zzjo.zzb.zzb) {
            return zzmf.zzb;
        }
        if (zzb2 == zzjo.zzb.zzc) {
            return zzmf.zzc;
        }
        throw new IllegalArgumentException("Unable to determine NIST curve type for ".concat(String.valueOf(zzb2)));
    }
}
