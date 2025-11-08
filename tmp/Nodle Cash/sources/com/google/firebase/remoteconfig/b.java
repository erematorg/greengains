package com.google.firebase.remoteconfig;

import java.util.concurrent.Callable;

public final /* synthetic */ class b implements Callable {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f7161a;

    /* renamed from: b  reason: collision with root package name */
    public final /* synthetic */ FirebaseRemoteConfig f7162b;

    public /* synthetic */ b(FirebaseRemoteConfig firebaseRemoteConfig, int i3) {
        this.f7161a = i3;
        this.f7162b = firebaseRemoteConfig;
    }

    public final Object call() {
        int i3 = this.f7161a;
        FirebaseRemoteConfig firebaseRemoteConfig = this.f7162b;
        switch (i3) {
            case 0:
                return firebaseRemoteConfig.lambda$reset$6();
            default:
                return firebaseRemoteConfig.getInfo();
        }
    }
}
