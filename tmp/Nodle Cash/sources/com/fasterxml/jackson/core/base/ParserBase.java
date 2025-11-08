package com.fasterxml.jackson.core.base;

import android.support.v4.media.session.a;
import androidx.collection.SieveCacheKt;
import com.fasterxml.jackson.core.Base64Variant;
import com.fasterxml.jackson.core.JsonLocation;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.core.StreamReadCapability;
import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.core.io.ContentReference;
import com.fasterxml.jackson.core.io.IOContext;
import com.fasterxml.jackson.core.io.NumberInput;
import com.fasterxml.jackson.core.json.DupDetector;
import com.fasterxml.jackson.core.json.JsonReadContext;
import com.fasterxml.jackson.core.json.PackageVersion;
import com.fasterxml.jackson.core.util.ByteArrayBuilder;
import com.fasterxml.jackson.core.util.JacksonFeatureSet;
import com.fasterxml.jackson.core.util.TextBuffer;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Arrays;

public abstract class ParserBase extends ParserMinimalBase {
    protected static final JacksonFeatureSet<StreamReadCapability> JSON_READ_CAPABILITIES = JsonParser.DEFAULT_READ_CAPABILITIES;
    protected byte[] _binaryValue;
    protected ByteArrayBuilder _byteArrayBuilder;
    protected boolean _closed;
    protected long _currInputProcessed;
    protected int _currInputRow = 1;
    protected int _currInputRowStart;
    protected int _expLength;
    protected int _fractLength;
    protected int _inputEnd;
    protected int _inputPtr;
    protected int _intLength;
    protected final IOContext _ioContext;
    protected boolean _nameCopied;
    protected char[] _nameCopyBuffer;
    protected JsonToken _nextToken;
    protected int _numTypesValid = 0;
    protected BigDecimal _numberBigDecimal;
    protected BigInteger _numberBigInt;
    protected double _numberDouble;
    protected float _numberFloat;
    protected int _numberInt;
    protected long _numberLong;
    protected boolean _numberNegative;
    protected String _numberString;
    protected JsonReadContext _parsingContext;
    protected final TextBuffer _textBuffer;
    protected int _tokenInputCol;
    protected int _tokenInputRow = 1;
    protected long _tokenInputTotal;

    public ParserBase(IOContext iOContext, int i3) {
        super(i3);
        this._ioContext = iOContext;
        this._textBuffer = iOContext.constructTextBuffer();
        this._parsingContext = JsonReadContext.createRootContext(JsonParser.Feature.STRICT_DUPLICATE_DETECTION.enabledIn(i3) ? DupDetector.rootDetector((JsonParser) this) : null);
    }

    private void _parseSlowFloat(int i3) throws IOException {
        if (i3 == 16) {
            try {
                this._numberBigDecimal = null;
                this._numberString = this._textBuffer.contentsAsString();
                this._numTypesValid = 16;
            } catch (NumberFormatException e3) {
                _wrapError("Malformed numeric value (" + _longNumberDesc(this._textBuffer.contentsAsString()) + ")", e3);
            }
        } else if (i3 == 32) {
            this._numberFloat = this._textBuffer.contentsAsFloat(isEnabled(JsonParser.Feature.USE_FAST_DOUBLE_PARSER));
            this._numTypesValid = 32;
        } else {
            this._numberDouble = this._textBuffer.contentsAsDouble(isEnabled(JsonParser.Feature.USE_FAST_DOUBLE_PARSER));
            this._numTypesValid = 8;
        }
    }

    private void _parseSlowInt(int i3) throws IOException {
        String contentsAsString = this._textBuffer.contentsAsString();
        try {
            int i4 = this._intLength;
            char[] textBuffer = this._textBuffer.getTextBuffer();
            int textOffset = this._textBuffer.getTextOffset();
            boolean z2 = this._numberNegative;
            if (z2) {
                textOffset++;
            }
            if (NumberInput.inLongRange(textBuffer, textOffset, i4, z2)) {
                this._numberLong = Long.parseLong(contentsAsString);
                this._numTypesValid = 2;
                return;
            }
            if (i3 == 1 || i3 == 2) {
                _reportTooLongIntegral(i3, contentsAsString);
            }
            if (i3 != 8) {
                if (i3 != 32) {
                    this._numberBigInt = null;
                    this._numberString = contentsAsString;
                    this._numTypesValid = 4;
                    return;
                }
            }
            this._numberDouble = NumberInput.parseDouble(contentsAsString, isEnabled(JsonParser.Feature.USE_FAST_DOUBLE_PARSER));
            this._numTypesValid = 8;
        } catch (NumberFormatException e3) {
            _wrapError("Malformed numeric value (" + _longNumberDesc(contentsAsString) + ")", e3);
        }
    }

