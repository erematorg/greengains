package com.apollographql.apollo3.api.json;

import com.apollographql.apollo3.api.Upload;
import com.apollographql.apollo3.api.json.internal.JsonScope;
import com.apollographql.apollo3.exception.JsonDataException;
import com.google.common.base.Ascii;
import com.reown.foundation.util.jwt.JwtUtilsKt;
import java.io.IOException;
import kotlin.Metadata;
import kotlin.jvm.JvmOverloads;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;
import okio.BufferedSink;
import org.apache.commons.text.StringSubstitutor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\u0015\n\u0000\n\u0002\u0010\u0011\n\u0002\b\u0005\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0016\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\u0010\u0006\n\u0002\u0010\t\n\u0002\b\u0003\u0018\u0000 32\u00020\u0001:\u00013B\u001b\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\u0002\u0010\u0006J\b\u0010\u0015\u001a\u00020\u0016H\u0002J\b\u0010\u0017\u001a\u00020\u0016H\u0002J\b\u0010\u0018\u001a\u00020\u0001H\u0016J\b\u0010\u0019\u001a\u00020\u0001H\u0016J\b\u0010\u001a\u001a\u00020\u0016H\u0016J \u0010\u001a\u001a\u00020\u00012\u0006\u0010\u001b\u001a\u00020\u00142\u0006\u0010\u001c\u001a\u00020\u00142\u0006\u0010\u001d\u001a\u00020\u0005H\u0002J\b\u0010\u001e\u001a\u00020\u0001H\u0016J\b\u0010\u001f\u001a\u00020\u0001H\u0016J\b\u0010 \u001a\u00020\u0016H\u0016J\u000e\u0010!\u001a\u00020\u00012\u0006\u0010\"\u001a\u00020\u0005J\u0010\u0010#\u001a\u00020\u00012\u0006\u0010#\u001a\u00020\u0005H\u0016J\b\u0010$\u001a\u00020\u0016H\u0002J\b\u0010%\u001a\u00020\u0001H\u0016J\u0018\u0010&\u001a\u00020\u00012\u0006\u0010\u001b\u001a\u00020\u00142\u0006\u0010'\u001a\u00020\u0005H\u0002J\b\u0010(\u001a\u00020\u0014H\u0002J\u0010\u0010)\u001a\u00020\u00162\u0006\u0010*\u001a\u00020\u0014H\u0002J\u0010\u0010+\u001a\u00020\u00162\u0006\u0010,\u001a\u00020\u0014H\u0002J\u0010\u0010\"\u001a\u00020\u00002\u0006\u0010\"\u001a\u00020-H\u0016J\u0010\u0010\"\u001a\u00020\u00012\u0006\u0010\"\u001a\u00020.H\u0016J\u0010\u0010\"\u001a\u00020\u00012\u0006\u0010\"\u001a\u00020/H\u0016J\u0010\u0010\"\u001a\u00020\u00012\u0006\u0010\"\u001a\u000200H\u0016J\u0010\u0010\"\u001a\u00020\u00012\u0006\u0010\"\u001a\u00020\u0014H\u0016J\u0010\u0010\"\u001a\u00020\u00012\u0006\u0010\"\u001a\u000201H\u0016J\u0010\u0010\"\u001a\u00020\u00012\u0006\u0010\"\u001a\u00020\u0005H\u0016J\b\u00102\u001a\u00020\u0016H\u0002R\u0010\u0010\u0007\u001a\u0004\u0018\u00010\u0005X\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\b\u001a\u00020\u00058VX\u0004¢\u0006\u0006\u001a\u0004\b\t\u0010\nR\u000e\u0010\u000b\u001a\u00020\fX\u0004¢\u0006\u0002\n\u0000R\u0018\u0010\r\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00050\u000eX\u0004¢\u0006\u0004\n\u0002\u0010\u000fR\u000e\u0010\u0010\u001a\u00020\fX\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0011\u001a\u00020\u00058BX\u0004¢\u0006\u0006\u001a\u0004\b\u0012\u0010\nR\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0014X\u000e¢\u0006\u0002\n\u0000¨\u00064"}, d2 = {"Lcom/apollographql/apollo3/api/json/BufferedSinkJsonWriter;", "Lcom/apollographql/apollo3/api/json/JsonWriter;", "sink", "Lokio/BufferedSink;", "indent", "", "(Lokio/BufferedSink;Ljava/lang/String;)V", "deferredName", "path", "getPath", "()Ljava/lang/String;", "pathIndices", "", "pathNames", "", "[Ljava/lang/String;", "scopes", "separator", "getSeparator", "stackSize", "", "beforeName", "", "beforeValue", "beginArray", "beginObject", "close", "empty", "nonempty", "closeBracket", "endArray", "endObject", "flush", "jsonValue", "value", "name", "newline", "nullValue", "open", "openBracket", "peekScope", "pushScope", "newTop", "replaceTop", "topOfStack", "Lcom/apollographql/apollo3/api/Upload;", "Lcom/apollographql/apollo3/api/json/JsonNumber;", "", "", "", "writeDeferredName", "Companion", "apollo-api"}, k = 1, mv = {1, 5, 1}, xi = 48)
@SourceDebugExtension({"SMAP\nBufferedSinkJsonWriter.kt\nKotlin\n*S Kotlin\n*F\n+ 1 BufferedSinkJsonWriter.kt\ncom/apollographql/apollo3/api/json/BufferedSinkJsonWriter\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,334:1\n1#2:335\n*E\n"})
public final class BufferedSinkJsonWriter implements JsonWriter {
    @NotNull
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    @NotNull
    private static final String HEX_ARRAY = "0123456789abcdef";
    /* access modifiers changed from: private */
    @NotNull
    public static final String[] REPLACEMENT_CHARS;
    @Nullable
    private String deferredName;
    @Nullable
    private final String indent;
    @NotNull
    private final int[] pathIndices;
    @NotNull
    private final String[] pathNames;
    @NotNull
    private final int[] scopes;
    @NotNull
    private final BufferedSink sink;
    private int stackSize;

