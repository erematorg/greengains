package com.google.firebase.components;

import com.google.firebase.FirebaseApp;
import com.google.firebase.inject.Provider;
import com.google.firebase.installations.FirebaseInstallations;

public final /* synthetic */ class b implements Provider {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f7045a;

    /* renamed from: b  reason: collision with root package name */
    public final /* synthetic */ Object f7046b;

    public /* synthetic */ b(Object obj, int i3) {
        this.f7045a = i3;
        this.f7046b = obj;
    }

    public final Object get() {
        int i3 = this.f7045a;
        Object obj = this.f7046b;
        switch (i3) {
            case 0:
                return ComponentDiscovery.instantiate((String) obj);
            default:
                return FirebaseInstallations.lambda$new$0((FirebaseApp) obj);
        }
    }
}
