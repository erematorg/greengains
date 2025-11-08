package com.fasterxml.jackson.dataformat.cbor;

import androidx.camera.camera2.internal.C0118y;
import androidx.camera.core.impl.i;
import com.fasterxml.jackson.core.Base64Variant;
import com.fasterxml.jackson.core.FormatFeature;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonStreamContext;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.core.PrettyPrinter;
import com.fasterxml.jackson.core.SerializableString;
import com.fasterxml.jackson.core.StreamWriteCapability;
import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.core.base.GeneratorBase;
import com.fasterxml.jackson.core.io.IOContext;
import com.fasterxml.jackson.core.json.ByteSourceJsonBootstrapper;
import com.fasterxml.jackson.core.json.DupDetector;
import com.fasterxml.jackson.core.json.JsonWriteContext;
import com.fasterxml.jackson.core.util.JacksonFeatureSet;
import com.google.common.base.Ascii;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Arrays;

public class CBORGenerator extends GeneratorBase {
    protected static final int BYTE_BUFFER_FOR_OUTPUT = 16000;
    private static final int INDEFINITE_LENGTH = -2;
    private static final int MAX_LONG_STRING_BYTES = 11991;
    private static final int MAX_LONG_STRING_CHARS = 3996;
    private static final int MAX_MEDIUM_STRING_BYTES = 768;
    private static final int MAX_MEDIUM_STRING_CHARS = 255;
    private static final int MAX_SHORT_STRING_BYTES = 71;
    private static final int MAX_SHORT_STRING_CHARS = 23;
    private static final int MIN_BUFFER_LENGTH = 770;
    private static final int[] NO_INTS = new int[0];
    protected static final int REPLACEMENT_CHAR = 65533;
    protected boolean _bufferRecyclable;
    protected int _bytesWritten;
    protected boolean _cfgMinimalInts;
    protected char[] _charBuffer;
    protected final int _charBufferLength;
    protected int _currentRemainingElements = -2;
    protected int[] _elementCounts = NO_INTS;
    protected int _elementCountsPtr;
    protected int _formatFeatures;
    protected final IOContext _ioContext;
    protected final OutputStream _out;
    protected byte[] _outputBuffer;
    protected final int _outputEnd;
    protected int _outputTail = 0;
    protected CBORWriteContext _streamWriteContext;

    public enum Feature implements FormatFeature {
        WRITE_MINIMAL_INTS(true),
        WRITE_TYPE_HEADER(false),
        LENIENT_UTF_ENCODING(false);
        
        protected final boolean _defaultState;
        protected final int _mask;

        private Feature(boolean z2) {
            this._defaultState = z2;
            this._mask = 1 << ordinal();
        }

        public static int collectDefaults() {
            int i3 = 0;
            for (Feature feature : values()) {
                if (feature.enabledByDefault()) {
                    i3 |= feature.getMask();
                }
            }
            return i3;
        }

        public boolean enabledByDefault() {
            return this._defaultState;
        }

        public boolean enabledIn(int i3) {
            return (getMask() & i3) != 0;
        }

        public int getMask() {
            return this._mask;
        }
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public CBORGenerator(IOContext iOContext, int i3, int i4, ObjectCodec objectCodec, OutputStream outputStream) {
        super(i3, objectCodec, (JsonWriteContext) null);
        DupDetector dupDetector = null;
        this._streamWriteContext = CBORWriteContext.createRootContext(JsonGenerator.Feature.STRICT_DUPLICATE_DETECTION.enabledIn(i3) ? DupDetector.rootDetector((JsonGenerator) this) : dupDetector);
        this._formatFeatures = i4;
        this._cfgMinimalInts = Feature.WRITE_MINIMAL_INTS.enabledIn(i4);
        this._ioContext = iOContext;
        this._out = outputStream;
        this._bufferRecyclable = true;
        byte[] allocWriteEncodingBuffer = iOContext.allocWriteEncodingBuffer(16000);
        this._outputBuffer = allocWriteEncodingBuffer;
        int length = allocWriteEncodingBuffer.length;
        this._outputEnd = length;
        char[] allocConcatBuffer = iOContext.allocConcatBuffer();
        this._charBuffer = allocConcatBuffer;
        this._charBufferLength = allocConcatBuffer.length;
        if (length < MIN_BUFFER_LENGTH) {
            throw new IllegalStateException(C0118y.c(length, "Internal encoding buffer length (", ") too short, must be at least 770"));
        }
    }

    private int _appendReplacementChar(byte[] bArr, int i3) {
        bArr[i3] = ByteSourceJsonBootstrapper.UTF8_BOM_1;
        int i4 = i3 + 2;
        bArr[i3 + 1] = -65;
        int i5 = i3 + 3;
        bArr[i4] = -67;
        return i5;
    }

    private int _decodeAndWriteSurrogate(int i3, int i4, byte[] bArr, int i5) {
        int i6 = (i4 - 56320) + ((i3 - GeneratorBase.SURR1_FIRST) << 10) + 65536;
        bArr[i5] = (byte) ((i6 >> 18) | 240);
        bArr[i5 + 1] = (byte) (((i6 >> 12) & 63) | 128);
        int i7 = i5 + 3;
        bArr[i5 + 2] = (byte) (((i6 >> 6) & 63) | 128);
        int i8 = i5 + 4;
        bArr[i7] = (byte) ((i6 & 63) | 128);
        return i8;
    }

    private final int _encode(int i3, char[] cArr, int i4, int i5) throws IOException {
        byte[] bArr = this._outputBuffer;
        int i6 = i3;
        int i7 = i4;
        while (true) {
            char c3 = cArr[i7];
            if (c3 > 127) {
                return _shortUTF8Encode2(cArr, i7, i5, i6, i3);
            }
            int i8 = i6 + 1;
            bArr[i6] = (byte) c3;
            i7++;
            if (i7 >= i5) {
                return i8 - i3;
            }
            i6 = i8;
        }
    }

