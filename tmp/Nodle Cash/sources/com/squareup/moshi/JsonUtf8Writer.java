package com.squareup.moshi;

import java.io.IOException;
import javax.annotation.Nullable;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;
import okio.Buffer;
import okio.BufferedSink;
import okio.Okio;
import okio.Sink;
import okio.Timeout;

final class JsonUtf8Writer extends JsonWriter {
    private static final String[] REPLACEMENT_CHARS = new String[128];
    private String deferredName;
    private String separator = ":";
    /* access modifiers changed from: private */
    public final BufferedSink sink;

    static {
        for (int i3 = 0; i3 <= 31; i3++) {
            REPLACEMENT_CHARS[i3] = String.format("\\u%04x", new Object[]{Integer.valueOf(i3)});
        }
        String[] strArr = REPLACEMENT_CHARS;
        strArr[34] = "\\\"";
        strArr[92] = "\\\\";
        strArr[9] = "\\t";
        strArr[8] = "\\b";
        strArr[10] = "\\n";
        strArr[13] = "\\r";
        strArr[12] = "\\f";
    }

    public JsonUtf8Writer(BufferedSink bufferedSink) {
        if (bufferedSink != null) {
            this.sink = bufferedSink;
            pushScope(6);
            return;
        }
        throw new NullPointerException("sink == null");
    }

    private void beforeName() throws IOException {
        int peekScope = peekScope();
        if (peekScope == 5) {
            this.sink.writeByte(44);
        } else if (peekScope != 3) {
            throw new IllegalStateException("Nesting problem.");
        }
        newline();
        replaceTop(4);
    }

    private void beforeValue() throws IOException {
        int peekScope = peekScope();
        int i3 = 2;
        if (peekScope != 1) {
            if (peekScope != 2) {
                if (peekScope == 4) {
                    this.sink.writeUtf8(this.separator);
                    i3 = 5;
                } else if (peekScope != 9) {
                    i3 = 7;
                    if (peekScope != 6) {
                        if (peekScope != 7) {
                            throw new IllegalStateException("Nesting problem.");
                        } else if (!this.lenient) {
                            throw new IllegalStateException("JSON must have only one top-level value.");
                        }
                    }
                } else {
                    throw new IllegalStateException("Sink from valueSink() was not closed");
                }
                replaceTop(i3);
            }
            this.sink.writeByte(44);
        }
        newline();
        replaceTop(i3);
    }

    private JsonWriter close(int i3, int i4, char c3) throws IOException {
        int peekScope = peekScope();
        if (peekScope != i4 && peekScope != i3) {
            throw new IllegalStateException("Nesting problem.");
        } else if (this.deferredName == null) {
            int i5 = this.stackSize;
            int i6 = this.flattenStackSize;
            if (i5 == (~i6)) {
                this.flattenStackSize = ~i6;
                return this;
            }
            int i7 = i5 - 1;
            this.stackSize = i7;
            this.pathNames[i7] = null;
            int[] iArr = this.pathIndices;
            int i8 = i5 - 2;
            iArr[i8] = iArr[i8] + 1;
            if (peekScope == i4) {
                newline();
            }
            this.sink.writeByte(c3);
            return this;
        } else {
            throw new IllegalStateException("Dangling name: " + this.deferredName);
        }
    }

    private void newline() throws IOException {
        if (this.indent != null) {
            this.sink.writeByte(10);
            int i3 = this.stackSize;
            for (int i4 = 1; i4 < i3; i4++) {
                this.sink.writeUtf8(this.indent);
            }
        }
    }

    private JsonWriter open(int i3, int i4, char c3) throws IOException {
        int i5 = this.stackSize;
        int i6 = this.flattenStackSize;
        if (i5 == i6) {
            int[] iArr = this.scopes;
            if (iArr[i5 - 1] == i3 || iArr[i5 - 1] == i4) {
                this.flattenStackSize = ~i6;
                return this;
            }
        }
        beforeValue();
        checkStack();
        pushScope(i3);
        this.pathIndices[this.stackSize - 1] = 0;
        this.sink.writeByte(c3);
        return this;
    }

    public static void string(BufferedSink bufferedSink, String str) throws IOException {
        String str2;
        String[] strArr = REPLACEMENT_CHARS;
        bufferedSink.writeByte(34);
        int length = str.length();
        int i3 = 0;
        for (int i4 = 0; i4 < length; i4++) {
            char charAt = str.charAt(i4);
            if (charAt < 128) {
                str2 = strArr[charAt];
                if (str2 == null) {
                }
            } else if (charAt == 8232) {
                str2 = "\\u2028";
            } else if (charAt == 8233) {
                str2 = "\\u2029";
            }
            if (i3 < i4) {
                bufferedSink.writeUtf8(str, i3, i4);
            }
            bufferedSink.writeUtf8(str2);
            i3 = i4 + 1;
        }
        if (i3 < length) {
            bufferedSink.writeUtf8(str, i3, length);
        }
        bufferedSink.writeByte(34);
    }

    private void writeDeferredName() throws IOException {
        if (this.deferredName != null) {
            beforeName();
            string(this.sink, this.deferredName);
            this.deferredName = null;
        }
    }

    public JsonWriter beginArray() throws IOException {
        if (!this.promoteValueToName) {
            writeDeferredName();
            return open(1, 2, AbstractJsonLexerKt.BEGIN_LIST);
        }
        throw new IllegalStateException("Array cannot be used as a map key in JSON at path " + getPath());
    }

    public JsonWriter beginObject() throws IOException {
        if (!this.promoteValueToName) {
            writeDeferredName();
            return open(3, 5, AbstractJsonLexerKt.BEGIN_OBJ);
        }
        throw new IllegalStateException("Object cannot be used as a map key in JSON at path " + getPath());
    }

