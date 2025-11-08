package com.google.android.gms.common.api.internal;

import androidx.annotation.NonNull;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.ApiExceptionUtil;

@KeepForSdk
public class ApiExceptionMapper implements StatusExceptionMapper {
    @NonNull
    public final Exception getException(@NonNull Status status) {
        return ApiExceptionUtil.fromStatus(status);
    }
}
