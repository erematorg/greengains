package com.fasterxml.jackson.core.json;

import com.fasterxml.jackson.core.Base64Variant;
import com.fasterxml.jackson.core.JsonLocation;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.core.SerializableString;
import com.fasterxml.jackson.core.StreamReadCapability;
import com.fasterxml.jackson.core.base.ParserBase;
import com.fasterxml.jackson.core.io.CharTypes;
import com.fasterxml.jackson.core.io.IOContext;
import com.fasterxml.jackson.core.sym.CharsToNameCanonicalizer;
import com.fasterxml.jackson.core.util.ByteArrayBuilder;
import com.fasterxml.jackson.core.util.JacksonFeatureSet;
import com.fasterxml.jackson.core.util.TextBuffer;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Reader;
import java.io.Writer;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;
import org.apache.commons.lang3.CharUtils;
import org.objectweb.asm.signature.SignatureVisitor;

public class ReaderBasedJsonParser extends ParserBase {
    private static final int FEAT_MASK_ALLOW_JAVA_COMMENTS = JsonParser.Feature.ALLOW_COMMENTS.getMask();
    private static final int FEAT_MASK_ALLOW_MISSING = JsonParser.Feature.ALLOW_MISSING_VALUES.getMask();
    private static final int FEAT_MASK_ALLOW_SINGLE_QUOTES = JsonParser.Feature.ALLOW_SINGLE_QUOTES.getMask();
    private static final int FEAT_MASK_ALLOW_UNQUOTED_NAMES = JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES.getMask();
    private static final int FEAT_MASK_ALLOW_YAML_COMMENTS = JsonParser.Feature.ALLOW_YAML_COMMENTS.getMask();
    private static final int FEAT_MASK_LEADING_ZEROS = JsonParser.Feature.ALLOW_NUMERIC_LEADING_ZEROS.getMask();
    private static final int FEAT_MASK_NON_NUM_NUMBERS = JsonParser.Feature.ALLOW_NON_NUMERIC_NUMBERS.getMask();
    private static final int FEAT_MASK_TRAILING_COMMA = JsonParser.Feature.ALLOW_TRAILING_COMMA.getMask();
    protected static final int[] _icLatin1 = CharTypes.getInputCodeLatin1();
    protected boolean _bufferRecyclable;
    protected final int _hashSeed;
    protected char[] _inputBuffer;
    protected int _nameStartCol;
    protected long _nameStartOffset;
    protected int _nameStartRow;
    protected ObjectCodec _objectCodec;
    protected Reader _reader;
    protected final CharsToNameCanonicalizer _symbols;
    protected boolean _tokenIncomplete;

    public ReaderBasedJsonParser(IOContext iOContext, int i3, Reader reader, ObjectCodec objectCodec, CharsToNameCanonicalizer charsToNameCanonicalizer, char[] cArr, int i4, int i5, boolean z2) {
        super(iOContext, i3);
        this._reader = reader;
        this._objectCodec = objectCodec;
        this._inputBuffer = cArr;
        this._inputPtr = i4;
        this._inputEnd = i5;
        this._currInputRowStart = i4;
        this._currInputProcessed = (long) (-i4);
        this._symbols = charsToNameCanonicalizer;
        this._hashSeed = charsToNameCanonicalizer.hashSeed();
        this._bufferRecyclable = z2;
    }

    private final void _checkMatchEnd(String str, int i3, int i4) throws IOException {
        if (Character.isJavaIdentifierPart((char) i4)) {
            _reportInvalidToken(str.substring(0, i3));
        }
    }

    private void _closeScope(int i3) throws JsonParseException {
        if (i3 == 93) {
            _updateLocation();
            if (!this._parsingContext.inArray()) {
                _reportMismatchedEndMarker(i3, AbstractJsonLexerKt.END_OBJ);
            }
            this._parsingContext = this._parsingContext.clearAndGetParent();
            this._currToken = JsonToken.END_ARRAY;
        }
        if (i3 == 125) {
            _updateLocation();
            if (!this._parsingContext.inObject()) {
                _reportMismatchedEndMarker(i3, AbstractJsonLexerKt.END_LIST);
            }
            this._parsingContext = this._parsingContext.clearAndGetParent();
            this._currToken = JsonToken.END_OBJECT;
        }
    }

