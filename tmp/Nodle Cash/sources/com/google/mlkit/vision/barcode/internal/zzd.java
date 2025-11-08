package com.google.mlkit.vision.barcode.internal;

import com.google.firebase.components.ComponentContainer;
import com.google.firebase.components.ComponentFactory;
import com.google.mlkit.common.sdkinternal.ExecutorSelector;
import com.google.mlkit.common.sdkinternal.MlKitContext;

public final /* synthetic */ class zzd implements ComponentFactory {
    public final Object create(ComponentContainer componentContainer) {
        return new zzg((zzi) componentContainer.get(zzi.class), (ExecutorSelector) componentContainer.get(ExecutorSelector.class), (MlKitContext) componentContainer.get(MlKitContext.class));
    }
}
