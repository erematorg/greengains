package com.google.android.gms.internal.location;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;
import java.util.Objects;

final /* synthetic */ class zzea implements OnCompleteListener {
    private final /* synthetic */ TaskCompletionSource zza;

    public /* synthetic */ zzea(TaskCompletionSource taskCompletionSource) {
        this.zza = taskCompletionSource;
    }

    public final /* synthetic */ void onComplete(Task task) {
        if (!task.isSuccessful()) {
            TaskCompletionSource taskCompletionSource = this.zza;
            Exception exception = task.getException();
            Objects.requireNonNull(exception);
            taskCompletionSource.trySetException(exception);
        }
    }
}
