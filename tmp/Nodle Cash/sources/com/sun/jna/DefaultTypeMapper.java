package com.sun.jna;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class DefaultTypeMapper implements TypeMapper {
    private List<Entry> fromNativeConverters = new ArrayList();
    private List<Entry> toNativeConverters = new ArrayList();

    public static class Entry {
        public Object converter;
        public Class<?> type;

        public Entry(Class<?> cls, Object obj) {
            this.type = cls;
            this.converter = obj;
        }
    }

    private Class<?> getAltClass(Class<?> cls) {
        Class<Boolean> cls2 = Boolean.class;
        if (cls == cls2) {
            return Boolean.TYPE;
        }
        if (cls == Boolean.TYPE) {
            return cls2;
        }
        Class<Byte> cls3 = Byte.class;
        if (cls == cls3) {
            return Byte.TYPE;
        }
        if (cls == Byte.TYPE) {
            return cls3;
        }
        Class<Character> cls4 = Character.class;
        if (cls == cls4) {
            return Character.TYPE;
        }
        if (cls == Character.TYPE) {
            return cls4;
        }
        Class<Short> cls5 = Short.class;
        if (cls == cls5) {
            return Short.TYPE;
        }
        if (cls == Short.TYPE) {
            return cls5;
        }
        Class<Integer> cls6 = Integer.class;
        if (cls == cls6) {
            return Integer.TYPE;
        }
        if (cls == Integer.TYPE) {
            return cls6;
        }
        Class<Long> cls7 = Long.class;
        if (cls == cls7) {
            return Long.TYPE;
        }
        if (cls == Long.TYPE) {
            return cls7;
        }
        Class<Float> cls8 = Float.class;
        if (cls == cls8) {
            return Float.TYPE;
        }
        if (cls == Float.TYPE) {
            return cls8;
        }
        Class<Double> cls9 = Double.class;
        if (cls == cls9) {
            return Double.TYPE;
        }
        if (cls == Double.TYPE) {
            return cls9;
        }
        return null;
    }

    private Object lookupConverter(Class<?> cls, Collection<? extends Entry> collection) {
        for (Entry entry : collection) {
            if (entry.type.isAssignableFrom(cls)) {
                return entry.converter;
            }
        }
        return null;
    }

    public void addFromNativeConverter(Class<?> cls, FromNativeConverter fromNativeConverter) {
        this.fromNativeConverters.add(new Entry(cls, fromNativeConverter));
        Class<?> altClass = getAltClass(cls);
        if (altClass != null) {
            this.fromNativeConverters.add(new Entry(altClass, fromNativeConverter));
        }
    }

    public void addToNativeConverter(Class<?> cls, ToNativeConverter toNativeConverter) {
        this.toNativeConverters.add(new Entry(cls, toNativeConverter));
        Class<?> altClass = getAltClass(cls);
        if (altClass != null) {
            this.toNativeConverters.add(new Entry(altClass, toNativeConverter));
        }
    }

    public void addTypeConverter(Class<?> cls, TypeConverter typeConverter) {
        addFromNativeConverter(cls, typeConverter);
        addToNativeConverter(cls, typeConverter);
    }

    public FromNativeConverter getFromNativeConverter(Class<?> cls) {
        return (FromNativeConverter) lookupConverter(cls, this.fromNativeConverters);
    }

    public ToNativeConverter getToNativeConverter(Class<?> cls) {
        return (ToNativeConverter) lookupConverter(cls, this.toNativeConverters);
    }
}
