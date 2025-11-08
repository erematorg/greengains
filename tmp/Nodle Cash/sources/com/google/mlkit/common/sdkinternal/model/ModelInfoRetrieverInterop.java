package com.google.mlkit.common.sdkinternal.model;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.mlkit.common.MlKitException;
import com.google.mlkit.common.model.RemoteModel;
import com.google.mlkit.common.sdkinternal.ModelInfo;

@KeepForSdk
public interface ModelInfoRetrieverInterop {
    @KeepForSdk
    @Nullable
    ModelInfo retrieveRemoteModelInfo(@NonNull RemoteModel remoteModel) throws MlKitException;
}
