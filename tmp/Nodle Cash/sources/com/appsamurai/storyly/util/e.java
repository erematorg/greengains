package com.appsamurai.storyly.util;

import java.util.List;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.IntRange;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public final class e {
    @Nullable
    public static final <T> T a(@NotNull List<? extends T> list, @Nullable Integer num) {
        Intrinsics.checkNotNullParameter(list, "<this>");
        if (num != null && new IntRange(0, CollectionsKt.getLastIndex(list)).contains(num.intValue())) {
            return list.get(num.intValue());
        }
        return null;
    }
}
