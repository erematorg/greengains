package com.google.android.recaptcha.internal;

import java.util.Iterator;
import java.util.List;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import org.jetbrains.annotations.NotNull;

public final class zzef {
    @NotNull
    private List zza = CollectionsKt.emptyList();

    public final long zza(@NotNull long[] jArr) {
        Iterator<T> it = CollectionsKt.plus(this.zza, ArraysKt.toList(jArr)).iterator();
        if (it.hasNext()) {
            T next = it.next();
            while (it.hasNext()) {
                next = Long.valueOf(((Number) it.next()).longValue() ^ ((Number) next).longValue());
            }
            return ((Number) next).longValue();
        }
        throw new UnsupportedOperationException("Empty collection can't be reduced.");
    }

    public final void zzb(@NotNull long[] jArr) {
        this.zza = ArraysKt.toList(jArr);
    }
}
