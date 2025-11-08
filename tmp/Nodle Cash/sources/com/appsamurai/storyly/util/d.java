package com.appsamurai.storyly.util;

import android.annotation.SuppressLint;
import android.widget.TextView;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

public final class d {
    @SuppressLint({"WrongConstant"})
    public static final void a(@NotNull TextView textView) {
        Intrinsics.checkNotNullParameter(textView, "<this>");
        textView.setBreakStrategy(0);
    }
}
