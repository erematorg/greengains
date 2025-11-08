package com.google.common.collect;

import com.google.common.base.Predicate;
import java.util.Collection;
import java.util.Map;

public final /* synthetic */ class a implements Predicate {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ Predicate f6877a;

    public /* synthetic */ a(Predicate predicate) {
        this.f6877a = predicate;
    }

    public final boolean apply(Object obj) {
        return this.f6877a.apply(Multisets.immutableEntry(((Map.Entry) obj).getKey(), ((Collection) ((Map.Entry) obj).getValue()).size()));
    }
}
