package com.google.android.recaptcha.internal;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.collections.ArraysKt;
import org.jetbrains.annotations.NotNull;

public final class zzct implements zzdd {
    @NotNull
    public static final zzct zza = new zzct();

    private zzct() {
    }

    private static final boolean zzb(List list) {
        ArrayList arrayList = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(list, 10));
        Iterator it = list.iterator();
        while (it.hasNext()) {
            arrayList.add(Boolean.valueOf(((zzpq) it.next()).zzN()));
        }
        return !arrayList.contains(Boolean.FALSE);
    }

    public final void zza(int i3, @NotNull zzcj zzcj, @NotNull zzpq... zzpqArr) throws zzae {
        if (zzb(ArraysKt.toList((T[]) zzpqArr))) {
            for (zzpq zzi : zzpqArr) {
                zzcj.zzc().zzb(zzi.zzi());
            }
            return;
        }
        throw new zzae(4, 5, (Throwable) null);
    }
}
