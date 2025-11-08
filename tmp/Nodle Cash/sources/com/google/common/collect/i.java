package com.google.common.collect;

import com.google.common.collect.TableCollectors;
import java.util.function.BinaryOperator;

public final /* synthetic */ class i implements BinaryOperator {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f6891a;

    /* renamed from: b  reason: collision with root package name */
    public final /* synthetic */ BinaryOperator f6892b;

    public /* synthetic */ i(BinaryOperator binaryOperator, int i3) {
        this.f6891a = i3;
        this.f6892b = binaryOperator;
    }

    public final Object apply(Object obj, Object obj2) {
        switch (this.f6891a) {
            case 0:
                return TableCollectors.lambda$toTable$7(this.f6892b, (Table) obj, (Table) obj2);
            default:
                return ((TableCollectors.ImmutableTableCollectorState) obj).combine((TableCollectors.ImmutableTableCollectorState) obj2, this.f6892b);
        }
    }
}
