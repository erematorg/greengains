package com.google.android.material.textfield;

import androidx.core.view.accessibility.AccessibilityManagerCompat;

public final /* synthetic */ class g implements AccessibilityManagerCompat.TouchExplorationStateChangeListener {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ DropdownMenuEndIconDelegate f6701a;

    public /* synthetic */ g(DropdownMenuEndIconDelegate dropdownMenuEndIconDelegate) {
        this.f6701a = dropdownMenuEndIconDelegate;
    }

    public final void onTouchExplorationStateChanged(boolean z2) {
        this.f6701a.lambda$new$2(z2);
    }
}
