package com.google.mlkit.vision.barcode.internal;

import com.google.firebase.components.ComponentContainer;
import com.google.firebase.components.ComponentFactory;
import com.google.mlkit.common.sdkinternal.MlKitContext;

public final /* synthetic */ class zzc implements ComponentFactory {
    public final Object create(ComponentContainer componentContainer) {
        return new zzi((MlKitContext) componentContainer.get(MlKitContext.class));
    }
}
