package com.google.android.gms.internal.location;

import com.google.android.gms.common.api.internal.ListenerHolder;
import com.google.android.gms.common.api.internal.RemoteCall;
import com.google.android.gms.tasks.TaskCompletionSource;

final /* synthetic */ class zzcj implements RemoteCall {
    private final /* synthetic */ ListenerHolder zza;

    public /* synthetic */ zzcj(ListenerHolder listenerHolder) {
        this.zza = listenerHolder;
    }

    public final /* synthetic */ void accept(Object obj, Object obj2) {
        TaskCompletionSource taskCompletionSource = (TaskCompletionSource) obj2;
        zzdz zzdz = (zzdz) obj;
        ListenerHolder.ListenerKey listenerKey = this.zza.getListenerKey();
        if (listenerKey != null) {
            zzdz.zzD(listenerKey, taskCompletionSource);
        }
    }
}
