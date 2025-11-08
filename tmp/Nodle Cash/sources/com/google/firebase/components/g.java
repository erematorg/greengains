package com.google.firebase.components;

import com.google.firebase.inject.Provider;
import java.util.Collections;

public final /* synthetic */ class g implements Provider {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f7054a;

    public /* synthetic */ g(int i3) {
        this.f7054a = i3;
    }

    public final Object get() {
        switch (this.f7054a) {
            case 0:
                return OptionalProvider.lambda$static$1();
            default:
                return Collections.emptySet();
        }
    }
}
