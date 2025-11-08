package com.google.android.gms.internal.p002firebaseauthapi;

import com.google.android.gms.internal.p002firebaseauthapi.zzjv;
import java.math.BigInteger;
import java.security.GeneralSecurityException;
import java.security.spec.ECParameterSpec;
import java.util.Arrays;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzka  reason: invalid package */
public final class zzka extends zzks {
    private final zzki zza;
    private final zzxw zzb;

    private zzka(zzki zzki, zzxw zzxw) {
        this.zza = zzki;
        this.zzb = zzxw;
    }

    public static zzka zza(zzki zzki, zzxw zzxw) throws GeneralSecurityException {
        ECParameterSpec eCParameterSpec;
        if (zzki == null) {
            throw new GeneralSecurityException("HPKE private key cannot be constructed without an HPKE public key");
        } else if (zzxw != null) {
            zzjv.zzd zze = zzki.zzb().zze();
            int zza2 = zzxw.zza();
            String str = "Encoded private key byte length for " + String.valueOf(zze) + " must be %d, not " + zza2;
            zzjv.zzd zzd = zzjv.zzd.zza;
            if (zze == zzd) {
                if (zza2 != 32) {
                    throw new GeneralSecurityException(String.format(str, new Object[]{32}));
                }
            } else if (zze == zzjv.zzd.zzb) {
                if (zza2 != 48) {
                    throw new GeneralSecurityException(String.format(str, new Object[]{48}));
                }
            } else if (zze == zzjv.zzd.zzc) {
                if (zza2 != 66) {
                    throw new GeneralSecurityException(String.format(str, new Object[]{66}));
                }
            } else if (zze != zzjv.zzd.zzd) {
                throw new GeneralSecurityException("Unable to validate private key length for ".concat(String.valueOf(zze)));
            } else if (zza2 != 32) {
                throw new GeneralSecurityException(String.format(str, new Object[]{32}));
            }
            zzjv.zzd zze2 = zzki.zzb().zze();
            byte[] zzb2 = zzki.zzd().zzb();
            byte[] zza3 = zzxw.zza(zzbr.zza());
            if (zze2 == zzd || zze2 == zzjv.zzd.zzb || zze2 == zzjv.zzd.zzc) {
                if (zze2 == zzd) {
                    eCParameterSpec = zzmf.zza;
                } else if (zze2 == zzjv.zzd.zzb) {
                    eCParameterSpec = zzmf.zzb;
                } else if (zze2 == zzjv.zzd.zzc) {
                    eCParameterSpec = zzmf.zzc;
                } else {
                    throw new IllegalArgumentException("Unable to determine NIST curve params for ".concat(String.valueOf(zze2)));
                }
                BigInteger order = eCParameterSpec.getOrder();
                BigInteger zza4 = zzmd.zza(zza3);
                if (zza4.signum() <= 0 || zza4.compareTo(order) >= 0) {
                    throw new GeneralSecurityException("Invalid private key.");
                } else if (!zzmf.zza(zza4, eCParameterSpec).equals(zzwr.zza(eCParameterSpec.getCurve(), zzwt.UNCOMPRESSED, zzb2))) {
                    throw new GeneralSecurityException("Invalid private key for public key.");
                }
            } else if (zze2 != zzjv.zzd.zzd) {
                throw new IllegalArgumentException("Unable to validate key pair for ".concat(String.valueOf(zze2)));
            } else if (!Arrays.equals(zzxt.zza(zza3), zzb2)) {
                throw new GeneralSecurityException("Invalid private key for public key.");
            }
            return new zzka(zzki, zzxw);
        } else {
            throw new GeneralSecurityException("HPKE private key cannot be constructed without secret");
        }
    }

    public final /* synthetic */ zzbu zzb() {
        return (zzki) zzd();
    }

    public final zzjv zzc() {
        return this.zza.zzb();
    }

    public final /* synthetic */ zzkr zzd() {
        return this.zza;
    }

    public final zzxw zze() {
        return this.zzb;
    }
}