    private final int _encode2(int i3, int i4, String str, int i5, int i6) throws IOException {
        byte[] bArr = this._outputBuffer;
        while (i3 < i5) {
            int i7 = i3 + 1;
            char charAt = str.charAt(i3);
            if (charAt <= 127) {
                bArr[i4] = (byte) charAt;
                i4++;
            } else if (charAt < 2048) {
                int i8 = i4 + 1;
                bArr[i4] = (byte) ((charAt >> 6) | 192);
                i4 += 2;
                bArr[i8] = (byte) ((charAt & '?') | 128);
            } else if (charAt < 55296 || charAt > 57343) {
                bArr[i4] = (byte) ((charAt >> 12) | 224);
                int i9 = i4 + 2;
                bArr[i4 + 1] = (byte) (((charAt >> 6) & 63) | 128);
                i4 += 3;
                bArr[i9] = (byte) ((charAt & '?') | 128);
            } else if (charAt > 56319 || i7 >= i5) {
                i4 = _invalidSurrogateStart(charAt, bArr, i4);
            } else {
                char charAt2 = str.charAt(i7);
                if (charAt2 > 57343 || charAt2 < 56320) {
                    i4 = _invalidSurrogateEnd(charAt, charAt2, bArr, i4);
                } else {
                    i3 += 2;
                    i4 = _decodeAndWriteSurrogate(charAt, charAt2, bArr, i4);
                }
            }
            i3 = i7;
        }
        return i4 - i6;
    }

    private final void _ensureRoomForOutput(int i3) throws IOException {
        if (this._outputTail + i3 >= this._outputEnd) {
            _flushBuffer();
        }
    }

    private void _failSizedArrayOrObject() throws IOException {
        String typeDesc = this._streamWriteContext.typeDesc();
        _reportError(typeDesc + " size mismatch: number of element encoded is not equal to reported array/map size.");
    }

    private int _invalidSurrogateEnd(int i3, int i4, byte[] bArr, int i5) throws IOException {
        if (isEnabled(Feature.LENIENT_UTF_ENCODING)) {
            return _appendReplacementChar(bArr, i5);
        }
        _reportError(String.format("Invalid surrogate pair, starts with valid high surrogate (0x%04X) but ends with invalid low surrogate (0x%04X), not in valid range [0xDC00, 0xDFFF]", new Object[]{Integer.valueOf(i3), Integer.valueOf(i4)}));
        return 0;
    }

    private int _invalidSurrogateStart(int i3, byte[] bArr, int i4) throws IOException {
        if (isEnabled(Feature.LENIENT_UTF_ENCODING)) {
            return _appendReplacementChar(bArr, i4);
        }
        if (i3 <= 56319) {
            _reportError(String.format("Unmatched surrogate pair, starts with valid high surrogate (0x%04X) but ends without low surrogate", new Object[]{Integer.valueOf(i3)}));
        }
        _reportError(String.format("Invalid surrogate pair, starts with invalid high surrogate (0x%04X), not in valid range [0xD800, 0xDBFF]", new Object[]{Integer.valueOf(i3)}));
        return 0;
    }

    private final void _pushRemainingElements() {
        int[] iArr = this._elementCounts;
        if (iArr.length == this._elementCountsPtr) {
            this._elementCounts = Arrays.copyOf(iArr, iArr.length + 10);
        }
        int[] iArr2 = this._elementCounts;
        int i3 = this._elementCountsPtr;
        this._elementCountsPtr = i3 + 1;
        iArr2[i3] = this._currentRemainingElements;
    }

    private final int _shortUTF8Encode2(char[] cArr, int i3, int i4, int i5, int i6) throws IOException {
        byte[] bArr = this._outputBuffer;
        while (i3 < i4) {
            int i7 = i3 + 1;
            char c3 = cArr[i3];
            if (c3 <= 127) {
                bArr[i5] = (byte) c3;
                i5++;
            } else if (c3 < 2048) {
                int i8 = i5 + 1;
                bArr[i5] = (byte) ((c3 >> 6) | 192);
                i5 += 2;
                bArr[i8] = (byte) ((c3 & '?') | 128);
            } else if (c3 < 55296 || c3 > 57343) {
                bArr[i5] = (byte) ((c3 >> 12) | 224);
                int i9 = i5 + 2;
                bArr[i5 + 1] = (byte) (((c3 >> 6) & 63) | 128);
                i5 += 3;
                bArr[i9] = (byte) ((c3 & '?') | 128);
            } else if (c3 > 56319 || i7 >= i4) {
                i5 = _invalidSurrogateStart(c3, bArr, i5);
            } else {
                char c4 = cArr[i7];
                if (c4 > 57343 || c4 < 56320) {
                    i5 = _invalidSurrogateEnd(c3, c4, bArr, i5);
                } else {
                    i3 += 2;
                    i5 = _decodeAndWriteSurrogate(c3, c4, bArr, i5);
                }
            }
            i3 = i7;
        }
        return i5 - i6;
    }

    private final void _writeByte(byte b3) throws IOException {
        if (this._outputTail >= this._outputEnd) {
            _flushBuffer();
        }
        byte[] bArr = this._outputBuffer;
        int i3 = this._outputTail;
        this._outputTail = i3 + 1;
        bArr[i3] = b3;
    }

    private final void _writeBytes(byte[] bArr, int i3, int i4) throws IOException {
        if (i4 != 0) {
            int i5 = this._outputTail;
            if (i5 + i4 >= this._outputEnd) {
                _writeBytesLong(bArr, i3, i4);
                return;
            }
            System.arraycopy(bArr, i3, this._outputBuffer, i5, i4);
            this._outputTail += i4;
        }
    }

    private final void _writeBytesLong(byte[] bArr, int i3, int i4) throws IOException {
        if (this._outputTail >= this._outputEnd) {
            _flushBuffer();
        }
        while (true) {
            int min = Math.min(i4, this._outputEnd - this._outputTail);
            System.arraycopy(bArr, i3, this._outputBuffer, this._outputTail, min);
            this._outputTail += min;
            i4 -= min;
            if (i4 != 0) {
                i3 += min;
                _flushBuffer();
            } else {
                return;
            }
        }
    }

    private final void _writeDoubleNoCheck(double d2) throws IOException {
        _ensureRoomForOutput(11);
        long doubleToRawLongBits = Double.doubleToRawLongBits(d2);
        byte[] bArr = this._outputBuffer;
        int i3 = this._outputTail;
        int i4 = i3 + 1;
        this._outputTail = i4;
        bArr[i3] = -5;
        int i5 = (int) (doubleToRawLongBits >> 32);
        int i6 = i3 + 2;
        this._outputTail = i6;
        bArr[i4] = (byte) (i5 >> 24);
        int i7 = i3 + 3;
        this._outputTail = i7;
        bArr[i6] = (byte) (i5 >> 16);
        int i8 = i3 + 4;
        this._outputTail = i8;
        bArr[i7] = (byte) (i5 >> 8);
        int i9 = i3 + 5;
        this._outputTail = i9;
        bArr[i8] = (byte) i5;
        int i10 = (int) doubleToRawLongBits;
        int i11 = i3 + 6;
        this._outputTail = i11;
        bArr[i9] = (byte) (i10 >> 24);
        int i12 = i3 + 7;
        this._outputTail = i12;
        bArr[i11] = (byte) (i10 >> 16);
        int i13 = i3 + 8;
        this._outputTail = i13;
        bArr[i12] = (byte) (i10 >> 8);
        this._outputTail = i3 + 9;
        bArr[i13] = (byte) i10;
    }

