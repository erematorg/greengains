package com.google.firebase.crashlytics.internal;

import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.Task;
import java.util.concurrent.Callable;

public final /* synthetic */ class b implements Continuation {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f7089a;

    /* renamed from: b  reason: collision with root package name */
    public final /* synthetic */ Callable f7090b;

    public /* synthetic */ b(Callable callable, int i3) {
        this.f7089a = i3;
        this.f7090b = callable;
    }

    public final Object then(Task task) {
        int i3 = this.f7089a;
        Callable callable = this.f7090b;
        switch (i3) {
            case 0:
                return callable.call();
            default:
                return CrashlyticsWorker.lambda$submitTask$4(callable, task);
        }
    }
}
