package com.google.android.gms.internal.measurement;

import java.util.Comparator;

final class zzju implements Comparator<zzjs> {
    public final /* synthetic */ int compare(Object obj, Object obj2) {
        zzjs zzjs = (zzjs) obj;
        zzjs zzjs2 = (zzjs) obj2;
        zzjy zzjy = (zzjy) zzjs.iterator();
        zzjy zzjy2 = (zzjy) zzjs2.iterator();
        while (zzjy.hasNext() && zzjy2.hasNext()) {
            int compare = Integer.compare(zzjs.zza(zzjy.zza()), zzjs.zza(zzjy2.zza()));
            if (compare != 0) {
                return compare;
            }
        }
        return Integer.compare(zzjs.zzb(), zzjs2.zzb());
    }
}
