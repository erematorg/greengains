package com.squareup.moshi.internal;

import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.JsonReader;
import com.squareup.moshi.JsonWriter;
import java.io.IOException;
import javax.annotation.Nullable;

public final class NullSafeJsonAdapter<T> extends JsonAdapter<T> {
    private final JsonAdapter<T> delegate;

    public NullSafeJsonAdapter(JsonAdapter<T> jsonAdapter) {
        this.delegate = jsonAdapter;
    }

    public JsonAdapter<T> delegate() {
        return this.delegate;
    }

    @Nullable
    public T fromJson(JsonReader jsonReader) throws IOException {
        return jsonReader.peek() == JsonReader.Token.NULL ? jsonReader.nextNull() : this.delegate.fromJson(jsonReader);
    }

    public void toJson(JsonWriter jsonWriter, @Nullable T t2) throws IOException {
        if (t2 == null) {
            jsonWriter.nullValue();
        } else {
            this.delegate.toJson(jsonWriter, t2);
        }
    }

    public String toString() {
        return this.delegate + ".nullSafe()";
    }
}
