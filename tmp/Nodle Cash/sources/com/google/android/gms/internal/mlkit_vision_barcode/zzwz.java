package com.google.android.gms.internal.mlkit_vision_barcode;

import com.google.mlkit.common.sdkinternal.LazyInstanceMap;
import com.google.mlkit.common.sdkinternal.MlKitContext;
import com.google.mlkit.common.sdkinternal.SharedPrefManager;

final class zzwz extends LazyInstanceMap {
    private zzwz() {
        throw null;
    }

    public final /* bridge */ /* synthetic */ Object create(Object obj) {
        zzwh zzwh = (zzwh) obj;
        MlKitContext instance = MlKitContext.getInstance();
        return new zzwp(instance.getApplicationContext(), (SharedPrefManager) instance.get(SharedPrefManager.class), new zzwi(MlKitContext.getInstance().getApplicationContext(), zzwh), zzwh.zzb());
    }

    public /* synthetic */ zzwz(zzwy zzwy) {
    }
}
