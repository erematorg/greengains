package com.google.mlkit.common.internal;

import com.google.firebase.components.ComponentContainer;
import com.google.firebase.components.ComponentFactory;
import com.google.mlkit.common.sdkinternal.Cleaner;
import com.google.mlkit.common.sdkinternal.CloseGuard;

public final /* synthetic */ class zzf implements ComponentFactory {
    public final Object create(ComponentContainer componentContainer) {
        return new CloseGuard.Factory((Cleaner) componentContainer.get(Cleaner.class));
    }
}
