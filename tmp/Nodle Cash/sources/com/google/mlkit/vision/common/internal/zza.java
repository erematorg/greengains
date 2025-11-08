package com.google.mlkit.vision.common.internal;

import com.google.mlkit.vision.common.InputImage;
import java.util.concurrent.Callable;

public final /* synthetic */ class zza implements Callable {
    public final /* synthetic */ MobileVisionBase zza;
    public final /* synthetic */ InputImage zzb;

    public /* synthetic */ zza(MobileVisionBase mobileVisionBase, InputImage inputImage) {
        this.zza = mobileVisionBase;
        this.zzb = inputImage;
    }

    public final Object call() {
        return this.zza.zza(this.zzb);
    }
}
