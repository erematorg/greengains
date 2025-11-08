package com.google.common.util.concurrent;

import com.google.common.collect.ImmutableList;
import com.google.common.util.concurrent.Futures;

public final /* synthetic */ class g implements Runnable {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f6946a;

    /* renamed from: b  reason: collision with root package name */
    public final /* synthetic */ int f6947b;

    /* renamed from: c  reason: collision with root package name */
    public final /* synthetic */ Object f6948c;

    /* renamed from: d  reason: collision with root package name */
    public final /* synthetic */ Object f6949d;

    public /* synthetic */ g(int i3, int i4, Object obj, Object obj2) {
        this.f6946a = i4;
        this.f6948c = obj;
        this.f6949d = obj2;
        this.f6947b = i3;
    }

    public final void run() {
        switch (this.f6946a) {
            case 0:
                ((AggregateFuture) this.f6948c).lambda$init$0((ListenableFuture) this.f6949d, this.f6947b);
                return;
            default:
                ((Futures.InCompletionOrderState) this.f6948c).recordInputCompletion((ImmutableList) this.f6949d, this.f6947b);
                return;
        }
    }
}
