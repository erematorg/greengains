package com.google.common.collect;

import com.google.common.collect.ImmutableTable;
import com.google.common.collect.TableCollectors;
import java.util.function.Function;

public final /* synthetic */ class k implements Function {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f6894a;

    public /* synthetic */ k(int i3) {
        this.f6894a = i3;
    }

    public final Object apply(Object obj) {
        switch (this.f6894a) {
            case 0:
                return ((TableCollectors.ImmutableTableCollectorState) obj).toTable();
            default:
                return ((ImmutableTable.Builder) obj).build();
        }
    }
}
