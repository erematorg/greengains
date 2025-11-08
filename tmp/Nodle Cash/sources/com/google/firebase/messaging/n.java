package com.google.firebase.messaging;

import android.content.Intent;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

public final /* synthetic */ class n implements Continuation, OnCompleteListener {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ Object f7151a;

    /* renamed from: b  reason: collision with root package name */
    public final /* synthetic */ Object f7152b;

    public /* synthetic */ n(Object obj, Object obj2) {
        this.f7151a = obj;
        this.f7152b = obj2;
    }

    public void onComplete(Task task) {
        ((EnhancedIntentService) this.f7151a).lambda$onStartCommand$1((Intent) this.f7152b, task);
    }

    public Object then(Task task) {
        return ((RequestDeduplicator) this.f7151a).lambda$getOrStartGetTokenRequest$0((String) this.f7152b, task);
    }
}
