package com.squareup.moshi.adapters;

import A.a;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.JsonDataException;
import com.squareup.moshi.JsonReader;
import com.squareup.moshi.JsonWriter;
import com.squareup.moshi.Moshi;
import com.squareup.moshi.Types;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import javax.annotation.CheckReturnValue;
import javax.annotation.Nullable;

public final class PolymorphicJsonAdapterFactory<T> implements JsonAdapter.Factory {
    final Class<T> baseType;
    @Nullable
    final JsonAdapter<Object> fallbackJsonAdapter;
    final String labelKey;
    final List<String> labels;
    final List<Type> subtypes;

    public static final class PolymorphicJsonAdapter extends JsonAdapter<Object> {
        @Nullable
        final JsonAdapter<Object> fallbackJsonAdapter;
        final List<JsonAdapter<Object>> jsonAdapters;
        final String labelKey;
        final JsonReader.Options labelKeyOptions;
        final JsonReader.Options labelOptions;
        final List<String> labels;
        final List<Type> subtypes;

        public PolymorphicJsonAdapter(String str, List<String> list, List<Type> list2, List<JsonAdapter<Object>> list3, @Nullable JsonAdapter<Object> jsonAdapter) {
            this.labelKey = str;
            this.labels = list;
            this.subtypes = list2;
            this.jsonAdapters = list3;
            this.fallbackJsonAdapter = jsonAdapter;
            this.labelKeyOptions = JsonReader.Options.of(str);
            this.labelOptions = JsonReader.Options.of((String[]) list.toArray(new String[0]));
        }

        private int labelIndex(JsonReader jsonReader) throws IOException {
            jsonReader.beginObject();
            while (jsonReader.hasNext()) {
                if (jsonReader.selectName(this.labelKeyOptions) == -1) {
                    jsonReader.skipName();
                    jsonReader.skipValue();
                } else {
                    int selectString = jsonReader.selectString(this.labelOptions);
                    if (selectString != -1 || this.fallbackJsonAdapter != null) {
                        return selectString;
                    }
                    throw new JsonDataException("Expected one of " + this.labels + " for key '" + this.labelKey + "' but found '" + jsonReader.nextString() + "'. Register a subtype for this label.");
                }
            }
            throw new JsonDataException("Missing label for " + this.labelKey);
        }

        /* JADX INFO: finally extract failed */
        public Object fromJson(JsonReader jsonReader) throws IOException {
            JsonReader peekJson = jsonReader.peekJson();
            peekJson.setFailOnUnknown(false);
            try {
                int labelIndex = labelIndex(peekJson);
                peekJson.close();
                return labelIndex == -1 ? this.fallbackJsonAdapter.fromJson(jsonReader) : this.jsonAdapters.get(labelIndex).fromJson(jsonReader);
            } catch (Throwable th) {
                peekJson.close();
                throw th;
            }
        }

        public void toJson(JsonWriter jsonWriter, Object obj) throws IOException {
            JsonAdapter<Object> jsonAdapter;
            int indexOf = this.subtypes.indexOf(obj.getClass());
            if (indexOf == -1) {
                jsonAdapter = this.fallbackJsonAdapter;
                if (jsonAdapter == null) {
                    throw new IllegalArgumentException("Expected one of " + this.subtypes + " but found " + obj + ", a " + obj.getClass() + ". Register this subtype.");
                }
            } else {
                jsonAdapter = this.jsonAdapters.get(indexOf);
            }
            jsonWriter.beginObject();
            if (jsonAdapter != this.fallbackJsonAdapter) {
                jsonWriter.name(this.labelKey).value(this.labels.get(indexOf));
            }
            int beginFlatten = jsonWriter.beginFlatten();
            jsonAdapter.toJson(jsonWriter, obj);
            jsonWriter.endFlatten(beginFlatten);
            jsonWriter.endObject();
        }

        public String toString() {
            return a.n(new StringBuilder("PolymorphicJsonAdapter("), this.labelKey, ")");
        }
    }

    public PolymorphicJsonAdapterFactory(Class<T> cls, String str, List<String> list, List<Type> list2, @Nullable JsonAdapter<Object> jsonAdapter) {
        this.baseType = cls;
        this.labelKey = str;
        this.labels = list;
        this.subtypes = list2;
        this.fallbackJsonAdapter = jsonAdapter;
    }

    private JsonAdapter<Object> buildFallbackJsonAdapter(final T t2) {
        return new JsonAdapter<Object>() {
            @Nullable
            public Object fromJson(JsonReader jsonReader) throws IOException {
                jsonReader.skipValue();
                return t2;
            }

            public void toJson(JsonWriter jsonWriter, Object obj) throws IOException {
                throw new IllegalArgumentException("Expected one of " + PolymorphicJsonAdapterFactory.this.subtypes + " but found " + obj + ", a " + obj.getClass() + ". Register this subtype.");
            }
        };
    }

    @CheckReturnValue
    public static <T> PolymorphicJsonAdapterFactory<T> of(Class<T> cls, String str) {
        if (cls == null) {
            throw new NullPointerException("baseType == null");
        } else if (str != null) {
            return new PolymorphicJsonAdapterFactory(cls, str, Collections.emptyList(), Collections.emptyList(), (JsonAdapter<Object>) null);
        } else {
            throw new NullPointerException("labelKey == null");
        }
    }

    public JsonAdapter<?> create(Type type, Set<? extends Annotation> set, Moshi moshi) {
        if (Types.getRawType(type) != this.baseType || !set.isEmpty()) {
            return null;
        }
        ArrayList arrayList = new ArrayList(this.subtypes.size());
        int size = this.subtypes.size();
        for (int i3 = 0; i3 < size; i3++) {
            arrayList.add(moshi.adapter(this.subtypes.get(i3)));
        }
        return new PolymorphicJsonAdapter(this.labelKey, this.labels, this.subtypes, arrayList, this.fallbackJsonAdapter).nullSafe();
    }

    public PolymorphicJsonAdapterFactory<T> withDefaultValue(@Nullable T t2) {
        return withFallbackJsonAdapter(buildFallbackJsonAdapter(t2));
    }

    public PolymorphicJsonAdapterFactory<T> withFallbackJsonAdapter(@Nullable JsonAdapter<Object> jsonAdapter) {
        return new PolymorphicJsonAdapterFactory(this.baseType, this.labelKey, this.labels, this.subtypes, jsonAdapter);
    }

    public PolymorphicJsonAdapterFactory<T> withSubtype(Class<? extends T> cls, String str) {
        if (cls == null) {
            throw new NullPointerException("subtype == null");
        } else if (str == null) {
            throw new NullPointerException("label == null");
        } else if (!this.labels.contains(str)) {
            ArrayList arrayList = new ArrayList(this.labels);
            arrayList.add(str);
            ArrayList arrayList2 = new ArrayList(this.subtypes);
            arrayList2.add(cls);
            return new PolymorphicJsonAdapterFactory(this.baseType, this.labelKey, arrayList, arrayList2, this.fallbackJsonAdapter);
        } else {
            throw new IllegalArgumentException("Labels must be unique.");
        }
    }
}
