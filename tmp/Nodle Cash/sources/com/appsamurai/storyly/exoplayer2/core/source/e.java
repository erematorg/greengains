package com.appsamurai.storyly.exoplayer2.core.source;

import com.appsamurai.storyly.exoplayer2.core.source.DefaultMediaSourceFactory;
import com.google.common.base.Supplier;

public final /* synthetic */ class e implements Supplier {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f4560a;

    /* renamed from: b  reason: collision with root package name */
    public final /* synthetic */ Object f4561b;

    public /* synthetic */ e(Object obj, int i3) {
        this.f4560a = i3;
        this.f4561b = obj;
    }

    public final Object get() {
        int i3 = this.f4560a;
        Object obj = this.f4561b;
        switch (i3) {
            case 0:
                return DefaultMediaSourceFactory.newInstance((Class) obj);
            default:
                return ((DefaultMediaSourceFactory.DelegateFactoryLoader) obj).lambda$maybeLoadSupplier$4();
        }
    }
}
