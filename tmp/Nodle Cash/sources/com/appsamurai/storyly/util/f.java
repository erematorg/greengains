package com.appsamurai.storyly.util;

import androidx.annotation.ColorInt;
import androidx.core.graphics.ColorUtils;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

public final class f {
    @NotNull
    public static final String a(int i3) {
        return Intrinsics.stringPlus("#", Integer.toHexString(i3));
    }

    public static final int a(int i3, float f2) {
        return a(i3, (int) (((float) 255) * f2));
    }

    public static final int a(@ColorInt int i3, int i4) {
        return (i4 < 0 || i4 > 255) ? i3 : ColorUtils.setAlphaComponent(i3, i4);
    }
}
