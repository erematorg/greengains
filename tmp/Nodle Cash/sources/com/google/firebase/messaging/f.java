package com.google.firebase.messaging;

public final /* synthetic */ class f implements Runnable {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f7135a;

    /* renamed from: b  reason: collision with root package name */
    public final /* synthetic */ FirebaseMessaging f7136b;

    public /* synthetic */ f(FirebaseMessaging firebaseMessaging, int i3) {
        this.f7135a = i3;
        this.f7136b = firebaseMessaging;
    }

    public final void run() {
        int i3 = this.f7135a;
        FirebaseMessaging firebaseMessaging = this.f7136b;
        switch (i3) {
            case 0:
                firebaseMessaging.lambda$new$2();
                return;
            default:
                firebaseMessaging.lambda$new$4();
                return;
        }
    }
}
