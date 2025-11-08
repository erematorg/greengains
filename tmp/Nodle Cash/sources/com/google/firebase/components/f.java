package com.google.firebase.components;

import com.google.firebase.inject.Deferred;
import com.google.firebase.inject.Provider;
import java.util.List;

public final /* synthetic */ class f implements Deferred.DeferredHandler, ComponentRegistrarProcessor {
    public void handle(Provider provider) {
        OptionalProvider.lambda$static$0(provider);
    }

    public List processRegistrar(ComponentRegistrar componentRegistrar) {
        return componentRegistrar.getComponents();
    }
}