    public static int[] growArrayBy(int[] iArr, int i3) {
        return iArr == null ? new int[i3] : Arrays.copyOf(iArr, iArr.length + i3);
    }

    public void _checkStdFeatureChanges(int i3, int i4) {
        int mask = JsonParser.Feature.STRICT_DUPLICATE_DETECTION.getMask();
        if ((i4 & mask) != 0 && (i3 & mask) != 0) {
            if (this._parsingContext.getDupDetector() == null) {
                this._parsingContext = this._parsingContext.withDupDetector(DupDetector.rootDetector((JsonParser) this));
            } else {
                this._parsingContext = this._parsingContext.withDupDetector((DupDetector) null);
            }
        }
    }

    public abstract void _closeInput() throws IOException;

    public ContentReference _contentReference() {
        return JsonParser.Feature.INCLUDE_SOURCE_IN_LOCATION.enabledIn(this._features) ? this._ioContext.contentReference() : ContentReference.unknown();
    }

    public final int _decodeBase64Escape(Base64Variant base64Variant, int i3, int i4) throws IOException {
        if (i3 == 92) {
            char _decodeEscaped = _decodeEscaped();
            if (_decodeEscaped <= ' ' && i4 == 0) {
                return -1;
            }
            int decodeBase64Char = base64Variant.decodeBase64Char((int) _decodeEscaped);
            if (decodeBase64Char >= 0 || decodeBase64Char == -2) {
                return decodeBase64Char;
            }
            throw reportInvalidBase64Char(base64Variant, _decodeEscaped, i4);
        }
        throw reportInvalidBase64Char(base64Variant, i3, i4);
    }

    public char _decodeEscaped() throws IOException {
        throw new UnsupportedOperationException();
    }

    public final int _eofAsNextChar() throws JsonParseException {
        _handleEOF();
        return -1;
    }

    public void _finishString() throws IOException {
    }

    public BigDecimal _getBigDecimal() {
        BigDecimal bigDecimal = this._numberBigDecimal;
        if (bigDecimal != null) {
            return bigDecimal;
        }
        String str = this._numberString;
        if (str != null) {
            BigDecimal parseBigDecimal = NumberInput.parseBigDecimal(str);
            this._numberBigDecimal = parseBigDecimal;
            this._numberString = null;
            return parseBigDecimal;
        }
        throw new IllegalStateException("cannot get BigDecimal from current parser state");
    }

