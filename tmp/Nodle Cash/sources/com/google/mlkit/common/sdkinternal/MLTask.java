package com.google.mlkit.common.sdkinternal;

import androidx.annotation.NonNull;
import androidx.annotation.WorkerThread;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.mlkit.common.MlKitException;
import com.google.mlkit.common.sdkinternal.MLTaskInput;

@KeepForSdk
public abstract class MLTask<T, S extends MLTaskInput> extends ModelResource {
    public MLTask() {
    }

    @WorkerThread
    @NonNull
    @KeepForSdk
    public abstract T run(@NonNull S s3) throws MlKitException;

    @KeepForSdk
    public MLTask(@NonNull TaskQueue taskQueue) {
        super(taskQueue);
    }
}
