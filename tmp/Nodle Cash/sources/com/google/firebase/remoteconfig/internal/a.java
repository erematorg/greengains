package com.google.firebase.remoteconfig.internal;

import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.Task;

public final /* synthetic */ class a implements Continuation {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ ConfigAutoFetch f7167a;

    /* renamed from: b  reason: collision with root package name */
    public final /* synthetic */ Task f7168b;

    /* renamed from: c  reason: collision with root package name */
    public final /* synthetic */ Task f7169c;

    /* renamed from: d  reason: collision with root package name */
    public final /* synthetic */ long f7170d;

    /* renamed from: e  reason: collision with root package name */
    public final /* synthetic */ int f7171e;

    public /* synthetic */ a(ConfigAutoFetch configAutoFetch, Task task, Task task2, long j2, int i3) {
        this.f7167a = configAutoFetch;
        this.f7168b = task;
        this.f7169c = task2;
        this.f7170d = j2;
        this.f7171e = i3;
    }

    public final Object then(Task task) {
        return this.f7167a.lambda$fetchLatestConfig$0(this.f7168b, this.f7169c, this.f7170d, this.f7171e, task);
    }
}
