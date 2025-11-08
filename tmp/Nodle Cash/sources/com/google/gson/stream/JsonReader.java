package com.google.gson.stream;

import A.a;
import androidx.camera.camera2.internal.C0118y;
import com.google.gson.Strictness;
import com.google.gson.internal.JsonReaderInternalAccess;
import com.google.gson.internal.TroubleshootingGuide;
import com.google.gson.internal.bind.JsonTreeReader;
import java.io.Closeable;
import java.io.EOFException;
import java.io.IOException;
import java.io.Reader;
import java.util.Arrays;
import java.util.Objects;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;
import org.apache.commons.lang3.CharUtils;
import org.apache.commons.lang3.ClassUtils;

public class JsonReader implements Closeable {
    static final int BUFFER_SIZE = 1024;
    static final int DEFAULT_NESTING_LIMIT = 255;
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
    private final char[] buffer = new char[1024];
    private final Reader in;
    private int limit = 0;
    private int lineNumber = 0;
    private int lineStart = 0;
    private int nestingLimit = 255;
    private int[] pathIndices;
    private String[] pathNames;
    int peeked = 0;
    private long peekedLong;
    private int peekedNumberLength;
    private String peekedString;
    private int pos = 0;
    private int[] stack;
    private int stackSize;
    private Strictness strictness = Strictness.LEGACY_STRICT;

    static {
        JsonReaderInternalAccess.INSTANCE = new JsonReaderInternalAccess() {
            public void promoteNameToValue(JsonReader jsonReader) throws IOException {
                if (jsonReader instanceof JsonTreeReader) {
                    ((JsonTreeReader) jsonReader).promoteNameToValue();
                    return;
                }
                int i3 = jsonReader.peeked;
                if (i3 == 0) {
                    i3 = jsonReader.doPeek();
                }
                if (i3 == 13) {
                    jsonReader.peeked = 9;
                } else if (i3 == 12) {
                    jsonReader.peeked = 8;
                } else if (i3 == 14) {
                    jsonReader.peeked = 10;
                } else {
                    throw jsonReader.unexpectedTokenError("a name");
                }
            }
        };
    }

    public JsonReader(Reader reader) {
        int[] iArr = new int[32];
        this.stack = iArr;
        this.stackSize = 1;
        iArr[0] = 6;
        this.pathNames = new String[32];
        this.pathIndices = new int[32];
        Objects.requireNonNull(reader, "in == null");
        this.in = reader;
    }

    private void checkLenient() throws MalformedJsonException {
        if (this.strictness != Strictness.LENIENT) {
            throw syntaxError("Use JsonReader.setStrictness(Strictness.LENIENT) to accept malformed JSON");
        }
    }

    private void consumeNonExecutePrefix() throws IOException {
        nextNonWhitespace(true);
        int i3 = this.pos;
        this.pos = i3 - 1;
        if (i3 + 4 <= this.limit || fillBuffer(5)) {
            int i4 = this.pos;
            char[] cArr = this.buffer;
            if (cArr[i4] == ')' && cArr[i4 + 1] == ']' && cArr[i4 + 2] == '}' && cArr[i4 + 3] == '\'' && cArr[i4 + 4] == 10) {
                this.pos = i4 + 5;
            }
        }
    }

    private boolean fillBuffer(int i3) throws IOException {
        int i4;
        int i5;
        char[] cArr = this.buffer;
        int i6 = this.lineStart;
        int i7 = this.pos;
        this.lineStart = i6 - i7;
        int i8 = this.limit;
        if (i8 != i7) {
            int i9 = i8 - i7;
            this.limit = i9;
            System.arraycopy(cArr, i7, cArr, 0, i9);
        } else {
            this.limit = 0;
        }
        this.pos = 0;
        do {
            Reader reader = this.in;
            int i10 = this.limit;
            int read = reader.read(cArr, i10, cArr.length - i10);
            if (read == -1) {
                return false;
            }
            i4 = this.limit + read;
            this.limit = i4;
            if (this.lineNumber == 0 && (i5 = this.lineStart) == 0 && i4 > 0 && cArr[0] == 65279) {
                this.pos++;
                this.lineStart = i5 + 1;
                i3++;
                continue;
            }
        } while (i4 < i3);
        return true;
    }

    private String getPath(boolean z2) {
        StringBuilder sb = new StringBuilder("$");
        int i3 = 0;
        while (true) {
            int i4 = this.stackSize;
            if (i3 >= i4) {
                return sb.toString();
            }
            int i5 = this.stack[i3];
            switch (i5) {
                case 1:
                case 2:
                    int i6 = this.pathIndices[i3];
                    if (z2 && i6 > 0 && i3 == i4 - 1) {
                        i6--;
                    }
                    sb.append(AbstractJsonLexerKt.BEGIN_LIST);
                    sb.append(i6);
                    sb.append(AbstractJsonLexerKt.END_LIST);
                    break;
                case 3:
                case 4:
                case 5:
                    sb.append(ClassUtils.PACKAGE_SEPARATOR_CHAR);
                    String str = this.pathNames[i3];
                    if (str == null) {
                        break;
                    } else {
                        sb.append(str);
                        break;
                    }
                case 6:
                case 7:
                case 8:
                    break;
                default:
                    throw new AssertionError(a.k("Unknown scope value: ", i5));
            }
            i3++;
        }
    }

    private boolean isLiteral(char c3) throws IOException {
        if (c3 == 9 || c3 == 10 || c3 == 12 || c3 == 13 || c3 == ' ') {
            return false;
        }
        if (c3 != '#') {
            if (c3 == ',') {
                return false;
            }
            if (!(c3 == '/' || c3 == '=')) {
                if (c3 == '{' || c3 == '}' || c3 == ':') {
                    return false;
                }
                if (c3 != ';') {
                    switch (c3) {
                        case '[':
                        case ']':
                            return false;
                        case '\\':
                            break;
                        default:
                            return true;
                    }
                }
            }
        }
        checkLenient();
        return false;
    }

