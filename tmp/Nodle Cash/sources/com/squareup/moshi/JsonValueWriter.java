package com.squareup.moshi;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.annotation.Nullable;
import okio.Buffer;
import okio.BufferedSink;
import okio.ForwardingSink;
import okio.Okio;
import okio.Sink;

final class JsonValueWriter extends JsonWriter {
    @Nullable
    private String deferredName;
    Object[] stack = new Object[32];

    public JsonValueWriter() {
        pushScope(6);
    }

    /* access modifiers changed from: private */
    public JsonValueWriter add(@Nullable Object obj) {
        String str;
        Object put;
        int peekScope = peekScope();
        int i3 = this.stackSize;
        if (i3 == 1) {
            if (peekScope == 6) {
                this.scopes[i3 - 1] = 7;
                this.stack[i3 - 1] = obj;
            } else {
                throw new IllegalStateException("JSON must have only one top-level value.");
            }
        } else if (peekScope != 3 || (str = this.deferredName) == null) {
            if (peekScope == 1) {
                ((List) this.stack[i3 - 1]).add(obj);
            } else if (peekScope == 9) {
                throw new IllegalStateException("Sink from valueSink() was not closed");
            } else {
                throw new IllegalStateException("Nesting problem.");
            }
        } else if ((obj != null || this.serializeNulls) && (put = ((Map) this.stack[i3 - 1]).put(str, obj)) != null) {
            throw new IllegalArgumentException("Map key '" + this.deferredName + "' has multiple values at path " + getPath() + ": " + put + " and " + obj);
        } else {
            this.deferredName = null;
        }
        return this;
    }

    public JsonWriter beginArray() throws IOException {
        if (!this.promoteValueToName) {
            int i3 = this.stackSize;
            int i4 = this.flattenStackSize;
            if (i3 == i4 && this.scopes[i3 - 1] == 1) {
                this.flattenStackSize = ~i4;
                return this;
            }
            checkStack();
            ArrayList arrayList = new ArrayList();
            add(arrayList);
            Object[] objArr = this.stack;
            int i5 = this.stackSize;
            objArr[i5] = arrayList;
            this.pathIndices[i5] = 0;
            pushScope(1);
            return this;
        }
        throw new IllegalStateException("Array cannot be used as a map key in JSON at path " + getPath());
    }

    public JsonWriter beginObject() throws IOException {
        if (!this.promoteValueToName) {
            int i3 = this.stackSize;
            int i4 = this.flattenStackSize;
            if (i3 == i4 && this.scopes[i3 - 1] == 3) {
                this.flattenStackSize = ~i4;
                return this;
            }
            checkStack();
            LinkedHashTreeMap linkedHashTreeMap = new LinkedHashTreeMap();
            add(linkedHashTreeMap);
            this.stack[this.stackSize] = linkedHashTreeMap;
            pushScope(3);
            return this;
        }
        throw new IllegalStateException("Object cannot be used as a map key in JSON at path " + getPath());
    }

    public void close() throws IOException {
        int i3 = this.stackSize;
        if (i3 > 1 || (i3 == 1 && this.scopes[i3 - 1] != 7)) {
            throw new IOException("Incomplete document");
        }
        this.stackSize = 0;
    }

    public JsonWriter endArray() throws IOException {
        if (peekScope() == 1) {
            int i3 = this.stackSize;
            int i4 = this.flattenStackSize;
            if (i3 == (~i4)) {
                this.flattenStackSize = ~i4;
                return this;
            }
            int i5 = i3 - 1;
            this.stackSize = i5;
            this.stack[i5] = null;
            int[] iArr = this.pathIndices;
            int i6 = i3 - 2;
            iArr[i6] = iArr[i6] + 1;
            return this;
        }
        throw new IllegalStateException("Nesting problem.");
    }

    public JsonWriter endObject() throws IOException {
        if (peekScope() != 3) {
            throw new IllegalStateException("Nesting problem.");
        } else if (this.deferredName == null) {
            int i3 = this.stackSize;
            int i4 = this.flattenStackSize;
            if (i3 == (~i4)) {
                this.flattenStackSize = ~i4;
                return this;
            }
            this.promoteValueToName = false;
            int i5 = i3 - 1;
            this.stackSize = i5;
            this.stack[i5] = null;
            this.pathNames[i5] = null;
            int[] iArr = this.pathIndices;
            int i6 = i3 - 2;
            iArr[i6] = iArr[i6] + 1;
            return this;
        } else {
            throw new IllegalStateException("Dangling name: " + this.deferredName);
        }
    }

    public void flush() throws IOException {
        if (this.stackSize == 0) {
            throw new IllegalStateException("JsonWriter is closed.");
        }
    }

    public JsonWriter name(String str) throws IOException {
        if (str == null) {
            throw new NullPointerException("name == null");
        } else if (this.stackSize == 0) {
            throw new IllegalStateException("JsonWriter is closed.");
        } else if (peekScope() == 3 && this.deferredName == null && !this.promoteValueToName) {
            this.deferredName = str;
            this.pathNames[this.stackSize - 1] = str;
            return this;
        } else {
            throw new IllegalStateException("Nesting problem.");
        }
    }

