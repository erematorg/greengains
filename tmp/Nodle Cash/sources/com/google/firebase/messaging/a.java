package com.google.firebase.messaging;

import android.content.Intent;
import com.google.android.gms.tasks.TaskCompletionSource;

public final /* synthetic */ class a implements Runnable {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ EnhancedIntentService f7122a;

    /* renamed from: b  reason: collision with root package name */
    public final /* synthetic */ Intent f7123b;

    /* renamed from: c  reason: collision with root package name */
    public final /* synthetic */ TaskCompletionSource f7124c;

    public /* synthetic */ a(EnhancedIntentService enhancedIntentService, Intent intent, TaskCompletionSource taskCompletionSource) {
        this.f7122a = enhancedIntentService;
        this.f7123b = intent;
        this.f7124c = taskCompletionSource;
    }

    public final void run() {
        this.f7122a.lambda$processIntent$0(this.f7123b, this.f7124c);
    }
}
