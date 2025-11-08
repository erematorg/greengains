package com.google.android.gms.internal.mlkit_common;

import android.content.Context;
import androidx.core.content.ContextCompat;
import java.util.concurrent.Callable;

public final /* synthetic */ class zzc implements Callable {
    public final /* synthetic */ Context zza;

    public /* synthetic */ zzc(Context context) {
        this.zza = context;
    }

    public final Object call() {
        int i3 = zzi.zza;
        return ContextCompat.getExternalCacheDirs(this.zza);
    }
}