    public JsonWriter endArray() throws IOException {
        return close(1, 2, AbstractJsonLexerKt.END_LIST);
    }

    public JsonWriter endObject() throws IOException {
        this.promoteValueToName = false;
        return close(3, 5, AbstractJsonLexerKt.END_OBJ);
    }

    public void flush() throws IOException {
        if (this.stackSize != 0) {
            this.sink.flush();
            return;
        }
        throw new IllegalStateException("JsonWriter is closed.");
    }

    public JsonWriter name(String str) throws IOException {
        if (str == null) {
            throw new NullPointerException("name == null");
        } else if (this.stackSize != 0) {
            int peekScope = peekScope();
            if ((peekScope == 3 || peekScope == 5) && this.deferredName == null && !this.promoteValueToName) {
                this.deferredName = str;
                this.pathNames[this.stackSize - 1] = str;
                return this;
            }
            throw new IllegalStateException("Nesting problem.");
        } else {
            throw new IllegalStateException("JsonWriter is closed.");
        }
    }

    public JsonWriter nullValue() throws IOException {
        if (!this.promoteValueToName) {
            if (this.deferredName != null) {
                if (this.serializeNulls) {
                    writeDeferredName();
                } else {
                    this.deferredName = null;
                    return this;
                }
            }
            beforeValue();
            this.sink.writeUtf8(AbstractJsonLexerKt.NULL);
            int[] iArr = this.pathIndices;
            int i3 = this.stackSize - 1;
            iArr[i3] = iArr[i3] + 1;
            return this;
        }
        throw new IllegalStateException("null cannot be used as a map key in JSON at path " + getPath());
    }

    public void setIndent(String str) {
        super.setIndent(str);
        this.separator = !str.isEmpty() ? ": " : ":";
    }

    public JsonWriter value(String str) throws IOException {
        if (str == null) {
            return nullValue();
        }
        if (this.promoteValueToName) {
            this.promoteValueToName = false;
            return name(str);
        }
        writeDeferredName();
        beforeValue();
        string(this.sink, str);
        int[] iArr = this.pathIndices;
        int i3 = this.stackSize - 1;
        iArr[i3] = iArr[i3] + 1;
        return this;
    }

    public BufferedSink valueSink() throws IOException {
        if (!this.promoteValueToName) {
            writeDeferredName();
            beforeValue();
            pushScope(9);
            return Okio.buffer((Sink) new Sink() {
                public void close() {
                    if (JsonUtf8Writer.this.peekScope() == 9) {
                        JsonUtf8Writer jsonUtf8Writer = JsonUtf8Writer.this;
                        int i3 = jsonUtf8Writer.stackSize;
                        jsonUtf8Writer.stackSize = i3 - 1;
                        int[] iArr = jsonUtf8Writer.pathIndices;
                        int i4 = i3 - 2;
                        iArr[i4] = iArr[i4] + 1;
                        return;
                    }
                    throw new AssertionError();
                }

                public void flush() throws IOException {
                    JsonUtf8Writer.this.sink.flush();
                }

                public Timeout timeout() {
                    return Timeout.NONE;
                }

                public void write(Buffer buffer, long j2) throws IOException {
                    JsonUtf8Writer.this.sink.write(buffer, j2);
                }
            });
        }
        throw new IllegalStateException("BufferedSink cannot be used as a map key in JSON at path " + getPath());
    }

    public JsonWriter value(boolean z2) throws IOException {
        if (!this.promoteValueToName) {
            writeDeferredName();
            beforeValue();
            this.sink.writeUtf8(z2 ? "true" : "false");
            int[] iArr = this.pathIndices;
            int i3 = this.stackSize - 1;
            iArr[i3] = iArr[i3] + 1;
            return this;
        }
        throw new IllegalStateException("Boolean cannot be used as a map key in JSON at path " + getPath());
    }

    public void close() throws IOException {
        this.sink.close();
        int i3 = this.stackSize;
        if (i3 > 1 || (i3 == 1 && this.scopes[i3 - 1] != 7)) {
            throw new IOException("Incomplete document");
        }
        this.stackSize = 0;
    }

    public JsonWriter value(Boolean bool) throws IOException {
        if (bool == null) {
            return nullValue();
        }
        return value(bool.booleanValue());
    }

    public JsonWriter value(double d2) throws IOException {
        if (!this.lenient && (Double.isNaN(d2) || Double.isInfinite(d2))) {
            throw new IllegalArgumentException("Numeric values must be finite, but was " + d2);
        } else if (this.promoteValueToName) {
            this.promoteValueToName = false;
            return name(Double.toString(d2));
        } else {
            writeDeferredName();
            beforeValue();
            this.sink.writeUtf8(Double.toString(d2));
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
        writeDeferredName();
        beforeValue();
        this.sink.writeUtf8(Long.toString(j2));
        int[] iArr = this.pathIndices;
        int i3 = this.stackSize - 1;
        iArr[i3] = iArr[i3] + 1;
        return this;
    }

    public JsonWriter value(@Nullable Number number) throws IOException {
        if (number == null) {
            return nullValue();
        }
        String obj = number.toString();
        if (!this.lenient && (obj.equals("-Infinity") || obj.equals("Infinity") || obj.equals("NaN"))) {
            throw new IllegalArgumentException("Numeric values must be finite, but was " + number);
        } else if (this.promoteValueToName) {
            this.promoteValueToName = false;
            return name(obj);
        } else {
            writeDeferredName();
            beforeValue();
            this.sink.writeUtf8(obj);
            int[] iArr = this.pathIndices;
            int i3 = this.stackSize - 1;
            iArr[i3] = iArr[i3] + 1;
            return this;
        }
    }
}
