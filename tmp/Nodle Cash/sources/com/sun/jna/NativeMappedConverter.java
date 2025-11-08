package com.sun.jna;

import androidx.constraintlayout.core.state.b;
import java.lang.ref.Reference;
import java.lang.ref.SoftReference;
import java.util.Map;
import java.util.WeakHashMap;

public class NativeMappedConverter implements TypeConverter {
    private static final Map<Class<?>, Reference<NativeMappedConverter>> converters = new WeakHashMap();
    private final NativeMapped instance;
    private final Class<?> nativeType;
    private final Class<?> type;

    public NativeMappedConverter(Class<?> cls) {
        Class<NativeMapped> cls2 = NativeMapped.class;
        if (cls2.isAssignableFrom(cls)) {
            this.type = cls;
            NativeMapped defaultValue = defaultValue();
            this.instance = defaultValue;
            this.nativeType = defaultValue.nativeType();
            return;
        }
        throw new IllegalArgumentException(b.k("Type must derive from ", cls2));
    }

    public static NativeMappedConverter getInstance(Class<?> cls) {
        NativeMappedConverter nativeMappedConverter;
        Map<Class<?>, Reference<NativeMappedConverter>> map = converters;
        synchronized (map) {
            try {
                Reference reference = map.get(cls);
                nativeMappedConverter = reference != null ? (NativeMappedConverter) reference.get() : null;
                if (nativeMappedConverter == null) {
                    nativeMappedConverter = new NativeMappedConverter(cls);
                    map.put(cls, new SoftReference(nativeMappedConverter));
                }
            } catch (Throwable th) {
                throw th;
            }
        }
        return nativeMappedConverter;
    }

    public NativeMapped defaultValue() {
        return this.type.isEnum() ? (NativeMapped) this.type.getEnumConstants()[0] : (NativeMapped) Klass.newInstance(this.type);
    }

    public Object fromNative(Object obj, FromNativeContext fromNativeContext) {
        return this.instance.fromNative(obj, fromNativeContext);
    }

    public Class<?> nativeType() {
        return this.nativeType;
    }

    public Object toNative(Object obj, ToNativeContext toNativeContext) {
        if (obj == null) {
            if (Pointer.class.isAssignableFrom(this.nativeType)) {
                return null;
            }
            obj = defaultValue();
        }
        return ((NativeMapped) obj).toNative();
    }
}
