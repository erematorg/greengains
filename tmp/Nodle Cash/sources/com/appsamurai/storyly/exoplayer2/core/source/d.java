package com.appsamurai.storyly.exoplayer2.core.source;

import com.appsamurai.storyly.exoplayer2.core.source.DefaultMediaSourceFactory;
import com.google.common.base.Supplier;

public final /* synthetic */ class d implements Supplier {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f4557a;

    /* renamed from: b  reason: collision with root package name */
    public final /* synthetic */ DefaultMediaSourceFactory.DelegateFactoryLoader f4558b;

    /* renamed from: c  reason: collision with root package name */
    public final /* synthetic */ Class f4559c;

    public /* synthetic */ d(DefaultMediaSourceFactory.DelegateFactoryLoader delegateFactoryLoader, Class cls, int i3) {
        this.f4557a = i3;
        this.f4558b = delegateFactoryLoader;
        this.f4559c = cls;
    }

    public final Object get() {
        switch (this.f4557a) {
            case 0:
                return this.f4558b.lambda$maybeLoadSupplier$0(this.f4559c);
            case 1:
                return this.f4558b.lambda$maybeLoadSupplier$1(this.f4559c);
            default:
                return this.f4558b.lambda$maybeLoadSupplier$2(this.f4559c);
        }
    }
}
