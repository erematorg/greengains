package com.google.mlkit.vision.common.internal;

import com.google.firebase.components.ComponentContainer;
import com.google.firebase.components.ComponentFactory;
import com.google.mlkit.vision.common.internal.MultiFlavorDetectorCreator;

public final /* synthetic */ class zzf implements ComponentFactory {
    public static final /* synthetic */ zzf zza = new zzf();

    private /* synthetic */ zzf() {
    }

    public final Object create(ComponentContainer componentContainer) {
        return new MultiFlavorDetectorCreator(componentContainer.setOf(MultiFlavorDetectorCreator.Registration.class));
    }
}