    private final void _writeIntFull(int i3, int i4) throws IOException {
        _ensureRoomForOutput(5);
        byte[] bArr = this._outputBuffer;
        int i5 = this._outputTail;
        int i6 = i5 + 1;
        this._outputTail = i6;
        bArr[i5] = (byte) (i3 + 26);
        int i7 = i5 + 2;
        this._outputTail = i7;
        bArr[i6] = (byte) (i4 >> 24);
        int i8 = i5 + 3;
        this._outputTail = i8;
        bArr[i7] = (byte) (i4 >> 16);
        int i9 = i5 + 4;
        this._outputTail = i9;
        bArr[i8] = (byte) (i4 >> 8);
        this._outputTail = i5 + 5;
        bArr[i9] = (byte) i4;
    }

    private final void _writeIntMinimal(int i3, int i4) throws IOException {
        int i5;
        byte b3;
        _ensureRoomForOutput(5);
        if (i4 < 0) {
            b3 = (byte) i4;
            i5 = i4 >> 8;
        } else if (i4 < 24) {
            byte[] bArr = this._outputBuffer;
            int i6 = this._outputTail;
            this._outputTail = i6 + 1;
            bArr[i6] = (byte) (i3 + i4);
            return;
        } else if (i4 <= 255) {
            byte[] bArr2 = this._outputBuffer;
            int i7 = this._outputTail;
            int i8 = i7 + 1;
            this._outputTail = i8;
            bArr2[i7] = (byte) (i3 + 24);
            this._outputTail = i7 + 2;
            bArr2[i8] = (byte) i4;
            return;
        } else {
            b3 = (byte) i4;
            i5 = i4 >> 8;
            if (i5 <= 255) {
                byte[] bArr3 = this._outputBuffer;
                int i9 = this._outputTail;
                int i10 = i9 + 1;
                this._outputTail = i10;
                bArr3[i9] = (byte) (i3 + 25);
                int i11 = i9 + 2;
                this._outputTail = i11;
                bArr3[i10] = (byte) i5;
                this._outputTail = i9 + 3;
                bArr3[i11] = b3;
                return;
            }
        }
        byte[] bArr4 = this._outputBuffer;
        int i12 = this._outputTail;
        int i13 = i12 + 1;
        this._outputTail = i13;
        bArr4[i12] = (byte) (i3 + 26);
        int i14 = i12 + 2;
        this._outputTail = i14;
        bArr4[i13] = (byte) (i5 >> 16);
        int i15 = i12 + 3;
        this._outputTail = i15;
        bArr4[i14] = (byte) (i5 >> 8);
        int i16 = i12 + 4;
        this._outputTail = i16;
        bArr4[i15] = (byte) i5;
        this._outputTail = i12 + 5;
        bArr4[i16] = b3;
    }

    private final void _writeIntValue(int i3) throws IOException {
        int i4;
        if (i3 < 0) {
            i3 = (-i3) - 1;
            i4 = 32;
        } else {
            i4 = 0;
        }
        _writeLengthMarker(i4, i3);
    }

    private final void _writeLengthMarker(int i3, int i4) throws IOException {
        _ensureRoomForOutput(5);
        if (i4 < 24) {
            byte[] bArr = this._outputBuffer;
            int i5 = this._outputTail;
            this._outputTail = i5 + 1;
            bArr[i5] = (byte) (i3 + i4);
        } else if (i4 <= 255) {
            byte[] bArr2 = this._outputBuffer;
            int i6 = this._outputTail;
            int i7 = i6 + 1;
            this._outputTail = i7;
            bArr2[i6] = (byte) (i3 + 24);
            this._outputTail = i6 + 2;
            bArr2[i7] = (byte) i4;
        } else {
            byte b3 = (byte) i4;
            int i8 = i4 >> 8;
            if (i8 <= 255) {
                byte[] bArr3 = this._outputBuffer;
                int i9 = this._outputTail;
                int i10 = i9 + 1;
                this._outputTail = i10;
                bArr3[i9] = (byte) (i3 + 25);
                int i11 = i9 + 2;
                this._outputTail = i11;
                bArr3[i10] = (byte) i8;
                this._outputTail = i9 + 3;
                bArr3[i11] = b3;
                return;
            }
            byte[] bArr4 = this._outputBuffer;
            int i12 = this._outputTail;
            int i13 = i12 + 1;
            this._outputTail = i13;
            bArr4[i12] = (byte) (i3 + 26);
            int i14 = i12 + 2;
            this._outputTail = i14;
            bArr4[i13] = (byte) (i4 >> 24);
            int i15 = i12 + 3;
            this._outputTail = i15;
            bArr4[i14] = (byte) (i4 >> 16);
            int i16 = i12 + 4;
            this._outputTail = i16;
            bArr4[i15] = (byte) i8;
            this._outputTail = i12 + 5;
            bArr4[i16] = b3;
        }
    }

    private final void _writeLongNoCheck(long j2) throws IOException {
        if (this._cfgMinimalInts) {
            if (j2 >= 0) {
                if (j2 < 4294967296L) {
                    _writeIntMinimal(0, (int) j2);
                    return;
                }
            } else if (j2 >= -4294967296L) {
                _writeIntMinimal(32, (int) ((-j2) - 1));
                return;
            }
        }
        _ensureRoomForOutput(9);
        if (j2 < 0) {
            j2 = -(j2 + 1);
            byte[] bArr = this._outputBuffer;
            int i3 = this._outputTail;
            this._outputTail = i3 + 1;
            bArr[i3] = 59;
        } else {
            byte[] bArr2 = this._outputBuffer;
            int i4 = this._outputTail;
            this._outputTail = i4 + 1;
            bArr2[i4] = Ascii.ESC;
        }
        int i5 = (int) (j2 >> 32);
        byte[] bArr3 = this._outputBuffer;
        int i6 = this._outputTail;
        int i7 = i6 + 1;
        this._outputTail = i7;
        bArr3[i6] = (byte) (i5 >> 24);
        int i8 = i6 + 2;
        this._outputTail = i8;
        bArr3[i7] = (byte) (i5 >> 16);
        int i9 = i6 + 3;
        this._outputTail = i9;
        bArr3[i8] = (byte) (i5 >> 8);
        int i10 = i6 + 4;
        this._outputTail = i10;
        bArr3[i9] = (byte) i5;
        int i11 = (int) j2;
        int i12 = i6 + 5;
        this._outputTail = i12;
        bArr3[i10] = (byte) (i11 >> 24);
        int i13 = i6 + 6;
        this._outputTail = i13;
        bArr3[i12] = (byte) (i11 >> 16);
        int i14 = i6 + 7;
        this._outputTail = i14;
        bArr3[i13] = (byte) (i11 >> 8);
        this._outputTail = i6 + 8;
        bArr3[i14] = (byte) i11;
    }

