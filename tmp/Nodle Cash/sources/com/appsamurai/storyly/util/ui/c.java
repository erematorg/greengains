package com.appsamurai.storyly.util.ui;

import G0.o;
import android.view.View;
import android.widget.Button;
import androidx.core.view.AccessibilityDelegateCompat;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import org.jetbrains.annotations.Nullable;

public final class c extends AccessibilityDelegateCompat {
    @Nullable

    /* renamed from: a  reason: collision with root package name */
    public final View f6382a;

    public c(@Nullable View view) {
        this.f6382a = view;
    }

    public static final void a(View view) {
    }

    public void onInitializeAccessibilityNodeInfo(@Nullable View view, @Nullable AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
        if (view != null && !view.hasOnClickListeners()) {
            view.setOnClickListener(new o(2));
        }
        super.onInitializeAccessibilityNodeInfo(view, accessibilityNodeInfoCompat);
        if (accessibilityNodeInfoCompat != null) {
            accessibilityNodeInfoCompat.setClassName(Button.class.getName());
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:2:0x0004, code lost:
        r0 = r1.f6382a;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean performAccessibilityAction(@org.jetbrains.annotations.Nullable android.view.View r2, int r3, @org.jetbrains.annotations.Nullable android.os.Bundle r4) {
        /*
            r1 = this;
            r0 = 16
            if (r3 != r0) goto L_0x000d
            android.view.View r0 = r1.f6382a
            if (r0 == 0) goto L_0x000d
            boolean r1 = r0.performClick()
            return r1
        L_0x000d:
            boolean r1 = super.performAccessibilityAction(r2, r3, r4)
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.appsamurai.storyly.util.ui.c.performAccessibilityAction(android.view.View, int, android.os.Bundle):boolean");
    }
}
