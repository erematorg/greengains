package com.appsamurai.storyly.util.ui.blur;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

public final class d extends Canvas {
    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public d(@NotNull Bitmap bitmap) {
        super(bitmap);
        Intrinsics.checkNotNullParameter(bitmap, "bitmap");
    }
}
