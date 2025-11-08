package com.google.android.play.integrity.internal;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;

public final /* synthetic */ class s implements OnCompleteListener {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ ac f6839a;

    /* renamed from: b  reason: collision with root package name */
    public final /* synthetic */ TaskCompletionSource f6840b;

    public /* synthetic */ s(ac acVar, TaskCompletionSource taskCompletionSource) {
        this.f6839a = acVar;
        this.f6840b = taskCompletionSource;
    }

    public final void onComplete(Task task) {
        this.f6839a.u(this.f6840b, task);
    }
}