    private final void _writeLongValue(long j2) throws IOException {
        _ensureRoomForOutput(9);
        if (j2 < 0) {
            j2 = -(j2 + 1);
            byte[] bArr = this._outputBuffer;
            int i3 = this._outputTail;
            this._outputTail = i3 + 1;
            bArr[i3] = 59;
        } else {
            byte[] bArr2 = this._outputBuffer;
            int i4 = this._outputTail;
            this._outputTail = i4 + 1;
            bArr2[i4] = Ascii.ESC;
        }
        int i5 = (int) (j2 >> 32);
        byte[] bArr3 = this._outputBuffer;
        int i6 = this._outputTail;
        int i7 = i6 + 1;
        this._outputTail = i7;
        bArr3[i6] = (byte) (i5 >> 24);
        int i8 = i6 + 2;
        this._outputTail = i8;
        bArr3[i7] = (byte) (i5 >> 16);
        int i9 = i6 + 3;
        this._outputTail = i9;
        bArr3[i8] = (byte) (i5 >> 8);
        int i10 = i6 + 4;
        this._outputTail = i10;
        bArr3[i9] = (byte) i5;
        int i11 = (int) j2;
        int i12 = i6 + 5;
        this._outputTail = i12;
        bArr3[i10] = (byte) (i11 >> 24);
        int i13 = i6 + 6;
        this._outputTail = i13;
        bArr3[i12] = (byte) (i11 >> 16);
        int i14 = i6 + 7;
        this._outputTail = i14;
        bArr3[i13] = (byte) (i11 >> 8);
        this._outputTail = i6 + 8;
        bArr3[i14] = (byte) i11;
    }

    private final void closeComplexElement() throws IOException {
        int i3 = this._currentRemainingElements;
        int i4 = -2;
        if (i3 == -2) {
            _writeByte((byte) -1);
        } else if (i3 != 0) {
            _reportError(String.format("%s size mismatch: expected %d more elements", new Object[]{this._streamWriteContext.typeDesc(), Integer.valueOf(this._currentRemainingElements)}));
        }
        int i5 = this._elementCountsPtr;
        if (i5 != 0) {
            int[] iArr = this._elementCounts;
            int i6 = i5 - 1;
            this._elementCountsPtr = i6;
            i4 = iArr[i6];
        }
        this._currentRemainingElements = i4;
    }

    public final void _ensureSpace(int i3) throws IOException {
        if (this._outputTail + i3 + 3 > this._outputEnd) {
            _flushBuffer();
        }
    }

    public final void _flushBuffer() throws IOException {
        int i3 = this._outputTail;
        if (i3 > 0) {
            this._bytesWritten += i3;
            this._out.write(this._outputBuffer, 0, i3);
            this._outputTail = 0;
        }
    }

    public UnsupportedOperationException _notSupported() {
        return new UnsupportedOperationException();
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
        if (!this._streamWriteContext.writeValue()) {
            _reportError("Can not " + str + ", expecting field name/id");
        }
        int i3 = this._currentRemainingElements;
        if (i3 != -2) {
            int i4 = i3 - 1;
            if (i4 < 0) {
                _failSizedArrayOrObject();
            } else {
                this._currentRemainingElements = i4;
            }
        }
    }

    public void _write(BigInteger bigInteger) throws IOException {
        if (bigInteger.signum() < 0) {
            _writeByte((byte) -61);
            bigInteger = bigInteger.negate();
        } else {
            _writeByte((byte) -62);
        }
        byte[] byteArray = bigInteger.toByteArray();
        int length = byteArray.length;
        _writeLengthMarker(64, length);
        _writeBytes(byteArray, 0, length);
    }

    public final void _writeChunkedString(char[] cArr, int i3, int i4) throws IOException {
        _writeByte(Byte.MAX_VALUE);
        while (true) {
            int i5 = MAX_LONG_STRING_CHARS;
            if (i4 <= MAX_LONG_STRING_CHARS) {
                break;
            }
            _ensureSpace(MAX_LONG_STRING_BYTES);
            int i6 = this._outputTail;
            int i7 = i3 + MAX_LONG_STRING_CHARS;
            char c3 = cArr[i3 + 3995];
            if (c3 >= 55296 && c3 <= 56319) {
                i7 = i3 + 3995;
                i5 = 3995;
            }
            int _encode = _encode(i6 + 3, cArr, i3, i7);
            byte[] bArr = this._outputBuffer;
            bArr[i6] = CBORConstants.BYTE_STRING_2BYTE_LEN;
            bArr[i6 + 1] = (byte) (_encode >> 8);
            bArr[i6 + 2] = (byte) _encode;
            this._outputTail = i6 + 3 + _encode;
            i3 += i5;
            i4 -= i5;
        }
        if (i4 > 0) {
            _writeString(cArr, i3, i4);
        }
        _writeByte((byte) -1);
    }

    public final void _writeString(String str) throws IOException {
        int length = str.length();
        if (length == 0) {
            _writeByte(CBORConstants.BYTE_EMPTY_STRING);
        } else if (length <= 23) {
            _ensureSpace(71);
            int _encode = _encode(this._outputTail + 1, str, length);
            byte[] bArr = this._outputBuffer;
            int i3 = this._outputTail;
            if (_encode <= 23) {
                bArr[i3] = (byte) (_encode + 96);
                this._outputTail = i3 + 1 + _encode;
                return;
            }
            int i4 = i3 + 1;
            System.arraycopy(bArr, i4, bArr, i3 + 2, _encode);
            bArr[i3] = CBORConstants.BYTE_STRING_1BYTE_LEN;
            bArr[i4] = (byte) _encode;
            this._outputTail = i3 + 2 + _encode;
        } else {
            char[] cArr = this._charBuffer;
            if (length > cArr.length) {
                cArr = new char[Math.max(cArr.length + 32, length)];
                this._charBuffer = cArr;
            }
            str.getChars(0, length, cArr, 0);
            _writeString(cArr, 0, length);
        }
    }

