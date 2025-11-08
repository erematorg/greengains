package com.google.android.gms.internal.fido;

import java.util.Comparator;
import java.util.Map;

public final /* synthetic */ class zzbd implements Comparator {
    public final /* synthetic */ Comparator zza;

    public /* synthetic */ zzbd(Comparator comparator) {
        this.zza = comparator;
    }

    public final int compare(Object obj, Object obj2) {
        Comparator comparator = this.zza;
        Map.Entry entry = (Map.Entry) obj;
        Map.Entry entry2 = (Map.Entry) obj2;
        int i3 = zzbg.zzb;
        entry.getClass();
        entry2.getClass();
        return comparator.compare(entry.getKey(), entry2.getKey());
    }
}
