package com.google.firebase.components;

import com.google.firebase.inject.Provider;

public final /* synthetic */ class d implements Provider {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ ComponentRuntime f7049a;

    /* renamed from: b  reason: collision with root package name */
    public final /* synthetic */ Component f7050b;

    public /* synthetic */ d(ComponentRuntime componentRuntime, Component component) {
        this.f7049a = componentRuntime;
        this.f7050b = component;
    }

    public final Object get() {
        return this.f7049a.lambda$discoverComponents$0(this.f7050b);
    }
}
