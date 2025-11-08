package com.kenai.jffi;

import androidx.core.view.accessibility.AccessibilityEventCompat;
import com.appsamurai.storyly.exoplayer2.common.C;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public final class ObjectParameterInfo {
    public static final ObjectType ARRAY = ObjectType.ARRAY;
    public static final ComponentType BOOLEAN = ComponentType.BOOLEAN;
    public static final ObjectType BUFFER = ObjectType.BUFFER;
    public static final ComponentType BYTE = ComponentType.BYTE;
    private static final ConcurrentMap<Integer, ObjectParameterInfo> CACHE = new ConcurrentHashMap();
    public static final ComponentType CHAR = ComponentType.CHAR;
    public static final int CLEAR = 16;
    public static final ComponentType DOUBLE = ComponentType.DOUBLE;
    public static final ComponentType FLOAT = ComponentType.FLOAT;
    public static final int IN = 1;
    public static final ComponentType INT = ComponentType.INT;
    public static final ComponentType LONG = ComponentType.LONG;
    public static final int NULTERMINATE = 4;
    public static final int OUT = 2;
    public static final int PINNED = 8;
    public static final ComponentType SHORT = ComponentType.SHORT;
    private final int ioflags;
    private final int objectInfo;
    private final int parameterIndex;

    public enum ComponentType {
        BYTE(16777216),
        SHORT(33554432),
        INT(50331648),
        LONG(AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL),
        FLOAT(83886080),
        DOUBLE(100663296),
        BOOLEAN(117440512),
        CHAR(C.BUFFER_FLAG_FIRST_SAMPLE);
        
        final int value;

        private ComponentType(int i3) {
            this.value = i3;
        }
    }

    public enum ObjectType {
        ARRAY(268435456),
        BUFFER(536870912);
        
        final int value;

        private ObjectType(int i3) {
            this.value = i3;
        }
    }

    private ObjectParameterInfo(int i3) {
        this.objectInfo = i3;
        this.ioflags = i3 & 255;
        this.parameterIndex = (i3 & 16711680) >> 16;
    }

    public static ObjectParameterInfo create(int i3, ObjectType objectType, ComponentType componentType, int i4) {
        return getCachedInfo(ObjectBuffer.makeObjectFlags(i4, objectType.value | componentType.value, i3));
    }

    private static ObjectParameterInfo getCachedInfo(int i3) {
        ConcurrentMap<Integer, ObjectParameterInfo> concurrentMap = CACHE;
        ObjectParameterInfo objectParameterInfo = concurrentMap.get(Integer.valueOf(i3));
        if (objectParameterInfo != null) {
            return objectParameterInfo;
        }
        Integer valueOf = Integer.valueOf(i3);
        ObjectParameterInfo objectParameterInfo2 = new ObjectParameterInfo(i3);
        ObjectParameterInfo putIfAbsent = concurrentMap.putIfAbsent(valueOf, objectParameterInfo2);
        return putIfAbsent != null ? putIfAbsent : objectParameterInfo2;
    }

    public final int asObjectInfo() {
        return this.objectInfo;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || ObjectParameterInfo.class != obj.getClass()) {
            return false;
        }
        return this.objectInfo == ((ObjectParameterInfo) obj).objectInfo;
    }

    public final int getParameterIndex() {
        return this.parameterIndex;
    }

    public int hashCode() {
        return this.objectInfo * 31;
    }

    public final int ioflags() {
        return this.ioflags;
    }

    public static ObjectParameterInfo create(int i3, int i4) {
        return getCachedInfo(ObjectBuffer.makeObjectFlags(i4, 0, i3));
    }
}
