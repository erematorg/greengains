package com.google.android.gms.internal.measurement;

import android.support.v4.media.session.a;
import androidx.browser.trusted.c;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public final /* synthetic */ class zzan {
    public static zzaq zza(zzak zzak, zzaq zzaq, zzh zzh, List<zzaq> list) {
        if (zzak.zzc(zzaq.zzf())) {
            zzaq zza = zzak.zza(zzaq.zzf());
            if (zza instanceof zzal) {
                return ((zzal) zza).zza(zzh, list);
            }
            throw new IllegalArgumentException(a.m(zzaq.zzf(), " is not a function"));
        } else if ("hasOwnProperty".equals(zzaq.zzf())) {
            zzg.zza("hasOwnProperty", 1, list);
            if (zzak.zzc(zzh.zza(list.get(0)).zzf())) {
                return zzaq.zzh;
            }
            return zzaq.zzi;
        } else {
            throw new IllegalArgumentException(c.a("Object has no function ", zzaq.zzf()));
        }
    }

    public static Iterator<zzaq> zza(Map<String, zzaq> map) {
        return new zzam(map.keySet().iterator());
    }
}
