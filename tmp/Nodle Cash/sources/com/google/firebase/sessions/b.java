package com.google.firebase.sessions;

import com.google.firebase.components.ComponentContainer;
import com.google.firebase.components.ComponentFactory;

public final /* synthetic */ class b implements ComponentFactory {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f7180a;

    public /* synthetic */ b(int i3) {
        this.f7180a = i3;
    }

    public final Object create(ComponentContainer componentContainer) {
        switch (this.f7180a) {
            case 0:
                return FirebaseSessionsRegistrar.getComponents$lambda$0(componentContainer);
            case 1:
                return FirebaseSessionsRegistrar.getComponents$lambda$1(componentContainer);
            case 2:
                return FirebaseSessionsRegistrar.getComponents$lambda$2(componentContainer);
            case 3:
                return FirebaseSessionsRegistrar.getComponents$lambda$3(componentContainer);
            case 4:
                return FirebaseSessionsRegistrar.getComponents$lambda$4(componentContainer);
            default:
                return FirebaseSessionsRegistrar.getComponents$lambda$5(componentContainer);
        }
    }
}
