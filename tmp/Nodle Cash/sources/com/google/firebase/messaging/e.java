package com.google.firebase.messaging;

import com.google.android.gms.tasks.TaskCompletionSource;

public final /* synthetic */ class e implements Runnable {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f7132a;

    /* renamed from: b  reason: collision with root package name */
    public final /* synthetic */ Object f7133b;

    /* renamed from: c  reason: collision with root package name */
    public final /* synthetic */ TaskCompletionSource f7134c;

    public /* synthetic */ e(Object obj, TaskCompletionSource taskCompletionSource, int i3) {
        this.f7132a = i3;
        this.f7133b = obj;
        this.f7134c = taskCompletionSource;
    }

    public final void run() {
        switch (this.f7132a) {
            case 0:
                ((FirebaseMessaging) this.f7133b).lambda$deleteToken$9(this.f7134c);
                return;
            case 1:
                ((FirebaseMessaging) this.f7133b).lambda$getToken$7(this.f7134c);
                return;
            case 2:
                ((FirebaseMessaging) this.f7133b).lambda$deleteToken$8(this.f7134c);
                return;
            default:
                ((ImageDownload) this.f7133b).lambda$start$0(this.f7134c);
                return;
        }
    }
}
