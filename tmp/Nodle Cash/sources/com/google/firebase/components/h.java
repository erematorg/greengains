package com.google.firebase.components;

import com.google.firebase.inject.Deferred;
import com.google.firebase.inject.Provider;

public final /* synthetic */ class h implements Deferred.DeferredHandler {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ Deferred.DeferredHandler f7055a;

    /* renamed from: b  reason: collision with root package name */
    public final /* synthetic */ Deferred.DeferredHandler f7056b;

    public /* synthetic */ h(Deferred.DeferredHandler deferredHandler, Deferred.DeferredHandler deferredHandler2) {
        this.f7055a = deferredHandler;
        this.f7056b = deferredHandler2;
    }

    public final void handle(Provider provider) {
        OptionalProvider.lambda$whenAvailable$2(this.f7055a, this.f7056b, provider);
    }
}
