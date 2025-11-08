package com.google.common.collect;

import com.google.common.collect.ImmutableTable;
import java.util.function.Supplier;

public final /* synthetic */ class j implements Supplier {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f6893a;

    public /* synthetic */ j(int i3) {
        this.f6893a = i3;
    }

    public final Object get() {
        switch (this.f6893a) {
            case 0:
                return TableCollectors.lambda$toImmutableTable$1();
            default:
                return new ImmutableTable.Builder();
        }
    }
}
