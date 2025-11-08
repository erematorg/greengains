package com.fasterxml.jackson.core.json;

import com.fasterxml.jackson.core.Base64Variant;
import com.fasterxml.jackson.core.JsonLocation;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.core.StreamReadCapability;
import com.fasterxml.jackson.core.base.GeneratorBase;
import com.fasterxml.jackson.core.base.ParserBase;
import com.fasterxml.jackson.core.io.CharTypes;
import com.fasterxml.jackson.core.io.IOContext;
import com.fasterxml.jackson.core.sym.ByteQuadsCanonicalizer;
import com.fasterxml.jackson.core.util.ByteArrayBuilder;
import com.fasterxml.jackson.core.util.JacksonFeatureSet;
import java.io.DataInput;
import java.io.EOFException;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Writer;
import java.util.Arrays;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;
import org.apache.commons.lang3.CharUtils;
import org.objectweb.asm.signature.SignatureVisitor;

public class UTF8DataInputJsonParser extends ParserBase {
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
    protected DataInput _inputData;
    protected int _nextByte;
    protected ObjectCodec _objectCodec;
    private int _quad1;
    protected int[] _quadBuffer = new int[16];
    protected final ByteQuadsCanonicalizer _symbols;
    protected boolean _tokenIncomplete;

    public UTF8DataInputJsonParser(IOContext iOContext, int i3, DataInput dataInput, ObjectCodec objectCodec, ByteQuadsCanonicalizer byteQuadsCanonicalizer, int i4) {
        super(iOContext, i3);
        this._objectCodec = objectCodec;
        this._symbols = byteQuadsCanonicalizer;
        this._inputData = dataInput;
        this._nextByte = i4;
    }

    private final void _checkMatchEnd(String str, int i3, int i4) throws IOException {
        char _decodeCharForError = (char) _decodeCharForError(i4);
        if (Character.isJavaIdentifierPart(_decodeCharForError)) {
            _reportInvalidToken(_decodeCharForError, str.substring(0, i3));
        }
    }

    private void _closeScope(int i3) throws JsonParseException {
        if (i3 == 93) {
            if (!this._parsingContext.inArray()) {
                _reportMismatchedEndMarker(i3, AbstractJsonLexerKt.END_OBJ);
            }
            this._parsingContext = this._parsingContext.clearAndGetParent();
            this._currToken = JsonToken.END_ARRAY;
        }
        if (i3 == 125) {
            if (!this._parsingContext.inObject()) {
                _reportMismatchedEndMarker(i3, AbstractJsonLexerKt.END_LIST);
            }
            this._parsingContext = this._parsingContext.clearAndGetParent();
            this._currToken = JsonToken.END_OBJECT;
        }
    }

    private final int _decodeUtf8_2(int i3) throws IOException {
        int readUnsignedByte = this._inputData.readUnsignedByte();
        if ((readUnsignedByte & 192) != 128) {
            _reportInvalidOther(readUnsignedByte & 255);
        }
        return ((i3 & 31) << 6) | (readUnsignedByte & 63);
    }

    private final int _decodeUtf8_3(int i3) throws IOException {
        int i4 = i3 & 15;
        int readUnsignedByte = this._inputData.readUnsignedByte();
        if ((readUnsignedByte & 192) != 128) {
            _reportInvalidOther(readUnsignedByte & 255);
        }
        int i5 = (i4 << 6) | (readUnsignedByte & 63);
        int readUnsignedByte2 = this._inputData.readUnsignedByte();
        if ((readUnsignedByte2 & 192) != 128) {
            _reportInvalidOther(readUnsignedByte2 & 255);
        }
        return (i5 << 6) | (readUnsignedByte2 & 63);
    }

    private final int _decodeUtf8_4(int i3) throws IOException {
        int readUnsignedByte = this._inputData.readUnsignedByte();
        if ((readUnsignedByte & 192) != 128) {
            _reportInvalidOther(readUnsignedByte & 255);
        }
        int i4 = ((i3 & 7) << 6) | (readUnsignedByte & 63);
        int readUnsignedByte2 = this._inputData.readUnsignedByte();
        if ((readUnsignedByte2 & 192) != 128) {
            _reportInvalidOther(readUnsignedByte2 & 255);
        }
        int i5 = (i4 << 6) | (readUnsignedByte2 & 63);
        int readUnsignedByte3 = this._inputData.readUnsignedByte();
        if ((readUnsignedByte3 & 192) != 128) {
            _reportInvalidOther(readUnsignedByte3 & 255);
        }
        return ((i5 << 6) | (readUnsignedByte3 & 63)) - 65536;
    }

    private String _finishAndReturnString() throws IOException {
        char[] emptyAndGetCurrentSegment = this._textBuffer.emptyAndGetCurrentSegment();
        int[] iArr = _icUTF8;
        int length = emptyAndGetCurrentSegment.length;
        int i3 = 0;
        while (true) {
            int readUnsignedByte = this._inputData.readUnsignedByte();
            if (iArr[readUnsignedByte] == 0) {
                int i4 = i3 + 1;
                emptyAndGetCurrentSegment[i3] = (char) readUnsignedByte;
                if (i4 >= length) {
                    _finishString2(emptyAndGetCurrentSegment, i4, this._inputData.readUnsignedByte());
                    return this._textBuffer.contentsAsString();
                }
                i3 = i4;
            } else if (readUnsignedByte == 34) {
                return this._textBuffer.setCurrentAndReturn(i3);
            } else {
                _finishString2(emptyAndGetCurrentSegment, i3, readUnsignedByte);
                return this._textBuffer.contentsAsString();
            }
        }
    }

    private final void _finishString2(char[] cArr, int i3, int i4) throws IOException {
        int[] iArr = _icUTF8;
        int length = cArr.length;
        while (true) {
            int i5 = iArr[i4];
            int i6 = 0;
            if (i5 == 0) {
                if (i3 >= length) {
                    cArr = this._textBuffer.finishCurrentSegment();
                    length = cArr.length;
                    i3 = 0;
                }
                cArr[i3] = (char) i4;
                i4 = this._inputData.readUnsignedByte();
                i3++;
            } else if (i4 == 34) {
                this._textBuffer.setCurrentLength(i3);
                return;
            } else {
                if (i5 == 1) {
                    i4 = _decodeEscaped();
                } else if (i5 == 2) {
                    i4 = _decodeUtf8_2(i4);
                } else if (i5 == 3) {
                    i4 = _decodeUtf8_3(i4);
                } else if (i5 == 4) {
                    int _decodeUtf8_4 = _decodeUtf8_4(i4);
                    if (i3 >= cArr.length) {
                        cArr = this._textBuffer.finishCurrentSegment();
                        length = cArr.length;
                        i3 = 0;
                    }
                    cArr[i3] = (char) ((_decodeUtf8_4 >> 10) | GeneratorBase.SURR1_FIRST);
                    i4 = 56320 | (_decodeUtf8_4 & 1023);
                    i3++;
                } else if (i4 < 32) {
                    _throwUnquotedSpace(i4, "string value");
                } else {
                    _reportInvalidChar(i4);
                }
                if (i3 >= cArr.length) {
                    cArr = this._textBuffer.finishCurrentSegment();
                    length = cArr.length;
                } else {
                    i6 = i3;
                }
                i3 = i6 + 1;
                cArr[i6] = (char) i4;
                i4 = this._inputData.readUnsignedByte();
            }
        }
    }

    private static int[] _growArrayBy(int[] iArr, int i3) {
        return iArr == null ? new int[i3] : Arrays.copyOf(iArr, iArr.length + i3);
    }

