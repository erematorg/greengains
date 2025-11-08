package com.google.mlkit.common.sdkinternal;

import java.lang.ref.ReferenceQueue;
import java.util.Set;

public final /* synthetic */ class zzb implements Runnable {
    public final /* synthetic */ ReferenceQueue zza;
    public final /* synthetic */ Set zzb;

    public /* synthetic */ zzb(ReferenceQueue referenceQueue, Set set) {
        this.zza = referenceQueue;
        this.zzb = set;
    }

    public final void run() {
        ReferenceQueue referenceQueue = this.zza;
        while (!this.zzb.isEmpty()) {
            try {
                ((zzd) referenceQueue.remove()).clean();
            } catch (InterruptedException unused) {
            }
        }
    }
}
