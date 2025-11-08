package com.google.android.recaptcha.internal;

import java.util.Comparator;

final class zzgo implements Comparator {
    public final /* synthetic */ int compare(Object obj, Object obj2) {
        zzgw zzgw = (zzgw) obj;
        zzgw zzgw2 = (zzgw) obj2;
        zzgn zzgn = new zzgn(zzgw);
        zzgn zzgn2 = new zzgn(zzgw2);
        while (zzgn.hasNext() && zzgn2.hasNext()) {
            int compareTo = Integer.valueOf(zzgn.zza() & 255).compareTo(Integer.valueOf(zzgn2.zza() & 255));
            if (compareTo != 0) {
                return compareTo;
            }
        }
        return Integer.valueOf(zzgw.zzd()).compareTo(Integer.valueOf(zzgw2.zzd()));
    }
}