    public void assignCurrentValue(Object obj) {
        this._streamWriteContext.setCurrentValue(obj);
    }

    public boolean canWriteBinaryNatively() {
        return true;
    }

    public void close() throws IOException {
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
        super.close();
        _flushBuffer();
        if (this._ioContext.isResourceManaged() || isEnabled(JsonGenerator.Feature.AUTO_CLOSE_TARGET)) {
            this._out.close();
        } else if (isEnabled(JsonGenerator.Feature.FLUSH_PASSED_TO_STREAM)) {
            this._out.flush();
        }
        _releaseBuffers();
    }

    public CBORGenerator configure(Feature feature, boolean z2) {
        if (z2) {
            enable(feature);
        } else {
            disable(feature);
        }
        return this;
    }

    public void copyCurrentEvent(JsonParser jsonParser) throws IOException {
        maybeCopyTag(jsonParser);
        super.copyCurrentEvent(jsonParser);
    }

    public void copyCurrentStructure(JsonParser jsonParser) throws IOException {
        maybeCopyTag(jsonParser);
        super.copyCurrentStructure(jsonParser);
    }

    public Object currentValue() {
        return this._streamWriteContext.getCurrentValue();
    }

    public CBORGenerator disable(Feature feature) {
        this._formatFeatures &= ~feature.getMask();
        if (feature == Feature.WRITE_MINIMAL_INTS) {
            this._cfgMinimalInts = false;
        }
        return this;
    }

    public CBORGenerator enable(Feature feature) {
        this._formatFeatures |= feature.getMask();
        if (feature == Feature.WRITE_MINIMAL_INTS) {
            this._cfgMinimalInts = true;
        }
        return this;
    }

    public final void flush() throws IOException {
        _flushBuffer();
        if (isEnabled(JsonGenerator.Feature.FLUSH_PASSED_TO_STREAM)) {
            this._out.flush();
        }
    }

    public Object getCurrentValue() {
        return this._streamWriteContext.getCurrentValue();
    }

    public int getFormatFeatures() {
        return this._formatFeatures;
    }

    public int getOutputBuffered() {
        return this._outputTail;
    }

    public JsonStreamContext getOutputContext() {
        return this._streamWriteContext;
    }

    public Object getOutputTarget() {
        return this._out;
    }

    public JacksonFeatureSet<StreamWriteCapability> getWriteCapabilities() {
        return JsonGenerator.DEFAULT_BINARY_WRITE_CAPABILITIES;
    }

    public final boolean isEnabled(Feature feature) {
        return (this._formatFeatures & feature.getMask()) != 0;
    }

    public void maybeCopyTag(JsonParser jsonParser) throws IOException {
        int currentTag;
        if ((jsonParser instanceof CBORParser) && jsonParser.hasCurrentToken() && (currentTag = ((CBORParser) jsonParser).getCurrentTag()) != -1) {
            writeTag(currentTag);
        }
    }

    public JsonGenerator overrideFormatFeatures(int i3, int i4) {
        int i5 = this._formatFeatures;
        int i6 = (i3 & i4) | ((~i4) & i5);
        if (i5 != i6) {
            this._formatFeatures = i6;
            this._cfgMinimalInts = Feature.WRITE_MINIMAL_INTS.enabledIn(i6);
        }
        return this;
    }

    public JsonGenerator overrideStdFeatures(int i3, int i4) {
        int i5 = this._features;
        int i6 = (i3 & i4) | ((~i4) & i5);
        if (i5 != i6) {
            this._features = i6;
        }
        return this;
    }

    public void setCurrentValue(Object obj) {
        this._streamWriteContext.setCurrentValue(obj);
    }

    public JsonGenerator setPrettyPrinter(PrettyPrinter prettyPrinter) {
        return this;
    }

    public JsonGenerator useDefaultPrettyPrinter() {
        return this;
    }

    public Version version() {
        return PackageVersion.VERSION;
    }

    public void writeArray(int[] iArr, int i3, int i4) throws IOException {
        _verifyOffsets(iArr.length, i3, i4);
        _verifyValueWrite("write int array");
        _writeLengthMarker(128, i4);
        if (this._cfgMinimalInts) {
            int i5 = i4 + i3;
            while (i3 < i5) {
                int i6 = iArr[i3];
                if (i6 < 0) {
                    _writeIntMinimal(32, (-i6) - 1);
                } else {
                    _writeIntMinimal(0, i6);
                }
                i3++;
            }
            return;
        }
        int i7 = i4 + i3;
        while (i3 < i7) {
            int i8 = iArr[i3];
            if (i8 < 0) {
                _writeIntFull(32, (-i8) - 1);
            } else {
                _writeIntFull(0, i8);
            }
            i3++;
        }
    }

    public void writeBinary(Base64Variant base64Variant, byte[] bArr, int i3, int i4) throws IOException {
        if (bArr == null) {
            writeNull();
            return;
        }
        _verifyValueWrite("write Binary value");
        _writeLengthMarker(64, i4);
        _writeBytes(bArr, i3, i4);
    }

    public void writeBoolean(boolean z2) throws IOException {
        _verifyValueWrite("write boolean value");
        if (z2) {
            _writeByte(CBORConstants.BYTE_TRUE);
        } else {
            _writeByte(CBORConstants.BYTE_FALSE);
        }
    }

    public void writeBytes(byte[] bArr, int i3, int i4) throws IOException {
        _writeBytes(bArr, i3, i4);
    }

    public final void writeEndArray() throws IOException {
        if (!this._streamWriteContext.inArray()) {
            _reportError("Current context not Array but " + this._streamWriteContext.typeDesc());
        }
        closeComplexElement();
        this._streamWriteContext = this._streamWriteContext.getParent();
    }

    public final void writeEndObject() throws IOException {
        if (!this._streamWriteContext.inObject()) {
            _reportError("Current context not Object but " + this._streamWriteContext.typeDesc());
        }
        closeComplexElement();
        this._streamWriteContext = this._streamWriteContext.getParent();
    }

