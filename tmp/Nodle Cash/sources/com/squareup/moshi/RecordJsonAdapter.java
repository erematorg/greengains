package com.squareup.moshi;

import com.squareup.moshi.JsonAdapter;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.Set;
import javax.annotation.Nullable;

final class RecordJsonAdapter<T> extends JsonAdapter<T> {
    static final JsonAdapter.Factory FACTORY = new JsonAdapter.Factory() {
        @Nullable
        public JsonAdapter<?> create(Type type, Set<? extends Annotation> set, Moshi moshi) {
            return null;
        }
    };

    @Nullable
    public T fromJson(JsonReader jsonReader) throws IOException {
        throw new AssertionError();
    }

    public void toJson(JsonWriter jsonWriter, @Nullable T t2) throws IOException {
        throw new AssertionError();
    }
}
