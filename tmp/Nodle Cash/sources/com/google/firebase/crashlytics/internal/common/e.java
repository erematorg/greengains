package com.google.firebase.crashlytics.internal.common;

import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;

public final /* synthetic */ class e implements Continuation {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f7095a;

    /* renamed from: b  reason: collision with root package name */
    public final /* synthetic */ TaskCompletionSource f7096b;

    public /* synthetic */ e(int i3, TaskCompletionSource taskCompletionSource) {
        this.f7095a = i3;
        this.f7096b = taskCompletionSource;
    }

    public final Object then(Task task) {
        int i3 = this.f7095a;
        TaskCompletionSource taskCompletionSource = this.f7096b;
        switch (i3) {
            case 0:
                return Utils.lambda$race$0(taskCompletionSource, task);
            case 1:
                return Utils.lambda$callTask$2(taskCompletionSource, task);
            default:
                return Utils.lambda$race$1(taskCompletionSource, task);
        }
    }
}
