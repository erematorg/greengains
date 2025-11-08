package com.fasterxml.jackson.core.json;

import A.a;
import com.fasterxml.jackson.core.Base64Variant;
import com.fasterxml.jackson.core.JsonLocation;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.core.SerializableString;
import com.fasterxml.jackson.core.StreamReadCapability;
import com.fasterxml.jackson.core.base.GeneratorBase;
import com.fasterxml.jackson.core.base.ParserBase;
import com.fasterxml.jackson.core.base.ParserMinimalBase;
import com.fasterxml.jackson.core.io.CharTypes;
import com.fasterxml.jackson.core.io.IOContext;
import com.fasterxml.jackson.core.sym.ByteQuadsCanonicalizer;
import com.fasterxml.jackson.core.util.ByteArrayBuilder;
import com.fasterxml.jackson.core.util.JacksonFeatureSet;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Writer;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;
import okio.Utf8;
import org.apache.commons.lang3.CharUtils;
import org.msgpack.core.MessagePack;
import org.objectweb.asm.signature.SignatureVisitor;

public class UTF8StreamJsonParser extends ParserBase {
    static final byte BYTE_LF = 10;
    private static final int FEAT_MASK_ALLOW_JAVA_COMMENTS = JsonParser.Feature.ALLOW_COMMENTS.getMask();
    private static final int FEAT_MASK_ALLOW_MISSING = JsonParser.Feature.ALLOW_MISSING_VALUES.getMask();
    private static final int FEAT_MASK_ALLOW_SINGLE_QUOTES = JsonParser.Feature.ALLOW_SINGLE_QUOTES.getMask();
    private static final int FEAT_MASK_ALLOW_UNQUOTED_NAMES = JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES.getMask();
    private static final int FEAT_MASK_ALLOW_YAML_COMMENTS = JsonParser.Feature.ALLOW_YAML_COMMENTS.getMask();
    private static final int FEAT_MASK_LEADING_ZEROS = JsonParser.Feature.ALLOW_NUMERIC_LEADING_ZEROS.getMask();
    private static final int FEAT_MASK_NON_NUM_NUMBERS = JsonParser.Feature.ALLOW_NON_NUMERIC_NUMBERS.getMask();
    private static final int FEAT_MASK_TRAILING_COMMA = JsonParser.Feature.ALLOW_TRAILING_COMMA.getMask();
    protected static final int[] _icLatin1 = CharTypes.getInputCodeLatin1();
    private static final int[] _icUTF8 = CharTypes.getInputCodeUtf8();
    protected boolean _bufferRecyclable;
    protected byte[] _inputBuffer;
    protected InputStream _inputStream;
    protected int _nameStartCol;
    protected int _nameStartOffset;
    protected int _nameStartRow;
    protected ObjectCodec _objectCodec;
    private int _quad1;
    protected int[] _quadBuffer;
    protected final ByteQuadsCanonicalizer _symbols;
    protected boolean _tokenIncomplete;

    @Deprecated
    public UTF8StreamJsonParser(IOContext iOContext, int i3, InputStream inputStream, ObjectCodec objectCodec, ByteQuadsCanonicalizer byteQuadsCanonicalizer, byte[] bArr, int i4, int i5, boolean z2) {
        this(iOContext, i3, inputStream, objectCodec, byteQuadsCanonicalizer, bArr, i4, i5, 0, z2);
    }

    private final void _checkMatchEnd(String str, int i3, int i4) throws IOException {
        if (Character.isJavaIdentifierPart((char) _decodeCharForError(i4))) {
            _reportInvalidToken(str.substring(0, i3));
        }
    }

    private final void _closeArrayScope() throws JsonParseException {
        _updateLocation();
        if (!this._parsingContext.inArray()) {
            _reportMismatchedEndMarker(93, AbstractJsonLexerKt.END_OBJ);
        }
        this._parsingContext = this._parsingContext.clearAndGetParent();
    }

    private final void _closeObjectScope() throws JsonParseException {
        _updateLocation();
        if (!this._parsingContext.inObject()) {
            _reportMismatchedEndMarker(125, AbstractJsonLexerKt.END_LIST);
        }
        this._parsingContext = this._parsingContext.clearAndGetParent();
    }

    private final JsonToken _closeScope(int i3) throws JsonParseException {
        if (i3 == 125) {
            _closeObjectScope();
            JsonToken jsonToken = JsonToken.END_OBJECT;
            this._currToken = jsonToken;
            return jsonToken;
        }
        _closeArrayScope();
        JsonToken jsonToken2 = JsonToken.END_ARRAY;
        this._currToken = jsonToken2;
        return jsonToken2;
    }

    private final int _decodeUtf8_2(int i3) throws IOException {
        if (this._inputPtr >= this._inputEnd) {
            _loadMoreGuaranteed();
        }
        byte[] bArr = this._inputBuffer;
        int i4 = this._inputPtr;
        int i5 = i4 + 1;
        this._inputPtr = i5;
        byte b3 = bArr[i4];
        if ((b3 & MessagePack.Code.NIL) != 128) {
            _reportInvalidOther(b3 & 255, i5);
        }
        return ((i3 & 31) << 6) | (b3 & Utf8.REPLACEMENT_BYTE);
    }

    private final int _decodeUtf8_3(int i3) throws IOException {
        if (this._inputPtr >= this._inputEnd) {
            _loadMoreGuaranteed();
        }
        int i4 = i3 & 15;
        byte[] bArr = this._inputBuffer;
        int i5 = this._inputPtr;
        int i6 = i5 + 1;
        this._inputPtr = i6;
        byte b3 = bArr[i5];
        if ((b3 & MessagePack.Code.NIL) != 128) {
            _reportInvalidOther(b3 & 255, i6);
        }
        byte b4 = (i4 << 6) | (b3 & Utf8.REPLACEMENT_BYTE);
        if (this._inputPtr >= this._inputEnd) {
            _loadMoreGuaranteed();
        }
        byte[] bArr2 = this._inputBuffer;
        int i7 = this._inputPtr;
        int i8 = i7 + 1;
        this._inputPtr = i8;
        byte b5 = bArr2[i7];
        if ((b5 & MessagePack.Code.NIL) != 128) {
            _reportInvalidOther(b5 & 255, i8);
        }
        return (b4 << 6) | (b5 & Utf8.REPLACEMENT_BYTE);
    }

    private final int _decodeUtf8_3fast(int i3) throws IOException {
        int i4 = i3 & 15;
        byte[] bArr = this._inputBuffer;
        int i5 = this._inputPtr;
        int i6 = i5 + 1;
        this._inputPtr = i6;
        byte b3 = bArr[i5];
        if ((b3 & MessagePack.Code.NIL) != 128) {
            _reportInvalidOther(b3 & 255, i6);
        }
        byte b4 = (i4 << 6) | (b3 & Utf8.REPLACEMENT_BYTE);
        byte[] bArr2 = this._inputBuffer;
        int i7 = this._inputPtr;
        int i8 = i7 + 1;
        this._inputPtr = i8;
        byte b5 = bArr2[i7];
        if ((b5 & MessagePack.Code.NIL) != 128) {
            _reportInvalidOther(b5 & 255, i8);
        }
        return (b4 << 6) | (b5 & Utf8.REPLACEMENT_BYTE);
    }

    private final int _decodeUtf8_4(int i3) throws IOException {
        if (this._inputPtr >= this._inputEnd) {
            _loadMoreGuaranteed();
        }
        byte[] bArr = this._inputBuffer;
        int i4 = this._inputPtr;
        int i5 = i4 + 1;
        this._inputPtr = i5;
        byte b3 = bArr[i4];
        if ((b3 & MessagePack.Code.NIL) != 128) {
            _reportInvalidOther(b3 & 255, i5);
        }
        byte b4 = ((i3 & 7) << 6) | (b3 & Utf8.REPLACEMENT_BYTE);
        if (this._inputPtr >= this._inputEnd) {
            _loadMoreGuaranteed();
        }
        byte[] bArr2 = this._inputBuffer;
        int i6 = this._inputPtr;
        int i7 = i6 + 1;
        this._inputPtr = i7;
        byte b5 = bArr2[i6];
        if ((b5 & MessagePack.Code.NIL) != 128) {
            _reportInvalidOther(b5 & 255, i7);
        }
        byte b6 = (b4 << 6) | (b5 & Utf8.REPLACEMENT_BYTE);
        if (this._inputPtr >= this._inputEnd) {
            _loadMoreGuaranteed();
        }
        byte[] bArr3 = this._inputBuffer;
        int i8 = this._inputPtr;
        int i9 = i8 + 1;
        this._inputPtr = i9;
        byte b7 = bArr3[i8];
        if ((b7 & MessagePack.Code.NIL) != 128) {
            _reportInvalidOther(b7 & 255, i9);
        }
        return ((b6 << 6) | (b7 & Utf8.REPLACEMENT_BYTE)) - 65536;
    }

    private final void _finishString2(char[] cArr, int i3) throws IOException {
        int[] iArr = _icUTF8;
        byte[] bArr = this._inputBuffer;
        while (true) {
            int i4 = this._inputPtr;
            if (i4 >= this._inputEnd) {
                _loadMoreGuaranteed();
                i4 = this._inputPtr;
            }
            int i5 = 0;
            if (i3 >= cArr.length) {
                cArr = this._textBuffer.finishCurrentSegment();
                i3 = 0;
            }
            int min = Math.min(this._inputEnd, (cArr.length - i3) + i4);
            while (true) {
                if (i4 >= min) {
                    this._inputPtr = i4;
                    break;
                }
                int i6 = i4 + 1;
                int i7 = bArr[i4] & 255;
                int i8 = iArr[i7];
                if (i8 != 0) {
                    this._inputPtr = i6;
                    if (i7 == 34) {
                        this._textBuffer.setCurrentLength(i3);
                        return;
                    }
                    if (i8 == 1) {
                        i7 = _decodeEscaped();
                    } else if (i8 == 2) {
                        i7 = _decodeUtf8_2(i7);
                    } else if (i8 == 3) {
                        i7 = this._inputEnd - i6 >= 2 ? _decodeUtf8_3fast(i7) : _decodeUtf8_3(i7);
                    } else if (i8 == 4) {
                        int _decodeUtf8_4 = _decodeUtf8_4(i7);
                        int i9 = i3 + 1;
                        cArr[i3] = (char) ((_decodeUtf8_4 >> 10) | GeneratorBase.SURR1_FIRST);
                        if (i9 >= cArr.length) {
                            cArr = this._textBuffer.finishCurrentSegment();
                            i3 = 0;
                        } else {
                            i3 = i9;
                        }
                        i7 = (_decodeUtf8_4 & 1023) | 56320;
                    } else if (i7 < 32) {
                        _throwUnquotedSpace(i7, "string value");
                    } else {
                        _reportInvalidChar(i7);
                    }
                    if (i3 >= cArr.length) {
                        cArr = this._textBuffer.finishCurrentSegment();
                    } else {
                        i5 = i3;
                    }
                    i3 = i5 + 1;
                    cArr[i5] = (char) i7;
                } else {
                    cArr[i3] = (char) i7;
                    i4 = i6;
                    i3++;
                }
            }
        }
    }

    private final boolean _isNextTokenNameMaybe(int i3, SerializableString serializableString) throws IOException {
        JsonToken jsonToken;
        String _parseName = _parseName(i3);
        this._parsingContext.setCurrentName(_parseName);
        boolean equals = _parseName.equals(serializableString.getValue());
        this._currToken = JsonToken.FIELD_NAME;
        int _skipColon = _skipColon();
        _updateLocation();
        if (_skipColon == 34) {
            this._tokenIncomplete = true;
            this._nextToken = JsonToken.VALUE_STRING;
            return equals;
        }
        if (_skipColon == 43) {
            jsonToken = isEnabled(JsonReadFeature.ALLOW_LEADING_PLUS_SIGN_FOR_NUMBERS.mappedFeature()) ? _parseSignedNumber(false) : _handleUnexpectedValue(_skipColon);
        } else if (_skipColon == 91) {
            jsonToken = JsonToken.START_ARRAY;
        } else if (_skipColon == 102) {
            _matchFalse();
            jsonToken = JsonToken.VALUE_FALSE;
        } else if (_skipColon == 110) {
            _matchNull();
            jsonToken = JsonToken.VALUE_NULL;
        } else if (_skipColon == 116) {
            _matchTrue();
            jsonToken = JsonToken.VALUE_TRUE;
        } else if (_skipColon == 123) {
            jsonToken = JsonToken.START_OBJECT;
        } else if (_skipColon == 45) {
            jsonToken = _parseSignedNumber(true);
        } else if (_skipColon != 46) {
            switch (_skipColon) {
                case 48:
                case 49:
                case 50:
                case 51:
                case 52:
                case 53:
                case 54:
                case 55:
                case 56:
                case 57:
                    jsonToken = _parseUnsignedNumber(_skipColon);
                    break;
                default:
                    jsonToken = _handleUnexpectedValue(_skipColon);
                    break;
            }
        } else {
            jsonToken = _parseFloatThatStartsWithPeriod(false, false);
        }
        this._nextToken = jsonToken;
        return equals;
    }

    private final void _isNextTokenNameYes(int i3) throws IOException {
        this._currToken = JsonToken.FIELD_NAME;
        _updateLocation();
        if (i3 == 34) {
            this._tokenIncomplete = true;
            this._nextToken = JsonToken.VALUE_STRING;
        } else if (i3 != 43) {
            if (i3 == 91) {
                this._nextToken = JsonToken.START_ARRAY;
            } else if (i3 == 102) {
                _matchFalse();
                this._nextToken = JsonToken.VALUE_FALSE;
            } else if (i3 == 110) {
                _matchNull();
                this._nextToken = JsonToken.VALUE_NULL;
            } else if (i3 == 116) {
                _matchTrue();
                this._nextToken = JsonToken.VALUE_TRUE;
            } else if (i3 == 123) {
                this._nextToken = JsonToken.START_OBJECT;
            } else if (i3 == 45) {
                this._nextToken = _parseSignedNumber(true);
            } else if (i3 != 46) {
                switch (i3) {
                    case 48:
                    case 49:
                    case 50:
                    case 51:
                    case 52:
                    case 53:
                    case 54:
                    case 55:
                    case 56:
                    case 57:
                        this._nextToken = _parseUnsignedNumber(i3);
                        return;
                    default:
                        this._nextToken = _handleUnexpectedValue(i3);
                        return;
                }
            } else {
                this._nextToken = _parseFloatThatStartsWithPeriod(false, false);
            }
        } else if (isEnabled(JsonReadFeature.ALLOW_LEADING_PLUS_SIGN_FOR_NUMBERS.mappedFeature())) {
            this._nextToken = _parseSignedNumber(false);
        } else {
            this._nextToken = _handleUnexpectedValue(i3);
        }
    }

    private final void _matchToken2(String str, int i3) throws IOException {
        int i4;
        byte b3;
        int length = str.length();
        do {
            if ((this._inputPtr >= this._inputEnd && !_loadMore()) || this._inputBuffer[this._inputPtr] != str.charAt(i3)) {
                _reportInvalidToken(str.substring(0, i3));
            }
            i4 = this._inputPtr + 1;
            this._inputPtr = i4;
            i3++;
        } while (i3 < length);
        if ((i4 < this._inputEnd || _loadMore()) && (b3 = this._inputBuffer[this._inputPtr] & 255) >= 48 && b3 != 93 && b3 != 125) {
            _checkMatchEnd(str, i3, b3);
        }
    }

    private final JsonToken _nextAfterName() {
        this._nameCopied = false;
        JsonToken jsonToken = this._nextToken;
        this._nextToken = null;
        if (jsonToken == JsonToken.START_ARRAY) {
            this._parsingContext = this._parsingContext.createChildArrayContext(this._tokenInputRow, this._tokenInputCol);
        } else if (jsonToken == JsonToken.START_OBJECT) {
            this._parsingContext = this._parsingContext.createChildObjectContext(this._tokenInputRow, this._tokenInputCol);
        }
        this._currToken = jsonToken;
        return jsonToken;
    }

