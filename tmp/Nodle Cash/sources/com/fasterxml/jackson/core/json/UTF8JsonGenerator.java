package com.fasterxml.jackson.core.json;

import androidx.compose.ui.autofill.a;
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
import java.io.OutputStream;
import java.io.Reader;
import java.math.BigDecimal;
import java.math.BigInteger;

public class UTF8JsonGenerator extends JsonGeneratorImpl {
    private static final byte BYTE_0 = 48;
    private static final byte BYTE_BACKSLASH = 92;
    private static final byte BYTE_COLON = 58;
    private static final byte BYTE_COMMA = 44;
    private static final byte BYTE_LBRACKET = 91;
    private static final byte BYTE_LCURLY = 123;
    private static final byte BYTE_RBRACKET = 93;
    private static final byte BYTE_RCURLY = 125;
    private static final byte BYTE_u = 117;
    private static final byte[] FALSE_BYTES = {102, 97, 108, 115, 101};
    private static final byte[] HEX_BYTES_LOWER = CharTypes.copyHexBytes(false);
    private static final byte[] HEX_BYTES_UPPER = CharTypes.copyHexBytes(true);
    private static final int MAX_BYTES_TO_BUFFER = 512;
    private static final byte[] NULL_BYTES = {110, BYTE_u, 108, 108};
    private static final byte[] TRUE_BYTES = {116, 114, BYTE_u, 101};
    protected boolean _bufferRecyclable;
    protected char[] _charBuffer;
    protected final int _charBufferLength;
    protected byte[] _entityBuffer;
    protected byte[] _outputBuffer;
    protected final int _outputEnd;
    protected final int _outputMaxContiguous;
    protected final OutputStream _outputStream;
    protected int _outputTail;
    protected byte _quoteChar;

    public UTF8JsonGenerator(IOContext iOContext, int i3, ObjectCodec objectCodec, OutputStream outputStream, char c3) {
        super(iOContext, i3, objectCodec);
        this._outputStream = outputStream;
        this._quoteChar = (byte) c3;
        if (c3 != '\"') {
            this._outputEscapes = CharTypes.get7BitOutputEscapes(c3);
        }
        this._bufferRecyclable = true;
        byte[] allocWriteEncodingBuffer = iOContext.allocWriteEncodingBuffer();
        this._outputBuffer = allocWriteEncodingBuffer;
        int length = allocWriteEncodingBuffer.length;
        this._outputEnd = length;
        this._outputMaxContiguous = length >> 3;
        char[] allocConcatBuffer = iOContext.allocConcatBuffer();
        this._charBuffer = allocConcatBuffer;
        this._charBufferLength = allocConcatBuffer.length;
        if (isEnabled(JsonGenerator.Feature.ESCAPE_NON_ASCII)) {
            setHighestNonEscapedChar(127);
        }
    }

    private final int _handleLongCustomEscape(byte[] bArr, int i3, int i4, byte[] bArr2, int i5) throws IOException, JsonGenerationException {
        int length = bArr2.length;
        if (i3 + length > i4) {
            this._outputTail = i3;
            _flushBuffer();
            i3 = this._outputTail;
            if (length > bArr.length) {
                this._outputStream.write(bArr2, 0, length);
                return i3;
            }
        }
        System.arraycopy(bArr2, 0, bArr, i3, length);
        int i6 = i3 + length;
        if ((i5 * 6) + i6 <= i4) {
            return i6;
        }
        this._outputTail = i6;
        _flushBuffer();
        return this._outputTail;
    }

    private final int _outputMultiByteChar(int i3, int i4) throws IOException {
        byte[] hexBytes = getHexBytes();
        byte[] bArr = this._outputBuffer;
        if (i3 < 55296 || i3 > 57343) {
            bArr[i4] = (byte) ((i3 >> 12) | 224);
            int i5 = i4 + 2;
            bArr[i4 + 1] = (byte) (((i3 >> 6) & 63) | 128);
            int i6 = i4 + 3;
            bArr[i5] = (byte) ((i3 & 63) | 128);
            return i6;
        }
        bArr[i4] = BYTE_BACKSLASH;
        bArr[i4 + 1] = BYTE_u;
        bArr[i4 + 2] = hexBytes[(i3 >> 12) & 15];
        bArr[i4 + 3] = hexBytes[(i3 >> 8) & 15];
        int i7 = i4 + 5;
        bArr[i4 + 4] = hexBytes[(i3 >> 4) & 15];
        int i8 = i4 + 6;
        bArr[i7] = hexBytes[i3 & 15];
        return i8;
    }

    private final int _outputRawMultiByteChar(int i3, char[] cArr, int i4, int i5) throws IOException {
        if (i3 < 55296 || i3 > 57343) {
            byte[] bArr = this._outputBuffer;
            int i6 = this._outputTail;
            int i7 = i6 + 1;
            this._outputTail = i7;
            bArr[i6] = (byte) ((i3 >> 12) | 224);
            int i8 = i6 + 2;
            this._outputTail = i8;
            bArr[i7] = (byte) (((i3 >> 6) & 63) | 128);
            this._outputTail = i6 + 3;
            bArr[i8] = (byte) ((i3 & 63) | 128);
            return i4;
        }
        if (i4 >= i5 || cArr == null) {
            _reportError(String.format("Split surrogate on writeRaw() input (last character): first character 0x%4x", new Object[]{Integer.valueOf(i3)}));
        } else {
            _outputSurrogates(i3, cArr[i4]);
        }
        return i4 + 1;
    }

