package com.google.android.material.motion;

import android.window.OnBackInvokedCallback;

public final /* synthetic */ class a implements OnBackInvokedCallback {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ MaterialBackHandler f6658a;

    public /* synthetic */ a(MaterialBackHandler materialBackHandler) {
        this.f6658a = materialBackHandler;
    }

    public final void onBackInvoked() {
        this.f6658a.handleBackInvoked();
    }
}
