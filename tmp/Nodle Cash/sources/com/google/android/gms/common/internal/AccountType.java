package com.google.android.gms.common.internal;

import androidx.annotation.NonNull;
import com.google.android.gms.common.annotation.KeepForSdk;

@KeepForSdk
public final class AccountType {
    @NonNull
    @KeepForSdk
    public static final String GOOGLE = "com.google";
    @NonNull
    public static final String[] zza = {"com.google", "com.google.work", "cn.google"};

    private AccountType() {
    }
}
