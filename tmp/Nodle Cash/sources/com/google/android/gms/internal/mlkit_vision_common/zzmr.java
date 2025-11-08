package com.google.android.gms.internal.mlkit_vision_common;

import com.google.mlkit.common.sdkinternal.LazyInstanceMap;
import com.google.mlkit.common.sdkinternal.MlKitContext;
import com.google.mlkit.common.sdkinternal.SharedPrefManager;

final class zzmr extends LazyInstanceMap {
    private zzmr() {
    }

    public final /* bridge */ /* synthetic */ Object create(Object obj) {
        zzme zzme = (zzme) obj;
        MlKitContext instance = MlKitContext.getInstance();
        return new zzmj(instance.getApplicationContext(), (SharedPrefManager) instance.get(SharedPrefManager.class), new zzmf(MlKitContext.getInstance().getApplicationContext(), zzme), zzme.zzb());
    }

    public /* synthetic */ zzmr(zzmq zzmq) {
    }
}
