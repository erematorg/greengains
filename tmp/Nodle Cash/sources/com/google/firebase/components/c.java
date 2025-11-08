package com.google.firebase.components;

import com.google.firebase.components.ComponentRuntime;
import com.google.firebase.inject.Provider;

public final /* synthetic */ class c implements Provider {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f7047a;

    /* renamed from: b  reason: collision with root package name */
    public final /* synthetic */ ComponentRegistrar f7048b;

    public /* synthetic */ c(ComponentRegistrar componentRegistrar, int i3) {
        this.f7047a = i3;
        this.f7048b = componentRegistrar;
    }

    public final Object get() {
        int i3 = this.f7047a;
        ComponentRegistrar componentRegistrar = this.f7048b;
        switch (i3) {
            case 0:
                return ComponentRuntime.lambda$toProviders$1(componentRegistrar);
            default:
                return ComponentRuntime.Builder.lambda$addComponentRegistrar$0(componentRegistrar);
        }
    }
}
