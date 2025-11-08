package com.google.android.play.integrity.internal;

import androidx.annotation.Nullable;
import com.google.android.gms.tasks.TaskCompletionSource;

public abstract class r implements Runnable {
    @Nullable

    /* renamed from: a  reason: collision with root package name */
    private final TaskCompletionSource f6838a;

    public r() {
        this.f6838a = null;
    }

    public void a(Exception exc) {
        TaskCompletionSource taskCompletionSource = this.f6838a;
        if (taskCompletionSource != null) {
            taskCompletionSource.trySetException(exc);
        }
    }

    public abstract void b();

    @Nullable
    public final TaskCompletionSource c() {
        return this.f6838a;
    }

    public final void run() {
        try {
            b();
        } catch (Exception e3) {
            a(e3);
        }
    }

    public r(@Nullable TaskCompletionSource taskCompletionSource) {
        this.f6838a = taskCompletionSource;
    }
}
