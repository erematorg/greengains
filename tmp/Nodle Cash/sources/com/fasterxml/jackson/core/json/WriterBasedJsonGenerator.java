package com.fasterxml.jackson.core.json;

import com.fasterxml.jackson.core.Base64Variant;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonStreamContext;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.core.PrettyPrinter;
import com.fasterxml.jackson.core.SerializableString;
import com.fasterxml.jackson.core.io.CharTypes;
import com.fasterxml.jackson.core.io.CharacterEscapes;
import com.fasterxml.jackson.core.io.IOContext;
import com.fasterxml.jackson.core.io.NumberOutput;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.io.Writer;
import java.math.BigDecimal;
import java.math.BigInteger;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;

public class WriterBasedJsonGenerator extends JsonGeneratorImpl {
    protected static final char[] HEX_CHARS_LOWER = CharTypes.copyHexChars(false);
    protected static final char[] HEX_CHARS_UPPER = CharTypes.copyHexChars(true);
    protected static final int SHORT_WRITE = 32;
    protected char[] _copyBuffer;
    protected SerializableString _currentEscape;
    protected char[] _entityBuffer;
    protected char[] _outputBuffer;
    protected int _outputEnd;
    protected int _outputHead;
    protected int _outputTail;
    protected char _quoteChar;
    protected final Writer _writer;

    @Deprecated
    public WriterBasedJsonGenerator(IOContext iOContext, int i3, ObjectCodec objectCodec, Writer writer) {
        this(iOContext, i3, objectCodec, writer, '\"');
    }

    private char[] _allocateCopyBuffer() {
        if (this._copyBuffer == null) {
            this._copyBuffer = this._ioContext.allocNameCopyBuffer(2000);
        }
        return this._copyBuffer;
    }

    private char[] _allocateEntityBuffer() {
        char[] cArr = new char[14];
        cArr[0] = AbstractJsonLexerKt.STRING_ESC;
        cArr[2] = AbstractJsonLexerKt.STRING_ESC;
        cArr[3] = AbstractJsonLexerKt.UNICODE_ESC;
        cArr[4] = '0';
        cArr[5] = '0';
        cArr[8] = AbstractJsonLexerKt.STRING_ESC;
        cArr[9] = AbstractJsonLexerKt.UNICODE_ESC;
        this._entityBuffer = cArr;
        return cArr;
    }

    private void _appendCharacterEscape(char c3, int i3) throws IOException, JsonGenerationException {
        String str;
        int i4;
        if (i3 >= 0) {
            if (this._outputTail + 2 > this._outputEnd) {
                _flushBuffer();
            }
            char[] cArr = this._outputBuffer;
            int i5 = this._outputTail;
            int i6 = i5 + 1;
            this._outputTail = i6;
            cArr[i5] = AbstractJsonLexerKt.STRING_ESC;
            this._outputTail = i5 + 2;
            cArr[i6] = (char) i3;
        } else if (i3 != -2) {
            if (this._outputTail + 5 >= this._outputEnd) {
                _flushBuffer();
            }
            int i7 = this._outputTail;
            char[] cArr2 = this._outputBuffer;
            char[] hexChars = getHexChars();
            cArr2[i7] = AbstractJsonLexerKt.STRING_ESC;
            int i8 = i7 + 2;
            cArr2[i7 + 1] = AbstractJsonLexerKt.UNICODE_ESC;
            if (c3 > 255) {
                int i9 = c3 >> 8;
                int i10 = i7 + 3;
                cArr2[i8] = hexChars[(i9 & 255) >> 4];
                i4 = i7 + 4;
                cArr2[i10] = hexChars[i9 & 15];
                c3 = (char) (c3 & 255);
            } else {
                int i11 = i7 + 3;
                cArr2[i8] = '0';
                i4 = i7 + 4;
                cArr2[i11] = '0';
            }
            cArr2[i4] = hexChars[c3 >> 4];
            cArr2[i4 + 1] = hexChars[c3 & 15];
            this._outputTail = i4 + 2;
        } else {
            SerializableString serializableString = this._currentEscape;
            if (serializableString == null) {
                str = this._characterEscapes.getEscapeSequence(c3).getValue();
            } else {
                str = serializableString.getValue();
                this._currentEscape = null;
            }
            int length = str.length();
            if (this._outputTail + length > this._outputEnd) {
                _flushBuffer();
                if (length > this._outputEnd) {
                    this._writer.write(str);
                    return;
                }
            }
            str.getChars(0, length, this._outputBuffer, this._outputTail);
            this._outputTail += length;
        }
    }

    private void _prependOrWriteCharacterEscape(char c3, int i3) throws IOException, JsonGenerationException {
        String str;
        int i4;
        if (i3 >= 0) {
            int i5 = this._outputTail;
            if (i5 >= 2) {
                int i6 = i5 - 2;
                this._outputHead = i6;
                char[] cArr = this._outputBuffer;
                cArr[i6] = AbstractJsonLexerKt.STRING_ESC;
                cArr[i5 - 1] = (char) i3;
                return;
            }
            char[] cArr2 = this._entityBuffer;
            if (cArr2 == null) {
                cArr2 = _allocateEntityBuffer();
            }
            this._outputHead = this._outputTail;
            cArr2[1] = (char) i3;
            this._writer.write(cArr2, 0, 2);
        } else if (i3 != -2) {
            char[] hexChars = getHexChars();
            int i7 = this._outputTail;
            if (i7 >= 6) {
                char[] cArr3 = this._outputBuffer;
                int i8 = i7 - 6;
                this._outputHead = i8;
                cArr3[i8] = AbstractJsonLexerKt.STRING_ESC;
                cArr3[i7 - 5] = AbstractJsonLexerKt.UNICODE_ESC;
                if (c3 > 255) {
                    int i9 = c3 >> 8;
                    cArr3[i7 - 4] = hexChars[(i9 & 255) >> 4];
                    i4 = i7 - 3;
                    cArr3[i4] = hexChars[i9 & 15];
                    c3 = (char) (c3 & 255);
                } else {
                    cArr3[i7 - 4] = '0';
                    i4 = i7 - 3;
                    cArr3[i4] = '0';
                }
                cArr3[i4 + 1] = hexChars[c3 >> 4];
                cArr3[i4 + 2] = hexChars[c3 & 15];
                return;
            }
            char[] cArr4 = this._entityBuffer;
            if (cArr4 == null) {
                cArr4 = _allocateEntityBuffer();
            }
            this._outputHead = this._outputTail;
            if (c3 > 255) {
                int i10 = c3 >> 8;
                cArr4[10] = hexChars[(i10 & 255) >> 4];
                cArr4[11] = hexChars[i10 & 15];
                cArr4[12] = hexChars[(c3 & 255) >> 4];
                cArr4[13] = hexChars[c3 & 15];
                this._writer.write(cArr4, 8, 6);
                return;
            }
            cArr4[6] = hexChars[c3 >> 4];
            cArr4[7] = hexChars[c3 & 15];
            this._writer.write(cArr4, 2, 6);
        } else {
            SerializableString serializableString = this._currentEscape;
            if (serializableString == null) {
                str = this._characterEscapes.getEscapeSequence(c3).getValue();
            } else {
                str = serializableString.getValue();
                this._currentEscape = null;
            }
            int length = str.length();
            int i11 = this._outputTail;
            if (i11 >= length) {
                int i12 = i11 - length;
                this._outputHead = i12;
                str.getChars(0, length, this._outputBuffer, i12);
                return;
            }
            this._outputHead = i11;
            this._writer.write(str);
        }
    }

    private int _readMore(InputStream inputStream, byte[] bArr, int i3, int i4, int i5) throws IOException {
        int i6 = 0;
        while (i3 < i4) {
            bArr[i6] = bArr[i3];
            i6++;
            i3++;
        }
        int min = Math.min(i5, bArr.length);
        do {
            int i7 = min - i6;
            if (i7 == 0) {
                break;
            }
            int read = inputStream.read(bArr, i6, i7);
            if (read < 0) {
                return i6;
            }
            i6 += read;
        } while (i6 < 3);
        return i6;
    }

