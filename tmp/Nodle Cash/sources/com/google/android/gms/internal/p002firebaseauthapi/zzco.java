package com.google.android.gms.internal.p002firebaseauthapi;

import A.a;
import java.security.GeneralSecurityException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.logging.Logger;
import javax.annotation.Nullable;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzco  reason: invalid package */
public final class zzco {
    private static final Logger zza = Logger.getLogger(zzco.class.getName());
    private static final ConcurrentMap<String, Object> zzb = new ConcurrentHashMap();
    private static final Set<Class<?>> zzc;

    static {
        HashSet hashSet = new HashSet();
        hashSet.add(zzbh.class);
        hashSet.add(zzbq.class);
        hashSet.add(zzcq.class);
        hashSet.add(zzbs.class);
        hashSet.add(zzbp.class);
        hashSet.add(zzcf.class);
        hashSet.add(zzru.class);
        hashSet.add(zzcm.class);
        hashSet.add(zzcl.class);
        zzc = Collections.unmodifiableSet(hashSet);
    }

    private zzco() {
    }

    @Deprecated
    public static zzuw zza(String str, zzaho zzaho) throws GeneralSecurityException {
        zzbt<?> zza2 = zzmn.zza().zza(str);
        if (zza2 instanceof zzcj) {
            return ((zzcj) zza2).zzc(zzaho);
        }
        throw new GeneralSecurityException(a.l("manager for key type ", str, " is not a PrivateKeyManager"));
    }

    @Deprecated
    public static synchronized zzuw zza(zzvc zzvc) throws GeneralSecurityException {
        zzuw zza2;
        synchronized (zzco.class) {
            zzbt<?> zza3 = zzmn.zza().zza(zzvc.zzf());
            if (zzmn.zza().zzb(zzvc.zzf())) {
                zza2 = zza3.zza(zzvc.zze());
            } else {
                String zzf = zzvc.zzf();
                throw new GeneralSecurityException("newKey-operation not permitted for key type " + zzf);
            }
        }
        return zza2;
    }

    @Nullable
    public static Class<?> zza(Class<?> cls) {
        try {
            return zznp.zza().zza(cls);
        } catch (GeneralSecurityException unused) {
            return null;
        }
    }

    public static <P> P zza(zzuw zzuw, Class<P> cls) throws GeneralSecurityException {
        String zzf = zzuw.zzf();
        return zzmn.zza().zza(zzf, cls).zzb(zzuw.zze());
    }

    public static <B, P> P zza(zzoo<B> zzoo, Class<P> cls) throws GeneralSecurityException {
        return zznp.zza().zza(zzoo, cls);
    }
}
