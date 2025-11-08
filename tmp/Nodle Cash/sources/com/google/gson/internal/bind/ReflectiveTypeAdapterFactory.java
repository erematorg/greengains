package com.google.gson.internal.bind;

import A.a;
import androidx.browser.trusted.c;
import androidx.constraintlayout.core.state.b;
import com.google.gson.FieldNamingStrategy;
import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSyntaxException;
import com.google.gson.ReflectionAccessFilter;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.annotations.JsonAdapter;
import com.google.gson.annotations.SerializedName;
import com.google.gson.internal.ConstructorConstructor;
import com.google.gson.internal.Excluder;
import com.google.gson.internal.ObjectConstructor;
import com.google.gson.internal.Primitives;
import com.google.gson.internal.ReflectionAccessFilterHelper;
import com.google.gson.internal.TroubleshootingGuide;
import com.google.gson.internal.reflect.ReflectionHelper;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Member;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class ReflectiveTypeAdapterFactory implements TypeAdapterFactory {
    private final ConstructorConstructor constructorConstructor;
    private final Excluder excluder;
    private final FieldNamingStrategy fieldNamingPolicy;
    private final JsonAdapterAnnotationTypeAdapterFactory jsonAdapterFactory;
    private final List<ReflectionAccessFilter> reflectionFilters;

    public static abstract class Adapter<T, A> extends TypeAdapter<T> {
        private final FieldsData fieldsData;

        public Adapter(FieldsData fieldsData2) {
            this.fieldsData = fieldsData2;
        }

        public abstract A createAccumulator();

        public abstract T finalize(A a2);

        public T read(JsonReader jsonReader) throws IOException {
            if (jsonReader.peek() == JsonToken.NULL) {
                jsonReader.nextNull();
                return null;
            }
            Object createAccumulator = createAccumulator();
            Map<String, BoundField> map = this.fieldsData.deserializedFields;
            try {
                jsonReader.beginObject();
                while (jsonReader.hasNext()) {
                    BoundField boundField = map.get(jsonReader.nextName());
                    if (boundField == null) {
                        jsonReader.skipValue();
                    } else {
                        readField(createAccumulator, jsonReader, boundField);
                    }
                }
                jsonReader.endObject();
                return finalize(createAccumulator);
            } catch (IllegalStateException e3) {
                throw new JsonSyntaxException((Throwable) e3);
            } catch (IllegalAccessException e4) {
                throw ReflectionHelper.createExceptionForUnexpectedIllegalAccess(e4);
            }
        }

        public abstract void readField(A a2, JsonReader jsonReader, BoundField boundField) throws IllegalAccessException, IOException;

        public void write(JsonWriter jsonWriter, T t2) throws IOException {
            if (t2 == null) {
                jsonWriter.nullValue();
                return;
            }
            jsonWriter.beginObject();
            try {
                for (BoundField write : this.fieldsData.serializedFields) {
                    write.write(jsonWriter, t2);
                }
                jsonWriter.endObject();
            } catch (IllegalAccessException e3) {
                throw ReflectionHelper.createExceptionForUnexpectedIllegalAccess(e3);
            }
        }
    }

    public static abstract class BoundField {
        final Field field;
        final String fieldName;
        final String serializedName;

        public BoundField(String str, Field field2) {
            this.serializedName = str;
            this.field = field2;
            this.fieldName = field2.getName();
        }

        public abstract void readIntoArray(JsonReader jsonReader, int i3, Object[] objArr) throws IOException, JsonParseException;

        public abstract void readIntoField(JsonReader jsonReader, Object obj) throws IOException, IllegalAccessException;

        public abstract void write(JsonWriter jsonWriter, Object obj) throws IOException, IllegalAccessException;
    }

    public static final class FieldReflectionAdapter<T> extends Adapter<T, T> {
        private final ObjectConstructor<T> constructor;

        public FieldReflectionAdapter(ObjectConstructor<T> objectConstructor, FieldsData fieldsData) {
            super(fieldsData);
            this.constructor = objectConstructor;
        }

        public T createAccumulator() {
            return this.constructor.construct();
        }

        public T finalize(T t2) {
            return t2;
        }

        public void readField(T t2, JsonReader jsonReader, BoundField boundField) throws IllegalAccessException, IOException {
            boundField.readIntoField(jsonReader, t2);
        }
    }

    public static class FieldsData {
        static final FieldsData EMPTY = new FieldsData(Collections.emptyMap(), Collections.emptyList());
        final Map<String, BoundField> deserializedFields;
        final List<BoundField> serializedFields;

        public FieldsData(Map<String, BoundField> map, List<BoundField> list) {
            this.deserializedFields = map;
            this.serializedFields = list;
        }
    }

    public static final class RecordAdapter<T> extends Adapter<T, Object[]> {
        static final Map<Class<?>, Object> PRIMITIVE_DEFAULTS = primitiveDefaults();
        private final Map<String, Integer> componentIndices = new HashMap();
        private final Constructor<T> constructor;
        private final Object[] constructorArgsDefaults;

        public RecordAdapter(Class<T> cls, FieldsData fieldsData, boolean z2) {
            super(fieldsData);
            Constructor<T> canonicalRecordConstructor = ReflectionHelper.getCanonicalRecordConstructor(cls);
            this.constructor = canonicalRecordConstructor;
            if (z2) {
                ReflectiveTypeAdapterFactory.checkAccessible((Object) null, canonicalRecordConstructor);
            } else {
                ReflectionHelper.makeAccessible(canonicalRecordConstructor);
            }
            String[] recordComponentNames = ReflectionHelper.getRecordComponentNames(cls);
            for (int i3 = 0; i3 < recordComponentNames.length; i3++) {
                this.componentIndices.put(recordComponentNames[i3], Integer.valueOf(i3));
            }
            Class[] parameterTypes = this.constructor.getParameterTypes();
            this.constructorArgsDefaults = new Object[parameterTypes.length];
            for (int i4 = 0; i4 < parameterTypes.length; i4++) {
                this.constructorArgsDefaults[i4] = PRIMITIVE_DEFAULTS.get(parameterTypes[i4]);
            }
        }

        private static Map<Class<?>, Object> primitiveDefaults() {
            HashMap hashMap = new HashMap();
            hashMap.put(Byte.TYPE, (byte) 0);
            hashMap.put(Short.TYPE, (short) 0);
            hashMap.put(Integer.TYPE, 0);
            hashMap.put(Long.TYPE, 0L);
            hashMap.put(Float.TYPE, Float.valueOf(0.0f));
            hashMap.put(Double.TYPE, Double.valueOf(0.0d));
            hashMap.put(Character.TYPE, 0);
            hashMap.put(Boolean.TYPE, Boolean.FALSE);
            return hashMap;
        }

        public Object[] createAccumulator() {
            return (Object[]) this.constructorArgsDefaults.clone();
        }

        public T finalize(Object[] objArr) {
            try {
                return this.constructor.newInstance(objArr);
            } catch (IllegalAccessException e3) {
                throw ReflectionHelper.createExceptionForUnexpectedIllegalAccess(e3);
            } catch (IllegalArgumentException | InstantiationException e4) {
                throw new RuntimeException("Failed to invoke constructor '" + ReflectionHelper.constructorToString(this.constructor) + "' with args " + Arrays.toString(objArr), e4);
            } catch (InvocationTargetException e5) {
                throw new RuntimeException("Failed to invoke constructor '" + ReflectionHelper.constructorToString(this.constructor) + "' with args " + Arrays.toString(objArr), e5.getCause());
            }
        }

        public void readField(Object[] objArr, JsonReader jsonReader, BoundField boundField) throws IOException {
            Integer num = this.componentIndices.get(boundField.fieldName);
            if (num != null) {
                boundField.readIntoArray(jsonReader, num.intValue(), objArr);
                return;
            }
            StringBuilder sb = new StringBuilder("Could not find the index in the constructor '");
            sb.append(ReflectionHelper.constructorToString(this.constructor));
            sb.append("' for field with name '");
            throw new IllegalStateException(a.n(sb, boundField.fieldName, "', unable to determine which argument in the constructor the field corresponds to. This is unexpected behavior, as we expect the RecordComponents to have the same names as the fields in the Java class, and that the order of the RecordComponents is the same as the order of the canonical constructor parameters."));
        }
    }

    public ReflectiveTypeAdapterFactory(ConstructorConstructor constructorConstructor2, FieldNamingStrategy fieldNamingStrategy, Excluder excluder2, JsonAdapterAnnotationTypeAdapterFactory jsonAdapterAnnotationTypeAdapterFactory, List<ReflectionAccessFilter> list) {
        this.constructorConstructor = constructorConstructor2;
        this.fieldNamingPolicy = fieldNamingStrategy;
        this.excluder = excluder2;
        this.jsonAdapterFactory = jsonAdapterAnnotationTypeAdapterFactory;
        this.reflectionFilters = list;
    }

    /* access modifiers changed from: private */
    public static <M extends AccessibleObject & Member> void checkAccessible(Object obj, M m3) {
        if (Modifier.isStatic(((Member) m3).getModifiers())) {
            obj = null;
        }
        if (!ReflectionAccessFilterHelper.canAccess(m3, obj)) {
            throw new JsonIOException(android.support.v4.media.session.a.m(ReflectionHelper.getAccessibleObjectDescription(m3, true), " is not accessible and ReflectionAccessFilter does not permit making it accessible. Register a TypeAdapter for the declaring type, adjust the access filter or increase the visibility of the element and its declaring type."));
        }
    }

    private BoundField createBoundField(Gson gson, Field field, Method method, String str, TypeToken<?> typeToken, boolean z2, boolean z3) {
        final TypeAdapter<?> typeAdapter;
        Gson gson2 = gson;
        final boolean isPrimitive = Primitives.isPrimitive(typeToken.getRawType());
        int modifiers = field.getModifiers();
        boolean z4 = false;
        boolean z5 = Modifier.isStatic(modifiers) && Modifier.isFinal(modifiers);
        Field field2 = field;
        JsonAdapter jsonAdapter = (JsonAdapter) field.getAnnotation(JsonAdapter.class);
        TypeAdapter<?> typeAdapter2 = jsonAdapter != null ? this.jsonAdapterFactory.getTypeAdapter(this.constructorConstructor, gson, typeToken, jsonAdapter, false) : null;
        if (typeAdapter2 != null) {
            z4 = true;
        }
        TypeToken<?> typeToken2 = typeToken;
        if (typeAdapter2 == null) {
            typeAdapter2 = gson.getAdapter(typeToken2);
        }
        final TypeAdapter<?> typeAdapter3 = typeAdapter2;
        if (z2) {
            typeAdapter = z4 ? typeAdapter3 : new TypeAdapterRuntimeTypeWrapper<>(gson, typeAdapter3, typeToken.getType());
        } else {
            typeAdapter = typeAdapter3;
        }
        final boolean z6 = z3;
        final Method method2 = method;
        final boolean z7 = z5;
        return new BoundField(str, field) {
            public void readIntoArray(JsonReader jsonReader, int i3, Object[] objArr) throws IOException, JsonParseException {
                Object read = typeAdapter3.read(jsonReader);
                if (read != null || !isPrimitive) {
                    objArr[i3] = read;
                    return;
                }
                throw new JsonParseException("null is not allowed as value for record component '" + this.fieldName + "' of primitive type; at path " + jsonReader.getPath());
            }

            public void readIntoField(JsonReader jsonReader, Object obj) throws IOException, IllegalAccessException {
                Object read = typeAdapter3.read(jsonReader);
                if (read != null || !isPrimitive) {
                    if (z6) {
                        ReflectiveTypeAdapterFactory.checkAccessible(obj, this.field);
                    } else if (z7) {
                        throw new JsonIOException(c.a("Cannot set value of 'static final' ", ReflectionHelper.getAccessibleObjectDescription(this.field, false)));
                    }
                    this.field.set(obj, read);
                }
            }

            public void write(JsonWriter jsonWriter, Object obj) throws IOException, IllegalAccessException {
                Object obj2;
                if (z6) {
                    Method method = method2;
                    if (method == null) {
                        ReflectiveTypeAdapterFactory.checkAccessible(obj, this.field);
                    } else {
                        ReflectiveTypeAdapterFactory.checkAccessible(obj, method);
                    }
                }
                Method method2 = method2;
                if (method2 != null) {
                    try {
                        obj2 = method2.invoke(obj, (Object[]) null);
                    } catch (InvocationTargetException e3) {
                        throw new JsonIOException(a.l("Accessor ", ReflectionHelper.getAccessibleObjectDescription(method2, false), " threw exception"), e3.getCause());
                    }
                } else {
                    obj2 = this.field.get(obj);
                }
                if (obj2 != obj) {
                    jsonWriter.name(this.serializedName);
                    typeAdapter.write(jsonWriter, obj2);
                }
            }
        };
    }

    private static IllegalArgumentException createDuplicateFieldException(Class<?> cls, String str, Field field, Field field2) {
        throw new IllegalArgumentException("Class " + cls.getName() + " declares multiple JSON fields named '" + str + "'; conflict is caused by fields " + ReflectionHelper.fieldToString(field) + " and " + ReflectionHelper.fieldToString(field2) + "\nSee " + TroubleshootingGuide.createUrl("duplicate-fields"));
    }

    /* JADX WARNING: Removed duplicated region for block: B:46:0x00f7  */
    /* JADX WARNING: Removed duplicated region for block: B:54:0x0119  */
    /* JADX WARNING: Removed duplicated region for block: B:69:0x012b A[SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private com.google.gson.internal.bind.ReflectiveTypeAdapterFactory.FieldsData getBoundFields(com.google.gson.Gson r24, com.google.gson.reflect.TypeToken<?> r25, java.lang.Class<?> r26, boolean r27, boolean r28) {
        /*
            r23 = this;
            r8 = r23
            r9 = r26
            boolean r0 = r26.isInterface()
            if (r0 == 0) goto L_0x000d
            com.google.gson.internal.bind.ReflectiveTypeAdapterFactory$FieldsData r0 = com.google.gson.internal.bind.ReflectiveTypeAdapterFactory.FieldsData.EMPTY
            return r0
        L_0x000d:
            java.util.LinkedHashMap r10 = new java.util.LinkedHashMap
            r10.<init>()
            java.util.LinkedHashMap r11 = new java.util.LinkedHashMap
            r11.<init>()
            r12 = r25
            r0 = r27
            r13 = r9
        L_0x001c:
            java.lang.Class<java.lang.Object> r1 = java.lang.Object.class
            if (r13 == r1) goto L_0x014c
            java.lang.reflect.Field[] r14 = r13.getDeclaredFields()
            r15 = 1
            r7 = 0
            if (r13 == r9) goto L_0x003c
            int r1 = r14.length
            if (r1 <= 0) goto L_0x003c
            java.util.List<com.google.gson.ReflectionAccessFilter> r0 = r8.reflectionFilters
            com.google.gson.ReflectionAccessFilter$FilterResult r0 = com.google.gson.internal.ReflectionAccessFilterHelper.getFilterResult(r0, r13)
            com.google.gson.ReflectionAccessFilter$FilterResult r1 = com.google.gson.ReflectionAccessFilter.FilterResult.BLOCK_ALL
            if (r0 == r1) goto L_0x003f
            com.google.gson.ReflectionAccessFilter$FilterResult r1 = com.google.gson.ReflectionAccessFilter.FilterResult.BLOCK_INACCESSIBLE
            if (r0 != r1) goto L_0x003b
            r0 = r15
            goto L_0x003c
        L_0x003b:
            r0 = r7
        L_0x003c:
            r16 = r0
            goto L_0x0060
        L_0x003f:
            com.google.gson.JsonIOException r0 = new com.google.gson.JsonIOException
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            java.lang.String r2 = "ReflectionAccessFilter does not permit using reflection for "
            r1.<init>(r2)
            r1.append(r13)
            java.lang.String r2 = " (supertype of "
            r1.append(r2)
            r1.append(r9)
            java.lang.String r2 = "). Register a TypeAdapter for this type or adjust the access filter."
            r1.append(r2)
            java.lang.String r1 = r1.toString()
            r0.<init>((java.lang.String) r1)
            throw r0
        L_0x0060:
            int r6 = r14.length
            r5 = r7
        L_0x0062:
            if (r5 >= r6) goto L_0x0134
            r4 = r14[r5]
            boolean r17 = r8.includeField(r4, r15)
            boolean r0 = r8.includeField(r4, r7)
            if (r17 != 0) goto L_0x007a
            if (r0 != 0) goto L_0x007a
            r21 = r5
            r19 = r6
            r22 = r7
            goto L_0x012b
        L_0x007a:
            r1 = 0
            if (r28 == 0) goto L_0x00b5
            int r2 = r4.getModifiers()
            boolean r2 = java.lang.reflect.Modifier.isStatic(r2)
            if (r2 == 0) goto L_0x008b
            r3 = r1
            r18 = r7
            goto L_0x00b8
        L_0x008b:
            java.lang.reflect.Method r1 = com.google.gson.internal.reflect.ReflectionHelper.getAccessor(r13, r4)
            if (r16 != 0) goto L_0x0094
            com.google.gson.internal.reflect.ReflectionHelper.makeAccessible(r1)
        L_0x0094:
            java.lang.Class<com.google.gson.annotations.SerializedName> r2 = com.google.gson.annotations.SerializedName.class
            java.lang.annotation.Annotation r3 = r1.getAnnotation(r2)
            if (r3 == 0) goto L_0x00b5
            java.lang.annotation.Annotation r2 = r4.getAnnotation(r2)
            if (r2 == 0) goto L_0x00a3
            goto L_0x00b5
        L_0x00a3:
            java.lang.String r0 = com.google.gson.internal.reflect.ReflectionHelper.getAccessibleObjectDescription(r1, r7)
            com.google.gson.JsonIOException r1 = new com.google.gson.JsonIOException
            java.lang.String r2 = "@SerializedName on "
            java.lang.String r3 = " is not supported"
            java.lang.String r0 = A.a.l(r2, r0, r3)
            r1.<init>((java.lang.String) r0)
            throw r1
        L_0x00b5:
            r18 = r0
            r3 = r1
        L_0x00b8:
            if (r16 != 0) goto L_0x00bf
            if (r3 != 0) goto L_0x00bf
            com.google.gson.internal.reflect.ReflectionHelper.makeAccessible(r4)
        L_0x00bf:
            java.lang.reflect.Type r0 = r12.getType()
            java.lang.reflect.Type r1 = r4.getGenericType()
            java.lang.reflect.Type r0 = com.google.gson.internal.GsonTypes.resolve(r0, r13, r1)
            java.util.List r2 = r8.getFieldNames(r4)
            java.lang.Object r1 = r2.get(r7)
            java.lang.String r1 = (java.lang.String) r1
            com.google.gson.reflect.TypeToken r19 = com.google.gson.reflect.TypeToken.get((java.lang.reflect.Type) r0)
            r0 = r23
            r25 = r1
            r1 = r24
            r20 = r2
            r2 = r4
            r15 = r4
            r4 = r25
            r21 = r5
            r5 = r19
            r19 = r6
            r6 = r17
            r22 = r7
            r7 = r16
            com.google.gson.internal.bind.ReflectiveTypeAdapterFactory$BoundField r0 = r0.createBoundField(r1, r2, r3, r4, r5, r6, r7)
            if (r18 == 0) goto L_0x0117
            java.util.Iterator r1 = r20.iterator()
        L_0x00fb:
            boolean r2 = r1.hasNext()
            if (r2 == 0) goto L_0x0117
            java.lang.Object r2 = r1.next()
            java.lang.String r2 = (java.lang.String) r2
            java.lang.Object r3 = r10.put(r2, r0)
            com.google.gson.internal.bind.ReflectiveTypeAdapterFactory$BoundField r3 = (com.google.gson.internal.bind.ReflectiveTypeAdapterFactory.BoundField) r3
            if (r3 != 0) goto L_0x0110
            goto L_0x00fb
        L_0x0110:
            java.lang.reflect.Field r0 = r3.field
            java.lang.IllegalArgumentException r0 = createDuplicateFieldException(r9, r2, r0, r15)
            throw r0
        L_0x0117:
            if (r17 == 0) goto L_0x012b
            r1 = r25
            java.lang.Object r0 = r11.put(r1, r0)
            com.google.gson.internal.bind.ReflectiveTypeAdapterFactory$BoundField r0 = (com.google.gson.internal.bind.ReflectiveTypeAdapterFactory.BoundField) r0
            if (r0 != 0) goto L_0x0124
            goto L_0x012b
        L_0x0124:
            java.lang.reflect.Field r0 = r0.field
            java.lang.IllegalArgumentException r0 = createDuplicateFieldException(r9, r1, r0, r15)
            throw r0
        L_0x012b:
            int r5 = r21 + 1
            r6 = r19
            r7 = r22
            r15 = 1
            goto L_0x0062
        L_0x0134:
            java.lang.reflect.Type r0 = r12.getType()
            java.lang.reflect.Type r1 = r13.getGenericSuperclass()
            java.lang.reflect.Type r0 = com.google.gson.internal.GsonTypes.resolve(r0, r13, r1)
            com.google.gson.reflect.TypeToken r12 = com.google.gson.reflect.TypeToken.get((java.lang.reflect.Type) r0)
            java.lang.Class r13 = r12.getRawType()
            r0 = r16
            goto L_0x001c
        L_0x014c:
            com.google.gson.internal.bind.ReflectiveTypeAdapterFactory$FieldsData r0 = new com.google.gson.internal.bind.ReflectiveTypeAdapterFactory$FieldsData
            java.util.ArrayList r1 = new java.util.ArrayList
            java.util.Collection r2 = r11.values()
            r1.<init>(r2)
            r0.<init>(r10, r1)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.gson.internal.bind.ReflectiveTypeAdapterFactory.getBoundFields(com.google.gson.Gson, com.google.gson.reflect.TypeToken, java.lang.Class, boolean, boolean):com.google.gson.internal.bind.ReflectiveTypeAdapterFactory$FieldsData");
    }

    private List<String> getFieldNames(Field field) {
        List<String> list;
        String str;
        SerializedName serializedName = (SerializedName) field.getAnnotation(SerializedName.class);
        if (serializedName == null) {
            str = this.fieldNamingPolicy.translateName(field);
            list = this.fieldNamingPolicy.alternateNames(field);
        } else {
            str = serializedName.value();
            list = Arrays.asList(serializedName.alternate());
        }
        if (list.isEmpty()) {
            return Collections.singletonList(str);
        }
        ArrayList arrayList = new ArrayList(list.size() + 1);
        arrayList.add(str);
        arrayList.addAll(list);
        return arrayList;
    }

    private boolean includeField(Field field, boolean z2) {
        return !this.excluder.excludeField(field, z2);
    }

    public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> typeToken) {
        Class<? super T> rawType = typeToken.getRawType();
        if (!Object.class.isAssignableFrom(rawType)) {
            return null;
        }
        if (ReflectionHelper.isAnonymousOrNonStaticLocal(rawType)) {
            return new TypeAdapter<T>() {
                public T read(JsonReader jsonReader) throws IOException {
                    jsonReader.skipValue();
                    return null;
                }

                public String toString() {
                    return "AnonymousOrNonStaticLocalClassAdapter";
                }

                public void write(JsonWriter jsonWriter, T t2) throws IOException {
                    jsonWriter.nullValue();
                }
            };
        }
        ReflectionAccessFilter.FilterResult filterResult = ReflectionAccessFilterHelper.getFilterResult(this.reflectionFilters, rawType);
        if (filterResult != ReflectionAccessFilter.FilterResult.BLOCK_ALL) {
            boolean z2 = filterResult == ReflectionAccessFilter.FilterResult.BLOCK_INACCESSIBLE;
            return ReflectionHelper.isRecord(rawType) ? new RecordAdapter(rawType, getBoundFields(gson, typeToken, rawType, z2, true), z2) : new FieldReflectionAdapter(this.constructorConstructor.get(typeToken, true), getBoundFields(gson, typeToken, rawType, z2, false));
        }
        throw new JsonIOException(b.l("ReflectionAccessFilter does not permit using reflection for ", rawType, ". Register a TypeAdapter for this type or adjust the access filter."));
    }
}