    public JsonWriter nullValue() throws IOException {
        if (!this.promoteValueToName) {
            add((Object) null);
            int[] iArr = this.pathIndices;
            int i3 = this.stackSize - 1;
            iArr[i3] = iArr[i3] + 1;
            return this;
        }
        throw new IllegalStateException("null cannot be used as a map key in JSON at path " + getPath());
    }

    public Object root() {
        int i3 = this.stackSize;
        if (i3 <= 1 && (i3 != 1 || this.scopes[i3 - 1] == 7)) {
            return this.stack[0];
        }
        throw new IllegalStateException("Incomplete document");
    }

    public JsonWriter value(@Nullable String str) throws IOException {
        if (this.promoteValueToName) {
            this.promoteValueToName = false;
            return name(str);
        }
        add(str);
        int[] iArr = this.pathIndices;
        int i3 = this.stackSize - 1;
        iArr[i3] = iArr[i3] + 1;
        return this;
    }

    public BufferedSink valueSink() {
        if (this.promoteValueToName) {
            throw new IllegalStateException("BufferedSink cannot be used as a map key in JSON at path " + getPath());
        } else if (peekScope() != 9) {
            pushScope(9);
            final Buffer buffer = new Buffer();
            return Okio.buffer((Sink) new ForwardingSink(buffer) {
                /* JADX INFO: finally extract failed */
                public void close() throws IOException {
                    if (JsonValueWriter.this.peekScope() == 9) {
                        JsonValueWriter jsonValueWriter = JsonValueWriter.this;
                        Object[] objArr = jsonValueWriter.stack;
                        int i3 = jsonValueWriter.stackSize;
                        if (objArr[i3] == null) {
                            jsonValueWriter.stackSize = i3 - 1;
                            Object readJsonValue = JsonReader.of(buffer).readJsonValue();
                            JsonValueWriter jsonValueWriter2 = JsonValueWriter.this;
                            boolean z2 = jsonValueWriter2.serializeNulls;
                            jsonValueWriter2.serializeNulls = true;
                            try {
                                JsonValueWriter unused = jsonValueWriter2.add(readJsonValue);
                                JsonValueWriter jsonValueWriter3 = JsonValueWriter.this;
                                jsonValueWriter3.serializeNulls = z2;
                                int[] iArr = jsonValueWriter3.pathIndices;
                                int i4 = jsonValueWriter3.stackSize - 1;
                                iArr[i4] = iArr[i4] + 1;
                                return;
                            } catch (Throwable th) {
                                JsonValueWriter.this.serializeNulls = z2;
                                throw th;
                            }
                        }
                    }
                    throw new AssertionError();
                }
            });
        } else {
            throw new IllegalStateException("Sink from valueSink() was not closed");
        }
    }

    public JsonWriter value(boolean z2) throws IOException {
        if (!this.promoteValueToName) {
            add(Boolean.valueOf(z2));
            int[] iArr = this.pathIndices;
            int i3 = this.stackSize - 1;
            iArr[i3] = iArr[i3] + 1;
            return this;
        }
        throw new IllegalStateException("Boolean cannot be used as a map key in JSON at path " + getPath());
    }

    public JsonWriter value(@Nullable Boolean bool) throws IOException {
        if (!this.promoteValueToName) {
            add(bool);
            int[] iArr = this.pathIndices;
            int i3 = this.stackSize - 1;
            iArr[i3] = iArr[i3] + 1;
            return this;
        }
        throw new IllegalStateException("Boolean cannot be used as a map key in JSON at path " + getPath());
    }

    public JsonWriter value(double d2) throws IOException {
        if (!this.lenient && (Double.isNaN(d2) || d2 == Double.NEGATIVE_INFINITY || d2 == Double.POSITIVE_INFINITY)) {
            throw new IllegalArgumentException("Numeric values must be finite, but was " + d2);
        } else if (this.promoteValueToName) {
            this.promoteValueToName = false;
            return name(Double.toString(d2));
        } else {
            add(Double.valueOf(d2));
            int[] iArr = this.pathIndices;
            int i3 = this.stackSize - 1;
            iArr[i3] = iArr[i3] + 1;
            return this;
        }
    }

    public JsonWriter value(long j2) throws IOException {
        if (this.promoteValueToName) {
            this.promoteValueToName = false;
            return name(Long.toString(j2));
        }
        add(Long.valueOf(j2));
        int[] iArr = this.pathIndices;
        int i3 = this.stackSize - 1;
        iArr[i3] = iArr[i3] + 1;
        return this;
    }

    public JsonWriter value(@Nullable Number number) throws IOException {
        if ((number instanceof Byte) || (number instanceof Short) || (number instanceof Integer) || (number instanceof Long)) {
            return value(number.longValue());
        }
        if ((number instanceof Float) || (number instanceof Double)) {
            return value(number.doubleValue());
        }
        if (number == null) {
            return nullValue();
        }
        BigDecimal bigDecimal = number instanceof BigDecimal ? (BigDecimal) number : new BigDecimal(number.toString());
        if (this.promoteValueToName) {
            this.promoteValueToName = false;
            return name(bigDecimal.toString());
        }
        add(bigDecimal);
        int[] iArr = this.pathIndices;
        int i3 = this.stackSize - 1;
        iArr[i3] = iArr[i3] + 1;
        return this;
    }
}
