package com.google.mlkit.common.sdkinternal.model;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.mlkit.common.MlKitException;
import java.io.File;

@KeepForSdk
public interface RemoteModelFileMover {
    @NonNull
    @KeepForSdk
    File getModelFileDestination() throws MlKitException;

    @KeepForSdk
    @Nullable
    File moveAllFilesFromPrivateTempToPrivateDestination(@NonNull File file) throws MlKitException;
}
