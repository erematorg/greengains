package com.google.mlkit.vision.common.internal;

import com.google.android.gms.tasks.OnFailureListener;

public final /* synthetic */ class zzc implements OnFailureListener {
    public static final /* synthetic */ zzc zza = new zzc();

    private /* synthetic */ zzc() {
    }

    public final void onFailure(Exception exc) {
        MobileVisionBase.zzb.e("MobileVisionBase", "Error preloading model resource", exc);
    }
}
