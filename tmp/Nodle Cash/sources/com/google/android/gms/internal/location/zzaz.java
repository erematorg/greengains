package com.google.android.gms.internal.location;

import androidx.annotation.GuardedBy;
import com.google.android.gms.common.api.internal.ListenerHolder;

final class zzaz implements zzdr {
    @GuardedBy("this")
    private ListenerHolder zza;

    public zzaz(ListenerHolder listenerHolder) {
        this.zza = listenerHolder;
    }

    public final synchronized ListenerHolder zza() {
        return this.zza;
    }

    public final synchronized void zzb(ListenerHolder listenerHolder) {
        ListenerHolder listenerHolder2 = this.zza;
        if (listenerHolder2 != listenerHolder) {
            listenerHolder2.clear();
            this.zza = listenerHolder;
        }
    }

    public final void zzc() {
    }
}
