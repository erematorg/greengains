package com.appsamurai.storyly.util.ui;

import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.view.View;
import androidx.appcompat.content.res.AppCompatResources;
import com.appsamurai.storyly.R;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public final class b {
    public static /* synthetic */ GradientDrawable a(View view, int i3, float f2, Integer num, int i4, int i5) {
        if ((i5 & 1) != 0) {
            i3 = 0;
        }
        if ((i5 & 4) != 0) {
            num = null;
        }
        if ((i5 & 8) != 0) {
            i4 = 0;
        }
        return a(view, i3, f2, num, i4);
    }

    @NotNull
    public static final GradientDrawable a(@NotNull View view, int i3, float f2, @Nullable Integer num, int i4) {
        Intrinsics.checkNotNullParameter(view, "<this>");
        return a(view, i3, f2, f2, f2, f2, num, i4);
    }

    public static /* synthetic */ GradientDrawable a(View view, int i3, float f2, float f3, float f4, float f5, Integer num, int i4, int i5) {
        return a(view, (i5 & 1) != 0 ? 0 : i3, f2, f3, f4, f5, (Integer) null, (i5 & 64) != 0 ? 0 : i4);
    }

    @NotNull
    public static final GradientDrawable a(@NotNull View view, int i3, float f2, float f3, float f4, float f5, @Nullable Integer num, int i4) {
        Intrinsics.checkNotNullParameter(view, "<this>");
        Drawable drawable = AppCompatResources.getDrawable(view.getContext(), R.drawable.st_default_interactive_bg);
        if (drawable != null) {
            GradientDrawable gradientDrawable = (GradientDrawable) ((GradientDrawable) drawable).mutate();
            gradientDrawable.setColor(i3);
            if (num != null) {
                gradientDrawable.setStroke(i4, num.intValue());
            }
            gradientDrawable.setCornerRadii(new float[]{f2, f2, f3, f3, f4, f4, f5, f5});
            return gradientDrawable;
        }
        throw new NullPointerException("null cannot be cast to non-null type android.graphics.drawable.GradientDrawable");
    }
}
