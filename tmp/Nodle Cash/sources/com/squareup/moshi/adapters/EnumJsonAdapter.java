package com.squareup.moshi.adapters;

import androidx.constraintlayout.core.state.b;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.JsonDataException;
import com.squareup.moshi.JsonReader;
import com.squareup.moshi.JsonWriter;
import com.squareup.moshi.internal.Util;
import java.io.IOException;
import java.lang.Enum;
import java.lang.reflect.AnnotatedElement;
import java.util.Arrays;
import javax.annotation.Nullable;

public final class EnumJsonAdapter<T extends Enum<T>> extends JsonAdapter<T> {
    final T[] constants;
    final Class<T> enumType;
    @Nullable
    final T fallbackValue;
    final String[] nameStrings;
    final JsonReader.Options options;
    final boolean useFallbackValue;

    public EnumJsonAdapter(Class<T> cls, @Nullable T t2, boolean z2) {
        this.enumType = cls;
        this.fallbackValue = t2;
        this.useFallbackValue = z2;
        try {
            T[] tArr = (Enum[]) cls.getEnumConstants();
            this.constants = tArr;
            this.nameStrings = new String[tArr.length];
            int i3 = 0;
            while (true) {
                T[] tArr2 = this.constants;
                if (i3 < tArr2.length) {
                    String name = tArr2[i3].name();
                    this.nameStrings[i3] = Util.jsonName(name, (AnnotatedElement) cls.getField(name));
                    i3++;
                } else {
                    this.options = JsonReader.Options.of(this.nameStrings);
                    return;
                }
            }
        } catch (NoSuchFieldException e3) {
            throw new AssertionError("Missing field in ".concat(cls.getName()), e3);
        }
    }

    public static <T extends Enum<T>> EnumJsonAdapter<T> create(Class<T> cls) {
        return new EnumJsonAdapter<>(cls, (Enum) null, false);
    }

    public String toString() {
        return b.g(this.enumType, new StringBuilder("EnumJsonAdapter("), ")");
    }

    public EnumJsonAdapter<T> withUnknownFallback(@Nullable T t2) {
        return new EnumJsonAdapter<>(this.enumType, t2, true);
    }

    @Nullable
    public T fromJson(JsonReader jsonReader) throws IOException {
        int selectString = jsonReader.selectString(this.options);
        if (selectString != -1) {
            return this.constants[selectString];
        }
        String path = jsonReader.getPath();
        if (!this.useFallbackValue) {
            String nextString = jsonReader.nextString();
            throw new JsonDataException("Expected one of " + Arrays.asList(this.nameStrings) + " but was " + nextString + " at path " + path);
        } else if (jsonReader.peek() == JsonReader.Token.STRING) {
            jsonReader.skipValue();
            return this.fallbackValue;
        } else {
            throw new JsonDataException("Expected a string but was " + jsonReader.peek() + " at path " + path);
        }
    }

    public void toJson(JsonWriter jsonWriter, T t2) throws IOException {
        if (t2 != null) {
            jsonWriter.value(this.nameStrings[t2.ordinal()]);
            return;
        }
        throw new NullPointerException("value was null! Wrap in .nullSafe() to write nullable values.");
    }
}
