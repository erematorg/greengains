package com.apollographql.apollo3.api.json;

import androidx.compose.animation.core.a;
import com.apollographql.apollo3.api.json.JsonReader;
import com.apollographql.apollo3.api.json.internal.JsonScope;
import com.apollographql.apollo3.exception.JsonDataException;
import com.apollographql.apollo3.exception.JsonEncodingException;
import com.reown.foundation.util.jwt.JwtUtilsKt;
import java.io.EOFException;
import java.util.List;
import kotlin.KotlinNothingValueException;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;
import okio.Buffer;
import okio.BufferedSource;
import okio.ByteString;
import org.apache.commons.lang3.CharUtils;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.msgpack.core.MessagePack;

@Metadata(d1 = {"\u0000\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0015\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\f\n\u0002\b\u0002\n\u0002\u0010\u0006\n\u0002\b\u0006\n\u0002\u0010\u0001\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u000f\u0018\u0000 G2\u00020\u0001:\u0001GB\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\b\u0010\u0017\u001a\u00020\u0001H\u0016J\b\u0010\u0018\u001a\u00020\u0001H\u0016J\b\u0010\u0019\u001a\u00020\u001aH\u0016J\b\u0010\u001b\u001a\u00020\nH\u0002J\b\u0010\u001c\u001a\u00020\u0001H\u0016J\b\u0010\u001d\u001a\u00020\u0001H\u0016J\u000e\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020 0\u001fH\u0016J\b\u0010!\u001a\u00020\u000eH\u0002J\b\u0010\"\u001a\u00020#H\u0016J\u0010\u0010$\u001a\u00020#2\u0006\u0010%\u001a\u00020&H\u0002J\b\u0010'\u001a\u00020#H\u0016J\b\u0010(\u001a\u00020)H\u0016J\b\u0010*\u001a\u00020\nH\u0016J\b\u0010+\u001a\u00020\u0012H\u0016J\b\u0010,\u001a\u00020\u000eH\u0016J\u0010\u0010-\u001a\u00020\n2\u0006\u0010.\u001a\u00020#H\u0002J\n\u0010/\u001a\u0004\u0018\u000100H\u0016J\b\u00101\u001a\u000202H\u0016J\u0010\u00103\u001a\u00020\u000e2\u0006\u00104\u001a\u000205H\u0002J\n\u00106\u001a\u0004\u0018\u00010\u000eH\u0016J\b\u00107\u001a\u00020\u000eH\u0002J\b\u00108\u001a\u000209H\u0016J\b\u0010:\u001a\u00020\nH\u0002J\b\u0010;\u001a\u00020\nH\u0002J\u0010\u0010<\u001a\u00020\u001a2\u0006\u0010=\u001a\u00020\nH\u0002J\b\u0010>\u001a\u00020&H\u0002J\b\u0010?\u001a\u00020\u001aH\u0016J\u0016\u0010@\u001a\u00020\n2\f\u0010A\u001a\b\u0012\u0004\u0012\u00020\u000e0\u001fH\u0016J\u0010\u0010B\u001a\u00020\u001a2\u0006\u00104\u001a\u000205H\u0002J\b\u0010C\u001a\u00020\u001aH\u0002J\b\u0010D\u001a\u00020\u001aH\u0016J\u0010\u0010E\u001a\u0002002\u0006\u0010F\u001a\u00020\u000eH\u0002R\u000e\u0010\u0005\u001a\u00020\u0006X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\bX\u0004¢\u0006\u0002\n\u0000R\u0018\u0010\f\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u000e0\rX\u0004¢\u0006\u0004\n\u0002\u0010\u000fR\u000e\u0010\u0010\u001a\u00020\nX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0012X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\nX\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0014\u001a\u0004\u0018\u00010\u000eX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\bX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\nX\u000e¢\u0006\u0002\n\u0000¨\u0006H"}, d2 = {"Lcom/apollographql/apollo3/api/json/BufferedSourceJsonReader;", "Lcom/apollographql/apollo3/api/json/JsonReader;", "source", "Lokio/BufferedSource;", "(Lokio/BufferedSource;)V", "buffer", "Lokio/Buffer;", "indexStack", "", "indexStackSize", "", "pathIndices", "pathNames", "", "", "[Ljava/lang/String;", "peeked", "peekedLong", "", "peekedNumberLength", "peekedString", "stack", "stackSize", "beginArray", "beginObject", "close", "", "doPeek", "endArray", "endObject", "getPath", "", "", "getPathAsString", "hasNext", "", "isLiteral", "c", "", "nextBoolean", "nextDouble", "", "nextInt", "nextLong", "nextName", "nextNonWhitespace", "throwOnEof", "nextNull", "", "nextNumber", "Lcom/apollographql/apollo3/api/json/JsonNumber;", "nextQuotedValue", "runTerminator", "Lokio/ByteString;", "nextString", "nextUnquotedValue", "peek", "Lcom/apollographql/apollo3/api/json/JsonReader$Token;", "peekKeyword", "peekNumber", "push", "newTop", "readEscapeCharacter", "rewind", "selectName", "names", "skipQuotedValue", "skipUnquotedValue", "skipValue", "throwSyntaxError", "message", "Companion", "apollo-api"}, k = 1, mv = {1, 5, 1}, xi = 48)
@SourceDebugExtension({"SMAP\nBufferedSourceJsonReader.kt\nKotlin\n*S Kotlin\n*F\n+ 1 BufferedSourceJsonReader.kt\ncom/apollographql/apollo3/api/json/BufferedSourceJsonReader\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,894:1\n1#2:895\n*E\n"})
public final class BufferedSourceJsonReader implements JsonReader {
    @NotNull
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    @NotNull
    private static final ByteString DOUBLE_QUOTE_OR_SLASH;
    public static final int MAX_STACK_SIZE = 256;
    private static final long MIN_INCOMPLETE_INTEGER = -922337203685477580L;
    private static final int NUMBER_CHAR_DECIMAL = 3;
    private static final int NUMBER_CHAR_DIGIT = 2;
    private static final int NUMBER_CHAR_EXP_DIGIT = 7;
    private static final int NUMBER_CHAR_EXP_E = 5;
    private static final int NUMBER_CHAR_EXP_SIGN = 6;
    private static final int NUMBER_CHAR_FRACTION_DIGIT = 4;
    private static final int NUMBER_CHAR_NONE = 0;
    private static final int NUMBER_CHAR_SIGN = 1;
    private static final int PEEKED_BEGIN_ARRAY = 3;
    private static final int PEEKED_BEGIN_OBJECT = 1;
    private static final int PEEKED_BUFFERED = 11;
    private static final int PEEKED_DOUBLE_QUOTED = 9;
    private static final int PEEKED_DOUBLE_QUOTED_NAME = 13;
    private static final int PEEKED_END_ARRAY = 4;
    private static final int PEEKED_END_OBJECT = 2;
    private static final int PEEKED_EOF = 17;
    private static final int PEEKED_FALSE = 6;
    private static final int PEEKED_LONG = 15;
    private static final int PEEKED_NONE = 0;
    private static final int PEEKED_NULL = 7;
    private static final int PEEKED_NUMBER = 16;
    private static final int PEEKED_SINGLE_QUOTED = 8;
    private static final int PEEKED_SINGLE_QUOTED_NAME = 12;
    private static final int PEEKED_TRUE = 5;
    private static final int PEEKED_UNQUOTED = 10;
    private static final int PEEKED_UNQUOTED_NAME = 14;
    @NotNull
    private static final ByteString SINGLE_QUOTE_OR_SLASH;
    @NotNull
    private static final ByteString UNQUOTED_STRING_TERMINALS;
    @NotNull
    private final Buffer buffer;
    @NotNull
    private final int[] indexStack;
    private int indexStackSize;
    @NotNull
    private final int[] pathIndices = new int[256];
    @NotNull
    private final String[] pathNames = new String[256];
    private int peeked;
    private long peekedLong;
    private int peekedNumberLength;
    @Nullable
    private String peekedString;
    @NotNull
    private final BufferedSource source;
    @NotNull
    private final int[] stack;
    private int stackSize = 1;

    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\t\n\u0002\b\u001d\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bXT¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0006XT¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0006XT¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0006XT¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0006XT¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u0006XT¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u0006XT¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0006XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0006XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0006XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0006XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0006XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0006XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u0006XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\u0006XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0017\u001a\u00020\u0006XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0018\u001a\u00020\u0006XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0019\u001a\u00020\u0006XT¢\u0006\u0002\n\u0000R\u000e\u0010\u001a\u001a\u00020\u0006XT¢\u0006\u0002\n\u0000R\u000e\u0010\u001b\u001a\u00020\u0006XT¢\u0006\u0002\n\u0000R\u000e\u0010\u001c\u001a\u00020\u0006XT¢\u0006\u0002\n\u0000R\u000e\u0010\u001d\u001a\u00020\u0006XT¢\u0006\u0002\n\u0000R\u000e\u0010\u001e\u001a\u00020\u0006XT¢\u0006\u0002\n\u0000R\u000e\u0010\u001f\u001a\u00020\u0006XT¢\u0006\u0002\n\u0000R\u000e\u0010 \u001a\u00020\u0006XT¢\u0006\u0002\n\u0000R\u000e\u0010!\u001a\u00020\u0006XT¢\u0006\u0002\n\u0000R\u000e\u0010\"\u001a\u00020\u0006XT¢\u0006\u0002\n\u0000R\u000e\u0010#\u001a\u00020\u0004X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010$\u001a\u00020\u0004X\u0004¢\u0006\u0002\n\u0000¨\u0006%"}, d2 = {"Lcom/apollographql/apollo3/api/json/BufferedSourceJsonReader$Companion;", "", "()V", "DOUBLE_QUOTE_OR_SLASH", "Lokio/ByteString;", "MAX_STACK_SIZE", "", "MIN_INCOMPLETE_INTEGER", "", "NUMBER_CHAR_DECIMAL", "NUMBER_CHAR_DIGIT", "NUMBER_CHAR_EXP_DIGIT", "NUMBER_CHAR_EXP_E", "NUMBER_CHAR_EXP_SIGN", "NUMBER_CHAR_FRACTION_DIGIT", "NUMBER_CHAR_NONE", "NUMBER_CHAR_SIGN", "PEEKED_BEGIN_ARRAY", "PEEKED_BEGIN_OBJECT", "PEEKED_BUFFERED", "PEEKED_DOUBLE_QUOTED", "PEEKED_DOUBLE_QUOTED_NAME", "PEEKED_END_ARRAY", "PEEKED_END_OBJECT", "PEEKED_EOF", "PEEKED_FALSE", "PEEKED_LONG", "PEEKED_NONE", "PEEKED_NULL", "PEEKED_NUMBER", "PEEKED_SINGLE_QUOTED", "PEEKED_SINGLE_QUOTED_NAME", "PEEKED_TRUE", "PEEKED_UNQUOTED", "PEEKED_UNQUOTED_NAME", "SINGLE_QUOTE_OR_SLASH", "UNQUOTED_STRING_TERMINALS", "apollo-api"}, k = 1, mv = {1, 5, 1}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }

    static {
        ByteString.Companion companion = ByteString.Companion;
        SINGLE_QUOTE_OR_SLASH = companion.encodeUtf8("'\\");
        DOUBLE_QUOTE_OR_SLASH = companion.encodeUtf8("\"\\");
        UNQUOTED_STRING_TERMINALS = companion.encodeUtf8("{}[]:, \n\t\r/\\;#=");
    }

    public BufferedSourceJsonReader(@NotNull BufferedSource bufferedSource) {
        Intrinsics.checkNotNullParameter(bufferedSource, "source");
        this.source = bufferedSource;
        this.buffer = bufferedSource.getBuffer();
        int[] iArr = new int[256];
        iArr[0] = 6;
        this.stack = iArr;
        int[] iArr2 = new int[256];
        iArr2[0] = 0;
        this.indexStack = iArr2;
        this.indexStackSize = 1;
    }

    private final int doPeek() {
        int[] iArr = this.stack;
        int i3 = this.stackSize;
        int i4 = iArr[i3 - 1];
        switch (i4) {
            case 1:
                iArr[i3 - 1] = 2;
                break;
            case 2:
                int nextNonWhitespace = nextNonWhitespace(true);
                this.buffer.readByte();
                char c3 = (char) nextNonWhitespace;
                if (c3 == ']') {
                    this.peeked = 4;
                    return 4;
                } else if (c3 != ',') {
                    throwSyntaxError("Unterminated array");
                    throw new KotlinNothingValueException();
                }
                break;
            case 3:
            case 5:
                iArr[i3 - 1] = 4;
                if (i4 == 5) {
                    int nextNonWhitespace2 = nextNonWhitespace(true);
                    this.buffer.readByte();
                    char c4 = (char) nextNonWhitespace2;
                    if (c4 == '}') {
                        this.peeked = 2;
                        return 2;
                    } else if (c4 != ',') {
                        throwSyntaxError("Unterminated object");
                        throw new KotlinNothingValueException();
                    }
                }
                char nextNonWhitespace3 = (char) nextNonWhitespace(true);
                if (nextNonWhitespace3 == '\"') {
                    this.buffer.readByte();
                    this.peeked = 13;
                    return 13;
                } else if (nextNonWhitespace3 != '}') {
                    throwSyntaxError(a.p("Unexpected character: ", nextNonWhitespace3));
                    throw new KotlinNothingValueException();
                } else if (i4 != 5) {
                    this.buffer.readByte();
                    this.peeked = 2;
                    return 2;
                } else {
                    throwSyntaxError("Expected name");
                    throw new KotlinNothingValueException();
                }
            case 4:
                iArr[i3 - 1] = 5;
                int nextNonWhitespace4 = nextNonWhitespace(true);
                this.buffer.readByte();
                if (((char) nextNonWhitespace4) != ':') {
                    throwSyntaxError("Expected ':'");
                    throw new KotlinNothingValueException();
                }
                break;
            case 6:
                iArr[i3 - 1] = 7;
                break;
            case 7:
                if (nextNonWhitespace(false) == -1) {
                    this.peeked = 17;
                    return 17;
                }
                throwSyntaxError("Malformed JSON");
                throw new KotlinNothingValueException();
            default:
                if (i4 == 8) {
                    throw new IllegalStateException("JsonReader is closed");
                }
                break;
        }
        char nextNonWhitespace5 = (char) nextNonWhitespace(true);
        if (nextNonWhitespace5 == ']') {
            if (i4 == 1) {
                this.buffer.readByte();
                this.peeked = 4;
                return 4;
            }
            throwSyntaxError("Unexpected value");
            throw new KotlinNothingValueException();
        } else if (nextNonWhitespace5 == ';' || nextNonWhitespace5 == ',' || nextNonWhitespace5 == '\'') {
            throwSyntaxError("Unexpected value");
            throw new KotlinNothingValueException();
        } else if (nextNonWhitespace5 == '\"') {
            this.buffer.readByte();
            this.peeked = 9;
            return 9;
        } else if (nextNonWhitespace5 == '[') {
            this.buffer.readByte();
            this.peeked = 3;
            return 3;
        } else if (nextNonWhitespace5 == '{') {
            this.buffer.readByte();
            this.peeked = 1;
            return 1;
        } else {
            int peekKeyword = peekKeyword();
            if (peekKeyword != 0) {
                return peekKeyword;
            }
            int peekNumber = peekNumber();
            if (peekNumber != 0) {
                return peekNumber;
            }
            if (!isLiteral((char) this.buffer.getByte(0))) {
                throwSyntaxError("Expected value");
                throw new KotlinNothingValueException();
            }
            throwSyntaxError("Malformed JSON");
            throw new KotlinNothingValueException();
        }
    }

    private final String getPathAsString() {
        return CollectionsKt___CollectionsKt.joinToString$default(getPath(), JwtUtilsKt.JWT_DELIMITER, (CharSequence) null, (CharSequence) null, 0, (CharSequence) null, (Function1) null, 62, (Object) null);
    }

    private final boolean isLiteral(char c3) {
        if (c3 == '/' || c3 == '\\' || c3 == ';' || c3 == '#' || c3 == '=') {
            throwSyntaxError(a.p("Unexpected character: ", c3));
            throw new KotlinNothingValueException();
        }
        return !(c3 == '{' || c3 == '}' || c3 == '[' || c3 == ']' || c3 == ':' || c3 == ',' || c3 == ' ' || c3 == 9 || c3 == 13 || c3 == 10);
    }

    private final int nextNonWhitespace(boolean z2) {
        int i3 = 0;
        while (true) {
            long j2 = (long) i3;
            if (this.source.request(j2 + 1)) {
                i3++;
                byte b3 = this.buffer.getByte(j2);
                if (b3 != 9 && b3 != 10 && b3 != 13 && b3 != 32) {
                    this.buffer.skip(((long) i3) - 1);
                    if (b3 == 35) {
                        throwSyntaxError("Malformed JSON");
                        throw new KotlinNothingValueException();
                    } else if (b3 != 47 || !this.source.request(2)) {
                        return b3;
                    } else {
                        throwSyntaxError("Malformed JSON");
                        throw new KotlinNothingValueException();
                    }
                }
            } else if (!z2) {
                return -1;
            } else {
                throw new EOFException("End of input");
            }
        }
    }

    private final String nextQuotedValue(ByteString byteString) {
        StringBuilder sb = null;
        while (true) {
            long indexOfElement = this.source.indexOfElement(byteString);
            if (indexOfElement == -1) {
                throwSyntaxError("Unterminated string");
                throw new KotlinNothingValueException();
            } else if (this.buffer.getByte(indexOfElement) == 92) {
                if (sb == null) {
                    sb = new StringBuilder();
                }
                sb.append(this.buffer.readUtf8(indexOfElement));
                this.buffer.readByte();
                sb.append(readEscapeCharacter());
            } else if (sb == null) {
                String readUtf8 = this.buffer.readUtf8(indexOfElement);
                this.buffer.readByte();
                return readUtf8;
            } else {
                sb.append(this.buffer.readUtf8(indexOfElement));
                this.buffer.readByte();
                String sb2 = sb.toString();
                Intrinsics.checkNotNullExpressionValue(sb2, "{\n        builder.append…uilder.toString()\n      }");
                return sb2;
            }
        }
    }

    private final String nextUnquotedValue() {
        long indexOfElement = this.source.indexOfElement(UNQUOTED_STRING_TERMINALS);
        int i3 = (indexOfElement > -1 ? 1 : (indexOfElement == -1 ? 0 : -1));
        Buffer buffer2 = this.buffer;
        return i3 != 0 ? buffer2.readUtf8(indexOfElement) : buffer2.readUtf8();
    }

    private final int peekKeyword() {
        int i3;
        String str;
        String str2;
        byte b3 = this.buffer.getByte(0);
        if (b3 == 116 || b3 == 84) {
            str2 = "true";
            str = "TRUE";
            i3 = 5;
        } else if (b3 == 102 || b3 == 70) {
            str2 = "false";
            str = "FALSE";
            i3 = 6;
        } else if (b3 != 110 && b3 != 78) {
            return 0;
        } else {
            str2 = AbstractJsonLexerKt.NULL;
            str = "NULL";
            i3 = 7;
        }
        int length = str2.length();
        for (int i4 = 1; i4 < length; i4++) {
            long j2 = (long) i4;
            if (!this.source.request(1 + j2)) {
                return 0;
            }
            byte b4 = this.buffer.getByte(j2);
            if (b4 != ((byte) str2.charAt(i4)) && b4 != ((byte) str.charAt(i4))) {
                return 0;
            }
        }
        long j3 = (long) length;
        if (this.source.request(1 + j3) && isLiteral((char) this.buffer.getByte(j3))) {
            return 0;
        }
        this.buffer.skip(j3);
        this.peeked = i3;
        return i3;
    }

    private final int peekNumber() {
        long j2;
        char c3;
        int i3 = 0;
        char c4 = 0;
        boolean z2 = false;
        long j3 = 0;
        boolean z3 = true;
        while (true) {
            j2 = (long) i3;
            if (!this.source.request(1 + j2)) {
                break;
            }
            byte b3 = this.buffer.getByte(j2);
            c3 = (char) b3;
            if (c3 == '-') {
                if (c4 == 0) {
                    c4 = 1;
                    z2 = true;
                    i3++;
                } else if (c4 != 5) {
                    return 0;
                }
            } else if (c3 != '+') {
                if (c3 == 'e' || c3 == 'E') {
                    if (c4 != 2 && c4 != 4) {
                        return 0;
                    }
                    c4 = 5;
                } else if (c3 == '.') {
                    if (c4 != 2) {
                        return 0;
                    }
                    c4 = 3;
                } else if (b3 >= 48 && b3 <= 57) {
                    if (c4 == 0 || c4 == 1) {
                        j3 = -((long) (b3 + MessagePack.Code.INT8));
                        c4 = 2;
                        i3++;
                    } else if (c4 != 2) {
                        if (c4 == 3) {
                            c4 = 4;
                        } else if (c4 == 5 || c4 == 6) {
                            c4 = 7;
                        }
                        i3++;
                    } else if (j3 == 0) {
                        return 0;
                    } else {
                        long j4 = (((long) 10) * j3) - ((long) (b3 + MessagePack.Code.INT8));
                        int i4 = (j3 > -922337203685477580L ? 1 : (j3 == -922337203685477580L ? 0 : -1));
                        z3 = ((i4 > 0) && z3) || (i4 == 0 && j4 < j3);
                        j3 = j4;
                        i3++;
                    }
                }
                i3++;
            } else if (c4 != 5) {
                return 0;
            }
            c4 = 6;
            i3++;
        }
        if (isLiteral(c3)) {
            return 0;
        }
        if (c4 == 2 && z3 && (j3 != Long.MIN_VALUE || z2)) {
            if (!z2) {
                j3 = -j3;
            }
            this.peekedLong = j3;
            this.buffer.skip(j2);
            this.peeked = 15;
            return 15;
        } else if (c4 != 2 && c4 != 4 && c4 != 7) {
            return 0;
        } else {
            this.peekedNumberLength = i3;
            this.peeked = 16;
            return 16;
        }
    }

    private final void push(int i3) {
        int i4 = this.stackSize;
        int[] iArr = this.stack;
        if (i4 != iArr.length) {
            this.stackSize = i4 + 1;
            iArr[i4] = i3;
            return;
        }
        throw new JsonDataException("Nesting too deep at " + getPath());
    }

    private final char readEscapeCharacter() {
        int i3;
        if (this.source.request(1)) {
            char readByte = (char) this.buffer.readByte();
            if (readByte == 'u') {
                if (this.source.request(4)) {
                    char c3 = 0;
                    for (int i4 = 0; i4 < 4; i4++) {
                        byte b3 = this.buffer.getByte((long) i4);
                        char c4 = (char) (c3 << 4);
                        if (b3 >= 48 && b3 <= 57) {
                            i3 = b3 + MessagePack.Code.INT8;
                        } else if (b3 >= 97 && b3 <= 102) {
                            i3 = b3 - 87;
                        } else if (b3 < 65 || b3 > 70) {
                            throwSyntaxError("\\u" + this.buffer.readUtf8(4));
                            throw new KotlinNothingValueException();
                        } else {
                            i3 = b3 + MessagePack.Code.EXT32;
                        }
                        c3 = (char) (c4 + i3);
                    }
                    this.buffer.skip(4);
                    return c3;
                }
                throw new EOFException("Unterminated escape sequence at path " + getPath());
            } else if (readByte == 't') {
                return 9;
            } else {
                if (readByte == 'b') {
                    return 8;
                }
                if (readByte == 'n') {
                    return 10;
                }
                if (readByte == 'r') {
                    return CharUtils.CR;
                }
                if (readByte == 'f') {
                    return 12;
                }
                if (readByte == 10 || readByte == '\'' || readByte == '\"' || readByte == '\\' || readByte == '/') {
                    return readByte;
                }
                throwSyntaxError(a.p("Invalid escape sequence: \\", readByte));
                throw new KotlinNothingValueException();
            }
        } else {
            throwSyntaxError("Unterminated escape sequence");
            throw new KotlinNothingValueException();
        }
    }

    private final void skipQuotedValue(ByteString byteString) {
        while (true) {
            long indexOfElement = this.source.indexOfElement(byteString);
            if (indexOfElement == -1) {
                throwSyntaxError("Unterminated string");
                throw new KotlinNothingValueException();
            } else if (this.buffer.getByte(indexOfElement) == 92) {
                this.buffer.skip(indexOfElement + 1);
                readEscapeCharacter();
            } else {
                this.buffer.skip(indexOfElement + 1);
                return;
            }
        }
    }

    private final void skipUnquotedValue() {
        long indexOfElement = this.source.indexOfElement(UNQUOTED_STRING_TERMINALS);
        Buffer buffer2 = this.buffer;
        if (indexOfElement == -1) {
            indexOfElement = buffer2.size();
        }
        buffer2.skip(indexOfElement);
    }

    private final Void throwSyntaxError(String str) {
        StringBuilder q2 = A.a.q(str, " at path ");
        q2.append(getPath());
        throw new JsonEncodingException(q2.toString());
    }

    @NotNull
    public JsonReader beginArray() {
        Integer valueOf = Integer.valueOf(this.peeked);
        if (valueOf.intValue() == 0) {
            valueOf = null;
        }
        if ((valueOf != null ? valueOf.intValue() : doPeek()) == 3) {
            push(1);
            this.pathIndices[this.stackSize - 1] = 0;
            this.peeked = 0;
            return this;
        }
        throw new JsonDataException("Expected BEGIN_ARRAY but was " + peek() + " at path " + getPathAsString());
    }

    @NotNull
    public JsonReader beginObject() {
        Integer valueOf = Integer.valueOf(this.peeked);
        if (valueOf.intValue() == 0) {
            valueOf = null;
        }
        if ((valueOf != null ? valueOf.intValue() : doPeek()) == 1) {
            push(3);
            this.peeked = 0;
            int i3 = this.indexStackSize;
            this.indexStackSize = i3 + 1;
            this.indexStack[i3] = 0;
            return this;
        }
        throw new JsonDataException("Expected BEGIN_OBJECT but was " + peek() + " at path " + getPathAsString());
    }

    public void close() {
        this.peeked = 0;
        this.stack[0] = 8;
        this.stackSize = 1;
        this.buffer.clear();
        this.source.close();
    }

    @NotNull
    public JsonReader endArray() {
        Integer valueOf = Integer.valueOf(this.peeked);
        if (valueOf.intValue() == 0) {
            valueOf = null;
        }
        if ((valueOf != null ? valueOf.intValue() : doPeek()) == 4) {
            int i3 = this.stackSize;
            this.stackSize = i3 - 1;
            int[] iArr = this.pathIndices;
            int i4 = i3 - 2;
            iArr[i4] = iArr[i4] + 1;
            this.peeked = 0;
            return this;
        }
        throw new JsonDataException("Expected END_ARRAY but was " + peek() + " at path " + getPathAsString());
    }

    @NotNull
    public JsonReader endObject() {
        Integer valueOf = Integer.valueOf(this.peeked);
        if (valueOf.intValue() == 0) {
            valueOf = null;
        }
        if ((valueOf != null ? valueOf.intValue() : doPeek()) == 2) {
            int i3 = this.stackSize;
            int i4 = i3 - 1;
            this.stackSize = i4;
            this.pathNames[i4] = null;
            int[] iArr = this.pathIndices;
            int i5 = i3 - 2;
            iArr[i5] = iArr[i5] + 1;
            this.peeked = 0;
            this.indexStackSize--;
            return this;
        }
        throw new JsonDataException("Expected END_OBJECT but was " + peek() + " at path " + getPathAsString());
    }

    @NotNull
    public List<Object> getPath() {
        return JsonScope.INSTANCE.getPath(this.stackSize, this.stack, this.pathNames, this.pathIndices);
    }

    public boolean hasNext() {
        Integer valueOf = Integer.valueOf(this.peeked);
        if (valueOf.intValue() == 0) {
            valueOf = null;
        }
        int intValue = valueOf != null ? valueOf.intValue() : doPeek();
        return (intValue == 2 || intValue == 4) ? false : true;
    }

    public boolean nextBoolean() {
        Integer valueOf = Integer.valueOf(this.peeked);
        if (valueOf.intValue() == 0) {
            valueOf = null;
        }
        int intValue = valueOf != null ? valueOf.intValue() : doPeek();
        if (intValue == 5) {
            this.peeked = 0;
            int[] iArr = this.pathIndices;
            int i3 = this.stackSize - 1;
            iArr[i3] = iArr[i3] + 1;
            return true;
        } else if (intValue == 6) {
            this.peeked = 0;
            int[] iArr2 = this.pathIndices;
            int i4 = this.stackSize - 1;
            iArr2[i4] = iArr2[i4] + 1;
            return false;
        } else {
            throw new JsonDataException("Expected a boolean but was " + peek() + " at path " + getPathAsString());
        }
    }

    public double nextDouble() {
        Integer valueOf = Integer.valueOf(this.peeked);
        if (valueOf.intValue() == 0) {
            valueOf = null;
        }
        int intValue = valueOf != null ? valueOf.intValue() : doPeek();
        if (intValue == 15) {
            this.peeked = 0;
            int[] iArr = this.pathIndices;
            int i3 = this.stackSize - 1;
            iArr[i3] = iArr[i3] + 1;
            return (double) this.peekedLong;
        }
        if (intValue == 16) {
            this.peekedString = this.buffer.readUtf8((long) this.peekedNumberLength);
        } else if (intValue == 9) {
            this.peekedString = nextQuotedValue(DOUBLE_QUOTE_OR_SLASH);
        } else if (intValue == 8) {
            this.peekedString = nextQuotedValue(SINGLE_QUOTE_OR_SLASH);
        } else if (intValue == 10) {
            this.peekedString = nextUnquotedValue();
        } else if (intValue != 11) {
            throw new JsonDataException("Expected a double but was " + peek() + " at path " + getPathAsString());
        }
        this.peeked = 11;
        try {
            String str = this.peekedString;
            Intrinsics.checkNotNull(str);
            double parseDouble = Double.parseDouble(str);
            if (Double.isNaN(parseDouble) || Double.isInfinite(parseDouble)) {
                throw new JsonEncodingException("JSON forbids NaN and infinities: " + parseDouble + " at path " + getPathAsString());
            }
            this.peekedString = null;
            this.peeked = 0;
            int[] iArr2 = this.pathIndices;
            int i4 = this.stackSize - 1;
            iArr2[i4] = iArr2[i4] + 1;
            return parseDouble;
        } catch (NumberFormatException unused) {
            throw new JsonDataException("Expected a double but was " + this.peekedString + " at path " + getPathAsString());
        }
    }

    public int nextInt() {
        Integer valueOf = Integer.valueOf(this.peeked);
        if (valueOf.intValue() == 0) {
            valueOf = null;
        }
        int intValue = valueOf != null ? valueOf.intValue() : doPeek();
        if (intValue == 15) {
            long j2 = this.peekedLong;
            int i3 = (int) j2;
            if (j2 == ((long) i3)) {
                this.peeked = 0;
                int[] iArr = this.pathIndices;
                int i4 = this.stackSize - 1;
                iArr[i4] = iArr[i4] + 1;
                return i3;
            }
            throw new JsonDataException("Expected an int but was " + this.peekedLong + " at path " + getPath());
        }
        if (intValue == 16) {
            this.peekedString = this.buffer.readUtf8((long) this.peekedNumberLength);
        } else if (intValue == 9 || intValue == 8) {
            String nextQuotedValue = nextQuotedValue(intValue == 9 ? DOUBLE_QUOTE_OR_SLASH : SINGLE_QUOTE_OR_SLASH);
            this.peekedString = nextQuotedValue;
            try {
                Intrinsics.checkNotNull(nextQuotedValue);
                int parseInt = Integer.parseInt(nextQuotedValue);
                this.peeked = 0;
                int[] iArr2 = this.pathIndices;
                int i5 = this.stackSize - 1;
                iArr2[i5] = iArr2[i5] + 1;
                return parseInt;
            } catch (NumberFormatException unused) {
            }
        } else if (intValue != 11) {
            throw new JsonDataException("Expected an int but was " + peek() + " at path " + getPathAsString());
        }
        this.peeked = 11;
        try {
            String str = this.peekedString;
            Intrinsics.checkNotNull(str);
            double parseDouble = Double.parseDouble(str);
            int i6 = (int) parseDouble;
            if (((double) i6) == parseDouble) {
                this.peekedString = null;
                this.peeked = 0;
                int[] iArr3 = this.pathIndices;
                int i7 = this.stackSize - 1;
                iArr3[i7] = iArr3[i7] + 1;
                return i6;
            }
            throw new JsonDataException("Expected an int but was " + this.peekedString + " at path " + getPathAsString());
        } catch (NumberFormatException unused2) {
            throw new JsonDataException("Expected an int but was " + this.peekedString + " at path " + getPathAsString());
        }
    }

    public long nextLong() {
        Integer valueOf = Integer.valueOf(this.peeked);
        if (valueOf.intValue() == 0) {
            valueOf = null;
        }
        int intValue = valueOf != null ? valueOf.intValue() : doPeek();
        if (intValue == 15) {
            this.peeked = 0;
            int[] iArr = this.pathIndices;
            int i3 = this.stackSize - 1;
            iArr[i3] = iArr[i3] + 1;
            return this.peekedLong;
        }
        if (intValue == 16) {
            this.peekedString = this.buffer.readUtf8((long) this.peekedNumberLength);
        } else if (intValue == 9 || intValue == 8) {
            String nextQuotedValue = nextQuotedValue(intValue == 9 ? DOUBLE_QUOTE_OR_SLASH : SINGLE_QUOTE_OR_SLASH);
            this.peekedString = nextQuotedValue;
            try {
                Intrinsics.checkNotNull(nextQuotedValue);
                long parseLong = Long.parseLong(nextQuotedValue);
                this.peeked = 0;
                int[] iArr2 = this.pathIndices;
                int i4 = this.stackSize - 1;
                iArr2[i4] = iArr2[i4] + 1;
                return parseLong;
            } catch (NumberFormatException unused) {
            }
        } else if (intValue != 11) {
            throw new JsonDataException("Expected a long but was " + peek() + " at path " + getPathAsString());
        }
        this.peeked = 11;
        try {
            String str = this.peekedString;
            Intrinsics.checkNotNull(str);
            double parseDouble = Double.parseDouble(str);
            long j2 = (long) parseDouble;
            if (((double) j2) == parseDouble) {
                this.peekedString = null;
                this.peeked = 0;
                int[] iArr3 = this.pathIndices;
                int i5 = this.stackSize - 1;
                iArr3[i5] = iArr3[i5] + 1;
                return j2;
            }
            throw new JsonDataException("Expected a long but was " + this.peekedString + " at path " + getPathAsString());
        } catch (NumberFormatException unused2) {
            throw new JsonDataException("Expected a long but was " + this.peekedString + " at path " + getPathAsString());
        }
    }

    @NotNull
    public String nextName() {
        String str;
        Integer valueOf = Integer.valueOf(this.peeked);
        if (valueOf.intValue() == 0) {
            valueOf = null;
        }
        switch (valueOf != null ? valueOf.intValue() : doPeek()) {
            case 12:
                str = nextQuotedValue(SINGLE_QUOTE_OR_SLASH);
                break;
            case 13:
                str = nextQuotedValue(DOUBLE_QUOTE_OR_SLASH);
                break;
            case 14:
                str = nextUnquotedValue();
                break;
            default:
                throw new JsonDataException("Expected a name but was " + peek() + " at path " + getPathAsString());
        }
        this.peeked = 0;
        this.pathNames[this.stackSize - 1] = str;
        return str;
    }

    @Nullable
    public Void nextNull() {
        Integer valueOf = Integer.valueOf(this.peeked);
        if (valueOf.intValue() == 0) {
            valueOf = null;
        }
        if ((valueOf != null ? valueOf.intValue() : doPeek()) == 7) {
            this.peeked = 0;
            int[] iArr = this.pathIndices;
            int i3 = this.stackSize - 1;
            iArr[i3] = iArr[i3] + 1;
            return null;
        }
        throw new JsonDataException("Expected null but was " + peek() + " at path " + getPathAsString());
    }

    @NotNull
    public JsonNumber nextNumber() {
        String nextString = nextString();
        Intrinsics.checkNotNull(nextString);
        return new JsonNumber(nextString);
    }

    @Nullable
    public String nextString() {
        Integer valueOf = Integer.valueOf(this.peeked);
        String str = null;
        if (valueOf.intValue() == 0) {
            valueOf = null;
        }
        int intValue = valueOf != null ? valueOf.intValue() : doPeek();
        if (intValue == 15) {
            str = String.valueOf(this.peekedLong);
        } else if (intValue != 16) {
            switch (intValue) {
                case 8:
                    str = nextQuotedValue(SINGLE_QUOTE_OR_SLASH);
                    break;
                case 9:
                    str = nextQuotedValue(DOUBLE_QUOTE_OR_SLASH);
                    break;
                case 10:
                    str = nextUnquotedValue();
                    break;
                case 11:
                    String str2 = this.peekedString;
                    if (str2 != null) {
                        this.peekedString = null;
                        str = str2;
                        break;
                    }
                    break;
                default:
                    throw new JsonDataException("Expected a string but was " + peek() + " at path " + getPathAsString());
            }
        } else {
            str = this.buffer.readUtf8((long) this.peekedNumberLength);
        }
        this.peeked = 0;
        int[] iArr = this.pathIndices;
        int i3 = this.stackSize - 1;
        iArr[i3] = iArr[i3] + 1;
        return str;
    }

    @NotNull
    public JsonReader.Token peek() {
        Integer valueOf = Integer.valueOf(this.peeked);
        if (valueOf.intValue() == 0) {
            valueOf = null;
        }
        switch (valueOf != null ? valueOf.intValue() : doPeek()) {
            case 1:
                return JsonReader.Token.BEGIN_OBJECT;
            case 2:
                return JsonReader.Token.END_OBJECT;
            case 3:
                return JsonReader.Token.BEGIN_ARRAY;
            case 4:
                return JsonReader.Token.END_ARRAY;
            case 5:
            case 6:
                return JsonReader.Token.BOOLEAN;
            case 7:
                return JsonReader.Token.NULL;
            case 8:
            case 9:
            case 10:
            case 11:
                return JsonReader.Token.STRING;
            case 12:
            case 13:
            case 14:
                return JsonReader.Token.NAME;
            case 15:
                return JsonReader.Token.LONG;
            case 16:
                return JsonReader.Token.NUMBER;
            case 17:
                return JsonReader.Token.END_DOCUMENT;
            default:
                throw new AssertionError();
        }
    }

    public void rewind() {
        throw new IllegalStateException("BufferedSourceJsonReader cannot rewind.");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0053, code lost:
        skipValue();
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public int selectName(@org.jetbrains.annotations.NotNull java.util.List<java.lang.String> r7) {
        /*
            r6 = this;
            java.lang.String r0 = "names"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r7, r0)
            boolean r0 = r7.isEmpty()
            r1 = -1
            if (r0 == 0) goto L_0x000d
            return r1
        L_0x000d:
            boolean r0 = r6.hasNext()
            if (r0 == 0) goto L_0x007e
            java.lang.String r0 = r6.nextName()
            int[] r2 = r6.indexStack
            int r3 = r6.indexStackSize
            int r3 = r3 + -1
            r2 = r2[r3]
            java.lang.Object r3 = r7.get(r2)
            boolean r3 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r3, (java.lang.Object) r0)
            r4 = 0
            if (r3 == 0) goto L_0x0047
            int[] r0 = r6.indexStack
            int r1 = r6.indexStackSize
            int r3 = r1 + -1
            int r5 = r2 + 1
            r0[r3] = r5
            int r1 = r1 + -1
            r0 = r0[r1]
            int r7 = r7.size()
            if (r0 != r7) goto L_0x0046
            int[] r7 = r6.indexStack
            int r6 = r6.indexStackSize
            int r6 = r6 + -1
            r7[r6] = r4
        L_0x0046:
            return r2
        L_0x0047:
            r3 = r2
        L_0x0048:
            int r3 = r3 + 1
            int r5 = r7.size()
            if (r3 != r5) goto L_0x0051
            r3 = r4
        L_0x0051:
            if (r3 != r2) goto L_0x0057
            r6.skipValue()
            goto L_0x000d
        L_0x0057:
            java.lang.Object r5 = r7.get(r3)
            boolean r5 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r5, (java.lang.Object) r0)
            if (r5 == 0) goto L_0x0048
            int[] r0 = r6.indexStack
            int r1 = r6.indexStackSize
            int r2 = r1 + -1
            int r5 = r3 + 1
            r0[r2] = r5
            int r1 = r1 + -1
            r0 = r0[r1]
            int r7 = r7.size()
            if (r0 != r7) goto L_0x007d
            int[] r7 = r6.indexStack
            int r6 = r6.indexStackSize
            int r6 = r6 + -1
            r7[r6] = r4
        L_0x007d:
            return r3
        L_0x007e:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.apollographql.apollo3.api.json.BufferedSourceJsonReader.selectName(java.util.List):int");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:14:0x003e, code lost:
        r1 = r1 - 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0044, code lost:
        r1 = r1 + 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x0053, code lost:
        r6.peeked = 0;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void skipValue() {
        /*
            r6 = this;
            r0 = 0
            r1 = r0
        L_0x0002:
            int r2 = r6.peeked
            java.lang.Integer r2 = java.lang.Integer.valueOf(r2)
            int r3 = r2.intValue()
            if (r3 != 0) goto L_0x000f
            r2 = 0
        L_0x000f:
            if (r2 == 0) goto L_0x0016
            int r2 = r2.intValue()
            goto L_0x001a
        L_0x0016:
            int r2 = r6.doPeek()
        L_0x001a:
            r3 = 1
            switch(r2) {
                case 1: goto L_0x004e;
                case 2: goto L_0x0047;
                case 3: goto L_0x0041;
                case 4: goto L_0x0038;
                case 5: goto L_0x001e;
                case 6: goto L_0x001e;
                case 7: goto L_0x001e;
                case 8: goto L_0x0032;
                case 9: goto L_0x002c;
                case 10: goto L_0x0028;
                case 11: goto L_0x001e;
                case 12: goto L_0x0032;
                case 13: goto L_0x002c;
                case 14: goto L_0x0028;
                case 15: goto L_0x001e;
                case 16: goto L_0x001f;
                default: goto L_0x001e;
            }
        L_0x001e:
            goto L_0x0053
        L_0x001f:
            okio.Buffer r2 = r6.buffer
            int r4 = r6.peekedNumberLength
            long r4 = (long) r4
            r2.skip(r4)
            goto L_0x0053
        L_0x0028:
            r6.skipUnquotedValue()
            goto L_0x0053
        L_0x002c:
            okio.ByteString r2 = DOUBLE_QUOTE_OR_SLASH
            r6.skipQuotedValue(r2)
            goto L_0x0053
        L_0x0032:
            okio.ByteString r2 = SINGLE_QUOTE_OR_SLASH
            r6.skipQuotedValue(r2)
            goto L_0x0053
        L_0x0038:
            int r2 = r6.stackSize
            int r2 = r2 + -1
            r6.stackSize = r2
        L_0x003e:
            int r1 = r1 + -1
            goto L_0x0053
        L_0x0041:
            r6.push(r3)
        L_0x0044:
            int r1 = r1 + 1
            goto L_0x0053
        L_0x0047:
            int r2 = r6.stackSize
            int r2 = r2 + -1
            r6.stackSize = r2
            goto L_0x003e
        L_0x004e:
            r2 = 3
            r6.push(r2)
            goto L_0x0044
        L_0x0053:
            r6.peeked = r0
            if (r1 != 0) goto L_0x0002
            int[] r0 = r6.pathIndices
            int r1 = r6.stackSize
            int r2 = r1 + -1
            r4 = r0[r2]
            int r4 = r4 + r3
            r0[r2] = r4
            java.lang.String[] r6 = r6.pathNames
            int r1 = r1 - r3
            java.lang.String r0 = "null"
            r6[r1] = r0
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.apollographql.apollo3.api.json.BufferedSourceJsonReader.skipValue():void");
    }
}
