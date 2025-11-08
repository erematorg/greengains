package com.google.common.collect;

import com.google.common.base.Function;
import java.util.Collection;
import java.util.Map;

public final /* synthetic */ class e implements Function {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f6882a;

    public /* synthetic */ e(int i3) {
        this.f6882a = i3;
    }

    public final Object apply(Object obj) {
        switch (this.f6882a) {
            case 0:
                return Multimaps.unmodifiableValueCollection((Collection) obj);
            case 1:
                return ((Iterable) obj).iterator();
            default:
                return ((Map) obj).keySet().iterator();
        }
    }
}
