package com.google.firebase.internal;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.GetTokenResult;

@KeepForSdk
public interface InternalTokenProvider {
    @NonNull
    @KeepForSdk
    Task<GetTokenResult> getAccessToken(boolean z2);

    @KeepForSdk
    @Nullable
    String getUid();
}
