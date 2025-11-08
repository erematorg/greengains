package com.google.mlkit.common.internal;

import com.google.firebase.components.ComponentContainer;
import com.google.firebase.components.ComponentFactory;
import com.google.mlkit.common.internal.model.zzg;
import com.google.mlkit.common.model.CustomRemoteModel;
import com.google.mlkit.common.model.RemoteModelManager;

public final /* synthetic */ class zzh implements ComponentFactory {
    public final Object create(ComponentContainer componentContainer) {
        return new RemoteModelManager.RemoteModelManagerRegistration(CustomRemoteModel.class, componentContainer.getProvider(zzg.class));
    }
}
