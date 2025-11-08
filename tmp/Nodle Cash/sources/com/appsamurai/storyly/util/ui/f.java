package com.appsamurai.storyly.util.ui;

import android.view.View;
import android.widget.Button;
import androidx.core.view.AccessibilityDelegateCompat;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public final class f extends AccessibilityDelegateCompat {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final Function1<View, String> f6385a;

    public f(@NotNull Function1<? super View, String> function1) {
        Intrinsics.checkNotNullParameter(function1, "onAccessibility");
        this.f6385a = function1;
    }

    public void onInitializeAccessibilityNodeInfo(@Nullable View view, @Nullable AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
        super.onInitializeAccessibilityNodeInfo(view, accessibilityNodeInfoCompat);
        if (accessibilityNodeInfoCompat != null) {
            accessibilityNodeInfoCompat.setClassName(Button.class.getName());
        }
        if (accessibilityNodeInfoCompat != null) {
            accessibilityNodeInfoCompat.setCollectionItemInfo((Object) null);
        }
        if (view != null && accessibilityNodeInfoCompat != null) {
            accessibilityNodeInfoCompat.setContentDescription(this.f6385a.invoke(view));
        }
    }
}
