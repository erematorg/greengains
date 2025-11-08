package com.squareup.moshi;

import androidx.constraintlayout.core.state.b;
import java.io.Closeable;
import java.io.Flushable;
import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.CheckReturnValue;
import javax.annotation.Nullable;
import okio.BufferedSink;
import okio.BufferedSource;

public abstract class JsonWriter implements Closeable, Flushable {
    int flattenStackSize = -1;
    String indent;
    boolean lenient;
    int[] pathIndices = new int[32];
    String[] pathNames = new String[32];
    boolean promoteValueToName;
    int[] scopes = new int[32];
    boolean serializeNulls;
    int stackSize = 0;
    private Map<Class<?>, Object> tags;

    @CheckReturnValue
    public static JsonWriter of(BufferedSink bufferedSink) {
        return new JsonUtf8Writer(bufferedSink);
    }

    public abstract JsonWriter beginArray() throws IOException;

    @CheckReturnValue
    public final int beginFlatten() {
        int peekScope = peekScope();
        if (peekScope == 5 || peekScope == 3 || peekScope == 2 || peekScope == 1) {
            int i3 = this.flattenStackSize;
            this.flattenStackSize = this.stackSize;
            return i3;
        }
        throw new IllegalStateException("Nesting problem.");
    }

    public abstract JsonWriter beginObject() throws IOException;

    public final boolean checkStack() {
        int i3 = this.stackSize;
        int[] iArr = this.scopes;
        if (i3 != iArr.length) {
            return false;
        }
        if (i3 != 256) {
            this.scopes = Arrays.copyOf(iArr, iArr.length * 2);
            String[] strArr = this.pathNames;
            this.pathNames = (String[]) Arrays.copyOf(strArr, strArr.length * 2);
            int[] iArr2 = this.pathIndices;
            this.pathIndices = Arrays.copyOf(iArr2, iArr2.length * 2);
            if (!(this instanceof JsonValueWriter)) {
                return true;
            }
            JsonValueWriter jsonValueWriter = (JsonValueWriter) this;
            Object[] objArr = jsonValueWriter.stack;
            jsonValueWriter.stack = Arrays.copyOf(objArr, objArr.length * 2);
            return true;
        }
        throw new JsonDataException("Nesting too deep at " + getPath() + ": circular reference?");
    }

    public abstract JsonWriter endArray() throws IOException;

    public final void endFlatten(int i3) {
        this.flattenStackSize = i3;
    }

    public abstract JsonWriter endObject() throws IOException;

    @CheckReturnValue
    public final String getIndent() {
        String str = this.indent;
        return str != null ? str : "";
    }

    @CheckReturnValue
    public final String getPath() {
        return JsonScope.getPath(this.stackSize, this.scopes, this.pathNames, this.pathIndices);
    }

    @CheckReturnValue
    public final boolean getSerializeNulls() {
        return this.serializeNulls;
    }

    @CheckReturnValue
    public final boolean isLenient() {
        return this.lenient;
    }

    public final JsonWriter jsonValue(@Nullable Object obj) throws IOException {
        if (obj instanceof Map) {
            beginObject();
            for (Map.Entry entry : ((Map) obj).entrySet()) {
                Object key = entry.getKey();
                if (!(key instanceof String)) {
                    throw new IllegalArgumentException(key == null ? "Map keys must be non-null" : b.h(key, "Map keys must be of type String: "));
                }
                name((String) key);
                jsonValue(entry.getValue());
            }
            endObject();
        } else if (obj instanceof List) {
            beginArray();
            for (Object jsonValue : (List) obj) {
                jsonValue(jsonValue);
            }
            endArray();
        } else if (obj instanceof String) {
            value((String) obj);
        } else if (obj instanceof Boolean) {
            value(((Boolean) obj).booleanValue());
        } else if (obj instanceof Double) {
            value(((Double) obj).doubleValue());
        } else if (obj instanceof Long) {
            value(((Long) obj).longValue());
        } else if (obj instanceof Number) {
            value((Number) obj);
        } else if (obj == null) {
            nullValue();
        } else {
            throw new IllegalArgumentException(b.h(obj, "Unsupported type: "));
        }
        return this;
    }

    public abstract JsonWriter name(String str) throws IOException;

    public abstract JsonWriter nullValue() throws IOException;

    public final int peekScope() {
        int i3 = this.stackSize;
        if (i3 != 0) {
            return this.scopes[i3 - 1];
        }
        throw new IllegalStateException("JsonWriter is closed.");
    }

    public final void promoteValueToName() throws IOException {
        int peekScope = peekScope();
        if (peekScope == 5 || peekScope == 3) {
            this.promoteValueToName = true;
            return;
        }
        throw new IllegalStateException("Nesting problem.");
    }

    public final void pushScope(int i3) {
        int[] iArr = this.scopes;
        int i4 = this.stackSize;
        this.stackSize = i4 + 1;
        iArr[i4] = i3;
    }

    public final void replaceTop(int i3) {
        this.scopes[this.stackSize - 1] = i3;
    }

    public void setIndent(String str) {
        if (str.isEmpty()) {
            str = null;
        }
        this.indent = str;
    }

    public final void setLenient(boolean z2) {
        this.lenient = z2;
    }

    public final void setSerializeNulls(boolean z2) {
        this.serializeNulls = z2;
    }

    public final <T> void setTag(Class<T> cls, T t2) {
        if (cls.isAssignableFrom(t2.getClass())) {
            if (this.tags == null) {
                this.tags = new LinkedHashMap();
            }
            this.tags.put(cls, t2);
            return;
        }
        throw new IllegalArgumentException("Tag value must be of type ".concat(cls.getName()));
    }

    @CheckReturnValue
    @Nullable
    public final <T> T tag(Class<T> cls) {
        Map<Class<?>, Object> map = this.tags;
        if (map == null) {
            return null;
        }
        return map.get(cls);
    }

    public abstract JsonWriter value(double d2) throws IOException;

    public abstract JsonWriter value(long j2) throws IOException;

    public abstract JsonWriter value(@Nullable Boolean bool) throws IOException;

    public abstract JsonWriter value(@Nullable Number number) throws IOException;

    public abstract JsonWriter value(@Nullable String str) throws IOException;

    public final JsonWriter value(BufferedSource bufferedSource) throws IOException {
        if (!this.promoteValueToName) {
            BufferedSink valueSink = valueSink();
            try {
                bufferedSource.readAll(valueSink);
                if (valueSink != null) {
                    valueSink.close();
                }
                return this;
            } catch (Throwable th) {
                th.addSuppressed(th);
            }
        } else {
            throw new IllegalStateException("BufferedSource cannot be used as a map key in JSON at path " + getPath());
        }
        throw th;
    }

    public abstract JsonWriter value(boolean z2) throws IOException;

    @CheckReturnValue
    public abstract BufferedSink valueSink() throws IOException;
}
