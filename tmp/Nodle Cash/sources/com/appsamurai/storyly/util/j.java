package com.appsamurai.storyly.util;

import java.util.Iterator;
import java.util.List;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public final class j {
    @Nullable
    public static final <T> T a(@NotNull List<? extends T> list, @NotNull Function1<? super T, Boolean> function1) {
        Intrinsics.checkNotNullParameter(list, "<this>");
        Intrinsics.checkNotNullParameter(function1, "predicate");
        Iterator<? extends T> it = list.iterator();
        int i3 = 0;
        while (true) {
            if (!it.hasNext()) {
                i3 = -1;
                break;
            } else if (function1.invoke(it.next()).booleanValue()) {
                break;
            } else {
                i3++;
            }
        }
        if (i3 == -1 || i3 == CollectionsKt.getLastIndex(list)) {
            return null;
        }
        return list.get(i3 + 1);
    }
}