    public final void writeFieldId(long j2) throws IOException {
        if (!this._streamWriteContext.writeFieldId(j2)) {
            _reportError("Can not write a field id, expecting a value");
        }
        _writeLongNoCheck(j2);
    }

    public final void writeFieldName(String str) throws IOException {
        if (!this._streamWriteContext.writeFieldName(str)) {
            _reportError("Can not write a field name, expecting a value");
        }
        _writeString(str);
    }

    public void writeNull() throws IOException {
        _verifyValueWrite("write null value");
        _writeByte((byte) -10);
    }

    public void writeNumber(int i3) throws IOException {
        int i4;
        int i5;
        byte b3;
        _verifyValueWrite("write number");
        if (i3 < 0) {
            i3 = (-i3) - 1;
            i4 = 32;
        } else {
            i4 = 0;
        }
        _ensureRoomForOutput(5);
        if (!this._cfgMinimalInts) {
            b3 = (byte) i3;
            i5 = i3 >> 8;
        } else if (i3 < 24) {
            byte[] bArr = this._outputBuffer;
            int i6 = this._outputTail;
            this._outputTail = i6 + 1;
            bArr[i6] = (byte) (i4 + i3);
            return;
        } else if (i3 <= 255) {
            byte[] bArr2 = this._outputBuffer;
            int i7 = this._outputTail;
            int i8 = i7 + 1;
            this._outputTail = i8;
            bArr2[i7] = (byte) (i4 + 24);
            this._outputTail = i7 + 2;
            bArr2[i8] = (byte) i3;
            return;
        } else {
            b3 = (byte) i3;
            i5 = i3 >> 8;
            if (i5 <= 255) {
                byte[] bArr3 = this._outputBuffer;
                int i9 = this._outputTail;
                int i10 = i9 + 1;
                this._outputTail = i10;
                bArr3[i9] = (byte) (i4 + 25);
                int i11 = i9 + 2;
                this._outputTail = i11;
                bArr3[i10] = (byte) i5;
                this._outputTail = i9 + 3;
                bArr3[i11] = b3;
                return;
            }
        }
        byte[] bArr4 = this._outputBuffer;
        int i12 = this._outputTail;
        int i13 = i12 + 1;
        this._outputTail = i13;
        bArr4[i12] = (byte) (i4 + 26);
        int i14 = i12 + 2;
        this._outputTail = i14;
        bArr4[i13] = (byte) (i5 >> 16);
        int i15 = i12 + 3;
        this._outputTail = i15;
        bArr4[i14] = (byte) (i5 >> 8);
        int i16 = i12 + 4;
        this._outputTail = i16;
        bArr4[i15] = (byte) i5;
        this._outputTail = i12 + 5;
        bArr4[i16] = b3;
    }

    public void writeRaw(String str) throws IOException {
        throw _notSupported();
    }

    public void writeRawUTF8String(byte[] bArr, int i3, int i4) throws IOException {
        _verifyValueWrite("write String value");
        if (i4 == 0) {
            _writeByte(CBORConstants.BYTE_EMPTY_STRING);
            return;
        }
        _writeLengthMarker(96, i4);
        _writeBytes(bArr, 0, i4);
    }

    public void writeRawValue(String str) throws IOException {
        throw _notSupported();
    }

    public final void writeStartArray() throws IOException {
        _verifyValueWrite("start an array");
        this._streamWriteContext = this._streamWriteContext.createChildArrayContext((Object) null);
        if (this._elementCountsPtr > 0) {
            _pushRemainingElements();
        }
        this._currentRemainingElements = -2;
        _writeByte(CBORConstants.BYTE_ARRAY_INDEFINITE);
    }

    public final void writeStartObject() throws IOException {
        _verifyValueWrite("start an object");
        this._streamWriteContext = this._streamWriteContext.createChildObjectContext((Object) null);
        if (this._elementCountsPtr > 0) {
            _pushRemainingElements();
        }
        this._currentRemainingElements = -2;
        _writeByte((byte) -65);
    }

    public void writeString(String str) throws IOException {
        if (str == null) {
            writeNull();
            return;
        }
        _verifyValueWrite("write String value");
        _writeString(str);
    }

    public void writeTag(int i3) throws IOException {
        if (i3 >= 0) {
            _writeLengthMarker(192, i3);
            return;
        }
        throw new IllegalArgumentException(C0118y.c(i3, "Can not write negative tag ids (", ")"));
    }

    public final void writeUTF8String(byte[] bArr, int i3, int i4) throws IOException {
        writeRawUTF8String(bArr, i3, i4);
    }

    public void writeRaw(String str, int i3, int i4) throws IOException {
        throw _notSupported();
    }

    public void writeRawValue(String str, int i3, int i4) throws IOException {
        throw _notSupported();
    }

    public void writeRaw(char[] cArr, int i3, int i4) throws IOException {
        throw _notSupported();
    }

    public void writeRawValue(char[] cArr, int i3, int i4) throws IOException {
        throw _notSupported();
    }

    public final void writeFieldName(SerializableString serializableString) throws IOException {
        if (!this._streamWriteContext.writeFieldName(serializableString.getValue())) {
            _reportError("Can not write a field name, expecting a value");
        }
        byte[] asUnquotedUTF8 = serializableString.asUnquotedUTF8();
        int length = asUnquotedUTF8.length;
        if (length == 0) {
            _writeByte(CBORConstants.BYTE_EMPTY_STRING);
            return;
        }
        _writeLengthMarker(96, length);
        _writeBytes(asUnquotedUTF8, 0, length);
    }

    public void writeRaw(char c3) throws IOException {
        throw _notSupported();
    }

    public final void writeString(SerializableString serializableString) throws IOException {
        _verifyValueWrite("write String value");
        byte[] asUnquotedUTF8 = serializableString.asUnquotedUTF8();
        int length = asUnquotedUTF8.length;
        if (length == 0) {
            _writeByte(CBORConstants.BYTE_EMPTY_STRING);
            return;
        }
        _writeLengthMarker(96, length);
        _writeBytes(asUnquotedUTF8, 0, length);
    }

    private final int _encode(int i3, String str, int i4) throws IOException {
        byte[] bArr = this._outputBuffer;
        int i5 = i3;
        int i6 = 0;
        while (i6 < i4) {
            char charAt = str.charAt(i6);
            if (charAt > 127) {
                return _encode2(i6, i5, str, i4, i3);
            }
            bArr[i5] = (byte) charAt;
            i6++;
            i5++;
        }
        return i5 - i3;
    }

