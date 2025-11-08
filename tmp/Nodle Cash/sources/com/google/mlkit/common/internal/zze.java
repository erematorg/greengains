package com.google.mlkit.common.internal;

import com.google.firebase.components.ComponentContainer;
import com.google.firebase.components.ComponentFactory;
import com.google.mlkit.common.sdkinternal.Cleaner;

public final /* synthetic */ class zze implements ComponentFactory {
    public final Object create(ComponentContainer componentContainer) {
        return Cleaner.create();
    }
}
