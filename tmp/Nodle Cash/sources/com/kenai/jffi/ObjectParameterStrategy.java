package com.kenai.jffi;

import androidx.core.view.ViewCompat;

public abstract class ObjectParameterStrategy<T> {
    protected static final StrategyType DIRECT = StrategyType.DIRECT;
    protected static final StrategyType HEAP = StrategyType.HEAP;
    private final boolean isDirect;
    final int typeInfo;

    public enum StrategyType {
        DIRECT,
        HEAP
    }

    public ObjectParameterStrategy(boolean z2) {
        this(z2, ObjectParameterType.INVALID);
    }

    public abstract long address(T t2);

    public final boolean isDirect() {
        return this.isDirect;
    }

    public abstract int length(T t2);

    public abstract Object object(T t2);

    public final int objectInfo(ObjectParameterInfo objectParameterInfo) {
        int asObjectInfo = objectParameterInfo.asObjectInfo();
        int i3 = this.typeInfo;
        return i3 != 0 ? i3 | (asObjectInfo & ViewCompat.MEASURED_SIZE_MASK) : asObjectInfo;
    }

    public abstract int offset(T t2);

    public ObjectParameterStrategy(boolean z2, ObjectParameterType objectParameterType) {
        this.isDirect = z2;
        this.typeInfo = objectParameterType.typeInfo;
    }

    public ObjectParameterStrategy(StrategyType strategyType) {
        this(strategyType, ObjectParameterType.INVALID);
    }

    public ObjectParameterStrategy(StrategyType strategyType, ObjectParameterType objectParameterType) {
        this.isDirect = strategyType == DIRECT;
        this.typeInfo = objectParameterType.typeInfo;
    }
}
