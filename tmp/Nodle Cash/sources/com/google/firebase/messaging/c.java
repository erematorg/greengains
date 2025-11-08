package com.google.firebase.messaging;

import android.content.Context;
import android.content.Intent;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.Task;

public final /* synthetic */ class c implements Continuation {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ Context f7127a;

    /* renamed from: b  reason: collision with root package name */
    public final /* synthetic */ Intent f7128b;

    /* renamed from: c  reason: collision with root package name */
    public final /* synthetic */ boolean f7129c;

    public /* synthetic */ c(Context context, Intent intent, boolean z2) {
        this.f7127a = context;
        this.f7128b = intent;
        this.f7129c = z2;
    }

    public final Object then(Task task) {
        return FcmBroadcastProcessor.lambda$startMessagingService$2(this.f7127a, this.f7128b, this.f7129c, task);
    }
}
