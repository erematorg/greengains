package com.squareup.moshi;

import com.squareup.moshi.JsonAdapter;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import javax.annotation.Nullable;

abstract class CollectionJsonAdapter<C extends Collection<T>, T> extends JsonAdapter<C> {
    public static final JsonAdapter.Factory FACTORY = new JsonAdapter.Factory() {
        @Nullable
        public JsonAdapter<?> create(Type type, Set<? extends Annotation> set, Moshi moshi) {
            Class<?> rawType = Types.getRawType(type);
            if (!set.isEmpty()) {
                return null;
            }
            if (rawType == List.class || rawType == Collection.class) {
                return CollectionJsonAdapter.newArrayListAdapter(type, moshi).nullSafe();
            }
            if (rawType == Set.class) {
                return CollectionJsonAdapter.newLinkedHashSetAdapter(type, moshi).nullSafe();
            }
            return null;
        }
    };
    private final JsonAdapter<T> elementAdapter;

    public static <T> JsonAdapter<Collection<T>> newArrayListAdapter(Type type, Moshi moshi) {
        return new CollectionJsonAdapter<Collection<T>, T>(moshi.adapter(Types.collectionElementType(type, Collection.class))) {
            public /* bridge */ /* synthetic */ Object fromJson(JsonReader jsonReader) throws IOException {
                return CollectionJsonAdapter.super.fromJson(jsonReader);
            }

            public Collection<T> newCollection() {
                return new ArrayList();
            }

            public /* bridge */ /* synthetic */ void toJson(JsonWriter jsonWriter, Object obj) throws IOException {
                CollectionJsonAdapter.super.toJson(jsonWriter, (Collection) obj);
            }
        };
    }

    public static <T> JsonAdapter<Set<T>> newLinkedHashSetAdapter(Type type, Moshi moshi) {
        return new CollectionJsonAdapter<Set<T>, T>(moshi.adapter(Types.collectionElementType(type, Collection.class))) {
            public /* bridge */ /* synthetic */ Object fromJson(JsonReader jsonReader) throws IOException {
                return CollectionJsonAdapter.super.fromJson(jsonReader);
            }

            public /* bridge */ /* synthetic */ void toJson(JsonWriter jsonWriter, Object obj) throws IOException {
                CollectionJsonAdapter.super.toJson(jsonWriter, (Collection) obj);
            }

            public Set<T> newCollection() {
                return new LinkedHashSet();
            }
        };
    }

    public abstract C newCollection();

    public String toString() {
        return this.elementAdapter + ".collection()";
    }

    private CollectionJsonAdapter(JsonAdapter<T> jsonAdapter) {
        this.elementAdapter = jsonAdapter;
    }

    public C fromJson(JsonReader jsonReader) throws IOException {
        C newCollection = newCollection();
        jsonReader.beginArray();
        while (jsonReader.hasNext()) {
            newCollection.add(this.elementAdapter.fromJson(jsonReader));
        }
        jsonReader.endArray();
        return newCollection;
    }

    public void toJson(JsonWriter jsonWriter, C c3) throws IOException {
        jsonWriter.beginArray();
        for (Object json : c3) {
            this.elementAdapter.toJson(jsonWriter, json);
        }
        jsonWriter.endArray();
    }
}