    private final JsonToken _nextTokenNotInObject(int i3) throws IOException {
        if (i3 == 34) {
            this._tokenIncomplete = true;
            JsonToken jsonToken = JsonToken.VALUE_STRING;
            this._currToken = jsonToken;
            return jsonToken;
        } else if (i3 != 43) {
            if (i3 == 91) {
                this._parsingContext = this._parsingContext.createChildArrayContext(this._tokenInputRow, this._tokenInputCol);
                JsonToken jsonToken2 = JsonToken.START_ARRAY;
                this._currToken = jsonToken2;
                return jsonToken2;
            } else if (i3 == 102) {
                _matchFalse();
                JsonToken jsonToken3 = JsonToken.VALUE_FALSE;
                this._currToken = jsonToken3;
                return jsonToken3;
            } else if (i3 == 110) {
                _matchNull();
                JsonToken jsonToken4 = JsonToken.VALUE_NULL;
                this._currToken = jsonToken4;
                return jsonToken4;
            } else if (i3 == 116) {
                _matchTrue();
                JsonToken jsonToken5 = JsonToken.VALUE_TRUE;
                this._currToken = jsonToken5;
                return jsonToken5;
            } else if (i3 == 123) {
                this._parsingContext = this._parsingContext.createChildObjectContext(this._tokenInputRow, this._tokenInputCol);
                JsonToken jsonToken6 = JsonToken.START_OBJECT;
                this._currToken = jsonToken6;
                return jsonToken6;
            } else if (i3 == 45) {
                JsonToken _parseSignedNumber = _parseSignedNumber(true);
                this._currToken = _parseSignedNumber;
                return _parseSignedNumber;
            } else if (i3 != 46) {
                switch (i3) {
                    case 48:
                    case 49:
                    case 50:
                    case 51:
                    case 52:
                    case 53:
                    case 54:
                    case 55:
                    case 56:
                    case 57:
                        JsonToken _parseUnsignedNumber = _parseUnsignedNumber(i3);
                        this._currToken = _parseUnsignedNumber;
                        return _parseUnsignedNumber;
                    default:
                        JsonToken _handleUnexpectedValue = _handleUnexpectedValue(i3);
                        this._currToken = _handleUnexpectedValue;
                        return _handleUnexpectedValue;
                }
            } else {
                JsonToken _parseFloatThatStartsWithPeriod = _parseFloatThatStartsWithPeriod(false, false);
                this._currToken = _parseFloatThatStartsWithPeriod;
                return _parseFloatThatStartsWithPeriod;
            }
        } else if (!isEnabled(JsonReadFeature.ALLOW_LEADING_PLUS_SIGN_FOR_NUMBERS.mappedFeature())) {
            JsonToken _handleUnexpectedValue2 = _handleUnexpectedValue(i3);
            this._currToken = _handleUnexpectedValue2;
            return _handleUnexpectedValue2;
        } else {
            JsonToken _parseSignedNumber2 = _parseSignedNumber(false);
            this._currToken = _parseSignedNumber2;
            return _parseSignedNumber2;
        }
    }

    private static final int _padLastQuad(int i3, int i4) {
        return i4 == 4 ? i3 : i3 | (-1 << (i4 << 3));
    }

    private final JsonToken _parseFloat(char[] cArr, int i3, int i4, boolean z2, int i5) throws IOException {
        boolean z3;
        int i6;
        int i7 = 0;
        if (i4 == 46) {
            if (i3 >= cArr.length) {
                cArr = this._textBuffer.finishCurrentSegment();
                i3 = 0;
            }
            cArr[i3] = (char) i4;
            i3++;
            i6 = 0;
            while (true) {
                if (this._inputPtr >= this._inputEnd && !_loadMore()) {
                    z3 = true;
                    break;
                }
                byte[] bArr = this._inputBuffer;
                int i8 = this._inputPtr;
                this._inputPtr = i8 + 1;
                i4 = bArr[i8] & 255;
                if (i4 < 48 || i4 > 57) {
                    z3 = false;
                } else {
                    i6++;
                    if (i3 >= cArr.length) {
                        cArr = this._textBuffer.finishCurrentSegment();
                        i3 = 0;
                    }
                    cArr[i3] = (char) i4;
                    i3++;
                }
            }
            z3 = false;
            if (i6 == 0 && !isEnabled(JsonReadFeature.ALLOW_TRAILING_DECIMAL_POINT_FOR_NUMBERS.mappedFeature())) {
                _reportUnexpectedNumberChar(i4, "Decimal point not followed by a digit");
            }
        } else {
            i6 = 0;
            z3 = false;
        }
        if (r12 == 101 || r12 == 69) {
            if (i3 >= cArr.length) {
                cArr = this._textBuffer.finishCurrentSegment();
                i3 = 0;
            }
            int i9 = i3 + 1;
            cArr[i3] = (char) r12;
            if (this._inputPtr >= this._inputEnd) {
                _loadMoreGuaranteed();
            }
            byte[] bArr2 = this._inputBuffer;
            int i10 = this._inputPtr;
            this._inputPtr = i10 + 1;
            byte b3 = bArr2[i10] & 255;
            if (b3 == 45 || b3 == 43) {
                if (i9 >= cArr.length) {
                    cArr = this._textBuffer.finishCurrentSegment();
                    i9 = 0;
                }
                int i11 = i9 + 1;
                cArr[i9] = (char) b3;
                if (this._inputPtr >= this._inputEnd) {
                    _loadMoreGuaranteed();
                }
                byte[] bArr3 = this._inputBuffer;
                int i12 = this._inputPtr;
                this._inputPtr = i12 + 1;
                b3 = bArr3[i12] & 255;
                i9 = i11;
            }
            r12 = b3;
            int i13 = 0;
            while (true) {
                if (r12 >= 48 && r12 <= 57) {
                    i13++;
                    if (i9 >= cArr.length) {
                        cArr = this._textBuffer.finishCurrentSegment();
                        i9 = 0;
                    }
                    int i14 = i9 + 1;
                    cArr[i9] = (char) r12;
                    if (this._inputPtr >= this._inputEnd && !_loadMore()) {
                        i7 = i13;
                        z3 = true;
                        r11 = i14;
                        break;
                    }
                    byte[] bArr4 = this._inputBuffer;
                    int i15 = this._inputPtr;
                    this._inputPtr = i15 + 1;
                    r12 = bArr4[i15] & 255;
                    i9 = i14;
                } else {
                    i7 = i13;
                    r11 = i9;
                }
            }
            i7 = i13;
            r11 = i9;
            if (i7 == 0) {
                _reportUnexpectedNumberChar(r12, "Exponent indicator not followed by a digit");
            }
        }
        if (!z3) {
            this._inputPtr--;
            if (this._parsingContext.inRoot()) {
                _verifyRootSpace(r12);
            }
        }
        this._textBuffer.setCurrentLength(i3);
        return resetFloat(z2, i5, i6, i7);
    }

    private final JsonToken _parseNumber2(char[] cArr, int i3, boolean z2, int i4) throws IOException {
        int i5;
        byte b3;
        char[] cArr2 = cArr;
        int i6 = i3;
        int i7 = i4;
        while (true) {
            if (this._inputPtr < this._inputEnd || _loadMore()) {
                byte[] bArr = this._inputBuffer;
                i5 = this._inputPtr;
                this._inputPtr = i5 + 1;
                b3 = bArr[i5] & 255;
                if (b3 <= 57 && b3 >= 48) {
                    if (i6 >= cArr2.length) {
                        i6 = 0;
                        cArr2 = this._textBuffer.finishCurrentSegment();
                    }
                    cArr2[i6] = (char) b3;
                    i7++;
                    i6++;
                }
            } else {
                this._textBuffer.setCurrentLength(i6);
                return resetInt(z2, i7);
            }
        }
        if (b3 == 46 || b3 == 101 || b3 == 69) {
            return _parseFloat(cArr2, i6, b3, z2, i7);
        }
        this._inputPtr = i5;
        this._textBuffer.setCurrentLength(i6);
        if (this._parsingContext.inRoot()) {
            _verifyRootSpace(this._inputBuffer[this._inputPtr] & 255);
        }
        return resetInt(z2, i7);
    }