    private final void _writeFieldNameTail(SerializableString serializableString) throws IOException {
        char[] asQuotedChars = serializableString.asQuotedChars();
        writeRaw(asQuotedChars, 0, asQuotedChars.length);
        if (this._outputTail >= this._outputEnd) {
            _flushBuffer();
        }
        char[] cArr = this._outputBuffer;
        int i3 = this._outputTail;
        this._outputTail = i3 + 1;
        cArr[i3] = this._quoteChar;
    }

    private void _writeLongString(String str) throws IOException {
        _flushBuffer();
        int length = str.length();
        int i3 = 0;
        while (true) {
            int i4 = this._outputEnd;
            if (i3 + i4 > length) {
                i4 = length - i3;
            }
            int i5 = i3 + i4;
            str.getChars(i3, i5, this._outputBuffer, 0);
            if (this._characterEscapes != null) {
                _writeSegmentCustom(i4);
            } else {
                int i6 = this._maximumNonEscapedChar;
                if (i6 != 0) {
                    _writeSegmentASCII(i4, i6);
                } else {
                    _writeSegment(i4);
                }
            }
            if (i5 < length) {
                i3 = i5;
            } else {
                return;
            }
        }
    }

    private final void _writeNull() throws IOException {
        if (this._outputTail + 4 >= this._outputEnd) {
            _flushBuffer();
        }
        int i3 = this._outputTail;
        char[] cArr = this._outputBuffer;
        cArr[i3] = 'n';
        cArr[i3 + 1] = AbstractJsonLexerKt.UNICODE_ESC;
        cArr[i3 + 2] = 'l';
        cArr[i3 + 3] = 'l';
        this._outputTail = i3 + 4;
    }

    private void _writeQuotedInt(int i3) throws IOException {
        if (this._outputTail + 13 >= this._outputEnd) {
            _flushBuffer();
        }
        char[] cArr = this._outputBuffer;
        int i4 = this._outputTail;
        int i5 = i4 + 1;
        this._outputTail = i5;
        cArr[i4] = this._quoteChar;
        int outputInt = NumberOutput.outputInt(i3, cArr, i5);
        char[] cArr2 = this._outputBuffer;
        this._outputTail = outputInt + 1;
        cArr2[outputInt] = this._quoteChar;
    }

    private void _writeQuotedLong(long j2) throws IOException {
        if (this._outputTail + 23 >= this._outputEnd) {
            _flushBuffer();
        }
        char[] cArr = this._outputBuffer;
        int i3 = this._outputTail;
        int i4 = i3 + 1;
        this._outputTail = i4;
        cArr[i3] = this._quoteChar;
        int outputLong = NumberOutput.outputLong(j2, cArr, i4);
        char[] cArr2 = this._outputBuffer;
        this._outputTail = outputLong + 1;
        cArr2[outputLong] = this._quoteChar;
    }

    private void _writeQuotedRaw(String str) throws IOException {
        if (this._outputTail >= this._outputEnd) {
            _flushBuffer();
        }
        char[] cArr = this._outputBuffer;
        int i3 = this._outputTail;
        this._outputTail = i3 + 1;
        cArr[i3] = this._quoteChar;
        writeRaw(str);
        if (this._outputTail >= this._outputEnd) {
            _flushBuffer();
        }
        char[] cArr2 = this._outputBuffer;
        int i4 = this._outputTail;
        this._outputTail = i4 + 1;
        cArr2[i4] = this._quoteChar;
    }

    private void _writeQuotedShort(short s3) throws IOException {
        if (this._outputTail + 8 >= this._outputEnd) {
            _flushBuffer();
        }
        char[] cArr = this._outputBuffer;
        int i3 = this._outputTail;
        int i4 = i3 + 1;
        this._outputTail = i4;
        cArr[i3] = this._quoteChar;
        int outputInt = NumberOutput.outputInt((int) s3, cArr, i4);
        char[] cArr2 = this._outputBuffer;
        this._outputTail = outputInt + 1;
        cArr2[outputInt] = this._quoteChar;
    }

    private void _writeSegment(int i3) throws IOException {
        char[] cArr;
        char c3;
        int[] iArr = this._outputEscapes;
        int length = iArr.length;
        int i4 = 0;
        int i5 = 0;
        while (i4 < i3) {
            do {
                cArr = this._outputBuffer;
                c3 = cArr[i4];
                if ((c3 < length && iArr[c3] != 0) || (i4 = i4 + 1) >= i3) {
                    int i6 = i4 - i5;
                }
                cArr = this._outputBuffer;
                c3 = cArr[i4];
                break;
            } while ((i4 = i4 + 1) >= i3);
            int i62 = i4 - i5;
            if (i62 > 0) {
                this._writer.write(cArr, i5, i62);
                if (i4 >= i3) {
                    return;
                }
            }
            i4++;
            i5 = _prependOrWriteCharacterEscape(this._outputBuffer, i4, i3, c3, iArr[c3]);
        }
    }

    private void _writeSegmentASCII(int i3, int i4) throws IOException, JsonGenerationException {
        char[] cArr;
        char c3;
        int[] iArr = this._outputEscapes;
        int min = Math.min(iArr.length, i4 + 1);
        int i5 = 0;
        int i6 = 0;
        int i7 = 0;
        while (i5 < i3) {
            while (true) {
                cArr = this._outputBuffer;
                c3 = cArr[i5];
                if (c3 >= min) {
                    if (c3 > i4) {
                        i7 = -1;
                        break;
                    }
                } else {
                    i7 = iArr[c3];
                    if (i7 != 0) {
                        break;
                    }
                }
                i5++;
                if (i5 >= i3) {
                    break;
                }
            }
            int i8 = i5 - i6;
            if (i8 > 0) {
                this._writer.write(cArr, i6, i8);
                if (i5 >= i3) {
                    return;
                }
            }
            i5++;
            i6 = _prependOrWriteCharacterEscape(this._outputBuffer, i5, i3, c3, i7);
        }
    }

    private void _writeSegmentCustom(int i3) throws IOException, JsonGenerationException {
        char c3;
        int[] iArr = this._outputEscapes;
        int i4 = this._maximumNonEscapedChar;
        if (i4 < 1) {
            i4 = 65535;
        }
        int min = Math.min(iArr.length, i4 + 1);
        CharacterEscapes characterEscapes = this._characterEscapes;
        int i5 = 0;
        int i6 = 0;
        int i7 = 0;
        while (i5 < i3) {
            while (true) {
                c3 = this._outputBuffer[i5];
                if (c3 >= min) {
                    if (c3 <= i4) {
                        SerializableString escapeSequence = characterEscapes.getEscapeSequence(c3);
                        this._currentEscape = escapeSequence;
                        if (escapeSequence != null) {
                            i7 = -2;
                            break;
                        }
                    } else {
                        i7 = -1;
                        break;
                    }
                } else {
                    i7 = iArr[c3];
                    if (i7 != 0) {
                        break;
                    }
                }
                i5++;
                if (i5 >= i3) {
                    break;
                }
            }
            int i8 = i5 - i6;
            if (i8 > 0) {
                this._writer.write(this._outputBuffer, i6, i8);
                if (i5 >= i3) {
                    return;
                }
            }
            i5++;
            i6 = _prependOrWriteCharacterEscape(this._outputBuffer, i5, i3, c3, i7);
        }
    }

    private void _writeString(String str) throws IOException {
        int length = str.length();
        int i3 = this._outputEnd;
        if (length > i3) {
            _writeLongString(str);
            return;
        }
        if (this._outputTail + length > i3) {
            _flushBuffer();
        }
        str.getChars(0, length, this._outputBuffer, this._outputTail);
        if (this._characterEscapes != null) {
            _writeStringCustom(length);
            return;
        }
        int i4 = this._maximumNonEscapedChar;
        if (i4 != 0) {
            _writeStringASCII(length, i4);
        } else {
            _writeString2(length);
        }
    }

