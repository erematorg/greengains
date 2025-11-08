package com.squareup.moshi;

import A.a;
import com.squareup.moshi.JsonReader;
import com.squareup.moshi.internal.NonNullJsonAdapter;
import com.squareup.moshi.internal.NullSafeJsonAdapter;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.Set;
import javax.annotation.CheckReturnValue;
import javax.annotation.Nullable;
import okio.Buffer;
import okio.BufferedSink;
import okio.BufferedSource;

public abstract class JsonAdapter<T> {

    public interface Factory {
        @CheckReturnValue
        @Nullable
        JsonAdapter<?> create(Type type, Set<? extends Annotation> set, Moshi moshi);
    }

    @CheckReturnValue
    public final JsonAdapter<T> failOnUnknown() {
        return new JsonAdapter<T>() {
            @Nullable
            public T fromJson(JsonReader jsonReader) throws IOException {
                boolean failOnUnknown = jsonReader.failOnUnknown();
                jsonReader.setFailOnUnknown(true);
                try {
                    return this.fromJson(jsonReader);
                } finally {
                    jsonReader.setFailOnUnknown(failOnUnknown);
                }
            }

            public boolean isLenient() {
                return this.isLenient();
            }

            public void toJson(JsonWriter jsonWriter, @Nullable T t2) throws IOException {
                this.toJson(jsonWriter, t2);
            }

            public String toString() {
                return this + ".failOnUnknown()";
            }
        };
    }

    @CheckReturnValue
    @Nullable
    public abstract T fromJson(JsonReader jsonReader) throws IOException;

    @CheckReturnValue
    @Nullable
    public final T fromJson(BufferedSource bufferedSource) throws IOException {
        return fromJson(JsonReader.of(bufferedSource));
    }

    @CheckReturnValue
    @Nullable
    public final T fromJsonValue(@Nullable Object obj) {
        try {
            return fromJson((JsonReader) new JsonValueReader(obj));
        } catch (IOException e3) {
            throw new AssertionError(e3);
        }
    }

    @CheckReturnValue
    public JsonAdapter<T> indent(final String str) {
        if (str != null) {
            return new JsonAdapter<T>() {
                @Nullable
                public T fromJson(JsonReader jsonReader) throws IOException {
                    return this.fromJson(jsonReader);
                }

                public boolean isLenient() {
                    return this.isLenient();
                }

                public void toJson(JsonWriter jsonWriter, @Nullable T t2) throws IOException {
                    String indent = jsonWriter.getIndent();
                    jsonWriter.setIndent(str);
                    try {
                        this.toJson(jsonWriter, t2);
                    } finally {
                        jsonWriter.setIndent(indent);
                    }
                }

                public String toString() {
                    StringBuilder sb = new StringBuilder();
                    sb.append(this);
                    sb.append(".indent(\"");
                    return a.n(sb, str, "\")");
                }
            };
        }
        throw new NullPointerException("indent == null");
    }

    public boolean isLenient() {
        return false;
    }

    @CheckReturnValue
    public final JsonAdapter<T> lenient() {
        return new JsonAdapter<T>() {
            @Nullable
            public T fromJson(JsonReader jsonReader) throws IOException {
                boolean isLenient = jsonReader.isLenient();
                jsonReader.setLenient(true);
                try {
                    return this.fromJson(jsonReader);
                } finally {
                    jsonReader.setLenient(isLenient);
                }
            }

            public boolean isLenient() {
                return true;
            }

            public void toJson(JsonWriter jsonWriter, @Nullable T t2) throws IOException {
                boolean isLenient = jsonWriter.isLenient();
                jsonWriter.setLenient(true);
                try {
                    this.toJson(jsonWriter, t2);
                } finally {
                    jsonWriter.setLenient(isLenient);
                }
            }

            public String toString() {
                return this + ".lenient()";
            }
        };
    }

    @CheckReturnValue
    public final JsonAdapter<T> nonNull() {
        return this instanceof NonNullJsonAdapter ? this : new NonNullJsonAdapter(this);
    }

    @CheckReturnValue
    public final JsonAdapter<T> nullSafe() {
        return this instanceof NullSafeJsonAdapter ? this : new NullSafeJsonAdapter(this);
    }

    @CheckReturnValue
    public final JsonAdapter<T> serializeNulls() {
        return new JsonAdapter<T>() {
            @Nullable
            public T fromJson(JsonReader jsonReader) throws IOException {
                return this.fromJson(jsonReader);
            }

            public boolean isLenient() {
                return this.isLenient();
            }

            public void toJson(JsonWriter jsonWriter, @Nullable T t2) throws IOException {
                boolean serializeNulls = jsonWriter.getSerializeNulls();
                jsonWriter.setSerializeNulls(true);
                try {
                    this.toJson(jsonWriter, t2);
                } finally {
                    jsonWriter.setSerializeNulls(serializeNulls);
                }
            }

            public String toString() {
                return this + ".serializeNulls()";
            }
        };
    }

    public abstract void toJson(JsonWriter jsonWriter, @Nullable T t2) throws IOException;

    public final void toJson(BufferedSink bufferedSink, @Nullable T t2) throws IOException {
        toJson(JsonWriter.of(bufferedSink), t2);
    }

    @CheckReturnValue
    @Nullable
    public final Object toJsonValue(@Nullable T t2) {
        JsonValueWriter jsonValueWriter = new JsonValueWriter();
        try {
            toJson((JsonWriter) jsonValueWriter, t2);
            return jsonValueWriter.root();
        } catch (IOException e3) {
            throw new AssertionError(e3);
        }
    }

    @CheckReturnValue
    @Nullable
    public final T fromJson(String str) throws IOException {
        JsonReader of = JsonReader.of(new Buffer().writeUtf8(str));
        T fromJson = fromJson(of);
        if (isLenient() || of.peek() == JsonReader.Token.END_DOCUMENT) {
            return fromJson;
        }
        throw new JsonDataException("JSON document was not fully consumed.");
    }

    @CheckReturnValue
    public final String toJson(@Nullable T t2) {
        Buffer buffer = new Buffer();
        try {
            toJson((BufferedSink) buffer, t2);
            return buffer.readUtf8();
        } catch (IOException e3) {
            throw new AssertionError(e3);
        }
    }
}
