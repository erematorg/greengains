package com.appsamurai.storyly.util.ui;

import android.os.Bundle;
import android.view.View;
import androidx.core.view.AccessibilityDelegateCompat;
import androidx.core.view.ViewCompat;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

public final class l {
    @NotNull
    public static final View a(@NotNull View view) {
        Intrinsics.checkNotNullParameter(view, "<this>");
        view.performAccessibilityAction(64, (Bundle) null);
        view.sendAccessibilityEvent(4);
        return view;
    }

    public static final void b(@NotNull View view) {
        Intrinsics.checkNotNullParameter(view, "<this>");
        view.setImportantForAccessibility(4);
    }

    public static final void a(@NotNull View view, @NotNull AccessibilityDelegateCompat accessibilityDelegateCompat) {
        Intrinsics.checkNotNullParameter(view, "<this>");
        Intrinsics.checkNotNullParameter(accessibilityDelegateCompat, "delegate");
        ViewCompat.setAccessibilityDelegate(view, accessibilityDelegateCompat);
    }
}
