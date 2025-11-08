package com.google.firebase.messaging;

import android.content.Context;
import com.google.android.gms.tasks.TaskCompletionSource;

public final /* synthetic */ class l implements Runnable {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ Context f7146a;

    /* renamed from: b  reason: collision with root package name */
    public final /* synthetic */ boolean f7147b;

    /* renamed from: c  reason: collision with root package name */
    public final /* synthetic */ TaskCompletionSource f7148c;

    public /* synthetic */ l(Context context, boolean z2, TaskCompletionSource taskCompletionSource) {
        this.f7146a = context;
        this.f7147b = z2;
        this.f7148c = taskCompletionSource;
    }

    public final void run() {
        ProxyNotificationInitializer.lambda$setEnableProxyNotification$0(this.f7146a, this.f7147b, this.f7148c);
    }
}
