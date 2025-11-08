package com.google.mlkit.vision.common.internal;

import com.google.android.odml.image.MlImage;
import java.util.concurrent.Callable;

public final /* synthetic */ class zzd implements Callable {
    public final /* synthetic */ MobileVisionBase zza;
    public final /* synthetic */ MlImage zzb;

    public /* synthetic */ zzd(MobileVisionBase mobileVisionBase, MlImage mlImage) {
        this.zza = mobileVisionBase;
        this.zzb = mlImage;
    }

    public final Object call() {
        return this.zza.zzb(this.zzb);
    }
}
