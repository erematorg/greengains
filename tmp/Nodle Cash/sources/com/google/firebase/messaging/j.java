package com.google.firebase.messaging;

import com.google.firebase.components.ComponentContainer;
import com.google.firebase.components.ComponentFactory;
import com.google.firebase.components.Qualified;

public final /* synthetic */ class j implements ComponentFactory {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ Qualified f7143a;

    public /* synthetic */ j(Qualified qualified) {
        this.f7143a = qualified;
    }

    public final Object create(ComponentContainer componentContainer) {
        return FirebaseMessagingRegistrar.lambda$getComponents$0(this.f7143a, componentContainer);
    }
}
