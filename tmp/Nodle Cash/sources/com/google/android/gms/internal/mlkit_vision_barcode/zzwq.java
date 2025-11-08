package com.google.android.gms.internal.mlkit_vision_barcode;

import com.google.android.gms.tasks.OnFailureListener;

public final /* synthetic */ class zzwq implements OnFailureListener {
    public final /* synthetic */ zzwr zza;
    public final /* synthetic */ long zzb;

    public /* synthetic */ zzwq(zzwr zzwr, long j2) {
        this.zza = zzwr;
        this.zzb = j2;
    }

    public final void onFailure(Exception exc) {
        this.zza.zzb(this.zzb, exc);
    }
}
