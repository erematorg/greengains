package com.google.gson.internal.bind;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.annotations.JsonAdapter;
import com.google.gson.internal.ConstructorConstructor;
import com.google.gson.reflect.TypeToken;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public final class JsonAdapterAnnotationTypeAdapterFactory implements TypeAdapterFactory {
    private static final TypeAdapterFactory TREE_TYPE_CLASS_DUMMY_FACTORY = new DummyTypeAdapterFactory();
    private static final TypeAdapterFactory TREE_TYPE_FIELD_DUMMY_FACTORY = new DummyTypeAdapterFactory();
    private final ConcurrentMap<Class<?>, TypeAdapterFactory> adapterFactoryMap = new ConcurrentHashMap();
    private final ConstructorConstructor constructorConstructor;

    public static class DummyTypeAdapterFactory implements TypeAdapterFactory {
        private DummyTypeAdapterFactory() {
        }

        public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> typeToken) {
            throw new AssertionError("Factory should not be used");
        }
    }

    public JsonAdapterAnnotationTypeAdapterFactory(ConstructorConstructor constructorConstructor2) {
        this.constructorConstructor = constructorConstructor2;
    }

    private static Object createAdapter(ConstructorConstructor constructorConstructor2, Class<?> cls) {
        return constructorConstructor2.get(TypeToken.get(cls), true).construct();
    }

    private static JsonAdapter getAnnotation(Class<?> cls) {
        return (JsonAdapter) cls.getAnnotation(JsonAdapter.class);
    }

    private TypeAdapterFactory putFactoryAndGetCurrent(Class<?> cls, TypeAdapterFactory typeAdapterFactory) {
        TypeAdapterFactory putIfAbsent = this.adapterFactoryMap.putIfAbsent(cls, typeAdapterFactory);
        return putIfAbsent != null ? putIfAbsent : typeAdapterFactory;
    }

    public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> typeToken) {
        JsonAdapter annotation = getAnnotation(typeToken.getRawType());
        if (annotation == null) {
            return null;
        }
        return getTypeAdapter(this.constructorConstructor, gson, typeToken, annotation, true);
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v14, resolved type: com.google.gson.TypeAdapter<?>} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v16, resolved type: com.google.gson.TypeAdapter} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v2, resolved type: com.google.gson.internal.bind.TreeTypeAdapter} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v17, resolved type: com.google.gson.internal.bind.TreeTypeAdapter} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v18, resolved type: com.google.gson.internal.bind.TreeTypeAdapter} */
    /* JADX WARNING: type inference failed for: r8v2, types: [com.google.gson.TypeAdapter<?>, com.google.gson.TypeAdapter] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.google.gson.TypeAdapter<?> getTypeAdapter(com.google.gson.internal.ConstructorConstructor r8, com.google.gson.Gson r9, com.google.gson.reflect.TypeToken<?> r10, com.google.gson.annotations.JsonAdapter r11, boolean r12) {
        /*
            r7 = this;
            java.lang.Class r0 = r11.value()
            java.lang.Object r8 = createAdapter(r8, r0)
            boolean r6 = r11.nullSafe()
            boolean r11 = r8 instanceof com.google.gson.TypeAdapter
            if (r11 == 0) goto L_0x0014
            com.google.gson.TypeAdapter r8 = (com.google.gson.TypeAdapter) r8
            goto L_0x0083
        L_0x0014:
            boolean r11 = r8 instanceof com.google.gson.TypeAdapterFactory
            if (r11 == 0) goto L_0x0029
            com.google.gson.TypeAdapterFactory r8 = (com.google.gson.TypeAdapterFactory) r8
            if (r12 == 0) goto L_0x0024
            java.lang.Class r11 = r10.getRawType()
            com.google.gson.TypeAdapterFactory r8 = r7.putFactoryAndGetCurrent(r11, r8)
        L_0x0024:
            com.google.gson.TypeAdapter r8 = r8.create(r9, r10)
            goto L_0x0083
        L_0x0029:
            boolean r7 = r8 instanceof com.google.gson.JsonSerializer
            if (r7 != 0) goto L_0x005f
            boolean r11 = r8 instanceof com.google.gson.JsonDeserializer
            if (r11 == 0) goto L_0x0032
            goto L_0x005f
        L_0x0032:
            java.lang.IllegalArgumentException r7 = new java.lang.IllegalArgumentException
            java.lang.StringBuilder r9 = new java.lang.StringBuilder
            java.lang.String r11 = "Invalid attempt to bind an instance of "
            r9.<init>(r11)
            java.lang.Class r8 = r8.getClass()
            java.lang.String r8 = r8.getName()
            r9.append(r8)
            java.lang.String r8 = " as a @JsonAdapter for "
            r9.append(r8)
            java.lang.String r8 = r10.toString()
            r9.append(r8)
            java.lang.String r8 = ". @JsonAdapter value must be a TypeAdapter, TypeAdapterFactory, JsonSerializer or JsonDeserializer."
            r9.append(r8)
            java.lang.String r8 = r9.toString()
            r7.<init>(r8)
            throw r7
        L_0x005f:
            r11 = 0
            if (r7 == 0) goto L_0x0067
            r7 = r8
            com.google.gson.JsonSerializer r7 = (com.google.gson.JsonSerializer) r7
            r1 = r7
            goto L_0x0068
        L_0x0067:
            r1 = r11
        L_0x0068:
            boolean r7 = r8 instanceof com.google.gson.JsonDeserializer
            if (r7 == 0) goto L_0x0070
            com.google.gson.JsonDeserializer r8 = (com.google.gson.JsonDeserializer) r8
            r2 = r8
            goto L_0x0071
        L_0x0070:
            r2 = r11
        L_0x0071:
            if (r12 == 0) goto L_0x0077
            com.google.gson.TypeAdapterFactory r7 = TREE_TYPE_CLASS_DUMMY_FACTORY
        L_0x0075:
            r5 = r7
            goto L_0x007a
        L_0x0077:
            com.google.gson.TypeAdapterFactory r7 = TREE_TYPE_FIELD_DUMMY_FACTORY
            goto L_0x0075
        L_0x007a:
            com.google.gson.internal.bind.TreeTypeAdapter r8 = new com.google.gson.internal.bind.TreeTypeAdapter
            r0 = r8
            r3 = r9
            r4 = r10
            r0.<init>(r1, r2, r3, r4, r5, r6)
            r6 = 0
        L_0x0083:
            if (r8 == 0) goto L_0x008b
            if (r6 == 0) goto L_0x008b
            com.google.gson.TypeAdapter r8 = r8.nullSafe()
        L_0x008b:
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.gson.internal.bind.JsonAdapterAnnotationTypeAdapterFactory.getTypeAdapter(com.google.gson.internal.ConstructorConstructor, com.google.gson.Gson, com.google.gson.reflect.TypeToken, com.google.gson.annotations.JsonAdapter, boolean):com.google.gson.TypeAdapter");
    }

    public boolean isClassJsonAdapterFactory(TypeToken<?> typeToken, TypeAdapterFactory typeAdapterFactory) {
        Objects.requireNonNull(typeToken);
        Objects.requireNonNull(typeAdapterFactory);
        if (typeAdapterFactory == TREE_TYPE_CLASS_DUMMY_FACTORY) {
            return true;
        }
        Class<? super Object> rawType = typeToken.getRawType();
        TypeAdapterFactory typeAdapterFactory2 = this.adapterFactoryMap.get(rawType);
        if (typeAdapterFactory2 != null) {
            return typeAdapterFactory2 == typeAdapterFactory;
        }
        JsonAdapter annotation = getAnnotation(rawType);
        if (annotation == null) {
            return false;
        }
        Class<?> value = annotation.value();
        if (!TypeAdapterFactory.class.isAssignableFrom(value)) {
            return false;
        }
        return putFactoryAndGetCurrent(rawType, (TypeAdapterFactory) createAdapter(this.constructorConstructor, value)) == typeAdapterFactory;
    }
}
