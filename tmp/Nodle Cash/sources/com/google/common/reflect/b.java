package com.google.common.reflect;

import com.google.common.base.Function;
import com.google.common.reflect.MutableTypeToInstanceMap;
import java.util.Map;

public final /* synthetic */ class b implements Function {
    public final Object apply(Object obj) {
        return MutableTypeToInstanceMap.UnmodifiableEntry.a((Map.Entry) obj);
    }
}