    private final int _handleLeadingZeroes() throws IOException {
        int readUnsignedByte = this._inputData.readUnsignedByte();
        if (readUnsignedByte >= 48 && readUnsignedByte <= 57) {
            if ((this._features & FEAT_MASK_LEADING_ZEROS) == 0) {
                reportInvalidNumber("Leading zeroes not allowed");
            }
            while (readUnsignedByte == 48) {
                readUnsignedByte = this._inputData.readUnsignedByte();
            }
        }
        return readUnsignedByte;
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
                _matchToken("false", 1);
                JsonToken jsonToken3 = JsonToken.VALUE_FALSE;
                this._currToken = jsonToken3;
                return jsonToken3;
            } else if (i3 == 110) {
                _matchToken(AbstractJsonLexerKt.NULL, 1);
                JsonToken jsonToken4 = JsonToken.VALUE_NULL;
                this._currToken = jsonToken4;
                return jsonToken4;
            } else if (i3 == 116) {
                _matchToken("true", 1);
                JsonToken jsonToken5 = JsonToken.VALUE_TRUE;
                this._currToken = jsonToken5;
                return jsonToken5;
            } else if (i3 == 123) {
                this._parsingContext = this._parsingContext.createChildObjectContext(this._tokenInputRow, this._tokenInputCol);
                JsonToken jsonToken6 = JsonToken.START_OBJECT;
                this._currToken = jsonToken6;
                return jsonToken6;
            } else if (i3 == 45) {
                JsonToken _parseNegNumber = _parseNegNumber();
                this._currToken = _parseNegNumber;
                return _parseNegNumber;
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
        } else if (isEnabled(JsonReadFeature.ALLOW_LEADING_PLUS_SIGN_FOR_NUMBERS.mappedFeature())) {
            JsonToken _parsePosNumber = _parsePosNumber();
            this._currToken = _parsePosNumber;
            return _parsePosNumber;
        } else {
            JsonToken _handleUnexpectedValue2 = _handleUnexpectedValue(i3);
            this._currToken = _handleUnexpectedValue2;
            return _handleUnexpectedValue2;
        }
    }

    private final JsonToken _parseFloat(char[] cArr, int i3, int i4, boolean z2, int i5) throws IOException {
        int i6;
        int i7;
        int readUnsignedByte;
        int i8 = 0;
        if (i4 == 46) {
            if (i3 >= cArr.length) {
                cArr = this._textBuffer.finishCurrentSegment();
                i3 = 0;
            }
            cArr[i3] = (char) i4;
            i3++;
            int i9 = 0;
            while (true) {
                readUnsignedByte = this._inputData.readUnsignedByte();
                if (readUnsignedByte >= 48 && readUnsignedByte <= 57) {
                    i9++;
                    if (i3 >= cArr.length) {
                        cArr = this._textBuffer.finishCurrentSegment();
                        i3 = 0;
                    }
                    cArr[i3] = (char) readUnsignedByte;
                    i3++;
                } else if (i9 == 0 && !isEnabled(JsonReadFeature.ALLOW_TRAILING_DECIMAL_POINT_FOR_NUMBERS.mappedFeature())) {
                    _reportUnexpectedNumberChar(readUnsignedByte, "Decimal point not followed by a digit");
                }
            }
            _reportUnexpectedNumberChar(readUnsignedByte, "Decimal point not followed by a digit");
            int i10 = readUnsignedByte;
            i6 = i9;
            i4 = i10;
        } else {
            i6 = 0;
        }
        if (r10 == 101 || r10 == 69) {
            if (i3 >= cArr.length) {
                cArr = this._textBuffer.finishCurrentSegment();
                i3 = 0;
            }
            int i11 = i3 + 1;
            cArr[i3] = (char) r10;
            int readUnsignedByte2 = this._inputData.readUnsignedByte();
            if (readUnsignedByte2 == 45 || readUnsignedByte2 == 43) {
                if (i11 >= cArr.length) {
                    cArr = this._textBuffer.finishCurrentSegment();
                    i11 = 0;
                }
                int i12 = i11 + 1;
                cArr[i11] = (char) readUnsignedByte2;
                i7 = 0;
                int i13 = i12;
                r10 = this._inputData.readUnsignedByte();
                r9 = i13;
            } else {
                r10 = readUnsignedByte2;
                r9 = i11;
                i7 = 0;
            }
            while (r10 <= 57 && r10 >= 48) {
                i7++;
                if (i3 >= cArr.length) {
                    cArr = this._textBuffer.finishCurrentSegment();
                    i3 = 0;
                }
                cArr[i3] = (char) r10;
                r10 = this._inputData.readUnsignedByte();
                r9 = i3 + 1;
            }
            if (i7 == 0) {
                _reportUnexpectedNumberChar(r10, "Exponent indicator not followed by a digit");
            }
            i8 = i7;
        }
        this._nextByte = r10;
        if (this._parsingContext.inRoot()) {
            _verifyRootSpace();
        }
        this._textBuffer.setCurrentLength(i3);
        return resetFloat(z2, i5, i6, i8);
    }

    private final String _parseLongName(int i3, int i4, int i5) throws IOException {
        int[] iArr = this._quadBuffer;
        iArr[0] = this._quad1;
        iArr[1] = i4;
        iArr[2] = i5;
        int[] iArr2 = _icLatin1;
        int i6 = i3;
        int i7 = 3;
        while (true) {
            int readUnsignedByte = this._inputData.readUnsignedByte();
            if (iArr2[readUnsignedByte] == 0) {
                int i8 = (i6 << 8) | readUnsignedByte;
                int readUnsignedByte2 = this._inputData.readUnsignedByte();
                if (iArr2[readUnsignedByte2] == 0) {
                    int i9 = (i8 << 8) | readUnsignedByte2;
                    int readUnsignedByte3 = this._inputData.readUnsignedByte();
                    if (iArr2[readUnsignedByte3] == 0) {
                        int i10 = (i9 << 8) | readUnsignedByte3;
                        int readUnsignedByte4 = this._inputData.readUnsignedByte();
                        if (iArr2[readUnsignedByte4] == 0) {
                            int[] iArr3 = this._quadBuffer;
                            if (i7 >= iArr3.length) {
                                this._quadBuffer = _growArrayBy(iArr3, i7);
                            }
                            this._quadBuffer[i7] = i10;
                            i7++;
                            i6 = readUnsignedByte4;
                        } else if (readUnsignedByte4 == 34) {
                            return findName(this._quadBuffer, i7, i10, 4);
                        } else {
                            return parseEscapedName(this._quadBuffer, i7, i10, readUnsignedByte4, 4);
                        }
                    } else if (readUnsignedByte3 == 34) {
                        return findName(this._quadBuffer, i7, i9, 3);
                    } else {
                        return parseEscapedName(this._quadBuffer, i7, i9, readUnsignedByte3, 3);
                    }
                } else if (readUnsignedByte2 == 34) {
                    return findName(this._quadBuffer, i7, i8, 2);
                } else {
                    return parseEscapedName(this._quadBuffer, i7, i8, readUnsignedByte2, 2);
                }
            } else if (readUnsignedByte == 34) {
                return findName(this._quadBuffer, i7, i6, 1);
            } else {
                return parseEscapedName(this._quadBuffer, i7, i6, readUnsignedByte, 1);
            }
        }
    }

    private final String _parseMediumName(int i3) throws IOException {
        int[] iArr = _icLatin1;
        int readUnsignedByte = this._inputData.readUnsignedByte();
        if (iArr[readUnsignedByte] != 0) {
            return readUnsignedByte == 34 ? findName(this._quad1, i3, 1) : parseName(this._quad1, i3, readUnsignedByte, 1);
        }
        int i4 = (i3 << 8) | readUnsignedByte;
        int readUnsignedByte2 = this._inputData.readUnsignedByte();
        if (iArr[readUnsignedByte2] != 0) {
            return readUnsignedByte2 == 34 ? findName(this._quad1, i4, 2) : parseName(this._quad1, i4, readUnsignedByte2, 2);
        }
        int i5 = (i4 << 8) | readUnsignedByte2;
        int readUnsignedByte3 = this._inputData.readUnsignedByte();
        if (iArr[readUnsignedByte3] != 0) {
            return readUnsignedByte3 == 34 ? findName(this._quad1, i5, 3) : parseName(this._quad1, i5, readUnsignedByte3, 3);
        }
        int i6 = (i5 << 8) | readUnsignedByte3;
        int readUnsignedByte4 = this._inputData.readUnsignedByte();
        return iArr[readUnsignedByte4] != 0 ? readUnsignedByte4 == 34 ? findName(this._quad1, i6, 4) : parseName(this._quad1, i6, readUnsignedByte4, 4) : _parseMediumName2(readUnsignedByte4, i6);
    }

    private final String _parseMediumName2(int i3, int i4) throws IOException {
        int[] iArr = _icLatin1;
        int readUnsignedByte = this._inputData.readUnsignedByte();
        if (iArr[readUnsignedByte] == 0) {
            int i5 = (i3 << 8) | readUnsignedByte;
            int readUnsignedByte2 = this._inputData.readUnsignedByte();
            if (iArr[readUnsignedByte2] == 0) {
                int i6 = (i5 << 8) | readUnsignedByte2;
                int readUnsignedByte3 = this._inputData.readUnsignedByte();
                if (iArr[readUnsignedByte3] == 0) {
                    int i7 = (i6 << 8) | readUnsignedByte3;
                    int readUnsignedByte4 = this._inputData.readUnsignedByte();
                    if (iArr[readUnsignedByte4] == 0) {
                        return _parseLongName(readUnsignedByte4, i4, i7);
                    }
                    if (readUnsignedByte4 == 34) {
                        return findName(this._quad1, i4, i7, 4);
                    }
                    return parseName(this._quad1, i4, i7, readUnsignedByte4, 4);
                } else if (readUnsignedByte3 == 34) {
                    return findName(this._quad1, i4, i6, 3);
                } else {
                    return parseName(this._quad1, i4, i6, readUnsignedByte3, 3);
                }
            } else if (readUnsignedByte2 == 34) {
                return findName(this._quad1, i4, i5, 2);
            } else {
                return parseName(this._quad1, i4, i5, readUnsignedByte2, 2);
            }
        } else if (readUnsignedByte == 34) {
            return findName(this._quad1, i4, i3, 1);
        } else {
            return parseName(this._quad1, i4, i3, readUnsignedByte, 1);
        }
    }

    private final JsonToken _parseSignedNumber(boolean z2) throws IOException {
        int i3;
        int i4;
        char[] emptyAndGetCurrentSegment = this._textBuffer.emptyAndGetCurrentSegment();
        if (z2) {
            emptyAndGetCurrentSegment[0] = SignatureVisitor.SUPER;
            i3 = 1;
        } else {
            i3 = 0;
        }
        int readUnsignedByte = this._inputData.readUnsignedByte();
        int i5 = i3 + 1;
        emptyAndGetCurrentSegment[i3] = (char) readUnsignedByte;
        if (readUnsignedByte <= 48) {
            if (readUnsignedByte != 48) {
                return readUnsignedByte == 46 ? _parseFloatThatStartsWithPeriod(z2, true) : _handleInvalidNumberStart(readUnsignedByte, z2, true);
            }
            i4 = _handleLeadingZeroes();
        } else if (readUnsignedByte > 57) {
            return _handleInvalidNumberStart(readUnsignedByte, z2, true);
        } else {
            i4 = this._inputData.readUnsignedByte();
        }
        char[] cArr = emptyAndGetCurrentSegment;
        int i6 = 1;
        int i7 = i4;
        int i8 = i5;
        while (i7 <= 57 && i7 >= 48) {
            i6++;
            if (i8 >= cArr.length) {
                cArr = this._textBuffer.finishCurrentSegment();
                i8 = 0;
            }
            cArr[i8] = (char) i7;
            i7 = this._inputData.readUnsignedByte();
            i8++;
        }
        if (i7 == 46 || i7 == 101 || i7 == 69) {
            return _parseFloat(cArr, i8, i7, z2, i6);
        }
        this._textBuffer.setCurrentLength(i8);
        this._nextByte = i7;
        if (this._parsingContext.inRoot()) {
            _verifyRootSpace();
        }
        return resetInt(z2, i6);
    }

    private void _reportInvalidOther(int i3) throws JsonParseException {
        _reportError("Invalid UTF-8 middle byte 0x" + Integer.toHexString(i3));
    }

    private final void _skipCComment() throws IOException {
        int[] inputCodeComment = CharTypes.getInputCodeComment();
        int readUnsignedByte = this._inputData.readUnsignedByte();
        while (true) {
            int i3 = inputCodeComment[readUnsignedByte];
            if (i3 != 0) {
                if (i3 == 2) {
                    _skipUtf8_2();
                } else if (i3 == 3) {
                    _skipUtf8_3();
                } else if (i3 == 4) {
                    _skipUtf8_4();
                } else if (i3 == 10 || i3 == 13) {
                    this._currInputRow++;
                } else if (i3 != 42) {
                    _reportInvalidChar(readUnsignedByte);
                } else {
                    readUnsignedByte = this._inputData.readUnsignedByte();
                    if (readUnsignedByte == 47) {
                        return;
                    }
                }
            }
            readUnsignedByte = this._inputData.readUnsignedByte();
        }
    }

    private final int _skipColon() throws IOException {
        int i3 = this._nextByte;
        if (i3 < 0) {
            i3 = this._inputData.readUnsignedByte();
        } else {
            this._nextByte = -1;
        }
        if (i3 == 58) {
            int readUnsignedByte = this._inputData.readUnsignedByte();
            return readUnsignedByte > 32 ? (readUnsignedByte == 47 || readUnsignedByte == 35) ? _skipColon2(readUnsignedByte, true) : readUnsignedByte : ((readUnsignedByte == 32 || readUnsignedByte == 9) && (readUnsignedByte = this._inputData.readUnsignedByte()) > 32) ? (readUnsignedByte == 47 || readUnsignedByte == 35) ? _skipColon2(readUnsignedByte, true) : readUnsignedByte : _skipColon2(readUnsignedByte, true);
        }
        if (i3 == 32 || i3 == 9) {
            i3 = this._inputData.readUnsignedByte();
        }
        if (i3 != 58) {
            return _skipColon2(i3, false);
        }
        int readUnsignedByte2 = this._inputData.readUnsignedByte();
        return readUnsignedByte2 > 32 ? (readUnsignedByte2 == 47 || readUnsignedByte2 == 35) ? _skipColon2(readUnsignedByte2, true) : readUnsignedByte2 : ((readUnsignedByte2 == 32 || readUnsignedByte2 == 9) && (readUnsignedByte2 = this._inputData.readUnsignedByte()) > 32) ? (readUnsignedByte2 == 47 || readUnsignedByte2 == 35) ? _skipColon2(readUnsignedByte2, true) : readUnsignedByte2 : _skipColon2(readUnsignedByte2, true);
    }

    private final int _skipColon2(int i3, boolean z2) throws IOException {
        while (true) {
            if (i3 > 32) {
                if (i3 == 47) {
                    _skipComment();
                } else if (i3 != 35 || !_skipYAMLComment()) {
                    if (z2) {
                        return i3;
                    }
                    if (i3 != 58) {
                        _reportUnexpectedChar(i3, "was expecting a colon to separate field name and value");
                    }
                    z2 = true;
                }
            } else if (i3 == 13 || i3 == 10) {
                this._currInputRow++;
            }
            i3 = this._inputData.readUnsignedByte();
        }
    }

    private final void _skipComment() throws IOException {
        if ((this._features & FEAT_MASK_ALLOW_JAVA_COMMENTS) == 0) {
            _reportUnexpectedChar(47, "maybe a (non-standard) comment? (not recognized as one since Feature 'ALLOW_COMMENTS' not enabled for parser)");
        }
        int readUnsignedByte = this._inputData.readUnsignedByte();
        if (readUnsignedByte == 47) {
            _skipLine();
        } else if (readUnsignedByte == 42) {
            _skipCComment();
        } else {
            _reportUnexpectedChar(readUnsignedByte, "was expecting either '*' or '/' for a comment");
        }
    }

    private final void _skipLine() throws IOException {
        int[] inputCodeComment = CharTypes.getInputCodeComment();
        while (true) {
            int readUnsignedByte = this._inputData.readUnsignedByte();
            int i3 = inputCodeComment[readUnsignedByte];
            if (i3 != 0) {
                if (i3 == 2) {
                    _skipUtf8_2();
                } else if (i3 == 3) {
                    _skipUtf8_3();
                } else if (i3 == 4) {
                    _skipUtf8_4();
                } else if (i3 == 10 || i3 == 13) {
                    this._currInputRow++;
                } else if (i3 != 42 && i3 < 0) {
                    _reportInvalidChar(readUnsignedByte);
                }
            }
        }
        this._currInputRow++;
    }

    private final void _skipUtf8_2() throws IOException {
        int readUnsignedByte = this._inputData.readUnsignedByte();
        if ((readUnsignedByte & 192) != 128) {
            _reportInvalidOther(readUnsignedByte & 255);
        }
    }

    private final void _skipUtf8_3() throws IOException {
        int readUnsignedByte = this._inputData.readUnsignedByte();
        if ((readUnsignedByte & 192) != 128) {
            _reportInvalidOther(readUnsignedByte & 255);
        }
        int readUnsignedByte2 = this._inputData.readUnsignedByte();
        if ((readUnsignedByte2 & 192) != 128) {
            _reportInvalidOther(readUnsignedByte2 & 255);
        }
    }

    private final void _skipUtf8_4() throws IOException {
        int readUnsignedByte = this._inputData.readUnsignedByte();
        if ((readUnsignedByte & 192) != 128) {
            _reportInvalidOther(readUnsignedByte & 255);
        }
        int readUnsignedByte2 = this._inputData.readUnsignedByte();
        if ((readUnsignedByte2 & 192) != 128) {
            _reportInvalidOther(readUnsignedByte2 & 255);
        }
        int readUnsignedByte3 = this._inputData.readUnsignedByte();
        if ((readUnsignedByte3 & 192) != 128) {
            _reportInvalidOther(readUnsignedByte3 & 255);
        }
    }

    private final int _skipWS() throws IOException {
        int i3 = this._nextByte;
        if (i3 < 0) {
            i3 = this._inputData.readUnsignedByte();
        } else {
            this._nextByte = -1;
        }
        while (i3 <= 32) {
            if (i3 == 13 || i3 == 10) {
                this._currInputRow++;
            }
            i3 = this._inputData.readUnsignedByte();
        }
        return (i3 == 47 || i3 == 35) ? _skipWSComment(i3) : i3;
    }

    private final int _skipWSComment(int i3) throws IOException {
        while (true) {
            if (i3 > 32) {
                if (i3 == 47) {
                    _skipComment();
                } else if (i3 != 35 || !_skipYAMLComment()) {
                    return i3;
                }
            } else if (i3 == 13 || i3 == 10) {
                this._currInputRow++;
            }
            i3 = this._inputData.readUnsignedByte();
        }
        return i3;
    }

    private final int _skipWSOrEnd() throws IOException {
        int i3 = this._nextByte;
        if (i3 < 0) {
            try {
                i3 = this._inputData.readUnsignedByte();
            } catch (EOFException unused) {
                return _eofAsNextChar();
            }
        } else {
            this._nextByte = -1;
        }
        while (i3 <= 32) {
            if (i3 == 13 || i3 == 10) {
                this._currInputRow++;
            }
            try {
                i3 = this._inputData.readUnsignedByte();
            } catch (EOFException unused2) {
                return _eofAsNextChar();
            }
        }
        return (i3 == 47 || i3 == 35) ? _skipWSComment(i3) : i3;
    }

    private final boolean _skipYAMLComment() throws IOException {
        if ((this._features & FEAT_MASK_ALLOW_YAML_COMMENTS) == 0) {
            return false;
        }
        _skipLine();
        return true;
    }

    private final void _verifyRootSpace() throws IOException {
        int i3 = this._nextByte;
        if (i3 <= 32) {
            this._nextByte = -1;
            if (i3 == 13 || i3 == 10) {
                this._currInputRow++;
                return;
            }
            return;
        }
        _reportMissingRootWS(i3);
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
        throw new UnsupportedOperationException("Method not decompiled: com.fasterxml.jackson.core.json.UTF8DataInputJsonParser.addName(int[], int, int):java.lang.String");
    }

    private final String findName(int i3, int i4) throws JsonParseException {
        int pad = pad(i3, i4);
        String findName = this._symbols.findName(pad);
        if (findName != null) {
            return findName;
        }
        int[] iArr = this._quadBuffer;
        iArr[0] = pad;
        return addName(iArr, 1, i4);
    }

    private static final int pad(int i3, int i4) {
        return i4 == 4 ? i3 : i3 | (-1 << (i4 << 3));
    }

    private final String parseName(int i3, int i4, int i5) throws IOException {
        return parseEscapedName(this._quadBuffer, 0, i3, i4, i5);
    }

    public void _closeInput() throws IOException {
    }

    public final byte[] _decodeBase64(Base64Variant base64Variant) throws IOException {
        int readUnsignedByte;
        ByteArrayBuilder _getByteArrayBuilder = _getByteArrayBuilder();
        while (true) {
            int readUnsignedByte2 = this._inputData.readUnsignedByte();
            if (readUnsignedByte2 > 32) {
                int decodeBase64Char = base64Variant.decodeBase64Char(readUnsignedByte2);
                if (decodeBase64Char < 0) {
                    if (readUnsignedByte2 == 34) {
                        return _getByteArrayBuilder.toByteArray();
                    }
                    decodeBase64Char = _decodeBase64Escape(base64Variant, readUnsignedByte2, 0);
                    if (decodeBase64Char < 0) {
                        continue;
                    }
                }
                int readUnsignedByte3 = this._inputData.readUnsignedByte();
                int decodeBase64Char2 = base64Variant.decodeBase64Char(readUnsignedByte3);
                if (decodeBase64Char2 < 0) {
                    decodeBase64Char2 = _decodeBase64Escape(base64Variant, readUnsignedByte3, 1);
                }
                int i3 = (decodeBase64Char << 6) | decodeBase64Char2;
                int readUnsignedByte4 = this._inputData.readUnsignedByte();
                int decodeBase64Char3 = base64Variant.decodeBase64Char(readUnsignedByte4);
                if (decodeBase64Char3 < 0) {
                    if (decodeBase64Char3 != -2) {
                        if (readUnsignedByte4 == 34) {
                            _getByteArrayBuilder.append(i3 >> 4);
                            if (base64Variant.usesPadding()) {
                                _handleBase64MissingPadding(base64Variant);
                            }
                            return _getByteArrayBuilder.toByteArray();
                        }
                        decodeBase64Char3 = _decodeBase64Escape(base64Variant, readUnsignedByte4, 2);
                    }
                    if (decodeBase64Char3 == -2) {
                        readUnsignedByte = this._inputData.readUnsignedByte();
                        if (base64Variant.usesPaddingChar(readUnsignedByte) || (readUnsignedByte == 92 && _decodeBase64Escape(base64Variant, readUnsignedByte, 3) == -2)) {
                            _getByteArrayBuilder.append(i3 >> 4);
                        }
                    }
                }
                int i4 = (i3 << 6) | decodeBase64Char3;
                int readUnsignedByte5 = this._inputData.readUnsignedByte();
                int decodeBase64Char4 = base64Variant.decodeBase64Char(readUnsignedByte5);
                if (decodeBase64Char4 < 0) {
                    if (decodeBase64Char4 != -2) {
                        if (readUnsignedByte5 == 34) {
                            _getByteArrayBuilder.appendTwoBytes(i4 >> 2);
                            if (base64Variant.usesPadding()) {
                                _handleBase64MissingPadding(base64Variant);
                            }
                            return _getByteArrayBuilder.toByteArray();
                        }
                        decodeBase64Char4 = _decodeBase64Escape(base64Variant, readUnsignedByte5, 3);
                    }
                    if (decodeBase64Char4 == -2) {
                        _getByteArrayBuilder.appendTwoBytes(i4 >> 2);
                    }
                }
                _getByteArrayBuilder.appendThreeBytes((i4 << 6) | decodeBase64Char4);
            }
        }
        throw reportInvalidBase64Char(base64Variant, readUnsignedByte, 3, "expected padding character '" + base64Variant.getPaddingChar() + "'");
    }

    /* JADX WARNING: Removed duplicated region for block: B:15:0x0038  */
    /* JADX WARNING: Removed duplicated region for block: B:18:0x0044  */
    /* JADX WARNING: Removed duplicated region for block: B:29:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public int _decodeCharForError(int r7) throws java.io.IOException {
        /*
            r6 = this;
            r0 = r7 & 255(0xff, float:3.57E-43)
            r1 = 127(0x7f, float:1.78E-43)
            if (r0 <= r1) goto L_0x006f
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
            java.io.DataInput r1 = r6._inputData
            int r1 = r1.readUnsignedByte()
            r4 = r1 & 192(0xc0, float:2.69E-43)
            r5 = 128(0x80, float:1.794E-43)
            if (r4 == r5) goto L_0x003d
            r4 = r1 & 255(0xff, float:3.57E-43)
            r6._reportInvalidOther(r4)
        L_0x003d:
            int r0 = r0 << 6
            r1 = r1 & 63
            r0 = r0 | r1
            if (r7 <= r3) goto L_0x006f
            java.io.DataInput r1 = r6._inputData
            int r1 = r1.readUnsignedByte()
            r3 = r1 & 192(0xc0, float:2.69E-43)
            if (r3 == r5) goto L_0x0053
            r3 = r1 & 255(0xff, float:3.57E-43)
            r6._reportInvalidOther(r3)
        L_0x0053:
            int r0 = r0 << 6
            r1 = r1 & 63
            r0 = r0 | r1
            if (r7 <= r2) goto L_0x006f
            java.io.DataInput r7 = r6._inputData
            int r7 = r7.readUnsignedByte()
            r1 = r7 & 192(0xc0, float:2.69E-43)
            if (r1 == r5) goto L_0x0069
            r1 = r7 & 255(0xff, float:3.57E-43)
            r6._reportInvalidOther(r1)
        L_0x0069:
            int r6 = r0 << 6
            r7 = r7 & 63
            r0 = r6 | r7
        L_0x006f:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.fasterxml.jackson.core.json.UTF8DataInputJsonParser._decodeCharForError(int):int");
    }

    public char _decodeEscaped() throws IOException {
        int readUnsignedByte = this._inputData.readUnsignedByte();
        if (readUnsignedByte == 34 || readUnsignedByte == 47 || readUnsignedByte == 92) {
            return (char) readUnsignedByte;
        }
        if (readUnsignedByte == 98) {
            return 8;
        }
        if (readUnsignedByte == 102) {
            return 12;
        }
        if (readUnsignedByte == 110) {
            return 10;
        }
        if (readUnsignedByte == 114) {
            return CharUtils.CR;
        }
        if (readUnsignedByte == 116) {
            return 9;
        }
        if (readUnsignedByte != 117) {
            return _handleUnrecognizedCharacterEscape((char) _decodeCharForError(readUnsignedByte));
        }
        int i3 = 0;
        for (int i4 = 0; i4 < 4; i4++) {
            int readUnsignedByte2 = this._inputData.readUnsignedByte();
            int charToHex = CharTypes.charToHex(readUnsignedByte2);
            if (charToHex < 0) {
                _reportUnexpectedChar(readUnsignedByte2, "expected a hex-digit for character escape sequence");
            }
            i3 = (i3 << 4) | charToHex;
        }
        return (char) i3;
    }

    public void _finishString() throws IOException {
        char[] emptyAndGetCurrentSegment = this._textBuffer.emptyAndGetCurrentSegment();
        int[] iArr = _icUTF8;
        int length = emptyAndGetCurrentSegment.length;
        int i3 = 0;
        while (true) {
            int readUnsignedByte = this._inputData.readUnsignedByte();
            if (iArr[readUnsignedByte] == 0) {
                int i4 = i3 + 1;
                emptyAndGetCurrentSegment[i3] = (char) readUnsignedByte;
                if (i4 >= length) {
                    _finishString2(emptyAndGetCurrentSegment, i4, this._inputData.readUnsignedByte());
                    return;
                }
                i3 = i4;
            } else if (readUnsignedByte == 34) {
                this._textBuffer.setCurrentLength(i3);
                return;
            } else {
                _finishString2(emptyAndGetCurrentSegment, i3, readUnsignedByte);
                return;
            }
        }
    }

    public final String _getText2(JsonToken jsonToken) {
        if (jsonToken == null) {
            return null;
        }
        int id = jsonToken.id();
        return id != 5 ? (id == 6 || id == 7 || id == 8) ? this._textBuffer.contentsAsString() : jsonToken.asString() : this._parsingContext.getCurrentName();
    }

    public JsonToken _handleApos() throws IOException {
        int i3;
        char[] emptyAndGetCurrentSegment = this._textBuffer.emptyAndGetCurrentSegment();
        int[] iArr = _icUTF8;
        int i4 = 0;
        while (true) {
            int length = emptyAndGetCurrentSegment.length;
            if (i4 >= emptyAndGetCurrentSegment.length) {
                emptyAndGetCurrentSegment = this._textBuffer.finishCurrentSegment();
                length = emptyAndGetCurrentSegment.length;
                i4 = 0;
            }
            while (true) {
                int readUnsignedByte = this._inputData.readUnsignedByte();
                if (readUnsignedByte == 39) {
                    this._textBuffer.setCurrentLength(i3);
                    return JsonToken.VALUE_STRING;
                }
                int i5 = iArr[readUnsignedByte];
                if (i5 == 0 || readUnsignedByte == 34) {
                    int i6 = i3 + 1;
                    emptyAndGetCurrentSegment[i3] = (char) readUnsignedByte;
                    i4 = i6;
                    if (i6 >= length) {
                        break;
                    }
                } else {
                    if (i5 == 1) {
                        readUnsignedByte = _decodeEscaped();
                    } else if (i5 == 2) {
                        readUnsignedByte = _decodeUtf8_2(readUnsignedByte);
                    } else if (i5 == 3) {
                        readUnsignedByte = _decodeUtf8_3(readUnsignedByte);
                    } else if (i5 != 4) {
                        if (readUnsignedByte < 32) {
                            _throwUnquotedSpace(readUnsignedByte, "string value");
                        }
                        _reportInvalidChar(readUnsignedByte);
                    } else {
                        int _decodeUtf8_4 = _decodeUtf8_4(readUnsignedByte);
                        int i7 = i3 + 1;
                        emptyAndGetCurrentSegment[i3] = (char) ((_decodeUtf8_4 >> 10) | GeneratorBase.SURR1_FIRST);
                        if (i7 >= emptyAndGetCurrentSegment.length) {
                            emptyAndGetCurrentSegment = this._textBuffer.finishCurrentSegment();
                            i3 = 0;
                        } else {
                            i3 = i7;
                        }
                        readUnsignedByte = 56320 | (_decodeUtf8_4 & 1023);
                    }
                    if (i3 >= emptyAndGetCurrentSegment.length) {
                        emptyAndGetCurrentSegment = this._textBuffer.finishCurrentSegment();
                        i3 = 0;
                    }
                    emptyAndGetCurrentSegment[i3] = (char) readUnsignedByte;
                    i4 = i3 + 1;
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
        do {
            if (i4 < 4) {
                i4++;
                i6 = i3 | (i6 << 8);
            } else {
                if (i5 >= iArr.length) {
                    iArr = _growArrayBy(iArr, iArr.length);
                    this._quadBuffer = iArr;
                }
                iArr[i5] = i6;
                i6 = i3;
                i5++;
                i4 = 1;
            }
            i3 = this._inputData.readUnsignedByte();
        } while (inputCodeUtf8JsNames[i3] == 0);
        this._nextByte = i3;
        if (i4 > 0) {
            if (i5 >= iArr.length) {
                iArr = _growArrayBy(iArr, iArr.length);
                this._quadBuffer = iArr;
            }
            iArr[i5] = i6;
            i5++;
        }
        String findName = this._symbols.findName(iArr, i5);
        return findName == null ? addName(iArr, i5, i4) : findName;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:13:0x001b, code lost:
        if (r4 != 44) goto L_0x008b;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0030, code lost:
        if (r3._parsingContext.inArray() == false) goto L_0x008b;
     */
    /* JADX WARNING: Removed duplicated region for block: B:41:0x0091  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.fasterxml.jackson.core.JsonToken _handleUnexpectedValue(int r4) throws java.io.IOException {
        /*
            r3 = this;
            r0 = 39
            if (r4 == r0) goto L_0x007f
            r0 = 73
            r1 = 1
            if (r4 == r0) goto L_0x0066
            r0 = 78
            if (r4 == r0) goto L_0x004d
            r0 = 93
            if (r4 == r0) goto L_0x002a
            r0 = 125(0x7d, float:1.75E-43)
            if (r4 == r0) goto L_0x0047
            r0 = 43
            if (r4 == r0) goto L_0x001e
            r0 = 44
            if (r4 == r0) goto L_0x0033
            goto L_0x008b
        L_0x001e:
            java.io.DataInput r4 = r3._inputData
            int r4 = r4.readUnsignedByte()
            r0 = 0
            com.fasterxml.jackson.core.JsonToken r3 = r3._handleInvalidNumberStart(r4, r0, r1)
            return r3
        L_0x002a:
            com.fasterxml.jackson.core.json.JsonReadContext r0 = r3._parsingContext
            boolean r0 = r0.inArray()
            if (r0 != 0) goto L_0x0033
            goto L_0x008b
        L_0x0033:
            com.fasterxml.jackson.core.json.JsonReadContext r0 = r3._parsingContext
            boolean r0 = r0.inRoot()
            if (r0 != 0) goto L_0x0047
            int r0 = r3._features
            int r1 = FEAT_MASK_ALLOW_MISSING
            r0 = r0 & r1
            if (r0 == 0) goto L_0x0047
            r3._nextByte = r4
            com.fasterxml.jackson.core.JsonToken r3 = com.fasterxml.jackson.core.JsonToken.VALUE_NULL
            return r3
        L_0x0047:
            java.lang.String r0 = "expected a value"
            r3._reportUnexpectedChar(r4, r0)
            goto L_0x007f
        L_0x004d:
            java.lang.String r0 = "NaN"
            r3._matchToken(r0, r1)
            int r1 = r3._features
            int r2 = FEAT_MASK_NON_NUM_NUMBERS
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0060
            r1 = 9221120237041090560(0x7ff8000000000000, double:NaN)
            com.fasterxml.jackson.core.JsonToken r3 = r3.resetAsNaN(r0, r1)
            return r3
        L_0x0060:
            java.lang.String r0 = "Non-standard token 'NaN': enable `JsonReadFeature.ALLOW_NON_NUMERIC_NUMBERS` to allow"
            r3._reportError(r0)
            goto L_0x008b
        L_0x0066:
            java.lang.String r0 = "Infinity"
            r3._matchToken(r0, r1)
            int r1 = r3._features
            int r2 = FEAT_MASK_NON_NUM_NUMBERS
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0079
            r1 = 9218868437227405312(0x7ff0000000000000, double:Infinity)
            com.fasterxml.jackson.core.JsonToken r3 = r3.resetAsNaN(r0, r1)
            return r3
        L_0x0079:
            java.lang.String r0 = "Non-standard token 'Infinity': enable `JsonReadFeature.ALLOW_NON_NUMERIC_NUMBERS` to allow"
            r3._reportError(r0)
            goto L_0x008b
        L_0x007f:
            int r0 = r3._features
            int r1 = FEAT_MASK_ALLOW_SINGLE_QUOTES
            r0 = r0 & r1
            if (r0 == 0) goto L_0x008b
            com.fasterxml.jackson.core.JsonToken r3 = r3._handleApos()
            return r3
        L_0x008b:
            boolean r0 = java.lang.Character.isJavaIdentifierStart(r4)
            if (r0 == 0) goto L_0x00a7
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            java.lang.String r1 = ""
            r0.<init>(r1)
            char r1 = (char) r4
            r0.append(r1)
            java.lang.String r0 = r0.toString()
            java.lang.String r1 = r3._validJsonTokenList()
            r3._reportInvalidToken(r4, r0, r1)
        L_0x00a7:
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
        throw new UnsupportedOperationException("Method not decompiled: com.fasterxml.jackson.core.json.UTF8DataInputJsonParser._handleUnexpectedValue(int):com.fasterxml.jackson.core.JsonToken");
    }

    public final void _matchToken(String str, int i3) throws IOException {
        int length = str.length();
        do {
            int readUnsignedByte = this._inputData.readUnsignedByte();
            if (readUnsignedByte != str.charAt(i3)) {
                _reportInvalidToken(readUnsignedByte, str.substring(0, i3));
            }
            i3++;
        } while (i3 < length);
        int readUnsignedByte2 = this._inputData.readUnsignedByte();
        if (!(readUnsignedByte2 < 48 || readUnsignedByte2 == 93 || readUnsignedByte2 == 125)) {
            _checkMatchEnd(str, i3, readUnsignedByte2);
        }
        this._nextByte = readUnsignedByte2;
    }

    public String _parseAposName() throws IOException {
        int i3;
        int i4;
        int readUnsignedByte = this._inputData.readUnsignedByte();
        if (readUnsignedByte == 39) {
            return "";
        }
        int[] iArr = this._quadBuffer;
        int[] iArr2 = _icLatin1;
        int i5 = 0;
        int i6 = 0;
        int i7 = 0;
        while (readUnsignedByte != 39) {
            if (!(readUnsignedByte == 34 || iArr2[readUnsignedByte] == 0)) {
                if (readUnsignedByte != 92) {
                    _throwUnquotedSpace(readUnsignedByte, "name");
                } else {
                    readUnsignedByte = _decodeEscaped();
                }
                if (readUnsignedByte > 127) {
                    if (i4 >= 4) {
                        if (i6 >= iArr.length) {
                            iArr = _growArrayBy(iArr, iArr.length);
                            this._quadBuffer = iArr;
                        }
                        iArr[i6] = i3;
                        i3 = 0;
                        i6++;
                        i4 = 0;
                    }
                    if (readUnsignedByte < 2048) {
                        i3 = (i3 << 8) | (readUnsignedByte >> 6) | 192;
                        i4++;
                    } else {
                        int i8 = (i3 << 8) | (readUnsignedByte >> 12) | 224;
                        int i9 = i4 + 1;
                        if (i9 >= 4) {
                            if (i6 >= iArr.length) {
                                iArr = _growArrayBy(iArr, iArr.length);
                                this._quadBuffer = iArr;
                            }
                            iArr[i6] = i8;
                            i8 = 0;
                            i6++;
                            i9 = 0;
                        }
                        i3 = (i8 << 8) | ((readUnsignedByte >> 6) & 63) | 128;
                        i4 = i9 + 1;
                    }
                    readUnsignedByte = (readUnsignedByte & 63) | 128;
                }
            }
            if (i4 < 4) {
                i5 = i4 + 1;
                i7 = readUnsignedByte | (i3 << 8);
            } else {
                if (i6 >= iArr.length) {
                    iArr = _growArrayBy(iArr, iArr.length);
                    this._quadBuffer = iArr;
                }
                iArr[i6] = i3;
                i7 = readUnsignedByte;
                i6++;
                i5 = 1;
            }
            readUnsignedByte = this._inputData.readUnsignedByte();
        }
        if (i4 > 0) {
            if (i6 >= iArr.length) {
                iArr = _growArrayBy(iArr, iArr.length);
                this._quadBuffer = iArr;
            }
            iArr[i6] = pad(i3, i4);
            i6++;
        }
        String findName = this._symbols.findName(iArr, i6);
        return findName == null ? addName(iArr, i6, i4) : findName;
    }

    @Deprecated
    public final JsonToken _parseFloatThatStartsWithPeriod() throws IOException {
        return _parseFloatThatStartsWithPeriod(false, false);
    }

    public final String _parseName(int i3) throws IOException {
        if (i3 != 34) {
            return _handleOddName(i3);
        }
        int[] iArr = _icLatin1;
        int readUnsignedByte = this._inputData.readUnsignedByte();
        if (iArr[readUnsignedByte] != 0) {
            return readUnsignedByte == 34 ? "" : parseName(0, readUnsignedByte, 0);
        }
        int readUnsignedByte2 = this._inputData.readUnsignedByte();
        if (iArr[readUnsignedByte2] != 0) {
            return readUnsignedByte2 == 34 ? findName(readUnsignedByte, 1) : parseName(readUnsignedByte, readUnsignedByte2, 1);
        }
        int i4 = (readUnsignedByte << 8) | readUnsignedByte2;
        int readUnsignedByte3 = this._inputData.readUnsignedByte();
        if (iArr[readUnsignedByte3] != 0) {
            return readUnsignedByte3 == 34 ? findName(i4, 2) : parseName(i4, readUnsignedByte3, 2);
        }
        int i5 = (i4 << 8) | readUnsignedByte3;
        int readUnsignedByte4 = this._inputData.readUnsignedByte();
        if (iArr[readUnsignedByte4] != 0) {
            return readUnsignedByte4 == 34 ? findName(i5, 3) : parseName(i5, readUnsignedByte4, 3);
        }
        int i6 = (i5 << 8) | readUnsignedByte4;
        int readUnsignedByte5 = this._inputData.readUnsignedByte();
        if (iArr[readUnsignedByte5] != 0) {
            return readUnsignedByte5 == 34 ? findName(i6, 4) : parseName(i6, readUnsignedByte5, 4);
        }
        this._quad1 = i6;
        return _parseMediumName(readUnsignedByte5);
    }

    public final JsonToken _parseNegNumber() throws IOException {
        return _parseSignedNumber(true);
    }

    public final JsonToken _parsePosNumber() throws IOException {
        return _parseSignedNumber(false);
    }

    public JsonToken _parseUnsignedNumber(int i3) throws IOException {
        int i4;
        char[] emptyAndGetCurrentSegment = this._textBuffer.emptyAndGetCurrentSegment();
        int i5 = 1;
        if (i3 == 48) {
            i4 = _handleLeadingZeroes();
            if (i4 <= 57 && i4 >= 48) {
                i5 = 0;
            } else if (i4 == 120 || i4 == 88) {
                return _handleInvalidNumberStart(i4, false);
            } else {
                emptyAndGetCurrentSegment[0] = '0';
            }
        } else {
            emptyAndGetCurrentSegment[0] = (char) i3;
            i4 = this._inputData.readUnsignedByte();
        }
        int i6 = i4;
        char[] cArr = emptyAndGetCurrentSegment;
        int i7 = i5;
        int i8 = i7;
        while (i6 <= 57 && i6 >= 48) {
            i8++;
            if (i7 >= cArr.length) {
                cArr = this._textBuffer.finishCurrentSegment();
                i7 = 0;
            }
            cArr[i7] = (char) i6;
            i6 = this._inputData.readUnsignedByte();
            i7++;
        }
        if (i6 == 46 || i6 == 101 || i6 == 69) {
            return _parseFloat(cArr, i7, i6, false, i8);
        }
        this._textBuffer.setCurrentLength(i7);
        if (this._parsingContext.inRoot()) {
            _verifyRootSpace();
        } else {
            this._nextByte = i6;
        }
        return resetInt(false, i8);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:39:0x00ce, code lost:
        r11._tokenIncomplete = false;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:40:0x00d0, code lost:
        if (r3 <= 0) goto L_?;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:41:0x00d2, code lost:
        r4 = r4 + r3;
        r13.write(r14, 0, r3);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:57:?, code lost:
        return r4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:58:?, code lost:
        return r4;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public int _readBinary(com.fasterxml.jackson.core.Base64Variant r12, java.io.OutputStream r13, byte[] r14) throws java.io.IOException {
        /*
            r11 = this;
            int r0 = r14.length
            r1 = 3
            int r0 = r0 - r1
            r2 = 0
            r3 = r2
            r4 = r3
        L_0x0006:
            java.io.DataInput r5 = r11._inputData
            int r5 = r5.readUnsignedByte()
            r6 = 32
            if (r5 <= r6) goto L_0x0006
            int r6 = r12.decodeBase64Char((int) r5)
            r7 = 34
            if (r6 >= 0) goto L_0x0023
            if (r5 != r7) goto L_0x001c
            goto L_0x00ce
        L_0x001c:
            int r6 = r11._decodeBase64Escape((com.fasterxml.jackson.core.Base64Variant) r12, (int) r5, (int) r2)
            if (r6 >= 0) goto L_0x0023
            goto L_0x0006
        L_0x0023:
            if (r3 <= r0) goto L_0x002a
            int r4 = r4 + r3
            r13.write(r14, r2, r3)
            r3 = r2
        L_0x002a:
            java.io.DataInput r5 = r11._inputData
            int r5 = r5.readUnsignedByte()
            int r8 = r12.decodeBase64Char((int) r5)
            if (r8 >= 0) goto L_0x003b
            r8 = 1
            int r8 = r11._decodeBase64Escape((com.fasterxml.jackson.core.Base64Variant) r12, (int) r5, (int) r8)
        L_0x003b:
            int r5 = r6 << 6
            r5 = r5 | r8
            java.io.DataInput r6 = r11._inputData
            int r6 = r6.readUnsignedByte()
            int r8 = r12.decodeBase64Char((int) r6)
            r9 = -2
            r10 = 2
            if (r8 >= 0) goto L_0x00a5
            if (r8 == r9) goto L_0x0066
            if (r6 != r7) goto L_0x0062
            int r0 = r5 >> 4
            int r1 = r3 + 1
            byte r0 = (byte) r0
            r14[r3] = r0
            boolean r0 = r12.usesPadding()
            if (r0 == 0) goto L_0x0060
            r11._handleBase64MissingPadding(r12)
        L_0x0060:
            r3 = r1
            goto L_0x00ce
        L_0x0062:
            int r8 = r11._decodeBase64Escape((com.fasterxml.jackson.core.Base64Variant) r12, (int) r6, (int) r10)
        L_0x0066:
            if (r8 != r9) goto L_0x00a5
            java.io.DataInput r6 = r11._inputData
            int r6 = r6.readUnsignedByte()
            boolean r7 = r12.usesPaddingChar((int) r6)
            if (r7 != 0) goto L_0x009b
            r7 = 92
            if (r6 != r7) goto L_0x007f
            int r7 = r11._decodeBase64Escape((com.fasterxml.jackson.core.Base64Variant) r12, (int) r6, (int) r1)
            if (r7 != r9) goto L_0x007f
            goto L_0x009b
        L_0x007f:
            java.lang.StringBuilder r13 = new java.lang.StringBuilder
            java.lang.String r14 = "expected padding character '"
            r13.<init>(r14)
            char r14 = r12.getPaddingChar()
            r13.append(r14)
            java.lang.String r14 = "'"
            r13.append(r14)
            java.lang.String r13 = r13.toString()
            java.lang.IllegalArgumentException r11 = r11.reportInvalidBase64Char(r12, r6, r1, r13)
            throw r11
        L_0x009b:
            int r5 = r5 >> 4
            int r6 = r3 + 1
            byte r5 = (byte) r5
            r14[r3] = r5
            r3 = r6
            goto L_0x0006
        L_0x00a5:
            int r5 = r5 << 6
            r5 = r5 | r8
            java.io.DataInput r6 = r11._inputData
            int r6 = r6.readUnsignedByte()
            int r8 = r12.decodeBase64Char((int) r6)
            if (r8 >= 0) goto L_0x00ed
            if (r8 == r9) goto L_0x00db
            if (r6 != r7) goto L_0x00d7
            int r0 = r5 >> 2
            int r1 = r3 + 1
            int r5 = r5 >> 10
            byte r5 = (byte) r5
            r14[r3] = r5
            int r3 = r3 + r10
            byte r0 = (byte) r0
            r14[r1] = r0
            boolean r0 = r12.usesPadding()
            if (r0 == 0) goto L_0x00ce
            r11._handleBase64MissingPadding(r12)
        L_0x00ce:
            r11._tokenIncomplete = r2
            if (r3 <= 0) goto L_0x00d6
            int r4 = r4 + r3
            r13.write(r14, r2, r3)
        L_0x00d6:
            return r4
        L_0x00d7:
            int r8 = r11._decodeBase64Escape((com.fasterxml.jackson.core.Base64Variant) r12, (int) r6, (int) r1)
        L_0x00db:
            if (r8 != r9) goto L_0x00ed
            int r6 = r5 >> 2
            int r7 = r3 + 1
            int r5 = r5 >> 10
            byte r5 = (byte) r5
            r14[r3] = r5
            int r3 = r3 + 2
            byte r5 = (byte) r6
            r14[r7] = r5
            goto L_0x0006
        L_0x00ed:
            int r5 = r5 << 6
            r5 = r5 | r8
            int r6 = r3 + 1
            int r7 = r5 >> 16
            byte r7 = (byte) r7
            r14[r3] = r7
            int r7 = r3 + 2
            int r8 = r5 >> 8
            byte r8 = (byte) r8
            r14[r6] = r8
            int r3 = r3 + 3
            byte r5 = (byte) r5
            r14[r7] = r5
            goto L_0x0006
        */
        throw new UnsupportedOperationException("Method not decompiled: com.fasterxml.jackson.core.json.UTF8DataInputJsonParser._readBinary(com.fasterxml.jackson.core.Base64Variant, java.io.OutputStream, byte[]):int");
    }

    public void _releaseBuffers() throws IOException {
        super._releaseBuffers();
        this._symbols.release();
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

    public void _reportInvalidToken(int i3, String str) throws IOException {
        _reportInvalidToken(i3, str, _validJsonTokenList());
    }

    public void _skipString() throws IOException {
        this._tokenIncomplete = false;
        int[] iArr = _icUTF8;
        while (true) {
            int readUnsignedByte = this._inputData.readUnsignedByte();
            int i3 = iArr[readUnsignedByte];
            if (i3 != 0) {
                if (readUnsignedByte != 34) {
                    if (i3 == 1) {
                        _decodeEscaped();
                    } else if (i3 == 2) {
                        _skipUtf8_2();
                    } else if (i3 == 3) {
                        _skipUtf8_3();
                    } else if (i3 == 4) {
                        _skipUtf8_4();
                    } else if (readUnsignedByte < 32) {
                        _throwUnquotedSpace(readUnsignedByte, "string value");
                    } else {
                        _reportInvalidChar(readUnsignedByte);
                    }
                } else {
                    return;
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
        return new JsonLocation(_contentReference(), -1, -1, this._currInputRow, -1);
    }

    public Object getInputSource() {
        return this._inputData;
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
        if (jsonToken == JsonToken.VALUE_STRING) {
            if (this._tokenIncomplete) {
                this._tokenIncomplete = false;
                _finishString();
            }
            return this._textBuffer.size();
        } else if (jsonToken == JsonToken.FIELD_NAME) {
            return this._parsingContext.getCurrentName().length();
        } else {
            if (jsonToken != null) {
                return jsonToken.isNumeric() ? this._textBuffer.size() : this._currToken.asCharArray().length;
            }
            return 0;
        }
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
        throw new UnsupportedOperationException("Method not decompiled: com.fasterxml.jackson.core.json.UTF8DataInputJsonParser.getTextOffset():int");
    }

    public JsonLocation getTokenLocation() {
        return new JsonLocation(_contentReference(), -1, -1, this._tokenInputRow, -1);
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
        int _skipWS = _skipWS();
        this._binaryValue = null;
        this._tokenInputRow = this._currInputRow;
        if (_skipWS == 93 || _skipWS == 125) {
            _closeScope(_skipWS);
            return null;
        }
        if (this._parsingContext.expectComma()) {
            if (_skipWS != 44) {
                _reportUnexpectedChar(_skipWS, "was expecting comma to separate " + this._parsingContext.typeDesc() + " entries");
            }
            _skipWS = _skipWS();
            if ((this._features & FEAT_MASK_TRAILING_COMMA) != 0 && (_skipWS == 93 || _skipWS == 125)) {
                _closeScope(_skipWS);
                return null;
            }
        }
        if (!this._parsingContext.inObject()) {
            _nextTokenNotInObject(_skipWS);
            return null;
        }
        String _parseName = _parseName(_skipWS);
        this._parsingContext.setCurrentName(_parseName);
        this._currToken = jsonToken3;
        int _skipColon = _skipColon();
        if (_skipColon == 34) {
            this._tokenIncomplete = true;
            this._nextToken = JsonToken.VALUE_STRING;
            return _parseName;
        }
        if (_skipColon == 43) {
            jsonToken = isEnabled(JsonReadFeature.ALLOW_LEADING_PLUS_SIGN_FOR_NUMBERS.mappedFeature()) ? _parsePosNumber() : _handleUnexpectedValue(_skipColon);
        } else if (_skipColon == 91) {
            jsonToken = JsonToken.START_ARRAY;
        } else if (_skipColon == 102) {
            _matchToken("false", 1);
            jsonToken = JsonToken.VALUE_FALSE;
        } else if (_skipColon == 110) {
            _matchToken(AbstractJsonLexerKt.NULL, 1);
            jsonToken = JsonToken.VALUE_NULL;
        } else if (_skipColon == 116) {
            _matchToken("true", 1);
            jsonToken = JsonToken.VALUE_TRUE;
        } else if (_skipColon == 123) {
            jsonToken = JsonToken.START_OBJECT;
        } else if (_skipColon != 45) {
            if (_skipColon != 46) {
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
                        break;
                    default:
                        jsonToken = _handleUnexpectedValue(_skipColon);
                        break;
                }
            } else {
                _parseFloatThatStartsWithPeriod(false, false);
            }
            jsonToken = _parseUnsignedNumber(_skipColon);
        } else {
            jsonToken = _parseNegNumber();
        }
        this._nextToken = jsonToken;
        return _parseName;
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
        if (this._closed) {
            return null;
        }
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
        this._tokenInputRow = this._currInputRow;
        if (_skipWSOrEnd == 93 || _skipWSOrEnd == 125) {
            _closeScope(_skipWSOrEnd);
            return this._currToken;
        }
        if (this._parsingContext.expectComma()) {
            if (_skipWSOrEnd != 44) {
                _reportUnexpectedChar(_skipWSOrEnd, "was expecting comma to separate " + this._parsingContext.typeDesc() + " entries");
            }
            _skipWSOrEnd = _skipWS();
            if ((this._features & FEAT_MASK_TRAILING_COMMA) != 0 && (_skipWSOrEnd == 93 || _skipWSOrEnd == 125)) {
                _closeScope(_skipWSOrEnd);
                return this._currToken;
            }
        }
        if (!this._parsingContext.inObject()) {
            return _nextTokenNotInObject(_skipWSOrEnd);
        }
        this._parsingContext.setCurrentName(_parseName(_skipWSOrEnd));
        this._currToken = jsonToken3;
        int _skipColon = _skipColon();
        if (_skipColon == 34) {
            this._tokenIncomplete = true;
            this._nextToken = JsonToken.VALUE_STRING;
            return this._currToken;
        }
        if (_skipColon == 43) {
            jsonToken = isEnabled(JsonReadFeature.ALLOW_LEADING_PLUS_SIGN_FOR_NUMBERS.mappedFeature()) ? _parsePosNumber() : _handleUnexpectedValue(_skipColon);
        } else if (_skipColon == 91) {
            jsonToken = JsonToken.START_ARRAY;
        } else if (_skipColon == 102) {
            _matchToken("false", 1);
            jsonToken = JsonToken.VALUE_FALSE;
        } else if (_skipColon == 110) {
            _matchToken(AbstractJsonLexerKt.NULL, 1);
            jsonToken = JsonToken.VALUE_NULL;
        } else if (_skipColon == 116) {
            _matchToken("true", 1);
            jsonToken = JsonToken.VALUE_TRUE;
        } else if (_skipColon == 123) {
            jsonToken = JsonToken.START_OBJECT;
        } else if (_skipColon == 45) {
            jsonToken = _parseNegNumber();
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
                            iArr = _growArrayBy(iArr, iArr.length);
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
                                iArr = _growArrayBy(iArr, iArr.length);
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
                    iArr = _growArrayBy(iArr, iArr.length);
                    this._quadBuffer = iArr;
                }
                iArr[i3] = r8;
                i4 = i5;
                i3++;
                i6 = 1;
            }
            i5 = this._inputData.readUnsignedByte();
        }
        if (r10 > 0) {
            if (i3 >= iArr.length) {
                iArr = _growArrayBy(iArr, iArr.length);
                this._quadBuffer = iArr;
            }
            iArr[i3] = pad(r8, r10);
            i3++;
        }
        String findName = this._symbols.findName(iArr, i3);
        return findName == null ? addName(iArr, i3, r10) : findName;
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
        return 0;
    }

    public void setCodec(ObjectCodec objectCodec) {
        this._objectCodec = objectCodec;
    }

    private final String parseName(int i3, int i4, int i5, int i6) throws IOException {
        int[] iArr = this._quadBuffer;
        iArr[0] = i3;
        return parseEscapedName(iArr, 1, i4, i5, i6);
    }

    public JsonToken _handleInvalidNumberStart(int i3, boolean z2, boolean z3) throws IOException {
        String str;
        while (i3 == 73) {
            i3 = this._inputData.readUnsignedByte();
            if (i3 != 78) {
                if (i3 != 110) {
                    break;
                }
                str = z2 ? "-Infinity" : "+Infinity";
            } else {
                str = z2 ? "-INF" : "+INF";
            }
            _matchToken(str, 3);
            if ((this._features & FEAT_MASK_NON_NUM_NUMBERS) != 0) {
                return resetAsNaN(str, z2 ? Double.NEGATIVE_INFINITY : Double.POSITIVE_INFINITY);
            }
            _reportError("Non-standard token '" + str + "': enable `JsonReadFeature.ALLOW_NON_NUMERIC_NUMBERS` to allow");
        }
        if (!isEnabled(JsonReadFeature.ALLOW_LEADING_PLUS_SIGN_FOR_NUMBERS.mappedFeature()) && z3 && !z2) {
            _reportUnexpectedNumberChar(43, "JSON spec does not allow numbers to have plus signs: enable `JsonReadFeature.ALLOW_LEADING_PLUS_SIGN_FOR_NUMBERS` to allow");
        }
        _reportUnexpectedNumberChar(i3, z2 ? "expected digit (0-9) to follow minus sign, for valid numeric value" : "expected digit (0-9) for valid numeric value");
        return null;
    }

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

    public void _reportInvalidToken(int i3, String str, String str2) throws IOException {
        StringBuilder sb = new StringBuilder(str);
        while (true) {
            char _decodeCharForError = (char) _decodeCharForError(i3);
            if (!Character.isJavaIdentifierPart(_decodeCharForError)) {
                _reportError("Unrecognized token '" + sb.toString() + "': was expecting " + str2);
                return;
            }
            sb.append(_decodeCharForError);
            i3 = this._inputData.readUnsignedByte();
        }
    }

    private final String parseName(int i3, int i4, int i5, int i6, int i7) throws IOException {
        int[] iArr = this._quadBuffer;
        iArr[0] = i3;
        iArr[1] = i4;
        return parseEscapedName(iArr, 2, i5, i6, i7);
    }

    private final String findName(int i3, int i4, int i5) throws JsonParseException {
        int pad = pad(i4, i5);
        String findName = this._symbols.findName(i3, pad);
        if (findName != null) {
            return findName;
        }
        int[] iArr = this._quadBuffer;
        iArr[0] = i3;
        iArr[1] = pad;
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
        int pad = pad(i5, i6);
        String findName = this._symbols.findName(i3, i4, pad);
        if (findName != null) {
            return findName;
        }
        int[] iArr = this._quadBuffer;
        iArr[0] = i3;
        iArr[1] = i4;
        iArr[2] = pad(pad, i6);
        return addName(iArr, 3, i6);
    }

    private final String findName(int[] iArr, int i3, int i4, int i5) throws JsonParseException {
        if (i3 >= iArr.length) {
            iArr = _growArrayBy(iArr, iArr.length);
            this._quadBuffer = iArr;
        }
        int i6 = i3 + 1;
        iArr[i3] = pad(i4, i5);
        String findName = this._symbols.findName(iArr, i6);
        return findName == null ? addName(iArr, i6, i5) : findName;
    }
}
