package com.squareup.moshi;

import android.support.v4.media.session.a;
import androidx.constraintlayout.core.state.b;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.JsonReader;
import com.squareup.moshi.internal.Util;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import javax.annotation.Nullable;

final class AdapterMethodsFactory implements JsonAdapter.Factory {
    private final List<AdapterMethod> fromAdapters;
    private final List<AdapterMethod> toAdapters;

    public AdapterMethodsFactory(List<AdapterMethod> list, List<AdapterMethod> list2) {
        this.toAdapters = list;
        this.fromAdapters = list2;
    }

    public static AdapterMethod fromAdapter(Object obj, Method method) {
        method.setAccessible(true);
        final Type genericReturnType = method.getGenericReturnType();
        final Set<? extends Annotation> jsonAnnotations = Util.jsonAnnotations((AnnotatedElement) method);
        final Type[] genericParameterTypes = method.getGenericParameterTypes();
        Annotation[][] parameterAnnotations = method.getParameterAnnotations();
        if (genericParameterTypes.length >= 1 && genericParameterTypes[0] == JsonReader.class && genericReturnType != Void.TYPE && parametersAreJsonAdapters(1, genericParameterTypes)) {
            return new AdapterMethod(genericReturnType, jsonAnnotations, obj, method, genericParameterTypes.length, 1, true) {
                public Object fromJson(Moshi moshi, JsonReader jsonReader) throws IOException, InvocationTargetException {
                    return invoke(jsonReader);
                }
            };
        } else if (genericParameterTypes.length != 1 || genericReturnType == Void.TYPE) {
            throw new IllegalArgumentException("Unexpected signature for " + method + ".\n@FromJson method signatures may have one of the following structures:\n    <any access modifier> R fromJson(JsonReader jsonReader) throws <any>;\n    <any access modifier> R fromJson(JsonReader jsonReader, JsonAdapter<any> delegate, <any more delegates>) throws <any>;\n    <any access modifier> R fromJson(T value) throws <any>;\n");
        } else {
            final Set<? extends Annotation> jsonAnnotations2 = Util.jsonAnnotations(parameterAnnotations[0]);
            return new AdapterMethod(genericReturnType, jsonAnnotations, obj, method, genericParameterTypes.length, 1, Util.hasNullable(parameterAnnotations[0])) {
                JsonAdapter<Object> delegate;

                public void bind(Moshi moshi, JsonAdapter.Factory factory) {
                    super.bind(moshi, factory);
                    this.delegate = (!Types.equals(genericParameterTypes[0], genericReturnType) || !jsonAnnotations2.equals(jsonAnnotations)) ? moshi.adapter(genericParameterTypes[0], (Set<? extends Annotation>) jsonAnnotations2) : moshi.nextAdapter(factory, genericParameterTypes[0], jsonAnnotations2);
                }

                public Object fromJson(Moshi moshi, JsonReader jsonReader) throws IOException, InvocationTargetException {
                    return invoke(this.delegate.fromJson(jsonReader));
                }
            };
        }
    }

    public static AdapterMethodsFactory get(Object obj) {
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        for (Class cls = obj.getClass(); cls != Object.class; cls = cls.getSuperclass()) {
            for (Method method : cls.getDeclaredMethods()) {
                if (method.isAnnotationPresent(ToJson.class)) {
                    AdapterMethod adapter = toAdapter(obj, method);
                    AdapterMethod adapterMethod = get(arrayList, adapter.type, adapter.annotations);
                    if (adapterMethod == null) {
                        arrayList.add(adapter);
                    } else {
                        throw new IllegalArgumentException("Conflicting @ToJson methods:\n    " + adapterMethod.method + "\n    " + adapter.method);
                    }
                }
                if (method.isAnnotationPresent(FromJson.class)) {
                    AdapterMethod fromAdapter = fromAdapter(obj, method);
                    AdapterMethod adapterMethod2 = get(arrayList2, fromAdapter.type, fromAdapter.annotations);
                    if (adapterMethod2 == null) {
                        arrayList2.add(fromAdapter);
                    } else {
                        throw new IllegalArgumentException("Conflicting @FromJson methods:\n    " + adapterMethod2.method + "\n    " + fromAdapter.method);
                    }
                }
            }
        }
        if (!arrayList.isEmpty() || !arrayList2.isEmpty()) {
            return new AdapterMethodsFactory(arrayList, arrayList2);
        }
        throw new IllegalArgumentException(b.h(obj, "Expected at least one @ToJson or @FromJson method on "));
    }

