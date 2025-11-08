package com.google.mlkit.common.sdkinternal;

import com.google.android.gms.common.Feature;
import com.google.android.gms.common.api.OptionalModuleApi;

public final /* synthetic */ class zzq implements OptionalModuleApi {
    public final /* synthetic */ Feature[] zza;

    public /* synthetic */ zzq(Feature[] featureArr) {
        this.zza = featureArr;
    }

    public final Feature[] getOptionalFeatures() {
        Feature[] featureArr = OptionalModuleUtils.EMPTY_FEATURES;
        return this.zza;
    }
}
