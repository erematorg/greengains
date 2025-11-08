package com.google.android.gms.internal.measurement;

import java.util.Iterator;
import java.util.Map;

final class zzmh implements zzme {
    public final int zza(int i3, Object obj, Object obj2) {
        zzmf zzmf = (zzmf) obj;
        zzmd zzmd = (zzmd) obj2;
        if (zzmf.isEmpty()) {
            return 0;
        }
        Iterator it = zzmf.entrySet().iterator();
        if (!it.hasNext()) {
            return 0;
        }
        Map.Entry entry = (Map.Entry) it.next();
        entry.getKey();
        entry.getValue();
        throw new NoSuchMethodError();
    }

    public final Object zzb(Object obj) {
        return zzmf.zza().zzb();
    }

    public final Object zzc(Object obj) {
        ((zzmf) obj).zzc();
        return obj;
    }

    public final Map<?, ?> zzd(Object obj) {
        return (zzmf) obj;
    }

    public final Map<?, ?> zze(Object obj) {
        return (zzmf) obj;
    }

    public final boolean zzf(Object obj) {
        return !((zzmf) obj).zzd();
    }

    public final zzmc<?, ?> zza(Object obj) {
        zzmd zzmd = (zzmd) obj;
        throw new NoSuchMethodError();
    }

    public final Object zza(Object obj, Object obj2) {
        zzmf zzmf = (zzmf) obj;
        zzmf zzmf2 = (zzmf) obj2;
        if (!zzmf2.isEmpty()) {
            if (!zzmf.zzd()) {
                zzmf = zzmf.zzb();
            }
            zzmf.zza(zzmf2);
        }
        return zzmf;
    }
}
