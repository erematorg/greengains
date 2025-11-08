package com.google.common.collect;

import com.google.common.collect.ImmutableTable;
import java.util.function.BinaryOperator;

public final /* synthetic */ class g implements BinaryOperator {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f6885a;

    public /* synthetic */ g(int i3) {
        this.f6885a = i3;
    }

    public final Object apply(Object obj, Object obj2) {
        switch (this.f6885a) {
            case 0:
                return TableCollectors.lambda$toTable$5(obj, obj2);
            default:
                return ((ImmutableTable.Builder) obj).combine((ImmutableTable.Builder) obj2);
        }
    }
}
