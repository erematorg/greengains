package com.appsamurai.storyly.data.managers.storage;

import android.content.Context;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

public final class a extends e {
    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public a(@NotNull Context context, @NotNull String str) {
        super(context, str, 0, 4);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(str, "statusKey");
    }
}