    private final int _writeBytes(InputStream inputStream, int i3) throws IOException {
        while (i3 > 0) {
            int i4 = this._outputEnd - this._outputTail;
            if (i4 <= 0) {
                _flushBuffer();
                i4 = this._outputEnd - this._outputTail;
            }
            int read = inputStream.read(this._outputBuffer, this._outputTail, i4);
            if (read < 0) {
                break;
            }
            this._outputTail += read;
            i3 -= read;
        }
        return i3;
    }

    public int writeBinary(InputStream inputStream, int i3) throws IOException {
        if (i3 >= 0) {
            _verifyValueWrite("write Binary value");
            _writeLengthMarker(64, i3);
            int _writeBytes = _writeBytes(inputStream, i3);
            if (_writeBytes > 0) {
                _reportError(i.a(_writeBytes, i3, "Too few bytes available: missing ", " bytes (out of ", ")"));
            }
            return i3;
        }
        throw new UnsupportedOperationException("Must pass actual length for CBOR encoded data");
    }

    public void writeRaw(byte b3) throws IOException {
        _writeByte(b3);
    }

    public void writeStartArray(Object obj) throws IOException {
        _verifyValueWrite("start an array");
        this._streamWriteContext = this._streamWriteContext.createChildArrayContext(obj);
        if (this._elementCountsPtr > 0) {
            _pushRemainingElements();
        }
        this._currentRemainingElements = -2;
        _writeByte(CBORConstants.BYTE_ARRAY_INDEFINITE);
    }

    public final void writeStartObject(Object obj) throws IOException {
        _verifyValueWrite("start an object");
        this._streamWriteContext = this._streamWriteContext.createChildObjectContext(obj);
        if (this._elementCountsPtr > 0) {
            _pushRemainingElements();
        }
        this._currentRemainingElements = -2;
        _writeByte((byte) -65);
    }

    public void writeString(char[] cArr, int i3, int i4) throws IOException {
        _verifyValueWrite("write String value");
        if (i4 == 0) {
            _writeByte(CBORConstants.BYTE_EMPTY_STRING);
        } else {
            _writeString(cArr, i3, i4);
        }
    }

    public void writeArray(long[] jArr, int i3, int i4) throws IOException {
        _verifyOffsets(jArr.length, i3, i4);
        _verifyValueWrite("write int array");
        _writeLengthMarker(128, i4);
        int i5 = i4 + i3;
        while (i3 < i5) {
            _writeLongNoCheck(jArr[i3]);
            i3++;
        }
    }

    public void writeStartArray(Object obj, int i3) throws IOException {
        _verifyValueWrite("start an array");
        this._streamWriteContext = this._streamWriteContext.createChildArrayContext(obj);
        _pushRemainingElements();
        this._currentRemainingElements = i3;
        _writeLengthMarker(128, i3);
    }

    public final void writeStartObject(int i3) throws IOException {
        writeStartObject((Object) null, i3);
    }

    public void writeArray(double[] dArr, int i3, int i4) throws IOException {
        _verifyOffsets(dArr.length, i3, i4);
        _verifyValueWrite("write int array");
        _writeLengthMarker(128, i4);
        int i5 = i4 + i3;
        while (i3 < i5) {
            _writeDoubleNoCheck(dArr[i3]);
            i3++;
        }
    }

    public void writeNumber(long j2) throws IOException {
        _verifyValueWrite("write number");
        if (this._cfgMinimalInts) {
            if (j2 >= 0) {
                if (j2 < 4294967296L) {
                    _writeIntMinimal(0, (int) j2);
                    return;
                }
            } else if (j2 >= -4294967296L) {
                _writeIntMinimal(32, (int) ((-j2) - 1));
                return;
            }
        }
        _ensureRoomForOutput(9);
        if (j2 < 0) {
            j2 = -(j2 + 1);
            byte[] bArr = this._outputBuffer;
            int i3 = this._outputTail;
            this._outputTail = i3 + 1;
            bArr[i3] = 59;
        } else {
            byte[] bArr2 = this._outputBuffer;
            int i4 = this._outputTail;
            this._outputTail = i4 + 1;
            bArr2[i4] = Ascii.ESC;
        }
        int i5 = (int) (j2 >> 32);
        byte[] bArr3 = this._outputBuffer;
        int i6 = this._outputTail;
        int i7 = i6 + 1;
        this._outputTail = i7;
        bArr3[i6] = (byte) (i5 >> 24);
        int i8 = i6 + 2;
        this._outputTail = i8;
        bArr3[i7] = (byte) (i5 >> 16);
        int i9 = i6 + 3;
        this._outputTail = i9;
        bArr3[i8] = (byte) (i5 >> 8);
        int i10 = i6 + 4;
        this._outputTail = i10;
        bArr3[i9] = (byte) i5;
        int i11 = (int) j2;
        int i12 = i6 + 5;
        this._outputTail = i12;
        bArr3[i10] = (byte) (i11 >> 24);
        int i13 = i6 + 6;
        this._outputTail = i13;
        bArr3[i12] = (byte) (i11 >> 16);
        int i14 = i6 + 7;
        this._outputTail = i14;
        bArr3[i13] = (byte) (i11 >> 8);
        this._outputTail = i6 + 8;
        bArr3[i14] = (byte) i11;
    }

    public void writeStartObject(Object obj, int i3) throws IOException {
        _verifyValueWrite("start an object");
        this._streamWriteContext = this._streamWriteContext.createChildObjectContext(obj);
        _pushRemainingElements();
        this._currentRemainingElements = i3;
        _writeLengthMarker(160, i3);
    }

    @Deprecated
    public void writeStartArray(int i3) throws IOException {
        _verifyValueWrite("start an array");
        this._streamWriteContext = this._streamWriteContext.createChildArrayContext((Object) null);
        _pushRemainingElements();
        this._currentRemainingElements = i3;
        _writeLengthMarker(128, i3);
    }