    private int nextNonWhitespace(boolean z2) throws IOException {
        char[] cArr = this.buffer;
        int i3 = this.pos;
        int i4 = this.limit;
        while (true) {
            if (i3 == i4) {
                this.pos = i3;
                if (fillBuffer(1)) {
                    i3 = this.pos;
                    i4 = this.limit;
                } else if (!z2) {
                    return -1;
                } else {
                    throw new EOFException("End of input" + locationString());
                }
            }
            int i5 = i3 + 1;
            char c3 = cArr[i3];
            if (c3 == 10) {
                this.lineNumber++;
                this.lineStart = i5;
            } else if (!(c3 == ' ' || c3 == 13 || c3 == 9)) {
                if (c3 == '/') {
                    this.pos = i5;
                    if (i5 == i4) {
                        this.pos = i3;
                        boolean fillBuffer = fillBuffer(2);
                        this.pos++;
                        if (!fillBuffer) {
                            return c3;
                        }
                    }
                    checkLenient();
                    int i6 = this.pos;
                    char c4 = cArr[i6];
                    if (c4 == '*') {
                        this.pos = i6 + 1;
                        if (skipTo("*/")) {
                            i3 = this.pos + 2;
                            i4 = this.limit;
                        } else {
                            throw syntaxError("Unterminated comment");
                        }
                    } else if (c4 != '/') {
                        return c3;
                    } else {
                        this.pos = i6 + 1;
                        skipToEndOfLine();
                        i3 = this.pos;
                        i4 = this.limit;
                    }
                } else if (c3 == '#') {
                    this.pos = i5;
                    checkLenient();
                    skipToEndOfLine();
                    i3 = this.pos;
                    i4 = this.limit;
                } else {
                    this.pos = i5;
                    return c3;
                }
            }
            i3 = i5;
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:28:0x006c, code lost:
        if (r1 != null) goto L_0x007c;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:29:0x006e, code lost:
        r1 = new java.lang.StringBuilder(java.lang.Math.max((r2 - r3) * 2, 16));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:30:0x007c, code lost:
        r1.append(r0, r3, r2 - r3);
        r10.pos = r2;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private java.lang.String nextQuotedValue(char r11) throws java.io.IOException {
        /*
            r10 = this;
            char[] r0 = r10.buffer
            r1 = 0
        L_0x0003:
            int r2 = r10.pos
            int r3 = r10.limit
        L_0x0007:
            r4 = r3
            r3 = r2
        L_0x0009:
            r5 = 16
            r6 = 1
            if (r2 >= r4) goto L_0x006c
            int r7 = r2 + 1
            char r2 = r0[r2]
            com.google.gson.Strictness r8 = r10.strictness
            com.google.gson.Strictness r9 = com.google.gson.Strictness.STRICT
            if (r8 != r9) goto L_0x0024
            r8 = 32
            if (r2 < r8) goto L_0x001d
            goto L_0x0024
        L_0x001d:
            java.lang.String r11 = "Unescaped control characters (\\u0000-\\u001F) are not allowed in strict mode"
            com.google.gson.stream.MalformedJsonException r10 = r10.syntaxError(r11)
            throw r10
        L_0x0024:
            if (r2 != r11) goto L_0x003a
            r10.pos = r7
            int r7 = r7 - r3
            int r7 = r7 - r6
            if (r1 != 0) goto L_0x0032
            java.lang.String r10 = new java.lang.String
            r10.<init>(r0, r3, r7)
            return r10
        L_0x0032:
            r1.append(r0, r3, r7)
            java.lang.String r10 = r1.toString()
            return r10
        L_0x003a:
            r8 = 92
            if (r2 != r8) goto L_0x005f
            r10.pos = r7
            int r7 = r7 - r3
            int r2 = r7 + -1
            if (r1 != 0) goto L_0x0050
            int r7 = r7 * 2
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            int r4 = java.lang.Math.max(r7, r5)
            r1.<init>(r4)
        L_0x0050:
            r1.append(r0, r3, r2)
            char r2 = r10.readEscapeCharacter()
            r1.append(r2)
            int r2 = r10.pos
            int r3 = r10.limit
            goto L_0x0007
        L_0x005f:
            r5 = 10
            if (r2 != r5) goto L_0x006a
            int r2 = r10.lineNumber
            int r2 = r2 + r6
            r10.lineNumber = r2
            r10.lineStart = r7
        L_0x006a:
            r2 = r7
            goto L_0x0009
        L_0x006c:
            if (r1 != 0) goto L_0x007c
            int r1 = r2 - r3
            int r1 = r1 * 2
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            int r1 = java.lang.Math.max(r1, r5)
            r4.<init>(r1)
            r1 = r4
        L_0x007c:
            int r4 = r2 - r3
            r1.append(r0, r3, r4)
            r10.pos = r2
            boolean r2 = r10.fillBuffer(r6)
            if (r2 == 0) goto L_0x008b
            goto L_0x0003
        L_0x008b:
            java.lang.String r11 = "Unterminated string"
            com.google.gson.stream.MalformedJsonException r10 = r10.syntaxError(r11)
            throw r10
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.gson.stream.JsonReader.nextQuotedValue(char):java.lang.String");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:32:0x004a, code lost:
        checkLenient();
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private java.lang.String nextUnquotedValue() throws java.io.IOException {
        /*
            r6 = this;
            r0 = 0
            r1 = 0
        L_0x0002:
            r2 = r1
        L_0x0003:
            int r3 = r6.pos
            int r4 = r3 + r2
            int r5 = r6.limit
            if (r4 >= r5) goto L_0x004e
            char[] r4 = r6.buffer
            int r3 = r3 + r2
            char r3 = r4[r3]
            r4 = 9
            if (r3 == r4) goto L_0x005c
            r4 = 10
            if (r3 == r4) goto L_0x005c
            r4 = 12
            if (r3 == r4) goto L_0x005c
            r4 = 13
            if (r3 == r4) goto L_0x005c
            r4 = 32
            if (r3 == r4) goto L_0x005c
            r4 = 35
            if (r3 == r4) goto L_0x004a
            r4 = 44
            if (r3 == r4) goto L_0x005c
            r4 = 47
            if (r3 == r4) goto L_0x004a
            r4 = 61
            if (r3 == r4) goto L_0x004a
            r4 = 123(0x7b, float:1.72E-43)
            if (r3 == r4) goto L_0x005c
            r4 = 125(0x7d, float:1.75E-43)
            if (r3 == r4) goto L_0x005c
            r4 = 58
            if (r3 == r4) goto L_0x005c
            r4 = 59
            if (r3 == r4) goto L_0x004a
            switch(r3) {
                case 91: goto L_0x005c;
                case 92: goto L_0x004a;
                case 93: goto L_0x005c;
                default: goto L_0x0047;
            }
        L_0x0047:
            int r2 = r2 + 1
            goto L_0x0003
        L_0x004a:
            r6.checkLenient()
            goto L_0x005c
        L_0x004e:
            char[] r3 = r6.buffer
            int r3 = r3.length
            if (r2 >= r3) goto L_0x005e
            int r3 = r2 + 1
            boolean r3 = r6.fillBuffer(r3)
            if (r3 == 0) goto L_0x005c
            goto L_0x0003
        L_0x005c:
            r1 = r2
            goto L_0x007e
        L_0x005e:
            if (r0 != 0) goto L_0x006b
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r3 = 16
            int r3 = java.lang.Math.max(r2, r3)
            r0.<init>(r3)
        L_0x006b:
            char[] r3 = r6.buffer
            int r4 = r6.pos
            r0.append(r3, r4, r2)
            int r3 = r6.pos
            int r3 = r3 + r2
            r6.pos = r3
            r2 = 1
            boolean r2 = r6.fillBuffer(r2)
            if (r2 != 0) goto L_0x0002
        L_0x007e:
            if (r0 != 0) goto L_0x008a
            java.lang.String r0 = new java.lang.String
            char[] r2 = r6.buffer
            int r3 = r6.pos
            r0.<init>(r2, r3, r1)
            goto L_0x0095
        L_0x008a:
            char[] r2 = r6.buffer
            int r3 = r6.pos
            r0.append(r2, r3, r1)
            java.lang.String r0 = r0.toString()
        L_0x0095:
            int r2 = r6.pos
            int r2 = r2 + r1
            r6.pos = r2
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.gson.stream.JsonReader.nextUnquotedValue():java.lang.String");
    }

    private int peekKeyword() throws IOException {
        int i3;
        String str;
        String str2;
        char c3 = this.buffer[this.pos];
        if (c3 == 't' || c3 == 'T') {
            str2 = "true";
            str = "TRUE";
            i3 = 5;
        } else if (c3 == 'f' || c3 == 'F') {
            str2 = "false";
            str = "FALSE";
            i3 = 6;
        } else if (c3 != 'n' && c3 != 'N') {
            return 0;
        } else {
            str2 = AbstractJsonLexerKt.NULL;
            str = "NULL";
            i3 = 7;
        }
        boolean z2 = this.strictness != Strictness.STRICT;
        int length = str2.length();
        for (int i4 = 0; i4 < length; i4++) {
            if (this.pos + i4 >= this.limit && !fillBuffer(i4 + 1)) {
                return 0;
            }
            char c4 = this.buffer[this.pos + i4];
            if (c4 != str2.charAt(i4) && (!z2 || c4 != str.charAt(i4))) {
                return 0;
            }
        }
        if ((this.pos + length < this.limit || fillBuffer(length + 1)) && isLiteral(this.buffer[this.pos + length])) {
            return 0;
        }
        this.pos += length;
        this.peeked = i3;
        return i3;
    }

    private int peekNumber() throws IOException {
        char c3;
        char c4;
        char[] cArr = this.buffer;
        int i3 = this.pos;
        int i4 = this.limit;
        int i5 = 0;
        int i6 = 0;
        char c5 = 0;
        boolean z2 = false;
        boolean z3 = true;
        long j2 = 0;
        while (true) {
            if (i3 + i6 == i4) {
                if (i6 == cArr.length) {
                    return i5;
                }
                if (!fillBuffer(i6 + 1)) {
                    break;
                }
                i3 = this.pos;
                i4 = this.limit;
            }
            c3 = cArr[i3 + i6];
            if (c3 == '+') {
                c4 = 6;
                i5 = 0;
                if (c5 != 5) {
                    return 0;
                }
            } else if (c3 == 'E' || c3 == 'e') {
                i5 = 0;
                if (c5 != 2 && c5 != 4) {
                    return 0;
                }
                c5 = 5;
                i6++;
            } else if (c3 != '-') {
                c4 = 3;
                if (c3 == '.') {
                    i5 = 0;
                    if (c5 != 2) {
                        return 0;
                    }
                } else if (c3 >= '0' && c3 <= '9') {
                    if (c5 == 1 || c5 == 0) {
                        j2 = (long) (-(c3 - '0'));
                        c5 = 2;
                    } else if (c5 != 2) {
                        if (c5 == 3) {
                            i5 = 0;
                            c5 = 4;
                        } else if (c5 == 5 || c5 == 6) {
                            i5 = 0;
                            c5 = 7;
                        }
                        i6++;
                    } else if (j2 == 0) {
                        return 0;
                    } else {
                        long j3 = (10 * j2) - ((long) (c3 - '0'));
                        int i7 = (j2 > -922337203685477580L ? 1 : (j2 == -922337203685477580L ? 0 : -1));
                        z3 &= i7 > 0 || (i7 == 0 && j3 < j2);
                        j2 = j3;
                    }
                    i5 = 0;
                    i6++;
                }
            } else {
                c4 = 6;
                i5 = 0;
                if (c5 == 0) {
                    c5 = 1;
                    z2 = true;
                    i6++;
                } else if (c5 != 5) {
                    return 0;
                }
            }
            c5 = c4;
            i6++;
        }
        if (isLiteral(c3)) {
            return 0;
        }
        if (c5 == 2 && z3 && ((j2 != Long.MIN_VALUE || z2) && (j2 != 0 || !z2))) {
            if (!z2) {
                j2 = -j2;
            }
            this.peekedLong = j2;
            this.pos += i6;
            this.peeked = 15;
            return 15;
        } else if (c5 != 2 && c5 != 4 && c5 != 7) {
            return 0;
        } else {
            this.peekedNumberLength = i6;
            this.peeked = 16;
            return 16;
        }
    }

    private void push(int i3) throws MalformedJsonException {
        int i4 = this.stackSize;
        if (i4 - 1 < this.nestingLimit) {
            int[] iArr = this.stack;
            if (i4 == iArr.length) {
                int i5 = i4 * 2;
                this.stack = Arrays.copyOf(iArr, i5);
                this.pathIndices = Arrays.copyOf(this.pathIndices, i5);
                this.pathNames = (String[]) Arrays.copyOf(this.pathNames, i5);
            }
            int[] iArr2 = this.stack;
            int i6 = this.stackSize;
            this.stackSize = i6 + 1;
            iArr2[i6] = i3;
            return;
        }
        throw new MalformedJsonException("Nesting limit " + this.nestingLimit + " reached" + locationString());
    }

    private char readEscapeCharacter() throws IOException {
        int i3;
        if (this.pos != this.limit || fillBuffer(1)) {
            char[] cArr = this.buffer;
            int i4 = this.pos;
            int i5 = i4 + 1;
            this.pos = i5;
            char c3 = cArr[i4];
            if (c3 != 10) {
                if (c3 != '\"') {
                    if (c3 != '\'') {
                        if (!(c3 == '/' || c3 == '\\')) {
                            if (c3 == 'b') {
                                return 8;
                            }
                            if (c3 == 'f') {
                                return 12;
                            }
                            if (c3 == 'n') {
                                return 10;
                            }
                            if (c3 == 'r') {
                                return CharUtils.CR;
                            }
                            if (c3 == 't') {
                                return 9;
                            }
                            if (c3 != 'u') {
                                throw syntaxError("Invalid escape sequence");
                            } else if (i4 + 5 <= this.limit || fillBuffer(4)) {
                                int i6 = this.pos;
                                int i7 = i6 + 4;
                                int i8 = 0;
                                while (i6 < i7) {
                                    char[] cArr2 = this.buffer;
                                    char c4 = cArr2[i6];
                                    int i9 = i8 << 4;
                                    if (c4 >= '0' && c4 <= '9') {
                                        i3 = c4 - '0';
                                    } else if (c4 >= 'a' && c4 <= 'f') {
                                        i3 = c4 - 'W';
                                    } else if (c4 < 'A' || c4 > 'F') {
                                        throw syntaxError("Malformed Unicode escape \\u".concat(new String(cArr2, this.pos, 4)));
                                    } else {
                                        i3 = c4 - '7';
                                    }
                                    i8 = i3 + i9;
                                    i6++;
                                }
                                this.pos += 4;
                                return (char) i8;
                            } else {
                                throw syntaxError("Unterminated escape sequence");
                            }
                        }
                    }
                }
                return c3;
            } else if (this.strictness != Strictness.STRICT) {
                this.lineNumber++;
                this.lineStart = i5;
            } else {
                throw syntaxError("Cannot escape a newline character in strict mode");
            }
            if (this.strictness == Strictness.STRICT) {
                throw syntaxError("Invalid escaped character \"'\" in strict mode");
            }
            return c3;
        }
        throw syntaxError("Unterminated escape sequence");
    }

    private void skipQuotedValue(char c3) throws IOException {
        char[] cArr = this.buffer;
        do {
            int i3 = this.pos;
            int i4 = this.limit;
            while (i3 < i4) {
                int i5 = i3 + 1;
                char c4 = cArr[i3];
                if (c4 == c3) {
                    this.pos = i5;
                    return;
                } else if (c4 == '\\') {
                    this.pos = i5;
                    readEscapeCharacter();
                    i3 = this.pos;
                    i4 = this.limit;
                } else {
                    if (c4 == 10) {
                        this.lineNumber++;
                        this.lineStart = i5;
                    }
                    i3 = i5;
                }
            }
            this.pos = i3;
        } while (fillBuffer(1));
        throw syntaxError("Unterminated string");
    }

    private boolean skipTo(String str) throws IOException {
        int length = str.length();
        while (true) {
            int i3 = 0;
            if (this.pos + length > this.limit && !fillBuffer(length)) {
                return false;
            }
            char[] cArr = this.buffer;
            int i4 = this.pos;
            if (cArr[i4] == 10) {
                this.lineNumber++;
                this.lineStart = i4 + 1;
            } else {
                while (i3 < length) {
                    if (this.buffer[this.pos + i3] == str.charAt(i3)) {
                        i3++;
                    }
                }
                return true;
            }
            this.pos++;
        }
    }

    private void skipToEndOfLine() throws IOException {
        char c3;
        do {
            if (this.pos < this.limit || fillBuffer(1)) {
                char[] cArr = this.buffer;
                int i3 = this.pos;
                int i4 = i3 + 1;
                this.pos = i4;
                c3 = cArr[i3];
                if (c3 == 10) {
                    this.lineNumber++;
                    this.lineStart = i4;
                    return;
                }
            } else {
                return;
            }
        } while (c3 != 13);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:31:0x0048, code lost:
        checkLenient();
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void skipUnquotedValue() throws java.io.IOException {
        /*
            r4 = this;
        L_0x0000:
            r0 = 0
        L_0x0001:
            int r1 = r4.pos
            int r2 = r1 + r0
            int r3 = r4.limit
            if (r2 >= r3) goto L_0x0051
            char[] r2 = r4.buffer
            int r1 = r1 + r0
            char r1 = r2[r1]
            r2 = 9
            if (r1 == r2) goto L_0x004b
            r2 = 10
            if (r1 == r2) goto L_0x004b
            r2 = 12
            if (r1 == r2) goto L_0x004b
            r2 = 13
            if (r1 == r2) goto L_0x004b
            r2 = 32
            if (r1 == r2) goto L_0x004b
            r2 = 35
            if (r1 == r2) goto L_0x0048
            r2 = 44
            if (r1 == r2) goto L_0x004b
            r2 = 47
            if (r1 == r2) goto L_0x0048
            r2 = 61
            if (r1 == r2) goto L_0x0048
            r2 = 123(0x7b, float:1.72E-43)
            if (r1 == r2) goto L_0x004b
            r2 = 125(0x7d, float:1.75E-43)
            if (r1 == r2) goto L_0x004b
            r2 = 58
            if (r1 == r2) goto L_0x004b
            r2 = 59
            if (r1 == r2) goto L_0x0048
            switch(r1) {
                case 91: goto L_0x004b;
                case 92: goto L_0x0048;
                case 93: goto L_0x004b;
                default: goto L_0x0045;
            }
        L_0x0045:
            int r0 = r0 + 1
            goto L_0x0001
        L_0x0048:
            r4.checkLenient()
        L_0x004b:
            int r1 = r4.pos
            int r1 = r1 + r0
            r4.pos = r1
            return
        L_0x0051:
            int r1 = r1 + r0
            r4.pos = r1
            r0 = 1
            boolean r0 = r4.fillBuffer(r0)
            if (r0 != 0) goto L_0x0000
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.gson.stream.JsonReader.skipUnquotedValue():void");
    }

    private MalformedJsonException syntaxError(String str) throws MalformedJsonException {
        StringBuilder p2 = a.p(str);
        p2.append(locationString());
        p2.append("\nSee ");
        p2.append(TroubleshootingGuide.createUrl("malformed-json"));
        throw new MalformedJsonException(p2.toString());
    }

    /* access modifiers changed from: private */
    public IllegalStateException unexpectedTokenError(String str) throws IOException {
        String str2 = peek() == JsonToken.NULL ? "adapter-not-null-safe" : "unexpected-json-structure";
        StringBuilder w2 = android.support.v4.media.session.a.w("Expected ", str, " but was ");
        w2.append(peek());
        w2.append(locationString());
        w2.append("\nSee ");
        w2.append(TroubleshootingGuide.createUrl(str2));
        return new IllegalStateException(w2.toString());
    }

    public void beginArray() throws IOException {
        int i3 = this.peeked;
        if (i3 == 0) {
            i3 = doPeek();
        }
        if (i3 == 3) {
            push(1);
            this.pathIndices[this.stackSize - 1] = 0;
            this.peeked = 0;
            return;
        }
        throw unexpectedTokenError("BEGIN_ARRAY");
    }

    public void beginObject() throws IOException {
        int i3 = this.peeked;
        if (i3 == 0) {
            i3 = doPeek();
        }
        if (i3 == 1) {
            push(3);
            this.peeked = 0;
            return;
        }
        throw unexpectedTokenError("BEGIN_OBJECT");
    }

    public void close() throws IOException {
        this.peeked = 0;
        this.stack[0] = 8;
        this.stackSize = 1;
        this.in.close();
    }

    public int doPeek() throws IOException {
        int nextNonWhitespace;
        int[] iArr = this.stack;
        int i3 = this.stackSize;
        int i4 = iArr[i3 - 1];
        if (i4 == 1) {
            iArr[i3 - 1] = 2;
        } else if (i4 == 2) {
            int nextNonWhitespace2 = nextNonWhitespace(true);
            if (nextNonWhitespace2 != 44) {
                if (nextNonWhitespace2 == 59) {
                    checkLenient();
                } else if (nextNonWhitespace2 == 93) {
                    this.peeked = 4;
                    return 4;
                } else {
                    throw syntaxError("Unterminated array");
                }
            }
        } else if (i4 == 3 || i4 == 5) {
            iArr[i3 - 1] = 4;
            if (i4 == 5 && (nextNonWhitespace = nextNonWhitespace(true)) != 44) {
                if (nextNonWhitespace == 59) {
                    checkLenient();
                } else if (nextNonWhitespace == 125) {
                    this.peeked = 2;
                    return 2;
                } else {
                    throw syntaxError("Unterminated object");
                }
            }
            int nextNonWhitespace3 = nextNonWhitespace(true);
            if (nextNonWhitespace3 == 34) {
                this.peeked = 13;
                return 13;
            } else if (nextNonWhitespace3 == 39) {
                checkLenient();
                this.peeked = 12;
                return 12;
            } else if (nextNonWhitespace3 != 125) {
                checkLenient();
                this.pos--;
                if (isLiteral((char) nextNonWhitespace3)) {
                    this.peeked = 14;
                    return 14;
                }
                throw syntaxError("Expected name");
            } else if (i4 != 5) {
                this.peeked = 2;
                return 2;
            } else {
                throw syntaxError("Expected name");
            }
        } else if (i4 == 4) {
            iArr[i3 - 1] = 5;
            int nextNonWhitespace4 = nextNonWhitespace(true);
            if (nextNonWhitespace4 != 58) {
                if (nextNonWhitespace4 == 61) {
                    checkLenient();
                    if (this.pos < this.limit || fillBuffer(1)) {
                        char[] cArr = this.buffer;
                        int i5 = this.pos;
                        if (cArr[i5] == '>') {
                            this.pos = i5 + 1;
                        }
                    }
                } else {
                    throw syntaxError("Expected ':'");
                }
            }
        } else if (i4 == 6) {
            if (this.strictness == Strictness.LENIENT) {
                consumeNonExecutePrefix();
            }
            this.stack[this.stackSize - 1] = 7;
        } else if (i4 == 7) {
            if (nextNonWhitespace(false) == -1) {
                this.peeked = 17;
                return 17;
            }
            checkLenient();
            this.pos--;
        } else if (i4 == 8) {
            throw new IllegalStateException("JsonReader is closed");
        }
        int nextNonWhitespace5 = nextNonWhitespace(true);
        if (nextNonWhitespace5 == 34) {
            this.peeked = 9;
            return 9;
        } else if (nextNonWhitespace5 != 39) {
            if (!(nextNonWhitespace5 == 44 || nextNonWhitespace5 == 59)) {
                if (nextNonWhitespace5 == 91) {
                    this.peeked = 3;
                    return 3;
                } else if (nextNonWhitespace5 != 93) {
                    if (nextNonWhitespace5 != 123) {
                        this.pos--;
                        int peekKeyword = peekKeyword();
                        if (peekKeyword != 0) {
                            return peekKeyword;
                        }
                        int peekNumber = peekNumber();
                        if (peekNumber != 0) {
                            return peekNumber;
                        }
                        if (isLiteral(this.buffer[this.pos])) {
                            checkLenient();
                            this.peeked = 10;
                            return 10;
                        }
                        throw syntaxError("Expected value");
                    }
                    this.peeked = 1;
                    return 1;
                } else if (i4 == 1) {
                    this.peeked = 4;
                    return 4;
                }
            }
            if (i4 == 1 || i4 == 2) {
                checkLenient();
                this.pos--;
                this.peeked = 7;
                return 7;
            }
            throw syntaxError("Unexpected value");
        } else {
            checkLenient();
            this.peeked = 8;
            return 8;
        }
    }

    public void endArray() throws IOException {
        int i3 = this.peeked;
        if (i3 == 0) {
            i3 = doPeek();
        }
        if (i3 == 4) {
            int i4 = this.stackSize;
            this.stackSize = i4 - 1;
            int[] iArr = this.pathIndices;
            int i5 = i4 - 2;
            iArr[i5] = iArr[i5] + 1;
            this.peeked = 0;
            return;
        }
        throw unexpectedTokenError("END_ARRAY");
    }

    public void endObject() throws IOException {
        int i3 = this.peeked;
        if (i3 == 0) {
            i3 = doPeek();
        }
        if (i3 == 2) {
            int i4 = this.stackSize;
            int i5 = i4 - 1;
            this.stackSize = i5;
            this.pathNames[i5] = null;
            int[] iArr = this.pathIndices;
            int i6 = i4 - 2;
            iArr[i6] = iArr[i6] + 1;
            this.peeked = 0;
            return;
        }
        throw unexpectedTokenError("END_OBJECT");
    }

    public final int getNestingLimit() {
        return this.nestingLimit;
    }

    public String getPreviousPath() {
        return getPath(true);
    }

    public final Strictness getStrictness() {
        return this.strictness;
    }

    public boolean hasNext() throws IOException {
        int i3 = this.peeked;
        if (i3 == 0) {
            i3 = doPeek();
        }
        return (i3 == 2 || i3 == 4 || i3 == 17) ? false : true;
    }

    public final boolean isLenient() {
        return this.strictness == Strictness.LENIENT;
    }

    public String locationString() {
        StringBuilder k2 = C0118y.k(this.lineNumber + 1, (this.pos - this.lineStart) + 1, " at line ", " column ", " path ");
        k2.append(getPath());
        return k2.toString();
    }

    public boolean nextBoolean() throws IOException {
        int i3 = this.peeked;
        if (i3 == 0) {
            i3 = doPeek();
        }
        if (i3 == 5) {
            this.peeked = 0;
            int[] iArr = this.pathIndices;
            int i4 = this.stackSize - 1;
            iArr[i4] = iArr[i4] + 1;
            return true;
        } else if (i3 == 6) {
            this.peeked = 0;
            int[] iArr2 = this.pathIndices;
            int i5 = this.stackSize - 1;
            iArr2[i5] = iArr2[i5] + 1;
            return false;
        } else {
            throw unexpectedTokenError("a boolean");
        }
    }

    public double nextDouble() throws IOException {
        int i3 = this.peeked;
        if (i3 == 0) {
            i3 = doPeek();
        }
        if (i3 == 15) {
            this.peeked = 0;
            int[] iArr = this.pathIndices;
            int i4 = this.stackSize - 1;
            iArr[i4] = iArr[i4] + 1;
            return (double) this.peekedLong;
        }
        if (i3 == 16) {
            this.peekedString = new String(this.buffer, this.pos, this.peekedNumberLength);
            this.pos += this.peekedNumberLength;
        } else if (i3 == 8 || i3 == 9) {
            this.peekedString = nextQuotedValue(i3 == 8 ? '\'' : '\"');
        } else if (i3 == 10) {
            this.peekedString = nextUnquotedValue();
        } else if (i3 != 11) {
            throw unexpectedTokenError("a double");
        }
        this.peeked = 11;
        double parseDouble = Double.parseDouble(this.peekedString);
        if (this.strictness == Strictness.LENIENT || (!Double.isNaN(parseDouble) && !Double.isInfinite(parseDouble))) {
            this.peekedString = null;
            this.peeked = 0;
            int[] iArr2 = this.pathIndices;
            int i5 = this.stackSize - 1;
            iArr2[i5] = iArr2[i5] + 1;
            return parseDouble;
        }
        throw syntaxError("JSON forbids NaN and infinities: " + parseDouble);
    }

    public int nextInt() throws IOException {
        int i3 = this.peeked;
        if (i3 == 0) {
            i3 = doPeek();
        }
        if (i3 == 15) {
            long j2 = this.peekedLong;
            int i4 = (int) j2;
            if (j2 == ((long) i4)) {
                this.peeked = 0;
                int[] iArr = this.pathIndices;
                int i5 = this.stackSize - 1;
                iArr[i5] = iArr[i5] + 1;
                return i4;
            }
            throw new NumberFormatException("Expected an int but was " + this.peekedLong + locationString());
        }
        if (i3 == 16) {
            this.peekedString = new String(this.buffer, this.pos, this.peekedNumberLength);
            this.pos += this.peekedNumberLength;
        } else if (i3 == 8 || i3 == 9 || i3 == 10) {
            if (i3 == 10) {
                this.peekedString = nextUnquotedValue();
            } else {
                this.peekedString = nextQuotedValue(i3 == 8 ? '\'' : '\"');
            }
            try {
                int parseInt = Integer.parseInt(this.peekedString);
                this.peeked = 0;
                int[] iArr2 = this.pathIndices;
                int i6 = this.stackSize - 1;
                iArr2[i6] = iArr2[i6] + 1;
                return parseInt;
            } catch (NumberFormatException unused) {
            }
        } else {
            throw unexpectedTokenError("an int");
        }
        this.peeked = 11;
        double parseDouble = Double.parseDouble(this.peekedString);
        int i7 = (int) parseDouble;
        if (((double) i7) == parseDouble) {
            this.peekedString = null;
            this.peeked = 0;
            int[] iArr3 = this.pathIndices;
            int i8 = this.stackSize - 1;
            iArr3[i8] = iArr3[i8] + 1;
            return i7;
        }
        throw new NumberFormatException("Expected an int but was " + this.peekedString + locationString());
    }

    public long nextLong() throws IOException {
        int i3 = this.peeked;
        if (i3 == 0) {
            i3 = doPeek();
        }
        if (i3 == 15) {
            this.peeked = 0;
            int[] iArr = this.pathIndices;
            int i4 = this.stackSize - 1;
            iArr[i4] = iArr[i4] + 1;
            return this.peekedLong;
        }
        if (i3 == 16) {
            this.peekedString = new String(this.buffer, this.pos, this.peekedNumberLength);
            this.pos += this.peekedNumberLength;
        } else if (i3 == 8 || i3 == 9 || i3 == 10) {
            if (i3 == 10) {
                this.peekedString = nextUnquotedValue();
            } else {
                this.peekedString = nextQuotedValue(i3 == 8 ? '\'' : '\"');
            }
            try {
                long parseLong = Long.parseLong(this.peekedString);
                this.peeked = 0;
                int[] iArr2 = this.pathIndices;
                int i5 = this.stackSize - 1;
                iArr2[i5] = iArr2[i5] + 1;
                return parseLong;
            } catch (NumberFormatException unused) {
            }
        } else {
            throw unexpectedTokenError("a long");
        }
        this.peeked = 11;
        double parseDouble = Double.parseDouble(this.peekedString);
        long j2 = (long) parseDouble;
        if (((double) j2) == parseDouble) {
            this.peekedString = null;
            this.peeked = 0;
            int[] iArr3 = this.pathIndices;
            int i6 = this.stackSize - 1;
            iArr3[i6] = iArr3[i6] + 1;
            return j2;
        }
        throw new NumberFormatException("Expected a long but was " + this.peekedString + locationString());
    }

    public String nextName() throws IOException {
        String str;
        int i3 = this.peeked;
        if (i3 == 0) {
            i3 = doPeek();
        }
        if (i3 == 14) {
            str = nextUnquotedValue();
        } else if (i3 == 12) {
            str = nextQuotedValue('\'');
        } else if (i3 == 13) {
            str = nextQuotedValue('\"');
        } else {
            throw unexpectedTokenError("a name");
        }
        this.peeked = 0;
        this.pathNames[this.stackSize - 1] = str;
        return str;
    }

    public void nextNull() throws IOException {
        int i3 = this.peeked;
        if (i3 == 0) {
            i3 = doPeek();
        }
        if (i3 == 7) {
            this.peeked = 0;
            int[] iArr = this.pathIndices;
            int i4 = this.stackSize - 1;
            iArr[i4] = iArr[i4] + 1;
            return;
        }
        throw unexpectedTokenError(AbstractJsonLexerKt.NULL);
    }

    public String nextString() throws IOException {
        String str;
        int i3 = this.peeked;
        if (i3 == 0) {
            i3 = doPeek();
        }
        if (i3 == 10) {
            str = nextUnquotedValue();
        } else if (i3 == 8) {
            str = nextQuotedValue('\'');
        } else if (i3 == 9) {
            str = nextQuotedValue('\"');
        } else if (i3 == 11) {
            str = this.peekedString;
            this.peekedString = null;
        } else if (i3 == 15) {
            str = Long.toString(this.peekedLong);
        } else if (i3 == 16) {
            str = new String(this.buffer, this.pos, this.peekedNumberLength);
            this.pos += this.peekedNumberLength;
        } else {
            throw unexpectedTokenError("a string");
        }
        this.peeked = 0;
        int[] iArr = this.pathIndices;
        int i4 = this.stackSize - 1;
        iArr[i4] = iArr[i4] + 1;
        return str;
    }

    public JsonToken peek() throws IOException {
        int i3 = this.peeked;
        if (i3 == 0) {
            i3 = doPeek();
        }
        switch (i3) {
            case 1:
                return JsonToken.BEGIN_OBJECT;
            case 2:
                return JsonToken.END_OBJECT;
            case 3:
                return JsonToken.BEGIN_ARRAY;
            case 4:
                return JsonToken.END_ARRAY;
            case 5:
            case 6:
                return JsonToken.BOOLEAN;
            case 7:
                return JsonToken.NULL;
            case 8:
            case 9:
            case 10:
            case 11:
                return JsonToken.STRING;
            case 12:
            case 13:
            case 14:
                return JsonToken.NAME;
            case 15:
            case 16:
                return JsonToken.NUMBER;
            case 17:
                return JsonToken.END_DOCUMENT;
            default:
                throw new AssertionError();
        }
    }

    @Deprecated
    public final void setLenient(boolean z2) {
        setStrictness(z2 ? Strictness.LENIENT : Strictness.LEGACY_STRICT);
    }

    public final void setNestingLimit(int i3) {
        if (i3 >= 0) {
            this.nestingLimit = i3;
            return;
        }
        throw new IllegalArgumentException(a.k("Invalid nesting limit: ", i3));
    }

    public final void setStrictness(Strictness strictness2) {
        Objects.requireNonNull(strictness2);
        this.strictness = strictness2;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:21:0x0056, code lost:
        r1 = r1 - 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x005c, code lost:
        r1 = r1 + 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:28:0x0074, code lost:
        r7.peeked = 0;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void skipValue() throws java.io.IOException {
        /*
            r7 = this;
            r0 = 0
            r1 = r0
        L_0x0002:
            int r2 = r7.peeked
            if (r2 != 0) goto L_0x000a
            int r2 = r7.doPeek()
        L_0x000a:
            r3 = 39
            r4 = 34
            java.lang.String r5 = "<skipped>"
            r6 = 1
            switch(r2) {
                case 1: goto L_0x006f;
                case 2: goto L_0x005f;
                case 3: goto L_0x0059;
                case 4: goto L_0x0051;
                case 5: goto L_0x0014;
                case 6: goto L_0x0014;
                case 7: goto L_0x0014;
                case 8: goto L_0x004d;
                case 9: goto L_0x0049;
                case 10: goto L_0x0045;
                case 11: goto L_0x0014;
                case 12: goto L_0x0038;
                case 13: goto L_0x002b;
                case 14: goto L_0x001e;
                case 15: goto L_0x0014;
                case 16: goto L_0x0016;
                case 17: goto L_0x0015;
                default: goto L_0x0014;
            }
        L_0x0014:
            goto L_0x0074
        L_0x0015:
            return
        L_0x0016:
            int r2 = r7.pos
            int r3 = r7.peekedNumberLength
            int r2 = r2 + r3
            r7.pos = r2
            goto L_0x0074
        L_0x001e:
            r7.skipUnquotedValue()
            if (r1 != 0) goto L_0x0074
            java.lang.String[] r2 = r7.pathNames
            int r3 = r7.stackSize
            int r3 = r3 - r6
            r2[r3] = r5
            goto L_0x0074
        L_0x002b:
            r7.skipQuotedValue(r4)
            if (r1 != 0) goto L_0x0074
            java.lang.String[] r2 = r7.pathNames
            int r3 = r7.stackSize
            int r3 = r3 - r6
            r2[r3] = r5
            goto L_0x0074
        L_0x0038:
            r7.skipQuotedValue(r3)
            if (r1 != 0) goto L_0x0074
            java.lang.String[] r2 = r7.pathNames
            int r3 = r7.stackSize
            int r3 = r3 - r6
            r2[r3] = r5
            goto L_0x0074
        L_0x0045:
            r7.skipUnquotedValue()
            goto L_0x0074
        L_0x0049:
            r7.skipQuotedValue(r4)
            goto L_0x0074
        L_0x004d:
            r7.skipQuotedValue(r3)
            goto L_0x0074
        L_0x0051:
            int r2 = r7.stackSize
            int r2 = r2 - r6
            r7.stackSize = r2
        L_0x0056:
            int r1 = r1 + -1
            goto L_0x0074
        L_0x0059:
            r7.push(r6)
        L_0x005c:
            int r1 = r1 + 1
            goto L_0x0074
        L_0x005f:
            if (r1 != 0) goto L_0x0069
            java.lang.String[] r2 = r7.pathNames
            int r3 = r7.stackSize
            int r3 = r3 - r6
            r4 = 0
            r2[r3] = r4
        L_0x0069:
            int r2 = r7.stackSize
            int r2 = r2 - r6
            r7.stackSize = r2
            goto L_0x0056
        L_0x006f:
            r2 = 3
            r7.push(r2)
            goto L_0x005c
        L_0x0074:
            r7.peeked = r0
            if (r1 > 0) goto L_0x0002
            int[] r0 = r7.pathIndices
            int r7 = r7.stackSize
            int r7 = r7 - r6
            r1 = r0[r7]
            int r1 = r1 + r6
            r0[r7] = r1
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.gson.stream.JsonReader.skipValue():void");
    }

    public String toString() {
        return getClass().getSimpleName() + locationString();
    }

    public String getPath() {
        return getPath(false);
    }
}
