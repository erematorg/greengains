package com.google.android.material.search;

import android.animation.Animator;
import android.view.View;
import androidx.core.view.WindowInsetsCompat;
import androidx.core.view.accessibility.AccessibilityManagerCompat;
import com.google.android.material.animation.AnimatableView;
import com.google.android.material.internal.ViewUtils;

public final /* synthetic */ class b implements AccessibilityManagerCompat.TouchExplorationStateChangeListener, AnimatableView.Listener, ViewUtils.OnApplyWindowInsetsListener {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ Object f6659a;

    public /* synthetic */ b(Object obj) {
        this.f6659a = obj;
    }

    public void onAnimationEnd() {
        ((Animator) this.f6659a).start();
    }

    public WindowInsetsCompat onApplyWindowInsets(View view, WindowInsetsCompat windowInsetsCompat, ViewUtils.RelativePadding relativePadding) {
        return ((SearchView) this.f6659a).lambda$setUpToolbarInsetListener$4(view, windowInsetsCompat, relativePadding);
    }

    public void onTouchExplorationStateChanged(boolean z2) {
        ((SearchBar) this.f6659a).lambda$new$0(z2);
    }
}
