package com.google.common.collect;

import com.google.common.collect.ImmutableTable;
import java.util.function.BiConsumer;
import java.util.function.Function;

public final /* synthetic */ class l implements BiConsumer {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ Function f6895a;

    /* renamed from: b  reason: collision with root package name */
    public final /* synthetic */ Function f6896b;

    /* renamed from: c  reason: collision with root package name */
    public final /* synthetic */ Function f6897c;

    public /* synthetic */ l(Function function, Function function2, Function function3) {
        this.f6895a = function;
        this.f6896b = function2;
        this.f6897c = function3;
    }

    public final void accept(Object obj, Object obj2) {
        ((ImmutableTable.Builder) obj).put(this.f6895a.apply(obj2), this.f6896b.apply(obj2), this.f6897c.apply(obj2));
    }
}
