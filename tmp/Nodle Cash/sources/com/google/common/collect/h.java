package com.google.common.collect;

import com.google.common.collect.TableCollectors;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;

public final /* synthetic */ class h implements BiConsumer {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f6886a;

    /* renamed from: b  reason: collision with root package name */
    public final /* synthetic */ Function f6887b;

    /* renamed from: c  reason: collision with root package name */
    public final /* synthetic */ Function f6888c;

    /* renamed from: d  reason: collision with root package name */
    public final /* synthetic */ Function f6889d;

    /* renamed from: e  reason: collision with root package name */
    public final /* synthetic */ BinaryOperator f6890e;

    public /* synthetic */ h(Function function, Function function2, Function function3, BinaryOperator binaryOperator, int i3) {
        this.f6886a = i3;
        this.f6887b = function;
        this.f6888c = function2;
        this.f6889d = function3;
        this.f6890e = binaryOperator;
    }

    public final void accept(Object obj, Object obj2) {
        switch (this.f6886a) {
            case 0:
                Function function = this.f6888c;
                Function function2 = this.f6889d;
                TableCollectors.mergeTables((Table) obj, this.f6887b.apply(obj2), function.apply(obj2), function2.apply(obj2), this.f6890e);
                return;
            default:
                Function function3 = this.f6888c;
                Function function4 = this.f6889d;
                ((TableCollectors.ImmutableTableCollectorState) obj).put(this.f6887b.apply(obj2), function3.apply(obj2), function4.apply(obj2), this.f6890e);
                return;
        }
    }
}
