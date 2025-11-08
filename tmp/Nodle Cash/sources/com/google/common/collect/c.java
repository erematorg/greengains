package com.google.common.collect;

import com.google.common.base.Function;
import com.google.common.collect.Multimaps;

public final /* synthetic */ class c implements Function {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ Multimaps.AsMap.EntrySet f6880a;

    public /* synthetic */ c(Multimaps.AsMap.EntrySet entrySet) {
        this.f6880a = entrySet;
    }

    public final Object apply(Object obj) {
        return this.f6880a.lambda$iterator$0(obj);
    }
}
