package com.google.common.util.concurrent;

import com.google.common.base.Function;
import java.lang.reflect.Constructor;
import java.util.Arrays;
import java.util.List;

public final /* synthetic */ class m implements Function {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f6963a;

    public /* synthetic */ m(int i3) {
        this.f6963a = i3;
    }

    public final Object apply(Object obj) {
        switch (this.f6963a) {
            case 0:
                return Boolean.valueOf(((List) obj).contains(String.class));
            case 1:
                return Boolean.valueOf(((List) obj).contains(Throwable.class));
            case 2:
                return Arrays.asList(((Constructor) obj).getParameterTypes());
            default:
                return ((ClosingFuture) obj).future;
        }
    }
}