    /* JADX WARNING: Removed duplicated region for block: B:32:0x0082  */
    /* JADX WARNING: Removed duplicated region for block: B:37:0x0099  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final com.fasterxml.jackson.core.JsonToken _parseSignedNumber(boolean r11) throws java.io.IOException {
        /*
            r10 = this;
            com.fasterxml.jackson.core.util.TextBuffer r0 = r10._textBuffer
            char[] r2 = r0.emptyAndGetCurrentSegment()
            r0 = 1
            r1 = 0
            if (r11 == 0) goto L_0x000f
            r3 = 45
            r2[r1] = r3
            r1 = r0
        L_0x000f:
            int r3 = r10._inputPtr
            int r4 = r10._inputEnd
            if (r3 < r4) goto L_0x0018
            r10._loadMoreGuaranteed()
        L_0x0018:
            byte[] r3 = r10._inputBuffer
            int r4 = r10._inputPtr
            int r5 = r4 + 1
            r10._inputPtr = r5
            byte r3 = r3[r4]
            r3 = r3 & 255(0xff, float:3.57E-43)
            r4 = 57
            r5 = 46
            r6 = 48
            if (r3 > r6) goto L_0x003f
            if (r3 == r6) goto L_0x003a
            if (r3 != r5) goto L_0x0035
            com.fasterxml.jackson.core.JsonToken r10 = r10._parseFloatThatStartsWithPeriod(r11, r0)
            return r10
        L_0x0035:
            com.fasterxml.jackson.core.JsonToken r10 = r10._handleInvalidNumberStart(r3, r11, r0)
            return r10
        L_0x003a:
            int r3 = r10._verifyNoLeadingZeroes()
            goto L_0x0046
        L_0x003f:
            if (r3 <= r4) goto L_0x0046
            com.fasterxml.jackson.core.JsonToken r10 = r10._handleInvalidNumberStart(r3, r11, r0)
            return r10
        L_0x0046:
            int r7 = r1 + 1
            char r3 = (char) r3
            r2[r1] = r3
            int r1 = r10._inputEnd
            int r3 = r10._inputPtr
            int r8 = r2.length
            int r3 = r3 + r8
            int r3 = r3 - r7
            int r1 = java.lang.Math.min(r1, r3)
        L_0x0056:
            r3 = r7
            int r7 = r10._inputPtr
            if (r7 < r1) goto L_0x0060
            com.fasterxml.jackson.core.JsonToken r10 = r10._parseNumber2(r2, r3, r11, r0)
            return r10
        L_0x0060:
            byte[] r8 = r10._inputBuffer
            int r9 = r7 + 1
            r10._inputPtr = r9
            byte r8 = r8[r7]
            r8 = r8 & 255(0xff, float:3.57E-43)
            if (r8 < r6) goto L_0x0077
            if (r8 <= r4) goto L_0x006f
            goto L_0x0077
        L_0x006f:
            int r0 = r0 + 1
            int r7 = r3 + 1
            char r8 = (char) r8
            r2[r3] = r8
            goto L_0x0056
        L_0x0077:
            if (r8 == r5) goto L_0x0099
            r1 = 101(0x65, float:1.42E-43)
            if (r8 == r1) goto L_0x0099
            r1 = 69
            if (r8 != r1) goto L_0x0082
            goto L_0x0099
        L_0x0082:
            r10._inputPtr = r7
            com.fasterxml.jackson.core.util.TextBuffer r1 = r10._textBuffer
            r1.setCurrentLength(r3)
            com.fasterxml.jackson.core.json.JsonReadContext r1 = r10._parsingContext
            boolean r1 = r1.inRoot()
            if (r1 == 0) goto L_0x0094
            r10._verifyRootSpace(r8)
        L_0x0094:
            com.fasterxml.jackson.core.JsonToken r10 = r10.resetInt(r11, r0)
            return r10
        L_0x0099:
            r1 = r10
            r4 = r8
            r5 = r11
            r6 = r0
            com.fasterxml.jackson.core.JsonToken r10 = r1._parseFloat(r2, r3, r4, r5, r6)
            return r10
        */
        throw new UnsupportedOperationException("Method not decompiled: com.fasterxml.jackson.core.json.UTF8StreamJsonParser._parseSignedNumber(boolean):com.fasterxml.jackson.core.JsonToken");
    }

    private final void _skipCComment() throws IOException {
        int[] inputCodeComment = CharTypes.getInputCodeComment();
        while (true) {
            if (this._inputPtr >= this._inputEnd && !_loadMore()) {
                break;
            }
            byte[] bArr = this._inputBuffer;
            int i3 = this._inputPtr;
            int i4 = i3 + 1;
            this._inputPtr = i4;
            byte b3 = bArr[i3] & 255;
            int i5 = inputCodeComment[b3];
            if (i5 != 0) {
                if (i5 == 2) {
                    _skipUtf8_2();
                } else if (i5 == 3) {
                    _skipUtf8_3();
                } else if (i5 == 4) {
                    _skipUtf8_4(b3);
                } else if (i5 == 10) {
                    this._currInputRow++;
                    this._currInputRowStart = i4;
                } else if (i5 != 13) {
                    if (i5 == 42) {
                        if (i4 >= this._inputEnd && !_loadMore()) {
                            break;
                        }
                        byte[] bArr2 = this._inputBuffer;
                        int i6 = this._inputPtr;
                        if (bArr2[i6] == 47) {
                            this._inputPtr = i6 + 1;
                            return;
                        }
                    } else {
                        _reportInvalidChar(b3);
                    }
                } else {
                    _skipCR();
                }
            }
        }
        _reportInvalidEOF(" in a comment", (JsonToken) null);
    }

    private final int _skipColon() throws IOException {
        int i3 = this._inputPtr;
        if (i3 + 4 >= this._inputEnd) {
            return _skipColon2(false);
        }
        byte[] bArr = this._inputBuffer;
        byte b3 = bArr[i3];
        if (b3 == 58) {
            int i4 = i3 + 1;
            this._inputPtr = i4;
            byte b4 = bArr[i4];
            if (b4 <= 32) {
                if (b4 == 32 || b4 == 9) {
                    int i5 = i3 + 2;
                    this._inputPtr = i5;
                    byte b5 = bArr[i5];
                    if (b5 > 32) {
                        if (b5 == 47 || b5 == 35) {
                            return _skipColon2(true);
                        }
                        this._inputPtr = i3 + 3;
                        return b5;
                    }
                }
                return _skipColon2(true);
            } else if (b4 == 47 || b4 == 35) {
                return _skipColon2(true);
            } else {
                this._inputPtr = i3 + 2;
                return b4;
            }
        } else {
            if (b3 == 32 || b3 == 9) {
                int i6 = i3 + 1;
                this._inputPtr = i6;
                b3 = bArr[i6];
            }
            if (b3 != 58) {
                return _skipColon2(false);
            }
            int i7 = this._inputPtr;
            int i8 = i7 + 1;
            this._inputPtr = i8;
            byte b6 = bArr[i8];
            if (b6 <= 32) {
                if (b6 == 32 || b6 == 9) {
                    int i9 = i7 + 2;
                    this._inputPtr = i9;
                    byte b7 = bArr[i9];
                    if (b7 > 32) {
                        if (b7 == 47 || b7 == 35) {
                            return _skipColon2(true);
                        }
                        this._inputPtr = i7 + 3;
                        return b7;
                    }
                }
                return _skipColon2(true);
            } else if (b6 == 47 || b6 == 35) {
                return _skipColon2(true);
            } else {
                this._inputPtr = i7 + 2;
                return b6;
            }
        }
    }

    private final int _skipColon2(boolean z2) throws IOException {
        while (true) {
            if (this._inputPtr < this._inputEnd || _loadMore()) {
                byte[] bArr = this._inputBuffer;
                int i3 = this._inputPtr;
                int i4 = i3 + 1;
                this._inputPtr = i4;
                byte b3 = bArr[i3] & 255;
                if (b3 > 32) {
                    if (b3 == 47) {
                        _skipComment();
                    } else if (b3 != 35 || !_skipYAMLComment()) {
                        if (z2) {
                            return b3;
                        }
                        if (b3 != 58) {
                            _reportUnexpectedChar(b3, "was expecting a colon to separate field name and value");
                        }
                        z2 = true;
                    }
                } else if (b3 != 32) {
                    if (b3 == 10) {
                        this._currInputRow++;
                        this._currInputRowStart = i4;
                    } else if (b3 == 13) {
                        _skipCR();
                    } else if (b3 != 9) {
                        _throwInvalidSpace(b3);
                    }
                }
            } else {
                _reportInvalidEOF(" within/between " + this._parsingContext.typeDesc() + " entries", (JsonToken) null);
                return -1;
            }
        }
    }

    private final int _skipColonFast(int i3) throws IOException {
        byte[] bArr = this._inputBuffer;
        int i4 = i3 + 1;
        byte b3 = bArr[i3];
        if (b3 == 58) {
            int i5 = i3 + 2;
            byte b4 = bArr[i4];
            if (b4 > 32) {
                if (!(b4 == 47 || b4 == 35)) {
                    this._inputPtr = i5;
                    return b4;
                }
            } else if (b4 == 32 || b4 == 9) {
                int i6 = i3 + 3;
                byte b5 = bArr[i5];
                if (b5 <= 32 || b5 == 47 || b5 == 35) {
                    i5 = i6;
                } else {
                    this._inputPtr = i6;
                    return b5;
                }
            }
            this._inputPtr = i5 - 1;
            return _skipColon2(true);
        }
        if (b3 == 32 || b3 == 9) {
            b3 = bArr[i4];
            i4 = i3 + 2;
        }
        if (b3 == 58) {
            int i7 = i4 + 1;
            byte b6 = bArr[i4];
            if (b6 > 32) {
                if (!(b6 == 47 || b6 == 35)) {
                    this._inputPtr = i7;
                    return b6;
                }
            } else if (b6 == 32 || b6 == 9) {
                int i8 = i4 + 2;
                byte b7 = bArr[i7];
                if (b7 <= 32 || b7 == 47 || b7 == 35) {
                    i7 = i8;
                } else {
                    this._inputPtr = i8;
                    return b7;
                }
            }
            this._inputPtr = i7 - 1;
            return _skipColon2(true);
        }
        this._inputPtr = i4 - 1;
        return _skipColon2(false);
    }

    private final void _skipComment() throws IOException {
        if ((this._features & FEAT_MASK_ALLOW_JAVA_COMMENTS) == 0) {
            _reportUnexpectedChar(47, "maybe a (non-standard) comment? (not recognized as one since Feature 'ALLOW_COMMENTS' not enabled for parser)");
        }
        if (this._inputPtr >= this._inputEnd && !_loadMore()) {
            _reportInvalidEOF(" in a comment", (JsonToken) null);
        }
        byte[] bArr = this._inputBuffer;
        int i3 = this._inputPtr;
        this._inputPtr = i3 + 1;
        byte b3 = bArr[i3] & 255;
        if (b3 == 47) {
            _skipLine();
        } else if (b3 == 42) {
            _skipCComment();
        } else {
            _reportUnexpectedChar(b3, "was expecting either '*' or '/' for a comment");
        }
    }

    private final void _skipLine() throws IOException {
        int[] inputCodeComment = CharTypes.getInputCodeComment();
        while (true) {
            if (this._inputPtr < this._inputEnd || _loadMore()) {
                byte[] bArr = this._inputBuffer;
                int i3 = this._inputPtr;
                int i4 = i3 + 1;
                this._inputPtr = i4;
                byte b3 = bArr[i3] & 255;
                int i5 = inputCodeComment[b3];
                if (i5 != 0) {
                    if (i5 == 2) {
                        _skipUtf8_2();
                    } else if (i5 == 3) {
                        _skipUtf8_3();
                    } else if (i5 == 4) {
                        _skipUtf8_4(b3);
                    } else if (i5 == 10) {
                        this._currInputRow++;
                        this._currInputRowStart = i4;
                        return;
                    } else if (i5 == 13) {
                        _skipCR();
                        return;
                    } else if (i5 != 42 && i5 < 0) {
                        _reportInvalidChar(b3);
                    }
                }
            } else {
                return;
            }
        }
    }

    private final void _skipUtf8_2() throws IOException {
        if (this._inputPtr >= this._inputEnd) {
            _loadMoreGuaranteed();
        }
        byte[] bArr = this._inputBuffer;
        int i3 = this._inputPtr;
        int i4 = i3 + 1;
        this._inputPtr = i4;
        byte b3 = bArr[i3];
        if ((b3 & MessagePack.Code.NIL) != 128) {
            _reportInvalidOther(b3 & 255, i4);
        }
    }

    private final void _skipUtf8_3() throws IOException {
        if (this._inputPtr >= this._inputEnd) {
            _loadMoreGuaranteed();
        }
        byte[] bArr = this._inputBuffer;
        int i3 = this._inputPtr;
        int i4 = i3 + 1;
        this._inputPtr = i4;
        byte b3 = bArr[i3];
        if ((b3 & MessagePack.Code.NIL) != 128) {
            _reportInvalidOther(b3 & 255, i4);
        }
        if (this._inputPtr >= this._inputEnd) {
            _loadMoreGuaranteed();
        }
        byte[] bArr2 = this._inputBuffer;
        int i5 = this._inputPtr;
        int i6 = i5 + 1;
        this._inputPtr = i6;
        byte b4 = bArr2[i5];
        if ((b4 & MessagePack.Code.NIL) != 128) {
            _reportInvalidOther(b4 & 255, i6);
        }
    }

    private final void _skipUtf8_4(int i3) throws IOException {
        if (this._inputPtr >= this._inputEnd) {
            _loadMoreGuaranteed();
        }
        byte[] bArr = this._inputBuffer;
        int i4 = this._inputPtr;
        int i5 = i4 + 1;
        this._inputPtr = i5;
        byte b3 = bArr[i4];
        if ((b3 & MessagePack.Code.NIL) != 128) {
            _reportInvalidOther(b3 & 255, i5);
        }
        if (this._inputPtr >= this._inputEnd) {
            _loadMoreGuaranteed();
        }
        byte[] bArr2 = this._inputBuffer;
        int i6 = this._inputPtr;
        int i7 = i6 + 1;
        this._inputPtr = i7;
        byte b4 = bArr2[i6];
        if ((b4 & MessagePack.Code.NIL) != 128) {
            _reportInvalidOther(b4 & 255, i7);
        }
        if (this._inputPtr >= this._inputEnd) {
            _loadMoreGuaranteed();
        }
        byte[] bArr3 = this._inputBuffer;
        int i8 = this._inputPtr;
        int i9 = i8 + 1;
        this._inputPtr = i9;
        byte b5 = bArr3[i8];
        if ((b5 & MessagePack.Code.NIL) != 128) {
            _reportInvalidOther(b5 & 255, i9);
        }
    }

    private final int _skipWS() throws IOException {
        while (true) {
            int i3 = this._inputPtr;
            if (i3 >= this._inputEnd) {
                return _skipWS2();
            }
            byte[] bArr = this._inputBuffer;
            int i4 = i3 + 1;
            this._inputPtr = i4;
            byte b3 = bArr[i3] & 255;
            if (b3 > 32) {
                if (b3 != 47 && b3 != 35) {
                    return b3;
                }
                this._inputPtr = i3;
                return _skipWS2();
            } else if (b3 != 32) {
                if (b3 == 10) {
                    this._currInputRow++;
                    this._currInputRowStart = i4;
                } else if (b3 == 13) {
                    _skipCR();
                } else if (b3 != 9) {
                    _throwInvalidSpace(b3);
                }
            }
        }
    }

    private final int _skipWS2() throws IOException {
        byte b3;
        while (true) {
            if (this._inputPtr < this._inputEnd || _loadMore()) {
                byte[] bArr = this._inputBuffer;
                int i3 = this._inputPtr;
                int i4 = i3 + 1;
                this._inputPtr = i4;
                b3 = bArr[i3] & 255;
                if (b3 > 32) {
                    if (b3 == 47) {
                        _skipComment();
                    } else if (b3 != 35 || !_skipYAMLComment()) {
                        return b3;
                    }
                } else if (b3 != 32) {
                    if (b3 == 10) {
                        this._currInputRow++;
                        this._currInputRowStart = i4;
                    } else if (b3 == 13) {
                        _skipCR();
                    } else if (b3 != 9) {
                        _throwInvalidSpace(b3);
                    }
                }
            } else {
                throw _constructError("Unexpected end-of-input within/between " + this._parsingContext.typeDesc() + " entries");
            }
        }
        return b3;
    }

    private final int _skipWSOrEnd() throws IOException {
        if (this._inputPtr >= this._inputEnd && !_loadMore()) {
            return _eofAsNextChar();
        }
        byte[] bArr = this._inputBuffer;
        int i3 = this._inputPtr;
        int i4 = i3 + 1;
        this._inputPtr = i4;
        byte b3 = bArr[i3] & 255;
        if (b3 <= 32) {
            if (b3 != 32) {
                if (b3 == 10) {
                    this._currInputRow++;
                    this._currInputRowStart = i4;
                } else if (b3 == 13) {
                    _skipCR();
                } else if (b3 != 9) {
                    _throwInvalidSpace(b3);
                }
            }
            while (true) {
                int i5 = this._inputPtr;
                if (i5 >= this._inputEnd) {
                    return _skipWSOrEnd2();
                }
                byte[] bArr2 = this._inputBuffer;
                int i6 = i5 + 1;
                this._inputPtr = i6;
                byte b4 = bArr2[i5] & 255;
                if (b4 > 32) {
                    if (b4 != 47 && b4 != 35) {
                        return b4;
                    }
                    this._inputPtr = i5;
                    return _skipWSOrEnd2();
                } else if (b4 != 32) {
                    if (b4 == 10) {
                        this._currInputRow++;
                        this._currInputRowStart = i6;
                    } else if (b4 == 13) {
                        _skipCR();
                    } else if (b4 != 9) {
                        _throwInvalidSpace(b4);
                    }
                }
            }
        } else if (b3 != 47 && b3 != 35) {
            return b3;
        } else {
            this._inputPtr = i3;
            return _skipWSOrEnd2();
        }
    }

    private final int _skipWSOrEnd2() throws IOException {
        byte b3;
        while (true) {
            if (this._inputPtr >= this._inputEnd && !_loadMore()) {
                return _eofAsNextChar();
            }
            byte[] bArr = this._inputBuffer;
            int i3 = this._inputPtr;
            int i4 = i3 + 1;
            this._inputPtr = i4;
            b3 = bArr[i3] & 255;
            if (b3 > 32) {
                if (b3 == 47) {
                    _skipComment();
                } else if (b3 != 35 || !_skipYAMLComment()) {
                    return b3;
                }
            } else if (b3 != 32) {
                if (b3 == 10) {
                    this._currInputRow++;
                    this._currInputRowStart = i4;
                } else if (b3 == 13) {
                    _skipCR();
                } else if (b3 != 9) {
                    _throwInvalidSpace(b3);
                }
            }
        }
        return b3;
    }

    private final boolean _skipYAMLComment() throws IOException {
        if ((this._features & FEAT_MASK_ALLOW_YAML_COMMENTS) == 0) {
            return false;
        }
        _skipLine();
        return true;
    }

    private final void _updateLocation() {
        this._tokenInputRow = this._currInputRow;
        int i3 = this._inputPtr;
        this._tokenInputTotal = this._currInputProcessed + ((long) i3);
        this._tokenInputCol = i3 - this._currInputRowStart;
    }

    private final void _updateNameLocation() {
        this._nameStartRow = this._currInputRow;
        int i3 = this._inputPtr;
        this._nameStartOffset = i3;
        this._nameStartCol = i3 - this._currInputRowStart;
    }

    private final int _verifyNoLeadingZeroes() throws IOException {
        byte b3;
        if ((this._inputPtr >= this._inputEnd && !_loadMore()) || (b3 = this._inputBuffer[this._inputPtr] & 255) < 48 || b3 > 57) {
            return 48;
        }
        if ((this._features & FEAT_MASK_LEADING_ZEROS) == 0) {
            reportInvalidNumber("Leading zeroes not allowed");
        }
        this._inputPtr++;
        if (b3 == 48) {
            do {
                if (this._inputPtr >= this._inputEnd && !_loadMore()) {
                    break;
                }
                byte[] bArr = this._inputBuffer;
                int i3 = this._inputPtr;
                b3 = bArr[i3] & 255;
                if (b3 < 48 || b3 > 57) {
                    return 48;
                }
                this._inputPtr = i3 + 1;
            } while (b3 == 48);
        }
        return b3;
    }

    private final void _verifyRootSpace(int i3) throws IOException {
        int i4 = this._inputPtr;
        int i5 = i4 + 1;
        this._inputPtr = i5;
        if (i3 == 9) {
            return;
        }
        if (i3 == 10) {
            this._currInputRow++;
            this._currInputRowStart = i5;
        } else if (i3 == 13) {
            this._inputPtr = i4;
        } else if (i3 != 32) {
            _reportMissingRootWS(i3);
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:21:0x0060  */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x0079  */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x0084  */
    /* JADX WARNING: Removed duplicated region for block: B:39:0x00c3  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final java.lang.String addName(int[] r18, int r19, int r20) throws com.fasterxml.jackson.core.JsonParseException {
        /*
            r17 = this;
            r0 = r17
            r1 = r18
            r2 = r19
            r3 = r20
            int r4 = r2 << 2
            r5 = 4
            int r4 = r4 - r5
            int r4 = r4 + r3
            r7 = 3
            if (r3 >= r5) goto L_0x001c
            int r8 = r2 + -1
            r9 = r1[r8]
            int r10 = 4 - r3
            int r10 = r10 << r7
            int r10 = r9 << r10
            r1[r8] = r10
            goto L_0x001d
        L_0x001c:
            r9 = 0
        L_0x001d:
            com.fasterxml.jackson.core.util.TextBuffer r8 = r0._textBuffer
            char[] r8 = r8.emptyAndGetCurrentSegment()
            r10 = 0
            r11 = 0
        L_0x0025:
            if (r10 >= r4) goto L_0x00f6
            int r12 = r10 >> 2
            r12 = r1[r12]
            r13 = r10 & 3
            int r13 = 3 - r13
            int r13 = r13 << r7
            int r12 = r12 >> r13
            r13 = r12 & 255(0xff, float:3.57E-43)
            int r14 = r10 + 1
            r15 = 127(0x7f, float:1.78E-43)
            if (r13 <= r15) goto L_0x00e2
            r15 = r12 & 224(0xe0, float:3.14E-43)
            r5 = 192(0xc0, float:2.69E-43)
            if (r15 != r5) goto L_0x0043
            r5 = r12 & 31
        L_0x0041:
            r12 = 1
            goto L_0x005c
        L_0x0043:
            r5 = r12 & 240(0xf0, float:3.36E-43)
            r15 = 224(0xe0, float:3.14E-43)
            if (r5 != r15) goto L_0x004d
            r5 = r12 & 15
            r12 = 2
            goto L_0x005c
        L_0x004d:
            r5 = r12 & 248(0xf8, float:3.48E-43)
            r15 = 240(0xf0, float:3.36E-43)
            if (r5 != r15) goto L_0x0057
            r5 = r12 & 7
            r12 = r7
            goto L_0x005c
        L_0x0057:
            r0._reportInvalidInitial(r13)
            r5 = 1
            goto L_0x0041
        L_0x005c:
            int r13 = r14 + r12
            if (r13 <= r4) goto L_0x0067
            java.lang.String r13 = " in field name"
            com.fasterxml.jackson.core.JsonToken r15 = com.fasterxml.jackson.core.JsonToken.FIELD_NAME
            r0._reportInvalidEOF(r13, r15)
        L_0x0067:
            int r13 = r14 >> 2
            r13 = r1[r13]
            r14 = r14 & 3
            int r14 = 3 - r14
            int r14 = r14 << r7
            int r13 = r13 >> r14
            int r14 = r10 + 2
            r15 = r13 & 192(0xc0, float:2.69E-43)
            r6 = 128(0x80, float:1.794E-43)
            if (r15 == r6) goto L_0x007c
            r0._reportInvalidOther(r13)
        L_0x007c:
            int r5 = r5 << 6
            r13 = r13 & 63
            r5 = r5 | r13
            r13 = 1
            if (r12 <= r13) goto L_0x00b9
            int r13 = r14 >> 2
            r13 = r1[r13]
            r14 = r14 & 3
            int r14 = 3 - r14
            int r14 = r14 << r7
            int r13 = r13 >> r14
            int r14 = r10 + 3
            r15 = r13 & 192(0xc0, float:2.69E-43)
            if (r15 == r6) goto L_0x0097
            r0._reportInvalidOther(r13)
        L_0x0097:
            int r5 = r5 << 6
            r13 = r13 & 63
            r5 = r5 | r13
            r13 = 2
            if (r12 <= r13) goto L_0x00bc
            int r13 = r14 >> 2
            r13 = r1[r13]
            r14 = r14 & 3
            int r14 = 3 - r14
            int r14 = r14 << r7
            int r13 = r13 >> r14
            int r14 = r10 + 4
            r10 = r13 & 192(0xc0, float:2.69E-43)
            if (r10 == r6) goto L_0x00b4
            r6 = r13 & 255(0xff, float:3.57E-43)
            r0._reportInvalidOther(r6)
        L_0x00b4:
            int r5 = r5 << 6
            r6 = r13 & 63
            r5 = r5 | r6
        L_0x00b9:
            r13 = r5
            r5 = 2
            goto L_0x00c1
        L_0x00bc:
            r16 = r13
            r13 = r5
            r5 = r16
        L_0x00c1:
            if (r12 <= r5) goto L_0x00e2
            r5 = 65536(0x10000, float:9.18355E-41)
            int r13 = r13 - r5
            int r5 = r8.length
            if (r11 < r5) goto L_0x00cf
            com.fasterxml.jackson.core.util.TextBuffer r5 = r0._textBuffer
            char[] r8 = r5.expandCurrentSegment()
        L_0x00cf:
            int r5 = r11 + 1
            int r6 = r13 >> 10
            r10 = 55296(0xd800, float:7.7486E-41)
            int r6 = r6 + r10
            char r6 = (char) r6
            r8[r11] = r6
            r6 = r13 & 1023(0x3ff, float:1.434E-42)
            r10 = 56320(0xdc00, float:7.8921E-41)
            r13 = r6 | r10
            r11 = r5
        L_0x00e2:
            r10 = r14
            int r5 = r8.length
            if (r11 < r5) goto L_0x00ed
            com.fasterxml.jackson.core.util.TextBuffer r5 = r0._textBuffer
            char[] r5 = r5.expandCurrentSegment()
            r8 = r5
        L_0x00ed:
            int r5 = r11 + 1
            char r6 = (char) r13
            r8[r11] = r6
            r11 = r5
            r5 = 4
            goto L_0x0025
        L_0x00f6:
            java.lang.String r4 = new java.lang.String
            r5 = 0
            r4.<init>(r8, r5, r11)
            r5 = 4
            if (r3 >= r5) goto L_0x0103
            int r3 = r2 + -1
            r1[r3] = r9
        L_0x0103:
            com.fasterxml.jackson.core.sym.ByteQuadsCanonicalizer r0 = r0._symbols
            java.lang.String r0 = r0.addName((java.lang.String) r4, (int[]) r1, (int) r2)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.fasterxml.jackson.core.json.UTF8StreamJsonParser.addName(int[], int, int):java.lang.String");
    }

    private final String findName(int i3, int i4) throws JsonParseException {
        int _padLastQuad = _padLastQuad(i3, i4);
        String findName = this._symbols.findName(_padLastQuad);
        if (findName != null) {
            return findName;
        }
        int[] iArr = this._quadBuffer;
        iArr[0] = _padLastQuad;
        return addName(iArr, 1, i4);
    }

    private int nextByte() throws IOException {
        if (this._inputPtr >= this._inputEnd) {
            _loadMoreGuaranteed();
        }
        byte[] bArr = this._inputBuffer;
        int i3 = this._inputPtr;
        this._inputPtr = i3 + 1;
        return bArr[i3] & 255;
    }

    private final String parseName(int i3, int i4, int i5) throws IOException {
        return parseEscapedName(this._quadBuffer, 0, i3, i4, i5);
    }

    public void _closeInput() throws IOException {
        if (this._inputStream != null) {
            if (this._ioContext.isResourceManaged() || isEnabled(JsonParser.Feature.AUTO_CLOSE_SOURCE)) {
                this._inputStream.close();
            }
            this._inputStream = null;
        }
    }

    public final byte[] _decodeBase64(Base64Variant base64Variant) throws IOException {
        ByteArrayBuilder _getByteArrayBuilder = _getByteArrayBuilder();
        while (true) {
            if (this._inputPtr >= this._inputEnd) {
                _loadMoreGuaranteed();
            }
            byte[] bArr = this._inputBuffer;
            int i3 = this._inputPtr;
            this._inputPtr = i3 + 1;
            byte b3 = bArr[i3] & 255;
            if (b3 > 32) {
                int decodeBase64Char = base64Variant.decodeBase64Char((int) b3);
                if (decodeBase64Char < 0) {
                    if (b3 == 34) {
                        return _getByteArrayBuilder.toByteArray();
                    }
                    decodeBase64Char = _decodeBase64Escape(base64Variant, (int) b3, 0);
                    if (decodeBase64Char < 0) {
                        continue;
                    }
                }
                if (this._inputPtr >= this._inputEnd) {
                    _loadMoreGuaranteed();
                }
                byte[] bArr2 = this._inputBuffer;
                int i4 = this._inputPtr;
                this._inputPtr = i4 + 1;
                byte b4 = bArr2[i4] & 255;
                int decodeBase64Char2 = base64Variant.decodeBase64Char((int) b4);
                if (decodeBase64Char2 < 0) {
                    decodeBase64Char2 = _decodeBase64Escape(base64Variant, (int) b4, 1);
                }
                int i5 = (decodeBase64Char << 6) | decodeBase64Char2;
                if (this._inputPtr >= this._inputEnd) {
                    _loadMoreGuaranteed();
                }
                byte[] bArr3 = this._inputBuffer;
                int i6 = this._inputPtr;
                this._inputPtr = i6 + 1;
                byte b5 = bArr3[i6] & 255;
                int decodeBase64Char3 = base64Variant.decodeBase64Char((int) b5);
                if (decodeBase64Char3 < 0) {
                    if (decodeBase64Char3 != -2) {
                        if (b5 == 34) {
                            _getByteArrayBuilder.append(i5 >> 4);
                            if (base64Variant.usesPadding()) {
                                this._inputPtr--;
                                _handleBase64MissingPadding(base64Variant);
                            }
                            return _getByteArrayBuilder.toByteArray();
                        }
                        decodeBase64Char3 = _decodeBase64Escape(base64Variant, (int) b5, 2);
                    }
                    if (decodeBase64Char3 == -2) {
                        if (this._inputPtr >= this._inputEnd) {
                            _loadMoreGuaranteed();
                        }
                        byte[] bArr4 = this._inputBuffer;
                        int i7 = this._inputPtr;
                        this._inputPtr = i7 + 1;
                        byte b6 = bArr4[i7] & 255;
                        if (base64Variant.usesPaddingChar((int) b6) || _decodeBase64Escape(base64Variant, (int) b6, 3) == -2) {
                            _getByteArrayBuilder.append(i5 >> 4);
                        } else {
                            throw reportInvalidBase64Char(base64Variant, b6, 3, "expected padding character '" + base64Variant.getPaddingChar() + "'");
                        }
                    }
                }
                int i8 = (i5 << 6) | decodeBase64Char3;
                if (this._inputPtr >= this._inputEnd) {
                    _loadMoreGuaranteed();
                }
                byte[] bArr5 = this._inputBuffer;
                int i9 = this._inputPtr;
                this._inputPtr = i9 + 1;
                byte b7 = bArr5[i9] & 255;
                int decodeBase64Char4 = base64Variant.decodeBase64Char((int) b7);
                if (decodeBase64Char4 < 0) {
                    if (decodeBase64Char4 != -2) {
                        if (b7 == 34) {
                            _getByteArrayBuilder.appendTwoBytes(i8 >> 2);
                            if (base64Variant.usesPadding()) {
                                this._inputPtr--;
                                _handleBase64MissingPadding(base64Variant);
                            }
                            return _getByteArrayBuilder.toByteArray();
                        }
                        decodeBase64Char4 = _decodeBase64Escape(base64Variant, (int) b7, 3);
                    }
                    if (decodeBase64Char4 == -2) {
                        _getByteArrayBuilder.appendTwoBytes(i8 >> 2);
                    }
                }
                _getByteArrayBuilder.appendThreeBytes((i8 << 6) | decodeBase64Char4);
            }
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:15:0x0036  */
    /* JADX WARNING: Removed duplicated region for block: B:18:0x0042  */
    /* JADX WARNING: Removed duplicated region for block: B:29:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public int _decodeCharForError(int r7) throws java.io.IOException {
        /*
            r6 = this;
            r0 = r7 & 255(0xff, float:3.57E-43)
            r1 = 127(0x7f, float:1.78E-43)
            if (r0 <= r1) goto L_0x0069
            r1 = r7 & 224(0xe0, float:3.14E-43)
            r2 = 2
            r3 = 1
            r4 = 192(0xc0, float:2.69E-43)
            if (r1 != r4) goto L_0x0012
            r0 = r7 & 31
        L_0x0010:
            r7 = r3
            goto L_0x002c
        L_0x0012:
            r1 = r7 & 240(0xf0, float:3.36E-43)
            r4 = 224(0xe0, float:3.14E-43)
            if (r1 != r4) goto L_0x001c
            r0 = r7 & 15
            r7 = r2
            goto L_0x002c
        L_0x001c:
            r1 = r7 & 248(0xf8, float:3.48E-43)
            r4 = 240(0xf0, float:3.36E-43)
            if (r1 != r4) goto L_0x0026
            r0 = r7 & 7
            r7 = 3
            goto L_0x002c
        L_0x0026:
            r7 = r7 & 255(0xff, float:3.57E-43)
            r6._reportInvalidInitial(r7)
            goto L_0x0010
        L_0x002c:
            int r1 = r6.nextByte()
            r4 = r1 & 192(0xc0, float:2.69E-43)
            r5 = 128(0x80, float:1.794E-43)
            if (r4 == r5) goto L_0x003b
            r4 = r1 & 255(0xff, float:3.57E-43)
            r6._reportInvalidOther(r4)
        L_0x003b:
            int r0 = r0 << 6
            r1 = r1 & 63
            r0 = r0 | r1
            if (r7 <= r3) goto L_0x0069
            int r1 = r6.nextByte()
            r3 = r1 & 192(0xc0, float:2.69E-43)
            if (r3 == r5) goto L_0x004f
            r3 = r1 & 255(0xff, float:3.57E-43)
            r6._reportInvalidOther(r3)
        L_0x004f:
            int r0 = r0 << 6
            r1 = r1 & 63
            r0 = r0 | r1
            if (r7 <= r2) goto L_0x0069
            int r7 = r6.nextByte()
            r1 = r7 & 192(0xc0, float:2.69E-43)
            if (r1 == r5) goto L_0x0063
            r1 = r7 & 255(0xff, float:3.57E-43)
            r6._reportInvalidOther(r1)
        L_0x0063:
            int r6 = r0 << 6
            r7 = r7 & 63
            r0 = r6 | r7
        L_0x0069:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.fasterxml.jackson.core.json.UTF8StreamJsonParser._decodeCharForError(int):int");
    }

    public char _decodeEscaped() throws IOException {
        if (this._inputPtr >= this._inputEnd && !_loadMore()) {
            _reportInvalidEOF(" in character escape sequence", JsonToken.VALUE_STRING);
        }
        byte[] bArr = this._inputBuffer;
        int i3 = this._inputPtr;
        this._inputPtr = i3 + 1;
        byte b3 = bArr[i3];
        if (b3 == 34 || b3 == 47 || b3 == 92) {
            return (char) b3;
        }
        if (b3 == 98) {
            return 8;
        }
        if (b3 == 102) {
            return 12;
        }
        if (b3 == 110) {
            return 10;
        }
        if (b3 == 114) {
            return CharUtils.CR;
        }
        if (b3 == 116) {
            return 9;
        }
        if (b3 != 117) {
            return _handleUnrecognizedCharacterEscape((char) _decodeCharForError(b3));
        }
        int i4 = 0;
        for (int i5 = 0; i5 < 4; i5++) {
            if (this._inputPtr >= this._inputEnd && !_loadMore()) {
                _reportInvalidEOF(" in character escape sequence", JsonToken.VALUE_STRING);
            }
            byte[] bArr2 = this._inputBuffer;
            int i6 = this._inputPtr;
            this._inputPtr = i6 + 1;
            byte b4 = bArr2[i6];
            int charToHex = CharTypes.charToHex(b4);
            if (charToHex < 0) {
                _reportUnexpectedChar(b4 & 255, "expected a hex-digit for character escape sequence");
            }
            i4 = (i4 << 4) | charToHex;
        }
        return (char) i4;
    }

    public String _finishAndReturnString() throws IOException {
        int i3 = this._inputPtr;
        if (i3 >= this._inputEnd) {
            _loadMoreGuaranteed();
            i3 = this._inputPtr;
        }
        char[] emptyAndGetCurrentSegment = this._textBuffer.emptyAndGetCurrentSegment();
        int[] iArr = _icUTF8;
        int min = Math.min(this._inputEnd, emptyAndGetCurrentSegment.length + i3);
        byte[] bArr = this._inputBuffer;
        int i4 = 0;
        while (true) {
            if (i3 >= min) {
                break;
            }
            byte b3 = bArr[i3] & 255;
            if (iArr[b3] == 0) {
                i3++;
                emptyAndGetCurrentSegment[i4] = (char) b3;
                i4++;
            } else if (b3 == 34) {
                this._inputPtr = i3 + 1;
                return this._textBuffer.setCurrentAndReturn(i4);
            }
        }
        this._inputPtr = i3;
        _finishString2(emptyAndGetCurrentSegment, i4);
        return this._textBuffer.contentsAsString();
    }

    public void _finishString() throws IOException {
        int i3 = this._inputPtr;
        if (i3 >= this._inputEnd) {
            _loadMoreGuaranteed();
            i3 = this._inputPtr;
        }
        char[] emptyAndGetCurrentSegment = this._textBuffer.emptyAndGetCurrentSegment();
        int[] iArr = _icUTF8;
        int min = Math.min(this._inputEnd, emptyAndGetCurrentSegment.length + i3);
        byte[] bArr = this._inputBuffer;
        int i4 = 0;
        while (true) {
            if (i3 >= min) {
                break;
            }
            byte b3 = bArr[i3] & 255;
            if (iArr[b3] == 0) {
                i3++;
                emptyAndGetCurrentSegment[i4] = (char) b3;
                i4++;
            } else if (b3 == 34) {
                this._inputPtr = i3 + 1;
                this._textBuffer.setCurrentLength(i4);
                return;
            }
        }
        this._inputPtr = i3;
        _finishString2(emptyAndGetCurrentSegment, i4);
    }

    public final String _getText2(JsonToken jsonToken) {
        if (jsonToken == null) {
            return null;
        }
        int id = jsonToken.id();
        return id != 5 ? (id == 6 || id == 7 || id == 8) ? this._textBuffer.contentsAsString() : jsonToken.asString() : this._parsingContext.getCurrentName();
    }

    public JsonToken _handleApos() throws IOException {
        char[] emptyAndGetCurrentSegment = this._textBuffer.emptyAndGetCurrentSegment();
        int[] iArr = _icUTF8;
        byte[] bArr = this._inputBuffer;
        int i3 = 0;
        while (true) {
            if (this._inputPtr >= this._inputEnd) {
                _loadMoreGuaranteed();
            }
            if (i3 >= emptyAndGetCurrentSegment.length) {
                emptyAndGetCurrentSegment = this._textBuffer.finishCurrentSegment();
                i3 = 0;
            }
            int i4 = this._inputEnd;
            int length = (emptyAndGetCurrentSegment.length - i3) + this._inputPtr;
            if (length < i4) {
                i4 = length;
            }
            while (true) {
                int i5 = this._inputPtr;
                if (i5 >= i4) {
                    break;
                }
                int i6 = i5 + 1;
                this._inputPtr = i6;
                int i7 = bArr[i5] & 255;
                if (i7 == 39) {
                    this._textBuffer.setCurrentLength(i3);
                    return JsonToken.VALUE_STRING;
                }
                int i8 = iArr[i7];
                if (i8 == 0 || i7 == 34) {
                    emptyAndGetCurrentSegment[i3] = (char) i7;
                    i3++;
                } else {
                    if (i8 == 1) {
                        i7 = _decodeEscaped();
                    } else if (i8 == 2) {
                        i7 = _decodeUtf8_2(i7);
                    } else if (i8 == 3) {
                        i7 = this._inputEnd - i6 >= 2 ? _decodeUtf8_3fast(i7) : _decodeUtf8_3(i7);
                    } else if (i8 != 4) {
                        if (i7 < 32) {
                            _throwUnquotedSpace(i7, "string value");
                        }
                        _reportInvalidChar(i7);
                    } else {
                        int _decodeUtf8_4 = _decodeUtf8_4(i7);
                        int i9 = i3 + 1;
                        emptyAndGetCurrentSegment[i3] = (char) ((_decodeUtf8_4 >> 10) | GeneratorBase.SURR1_FIRST);
                        if (i9 >= emptyAndGetCurrentSegment.length) {
                            emptyAndGetCurrentSegment = this._textBuffer.finishCurrentSegment();
                            i3 = 0;
                        } else {
                            i3 = i9;
                        }
                        i7 = 56320 | (_decodeUtf8_4 & 1023);
                    }
                    if (i3 >= emptyAndGetCurrentSegment.length) {
                        emptyAndGetCurrentSegment = this._textBuffer.finishCurrentSegment();
                        i3 = 0;
                    }
                    emptyAndGetCurrentSegment[i3] = (char) i7;
                    i3++;
                }
            }
        }
    }

    public JsonToken _handleInvalidNumberStart(int i3, boolean z2) throws IOException {
        return _handleInvalidNumberStart(i3, z2, false);
    }

    public String _handleOddName(int i3) throws IOException {
        if (i3 == 39 && (this._features & FEAT_MASK_ALLOW_SINGLE_QUOTES) != 0) {
            return _parseAposName();
        }
        if ((this._features & FEAT_MASK_ALLOW_UNQUOTED_NAMES) == 0) {
            _reportUnexpectedChar((char) _decodeCharForError(i3), "was expecting double-quote to start field name");
        }
        int[] inputCodeUtf8JsNames = CharTypes.getInputCodeUtf8JsNames();
        if (inputCodeUtf8JsNames[i3] != 0) {
            _reportUnexpectedChar(i3, "was expecting either valid name character (for unquoted name) or double-quote (for quoted) to start field name");
        }
        int[] iArr = this._quadBuffer;
        int i4 = 0;
        int i5 = 0;
        int i6 = 0;
        while (true) {
            if (i4 < 4) {
                i4++;
                i6 = i3 | (i6 << 8);
            } else {
                if (i5 >= iArr.length) {
                    iArr = ParserBase.growArrayBy(iArr, iArr.length);
                    this._quadBuffer = iArr;
                }
                iArr[i5] = i6;
                i6 = i3;
                i5++;
                i4 = 1;
            }
            if (this._inputPtr >= this._inputEnd && !_loadMore()) {
                _reportInvalidEOF(" in field name", JsonToken.FIELD_NAME);
            }
            byte[] bArr = this._inputBuffer;
            int i7 = this._inputPtr;
            i3 = bArr[i7] & 255;
            if (inputCodeUtf8JsNames[i3] != 0) {
                break;
            }
            this._inputPtr = i7 + 1;
        }
        if (i4 > 0) {
            if (i5 >= iArr.length) {
                iArr = ParserBase.growArrayBy(iArr, iArr.length);
                this._quadBuffer = iArr;
            }
            iArr[i5] = i6;
            i5++;
        }
        String findName = this._symbols.findName(iArr, i5);
        return findName == null ? addName(iArr, i5, i4) : findName;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:13:0x001b, code lost:
        if (r4 != 44) goto L_0x00a6;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x0048, code lost:
        if (r3._parsingContext.inArray() == false) goto L_0x00a6;
     */
    /* JADX WARNING: Removed duplicated region for block: B:46:0x00ac  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.fasterxml.jackson.core.JsonToken _handleUnexpectedValue(int r4) throws java.io.IOException {
        /*
            r3 = this;
            r0 = 39
            if (r4 == r0) goto L_0x009a
            r0 = 73
            r1 = 1
            if (r4 == r0) goto L_0x0081
            r0 = 78
            if (r4 == r0) goto L_0x0068
            r0 = 93
            if (r4 == r0) goto L_0x0042
            r0 = 125(0x7d, float:1.75E-43)
            if (r4 == r0) goto L_0x0062
            r0 = 43
            if (r4 == r0) goto L_0x001f
            r0 = 44
            if (r4 == r0) goto L_0x004b
            goto L_0x00a6
        L_0x001f:
            int r4 = r3._inputPtr
            int r0 = r3._inputEnd
            if (r4 < r0) goto L_0x0030
            boolean r4 = r3._loadMore()
            if (r4 != 0) goto L_0x0030
            com.fasterxml.jackson.core.JsonToken r4 = com.fasterxml.jackson.core.JsonToken.VALUE_NUMBER_INT
            r3._reportInvalidEOFInValue(r4)
        L_0x0030:
            byte[] r4 = r3._inputBuffer
            int r0 = r3._inputPtr
            int r2 = r0 + 1
            r3._inputPtr = r2
            byte r4 = r4[r0]
            r4 = r4 & 255(0xff, float:3.57E-43)
            r0 = 0
            com.fasterxml.jackson.core.JsonToken r3 = r3._handleInvalidNumberStart(r4, r0, r1)
            return r3
        L_0x0042:
            com.fasterxml.jackson.core.json.JsonReadContext r0 = r3._parsingContext
            boolean r0 = r0.inArray()
            if (r0 != 0) goto L_0x004b
            goto L_0x00a6
        L_0x004b:
            com.fasterxml.jackson.core.json.JsonReadContext r0 = r3._parsingContext
            boolean r0 = r0.inRoot()
            if (r0 != 0) goto L_0x0062
            int r0 = r3._features
            int r2 = FEAT_MASK_ALLOW_MISSING
            r0 = r0 & r2
            if (r0 == 0) goto L_0x0062
            int r4 = r3._inputPtr
            int r4 = r4 - r1
            r3._inputPtr = r4
            com.fasterxml.jackson.core.JsonToken r3 = com.fasterxml.jackson.core.JsonToken.VALUE_NULL
            return r3
        L_0x0062:
            java.lang.String r0 = "expected a value"
            r3._reportUnexpectedChar(r4, r0)
            goto L_0x009a
        L_0x0068:
            java.lang.String r0 = "NaN"
            r3._matchToken(r0, r1)
            int r1 = r3._features
            int r2 = FEAT_MASK_NON_NUM_NUMBERS
            r1 = r1 & r2
            if (r1 == 0) goto L_0x007b
            r1 = 9221120237041090560(0x7ff8000000000000, double:NaN)
            com.fasterxml.jackson.core.JsonToken r3 = r3.resetAsNaN(r0, r1)
            return r3
        L_0x007b:
            java.lang.String r0 = "Non-standard token 'NaN': enable `JsonReadFeature.ALLOW_NON_NUMERIC_NUMBERS` to allow"
            r3._reportError(r0)
            goto L_0x00a6
        L_0x0081:
            java.lang.String r0 = "Infinity"
            r3._matchToken(r0, r1)
            int r1 = r3._features
            int r2 = FEAT_MASK_NON_NUM_NUMBERS
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0094
            r1 = 9218868437227405312(0x7ff0000000000000, double:Infinity)
            com.fasterxml.jackson.core.JsonToken r3 = r3.resetAsNaN(r0, r1)
            return r3
        L_0x0094:
            java.lang.String r0 = "Non-standard token 'Infinity': enable `JsonReadFeature.ALLOW_NON_NUMERIC_NUMBERS` to allow"
            r3._reportError(r0)
            goto L_0x00a6
        L_0x009a:
            int r0 = r3._features
            int r1 = FEAT_MASK_ALLOW_SINGLE_QUOTES
            r0 = r0 & r1
            if (r0 == 0) goto L_0x00a6
            com.fasterxml.jackson.core.JsonToken r3 = r3._handleApos()
            return r3
        L_0x00a6:
            boolean r0 = java.lang.Character.isJavaIdentifierStart(r4)
            if (r0 == 0) goto L_0x00c2
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            java.lang.String r1 = ""
            r0.<init>(r1)
            char r1 = (char) r4
            r0.append(r1)
            java.lang.String r0 = r0.toString()
            java.lang.String r1 = r3._validJsonTokenList()
            r3._reportInvalidToken((java.lang.String) r0, (java.lang.String) r1)
        L_0x00c2:
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            java.lang.String r1 = "expected a valid value "
            r0.<init>(r1)
            java.lang.String r1 = r3._validJsonValueList()
            r0.append(r1)
            java.lang.String r0 = r0.toString()
            r3._reportUnexpectedChar(r4, r0)
            r3 = 0
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.fasterxml.jackson.core.json.UTF8StreamJsonParser._handleUnexpectedValue(int):com.fasterxml.jackson.core.JsonToken");
    }

    public final boolean _loadMore() throws IOException {
        byte[] bArr;
        int length;
        InputStream inputStream = this._inputStream;
        if (inputStream == null || (length = bArr.length) == 0) {
            return false;
        }
        int read = inputStream.read((bArr = this._inputBuffer), 0, length);
        if (read > 0) {
            int i3 = this._inputEnd;
            this._currInputProcessed += (long) i3;
            this._currInputRowStart -= i3;
            this._nameStartOffset -= i3;
            this._inputPtr = 0;
            this._inputEnd = read;
            return true;
        }
        _closeInput();
        if (read == 0) {
            throw new IOException(a.m(new StringBuilder("InputStream.read() returned 0 characters when trying to read "), " bytes", this._inputBuffer.length));
        }
        return false;
    }

    public void _loadMoreGuaranteed() throws IOException {
        if (!_loadMore()) {
            _reportInvalidEOF();
        }
    }

    public final void _matchFalse() throws IOException {
        byte b3;
        int i3 = this._inputPtr;
        if (i3 + 4 < this._inputEnd) {
            byte[] bArr = this._inputBuffer;
            int i4 = i3 + 1;
            if (bArr[i3] == 97) {
                int i5 = i3 + 2;
                if (bArr[i4] == 108) {
                    int i6 = i3 + 3;
                    if (bArr[i5] == 115) {
                        int i7 = i3 + 4;
                        if (bArr[i6] == 101 && ((b3 = bArr[i7] & 255) < 48 || b3 == 93 || b3 == 125)) {
                            this._inputPtr = i7;
                            return;
                        }
                    }
                }
            }
        }
        _matchToken2("false", 1);
    }

    public final void _matchNull() throws IOException {
        byte b3;
        int i3 = this._inputPtr;
        if (i3 + 3 < this._inputEnd) {
            byte[] bArr = this._inputBuffer;
            int i4 = i3 + 1;
            if (bArr[i3] == 117) {
                int i5 = i3 + 2;
                if (bArr[i4] == 108) {
                    int i6 = i3 + 3;
                    if (bArr[i5] == 108 && ((b3 = bArr[i6] & 255) < 48 || b3 == 93 || b3 == 125)) {
                        this._inputPtr = i6;
                        return;
                    }
                }
            }
        }
        _matchToken2(AbstractJsonLexerKt.NULL, 1);
    }

    public final void _matchToken(String str, int i3) throws IOException {
        int i4;
        int length = str.length();
        if (this._inputPtr + length >= this._inputEnd) {
            _matchToken2(str, i3);
            return;
        }
        do {
            if (this._inputBuffer[this._inputPtr] != str.charAt(i3)) {
                _reportInvalidToken(str.substring(0, i3));
            }
            i4 = this._inputPtr + 1;
            this._inputPtr = i4;
            i3++;
        } while (i3 < length);
        byte b3 = this._inputBuffer[i4] & 255;
        if (b3 >= 48 && b3 != 93 && b3 != 125) {
            _checkMatchEnd(str, i3, b3);
        }
    }

    public final void _matchTrue() throws IOException {
        byte b3;
        int i3 = this._inputPtr;
        if (i3 + 3 < this._inputEnd) {
            byte[] bArr = this._inputBuffer;
            int i4 = i3 + 1;
            if (bArr[i3] == 114) {
                int i5 = i3 + 2;
                if (bArr[i4] == 117) {
                    int i6 = i3 + 3;
                    if (bArr[i5] == 101 && ((b3 = bArr[i6] & 255) < 48 || b3 == 93 || b3 == 125)) {
                        this._inputPtr = i6;
                        return;
                    }
                }
            }
        }
        _matchToken2("true", 1);
    }

    public String _parseAposName() throws IOException {
        int i3;
        int i4;
        if (this._inputPtr >= this._inputEnd && !_loadMore()) {
            _reportInvalidEOF(": was expecting closing ''' for field name", JsonToken.FIELD_NAME);
        }
        byte[] bArr = this._inputBuffer;
        int i5 = this._inputPtr;
        this._inputPtr = i5 + 1;
        char c3 = bArr[i5] & 255;
        if (c3 == '\'') {
            return "";
        }
        int[] iArr = this._quadBuffer;
        int[] iArr2 = _icLatin1;
        int i6 = 0;
        int i7 = 0;
        int i8 = 0;
        while (c3 != '\'') {
            if (!(iArr2[c3] == 0 || c3 == '\"')) {
                if (c3 != '\\') {
                    _throwUnquotedSpace(c3, "name");
                } else {
                    c3 = _decodeEscaped();
                }
                if (c3 > 127) {
                    if (i4 >= 4) {
                        if (i7 >= iArr.length) {
                            iArr = ParserBase.growArrayBy(iArr, iArr.length);
                            this._quadBuffer = iArr;
                        }
                        iArr[i7] = i3;
                        i3 = 0;
                        i7++;
                        i4 = 0;
                    }
                    if (c3 < 2048) {
                        i3 = (i3 << 8) | (c3 >> 6) | 192;
                        i4++;
                    } else {
                        int i9 = (i3 << 8) | (c3 >> 12) | 224;
                        int i10 = i4 + 1;
                        if (i10 >= 4) {
                            if (i7 >= iArr.length) {
                                iArr = ParserBase.growArrayBy(iArr, iArr.length);
                                this._quadBuffer = iArr;
                            }
                            iArr[i7] = i9;
                            i9 = 0;
                            i7++;
                            i10 = 0;
                        }
                        i3 = (i9 << 8) | ((c3 >> 6) & 63) | 128;
                        i4 = i10 + 1;
                    }
                    c3 = (c3 & '?') | 128;
                }
            }
            if (i4 < 4) {
                i6 = i4 + 1;
                i8 = c3 | (i3 << 8);
            } else {
                if (i7 >= iArr.length) {
                    iArr = ParserBase.growArrayBy(iArr, iArr.length);
                    this._quadBuffer = iArr;
                }
                iArr[i7] = i3;
                i8 = c3;
                i7++;
                i6 = 1;
            }
            if (this._inputPtr >= this._inputEnd && !_loadMore()) {
                _reportInvalidEOF(" in field name", JsonToken.FIELD_NAME);
            }
            byte[] bArr2 = this._inputBuffer;
            int i11 = this._inputPtr;
            this._inputPtr = i11 + 1;
            c3 = bArr2[i11] & 255;
        }
        if (i4 > 0) {
            if (i7 >= iArr.length) {
                iArr = ParserBase.growArrayBy(iArr, iArr.length);
                this._quadBuffer = iArr;
            }
            iArr[i7] = _padLastQuad(i3, i4);
            i7++;
        }
        String findName = this._symbols.findName(iArr, i7);
        return findName == null ? addName(iArr, i7, i4) : findName;
    }

    @Deprecated
    public final JsonToken _parseFloatThatStartsWithPeriod() throws IOException {
        return _parseFloatThatStartsWithPeriod(false, false);
    }

    public final String _parseName(int i3) throws IOException {
        if (i3 != 34) {
            return _handleOddName(i3);
        }
        int i4 = this._inputPtr;
        if (i4 + 13 > this._inputEnd) {
            return slowParseName();
        }
        byte[] bArr = this._inputBuffer;
        int[] iArr = _icLatin1;
        int i5 = i4 + 1;
        this._inputPtr = i5;
        byte b3 = bArr[i4] & 255;
        if (iArr[b3] != 0) {
            return b3 == 34 ? "" : parseName(0, b3, 0);
        }
        int i6 = i4 + 2;
        this._inputPtr = i6;
        byte b4 = bArr[i5] & 255;
        if (iArr[b4] != 0) {
            return b4 == 34 ? findName(b3, 1) : parseName(b3, b4, 1);
        }
        byte b5 = b4 | (b3 << 8);
        int i7 = i4 + 3;
        this._inputPtr = i7;
        byte b6 = bArr[i6] & 255;
        if (iArr[b6] != 0) {
            return b6 == 34 ? findName(b5, 2) : parseName(b5, b6, 2);
        }
        byte b7 = (b5 << 8) | b6;
        int i8 = i4 + 4;
        this._inputPtr = i8;
        byte b8 = bArr[i7] & 255;
        if (iArr[b8] != 0) {
            return b8 == 34 ? findName(b7, 3) : parseName(b7, b8, 3);
        }
        byte b9 = (b7 << 8) | b8;
        this._inputPtr = i4 + 5;
        byte b10 = bArr[i8] & 255;
        if (iArr[b10] != 0) {
            return b10 == 34 ? findName(b9, 4) : parseName(b9, b10, 4);
        }
        this._quad1 = b9;
        return parseMediumName(b10);
    }

    public JsonToken _parseUnsignedNumber(int i3) throws IOException {
        int i4;
        byte b3;
        char[] emptyAndGetCurrentSegment = this._textBuffer.emptyAndGetCurrentSegment();
        if (i3 == 48) {
            i3 = _verifyNoLeadingZeroes();
        }
        emptyAndGetCurrentSegment[0] = (char) i3;
        int min = Math.min(this._inputEnd, (this._inputPtr + emptyAndGetCurrentSegment.length) - 1);
        int i5 = 1;
        int i6 = 1;
        while (true) {
            i4 = this._inputPtr;
            if (i4 >= min) {
                return _parseNumber2(emptyAndGetCurrentSegment, i5, false, i6);
            }
            byte[] bArr = this._inputBuffer;
            this._inputPtr = i4 + 1;
            b3 = bArr[i4] & 255;
            if (b3 >= 48 && b3 <= 57) {
                i6++;
                emptyAndGetCurrentSegment[i5] = (char) b3;
                i5++;
            }
        }
        if (b3 == 46 || b3 == 101 || b3 == 69) {
            return _parseFloat(emptyAndGetCurrentSegment, i5, b3, false, i6);
        }
        this._inputPtr = i4;
        this._textBuffer.setCurrentLength(i5);
        if (this._parsingContext.inRoot()) {
            _verifyRootSpace(b3);
        }
        return resetInt(false, i6);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x0037, code lost:
        if (r10 < 0) goto L_0x0039;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public int _readBinary(com.fasterxml.jackson.core.Base64Variant r17, java.io.OutputStream r18, byte[] r19) throws java.io.IOException {
        /*
            r16 = this;
            r0 = r16
            r1 = r17
            r2 = r18
            r3 = r19
            int r4 = r3.length
            r5 = 3
            int r4 = r4 - r5
            r6 = 0
            r7 = r6
            r8 = r7
        L_0x000e:
            int r9 = r0._inputPtr
            int r10 = r0._inputEnd
            if (r9 < r10) goto L_0x0017
            r16._loadMoreGuaranteed()
        L_0x0017:
            byte[] r9 = r0._inputBuffer
            int r10 = r0._inputPtr
            int r11 = r10 + 1
            r0._inputPtr = r11
            byte r9 = r9[r10]
            r9 = r9 & 255(0xff, float:3.57E-43)
            r10 = 32
            if (r9 <= r10) goto L_0x0039
            int r10 = r1.decodeBase64Char((int) r9)
            r11 = 34
            if (r10 >= 0) goto L_0x003c
            if (r9 != r11) goto L_0x0033
            goto L_0x012a
        L_0x0033:
            int r10 = r0._decodeBase64Escape((com.fasterxml.jackson.core.Base64Variant) r1, (int) r9, (int) r6)
            if (r10 >= 0) goto L_0x003c
        L_0x0039:
            r11 = r5
            goto L_0x014a
        L_0x003c:
            if (r7 <= r4) goto L_0x0043
            int r8 = r8 + r7
            r2.write(r3, r6, r7)
            r7 = r6
        L_0x0043:
            int r9 = r0._inputPtr
            int r12 = r0._inputEnd
            if (r9 < r12) goto L_0x004c
            r16._loadMoreGuaranteed()
        L_0x004c:
            byte[] r9 = r0._inputBuffer
            int r12 = r0._inputPtr
            int r13 = r12 + 1
            r0._inputPtr = r13
            byte r9 = r9[r12]
            r9 = r9 & 255(0xff, float:3.57E-43)
            int r12 = r1.decodeBase64Char((int) r9)
            r13 = 1
            if (r12 >= 0) goto L_0x0063
            int r12 = r0._decodeBase64Escape((com.fasterxml.jackson.core.Base64Variant) r1, (int) r9, (int) r13)
        L_0x0063:
            int r9 = r10 << 6
            r9 = r9 | r12
            int r10 = r0._inputPtr
            int r12 = r0._inputEnd
            if (r10 < r12) goto L_0x006f
            r16._loadMoreGuaranteed()
        L_0x006f:
            byte[] r10 = r0._inputBuffer
            int r12 = r0._inputPtr
            int r14 = r12 + 1
            r0._inputPtr = r14
            byte r10 = r10[r12]
            r10 = r10 & 255(0xff, float:3.57E-43)
            int r12 = r1.decodeBase64Char((int) r10)
            r14 = -2
            r15 = 2
            if (r12 >= 0) goto L_0x00ed
            if (r12 == r14) goto L_0x00a3
            if (r10 != r11) goto L_0x009f
            int r4 = r9 >> 4
            int r5 = r7 + 1
            byte r4 = (byte) r4
            r3[r7] = r4
            boolean r4 = r17.usesPadding()
            if (r4 == 0) goto L_0x009c
            int r4 = r0._inputPtr
            int r4 = r4 - r13
            r0._inputPtr = r4
            r16._handleBase64MissingPadding(r17)
        L_0x009c:
            r7 = r5
            goto L_0x012a
        L_0x009f:
            int r12 = r0._decodeBase64Escape((com.fasterxml.jackson.core.Base64Variant) r1, (int) r10, (int) r15)
        L_0x00a3:
            if (r12 != r14) goto L_0x00ed
            int r10 = r0._inputPtr
            int r11 = r0._inputEnd
            if (r10 < r11) goto L_0x00ae
            r16._loadMoreGuaranteed()
        L_0x00ae:
            byte[] r10 = r0._inputBuffer
            int r11 = r0._inputPtr
            int r12 = r11 + 1
            r0._inputPtr = r12
            byte r10 = r10[r11]
            r10 = r10 & 255(0xff, float:3.57E-43)
            boolean r11 = r1.usesPaddingChar((int) r10)
            if (r11 != 0) goto L_0x00e3
            int r11 = r0._decodeBase64Escape((com.fasterxml.jackson.core.Base64Variant) r1, (int) r10, (int) r5)
            if (r11 != r14) goto L_0x00c7
            goto L_0x00e3
        L_0x00c7:
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            java.lang.String r3 = "expected padding character '"
            r2.<init>(r3)
            char r3 = r17.getPaddingChar()
            r2.append(r3)
            java.lang.String r3 = "'"
            r2.append(r3)
            java.lang.String r2 = r2.toString()
            java.lang.IllegalArgumentException r0 = r0.reportInvalidBase64Char(r1, r10, r5, r2)
            throw r0
        L_0x00e3:
            int r9 = r9 >> 4
            int r10 = r7 + 1
            byte r9 = (byte) r9
            r3[r7] = r9
            r7 = r10
            goto L_0x000e
        L_0x00ed:
            int r9 = r9 << 6
            r9 = r9 | r12
            int r10 = r0._inputPtr
            int r12 = r0._inputEnd
            if (r10 < r12) goto L_0x00f9
            r16._loadMoreGuaranteed()
        L_0x00f9:
            byte[] r10 = r0._inputBuffer
            int r12 = r0._inputPtr
            int r5 = r12 + 1
            r0._inputPtr = r5
            byte r5 = r10[r12]
            r5 = r5 & 255(0xff, float:3.57E-43)
            int r10 = r1.decodeBase64Char((int) r5)
            if (r10 >= 0) goto L_0x014d
            if (r10 == r14) goto L_0x0139
            if (r5 != r11) goto L_0x0133
            int r4 = r9 >> 2
            int r5 = r7 + 1
            int r9 = r9 >> 10
            byte r9 = (byte) r9
            r3[r7] = r9
            int r7 = r7 + r15
            byte r4 = (byte) r4
            r3[r5] = r4
            boolean r4 = r17.usesPadding()
            if (r4 == 0) goto L_0x012a
            int r4 = r0._inputPtr
            int r4 = r4 - r13
            r0._inputPtr = r4
            r16._handleBase64MissingPadding(r17)
        L_0x012a:
            r0._tokenIncomplete = r6
            if (r7 <= 0) goto L_0x0132
            int r8 = r8 + r7
            r2.write(r3, r6, r7)
        L_0x0132:
            return r8
        L_0x0133:
            r11 = 3
            int r10 = r0._decodeBase64Escape((com.fasterxml.jackson.core.Base64Variant) r1, (int) r5, (int) r11)
            goto L_0x013a
        L_0x0139:
            r11 = 3
        L_0x013a:
            if (r10 != r14) goto L_0x014e
            int r5 = r9 >> 2
            int r10 = r7 + 1
            int r9 = r9 >> 10
            byte r9 = (byte) r9
            r3[r7] = r9
            int r7 = r7 + 2
            byte r5 = (byte) r5
            r3[r10] = r5
        L_0x014a:
            r5 = r11
            goto L_0x000e
        L_0x014d:
            r11 = 3
        L_0x014e:
            int r5 = r9 << 6
            r5 = r5 | r10
            int r9 = r7 + 1
            int r10 = r5 >> 16
            byte r10 = (byte) r10
            r3[r7] = r10
            int r10 = r7 + 2
            int r12 = r5 >> 8
            byte r12 = (byte) r12
            r3[r9] = r12
            int r7 = r7 + 3
            byte r5 = (byte) r5
            r3[r10] = r5
            goto L_0x014a
        */
        throw new UnsupportedOperationException("Method not decompiled: com.fasterxml.jackson.core.json.UTF8StreamJsonParser._readBinary(com.fasterxml.jackson.core.Base64Variant, java.io.OutputStream, byte[]):int");
    }

    public void _releaseBuffers() throws IOException {
        byte[] bArr;
        byte[] bArr2;
        super._releaseBuffers();
        this._symbols.release();
        if (this._bufferRecyclable && (bArr = this._inputBuffer) != null && bArr != (bArr2 = ParserMinimalBase.NO_BYTES)) {
            this._inputBuffer = bArr2;
            this._ioContext.releaseReadIOBuffer(bArr);
        }
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

    public void _reportInvalidToken(String str, int i3) throws IOException {
        this._inputPtr = i3;
        _reportInvalidToken(str, _validJsonTokenList());
    }

    public final void _skipCR() throws IOException {
        if (this._inputPtr < this._inputEnd || _loadMore()) {
            byte[] bArr = this._inputBuffer;
            int i3 = this._inputPtr;
            if (bArr[i3] == 10) {
                this._inputPtr = i3 + 1;
            }
        }
        this._currInputRow++;
        this._currInputRowStart = this._inputPtr;
    }

    public void _skipString() throws IOException {
        this._tokenIncomplete = false;
        int[] iArr = _icUTF8;
        byte[] bArr = this._inputBuffer;
        while (true) {
            int i3 = this._inputPtr;
            int i4 = this._inputEnd;
            if (i3 >= i4) {
                _loadMoreGuaranteed();
                i3 = this._inputPtr;
                i4 = this._inputEnd;
            }
            while (true) {
                if (i3 >= i4) {
                    this._inputPtr = i3;
                    break;
                }
                int i5 = i3 + 1;
                byte b3 = bArr[i3] & 255;
                int i6 = iArr[b3];
                if (i6 != 0) {
                    this._inputPtr = i5;
                    if (b3 != 34) {
                        if (i6 == 1) {
                            _decodeEscaped();
                        } else if (i6 == 2) {
                            _skipUtf8_2();
                        } else if (i6 == 3) {
                            _skipUtf8_3();
                        } else if (i6 == 4) {
                            _skipUtf8_4(b3);
                        } else if (b3 < 32) {
                            _throwUnquotedSpace(b3, "string value");
                        } else {
                            _reportInvalidChar(b3);
                        }
                    } else {
                        return;
                    }
                } else {
                    i3 = i5;
                }
            }
        }
    }

    public void finishToken() throws IOException {
        if (this._tokenIncomplete) {
            this._tokenIncomplete = false;
            _finishString();
        }
    }

    public byte[] getBinaryValue(Base64Variant base64Variant) throws IOException {
        JsonToken jsonToken = this._currToken;
        if (jsonToken != JsonToken.VALUE_STRING && (jsonToken != JsonToken.VALUE_EMBEDDED_OBJECT || this._binaryValue == null)) {
            _reportError("Current token (" + this._currToken + ") not VALUE_STRING or VALUE_EMBEDDED_OBJECT, can not access as binary");
        }
        if (this._tokenIncomplete) {
            try {
                this._binaryValue = _decodeBase64(base64Variant);
                this._tokenIncomplete = false;
            } catch (IllegalArgumentException e3) {
                throw _constructError("Failed to decode VALUE_STRING as base64 (" + base64Variant + "): " + e3.getMessage());
            }
        } else if (this._binaryValue == null) {
            ByteArrayBuilder _getByteArrayBuilder = _getByteArrayBuilder();
            _decodeBase64(getText(), _getByteArrayBuilder, base64Variant);
            this._binaryValue = _getByteArrayBuilder.toByteArray();
        }
        return this._binaryValue;
    }

    public ObjectCodec getCodec() {
        return this._objectCodec;
    }

    public JsonLocation getCurrentLocation() {
        return new JsonLocation(_contentReference(), this._currInputProcessed + ((long) this._inputPtr), -1, this._currInputRow, (this._inputPtr - this._currInputRowStart) + 1);
    }

    public Object getInputSource() {
        return this._inputStream;
    }

    public JacksonFeatureSet<StreamReadCapability> getReadCapabilities() {
        return ParserBase.JSON_READ_CAPABILITIES;
    }

    public String getText() throws IOException {
        JsonToken jsonToken = this._currToken;
        if (jsonToken != JsonToken.VALUE_STRING) {
            return _getText2(jsonToken);
        }
        if (!this._tokenIncomplete) {
            return this._textBuffer.contentsAsString();
        }
        this._tokenIncomplete = false;
        return _finishAndReturnString();
    }

    public char[] getTextCharacters() throws IOException {
        JsonToken jsonToken = this._currToken;
        if (jsonToken == null) {
            return null;
        }
        int id = jsonToken.id();
        if (id != 5) {
            if (id != 6) {
                if (!(id == 7 || id == 8)) {
                    return this._currToken.asCharArray();
                }
            } else if (this._tokenIncomplete) {
                this._tokenIncomplete = false;
                _finishString();
            }
            return this._textBuffer.getTextBuffer();
        }
        if (!this._nameCopied) {
            String currentName = this._parsingContext.getCurrentName();
            int length = currentName.length();
            char[] cArr = this._nameCopyBuffer;
            if (cArr == null) {
                this._nameCopyBuffer = this._ioContext.allocNameCopyBuffer(length);
            } else if (cArr.length < length) {
                this._nameCopyBuffer = new char[length];
            }
            currentName.getChars(0, length, this._nameCopyBuffer, 0);
            this._nameCopied = true;
        }
        return this._nameCopyBuffer;
    }

    public int getTextLength() throws IOException {
        JsonToken jsonToken = this._currToken;
        if (jsonToken == null) {
            return 0;
        }
        int id = jsonToken.id();
        if (id == 5) {
            return this._parsingContext.getCurrentName().length();
        }
        if (id != 6) {
            if (!(id == 7 || id == 8)) {
                return this._currToken.asCharArray().length;
            }
        } else if (this._tokenIncomplete) {
            this._tokenIncomplete = false;
            _finishString();
        }
        return this._textBuffer.size();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:7:0x0011, code lost:
        if (r0 != 8) goto L_0x0024;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public int getTextOffset() throws java.io.IOException {
        /*
            r3 = this;
            com.fasterxml.jackson.core.JsonToken r0 = r3._currToken
            r1 = 0
            if (r0 == 0) goto L_0x0024
            int r0 = r0.id()
            r2 = 6
            if (r0 == r2) goto L_0x0014
            r2 = 7
            if (r0 == r2) goto L_0x001d
            r2 = 8
            if (r0 == r2) goto L_0x001d
            goto L_0x0024
        L_0x0014:
            boolean r0 = r3._tokenIncomplete
            if (r0 == 0) goto L_0x001d
            r3._tokenIncomplete = r1
            r3._finishString()
        L_0x001d:
            com.fasterxml.jackson.core.util.TextBuffer r3 = r3._textBuffer
            int r3 = r3.getTextOffset()
            return r3
        L_0x0024:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.fasterxml.jackson.core.json.UTF8StreamJsonParser.getTextOffset():int");
    }

    public JsonLocation getTokenLocation() {
        if (this._currToken == JsonToken.FIELD_NAME) {
            return new JsonLocation(_contentReference(), this._currInputProcessed + ((long) (this._nameStartOffset - 1)), -1, this._nameStartRow, this._nameStartCol);
        }
        return new JsonLocation(_contentReference(), this._tokenInputTotal - 1, -1, this._tokenInputRow, this._tokenInputCol);
    }

    public int getValueAsInt() throws IOException {
        JsonToken jsonToken = this._currToken;
        if (jsonToken != JsonToken.VALUE_NUMBER_INT && jsonToken != JsonToken.VALUE_NUMBER_FLOAT) {
            return super.getValueAsInt(0);
        }
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

    public String getValueAsString() throws IOException {
        JsonToken jsonToken = this._currToken;
        if (jsonToken == JsonToken.VALUE_STRING) {
            if (!this._tokenIncomplete) {
                return this._textBuffer.contentsAsString();
            }
            this._tokenIncomplete = false;
            return _finishAndReturnString();
        } else if (jsonToken == JsonToken.FIELD_NAME) {
            return getCurrentName();
        } else {
            return super.getValueAsString((String) null);
        }
    }

    public Boolean nextBooleanValue() throws IOException {
        if (this._currToken == JsonToken.FIELD_NAME) {
            this._nameCopied = false;
            JsonToken jsonToken = this._nextToken;
            this._nextToken = null;
            this._currToken = jsonToken;
            if (jsonToken == JsonToken.VALUE_TRUE) {
                return Boolean.TRUE;
            }
            if (jsonToken == JsonToken.VALUE_FALSE) {
                return Boolean.FALSE;
            }
            if (jsonToken == JsonToken.START_ARRAY) {
                this._parsingContext = this._parsingContext.createChildArrayContext(this._tokenInputRow, this._tokenInputCol);
            } else if (jsonToken == JsonToken.START_OBJECT) {
                this._parsingContext = this._parsingContext.createChildObjectContext(this._tokenInputRow, this._tokenInputCol);
            }
            return null;
        }
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
        int i3 = 0;
        this._numTypesValid = 0;
        if (this._currToken == JsonToken.FIELD_NAME) {
            _nextAfterName();
            return false;
        }
        if (this._tokenIncomplete) {
            _skipString();
        }
        int _skipWSOrEnd = _skipWSOrEnd();
        if (_skipWSOrEnd < 0) {
            close();
            this._currToken = null;
            return false;
        }
        this._binaryValue = null;
        if (_skipWSOrEnd == 93) {
            _closeArrayScope();
            this._currToken = JsonToken.END_ARRAY;
            return false;
        } else if (_skipWSOrEnd == 125) {
            _closeObjectScope();
            this._currToken = JsonToken.END_OBJECT;
            return false;
        } else {
            if (this._parsingContext.expectComma()) {
                if (_skipWSOrEnd != 44) {
                    _reportUnexpectedChar(_skipWSOrEnd, "was expecting comma to separate " + this._parsingContext.typeDesc() + " entries");
                }
                _skipWSOrEnd = _skipWS();
                if ((this._features & FEAT_MASK_TRAILING_COMMA) != 0 && (_skipWSOrEnd == 93 || _skipWSOrEnd == 125)) {
                    _closeScope(_skipWSOrEnd);
                    return false;
                }
            }
            if (!this._parsingContext.inObject()) {
                _updateLocation();
                _nextTokenNotInObject(_skipWSOrEnd);
                return false;
            }
            _updateNameLocation();
            if (_skipWSOrEnd == 34) {
                byte[] asQuotedUTF8 = serializableString.asQuotedUTF8();
                int length = asQuotedUTF8.length;
                int i4 = this._inputPtr;
                if (i4 + length + 4 < this._inputEnd) {
                    int i5 = length + i4;
                    if (this._inputBuffer[i5] == 34) {
                        while (i4 != i5) {
                            if (asQuotedUTF8[i3] == this._inputBuffer[i4]) {
                                i3++;
                                i4++;
                            }
                        }
                        this._parsingContext.setCurrentName(serializableString.getValue());
                        _isNextTokenNameYes(_skipColonFast(i4 + 1));
                        return true;
                    }
                }
            }
            return _isNextTokenNameMaybe(_skipWSOrEnd, serializableString);
        }
    }

    public int nextIntValue(int i3) throws IOException {
        if (this._currToken != JsonToken.FIELD_NAME) {
            return nextToken() == JsonToken.VALUE_NUMBER_INT ? getIntValue() : i3;
        }
        this._nameCopied = false;
        JsonToken jsonToken = this._nextToken;
        this._nextToken = null;
        this._currToken = jsonToken;
        if (jsonToken == JsonToken.VALUE_NUMBER_INT) {
            return getIntValue();
        }
        if (jsonToken == JsonToken.START_ARRAY) {
            this._parsingContext = this._parsingContext.createChildArrayContext(this._tokenInputRow, this._tokenInputCol);
        } else if (jsonToken == JsonToken.START_OBJECT) {
            this._parsingContext = this._parsingContext.createChildObjectContext(this._tokenInputRow, this._tokenInputCol);
        }
        return i3;
    }

    public long nextLongValue(long j2) throws IOException {
        if (this._currToken != JsonToken.FIELD_NAME) {
            return nextToken() == JsonToken.VALUE_NUMBER_INT ? getLongValue() : j2;
        }
        this._nameCopied = false;
        JsonToken jsonToken = this._nextToken;
        this._nextToken = null;
        this._currToken = jsonToken;
        if (jsonToken == JsonToken.VALUE_NUMBER_INT) {
            return getLongValue();
        }
        if (jsonToken == JsonToken.START_ARRAY) {
            this._parsingContext = this._parsingContext.createChildArrayContext(this._tokenInputRow, this._tokenInputCol);
        } else if (jsonToken == JsonToken.START_OBJECT) {
            this._parsingContext = this._parsingContext.createChildObjectContext(this._tokenInputRow, this._tokenInputCol);
        }
        return j2;
    }

    public String nextTextValue() throws IOException {
        if (this._currToken == JsonToken.FIELD_NAME) {
            this._nameCopied = false;
            JsonToken jsonToken = this._nextToken;
            this._nextToken = null;
            this._currToken = jsonToken;
            if (jsonToken != JsonToken.VALUE_STRING) {
                if (jsonToken == JsonToken.START_ARRAY) {
                    this._parsingContext = this._parsingContext.createChildArrayContext(this._tokenInputRow, this._tokenInputCol);
                } else if (jsonToken == JsonToken.START_OBJECT) {
                    this._parsingContext = this._parsingContext.createChildObjectContext(this._tokenInputRow, this._tokenInputCol);
                }
                return null;
            } else if (!this._tokenIncomplete) {
                return this._textBuffer.contentsAsString();
            } else {
                this._tokenIncomplete = false;
                return _finishAndReturnString();
            }
        } else if (nextToken() == JsonToken.VALUE_STRING) {
            return getText();
        } else {
            return null;
        }
    }

    public JsonToken nextToken() throws IOException {
        JsonToken jsonToken;
        JsonToken jsonToken2 = this._currToken;
        JsonToken jsonToken3 = JsonToken.FIELD_NAME;
        if (jsonToken2 == jsonToken3) {
            return _nextAfterName();
        }
        this._numTypesValid = 0;
        if (this._tokenIncomplete) {
            _skipString();
        }
        int _skipWSOrEnd = _skipWSOrEnd();
        if (_skipWSOrEnd < 0) {
            close();
            this._currToken = null;
            return null;
        }
        this._binaryValue = null;
        if (_skipWSOrEnd == 93) {
            _closeArrayScope();
            JsonToken jsonToken4 = JsonToken.END_ARRAY;
            this._currToken = jsonToken4;
            return jsonToken4;
        } else if (_skipWSOrEnd == 125) {
            _closeObjectScope();
            JsonToken jsonToken5 = JsonToken.END_OBJECT;
            this._currToken = jsonToken5;
            return jsonToken5;
        } else {
            if (this._parsingContext.expectComma()) {
                if (_skipWSOrEnd != 44) {
                    _reportUnexpectedChar(_skipWSOrEnd, "was expecting comma to separate " + this._parsingContext.typeDesc() + " entries");
                }
                _skipWSOrEnd = _skipWS();
                if ((this._features & FEAT_MASK_TRAILING_COMMA) != 0 && (_skipWSOrEnd == 93 || _skipWSOrEnd == 125)) {
                    return _closeScope(_skipWSOrEnd);
                }
            }
            if (!this._parsingContext.inObject()) {
                _updateLocation();
                return _nextTokenNotInObject(_skipWSOrEnd);
            }
            _updateNameLocation();
            this._parsingContext.setCurrentName(_parseName(_skipWSOrEnd));
            this._currToken = jsonToken3;
            int _skipColon = _skipColon();
            _updateLocation();
            if (_skipColon == 34) {
                this._tokenIncomplete = true;
                this._nextToken = JsonToken.VALUE_STRING;
                return this._currToken;
            }
            if (_skipColon == 43) {
                jsonToken = isEnabled(JsonReadFeature.ALLOW_LEADING_PLUS_SIGN_FOR_NUMBERS.mappedFeature()) ? _parseSignedNumber(false) : _handleUnexpectedValue(_skipColon);
            } else if (_skipColon == 91) {
                jsonToken = JsonToken.START_ARRAY;
            } else if (_skipColon == 102) {
                _matchFalse();
                jsonToken = JsonToken.VALUE_FALSE;
            } else if (_skipColon == 110) {
                _matchNull();
                jsonToken = JsonToken.VALUE_NULL;
            } else if (_skipColon == 116) {
                _matchTrue();
                jsonToken = JsonToken.VALUE_TRUE;
            } else if (_skipColon == 123) {
                jsonToken = JsonToken.START_OBJECT;
            } else if (_skipColon == 45) {
                jsonToken = _parseSignedNumber(true);
            } else if (_skipColon != 46) {
                switch (_skipColon) {
                    case 48:
                    case 49:
                    case 50:
                    case 51:
                    case 52:
                    case 53:
                    case 54:
                    case 55:
                    case 56:
                    case 57:
                        jsonToken = _parseUnsignedNumber(_skipColon);
                        break;
                    default:
                        jsonToken = _handleUnexpectedValue(_skipColon);
                        break;
                }
            } else {
                jsonToken = _parseFloatThatStartsWithPeriod(false, false);
            }
            this._nextToken = jsonToken;
            return this._currToken;
        }
    }

    public final String parseEscapedName(int[] iArr, int i3, int i4, int i5, int i6) throws IOException {
        int[] iArr2 = _icLatin1;
        while (true) {
            if (iArr2[i5] != 0) {
                if (i5 == 34) {
                    break;
                }
                if (i5 != 92) {
                    _throwUnquotedSpace(i5, "name");
                } else {
                    i5 = _decodeEscaped();
                }
                if (i5 > 127) {
                    int i7 = 0;
                    if (r10 >= 4) {
                        if (i3 >= iArr.length) {
                            iArr = ParserBase.growArrayBy(iArr, iArr.length);
                            this._quadBuffer = iArr;
                        }
                        iArr[i3] = r8;
                        i3++;
                        r8 = 0;
                        r10 = 0;
                    }
                    if (i5 < 2048) {
                        r8 = (r8 << 8) | (i5 >> 6) | 192;
                        r10++;
                    } else {
                        int i8 = (r8 << 8) | (i5 >> 12) | 224;
                        int i9 = r10 + 1;
                        if (i9 >= 4) {
                            if (i3 >= iArr.length) {
                                iArr = ParserBase.growArrayBy(iArr, iArr.length);
                                this._quadBuffer = iArr;
                            }
                            iArr[i3] = i8;
                            i3++;
                            i9 = 0;
                        } else {
                            i7 = i8;
                        }
                        r8 = (i7 << 8) | ((i5 >> 6) & 63) | 128;
                        r10 = i9 + 1;
                    }
                    i5 = (i5 & 63) | 128;
                }
            }
            if (r10 < 4) {
                i6 = r10 + 1;
                i4 = (r8 << 8) | i5;
            } else {
                if (i3 >= iArr.length) {
                    iArr = ParserBase.growArrayBy(iArr, iArr.length);
                    this._quadBuffer = iArr;
                }
                iArr[i3] = r8;
                i4 = i5;
                i3++;
                i6 = 1;
            }
            if (this._inputPtr >= this._inputEnd && !_loadMore()) {
                _reportInvalidEOF(" in field name", JsonToken.FIELD_NAME);
            }
            byte[] bArr = this._inputBuffer;
            int i10 = this._inputPtr;
            this._inputPtr = i10 + 1;
            i5 = bArr[i10] & 255;
        }
        if (r10 > 0) {
            if (i3 >= iArr.length) {
                iArr = ParserBase.growArrayBy(iArr, iArr.length);
                this._quadBuffer = iArr;
            }
            iArr[i3] = _padLastQuad(r8, r10);
            i3++;
        }
        String findName = this._symbols.findName(iArr, i3);
        return findName == null ? addName(iArr, i3, r10) : findName;
    }

    public final String parseLongName(int i3, int i4, int i5) throws IOException {
        int[] iArr = this._quadBuffer;
        iArr[0] = this._quad1;
        iArr[1] = i4;
        iArr[2] = i5;
        byte[] bArr = this._inputBuffer;
        int[] iArr2 = _icLatin1;
        byte b3 = i3;
        int i6 = 3;
        while (true) {
            int i7 = this._inputPtr;
            if (i7 + 4 <= this._inputEnd) {
                int i8 = i7 + 1;
                this._inputPtr = i8;
                byte b4 = bArr[i7] & 255;
                if (iArr2[b4] == 0) {
                    byte b5 = (b3 << 8) | b4;
                    int i9 = i7 + 2;
                    this._inputPtr = i9;
                    byte b6 = bArr[i8] & 255;
                    if (iArr2[b6] == 0) {
                        byte b7 = (b5 << 8) | b6;
                        int i10 = i7 + 3;
                        this._inputPtr = i10;
                        byte b8 = bArr[i9] & 255;
                        if (iArr2[b8] == 0) {
                            int i11 = (b7 << 8) | b8;
                            this._inputPtr = i7 + 4;
                            byte b9 = bArr[i10] & 255;
                            if (iArr2[b9] == 0) {
                                int[] iArr3 = this._quadBuffer;
                                if (i6 >= iArr3.length) {
                                    this._quadBuffer = ParserBase.growArrayBy(iArr3, i6);
                                }
                                this._quadBuffer[i6] = i11;
                                b3 = b9;
                                i6++;
                            } else if (b9 == 34) {
                                return findName(this._quadBuffer, i6, i11, 4);
                            } else {
                                return parseEscapedName(this._quadBuffer, i6, i11, b9, 4);
                            }
                        } else if (b8 == 34) {
                            return findName(this._quadBuffer, i6, (int) b7, 3);
                        } else {
                            return parseEscapedName(this._quadBuffer, i6, b7, b8, 3);
                        }
                    } else if (b6 == 34) {
                        return findName(this._quadBuffer, i6, (int) b5, 2);
                    } else {
                        return parseEscapedName(this._quadBuffer, i6, b5, b6, 2);
                    }
                } else if (b4 == 34) {
                    return findName(this._quadBuffer, i6, b3, 1);
                } else {
                    return parseEscapedName(this._quadBuffer, i6, b3, b4, 1);
                }
            } else {
                return parseEscapedName(this._quadBuffer, i6, 0, b3, 0);
            }
        }
    }

    public final String parseMediumName(int i3) throws IOException {
        byte[] bArr = this._inputBuffer;
        int[] iArr = _icLatin1;
        int i4 = this._inputPtr;
        int i5 = i4 + 1;
        this._inputPtr = i5;
        byte b3 = bArr[i4] & 255;
        if (iArr[b3] != 0) {
            return b3 == 34 ? findName(this._quad1, i3, 1) : parseName(this._quad1, i3, b3, 1);
        }
        byte b4 = (i3 << 8) | b3;
        int i6 = i4 + 2;
        this._inputPtr = i6;
        byte b5 = bArr[i5] & 255;
        if (iArr[b5] != 0) {
            return b5 == 34 ? findName(this._quad1, b4, 2) : parseName(this._quad1, b4, b5, 2);
        }
        byte b6 = (b4 << 8) | b5;
        int i7 = i4 + 3;
        this._inputPtr = i7;
        byte b7 = bArr[i6] & 255;
        if (iArr[b7] != 0) {
            return b7 == 34 ? findName(this._quad1, b6, 3) : parseName(this._quad1, b6, b7, 3);
        }
        byte b8 = (b6 << 8) | b7;
        this._inputPtr = i4 + 4;
        byte b9 = bArr[i7] & 255;
        return iArr[b9] != 0 ? b9 == 34 ? findName(this._quad1, b8, 4) : parseName(this._quad1, b8, b9, 4) : parseMediumName2(b9, b8);
    }

    public final String parseMediumName2(int i3, int i4) throws IOException {
        byte[] bArr = this._inputBuffer;
        int[] iArr = _icLatin1;
        int i5 = this._inputPtr;
        int i6 = i5 + 1;
        this._inputPtr = i6;
        byte b3 = bArr[i5] & 255;
        if (iArr[b3] == 0) {
            byte b4 = (i3 << 8) | b3;
            int i7 = i5 + 2;
            this._inputPtr = i7;
            byte b5 = bArr[i6] & 255;
            if (iArr[b5] == 0) {
                byte b6 = (b4 << 8) | b5;
                int i8 = i5 + 3;
                this._inputPtr = i8;
                byte b7 = bArr[i7] & 255;
                if (iArr[b7] == 0) {
                    byte b8 = (b6 << 8) | b7;
                    this._inputPtr = i5 + 4;
                    byte b9 = bArr[i8] & 255;
                    if (iArr[b9] == 0) {
                        return parseLongName(b9, i4, b8);
                    }
                    if (b9 == 34) {
                        return findName(this._quad1, i4, (int) b8, 4);
                    }
                    return parseName(this._quad1, i4, b8, b9, 4);
                } else if (b7 == 34) {
                    return findName(this._quad1, i4, (int) b6, 3);
                } else {
                    return parseName(this._quad1, i4, b6, b7, 3);
                }
            } else if (b5 == 34) {
                return findName(this._quad1, i4, (int) b4, 2);
            } else {
                return parseName(this._quad1, i4, b4, b5, 2);
            }
        } else if (b3 == 34) {
            return findName(this._quad1, i4, i3, 1);
        } else {
            return parseName(this._quad1, i4, i3, b3, 1);
        }
    }

    public int readBinaryValue(Base64Variant base64Variant, OutputStream outputStream) throws IOException {
        if (!this._tokenIncomplete || this._currToken != JsonToken.VALUE_STRING) {
            byte[] binaryValue = getBinaryValue(base64Variant);
            outputStream.write(binaryValue);
            return binaryValue.length;
        }
        byte[] allocBase64Buffer = this._ioContext.allocBase64Buffer();
        try {
            return _readBinary(base64Variant, outputStream, allocBase64Buffer);
        } finally {
            this._ioContext.releaseBase64Buffer(allocBase64Buffer);
        }
    }

    public int releaseBuffered(OutputStream outputStream) throws IOException {
        int i3 = this._inputEnd;
        int i4 = this._inputPtr;
        int i5 = i3 - i4;
        if (i5 < 1) {
            return 0;
        }
        this._inputPtr = i4 + i5;
        outputStream.write(this._inputBuffer, i4, i5);
        return i5;
    }

    public void setCodec(ObjectCodec objectCodec) {
        this._objectCodec = objectCodec;
    }

    public String slowParseName() throws IOException {
        if (this._inputPtr >= this._inputEnd && !_loadMore()) {
            _reportInvalidEOF(": was expecting closing '\"' for name", JsonToken.FIELD_NAME);
        }
        byte[] bArr = this._inputBuffer;
        int i3 = this._inputPtr;
        this._inputPtr = i3 + 1;
        byte b3 = bArr[i3] & 255;
        if (b3 == 34) {
            return "";
        }
        return parseEscapedName(this._quadBuffer, 0, 0, b3, 0);
    }

    public UTF8StreamJsonParser(IOContext iOContext, int i3, InputStream inputStream, ObjectCodec objectCodec, ByteQuadsCanonicalizer byteQuadsCanonicalizer, byte[] bArr, int i4, int i5, int i6, boolean z2) {
        super(iOContext, i3);
        this._quadBuffer = new int[16];
        this._inputStream = inputStream;
        this._objectCodec = objectCodec;
        this._symbols = byteQuadsCanonicalizer;
        this._inputBuffer = bArr;
        this._inputPtr = i4;
        this._inputEnd = i5;
        this._currInputRowStart = i4 - i6;
        this._currInputProcessed = (long) ((-i4) + i6);
        this._bufferRecyclable = z2;
    }

    private final String parseName(int i3, int i4, int i5, int i6) throws IOException {
        int[] iArr = this._quadBuffer;
        iArr[0] = i3;
        return parseEscapedName(iArr, 1, i4, i5, i6);
    }

    /*  JADX ERROR: JadxRuntimeException in pass: InitCodeVariables
        jadx.core.utils.exceptions.JadxRuntimeException: Several immutable types in one variable: [int, byte], vars: [r4v0 ?, r4v1 ?, r4v2 ?, r4v5 ?]
        	at jadx.core.dex.visitors.InitCodeVariables.setCodeVarType(InitCodeVariables.java:102)
        	at jadx.core.dex.visitors.InitCodeVariables.setCodeVar(InitCodeVariables.java:78)
        	at jadx.core.dex.visitors.InitCodeVariables.initCodeVar(InitCodeVariables.java:69)
        	at jadx.core.dex.visitors.InitCodeVariables.initCodeVars(InitCodeVariables.java:48)
        	at jadx.core.dex.visitors.InitCodeVariables.visit(InitCodeVariables.java:32)
        */
    public com.fasterxml.jackson.core.JsonToken _handleInvalidNumberStart(
/*
Method generation error in method: com.fasterxml.jackson.core.json.UTF8StreamJsonParser._handleInvalidNumberStart(int, boolean, boolean):com.fasterxml.jackson.core.JsonToken, dex: classes3.dex
    jadx.core.utils.exceptions.JadxRuntimeException: Code variable not set in r4v0 ?
    	at jadx.core.dex.instructions.args.SSAVar.getCodeVar(SSAVar.java:189)
    	at jadx.core.codegen.MethodGen.addMethodArguments(MethodGen.java:157)
    	at jadx.core.codegen.MethodGen.addDefinition(MethodGen.java:129)
    	at jadx.core.codegen.ClassGen.addMethodCode(ClassGen.java:313)
    	at jadx.core.codegen.ClassGen.addMethod(ClassGen.java:271)
    	at jadx.core.codegen.ClassGen.lambda$addInnerClsAndMethods$2(ClassGen.java:240)
    	at java.util.stream.ForEachOps$ForEachOp$OfRef.accept(ForEachOps.java:183)
    	at java.util.ArrayList.forEach(ArrayList.java:1259)
    	at java.util.stream.SortedOps$RefSortingSink.end(SortedOps.java:395)
    	at java.util.stream.Sink$ChainedReference.end(Sink.java:258)
    	at java.util.stream.AbstractPipeline.copyInto(AbstractPipeline.java:483)
    	at java.util.stream.AbstractPipeline.wrapAndCopyInto(AbstractPipeline.java:472)
    	at java.util.stream.ForEachOps$ForEachOp.evaluateSequential(ForEachOps.java:150)
    	at java.util.stream.ForEachOps$ForEachOp$OfRef.evaluateSequential(ForEachOps.java:173)
    	at java.util.stream.AbstractPipeline.evaluate(AbstractPipeline.java:234)
    	at java.util.stream.ReferencePipeline.forEach(ReferencePipeline.java:485)
    	at jadx.core.codegen.ClassGen.addInnerClsAndMethods(ClassGen.java:236)
    	at jadx.core.codegen.ClassGen.addClassBody(ClassGen.java:227)
    	at jadx.core.codegen.ClassGen.addClassCode(ClassGen.java:112)
    	at jadx.core.codegen.ClassGen.makeClass(ClassGen.java:78)
    	at jadx.core.codegen.CodeGen.wrapCodeGen(CodeGen.java:44)
    	at jadx.core.codegen.CodeGen.generateJavaCode(CodeGen.java:33)
    	at jadx.core.codegen.CodeGen.generate(CodeGen.java:21)
    	at jadx.core.ProcessClass.generateCode(ProcessClass.java:61)
    	at jadx.core.dex.nodes.ClassNode.decompile(ClassNode.java:273)
    
*/

    public final JsonToken _parseFloatThatStartsWithPeriod(boolean z2, boolean z3) throws IOException {
        if (!isEnabled(JsonReadFeature.ALLOW_LEADING_DECIMAL_POINT_FOR_NUMBERS.mappedFeature())) {
            return _handleUnexpectedValue(46);
        }
        char[] emptyAndGetCurrentSegment = this._textBuffer.emptyAndGetCurrentSegment();
        int i3 = 0;
        if (z2) {
            emptyAndGetCurrentSegment[0] = SignatureVisitor.SUPER;
            i3 = 1;
        }
        return _parseFloat(emptyAndGetCurrentSegment, i3, 46, z2, 0);
    }

    public void _reportInvalidOther(int i3, int i4) throws JsonParseException {
        this._inputPtr = i4;
        _reportInvalidOther(i3);
    }

    public void _reportInvalidToken(String str) throws IOException {
        _reportInvalidToken(str, _validJsonTokenList());
    }

    private final String parseName(int i3, int i4, int i5, int i6, int i7) throws IOException {
        int[] iArr = this._quadBuffer;
        iArr[0] = i3;
        iArr[1] = i4;
        return parseEscapedName(iArr, 2, i5, i6, i7);
    }

    public void _reportInvalidToken(String str, String str2) throws IOException {
        StringBuilder sb = new StringBuilder(str);
        while (true) {
            if (this._inputPtr >= this._inputEnd && !_loadMore()) {
                break;
            }
            byte[] bArr = this._inputBuffer;
            int i3 = this._inputPtr;
            this._inputPtr = i3 + 1;
            char _decodeCharForError = (char) _decodeCharForError(bArr[i3]);
            if (Character.isJavaIdentifierPart(_decodeCharForError)) {
                sb.append(_decodeCharForError);
                if (sb.length() >= 256) {
                    sb.append("...");
                    break;
                }
            } else {
                break;
            }
        }
        _reportError("Unrecognized token '%s': was expecting %s", sb, str2);
    }

    private final String findName(int i3, int i4, int i5) throws JsonParseException {
        int _padLastQuad = _padLastQuad(i4, i5);
        String findName = this._symbols.findName(i3, _padLastQuad);
        if (findName != null) {
            return findName;
        }
        int[] iArr = this._quadBuffer;
        iArr[0] = i3;
        iArr[1] = _padLastQuad;
        return addName(iArr, 2, i5);
    }

    public int getText(Writer writer) throws IOException {
        JsonToken jsonToken = this._currToken;
        if (jsonToken == JsonToken.VALUE_STRING) {
            if (this._tokenIncomplete) {
                this._tokenIncomplete = false;
                _finishString();
            }
            return this._textBuffer.contentsToWriter(writer);
        } else if (jsonToken == JsonToken.FIELD_NAME) {
            String currentName = this._parsingContext.getCurrentName();
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

    public int getValueAsInt(int i3) throws IOException {
        JsonToken jsonToken = this._currToken;
        if (jsonToken != JsonToken.VALUE_NUMBER_INT && jsonToken != JsonToken.VALUE_NUMBER_FLOAT) {
            return super.getValueAsInt(i3);
        }
        int i4 = this._numTypesValid;
        if ((i4 & 1) == 0) {
            if (i4 == 0) {
                return _parseIntValue();
            }
            if ((i4 & 1) == 0) {
                convertNumberToInt();
            }
        }
        return this._numberInt;
    }

    public String getValueAsString(String str) throws IOException {
        JsonToken jsonToken = this._currToken;
        if (jsonToken == JsonToken.VALUE_STRING) {
            if (!this._tokenIncomplete) {
                return this._textBuffer.contentsAsString();
            }
            this._tokenIncomplete = false;
            return _finishAndReturnString();
        } else if (jsonToken == JsonToken.FIELD_NAME) {
            return getCurrentName();
        } else {
            return super.getValueAsString(str);
        }
    }

    private final String findName(int i3, int i4, int i5, int i6) throws JsonParseException {
        int _padLastQuad = _padLastQuad(i5, i6);
        String findName = this._symbols.findName(i3, i4, _padLastQuad);
        if (findName != null) {
            return findName;
        }
        int[] iArr = this._quadBuffer;
        iArr[0] = i3;
        iArr[1] = i4;
        iArr[2] = _padLastQuad(_padLastQuad, i6);
        return addName(iArr, 3, i6);
    }

    private final String findName(int[] iArr, int i3, int i4, int i5) throws JsonParseException {
        if (i3 >= iArr.length) {
            iArr = ParserBase.growArrayBy(iArr, iArr.length);
            this._quadBuffer = iArr;
        }
        int i6 = i3 + 1;
        iArr[i3] = _padLastQuad(i4, i5);
        String findName = this._symbols.findName(iArr, i6);
        return findName == null ? addName(iArr, i6, i5) : findName;
    }

    public String nextFieldName() throws IOException {
        JsonToken jsonToken;
        this._numTypesValid = 0;
        JsonToken jsonToken2 = this._currToken;
        JsonToken jsonToken3 = JsonToken.FIELD_NAME;
        if (jsonToken2 == jsonToken3) {
            _nextAfterName();
            return null;
        }
        if (this._tokenIncomplete) {
            _skipString();
        }
        int _skipWSOrEnd = _skipWSOrEnd();
        if (_skipWSOrEnd < 0) {
            close();
            this._currToken = null;
            return null;
        }
        this._binaryValue = null;
        if (_skipWSOrEnd == 93) {
            _closeArrayScope();
            this._currToken = JsonToken.END_ARRAY;
            return null;
        } else if (_skipWSOrEnd == 125) {
            _closeObjectScope();
            this._currToken = JsonToken.END_OBJECT;
            return null;
        } else {
            if (this._parsingContext.expectComma()) {
                if (_skipWSOrEnd != 44) {
                    _reportUnexpectedChar(_skipWSOrEnd, "was expecting comma to separate " + this._parsingContext.typeDesc() + " entries");
                }
                _skipWSOrEnd = _skipWS();
                if ((this._features & FEAT_MASK_TRAILING_COMMA) != 0 && (_skipWSOrEnd == 93 || _skipWSOrEnd == 125)) {
                    _closeScope(_skipWSOrEnd);
                    return null;
                }
            }
            if (!this._parsingContext.inObject()) {
                _updateLocation();
                _nextTokenNotInObject(_skipWSOrEnd);
                return null;
            }
            _updateNameLocation();
            String _parseName = _parseName(_skipWSOrEnd);
            this._parsingContext.setCurrentName(_parseName);
            this._currToken = jsonToken3;
            int _skipColon = _skipColon();
            _updateLocation();
            if (_skipColon == 34) {
                this._tokenIncomplete = true;
                this._nextToken = JsonToken.VALUE_STRING;
                return _parseName;
            }
            if (_skipColon != 43) {
                if (_skipColon == 91) {
                    jsonToken = JsonToken.START_ARRAY;
                } else if (_skipColon == 102) {
                    _matchFalse();
                    jsonToken = JsonToken.VALUE_FALSE;
                } else if (_skipColon == 110) {
                    _matchNull();
                    jsonToken = JsonToken.VALUE_NULL;
                } else if (_skipColon == 116) {
                    _matchTrue();
                    jsonToken = JsonToken.VALUE_TRUE;
                } else if (_skipColon == 123) {
                    jsonToken = JsonToken.START_OBJECT;
                } else if (_skipColon == 45) {
                    jsonToken = _parseSignedNumber(true);
                } else if (_skipColon != 46) {
                    switch (_skipColon) {
                        case 48:
                        case 49:
                        case 50:
                        case 51:
                        case 52:
                        case 53:
                        case 54:
                        case 55:
                        case 56:
                        case 57:
                            jsonToken = _parseUnsignedNumber(_skipColon);
                            break;
                        default:
                            jsonToken = _handleUnexpectedValue(_skipColon);
                            break;
                    }
                } else {
                    jsonToken = _parseFloatThatStartsWithPeriod(false, false);
                }
            } else if (isEnabled(JsonReadFeature.ALLOW_LEADING_PLUS_SIGN_FOR_NUMBERS.mappedFeature())) {
                jsonToken = _parseSignedNumber(false);
            } else {
                jsonToken = _handleUnexpectedValue(_skipColon);
            }
            this._nextToken = jsonToken;
            return _parseName;
        }
    }
}