    public BigInteger _getBigInteger() {
        BigInteger bigInteger = this._numberBigInt;
        if (bigInteger != null) {
            return bigInteger;
        }
        String str = this._numberString;
        if (str != null) {
            BigInteger parseBigInteger = NumberInput.parseBigInteger(str);
            this._numberBigInt = parseBigInteger;
            this._numberString = null;
            return parseBigInteger;
        }
        throw new IllegalStateException("cannot get BigInteger from current parser state");
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

    @Deprecated
    public Object _getSourceReference() {
        if (JsonParser.Feature.INCLUDE_SOURCE_IN_LOCATION.enabledIn(this._features)) {
            return this._ioContext.contentReference().getRawContent();
        }
        return null;
    }

    public void _handleBase64MissingPadding(Base64Variant base64Variant) throws IOException {
        _reportError(base64Variant.missingPaddingMessage());
    }

    public void _handleEOF() throws JsonParseException {
        if (!this._parsingContext.inRoot()) {
            _reportInvalidEOF(String.format(": expected close marker for %s (start marker at %s)", new Object[]{this._parsingContext.inArray() ? "Array" : "Object", this._parsingContext.startLocation(_contentReference())}), (JsonToken) null);
        }
    }

    public char _handleUnrecognizedCharacterEscape(char c3) throws JsonProcessingException {
        if (isEnabled(JsonParser.Feature.ALLOW_BACKSLASH_ESCAPING_ANY_CHARACTER)) {
            return c3;
        }
        if (c3 == '\'' && isEnabled(JsonParser.Feature.ALLOW_SINGLE_QUOTES)) {
            return c3;
        }
        _reportError("Unrecognized character escape " + ParserMinimalBase._getCharDesc(c3));
        return c3;
    }

    public int _parseIntValue() throws IOException {
        if (this._closed) {
            _reportError("Internal error: _parseNumericValue called when parser instance closed");
        }
        if (this._currToken != JsonToken.VALUE_NUMBER_INT || this._intLength > 9) {
            _parseNumericValue(1);
            if ((this._numTypesValid & 1) == 0) {
                convertNumberToInt();
            }
            return this._numberInt;
        }
        int contentsAsInt = this._textBuffer.contentsAsInt(this._numberNegative);
        this._numberInt = contentsAsInt;
        this._numTypesValid = 1;
        return contentsAsInt;
    }

    public void _parseNumericValue(int i3) throws IOException {
        if (this._closed) {
            _reportError("Internal error: _parseNumericValue called when parser instance closed");
        }
        JsonToken jsonToken = this._currToken;
        if (jsonToken == JsonToken.VALUE_NUMBER_INT) {
            int i4 = this._intLength;
            if (i4 <= 9) {
                this._numberInt = this._textBuffer.contentsAsInt(this._numberNegative);
                this._numTypesValid = 1;
            } else if (i4 <= 18) {
                long contentsAsLong = this._textBuffer.contentsAsLong(this._numberNegative);
                if (i4 == 10) {
                    if (this._numberNegative) {
                        if (contentsAsLong >= SieveCacheKt.NodeMetaAndPreviousMask) {
                            this._numberInt = (int) contentsAsLong;
                            this._numTypesValid = 1;
                            return;
                        }
                    } else if (contentsAsLong <= SieveCacheKt.NodeLinkMask) {
                        this._numberInt = (int) contentsAsLong;
                        this._numTypesValid = 1;
                        return;
                    }
                }
                this._numberLong = contentsAsLong;
                this._numTypesValid = 2;
            } else {
                _parseSlowInt(i3);
            }
        } else if (jsonToken == JsonToken.VALUE_NUMBER_FLOAT) {
            _parseSlowFloat(i3);
        } else {
            _reportError("Current token (%s) not numeric, can not use numeric value accessors", jsonToken);
        }
    }

    public void _releaseBuffers() throws IOException {
        this._textBuffer.releaseBuffers();
        char[] cArr = this._nameCopyBuffer;
        if (cArr != null) {
            this._nameCopyBuffer = null;
            this._ioContext.releaseNameCopyBuffer(cArr);
        }
    }

    public void _reportMismatchedEndMarker(int i3, char c3) throws JsonParseException {
        JsonReadContext parsingContext = getParsingContext();
        _reportError(String.format("Unexpected close marker '%s': expected '%c' (for %s starting at %s)", new Object[]{Character.valueOf((char) i3), Character.valueOf(c3), parsingContext.typeDesc(), parsingContext.startLocation(_contentReference())}));
    }

    public void _reportTooLongIntegral(int i3, String str) throws IOException {
        if (i3 == 1) {
            reportOverflowInt(str);
        } else {
            reportOverflowLong(str);
        }
    }

    public void _throwUnquotedSpace(int i3, String str) throws JsonParseException {
        if (!isEnabled(JsonParser.Feature.ALLOW_UNQUOTED_CONTROL_CHARS) || i3 > 32) {
            _reportError("Illegal unquoted character (" + ParserMinimalBase._getCharDesc((char) i3) + "): has to be escaped using backslash to be included in " + str);
        }
    }

    public String _validJsonTokenList() throws IOException {
        return _validJsonValueList();
    }

    public String _validJsonValueList() throws IOException {
        return isEnabled(JsonParser.Feature.ALLOW_NON_NUMERIC_NUMBERS) ? "(JSON String, Number (or 'NaN'/'INF'/'+INF'), Array, Object or token 'null', 'true' or 'false')" : "(JSON String, Number, Array, Object or token 'null', 'true' or 'false')";
    }

    public void close() throws IOException {
        if (!this._closed) {
            this._inputPtr = Math.max(this._inputPtr, this._inputEnd);
            this._closed = true;
            try {
                _closeInput();
            } finally {
                _releaseBuffers();
            }
        }
    }

    public void convertNumberToBigDecimal() throws IOException {
        int i3 = this._numTypesValid;
        if ((i3 & 8) != 0) {
            this._numberBigDecimal = NumberInput.parseBigDecimal(getText());
        } else if ((i3 & 4) != 0) {
            this._numberBigDecimal = new BigDecimal(_getBigInteger());
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
            this._numberBigInt = _getBigDecimal().toBigInteger();
        } else if ((i3 & 2) != 0) {
            this._numberBigInt = BigInteger.valueOf(this._numberLong);
        } else if ((i3 & 1) != 0) {
            this._numberBigInt = BigInteger.valueOf((long) this._numberInt);
        } else if ((i3 & 8) != 0) {
            this._numberBigInt = BigDecimal.valueOf(this._numberDouble).toBigInteger();
        } else {
            _throwInternal();
        }
        this._numTypesValid |= 4;
    }

    public void convertNumberToDouble() throws IOException {
        int i3 = this._numTypesValid;
        if ((i3 & 16) != 0) {
            this._numberDouble = _getBigDecimal().doubleValue();
        } else if ((i3 & 4) != 0) {
            this._numberDouble = _getBigInteger().doubleValue();
        } else if ((i3 & 2) != 0) {
            this._numberDouble = (double) this._numberLong;
        } else if ((i3 & 1) != 0) {
            this._numberDouble = (double) this._numberInt;
        } else if ((i3 & 32) != 0) {
            this._numberDouble = (double) this._numberFloat;
        } else {
            _throwInternal();
        }
        this._numTypesValid |= 8;
    }

    public void convertNumberToFloat() throws IOException {
        int i3 = this._numTypesValid;
        if ((i3 & 16) != 0) {
            this._numberFloat = _getBigDecimal().floatValue();
        } else if ((i3 & 4) != 0) {
            this._numberFloat = _getBigInteger().floatValue();
        } else if ((i3 & 2) != 0) {
            this._numberFloat = (float) this._numberLong;
        } else if ((i3 & 1) != 0) {
            this._numberFloat = (float) this._numberInt;
        } else if ((i3 & 8) != 0) {
            this._numberFloat = (float) this._numberDouble;
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
                reportOverflowInt(getText(), currentToken());
            }
            this._numberInt = i4;
        } else if ((i3 & 4) != 0) {
            BigInteger _getBigInteger = _getBigInteger();
            if (ParserMinimalBase.BI_MIN_INT.compareTo(_getBigInteger) > 0 || ParserMinimalBase.BI_MAX_INT.compareTo(_getBigInteger) < 0) {
                reportOverflowInt();
            }
            this._numberInt = _getBigInteger.intValue();
        } else if ((i3 & 8) != 0) {
            double d2 = this._numberDouble;
            if (d2 < -2.147483648E9d || d2 > 2.147483647E9d) {
                reportOverflowInt();
            }
            this._numberInt = (int) this._numberDouble;
        } else if ((i3 & 16) != 0) {
            BigDecimal _getBigDecimal = _getBigDecimal();
            if (ParserMinimalBase.BD_MIN_INT.compareTo(_getBigDecimal) > 0 || ParserMinimalBase.BD_MAX_INT.compareTo(_getBigDecimal) < 0) {
                reportOverflowInt();
            }
            this._numberInt = _getBigDecimal.intValue();
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
            BigInteger _getBigInteger = _getBigInteger();
            if (ParserMinimalBase.BI_MIN_LONG.compareTo(_getBigInteger) > 0 || ParserMinimalBase.BI_MAX_LONG.compareTo(_getBigInteger) < 0) {
                reportOverflowLong();
            }
            this._numberLong = _getBigInteger.longValue();
        } else if ((i3 & 8) != 0) {
            double d2 = this._numberDouble;
            if (d2 < -9.223372036854776E18d || d2 > 9.223372036854776E18d) {
                reportOverflowLong();
            }
            this._numberLong = (long) this._numberDouble;
        } else if ((i3 & 16) != 0) {
            BigDecimal _getBigDecimal = _getBigDecimal();
            if (ParserMinimalBase.BD_MIN_LONG.compareTo(_getBigDecimal) > 0 || ParserMinimalBase.BD_MAX_LONG.compareTo(_getBigDecimal) < 0) {
                reportOverflowLong();
            }
            this._numberLong = _getBigDecimal.longValue();
        } else {
            _throwInternal();
        }
        this._numTypesValid |= 2;
    }

