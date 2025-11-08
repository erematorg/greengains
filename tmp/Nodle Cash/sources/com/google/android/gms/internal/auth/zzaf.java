package com.google.android.gms.internal.auth;

import com.google.android.gms.common.api.Status;

final class zzaf extends zzah {
    final /* synthetic */ zzag zza;

    public zzaf(zzag zzag) {
        this.zza = zzag;
    }

    public final void zzc(boolean z2) {
        this.zza.setResult(new zzak(z2 ? Status.RESULT_SUCCESS : zzal.zza));
    }
}