    private String _handleOddName2(int i3, int i4, int[] iArr) throws IOException {
        this._textBuffer.resetWithShared(this._inputBuffer, i3, this._inputPtr - i3);
        char[] currentSegment = this._textBuffer.getCurrentSegment();
        int currentSegmentSize = this._textBuffer.getCurrentSegmentSize();
        int length = iArr.length;
        while (true) {
            if (this._inputPtr >= this._inputEnd && !_loadMore()) {
                break;
            }
            char c3 = this._inputBuffer[this._inputPtr];
            if (c3 >= length) {
                if (!Character.isJavaIdentifierPart(c3)) {
                    break;
                }
            } else if (iArr[c3] != 0) {
                break;
            }
            this._inputPtr++;
            i4 = (i4 * 33) + c3;
            int i5 = currentSegmentSize + 1;
            currentSegment[currentSegmentSize] = c3;
            if (i5 >= currentSegment.length) {
                currentSegment = this._textBuffer.finishCurrentSegment();
                currentSegmentSize = 0;
            } else {
                currentSegmentSize = i5;
            }
        }
        this._textBuffer.setCurrentLength(currentSegmentSize);
        TextBuffer textBuffer = this._textBuffer;
        return this._symbols.findSymbol(textBuffer.getTextBuffer(), textBuffer.getTextOffset(), textBuffer.size(), i4);
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
                _matchToken("false", 1);
                this._nextToken = JsonToken.VALUE_FALSE;
            } else if (i3 == 110) {
                _matchToken(AbstractJsonLexerKt.NULL, 1);
                this._nextToken = JsonToken.VALUE_NULL;
            } else if (i3 == 116) {
                _matchToken("true", 1);
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
                        this._nextToken = _handleOddValue(i3);
                        return;
                }
            } else {
                this._nextToken = _parseFloatThatStartsWithPeriod(false);
            }
        } else if (isEnabled(JsonReadFeature.ALLOW_LEADING_PLUS_SIGN_FOR_NUMBERS.mappedFeature())) {
            this._nextToken = _parseSignedNumber(false);
        } else {
            this._nextToken = _handleOddValue(i3);
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x0028, code lost:
        r0 = r0 + 4;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final void _matchFalse() throws java.io.IOException {
        /*
            r4 = this;
            int r0 = r4._inputPtr
            int r1 = r0 + 4
            int r2 = r4._inputEnd
            if (r1 >= r2) goto L_0x003b
            char[] r1 = r4._inputBuffer
            char r2 = r1[r0]
            r3 = 97
            if (r2 != r3) goto L_0x003b
            int r2 = r0 + 1
            char r2 = r1[r2]
            r3 = 108(0x6c, float:1.51E-43)
            if (r2 != r3) goto L_0x003b
            int r2 = r0 + 2
            char r2 = r1[r2]
            r3 = 115(0x73, float:1.61E-43)
            if (r2 != r3) goto L_0x003b
            int r2 = r0 + 3
            char r2 = r1[r2]
            r3 = 101(0x65, float:1.42E-43)
            if (r2 != r3) goto L_0x003b
            int r0 = r0 + 4
            char r1 = r1[r0]
            r2 = 48
            if (r1 < r2) goto L_0x0038
            r2 = 93
            if (r1 == r2) goto L_0x0038
            r2 = 125(0x7d, float:1.75E-43)
            if (r1 != r2) goto L_0x003b
        L_0x0038:
            r4._inputPtr = r0
            return
        L_0x003b:
            java.lang.String r0 = "false"
            r1 = 1
            r4._matchToken(r0, r1)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.fasterxml.jackson.core.json.ReaderBasedJsonParser._matchFalse():void");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:8:0x001e, code lost:
        r0 = r0 + 3;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final void _matchNull() throws java.io.IOException {
        /*
            r4 = this;
            int r0 = r4._inputPtr
            int r1 = r0 + 3
            int r2 = r4._inputEnd
            if (r1 >= r2) goto L_0x0031
            char[] r1 = r4._inputBuffer
            char r2 = r1[r0]
            r3 = 117(0x75, float:1.64E-43)
            if (r2 != r3) goto L_0x0031
            int r2 = r0 + 1
            char r2 = r1[r2]
            r3 = 108(0x6c, float:1.51E-43)
            if (r2 != r3) goto L_0x0031
            int r2 = r0 + 2
            char r2 = r1[r2]
            if (r2 != r3) goto L_0x0031
            int r0 = r0 + 3
            char r1 = r1[r0]
            r2 = 48
            if (r1 < r2) goto L_0x002e
            r2 = 93
            if (r1 == r2) goto L_0x002e
            r2 = 125(0x7d, float:1.75E-43)
            if (r1 != r2) goto L_0x0031
        L_0x002e:
            r4._inputPtr = r0
            return
        L_0x0031:
            java.lang.String r0 = "null"
            r1 = 1
            r4._matchToken(r0, r1)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.fasterxml.jackson.core.json.ReaderBasedJsonParser._matchNull():void");
    }

    private final void _matchToken2(String str, int i3) throws IOException {
        int i4;
        char c3;
        int length = str.length();
        do {
            if ((this._inputPtr >= this._inputEnd && !_loadMore()) || this._inputBuffer[this._inputPtr] != str.charAt(i3)) {
                _reportInvalidToken(str.substring(0, i3));
            }
            i4 = this._inputPtr + 1;
            this._inputPtr = i4;
            i3++;
        } while (i3 < length);
        if ((i4 < this._inputEnd || _loadMore()) && (c3 = this._inputBuffer[this._inputPtr]) >= '0' && c3 != ']' && c3 != '}') {
            _checkMatchEnd(str, i3, c3);
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:8:0x0020, code lost:
        r0 = r0 + 3;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final void _matchTrue() throws java.io.IOException {
        /*
            r4 = this;
            int r0 = r4._inputPtr
            int r1 = r0 + 3
            int r2 = r4._inputEnd
            if (r1 >= r2) goto L_0x0033
            char[] r1 = r4._inputBuffer
            char r2 = r1[r0]
            r3 = 114(0x72, float:1.6E-43)
            if (r2 != r3) goto L_0x0033
            int r2 = r0 + 1
            char r2 = r1[r2]
            r3 = 117(0x75, float:1.64E-43)
            if (r2 != r3) goto L_0x0033
            int r2 = r0 + 2
            char r2 = r1[r2]
            r3 = 101(0x65, float:1.42E-43)
            if (r2 != r3) goto L_0x0033
            int r0 = r0 + 3
            char r1 = r1[r0]
            r2 = 48
            if (r1 < r2) goto L_0x0030
            r2 = 93
            if (r1 == r2) goto L_0x0030
            r2 = 125(0x7d, float:1.75E-43)
            if (r1 != r2) goto L_0x0033
        L_0x0030:
            r4._inputPtr = r0
            return
        L_0x0033:
            java.lang.String r0 = "true"
            r1 = 1
            r4._matchToken(r0, r1)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.fasterxml.jackson.core.json.ReaderBasedJsonParser._matchTrue():void");
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
        } else if (i3 == 91) {
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
        } else if (i3 != 123) {
            switch (i3) {
                case 44:
                    if (!this._parsingContext.inRoot() && (this._features & FEAT_MASK_ALLOW_MISSING) != 0) {
                        this._inputPtr--;
                        JsonToken jsonToken6 = JsonToken.VALUE_NULL;
                        this._currToken = jsonToken6;
                        return jsonToken6;
                    }
                case 45:
                    JsonToken _parseSignedNumber = _parseSignedNumber(true);
                    this._currToken = _parseSignedNumber;
                    return _parseSignedNumber;
                case 46:
                    JsonToken _parseFloatThatStartsWithPeriod = _parseFloatThatStartsWithPeriod(false);
                    this._currToken = _parseFloatThatStartsWithPeriod;
                    return _parseFloatThatStartsWithPeriod;
                default:
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
                    }
            }
            JsonToken _handleOddValue = _handleOddValue(i3);
            this._currToken = _handleOddValue;
            return _handleOddValue;
        } else {
            this._parsingContext = this._parsingContext.createChildObjectContext(this._tokenInputRow, this._tokenInputCol);
            JsonToken jsonToken7 = JsonToken.START_OBJECT;
            this._currToken = jsonToken7;
            return jsonToken7;
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:19:0x0041, code lost:
        if (r10 == 'E') goto L_0x0043;
     */
    /* JADX WARNING: Failed to insert additional move for type inference */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final com.fasterxml.jackson.core.JsonToken _parseFloat(int r10, int r11, int r12, boolean r13, int r14) throws java.io.IOException {
        /*
            r9 = this;
            int r0 = r9._inputEnd
            r1 = 46
            r2 = 57
            r3 = 48
            r4 = 0
            if (r10 != r1) goto L_0x0039
            r10 = r4
        L_0x000c:
            if (r12 < r0) goto L_0x0013
            com.fasterxml.jackson.core.JsonToken r9 = r9._parseNumber2(r13, r11)
            return r9
        L_0x0013:
            char[] r1 = r9._inputBuffer
            int r5 = r12 + 1
            char r12 = r1[r12]
            if (r12 < r3) goto L_0x0022
            if (r12 <= r2) goto L_0x001e
            goto L_0x0022
        L_0x001e:
            int r10 = r10 + 1
            r12 = r5
            goto L_0x000c
        L_0x0022:
            if (r10 != 0) goto L_0x0035
            com.fasterxml.jackson.core.json.JsonReadFeature r1 = com.fasterxml.jackson.core.json.JsonReadFeature.ALLOW_TRAILING_DECIMAL_POINT_FOR_NUMBERS
            com.fasterxml.jackson.core.JsonParser$Feature r1 = r1.mappedFeature()
            boolean r1 = r9.isEnabled((com.fasterxml.jackson.core.JsonParser.Feature) r1)
            if (r1 != 0) goto L_0x0035
            java.lang.String r1 = "Decimal point not followed by a digit"
            r9._reportUnexpectedNumberChar(r12, r1)
        L_0x0035:
            r8 = r12
            r12 = r10
            r10 = r8
            goto L_0x003b
        L_0x0039:
            r5 = r12
            r12 = r4
        L_0x003b:
            r1 = 101(0x65, float:1.42E-43)
            if (r10 == r1) goto L_0x0043
            r1 = 69
            if (r10 != r1) goto L_0x0089
        L_0x0043:
            if (r5 < r0) goto L_0x004c
            r9._inputPtr = r11
            com.fasterxml.jackson.core.JsonToken r9 = r9._parseNumber2(r13, r11)
            return r9
        L_0x004c:
            char[] r10 = r9._inputBuffer
            int r1 = r5 + 1
            char r6 = r10[r5]
            r7 = 45
            if (r6 == r7) goto L_0x005e
            r7 = 43
            if (r6 != r7) goto L_0x005b
            goto L_0x005e
        L_0x005b:
            r5 = r1
            r10 = r6
            goto L_0x006b
        L_0x005e:
            if (r1 < r0) goto L_0x0067
            r9._inputPtr = r11
            com.fasterxml.jackson.core.JsonToken r9 = r9._parseNumber2(r13, r11)
            return r9
        L_0x0067:
            int r5 = r5 + 2
            char r10 = r10[r1]
        L_0x006b:
            if (r10 > r2) goto L_0x0082
            if (r10 < r3) goto L_0x0082
            int r4 = r4 + 1
            if (r5 < r0) goto L_0x007a
            r9._inputPtr = r11
            com.fasterxml.jackson.core.JsonToken r9 = r9._parseNumber2(r13, r11)
            return r9
        L_0x007a:
            char[] r10 = r9._inputBuffer
            int r1 = r5 + 1
            char r10 = r10[r5]
            r5 = r1
            goto L_0x006b
        L_0x0082:
            if (r4 != 0) goto L_0x0089
            java.lang.String r0 = "Exponent indicator not followed by a digit"
            r9._reportUnexpectedNumberChar(r10, r0)
        L_0x0089:
            int r5 = r5 + -1
            r9._inputPtr = r5
            com.fasterxml.jackson.core.json.JsonReadContext r0 = r9._parsingContext
            boolean r0 = r0.inRoot()
            if (r0 == 0) goto L_0x0098
            r9._verifyRootSpace(r10)
        L_0x0098:
            int r5 = r5 - r11
            com.fasterxml.jackson.core.util.TextBuffer r10 = r9._textBuffer
            char[] r0 = r9._inputBuffer
            r10.resetWithShared(r0, r11, r5)
            com.fasterxml.jackson.core.JsonToken r9 = r9.resetFloat(r13, r14, r12, r4)
            return r9
        */
        throw new UnsupportedOperationException("Method not decompiled: com.fasterxml.jackson.core.json.ReaderBasedJsonParser._parseFloat(int, int, int, boolean, int):com.fasterxml.jackson.core.JsonToken");
    }

    private String _parseName2(int i3, int i4, int i5) throws IOException {
        this._textBuffer.resetWithShared(this._inputBuffer, i3, this._inputPtr - i3);
        char[] currentSegment = this._textBuffer.getCurrentSegment();
        int currentSegmentSize = this._textBuffer.getCurrentSegmentSize();
        while (true) {
            if (this._inputPtr >= this._inputEnd && !_loadMore()) {
                _reportInvalidEOF(" in field name", JsonToken.FIELD_NAME);
            }
            char[] cArr = this._inputBuffer;
            int i6 = this._inputPtr;
            this._inputPtr = i6 + 1;
            char c3 = cArr[i6];
            if (c3 <= '\\') {
                if (c3 == '\\') {
                    c3 = _decodeEscaped();
                } else if (c3 <= i5) {
                    if (c3 == i5) {
                        this._textBuffer.setCurrentLength(currentSegmentSize);
                        TextBuffer textBuffer = this._textBuffer;
                        return this._symbols.findSymbol(textBuffer.getTextBuffer(), textBuffer.getTextOffset(), textBuffer.size(), i4);
                    } else if (c3 < ' ') {
                        _throwUnquotedSpace(c3, "name");
                    }
                }
            }
            i4 = (i4 * 33) + c3;
            int i7 = currentSegmentSize + 1;
            currentSegment[currentSegmentSize] = c3;
            if (i7 >= currentSegment.length) {
                currentSegment = this._textBuffer.finishCurrentSegment();
                currentSegmentSize = 0;
            } else {
                currentSegmentSize = i7;
            }
        }
    }

    private final JsonToken _parseNumber2(boolean z2, int i3) throws IOException {
        int i4;
        char c3;
        boolean z3;
        int i5;
        char c4;
        char nextChar;
        int i6;
        if (z2) {
            i3++;
        }
        this._inputPtr = i3;
        char[] emptyAndGetCurrentSegment = this._textBuffer.emptyAndGetCurrentSegment();
        if (z2) {
            emptyAndGetCurrentSegment[0] = SignatureVisitor.SUPER;
            i4 = 1;
        } else {
            i4 = 0;
        }
        int i7 = this._inputPtr;
        if (i7 < this._inputEnd) {
            char[] cArr = this._inputBuffer;
            this._inputPtr = i7 + 1;
            c3 = cArr[i7];
        } else {
            c3 = getNextChar("No digit following minus sign", JsonToken.VALUE_NUMBER_INT);
        }
        if (c3 == '0') {
            c3 = _verifyNoLeadingZeroes();
        }
        int i8 = 0;
        while (true) {
            if (c3 >= '0' && c3 <= '9') {
                i8++;
                if (i4 >= emptyAndGetCurrentSegment.length) {
                    emptyAndGetCurrentSegment = this._textBuffer.finishCurrentSegment();
                    i4 = 0;
                }
                int i9 = i4 + 1;
                emptyAndGetCurrentSegment[i4] = c3;
                if (this._inputPtr >= this._inputEnd && !_loadMore()) {
                    c3 = 0;
                    i4 = i9;
                    z3 = true;
                    break;
                }
                char[] cArr2 = this._inputBuffer;
                int i10 = this._inputPtr;
                this._inputPtr = i10 + 1;
                c3 = cArr2[i10];
                i4 = i9;
            } else {
                z3 = false;
            }
        }
        z3 = false;
        if (i8 == 0 && !isEnabled(JsonReadFeature.ALLOW_LEADING_DECIMAL_POINT_FOR_NUMBERS.mappedFeature())) {
            return _handleInvalidNumberStart(c3, z2);
        }
        int i11 = -1;
        if (c3 == '.') {
            if (i4 >= emptyAndGetCurrentSegment.length) {
                emptyAndGetCurrentSegment = this._textBuffer.finishCurrentSegment();
                i4 = 0;
            }
            emptyAndGetCurrentSegment[i4] = c3;
            int i12 = i4 + 1;
            i5 = 0;
            while (true) {
                if (this._inputPtr >= this._inputEnd && !_loadMore()) {
                    z3 = true;
                    break;
                }
                char[] cArr3 = this._inputBuffer;
                int i13 = this._inputPtr;
                this._inputPtr = i13 + 1;
                c3 = cArr3[i13];
                if (c3 < '0' || c3 > '9') {
                    break;
                }
                i5++;
                if (i4 >= emptyAndGetCurrentSegment.length) {
                    emptyAndGetCurrentSegment = this._textBuffer.finishCurrentSegment();
                    i4 = 0;
                }
                emptyAndGetCurrentSegment[i4] = c3;
                i12 = i4 + 1;
            }
            if (i5 == 0 && !isEnabled(JsonReadFeature.ALLOW_TRAILING_DECIMAL_POINT_FOR_NUMBERS.mappedFeature())) {
                _reportUnexpectedNumberChar(c3, "Decimal point not followed by a digit");
            }
        } else {
            i5 = -1;
        }
        if (c3 == 'e' || c3 == 'E') {
            if (i4 >= emptyAndGetCurrentSegment.length) {
                emptyAndGetCurrentSegment = this._textBuffer.finishCurrentSegment();
                i4 = 0;
            }
            int i14 = i4 + 1;
            emptyAndGetCurrentSegment[i4] = c3;
            int i15 = this._inputPtr;
            if (i15 < this._inputEnd) {
                char[] cArr4 = this._inputBuffer;
                this._inputPtr = i15 + 1;
                c4 = cArr4[i15];
            } else {
                c4 = getNextChar("expected a digit for number exponent", JsonToken.VALUE_NUMBER_FLOAT);
            }
            if (c4 == '-' || c4 == '+') {
                if (i14 >= emptyAndGetCurrentSegment.length) {
                    emptyAndGetCurrentSegment = this._textBuffer.finishCurrentSegment();
                    i14 = 0;
                }
                int i16 = i14 + 1;
                emptyAndGetCurrentSegment[i14] = c4;
                int i17 = this._inputPtr;
                if (i17 < this._inputEnd) {
                    char[] cArr5 = this._inputBuffer;
                    this._inputPtr = i17 + 1;
                    nextChar = cArr5[i17];
                } else {
                    nextChar = getNextChar("expected a digit for number exponent", JsonToken.VALUE_NUMBER_FLOAT);
                }
                i14 = i16;
            }
            int i18 = 0;
            char c5 = c4;
            while (true) {
                if (c3 <= '9' && c3 >= '0') {
                    i18++;
                    if (i14 >= emptyAndGetCurrentSegment.length) {
                        emptyAndGetCurrentSegment = this._textBuffer.finishCurrentSegment();
                        i14 = 0;
                    }
                    i6 = i14 + 1;
                    emptyAndGetCurrentSegment[i14] = c3;
                    if (this._inputPtr >= this._inputEnd && !_loadMore()) {
                        i11 = i18;
                        z3 = true;
                        break;
                    }
                    char[] cArr6 = this._inputBuffer;
                    int i19 = this._inputPtr;
                    this._inputPtr = i19 + 1;
                    c5 = cArr6[i19];
                    i14 = i6;
                } else {
                    i6 = i14;
                    i11 = i18;
                }
            }
            i6 = i14;
            i11 = i18;
            if (i11 == 0) {
                _reportUnexpectedNumberChar(c3, "Exponent indicator not followed by a digit");
            }
        }
        if (!z3) {
            this._inputPtr--;
            if (this._parsingContext.inRoot()) {
                _verifyRootSpace(c3);
            }
        }
        this._textBuffer.setCurrentLength(i4);
        return (i5 >= 0 || i11 >= 0) ? resetFloat(z2, i8, i5, i11) : resetInt(z2, i8);
    }

    private final JsonToken _parseSignedNumber(boolean z2) throws IOException {
        int i3 = this._inputPtr;
        int i4 = z2 ? i3 - 1 : i3;
        int i5 = this._inputEnd;
        if (i3 >= i5) {
            return _parseNumber2(z2, i4);
        }
        int i6 = i3 + 1;
        char c3 = this._inputBuffer[i3];
        if (c3 > '9' || c3 < '0') {
            this._inputPtr = i6;
            return c3 == '.' ? _parseFloatThatStartsWithPeriod(z2) : _handleInvalidNumberStart(c3, z2, true);
        } else if (c3 == '0') {
            return _parseNumber2(z2, i4);
        } else {
            int i7 = 1;
            while (i6 < i5) {
                int i8 = i6 + 1;
                char c4 = this._inputBuffer[i6];
                if (c4 >= '0' && c4 <= '9') {
                    i7++;
                    i6 = i8;
                } else if (c4 == '.' || c4 == 'e' || c4 == 'E') {
                    this._inputPtr = i8;
                    return _parseFloat(c4, i4, i8, z2, i7);
                } else {
                    this._inputPtr = i6;
                    if (this._parsingContext.inRoot()) {
                        _verifyRootSpace(c4);
                    }
                    this._textBuffer.resetWithShared(this._inputBuffer, i4, i6 - i4);
                    return resetInt(z2, i7);
                }
            }
            return _parseNumber2(z2, i4);
        }
    }

    private final int _skipAfterComma2() throws IOException {
        char c3;
        while (true) {
            if (this._inputPtr < this._inputEnd || _loadMore()) {
                char[] cArr = this._inputBuffer;
                int i3 = this._inputPtr;
                int i4 = i3 + 1;
                this._inputPtr = i4;
                c3 = cArr[i3];
                if (c3 > ' ') {
                    if (c3 == '/') {
                        _skipComment();
                    } else if (c3 != '#' || !_skipYAMLComment()) {
                        return c3;
                    }
                } else if (c3 < ' ') {
                    if (c3 == 10) {
                        this._currInputRow++;
                        this._currInputRowStart = i4;
                    } else if (c3 == 13) {
                        _skipCR();
                    } else if (c3 != 9) {
                        _throwInvalidSpace(c3);
                    }
                }
            } else {
                throw _constructError("Unexpected end-of-input within/between " + this._parsingContext.typeDesc() + " entries");
            }
        }
        return c3;
    }

    private void _skipCComment() throws IOException {
        while (true) {
            if (this._inputPtr >= this._inputEnd && !_loadMore()) {
                break;
            }
            char[] cArr = this._inputBuffer;
            int i3 = this._inputPtr;
            int i4 = i3 + 1;
            this._inputPtr = i4;
            char c3 = cArr[i3];
            if (c3 <= '*') {
                if (c3 == '*') {
                    if (i4 >= this._inputEnd && !_loadMore()) {
                        break;
                    }
                    char[] cArr2 = this._inputBuffer;
                    int i5 = this._inputPtr;
                    if (cArr2[i5] == '/') {
                        this._inputPtr = i5 + 1;
                        return;
                    }
                } else if (c3 < ' ') {
                    if (c3 == 10) {
                        this._currInputRow++;
                        this._currInputRowStart = i4;
                    } else if (c3 == 13) {
                        _skipCR();
                    } else if (c3 != 9) {
                        _throwInvalidSpace(c3);
                    }
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
        char[] cArr = this._inputBuffer;
        char c3 = cArr[i3];
        if (c3 == ':') {
            int i4 = i3 + 1;
            this._inputPtr = i4;
            char c4 = cArr[i4];
            if (c4 <= ' ') {
                if (c4 == ' ' || c4 == 9) {
                    int i5 = i3 + 2;
                    this._inputPtr = i5;
                    char c5 = cArr[i5];
                    if (c5 > ' ') {
                        if (c5 == '/' || c5 == '#') {
                            return _skipColon2(true);
                        }
                        this._inputPtr = i3 + 3;
                        return c5;
                    }
                }
                return _skipColon2(true);
            } else if (c4 == '/' || c4 == '#') {
                return _skipColon2(true);
            } else {
                this._inputPtr = i3 + 2;
                return c4;
            }
        } else {
            if (c3 == ' ' || c3 == 9) {
                int i6 = i3 + 1;
                this._inputPtr = i6;
                c3 = cArr[i6];
            }
            if (c3 != ':') {
                return _skipColon2(false);
            }
            int i7 = this._inputPtr;
            int i8 = i7 + 1;
            this._inputPtr = i8;
            char c6 = cArr[i8];
            if (c6 <= ' ') {
                if (c6 == ' ' || c6 == 9) {
                    int i9 = i7 + 2;
                    this._inputPtr = i9;
                    char c7 = cArr[i9];
                    if (c7 > ' ') {
                        if (c7 == '/' || c7 == '#') {
                            return _skipColon2(true);
                        }
                        this._inputPtr = i7 + 3;
                        return c7;
                    }
                }
                return _skipColon2(true);
            } else if (c6 == '/' || c6 == '#') {
                return _skipColon2(true);
            } else {
                this._inputPtr = i7 + 2;
                return c6;
            }
        }
    }

    private final int _skipColon2(boolean z2) throws IOException {
        while (true) {
            if (this._inputPtr < this._inputEnd || _loadMore()) {
                char[] cArr = this._inputBuffer;
                int i3 = this._inputPtr;
                int i4 = i3 + 1;
                this._inputPtr = i4;
                char c3 = cArr[i3];
                if (c3 > ' ') {
                    if (c3 == '/') {
                        _skipComment();
                    } else if (c3 != '#' || !_skipYAMLComment()) {
                        if (z2) {
                            return c3;
                        }
                        if (c3 != ':') {
                            _reportUnexpectedChar(c3, "was expecting a colon to separate field name and value");
                        }
                        z2 = true;
                    }
                } else if (c3 < ' ') {
                    if (c3 == 10) {
                        this._currInputRow++;
                        this._currInputRowStart = i4;
                    } else if (c3 == 13) {
                        _skipCR();
                    } else if (c3 != 9) {
                        _throwInvalidSpace(c3);
                    }
                }
            } else {
                _reportInvalidEOF(" within/between " + this._parsingContext.typeDesc() + " entries", (JsonToken) null);
                return -1;
            }
        }
    }

    private final int _skipColonFast(int i3) throws IOException {
        char[] cArr = this._inputBuffer;
        int i4 = i3 + 1;
        char c3 = cArr[i3];
        if (c3 == ':') {
            int i5 = i3 + 2;
            char c4 = cArr[i4];
            if (c4 > ' ') {
                if (!(c4 == '/' || c4 == '#')) {
                    this._inputPtr = i5;
                    return c4;
                }
            } else if (c4 == ' ' || c4 == 9) {
                int i6 = i3 + 3;
                char c5 = cArr[i5];
                if (c5 <= ' ' || c5 == '/' || c5 == '#') {
                    i5 = i6;
                } else {
                    this._inputPtr = i6;
                    return c5;
                }
            }
            this._inputPtr = i5 - 1;
            return _skipColon2(true);
        }
        if (c3 == ' ' || c3 == 9) {
            c3 = cArr[i4];
            i4 = i3 + 2;
        }
        boolean z2 = c3 == ':';
        if (z2) {
            int i7 = i4 + 1;
            char c6 = cArr[i4];
            if (c6 > ' ') {
                if (!(c6 == '/' || c6 == '#')) {
                    this._inputPtr = i7;
                    return c6;
                }
            } else if (c6 == ' ' || c6 == 9) {
                i4 += 2;
                char c7 = cArr[i7];
                if (!(c7 <= ' ' || c7 == '/' || c7 == '#')) {
                    this._inputPtr = i4;
                    return c7;
                }
            }
            i4 = i7;
        }
        this._inputPtr = i4 - 1;
        return _skipColon2(z2);
    }

    private final int _skipComma(int i3) throws IOException {
        if (i3 != 44) {
            _reportUnexpectedChar(i3, "was expecting comma to separate " + this._parsingContext.typeDesc() + " entries");
        }
        while (true) {
            int i4 = this._inputPtr;
            if (i4 >= this._inputEnd) {
                return _skipAfterComma2();
            }
            char[] cArr = this._inputBuffer;
            int i5 = i4 + 1;
            this._inputPtr = i5;
            char c3 = cArr[i4];
            if (c3 > ' ') {
                if (c3 != '/' && c3 != '#') {
                    return c3;
                }
                this._inputPtr = i4;
                return _skipAfterComma2();
            } else if (c3 < ' ') {
                if (c3 == 10) {
                    this._currInputRow++;
                    this._currInputRowStart = i5;
                } else if (c3 == 13) {
                    _skipCR();
                } else if (c3 != 9) {
                    _throwInvalidSpace(c3);
                }
            }
        }
    }

    private void _skipComment() throws IOException {
        if ((this._features & FEAT_MASK_ALLOW_JAVA_COMMENTS) == 0) {
            _reportUnexpectedChar(47, "maybe a (non-standard) comment? (not recognized as one since Feature 'ALLOW_COMMENTS' not enabled for parser)");
        }
        if (this._inputPtr >= this._inputEnd && !_loadMore()) {
            _reportInvalidEOF(" in a comment", (JsonToken) null);
        }
        char[] cArr = this._inputBuffer;
        int i3 = this._inputPtr;
        this._inputPtr = i3 + 1;
        char c3 = cArr[i3];
        if (c3 == '/') {
            _skipLine();
        } else if (c3 == '*') {
            _skipCComment();
        } else {
            _reportUnexpectedChar(c3, "was expecting either '*' or '/' for a comment");
        }
    }

    private void _skipLine() throws IOException {
        while (true) {
            if (this._inputPtr < this._inputEnd || _loadMore()) {
                char[] cArr = this._inputBuffer;
                int i3 = this._inputPtr;
                int i4 = i3 + 1;
                this._inputPtr = i4;
                char c3 = cArr[i3];
                if (c3 < ' ') {
                    if (c3 == 10) {
                        this._currInputRow++;
                        this._currInputRowStart = i4;
                        return;
                    } else if (c3 == 13) {
                        _skipCR();
                        return;
                    } else if (c3 != 9) {
                        _throwInvalidSpace(c3);
                    }
                }
            } else {
                return;
            }
        }
    }

    private final int _skipWSOrEnd() throws IOException {
        if (this._inputPtr >= this._inputEnd && !_loadMore()) {
            return _eofAsNextChar();
        }
        char[] cArr = this._inputBuffer;
        int i3 = this._inputPtr;
        int i4 = i3 + 1;
        this._inputPtr = i4;
        char c3 = cArr[i3];
        if (c3 <= ' ') {
            if (c3 != ' ') {
                if (c3 == 10) {
                    this._currInputRow++;
                    this._currInputRowStart = i4;
                } else if (c3 == 13) {
                    _skipCR();
                } else if (c3 != 9) {
                    _throwInvalidSpace(c3);
                }
            }
            while (true) {
                int i5 = this._inputPtr;
                if (i5 >= this._inputEnd) {
                    return _skipWSOrEnd2();
                }
                char[] cArr2 = this._inputBuffer;
                int i6 = i5 + 1;
                this._inputPtr = i6;
                char c4 = cArr2[i5];
                if (c4 > ' ') {
                    if (c4 != '/' && c4 != '#') {
                        return c4;
                    }
                    this._inputPtr = i5;
                    return _skipWSOrEnd2();
                } else if (c4 != ' ') {
                    if (c4 == 10) {
                        this._currInputRow++;
                        this._currInputRowStart = i6;
                    } else if (c4 == 13) {
                        _skipCR();
                    } else if (c4 != 9) {
                        _throwInvalidSpace(c4);
                    }
                }
            }
        } else if (c3 != '/' && c3 != '#') {
            return c3;
        } else {
            this._inputPtr = i3;
            return _skipWSOrEnd2();
        }
    }

    private int _skipWSOrEnd2() throws IOException {
        char c3;
        while (true) {
            if (this._inputPtr >= this._inputEnd && !_loadMore()) {
                return _eofAsNextChar();
            }
            char[] cArr = this._inputBuffer;
            int i3 = this._inputPtr;
            int i4 = i3 + 1;
            this._inputPtr = i4;
            c3 = cArr[i3];
            if (c3 > ' ') {
                if (c3 == '/') {
                    _skipComment();
                } else if (c3 != '#' || !_skipYAMLComment()) {
                    return c3;
                }
            } else if (c3 != ' ') {
                if (c3 == 10) {
                    this._currInputRow++;
                    this._currInputRowStart = i4;
                } else if (c3 == 13) {
                    _skipCR();
                } else if (c3 != 9) {
                    _throwInvalidSpace(c3);
                }
            }
        }
        return c3;
    }

    private boolean _skipYAMLComment() throws IOException {
        if ((this._features & FEAT_MASK_ALLOW_YAML_COMMENTS) == 0) {
            return false;
        }
        _skipLine();
        return true;
    }

    private final void _updateLocation() {
        int i3 = this._inputPtr;
        this._tokenInputTotal = this._currInputProcessed + ((long) i3);
        this._tokenInputRow = this._currInputRow;
        this._tokenInputCol = i3 - this._currInputRowStart;
    }

    private final void _updateNameLocation() {
        int i3 = this._inputPtr;
        this._nameStartOffset = (long) i3;
        this._nameStartRow = this._currInputRow;
        this._nameStartCol = i3 - this._currInputRowStart;
    }

    private char _verifyNLZ2() throws IOException {
        char c3;
        if ((this._inputPtr >= this._inputEnd && !_loadMore()) || (c3 = this._inputBuffer[this._inputPtr]) < '0' || c3 > '9') {
            return '0';
        }
        if ((this._features & FEAT_MASK_LEADING_ZEROS) == 0) {
            reportInvalidNumber("Leading zeroes not allowed");
        }
        this._inputPtr++;
        if (c3 == '0') {
            do {
                if (this._inputPtr >= this._inputEnd && !_loadMore()) {
                    break;
                }
                char[] cArr = this._inputBuffer;
                int i3 = this._inputPtr;
                c3 = cArr[i3];
                if (c3 < '0' || c3 > '9') {
                    return '0';
                }
                this._inputPtr = i3 + 1;
            } while (c3 == '0');
        }
        return c3;
    }

    private final char _verifyNoLeadingZeroes() throws IOException {
        char c3;
        int i3 = this._inputPtr;
        if (i3 >= this._inputEnd || ((c3 = this._inputBuffer[i3]) >= '0' && c3 <= '9')) {
            return _verifyNLZ2();
        }
        return '0';
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

    public void _closeInput() throws IOException {
        if (this._reader != null) {
            if (this._ioContext.isResourceManaged() || isEnabled(JsonParser.Feature.AUTO_CLOSE_SOURCE)) {
                this._reader.close();
            }
            this._reader = null;
        }
    }

    public byte[] _decodeBase64(Base64Variant base64Variant) throws IOException {
        ByteArrayBuilder _getByteArrayBuilder = _getByteArrayBuilder();
        while (true) {
            if (this._inputPtr >= this._inputEnd) {
                _loadMoreGuaranteed();
            }
            char[] cArr = this._inputBuffer;
            int i3 = this._inputPtr;
            this._inputPtr = i3 + 1;
            char c3 = cArr[i3];
            if (c3 > ' ') {
                int decodeBase64Char = base64Variant.decodeBase64Char(c3);
                if (decodeBase64Char < 0) {
                    if (c3 == '\"') {
                        return _getByteArrayBuilder.toByteArray();
                    }
                    decodeBase64Char = _decodeBase64Escape(base64Variant, c3, 0);
                    if (decodeBase64Char < 0) {
                        continue;
                    }
                }
                if (this._inputPtr >= this._inputEnd) {
                    _loadMoreGuaranteed();
                }
                char[] cArr2 = this._inputBuffer;
                int i4 = this._inputPtr;
                this._inputPtr = i4 + 1;
                char c4 = cArr2[i4];
                int decodeBase64Char2 = base64Variant.decodeBase64Char(c4);
                if (decodeBase64Char2 < 0) {
                    decodeBase64Char2 = _decodeBase64Escape(base64Variant, c4, 1);
                }
                int i5 = (decodeBase64Char << 6) | decodeBase64Char2;
                if (this._inputPtr >= this._inputEnd) {
                    _loadMoreGuaranteed();
                }
                char[] cArr3 = this._inputBuffer;
                int i6 = this._inputPtr;
                this._inputPtr = i6 + 1;
                char c5 = cArr3[i6];
                int decodeBase64Char3 = base64Variant.decodeBase64Char(c5);
                if (decodeBase64Char3 < 0) {
                    if (decodeBase64Char3 != -2) {
                        if (c5 == '\"') {
                            _getByteArrayBuilder.append(i5 >> 4);
                            if (base64Variant.usesPadding()) {
                                this._inputPtr--;
                                _handleBase64MissingPadding(base64Variant);
                            }
                            return _getByteArrayBuilder.toByteArray();
                        }
                        decodeBase64Char3 = _decodeBase64Escape(base64Variant, c5, 2);
                    }
                    if (decodeBase64Char3 == -2) {
                        if (this._inputPtr >= this._inputEnd) {
                            _loadMoreGuaranteed();
                        }
                        char[] cArr4 = this._inputBuffer;
                        int i7 = this._inputPtr;
                        this._inputPtr = i7 + 1;
                        char c6 = cArr4[i7];
                        if (base64Variant.usesPaddingChar(c6) || _decodeBase64Escape(base64Variant, c6, 3) == -2) {
                            _getByteArrayBuilder.append(i5 >> 4);
                        } else {
                            throw reportInvalidBase64Char(base64Variant, c6, 3, "expected padding character '" + base64Variant.getPaddingChar() + "'");
                        }
                    }
                }
                int i8 = (i5 << 6) | decodeBase64Char3;
                if (this._inputPtr >= this._inputEnd) {
                    _loadMoreGuaranteed();
                }
                char[] cArr5 = this._inputBuffer;
                int i9 = this._inputPtr;
                this._inputPtr = i9 + 1;
                char c7 = cArr5[i9];
                int decodeBase64Char4 = base64Variant.decodeBase64Char(c7);
                if (decodeBase64Char4 < 0) {
                    if (decodeBase64Char4 != -2) {
                        if (c7 == '\"') {
                            _getByteArrayBuilder.appendTwoBytes(i8 >> 2);
                            if (base64Variant.usesPadding()) {
                                this._inputPtr--;
                                _handleBase64MissingPadding(base64Variant);
                            }
                            return _getByteArrayBuilder.toByteArray();
                        }
                        decodeBase64Char4 = _decodeBase64Escape(base64Variant, c7, 3);
                    }
                    if (decodeBase64Char4 == -2) {
                        _getByteArrayBuilder.appendTwoBytes(i8 >> 2);
                    }
                }
                _getByteArrayBuilder.appendThreeBytes((i8 << 6) | decodeBase64Char4);
            }
        }
    }

    public char _decodeEscaped() throws IOException {
        if (this._inputPtr >= this._inputEnd && !_loadMore()) {
            _reportInvalidEOF(" in character escape sequence", JsonToken.VALUE_STRING);
        }
        char[] cArr = this._inputBuffer;
        int i3 = this._inputPtr;
        this._inputPtr = i3 + 1;
        char c3 = cArr[i3];
        if (c3 == '\"' || c3 == '/' || c3 == '\\') {
            return c3;
        }
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
            return _handleUnrecognizedCharacterEscape(c3);
        }
        int i4 = 0;
        for (int i5 = 0; i5 < 4; i5++) {
            if (this._inputPtr >= this._inputEnd && !_loadMore()) {
                _reportInvalidEOF(" in character escape sequence", JsonToken.VALUE_STRING);
            }
            char[] cArr2 = this._inputBuffer;
            int i6 = this._inputPtr;
            this._inputPtr = i6 + 1;
            char c4 = cArr2[i6];
            int charToHex = CharTypes.charToHex(c4);
            if (charToHex < 0) {
                _reportUnexpectedChar(c4, "expected a hex-digit for character escape sequence");
            }
            i4 = (i4 << 4) | charToHex;
        }
        return (char) i4;
    }

    public final void _finishString() throws IOException {
        int i3 = this._inputPtr;
        int i4 = this._inputEnd;
        if (i3 < i4) {
            int[] iArr = _icLatin1;
            int length = iArr.length;
            while (true) {
                char[] cArr = this._inputBuffer;
                char c3 = cArr[i3];
                if (c3 >= length || iArr[c3] == 0) {
                    i3++;
                    if (i3 >= i4) {
                        break;
                    }
                } else if (c3 == '\"') {
                    TextBuffer textBuffer = this._textBuffer;
                    int i5 = this._inputPtr;
                    textBuffer.resetWithShared(cArr, i5, i3 - i5);
                    this._inputPtr = i3 + 1;
                    return;
                }
            }
        }
        TextBuffer textBuffer2 = this._textBuffer;
        char[] cArr2 = this._inputBuffer;
        int i6 = this._inputPtr;
        textBuffer2.resetWithCopy(cArr2, i6, i3 - i6);
        this._inputPtr = i3;
        _finishString2();
    }

    public void _finishString2() throws IOException {
        char[] currentSegment = this._textBuffer.getCurrentSegment();
        int currentSegmentSize = this._textBuffer.getCurrentSegmentSize();
        int[] iArr = _icLatin1;
        int length = iArr.length;
        while (true) {
            if (this._inputPtr >= this._inputEnd && !_loadMore()) {
                _reportInvalidEOF(": was expecting closing quote for a string value", JsonToken.VALUE_STRING);
            }
            char[] cArr = this._inputBuffer;
            int i3 = this._inputPtr;
            this._inputPtr = i3 + 1;
            char c3 = cArr[i3];
            if (c3 < length && iArr[c3] != 0) {
                if (c3 == '\"') {
                    this._textBuffer.setCurrentLength(currentSegmentSize);
                    return;
                } else if (c3 == '\\') {
                    c3 = _decodeEscaped();
                } else if (c3 < ' ') {
                    _throwUnquotedSpace(c3, "string value");
                }
            }
            if (currentSegmentSize >= currentSegment.length) {
                currentSegment = this._textBuffer.finishCurrentSegment();
                currentSegmentSize = 0;
            }
            currentSegment[currentSegmentSize] = c3;
            currentSegmentSize++;
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
        char[] emptyAndGetCurrentSegment = this._textBuffer.emptyAndGetCurrentSegment();
        int currentSegmentSize = this._textBuffer.getCurrentSegmentSize();
        while (true) {
            if (this._inputPtr >= this._inputEnd && !_loadMore()) {
                _reportInvalidEOF(": was expecting closing quote for a string value", JsonToken.VALUE_STRING);
            }
            char[] cArr = this._inputBuffer;
            int i3 = this._inputPtr;
            this._inputPtr = i3 + 1;
            char c3 = cArr[i3];
            if (c3 <= '\\') {
                if (c3 == '\\') {
                    c3 = _decodeEscaped();
                } else if (c3 <= '\'') {
                    if (c3 == '\'') {
                        this._textBuffer.setCurrentLength(currentSegmentSize);
                        return JsonToken.VALUE_STRING;
                    } else if (c3 < ' ') {
                        _throwUnquotedSpace(c3, "string value");
                    }
                }
            }
            if (currentSegmentSize >= emptyAndGetCurrentSegment.length) {
                emptyAndGetCurrentSegment = this._textBuffer.finishCurrentSegment();
                currentSegmentSize = 0;
            }
            emptyAndGetCurrentSegment[currentSegmentSize] = c3;
            currentSegmentSize++;
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
            _reportUnexpectedChar(i3, "was expecting double-quote to start field name");
        }
        int[] inputCodeLatin1JsNames = CharTypes.getInputCodeLatin1JsNames();
        int length = inputCodeLatin1JsNames.length;
        if (!(i3 < length ? inputCodeLatin1JsNames[i3] == 0 : Character.isJavaIdentifierPart((char) i3))) {
            _reportUnexpectedChar(i3, "was expecting either valid name character (for unquoted name) or double-quote (for quoted) to start field name");
        }
        int i4 = this._inputPtr;
        int i5 = this._hashSeed;
        int i6 = this._inputEnd;
        if (i4 < i6) {
            do {
                char[] cArr = this._inputBuffer;
                char c3 = cArr[i4];
                if (c3 < length) {
                    if (inputCodeLatin1JsNames[c3] != 0) {
                        int i7 = this._inputPtr - 1;
                        this._inputPtr = i4;
                        return this._symbols.findSymbol(cArr, i7, i4 - i7, i5);
                    }
                } else if (!Character.isJavaIdentifierPart((char) c3)) {
                    int i8 = this._inputPtr - 1;
                    this._inputPtr = i4;
                    return this._symbols.findSymbol(this._inputBuffer, i8, i4 - i8, i5);
                }
                i5 = (i5 * 33) + c3;
                i4++;
            } while (i4 < i6);
        }
        this._inputPtr = i4;
        return _handleOddName2(this._inputPtr - 1, i5, inputCodeLatin1JsNames);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0017, code lost:
        if (r4 != 44) goto L_0x009a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x0042, code lost:
        if (r3._parsingContext.inArray() == false) goto L_0x009a;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.fasterxml.jackson.core.JsonToken _handleOddValue(int r4) throws java.io.IOException {
        /*
            r3 = this;
            r0 = 39
            if (r4 == r0) goto L_0x008e
            r0 = 73
            r1 = 1
            if (r4 == r0) goto L_0x0075
            r0 = 78
            if (r4 == r0) goto L_0x005c
            r0 = 93
            if (r4 == r0) goto L_0x003c
            r0 = 43
            if (r4 == r0) goto L_0x001b
            r0 = 44
            if (r4 == r0) goto L_0x0045
            goto L_0x009a
        L_0x001b:
            int r4 = r3._inputPtr
            int r0 = r3._inputEnd
            if (r4 < r0) goto L_0x002c
            boolean r4 = r3._loadMore()
            if (r4 != 0) goto L_0x002c
            com.fasterxml.jackson.core.JsonToken r4 = com.fasterxml.jackson.core.JsonToken.VALUE_NUMBER_INT
            r3._reportInvalidEOFInValue(r4)
        L_0x002c:
            char[] r4 = r3._inputBuffer
            int r0 = r3._inputPtr
            int r2 = r0 + 1
            r3._inputPtr = r2
            char r4 = r4[r0]
            r0 = 0
            com.fasterxml.jackson.core.JsonToken r3 = r3._handleInvalidNumberStart(r4, r0, r1)
            return r3
        L_0x003c:
            com.fasterxml.jackson.core.json.JsonReadContext r0 = r3._parsingContext
            boolean r0 = r0.inArray()
            if (r0 != 0) goto L_0x0045
            goto L_0x009a
        L_0x0045:
            com.fasterxml.jackson.core.json.JsonReadContext r0 = r3._parsingContext
            boolean r0 = r0.inRoot()
            if (r0 != 0) goto L_0x009a
            int r0 = r3._features
            int r2 = FEAT_MASK_ALLOW_MISSING
            r0 = r0 & r2
            if (r0 == 0) goto L_0x009a
            int r4 = r3._inputPtr
            int r4 = r4 - r1
            r3._inputPtr = r4
            com.fasterxml.jackson.core.JsonToken r3 = com.fasterxml.jackson.core.JsonToken.VALUE_NULL
            return r3
        L_0x005c:
            java.lang.String r0 = "NaN"
            r3._matchToken(r0, r1)
            int r1 = r3._features
            int r2 = FEAT_MASK_NON_NUM_NUMBERS
            r1 = r1 & r2
            if (r1 == 0) goto L_0x006f
            r1 = 9221120237041090560(0x7ff8000000000000, double:NaN)
            com.fasterxml.jackson.core.JsonToken r3 = r3.resetAsNaN(r0, r1)
            return r3
        L_0x006f:
            java.lang.String r0 = "Non-standard token 'NaN': enable `JsonReadFeature.ALLOW_NON_NUMERIC_NUMBERS` to allow"
            r3._reportError(r0)
            goto L_0x009a
        L_0x0075:
            java.lang.String r0 = "Infinity"
            r3._matchToken(r0, r1)
            int r1 = r3._features
            int r2 = FEAT_MASK_NON_NUM_NUMBERS
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0088
            r1 = 9218868437227405312(0x7ff0000000000000, double:Infinity)
            com.fasterxml.jackson.core.JsonToken r3 = r3.resetAsNaN(r0, r1)
            return r3
        L_0x0088:
            java.lang.String r0 = "Non-standard token 'Infinity': enable `JsonReadFeature.ALLOW_NON_NUMERIC_NUMBERS` to allow"
            r3._reportError(r0)
            goto L_0x009a
        L_0x008e:
            int r0 = r3._features
            int r1 = FEAT_MASK_ALLOW_SINGLE_QUOTES
            r0 = r0 & r1
            if (r0 == 0) goto L_0x009a
            com.fasterxml.jackson.core.JsonToken r3 = r3._handleApos()
            return r3
        L_0x009a:
            boolean r0 = java.lang.Character.isJavaIdentifierStart(r4)
            if (r0 == 0) goto L_0x00b6
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            java.lang.String r1 = ""
            r0.<init>(r1)
            char r1 = (char) r4
            r0.append(r1)
            java.lang.String r0 = r0.toString()
            java.lang.String r1 = r3._validJsonTokenList()
            r3._reportInvalidToken(r0, r1)
        L_0x00b6:
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
        throw new UnsupportedOperationException("Method not decompiled: com.fasterxml.jackson.core.json.ReaderBasedJsonParser._handleOddValue(int):com.fasterxml.jackson.core.JsonToken");
    }

    public boolean _isNextTokenNameMaybe(int i3, String str) throws IOException {
        JsonToken jsonToken;
        String _parseName = i3 == 34 ? _parseName() : _handleOddName(i3);
        this._parsingContext.setCurrentName(_parseName);
        this._currToken = JsonToken.FIELD_NAME;
        int _skipColon = _skipColon();
        _updateLocation();
        if (_skipColon == 34) {
            this._tokenIncomplete = true;
            this._nextToken = JsonToken.VALUE_STRING;
            return str.equals(_parseName);
        }
        if (_skipColon == 43) {
            jsonToken = isEnabled(JsonReadFeature.ALLOW_LEADING_PLUS_SIGN_FOR_NUMBERS.mappedFeature()) ? _parseSignedNumber(false) : _handleOddValue(_skipColon);
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
                    jsonToken = _handleOddValue(_skipColon);
                    break;
            }
        } else {
            jsonToken = _parseFloatThatStartsWithPeriod(false);
        }
        this._nextToken = jsonToken;
        return str.equals(_parseName);
    }

    public boolean _loadMore() throws IOException {
        Reader reader = this._reader;
        if (reader != null) {
            char[] cArr = this._inputBuffer;
            int read = reader.read(cArr, 0, cArr.length);
            if (read > 0) {
                int i3 = this._inputEnd;
                long j2 = (long) i3;
                this._currInputProcessed += j2;
                this._currInputRowStart -= i3;
                this._nameStartOffset -= j2;
                this._inputPtr = 0;
                this._inputEnd = read;
                return true;
            }
            _closeInput();
            if (read == 0) {
                throw new IOException("Reader returned 0 characters when trying to read " + this._inputEnd);
            }
        }
        return false;
    }

    public void _loadMoreGuaranteed() throws IOException {
        if (!_loadMore()) {
            _reportInvalidEOF();
        }
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
        char c3 = this._inputBuffer[i4];
        if (c3 >= '0' && c3 != ']' && c3 != '}') {
            _checkMatchEnd(str, i3, c3);
        }
    }

    public String _parseAposName() throws IOException {
        int i3 = this._inputPtr;
        int i4 = this._hashSeed;
        int i5 = this._inputEnd;
        if (i3 < i5) {
            int[] iArr = _icLatin1;
            int length = iArr.length;
            do {
                char[] cArr = this._inputBuffer;
                char c3 = cArr[i3];
                if (c3 != '\'') {
                    if (c3 < length && iArr[c3] != 0) {
                        break;
                    }
                    i4 = (i4 * 33) + c3;
                    i3++;
                } else {
                    int i6 = this._inputPtr;
                    this._inputPtr = i3 + 1;
                    return this._symbols.findSymbol(cArr, i6, i3 - i6, i4);
                }
            } while (i3 < i5);
        }
        int i7 = this._inputPtr;
        this._inputPtr = i3;
        return _parseName2(i7, i4, 39);
    }

    @Deprecated
    public final JsonToken _parseFloatThatStartsWithPeriod() throws IOException {
        return _parseFloatThatStartsWithPeriod(false);
    }

    public final String _parseName() throws IOException {
        int i3 = this._inputPtr;
        int i4 = this._hashSeed;
        int[] iArr = _icLatin1;
        while (true) {
            if (i3 >= this._inputEnd) {
                break;
            }
            char[] cArr = this._inputBuffer;
            char c3 = cArr[i3];
            if (c3 >= iArr.length || iArr[c3] == 0) {
                i4 = (i4 * 33) + c3;
                i3++;
            } else if (c3 == '\"') {
                int i5 = this._inputPtr;
                this._inputPtr = i3 + 1;
                return this._symbols.findSymbol(cArr, i5, i3 - i5, i4);
            }
        }
        int i6 = this._inputPtr;
        this._inputPtr = i3;
        return _parseName2(i6, i4, 34);
    }

    public final JsonToken _parseUnsignedNumber(int i3) throws IOException {
        int i4 = this._inputPtr;
        int i5 = i4 - 1;
        int i6 = this._inputEnd;
        if (i3 == 48) {
            return _parseNumber2(false, i5);
        }
        int i7 = 1;
        while (i4 < i6) {
            int i8 = i4 + 1;
            char c3 = this._inputBuffer[i4];
            if (c3 >= '0' && c3 <= '9') {
                i7++;
                i4 = i8;
            } else if (c3 == '.' || c3 == 'e' || c3 == 'E') {
                this._inputPtr = i8;
                return _parseFloat(c3, i5, i8, false, i7);
            } else {
                this._inputPtr = i4;
                if (this._parsingContext.inRoot()) {
                    _verifyRootSpace(c3);
                }
                this._textBuffer.resetWithShared(this._inputBuffer, i5, i4 - i5);
                return resetInt(false, i7);
            }
        }
        this._inputPtr = i5;
        return _parseNumber2(false, i5);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x0035, code lost:
        if (r10 < 0) goto L_0x0037;
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
            char[] r9 = r0._inputBuffer
            int r10 = r0._inputPtr
            int r11 = r10 + 1
            r0._inputPtr = r11
            char r9 = r9[r10]
            r10 = 32
            if (r9 <= r10) goto L_0x0037
            int r10 = r1.decodeBase64Char((char) r9)
            r11 = 34
            if (r10 >= 0) goto L_0x003a
            if (r9 != r11) goto L_0x0031
            goto L_0x0120
        L_0x0031:
            int r10 = r0._decodeBase64Escape((com.fasterxml.jackson.core.Base64Variant) r1, (char) r9, (int) r6)
            if (r10 >= 0) goto L_0x003a
        L_0x0037:
            r11 = r5
            goto L_0x0140
        L_0x003a:
            if (r7 <= r4) goto L_0x0041
            int r8 = r8 + r7
            r2.write(r3, r6, r7)
            r7 = r6
        L_0x0041:
            int r9 = r0._inputPtr
            int r12 = r0._inputEnd
            if (r9 < r12) goto L_0x004a
            r16._loadMoreGuaranteed()
        L_0x004a:
            char[] r9 = r0._inputBuffer
            int r12 = r0._inputPtr
            int r13 = r12 + 1
            r0._inputPtr = r13
            char r9 = r9[r12]
            int r12 = r1.decodeBase64Char((char) r9)
            r13 = 1
            if (r12 >= 0) goto L_0x005f
            int r12 = r0._decodeBase64Escape((com.fasterxml.jackson.core.Base64Variant) r1, (char) r9, (int) r13)
        L_0x005f:
            int r9 = r10 << 6
            r9 = r9 | r12
            int r10 = r0._inputPtr
            int r12 = r0._inputEnd
            if (r10 < r12) goto L_0x006b
            r16._loadMoreGuaranteed()
        L_0x006b:
            char[] r10 = r0._inputBuffer
            int r12 = r0._inputPtr
            int r14 = r12 + 1
            r0._inputPtr = r14
            char r10 = r10[r12]
            int r12 = r1.decodeBase64Char((char) r10)
            r14 = -2
            r15 = 2
            if (r12 >= 0) goto L_0x00e5
            if (r12 == r14) goto L_0x009d
            if (r10 != r11) goto L_0x0099
            int r4 = r9 >> 4
            int r5 = r7 + 1
            byte r4 = (byte) r4
            r3[r7] = r4
            boolean r4 = r17.usesPadding()
            if (r4 == 0) goto L_0x0096
            int r4 = r0._inputPtr
            int r4 = r4 - r13
            r0._inputPtr = r4
            r16._handleBase64MissingPadding(r17)
        L_0x0096:
            r7 = r5
            goto L_0x0120
        L_0x0099:
            int r12 = r0._decodeBase64Escape((com.fasterxml.jackson.core.Base64Variant) r1, (char) r10, (int) r15)
        L_0x009d:
            if (r12 != r14) goto L_0x00e5
            int r10 = r0._inputPtr
            int r11 = r0._inputEnd
            if (r10 < r11) goto L_0x00a8
            r16._loadMoreGuaranteed()
        L_0x00a8:
            char[] r10 = r0._inputBuffer
            int r11 = r0._inputPtr
            int r12 = r11 + 1
            r0._inputPtr = r12
            char r10 = r10[r11]
            boolean r11 = r1.usesPaddingChar((char) r10)
            if (r11 != 0) goto L_0x00db
            int r11 = r0._decodeBase64Escape((com.fasterxml.jackson.core.Base64Variant) r1, (char) r10, (int) r5)
            if (r11 != r14) goto L_0x00bf
            goto L_0x00db
        L_0x00bf:
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
        L_0x00db:
            int r9 = r9 >> 4
            int r10 = r7 + 1
            byte r9 = (byte) r9
            r3[r7] = r9
            r7 = r10
            goto L_0x000e
        L_0x00e5:
            int r9 = r9 << 6
            r9 = r9 | r12
            int r10 = r0._inputPtr
            int r12 = r0._inputEnd
            if (r10 < r12) goto L_0x00f1
            r16._loadMoreGuaranteed()
        L_0x00f1:
            char[] r10 = r0._inputBuffer
            int r12 = r0._inputPtr
            int r5 = r12 + 1
            r0._inputPtr = r5
            char r5 = r10[r12]
            int r10 = r1.decodeBase64Char((char) r5)
            if (r10 >= 0) goto L_0x0143
            if (r10 == r14) goto L_0x012f
            if (r5 != r11) goto L_0x0129
            int r4 = r9 >> 2
            int r5 = r7 + 1
            int r9 = r9 >> 10
            byte r9 = (byte) r9
            r3[r7] = r9
            int r7 = r7 + r15
            byte r4 = (byte) r4
            r3[r5] = r4
            boolean r4 = r17.usesPadding()
            if (r4 == 0) goto L_0x0120
            int r4 = r0._inputPtr
            int r4 = r4 - r13
            r0._inputPtr = r4
            r16._handleBase64MissingPadding(r17)
        L_0x0120:
            r0._tokenIncomplete = r6
            if (r7 <= 0) goto L_0x0128
            int r8 = r8 + r7
            r2.write(r3, r6, r7)
        L_0x0128:
            return r8
        L_0x0129:
            r11 = 3
            int r10 = r0._decodeBase64Escape((com.fasterxml.jackson.core.Base64Variant) r1, (char) r5, (int) r11)
            goto L_0x0130
        L_0x012f:
            r11 = 3
        L_0x0130:
            if (r10 != r14) goto L_0x0144
            int r5 = r9 >> 2
            int r10 = r7 + 1
            int r9 = r9 >> 10
            byte r9 = (byte) r9
            r3[r7] = r9
            int r7 = r7 + 2
            byte r5 = (byte) r5
            r3[r10] = r5
        L_0x0140:
            r5 = r11
            goto L_0x000e
        L_0x0143:
            r11 = 3
        L_0x0144:
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
            goto L_0x0140
        */
        throw new UnsupportedOperationException("Method not decompiled: com.fasterxml.jackson.core.json.ReaderBasedJsonParser._readBinary(com.fasterxml.jackson.core.Base64Variant, java.io.OutputStream, byte[]):int");
    }

    public void _releaseBuffers() throws IOException {
        char[] cArr;
        super._releaseBuffers();
        this._symbols.release();
        if (this._bufferRecyclable && (cArr = this._inputBuffer) != null) {
            this._inputBuffer = null;
            this._ioContext.releaseTokenBuffer(cArr);
        }
    }

    public void _reportInvalidToken(String str) throws IOException {
        _reportInvalidToken(str, _validJsonTokenList());
    }

    public final void _skipCR() throws IOException {
        if (this._inputPtr < this._inputEnd || _loadMore()) {
            char[] cArr = this._inputBuffer;
            int i3 = this._inputPtr;
            if (cArr[i3] == 10) {
                this._inputPtr = i3 + 1;
            }
        }
        this._currInputRow++;
        this._currInputRowStart = this._inputPtr;
    }

    public final void _skipString() throws IOException {
        this._tokenIncomplete = false;
        int i3 = this._inputPtr;
        int i4 = this._inputEnd;
        char[] cArr = this._inputBuffer;
        while (true) {
            if (i3 >= i4) {
                this._inputPtr = i3;
                if (!_loadMore()) {
                    _reportInvalidEOF(": was expecting closing quote for a string value", JsonToken.VALUE_STRING);
                }
                i3 = this._inputPtr;
                i4 = this._inputEnd;
            }
            int i5 = i3 + 1;
            char c3 = cArr[i3];
            if (c3 <= '\\') {
                if (c3 == '\\') {
                    this._inputPtr = i5;
                    _decodeEscaped();
                    i3 = this._inputPtr;
                    i4 = this._inputEnd;
                } else if (c3 <= '\"') {
                    if (c3 == '\"') {
                        this._inputPtr = i5;
                        return;
                    } else if (c3 < ' ') {
                        this._inputPtr = i5;
                        _throwUnquotedSpace(c3, "string value");
                    }
                }
            }
            i3 = i5;
        }
    }

    public void finishToken() throws IOException {
        if (this._tokenIncomplete) {
            this._tokenIncomplete = false;
            _finishString();
        }
    }

    public byte[] getBinaryValue(Base64Variant base64Variant) throws IOException {
        byte[] bArr;
        JsonToken jsonToken = this._currToken;
        if (jsonToken == JsonToken.VALUE_EMBEDDED_OBJECT && (bArr = this._binaryValue) != null) {
            return bArr;
        }
        if (jsonToken != JsonToken.VALUE_STRING) {
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
        return new JsonLocation(_contentReference(), -1, ((long) this._inputPtr) + this._currInputProcessed, this._currInputRow, (this._inputPtr - this._currInputRowStart) + 1);
    }

    public Object getInputSource() {
        return this._reader;
    }

    @Deprecated
    public char getNextChar(String str) throws IOException {
        return getNextChar(str, (JsonToken) null);
    }

    public JacksonFeatureSet<StreamReadCapability> getReadCapabilities() {
        return ParserBase.JSON_READ_CAPABILITIES;
    }

    public final String getText() throws IOException {
        JsonToken jsonToken = this._currToken;
        if (jsonToken != JsonToken.VALUE_STRING) {
            return _getText2(jsonToken);
        }
        if (this._tokenIncomplete) {
            this._tokenIncomplete = false;
            _finishString();
        }
        return this._textBuffer.contentsAsString();
    }

    public final char[] getTextCharacters() throws IOException {
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

    public final int getTextLength() throws IOException {
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
    public final int getTextOffset() throws java.io.IOException {
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
        throw new UnsupportedOperationException("Method not decompiled: com.fasterxml.jackson.core.json.ReaderBasedJsonParser.getTextOffset():int");
    }

    public JsonLocation getTokenLocation() {
        if (this._currToken == JsonToken.FIELD_NAME) {
            long j2 = this._currInputProcessed;
            return new JsonLocation(_contentReference(), -1, (this._nameStartOffset - 1) + j2, this._nameStartRow, this._nameStartCol);
        }
        return new JsonLocation(_contentReference(), -1, this._tokenInputTotal - 1, this._tokenInputRow, this._tokenInputCol);
    }

    public final String getValueAsString() throws IOException {
        JsonToken jsonToken = this._currToken;
        if (jsonToken == JsonToken.VALUE_STRING) {
            if (this._tokenIncomplete) {
                this._tokenIncomplete = false;
                _finishString();
            }
            return this._textBuffer.contentsAsString();
        } else if (jsonToken == JsonToken.FIELD_NAME) {
            return getCurrentName();
        } else {
            return super.getValueAsString((String) null);
        }
    }

    public final Boolean nextBooleanValue() throws IOException {
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
        if (nextToken != null) {
            int id = nextToken.id();
            if (id == 9) {
                return Boolean.TRUE;
            }
            if (id == 10) {
                return Boolean.FALSE;
            }
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
        if (_skipWSOrEnd == 93 || _skipWSOrEnd == 125) {
            _closeScope(_skipWSOrEnd);
            return false;
        }
        if (this._parsingContext.expectComma()) {
            _skipWSOrEnd = _skipComma(_skipWSOrEnd);
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
            char[] asQuotedChars = serializableString.asQuotedChars();
            int length = asQuotedChars.length;
            int i4 = this._inputPtr;
            if (i4 + length + 4 < this._inputEnd) {
                int i5 = length + i4;
                if (this._inputBuffer[i5] == '\"') {
                    while (i4 != i5) {
                        if (asQuotedChars[i3] == this._inputBuffer[i4]) {
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
        return _isNextTokenNameMaybe(_skipWSOrEnd, serializableString.getValue());
    }

    public final int nextIntValue(int i3) throws IOException {
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

    public final long nextLongValue(long j2) throws IOException {
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

    public final String nextTextValue() throws IOException {
        if (this._currToken == JsonToken.FIELD_NAME) {
            this._nameCopied = false;
            JsonToken jsonToken = this._nextToken;
            this._nextToken = null;
            this._currToken = jsonToken;
            if (jsonToken == JsonToken.VALUE_STRING) {
                if (this._tokenIncomplete) {
                    this._tokenIncomplete = false;
                    _finishString();
                }
                return this._textBuffer.contentsAsString();
            }
            if (jsonToken == JsonToken.START_ARRAY) {
                this._parsingContext = this._parsingContext.createChildArrayContext(this._tokenInputRow, this._tokenInputCol);
            } else if (jsonToken == JsonToken.START_OBJECT) {
                this._parsingContext = this._parsingContext.createChildObjectContext(this._tokenInputRow, this._tokenInputCol);
            }
            return null;
        } else if (nextToken() == JsonToken.VALUE_STRING) {
            return getText();
        } else {
            return null;
        }
    }

    public final JsonToken nextToken() throws IOException {
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
        if (_skipWSOrEnd == 93 || _skipWSOrEnd == 125) {
            _closeScope(_skipWSOrEnd);
            return this._currToken;
        }
        if (this._parsingContext.expectComma()) {
            _skipWSOrEnd = _skipComma(_skipWSOrEnd);
            if ((this._features & FEAT_MASK_TRAILING_COMMA) != 0 && (_skipWSOrEnd == 93 || _skipWSOrEnd == 125)) {
                _closeScope(_skipWSOrEnd);
                return this._currToken;
            }
        }
        boolean inObject = this._parsingContext.inObject();
        if (inObject) {
            _updateNameLocation();
            this._parsingContext.setCurrentName(_skipWSOrEnd == 34 ? _parseName() : _handleOddName(_skipWSOrEnd));
            this._currToken = jsonToken3;
            _skipWSOrEnd = _skipColon();
        }
        _updateLocation();
        if (_skipWSOrEnd == 34) {
            this._tokenIncomplete = true;
            jsonToken = JsonToken.VALUE_STRING;
        } else if (_skipWSOrEnd == 43) {
            jsonToken = isEnabled(JsonReadFeature.ALLOW_LEADING_PLUS_SIGN_FOR_NUMBERS.mappedFeature()) ? _parseSignedNumber(false) : _handleOddValue(_skipWSOrEnd);
        } else if (_skipWSOrEnd == 91) {
            if (!inObject) {
                this._parsingContext = this._parsingContext.createChildArrayContext(this._tokenInputRow, this._tokenInputCol);
            }
            jsonToken = JsonToken.START_ARRAY;
        } else if (_skipWSOrEnd == 102) {
            _matchFalse();
            jsonToken = JsonToken.VALUE_FALSE;
        } else if (_skipWSOrEnd != 110) {
            if (_skipWSOrEnd != 116) {
                if (_skipWSOrEnd == 123) {
                    if (!inObject) {
                        this._parsingContext = this._parsingContext.createChildObjectContext(this._tokenInputRow, this._tokenInputCol);
                    }
                    jsonToken = JsonToken.START_OBJECT;
                } else if (_skipWSOrEnd == 125) {
                    _reportUnexpectedChar(_skipWSOrEnd, "expected a value");
                } else if (_skipWSOrEnd == 45) {
                    jsonToken = _parseSignedNumber(true);
                } else if (_skipWSOrEnd != 46) {
                    switch (_skipWSOrEnd) {
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
                            jsonToken = _parseUnsignedNumber(_skipWSOrEnd);
                            break;
                        default:
                            jsonToken = _handleOddValue(_skipWSOrEnd);
                            break;
                    }
                } else {
                    jsonToken = _parseFloatThatStartsWithPeriod(false);
                }
            }
            _matchTrue();
            jsonToken = JsonToken.VALUE_TRUE;
        } else {
            _matchNull();
            jsonToken = JsonToken.VALUE_NULL;
        }
        if (inObject) {
            this._nextToken = jsonToken;
            return this._currToken;
        }
        this._currToken = jsonToken;
        return jsonToken;
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

    public int releaseBuffered(Writer writer) throws IOException {
        int i3 = this._inputEnd;
        int i4 = this._inputPtr;
        int i5 = i3 - i4;
        if (i5 < 1) {
            return 0;
        }
        this._inputPtr = i4 + i5;
        writer.write(this._inputBuffer, i4, i5);
        return i5;
    }

    public void setCodec(ObjectCodec objectCodec) {
        this._objectCodec = objectCodec;
    }

    /*  JADX ERROR: JadxRuntimeException in pass: InitCodeVariables
        jadx.core.utils.exceptions.JadxRuntimeException: Several immutable types in one variable: [int, char], vars: [r10v0 ?, r10v1 ?, r10v4 ?]
        	at jadx.core.dex.visitors.InitCodeVariables.setCodeVarType(InitCodeVariables.java:102)
        	at jadx.core.dex.visitors.InitCodeVariables.setCodeVar(InitCodeVariables.java:78)
        	at jadx.core.dex.visitors.InitCodeVariables.initCodeVar(InitCodeVariables.java:69)
        	at jadx.core.dex.visitors.InitCodeVariables.initCodeVars(InitCodeVariables.java:48)
        	at jadx.core.dex.visitors.InitCodeVariables.visit(InitCodeVariables.java:32)
        */
    public com.fasterxml.jackson.core.JsonToken _handleInvalidNumberStart(
/*
Method generation error in method: com.fasterxml.jackson.core.json.ReaderBasedJsonParser._handleInvalidNumberStart(int, boolean, boolean):com.fasterxml.jackson.core.JsonToken, dex: classes3.dex
    jadx.core.utils.exceptions.JadxRuntimeException: Code variable not set in r10v0 ?
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

    public final JsonToken _parseFloatThatStartsWithPeriod(boolean z2) throws IOException {
        if (!isEnabled(JsonReadFeature.ALLOW_LEADING_DECIMAL_POINT_FOR_NUMBERS.mappedFeature())) {
            return _handleOddValue(46);
        }
        int i3 = this._inputPtr;
        int i4 = i3 - 1;
        if (z2) {
            i4 = i3 - 2;
        }
        return _parseFloat(46, i4, i3, z2, 0);
    }

    public void _reportInvalidToken(String str, String str2) throws IOException {
        StringBuilder sb = new StringBuilder(str);
        while (true) {
            if (this._inputPtr >= this._inputEnd && !_loadMore()) {
                break;
            }
            char c3 = this._inputBuffer[this._inputPtr];
            if (Character.isJavaIdentifierPart(c3)) {
                this._inputPtr++;
                sb.append(c3);
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

    public char getNextChar(String str, JsonToken jsonToken) throws IOException {
        if (this._inputPtr >= this._inputEnd && !_loadMore()) {
            _reportInvalidEOF(str, jsonToken);
        }
        char[] cArr = this._inputBuffer;
        int i3 = this._inputPtr;
        this._inputPtr = i3 + 1;
        return cArr[i3];
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

    public final String getValueAsString(String str) throws IOException {
        JsonToken jsonToken = this._currToken;
        if (jsonToken == JsonToken.VALUE_STRING) {
            if (this._tokenIncomplete) {
                this._tokenIncomplete = false;
                _finishString();
            }
            return this._textBuffer.contentsAsString();
        } else if (jsonToken == JsonToken.FIELD_NAME) {
            return getCurrentName();
        } else {
            return super.getValueAsString(str);
        }
    }

    public ReaderBasedJsonParser(IOContext iOContext, int i3, Reader reader, ObjectCodec objectCodec, CharsToNameCanonicalizer charsToNameCanonicalizer) {
        super(iOContext, i3);
        this._reader = reader;
        this._inputBuffer = iOContext.allocTokenBuffer();
        this._inputPtr = 0;
        this._inputEnd = 0;
        this._objectCodec = objectCodec;
        this._symbols = charsToNameCanonicalizer;
        this._hashSeed = charsToNameCanonicalizer.hashSeed();
        this._bufferRecyclable = true;
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
        if (_skipWSOrEnd == 93 || _skipWSOrEnd == 125) {
            _closeScope(_skipWSOrEnd);
            return null;
        }
        if (this._parsingContext.expectComma()) {
            _skipWSOrEnd = _skipComma(_skipWSOrEnd);
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
        String _parseName = _skipWSOrEnd == 34 ? _parseName() : _handleOddName(_skipWSOrEnd);
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
                        jsonToken = _handleOddValue(_skipColon);
                        break;
                }
            } else {
                jsonToken = _parseFloatThatStartsWithPeriod(false);
            }
        } else if (isEnabled(JsonReadFeature.ALLOW_LEADING_PLUS_SIGN_FOR_NUMBERS.mappedFeature())) {
            jsonToken = _parseSignedNumber(false);
        } else {
            jsonToken = _handleOddValue(_skipColon);
        }
        this._nextToken = jsonToken;
        return _parseName;
    }
}
