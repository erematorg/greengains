package com.appsamurai.storyly.util.ui;

import G0.o;
import android.view.View;
import android.widget.TextView;
import androidx.core.view.AccessibilityDelegateCompat;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import org.jetbrains.annotations.Nullable;

public final class e extends AccessibilityDelegateCompat {
    public static final void a(View view) {
    }

    public void onInitializeAccessibilityNodeInfo(@Nullable View view, @Nullable AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
        if (view != null && !view.hasOnClickListeners()) {
            view.setOnClickListener(new o(3));
        }
        super.onInitializeAccessibilityNodeInfo(view, accessibilityNodeInfoCompat);
        if (accessibilityNodeInfoCompat != null) {
            accessibilityNodeInfoCompat.setClassName(TextView.class.getName());
            accessibilityNodeInfoCompat.removeAction(AccessibilityNodeInfoCompat.AccessibilityActionCompat.ACTION_CLICK);
            accessibilityNodeInfoCompat.setClickable(false);
        }
    }
}