    private void _writeString2(SerializableString serializableString) throws IOException {
        char[] asQuotedChars = serializableString.asQuotedChars();
        int length = asQuotedChars.length;
        if (length < 32) {
            if (length > this._outputEnd - this._outputTail) {
                _flushBuffer();
            }
            System.arraycopy(asQuotedChars, 0, this._outputBuffer, this._outputTail, length);
            this._outputTail += length;
        } else {
            _flushBuffer();
            this._writer.write(asQuotedChars, 0, length);
        }
        if (this._outputTail >= this._outputEnd) {
            _flushBuffer();
        }
        char[] cArr = this._outputBuffer;
        int i3 = this._outputTail;
        this._outputTail = i3 + 1;
        cArr[i3] = this._quoteChar;
    }

    /* JADX WARNING: Removed duplicated region for block: B:16:0x003a A[SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void _writeStringASCII(int r9, int r10) throws java.io.IOException, com.fasterxml.jackson.core.JsonGenerationException {
        /*
            r8 = this;
            int r0 = r8._outputTail
            int r0 = r0 + r9
            int[] r9 = r8._outputEscapes
            int r1 = r9.length
            int r2 = r10 + 1
            int r1 = java.lang.Math.min(r1, r2)
        L_0x000c:
            int r2 = r8._outputTail
            if (r2 >= r0) goto L_0x003a
        L_0x0010:
            char[] r2 = r8._outputBuffer
            int r3 = r8._outputTail
            char r4 = r2[r3]
            if (r4 >= r1) goto L_0x001d
            r5 = r9[r4]
            if (r5 == 0) goto L_0x0034
            goto L_0x0020
        L_0x001d:
            if (r4 <= r10) goto L_0x0034
            r5 = -1
        L_0x0020:
            int r6 = r8._outputHead
            int r3 = r3 - r6
            if (r3 <= 0) goto L_0x002a
            java.io.Writer r7 = r8._writer
            r7.write(r2, r6, r3)
        L_0x002a:
            int r2 = r8._outputTail
            int r2 = r2 + 1
            r8._outputTail = r2
            r8._prependOrWriteCharacterEscape(r4, r5)
            goto L_0x000c
        L_0x0034:
            int r3 = r3 + 1
            r8._outputTail = r3
            if (r3 < r0) goto L_0x0010
        L_0x003a:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.fasterxml.jackson.core.json.WriterBasedJsonGenerator._writeStringASCII(int, int):void");
    }

    /* JADX WARNING: Removed duplicated region for block: B:21:0x0052 A[SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void _writeStringCustom(int r12) throws java.io.IOException, com.fasterxml.jackson.core.JsonGenerationException {
        /*
            r11 = this;
            int r0 = r11._outputTail
            int r0 = r0 + r12
            int[] r12 = r11._outputEscapes
            int r1 = r11._maximumNonEscapedChar
            r2 = 1
            if (r1 >= r2) goto L_0x000d
            r1 = 65535(0xffff, float:9.1834E-41)
        L_0x000d:
            int r3 = r12.length
            int r4 = r1 + 1
            int r3 = java.lang.Math.min(r3, r4)
            com.fasterxml.jackson.core.io.CharacterEscapes r4 = r11._characterEscapes
        L_0x0016:
            int r5 = r11._outputTail
            if (r5 >= r0) goto L_0x0052
        L_0x001a:
            char[] r5 = r11._outputBuffer
            int r6 = r11._outputTail
            char r5 = r5[r6]
            if (r5 >= r3) goto L_0x0027
            r6 = r12[r5]
            if (r6 == 0) goto L_0x004b
            goto L_0x0034
        L_0x0027:
            if (r5 <= r1) goto L_0x002b
            r6 = -1
            goto L_0x0034
        L_0x002b:
            com.fasterxml.jackson.core.SerializableString r6 = r4.getEscapeSequence(r5)
            r11._currentEscape = r6
            if (r6 == 0) goto L_0x004b
            r6 = -2
        L_0x0034:
            int r7 = r11._outputTail
            int r8 = r11._outputHead
            int r7 = r7 - r8
            if (r7 <= 0) goto L_0x0042
            java.io.Writer r9 = r11._writer
            char[] r10 = r11._outputBuffer
            r9.write(r10, r8, r7)
        L_0x0042:
            int r7 = r11._outputTail
            int r7 = r7 + r2
            r11._outputTail = r7
            r11._prependOrWriteCharacterEscape(r5, r6)
            goto L_0x0016
        L_0x004b:
            int r5 = r11._outputTail
            int r5 = r5 + r2
            r11._outputTail = r5
            if (r5 < r0) goto L_0x001a
        L_0x0052:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.fasterxml.jackson.core.json.WriterBasedJsonGenerator._writeStringCustom(int):void");
    }

    private char[] getHexChars() {
        return this._cfgWriteHexUppercase ? HEX_CHARS_UPPER : HEX_CHARS_LOWER;
    }

    private void writeRawLong(String str) throws IOException {
        int i3 = this._outputEnd;
        int i4 = this._outputTail;
        int i5 = i3 - i4;
        str.getChars(0, i5, this._outputBuffer, i4);
        this._outputTail += i5;
        _flushBuffer();
        int length = str.length() - i5;
        while (true) {
            int i6 = this._outputEnd;
            if (length > i6) {
                int i7 = i5 + i6;
                str.getChars(i5, i7, this._outputBuffer, 0);
                this._outputHead = 0;
                this._outputTail = i6;
                _flushBuffer();
                length -= i6;
                i5 = i7;
            } else {
                str.getChars(i5, i5 + length, this._outputBuffer, 0);
                this._outputHead = 0;
                this._outputTail = length;
                return;
            }
        }
    }

    public void _flushBuffer() throws IOException {
        int i3 = this._outputTail;
        int i4 = this._outputHead;
        int i5 = i3 - i4;
        if (i5 > 0) {
            this._outputHead = 0;
            this._outputTail = 0;
            this._writer.write(this._outputBuffer, i4, i5);
        }
    }

    public void _releaseBuffers() {
        char[] cArr = this._outputBuffer;
        if (cArr != null) {
            this._outputBuffer = null;
            this._ioContext.releaseConcatBuffer(cArr);
        }
        char[] cArr2 = this._copyBuffer;
        if (cArr2 != null) {
            this._copyBuffer = null;
            this._ioContext.releaseNameCopyBuffer(cArr2);
        }
    }

    public final void _verifyValueWrite(String str) throws IOException {
        char c3;
        int writeValue = this._writeContext.writeValue();
        if (this._cfgPrettyPrinter != null) {
            _verifyPrettyValueWrite(str, writeValue);
            return;
        }
        if (writeValue == 1) {
            c3 = AbstractJsonLexerKt.COMMA;
        } else if (writeValue == 2) {
            c3 = AbstractJsonLexerKt.COLON;
        } else if (writeValue == 3) {
            SerializableString serializableString = this._rootValueSeparator;
            if (serializableString != null) {
                writeRaw(serializableString.getValue());
                return;
            }
            return;
        } else if (writeValue == 5) {
            _reportCantWriteValueExpectName(str);
            return;
        } else {
            return;
        }
        if (this._outputTail >= this._outputEnd) {
            _flushBuffer();
        }
        char[] cArr = this._outputBuffer;
        int i3 = this._outputTail;
        this._outputTail = i3 + 1;
        cArr[i3] = c3;
    }

    public final void _writeBinary(Base64Variant base64Variant, byte[] bArr, int i3, int i4) throws IOException, JsonGenerationException {
        int i5 = i4 - 3;
        int i6 = this._outputEnd - 6;
        int maxLineLength = base64Variant.getMaxLineLength();
        loop0:
        while (true) {
            int i7 = maxLineLength >> 2;
            while (i3 <= i5) {
                if (this._outputTail > i6) {
                    _flushBuffer();
                }
                int i8 = i3 + 2;
                byte b3 = bArr[i3 + 1] & 255;
                i3 += 3;
                int encodeBase64Chunk = base64Variant.encodeBase64Chunk((int) ((b3 | (bArr[i3] << 8)) << 8) | (bArr[i8] & 255), this._outputBuffer, this._outputTail);
                this._outputTail = encodeBase64Chunk;
                i7--;
                if (i7 <= 0) {
                    char[] cArr = this._outputBuffer;
                    int i9 = encodeBase64Chunk + 1;
                    this._outputTail = i9;
                    cArr[encodeBase64Chunk] = AbstractJsonLexerKt.STRING_ESC;
                    this._outputTail = encodeBase64Chunk + 2;
                    cArr[i9] = 'n';
                    maxLineLength = base64Variant.getMaxLineLength();
                }
            }
            break loop0;
        }
        int i10 = i4 - i3;
        if (i10 > 0) {
            if (this._outputTail > i6) {
                _flushBuffer();
            }
            int i11 = i3 + 1;
            int i12 = bArr[i3] << 16;
            if (i10 == 2) {
                i12 |= (bArr[i11] & 255) << 8;
            }
            this._outputTail = base64Variant.encodeBase64Partial(i12, i10, this._outputBuffer, this._outputTail);
        }
    }

    public final void _writeFieldName(String str, boolean z2) throws IOException {
        if (this._cfgPrettyPrinter != null) {
            _writePPFieldName(str, z2);
            return;
        }
        if (this._outputTail + 1 >= this._outputEnd) {
            _flushBuffer();
        }
        if (z2) {
            char[] cArr = this._outputBuffer;
            int i3 = this._outputTail;
            this._outputTail = i3 + 1;
            cArr[i3] = AbstractJsonLexerKt.COMMA;
        }
        if (this._cfgUnqNames) {
            _writeString(str);
            return;
        }
        char[] cArr2 = this._outputBuffer;
        int i4 = this._outputTail;
        this._outputTail = i4 + 1;
        cArr2[i4] = this._quoteChar;
        _writeString(str);
        if (this._outputTail >= this._outputEnd) {
            _flushBuffer();
        }
        char[] cArr3 = this._outputBuffer;
        int i5 = this._outputTail;
        this._outputTail = i5 + 1;
        cArr3[i5] = this._quoteChar;
    }

    public final void _writePPFieldName(String str, boolean z2) throws IOException {
        if (z2) {
            this._cfgPrettyPrinter.writeObjectEntrySeparator(this);
        } else {
            this._cfgPrettyPrinter.beforeObjectEntries(this);
        }
        if (this._cfgUnqNames) {
            _writeString(str);
            return;
        }
        if (this._outputTail >= this._outputEnd) {
            _flushBuffer();
        }
        char[] cArr = this._outputBuffer;
        int i3 = this._outputTail;
        this._outputTail = i3 + 1;
        cArr[i3] = this._quoteChar;
        _writeString(str);
        if (this._outputTail >= this._outputEnd) {
            _flushBuffer();
        }
        char[] cArr2 = this._outputBuffer;
        int i4 = this._outputTail;
        this._outputTail = i4 + 1;
        cArr2[i4] = this._quoteChar;
    }

    public boolean canWriteFormattedNumbers() {
        return true;
    }

    public void close() throws IOException {
        super.close();
        try {
            if (this._outputBuffer != null && isEnabled(JsonGenerator.Feature.AUTO_CLOSE_JSON_CONTENT)) {
                while (true) {
                    JsonStreamContext outputContext = getOutputContext();
                    if (!outputContext.inArray()) {
                        if (!outputContext.inObject()) {
                            break;
                        }
                        writeEndObject();
                    } else {
                        writeEndArray();
                    }
                }
            }
            _flushBuffer();
            e = null;
        } catch (IOException e3) {
            e = e3;
        }
        this._outputHead = 0;
        this._outputTail = 0;
        if (this._writer != null) {
            try {
                if (!this._ioContext.isResourceManaged()) {
                    if (!isEnabled(JsonGenerator.Feature.AUTO_CLOSE_TARGET)) {
                        if (isEnabled(JsonGenerator.Feature.FLUSH_PASSED_TO_STREAM)) {
                            this._writer.flush();
                        }
                    }
                }
                this._writer.close();
            } catch (IOException | RuntimeException e4) {
                if (e != null) {
                    e4.addSuppressed(e);
                }
                throw e4;
            }
        }
        _releaseBuffers();
        if (e != null) {
            throw e;
        }
    }

    public void flush() throws IOException {
        _flushBuffer();
        if (this._writer != null && isEnabled(JsonGenerator.Feature.FLUSH_PASSED_TO_STREAM)) {
            this._writer.flush();
        }
    }

    public int getOutputBuffered() {
        return Math.max(0, this._outputTail - this._outputHead);
    }

    public Object getOutputTarget() {
        return this._writer;
    }

    public void writeBinary(Base64Variant base64Variant, byte[] bArr, int i3, int i4) throws IOException, JsonGenerationException {
        _checkRangeBoundsForByteArray(bArr, i3, i4);
        _verifyValueWrite("write a binary value");
        if (this._outputTail >= this._outputEnd) {
            _flushBuffer();
        }
        char[] cArr = this._outputBuffer;
        int i5 = this._outputTail;
        this._outputTail = i5 + 1;
        cArr[i5] = this._quoteChar;
        _writeBinary(base64Variant, bArr, i3, i4 + i3);
        if (this._outputTail >= this._outputEnd) {
            _flushBuffer();
        }
        char[] cArr2 = this._outputBuffer;
        int i6 = this._outputTail;
        this._outputTail = i6 + 1;
        cArr2[i6] = this._quoteChar;
    }

    public void writeBoolean(boolean z2) throws IOException {
        int i3;
        _verifyValueWrite("write a boolean value");
        if (this._outputTail + 5 >= this._outputEnd) {
            _flushBuffer();
        }
        int i4 = this._outputTail;
        char[] cArr = this._outputBuffer;
        if (z2) {
            cArr[i4] = 't';
            cArr[i4 + 1] = 'r';
            cArr[i4 + 2] = AbstractJsonLexerKt.UNICODE_ESC;
            i3 = i4 + 3;
            cArr[i3] = 'e';
        } else {
            cArr[i4] = 'f';
            cArr[i4 + 1] = 'a';
            cArr[i4 + 2] = 'l';
            cArr[i4 + 3] = 's';
            i3 = i4 + 4;
            cArr[i3] = 'e';
        }
        this._outputTail = i3 + 1;
    }

    public void writeEndArray() throws IOException {
        if (!this._writeContext.inArray()) {
            _reportError("Current context not Array but " + this._writeContext.typeDesc());
        }
        PrettyPrinter prettyPrinter = this._cfgPrettyPrinter;
        if (prettyPrinter != null) {
            prettyPrinter.writeEndArray(this, this._writeContext.getEntryCount());
        } else {
            if (this._outputTail >= this._outputEnd) {
                _flushBuffer();
            }
            char[] cArr = this._outputBuffer;
            int i3 = this._outputTail;
            this._outputTail = i3 + 1;
            cArr[i3] = AbstractJsonLexerKt.END_LIST;
        }
        this._writeContext = this._writeContext.clearAndGetParent();
    }

    public void writeEndObject() throws IOException {
        if (!this._writeContext.inObject()) {
            _reportError("Current context not Object but " + this._writeContext.typeDesc());
        }
        PrettyPrinter prettyPrinter = this._cfgPrettyPrinter;
        if (prettyPrinter != null) {
            prettyPrinter.writeEndObject(this, this._writeContext.getEntryCount());
        } else {
            if (this._outputTail >= this._outputEnd) {
                _flushBuffer();
            }
            char[] cArr = this._outputBuffer;
            int i3 = this._outputTail;
            this._outputTail = i3 + 1;
            cArr[i3] = AbstractJsonLexerKt.END_OBJ;
        }
        this._writeContext = this._writeContext.clearAndGetParent();
    }

    public void writeFieldName(String str) throws IOException {
        int writeFieldName = this._writeContext.writeFieldName(str);
        if (writeFieldName == 4) {
            _reportError("Can not write a field name, expecting a value");
        }
        boolean z2 = true;
        if (writeFieldName != 1) {
            z2 = false;
        }
        _writeFieldName(str, z2);
    }

    public void writeNull() throws IOException {
        _verifyValueWrite("write a null");
        _writeNull();
    }

    public void writeNumber(short s3) throws IOException {
        _verifyValueWrite("write a number");
        if (this._cfgNumbersAsStrings) {
            _writeQuotedShort(s3);
            return;
        }
        if (this._outputTail + 6 >= this._outputEnd) {
            _flushBuffer();
        }
        this._outputTail = NumberOutput.outputInt((int) s3, this._outputBuffer, this._outputTail);
    }

    public void writeRaw(String str) throws IOException {
        int length = str.length();
        int i3 = this._outputEnd - this._outputTail;
        if (i3 == 0) {
            _flushBuffer();
            i3 = this._outputEnd - this._outputTail;
        }
        if (i3 >= length) {
            str.getChars(0, length, this._outputBuffer, this._outputTail);
            this._outputTail += length;
            return;
        }
        writeRawLong(str);
    }

    public void writeRawUTF8String(byte[] bArr, int i3, int i4) throws IOException {
        _reportUnsupportedOperation();
    }

    public void writeStartArray() throws IOException {
        _verifyValueWrite("start an array");
        this._writeContext = this._writeContext.createChildArrayContext();
        PrettyPrinter prettyPrinter = this._cfgPrettyPrinter;
        if (prettyPrinter != null) {
            prettyPrinter.writeStartArray(this);
            return;
        }
        if (this._outputTail >= this._outputEnd) {
            _flushBuffer();
        }
        char[] cArr = this._outputBuffer;
        int i3 = this._outputTail;
        this._outputTail = i3 + 1;
        cArr[i3] = AbstractJsonLexerKt.BEGIN_LIST;
    }

    public void writeStartObject() throws IOException {
        _verifyValueWrite("start an object");
        this._writeContext = this._writeContext.createChildObjectContext();
        PrettyPrinter prettyPrinter = this._cfgPrettyPrinter;
        if (prettyPrinter != null) {
            prettyPrinter.writeStartObject(this);
            return;
        }
        if (this._outputTail >= this._outputEnd) {
            _flushBuffer();
        }
        char[] cArr = this._outputBuffer;
        int i3 = this._outputTail;
        this._outputTail = i3 + 1;
        cArr[i3] = AbstractJsonLexerKt.BEGIN_OBJ;
    }

    public void writeString(String str) throws IOException {
        _verifyValueWrite("write a string");
        if (str == null) {
            _writeNull();
            return;
        }
        if (this._outputTail >= this._outputEnd) {
            _flushBuffer();
        }
        char[] cArr = this._outputBuffer;
        int i3 = this._outputTail;
        this._outputTail = i3 + 1;
        cArr[i3] = this._quoteChar;
        _writeString(str);
        if (this._outputTail >= this._outputEnd) {
            _flushBuffer();
        }
        char[] cArr2 = this._outputBuffer;
        int i4 = this._outputTail;
        this._outputTail = i4 + 1;
        cArr2[i4] = this._quoteChar;
    }

    public void writeUTF8String(byte[] bArr, int i3, int i4) throws IOException {
        _reportUnsupportedOperation();
    }

    public WriterBasedJsonGenerator(IOContext iOContext, int i3, ObjectCodec objectCodec, Writer writer, char c3) {
        super(iOContext, i3, objectCodec);
        this._writer = writer;
        char[] allocConcatBuffer = iOContext.allocConcatBuffer();
        this._outputBuffer = allocConcatBuffer;
        this._outputEnd = allocConcatBuffer.length;
        this._quoteChar = c3;
        if (c3 != '\"') {
            this._outputEscapes = CharTypes.get7BitOutputEscapes(c3);
        }
    }

    public void writeFieldName(SerializableString serializableString) throws IOException {
        int writeFieldName = this._writeContext.writeFieldName(serializableString.getValue());
        if (writeFieldName == 4) {
            _reportError("Can not write a field name, expecting a value");
        }
        boolean z2 = true;
        if (writeFieldName != 1) {
            z2 = false;
        }
        _writeFieldName(serializableString, z2);
    }

    public void writeNumber(int i3) throws IOException {
        _verifyValueWrite("write a number");
        if (this._cfgNumbersAsStrings) {
            _writeQuotedInt(i3);
            return;
        }
        if (this._outputTail + 11 >= this._outputEnd) {
            _flushBuffer();
        }
        this._outputTail = NumberOutput.outputInt(i3, this._outputBuffer, this._outputTail);
    }

    private void _writeQuotedRaw(char[] cArr, int i3, int i4) throws IOException {
        if (this._outputTail >= this._outputEnd) {
            _flushBuffer();
        }
        char[] cArr2 = this._outputBuffer;
        int i5 = this._outputTail;
        this._outputTail = i5 + 1;
        cArr2[i5] = this._quoteChar;
        writeRaw(cArr, i3, i4);
        if (this._outputTail >= this._outputEnd) {
            _flushBuffer();
        }
        char[] cArr3 = this._outputBuffer;
        int i6 = this._outputTail;
        this._outputTail = i6 + 1;
        cArr3[i6] = this._quoteChar;
    }

    public void writeRaw(String str, int i3, int i4) throws IOException {
        _checkRangeBoundsForString(str, i3, i4);
        int i5 = this._outputEnd - this._outputTail;
        if (i5 < i4) {
            _flushBuffer();
            i5 = this._outputEnd - this._outputTail;
        }
        if (i5 >= i4) {
            str.getChars(i3, i3 + i4, this._outputBuffer, this._outputTail);
            this._outputTail += i4;
            return;
        }
        writeRawLong(str.substring(i3, i4 + i3));
    }

    public void writeStartArray(Object obj) throws IOException {
        _verifyValueWrite("start an array");
        this._writeContext = this._writeContext.createChildArrayContext(obj);
        PrettyPrinter prettyPrinter = this._cfgPrettyPrinter;
        if (prettyPrinter != null) {
            prettyPrinter.writeStartArray(this);
            return;
        }
        if (this._outputTail >= this._outputEnd) {
            _flushBuffer();
        }
        char[] cArr = this._outputBuffer;
        int i3 = this._outputTail;
        this._outputTail = i3 + 1;
        cArr[i3] = AbstractJsonLexerKt.BEGIN_LIST;
    }

    public void writeStartObject(Object obj) throws IOException {
        _verifyValueWrite("start an object");
        this._writeContext = this._writeContext.createChildObjectContext(obj);
        PrettyPrinter prettyPrinter = this._cfgPrettyPrinter;
        if (prettyPrinter != null) {
            prettyPrinter.writeStartObject(this);
            return;
        }
        if (this._outputTail >= this._outputEnd) {
            _flushBuffer();
        }
        char[] cArr = this._outputBuffer;
        int i3 = this._outputTail;
        this._outputTail = i3 + 1;
        cArr[i3] = AbstractJsonLexerKt.BEGIN_OBJ;
    }

    /* JADX INFO: finally extract failed */
    public int writeBinary(Base64Variant base64Variant, InputStream inputStream, int i3) throws IOException, JsonGenerationException {
        _verifyValueWrite("write a binary value");
        if (this._outputTail >= this._outputEnd) {
            _flushBuffer();
        }
        char[] cArr = this._outputBuffer;
        int i4 = this._outputTail;
        this._outputTail = i4 + 1;
        cArr[i4] = this._quoteChar;
        byte[] allocBase64Buffer = this._ioContext.allocBase64Buffer();
        if (i3 < 0) {
            try {
                i3 = _writeBinary(base64Variant, inputStream, allocBase64Buffer);
            } catch (Throwable th) {
                this._ioContext.releaseBase64Buffer(allocBase64Buffer);
                throw th;
            }
        } else {
            int _writeBinary = _writeBinary(base64Variant, inputStream, allocBase64Buffer, i3);
            if (_writeBinary > 0) {
                _reportError("Too few bytes available: missing " + _writeBinary + " bytes (out of " + i3 + ")");
            }
        }
        this._ioContext.releaseBase64Buffer(allocBase64Buffer);
        if (this._outputTail >= this._outputEnd) {
            _flushBuffer();
        }
        char[] cArr2 = this._outputBuffer;
        int i5 = this._outputTail;
        this._outputTail = i5 + 1;
        cArr2[i5] = this._quoteChar;
        return i3;
    }