    private static boolean parametersAreJsonAdapters(int i3, Type[] typeArr) {
        int length = typeArr.length;
        while (i3 < length) {
            ParameterizedType parameterizedType = typeArr[i3];
            if (!(parameterizedType instanceof ParameterizedType) || parameterizedType.getRawType() != JsonAdapter.class) {
                return false;
            }
            i3++;
        }
        return true;
    }

    public static AdapterMethod toAdapter(Object obj, Method method) {
        method.setAccessible(true);
        final Type genericReturnType = method.getGenericReturnType();
        final Type[] genericParameterTypes = method.getGenericParameterTypes();
        Annotation[][] parameterAnnotations = method.getParameterAnnotations();
        if (genericParameterTypes.length >= 2 && genericParameterTypes[0] == JsonWriter.class && genericReturnType == Void.TYPE && parametersAreJsonAdapters(2, genericParameterTypes)) {
            return new AdapterMethod(genericParameterTypes[1], Util.jsonAnnotations(parameterAnnotations[1]), obj, method, genericParameterTypes.length, 2, true) {
                public void toJson(Moshi moshi, JsonWriter jsonWriter, @Nullable Object obj) throws IOException, InvocationTargetException {
                    invoke(jsonWriter, obj);
                }
            };
        } else if (genericParameterTypes.length != 1 || genericReturnType == Void.TYPE) {
            throw new IllegalArgumentException("Unexpected signature for " + method + ".\n@ToJson method signatures may have one of the following structures:\n    <any access modifier> void toJson(JsonWriter writer, T value) throws <any>;\n    <any access modifier> void toJson(JsonWriter writer, T value, JsonAdapter<any> delegate, <any more delegates>) throws <any>;\n    <any access modifier> R toJson(T value) throws <any>;\n");
        } else {
            final Set<? extends Annotation> jsonAnnotations = Util.jsonAnnotations((AnnotatedElement) method);
            final Set<? extends Annotation> jsonAnnotations2 = Util.jsonAnnotations(parameterAnnotations[0]);
            return new AdapterMethod(genericParameterTypes[0], jsonAnnotations2, obj, method, genericParameterTypes.length, 1, Util.hasNullable(parameterAnnotations[0])) {
                private JsonAdapter<Object> delegate;

                public void bind(Moshi moshi, JsonAdapter.Factory factory) {
                    super.bind(moshi, factory);
                    this.delegate = (!Types.equals(genericParameterTypes[0], genericReturnType) || !jsonAnnotations2.equals(jsonAnnotations)) ? moshi.adapter(genericReturnType, (Set<? extends Annotation>) jsonAnnotations) : moshi.nextAdapter(factory, genericReturnType, jsonAnnotations);
                }

                public void toJson(Moshi moshi, JsonWriter jsonWriter, @Nullable Object obj) throws IOException, InvocationTargetException {
                    this.delegate.toJson(jsonWriter, invoke(obj));
                }
            };
        }
    }

    @Nullable
    public JsonAdapter<?> create(Type type, Set<? extends Annotation> set, Moshi moshi) {
        final AdapterMethod adapterMethod = get(this.toAdapters, type, set);
        final AdapterMethod adapterMethod2 = get(this.fromAdapters, type, set);
        JsonAdapter<T> jsonAdapter = null;
        if (adapterMethod == null && adapterMethod2 == null) {
            return null;
        }
        if (adapterMethod == null || adapterMethod2 == null) {
            try {
                jsonAdapter = moshi.nextAdapter(this, type, set);
            } catch (IllegalArgumentException e3) {
                StringBuilder w2 = a.w("No ", adapterMethod == null ? "@ToJson" : "@FromJson", " adapter for ");
                w2.append(Util.typeAnnotatedWithAnnotations(type, set));
                throw new IllegalArgumentException(w2.toString(), e3);
            }
        }
        final JsonAdapter<T> jsonAdapter2 = jsonAdapter;
        if (adapterMethod != null) {
            adapterMethod.bind(moshi, this);
        }
        if (adapterMethod2 != null) {
            adapterMethod2.bind(moshi, this);
        }
        final Moshi moshi2 = moshi;
        final Set<? extends Annotation> set2 = set;
        final Type type2 = type;
        return new JsonAdapter<Object>() {
            @Nullable
            public Object fromJson(JsonReader jsonReader) throws IOException {
                AdapterMethod adapterMethod = adapterMethod2;
                if (adapterMethod == null) {
                    return jsonAdapter2.fromJson(jsonReader);
                }
                if (adapterMethod.nullable || jsonReader.peek() != JsonReader.Token.NULL) {
                    try {
                        return adapterMethod2.fromJson(moshi2, jsonReader);
                    } catch (InvocationTargetException e3) {
                        Throwable cause = e3.getCause();
                        if (cause instanceof IOException) {
                            throw ((IOException) cause);
                        }
                        throw new JsonDataException(cause + " at " + jsonReader.getPath(), cause);
                    }
                } else {
                    jsonReader.nextNull();
                    return null;
                }
            }

            public void toJson(JsonWriter jsonWriter, @Nullable Object obj) throws IOException {
                AdapterMethod adapterMethod = adapterMethod;
                if (adapterMethod == null) {
                    jsonAdapter2.toJson(jsonWriter, obj);
                } else if (adapterMethod.nullable || obj != null) {
                    try {
                        adapterMethod.toJson(moshi2, jsonWriter, obj);
                    } catch (InvocationTargetException e3) {
                        Throwable cause = e3.getCause();
                        if (cause instanceof IOException) {
                            throw ((IOException) cause);
                        }
                        throw new JsonDataException(cause + " at " + jsonWriter.getPath(), cause);
                    }
                } else {
                    jsonWriter.nullValue();
                }
            }

            public String toString() {
                return "JsonAdapter" + set2 + "(" + type2 + ")";
            }
        };
    }

