package com.appsamurai.storyly.util;

import android.view.View;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

public final class l {
    public static final boolean a(@NotNull View view) {
        Intrinsics.checkNotNullParameter(view, "<this>");
        return view.getLayoutDirection() == 0;
    }
}
