package com.google.mlkit.common.internal;

import com.google.firebase.components.ComponentContainer;
import com.google.firebase.components.ComponentFactory;
import com.google.mlkit.common.sdkinternal.MlKitThreadPool;

public final /* synthetic */ class zzb implements ComponentFactory {
    public final Object create(ComponentContainer componentContainer) {
        return new MlKitThreadPool();
    }
}
