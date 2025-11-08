package com.google.mlkit.vision.barcode.internal;

import androidx.annotation.NonNull;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.internal.mlkit_vision_barcode.zzcs;
import com.google.firebase.components.Component;
import com.google.firebase.components.ComponentRegistrar;
import com.google.firebase.components.Dependency;
import com.google.mlkit.common.sdkinternal.ExecutorSelector;
import com.google.mlkit.common.sdkinternal.MlKitContext;
import java.util.List;

@KeepForSdk
public class BarcodeRegistrar implements ComponentRegistrar {
    public static final /* synthetic */ int zza = 0;

    @NonNull
    public final List getComponents() {
        Class<zzi> cls = zzi.class;
        Class<MlKitContext> cls2 = MlKitContext.class;
        return zzcs.zzh(Component.builder(cls).add(Dependency.required((Class<?>) cls2)).factory(new zzc()).build(), Component.builder(zzg.class).add(Dependency.required((Class<?>) cls)).add(Dependency.required((Class<?>) ExecutorSelector.class)).add(Dependency.required((Class<?>) cls2)).factory(new zzd()).build());
    }
}
