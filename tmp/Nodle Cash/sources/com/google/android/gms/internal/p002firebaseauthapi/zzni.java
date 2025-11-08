package com.google.android.gms.internal.p002firebaseauthapi;

import java.security.GeneralSecurityException;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.Nullable;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzni  reason: invalid package */
public final class zzni {
    private static final zznk<zznc> zza = new zznh();
    private static final zzni zzb = zzb();
    private final Map<Class<? extends zzci>, zznk<? extends zzci>> zzc = new HashMap();

    private final synchronized <ParametersT extends zzci> zzbu zzb(ParametersT parameterst, @Nullable Integer num) throws GeneralSecurityException {
        zznk zznk;
        zznk = this.zzc.get(parameterst.getClass());
        if (zznk != null) {
        } else {
            String valueOf = String.valueOf(parameterst);
            throw new GeneralSecurityException("Cannot create a new key for parameters " + valueOf + ": no key creator for this class was registered.");
        }
        return zznk.zza(parameterst, (Integer) null);
    }

    public final zzbu zza(zzci zzci, @Nullable Integer num) throws GeneralSecurityException {
        return zzb(zzci, (Integer) null);
    }

    public static /* synthetic */ zzna zza(zznc zznc, Integer num) {
        zzvc zza2 = zznc.zzb().zza();
        zzbt<?> zza3 = zzmn.zza().zza(zza2.zzf());
        if (zzmn.zza().zzb(zza2.zzf())) {
            zzuw zza4 = zza3.zza(zza2.zze());
            return new zzna(zzou.zza(zza4.zzf(), zza4.zze(), zza4.zzb(), zza2.zzd(), num), zzbr.zza());
        }
        throw new GeneralSecurityException("Creating new keys is not allowed.");
    }

    private static zzni zzb() {
        zzni zzni = new zzni();
        try {
            zzni.zza(zza, zznc.class);
            return zzni;
        } catch (GeneralSecurityException e3) {
            throw new IllegalStateException("unexpected error.", e3);
        }
    }

    public static zzni zza() {
        return zzb;
    }

    public final synchronized <ParametersT extends zzci> void zza(zznk<ParametersT> zznk, Class<ParametersT> cls) throws GeneralSecurityException {
        try {
            zznk zznk2 = this.zzc.get(cls);
            if (zznk2 != null) {
                if (!zznk2.equals(zznk)) {
                    String valueOf = String.valueOf(cls);
                    throw new GeneralSecurityException("Different key creator for parameters class " + valueOf + " already inserted");
                }
            }
            this.zzc.put(cls, zznk);
        } catch (Throwable th) {
            while (true) {
                throw th;
            }
        }
    }
}