    private final int _readMore(InputStream inputStream, byte[] bArr, int i3, int i4, int i5) throws IOException {
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

    private final void _writeBytes(byte[] bArr) throws IOException {
        int length = bArr.length;
        if (this._outputTail + length > this._outputEnd) {
            _flushBuffer();
            if (length > 512) {
                this._outputStream.write(bArr, 0, length);
                return;
            }
        }
        System.arraycopy(bArr, 0, this._outputBuffer, this._outputTail, length);
        this._outputTail += length;
    }

    private final int _writeCustomEscape(byte[] bArr, int i3, SerializableString serializableString, int i4) throws IOException, JsonGenerationException {
        byte[] asUnquotedUTF8 = serializableString.asUnquotedUTF8();
        int length = asUnquotedUTF8.length;
        if (length > 6) {
            return _handleLongCustomEscape(bArr, i3, this._outputEnd, asUnquotedUTF8, i4);
        }
        System.arraycopy(asUnquotedUTF8, 0, bArr, i3, length);
        return i3 + length;
    }

    private final void _writeCustomStringSegment2(char[] cArr, int i3, int i4) throws IOException {
        if (a.c(i4, i3, 6, this._outputTail) > this._outputEnd) {
            _flushBuffer();
        }
        int i5 = this._outputTail;
        byte[] bArr = this._outputBuffer;
        int[] iArr = this._outputEscapes;
        int i6 = this._maximumNonEscapedChar;
        if (i6 <= 0) {
            i6 = 65535;
        }
        CharacterEscapes characterEscapes = this._characterEscapes;
        while (i3 < i4) {
            int i7 = i3 + 1;
            char c3 = cArr[i3];
            if (c3 <= 127) {
                int i8 = iArr[c3];
                if (i8 == 0) {
                    bArr[i5] = (byte) c3;
                    i3 = i7;
                    i5++;
                } else if (i8 > 0) {
                    int i9 = i5 + 1;
                    bArr[i5] = BYTE_BACKSLASH;
                    i5 += 2;
                    bArr[i9] = (byte) i8;
                } else if (i8 == -2) {
                    SerializableString escapeSequence = characterEscapes.getEscapeSequence(c3);
                    if (escapeSequence == null) {
                        _reportError("Invalid custom escape definitions; custom escape not found for character code 0x" + Integer.toHexString(c3) + ", although was supposed to have one");
                    }
                    i5 = _writeCustomEscape(bArr, i5, escapeSequence, i4 - i7);
                } else {
                    i5 = _writeGenericEscape(c3, i5);
                }
            } else if (c3 > i6) {
                i5 = _writeGenericEscape(c3, i5);
            } else {
                SerializableString escapeSequence2 = characterEscapes.getEscapeSequence(c3);
                if (escapeSequence2 != null) {
                    i5 = _writeCustomEscape(bArr, i5, escapeSequence2, i4 - i7);
                } else if (c3 <= 2047) {
                    int i10 = i5 + 1;
                    bArr[i5] = (byte) ((c3 >> 6) | 192);
                    i5 += 2;
                    bArr[i10] = (byte) ((c3 & '?') | 128);
                } else {
                    i5 = _outputMultiByteChar(c3, i5);
                }
            }
            i3 = i7;
        }
        this._outputTail = i5;
    }

    private int _writeGenericEscape(int i3, int i4) throws IOException {
        int i5;
        byte[] bArr = this._outputBuffer;
        byte[] hexBytes = getHexBytes();
        bArr[i4] = BYTE_BACKSLASH;
        int i6 = i4 + 2;
        bArr[i4 + 1] = BYTE_u;
        if (i3 > 255) {
            int i7 = i3 >> 8;
            int i8 = i4 + 3;
            bArr[i6] = hexBytes[(i7 & 255) >> 4];
            i5 = i4 + 4;
            bArr[i8] = hexBytes[i7 & 15];
            i3 &= 255;
        } else {
            int i9 = i4 + 3;
            bArr[i6] = BYTE_0;
            i5 = i4 + 4;
            bArr[i9] = BYTE_0;
        }
        int i10 = i5 + 1;
        bArr[i5] = hexBytes[i3 >> 4];
        int i11 = i5 + 2;
        bArr[i10] = hexBytes[i3 & 15];
        return i11;
    }

    private final void _writeNull() throws IOException {
        if (this._outputTail + 4 >= this._outputEnd) {
            _flushBuffer();
        }
        System.arraycopy(NULL_BYTES, 0, this._outputBuffer, this._outputTail, 4);
        this._outputTail += 4;
    }

    private final void _writeQuotedInt(int i3) throws IOException {
        if (this._outputTail + 13 >= this._outputEnd) {
            _flushBuffer();
        }
        byte[] bArr = this._outputBuffer;
        int i4 = this._outputTail;
        int i5 = i4 + 1;
        this._outputTail = i5;
        bArr[i4] = this._quoteChar;
        int outputInt = NumberOutput.outputInt(i3, bArr, i5);
        byte[] bArr2 = this._outputBuffer;
        this._outputTail = outputInt + 1;
        bArr2[outputInt] = this._quoteChar;
    }

    private final void _writeQuotedLong(long j2) throws IOException {
        if (this._outputTail + 23 >= this._outputEnd) {
            _flushBuffer();
        }
        byte[] bArr = this._outputBuffer;
        int i3 = this._outputTail;
        int i4 = i3 + 1;
        this._outputTail = i4;
        bArr[i3] = this._quoteChar;
        int outputLong = NumberOutput.outputLong(j2, bArr, i4);
        byte[] bArr2 = this._outputBuffer;
        this._outputTail = outputLong + 1;
        bArr2[outputLong] = this._quoteChar;
    }

    private final void _writeQuotedRaw(String str) throws IOException {
        if (this._outputTail >= this._outputEnd) {
            _flushBuffer();
        }
        byte[] bArr = this._outputBuffer;
        int i3 = this._outputTail;
        this._outputTail = i3 + 1;
        bArr[i3] = this._quoteChar;
        writeRaw(str);
        if (this._outputTail >= this._outputEnd) {
            _flushBuffer();
        }
        byte[] bArr2 = this._outputBuffer;
        int i4 = this._outputTail;
        this._outputTail = i4 + 1;
        bArr2[i4] = this._quoteChar;
    }

    private final void _writeQuotedShort(short s3) throws IOException {
        if (this._outputTail + 8 >= this._outputEnd) {
            _flushBuffer();
        }
        byte[] bArr = this._outputBuffer;
        int i3 = this._outputTail;
        int i4 = i3 + 1;
        this._outputTail = i4;
        bArr[i3] = this._quoteChar;
        int outputInt = NumberOutput.outputInt((int) s3, bArr, i4);
        byte[] bArr2 = this._outputBuffer;
        this._outputTail = outputInt + 1;
        bArr2[outputInt] = this._quoteChar;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:3:0x0008, code lost:
        r7 = r7 + 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:4:0x000c, code lost:
        if (r0 >= 2048) goto L_0x0029;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:5:0x000e, code lost:
        r1 = r5._outputBuffer;
        r2 = r5._outputTail;
        r3 = r2 + 1;
        r5._outputTail = r3;
        r1[r2] = (byte) ((r0 >> 6) | 192);
        r5._outputTail = r2 + 2;
        r1[r3] = (byte) ((r0 & '?') | 128);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:6:0x0029, code lost:
        r7 = _outputRawMultiByteChar(r0, r6, r7, r8);
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void _writeRawSegment(char[] r6, int r7, int r8) throws java.io.IOException {
        /*
            r5 = this;
        L_0x0000:
            if (r7 >= r8) goto L_0x003d
        L_0x0002:
            char r0 = r6[r7]
            r1 = 127(0x7f, float:1.78E-43)
            if (r0 <= r1) goto L_0x002e
            int r7 = r7 + 1
            r1 = 2048(0x800, float:2.87E-42)
            if (r0 >= r1) goto L_0x0029
            byte[] r1 = r5._outputBuffer
            int r2 = r5._outputTail
            int r3 = r2 + 1
            r5._outputTail = r3
            int r4 = r0 >> 6
            r4 = r4 | 192(0xc0, float:2.69E-43)
            byte r4 = (byte) r4
            r1[r2] = r4
            int r2 = r2 + 2
            r5._outputTail = r2
            r0 = r0 & 63
            r0 = r0 | 128(0x80, float:1.794E-43)
            byte r0 = (byte) r0
            r1[r3] = r0
            goto L_0x0000
        L_0x0029:
            int r7 = r5._outputRawMultiByteChar(r0, r6, r7, r8)
            goto L_0x0000
        L_0x002e:
            byte[] r1 = r5._outputBuffer
            int r2 = r5._outputTail
            int r3 = r2 + 1
            r5._outputTail = r3
            byte r0 = (byte) r0
            r1[r2] = r0
            int r7 = r7 + 1
            if (r7 < r8) goto L_0x0002
        L_0x003d:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.fasterxml.jackson.core.json.UTF8JsonGenerator._writeRawSegment(char[], int, int):void");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x0039, code lost:
        r9 = _outputRawMultiByteChar(r9, r8, r2, r10);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:5:0x0013, code lost:
        if ((r7._outputTail + 3) < r7._outputEnd) goto L_0x0018;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:6:0x0015, code lost:
        _flushBuffer();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:7:0x0018, code lost:
        r2 = r9 + 1;
        r9 = r8[r9];
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x001e, code lost:
        if (r9 >= 2048) goto L_0x0039;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x0020, code lost:
        r4 = r7._outputTail;
        r5 = r4 + 1;
        r7._outputTail = r5;
        r1[r4] = (byte) ((r9 >> 6) | 192);
        r7._outputTail = r4 + 2;
        r1[r5] = (byte) ((r9 & '?') | 128);
        r9 = r2;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final void _writeSegmentedRaw(char[] r8, int r9, int r10) throws java.io.IOException {
        /*
            r7 = this;
            int r0 = r7._outputEnd
            byte[] r1 = r7._outputBuffer
            int r10 = r10 + r9
        L_0x0005:
            if (r9 >= r10) goto L_0x0052
        L_0x0007:
            char r2 = r8[r9]
            r3 = 128(0x80, float:1.794E-43)
            if (r2 < r3) goto L_0x003e
            int r2 = r7._outputTail
            int r2 = r2 + 3
            int r4 = r7._outputEnd
            if (r2 < r4) goto L_0x0018
            r7._flushBuffer()
        L_0x0018:
            int r2 = r9 + 1
            char r9 = r8[r9]
            r4 = 2048(0x800, float:2.87E-42)
            if (r9 >= r4) goto L_0x0039
            int r4 = r7._outputTail
            int r5 = r4 + 1
            r7._outputTail = r5
            int r6 = r9 >> 6
            r6 = r6 | 192(0xc0, float:2.69E-43)
            byte r6 = (byte) r6
            r1[r4] = r6
            int r4 = r4 + 2
            r7._outputTail = r4
            r9 = r9 & 63
            r9 = r9 | r3
            byte r9 = (byte) r9
            r1[r5] = r9
            r9 = r2
            goto L_0x0005
        L_0x0039:
            int r9 = r7._outputRawMultiByteChar(r9, r8, r2, r10)
            goto L_0x0005
        L_0x003e:
            int r3 = r7._outputTail
            if (r3 < r0) goto L_0x0045
            r7._flushBuffer()
        L_0x0045:
            int r3 = r7._outputTail
            int r4 = r3 + 1
            r7._outputTail = r4
            byte r2 = (byte) r2
            r1[r3] = r2
            int r9 = r9 + 1
            if (r9 < r10) goto L_0x0007
        L_0x0052:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.fasterxml.jackson.core.json.UTF8JsonGenerator._writeSegmentedRaw(char[], int, int):void");
    }

    private final void _writeStringSegment(char[] cArr, int i3, int i4) throws IOException {
        int i5 = i4 + i3;
        int i6 = this._outputTail;
        byte[] bArr = this._outputBuffer;
        int[] iArr = this._outputEscapes;
        while (i3 < i5) {
            char c3 = cArr[i3];
            if (c3 > 127 || iArr[c3] != 0) {
                break;
            }
            bArr[i6] = (byte) c3;
            i3++;
            i6++;
        }
        this._outputTail = i6;
        if (i3 >= i5) {
            return;
        }
        if (this._characterEscapes != null) {
            _writeCustomStringSegment2(cArr, i3, i5);
        } else if (this._maximumNonEscapedChar == 0) {
            _writeStringSegment2(cArr, i3, i5);
        } else {
            _writeStringSegmentASCII2(cArr, i3, i5);
        }
    }

    private final void _writeStringSegment2(char[] cArr, int i3, int i4) throws IOException {
        if (a.c(i4, i3, 6, this._outputTail) > this._outputEnd) {
            _flushBuffer();
        }
        int i5 = this._outputTail;
        byte[] bArr = this._outputBuffer;
        int[] iArr = this._outputEscapes;
        while (i3 < i4) {
            int i6 = i3 + 1;
            char c3 = cArr[i3];
            if (c3 <= 127) {
                int i7 = iArr[c3];
                if (i7 == 0) {
                    bArr[i5] = (byte) c3;
                    i3 = i6;
                    i5++;
                } else if (i7 > 0) {
                    int i8 = i5 + 1;
                    bArr[i5] = BYTE_BACKSLASH;
                    i5 += 2;
                    bArr[i8] = (byte) i7;
                } else {
                    i5 = _writeGenericEscape(c3, i5);
                }
            } else if (c3 <= 2047) {
                int i9 = i5 + 1;
                bArr[i5] = (byte) ((c3 >> 6) | 192);
                i5 += 2;
                bArr[i9] = (byte) ((c3 & '?') | 128);
            } else {
                i5 = _outputMultiByteChar(c3, i5);
            }
            i3 = i6;
        }
        this._outputTail = i5;
    }

    private final void _writeStringSegmentASCII2(char[] cArr, int i3, int i4) throws IOException {
        if (a.c(i4, i3, 6, this._outputTail) > this._outputEnd) {
            _flushBuffer();
        }
        int i5 = this._outputTail;
        byte[] bArr = this._outputBuffer;
        int[] iArr = this._outputEscapes;
        int i6 = this._maximumNonEscapedChar;
        while (i3 < i4) {
            int i7 = i3 + 1;
            char c3 = cArr[i3];
            if (c3 <= 127) {
                int i8 = iArr[c3];
                if (i8 == 0) {
                    bArr[i5] = (byte) c3;
                    i3 = i7;
                    i5++;
                } else if (i8 > 0) {
                    int i9 = i5 + 1;
                    bArr[i5] = BYTE_BACKSLASH;
                    i5 += 2;
                    bArr[i9] = (byte) i8;
                } else {
                    i5 = _writeGenericEscape(c3, i5);
                }
            } else if (c3 > i6) {
                i5 = _writeGenericEscape(c3, i5);
            } else if (c3 <= 2047) {
                int i10 = i5 + 1;
                bArr[i5] = (byte) ((c3 >> 6) | 192);
                i5 += 2;
                bArr[i10] = (byte) ((c3 & '?') | 128);
            } else {
                i5 = _outputMultiByteChar(c3, i5);
            }
            i3 = i7;
        }
        this._outputTail = i5;
    }

    private final void _writeStringSegments(String str, boolean z2) throws IOException {
        if (z2) {
            if (this._outputTail >= this._outputEnd) {
                _flushBuffer();
            }
            byte[] bArr = this._outputBuffer;
            int i3 = this._outputTail;
            this._outputTail = i3 + 1;
            bArr[i3] = this._quoteChar;
        }
        int length = str.length();
        int i4 = 0;
        while (length > 0) {
            int min = Math.min(this._outputMaxContiguous, length);
            if (this._outputTail + min > this._outputEnd) {
                _flushBuffer();
            }
            _writeStringSegment(str, i4, min);
            i4 += min;
            length -= min;
        }
        if (z2) {
            if (this._outputTail >= this._outputEnd) {
                _flushBuffer();
            }
            byte[] bArr2 = this._outputBuffer;
            int i5 = this._outputTail;
            this._outputTail = i5 + 1;
            bArr2[i5] = this._quoteChar;
        }
    }

    private final void _writeUTF8Segment(byte[] bArr, int i3, int i4) throws IOException, JsonGenerationException {
        int[] iArr = this._outputEscapes;
        int i5 = i3 + i4;
        int i6 = i3;
        while (i6 < i5) {
            int i7 = i6 + 1;
            byte b3 = bArr[i6];
            if (b3 < 0 || iArr[b3] == 0) {
                i6 = i7;
            } else {
                _writeUTF8Segment2(bArr, i3, i4);
                return;
            }
        }
        if (this._outputTail + i4 > this._outputEnd) {
            _flushBuffer();
        }
        System.arraycopy(bArr, i3, this._outputBuffer, this._outputTail, i4);
        this._outputTail += i4;
    }

    private final void _writeUTF8Segment2(byte[] bArr, int i3, int i4) throws IOException, JsonGenerationException {
        int i5;
        int i6;
        int i7 = this._outputTail;
        if ((i4 * 6) + i7 > this._outputEnd) {
            _flushBuffer();
            i7 = this._outputTail;
        }
        byte[] bArr2 = this._outputBuffer;
        int[] iArr = this._outputEscapes;
        int i8 = i4 + i3;
        while (i3 < i8) {
            int i9 = i3 + 1;
            byte b3 = bArr[i3];
            if (b3 < 0 || (i6 = iArr[b3]) == 0) {
                bArr2[i5] = b3;
                i3 = i9;
                i5++;
            } else {
                if (i6 > 0) {
                    int i10 = i5 + 1;
                    bArr2[i5] = BYTE_BACKSLASH;
                    i5 += 2;
                    bArr2[i10] = (byte) i6;
                } else {
                    i5 = _writeGenericEscape(b3, i5);
                }
                i3 = i9;
            }
        }
        this._outputTail = i5;
    }

    private final void _writeUTF8Segments(byte[] bArr, int i3, int i4) throws IOException, JsonGenerationException {
        do {
            int min = Math.min(this._outputMaxContiguous, i4);
            _writeUTF8Segment(bArr, i3, min);
            i3 += min;
            i4 -= min;
        } while (i4 > 0);
    }

    private final void _writeUnq(SerializableString serializableString) throws IOException {
        int appendQuotedUTF8 = serializableString.appendQuotedUTF8(this._outputBuffer, this._outputTail);
        if (appendQuotedUTF8 < 0) {
            _writeBytes(serializableString.asQuotedUTF8());
        } else {
            this._outputTail += appendQuotedUTF8;
        }
    }

    private byte[] getHexBytes() {
        return this._cfgWriteHexUppercase ? HEX_BYTES_UPPER : HEX_BYTES_LOWER;
    }

    public final void _flushBuffer() throws IOException {
        int i3 = this._outputTail;
        if (i3 > 0) {
            this._outputTail = 0;
            this._outputStream.write(this._outputBuffer, 0, i3);
        }
    }

    public final void _outputSurrogates(int i3, int i4) throws IOException {
        int _decodeSurrogate = _decodeSurrogate(i3, i4);
        if (this._outputTail + 4 > this._outputEnd) {
            _flushBuffer();
        }
        byte[] bArr = this._outputBuffer;
        int i5 = this._outputTail;
        int i6 = i5 + 1;
        this._outputTail = i6;
        bArr[i5] = (byte) ((_decodeSurrogate >> 18) | 240);
        int i7 = i5 + 2;
        this._outputTail = i7;
        bArr[i6] = (byte) (((_decodeSurrogate >> 12) & 63) | 128);
        int i8 = i5 + 3;
        this._outputTail = i8;
        bArr[i7] = (byte) (((_decodeSurrogate >> 6) & 63) | 128);
        this._outputTail = i5 + 4;
        bArr[i8] = (byte) ((_decodeSurrogate & 63) | 128);
    }

    public void _releaseBuffers() {
        byte[] bArr = this._outputBuffer;
        if (bArr != null && this._bufferRecyclable) {
            this._outputBuffer = null;
            this._ioContext.releaseWriteEncodingBuffer(bArr);
        }
        char[] cArr = this._charBuffer;
        if (cArr != null) {
            this._charBuffer = null;
            this._ioContext.releaseConcatBuffer(cArr);
        }
    }

    public final void _verifyValueWrite(String str) throws IOException {
        byte b3;
        int writeValue = this._writeContext.writeValue();
        if (this._cfgPrettyPrinter != null) {
            _verifyPrettyValueWrite(str, writeValue);
            return;
        }
        if (writeValue == 1) {
            b3 = BYTE_COMMA;
        } else if (writeValue == 2) {
            b3 = BYTE_COLON;
        } else if (writeValue == 3) {
            SerializableString serializableString = this._rootValueSeparator;
            if (serializableString != null) {
                byte[] asUnquotedUTF8 = serializableString.asUnquotedUTF8();
                if (asUnquotedUTF8.length > 0) {
                    _writeBytes(asUnquotedUTF8);
                    return;
                }
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
        byte[] bArr = this._outputBuffer;
        int i3 = this._outputTail;
        this._outputTail = i3 + 1;
        bArr[i3] = b3;
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
                    byte[] bArr2 = this._outputBuffer;
                    int i9 = encodeBase64Chunk + 1;
                    this._outputTail = i9;
                    bArr2[encodeBase64Chunk] = BYTE_BACKSLASH;
                    this._outputTail = encodeBase64Chunk + 2;
                    bArr2[i9] = 110;
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

    public final void _writePPFieldName(String str) throws IOException {
        int writeFieldName = this._writeContext.writeFieldName(str);
        if (writeFieldName == 4) {
            _reportError("Can not write a field name, expecting a value");
        }
        if (writeFieldName == 1) {
            this._cfgPrettyPrinter.writeObjectEntrySeparator(this);
        } else {
            this._cfgPrettyPrinter.beforeObjectEntries(this);
        }
        if (this._cfgUnqNames) {
            _writeStringSegments(str, false);
            return;
        }
        int length = str.length();
        if (length > this._charBufferLength) {
            _writeStringSegments(str, true);
            return;
        }
        if (this._outputTail >= this._outputEnd) {
            _flushBuffer();
        }
        byte[] bArr = this._outputBuffer;
        int i3 = this._outputTail;
        this._outputTail = i3 + 1;
        bArr[i3] = this._quoteChar;
        str.getChars(0, length, this._charBuffer, 0);
        if (length <= this._outputMaxContiguous) {
            if (this._outputTail + length > this._outputEnd) {
                _flushBuffer();
            }
            _writeStringSegment(this._charBuffer, 0, length);
        } else {
            _writeStringSegments(this._charBuffer, 0, length);
        }
        if (this._outputTail >= this._outputEnd) {
            _flushBuffer();
        }
        byte[] bArr2 = this._outputBuffer;
        int i4 = this._outputTail;
        this._outputTail = i4 + 1;
        bArr2[i4] = this._quoteChar;
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
        this._outputTail = 0;
        if (this._outputStream != null) {
            try {
                if (!this._ioContext.isResourceManaged()) {
                    if (!isEnabled(JsonGenerator.Feature.AUTO_CLOSE_TARGET)) {
                        if (isEnabled(JsonGenerator.Feature.FLUSH_PASSED_TO_STREAM)) {
                            this._outputStream.flush();
                        }
                    }
                }
                this._outputStream.close();
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
        if (this._outputStream != null && isEnabled(JsonGenerator.Feature.FLUSH_PASSED_TO_STREAM)) {
            this._outputStream.flush();
        }
    }

    public int getOutputBuffered() {
        return this._outputTail;
    }

    public Object getOutputTarget() {
        return this._outputStream;
    }

    public void writeBinary(Base64Variant base64Variant, byte[] bArr, int i3, int i4) throws IOException, JsonGenerationException {
        _checkRangeBoundsForByteArray(bArr, i3, i4);
        _verifyValueWrite("write a binary value");
        if (this._outputTail >= this._outputEnd) {
            _flushBuffer();
        }
        byte[] bArr2 = this._outputBuffer;
        int i5 = this._outputTail;
        this._outputTail = i5 + 1;
        bArr2[i5] = this._quoteChar;
        _writeBinary(base64Variant, bArr, i3, i4 + i3);
        if (this._outputTail >= this._outputEnd) {
            _flushBuffer();
        }
        byte[] bArr3 = this._outputBuffer;
        int i6 = this._outputTail;
        this._outputTail = i6 + 1;
        bArr3[i6] = this._quoteChar;
    }

    public void writeBoolean(boolean z2) throws IOException {
        _verifyValueWrite("write a boolean value");
        if (this._outputTail + 5 >= this._outputEnd) {
            _flushBuffer();
        }
        byte[] bArr = z2 ? TRUE_BYTES : FALSE_BYTES;
        int length = bArr.length;
        System.arraycopy(bArr, 0, this._outputBuffer, this._outputTail, length);
        this._outputTail += length;
    }

    public final void writeEndArray() throws IOException {
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
            byte[] bArr = this._outputBuffer;
            int i3 = this._outputTail;
            this._outputTail = i3 + 1;
            bArr[i3] = BYTE_RBRACKET;
        }
        this._writeContext = this._writeContext.clearAndGetParent();
    }

    public final void writeEndObject() throws IOException {
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
            byte[] bArr = this._outputBuffer;
            int i3 = this._outputTail;
            this._outputTail = i3 + 1;
            bArr[i3] = BYTE_RCURLY;
        }
        this._writeContext = this._writeContext.clearAndGetParent();
    }

    public void writeFieldName(String str) throws IOException {
        if (this._cfgPrettyPrinter != null) {
            _writePPFieldName(str);
            return;
        }
        int writeFieldName = this._writeContext.writeFieldName(str);
        if (writeFieldName == 4) {
            _reportError("Can not write a field name, expecting a value");
        }
        if (writeFieldName == 1) {
            if (this._outputTail >= this._outputEnd) {
                _flushBuffer();
            }
            byte[] bArr = this._outputBuffer;
            int i3 = this._outputTail;
            this._outputTail = i3 + 1;
            bArr[i3] = BYTE_COMMA;
        }
        if (this._cfgUnqNames) {
            _writeStringSegments(str, false);
            return;
        }
        int length = str.length();
        if (length > this._charBufferLength) {
            _writeStringSegments(str, true);
            return;
        }
        if (this._outputTail >= this._outputEnd) {
            _flushBuffer();
        }
        byte[] bArr2 = this._outputBuffer;
        int i4 = this._outputTail;
        int i5 = i4 + 1;
        this._outputTail = i5;
        bArr2[i4] = this._quoteChar;
        if (length <= this._outputMaxContiguous) {
            if (i5 + length > this._outputEnd) {
                _flushBuffer();
            }
            _writeStringSegment(str, 0, length);
        } else {
            _writeStringSegments(str, 0, length);
        }
        if (this._outputTail >= this._outputEnd) {
            _flushBuffer();
        }
        byte[] bArr3 = this._outputBuffer;
        int i6 = this._outputTail;
        this._outputTail = i6 + 1;
        bArr3[i6] = this._quoteChar;
    }

    public void writeNull() throws IOException {
        _verifyValueWrite("write a null");
        _writeNull();
    }

    public void writeNumber(short s3) throws IOException {
        _verifyValueWrite("write a number");
        if (this._outputTail + 6 >= this._outputEnd) {
            _flushBuffer();
        }
        if (this._cfgNumbersAsStrings) {
            _writeQuotedShort(s3);
        } else {
            this._outputTail = NumberOutput.outputInt((int) s3, this._outputBuffer, this._outputTail);
        }
    }

    public void writeRaw(String str) throws IOException {
        int length = str.length();
        char[] cArr = this._charBuffer;
        if (length <= cArr.length) {
            str.getChars(0, length, cArr, 0);
            writeRaw(cArr, 0, length);
            return;
        }
        writeRaw(str, 0, length);
    }

    public void writeRawUTF8String(byte[] bArr, int i3, int i4) throws IOException {
        _checkRangeBoundsForByteArray(bArr, i3, i4);
        _verifyValueWrite("write a string");
        if (this._outputTail >= this._outputEnd) {
            _flushBuffer();
        }
        byte[] bArr2 = this._outputBuffer;
        int i5 = this._outputTail;
        this._outputTail = i5 + 1;
        bArr2[i5] = this._quoteChar;
        _writeBytes(bArr, i3, i4);
        if (this._outputTail >= this._outputEnd) {
            _flushBuffer();
        }
        byte[] bArr3 = this._outputBuffer;
        int i6 = this._outputTail;
        this._outputTail = i6 + 1;
        bArr3[i6] = this._quoteChar;
    }

    public void writeRawValue(SerializableString serializableString) throws IOException {
        _verifyValueWrite("write a raw (unencoded) value");
        int appendUnquotedUTF8 = serializableString.appendUnquotedUTF8(this._outputBuffer, this._outputTail);
        if (appendUnquotedUTF8 < 0) {
            _writeBytes(serializableString.asUnquotedUTF8());
        } else {
            this._outputTail += appendUnquotedUTF8;
        }
    }

    public final void writeStartArray() throws IOException {
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
        byte[] bArr = this._outputBuffer;
        int i3 = this._outputTail;
        this._outputTail = i3 + 1;
        bArr[i3] = BYTE_LBRACKET;
    }

    public final void writeStartObject() throws IOException {
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
        byte[] bArr = this._outputBuffer;
        int i3 = this._outputTail;
        this._outputTail = i3 + 1;
        bArr[i3] = BYTE_LCURLY;
    }

    public void writeString(String str) throws IOException {
        _verifyValueWrite("write a string");
        if (str == null) {
            _writeNull();
            return;
        }
        int length = str.length();
        if (length > this._outputMaxContiguous) {
            _writeStringSegments(str, true);
            return;
        }
        if (this._outputTail + length >= this._outputEnd) {
            _flushBuffer();
        }
        byte[] bArr = this._outputBuffer;
        int i3 = this._outputTail;
        this._outputTail = i3 + 1;
        bArr[i3] = this._quoteChar;
        _writeStringSegment(str, 0, length);
        if (this._outputTail >= this._outputEnd) {
            _flushBuffer();
        }
        byte[] bArr2 = this._outputBuffer;
        int i4 = this._outputTail;
        this._outputTail = i4 + 1;
        bArr2[i4] = this._quoteChar;
    }

    public void writeUTF8String(byte[] bArr, int i3, int i4) throws IOException {
        _checkRangeBoundsForByteArray(bArr, i3, i4);
        _verifyValueWrite("write a string");
        if (this._outputTail >= this._outputEnd) {
            _flushBuffer();
        }
        byte[] bArr2 = this._outputBuffer;
        int i5 = this._outputTail;
        this._outputTail = i5 + 1;
        bArr2[i5] = this._quoteChar;
        if (i4 <= this._outputMaxContiguous) {
            _writeUTF8Segment(bArr, i3, i4);
        } else {
            _writeUTF8Segments(bArr, i3, i4);
        }
        if (this._outputTail >= this._outputEnd) {
            _flushBuffer();
        }
        byte[] bArr3 = this._outputBuffer;
        int i6 = this._outputTail;
        this._outputTail = i6 + 1;
        bArr3[i6] = this._quoteChar;
    }

    private final void _writeBytes(byte[] bArr, int i3, int i4) throws IOException {
        if (this._outputTail + i4 > this._outputEnd) {
            _flushBuffer();
            if (i4 > 512) {
                this._outputStream.write(bArr, i3, i4);
                return;
            }
        }
        System.arraycopy(bArr, i3, this._outputBuffer, this._outputTail, i4);
        this._outputTail += i4;
    }

    public void writeNumber(int i3) throws IOException {
        _verifyValueWrite("write a number");
        if (this._outputTail + 11 >= this._outputEnd) {
            _flushBuffer();
        }
        if (this._cfgNumbersAsStrings) {
            _writeQuotedInt(i3);
        } else {
            this._outputTail = NumberOutput.outputInt(i3, this._outputBuffer, this._outputTail);
        }
    }

    public void writeRaw(String str, int i3, int i4) throws IOException {
        char c3;
        _checkRangeBoundsForString(str, i3, i4);
        char[] cArr = this._charBuffer;
        int length = cArr.length;
        if (i4 <= length) {
            str.getChars(i3, i3 + i4, cArr, 0);
            writeRaw(cArr, 0, i4);
            return;
        }
        int i5 = this._outputEnd;
        int min = Math.min(length, (i5 >> 2) + (i5 >> 4));
        int i6 = min * 3;
        while (i4 > 0) {
            int min2 = Math.min(min, i4);
            str.getChars(i3, i3 + min2, cArr, 0);
            if (this._outputTail + i6 > this._outputEnd) {
                _flushBuffer();
            }
            if (min2 > 1 && (c3 = cArr[min2 - 1]) >= 55296 && c3 <= 56319) {
                min2--;
            }
            _writeRawSegment(cArr, 0, min2);
            i3 += min2;
            i4 -= min2;
        }
    }

    private void _writeQuotedRaw(char[] cArr, int i3, int i4) throws IOException {
        if (this._outputTail >= this._outputEnd) {
            _flushBuffer();
        }
        byte[] bArr = this._outputBuffer;
        int i5 = this._outputTail;
        this._outputTail = i5 + 1;
        bArr[i5] = this._quoteChar;
        writeRaw(cArr, i3, i4);
        if (this._outputTail >= this._outputEnd) {
            _flushBuffer();
        }
        byte[] bArr2 = this._outputBuffer;
        int i6 = this._outputTail;
        this._outputTail = i6 + 1;
        bArr2[i6] = this._quoteChar;
    }

    public final void writeStartArray(Object obj) throws IOException {
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
        byte[] bArr = this._outputBuffer;
        int i3 = this._outputTail;
        this._outputTail = i3 + 1;
        bArr[i3] = BYTE_LBRACKET;
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
        byte[] bArr = this._outputBuffer;
        int i3 = this._outputTail;
        this._outputTail = i3 + 1;
        bArr[i3] = BYTE_LCURLY;
    }

    /* JADX INFO: finally extract failed */
    public int writeBinary(Base64Variant base64Variant, InputStream inputStream, int i3) throws IOException, JsonGenerationException {
        _verifyValueWrite("write a binary value");
        if (this._outputTail >= this._outputEnd) {
            _flushBuffer();
        }
        byte[] bArr = this._outputBuffer;
        int i4 = this._outputTail;
        this._outputTail = i4 + 1;
        bArr[i4] = this._quoteChar;
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
        byte[] bArr2 = this._outputBuffer;
        int i5 = this._outputTail;
        this._outputTail = i5 + 1;
        bArr2[i5] = this._quoteChar;
        return i3;
    }

    private final void _writeStringSegments(char[] cArr, int i3, int i4) throws IOException {
        do {
            int min = Math.min(this._outputMaxContiguous, i4);
            if (this._outputTail + min > this._outputEnd) {
                _flushBuffer();
            }
            _writeStringSegment(cArr, i3, min);
            i3 += min;
            i4 -= min;
        } while (i4 > 0);
    }

    public UTF8JsonGenerator(IOContext iOContext, int i3, ObjectCodec objectCodec, OutputStream outputStream, char c3, byte[] bArr, int i4, boolean z2) {
        super(iOContext, i3, objectCodec);
        this._outputStream = outputStream;
        this._quoteChar = (byte) c3;
        if (c3 != '\"') {
            this._outputEscapes = CharTypes.get7BitOutputEscapes(c3);
        }
        this._bufferRecyclable = z2;
        this._outputTail = i4;
        this._outputBuffer = bArr;
        int length = bArr.length;
        this._outputEnd = length;
        this._outputMaxContiguous = length >> 3;
        char[] allocConcatBuffer = iOContext.allocConcatBuffer();
        this._charBuffer = allocConcatBuffer;
        this._charBufferLength = allocConcatBuffer.length;
    }

    private final void _writeStringSegment(String str, int i3, int i4) throws IOException {
        int i5 = i4 + i3;
        int i6 = this._outputTail;
        byte[] bArr = this._outputBuffer;
        int[] iArr = this._outputEscapes;
        while (i3 < i5) {
            char charAt = str.charAt(i3);
            if (charAt > 127 || iArr[charAt] != 0) {
                break;
            }
            bArr[i6] = (byte) charAt;
            i3++;
            i6++;
        }
        this._outputTail = i6;
        if (i3 >= i5) {
            return;
        }
        if (this._characterEscapes != null) {
            _writeCustomStringSegment2(str, i3, i5);
        } else if (this._maximumNonEscapedChar == 0) {
            _writeStringSegment2(str, i3, i5);
        } else {
            _writeStringSegmentASCII2(str, i3, i5);
        }
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

    public void writeString(Reader reader, int i3) throws IOException {
        _verifyValueWrite("write a string");
        if (reader == null) {
            _reportError("null reader");
            return;
        }
        int i4 = i3 >= 0 ? i3 : Integer.MAX_VALUE;
        char[] cArr = this._charBuffer;
        if (this._outputTail >= this._outputEnd) {
            _flushBuffer();
        }
        byte[] bArr = this._outputBuffer;
        int i5 = this._outputTail;
        this._outputTail = i5 + 1;
        bArr[i5] = this._quoteChar;
        while (i4 > 0) {
            int read = reader.read(cArr, 0, Math.min(i4, cArr.length));
            if (read <= 0) {
                break;
            }
            if (this._outputTail + i3 >= this._outputEnd) {
                _flushBuffer();
            }
            _writeStringSegments(cArr, 0, read);
            i4 -= read;
        }
        if (this._outputTail >= this._outputEnd) {
            _flushBuffer();
        }
        byte[] bArr2 = this._outputBuffer;
        int i6 = this._outputTail;
        this._outputTail = i6 + 1;
        bArr2[i6] = this._quoteChar;
        if (i4 > 0 && i3 >= 0) {
            _reportError("Didn't read enough from reader");
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
        byte[] bArr = this._outputBuffer;
        int i4 = this._outputTail;
        this._outputTail = i4 + 1;
        bArr[i4] = BYTE_LBRACKET;
    }

    private final void _writeStringSegment2(String str, int i3, int i4) throws IOException {
        if (a.c(i4, i3, 6, this._outputTail) > this._outputEnd) {
            _flushBuffer();
        }
        int i5 = this._outputTail;
        byte[] bArr = this._outputBuffer;
        int[] iArr = this._outputEscapes;
        while (i3 < i4) {
            int i6 = i3 + 1;
            char charAt = str.charAt(i3);
            if (charAt <= 127) {
                int i7 = iArr[charAt];
                if (i7 == 0) {
                    bArr[i5] = (byte) charAt;
                    i3 = i6;
                    i5++;
                } else if (i7 > 0) {
                    int i8 = i5 + 1;
                    bArr[i5] = BYTE_BACKSLASH;
                    i5 += 2;
                    bArr[i8] = (byte) i7;
                } else {
                    i5 = _writeGenericEscape(charAt, i5);
                }
            } else if (charAt <= 2047) {
                int i9 = i5 + 1;
                bArr[i5] = (byte) ((charAt >> 6) | 192);
                i5 += 2;
                bArr[i9] = (byte) ((charAt & '?') | 128);
            } else {
                i5 = _outputMultiByteChar(charAt, i5);
            }
            i3 = i6;
        }
        this._outputTail = i5;
    }

    private final void _writeStringSegments(String str, int i3, int i4) throws IOException {
        do {
            int min = Math.min(this._outputMaxContiguous, i4);
            if (this._outputTail + min > this._outputEnd) {
                _flushBuffer();
            }
            _writeStringSegment(str, i3, min);
            i3 += min;
            i4 -= min;
        } while (i4 > 0);
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
                byte[] bArr2 = this._outputBuffer;
                int i11 = encodeBase64Chunk + 1;
                this._outputTail = i11;
                bArr2[encodeBase64Chunk] = BYTE_BACKSLASH;
                this._outputTail = encodeBase64Chunk + 2;
                bArr2[i11] = 110;
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

    private final void _writeStringSegmentASCII2(String str, int i3, int i4) throws IOException {
        if (a.c(i4, i3, 6, this._outputTail) > this._outputEnd) {
            _flushBuffer();
        }
        int i5 = this._outputTail;
        byte[] bArr = this._outputBuffer;
        int[] iArr = this._outputEscapes;
        int i6 = this._maximumNonEscapedChar;
        while (i3 < i4) {
            int i7 = i3 + 1;
            char charAt = str.charAt(i3);
            if (charAt <= 127) {
                int i8 = iArr[charAt];
                if (i8 == 0) {
                    bArr[i5] = (byte) charAt;
                    i3 = i7;
                    i5++;
                } else if (i8 > 0) {
                    int i9 = i5 + 1;
                    bArr[i5] = BYTE_BACKSLASH;
                    i5 += 2;
                    bArr[i9] = (byte) i8;
                } else {
                    i5 = _writeGenericEscape(charAt, i5);
                }
            } else if (charAt > i6) {
                i5 = _writeGenericEscape(charAt, i5);
            } else if (charAt <= 2047) {
                int i10 = i5 + 1;
                bArr[i5] = (byte) ((charAt >> 6) | 192);
                i5 += 2;
                bArr[i10] = (byte) ((charAt & '?') | 128);
            } else {
                i5 = _outputMultiByteChar(charAt, i5);
            }
            i3 = i7;
        }
        this._outputTail = i5;
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

    public void writeRaw(SerializableString serializableString) throws IOException {
        int appendUnquotedUTF8 = serializableString.appendUnquotedUTF8(this._outputBuffer, this._outputTail);
        if (appendUnquotedUTF8 < 0) {
            _writeBytes(serializableString.asUnquotedUTF8());
        } else {
            this._outputTail += appendUnquotedUTF8;
        }
    }

    public final void _writePPFieldName(SerializableString serializableString) throws IOException {
        int writeFieldName = this._writeContext.writeFieldName(serializableString.getValue());
        if (writeFieldName == 4) {
            _reportError("Can not write a field name, expecting a value");
        }
        if (writeFieldName == 1) {
            this._cfgPrettyPrinter.writeObjectEntrySeparator(this);
        } else {
            this._cfgPrettyPrinter.beforeObjectEntries(this);
        }
        boolean z2 = this._cfgUnqNames;
        if (!z2) {
            if (this._outputTail >= this._outputEnd) {
                _flushBuffer();
            }
            byte[] bArr = this._outputBuffer;
            int i3 = this._outputTail;
            this._outputTail = i3 + 1;
            bArr[i3] = this._quoteChar;
        }
        int appendQuotedUTF8 = serializableString.appendQuotedUTF8(this._outputBuffer, this._outputTail);
        if (appendQuotedUTF8 < 0) {
            _writeBytes(serializableString.asQuotedUTF8());
        } else {
            this._outputTail += appendQuotedUTF8;
        }
        if (!z2) {
            if (this._outputTail >= this._outputEnd) {
                _flushBuffer();
            }
            byte[] bArr2 = this._outputBuffer;
            int i4 = this._outputTail;
            this._outputTail = i4 + 1;
            bArr2[i4] = this._quoteChar;
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x001f, code lost:
        r7 = r7 + 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0023, code lost:
        if (r0 >= 2048) goto L_0x0040;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0025, code lost:
        r1 = r5._outputBuffer;
        r2 = r5._outputTail;
        r3 = r2 + 1;
        r5._outputTail = r3;
        r1[r2] = (byte) ((r0 >> 6) | 192);
        r5._outputTail = r2 + 2;
        r1[r3] = (byte) ((r0 & '?') | 128);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0040, code lost:
        r7 = _outputRawMultiByteChar(r0, r6, r7, r8);
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void writeRaw(char[] r6, int r7, int r8) throws java.io.IOException {
        /*
            r5 = this;
            r5._checkRangeBoundsForCharArray(r6, r7, r8)
            int r0 = r8 + r8
            int r0 = r0 + r8
            int r1 = r5._outputTail
            int r1 = r1 + r0
            int r2 = r5._outputEnd
            if (r1 <= r2) goto L_0x0016
            if (r2 >= r0) goto L_0x0013
            r5._writeSegmentedRaw(r6, r7, r8)
            return
        L_0x0013:
            r5._flushBuffer()
        L_0x0016:
            int r8 = r8 + r7
        L_0x0017:
            if (r7 >= r8) goto L_0x0054
        L_0x0019:
            char r0 = r6[r7]
            r1 = 127(0x7f, float:1.78E-43)
            if (r0 <= r1) goto L_0x0045
            int r7 = r7 + 1
            r1 = 2048(0x800, float:2.87E-42)
            if (r0 >= r1) goto L_0x0040
            byte[] r1 = r5._outputBuffer
            int r2 = r5._outputTail
            int r3 = r2 + 1
            r5._outputTail = r3
            int r4 = r0 >> 6
            r4 = r4 | 192(0xc0, float:2.69E-43)
            byte r4 = (byte) r4
            r1[r2] = r4
            int r2 = r2 + 2
            r5._outputTail = r2
            r0 = r0 & 63
            r0 = r0 | 128(0x80, float:1.794E-43)
            byte r0 = (byte) r0
            r1[r3] = r0
            goto L_0x0017
        L_0x0040:
            int r7 = r5._outputRawMultiByteChar(r0, r6, r7, r8)
            goto L_0x0017
        L_0x0045:
            byte[] r1 = r5._outputBuffer
            int r2 = r5._outputTail
            int r3 = r2 + 1
            r5._outputTail = r3
            byte r0 = (byte) r0
            r1[r2] = r0
            int r7 = r7 + 1
            if (r7 < r8) goto L_0x0019
        L_0x0054:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.fasterxml.jackson.core.json.UTF8JsonGenerator.writeRaw(char[], int, int):void");
    }

    @Deprecated
    public UTF8JsonGenerator(IOContext iOContext, int i3, ObjectCodec objectCodec, OutputStream outputStream) {
        this(iOContext, i3, objectCodec, outputStream, '\"');
    }

    public void writeFieldName(SerializableString serializableString) throws IOException {
        if (this._cfgPrettyPrinter != null) {
            _writePPFieldName(serializableString);
            return;
        }
        int writeFieldName = this._writeContext.writeFieldName(serializableString.getValue());
        if (writeFieldName == 4) {
            _reportError("Can not write a field name, expecting a value");
        }
        if (writeFieldName == 1) {
            if (this._outputTail >= this._outputEnd) {
                _flushBuffer();
            }
            byte[] bArr = this._outputBuffer;
            int i3 = this._outputTail;
            this._outputTail = i3 + 1;
            bArr[i3] = BYTE_COMMA;
        }
        if (this._cfgUnqNames) {
            _writeUnq(serializableString);
            return;
        }
        if (this._outputTail >= this._outputEnd) {
            _flushBuffer();
        }
        byte[] bArr2 = this._outputBuffer;
        int i4 = this._outputTail;
        int i5 = i4 + 1;
        this._outputTail = i5;
        bArr2[i4] = this._quoteChar;
        int appendQuotedUTF8 = serializableString.appendQuotedUTF8(bArr2, i5);
        if (appendQuotedUTF8 < 0) {
            _writeBytes(serializableString.asQuotedUTF8());
        } else {
            this._outputTail += appendQuotedUTF8;
        }
        if (this._outputTail >= this._outputEnd) {
            _flushBuffer();
        }
        byte[] bArr3 = this._outputBuffer;
        int i6 = this._outputTail;
        this._outputTail = i6 + 1;
        bArr3[i6] = this._quoteChar;
    }

    public void writeNumber(double d2) throws IOException {
        if (this._cfgNumbersAsStrings || (NumberOutput.notFinite(d2) && JsonGenerator.Feature.QUOTE_NON_NUMERIC_NUMBERS.enabledIn(this._features))) {
            writeString(NumberOutput.toString(d2, isEnabled(JsonGenerator.Feature.USE_FAST_DOUBLE_WRITER)));
            return;
        }
        _verifyValueWrite("write a number");
        writeRaw(NumberOutput.toString(d2, isEnabled(JsonGenerator.Feature.USE_FAST_DOUBLE_WRITER)));
    }

    @Deprecated
    public UTF8JsonGenerator(IOContext iOContext, int i3, ObjectCodec objectCodec, OutputStream outputStream, byte[] bArr, int i4, boolean z2) {
        this(iOContext, i3, objectCodec, outputStream, '\"', bArr, i4, z2);
    }

    private final void _writeCustomStringSegment2(String str, int i3, int i4) throws IOException {
        if (a.c(i4, i3, 6, this._outputTail) > this._outputEnd) {
            _flushBuffer();
        }
        int i5 = this._outputTail;
        byte[] bArr = this._outputBuffer;
        int[] iArr = this._outputEscapes;
        int i6 = this._maximumNonEscapedChar;
        if (i6 <= 0) {
            i6 = 65535;
        }
        CharacterEscapes characterEscapes = this._characterEscapes;
        while (i3 < i4) {
            int i7 = i3 + 1;
            char charAt = str.charAt(i3);
            if (charAt <= 127) {
                int i8 = iArr[charAt];
                if (i8 == 0) {
                    bArr[i5] = (byte) charAt;
                    i3 = i7;
                    i5++;
                } else if (i8 > 0) {
                    int i9 = i5 + 1;
                    bArr[i5] = BYTE_BACKSLASH;
                    i5 += 2;
                    bArr[i9] = (byte) i8;
                } else if (i8 == -2) {
                    SerializableString escapeSequence = characterEscapes.getEscapeSequence(charAt);
                    if (escapeSequence == null) {
                        _reportError("Invalid custom escape definitions; custom escape not found for character code 0x" + Integer.toHexString(charAt) + ", although was supposed to have one");
                    }
                    i5 = _writeCustomEscape(bArr, i5, escapeSequence, i4 - i7);
                } else {
                    i5 = _writeGenericEscape(charAt, i5);
                }
            } else if (charAt > i6) {
                i5 = _writeGenericEscape(charAt, i5);
            } else {
                SerializableString escapeSequence2 = characterEscapes.getEscapeSequence(charAt);
                if (escapeSequence2 != null) {
                    i5 = _writeCustomEscape(bArr, i5, escapeSequence2, i4 - i7);
                } else if (charAt <= 2047) {
                    int i10 = i5 + 1;
                    bArr[i5] = (byte) ((charAt >> 6) | 192);
                    i5 += 2;
                    bArr[i10] = (byte) ((charAt & '?') | 128);
                } else {
                    i5 = _outputMultiByteChar(charAt, i5);
                }
            }
            i3 = i7;
        }
        this._outputTail = i5;
    }

    public void writeString(char[] cArr, int i3, int i4) throws IOException {
        _verifyValueWrite("write a string");
        if (this._outputTail >= this._outputEnd) {
            _flushBuffer();
        }
        byte[] bArr = this._outputBuffer;
        int i5 = this._outputTail;
        int i6 = i5 + 1;
        this._outputTail = i6;
        bArr[i5] = this._quoteChar;
        if (i4 <= this._outputMaxContiguous) {
            if (i6 + i4 > this._outputEnd) {
                _flushBuffer();
            }
            _writeStringSegment(cArr, i3, i4);
        } else {
            _writeStringSegments(cArr, i3, i4);
        }
        if (this._outputTail >= this._outputEnd) {
            _flushBuffer();
        }
        byte[] bArr2 = this._outputBuffer;
        int i7 = this._outputTail;
        this._outputTail = i7 + 1;
        bArr2[i7] = this._quoteChar;
    }

    public void writeNumber(float f2) throws IOException {
        if (this._cfgNumbersAsStrings || (NumberOutput.notFinite(f2) && JsonGenerator.Feature.QUOTE_NON_NUMERIC_NUMBERS.enabledIn(this._features))) {
            writeString(NumberOutput.toString(f2, isEnabled(JsonGenerator.Feature.USE_FAST_DOUBLE_WRITER)));
            return;
        }
        _verifyValueWrite("write a number");
        writeRaw(NumberOutput.toString(f2, isEnabled(JsonGenerator.Feature.USE_FAST_DOUBLE_WRITER)));
    }

    public void writeRaw(char c3) throws IOException {
        if (this._outputTail + 3 >= this._outputEnd) {
            _flushBuffer();
        }
        byte[] bArr = this._outputBuffer;
        if (c3 <= 127) {
            int i3 = this._outputTail;
            this._outputTail = i3 + 1;
            bArr[i3] = (byte) c3;
        } else if (c3 < 2048) {
            int i4 = this._outputTail;
            int i5 = i4 + 1;
            this._outputTail = i5;
            bArr[i4] = (byte) ((c3 >> 6) | 192);
            this._outputTail = i4 + 2;
            bArr[i5] = (byte) ((c3 & '?') | 128);
        } else {
            _outputRawMultiByteChar(c3, (char[]) null, 0, 0);
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
                byte[] bArr3 = this._outputBuffer;
                int i10 = encodeBase64Chunk + 1;
                this._outputTail = i10;
                bArr3[encodeBase64Chunk] = BYTE_BACKSLASH;
                this._outputTail = encodeBase64Chunk + 2;
                bArr3[i10] = 110;
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

    public final void writeString(SerializableString serializableString) throws IOException {
        _verifyValueWrite("write a string");
        if (this._outputTail >= this._outputEnd) {
            _flushBuffer();
        }
        byte[] bArr = this._outputBuffer;
        int i3 = this._outputTail;
        int i4 = i3 + 1;
        this._outputTail = i4;
        bArr[i3] = this._quoteChar;
        int appendQuotedUTF8 = serializableString.appendQuotedUTF8(bArr, i4);
        if (appendQuotedUTF8 < 0) {
            _writeBytes(serializableString.asQuotedUTF8());
        } else {
            this._outputTail += appendQuotedUTF8;
        }
        if (this._outputTail >= this._outputEnd) {
            _flushBuffer();
        }
        byte[] bArr2 = this._outputBuffer;
        int i5 = this._outputTail;
        this._outputTail = i5 + 1;
        bArr2[i5] = this._quoteChar;
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

    public void writeNumber(char[] cArr, int i3, int i4) throws IOException {
        _verifyValueWrite("write a number");
        if (this._cfgNumbersAsStrings) {
            _writeQuotedRaw(cArr, i3, i4);
        } else {
            writeRaw(cArr, i3, i4);
        }
    }
}
