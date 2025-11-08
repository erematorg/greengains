package com.google.firebase;

import com.google.firebase.FirebaseApp;

public final /* synthetic */ class b implements FirebaseApp.BackgroundStateChangeListener {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ FirebaseApp f7042a;

    public /* synthetic */ b(FirebaseApp firebaseApp) {
        this.f7042a = firebaseApp;
    }

    public final void onBackgroundStateChanged(boolean z2) {
        this.f7042a.lambda$new$1(z2);
    }
}
