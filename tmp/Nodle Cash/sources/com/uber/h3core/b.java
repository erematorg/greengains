package com.uber.h3core;

import java.util.List;
import java.util.function.Function;

public final /* synthetic */ class b implements Function {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f7658a;

    /* renamed from: b  reason: collision with root package name */
    public final /* synthetic */ H3Core f7659b;

    public /* synthetic */ b(H3Core h3Core, int i3) {
        this.f7658a = i3;
        this.f7659b = h3Core;
    }

    public final Object apply(Object obj) {
        switch (this.f7658a) {
            case 0:
                return this.f7659b.h3ToStringList((List) obj);
            case 1:
                return Long.valueOf(this.f7659b.stringToH3((String) obj));
            default:
                return this.f7659b.h3ToString(((Long) obj).longValue());
        }
    }
}
