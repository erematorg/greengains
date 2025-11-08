package com.google.android.gms.common.api.internal;

import androidx.annotation.NonNull;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;

final class zaac implements OnCompleteListener {
    final /* synthetic */ TaskCompletionSource zaa;
    final /* synthetic */ zaad zab;

    public zaac(zaad zaad, TaskCompletionSource taskCompletionSource) {
        this.zab = zaad;
        this.zaa = taskCompletionSource;
    }

    public final void onComplete(@NonNull Task task) {
        this.zab.zab.remove(this.zaa);
    }
}
