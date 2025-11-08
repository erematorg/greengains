package com.google.mlkit.common.internal;

import com.google.firebase.components.ComponentContainer;
import com.google.firebase.components.ComponentFactory;
import com.google.mlkit.common.model.RemoteModelManager;

public final /* synthetic */ class zzc implements ComponentFactory {
    public final Object create(ComponentContainer componentContainer) {
        return new RemoteModelManager(componentContainer.setOf(RemoteModelManager.RemoteModelManagerRegistration.class));
    }
}
