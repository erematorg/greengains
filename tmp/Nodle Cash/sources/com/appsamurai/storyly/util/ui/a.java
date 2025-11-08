package com.appsamurai.storyly.util.ui;

import android.view.View;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

public final class a {
    public static final void a(@NotNull View view) {
        Intrinsics.checkNotNullParameter(view, "page");
        view.setRotationX(0.0f);
        view.setRotationY(0.0f);
        view.setRotation(0.0f);
        view.setScaleX(1.0f);
        view.setScaleY(1.0f);
        view.setPivotX(0.0f);
        view.setPivotY(0.0f);
        view.setTranslationY(0.0f);
        view.setTranslationX(0.0f);
        view.setAlpha(1.0f);
        view.setEnabled(false);
    }

    public static final void a(@NotNull View view, float f2) {
        Intrinsics.checkNotNullParameter(view, "page");
        float f3 = 1.0f;
        if (f2 < -1.0f) {
            f2 = -1.0f;
        } else if (f2 > 1.0f) {
            f2 = 1.0f;
        }
        float f4 = 0.0f;
        view.setRotationX(0.0f);
        view.setRotationY(0.0f);
        view.setRotation(0.0f);
        view.setScaleX(1.0f);
        view.setScaleY(1.0f);
        view.setPivotX(0.0f);
        view.setPivotY(0.0f);
        view.setTranslationY(0.0f);
        view.setTranslationX(0.0f);
        if (f2 <= -1.0f || f2 >= 1.0f) {
            f3 = 0.0f;
        }
        view.setAlpha(f3);
        view.setEnabled(false);
        view.setCameraDistance((float) (view.getWidth() * 20));
        if (f2 < 0.0f) {
            f4 = (float) view.getWidth();
        }
        view.setPivotX(f4);
        view.setPivotY(((float) view.getHeight()) * 0.5f);
        view.setRotationY(f2 * 90.0f);
    }
}
