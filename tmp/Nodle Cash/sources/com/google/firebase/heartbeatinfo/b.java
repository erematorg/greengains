package com.google.firebase.heartbeatinfo;

import com.google.firebase.components.ComponentContainer;
import com.google.firebase.components.ComponentFactory;
import com.google.firebase.components.Qualified;
import com.google.firebase.crashlytics.CrashlyticsRegistrar;
import com.google.firebase.remoteconfig.RemoteConfigRegistrar;

public final /* synthetic */ class b implements ComponentFactory {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f7116a;

    /* renamed from: b  reason: collision with root package name */
    public final /* synthetic */ Object f7117b;

    public /* synthetic */ b(Object obj, int i3) {
        this.f7116a = i3;
        this.f7117b = obj;
    }

    public final Object create(ComponentContainer componentContainer) {
        int i3 = this.f7116a;
        Object obj = this.f7117b;
        switch (i3) {
            case 0:
                return DefaultHeartBeatController.lambda$component$3((Qualified) obj, componentContainer);
            case 1:
                return RemoteConfigRegistrar.lambda$getComponents$0((Qualified) obj, componentContainer);
            default:
                return ((CrashlyticsRegistrar) obj).buildCrashlytics(componentContainer);
        }
    }
}
