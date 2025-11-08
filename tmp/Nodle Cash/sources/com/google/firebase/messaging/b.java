package com.google.firebase.messaging;

import android.content.Context;
import android.content.Intent;
import java.util.concurrent.Callable;

public final /* synthetic */ class b implements Callable {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ Context f7125a;

    /* renamed from: b  reason: collision with root package name */
    public final /* synthetic */ Intent f7126b;

    public /* synthetic */ b(Context context, Intent intent) {
        this.f7125a = context;
        this.f7126b = intent;
    }

    public final Object call() {
        return Integer.valueOf(ServiceStarter.getInstance().startMessagingService(this.f7125a, this.f7126b));
    }
}
