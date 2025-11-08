package com.google.firebase.analytics;

import androidx.annotation.Nullable;
import java.util.concurrent.Callable;

final class zzd implements Callable<Long> {
    private final /* synthetic */ FirebaseAnalytics zza;

    public zzd(FirebaseAnalytics firebaseAnalytics) {
        this.zza = firebaseAnalytics;
    }

    @Nullable
    public final /* synthetic */ Object call() throws Exception {
        return this.zza.zzb.zzc();
    }
}
