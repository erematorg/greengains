package com.google.android.gms.internal.p002firebaseauthapi;

import androidx.camera.camera2.internal.C0118y;
import com.google.android.gms.internal.p002firebaseauthapi.zzig;
import java.security.GeneralSecurityException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzmn  reason: invalid package */
public final class zzmn {
    private static final Logger zza = Logger.getLogger(zzmn.class.getName());
    private static final zzmn zzb = new zzmn();
    private ConcurrentMap<String, zzbt<?>> zzc = new ConcurrentHashMap();
    private ConcurrentMap<String, Boolean> zzd = new ConcurrentHashMap();

    private final synchronized zzbt<?> zzc(String str) throws GeneralSecurityException {
        if (this.zzc.containsKey(str)) {
        } else {
            throw new GeneralSecurityException("No key manager found for key type " + str);
        }
        return this.zzc.get(str);
    }

    public final <P> zzbt<P> zza(String str, Class<P> cls) throws GeneralSecurityException {
        zzbt<?> zzc2 = zzc(str);
        if (zzc2.zza().equals(cls)) {
            return zzc2;
        }
        String name = cls.getName();
        String valueOf = String.valueOf(zzc2.getClass());
        String valueOf2 = String.valueOf(zzc2.zza());
        StringBuilder l2 = C0118y.l("Primitive type ", name, " not supported by key manager of type ", valueOf, ", which only supports: ");
        l2.append(valueOf2);
        throw new GeneralSecurityException(l2.toString());
    }

    public final boolean zzb(String str) {
        return this.zzd.get(str).booleanValue();
    }

    public final zzbt<?> zza(String str) throws GeneralSecurityException {
        return zzc(str);
    }

    public static zzmn zza() {
        return zzb;
    }

    private final synchronized void zza(zzbt<?> zzbt, boolean z2, boolean z3) throws GeneralSecurityException {
        try {
            String zzb2 = zzbt.zzb();
            if (z3 && this.zzd.containsKey(zzb2)) {
                if (!this.zzd.get(zzb2).booleanValue()) {
                    throw new GeneralSecurityException("New keys are already disallowed for key type " + zzb2);
                }
            }
            zzbt zzbt2 = this.zzc.get(zzb2);
            if (zzbt2 != null) {
                if (!zzbt2.getClass().equals(zzbt.getClass())) {
                    Logger logger = zza;
                    Level level = Level.WARNING;
                    logger.logp(level, "com.google.crypto.tink.internal.KeyManagerRegistry", "insertKeyManager", "Attempted overwrite of a registered key manager for key type " + zzb2);
                    String name = zzbt2.getClass().getName();
                    String name2 = zzbt.getClass().getName();
                    throw new GeneralSecurityException("typeUrl (" + zzb2 + ") is already registered with " + name + ", cannot be re-registered with " + name2);
                }
            }
            this.zzc.putIfAbsent(zzb2, zzbt);
            this.zzd.put(zzb2, Boolean.valueOf(z3));
        } catch (Throwable th) {
            while (true) {
                throw th;
            }
        }
    }

    public final synchronized <P> void zza(zzbt<P> zzbt, boolean z2) throws GeneralSecurityException {
        zza(zzbt, zzig.zza.ALGORITHM_NOT_FIPS, z2);
    }

    public final synchronized <P> void zza(zzbt<P> zzbt, zzig.zza zza2, boolean z2) throws GeneralSecurityException {
        if (zza2.zza()) {
            zza((zzbt<?>) zzbt, false, z2);
        } else {
            throw new GeneralSecurityException("Cannot register key manager: FIPS compatibility insufficient");
        }
    }
}
