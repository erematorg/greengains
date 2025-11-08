package com.fasterxml.jackson.dataformat.cbor;

import A.a;
import androidx.camera.camera2.internal.C0118y;
import androidx.camera.core.impl.i;
import androidx.collection.SieveCacheKt;
import com.fasterxml.jackson.core.Base64Variant;
import com.fasterxml.jackson.core.FormatFeature;
import com.fasterxml.jackson.core.JsonLocation;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.core.SerializableString;
import com.fasterxml.jackson.core.StreamReadCapability;
import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.core.base.GeneratorBase;
import com.fasterxml.jackson.core.base.ParserMinimalBase;
import com.fasterxml.jackson.core.io.ContentReference;
import com.fasterxml.jackson.core.io.IOContext;
import com.fasterxml.jackson.core.io.NumberInput;
import com.fasterxml.jackson.core.json.DupDetector;
import com.fasterxml.jackson.core.sym.ByteQuadsCanonicalizer;
import com.fasterxml.jackson.core.util.ByteArrayBuilder;
import com.fasterxml.jackson.core.util.JacksonFeatureSet;
import com.fasterxml.jackson.core.util.TextBuffer;
import com.google.common.base.Ascii;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Writer;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import okio.Utf8;
import org.msgpack.core.MessagePack;

public class CBORParser extends ParserMinimalBase {
    static final BigDecimal BD_MAX_INT;
    static final BigDecimal BD_MAX_LONG;
    static final BigDecimal BD_MIN_INT;
    static final BigDecimal BD_MIN_LONG;
    private static final BigInteger BIT_63 = BigInteger.ONE.shiftLeft(63);
    static final BigInteger BI_MAX_INT;
    static final BigInteger BI_MAX_LONG;
    static final BigInteger BI_MIN_INT;
    static final BigInteger BI_MIN_LONG;
    protected static final JacksonFeatureSet<StreamReadCapability> CBOR_READ_CAPABILITIES = JsonParser.DEFAULT_READ_CAPABILITIES.with(StreamReadCapability.EXACT_FLOATS);
    protected static final int LONGEST_NON_CHUNKED_BINARY = 250000;
    private static final double MATH_POW_2_10 = Math.pow(2.0d, 10.0d);
    private static final double MATH_POW_2_NEG14 = Math.pow(2.0d, -14.0d);
    private static final Charset UTF8 = StandardCharsets.UTF_8;
    private static final int[] UTF8_UNIT_CODES = CBORConstants.sUtf8UnitLengths;
    protected byte[] _binaryValue;
    protected boolean _bufferRecyclable;
    protected ByteArrayBuilder _byteArrayBuilder;
    private int _chunkEnd;
    private int _chunkLeft;
    protected boolean _closed;
    protected long _currInputProcessed = 0;
    protected int _currInputRow = 1;
    protected int _currInputRowStart = 0;
    protected byte[] _inputBuffer;
    protected int _inputEnd = 0;
    protected int _inputPtr = 0;
    protected InputStream _inputStream;
    protected final IOContext _ioContext;
    protected boolean _nameCopied;
    protected char[] _nameCopyBuffer;
    protected int _numTypesValid;
    protected BigDecimal _numberBigDecimal;
    protected BigInteger _numberBigInt;
    protected double _numberDouble;
    protected float _numberFloat;
    protected int _numberInt;
    protected long _numberLong;
    protected ObjectCodec _objectCodec;
    protected int _quad1;
    protected int _quad2;
    protected int _quad3;
    protected int[] _quadBuffer;
    protected CBORReadContext _streamReadContext;
    protected final ByteQuadsCanonicalizer _symbols;
    protected final boolean _symbolsCanonical;
    protected int _tagValue;
    protected final TextBuffer _textBuffer;
    protected boolean _tokenIncomplete;
    protected int _tokenInputCol = 0;
    protected int _tokenInputRow = 1;
    protected long _tokenInputTotal = 0;
    protected int _typeByte;

    public enum Feature implements FormatFeature {
        ;
        
        final boolean _defaultState;
        final int _mask;

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
            return (this._mask & i3) != 0;
        }

