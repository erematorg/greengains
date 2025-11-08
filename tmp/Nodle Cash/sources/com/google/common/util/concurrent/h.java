package com.google.common.util.concurrent;

import com.google.common.base.Supplier;
import com.google.common.collect.ImmutableCollection;

public final /* synthetic */ class h implements Runnable {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f6950a;

    /* renamed from: b  reason: collision with root package name */
    public final /* synthetic */ Object f6951b;

    /* renamed from: c  reason: collision with root package name */
    public final /* synthetic */ Object f6952c;

    public /* synthetic */ h(Object obj, Object obj2, int i3) {
        this.f6950a = i3;
        this.f6951b = obj;
        this.f6952c = obj2;
    }

    public final void run() {
        switch (this.f6950a) {
            case 0:
                ((AggregateFuture) this.f6951b).lambda$init$1((ImmutableCollection) this.f6952c);
                return;
            default:
                Callables.lambda$threadRenaming$3((Supplier) this.f6951b, (Runnable) this.f6952c);
                return;
        }
    }
}