    public JsonParser disable(JsonParser.Feature feature) {
        this._features &= ~feature.getMask();
        if (feature == JsonParser.Feature.STRICT_DUPLICATE_DETECTION) {
            this._parsingContext = this._parsingContext.withDupDetector((DupDetector) null);
        }
        return this;
    }

    public JsonParser enable(JsonParser.Feature feature) {
        this._features |= feature.getMask();
        if (feature == JsonParser.Feature.STRICT_DUPLICATE_DETECTION && this._parsingContext.getDupDetector() == null) {
            this._parsingContext = this._parsingContext.withDupDetector(DupDetector.rootDetector((JsonParser) this));
        }
        return this;
    }

    public BigInteger getBigIntegerValue() throws IOException {
        int i3 = this._numTypesValid;
        if ((i3 & 4) == 0) {
            if (i3 == 0) {
                _parseNumericValue(4);
            }
            if ((this._numTypesValid & 4) == 0) {
                convertNumberToBigInteger();
            }
        }
        return _getBigInteger();
    }

    public byte[] getBinaryValue(Base64Variant base64Variant) throws IOException {
        if (this._binaryValue == null) {
            if (this._currToken != JsonToken.VALUE_STRING) {
                _reportError("Current token (" + this._currToken + ") not VALUE_STRING, can not access as binary");
            }
            ByteArrayBuilder _getByteArrayBuilder = _getByteArrayBuilder();
            _decodeBase64(getText(), _getByteArrayBuilder, base64Variant);
            this._binaryValue = _getByteArrayBuilder.toByteArray();
        }
        return this._binaryValue;
    }

