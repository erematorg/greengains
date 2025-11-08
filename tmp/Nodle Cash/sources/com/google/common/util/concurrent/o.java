package com.google.common.util.concurrent;

import com.google.common.base.Supplier;
import com.google.common.util.concurrent.Striped;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public final /* synthetic */ class o implements Supplier {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f6967a;

    public /* synthetic */ o(int i3) {
        this.f6967a = i3;
    }

    public final Object get() {
        switch (this.f6967a) {
            case 0:
                return new Striped.WeakSafeReadWriteLock();
            case 1:
                return new Striped.PaddedLock();
            case 2:
                return Striped.lambda$lazyWeakLock$0();
            default:
                return new ReentrantReadWriteLock();
        }
    }
}
