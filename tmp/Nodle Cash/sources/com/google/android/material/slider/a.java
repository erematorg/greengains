package com.google.android.material.slider;

import android.view.ViewTreeObserver;

public final /* synthetic */ class a implements ViewTreeObserver.OnScrollChangedListener {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ BaseSlider f6690a;

    public /* synthetic */ a(BaseSlider baseSlider) {
        this.f6690a = baseSlider;
    }

    public final void onScrollChanged() {
        this.f6690a.updateLabels();
    }
}