    public JsonLocation getCurrentLocation() {
        return new JsonLocation(_contentReference(), -1, ((long) this._inputPtr) + this._currInputProcessed, this._currInputRow, (this._inputPtr - this._currInputRowStart) + 1);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:4:0x000a, code lost:
        r0 = r2._parsingContext.getParent();
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.String getCurrentName() throws java.io.IOException {
        /*
            r2 = this;
            com.fasterxml.jackson.core.JsonToken r0 = r2._currToken
            com.fasterxml.jackson.core.JsonToken r1 = com.fasterxml.jackson.core.JsonToken.START_OBJECT
            if (r0 == r1) goto L_0x000a
            com.fasterxml.jackson.core.JsonToken r1 = com.fasterxml.jackson.core.JsonToken.START_ARRAY
            if (r0 != r1) goto L_0x0017
        L_0x000a:
            com.fasterxml.jackson.core.json.JsonReadContext r0 = r2._parsingContext
            com.fasterxml.jackson.core.json.JsonReadContext r0 = r0.getParent()
            if (r0 == 0) goto L_0x0017
            java.lang.String r2 = r0.getCurrentName()
            return r2
        L_0x0017:
            com.fasterxml.jackson.core.json.JsonReadContext r2 = r2._parsingContext
            java.lang.String r2 = r2.getCurrentName()
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.fasterxml.jackson.core.base.ParserBase.getCurrentName():java.lang.String");
    }

    public Object getCurrentValue() {
        return this._parsingContext.getCurrentValue();
    }

    public BigDecimal getDecimalValue() throws IOException {
        int i3 = this._numTypesValid;
        if ((i3 & 16) == 0) {
            if (i3 == 0) {
                _parseNumericValue(16);
            }
            if ((this._numTypesValid & 16) == 0) {
                convertNumberToBigDecimal();
            }
        }
        return _getBigDecimal();
    }

    public double getDoubleValue() throws IOException {
        int i3 = this._numTypesValid;
        if ((i3 & 8) == 0) {
            if (i3 == 0) {
                _parseNumericValue(8);
            }
            if ((this._numTypesValid & 8) == 0) {
                convertNumberToDouble();
            }
        }
        return this._numberDouble;
    }

    public float getFloatValue() throws IOException {
        int i3 = this._numTypesValid;
        if ((i3 & 32) == 0) {
            if (i3 == 0) {
                _parseNumericValue(32);
            }
            if ((this._numTypesValid & 32) == 0) {
                convertNumberToFloat();
            }
        }
        return this._numberFloat;
    }

    public int getIntValue() throws IOException {
        int i3 = this._numTypesValid;
        if ((i3 & 1) == 0) {
            if (i3 == 0) {
                return _parseIntValue();
            }
            if ((i3 & 1) == 0) {
                convertNumberToInt();
            }
        }
        return this._numberInt;
    }

    public long getLongValue() throws IOException {
        int i3 = this._numTypesValid;
        if ((i3 & 2) == 0) {
            if (i3 == 0) {
                _parseNumericValue(2);
            }
            if ((this._numTypesValid & 2) == 0) {
                convertNumberToLong();
            }
        }
        return this._numberLong;
    }

    public JsonParser.NumberType getNumberType() throws IOException {
        if (this._numTypesValid == 0) {
            _parseNumericValue(0);
        }
        if (this._currToken == JsonToken.VALUE_NUMBER_INT) {
            int i3 = this._numTypesValid;
            return (i3 & 1) != 0 ? JsonParser.NumberType.INT : (i3 & 2) != 0 ? JsonParser.NumberType.LONG : JsonParser.NumberType.BIG_INTEGER;
        }
        int i4 = this._numTypesValid;
        return (i4 & 16) != 0 ? JsonParser.NumberType.BIG_DECIMAL : (i4 & 32) != 0 ? JsonParser.NumberType.FLOAT : JsonParser.NumberType.DOUBLE;
    }

    public Number getNumberValue() throws IOException {
        if (this._numTypesValid == 0) {
            _parseNumericValue(0);
        }
        if (this._currToken == JsonToken.VALUE_NUMBER_INT) {
            int i3 = this._numTypesValid;
            if ((i3 & 1) != 0) {
                return Integer.valueOf(this._numberInt);
            }
            if ((i3 & 2) != 0) {
                return Long.valueOf(this._numberLong);
            }
            if ((i3 & 4) != 0) {
                return _getBigInteger();
            }
            _throwInternal();
        }
        int i4 = this._numTypesValid;
        if ((i4 & 16) != 0) {
            return _getBigDecimal();
        }
        if ((i4 & 32) != 0) {
            return Float.valueOf(this._numberFloat);
        }
        if ((i4 & 8) == 0) {
            _throwInternal();
        }
        return Double.valueOf(this._numberDouble);
    }

    public Number getNumberValueExact() throws IOException {
        if (this._currToken == JsonToken.VALUE_NUMBER_INT) {
            if (this._numTypesValid == 0) {
                _parseNumericValue(0);
            }
            int i3 = this._numTypesValid;
            if ((i3 & 1) != 0) {
                return Integer.valueOf(this._numberInt);
            }
            if ((i3 & 2) != 0) {
                return Long.valueOf(this._numberLong);
            }
            if ((i3 & 4) != 0) {
                return _getBigInteger();
            }
            _throwInternal();
        }
        if (this._numTypesValid == 0) {
            _parseNumericValue(16);
        }
        int i4 = this._numTypesValid;
        if ((i4 & 16) != 0) {
            return _getBigDecimal();
        }
        if ((i4 & 32) != 0) {
            return Float.valueOf(this._numberFloat);
        }
        if ((i4 & 8) == 0) {
            _throwInternal();
        }
        return Double.valueOf(this._numberDouble);
    }

    public long getTokenCharacterOffset() {
        return this._tokenInputTotal;
    }

    public int getTokenColumnNr() {
        int i3 = this._tokenInputCol;
        return i3 < 0 ? i3 : i3 + 1;
    }

    public int getTokenLineNr() {
        return this._tokenInputRow;
    }

    public JsonLocation getTokenLocation() {
        return new JsonLocation(_contentReference(), -1, getTokenCharacterOffset(), getTokenLineNr(), getTokenColumnNr());
    }

    public boolean hasTextCharacters() {
        JsonToken jsonToken = this._currToken;
        if (jsonToken == JsonToken.VALUE_STRING) {
            return true;
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
        if (this._currToken != JsonToken.VALUE_NUMBER_FLOAT || (this._numTypesValid & 8) == 0) {
            return false;
        }
        double d2 = this._numberDouble;
        return Double.isNaN(d2) || Double.isInfinite(d2);
    }

    @Deprecated
    public boolean loadMore() throws IOException {
        return false;
    }

    @Deprecated
    public void loadMoreGuaranteed() throws IOException {
        if (!loadMore()) {
            _reportInvalidEOF();
        }
    }

    public void overrideCurrentName(String str) {
        JsonReadContext jsonReadContext = this._parsingContext;
        JsonToken jsonToken = this._currToken;
        if (jsonToken == JsonToken.START_OBJECT || jsonToken == JsonToken.START_ARRAY) {
            jsonReadContext = jsonReadContext.getParent();
        }
        try {
            jsonReadContext.setCurrentName(str);
        } catch (IOException e3) {
            throw new IllegalStateException(e3);
        }
    }

    public JsonParser overrideStdFeatures(int i3, int i4) {
        int i5 = this._features;
        int i6 = (i3 & i4) | ((~i4) & i5);
        int i7 = i5 ^ i6;
        if (i7 != 0) {
            this._features = i6;
            _checkStdFeatureChanges(i6, i7);
        }
        return this;
    }

    public IllegalArgumentException reportInvalidBase64Char(Base64Variant base64Variant, int i3, int i4) throws IllegalArgumentException {
        return reportInvalidBase64Char(base64Variant, i3, i4, (String) null);
    }

    public final JsonToken reset(boolean z2, int i3, int i4, int i5) {
        return (i4 >= 1 || i5 >= 1) ? resetFloat(z2, i3, i4, i5) : resetInt(z2, i3);
    }

    public final JsonToken resetAsNaN(String str, double d2) {
        this._textBuffer.resetWithString(str);
        this._numberDouble = d2;
        this._numTypesValid = 8;
        return JsonToken.VALUE_NUMBER_FLOAT;
    }

    public final JsonToken resetFloat(boolean z2, int i3, int i4, int i5) {
        this._numberNegative = z2;
        this._intLength = i3;
        this._fractLength = i4;
        this._expLength = i5;
        this._numTypesValid = 0;
        return JsonToken.VALUE_NUMBER_FLOAT;
    }

    public final JsonToken resetInt(boolean z2, int i3) {
        this._numberNegative = z2;
        this._intLength = i3;
        this._fractLength = 0;
        this._expLength = 0;
        this._numTypesValid = 0;
        return JsonToken.VALUE_NUMBER_INT;
    }

    public void setCurrentValue(Object obj) {
        this._parsingContext.setCurrentValue(obj);
    }

    @Deprecated
    public JsonParser setFeatureMask(int i3) {
        int i4 = this._features ^ i3;
        if (i4 != 0) {
            this._features = i3;
            _checkStdFeatureChanges(i3, i4);
        }
        return this;
    }

    public Version version() {
        return PackageVersion.VERSION;
    }

    public JsonReadContext getParsingContext() {
        return this._parsingContext;
    }

    public IllegalArgumentException reportInvalidBase64Char(Base64Variant base64Variant, int i3, int i4, String str) throws IllegalArgumentException {
        String str2;
        if (i3 <= 32) {
            str2 = String.format("Illegal white space character (code 0x%s) as character #%d of 4-char base64 unit: can only used between units", new Object[]{Integer.toHexString(i3), Integer.valueOf(i4 + 1)});
        } else if (base64Variant.usesPaddingChar(i3)) {
            str2 = "Unexpected padding character ('" + base64Variant.getPaddingChar() + "') as character #" + (i4 + 1) + " of 4-char base64 unit: padding only legal as 3rd or 4th character";
        } else if (!Character.isDefined(i3) || Character.isISOControl(i3)) {
            str2 = "Illegal character (code 0x" + Integer.toHexString(i3) + ") in base64 content";
        } else {
            str2 = "Illegal character '" + ((char) i3) + "' (code 0x" + Integer.toHexString(i3) + ") in base64 content";
        }
        if (str != null) {
            str2 = a.n(str2, ": ", str);
        }
        return new IllegalArgumentException(str2);
    }

    public final int _decodeBase64Escape(Base64Variant base64Variant, char c3, int i3) throws IOException {
        if (c3 == '\\') {
            char _decodeEscaped = _decodeEscaped();
            if (_decodeEscaped <= ' ' && i3 == 0) {
                return -1;
            }
            int decodeBase64Char = base64Variant.decodeBase64Char(_decodeEscaped);
            if (decodeBase64Char >= 0 || (decodeBase64Char == -2 && i3 >= 2)) {
                return decodeBase64Char;
            }
            throw reportInvalidBase64Char(base64Variant, _decodeEscaped, i3);
        }
        throw reportInvalidBase64Char(base64Variant, c3, i3);
    }
}
