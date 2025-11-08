package com.google.android.gms.internal.mlkit_common;

import com.google.mlkit.common.sdkinternal.SharedPrefManager;
import java.util.concurrent.Callable;

public final /* synthetic */ class zzsf implements Callable {
    public final /* synthetic */ SharedPrefManager zza;

    public /* synthetic */ zzsf(SharedPrefManager sharedPrefManager) {
        this.zza = sharedPrefManager;
    }

    public final Object call() {
        return this.zza.getMlSdkInstanceId();
    }
}
