package com.google.gson.stream;

import com.google.errorprone.annotations.CanIgnoreReturnValue;
import com.google.gson.FormattingStyle;
import com.google.gson.Strictness;
import java.io.Closeable;
import java.io.Flushable;
import java.io.IOException;
import java.io.Writer;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.regex.Pattern;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;

public class JsonWriter implements Closeable, Flushable {
    private static final String[] HTML_SAFE_REPLACEMENT_CHARS;
    private static final String[] REPLACEMENT_CHARS = new String[128];
    private static final Pattern VALID_JSON_NUMBER_PATTERN = Pattern.compile("-?(?:0|[1-9][0-9]*)(?:\\.[0-9]+)?(?:[eE][-+]?[0-9]+)?");
    private String deferredName;
    private String formattedColon;
    private String formattedComma;
    private FormattingStyle formattingStyle;
    private boolean htmlSafe;
    private final Writer out;
    private boolean serializeNulls;
    private int[] stack = new int[32];
    private int stackSize = 0;
    private Strictness strictness;
    private boolean usesEmptyNewlineAndIndent;

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
        String[] strArr2 = (String[]) strArr.clone();
        HTML_SAFE_REPLACEMENT_CHARS = strArr2;
        strArr2[60] = "\\u003c";
        strArr2[62] = "\\u003e";
        strArr2[38] = "\\u0026";
        strArr2[61] = "\\u003d";
        strArr2[39] = "\\u0027";
    }

    public JsonWriter(Writer writer) {
        push(6);
        this.strictness = Strictness.LEGACY_STRICT;
        this.serializeNulls = true;
        Objects.requireNonNull(writer, "out == null");
        this.out = writer;
        setFormattingStyle(FormattingStyle.COMPACT);
    }

    private static boolean alwaysCreatesValidJsonNumber(Class<? extends Number> cls) {
        return cls == Integer.class || cls == Long.class || cls == Byte.class || cls == Short.class || cls == BigDecimal.class || cls == BigInteger.class || cls == AtomicInteger.class || cls == AtomicLong.class;
    }

    private void beforeName() throws IOException {
        int peek = peek();
        if (peek == 5) {
            this.out.write(this.formattedComma);
        } else if (peek != 3) {
            throw new IllegalStateException("Nesting problem.");
        }
        newline();
        replaceTop(4);
    }

    private void beforeValue() throws IOException {
        int peek = peek();
        if (peek == 1) {
            replaceTop(2);
            newline();
        } else if (peek == 2) {
            this.out.append(this.formattedComma);
            newline();
        } else if (peek != 4) {
            if (peek != 6) {
                if (peek != 7) {
                    throw new IllegalStateException("Nesting problem.");
                } else if (this.strictness != Strictness.LENIENT) {
                    throw new IllegalStateException("JSON must have only one top-level value.");
                }
            }
            replaceTop(7);
        } else {
            this.out.append(this.formattedColon);
            replaceTop(5);
        }
    }

    @CanIgnoreReturnValue
    private JsonWriter closeScope(int i3, int i4, char c3) throws IOException {
        int peek = peek();
        if (peek != i4 && peek != i3) {
            throw new IllegalStateException("Nesting problem.");
        } else if (this.deferredName == null) {
            this.stackSize--;
            if (peek == i4) {
                newline();
            }
            this.out.write(c3);
            return this;
        } else {
            throw new IllegalStateException("Dangling name: " + this.deferredName);
        }
    }

    private void newline() throws IOException {
        if (!this.usesEmptyNewlineAndIndent) {
            this.out.write(this.formattingStyle.getNewline());
            int i3 = this.stackSize;
            for (int i4 = 1; i4 < i3; i4++) {
                this.out.write(this.formattingStyle.getIndent());
            }
        }
    }

    @CanIgnoreReturnValue
    private JsonWriter openScope(int i3, char c3) throws IOException {
        beforeValue();
        push(i3);
        this.out.write(c3);
        return this;
    }

    private int peek() {
        int i3 = this.stackSize;
        if (i3 != 0) {
            return this.stack[i3 - 1];
        }
        throw new IllegalStateException("JsonWriter is closed.");
    }

    private void push(int i3) {
        int i4 = this.stackSize;
        int[] iArr = this.stack;
        if (i4 == iArr.length) {
            this.stack = Arrays.copyOf(iArr, i4 * 2);
        }
        int[] iArr2 = this.stack;
        int i5 = this.stackSize;
        this.stackSize = i5 + 1;
        iArr2[i5] = i3;
    }

    private void replaceTop(int i3) {
        this.stack[this.stackSize - 1] = i3;
    }

    private void string(String str) throws IOException {
        String str2;
        String[] strArr = this.htmlSafe ? HTML_SAFE_REPLACEMENT_CHARS : REPLACEMENT_CHARS;
        this.out.write(34);
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
                this.out.write(str, i3, i4 - i3);
            }
            this.out.write(str2);
            i3 = i4 + 1;
        }
        if (i3 < length) {
            this.out.write(str, i3, length - i3);
        }
        this.out.write(34);
    }

    private void writeDeferredName() throws IOException {
        if (this.deferredName != null) {
            beforeName();
            string(this.deferredName);
            this.deferredName = null;
        }
    }

    @CanIgnoreReturnValue
    public JsonWriter beginArray() throws IOException {
        writeDeferredName();
        return openScope(1, AbstractJsonLexerKt.BEGIN_LIST);
    }

    @CanIgnoreReturnValue
    public JsonWriter beginObject() throws IOException {
        writeDeferredName();
        return openScope(3, AbstractJsonLexerKt.BEGIN_OBJ);
    }

    public void close() throws IOException {
        this.out.close();
        int i3 = this.stackSize;
        if (i3 > 1 || (i3 == 1 && this.stack[i3 - 1] != 7)) {
            throw new IOException("Incomplete document");
        }
        this.stackSize = 0;
    }

    @CanIgnoreReturnValue
    public JsonWriter endArray() throws IOException {
        return closeScope(1, 2, AbstractJsonLexerKt.END_LIST);
    }

    @CanIgnoreReturnValue
    public JsonWriter endObject() throws IOException {
        return closeScope(3, 5, AbstractJsonLexerKt.END_OBJ);
    }

    public void flush() throws IOException {
        if (this.stackSize != 0) {
            this.out.flush();
            return;
        }
        throw new IllegalStateException("JsonWriter is closed.");
    }

    public final FormattingStyle getFormattingStyle() {
        return this.formattingStyle;
    }

    public final boolean getSerializeNulls() {
        return this.serializeNulls;
    }

    public final Strictness getStrictness() {
        return this.strictness;
    }

    public final boolean isHtmlSafe() {
        return this.htmlSafe;
    }

    public boolean isLenient() {
        return this.strictness == Strictness.LENIENT;
    }

    @CanIgnoreReturnValue
    public JsonWriter jsonValue(String str) throws IOException {
        if (str == null) {
            return nullValue();
        }
        writeDeferredName();
        beforeValue();
        this.out.append(str);
        return this;
    }

    @CanIgnoreReturnValue
    public JsonWriter name(String str) throws IOException {
        Objects.requireNonNull(str, "name == null");
        if (this.deferredName == null) {
            int peek = peek();
            if (peek == 3 || peek == 5) {
                this.deferredName = str;
                return this;
            }
            throw new IllegalStateException("Please begin an object before writing a name.");
        }
        throw new IllegalStateException("Already wrote a name, expecting a value.");
    }

    @CanIgnoreReturnValue
    public JsonWriter nullValue() throws IOException {
        if (this.deferredName != null) {
            if (this.serializeNulls) {
                writeDeferredName();
            } else {
                this.deferredName = null;
                return this;
            }
        }
        beforeValue();
        this.out.write(AbstractJsonLexerKt.NULL);
        return this;
    }

    public final void setFormattingStyle(FormattingStyle formattingStyle2) {
        Objects.requireNonNull(formattingStyle2);
        this.formattingStyle = formattingStyle2;
        this.formattedComma = ",";
        if (formattingStyle2.usesSpaceAfterSeparators()) {
            this.formattedColon = ": ";
            if (this.formattingStyle.getNewline().isEmpty()) {
                this.formattedComma = ", ";
            }
        } else {
            this.formattedColon = ":";
        }
        this.usesEmptyNewlineAndIndent = this.formattingStyle.getNewline().isEmpty() && this.formattingStyle.getIndent().isEmpty();
    }

    public final void setHtmlSafe(boolean z2) {
        this.htmlSafe = z2;
    }

    public final void setIndent(String str) {
        if (str.isEmpty()) {
            setFormattingStyle(FormattingStyle.COMPACT);
        } else {
            setFormattingStyle(FormattingStyle.PRETTY.withIndent(str));
        }
    }

    @Deprecated
    public final void setLenient(boolean z2) {
        setStrictness(z2 ? Strictness.LENIENT : Strictness.LEGACY_STRICT);
    }

    public final void setSerializeNulls(boolean z2) {
        this.serializeNulls = z2;
    }

    public final void setStrictness(Strictness strictness2) {
        Objects.requireNonNull(strictness2);
        this.strictness = strictness2;
    }

    @CanIgnoreReturnValue
    public JsonWriter value(String str) throws IOException {
        if (str == null) {
            return nullValue();
        }
        writeDeferredName();
        beforeValue();
        string(str);
        return this;
    }

    @CanIgnoreReturnValue
    public JsonWriter value(boolean z2) throws IOException {
        writeDeferredName();
        beforeValue();
        this.out.write(z2 ? "true" : "false");
        return this;
    }

    @CanIgnoreReturnValue
    public JsonWriter value(Boolean bool) throws IOException {
        if (bool == null) {
            return nullValue();
        }
        writeDeferredName();
        beforeValue();
        this.out.write(bool.booleanValue() ? "true" : "false");
        return this;
    }

    @CanIgnoreReturnValue
    public JsonWriter value(float f2) throws IOException {
        writeDeferredName();
        if (this.strictness == Strictness.LENIENT || (!Float.isNaN(f2) && !Float.isInfinite(f2))) {
            beforeValue();
            this.out.append(Float.toString(f2));
            return this;
        }
        throw new IllegalArgumentException("Numeric values must be finite, but was " + f2);
    }

    @CanIgnoreReturnValue
    public JsonWriter value(double d2) throws IOException {
        writeDeferredName();
        if (this.strictness == Strictness.LENIENT || (!Double.isNaN(d2) && !Double.isInfinite(d2))) {
            beforeValue();
            this.out.append(Double.toString(d2));
            return this;
        }
        throw new IllegalArgumentException("Numeric values must be finite, but was " + d2);
    }

    @CanIgnoreReturnValue
    public JsonWriter value(long j2) throws IOException {
        writeDeferredName();
        beforeValue();
        this.out.write(Long.toString(j2));
        return this;
    }

    @CanIgnoreReturnValue
    public JsonWriter value(Number number) throws IOException {
        if (number == null) {
            return nullValue();
        }
        writeDeferredName();
        String obj = number.toString();
        Class<?> cls = number.getClass();
        if (!alwaysCreatesValidJsonNumber(cls)) {
            if (obj.equals("-Infinity") || obj.equals("Infinity") || obj.equals("NaN")) {
                if (this.strictness != Strictness.LENIENT) {
                    throw new IllegalArgumentException("Numeric values must be finite, but was ".concat(obj));
                }
            } else if (!(cls == Float.class || cls == Double.class || VALID_JSON_NUMBER_PATTERN.matcher(obj).matches())) {
                throw new IllegalArgumentException("String created by " + cls + " is not a valid JSON number: " + obj);
            }
        }
        beforeValue();
        this.out.append(obj);
        return this;
    }
}