    public final void _writeString(char[] cArr, int i3, int i4) throws IOException {
        if (i4 <= 23) {
            _ensureSpace(71);
            int _encode = _encode(this._outputTail + 1, cArr, i3, i4 + i3);
            byte[] bArr = this._outputBuffer;
            int i5 = this._outputTail;
            if (_encode <= 23) {
                bArr[i5] = (byte) (_encode + 96);
                this._outputTail = i5 + 1 + _encode;
                return;
            }
            int i6 = i5 + 1;
            System.arraycopy(bArr, i6, bArr, i5 + 2, _encode);
            bArr[i5] = CBORConstants.BYTE_STRING_1BYTE_LEN;
            bArr[i6] = (byte) _encode;
            this._outputTail = i5 + 2 + _encode;
        } else if (i4 <= 255) {
            _ensureSpace(768);
            int _encode2 = _encode(this._outputTail + 2, cArr, i3, i4 + i3);
            byte[] bArr2 = this._outputBuffer;
            int i7 = this._outputTail;
            if (_encode2 <= 255) {
                bArr2[i7] = CBORConstants.BYTE_STRING_1BYTE_LEN;
                bArr2[i7 + 1] = (byte) _encode2;
                this._outputTail = i7 + 2 + _encode2;
                return;
            }
            System.arraycopy(bArr2, i7 + 2, bArr2, i7 + 3, _encode2);
            bArr2[i7] = CBORConstants.BYTE_STRING_2BYTE_LEN;
            bArr2[i7 + 1] = (byte) (_encode2 >> 8);
            bArr2[i7 + 2] = (byte) _encode2;
            this._outputTail = i7 + 3 + _encode2;
        } else if (i4 <= MAX_LONG_STRING_CHARS) {
            _ensureSpace(MAX_LONG_STRING_BYTES);
            int i8 = this._outputTail;
            int _encode3 = _encode(i8 + 3, cArr, i3, i4 + i3);
            byte[] bArr3 = this._outputBuffer;
            bArr3[i8] = CBORConstants.BYTE_STRING_2BYTE_LEN;
            bArr3[i8 + 1] = (byte) (_encode3 >> 8);
            bArr3[i8 + 2] = (byte) _encode3;
            this._outputTail = i8 + 3 + _encode3;
        } else {
            _writeChunkedString(cArr, i3, i4);
        }
    }

    public int writeBinary(Base64Variant base64Variant, InputStream inputStream, int i3) throws IOException {
        return writeBinary(inputStream, i3);
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public CBORGenerator(IOContext iOContext, int i3, int i4, ObjectCodec objectCodec, OutputStream outputStream, byte[] bArr, int i5, boolean z2) {
        super(i3, objectCodec, (JsonWriteContext) null);
        DupDetector dupDetector = null;
        this._streamWriteContext = CBORWriteContext.createRootContext(JsonGenerator.Feature.STRICT_DUPLICATE_DETECTION.enabledIn(i3) ? DupDetector.rootDetector((JsonGenerator) this) : dupDetector);
        this._formatFeatures = i4;
        this._cfgMinimalInts = Feature.WRITE_MINIMAL_INTS.enabledIn(i4);
        this._ioContext = iOContext;
        this._out = outputStream;
        this._bufferRecyclable = z2;
        this._outputTail = i5;
        this._outputBuffer = bArr;
        int length = bArr.length;
        this._outputEnd = length;
        char[] allocConcatBuffer = iOContext.allocConcatBuffer();
        this._charBuffer = allocConcatBuffer;
        this._charBufferLength = allocConcatBuffer.length;
        if (length < MIN_BUFFER_LENGTH) {
            throw new IllegalStateException(C0118y.c(length, "Internal encoding buffer length (", ") too short, must be at least 770"));
        }
    }

    public void writeNumber(BigInteger bigInteger) throws IOException {
        if (bigInteger == null) {
            writeNull();
            return;
        }
        _verifyValueWrite("write number");
        _write(bigInteger);
    }

    public void writeNumber(double d2) throws IOException {
        _verifyValueWrite("write number");
        _ensureRoomForOutput(11);
        long doubleToRawLongBits = Double.doubleToRawLongBits(d2);
        byte[] bArr = this._outputBuffer;
        int i3 = this._outputTail;
        int i4 = i3 + 1;
        this._outputTail = i4;
        bArr[i3] = -5;
        int i5 = (int) (doubleToRawLongBits >> 32);
        int i6 = i3 + 2;
        this._outputTail = i6;
        bArr[i4] = (byte) (i5 >> 24);
        int i7 = i3 + 3;
        this._outputTail = i7;
        bArr[i6] = (byte) (i5 >> 16);
        int i8 = i3 + 4;
        this._outputTail = i8;
        bArr[i7] = (byte) (i5 >> 8);
        int i9 = i3 + 5;
        this._outputTail = i9;
        bArr[i8] = (byte) i5;
        int i10 = (int) doubleToRawLongBits;
        int i11 = i3 + 6;
        this._outputTail = i11;
        bArr[i9] = (byte) (i10 >> 24);
        int i12 = i3 + 7;
        this._outputTail = i12;
        bArr[i11] = (byte) (i10 >> 16);
        int i13 = i3 + 8;
        this._outputTail = i13;
        bArr[i12] = (byte) (i10 >> 8);
        this._outputTail = i3 + 9;
        bArr[i13] = (byte) i10;
    }

    public void writeNumber(float f2) throws IOException {
        _ensureRoomForOutput(6);
        _verifyValueWrite("write number");
        int floatToRawIntBits = Float.floatToRawIntBits(f2);
        byte[] bArr = this._outputBuffer;
        int i3 = this._outputTail;
        int i4 = i3 + 1;
        this._outputTail = i4;
        bArr[i3] = -6;
        int i5 = i3 + 2;
        this._outputTail = i5;
        bArr[i4] = (byte) (floatToRawIntBits >> 24);
        int i6 = i3 + 3;
        this._outputTail = i6;
        bArr[i5] = (byte) (floatToRawIntBits >> 16);
        int i7 = i3 + 4;
        this._outputTail = i7;
        bArr[i6] = (byte) (floatToRawIntBits >> 8);
        this._outputTail = i3 + 5;
        bArr[i7] = (byte) floatToRawIntBits;
    }

    public void writeNumber(BigDecimal bigDecimal) throws IOException {
        if (bigDecimal == null) {
            writeNull();
            return;
        }
        _verifyValueWrite("write number");
        _writeByte((byte) -60);
        _writeByte(CBORConstants.BYTE_ARRAY_2_ELEMENTS);
        _writeIntValue(-bigDecimal.scale());
        BigInteger unscaledValue = bigDecimal.unscaledValue();
        int bitLength = unscaledValue.bitLength();
        if (bitLength <= 31) {
            _writeIntValue(unscaledValue.intValue());
        } else if (bitLength <= 63) {
            _writeLongValue(unscaledValue.longValue());
        } else {
            _write(unscaledValue);
        }
    }

    public void writeNumber(String str) throws IOException, JsonGenerationException, UnsupportedOperationException {
        writeString(str);
    }
}
