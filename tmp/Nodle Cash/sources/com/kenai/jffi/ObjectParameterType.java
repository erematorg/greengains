package com.kenai.jffi;

import androidx.core.view.accessibility.AccessibilityEventCompat;
import com.appsamurai.storyly.exoplayer2.common.C;
import java.util.EnumSet;
import java.util.Iterator;

public final class ObjectParameterType {
    public static final ObjectType ARRAY = ObjectType.ARRAY;
    public static final ComponentType BOOLEAN = ComponentType.BOOLEAN;
    public static final ObjectType BUFFER = ObjectType.BUFFER;
    public static final ComponentType BYTE = ComponentType.BYTE;
    public static final ComponentType CHAR = ComponentType.CHAR;
    public static final ComponentType DOUBLE = ComponentType.DOUBLE;
    public static final ComponentType FLOAT = ComponentType.FLOAT;
    public static final ComponentType INT = ComponentType.INT;
    static final ObjectParameterType INVALID = new ObjectParameterType(0);
    public static final ComponentType LONG = ComponentType.LONG;
    static final ObjectParameterType NONE = new ObjectParameterType(0);
    public static final ComponentType SHORT = ComponentType.SHORT;
    final int typeInfo;

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

    public static final class TypeCache {
        static final ObjectParameterType[] arrayTypeCache;
        static final ObjectParameterType[] bufferTypeCache;

        static {
            EnumSet<E> allOf = EnumSet.allOf(ComponentType.class);
            arrayTypeCache = new ObjectParameterType[allOf.size()];
            bufferTypeCache = new ObjectParameterType[allOf.size()];
            Iterator<E> it = allOf.iterator();
            while (it.hasNext()) {
                ComponentType componentType = (ComponentType) it.next();
                arrayTypeCache[componentType.ordinal()] = new ObjectParameterType(ObjectParameterType.ARRAY, componentType);
                bufferTypeCache[componentType.ordinal()] = new ObjectParameterType(ObjectParameterType.BUFFER, componentType);
            }
        }

        private TypeCache() {
        }
    }

    public ObjectParameterType(int i3) {
        this.typeInfo = i3;
    }

    public static ObjectParameterType create(ObjectType objectType, ComponentType componentType) {
        return objectType == ObjectType.ARRAY ? TypeCache.arrayTypeCache[componentType.ordinal()] : objectType == ObjectType.BUFFER ? TypeCache.bufferTypeCache[componentType.ordinal()] : new ObjectParameterType(objectType.value | componentType.value);
    }

    public boolean equals(Object obj) {
        return this == obj || ((obj instanceof ObjectParameterType) && this.typeInfo == ((ObjectParameterType) obj).typeInfo);
    }

    public int hashCode() {
        return this.typeInfo;
    }

    public ObjectParameterType(ObjectType objectType, ComponentType componentType) {
        this.typeInfo = objectType.value | componentType.value;
    }
}
