package com.google.android.gms.internal.p002firebaseauthapi;

import java.util.Iterator;
import java.util.Map;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzakl  reason: invalid package */
final class zzakl implements zzaki {
    public final int zza(int i3, Object obj, Object obj2) {
        zzakj zzakj = (zzakj) obj;
        zzakh zzakh = (zzakh) obj2;
        if (zzakj.isEmpty()) {
            return 0;
        }
        Iterator it = zzakj.entrySet().iterator();
        if (!it.hasNext()) {
            return 0;
        }
        Map.Entry entry = (Map.Entry) it.next();
        entry.getKey();
        entry.getValue();
        throw new NoSuchMethodError();
    }

    public final Object zzb(Object obj) {
        return zzakj.zza().zzb();
    }

    public final Object zzc(Object obj) {
        ((zzakj) obj).zzc();
        return obj;
    }

    public final Map<?, ?> zzd(Object obj) {
        return (zzakj) obj;
    }

    public final Map<?, ?> zze(Object obj) {
        return (zzakj) obj;
    }

    public final boolean zzf(Object obj) {
        return !((zzakj) obj).zzd();
    }

    public final zzakg<?, ?> zza(Object obj) {
        zzakh zzakh = (zzakh) obj;
        throw new NoSuchMethodError();
    }

    public final Object zza(Object obj, Object obj2) {
        zzakj zzakj = (zzakj) obj;
        zzakj zzakj2 = (zzakj) obj2;
        if (!zzakj2.isEmpty()) {
            if (!zzakj.zzd()) {
                zzakj = zzakj.zzb();
            }
            zzakj.zza(zzakj2);
        }
        return zzakj;
    }
}
