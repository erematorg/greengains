package com.google.firebase.messaging;

import android.content.Context;
import java.util.concurrent.Callable;
import java.util.concurrent.ScheduledExecutorService;

public final /* synthetic */ class p implements Callable {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ Context f7155a;

    /* renamed from: b  reason: collision with root package name */
    public final /* synthetic */ ScheduledExecutorService f7156b;

    /* renamed from: c  reason: collision with root package name */
    public final /* synthetic */ FirebaseMessaging f7157c;

    /* renamed from: d  reason: collision with root package name */
    public final /* synthetic */ Metadata f7158d;

    /* renamed from: e  reason: collision with root package name */
    public final /* synthetic */ GmsRpc f7159e;

    public /* synthetic */ p(Context context, ScheduledExecutorService scheduledExecutorService, FirebaseMessaging firebaseMessaging, Metadata metadata, GmsRpc gmsRpc) {
        this.f7155a = context;
        this.f7156b = scheduledExecutorService;
        this.f7157c = firebaseMessaging;
        this.f7158d = metadata;
        this.f7159e = gmsRpc;
    }

    public final Object call() {
        return TopicsSubscriber.lambda$createInstance$0(this.f7155a, this.f7156b, this.f7157c, this.f7158d, this.f7159e);
    }
}