    public void writeString(Reader reader, int i3) throws IOException {
        _verifyValueWrite("write a string");
        if (reader == null) {
            _reportError("null reader");
            return;
        }
        int i4 = i3 >= 0 ? i3 : Integer.MAX_VALUE;
        if (this._outputTail >= this._outputEnd) {
            _flushBuffer();
        }
        char[] cArr = this._outputBuffer;
        int i5 = this._outputTail;
        this._outputTail = i5 + 1;
        cArr[i5] = this._quoteChar;
        char[] _allocateCopyBuffer = _allocateCopyBuffer();
        while (i4 > 0) {
            int read = reader.read(_allocateCopyBuffer, 0, Math.min(i4, _allocateCopyBuffer.length));
            if (read <= 0) {
                break;
            }
            _writeString(_allocateCopyBuffer, 0, read);
            i4 -= read;
        }
        if (this._outputTail >= this._outputEnd) {
            _flushBuffer();
        }
        char[] cArr2 = this._outputBuffer;
        int i6 = this._outputTail;
        this._outputTail = i6 + 1;
        cArr2[i6] = this._quoteChar;
        if (i4 > 0 && i3 >= 0) {
            _reportError("Didn't read enough from reader");
        }
    }

    private void _writeString(char[] cArr, int i3, int i4) throws IOException {
        if (this._characterEscapes != null) {
            _writeStringCustom(cArr, i3, i4);
            return;
        }
        int i5 = this._maximumNonEscapedChar;
        if (i5 != 0) {
            _writeStringASCII(cArr, i3, i4, i5);
            return;
        }
        int i6 = i4 + i3;
        int[] iArr = this._outputEscapes;
        int length = iArr.length;
        while (i3 < i6) {
            int i7 = i3;
            do {
                char c3 = cArr[i7];
                if ((c3 < length && iArr[c3] != 0) || (i7 = i7 + 1) >= i6) {
                    int i8 = i7 - i3;
                }
                char c32 = cArr[i7];
                break;
            } while ((i7 = i7 + 1) >= i6);
            int i82 = i7 - i3;
            if (i82 < 32) {
                if (this._outputTail + i82 > this._outputEnd) {
                    _flushBuffer();
                }
                if (i82 > 0) {
                    System.arraycopy(cArr, i3, this._outputBuffer, this._outputTail, i82);
                    this._outputTail += i82;
                }
            } else {
                _flushBuffer();
                this._writer.write(cArr, i3, i82);
            }
            if (i7 < i6) {
                i3 = i7 + 1;
                char c4 = cArr[i7];
                _appendCharacterEscape(c4, iArr[c4]);
            } else {
                return;
            }
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x0020, code lost:
        r2 = r6._outputBuffer;
        r3 = r6._outputTail;
        r6._outputTail = r3 + 1;
        r2 = r2[r3];
        _prependOrWriteCharacterEscape(r2, r7[r2]);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:7:0x0016, code lost:
        r4 = r6._outputHead;
        r3 = r3 - r4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x0019, code lost:
        if (r3 <= 0) goto L_0x0020;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x001b, code lost:
        r6._writer.write(r2, r4, r3);
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void _writeString2(int r7) throws java.io.IOException {
        /*
            r6 = this;
            int r0 = r6._outputTail
            int r0 = r0 + r7
            int[] r7 = r6._outputEscapes
            int r1 = r7.length
        L_0x0006:
            int r2 = r6._outputTail
            if (r2 >= r0) goto L_0x0036
        L_0x000a:
            char[] r2 = r6._outputBuffer
            int r3 = r6._outputTail
            char r4 = r2[r3]
            if (r4 >= r1) goto L_0x0030
            r4 = r7[r4]
            if (r4 == 0) goto L_0x0030
            int r4 = r6._outputHead
            int r3 = r3 - r4
            if (r3 <= 0) goto L_0x0020
            java.io.Writer r5 = r6._writer
            r5.write(r2, r4, r3)
        L_0x0020:
            char[] r2 = r6._outputBuffer
            int r3 = r6._outputTail
            int r4 = r3 + 1
            r6._outputTail = r4
            char r2 = r2[r3]
            r3 = r7[r2]
            r6._prependOrWriteCharacterEscape(r2, r3)
            goto L_0x0006
        L_0x0030:
            int r3 = r3 + 1
            r6._outputTail = r3
            if (r3 < r0) goto L_0x000a
        L_0x0036:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.fasterxml.jackson.core.json.WriterBasedJsonGenerator._writeString2(int):void");
    }

    private void _writeStringASCII(char[] cArr, int i3, int i4, int i5) throws IOException, JsonGenerationException {
        char c3;
        int i6 = i4 + i3;
        int[] iArr = this._outputEscapes;
        int min = Math.min(iArr.length, i5 + 1);
        int i7 = 0;
        while (i3 < i6) {
            int i8 = i3;
            while (true) {
                c3 = cArr[i8];
                if (c3 >= min) {
                    if (c3 > i5) {
                        i7 = -1;
                        break;
                    }
                } else {
                    i7 = iArr[c3];
                    if (i7 != 0) {
                        break;
                    }
                }
                i8++;
                if (i8 >= i6) {
                    break;
                }
            }
            int i9 = i8 - i3;
            if (i9 < 32) {
                if (this._outputTail + i9 > this._outputEnd) {
                    _flushBuffer();
                }
                if (i9 > 0) {
                    System.arraycopy(cArr, i3, this._outputBuffer, this._outputTail, i9);
                    this._outputTail += i9;
                }
            } else {
                _flushBuffer();
                this._writer.write(cArr, i3, i9);
            }
            if (i8 < i6) {
                i3 = i8 + 1;
                _appendCharacterEscape(c3, i7);
            } else {
                return;
            }
        }
    }

    public final void _writePPFieldName(SerializableString serializableString, boolean z2) throws IOException {
        if (z2) {
            this._cfgPrettyPrinter.writeObjectEntrySeparator(this);
        } else {
            this._cfgPrettyPrinter.beforeObjectEntries(this);
        }
        char[] asQuotedChars = serializableString.asQuotedChars();
        if (this._cfgUnqNames) {
            writeRaw(asQuotedChars, 0, asQuotedChars.length);
            return;
        }
        if (this._outputTail >= this._outputEnd) {
            _flushBuffer();
        }
        char[] cArr = this._outputBuffer;
        int i3 = this._outputTail;
        this._outputTail = i3 + 1;
        cArr[i3] = this._quoteChar;
        writeRaw(asQuotedChars, 0, asQuotedChars.length);
        if (this._outputTail >= this._outputEnd) {
            _flushBuffer();
        }
        char[] cArr2 = this._outputBuffer;
        int i4 = this._outputTail;
        this._outputTail = i4 + 1;
        cArr2[i4] = this._quoteChar;
    }

    public final void _writeFieldName(SerializableString serializableString, boolean z2) throws IOException {
        if (this._cfgPrettyPrinter != null) {
            _writePPFieldName(serializableString, z2);
            return;
        }
        if (this._outputTail + 1 >= this._outputEnd) {
            _flushBuffer();
        }
        if (z2) {
            char[] cArr = this._outputBuffer;
            int i3 = this._outputTail;
            this._outputTail = i3 + 1;
            cArr[i3] = AbstractJsonLexerKt.COMMA;
        }
        if (this._cfgUnqNames) {
            char[] asQuotedChars = serializableString.asQuotedChars();
            writeRaw(asQuotedChars, 0, asQuotedChars.length);
            return;
        }
        char[] cArr2 = this._outputBuffer;
        int i4 = this._outputTail;
        int i5 = i4 + 1;
        this._outputTail = i5;
        cArr2[i4] = this._quoteChar;
        int appendQuoted = serializableString.appendQuoted(cArr2, i5);
        if (appendQuoted < 0) {
            _writeFieldNameTail(serializableString);
            return;
        }
        int i6 = this._outputTail + appendQuoted;
        this._outputTail = i6;
        if (i6 >= this._outputEnd) {
            _flushBuffer();
        }
        char[] cArr3 = this._outputBuffer;
        int i7 = this._outputTail;
        this._outputTail = i7 + 1;
        cArr3[i7] = this._quoteChar;
    }

    public void writeNumber(long j2) throws IOException {
        _verifyValueWrite("write a number");
        if (this._cfgNumbersAsStrings) {
            _writeQuotedLong(j2);
            return;
        }
        if (this._outputTail + 21 >= this._outputEnd) {
            _flushBuffer();
        }
        this._outputTail = NumberOutput.outputLong(j2, this._outputBuffer, this._outputTail);
    }

    private void _writeStringCustom(char[] cArr, int i3, int i4) throws IOException, JsonGenerationException {
        char c3;
        int i5 = i4 + i3;
        int[] iArr = this._outputEscapes;
        int i6 = this._maximumNonEscapedChar;
        if (i6 < 1) {
            i6 = 65535;
        }
        int min = Math.min(iArr.length, i6 + 1);
        CharacterEscapes characterEscapes = this._characterEscapes;
        int i7 = 0;
        while (i3 < i5) {
            int i8 = i3;
            while (true) {
                c3 = cArr[i8];
                if (c3 >= min) {
                    if (c3 <= i6) {
                        SerializableString escapeSequence = characterEscapes.getEscapeSequence(c3);
                        this._currentEscape = escapeSequence;
                        if (escapeSequence != null) {
                            i7 = -2;
                            break;
                        }
                    } else {
                        i7 = -1;
                        break;
                    }
                } else {
                    i7 = iArr[c3];
                    if (i7 != 0) {
                        break;
                    }
                }
                i8++;
                if (i8 >= i5) {
                    break;
                }
            }
            int i9 = i8 - i3;
            if (i9 < 32) {
                if (this._outputTail + i9 > this._outputEnd) {
                    _flushBuffer();
                }
                if (i9 > 0) {
                    System.arraycopy(cArr, i3, this._outputBuffer, this._outputTail, i9);
                    this._outputTail += i9;
                }
            } else {
                _flushBuffer();
                this._writer.write(cArr, i3, i9);
            }
            if (i8 < i5) {
                i3 = i8 + 1;
                _appendCharacterEscape(c3, i7);
            } else {
                return;
            }
        }
    }

    public void writeRaw(SerializableString serializableString) throws IOException {
        int appendUnquoted = serializableString.appendUnquoted(this._outputBuffer, this._outputTail);
        if (appendUnquoted < 0) {
            writeRaw(serializableString.getValue());
        } else {
            this._outputTail += appendUnquoted;
        }
    }

    public void writeStartArray(Object obj, int i3) throws IOException {
        _verifyValueWrite("start an array");
        this._writeContext = this._writeContext.createChildArrayContext(obj);
        PrettyPrinter prettyPrinter = this._cfgPrettyPrinter;
        if (prettyPrinter != null) {
            prettyPrinter.writeStartArray(this);
            return;
        }
        if (this._outputTail >= this._outputEnd) {
            _flushBuffer();
        }
        char[] cArr = this._outputBuffer;
        int i4 = this._outputTail;
        this._outputTail = i4 + 1;
        cArr[i4] = AbstractJsonLexerKt.BEGIN_LIST;
    }

    public void writeStartObject(Object obj, int i3) throws IOException {
        writeStartObject(obj);
    }

    public final int _writeBinary(Base64Variant base64Variant, InputStream inputStream, byte[] bArr, int i3) throws IOException, JsonGenerationException {
        int _readMore;
        Base64Variant base64Variant2 = base64Variant;
        int i4 = this._outputEnd - 6;
        int i5 = 2;
        int i6 = -3;
        int i7 = i3;
        int maxLineLength = base64Variant.getMaxLineLength() >> 2;
        int i8 = 0;
        int i9 = 0;
        while (true) {
            if (i7 <= 2) {
                break;
            }
            if (i8 > i6) {
                i9 = _readMore(inputStream, bArr, i8, i9, i7);
                if (i9 < 3) {
                    i8 = 0;
                    break;
                }
                i6 = i9 - 3;
                i8 = 0;
            }
            if (this._outputTail > i4) {
                _flushBuffer();
            }
            int i10 = i8 + 2;
            byte b3 = bArr[i8 + 1] & 255;
            i8 += 3;
            i7 -= 3;
            int encodeBase64Chunk = base64Variant.encodeBase64Chunk((int) ((b3 | (bArr[i8] << 8)) << 8) | (bArr[i10] & 255), this._outputBuffer, this._outputTail);
            this._outputTail = encodeBase64Chunk;
            maxLineLength--;
            if (maxLineLength <= 0) {
                char[] cArr = this._outputBuffer;
                int i11 = encodeBase64Chunk + 1;
                this._outputTail = i11;
                cArr[encodeBase64Chunk] = AbstractJsonLexerKt.STRING_ESC;
                this._outputTail = encodeBase64Chunk + 2;
                cArr[i11] = 'n';
                maxLineLength = base64Variant.getMaxLineLength() >> 2;
            }
        }
        if (i7 <= 0 || (_readMore = _readMore(inputStream, bArr, i8, i9, i7)) <= 0) {
            return i7;
        }
        if (this._outputTail > i4) {
            _flushBuffer();
        }
        int i12 = bArr[0] << 16;
        if (1 < _readMore) {
            i12 |= (bArr[1] & 255) << 8;
        } else {
            i5 = 1;
        }
        this._outputTail = base64Variant.encodeBase64Partial(i12, i5, this._outputBuffer, this._outputTail);
        return i7 - i5;
    }

    public void writeRaw(char[] cArr, int i3, int i4) throws IOException {
        _checkRangeBoundsForCharArray(cArr, i3, i4);
        if (i4 < 32) {
            if (i4 > this._outputEnd - this._outputTail) {
                _flushBuffer();
            }
            System.arraycopy(cArr, i3, this._outputBuffer, this._outputTail, i4);
            this._outputTail += i4;
            return;
        }
        _flushBuffer();
        this._writer.write(cArr, i3, i4);
    }

    public void writeNumber(BigInteger bigInteger) throws IOException {
        _verifyValueWrite("write a number");
        if (bigInteger == null) {
            _writeNull();
        } else if (this._cfgNumbersAsStrings) {
            _writeQuotedRaw(bigInteger.toString());
        } else {
            writeRaw(bigInteger.toString());
        }
    }

    public void writeString(char[] cArr, int i3, int i4) throws IOException {
        _verifyValueWrite("write a string");
        if (this._outputTail >= this._outputEnd) {
            _flushBuffer();
        }
        char[] cArr2 = this._outputBuffer;
        int i5 = this._outputTail;
        this._outputTail = i5 + 1;
        cArr2[i5] = this._quoteChar;
        _writeString(cArr, i3, i4);
        if (this._outputTail >= this._outputEnd) {
            _flushBuffer();
        }
        char[] cArr3 = this._outputBuffer;
        int i6 = this._outputTail;
        this._outputTail = i6 + 1;
        cArr3[i6] = this._quoteChar;
    }

    public void writeNumber(double d2) throws IOException {
        if (this._cfgNumbersAsStrings || (NumberOutput.notFinite(d2) && isEnabled(JsonGenerator.Feature.QUOTE_NON_NUMERIC_NUMBERS))) {
            writeString(NumberOutput.toString(d2, isEnabled(JsonGenerator.Feature.USE_FAST_DOUBLE_WRITER)));
            return;
        }
        _verifyValueWrite("write a number");
        writeRaw(NumberOutput.toString(d2, isEnabled(JsonGenerator.Feature.USE_FAST_DOUBLE_WRITER)));
    }

    public void writeRaw(char c3) throws IOException {
        if (this._outputTail >= this._outputEnd) {
            _flushBuffer();
        }
        char[] cArr = this._outputBuffer;
        int i3 = this._outputTail;
        this._outputTail = i3 + 1;
        cArr[i3] = c3;
    }

    public void writeNumber(float f2) throws IOException {
        if (this._cfgNumbersAsStrings || (NumberOutput.notFinite(f2) && isEnabled(JsonGenerator.Feature.QUOTE_NON_NUMERIC_NUMBERS))) {
            writeString(NumberOutput.toString(f2, isEnabled(JsonGenerator.Feature.USE_FAST_DOUBLE_WRITER)));
            return;
        }
        _verifyValueWrite("write a number");
        writeRaw(NumberOutput.toString(f2, isEnabled(JsonGenerator.Feature.USE_FAST_DOUBLE_WRITER)));
    }

    public void writeString(SerializableString serializableString) throws IOException {
        _verifyValueWrite("write a string");
        if (this._outputTail >= this._outputEnd) {
            _flushBuffer();
        }
        char[] cArr = this._outputBuffer;
        int i3 = this._outputTail;
        int i4 = i3 + 1;
        this._outputTail = i4;
        cArr[i3] = this._quoteChar;
        int appendQuoted = serializableString.appendQuoted(cArr, i4);
        if (appendQuoted < 0) {
            _writeString2(serializableString);
            return;
        }
        int i5 = this._outputTail + appendQuoted;
        this._outputTail = i5;
        if (i5 >= this._outputEnd) {
            _flushBuffer();
        }
        char[] cArr2 = this._outputBuffer;
        int i6 = this._outputTail;
        this._outputTail = i6 + 1;
        cArr2[i6] = this._quoteChar;
    }

    public void writeNumber(BigDecimal bigDecimal) throws IOException {
        _verifyValueWrite("write a number");
        if (bigDecimal == null) {
            _writeNull();
        } else if (this._cfgNumbersAsStrings) {
            _writeQuotedRaw(_asString(bigDecimal));
        } else {
            writeRaw(_asString(bigDecimal));
        }
    }

    public final int _writeBinary(Base64Variant base64Variant, InputStream inputStream, byte[] bArr) throws IOException, JsonGenerationException {
        Base64Variant base64Variant2 = base64Variant;
        byte[] bArr2 = bArr;
        int i3 = this._outputEnd - 6;
        int i4 = 2;
        int i5 = -3;
        int maxLineLength = base64Variant.getMaxLineLength() >> 2;
        int i6 = 0;
        int i7 = 0;
        int i8 = 0;
        while (true) {
            if (i6 > i5) {
                i7 = _readMore(inputStream, bArr, i6, i7, bArr2.length);
                if (i7 < 3) {
                    break;
                }
                i5 = i7 - 3;
                i6 = 0;
            }
            if (this._outputTail > i3) {
                _flushBuffer();
            }
            int i9 = i6 + 2;
            byte b3 = bArr2[i6 + 1] & 255;
            i6 += 3;
            i8 += 3;
            int encodeBase64Chunk = base64Variant2.encodeBase64Chunk((int) ((b3 | (bArr2[i6] << 8)) << 8) | (bArr2[i9] & 255), this._outputBuffer, this._outputTail);
            this._outputTail = encodeBase64Chunk;
            maxLineLength--;
            if (maxLineLength <= 0) {
                char[] cArr = this._outputBuffer;
                int i10 = encodeBase64Chunk + 1;
                this._outputTail = i10;
                cArr[encodeBase64Chunk] = AbstractJsonLexerKt.STRING_ESC;
                this._outputTail = encodeBase64Chunk + 2;
                cArr[i10] = 'n';
                maxLineLength = base64Variant.getMaxLineLength() >> 2;
            }
        }
        if (i7 <= 0) {
            return i8;
        }
        if (this._outputTail > i3) {
            _flushBuffer();
        }
        int i11 = bArr2[0] << 16;
        if (1 < i7) {
            i11 |= (bArr2[1] & 255) << 8;
        } else {
            i4 = 1;
        }
        int i12 = i8 + i4;
        this._outputTail = base64Variant2.encodeBase64Partial(i11, i4, this._outputBuffer, this._outputTail);
        return i12;
    }

    public void writeNumber(String str) throws IOException {
        _verifyValueWrite("write a number");
        if (str == null) {
            _writeNull();
        } else if (this._cfgNumbersAsStrings) {
            _writeQuotedRaw(str);
        } else {
            writeRaw(str);
        }
    }

    private int _prependOrWriteCharacterEscape(char[] cArr, int i3, int i4, char c3, int i5) throws IOException, JsonGenerationException {
        String str;
        int i6;
        if (i5 >= 0) {
            if (i3 <= 1 || i3 >= i4) {
                char[] cArr2 = this._entityBuffer;
                if (cArr2 == null) {
                    cArr2 = _allocateEntityBuffer();
                }
                cArr2[1] = (char) i5;
                this._writer.write(cArr2, 0, 2);
                return i3;
            }
            int i7 = i3 - 2;
            cArr[i7] = AbstractJsonLexerKt.STRING_ESC;
            cArr[i3 - 1] = (char) i5;
            return i7;
        } else if (i5 != -2) {
            char[] hexChars = getHexChars();
            if (i3 <= 5 || i3 >= i4) {
                char[] cArr3 = this._entityBuffer;
                if (cArr3 == null) {
                    cArr3 = _allocateEntityBuffer();
                }
                this._outputHead = this._outputTail;
                if (c3 > 255) {
                    int i8 = c3 >> 8;
                    cArr3[10] = hexChars[(i8 & 255) >> 4];
                    cArr3[11] = hexChars[i8 & 15];
                    cArr3[12] = hexChars[(c3 & 255) >> 4];
                    cArr3[13] = hexChars[c3 & 15];
                    this._writer.write(cArr3, 8, 6);
                    return i3;
                }
                cArr3[6] = hexChars[c3 >> 4];
                cArr3[7] = hexChars[c3 & 15];
                this._writer.write(cArr3, 2, 6);
                return i3;
            }
            cArr[i3 - 6] = AbstractJsonLexerKt.STRING_ESC;
            int i9 = i3 - 4;
            cArr[i3 - 5] = AbstractJsonLexerKt.UNICODE_ESC;
            if (c3 > 255) {
                int i10 = c3 >> 8;
                int i11 = i3 - 3;
                cArr[i9] = hexChars[(i10 & 255) >> 4];
                i6 = i3 - 2;
                cArr[i11] = hexChars[i10 & 15];
                c3 = (char) (c3 & 255);
            } else {
                int i12 = i3 - 3;
                cArr[i9] = '0';
                i6 = i3 - 2;
                cArr[i12] = '0';
            }
            cArr[i6] = hexChars[c3 >> 4];
            cArr[i6 + 1] = hexChars[c3 & 15];
            return i6 - 4;
        } else {
            SerializableString serializableString = this._currentEscape;
            if (serializableString == null) {
                str = this._characterEscapes.getEscapeSequence(c3).getValue();
            } else {
                str = serializableString.getValue();
                this._currentEscape = null;
            }
            int length = str.length();
            if (i3 < length || i3 >= i4) {
                this._writer.write(str);
                return i3;
            }
            int i13 = i3 - length;
            str.getChars(0, length, cArr, i13);
            return i13;
        }
    }

    public void writeNumber(char[] cArr, int i3, int i4) throws IOException {
        _verifyValueWrite("write a number");
        if (this._cfgNumbersAsStrings) {
            _writeQuotedRaw(cArr, i3, i4);
        } else {
            writeRaw(cArr, i3, i4);
        }
    }
}