        public int getMask() {
            return this._mask;
        }
    }

    static {
        BigInteger valueOf = BigInteger.valueOf(SieveCacheKt.NodeMetaAndPreviousMask);
        BI_MIN_INT = valueOf;
        BigInteger valueOf2 = BigInteger.valueOf(SieveCacheKt.NodeLinkMask);
        BI_MAX_INT = valueOf2;
        BigInteger valueOf3 = BigInteger.valueOf(Long.MIN_VALUE);
        BI_MIN_LONG = valueOf3;
        BigInteger valueOf4 = BigInteger.valueOf(Long.MAX_VALUE);
        BI_MAX_LONG = valueOf4;
        BD_MIN_LONG = new BigDecimal(valueOf3);
        BD_MAX_LONG = new BigDecimal(valueOf4);
        BD_MIN_INT = new BigDecimal(valueOf);
        BD_MAX_INT = new BigDecimal(valueOf2);
    }

    public CBORParser(IOContext iOContext, int i3, int i4, ObjectCodec objectCodec, ByteQuadsCanonicalizer byteQuadsCanonicalizer, InputStream inputStream, byte[] bArr, int i5, int i6, boolean z2) {
        super(i3);
        DupDetector dupDetector = null;
        this._nameCopyBuffer = null;
        this._nameCopied = false;
        this._byteArrayBuilder = null;
        this._tagValue = -1;
        this._tokenIncomplete = false;
        this._quadBuffer = ParserMinimalBase.NO_INTS;
        this._numTypesValid = 0;
        this._ioContext = iOContext;
        this._objectCodec = objectCodec;
        this._symbols = byteQuadsCanonicalizer;
        this._symbolsCanonical = byteQuadsCanonicalizer.isCanonicalizing();
        this._inputStream = inputStream;
        this._inputBuffer = bArr;
        this._inputPtr = i5;
        this._inputEnd = i6;
        this._bufferRecyclable = z2;
        this._textBuffer = iOContext.constructTextBuffer();
        this._streamReadContext = CBORReadContext.createRootContext(JsonParser.Feature.STRICT_DUPLICATE_DETECTION.enabledIn(i3) ? DupDetector.rootDetector((JsonParser) this) : dupDetector);
        this._tokenInputRow = -1;
        this._tokenInputCol = -1;
    }

    private final String _addDecodedToSymbols(int i3, String str) {
        if (i3 < 5) {
            return this._symbols.addName(str, this._quad1);
        }
        if (i3 < 9) {
            return this._symbols.addName(str, this._quad1, this._quad2);
        }
        if (i3 < 13) {
            return this._symbols.addName(str, this._quad1, this._quad2, this._quad3);
        }
        return this._symbols.addName(str, this._quadBuffer, (i3 + 3) >> 2);
    }

    private final BigInteger _bigNegative(long j2) {
        return _bigPositive(j2).negate().subtract(BigInteger.ONE);
    }

    private final BigInteger _bigPositive(long j2) {
        return BigInteger.valueOf((j2 << 1) >>> 1).or(BIT_63);
    }

    private final int _decode16Bits() throws IOException {
        int i3 = this._inputPtr;
        int i4 = i3 + 1;
        if (i4 >= this._inputEnd) {
            return _slow16();
        }
        byte[] bArr = this._inputBuffer;
        int i5 = ((bArr[i3] & 255) << 8) + (bArr[i4] & 255);
        this._inputPtr = i3 + 2;
        return i5;
    }

    private final int _decode32Bits() throws IOException {
        int i3 = this._inputPtr;
        if (i3 + 3 >= this._inputEnd) {
            return _slow32();
        }
        byte[] bArr = this._inputBuffer;
        int i4 = (bArr[i3] << Ascii.CAN) + ((bArr[i3 + 1] & 255) << 16) + ((bArr[i3 + 2] & 255) << 8) + (bArr[i3 + 3] & 255);
        this._inputPtr = i3 + 4;
        return i4;
    }

    private final long _decode64Bits() throws IOException {
        int i3 = this._inputPtr;
        if (i3 + 7 >= this._inputEnd) {
            return _slow64();
        }
        byte[] bArr = this._inputBuffer;
        int i4 = (bArr[i3] << Ascii.CAN) + ((bArr[i3 + 1] & 255) << 16) + ((bArr[i3 + 2] & 255) << 8) + (bArr[i3 + 3] & 255);
        int i5 = (bArr[i3 + 4] << Ascii.CAN) + ((bArr[i3 + 5] & 255) << 16);
        int i6 = i3 + 7;
        this._inputPtr = i3 + 8;
        return _long(i4, i5 + ((bArr[i3 + 6] & 255) << 8) + (bArr[i6] & 255));
    }

    private final int _decode8Bits() throws IOException {
        if (this._inputPtr >= this._inputEnd) {
            loadMoreGuaranteed();
        }
        byte[] bArr = this._inputBuffer;
        int i3 = this._inputPtr;
        this._inputPtr = i3 + 1;
        return bArr[i3] & 255;
    }

    private int _decodeChunkLength(int i3) throws IOException {
        if (this._inputPtr >= this._inputEnd) {
            loadMoreGuaranteed();
        }
        byte[] bArr = this._inputBuffer;
        int i4 = this._inputPtr;
        this._inputPtr = i4 + 1;
        byte b3 = bArr[i4];
        byte b4 = b3 & 255;
        if (b4 == 255) {
            return -1;
        }
        int i5 = b4 >> 5;
        if (i5 == i3) {
            int _decodeExplicitLength = _decodeExplicitLength(b3 & 31);
            if (_decodeExplicitLength >= 0) {
                return _decodeExplicitLength;
            }
            throw _constructReadException("Illegal chunked-length indicator within chunked-length value (major type %d)", (Object) Integer.valueOf(i3));
        }
        throw _constructError(String.format("Mismatched chunk in chunked content: expected major type %d but encountered %d (byte 0x%02X)", new Object[]{Integer.valueOf(i3), Integer.valueOf(i5), Integer.valueOf(b4)}));
    }

    private final String _decodeChunkedName() throws IOException {
        _finishChunkedText();
        return this._textBuffer.contentsAsString();
    }

    private final int _decodeChunkedUTF8_3(int i3) throws IOException {
        int i4 = i3 & 15;
        int _nextChunkedByte = _nextChunkedByte();
        if ((_nextChunkedByte & 192) != 128) {
            _reportInvalidOther(_nextChunkedByte & 255, this._inputPtr);
        }
        int i5 = (i4 << 6) | (_nextChunkedByte & 63);
        int _nextChunkedByte2 = _nextChunkedByte();
        if ((_nextChunkedByte2 & 192) != 128) {
            _reportInvalidOther(_nextChunkedByte2 & 255, this._inputPtr);
        }
        return (i5 << 6) | (_nextChunkedByte2 & 63);
    }

    private final int _decodeChunkedUTF8_4(int i3) throws IOException {
        int _nextChunkedByte = _nextChunkedByte();
        if ((_nextChunkedByte & 192) != 128) {
            _reportInvalidOther(_nextChunkedByte & 255, this._inputPtr);
        }
        int i4 = ((i3 & 7) << 6) | (_nextChunkedByte & 63);
        int _nextChunkedByte2 = _nextChunkedByte();
        if ((_nextChunkedByte2 & 192) != 128) {
            _reportInvalidOther(_nextChunkedByte2 & 255, this._inputPtr);
        }
        int i5 = (i4 << 6) | (_nextChunkedByte2 & 63);
        int _nextChunkedByte3 = _nextChunkedByte();
        if ((_nextChunkedByte3 & 192) != 128) {
            _reportInvalidOther(_nextChunkedByte3 & 255, this._inputPtr);
        }
        return ((i5 << 6) | (_nextChunkedByte3 & 63)) - 65536;
    }

    private final String _decodeContiguousName(int i3) throws IOException {
        int i4;
        char[] emptyAndGetCurrentSegment = this._textBuffer.emptyAndGetCurrentSegment();
        if (emptyAndGetCurrentSegment.length < i3) {
            emptyAndGetCurrentSegment = this._textBuffer.expandCurrentSegment(i3);
        }
        int i5 = this._inputPtr;
        this._inputPtr = i5 + i3;
        int[] iArr = UTF8_UNIT_CODES;
        byte[] bArr = this._inputBuffer;
        int i6 = i5 + i3;
        int i7 = 0;
        while (true) {
            byte b3 = bArr[i4] & 255;
            if (iArr[b3] != 0) {
                while (i4 < i6) {
                    int i8 = i4 + 1;
                    byte b4 = bArr[i4];
                    byte b5 = b4 & 255;
                    int i9 = iArr[b5];
                    if (i9 != 0) {
                        if (i8 + i9 > i6) {
                            _reportTruncatedUTF8InName(i3, (i3 - (i6 - i8)) - 1, b5, i9);
                        }
                        if (i9 == 1) {
                            i4 += 2;
                            byte b6 = bArr[i8];
                            if ((b6 & MessagePack.Code.NIL) != 128) {
                                _reportInvalidOther(b6 & 255, i4);
                            }
                            b5 = ((b4 & 31) << 6) | (b6 & Utf8.REPLACEMENT_BYTE);
                        } else if (i9 == 2) {
                            int i10 = i4 + 2;
                            byte b7 = bArr[i8];
                            if ((b7 & MessagePack.Code.NIL) != 128) {
                                _reportInvalidOther(b7 & 255, i10);
                            }
                            i4 += 3;
                            byte b8 = bArr[i10];
                            if ((b8 & MessagePack.Code.NIL) != 128) {
                                _reportInvalidOther(b8 & 255, i4);
                            }
                            b5 = ((b7 & Utf8.REPLACEMENT_BYTE) << 6) | ((b4 & Ascii.SI) << Ascii.FF) | (b8 & Utf8.REPLACEMENT_BYTE);
                        } else if (i9 == 3) {
                            byte b9 = ((bArr[i8] & Utf8.REPLACEMENT_BYTE) << Ascii.FF) | ((b4 & 7) << Ascii.DC2);
                            int i11 = i4 + 3;
                            i4 += 4;
                            int i12 = ((b9 | ((bArr[i4 + 2] & Utf8.REPLACEMENT_BYTE) << 6)) | (bArr[i11] & Utf8.REPLACEMENT_BYTE)) - 65536;
                            emptyAndGetCurrentSegment[i7] = (char) ((i12 >> 10) | GeneratorBase.SURR1_FIRST);
                            b5 = (i12 & 1023) | 56320;
                            i7++;
                        } else {
                            throw _constructReadException("Invalid UTF-8 byte 0x%s in Object property name", (Object) Integer.toHexString(b5));
                        }
                    } else {
                        i4 = i8;
                    }
                    emptyAndGetCurrentSegment[i7] = (char) b5;
                    i7++;
                }
                return this._textBuffer.setCurrentAndReturn(i7);
            }
            int i13 = i7 + 1;
            emptyAndGetCurrentSegment[i7] = (char) b3;
            i5 = i4 + 1;
            if (i5 == i6) {
                return this._textBuffer.setCurrentAndReturn(i13);
            }
            i7 = i13;
        }
    }

    private final int _decodeExplicitLength(int i3) throws IOException {
        if (i3 == 31) {
            return -1;
        }
        if (i3 <= 23) {
            return i3;
        }
        int i4 = i3 - 24;
        if (i4 == 0) {
            return _decode8Bits();
        }
        if (i4 == 1) {
            return _decode16Bits();
        }
        if (i4 == 2) {
            return _decode32Bits();
        }
        if (i4 == 3) {
            long _decode64Bits = _decode64Bits();
            if (_decode64Bits >= 0 && _decode64Bits <= SieveCacheKt.NodeLinkMask) {
                return (int) _decode64Bits;
            }
            throw _constructError("Illegal length for " + currentToken() + ": " + _decode64Bits);
        }
        throw _constructError(String.format("Invalid length for %s: 0x%02X,", new Object[]{currentToken(), Integer.valueOf(i3)}));
    }

    private float _decodeHalfSizeFloat() throws IOException {
        int _decode16Bits = _decode16Bits();
        int i3 = 65535 & _decode16Bits;
        boolean z2 = (i3 >> 15) != 0;
        int i4 = (i3 >> 10) & 31;
        int i5 = _decode16Bits & 1023;
        if (i4 == 0) {
            float f2 = (float) ((((double) i5) / MATH_POW_2_10) * MATH_POW_2_NEG14);
            return z2 ? -f2 : f2;
        } else if (i4 != 31) {
            float pow = (float) (((((double) i5) / MATH_POW_2_10) + 1.0d) * Math.pow(2.0d, (double) (i4 - 15)));
            return z2 ? -pow : pow;
        } else if (i5 != 0) {
            return Float.NaN;
        } else {
            return z2 ? Float.NEGATIVE_INFINITY : Float.POSITIVE_INFINITY;
        }
    }

    private final String _decodeLongerName(int i3) throws IOException {
        if (i3 == 0) {
            return "";
        }
        if (this._inputEnd - this._inputPtr < i3) {
            if (i3 >= this._inputBuffer.length) {
                _finishLongText(i3);
                return this._textBuffer.contentsAsString();
            }
            _loadToHaveAtLeast(i3);
        }
        if (!this._symbolsCanonical) {
            return _decodeContiguousName(i3);
        }
        String _findDecodedFromSymbols = _findDecodedFromSymbols(i3);
        if (_findDecodedFromSymbols == null) {
            return _addDecodedToSymbols(i3, _decodeContiguousName(i3));
        }
        this._inputPtr += i3;
        return _findDecodedFromSymbols;
    }

    private final int _decodeTag(int i3) throws IOException {
        if (i3 <= 23) {
            return i3;
        }
        int i4 = i3 - 24;
        if (i4 == 0) {
            return _decode8Bits();
        }
        if (i4 == 1) {
            return _decode16Bits();
        }
        if (i4 == 2) {
            return _decode32Bits();
        }
        if (i4 == 3) {
            long _decode64Bits = _decode64Bits();
            if (_decode64Bits >= SieveCacheKt.NodeMetaAndPreviousMask && _decode64Bits <= SieveCacheKt.NodeLinkMask) {
                return (int) _decode64Bits;
            }
            throw _constructReadException("Illegal Tag value: %d", (Object) Long.valueOf(_decode64Bits));
        }
        throw _constructReadException("Invalid low bits for Tag token: 0x%s", (Object) Integer.toHexString(i3));
    }

    private final int _decodeUTF8_3(int i3) throws IOException {
        int i4 = i3 & 15;
        int _nextByte = _nextByte();
        if ((_nextByte & 192) != 128) {
            _reportInvalidOther(_nextByte & 255, this._inputPtr);
        }
        int i5 = (i4 << 6) | (_nextByte & 63);
        int _nextByte2 = _nextByte();
        if ((_nextByte2 & 192) != 128) {
            _reportInvalidOther(_nextByte2 & 255, this._inputPtr);
        }
        return (i5 << 6) | (_nextByte2 & 63);
    }

    private final int _decodeUTF8_4(int i3) throws IOException {
        int _nextByte = _nextByte();
        if ((_nextByte & 192) != 128) {
            _reportInvalidOther(_nextByte & 255, this._inputPtr);
        }
        int i4 = ((i3 & 7) << 6) | (_nextByte & 63);
        int _nextByte2 = _nextByte();
        if ((_nextByte2 & 192) != 128) {
            _reportInvalidOther(_nextByte2 & 255, this._inputPtr);
        }
        int i5 = (i4 << 6) | (_nextByte2 & 63);
        int _nextByte3 = _nextByte();
        if ((_nextByte3 & 192) != 128) {
            _reportInvalidOther(_nextByte3 & 255, this._inputPtr);
        }
        return ((i5 << 6) | (_nextByte3 & 63)) - 65536;
    }

    private final String _findDecodedFromSymbols(int i3) throws IOException {
        if (i3 < 5) {
            int i4 = this._inputPtr;
            byte[] bArr = this._inputBuffer;
            int _padQuadForNulls = _padQuadForNulls(bArr[i4]);
            if (i3 > 1) {
                _padQuadForNulls = (bArr[i4 + 1] & 255) + (_padQuadForNulls << 8);
                if (i3 > 2) {
                    _padQuadForNulls = (_padQuadForNulls << 8) + (bArr[i4 + 2] & 255);
                    if (i3 > 3) {
                        _padQuadForNulls = (_padQuadForNulls << 8) + (bArr[i4 + 3] & 255);
                    }
                }
            }
            this._quad1 = _padQuadForNulls;
            return this._symbols.findName(_padQuadForNulls);
        }
        byte[] bArr2 = this._inputBuffer;
        int i5 = this._inputPtr;
        int i6 = i5 + 4;
        byte b3 = (((((bArr2[i5 + 1] & 255) | ((bArr2[i5] & 255) << 8)) << 8) | (bArr2[i5 + 2] & 255)) << 8) | (bArr2[i5 + 3] & 255);
        if (i3 < 9) {
            int i7 = i5 + 5;
            int _padQuadForNulls2 = _padQuadForNulls(bArr2[i6]);
            int i8 = i3 - 5;
            if (i8 > 0) {
                int i9 = _padQuadForNulls2 << 8;
                int i10 = i5 + 6;
                int i11 = i9 + (bArr2[i7] & 255);
                if (i8 > 1) {
                    int i12 = i5 + 7;
                    _padQuadForNulls2 = (i11 << 8) + (bArr2[i10] & 255);
                    if (i8 > 2) {
                        _padQuadForNulls2 = (_padQuadForNulls2 << 8) + (bArr2[i12] & 255);
                    }
                } else {
                    _padQuadForNulls2 = i11;
                }
            }
            this._quad1 = b3;
            this._quad2 = _padQuadForNulls2;
            return this._symbols.findName((int) b3, _padQuadForNulls2);
        }
        int i13 = i5 + 8;
        byte b4 = (((((bArr2[i5 + 5] & 255) | ((bArr2[i6] & 255) << 8)) << 8) | (bArr2[i5 + 6] & 255)) << 8) | (bArr2[i5 + 7] & 255);
        if (i3 >= 13) {
            return _findDecodedLong(i3, b3, b4);
        }
        int i14 = i5 + 9;
        int _padQuadForNulls3 = _padQuadForNulls(bArr2[i13]);
        int i15 = i3 - 9;
        if (i15 > 0) {
            int i16 = _padQuadForNulls3 << 8;
            int i17 = i5 + 10;
            int i18 = i16 + (bArr2[i14] & 255);
            if (i15 > 1) {
                int i19 = i5 + 11;
                _padQuadForNulls3 = (i18 << 8) + (bArr2[i17] & 255);
                if (i15 > 2) {
                    _padQuadForNulls3 = (_padQuadForNulls3 << 8) + (bArr2[i19] & 255);
                }
            } else {
                _padQuadForNulls3 = i18;
            }
        }
        this._quad1 = b3;
        this._quad2 = b4;
        this._quad3 = _padQuadForNulls3;
        return this._symbols.findName(b3, b4, _padQuadForNulls3);
    }

    private final String _findDecodedLong(int i3, int i4, int i5) throws IOException {
        int i6;
        int i7;
        int i8 = (i3 + 3) >> 2;
        int[] iArr = this._quadBuffer;
        if (i8 > iArr.length) {
            this._quadBuffer = _growArrayTo(iArr, i8);
        }
        int[] iArr2 = this._quadBuffer;
        iArr2[0] = i4;
        iArr2[1] = i5;
        int i9 = this._inputPtr + 8;
        int i10 = i3 - 8;
        byte[] bArr = this._inputBuffer;
        int i11 = 2;
        while (true) {
            i6 = i9 + 4;
            i7 = i11 + 1;
            this._quadBuffer[i11] = (((((bArr[i9 + 1] & 255) | ((bArr[i9] & 255) << 8)) << 8) | (bArr[i9 + 2] & 255)) << 8) | (bArr[i9 + 3] & 255);
            i10 -= 4;
            if (i10 <= 3) {
                break;
            }
            i9 = i6;
            i11 = i7;
        }
        if (i10 > 0) {
            int _padQuadForNulls = _padQuadForNulls(bArr[i6]);
            if (i10 > 1) {
                _padQuadForNulls = (bArr[i9 + 5] & 255) + (_padQuadForNulls << 8);
                if (i10 > 2) {
                    _padQuadForNulls = (_padQuadForNulls << 8) + (bArr[i9 + 6] & 255);
                }
            }
            this._quadBuffer[i7] = _padQuadForNulls;
            i7 = i11 + 2;
        }
        return this._symbols.findName(this._quadBuffer, i7);
    }

    private final void _finishChunkedText() throws IOException {
        int i3;
        char[] emptyAndGetCurrentSegment = this._textBuffer.emptyAndGetCurrentSegment();
        int[] iArr = UTF8_UNIT_CODES;
        int length = emptyAndGetCurrentSegment.length;
        byte[] bArr = this._inputBuffer;
        this._chunkEnd = this._inputPtr;
        this._chunkLeft = 0;
        int i4 = 0;
        while (true) {
            if (this._inputPtr >= this._chunkEnd) {
                if (this._chunkLeft == 0) {
                    int _decodeChunkLength = _decodeChunkLength(3);
                    if (_decodeChunkLength > 0) {
                        this._chunkLeft = _decodeChunkLength;
                        int i5 = this._inputPtr + _decodeChunkLength;
                        int i6 = this._inputEnd;
                        if (i5 <= i6) {
                            this._chunkLeft = 0;
                            this._chunkEnd = i5;
                        } else {
                            this._chunkLeft = i5 - i6;
                            this._chunkEnd = i6;
                        }
                    } else if (_decodeChunkLength != 0) {
                        this._textBuffer.setCurrentLength(i4);
                        return;
                    }
                }
                if (this._inputPtr >= this._inputEnd) {
                    loadMoreGuaranteed();
                    int i7 = this._inputPtr + this._chunkLeft;
                    int i8 = this._inputEnd;
                    if (i7 <= i8) {
                        this._chunkLeft = 0;
                        this._chunkEnd = i7;
                    } else {
                        this._chunkLeft = i7 - i8;
                        this._chunkEnd = i8;
                    }
                }
            }
            int i9 = this._inputPtr;
            this._inputPtr = i9 + 1;
            byte b3 = bArr[i9];
            int i10 = b3 & 255;
            int i11 = iArr[i10];
            if (i11 != 0 || i4 >= length) {
                if (i11 != 0) {
                    if (i11 == 1) {
                        int _nextChunkedByte = _nextChunkedByte();
                        if ((_nextChunkedByte & 192) != 128) {
                            _reportInvalidOther(_nextChunkedByte & 255, this._inputPtr);
                        }
                        i10 = (_nextChunkedByte & 63) | ((b3 & 31) << 6);
                    } else if (i11 == 2) {
                        i10 = _decodeChunkedUTF8_3(i10);
                    } else if (i11 != 3) {
                        _reportInvalidChar(i10);
                    } else {
                        int _decodeChunkedUTF8_4 = _decodeChunkedUTF8_4(i10);
                        if (i4 >= emptyAndGetCurrentSegment.length) {
                            emptyAndGetCurrentSegment = this._textBuffer.finishCurrentSegment();
                            length = emptyAndGetCurrentSegment.length;
                            i4 = 0;
                        }
                        emptyAndGetCurrentSegment[i4] = (char) ((_decodeChunkedUTF8_4 >> 10) | GeneratorBase.SURR1_FIRST);
                        i10 = (_decodeChunkedUTF8_4 & 1023) | 56320;
                        i4++;
                    }
                }
                if (i4 >= length) {
                    emptyAndGetCurrentSegment = this._textBuffer.finishCurrentSegment();
                    length = emptyAndGetCurrentSegment.length;
                    i4 = 0;
                }
                i3 = i4 + 1;
                emptyAndGetCurrentSegment[i4] = (char) i10;
            } else {
                i3 = i4 + 1;
                emptyAndGetCurrentSegment[i4] = (char) i10;
            }
            i4 = i3;
        }
    }

    private final void _finishLongText(int i3) throws IOException {
        int i4;
        char[] emptyAndGetCurrentSegment = this._textBuffer.emptyAndGetCurrentSegment();
        int[] iArr = UTF8_UNIT_CODES;
        int length = emptyAndGetCurrentSegment.length;
        int i5 = 0;
        while (true) {
            i3--;
            if (i3 >= 0) {
                int _nextByte = _nextByte();
                int i6 = _nextByte & 255;
                int i7 = iArr[i6];
                if (i7 != 0 || i5 >= length) {
                    i3 -= i7;
                    if (i3 >= 0) {
                        if (i7 != 0) {
                            if (i7 == 1) {
                                int _nextByte2 = _nextByte();
                                if ((_nextByte2 & 192) != 128) {
                                    _reportInvalidOther(_nextByte2 & 255, this._inputPtr);
                                }
                                i6 = (_nextByte2 & 63) | ((_nextByte & 31) << 6);
                            } else if (i7 == 2) {
                                i6 = _decodeUTF8_3(i6);
                            } else if (i7 != 3) {
                                _reportInvalidChar(i6);
                            } else {
                                int _decodeUTF8_4 = _decodeUTF8_4(i6);
                                if (i5 >= emptyAndGetCurrentSegment.length) {
                                    emptyAndGetCurrentSegment = this._textBuffer.finishCurrentSegment();
                                    length = emptyAndGetCurrentSegment.length;
                                    i5 = 0;
                                }
                                emptyAndGetCurrentSegment[i5] = (char) ((_decodeUTF8_4 >> 10) | GeneratorBase.SURR1_FIRST);
                                i6 = (_decodeUTF8_4 & 1023) | 56320;
                                i5++;
                            }
                        }
                        if (i5 >= length) {
                            emptyAndGetCurrentSegment = this._textBuffer.finishCurrentSegment();
                            length = emptyAndGetCurrentSegment.length;
                            i5 = 0;
                        }
                        i4 = i5 + 1;
                        emptyAndGetCurrentSegment[i5] = (char) i6;
                    } else {
                        throw _constructReadException("Malformed UTF-8 character at the end of a (non-chunked) text segment");
                    }
                } else {
                    i4 = i5 + 1;
                    emptyAndGetCurrentSegment[i5] = (char) i6;
                }
                i5 = i4;
            } else {
                this._textBuffer.setCurrentLength(i5);
                return;
            }
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:38:0x00cc A[LOOP:1: B:12:0x0031->B:38:0x00cc, LOOP_END] */
    /* JADX WARNING: Removed duplicated region for block: B:41:0x00c5 A[SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final java.lang.String _finishShortText(int r12) throws java.io.IOException {
        /*
            r11 = this;
            com.fasterxml.jackson.core.util.TextBuffer r0 = r11._textBuffer
            char[] r0 = r0.emptyAndGetCurrentSegment()
            int r1 = r0.length
            if (r1 >= r12) goto L_0x000f
            com.fasterxml.jackson.core.util.TextBuffer r0 = r11._textBuffer
            char[] r0 = r0.expandCurrentSegment(r12)
        L_0x000f:
            int r1 = r11._inputPtr
            int r2 = r1 + r12
            r11._inputPtr = r2
            byte[] r2 = r11._inputBuffer
            int r12 = r12 + r1
            r3 = 0
        L_0x0019:
            byte r4 = r2[r1]
            if (r4 < 0) goto L_0x002f
            int r5 = r3 + 1
            char r4 = (char) r4
            r0[r3] = r4
            int r1 = r1 + 1
            if (r1 != r12) goto L_0x002d
            com.fasterxml.jackson.core.util.TextBuffer r11 = r11._textBuffer
            java.lang.String r11 = r11.setCurrentAndReturn(r5)
            return r11
        L_0x002d:
            r3 = r5
            goto L_0x0019
        L_0x002f:
            int[] r4 = UTF8_UNIT_CODES
        L_0x0031:
            int r5 = r1 + 1
            byte r6 = r2[r1]
            r7 = r6 & 255(0xff, float:3.57E-43)
            r8 = r4[r7]
            if (r8 == 0) goto L_0x00bd
            r9 = 128(0x80, float:1.794E-43)
            r10 = 1
            if (r8 == r10) goto L_0x00a7
            r10 = 2
            if (r8 == r10) goto L_0x007f
            r9 = 3
            if (r8 == r9) goto L_0x004b
            r11._reportInvalidInitial(r7)
            goto L_0x00bd
        L_0x004b:
            r6 = r6 & 7
            int r6 = r6 << 18
            int r7 = r1 + 2
            byte r5 = r2[r5]
            r5 = r5 & 63
            int r5 = r5 << 12
            r5 = r5 | r6
            int r6 = r1 + 3
            byte r7 = r2[r7]
            r7 = r7 & 63
            int r7 = r7 << 6
            r5 = r5 | r7
            int r1 = r1 + 4
            byte r6 = r2[r6]
            r6 = r6 & 63
            r5 = r5 | r6
            r6 = 65536(0x10000, float:9.18355E-41)
            int r5 = r5 - r6
            int r6 = r3 + 1
            int r7 = r5 >> 10
            r8 = 55296(0xd800, float:7.7486E-41)
            r7 = r7 | r8
            char r7 = (char) r7
            r0[r3] = r7
            r3 = r5 & 1023(0x3ff, float:1.434E-42)
            r5 = 56320(0xdc00, float:7.8921E-41)
            r7 = r3 | r5
            r3 = r6
            goto L_0x00be
        L_0x007f:
            int r7 = r1 + 2
            byte r5 = r2[r5]
            r8 = r5 & 192(0xc0, float:2.69E-43)
            if (r8 == r9) goto L_0x008c
            r8 = r5 & 255(0xff, float:3.57E-43)
            r11._reportInvalidOther(r8, r7)
        L_0x008c:
            int r1 = r1 + 3
            byte r7 = r2[r7]
            r8 = r7 & 192(0xc0, float:2.69E-43)
            if (r8 == r9) goto L_0x0099
            r8 = r7 & 255(0xff, float:3.57E-43)
            r11._reportInvalidOther(r8, r1)
        L_0x0099:
            r6 = r6 & 15
            int r6 = r6 << 12
            r5 = r5 & 63
            int r5 = r5 << 6
            r5 = r5 | r6
            r6 = r7 & 63
            r7 = r5 | r6
            goto L_0x00be
        L_0x00a7:
            int r1 = r1 + 2
            byte r5 = r2[r5]
            r7 = r5 & 192(0xc0, float:2.69E-43)
            if (r7 == r9) goto L_0x00b4
            r7 = r5 & 255(0xff, float:3.57E-43)
            r11._reportInvalidOther(r7, r1)
        L_0x00b4:
            r6 = r6 & 31
            int r6 = r6 << 6
            r5 = r5 & 63
            r7 = r6 | r5
            goto L_0x00be
        L_0x00bd:
            r1 = r5
        L_0x00be:
            int r5 = r3 + 1
            char r6 = (char) r7
            r0[r3] = r6
            if (r1 < r12) goto L_0x00cc
            com.fasterxml.jackson.core.util.TextBuffer r11 = r11._textBuffer
            java.lang.String r11 = r11.setCurrentAndReturn(r5)
            return r11
        L_0x00cc:
            r3 = r5
            goto L_0x0031
        */
        throw new UnsupportedOperationException("Method not decompiled: com.fasterxml.jackson.dataformat.cbor.CBORParser._finishShortText(int):java.lang.String");
    }

    private final byte[] _getBinaryFromString(Base64Variant base64Variant) throws IOException {
        if (this._tokenIncomplete) {
            _finishToken();
        }
        if (this._binaryValue == null) {
            ByteArrayBuilder _getByteArrayBuilder = _getByteArrayBuilder();
            _decodeBase64(getText(), _getByteArrayBuilder, base64Variant);
            this._binaryValue = _getByteArrayBuilder.toByteArray();
        }
        return this._binaryValue;
    }

    private static int[] _growArrayTo(int[] iArr, int i3) {
        return Arrays.copyOf(iArr, i3 + 4);
    }

    private static final long _long(int i3, int i4) {
        return (((long) i3) << 32) + ((((long) i4) << 32) >>> 32);
    }

    private final int _nextByte() throws IOException {
        int i3 = this._inputPtr;
        if (i3 < this._inputEnd) {
            byte b3 = this._inputBuffer[i3];
            this._inputPtr = i3 + 1;
            return b3;
        }
        loadMoreGuaranteed();
        byte[] bArr = this._inputBuffer;
        int i4 = this._inputPtr;
        this._inputPtr = i4 + 1;
        return bArr[i4];
    }

    private final int _nextChunkedByte() throws IOException {
        int i3 = this._inputPtr;
        if (i3 >= this._chunkEnd) {
            return _nextChunkedByte2();
        }
        byte b3 = this._inputBuffer[i3];
        this._inputPtr = i3 + 1;
        return b3;
    }

    private final int _nextChunkedByte2() throws IOException {
        if (this._inputPtr >= this._inputEnd) {
            loadMoreGuaranteed();
            int i3 = this._chunkLeft;
            if (i3 > 0) {
                int i4 = this._inputPtr;
                int i5 = i3 + i4;
                int i6 = this._inputEnd;
                if (i5 <= i6) {
                    this._chunkLeft = 0;
                    this._chunkEnd = i5;
                } else {
                    this._chunkLeft = i5 - i6;
                    this._chunkEnd = i6;
                }
                byte[] bArr = this._inputBuffer;
                this._inputPtr = i4 + 1;
                return bArr[i4];
            }
        }
        int _decodeChunkLength = _decodeChunkLength(3);
        if (_decodeChunkLength <= 0) {
            _reportInvalidEOF(": chunked Text ends with partial UTF-8 character", JsonToken.VALUE_STRING);
        }
        if (this._inputPtr >= this._inputEnd) {
            loadMoreGuaranteed();
        }
        int i7 = this._inputPtr;
        int i8 = _decodeChunkLength + i7;
        int i9 = this._inputEnd;
        if (i8 <= i9) {
            this._chunkLeft = 0;
            this._chunkEnd = i8;
        } else {
            this._chunkLeft = i8 - i9;
            this._chunkEnd = i9;
        }
        byte[] bArr2 = this._inputBuffer;
        this._inputPtr = i7 + 1;
        return bArr2[i7];
    }

    private static final int _padQuadForNulls(int i3) {
        return (i3 & 255) | -256;
    }

    private int _readAndWriteBytes(OutputStream outputStream, int i3) throws IOException {
        int i4 = i3;
        while (i4 > 0) {
            int i5 = this._inputEnd;
            int i6 = this._inputPtr;
            int i7 = i5 - i6;
            if (i6 >= i5) {
                if (!loadMore()) {
                    _reportIncompleteBinaryRead(i3, i3 - i4);
                }
                i7 = this._inputEnd - this._inputPtr;
            }
            int min = Math.min(i7, i4);
            outputStream.write(this._inputBuffer, this._inputPtr, min);
            this._inputPtr += min;
            i4 -= min;
        }
        this._tokenIncomplete = false;
        return i3;
    }

    private String _reportTruncatedUTF8InName(int i3, int i4, int i5, int i6) throws IOException {
        throw _constructReadException(String.format("Truncated UTF-8 character in Map key (%d bytes): byte 0x%02X at offset #%d indicated %d more bytes needed", new Object[]{Integer.valueOf(i3), Integer.valueOf(i5), Integer.valueOf(i4), Integer.valueOf(i6)}));
    }

    private final int _slow16() throws IOException {
        if (this._inputPtr >= this._inputEnd) {
            loadMoreGuaranteed();
        }
        byte[] bArr = this._inputBuffer;
        int i3 = this._inputPtr;
        int i4 = i3 + 1;
        this._inputPtr = i4;
        byte b3 = bArr[i3] & 255;
        if (i4 >= this._inputEnd) {
            loadMoreGuaranteed();
        }
        byte[] bArr2 = this._inputBuffer;
        int i5 = this._inputPtr;
        this._inputPtr = i5 + 1;
        return (b3 << 8) + (bArr2[i5] & 255);
    }

    private final int _slow32() throws IOException {
        if (this._inputPtr >= this._inputEnd) {
            loadMoreGuaranteed();
        }
        byte[] bArr = this._inputBuffer;
        int i3 = this._inputPtr;
        int i4 = i3 + 1;
        this._inputPtr = i4;
        byte b3 = bArr[i3];
        if (i4 >= this._inputEnd) {
            loadMoreGuaranteed();
        }
        byte[] bArr2 = this._inputBuffer;
        int i5 = this._inputPtr;
        int i6 = i5 + 1;
        this._inputPtr = i6;
        int i7 = (b3 << 8) + (bArr2[i5] & 255);
        if (i6 >= this._inputEnd) {
            loadMoreGuaranteed();
        }
        byte[] bArr3 = this._inputBuffer;
        int i8 = this._inputPtr;
        int i9 = i8 + 1;
        this._inputPtr = i9;
        int i10 = (i7 << 8) + (bArr3[i8] & 255);
        if (i9 >= this._inputEnd) {
            loadMoreGuaranteed();
        }
        byte[] bArr4 = this._inputBuffer;
        int i11 = this._inputPtr;
        this._inputPtr = i11 + 1;
        return (i10 << 8) + (bArr4[i11] & 255);
    }

    private final long _slow64() throws IOException {
        return _long(_decode32Bits(), _decode32Bits());
    }

    public final boolean _checkNextIsEndArray() throws IOException {
        if (!this._streamReadContext.expectMoreValues()) {
            this._tagValue = -1;
            this._streamReadContext = this._streamReadContext.getParent();
            this._currToken = JsonToken.END_ARRAY;
            return true;
        }
        byte[] bArr = this._inputBuffer;
        int i3 = this._inputPtr;
        this._inputPtr = i3 + 1;
        byte b3 = bArr[i3];
        if (((b3 >> 5) & 7) == 6) {
            int _decodeTag = _decodeTag(b3 & 31);
            if (this._inputPtr < this._inputEnd || loadMore()) {
                byte[] bArr2 = this._inputBuffer;
                int i4 = this._inputPtr;
                this._inputPtr = i4 + 1;
                if (((bArr2[i4] >> 5) & 7) == 6) {
                    _reportError("Multiple tags not allowed per value (first tag: " + _decodeTag + ")");
                }
            } else {
                _eofAsNextToken();
                return false;
            }
        }
        this._inputPtr--;
        return nextToken() == JsonToken.END_ARRAY;
    }

    public final boolean _checkNextIsIntInArray(String str) throws IOException {
        int i3 = -1;
        if (!this._streamReadContext.expectMoreValues()) {
            this._tagValue = -1;
            this._streamReadContext = this._streamReadContext.getParent();
            this._currToken = JsonToken.END_ARRAY;
            return false;
        } else if (this._inputPtr < this._inputEnd || loadMore()) {
            byte[] bArr = this._inputBuffer;
            int i4 = this._inputPtr;
            this._inputPtr = i4 + 1;
            byte b3 = bArr[i4];
            byte b4 = b3 & 255;
            int i5 = b4 >> 5;
            byte b5 = b3 & 31;
            if (i5 == 6) {
                i3 = _decodeTag(b5);
                if (this._inputPtr < this._inputEnd || loadMore()) {
                    byte[] bArr2 = this._inputBuffer;
                    int i6 = this._inputPtr;
                    this._inputPtr = i6 + 1;
                    byte b6 = bArr2[i6];
                    b4 = b6 & 255;
                    i5 = b4 >> 5;
                    b5 = b6 & 31;
                } else {
                    _eofAsNextToken();
                    return false;
                }
            }
            if (i5 == 0) {
                this._numTypesValid = 1;
                if (b5 <= 23) {
                    this._numberInt = b5;
                } else {
                    int i7 = b5 - 24;
                    if (i7 == 0) {
                        this._numberInt = _decode8Bits();
                    } else if (i7 == 1) {
                        this._numberInt = _decode16Bits();
                    } else if (i7 == 2) {
                        int _decode32Bits = _decode32Bits();
                        if (_decode32Bits >= 0) {
                            this._numberInt = _decode32Bits;
                        } else {
                            this._numberLong = ((long) _decode32Bits) & 4294967295L;
                            this._numTypesValid = 2;
                        }
                    } else if (i7 != 3) {
                        _invalidToken(b4);
                    } else {
                        long _decode64Bits = _decode64Bits();
                        if (_decode64Bits >= 0) {
                            this._numberLong = _decode64Bits;
                            this._numTypesValid = 2;
                        } else {
                            this._numberBigInt = _bigPositive(_decode64Bits);
                            this._numTypesValid = 4;
                        }
                    }
                }
                this._currToken = JsonToken.VALUE_NUMBER_INT;
                return true;
            } else if (i5 != 1) {
                if (i5 != 2) {
                    if (i5 == 6) {
                        _reportError("Multiple tags not allowed per value (first tag: " + i3 + ")");
                    }
                } else if (i3 >= 0) {
                    this._typeByte = b4;
                    this._tokenIncomplete = true;
                    JsonToken _handleTaggedBinary = _handleTaggedBinary(i3);
                    this._currToken = _handleTaggedBinary;
                    return _handleTaggedBinary == JsonToken.VALUE_NUMBER_INT;
                }
                this._inputPtr--;
                nextToken();
                return false;
            } else {
                this._numTypesValid = 1;
                if (b5 <= 23) {
                    this._numberInt = (-b5) - 1;
                } else {
                    int i8 = b5 - 24;
                    if (i8 == 0) {
                        this._numberInt = (-_decode8Bits()) - 1;
                    } else if (i8 == 1) {
                        this._numberInt = (-_decode16Bits()) - 1;
                    } else if (i8 == 2) {
                        int _decode32Bits2 = _decode32Bits();
                        if (_decode32Bits2 < 0) {
                            this._numberLong = (-(((long) _decode32Bits2) & 4294967295L)) - 1;
                            this._numTypesValid = 2;
                        } else {
                            this._numberInt = (-_decode32Bits2) - 1;
                        }
                    } else if (i8 != 3) {
                        _invalidToken(b4);
                    } else {
                        long _decode64Bits2 = _decode64Bits();
                        if (_decode64Bits2 >= 0) {
                            this._numberLong = (-_decode64Bits2) - 1;
                            this._numTypesValid = 2;
                        } else {
                            this._numberBigInt = _bigNegative(_decode64Bits2);
                            this._numTypesValid = 4;
                        }
                    }
                }
                this._currToken = JsonToken.VALUE_NUMBER_INT;
                return true;
            }
        } else {
            _eofAsNextToken();
            return false;
        }
    }

    public void _checkNumericValue(int i3) throws IOException {
        JsonToken jsonToken = this._currToken;
        if (jsonToken != JsonToken.VALUE_NUMBER_INT && jsonToken != JsonToken.VALUE_NUMBER_FLOAT) {
            _reportError("Current token (" + currentToken() + ") not numeric, can not use numeric value accessors");
        }
    }

    public void _closeInput() throws IOException {
        if (this._inputStream != null) {
            if (this._ioContext.isResourceManaged() || isEnabled(JsonParser.Feature.AUTO_CLOSE_SOURCE)) {
                this._inputStream.close();
            }
            this._inputStream = null;
        }
    }

    public final void _decodeNonStringName(int i3) throws IOException {
        String str;
        int i4 = (i3 >> 5) & 7;
        if (i4 == 0) {
            str = _numberToName(i3, false);
        } else if (i4 == 1) {
            str = _numberToName(i3, true);
        } else if (i4 == 2) {
            str = new String(_finishBytes(_decodeExplicitLength(i3 & 31)), UTF8);
        } else {
            if ((i3 & 255) == 255) {
                _reportUnexpectedBreak();
            }
            throw _constructReadException("Unsupported major type (%d) for CBOR Objects, not (yet?) supported, only Strings", (Object) Integer.valueOf(i4));
        }
        this._streamReadContext.setCurrentName(str);
    }

    public final JsonToken _decodePropertyName() throws IOException {
        String str;
        if (this._inputPtr >= this._inputEnd && !loadMore()) {
            _eofAsNextToken();
        }
        byte[] bArr = this._inputBuffer;
        int i3 = this._inputPtr;
        int i4 = i3 + 1;
        this._inputPtr = i4;
        byte b3 = bArr[i3];
        if (((b3 >> 5) & 7) != 3) {
            if (b3 == -1) {
                if (!this._streamReadContext.hasExpectedLength()) {
                    this._streamReadContext = this._streamReadContext.getParent();
                    return JsonToken.END_OBJECT;
                }
                _reportUnexpectedBreak();
            }
            _decodeNonStringName(b3);
            return JsonToken.FIELD_NAME;
        }
        byte b4 = b3 & 31;
        if (b4 > 23) {
            int _decodeExplicitLength = _decodeExplicitLength(b4);
            str = _decodeExplicitLength < 0 ? _decodeChunkedName() : _decodeLongerName(_decodeExplicitLength);
        } else if (b4 == 0) {
            str = "";
        } else {
            if (this._inputEnd - i4 < b4) {
                _loadToHaveAtLeast(b4);
            }
            if (this._symbolsCanonical) {
                String _findDecodedFromSymbols = _findDecodedFromSymbols(b4);
                if (_findDecodedFromSymbols != null) {
                    this._inputPtr += b4;
                    str = _findDecodedFromSymbols;
                } else {
                    str = _addDecodedToSymbols(b4, _decodeContiguousName(b4));
                }
            } else {
                str = _decodeContiguousName(b4);
            }
        }
        this._streamReadContext.setCurrentName(str);
        return JsonToken.FIELD_NAME;
    }

    public JsonToken _decodeSimpleValue(int i3, int i4) throws IOException {
        if (i3 > 24) {
            _invalidToken(i4);
        }
        if (i3 < 24) {
            this._numberInt = i3;
        } else {
            if (this._inputPtr >= this._inputEnd) {
                loadMoreGuaranteed();
            }
            byte[] bArr = this._inputBuffer;
            int i5 = this._inputPtr;
            this._inputPtr = i5 + 1;
            byte b3 = bArr[i5] & 255;
            this._numberInt = b3;
            if (b3 < 32) {
                throw _constructError("Invalid second byte for simple value: 0x" + Integer.toHexString(this._numberInt) + " (only values 0x20 - 0xFF allowed)");
            }
        }
        this._numTypesValid = 1;
        return JsonToken.VALUE_NUMBER_INT;
    }

    public JsonToken _decodeUndefinedValue() throws IOException {
        return JsonToken.VALUE_NULL;
    }

    public JsonToken _eofAsNextToken() throws IOException {
        this._tagValue = -1;
        close();
        _handleEOF();
        this._currToken = null;
        return null;
    }

    public byte[] _finishBytes(int i3) throws IOException {
        if (i3 <= 0) {
            return i3 == 0 ? ParserMinimalBase.NO_BYTES : _finishChunkedBytes();
        }
        if (i3 > LONGEST_NON_CHUNKED_BINARY) {
            return _finishLongContiguousBytes(i3);
        }
        byte[] bArr = new byte[i3];
        int i4 = 0;
        if (this._inputPtr >= this._inputEnd && !loadMore()) {
            _reportIncompleteBinaryRead(i3, 0);
        }
        int i5 = i3;
        while (true) {
            int min = Math.min(i5, this._inputEnd - this._inputPtr);
            System.arraycopy(this._inputBuffer, this._inputPtr, bArr, i4, min);
            this._inputPtr += min;
            i4 += min;
            i5 -= min;
            if (i5 <= 0) {
                return bArr;
            }
            if (!loadMore()) {
                _reportIncompleteBinaryRead(i3, i4);
            }
        }
    }

    public byte[] _finishChunkedBytes() throws IOException {
        ByteArrayBuilder _getByteArrayBuilder = _getByteArrayBuilder();
        while (true) {
            if (this._inputPtr >= this._inputEnd) {
                loadMoreGuaranteed();
            }
            byte[] bArr = this._inputBuffer;
            int i3 = this._inputPtr;
            this._inputPtr = i3 + 1;
            byte b3 = bArr[i3];
            byte b4 = b3 & 255;
            if (b4 == 255) {
                return _getByteArrayBuilder.toByteArray();
            }
            int i4 = b4 >> 5;
            if (i4 == 2) {
                int _decodeExplicitLength = _decodeExplicitLength(b3 & 31);
                if (_decodeExplicitLength >= 0) {
                    int i5 = _decodeExplicitLength;
                    while (i5 > 0) {
                        int i6 = this._inputEnd;
                        int i7 = this._inputPtr;
                        int i8 = i6 - i7;
                        if (i7 >= i6) {
                            if (!loadMore()) {
                                _reportIncompleteBinaryRead(_decodeExplicitLength, _decodeExplicitLength - i5);
                            }
                            i8 = this._inputEnd - this._inputPtr;
                        }
                        int min = Math.min(i8, i5);
                        _getByteArrayBuilder.write(this._inputBuffer, this._inputPtr, min);
                        this._inputPtr += min;
                        i5 -= min;
                    }
                } else {
                    throw _constructReadException("Illegal chunked-length indicator within chunked-length value (type %d)", (Object) 2);
                }
            } else {
                throw _constructReadException("Mismatched chunk in chunked content: expected %d but encountered %d", 2, Integer.valueOf(i4));
            }
        }
    }

    public byte[] _finishLongContiguousBytes(int i3) throws IOException {
        ByteArrayBuilder byteArrayBuilder = new ByteArrayBuilder(125000);
        int i4 = i3;
        while (i4 > 0) {
            try {
                int i5 = this._inputEnd - this._inputPtr;
                if (i5 <= 0) {
                    if (!loadMore()) {
                        _reportIncompleteBinaryRead(i3, i3 - i4);
                    }
                    i5 = this._inputEnd - this._inputPtr;
                }
                int min = Math.min(i5, i4);
                byteArrayBuilder.write(this._inputBuffer, this._inputPtr, min);
                this._inputPtr += min;
                i4 -= min;
            } catch (Throwable th) {
                try {
                    byteArrayBuilder.close();
                } catch (Throwable th2) {
                    th.addSuppressed(th2);
                }
                throw th;
            }
        }
        byte[] byteArray = byteArrayBuilder.toByteArray();
        byteArrayBuilder.close();
        return byteArray;
    }

    public String _finishTextToken(int i3) throws IOException {
        this._tokenIncomplete = false;
        int i4 = (i3 >> 5) & 7;
        int i5 = i3 & 31;
        if (i4 != 3) {
            _throwInternal();
        }
        int _decodeExplicitLength = _decodeExplicitLength(i5);
        if (_decodeExplicitLength > 0) {
            int max = Math.max(_decodeExplicitLength + 3, _decodeExplicitLength);
            if (this._inputEnd - this._inputPtr >= max || (this._inputBuffer.length >= max && _tryToLoadToHaveAtLeast(max))) {
                return _finishShortText(_decodeExplicitLength);
            }
            _finishLongText(_decodeExplicitLength);
            return this._textBuffer.contentsAsString();
        } else if (_decodeExplicitLength == 0) {
            this._textBuffer.resetWithEmpty();
            return "";
        } else {
            _finishChunkedText();
            return this._textBuffer.contentsAsString();
        }
    }

    public void _finishToken() throws IOException {
        this._tokenIncomplete = false;
        int i3 = this._typeByte;
        int i4 = (i3 >> 5) & 7;
        int i5 = i3 & 31;
        if (i4 != 3) {
            if (i4 == 2) {
                this._binaryValue = _finishBytes(_decodeExplicitLength(i5));
                return;
            }
            _throwInternal();
        }
        int _decodeExplicitLength = _decodeExplicitLength(i5);
        if (_decodeExplicitLength > 0) {
            int i6 = _decodeExplicitLength + 3;
            if (this._inputEnd - this._inputPtr >= i6 || (this._inputBuffer.length >= i6 && _tryToLoadToHaveAtLeast(i6))) {
                _finishShortText(_decodeExplicitLength);
            } else {
                _finishLongText(_decodeExplicitLength);
            }
        } else if (_decodeExplicitLength < 0) {
            _finishChunkedText();
        } else {
            this._textBuffer.resetWithEmpty();
        }
    }

    public ByteArrayBuilder _getByteArrayBuilder() {
        ByteArrayBuilder byteArrayBuilder = this._byteArrayBuilder;
        if (byteArrayBuilder == null) {
            this._byteArrayBuilder = new ByteArrayBuilder();
        } else {
            byteArrayBuilder.reset();
        }
        return this._byteArrayBuilder;
    }

    public void _handleEOF() throws JsonParseException {
        if (!this._streamReadContext.inRoot()) {
            JsonLocation startLocation = this._streamReadContext.startLocation(this._ioContext.contentReference());
            String sourceDescription = startLocation == null ? "[N/A]" : startLocation.sourceDescription();
            if (this._streamReadContext.hasExpectedLength()) {
                int remainingExpectedLength = this._streamReadContext.getRemainingExpectedLength();
                if (this._streamReadContext.inArray()) {
                    _reportInvalidEOF(String.format(" in Array value: expected %d more elements (start token at %s)", new Object[]{Integer.valueOf(remainingExpectedLength), sourceDescription}), (JsonToken) null);
                } else {
                    _reportInvalidEOF(String.format(" in Object value: expected %d more properties (start token at %s)", new Object[]{Integer.valueOf(remainingExpectedLength), sourceDescription}), (JsonToken) null);
                }
            } else if (this._streamReadContext.inArray()) {
                _reportInvalidEOF(" in Array value: expected an element or close marker (0xFF) (start token at " + sourceDescription + ")", (JsonToken) null);
            } else {
                _reportInvalidEOF(" in Object value: expected a property or close marker (0xFF) (start token at " + sourceDescription + ")", (JsonToken) null);
            }
        }
    }

    public JsonToken _handleTaggedArray(int i3, int i4) throws IOException {
        this._streamReadContext = this._streamReadContext.createChildArrayContext(i4);
        if (i3 != 4) {
            JsonToken jsonToken = JsonToken.START_ARRAY;
            this._currToken = jsonToken;
            return jsonToken;
        }
        this._currToken = JsonToken.START_ARRAY;
        if (i4 != 2) {
            _reportError("Unexpected array size (" + i4 + ") for tagged 'bigfloat' value; should have exactly 2 number elements");
        }
        if (!_checkNextIsIntInArray("bigfloat")) {
            _reportError("Unexpected token (" + currentToken() + ") as the first part of 'bigfloat' value: should get VALUE_NUMBER_INT");
        }
        int i5 = -getIntValue();
        if (!_checkNextIsIntInArray("bigfloat")) {
            _reportError("Unexpected token (" + currentToken() + ") as the second part of 'bigfloat' value: should get VALUE_NUMBER_INT");
        }
        BigDecimal bigDecimal = getNumberType() == JsonParser.NumberType.BIG_INTEGER ? new BigDecimal(getBigIntegerValue(), i5) : BigDecimal.valueOf(getLongValue(), i5);
        if (!_checkNextIsEndArray()) {
            _reportError("Unexpected token (" + currentToken() + ") after 2 elements of 'bigfloat' value");
        }
        this._numberBigDecimal = bigDecimal;
        this._numTypesValid = 16;
        JsonToken jsonToken2 = JsonToken.VALUE_NUMBER_FLOAT;
        this._currToken = jsonToken2;
        return jsonToken2;
    }

    public JsonToken _handleTaggedBinary(int i3) throws IOException {
        boolean z2;
        if (i3 == 2) {
            z2 = false;
        } else if (i3 == 3) {
            z2 = true;
        } else {
            JsonToken jsonToken = JsonToken.VALUE_EMBEDDED_OBJECT;
            this._currToken = jsonToken;
            return jsonToken;
        }
        _finishToken();
        if (this._binaryValue.length == 0) {
            this._numberBigInt = BigInteger.ZERO;
        } else {
            BigInteger bigInteger = new BigInteger(this._binaryValue);
            if (z2) {
                bigInteger = bigInteger.negate();
            }
            this._numberBigInt = bigInteger;
        }
        this._numTypesValid = 4;
        this._tagValue = -1;
        JsonToken jsonToken2 = JsonToken.VALUE_NUMBER_INT;
        this._currToken = jsonToken2;
        return jsonToken2;
    }

    public void _invalidToken(int i3) throws JsonParseException {
        int i4 = i3 & 255;
        if (i4 == 255) {
            throw _constructError("Mismatched BREAK byte (0xFF): encountered where value expected");
        }
        throw _constructError("Invalid CBOR value token (first byte): 0x" + Integer.toHexString(i4));
    }

    public final void _loadToHaveAtLeast(int i3) throws IOException {
        if (this._inputStream != null) {
            int i4 = this._inputEnd;
            int i5 = this._inputPtr;
            int i6 = i4 - i5;
            if (i6 <= 0 || i5 <= 0) {
                this._inputEnd = 0;
            } else {
                byte[] bArr = this._inputBuffer;
                System.arraycopy(bArr, i5, bArr, 0, i6);
                this._inputEnd = i6;
            }
            this._currInputProcessed += (long) this._inputPtr;
            this._inputPtr = 0;
            while (true) {
                int i7 = this._inputEnd;
                if (i7 < i3) {
                    InputStream inputStream = this._inputStream;
                    byte[] bArr2 = this._inputBuffer;
                    int read = inputStream.read(bArr2, i7, bArr2.length - i7);
                    if (read < 1) {
                        _closeInput();
                        if (read == 0) {
                            throw new IOException(C0118y.c(i6, "InputStream.read() returned 0 characters when trying to read ", " bytes"));
                        }
                        throw _constructError(i.a(i3, i3, "Needed to read ", " bytes, missed ", " before end-of-input"));
                    }
                    this._inputEnd += read;
                } else {
                    return;
                }
            }
        } else {
            throw _constructError("Needed to read " + i3 + " bytes, reached end-of-input");
        }
    }

    public String _numberToName(int i3, boolean z2) throws IOException {
        int i4 = i3 & 31;
        if (i4 > 23) {
            switch (i4) {
                case 24:
                    i4 = _decode8Bits();
                    break;
                case 25:
                    i4 = _decode16Bits();
                    break;
                case 26:
                    i4 = _decode32Bits();
                    if (i4 < 0) {
                        return String.valueOf(z2 ? (-(4294967295L & ((long) i4))) - 1 : 4294967295L & ((long) i4));
                    }
                    break;
                case 27:
                    long _decode64Bits = _decode64Bits();
                    if (z2) {
                        _decode64Bits = (-_decode64Bits) - 1;
                    }
                    return String.valueOf(_decode64Bits);
                default:
                    throw _constructReadException("Invalid length indicator for ints (%d), token 0x%s", Integer.valueOf(i4), Integer.toHexString(i3));
            }
        }
        if (z2) {
            i4 = (-i4) - 1;
        }
        return String.valueOf(i4);
    }

    public void _releaseBuffers() throws IOException {
        byte[] bArr;
        if (this._bufferRecyclable && (bArr = this._inputBuffer) != null) {
            this._inputBuffer = null;
            this._ioContext.releaseReadIOBuffer(bArr);
        }
        this._textBuffer.releaseBuffers();
        char[] cArr = this._nameCopyBuffer;
        if (cArr != null) {
            this._nameCopyBuffer = null;
            this._ioContext.releaseNameCopyBuffer(cArr);
        }
    }

    public void _reportIncompleteBinaryRead(int i3, int i4) throws IOException {
        _reportInvalidEOF(String.format(" for Binary value: expected %d bytes, only found %d", new Object[]{Integer.valueOf(i3), Integer.valueOf(i4)}), this._currToken);
    }

    public void _reportInvalidChar(int i3) throws JsonParseException {
        if (i3 < 32) {
            _throwInvalidSpace(i3);
        }
        _reportInvalidInitial(i3);
    }

    public void _reportInvalidInitial(int i3) throws JsonParseException {
        _reportError("Invalid UTF-8 start byte 0x" + Integer.toHexString(i3));
    }

    public void _reportInvalidOther(int i3) throws JsonParseException {
        _reportError("Invalid UTF-8 middle byte 0x" + Integer.toHexString(i3));
    }

    public void _reportUnexpectedBreak() throws IOException {
        if (!this._streamReadContext.inRoot()) {
            StringBuilder sb = new StringBuilder("Unexpected Break (0xFF) token in definite length (");
            sb.append(this._streamReadContext.getExpectedLength());
            sb.append(") ");
            sb.append(this._streamReadContext.inObject() ? "Object" : "Array");
            throw _constructError(sb.toString());
        }
        throw _constructError("Unexpected Break (0xFF) token in Root context");
    }

    public void _skipBytes(int i3) throws IOException {
        while (true) {
            int min = Math.min(i3, this._inputEnd - this._inputPtr);
            this._inputPtr += min;
            i3 -= min;
            if (i3 > 0) {
                loadMoreGuaranteed();
            } else {
                return;
            }
        }
    }

    public void _skipBytesL(long j2) throws IOException {
        while (j2 > SieveCacheKt.NodeLinkMask) {
            _skipBytes(Integer.MAX_VALUE);
            j2 -= SieveCacheKt.NodeLinkMask;
        }
        _skipBytes((int) j2);
    }

    public void _skipChunked(int i3) throws IOException {
        while (true) {
            if (this._inputPtr >= this._inputEnd) {
                loadMoreGuaranteed();
            }
            byte[] bArr = this._inputBuffer;
            int i4 = this._inputPtr;
            this._inputPtr = i4 + 1;
            byte b3 = bArr[i4];
            byte b4 = b3 & 255;
            if (b4 != 255) {
                int i5 = b4 >> 5;
                if (i5 == i3) {
                    byte b5 = b3 & 31;
                    if (b5 <= 23) {
                        if (b5 > 0) {
                            _skipBytes(b5);
                        }
                    } else if (b5 != 31) {
                        switch (b5) {
                            case 24:
                                _skipBytes(_decode8Bits());
                                break;
                            case 25:
                                _skipBytes(_decode16Bits());
                                break;
                            case 26:
                                _skipBytes(_decode32Bits());
                                break;
                            case 27:
                                _skipBytesL(_decode64Bits());
                                break;
                            default:
                                _invalidToken(this._typeByte);
                                break;
                        }
                    } else {
                        throw _constructReadException("Illegal chunked-length indicator within chunked-length value (type %d)", (Object) Integer.valueOf(i3));
                    }
                } else {
                    throw _constructError("Mismatched chunk in chunked content: expected " + i3 + " but encountered " + i5);
                }
            } else {
                return;
            }
        }
    }

    public void _skipIncomplete() throws IOException {
        this._tokenIncomplete = false;
        int i3 = (this._typeByte >> 5) & 7;
        if (!(i3 == 3 || i3 == 2)) {
            _throwInternal();
        }
        int i4 = this._typeByte;
        int i5 = i4 & 31;
        if (i5 <= 23) {
            if (i5 > 0) {
                _skipBytes(i5);
            }
        } else if (i5 != 31) {
            switch (i5) {
                case 24:
                    _skipBytes(_decode8Bits());
                    return;
                case 25:
                    _skipBytes(_decode16Bits());
                    return;
                case 26:
                    _skipBytes(_decode32Bits());
                    return;
                case 27:
                    _skipBytesL(_decode64Bits());
                    return;
                default:
                    _invalidToken(i4);
                    return;
            }
        } else {
            _skipChunked(i3);
        }
    }

    public final boolean _tryToLoadToHaveAtLeast(int i3) throws IOException {
        if (this._inputStream == null) {
            return false;
        }
        int i4 = this._inputEnd;
        int i5 = this._inputPtr;
        int i6 = i4 - i5;
        if (i6 <= 0 || i5 <= 0) {
            this._inputEnd = 0;
        } else {
            byte[] bArr = this._inputBuffer;
            System.arraycopy(bArr, i5, bArr, 0, i6);
            this._inputEnd = i6;
        }
        this._currInputProcessed += (long) this._inputPtr;
        this._inputPtr = 0;
        while (true) {
            int i7 = this._inputEnd;
            if (i7 >= i3) {
                return true;
            }
            InputStream inputStream = this._inputStream;
            byte[] bArr2 = this._inputBuffer;
            int read = inputStream.read(bArr2, i7, bArr2.length - i7);
            if (read < 1) {
                _closeInput();
                return false;
            }
            this._inputEnd += read;
        }
    }

    public void close() throws IOException {
        if (!this._closed) {
            this._closed = true;
            this._symbols.release();
            try {
                _closeInput();
            } finally {
                _releaseBuffers();
            }
        }
    }

    public void convertNumberToBigDecimal() throws IOException {
        int i3 = this._numTypesValid;
        if ((i3 & 40) != 0) {
            this._numberBigDecimal = NumberInput.parseBigDecimal(getText());
        } else if ((i3 & 4) != 0) {
            this._numberBigDecimal = new BigDecimal(this._numberBigInt);
        } else if ((i3 & 2) != 0) {
            this._numberBigDecimal = BigDecimal.valueOf(this._numberLong);
        } else if ((i3 & 1) != 0) {
            this._numberBigDecimal = BigDecimal.valueOf((long) this._numberInt);
        } else {
            _throwInternal();
        }
        this._numTypesValid |= 16;
    }

    public void convertNumberToBigInteger() throws IOException {
        int i3 = this._numTypesValid;
        if ((i3 & 16) != 0) {
            this._numberBigInt = this._numberBigDecimal.toBigInteger();
        } else if ((i3 & 2) != 0) {
            this._numberBigInt = BigInteger.valueOf(this._numberLong);
        } else if ((i3 & 1) != 0) {
            this._numberBigInt = BigInteger.valueOf((long) this._numberInt);
        } else if ((i3 & 8) != 0) {
            this._numberBigInt = BigDecimal.valueOf(this._numberDouble).toBigInteger();
        } else if ((i3 & 32) != 0) {
            this._numberBigInt = BigDecimal.valueOf((double) this._numberFloat).toBigInteger();
        } else {
            _throwInternal();
        }
        this._numTypesValid |= 4;
    }

    public void convertNumberToDouble() throws IOException {
        int i3 = this._numTypesValid;
        if ((i3 & 16) != 0) {
            this._numberDouble = this._numberBigDecimal.doubleValue();
        } else if ((i3 & 32) != 0) {
            this._numberDouble = (double) this._numberFloat;
        } else if ((i3 & 4) != 0) {
            this._numberDouble = this._numberBigInt.doubleValue();
        } else if ((i3 & 2) != 0) {
            this._numberDouble = (double) this._numberLong;
        } else if ((i3 & 1) != 0) {
            this._numberDouble = (double) this._numberInt;
        } else {
            _throwInternal();
        }
        this._numTypesValid |= 8;
    }

    public void convertNumberToFloat() throws IOException {
        int i3 = this._numTypesValid;
        if ((i3 & 16) != 0) {
            this._numberFloat = this._numberBigDecimal.floatValue();
        } else if ((i3 & 4) != 0) {
            this._numberFloat = this._numberBigInt.floatValue();
        } else if ((i3 & 8) != 0) {
            this._numberFloat = (float) this._numberDouble;
        } else if ((i3 & 2) != 0) {
            this._numberFloat = (float) this._numberLong;
        } else if ((i3 & 1) != 0) {
            this._numberFloat = (float) this._numberInt;
        } else {
            _throwInternal();
        }
        this._numTypesValid |= 32;
    }

    public void convertNumberToInt() throws IOException {
        int i3 = this._numTypesValid;
        if ((i3 & 2) != 0) {
            long j2 = this._numberLong;
            int i4 = (int) j2;
            if (((long) i4) != j2) {
                _reportError("Numeric value (" + getText() + ") out of range of int");
            }
            this._numberInt = i4;
        } else if ((i3 & 4) != 0) {
            if (BI_MIN_INT.compareTo(this._numberBigInt) > 0 || BI_MAX_INT.compareTo(this._numberBigInt) < 0) {
                reportOverflowInt();
            }
            this._numberInt = this._numberBigInt.intValue();
        } else if ((i3 & 8) != 0) {
            double d2 = this._numberDouble;
            if (d2 < -2.147483648E9d || d2 > 2.147483647E9d) {
                reportOverflowInt();
            }
            this._numberInt = (int) this._numberDouble;
        } else if ((i3 & 32) != 0) {
            float f2 = this._numberFloat;
            if (((double) f2) < -2.147483648E9d || ((double) f2) > 2.147483647E9d) {
                reportOverflowInt();
            }
            this._numberInt = (int) this._numberFloat;
        } else if ((i3 & 16) != 0) {
            if (BD_MIN_INT.compareTo(this._numberBigDecimal) > 0 || BD_MAX_INT.compareTo(this._numberBigDecimal) < 0) {
                reportOverflowInt();
            }
            this._numberInt = this._numberBigDecimal.intValue();
        } else {
            _throwInternal();
        }
        this._numTypesValid |= 1;
    }

    public void convertNumberToLong() throws IOException {
        int i3 = this._numTypesValid;
        if ((i3 & 1) != 0) {
            this._numberLong = (long) this._numberInt;
        } else if ((i3 & 4) != 0) {
            if (BI_MIN_LONG.compareTo(this._numberBigInt) > 0 || BI_MAX_LONG.compareTo(this._numberBigInt) < 0) {
                reportOverflowLong();
            }
            this._numberLong = this._numberBigInt.longValue();
        } else if ((i3 & 8) != 0) {
            double d2 = this._numberDouble;
            if (d2 < -9.223372036854776E18d || d2 > 9.223372036854776E18d) {
                reportOverflowLong();
            }
            this._numberLong = (long) this._numberDouble;
        } else if ((i3 & 32) != 0) {
            float f2 = this._numberFloat;
            if (((double) f2) < -9.223372036854776E18d || ((double) f2) > 9.223372036854776E18d) {
                reportOverflowInt();
            }
            this._numberLong = (long) this._numberFloat;
        } else if ((i3 & 16) != 0) {
            if (BD_MIN_LONG.compareTo(this._numberBigDecimal) > 0 || BD_MAX_LONG.compareTo(this._numberBigDecimal) < 0) {
                reportOverflowLong();
            }
            this._numberLong = this._numberBigDecimal.longValue();
        } else {
            _throwInternal();
        }
        this._numTypesValid |= 2;
    }

    public void finishToken() throws IOException {
        if (this._tokenIncomplete) {
            _finishToken();
        }
    }

    public BigInteger getBigIntegerValue() throws IOException {
        int i3 = this._numTypesValid;
        if ((i3 & 4) == 0) {
            if (i3 == 0) {
                _checkNumericValue(4);
            }
            if ((this._numTypesValid & 4) == 0) {
                convertNumberToBigInteger();
            }
        }
        return this._numberBigInt;
    }

    public byte[] getBinaryValue(Base64Variant base64Variant) throws IOException {
        JsonToken jsonToken = this._currToken;
        if (jsonToken == JsonToken.VALUE_EMBEDDED_OBJECT) {
            if (this._tokenIncomplete) {
                _finishToken();
            }
            return this._binaryValue;
        } else if (jsonToken == JsonToken.VALUE_STRING) {
            return _getBinaryFromString(base64Variant);
        } else {
            throw _constructReadException("Current token (%s) not VALUE_EMBEDDED_OBJECT or VALUE_STRING, can not access as binary", (Object) currentToken());
        }
    }

    public ObjectCodec getCodec() {
        return this._objectCodec;
    }

    public JsonLocation getCurrentLocation() {
        long j2 = this._currInputProcessed + ((long) this._inputPtr);
        return new JsonLocation(this._ioContext.contentReference(), j2, -1, -1, (int) j2);
    }

    public String getCurrentName() throws IOException {
        JsonToken jsonToken = this._currToken;
        return (jsonToken == JsonToken.START_OBJECT || jsonToken == JsonToken.START_ARRAY) ? this._streamReadContext.getParent().getCurrentName() : this._streamReadContext.getCurrentName();
    }

    public int getCurrentTag() {
        return this._tagValue;
    }

    public BigDecimal getDecimalValue() throws IOException {
        int i3 = this._numTypesValid;
        if ((i3 & 16) == 0) {
            if (i3 == 0) {
                _checkNumericValue(16);
            }
            if ((this._numTypesValid & 16) == 0) {
                convertNumberToBigDecimal();
            }
        }
        return this._numberBigDecimal;
    }

    public double getDoubleValue() throws IOException {
        int i3 = this._numTypesValid;
        if ((i3 & 8) == 0) {
            if (i3 == 0) {
                _checkNumericValue(8);
            }
            if ((this._numTypesValid & 8) == 0) {
                convertNumberToDouble();
            }
        }
        return this._numberDouble;
    }

    public Object getEmbeddedObject() throws IOException {
        if (this._tokenIncomplete) {
            _finishToken();
        }
        if (this._currToken == JsonToken.VALUE_EMBEDDED_OBJECT) {
            return this._binaryValue;
        }
        return null;
    }

    public float getFloatValue() throws IOException {
        int i3 = this._numTypesValid;
        if ((i3 & 32) == 0) {
            if (i3 == 0) {
                _checkNumericValue(32);
            }
            if ((this._numTypesValid & 32) == 0) {
                convertNumberToFloat();
            }
        }
        return this._numberFloat;
    }

    public int getFormatFeatures() {
        return 0;
    }

    public Object getInputSource() {
        return this._inputStream;
    }

    public int getIntValue() throws IOException {
        int i3 = this._numTypesValid;
        if ((i3 & 1) == 0) {
            if (i3 == 0) {
                _checkNumericValue(1);
            }
            if ((this._numTypesValid & 1) == 0) {
                convertNumberToInt();
            }
        }
        return this._numberInt;
    }

    public long getLongValue() throws IOException {
        int i3 = this._numTypesValid;
        if ((i3 & 2) == 0) {
            if (i3 == 0) {
                _checkNumericValue(2);
            }
            if ((this._numTypesValid & 2) == 0) {
                convertNumberToLong();
            }
        }
        return this._numberLong;
    }

    public JsonParser.NumberType getNumberType() throws IOException {
        if (this._numTypesValid == 0) {
            _checkNumericValue(0);
        }
        if (this._currToken == JsonToken.VALUE_NUMBER_INT) {
            int i3 = this._numTypesValid;
            return (i3 & 1) != 0 ? JsonParser.NumberType.INT : (i3 & 2) != 0 ? JsonParser.NumberType.LONG : JsonParser.NumberType.BIG_INTEGER;
        }
        int i4 = this._numTypesValid;
        return (i4 & 16) != 0 ? JsonParser.NumberType.BIG_DECIMAL : (i4 & 8) != 0 ? JsonParser.NumberType.DOUBLE : JsonParser.NumberType.FLOAT;
    }

    public Number getNumberValue() throws IOException {
        if (this._numTypesValid == 0) {
            _checkNumericValue(0);
        }
        if (this._currToken == JsonToken.VALUE_NUMBER_INT) {
            int i3 = this._numTypesValid;
            return (i3 & 1) != 0 ? Integer.valueOf(this._numberInt) : (i3 & 2) != 0 ? Long.valueOf(this._numberLong) : (i3 & 4) != 0 ? this._numberBigInt : this._numberBigDecimal;
        }
        int i4 = this._numTypesValid;
        if ((i4 & 16) != 0) {
            return this._numberBigDecimal;
        }
        if ((i4 & 8) != 0) {
            return Double.valueOf(this._numberDouble);
        }
        if ((i4 & 32) == 0) {
            _throwInternal();
        }
        return Float.valueOf(this._numberFloat);
    }

    public final Number getNumberValueExact() throws IOException {
        return getNumberValue();
    }

    public JacksonFeatureSet<StreamReadCapability> getReadCapabilities() {
        return CBOR_READ_CAPABILITIES;
    }

    public String getText() throws IOException {
        JsonToken jsonToken = this._currToken;
        if (this._tokenIncomplete && jsonToken == JsonToken.VALUE_STRING) {
            return _finishTextToken(this._typeByte);
        }
        if (jsonToken == JsonToken.VALUE_STRING) {
            return this._textBuffer.contentsAsString();
        }
        if (jsonToken == null) {
            return null;
        }
        if (jsonToken == JsonToken.FIELD_NAME) {
            return this._streamReadContext.getCurrentName();
        }
        if (jsonToken.isNumeric()) {
            return getNumberValue().toString();
        }
        return this._currToken.asString();
    }

    public char[] getTextCharacters() throws IOException {
        if (this._currToken == null) {
            return null;
        }
        if (this._tokenIncomplete) {
            _finishToken();
        }
        JsonToken jsonToken = this._currToken;
        return jsonToken == JsonToken.VALUE_STRING ? this._textBuffer.getTextBuffer() : jsonToken == JsonToken.FIELD_NAME ? this._streamReadContext.getCurrentName().toCharArray() : (jsonToken == JsonToken.VALUE_NUMBER_INT || jsonToken == JsonToken.VALUE_NUMBER_FLOAT) ? getNumberValue().toString().toCharArray() : jsonToken.asCharArray();
    }

    public int getTextLength() throws IOException {
        if (this._currToken == null) {
            return 0;
        }
        if (this._tokenIncomplete) {
            _finishToken();
        }
        JsonToken jsonToken = this._currToken;
        return jsonToken == JsonToken.VALUE_STRING ? this._textBuffer.size() : jsonToken == JsonToken.FIELD_NAME ? this._streamReadContext.getCurrentName().length() : (jsonToken == JsonToken.VALUE_NUMBER_INT || jsonToken == JsonToken.VALUE_NUMBER_FLOAT) ? getNumberValue().toString().length() : jsonToken.asCharArray().length;
    }

    public int getTextOffset() throws IOException {
        return 0;
    }

    public JsonLocation getTokenLocation() {
        ContentReference contentReference = this._ioContext.contentReference();
        long j2 = this._tokenInputTotal;
        return new JsonLocation(contentReference, j2, -1, -1, (int) j2);
    }

    public String getValueAsString() throws IOException {
        if (this._tokenIncomplete && this._currToken == JsonToken.VALUE_STRING) {
            return _finishTextToken(this._typeByte);
        }
        JsonToken jsonToken = this._currToken;
        if (jsonToken == JsonToken.VALUE_STRING) {
            return this._textBuffer.contentsAsString();
        }
        if (jsonToken == null || jsonToken == JsonToken.VALUE_NULL || !jsonToken.isScalarValue()) {
            return null;
        }
        return getText();
    }

    public boolean hasTextCharacters() {
        JsonToken jsonToken = this._currToken;
        if (jsonToken == JsonToken.VALUE_STRING) {
            return this._textBuffer.hasTextAsCharacters();
        }
        if (jsonToken == JsonToken.FIELD_NAME) {
            return this._nameCopied;
        }
        return false;
    }

    public boolean isClosed() {
        return this._closed;
    }

    public boolean isNaN() {
        if (this._currToken != JsonToken.VALUE_NUMBER_FLOAT) {
            return false;
        }
        int i3 = this._numTypesValid;
        if ((i3 & 8) != 0) {
            double d2 = this._numberDouble;
            return Double.isNaN(d2) || Double.isInfinite(d2);
        } else if ((i3 & 32) == 0) {
            return false;
        } else {
            float f2 = this._numberFloat;
            return Float.isNaN(f2) || Float.isInfinite(f2);
        }
    }

    public boolean loadMore() throws IOException {
        InputStream inputStream = this._inputStream;
        if (inputStream != null) {
            this._currInputProcessed += (long) this._inputEnd;
            byte[] bArr = this._inputBuffer;
            int read = inputStream.read(bArr, 0, bArr.length);
            if (read > 0) {
                this._inputPtr = 0;
                this._inputEnd = read;
                return true;
            }
            _closeInput();
            if (read == 0) {
                throw new IOException(a.m(new StringBuilder("InputStream.read() returned 0 characters when trying to read "), " bytes", this._inputBuffer.length));
            }
        }
        return false;
    }

    public void loadMoreGuaranteed() throws IOException {
        if (!loadMore()) {
            _reportInvalidEOF();
        }
    }

    public Boolean nextBooleanValue() throws IOException {
        JsonToken nextToken = nextToken();
        if (nextToken == JsonToken.VALUE_TRUE) {
            return Boolean.TRUE;
        }
        if (nextToken == JsonToken.VALUE_FALSE) {
            return Boolean.FALSE;
        }
        return null;
    }

    public boolean nextFieldName(SerializableString serializableString) throws IOException {
        byte b3;
        if (this._streamReadContext.inObject() && this._currToken != JsonToken.FIELD_NAME) {
            this._numTypesValid = 0;
            if (this._tokenIncomplete) {
                _skipIncomplete();
            }
            this._tokenInputTotal = this._currInputProcessed + ((long) this._inputPtr);
            this._binaryValue = null;
            this._tagValue = -1;
            if (!this._streamReadContext.expectMoreValues()) {
                this._streamReadContext = this._streamReadContext.getParent();
                this._currToken = JsonToken.END_OBJECT;
                return false;
            }
            byte[] asQuotedUTF8 = serializableString.asQuotedUTF8();
            int length = asQuotedUTF8.length;
            int i3 = this._inputPtr;
            if (i3 + length + 1 < this._inputEnd) {
                byte[] bArr = this._inputBuffer;
                int i4 = i3 + 1;
                byte b4 = bArr[i3];
                if (((b4 >> 5) & 7) == 3 && (b3 = b4 & 31) <= 24) {
                    if (b3 == 23) {
                        b3 = bArr[i4] & 255;
                        i4 = i3 + 2;
                    }
                    if (b3 == length) {
                        int i5 = 0;
                        while (i5 != b3) {
                            if (asQuotedUTF8[i5] == this._inputBuffer[i4 + i5]) {
                                i5++;
                            }
                        }
                        this._inputPtr = i4 + i5;
                        this._streamReadContext.setCurrentName(serializableString.getValue());
                        this._currToken = JsonToken.FIELD_NAME;
                        return true;
                    }
                }
            }
        }
        if (nextToken() != JsonToken.FIELD_NAME || !serializableString.getValue().equals(getCurrentName())) {
            return false;
        }
        return true;
    }

    public int nextIntValue(int i3) throws IOException {
        return nextToken() == JsonToken.VALUE_NUMBER_INT ? getIntValue() : i3;
    }

    public long nextLongValue(long j2) throws IOException {
        return nextToken() == JsonToken.VALUE_NUMBER_INT ? getLongValue() : j2;
    }

    public String nextTextValue() throws IOException {
        this._numTypesValid = 0;
        if (this._tokenIncomplete) {
            _skipIncomplete();
        }
        this._tokenInputTotal = this._currInputProcessed + ((long) this._inputPtr);
        this._binaryValue = null;
        this._tagValue = -1;
        if (this._streamReadContext.inObject()) {
            if (this._currToken != JsonToken.FIELD_NAME) {
                this._tagValue = -1;
                if (!this._streamReadContext.expectMoreValues()) {
                    this._streamReadContext = this._streamReadContext.getParent();
                    this._currToken = JsonToken.END_OBJECT;
                    return null;
                }
                this._currToken = _decodePropertyName();
                return null;
            }
        } else if (!this._streamReadContext.expectMoreValues()) {
            this._tagValue = -1;
            this._streamReadContext = this._streamReadContext.getParent();
            this._currToken = JsonToken.END_ARRAY;
            return null;
        }
        if (this._inputPtr < this._inputEnd || loadMore()) {
            byte[] bArr = this._inputBuffer;
            int i3 = this._inputPtr;
            this._inputPtr = i3 + 1;
            byte b3 = bArr[i3];
            byte b4 = b3 & 255;
            int i4 = b4 >> 5;
            byte b5 = b3 & 31;
            if (i4 == 6) {
                this._tagValue = _decodeTag(b5);
                if (this._inputPtr < this._inputEnd || loadMore()) {
                    byte[] bArr2 = this._inputBuffer;
                    int i5 = this._inputPtr;
                    this._inputPtr = i5 + 1;
                    byte b6 = bArr2[i5];
                    b4 = b6 & 255;
                    i4 = b4 >> 5;
                    b5 = b6 & 31;
                } else {
                    _eofAsNextToken();
                    return null;
                }
            } else {
                this._tagValue = -1;
            }
            switch (i4) {
                case 0:
                    this._numTypesValid = 1;
                    if (b5 <= 23) {
                        this._numberInt = b5;
                    } else {
                        int i6 = b5 - 24;
                        if (i6 == 0) {
                            this._numberInt = _decode8Bits();
                        } else if (i6 == 1) {
                            this._numberInt = _decode16Bits();
                        } else if (i6 == 2) {
                            int _decode32Bits = _decode32Bits();
                            if (_decode32Bits < 0) {
                                this._numberLong = ((long) _decode32Bits) & 4294967295L;
                                this._numTypesValid = 2;
                            } else {
                                this._numberInt = _decode32Bits;
                            }
                        } else if (i6 != 3) {
                            _invalidToken(b4);
                        } else {
                            long _decode64Bits = _decode64Bits();
                            if (_decode64Bits >= 0) {
                                this._numberLong = _decode64Bits;
                                this._numTypesValid = 2;
                            } else {
                                this._numberBigInt = _bigPositive(_decode64Bits);
                                this._numTypesValid = 4;
                            }
                        }
                    }
                    this._currToken = JsonToken.VALUE_NUMBER_INT;
                    return null;
                case 1:
                    this._numTypesValid = 1;
                    if (b5 <= 23) {
                        this._numberInt = (-b5) - 1;
                    } else {
                        int i7 = b5 - 24;
                        if (i7 == 0) {
                            this._numberInt = (-_decode8Bits()) - 1;
                        } else if (i7 == 1) {
                            this._numberInt = (-_decode16Bits()) - 1;
                        } else if (i7 == 2) {
                            int _decode32Bits2 = _decode32Bits();
                            if (_decode32Bits2 < 0) {
                                this._numberLong = (-(((long) _decode32Bits2) & 4294967295L)) - 1;
                                this._numTypesValid = 2;
                            } else {
                                this._numberInt = (-_decode32Bits2) - 1;
                            }
                        } else if (i7 != 3) {
                            _invalidToken(b4);
                        } else {
                            long _decode64Bits2 = _decode64Bits();
                            if (_decode64Bits2 >= 0) {
                                this._numberLong = _decode64Bits2;
                                this._numTypesValid = 2;
                            } else {
                                this._numberBigInt = _bigNegative(_decode64Bits2);
                                this._numTypesValid = 4;
                            }
                        }
                    }
                    this._currToken = JsonToken.VALUE_NUMBER_INT;
                    return null;
                case 2:
                    this._typeByte = b4;
                    this._tokenIncomplete = true;
                    this._currToken = JsonToken.VALUE_EMBEDDED_OBJECT;
                    return null;
                case 3:
                    this._typeByte = b4;
                    this._tokenIncomplete = true;
                    this._currToken = JsonToken.VALUE_STRING;
                    return _finishTextToken(b4);
                case 4:
                    this._currToken = JsonToken.START_ARRAY;
                    this._streamReadContext = this._streamReadContext.createChildArrayContext(_decodeExplicitLength(b5));
                    return null;
                case 5:
                    this._currToken = JsonToken.START_OBJECT;
                    this._streamReadContext = this._streamReadContext.createChildObjectContext(_decodeExplicitLength(b5));
                    return null;
                case 6:
                    _reportError("Multiple tags not allowed per value (first tag: " + this._tagValue + ")");
                    break;
            }
            switch (b5) {
                case 20:
                    this._currToken = JsonToken.VALUE_FALSE;
                    return null;
                case 21:
                    this._currToken = JsonToken.VALUE_TRUE;
                    return null;
                case 22:
                    this._currToken = JsonToken.VALUE_NULL;
                    return null;
                case 23:
                    this._currToken = _decodeUndefinedValue();
                    return null;
                case 25:
                    this._numberFloat = _decodeHalfSizeFloat();
                    this._numTypesValid = 32;
                    this._currToken = JsonToken.VALUE_NUMBER_FLOAT;
                    return null;
                case 26:
                    this._numberFloat = Float.intBitsToFloat(_decode32Bits());
                    this._numTypesValid = 32;
                    this._currToken = JsonToken.VALUE_NUMBER_FLOAT;
                    return null;
                case 27:
                    this._numberDouble = Double.longBitsToDouble(_decode64Bits());
                    this._numTypesValid = 8;
                    this._currToken = JsonToken.VALUE_NUMBER_FLOAT;
                    return null;
                case 31:
                    if (!this._streamReadContext.inArray() || this._streamReadContext.hasExpectedLength()) {
                        _reportUnexpectedBreak();
                        break;
                    } else {
                        this._streamReadContext = this._streamReadContext.getParent();
                        this._currToken = JsonToken.END_ARRAY;
                        return null;
                    }
            }
            this._currToken = _decodeSimpleValue(b5, b4);
            return null;
        }
        _eofAsNextToken();
        return null;
    }

    public JsonToken nextToken() throws IOException {
        this._numTypesValid = 0;
        if (this._tokenIncomplete) {
            _skipIncomplete();
        }
        this._tokenInputTotal = this._currInputProcessed + ((long) this._inputPtr);
        this._binaryValue = null;
        if (this._streamReadContext.inObject()) {
            if (this._currToken != JsonToken.FIELD_NAME) {
                this._tagValue = -1;
                if (!this._streamReadContext.expectMoreValues()) {
                    this._streamReadContext = this._streamReadContext.getParent();
                    JsonToken jsonToken = JsonToken.END_OBJECT;
                    this._currToken = jsonToken;
                    return jsonToken;
                }
                JsonToken _decodePropertyName = _decodePropertyName();
                this._currToken = _decodePropertyName;
                return _decodePropertyName;
            }
        } else if (!this._streamReadContext.expectMoreValues()) {
            this._tagValue = -1;
            this._streamReadContext = this._streamReadContext.getParent();
            JsonToken jsonToken2 = JsonToken.END_ARRAY;
            this._currToken = jsonToken2;
            return jsonToken2;
        }
        if (this._inputPtr >= this._inputEnd && !loadMore()) {
            return _eofAsNextToken();
        }
        byte[] bArr = this._inputBuffer;
        int i3 = this._inputPtr;
        this._inputPtr = i3 + 1;
        byte b3 = bArr[i3];
        byte b4 = b3 & 255;
        int i4 = b4 >> 5;
        byte b5 = b3 & 31;
        if (i4 == 6) {
            this._tagValue = _decodeTag(b5);
            if (this._inputPtr >= this._inputEnd && !loadMore()) {
                return _eofAsNextToken();
            }
            byte[] bArr2 = this._inputBuffer;
            int i5 = this._inputPtr;
            this._inputPtr = i5 + 1;
            byte b6 = bArr2[i5];
            b4 = b6 & 255;
            i4 = b4 >> 5;
            b5 = b6 & 31;
        } else {
            this._tagValue = -1;
        }
        switch (i4) {
            case 0:
                this._numTypesValid = 1;
                if (b5 <= 23) {
                    this._numberInt = b5;
                } else {
                    int i6 = b5 - 24;
                    if (i6 == 0) {
                        this._numberInt = _decode8Bits();
                    } else if (i6 == 1) {
                        this._numberInt = _decode16Bits();
                    } else if (i6 == 2) {
                        int _decode32Bits = _decode32Bits();
                        if (_decode32Bits >= 0) {
                            this._numberInt = _decode32Bits;
                        } else {
                            this._numberLong = ((long) _decode32Bits) & 4294967295L;
                            this._numTypesValid = 2;
                        }
                    } else if (i6 != 3) {
                        _invalidToken(b4);
                    } else {
                        long _decode64Bits = _decode64Bits();
                        if (_decode64Bits >= 0) {
                            this._numberLong = _decode64Bits;
                            this._numTypesValid = 2;
                        } else {
                            this._numberBigInt = _bigPositive(_decode64Bits);
                            this._numTypesValid = 4;
                        }
                    }
                }
                JsonToken jsonToken3 = JsonToken.VALUE_NUMBER_INT;
                this._currToken = jsonToken3;
                return jsonToken3;
            case 1:
                this._numTypesValid = 1;
                if (b5 <= 23) {
                    this._numberInt = (-b5) - 1;
                } else {
                    int i7 = b5 - 24;
                    if (i7 == 0) {
                        this._numberInt = (-_decode8Bits()) - 1;
                    } else if (i7 == 1) {
                        this._numberInt = (-_decode16Bits()) - 1;
                    } else if (i7 == 2) {
                        int _decode32Bits2 = _decode32Bits();
                        if (_decode32Bits2 < 0) {
                            this._numberLong = (-(((long) _decode32Bits2) & 4294967295L)) - 1;
                            this._numTypesValid = 2;
                        } else {
                            this._numberInt = (-_decode32Bits2) - 1;
                        }
                    } else if (i7 != 3) {
                        _invalidToken(b4);
                    } else {
                        long _decode64Bits2 = _decode64Bits();
                        if (_decode64Bits2 >= 0) {
                            this._numberLong = (-_decode64Bits2) - 1;
                            this._numTypesValid = 2;
                        } else {
                            this._numberBigInt = _bigNegative(_decode64Bits2);
                            this._numTypesValid = 4;
                        }
                    }
                }
                JsonToken jsonToken4 = JsonToken.VALUE_NUMBER_INT;
                this._currToken = jsonToken4;
                return jsonToken4;
            case 2:
                this._typeByte = b4;
                this._tokenIncomplete = true;
                int i8 = this._tagValue;
                if (i8 >= 0) {
                    return _handleTaggedBinary(i8);
                }
                JsonToken jsonToken5 = JsonToken.VALUE_EMBEDDED_OBJECT;
                this._currToken = jsonToken5;
                return jsonToken5;
            case 3:
                this._typeByte = b4;
                this._tokenIncomplete = true;
                JsonToken jsonToken6 = JsonToken.VALUE_STRING;
                this._currToken = jsonToken6;
                return jsonToken6;
            case 4:
                int _decodeExplicitLength = _decodeExplicitLength(b5);
                int i9 = this._tagValue;
                if (i9 >= 0) {
                    return _handleTaggedArray(i9, _decodeExplicitLength);
                }
                this._streamReadContext = this._streamReadContext.createChildArrayContext(_decodeExplicitLength);
                JsonToken jsonToken7 = JsonToken.START_ARRAY;
                this._currToken = jsonToken7;
                return jsonToken7;
            case 5:
                this._currToken = JsonToken.START_OBJECT;
                this._streamReadContext = this._streamReadContext.createChildObjectContext(_decodeExplicitLength(b5));
                return this._currToken;
            case 6:
                _reportError("Multiple tags not allowed per value (first tag: " + this._tagValue + ")");
                break;
        }
        switch (b5) {
            case 20:
                JsonToken jsonToken8 = JsonToken.VALUE_FALSE;
                this._currToken = jsonToken8;
                return jsonToken8;
            case 21:
                JsonToken jsonToken9 = JsonToken.VALUE_TRUE;
                this._currToken = jsonToken9;
                return jsonToken9;
            case 22:
                JsonToken jsonToken10 = JsonToken.VALUE_NULL;
                this._currToken = jsonToken10;
                return jsonToken10;
            case 23:
                JsonToken _decodeUndefinedValue = _decodeUndefinedValue();
                this._currToken = _decodeUndefinedValue;
                return _decodeUndefinedValue;
            case 25:
                this._numberFloat = _decodeHalfSizeFloat();
                this._numTypesValid = 32;
                JsonToken jsonToken11 = JsonToken.VALUE_NUMBER_FLOAT;
                this._currToken = jsonToken11;
                return jsonToken11;
            case 26:
                this._numberFloat = Float.intBitsToFloat(_decode32Bits());
                this._numTypesValid = 32;
                JsonToken jsonToken12 = JsonToken.VALUE_NUMBER_FLOAT;
                this._currToken = jsonToken12;
                return jsonToken12;
            case 27:
                this._numberDouble = Double.longBitsToDouble(_decode64Bits());
                this._numTypesValid = 8;
                JsonToken jsonToken13 = JsonToken.VALUE_NUMBER_FLOAT;
                this._currToken = jsonToken13;
                return jsonToken13;
            case 31:
                if (!this._streamReadContext.inArray() || this._streamReadContext.hasExpectedLength()) {
                    _reportUnexpectedBreak();
                    break;
                } else {
                    this._streamReadContext = this._streamReadContext.getParent();
                    JsonToken jsonToken14 = JsonToken.END_ARRAY;
                    this._currToken = jsonToken14;
                    return jsonToken14;
                }
        }
        JsonToken _decodeSimpleValue = _decodeSimpleValue(b5, b4);
        this._currToken = _decodeSimpleValue;
        return _decodeSimpleValue;
    }

    public void overrideCurrentName(String str) {
        CBORReadContext cBORReadContext = this._streamReadContext;
        JsonToken jsonToken = this._currToken;
        if (jsonToken == JsonToken.START_OBJECT || jsonToken == JsonToken.START_ARRAY) {
            cBORReadContext = cBORReadContext.getParent();
        }
        try {
            cBORReadContext.setCurrentName(str);
        } catch (IOException e3) {
            throw new IllegalStateException(e3);
        }
    }

    public int readBinaryValue(Base64Variant base64Variant, OutputStream outputStream) throws IOException {
        JsonToken jsonToken = this._currToken;
        int i3 = 0;
        if (jsonToken != JsonToken.VALUE_EMBEDDED_OBJECT) {
            if (jsonToken == JsonToken.VALUE_STRING) {
                byte[] _getBinaryFromString = _getBinaryFromString(base64Variant);
                int length = _getBinaryFromString.length;
                outputStream.write(_getBinaryFromString, 0, length);
                return length;
            }
            throw _constructReadException("Current token (%s) not VALUE_EMBEDDED_OBJECT or VALUE_STRING, can not access as binary", (Object) currentToken());
        } else if (!this._tokenIncomplete) {
            byte[] bArr = this._binaryValue;
            if (bArr == null) {
                return 0;
            }
            int length2 = bArr.length;
            outputStream.write(bArr, 0, length2);
            return length2;
        } else {
            this._tokenIncomplete = false;
            int _decodeExplicitLength = _decodeExplicitLength(this._typeByte & 31);
            if (_decodeExplicitLength >= 0) {
                return _readAndWriteBytes(outputStream, _decodeExplicitLength);
            }
            while (true) {
                int _decodeChunkLength = _decodeChunkLength(2);
                if (_decodeChunkLength < 0) {
                    return i3;
                }
                i3 += _readAndWriteBytes(outputStream, _decodeChunkLength);
            }
        }
    }

    public int releaseBuffered(OutputStream outputStream) throws IOException {
        int i3 = this._inputEnd;
        int i4 = this._inputPtr;
        int i5 = i3 - i4;
        if (i5 < 1) {
            return 0;
        }
        outputStream.write(this._inputBuffer, i4, i5);
        return i5;
    }

    public void setCodec(ObjectCodec objectCodec) {
        this._objectCodec = objectCodec;
    }

    public Version version() {
        return PackageVersion.VERSION;
    }

    public void _reportInvalidOther(int i3, int i4) throws JsonParseException {
        this._inputPtr = i4;
        _reportInvalidOther(i3);
    }

    public CBORReadContext getParsingContext() {
        return this._streamReadContext;
    }

    public String getValueAsString(String str) throws IOException {
        JsonToken jsonToken = this._currToken;
        if (jsonToken == JsonToken.VALUE_STRING || (jsonToken != null && jsonToken != JsonToken.VALUE_NULL && jsonToken.isScalarValue())) {
            return getText();
        }
        return str;
    }

    public int getText(Writer writer) throws IOException {
        if (this._tokenIncomplete) {
            _finishToken();
        }
        JsonToken jsonToken = this._currToken;
        if (jsonToken == JsonToken.VALUE_STRING) {
            return this._textBuffer.contentsToWriter(writer);
        }
        if (jsonToken == JsonToken.FIELD_NAME) {
            String currentName = this._streamReadContext.getCurrentName();
            writer.write(currentName);
            return currentName.length();
        } else if (jsonToken == null) {
            return 0;
        } else {
            if (jsonToken.isNumeric()) {
                return this._textBuffer.contentsToWriter(writer);
            }
            char[] asCharArray = jsonToken.asCharArray();
            writer.write(asCharArray);
            return asCharArray.length;
        }
    }

    public String nextFieldName() throws IOException {
        JsonToken jsonToken;
        String str;
        if (this._streamReadContext.inObject() && this._currToken != (jsonToken = JsonToken.FIELD_NAME)) {
            this._numTypesValid = 0;
            if (this._tokenIncomplete) {
                _skipIncomplete();
            }
            this._tokenInputTotal = this._currInputProcessed + ((long) this._inputPtr);
            this._binaryValue = null;
            this._tagValue = -1;
            if (!this._streamReadContext.expectMoreValues()) {
                this._streamReadContext = this._streamReadContext.getParent();
                this._currToken = JsonToken.END_OBJECT;
                return null;
            }
            if (this._inputPtr >= this._inputEnd && !loadMore()) {
                _eofAsNextToken();
            }
            byte[] bArr = this._inputBuffer;
            int i3 = this._inputPtr;
            int i4 = i3 + 1;
            this._inputPtr = i4;
            byte b3 = bArr[i3];
            if (((b3 >> 5) & 7) != 3) {
                if (b3 == -1) {
                    if (!this._streamReadContext.hasExpectedLength()) {
                        this._streamReadContext = this._streamReadContext.getParent();
                        this._currToken = JsonToken.END_OBJECT;
                        return null;
                    }
                    _reportUnexpectedBreak();
                }
                _decodeNonStringName(b3);
                this._currToken = jsonToken;
                return getText();
            }
            byte b4 = b3 & 31;
            if (b4 > 23) {
                int _decodeExplicitLength = _decodeExplicitLength(b4);
                if (_decodeExplicitLength < 0) {
                    str = _decodeChunkedName();
                } else {
                    str = _decodeLongerName(_decodeExplicitLength);
                }
            } else if (b4 == 0) {
                str = "";
            } else {
                if (this._inputEnd - i4 < b4) {
                    _loadToHaveAtLeast(b4);
                }
                if (this._symbolsCanonical) {
                    String _findDecodedFromSymbols = _findDecodedFromSymbols(b4);
                    if (_findDecodedFromSymbols != null) {
                        this._inputPtr += b4;
                        str = _findDecodedFromSymbols;
                    } else {
                        str = _addDecodedToSymbols(b4, _decodeContiguousName(b4));
                    }
                } else {
                    str = _decodeContiguousName(b4);
                }
            }
            this._streamReadContext.setCurrentName(str);
            this._currToken = jsonToken;
            return str;
        } else if (nextToken() == JsonToken.FIELD_NAME) {
            return getCurrentName();
        } else {
            return null;
        }
    }
}
