package com.google.firebase.auth.internal;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.tasks.Task;
import com.google.firebase.annotations.DeferredApi;
import com.google.firebase.auth.GetTokenResult;
import com.google.firebase.internal.InternalTokenProvider;

@KeepForSdk
public interface InternalAuthProvider extends InternalTokenProvider {
    @KeepForSdk
    @DeferredApi
    void addIdTokenListener(@NonNull IdTokenListener idTokenListener);

    @NonNull
    @KeepForSdk
    Task<GetTokenResult> getAccessToken(boolean z2);

    @Nullable
    String getUid();

    @KeepForSdk
    void removeIdTokenListener(@NonNull IdTokenListener idTokenListener);
}