    public static abstract class AdapterMethod {
        final Object adapter;
        final int adaptersOffset;
        final Set<? extends Annotation> annotations;
        final JsonAdapter<?>[] jsonAdapters;
        final Method method;
        final boolean nullable;
        final Type type;

        public AdapterMethod(Type type2, Set<? extends Annotation> set, Object obj, Method method2, int i3, int i4, boolean z2) {
            this.type = Util.canonicalize(type2);
            this.annotations = set;
            this.adapter = obj;
            this.method = method2;
            this.adaptersOffset = i4;
            this.jsonAdapters = new JsonAdapter[(i3 - i4)];
            this.nullable = z2;
        }

        public void bind(Moshi moshi, JsonAdapter.Factory factory) {
            if (this.jsonAdapters.length > 0) {
                Type[] genericParameterTypes = this.method.getGenericParameterTypes();
                Annotation[][] parameterAnnotations = this.method.getParameterAnnotations();
                int length = genericParameterTypes.length;
                for (int i3 = this.adaptersOffset; i3 < length; i3++) {
                    Type type2 = ((ParameterizedType) genericParameterTypes[i3]).getActualTypeArguments()[0];
                    Set<? extends Annotation> jsonAnnotations = Util.jsonAnnotations(parameterAnnotations[i3]);
                    this.jsonAdapters[i3 - this.adaptersOffset] = (!Types.equals(this.type, type2) || !this.annotations.equals(jsonAnnotations)) ? moshi.adapter(type2, jsonAnnotations) : moshi.nextAdapter(factory, type2, jsonAnnotations);
                }
            }
        }

        @Nullable
        public Object fromJson(Moshi moshi, JsonReader jsonReader) throws IOException, InvocationTargetException {
            throw new AssertionError();
        }

        @Nullable
        public Object invoke(@Nullable Object obj) throws InvocationTargetException {
            JsonAdapter<?>[] jsonAdapterArr = this.jsonAdapters;
            Object[] objArr = new Object[(jsonAdapterArr.length + 1)];
            objArr[0] = obj;
            System.arraycopy(jsonAdapterArr, 0, objArr, 1, jsonAdapterArr.length);
            try {
                return this.method.invoke(this.adapter, objArr);
            } catch (IllegalAccessException unused) {
                throw new AssertionError();
            }
        }

        public void toJson(Moshi moshi, JsonWriter jsonWriter, @Nullable Object obj) throws IOException, InvocationTargetException {
            throw new AssertionError();
        }

        public Object invoke(@Nullable Object obj, @Nullable Object obj2) throws InvocationTargetException {
            JsonAdapter<?>[] jsonAdapterArr = this.jsonAdapters;
            Object[] objArr = new Object[(jsonAdapterArr.length + 2)];
            objArr[0] = obj;
            objArr[1] = obj2;
            System.arraycopy(jsonAdapterArr, 0, objArr, 2, jsonAdapterArr.length);
            try {
                return this.method.invoke(this.adapter, objArr);
            } catch (IllegalAccessException unused) {
                throw new AssertionError();
            }
        }
    }

    @Nullable
    private static AdapterMethod get(List<AdapterMethod> list, Type type, Set<? extends Annotation> set) {
        int size = list.size();
        for (int i3 = 0; i3 < size; i3++) {
            AdapterMethod adapterMethod = list.get(i3);
            if (Types.equals(adapterMethod.type, type) && adapterMethod.annotations.equals(set)) {
                return adapterMethod;
            }
        }
        return null;
    }
}