    @Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0011\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0005\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0016\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u0004J\f\u0010\r\u001a\u00020\u0004*\u00020\u000eH\u0002R\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u0018\u0010\u0005\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00040\u0006X\u0004¢\u0006\u0004\n\u0002\u0010\u0007¨\u0006\u000f"}, d2 = {"Lcom/apollographql/apollo3/api/json/BufferedSinkJsonWriter$Companion;", "", "()V", "HEX_ARRAY", "", "REPLACEMENT_CHARS", "", "[Ljava/lang/String;", "string", "", "sink", "Lokio/BufferedSink;", "value", "hexString", "", "apollo-api"}, k = 1, mv = {1, 5, 1}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        /* access modifiers changed from: private */
        public final String hexString(byte b3) {
            StringBuilder sb = new StringBuilder();
            sb.append(BufferedSinkJsonWriter.HEX_ARRAY.charAt(b3 >>> 4));
            sb.append(BufferedSinkJsonWriter.HEX_ARRAY.charAt(b3 & Ascii.SI));
            return sb.toString();
        }

        public final void string(@NotNull BufferedSink bufferedSink, @NotNull String str) {
            String str2;
            Intrinsics.checkNotNullParameter(bufferedSink, "sink");
            Intrinsics.checkNotNullParameter(str, "value");
            String[] access$getREPLACEMENT_CHARS$cp = BufferedSinkJsonWriter.REPLACEMENT_CHARS;
            bufferedSink.writeByte(34);
            int length = str.length();
            int i3 = 0;
            for (int i4 = 0; i4 < length; i4++) {
                char charAt = str.charAt(i4);
                if (charAt < 128) {
                    str2 = access$getREPLACEMENT_CHARS$cp[charAt];
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

        private Companion() {
        }
    }

    static {
        String[] strArr = new String[128];
        for (int i3 = 0; i3 < 32; i3++) {
            strArr[i3] = "\\u00" + Companion.hexString((byte) i3);
        }
        strArr[34] = "\\\"";
        strArr[92] = "\\\\";
        strArr[9] = "\\t";
        strArr[8] = "\\b";
        strArr[10] = "\\n";
        strArr[13] = "\\r";
        REPLACEMENT_CHARS = strArr;
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    @JvmOverloads
    public BufferedSinkJsonWriter(@NotNull BufferedSink bufferedSink) {
        this(bufferedSink, (String) null, 2, (DefaultConstructorMarker) null);
        Intrinsics.checkNotNullParameter(bufferedSink, "sink");
    }

    private final void beforeName() {
        int peekScope = peekScope();
        if (peekScope == 5) {
            this.sink.writeByte(44);
        } else if (peekScope != 3) {
            throw new IllegalStateException("Nesting problem.");
        }
        newline();
        replaceTop(4);
    }

    private final void beforeValue() {
        int peekScope = peekScope();
        if (peekScope == 1) {
            replaceTop(2);
            newline();
        } else if (peekScope == 2) {
            this.sink.writeByte(44);
            newline();
        } else if (peekScope == 4) {
            this.sink.writeUtf8(getSeparator());
            replaceTop(5);
        } else if (peekScope == 6) {
            replaceTop(7);
        } else if (peekScope != 7) {
            throw new IllegalStateException("Nesting problem.");
        } else {
            throw new IllegalStateException("JSON must have only one top-level value.");
        }
    }

    private final JsonWriter close(int i3, int i4, String str) {
        int peekScope = peekScope();
        if (peekScope != i4 && peekScope != i3) {
            throw new IllegalStateException("Nesting problem.");
        } else if (this.deferredName == null) {
            int i5 = this.stackSize;
            int i6 = i5 - 1;
            this.stackSize = i6;
            this.pathNames[i6] = null;
            int[] iArr = this.pathIndices;
            int i7 = i5 - 2;
            iArr[i7] = iArr[i7] + 1;
            if (peekScope == i4) {
                newline();
            }
            this.sink.writeUtf8(str);
            return this;
        } else {
            throw new IllegalStateException(("Dangling name: " + this.deferredName).toString());
        }
    }

    private final String getSeparator() {
        String str = this.indent;
        return (str == null || str.length() == 0) ? ":" : ": ";
    }

    private final void newline() {
        if (this.indent != null) {
            this.sink.writeByte(10);
            int i3 = this.stackSize;
            for (int i4 = 1; i4 < i3; i4++) {
                this.sink.writeUtf8(this.indent);
            }
        }
    }

    private final JsonWriter open(int i3, String str) {
        beforeValue();
        pushScope(i3);
        this.pathIndices[this.stackSize - 1] = 0;
        this.sink.writeUtf8(str);
        return this;
    }

    private final int peekScope() {
        int i3 = this.stackSize;
        if (i3 != 0) {
            return this.scopes[i3 - 1];
        }
        throw new IllegalStateException("JsonWriter is closed.");
    }

    private final void pushScope(int i3) {
        int i4 = this.stackSize;
        int[] iArr = this.scopes;
        if (i4 != iArr.length) {
            this.stackSize = i4 + 1;
            iArr[i4] = i3;
            return;
        }
        throw new JsonDataException("Nesting too deep at " + getPath() + ": circular reference?");
    }

    private final void replaceTop(int i3) {
        this.scopes[this.stackSize - 1] = i3;
    }

    private final void writeDeferredName() {
        if (this.deferredName != null) {
            beforeName();
            Companion companion = Companion;
            BufferedSink bufferedSink = this.sink;
            String str = this.deferredName;
            Intrinsics.checkNotNull(str);
            companion.string(bufferedSink, str);
            this.deferredName = null;
        }
    }

    @NotNull
    public JsonWriter beginArray() {
        writeDeferredName();
        return open(1, "[");
    }

    @NotNull
    public JsonWriter beginObject() {
        writeDeferredName();
        return open(3, "{");
    }

    @NotNull
    public JsonWriter endArray() {
        return close(1, 2, "]");
    }

    @NotNull
    public JsonWriter endObject() {
        return close(3, 5, StringSubstitutor.DEFAULT_VAR_END);
    }

    public void flush() {
        if (this.stackSize != 0) {
            this.sink.flush();
            return;
        }
        throw new IllegalStateException("JsonWriter is closed.");
    }

    @NotNull
    public String getPath() {
        return CollectionsKt___CollectionsKt.joinToString$default(JsonScope.INSTANCE.getPath(this.stackSize, this.scopes, this.pathNames, this.pathIndices), JwtUtilsKt.JWT_DELIMITER, (CharSequence) null, (CharSequence) null, 0, (CharSequence) null, (Function1) null, 62, (Object) null);
    }

    @NotNull
    public final JsonWriter jsonValue(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "value");
        writeDeferredName();
        beforeValue();
        this.sink.writeUtf8(str);
        int[] iArr = this.pathIndices;
        int i3 = this.stackSize - 1;
        iArr[i3] = iArr[i3] + 1;
        return this;
    }

    @NotNull
    public JsonWriter name(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "name");
        int i3 = this.stackSize;
        if (i3 == 0) {
            throw new IllegalStateException("JsonWriter is closed.");
        } else if (this.deferredName == null) {
            this.deferredName = str;
            this.pathNames[i3 - 1] = str;
            return this;
        } else {
            throw new IllegalStateException("Nesting problem.");
        }
    }

    @NotNull
    public JsonWriter nullValue() {
        return jsonValue(AbstractJsonLexerKt.NULL);
    }

    @JvmOverloads
    public BufferedSinkJsonWriter(@NotNull BufferedSink bufferedSink, @Nullable String str) {
        Intrinsics.checkNotNullParameter(bufferedSink, "sink");
        this.sink = bufferedSink;
        this.indent = str;
        this.scopes = new int[256];
        this.pathNames = new String[256];
        this.pathIndices = new int[256];
        pushScope(6);
    }

    @NotNull
    public JsonWriter value(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "value");
        writeDeferredName();
        beforeValue();
        Companion.string(this.sink, str);
        int[] iArr = this.pathIndices;
        int i3 = this.stackSize - 1;
        iArr[i3] = iArr[i3] + 1;
        return this;
    }

    @NotNull
    public JsonWriter value(boolean z2) {
        return jsonValue(z2 ? "true" : "false");
    }

    @NotNull
    public JsonWriter value(double d2) {
        if (!Double.isNaN(d2) && !Double.isInfinite(d2)) {
            return jsonValue(String.valueOf(d2));
        }
        throw new IllegalArgumentException(("Numeric values must be finite, but was " + d2).toString());
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ BufferedSinkJsonWriter(BufferedSink bufferedSink, String str, int i3, DefaultConstructorMarker defaultConstructorMarker) {
        this(bufferedSink, (i3 & 2) != 0 ? null : str);
    }

    public void close() {
        this.sink.close();
        int i3 = this.stackSize;
        if (i3 > 1 || (i3 == 1 && this.scopes[i3 - 1] != 7)) {
            throw new IOException("Incomplete document");
        }
        this.stackSize = 0;
    }

    @NotNull
    public JsonWriter value(int i3) {
        return jsonValue(String.valueOf(i3));
    }

    @NotNull
    public JsonWriter value(long j2) {
        return jsonValue(String.valueOf(j2));
    }

    @NotNull
    public JsonWriter value(@NotNull JsonNumber jsonNumber) {
        Intrinsics.checkNotNullParameter(jsonNumber, "value");
        return jsonValue(jsonNumber.getValue());
    }

    @NotNull
    public BufferedSinkJsonWriter value(@NotNull Upload upload) {
        Intrinsics.checkNotNullParameter(upload, "value");
        nullValue();
        return this;
    }
}
