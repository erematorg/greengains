package com.google.firebase.sessions;

import com.google.firebase.FirebaseAppLifecycleListener;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.sessions.FirebaseSessions;

public final /* synthetic */ class a implements FirebaseAppLifecycleListener {
    public final void onDeleted(String str, FirebaseOptions firebaseOptions) {
        FirebaseSessions.AnonymousClass1.invokeSuspend$lambda$1(str, firebaseOptions);
    }
}
