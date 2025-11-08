package com.appsamurai.storyly.exoplayer2.core;

import com.google.common.base.Supplier;
import java.util.concurrent.atomic.AtomicBoolean;

public final /* synthetic */ class x implements Supplier {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f4611a;

    /* renamed from: b  reason: collision with root package name */
    public final /* synthetic */ Object f4612b;

    public /* synthetic */ x(Object obj, int i3) {
        this.f4611a = i3;
        this.f4612b = obj;
    }

    public final Object get() {
        int i3 = this.f4611a;
        Object obj = this.f4612b;
        switch (i3) {
            case 0:
                return ((ExoPlayerImplInternal) obj).lambda$release$0();
            default:
                return Boolean.valueOf(((AtomicBoolean) obj).get());
        }
    }
}
