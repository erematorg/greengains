package com.fasterxml.jackson.databind.deser.std;

import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonMappingException;
import java.nio.ByteBuffer;
import java.util.HashSet;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

public class JdkDeserializers {
    private static final HashSet<String> _classNames = new HashSet<>();

    static {
        Class[] clsArr = {UUID.class, AtomicBoolean.class, AtomicInteger.class, AtomicLong.class, StackTraceElement.class, ByteBuffer.class, Void.class};
        for (int i3 = 0; i3 < 7; i3++) {
            _classNames.add(clsArr[i3].getName());
        }
        for (Class name : FromStringDeserializer.types()) {
            _classNames.add(name.getName());
        }
    }

    @Deprecated
    public static JsonDeserializer<?> find(Class<?> cls, String str) throws JsonMappingException {
        return find((DeserializationContext) null, cls, str);
    }

    public static boolean hasDeserializerFor(Class<?> cls) {
        return _classNames.contains(cls.getName());
    }

    public static JsonDeserializer<?> find(DeserializationContext deserializationContext, Class<?> cls, String str) throws JsonMappingException {
        if (!_classNames.contains(str)) {
            return null;
        }
        FromStringDeserializer<?> findDeserializer = FromStringDeserializer.findDeserializer(cls);
        if (findDeserializer != null) {
            return findDeserializer;
        }
        if (cls == UUID.class) {
            return new UUIDDeserializer();
        }
        if (cls == StackTraceElement.class) {
            return StackTraceElementDeserializer.construct(deserializationContext);
        }
        if (cls == AtomicBoolean.class) {
            return new AtomicBooleanDeserializer();
        }
        if (cls == AtomicInteger.class) {
            return new AtomicIntegerDeserializer();
        }
        if (cls == AtomicLong.class) {
            return new AtomicLongDeserializer();
        }
        if (cls == ByteBuffer.class) {
            return new ByteBufferDeserializer();
        }
        if (cls == Void.class) {
            return NullifyingDeserializer.instance;
        }
        return null;
    }
}
