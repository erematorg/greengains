package com.google.firebase.concurrent;

import com.google.firebase.inject.Provider;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.remoteconfig.RemoteConfigComponent;
import java.util.concurrent.Executors;

public final /* synthetic */ class g implements Provider {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f7081a;

    public /* synthetic */ g(int i3) {
        this.f7081a = i3;
    }

    public final Object get() {
        switch (this.f7081a) {
            case 0:
                return ExecutorsRegistrar.scheduled(Executors.newFixedThreadPool(4, ExecutorsRegistrar.factory("Firebase Background", 10, ExecutorsRegistrar.bgPolicy())));
            case 1:
                return ExecutorsRegistrar.scheduled(Executors.newFixedThreadPool(Math.max(2, Runtime.getRuntime().availableProcessors()), ExecutorsRegistrar.factory("Firebase Lite", 0, ExecutorsRegistrar.litePolicy())));
            case 2:
                return ExecutorsRegistrar.scheduled(Executors.newCachedThreadPool(ExecutorsRegistrar.factory("Firebase Blocking", 11)));
            case 3:
                return Executors.newSingleThreadScheduledExecutor(ExecutorsRegistrar.factory("Firebase Scheduler", 0));
            case 4:
                return FirebaseMessaging.lambda$clearTransportFactoryForTest$12();
            case 5:
                return FirebaseMessaging.lambda$static$0();
            default:
                return RemoteConfigComponent.lambda$getFetchHandler$0();
        }
    }
}
