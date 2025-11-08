package com.fasterxml.jackson.databind.ser;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.impl.ReadOnlyClassToSerializerMap;
import com.fasterxml.jackson.databind.util.LRUMap;
import com.fasterxml.jackson.databind.util.TypeKey;
import java.util.concurrent.atomic.AtomicReference;

public final class SerializerCache {
    public static final int DEFAULT_MAX_CACHED = 4000;
    private final AtomicReference<ReadOnlyClassToSerializerMap> _readOnlyMap;
    private final LRUMap<TypeKey, JsonSerializer<Object>> _sharedMap;

    public SerializerCache() {
        this(DEFAULT_MAX_CACHED);
    }

    private final synchronized ReadOnlyClassToSerializerMap _makeReadOnlyLookupMap() {
        ReadOnlyClassToSerializerMap readOnlyClassToSerializerMap;
        readOnlyClassToSerializerMap = this._readOnlyMap.get();
        if (readOnlyClassToSerializerMap == null) {
            readOnlyClassToSerializerMap = ReadOnlyClassToSerializerMap.from(this._sharedMap);
            this._readOnlyMap.set(readOnlyClassToSerializerMap);
        }
        return readOnlyClassToSerializerMap;
    }

    public void addAndResolveNonTypedSerializer(Class<?> cls, JsonSerializer<Object> jsonSerializer, SerializerProvider serializerProvider) throws JsonMappingException {
        synchronized (this) {
            try {
                if (this._sharedMap.put(new TypeKey(cls, false), jsonSerializer) == null) {
                    this._readOnlyMap.set((Object) null);
                }
                if (jsonSerializer instanceof ResolvableSerializer) {
                    ((ResolvableSerializer) jsonSerializer).resolve(serializerProvider);
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public void addTypedSerializer(JavaType javaType, JsonSerializer<Object> jsonSerializer) {
        synchronized (this) {
            try {
                if (this._sharedMap.put(new TypeKey(javaType, true), jsonSerializer) == null) {
                    this._readOnlyMap.set((Object) null);
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public synchronized void flush() {
        this._sharedMap.clear();
    }

    public ReadOnlyClassToSerializerMap getReadOnlyLookupMap() {
        ReadOnlyClassToSerializerMap readOnlyClassToSerializerMap = this._readOnlyMap.get();
        return readOnlyClassToSerializerMap != null ? readOnlyClassToSerializerMap : _makeReadOnlyLookupMap();
    }

    public synchronized int size() {
        return this._sharedMap.size();
    }

    public JsonSerializer<Object> typedValueSerializer(JavaType javaType) {
        JsonSerializer<Object> jsonSerializer;
        synchronized (this) {
            jsonSerializer = this._sharedMap.get(new TypeKey(javaType, true));
        }
        return jsonSerializer;
    }

    public JsonSerializer<Object> untypedValueSerializer(Class<?> cls) {
        JsonSerializer<Object> jsonSerializer;
        synchronized (this) {
            jsonSerializer = this._sharedMap.get(new TypeKey(cls, false));
        }
        return jsonSerializer;
    }

    public SerializerCache(int i3) {
        this._sharedMap = new LRUMap<>(Math.min(64, i3 >> 2), i3);
        this._readOnlyMap = new AtomicReference<>();
    }

    public JsonSerializer<Object> typedValueSerializer(Class<?> cls) {
        JsonSerializer<Object> jsonSerializer;
        synchronized (this) {
            jsonSerializer = this._sharedMap.get(new TypeKey(cls, true));
        }
        return jsonSerializer;
    }

    public JsonSerializer<Object> untypedValueSerializer(JavaType javaType) {
        JsonSerializer<Object> jsonSerializer;
        synchronized (this) {
            jsonSerializer = this._sharedMap.get(new TypeKey(javaType, false));
        }
        return jsonSerializer;
    }

    public void addTypedSerializer(Class<?> cls, JsonSerializer<Object> jsonSerializer) {
        synchronized (this) {
            try {
                if (this._sharedMap.put(new TypeKey(cls, true), jsonSerializer) == null) {
                    this._readOnlyMap.set((Object) null);
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public void addAndResolveNonTypedSerializer(JavaType javaType, JsonSerializer<Object> jsonSerializer, SerializerProvider serializerProvider) throws JsonMappingException {
        synchronized (this) {
            try {
                if (this._sharedMap.put(new TypeKey(javaType, false), jsonSerializer) == null) {
                    this._readOnlyMap.set((Object) null);
                }
                if (jsonSerializer instanceof ResolvableSerializer) {
                    ((ResolvableSerializer) jsonSerializer).resolve(serializerProvider);
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public void addAndResolveNonTypedSerializer(Class<?> cls, JavaType javaType, JsonSerializer<Object> jsonSerializer, SerializerProvider serializerProvider) throws JsonMappingException {
        synchronized (this) {
            try {
                JsonSerializer<Object> put = this._sharedMap.put(new TypeKey(cls, false), jsonSerializer);
                JsonSerializer<Object> put2 = this._sharedMap.put(new TypeKey(javaType, false), jsonSerializer);
                if (put == null || put2 == null) {
                    this._readOnlyMap.set((Object) null);
                }
                if (jsonSerializer instanceof ResolvableSerializer) {
                    ((ResolvableSerializer) jsonSerializer).resolve(serializerProvider);
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }
}
