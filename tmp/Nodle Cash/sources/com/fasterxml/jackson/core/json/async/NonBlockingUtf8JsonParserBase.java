package com.fasterxml.jackson.core.json.async;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.core.base.GeneratorBase;
import com.fasterxml.jackson.core.base.ParserBase;
import com.fasterxml.jackson.core.io.CharTypes;
import com.fasterxml.jackson.core.io.IOContext;
import com.fasterxml.jackson.core.json.JsonReadFeature;
import com.fasterxml.jackson.core.sym.ByteQuadsCanonicalizer;
import com.fasterxml.jackson.core.util.VersionUtil;
import java.io.IOException;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;
import org.apache.xerces.impl.xs.SchemaSymbols;
import org.objectweb.asm.signature.SignatureVisitor;

public abstract class NonBlockingUtf8JsonParserBase extends NonBlockingJsonParserBase {
    private static final int FEAT_MASK_ALLOW_JAVA_COMMENTS = JsonParser.Feature.ALLOW_COMMENTS.getMask();
    private static final int FEAT_MASK_ALLOW_MISSING = JsonParser.Feature.ALLOW_MISSING_VALUES.getMask();
    private static final int FEAT_MASK_ALLOW_SINGLE_QUOTES = JsonParser.Feature.ALLOW_SINGLE_QUOTES.getMask();
    private static final int FEAT_MASK_ALLOW_UNQUOTED_NAMES = JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES.getMask();
    private static final int FEAT_MASK_ALLOW_YAML_COMMENTS = JsonParser.Feature.ALLOW_YAML_COMMENTS.getMask();
    private static final int FEAT_MASK_LEADING_ZEROS = JsonParser.Feature.ALLOW_NUMERIC_LEADING_ZEROS.getMask();
    private static final int FEAT_MASK_TRAILING_COMMA = JsonParser.Feature.ALLOW_TRAILING_COMMA.getMask();
    protected static final int[] _icLatin1 = CharTypes.getInputCodeLatin1();
    private static final int[] _icUTF8 = CharTypes.getInputCodeUtf8();
    protected int _origBufferLen;

    public NonBlockingUtf8JsonParserBase(IOContext iOContext, int i3, ByteQuadsCanonicalizer byteQuadsCanonicalizer) {
        super(iOContext, i3, byteQuadsCanonicalizer);
    }

    private final int _decodeCharEscape() throws IOException {
        return this._inputEnd - this._inputPtr < 5 ? _decodeSplitEscaped(0, -1) : _decodeFastCharEscape();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:22:0x0038, code lost:
        r0 = getNextSignedByteFromBuffer();
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final int _decodeFastCharEscape() throws java.io.IOException {
        /*
            r3 = this;
            byte r0 = r3.getNextSignedByteFromBuffer()
            r1 = 34
            if (r0 == r1) goto L_0x0079
            r1 = 47
            if (r0 == r1) goto L_0x0079
            r1 = 92
            if (r0 == r1) goto L_0x0079
            r1 = 98
            if (r0 == r1) goto L_0x0076
            r1 = 102(0x66, float:1.43E-43)
            if (r0 == r1) goto L_0x0073
            r1 = 110(0x6e, float:1.54E-43)
            if (r0 == r1) goto L_0x0070
            r1 = 114(0x72, float:1.6E-43)
            if (r0 == r1) goto L_0x006d
            r1 = 116(0x74, float:1.63E-43)
            if (r0 == r1) goto L_0x006a
            r1 = 117(0x75, float:1.64E-43)
            if (r0 == r1) goto L_0x002e
            char r0 = (char) r0
            char r3 = r3._handleUnrecognizedCharacterEscape(r0)
            return r3
        L_0x002e:
            byte r0 = r3.getNextSignedByteFromBuffer()
            int r1 = com.fasterxml.jackson.core.io.CharTypes.charToHex(r0)
            if (r1 < 0) goto L_0x0061
            byte r0 = r3.getNextSignedByteFromBuffer()
            int r2 = com.fasterxml.jackson.core.io.CharTypes.charToHex(r0)
            if (r2 < 0) goto L_0x0061
            int r0 = r1 << 4
            r0 = r0 | r2
            byte r1 = r3.getNextSignedByteFromBuffer()
            int r2 = com.fasterxml.jackson.core.io.CharTypes.charToHex(r1)
            if (r2 < 0) goto L_0x0060
            int r0 = r0 << 4
            r0 = r0 | r2
            byte r1 = r3.getNextSignedByteFromBuffer()
            int r2 = com.fasterxml.jackson.core.io.CharTypes.charToHex(r1)
            if (r2 < 0) goto L_0x0060
            int r3 = r0 << 4
            r3 = r3 | r2
            return r3
        L_0x0060:
            r0 = r1
        L_0x0061:
            r0 = r0 & 255(0xff, float:3.57E-43)
            java.lang.String r1 = "expected a hex-digit for character escape sequence"
            r3._reportUnexpectedChar(r0, r1)
            r3 = -1
            return r3
        L_0x006a:
            r3 = 9
            return r3
        L_0x006d:
            r3 = 13
            return r3
        L_0x0070:
            r3 = 10
            return r3
        L_0x0073:
            r3 = 12
            return r3
        L_0x0076:
            r3 = 8
            return r3
        L_0x0079:
            char r3 = (char) r0
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.fasterxml.jackson.core.json.async.NonBlockingUtf8JsonParserBase._decodeFastCharEscape():int");
    }

    private int _decodeSplitEscaped(int i3, int i4) throws IOException {
        if (this._inputPtr >= this._inputEnd) {
            this._quoted32 = i3;
            this._quotedDigits = i4;
            return -1;
        }
        byte nextSignedByteFromBuffer = getNextSignedByteFromBuffer();
        if (i4 == -1) {
            if (nextSignedByteFromBuffer == 34 || nextSignedByteFromBuffer == 47 || nextSignedByteFromBuffer == 92) {
                return nextSignedByteFromBuffer;
            }
            if (nextSignedByteFromBuffer == 98) {
                return 8;
            }
            if (nextSignedByteFromBuffer == 102) {
                return 12;
            }
            if (nextSignedByteFromBuffer == 110) {
                return 10;
            }
            if (nextSignedByteFromBuffer == 114) {
                return 13;
            }
            if (nextSignedByteFromBuffer == 116) {
                return 9;
            }
            if (nextSignedByteFromBuffer != 117) {
                return _handleUnrecognizedCharacterEscape((char) nextSignedByteFromBuffer);
            }
            if (this._inputPtr >= this._inputEnd) {
                this._quotedDigits = 0;
                this._quoted32 = 0;
                return -1;
            }
            nextSignedByteFromBuffer = getNextSignedByteFromBuffer();
            i4 = 0;
        }
        int i5 = nextSignedByteFromBuffer & 255;
        while (true) {
            int charToHex = CharTypes.charToHex(i5);
            if (charToHex < 0) {
                _reportUnexpectedChar(i5 & 255, "expected a hex-digit for character escape sequence");
            }
            i3 = (i3 << 4) | charToHex;
            i4++;
            if (i4 == 4) {
                return i3;
            }
            if (this._inputPtr >= this._inputEnd) {
                this._quotedDigits = i4;
                this._quoted32 = i3;
                return -1;
            }
            i5 = getNextUnsignedByteFromBuffer();
        }
    }

    private final boolean _decodeSplitMultiByte(int i3, int i4, boolean z2) throws IOException {
        if (i4 == 1) {
            int _decodeSplitEscaped = _decodeSplitEscaped(0, -1);
            if (_decodeSplitEscaped < 0) {
                this._minorState = 41;
                return false;
            }
            this._textBuffer.append((char) _decodeSplitEscaped);
            return true;
        } else if (i4 != 2) {
            if (i4 == 3) {
                int i5 = i3 & 15;
                if (z2) {
                    return _decodeSplitUTF8_3(i5, 1, getNextSignedByteFromBuffer());
                }
                this._minorState = 43;
                this._pending32 = i5;
                this._pendingBytes = 1;
                return false;
            } else if (i4 != 4) {
                if (i3 < 32) {
                    _throwUnquotedSpace(i3, "string value");
                } else {
                    _reportInvalidChar(i3);
                }
                this._textBuffer.append((char) i3);
                return true;
            } else {
                int i6 = i3 & 7;
                if (z2) {
                    return _decodeSplitUTF8_4(i6, 1, getNextSignedByteFromBuffer());
                }
                this._pending32 = i6;
                this._pendingBytes = 1;
                this._minorState = 44;
                return false;
            }
        } else if (z2) {
            this._textBuffer.append((char) _decodeUTF8_2(i3, getNextSignedByteFromBuffer()));
            return true;
        } else {
            this._minorState = 42;
            this._pending32 = i3;
            return false;
        }
    }

    private final boolean _decodeSplitUTF8_3(int i3, int i4, int i5) throws IOException {
        if (i4 == 1) {
            if ((i5 & 192) != 128) {
                _reportInvalidOther(i5 & 255, this._inputPtr);
            }
            i3 = (i3 << 6) | (i5 & 63);
            if (this._inputPtr >= this._inputEnd) {
                this._minorState = 43;
                this._pending32 = i3;
                this._pendingBytes = 2;
                return false;
            }
            i5 = getNextSignedByteFromBuffer();
        }
        if ((i5 & 192) != 128) {
            _reportInvalidOther(i5 & 255, this._inputPtr);
        }
        this._textBuffer.append((char) ((i3 << 6) | (i5 & 63)));
        return true;
    }

    private final boolean _decodeSplitUTF8_4(int i3, int i4, int i5) throws IOException {
        if (i4 == 1) {
            if ((i5 & 192) != 128) {
                _reportInvalidOther(i5 & 255, this._inputPtr);
            }
            i3 = (i3 << 6) | (i5 & 63);
            if (this._inputPtr >= this._inputEnd) {
                this._minorState = 44;
                this._pending32 = i3;
                this._pendingBytes = 2;
                return false;
            }
            i5 = getNextSignedByteFromBuffer();
            i4 = 2;
        }
        if (i4 == 2) {
            if ((i5 & 192) != 128) {
                _reportInvalidOther(i5 & 255, this._inputPtr);
            }
            i3 = (i3 << 6) | (i5 & 63);
            if (this._inputPtr >= this._inputEnd) {
                this._minorState = 44;
                this._pending32 = i3;
                this._pendingBytes = 3;
                return false;
            }
            i5 = getNextSignedByteFromBuffer();
        }
        if ((i5 & 192) != 128) {
            _reportInvalidOther(i5 & 255, this._inputPtr);
        }
        int i6 = ((i3 << 6) | (i5 & 63)) - 65536;
        this._textBuffer.append((char) ((i6 >> 10) | GeneratorBase.SURR1_FIRST));
        this._textBuffer.append((char) ((i6 & 1023) | 56320));
        return true;
    }

    private final int _decodeUTF8_2(int i3, int i4) throws IOException {
        if ((i4 & 192) != 128) {
            _reportInvalidOther(i4 & 255, this._inputPtr);
        }
        return ((i3 & 31) << 6) | (i4 & 63);
    }

    private final int _decodeUTF8_3(int i3, int i4, int i5) throws IOException {
        int i6 = i3 & 15;
        if ((i4 & 192) != 128) {
            _reportInvalidOther(i4 & 255, this._inputPtr);
        }
        int i7 = (i6 << 6) | (i4 & 63);
        if ((i5 & 192) != 128) {
            _reportInvalidOther(i5 & 255, this._inputPtr);
        }
        return (i7 << 6) | (i5 & 63);
    }

    private final int _decodeUTF8_4(int i3, int i4, int i5, int i6) throws IOException {
        if ((i4 & 192) != 128) {
            _reportInvalidOther(i4 & 255, this._inputPtr);
        }
        int i7 = ((i3 & 7) << 6) | (i4 & 63);
        if ((i5 & 192) != 128) {
            _reportInvalidOther(i5 & 255, this._inputPtr);
        }
        int i8 = (i7 << 6) | (i5 & 63);
        if ((i6 & 192) != 128) {
            _reportInvalidOther(i6 & 255, this._inputPtr);
        }
        return ((i8 << 6) | (i6 & 63)) - 65536;
    }

    private final String _fastParseName() throws IOException {
        int[] iArr = _icLatin1;
        int i3 = this._inputPtr;
        int i4 = i3 + 1;
        byte byteFromBuffer = getByteFromBuffer(i3) & 255;
        if (iArr[byteFromBuffer] == 0) {
            int i5 = i3 + 2;
            byte byteFromBuffer2 = getByteFromBuffer(i4) & 255;
            if (iArr[byteFromBuffer2] == 0) {
                byte b3 = byteFromBuffer2 | (byteFromBuffer << 8);
                int i6 = i3 + 3;
                byte byteFromBuffer3 = getByteFromBuffer(i5) & 255;
                if (iArr[byteFromBuffer3] == 0) {
                    byte b4 = (b3 << 8) | byteFromBuffer3;
                    int i7 = i3 + 4;
                    byte byteFromBuffer4 = getByteFromBuffer(i6) & 255;
                    if (iArr[byteFromBuffer4] == 0) {
                        byte b5 = (b4 << 8) | byteFromBuffer4;
                        int i8 = i3 + 5;
                        byte byteFromBuffer5 = getByteFromBuffer(i7) & 255;
                        if (iArr[byteFromBuffer5] == 0) {
                            this._quad1 = b5;
                            return _parseMediumName(i8, byteFromBuffer5);
                        } else if (byteFromBuffer5 != 34) {
                            return null;
                        } else {
                            this._inputPtr = i8;
                            return _findName(b5, 4);
                        }
                    } else if (byteFromBuffer4 != 34) {
                        return null;
                    } else {
                        this._inputPtr = i7;
                        return _findName(b4, 3);
                    }
                } else if (byteFromBuffer3 != 34) {
                    return null;
                } else {
                    this._inputPtr = i6;
                    return _findName(b3, 2);
                }
            } else if (byteFromBuffer2 != 34) {
                return null;
            } else {
                this._inputPtr = i5;
                return _findName(byteFromBuffer, 1);
            }
        } else if (byteFromBuffer != 34) {
            return null;
        } else {
            this._inputPtr = i4;
            return "";
        }
    }

    private JsonToken _finishAposName(int i3, int i4, int i5) throws IOException {
        int[] iArr = this._quadBuffer;
        int[] iArr2 = _icLatin1;
        while (this._inputPtr < this._inputEnd) {
            int nextUnsignedByteFromBuffer = getNextUnsignedByteFromBuffer();
            if (nextUnsignedByteFromBuffer == 39) {
                if (r11 > 0) {
                    if (i3 >= iArr.length) {
                        iArr = ParserBase.growArrayBy(iArr, iArr.length);
                        this._quadBuffer = iArr;
                    }
                    iArr[i3] = NonBlockingJsonParserBase._padLastQuad(r10, r11);
                    i3++;
                } else if (i3 == 0) {
                    return _fieldComplete("");
                }
                String findName = this._symbols.findName(iArr, i3);
                if (findName == null) {
                    findName = _addName(iArr, i3, r11);
                }
                return _fieldComplete(findName);
            }
            if (!(nextUnsignedByteFromBuffer == 34 || iArr2[nextUnsignedByteFromBuffer] == 0)) {
                if (nextUnsignedByteFromBuffer != 92) {
                    _throwUnquotedSpace(nextUnsignedByteFromBuffer, "name");
                } else {
                    nextUnsignedByteFromBuffer = _decodeCharEscape();
                    if (nextUnsignedByteFromBuffer < 0) {
                        this._minorState = 8;
                        this._minorStateAfterSplit = 9;
                        this._quadLength = i3;
                        this._pending32 = r10;
                        this._pendingBytes = r11;
                        JsonToken jsonToken = JsonToken.NOT_AVAILABLE;
                        this._currToken = jsonToken;
                        return jsonToken;
                    }
                }
                if (nextUnsignedByteFromBuffer > 127) {
                    int i6 = 0;
                    if (r11 >= 4) {
                        if (i3 >= iArr.length) {
                            iArr = ParserBase.growArrayBy(iArr, iArr.length);
                            this._quadBuffer = iArr;
                        }
                        iArr[i3] = r10;
                        i3++;
                        r10 = 0;
                        r11 = 0;
                    }
                    if (nextUnsignedByteFromBuffer < 2048) {
                        r10 = (r10 << 8) | (nextUnsignedByteFromBuffer >> 6) | 192;
                        r11++;
                    } else {
                        int i7 = (r10 << 8) | (nextUnsignedByteFromBuffer >> 12) | 224;
                        int i8 = r11 + 1;
                        if (i8 >= 4) {
                            if (i3 >= iArr.length) {
                                iArr = ParserBase.growArrayBy(iArr, iArr.length);
                                this._quadBuffer = iArr;
                            }
                            iArr[i3] = i7;
                            i3++;
                            i8 = 0;
                        } else {
                            i6 = i7;
                        }
                        r10 = (i6 << 8) | ((nextUnsignedByteFromBuffer >> 6) & 63) | 128;
                        r11 = i8 + 1;
                    }
                    nextUnsignedByteFromBuffer = (nextUnsignedByteFromBuffer & 63) | 128;
                }
            }
            if (r11 < 4) {
                i5 = r11 + 1;
                i4 = (r10 << 8) | nextUnsignedByteFromBuffer;
            } else {
                if (i3 >= iArr.length) {
                    iArr = ParserBase.growArrayBy(iArr, iArr.length);
                    this._quadBuffer = iArr;
                }
                iArr[i3] = r10;
                i3++;
                i4 = nextUnsignedByteFromBuffer;
                i5 = 1;
            }
        }
        this._quadLength = i3;
        this._pending32 = r10;
        this._pendingBytes = r11;
        this._minorState = 9;
        JsonToken jsonToken2 = JsonToken.NOT_AVAILABLE;
        this._currToken = jsonToken2;
        return jsonToken2;
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v3, resolved type: boolean} */
    /* JADX WARNING: type inference failed for: r7v0 */
    /* JADX WARNING: type inference failed for: r7v1, types: [int] */
    /* JADX WARNING: type inference failed for: r7v2 */
    /* JADX WARNING: type inference failed for: r7v4 */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final com.fasterxml.jackson.core.JsonToken _finishAposString() throws java.io.IOException {
        /*
            r12 = this;
            int[] r0 = _icUTF8
            com.fasterxml.jackson.core.util.TextBuffer r1 = r12._textBuffer
            char[] r1 = r1.getBufferWithoutReset()
            com.fasterxml.jackson.core.util.TextBuffer r2 = r12._textBuffer
            int r2 = r2.getCurrentSegmentSize()
            int r3 = r12._inputPtr
            int r4 = r12._inputEnd
            int r4 = r4 + -5
        L_0x0014:
            int r5 = r12._inputEnd
            r6 = 45
            if (r3 < r5) goto L_0x0028
            r12._inputPtr = r3
            r12._minorState = r6
            com.fasterxml.jackson.core.util.TextBuffer r0 = r12._textBuffer
            r0.setCurrentLength(r2)
            com.fasterxml.jackson.core.JsonToken r0 = com.fasterxml.jackson.core.JsonToken.NOT_AVAILABLE
            r12._currToken = r0
            return r0
        L_0x0028:
            int r5 = r1.length
            r7 = 0
            if (r2 < r5) goto L_0x0033
            com.fasterxml.jackson.core.util.TextBuffer r1 = r12._textBuffer
            char[] r1 = r1.finishCurrentSegment()
            r2 = r7
        L_0x0033:
            int r5 = r12._inputEnd
            int r8 = r1.length
            int r8 = r8 - r2
            int r8 = r8 + r3
            int r5 = java.lang.Math.min(r5, r8)
        L_0x003c:
            if (r3 >= r5) goto L_0x0014
            int r8 = r3 + 1
            byte r9 = r12.getByteFromBuffer(r3)
            r9 = r9 & 255(0xff, float:3.57E-43)
            r10 = r0[r9]
            if (r10 == 0) goto L_0x0101
            r11 = 34
            if (r9 == r11) goto L_0x0101
            r5 = 1
            if (r8 < r4) goto L_0x007b
            r12._inputPtr = r8
            com.fasterxml.jackson.core.util.TextBuffer r1 = r12._textBuffer
            r1.setCurrentLength(r2)
            r1 = r0[r9]
            int r2 = r12._inputEnd
            if (r8 >= r2) goto L_0x005f
            r7 = r5
        L_0x005f:
            boolean r1 = r12._decodeSplitMultiByte(r9, r1, r7)
            if (r1 != 0) goto L_0x006c
            r12._minorStateAfterSplit = r6
            com.fasterxml.jackson.core.JsonToken r0 = com.fasterxml.jackson.core.JsonToken.NOT_AVAILABLE
            r12._currToken = r0
            return r0
        L_0x006c:
            com.fasterxml.jackson.core.util.TextBuffer r1 = r12._textBuffer
            char[] r1 = r1.getBufferWithoutReset()
            com.fasterxml.jackson.core.util.TextBuffer r2 = r12._textBuffer
            int r2 = r2.getCurrentSegmentSize()
            int r3 = r12._inputPtr
            goto L_0x0014
        L_0x007b:
            if (r10 == r5) goto L_0x00e7
            r5 = 2
            if (r10 == r5) goto L_0x00dc
            r5 = 3
            if (r10 == r5) goto L_0x00cb
            r5 = 4
            if (r10 == r5) goto L_0x0096
            r3 = 32
            if (r9 >= r3) goto L_0x0091
            java.lang.String r3 = "string value"
            r12._throwUnquotedSpace(r9, r3)
            goto L_0x0094
        L_0x0091:
            r12._reportInvalidChar(r9)
        L_0x0094:
            r3 = r8
            goto L_0x00ef
        L_0x0096:
            int r5 = r3 + 2
            byte r6 = r12.getByteFromBuffer(r8)
            int r8 = r3 + 3
            byte r5 = r12.getByteFromBuffer(r5)
            int r3 = r3 + 4
            byte r8 = r12.getByteFromBuffer(r8)
            int r5 = r12._decodeUTF8_4(r9, r6, r5, r8)
            int r6 = r2 + 1
            int r8 = r5 >> 10
            r9 = 55296(0xd800, float:7.7486E-41)
            r8 = r8 | r9
            char r8 = (char) r8
            r1[r2] = r8
            int r2 = r1.length
            if (r6 < r2) goto L_0x00c2
            com.fasterxml.jackson.core.util.TextBuffer r1 = r12._textBuffer
            char[] r1 = r1.finishCurrentSegment()
            r2 = r7
            goto L_0x00c3
        L_0x00c2:
            r2 = r6
        L_0x00c3:
            r5 = r5 & 1023(0x3ff, float:1.434E-42)
            r6 = 56320(0xdc00, float:7.8921E-41)
            r9 = r5 | r6
            goto L_0x00ef
        L_0x00cb:
            int r5 = r3 + 2
            byte r6 = r12.getByteFromBuffer(r8)
            int r3 = r3 + 3
            byte r5 = r12.getByteFromBuffer(r5)
            int r9 = r12._decodeUTF8_3(r9, r6, r5)
            goto L_0x00ef
        L_0x00dc:
            int r3 = r3 + 2
            byte r5 = r12.getByteFromBuffer(r8)
            int r9 = r12._decodeUTF8_2(r9, r5)
            goto L_0x00ef
        L_0x00e7:
            r12._inputPtr = r8
            int r9 = r12._decodeFastCharEscape()
            int r3 = r12._inputPtr
        L_0x00ef:
            int r5 = r1.length
            if (r2 < r5) goto L_0x00f9
            com.fasterxml.jackson.core.util.TextBuffer r1 = r12._textBuffer
            char[] r1 = r1.finishCurrentSegment()
            goto L_0x00fa
        L_0x00f9:
            r7 = r2
        L_0x00fa:
            int r2 = r7 + 1
            char r5 = (char) r9
            r1[r7] = r5
            goto L_0x0014
        L_0x0101:
            r3 = 39
            if (r9 != r3) goto L_0x0113
            r12._inputPtr = r8
            com.fasterxml.jackson.core.util.TextBuffer r0 = r12._textBuffer
            r0.setCurrentLength(r2)
            com.fasterxml.jackson.core.JsonToken r0 = com.fasterxml.jackson.core.JsonToken.VALUE_STRING
            com.fasterxml.jackson.core.JsonToken r12 = r12._valueComplete(r0)
            return r12
        L_0x0113:
            int r3 = r2 + 1
            char r9 = (char) r9
            r1[r2] = r9
            r2 = r3
            r3 = r8
            goto L_0x003c
        */
        throw new UnsupportedOperationException("Method not decompiled: com.fasterxml.jackson.core.json.async.NonBlockingUtf8JsonParserBase._finishAposString():com.fasterxml.jackson.core.JsonToken");
    }

    private final JsonToken _finishBOM(int i3) throws IOException {
        while (this._inputPtr < this._inputEnd) {
            int nextUnsignedByteFromBuffer = getNextUnsignedByteFromBuffer();
            if (i3 != 1) {
                if (i3 != 2) {
                    if (i3 == 3) {
                        this._currInputProcessed -= 3;
                        return _startDocument(nextUnsignedByteFromBuffer);
                    }
                } else if (nextUnsignedByteFromBuffer != 191) {
                    _reportError("Unexpected byte 0x%02x following 0xEF 0xBB; should get 0xBF as third byte of UTF-8 BOM", Integer.valueOf(nextUnsignedByteFromBuffer));
                }
            } else if (nextUnsignedByteFromBuffer != 187) {
                _reportError("Unexpected byte 0x%02x following 0xEF; should get 0xBB as second byte UTF-8 BOM", Integer.valueOf(nextUnsignedByteFromBuffer));
            }
            i3++;
        }
        this._pending32 = i3;
        this._minorState = 1;
        JsonToken jsonToken = JsonToken.NOT_AVAILABLE;
        this._currToken = jsonToken;
        return jsonToken;
    }

    private final JsonToken _finishCComment(int i3, boolean z2) throws IOException {
        while (this._inputPtr < this._inputEnd) {
            int nextUnsignedByteFromBuffer = getNextUnsignedByteFromBuffer();
            if (nextUnsignedByteFromBuffer < 32) {
                if (nextUnsignedByteFromBuffer == 10) {
                    this._currInputRow++;
                    this._currInputRowStart = this._inputPtr;
                } else if (nextUnsignedByteFromBuffer == 13) {
                    this._currInputRowAlt++;
                    this._currInputRowStart = this._inputPtr;
                } else if (nextUnsignedByteFromBuffer != 9) {
                    _throwInvalidSpace(nextUnsignedByteFromBuffer);
                }
            } else if (nextUnsignedByteFromBuffer == 42) {
                z2 = true;
            } else if (nextUnsignedByteFromBuffer == 47 && z2) {
                return _startAfterComment(i3);
            }
            z2 = false;
        }
        this._minorState = z2 ? 52 : 53;
        this._pending32 = i3;
        JsonToken jsonToken = JsonToken.NOT_AVAILABLE;
        this._currToken = jsonToken;
        return jsonToken;
    }

    private final JsonToken _finishCppComment(int i3) throws IOException {
        while (this._inputPtr < this._inputEnd) {
            int nextUnsignedByteFromBuffer = getNextUnsignedByteFromBuffer();
            if (nextUnsignedByteFromBuffer < 32) {
                if (nextUnsignedByteFromBuffer == 10) {
                    this._currInputRow++;
                    this._currInputRowStart = this._inputPtr;
                } else if (nextUnsignedByteFromBuffer == 13) {
                    this._currInputRowAlt++;
                    this._currInputRowStart = this._inputPtr;
                } else if (nextUnsignedByteFromBuffer != 9) {
                    _throwInvalidSpace(nextUnsignedByteFromBuffer);
                }
                return _startAfterComment(i3);
            }
        }
        this._minorState = 54;
        this._pending32 = i3;
        JsonToken jsonToken = JsonToken.NOT_AVAILABLE;
        this._currToken = jsonToken;
        return jsonToken;
    }

    private final JsonToken _finishHashComment(int i3) throws IOException {
        if ((this._features & FEAT_MASK_ALLOW_YAML_COMMENTS) == 0) {
            _reportUnexpectedChar(35, "maybe a (non-standard) comment? (not recognized as one since Feature 'ALLOW_YAML_COMMENTS' not enabled for parser)");
        }
        while (this._inputPtr < this._inputEnd) {
            int nextUnsignedByteFromBuffer = getNextUnsignedByteFromBuffer();
            if (nextUnsignedByteFromBuffer < 32) {
                if (nextUnsignedByteFromBuffer == 10) {
                    this._currInputRow++;
                    this._currInputRowStart = this._inputPtr;
                } else if (nextUnsignedByteFromBuffer == 13) {
                    this._currInputRowAlt++;
                    this._currInputRowStart = this._inputPtr;
                } else if (nextUnsignedByteFromBuffer != 9) {
                    _throwInvalidSpace(nextUnsignedByteFromBuffer);
                }
                return _startAfterComment(i3);
            }
        }
        this._minorState = 55;
        this._pending32 = i3;
        JsonToken jsonToken = JsonToken.NOT_AVAILABLE;
        this._currToken = jsonToken;
        return jsonToken;
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v3, resolved type: boolean} */
    /* JADX WARNING: type inference failed for: r7v0 */
    /* JADX WARNING: type inference failed for: r7v1, types: [int] */
    /* JADX WARNING: type inference failed for: r7v2 */
    /* JADX WARNING: type inference failed for: r7v4 */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final com.fasterxml.jackson.core.JsonToken _finishRegularString() throws java.io.IOException {
        /*
            r11 = this;
            int[] r0 = _icUTF8
            com.fasterxml.jackson.core.util.TextBuffer r1 = r11._textBuffer
            char[] r1 = r1.getBufferWithoutReset()
            com.fasterxml.jackson.core.util.TextBuffer r2 = r11._textBuffer
            int r2 = r2.getCurrentSegmentSize()
            int r3 = r11._inputPtr
            int r4 = r11._inputEnd
            int r4 = r4 + -5
        L_0x0014:
            int r5 = r11._inputEnd
            r6 = 40
            if (r3 < r5) goto L_0x0028
            r11._inputPtr = r3
            r11._minorState = r6
            com.fasterxml.jackson.core.util.TextBuffer r0 = r11._textBuffer
            r0.setCurrentLength(r2)
            com.fasterxml.jackson.core.JsonToken r0 = com.fasterxml.jackson.core.JsonToken.NOT_AVAILABLE
            r11._currToken = r0
            return r0
        L_0x0028:
            int r5 = r1.length
            r7 = 0
            if (r2 < r5) goto L_0x0033
            com.fasterxml.jackson.core.util.TextBuffer r1 = r11._textBuffer
            char[] r1 = r1.finishCurrentSegment()
            r2 = r7
        L_0x0033:
            int r5 = r11._inputEnd
            int r8 = r1.length
            int r8 = r8 - r2
            int r8 = r8 + r3
            int r5 = java.lang.Math.min(r5, r8)
        L_0x003c:
            if (r3 >= r5) goto L_0x0014
            int r8 = r3 + 1
            byte r9 = r11.getByteFromBuffer(r3)
            r9 = r9 & 255(0xff, float:3.57E-43)
            r10 = r0[r9]
            if (r10 == 0) goto L_0x010f
            r5 = 34
            if (r9 != r5) goto L_0x005c
            r11._inputPtr = r8
            com.fasterxml.jackson.core.util.TextBuffer r0 = r11._textBuffer
            r0.setCurrentLength(r2)
            com.fasterxml.jackson.core.JsonToken r0 = com.fasterxml.jackson.core.JsonToken.VALUE_STRING
            com.fasterxml.jackson.core.JsonToken r11 = r11._valueComplete(r0)
            return r11
        L_0x005c:
            r5 = 1
            if (r8 < r4) goto L_0x0089
            r11._inputPtr = r8
            com.fasterxml.jackson.core.util.TextBuffer r1 = r11._textBuffer
            r1.setCurrentLength(r2)
            r1 = r0[r9]
            int r2 = r11._inputEnd
            if (r8 >= r2) goto L_0x006d
            r7 = r5
        L_0x006d:
            boolean r1 = r11._decodeSplitMultiByte(r9, r1, r7)
            if (r1 != 0) goto L_0x007a
            r11._minorStateAfterSplit = r6
            com.fasterxml.jackson.core.JsonToken r0 = com.fasterxml.jackson.core.JsonToken.NOT_AVAILABLE
            r11._currToken = r0
            return r0
        L_0x007a:
            com.fasterxml.jackson.core.util.TextBuffer r1 = r11._textBuffer
            char[] r1 = r1.getBufferWithoutReset()
            com.fasterxml.jackson.core.util.TextBuffer r2 = r11._textBuffer
            int r2 = r2.getCurrentSegmentSize()
            int r3 = r11._inputPtr
            goto L_0x0014
        L_0x0089:
            if (r10 == r5) goto L_0x00f5
            r5 = 2
            if (r10 == r5) goto L_0x00ea
            r5 = 3
            if (r10 == r5) goto L_0x00d9
            r5 = 4
            if (r10 == r5) goto L_0x00a4
            r3 = 32
            if (r9 >= r3) goto L_0x009f
            java.lang.String r3 = "string value"
            r11._throwUnquotedSpace(r9, r3)
            goto L_0x00a2
        L_0x009f:
            r11._reportInvalidChar(r9)
        L_0x00a2:
            r3 = r8
            goto L_0x00fd
        L_0x00a4:
            int r5 = r3 + 2
            byte r6 = r11.getByteFromBuffer(r8)
            int r8 = r3 + 3
            byte r5 = r11.getByteFromBuffer(r5)
            int r3 = r3 + 4
            byte r8 = r11.getByteFromBuffer(r8)
            int r5 = r11._decodeUTF8_4(r9, r6, r5, r8)
            int r6 = r2 + 1
            int r8 = r5 >> 10
            r9 = 55296(0xd800, float:7.7486E-41)
            r8 = r8 | r9
            char r8 = (char) r8
            r1[r2] = r8
            int r2 = r1.length
            if (r6 < r2) goto L_0x00d0
            com.fasterxml.jackson.core.util.TextBuffer r1 = r11._textBuffer
            char[] r1 = r1.finishCurrentSegment()
            r2 = r7
            goto L_0x00d1
        L_0x00d0:
            r2 = r6
        L_0x00d1:
            r5 = r5 & 1023(0x3ff, float:1.434E-42)
            r6 = 56320(0xdc00, float:7.8921E-41)
            r9 = r5 | r6
            goto L_0x00fd
        L_0x00d9:
            int r5 = r3 + 2
            byte r6 = r11.getByteFromBuffer(r8)
            int r3 = r3 + 3
            byte r5 = r11.getByteFromBuffer(r5)
            int r9 = r11._decodeUTF8_3(r9, r6, r5)
            goto L_0x00fd
        L_0x00ea:
            int r3 = r3 + 2
            byte r5 = r11.getByteFromBuffer(r8)
            int r9 = r11._decodeUTF8_2(r9, r5)
            goto L_0x00fd
        L_0x00f5:
            r11._inputPtr = r8
            int r9 = r11._decodeFastCharEscape()
            int r3 = r11._inputPtr
        L_0x00fd:
            int r5 = r1.length
            if (r2 < r5) goto L_0x0107
            com.fasterxml.jackson.core.util.TextBuffer r1 = r11._textBuffer
            char[] r1 = r1.finishCurrentSegment()
            goto L_0x0108
        L_0x0107:
            r7 = r2
        L_0x0108:
            int r2 = r7 + 1
            char r5 = (char) r9
            r1[r7] = r5
            goto L_0x0014
        L_0x010f:
            int r3 = r2 + 1
            char r9 = (char) r9
            r1[r2] = r9
            r2 = r3
            r3 = r8
            goto L_0x003c
        */
        throw new UnsupportedOperationException("Method not decompiled: com.fasterxml.jackson.core.json.async.NonBlockingUtf8JsonParserBase._finishRegularString():com.fasterxml.jackson.core.JsonToken");
    }

    private JsonToken _finishUnquotedName(int i3, int i4, int i5) throws IOException {
        int[] iArr = this._quadBuffer;
        int[] inputCodeUtf8JsNames = CharTypes.getInputCodeUtf8JsNames();
        while (true) {
            int i6 = this._inputPtr;
            if (i6 >= this._inputEnd) {
                this._quadLength = i3;
                this._pending32 = i4;
                this._pendingBytes = i5;
                this._minorState = 10;
                JsonToken jsonToken = JsonToken.NOT_AVAILABLE;
                this._currToken = jsonToken;
                return jsonToken;
            }
            byte byteFromBuffer = getByteFromBuffer(i6) & 255;
            if (inputCodeUtf8JsNames[byteFromBuffer] != 0) {
                if (i5 > 0) {
                    if (i3 >= iArr.length) {
                        iArr = ParserBase.growArrayBy(iArr, iArr.length);
                        this._quadBuffer = iArr;
                    }
                    iArr[i3] = i4;
                    i3++;
                }
                String findName = this._symbols.findName(iArr, i3);
                if (findName == null) {
                    findName = _addName(iArr, i3, i5);
                }
                return _fieldComplete(findName);
            }
            this._inputPtr++;
            if (i5 < 4) {
                i5++;
                i4 = (i4 << 8) | byteFromBuffer;
            } else {
                if (i3 >= iArr.length) {
                    iArr = ParserBase.growArrayBy(iArr, iArr.length);
                    this._quadBuffer = iArr;
                }
                iArr[i3] = i4;
                i3++;
                i4 = byteFromBuffer;
                i5 = 1;
            }
        }
    }

    private JsonToken _handleOddName(int i3) throws IOException {
        if (i3 != 35) {
            if (i3 != 39) {
                if (i3 == 47) {
                    return _startSlashComment(4);
                }
                if (i3 == 93) {
                    return _closeArrayScope();
                }
            } else if ((this._features & FEAT_MASK_ALLOW_SINGLE_QUOTES) != 0) {
                return _finishAposName(0, 0, 0);
            }
        } else if ((this._features & FEAT_MASK_ALLOW_YAML_COMMENTS) != 0) {
            return _finishHashComment(4);
        }
        if ((this._features & FEAT_MASK_ALLOW_UNQUOTED_NAMES) == 0) {
            _reportUnexpectedChar((char) i3, "was expecting double-quote to start field name");
        }
        if (CharTypes.getInputCodeUtf8JsNames()[i3] != 0) {
            _reportUnexpectedChar(i3, "was expecting either valid name character (for unquoted name) or double-quote (for quoted) to start field name");
        }
        return _finishUnquotedName(0, i3, 1);
    }

    private final JsonToken _parseEscapedName(int i3, int i4, int i5) throws IOException {
        int i6;
        int[] iArr = this._quadBuffer;
        int[] iArr2 = _icLatin1;
        while (this._inputPtr < this._inputEnd) {
            int nextUnsignedByteFromBuffer = getNextUnsignedByteFromBuffer();
            if (iArr2[nextUnsignedByteFromBuffer] == 0) {
                if (i5 >= 4) {
                    if (i3 >= iArr.length) {
                        int[] growArrayBy = ParserBase.growArrayBy(iArr, iArr.length);
                        this._quadBuffer = growArrayBy;
                        iArr = growArrayBy;
                    }
                    i6 = i3 + 1;
                    iArr[i3] = i4;
                    i3 = i6;
                    i4 = nextUnsignedByteFromBuffer;
                    i5 = 1;
                }
            } else if (nextUnsignedByteFromBuffer == 34) {
                if (i5 > 0) {
                    if (i3 >= iArr.length) {
                        iArr = ParserBase.growArrayBy(iArr, iArr.length);
                        this._quadBuffer = iArr;
                    }
                    iArr[i3] = NonBlockingJsonParserBase._padLastQuad(i4, i5);
                    i3++;
                } else if (i3 == 0) {
                    return _fieldComplete("");
                }
                String findName = this._symbols.findName(iArr, i3);
                if (findName == null) {
                    findName = _addName(iArr, i3, i5);
                }
                return _fieldComplete(findName);
            } else {
                if (nextUnsignedByteFromBuffer != 92) {
                    _throwUnquotedSpace(nextUnsignedByteFromBuffer, "name");
                } else {
                    nextUnsignedByteFromBuffer = _decodeCharEscape();
                    if (nextUnsignedByteFromBuffer < 0) {
                        this._minorState = 8;
                        this._minorStateAfterSplit = 7;
                        this._quadLength = i3;
                        this._pending32 = i4;
                        this._pendingBytes = i5;
                        JsonToken jsonToken = JsonToken.NOT_AVAILABLE;
                        this._currToken = jsonToken;
                        return jsonToken;
                    }
                }
                if (i3 >= iArr.length) {
                    iArr = ParserBase.growArrayBy(iArr, iArr.length);
                    this._quadBuffer = iArr;
                }
                if (nextUnsignedByteFromBuffer > 127) {
                    int i7 = 0;
                    if (i5 >= 4) {
                        iArr[i3] = i4;
                        i3++;
                        i4 = 0;
                        i5 = 0;
                    }
                    if (nextUnsignedByteFromBuffer < 2048) {
                        r10 = (i4 << 8) | (nextUnsignedByteFromBuffer >> 6) | 192;
                        r11 = i5 + 1;
                    } else {
                        int i8 = (i4 << 8) | (nextUnsignedByteFromBuffer >> 12) | 224;
                        int i9 = i5 + 1;
                        if (i9 >= 4) {
                            iArr[i3] = i8;
                            i3++;
                            i9 = 0;
                        } else {
                            i7 = i8;
                        }
                        r10 = (i7 << 8) | ((nextUnsignedByteFromBuffer >> 6) & 63) | 128;
                        r11 = i9 + 1;
                    }
                    nextUnsignedByteFromBuffer = (nextUnsignedByteFromBuffer & 63) | 128;
                }
                if (i5 >= 4) {
                    i6 = i3 + 1;
                    iArr[i3] = i4;
                    i3 = i6;
                    i4 = nextUnsignedByteFromBuffer;
                    i5 = 1;
                }
            }
            i5++;
            i4 = (i4 << 8) | nextUnsignedByteFromBuffer;
        }
        this._quadLength = i3;
        this._pending32 = i4;
        this._pendingBytes = i5;
        this._minorState = 7;
        JsonToken jsonToken2 = JsonToken.NOT_AVAILABLE;
        this._currToken = jsonToken2;
        return jsonToken2;
    }

    private final String _parseMediumName(int i3, int i4) throws IOException {
        int[] iArr = _icLatin1;
        int i5 = i3 + 1;
        byte byteFromBuffer = getByteFromBuffer(i3) & 255;
        if (iArr[byteFromBuffer] == 0) {
            byte b3 = (i4 << 8) | byteFromBuffer;
            int i6 = i3 + 2;
            byte byteFromBuffer2 = getByteFromBuffer(i5) & 255;
            if (iArr[byteFromBuffer2] == 0) {
                byte b4 = (b3 << 8) | byteFromBuffer2;
                int i7 = i3 + 3;
                byte byteFromBuffer3 = getByteFromBuffer(i6) & 255;
                if (iArr[byteFromBuffer3] == 0) {
                    byte b5 = (b4 << 8) | byteFromBuffer3;
                    int i8 = i3 + 4;
                    byte byteFromBuffer4 = getByteFromBuffer(i7) & 255;
                    if (iArr[byteFromBuffer4] == 0) {
                        return _parseMediumName2(i8, byteFromBuffer4, b5);
                    }
                    if (byteFromBuffer4 != 34) {
                        return null;
                    }
                    this._inputPtr = i8;
                    return _findName(this._quad1, b5, 4);
                } else if (byteFromBuffer3 != 34) {
                    return null;
                } else {
                    this._inputPtr = i7;
                    return _findName(this._quad1, b4, 3);
                }
            } else if (byteFromBuffer2 != 34) {
                return null;
            } else {
                this._inputPtr = i6;
                return _findName(this._quad1, b3, 2);
            }
        } else if (byteFromBuffer != 34) {
            return null;
        } else {
            this._inputPtr = i5;
            return _findName(this._quad1, i4, 1);
        }
    }

    private final String _parseMediumName2(int i3, int i4, int i5) throws IOException {
        int[] iArr = _icLatin1;
        int i6 = i3 + 1;
        byte byteFromBuffer = getByteFromBuffer(i3) & 255;
        if (iArr[byteFromBuffer] == 0) {
            byte b3 = (i4 << 8) | byteFromBuffer;
            int i7 = i3 + 2;
            byte byteFromBuffer2 = getByteFromBuffer(i6) & 255;
            if (iArr[byteFromBuffer2] == 0) {
                byte b4 = (b3 << 8) | byteFromBuffer2;
                int i8 = i3 + 3;
                byte byteFromBuffer3 = getByteFromBuffer(i7) & 255;
                if (iArr[byteFromBuffer3] == 0) {
                    byte b5 = (b4 << 8) | byteFromBuffer3;
                    int i9 = i3 + 4;
                    if ((getByteFromBuffer(i8) & 255) != 34) {
                        return null;
                    }
                    this._inputPtr = i9;
                    return _findName(this._quad1, i5, b5, 4);
                } else if (byteFromBuffer3 != 34) {
                    return null;
                } else {
                    this._inputPtr = i8;
                    return _findName(this._quad1, i5, b4, 3);
                }
            } else if (byteFromBuffer2 != 34) {
                return null;
            } else {
                this._inputPtr = i7;
                return _findName(this._quad1, i5, b3, 2);
            }
        } else if (byteFromBuffer != 34) {
            return null;
        } else {
            this._inputPtr = i6;
            return _findName(this._quad1, i5, i4, 1);
        }
    }

    private final int _skipWS(int i3) throws IOException {
        do {
            if (i3 != 32) {
                if (i3 == 10) {
                    this._currInputRow++;
                    this._currInputRowStart = this._inputPtr;
                } else if (i3 == 13) {
                    this._currInputRowAlt++;
                    this._currInputRowStart = this._inputPtr;
                } else if (i3 != 9) {
                    _throwInvalidSpace(i3);
                }
            }
            if (this._inputPtr >= this._inputEnd) {
                this._currToken = JsonToken.NOT_AVAILABLE;
                return 0;
            }
            i3 = getNextUnsignedByteFromBuffer();
        } while (i3 <= 32);
        return i3;
    }

    private final JsonToken _startAfterComment(int i3) throws IOException {
        if (this._inputPtr >= this._inputEnd) {
            this._minorState = i3;
            JsonToken jsonToken = JsonToken.NOT_AVAILABLE;
            this._currToken = jsonToken;
            return jsonToken;
        }
        int nextUnsignedByteFromBuffer = getNextUnsignedByteFromBuffer();
        if (i3 == 4) {
            return _startFieldName(nextUnsignedByteFromBuffer);
        }
        if (i3 == 5) {
            return _startFieldNameAfterComma(nextUnsignedByteFromBuffer);
        }
        switch (i3) {
            case 12:
                return _startValue(nextUnsignedByteFromBuffer);
            case 13:
                return _startValueExpectComma(nextUnsignedByteFromBuffer);
            case 14:
                return _startValueExpectColon(nextUnsignedByteFromBuffer);
            case 15:
                return _startValueAfterComma(nextUnsignedByteFromBuffer);
            default:
                VersionUtil.throwInternal();
                return null;
        }
    }

    private final JsonToken _startDocument(int i3) throws IOException {
        int i4 = i3 & 255;
        if (i4 == 239 && this._minorState != 1) {
            return _finishBOM(1);
        }
        while (i4 <= 32) {
            if (i4 != 32) {
                if (i4 == 10) {
                    this._currInputRow++;
                    this._currInputRowStart = this._inputPtr;
                } else if (i4 == 13) {
                    this._currInputRowAlt++;
                    this._currInputRowStart = this._inputPtr;
                } else if (i4 != 9) {
                    _throwInvalidSpace(i4);
                }
            }
            if (this._inputPtr >= this._inputEnd) {
                this._minorState = 3;
                if (this._closed) {
                    return null;
                }
                return this._endOfInput ? _eofAsNextToken() : JsonToken.NOT_AVAILABLE;
            }
            i4 = getNextUnsignedByteFromBuffer();
        }
        return _startValue(i4);
    }

    private final JsonToken _startFieldName(int i3) throws IOException {
        String _fastParseName;
        if (i3 > 32 || (i3 = _skipWS(i3)) > 0) {
            _updateTokenLocation();
            return i3 != 34 ? i3 == 125 ? _closeObjectScope() : _handleOddName(i3) : (this._inputPtr + 13 > this._inputEnd || (_fastParseName = _fastParseName()) == null) ? _parseEscapedName(0, 0, 0) : _fieldComplete(_fastParseName);
        }
        this._minorState = 4;
        return this._currToken;
    }

    private final JsonToken _startFieldNameAfterComma(int i3) throws IOException {
        String _fastParseName;
        if (i3 > 32 || (i3 = _skipWS(i3)) > 0) {
            if (i3 != 44) {
                if (i3 == 125) {
                    return _closeObjectScope();
                }
                if (i3 == 35) {
                    return _finishHashComment(5);
                }
                if (i3 == 47) {
                    return _startSlashComment(5);
                }
                _reportUnexpectedChar(i3, "was expecting comma to separate " + this._parsingContext.typeDesc() + " entries");
            }
            int i4 = this._inputPtr;
            if (i4 >= this._inputEnd) {
                this._minorState = 4;
                JsonToken jsonToken = JsonToken.NOT_AVAILABLE;
                this._currToken = jsonToken;
                return jsonToken;
            }
            int byteFromBuffer = getByteFromBuffer(i4);
            this._inputPtr = i4 + 1;
            if (byteFromBuffer > 32 || (byteFromBuffer = _skipWS(byteFromBuffer)) > 0) {
                _updateTokenLocation();
                return byteFromBuffer != 34 ? (byteFromBuffer != 125 || (this._features & FEAT_MASK_TRAILING_COMMA) == 0) ? _handleOddName(byteFromBuffer) : _closeObjectScope() : (this._inputPtr + 13 > this._inputEnd || (_fastParseName = _fastParseName()) == null) ? _parseEscapedName(0, 0, 0) : _fieldComplete(_fastParseName);
            }
            this._minorState = 4;
            return this._currToken;
        }
        this._minorState = 5;
        return this._currToken;
    }

    private final JsonToken _startSlashComment(int i3) throws IOException {
        if ((this._features & FEAT_MASK_ALLOW_JAVA_COMMENTS) == 0) {
            _reportUnexpectedChar(47, "maybe a (non-standard) comment? (not recognized as one since Feature 'ALLOW_COMMENTS' not enabled for parser)");
        }
        if (this._inputPtr >= this._inputEnd) {
            this._pending32 = i3;
            this._minorState = 51;
            JsonToken jsonToken = JsonToken.NOT_AVAILABLE;
            this._currToken = jsonToken;
            return jsonToken;
        }
        byte nextSignedByteFromBuffer = getNextSignedByteFromBuffer();
        if (nextSignedByteFromBuffer == 42) {
            return _finishCComment(i3, false);
        }
        if (nextSignedByteFromBuffer == 47) {
            return _finishCppComment(i3);
        }
        _reportUnexpectedChar(nextSignedByteFromBuffer & 255, "was expecting either '*' or '/' for a comment");
        return null;
    }

    private final JsonToken _startValue(int i3) throws IOException {
        if (i3 > 32 || (i3 = _skipWS(i3)) > 0) {
            _updateTokenLocation();
            this._parsingContext.expectComma();
            if (i3 == 34) {
                return _startString();
            }
            if (i3 == 35) {
                return _finishHashComment(12);
            }
            if (i3 == 43) {
                return _startPositiveNumber();
            }
            if (i3 == 91) {
                return _startArrayScope();
            }
            if (i3 == 93) {
                return _closeArrayScope();
            }
            if (i3 == 102) {
                return _startFalseToken();
            }
            if (i3 == 110) {
                return _startNullToken();
            }
            if (i3 == 116) {
                return _startTrueToken();
            }
            if (i3 == 123) {
                return _startObjectScope();
            }
            if (i3 == 125) {
                return _closeObjectScope();
            }
            switch (i3) {
                case 45:
                    return _startNegativeNumber();
                case 46:
                    if (isEnabled(JsonReadFeature.ALLOW_LEADING_DECIMAL_POINT_FOR_NUMBERS.mappedFeature())) {
                        return _startFloatThatStartsWithPeriod();
                    }
                    break;
                case 47:
                    return _startSlashComment(12);
                case 48:
                    return _startNumberLeadingZero();
                case 49:
                case 50:
                case 51:
                case 52:
                case 53:
                case 54:
                case 55:
                case 56:
                case 57:
                    return _startPositiveNumber(i3);
            }
            return _startUnexpectedValue(false, i3);
        }
        this._minorState = 12;
        return this._currToken;
    }

    private final JsonToken _startValueAfterComma(int i3) throws IOException {
        if (i3 > 32 || (i3 = _skipWS(i3)) > 0) {
            _updateTokenLocation();
            if (i3 == 34) {
                return _startString();
            }
            if (i3 == 35) {
                return _finishHashComment(15);
            }
            if (i3 == 43) {
                return _startPositiveNumber();
            }
            if (i3 == 45) {
                return _startNegativeNumber();
            }
            if (i3 == 91) {
                return _startArrayScope();
            }
            if (i3 != 93) {
                if (i3 == 102) {
                    return _startFalseToken();
                }
                if (i3 == 110) {
                    return _startNullToken();
                }
                if (i3 == 116) {
                    return _startTrueToken();
                }
                if (i3 == 123) {
                    return _startObjectScope();
                }
                if (i3 != 125) {
                    switch (i3) {
                        case 47:
                            return _startSlashComment(15);
                        case 48:
                            return _startNumberLeadingZero();
                        case 49:
                        case 50:
                        case 51:
                        case 52:
                        case 53:
                        case 54:
                        case 55:
                        case 56:
                        case 57:
                            return _startPositiveNumber(i3);
                    }
                } else if ((this._features & FEAT_MASK_TRAILING_COMMA) != 0) {
                    return _closeObjectScope();
                }
            } else if ((this._features & FEAT_MASK_TRAILING_COMMA) != 0) {
                return _closeArrayScope();
            }
            return _startUnexpectedValue(true, i3);
        }
        this._minorState = 15;
        return this._currToken;
    }

    private final JsonToken _startValueExpectColon(int i3) throws IOException {
        if (i3 > 32 || (i3 = _skipWS(i3)) > 0) {
            if (i3 != 58) {
                if (i3 == 47) {
                    return _startSlashComment(14);
                }
                if (i3 == 35) {
                    return _finishHashComment(14);
                }
                _reportUnexpectedChar(i3, "was expecting a colon to separate field name and value");
            }
            int i4 = this._inputPtr;
            if (i4 >= this._inputEnd) {
                this._minorState = 12;
                JsonToken jsonToken = JsonToken.NOT_AVAILABLE;
                this._currToken = jsonToken;
                return jsonToken;
            }
            int byteFromBuffer = getByteFromBuffer(i4);
            this._inputPtr = i4 + 1;
            if (byteFromBuffer > 32 || (byteFromBuffer = _skipWS(byteFromBuffer)) > 0) {
                _updateTokenLocation();
                if (byteFromBuffer == 34) {
                    return _startString();
                }
                if (byteFromBuffer == 35) {
                    return _finishHashComment(12);
                }
                if (byteFromBuffer == 43) {
                    return _startPositiveNumber();
                }
                if (byteFromBuffer == 45) {
                    return _startNegativeNumber();
                }
                if (byteFromBuffer == 91) {
                    return _startArrayScope();
                }
                if (byteFromBuffer == 102) {
                    return _startFalseToken();
                }
                if (byteFromBuffer == 110) {
                    return _startNullToken();
                }
                if (byteFromBuffer == 116) {
                    return _startTrueToken();
                }
                if (byteFromBuffer == 123) {
                    return _startObjectScope();
                }
                switch (byteFromBuffer) {
                    case 47:
                        return _startSlashComment(12);
                    case 48:
                        return _startNumberLeadingZero();
                    case 49:
                    case 50:
                    case 51:
                    case 52:
                    case 53:
                    case 54:
                    case 55:
                    case 56:
                    case 57:
                        return _startPositiveNumber(byteFromBuffer);
                    default:
                        return _startUnexpectedValue(false, byteFromBuffer);
                }
            } else {
                this._minorState = 12;
                return this._currToken;
            }
        } else {
            this._minorState = 14;
            return this._currToken;
        }
    }

    private final JsonToken _startValueExpectComma(int i3) throws IOException {
        if (i3 > 32 || (i3 = _skipWS(i3)) > 0) {
            if (i3 != 44) {
                if (i3 == 93) {
                    return _closeArrayScope();
                }
                if (i3 == 125) {
                    return _closeObjectScope();
                }
                if (i3 == 47) {
                    return _startSlashComment(13);
                }
                if (i3 == 35) {
                    return _finishHashComment(13);
                }
                _reportUnexpectedChar(i3, "was expecting comma to separate " + this._parsingContext.typeDesc() + " entries");
            }
            this._parsingContext.expectComma();
            int i4 = this._inputPtr;
            if (i4 >= this._inputEnd) {
                this._minorState = 15;
                JsonToken jsonToken = JsonToken.NOT_AVAILABLE;
                this._currToken = jsonToken;
                return jsonToken;
            }
            int byteFromBuffer = getByteFromBuffer(i4);
            this._inputPtr = i4 + 1;
            if (byteFromBuffer > 32 || (byteFromBuffer = _skipWS(byteFromBuffer)) > 0) {
                _updateTokenLocation();
                if (byteFromBuffer == 34) {
                    return _startString();
                }
                if (byteFromBuffer == 35) {
                    return _finishHashComment(15);
                }
                if (byteFromBuffer == 43) {
                    return _startPositiveNumber();
                }
                if (byteFromBuffer == 45) {
                    return _startNegativeNumber();
                }
                if (byteFromBuffer == 91) {
                    return _startArrayScope();
                }
                if (byteFromBuffer != 93) {
                    if (byteFromBuffer == 102) {
                        return _startFalseToken();
                    }
                    if (byteFromBuffer == 110) {
                        return _startNullToken();
                    }
                    if (byteFromBuffer == 116) {
                        return _startTrueToken();
                    }
                    if (byteFromBuffer == 123) {
                        return _startObjectScope();
                    }
                    if (byteFromBuffer != 125) {
                        switch (byteFromBuffer) {
                            case 47:
                                return _startSlashComment(15);
                            case 48:
                                return _startNumberLeadingZero();
                            case 49:
                            case 50:
                            case 51:
                            case 52:
                            case 53:
                            case 54:
                            case 55:
                            case 56:
                            case 57:
                                return _startPositiveNumber(byteFromBuffer);
                        }
                    } else if ((this._features & FEAT_MASK_TRAILING_COMMA) != 0) {
                        return _closeObjectScope();
                    }
                } else if ((this._features & FEAT_MASK_TRAILING_COMMA) != 0) {
                    return _closeArrayScope();
                }
                return _startUnexpectedValue(true, byteFromBuffer);
            }
            this._minorState = 15;
            return this._currToken;
        }
        this._minorState = 13;
        return this._currToken;
    }

    public char _decodeEscaped() throws IOException {
        VersionUtil.throwInternal();
        return ' ';
    }

    public JsonToken _finishErrorToken() throws IOException {
        while (this._inputPtr < this._inputEnd) {
            char nextSignedByteFromBuffer = (char) getNextSignedByteFromBuffer();
            if (Character.isJavaIdentifierPart(nextSignedByteFromBuffer)) {
                this._textBuffer.append(nextSignedByteFromBuffer);
                if (this._textBuffer.size() >= 256) {
                }
            }
            return _reportErrorToken(this._textBuffer.contentsAsString());
        }
        JsonToken jsonToken = JsonToken.NOT_AVAILABLE;
        this._currToken = jsonToken;
        return jsonToken;
    }

    public JsonToken _finishErrorTokenWithEOF() throws IOException {
        return _reportErrorToken(this._textBuffer.contentsAsString());
    }

    public final JsonToken _finishFieldWithEscape() throws IOException {
        int i3;
        int i4;
        int i5;
        int _decodeSplitEscaped = _decodeSplitEscaped(this._quoted32, this._quotedDigits);
        if (_decodeSplitEscaped < 0) {
            this._minorState = 8;
            return JsonToken.NOT_AVAILABLE;
        }
        int i6 = this._quadLength;
        int[] iArr = this._quadBuffer;
        if (i6 >= iArr.length) {
            this._quadBuffer = ParserBase.growArrayBy(iArr, 32);
        }
        int i7 = this._pending32;
        int i8 = this._pendingBytes;
        int i9 = 1;
        if (_decodeSplitEscaped > 127) {
            int i10 = 0;
            if (i8 >= 4) {
                int[] iArr2 = this._quadBuffer;
                int i11 = this._quadLength;
                this._quadLength = i11 + 1;
                iArr2[i11] = i7;
                i7 = 0;
                i8 = 0;
            }
            if (_decodeSplitEscaped < 2048) {
                i4 = i7 << 8;
                i5 = (_decodeSplitEscaped >> 6) | 192;
            } else {
                int i12 = (i7 << 8) | (_decodeSplitEscaped >> 12) | 224;
                i3++;
                if (i3 >= 4) {
                    int[] iArr3 = this._quadBuffer;
                    int i13 = this._quadLength;
                    this._quadLength = i13 + 1;
                    iArr3[i13] = i12;
                    i3 = 0;
                } else {
                    i10 = i12;
                }
                i4 = i10 << 8;
                i5 = ((_decodeSplitEscaped >> 6) & 63) | 128;
            }
            i7 = i4 | i5;
            i8 = i3 + 1;
            _decodeSplitEscaped = (_decodeSplitEscaped & 63) | 128;
        }
        if (i8 < 4) {
            i9 = 1 + i8;
            _decodeSplitEscaped |= i7 << 8;
        } else {
            int[] iArr4 = this._quadBuffer;
            int i14 = this._quadLength;
            this._quadLength = i14 + 1;
            iArr4[i14] = i7;
        }
        return this._minorStateAfterSplit == 9 ? _finishAposName(this._quadLength, _decodeSplitEscaped, i9) : _parseEscapedName(this._quadLength, _decodeSplitEscaped, i9);
    }

    public JsonToken _finishFloatExponent(boolean z2, int i3) throws IOException {
        if (z2) {
            this._minorState = 32;
            if (i3 == 45 || i3 == 43) {
                this._textBuffer.append((char) i3);
                if (this._inputPtr >= this._inputEnd) {
                    this._minorState = 32;
                    this._expLength = 0;
                    return JsonToken.NOT_AVAILABLE;
                }
                i3 = getNextSignedByteFromBuffer();
            }
        }
        char[] bufferWithoutReset = this._textBuffer.getBufferWithoutReset();
        int currentSegmentSize = this._textBuffer.getCurrentSegmentSize();
        int i4 = this._expLength;
        while (i3 >= 48 && i3 <= 57) {
            i4++;
            if (currentSegmentSize >= bufferWithoutReset.length) {
                bufferWithoutReset = this._textBuffer.expandCurrentSegment();
            }
            int i5 = currentSegmentSize + 1;
            bufferWithoutReset[currentSegmentSize] = (char) i3;
            if (this._inputPtr >= this._inputEnd) {
                this._textBuffer.setCurrentLength(i5);
                this._expLength = i4;
                return JsonToken.NOT_AVAILABLE;
            }
            i3 = getNextSignedByteFromBuffer();
            currentSegmentSize = i5;
        }
        int i6 = i3 & 255;
        if (i4 == 0) {
            _reportUnexpectedNumberChar(i6, "Exponent indicator not followed by a digit");
        }
        this._inputPtr--;
        this._textBuffer.setCurrentLength(currentSegmentSize);
        this._expLength = i4;
        return _valueComplete(JsonToken.VALUE_NUMBER_FLOAT);
    }

    public JsonToken _finishFloatFraction() throws IOException {
        int i3 = this._fractLength;
        char[] bufferWithoutReset = this._textBuffer.getBufferWithoutReset();
        int currentSegmentSize = this._textBuffer.getCurrentSegmentSize();
        byte nextSignedByteFromBuffer = getNextSignedByteFromBuffer();
        boolean z2 = true;
        while (z2) {
            if (nextSignedByteFromBuffer >= 48 && nextSignedByteFromBuffer <= 57) {
                i3++;
                if (currentSegmentSize >= bufferWithoutReset.length) {
                    bufferWithoutReset = this._textBuffer.expandCurrentSegment();
                }
                int i4 = currentSegmentSize + 1;
                bufferWithoutReset[currentSegmentSize] = (char) nextSignedByteFromBuffer;
                if (this._inputPtr >= this._inputEnd) {
                    this._textBuffer.setCurrentLength(i4);
                    this._fractLength = i3;
                    return JsonToken.NOT_AVAILABLE;
                }
                nextSignedByteFromBuffer = getNextSignedByteFromBuffer();
                currentSegmentSize = i4;
            } else if (nextSignedByteFromBuffer == 102 || nextSignedByteFromBuffer == 100 || nextSignedByteFromBuffer == 70 || nextSignedByteFromBuffer == 68) {
                _reportUnexpectedNumberChar(nextSignedByteFromBuffer, "JSON does not support parsing numbers that have 'f' or 'd' suffixes");
            } else if (nextSignedByteFromBuffer == 46) {
                _reportUnexpectedNumberChar(nextSignedByteFromBuffer, "Cannot parse number with more than one decimal point");
            } else {
                z2 = false;
            }
        }
        if (i3 == 0 && !isEnabled(JsonReadFeature.ALLOW_TRAILING_DECIMAL_POINT_FOR_NUMBERS.mappedFeature())) {
            _reportUnexpectedNumberChar(nextSignedByteFromBuffer, "Decimal point not followed by a digit");
        }
        this._fractLength = i3;
        this._textBuffer.setCurrentLength(currentSegmentSize);
        if (nextSignedByteFromBuffer == 101 || nextSignedByteFromBuffer == 69) {
            this._textBuffer.append((char) nextSignedByteFromBuffer);
            this._expLength = 0;
            if (this._inputPtr >= this._inputEnd) {
                this._minorState = 31;
                return JsonToken.NOT_AVAILABLE;
            }
            this._minorState = 32;
            return _finishFloatExponent(true, getNextUnsignedByteFromBuffer());
        }
        this._inputPtr--;
        this._textBuffer.setCurrentLength(currentSegmentSize);
        this._expLength = 0;
        return _valueComplete(JsonToken.VALUE_NUMBER_FLOAT);
    }

    public JsonToken _finishKeywordToken(String str, int i3, JsonToken jsonToken) throws IOException {
        int length = str.length();
        while (true) {
            int i4 = this._inputPtr;
            if (i4 >= this._inputEnd) {
                this._pending32 = i3;
                JsonToken jsonToken2 = JsonToken.NOT_AVAILABLE;
                this._currToken = jsonToken2;
                return jsonToken2;
            }
            byte byteFromBuffer = getByteFromBuffer(i4);
            if (i3 == length) {
                if (byteFromBuffer < 48 || byteFromBuffer == 93 || byteFromBuffer == 125) {
                    return _valueComplete(jsonToken);
                }
            } else if (byteFromBuffer != str.charAt(i3)) {
                break;
            } else {
                i3++;
                this._inputPtr++;
            }
        }
        this._minorState = 50;
        this._textBuffer.resetWithCopy(str, 0, i3);
        return _finishErrorToken();
    }

    public JsonToken _finishKeywordTokenWithEOF(String str, int i3, JsonToken jsonToken) throws IOException {
        if (i3 == str.length()) {
            this._currToken = jsonToken;
            return jsonToken;
        }
        this._textBuffer.resetWithCopy(str, 0, i3);
        return _finishErrorTokenWithEOF();
    }

    public JsonToken _finishNonStdToken(int i3, int i4) throws IOException {
        String _nonStdToken = _nonStdToken(i3);
        int length = _nonStdToken.length();
        while (true) {
            int i5 = this._inputPtr;
            if (i5 >= this._inputEnd) {
                this._nonStdTokenType = i3;
                this._pending32 = i4;
                this._minorState = 19;
                JsonToken jsonToken = JsonToken.NOT_AVAILABLE;
                this._currToken = jsonToken;
                return jsonToken;
            }
            byte byteFromBuffer = getByteFromBuffer(i5);
            if (i4 == length) {
                if (byteFromBuffer < 48 || byteFromBuffer == 93 || byteFromBuffer == 125) {
                    return _valueNonStdNumberComplete(i3);
                }
            } else if (byteFromBuffer != _nonStdToken.charAt(i4)) {
                break;
            } else {
                i4++;
                this._inputPtr++;
            }
        }
        this._minorState = 50;
        this._textBuffer.resetWithCopy(_nonStdToken, 0, i4);
        return _finishErrorToken();
    }

    public JsonToken _finishNonStdTokenWithEOF(int i3, int i4) throws IOException {
        String _nonStdToken = _nonStdToken(i3);
        if (i4 == _nonStdToken.length()) {
            return _valueNonStdNumberComplete(i3);
        }
        this._textBuffer.resetWithCopy(_nonStdToken, 0, i4);
        return _finishErrorTokenWithEOF();
    }

    public JsonToken _finishNumberIntegralPart(char[] cArr, int i3) throws IOException {
        int i4 = this._numberNegative ? -1 : 0;
        while (true) {
            int i5 = this._inputPtr;
            if (i5 >= this._inputEnd) {
                this._minorState = 26;
                this._textBuffer.setCurrentLength(i3);
                JsonToken jsonToken = JsonToken.NOT_AVAILABLE;
                this._currToken = jsonToken;
                return jsonToken;
            }
            byte byteFromBuffer = getByteFromBuffer(i5) & 255;
            if (byteFromBuffer < 48) {
                if (byteFromBuffer == 46) {
                    this._intLength = i4 + i3;
                    this._inputPtr++;
                    return _startFloat(cArr, i3, byteFromBuffer);
                }
            } else if (byteFromBuffer <= 57) {
                this._inputPtr++;
                if (i3 >= cArr.length) {
                    cArr = this._textBuffer.expandCurrentSegment();
                }
                cArr[i3] = (char) byteFromBuffer;
                i3++;
            } else if (byteFromBuffer == 101 || byteFromBuffer == 69) {
                this._intLength = i4 + i3;
                this._inputPtr++;
                return _startFloat(cArr, i3, byteFromBuffer);
            }
        }
        this._intLength = i4 + i3;
        this._textBuffer.setCurrentLength(i3);
        return _valueComplete(JsonToken.VALUE_NUMBER_INT);
    }

    public JsonToken _finishNumberLeadingNegZeroes() throws IOException {
        return _finishNumberLeadingPosNegZeroes(true);
    }

    public JsonToken _finishNumberLeadingPosNegZeroes(boolean z2) throws IOException {
        while (this._inputPtr < this._inputEnd) {
            int nextUnsignedByteFromBuffer = getNextUnsignedByteFromBuffer();
            char c3 = SignatureVisitor.EXTENDS;
            if (nextUnsignedByteFromBuffer < 48) {
                if (nextUnsignedByteFromBuffer == 46) {
                    char[] emptyAndGetCurrentSegment = this._textBuffer.emptyAndGetCurrentSegment();
                    if (z2) {
                        c3 = '-';
                    }
                    emptyAndGetCurrentSegment[0] = c3;
                    emptyAndGetCurrentSegment[1] = '0';
                    this._intLength = 1;
                    return _startFloat(emptyAndGetCurrentSegment, 2, nextUnsignedByteFromBuffer);
                }
            } else if (nextUnsignedByteFromBuffer <= 57) {
                if ((this._features & FEAT_MASK_LEADING_ZEROS) == 0) {
                    reportInvalidNumber("Leading zeroes not allowed");
                    continue;
                }
                if (nextUnsignedByteFromBuffer != 48) {
                    char[] emptyAndGetCurrentSegment2 = this._textBuffer.emptyAndGetCurrentSegment();
                    if (z2) {
                        c3 = '-';
                    }
                    emptyAndGetCurrentSegment2[0] = c3;
                    emptyAndGetCurrentSegment2[1] = (char) nextUnsignedByteFromBuffer;
                    this._intLength = 1;
                    return _finishNumberIntegralPart(emptyAndGetCurrentSegment2, 2);
                }
            } else if (nextUnsignedByteFromBuffer == 101 || nextUnsignedByteFromBuffer == 69) {
                char[] emptyAndGetCurrentSegment3 = this._textBuffer.emptyAndGetCurrentSegment();
                if (z2) {
                    c3 = '-';
                }
                emptyAndGetCurrentSegment3[0] = c3;
                emptyAndGetCurrentSegment3[1] = '0';
                this._intLength = 1;
                return _startFloat(emptyAndGetCurrentSegment3, 2, nextUnsignedByteFromBuffer);
            } else if (!(nextUnsignedByteFromBuffer == 93 || nextUnsignedByteFromBuffer == 125)) {
                _reportUnexpectedNumberChar(nextUnsignedByteFromBuffer, "expected digit (0-9), decimal point (.) or exponent indicator (e/E) to follow '0'");
            }
            this._inputPtr--;
            return _valueCompleteInt(0, SchemaSymbols.ATTVAL_FALSE_0);
        }
        this._minorState = z2 ? 25 : 24;
        JsonToken jsonToken = JsonToken.NOT_AVAILABLE;
        this._currToken = jsonToken;
        return jsonToken;
    }

    public JsonToken _finishNumberLeadingPosZeroes() throws IOException {
        return _finishNumberLeadingPosNegZeroes(false);
    }

    public JsonToken _finishNumberLeadingZeroes() throws IOException {
        while (this._inputPtr < this._inputEnd) {
            int nextUnsignedByteFromBuffer = getNextUnsignedByteFromBuffer();
            if (nextUnsignedByteFromBuffer < 48) {
                if (nextUnsignedByteFromBuffer == 46) {
                    char[] emptyAndGetCurrentSegment = this._textBuffer.emptyAndGetCurrentSegment();
                    emptyAndGetCurrentSegment[0] = '0';
                    this._intLength = 1;
                    return _startFloat(emptyAndGetCurrentSegment, 1, nextUnsignedByteFromBuffer);
                }
            } else if (nextUnsignedByteFromBuffer <= 57) {
                if ((this._features & FEAT_MASK_LEADING_ZEROS) == 0) {
                    reportInvalidNumber("Leading zeroes not allowed");
                    continue;
                }
                if (nextUnsignedByteFromBuffer != 48) {
                    char[] emptyAndGetCurrentSegment2 = this._textBuffer.emptyAndGetCurrentSegment();
                    emptyAndGetCurrentSegment2[0] = (char) nextUnsignedByteFromBuffer;
                    this._intLength = 1;
                    return _finishNumberIntegralPart(emptyAndGetCurrentSegment2, 1);
                }
            } else if (nextUnsignedByteFromBuffer == 101 || nextUnsignedByteFromBuffer == 69) {
                char[] emptyAndGetCurrentSegment3 = this._textBuffer.emptyAndGetCurrentSegment();
                emptyAndGetCurrentSegment3[0] = '0';
                this._intLength = 1;
                return _startFloat(emptyAndGetCurrentSegment3, 1, nextUnsignedByteFromBuffer);
            } else if (!(nextUnsignedByteFromBuffer == 93 || nextUnsignedByteFromBuffer == 125)) {
                _reportUnexpectedNumberChar(nextUnsignedByteFromBuffer, "expected digit (0-9), decimal point (.) or exponent indicator (e/E) to follow '0'");
            }
            this._inputPtr--;
            return _valueCompleteInt(0, SchemaSymbols.ATTVAL_FALSE_0);
        }
        this._minorState = 24;
        JsonToken jsonToken = JsonToken.NOT_AVAILABLE;
        this._currToken = jsonToken;
        return jsonToken;
    }

    public JsonToken _finishNumberMinus(int i3) throws IOException {
        return _finishNumberPlusMinus(i3, true);
    }

    public JsonToken _finishNumberPlus(int i3) throws IOException {
        return _finishNumberPlusMinus(i3, false);
    }

    public JsonToken _finishNumberPlusMinus(int i3, boolean z2) throws IOException {
        String str = "expected digit (0-9) for valid numeric value";
        char c3 = SignatureVisitor.EXTENDS;
        if (i3 <= 48) {
            if (i3 == 48) {
                if (z2) {
                    return _finishNumberLeadingNegZeroes();
                }
                if (!isEnabled(JsonReadFeature.ALLOW_LEADING_PLUS_SIGN_FOR_NUMBERS.mappedFeature())) {
                    _reportUnexpectedNumberChar(43, "JSON spec does not allow numbers to have plus signs: enable `JsonReadFeature.ALLOW_LEADING_PLUS_SIGN_FOR_NUMBERS` to allow");
                }
                return _finishNumberLeadingPosZeroes();
            } else if (i3 != 46 || !isEnabled(JsonReadFeature.ALLOW_LEADING_DECIMAL_POINT_FOR_NUMBERS.mappedFeature())) {
                if (z2) {
                    str = "expected digit (0-9) to follow minus sign, for valid numeric value";
                }
                _reportUnexpectedNumberChar(i3, str);
            } else if (z2) {
                this._inputPtr--;
                return _finishNumberLeadingNegZeroes();
            } else {
                if (!isEnabled(JsonReadFeature.ALLOW_LEADING_PLUS_SIGN_FOR_NUMBERS.mappedFeature())) {
                    _reportUnexpectedNumberChar(43, "JSON spec does not allow numbers to have plus signs: enable `JsonReadFeature.ALLOW_LEADING_PLUS_SIGN_FOR_NUMBERS` to allow");
                }
                this._inputPtr--;
                return _finishNumberLeadingPosZeroes();
            }
        } else if (i3 > 57) {
            if (i3 == 73) {
                return _finishNonStdToken(z2 ? 3 : 2, 2);
            }
            if (z2) {
                str = "expected digit (0-9) to follow minus sign, for valid numeric value";
            }
            _reportUnexpectedNumberChar(i3, str);
        }
        if (!z2 && !isEnabled(JsonReadFeature.ALLOW_LEADING_PLUS_SIGN_FOR_NUMBERS.mappedFeature())) {
            _reportUnexpectedNumberChar(43, "JSON spec does not allow numbers to have plus signs: enable `JsonReadFeature.ALLOW_LEADING_PLUS_SIGN_FOR_NUMBERS` to allow");
        }
        char[] emptyAndGetCurrentSegment = this._textBuffer.emptyAndGetCurrentSegment();
        if (z2) {
            c3 = SignatureVisitor.SUPER;
        }
        emptyAndGetCurrentSegment[0] = c3;
        emptyAndGetCurrentSegment[1] = (char) i3;
        this._intLength = 1;
        return _finishNumberIntegralPart(emptyAndGetCurrentSegment, 2);
    }

    public final JsonToken _finishToken() throws IOException {
        int i3 = this._minorState;
        if (i3 == 1) {
            return _finishBOM(this._pending32);
        }
        if (i3 == 4) {
            return _startFieldName(getNextUnsignedByteFromBuffer());
        }
        if (i3 == 5) {
            return _startFieldNameAfterComma(getNextUnsignedByteFromBuffer());
        }
        switch (i3) {
            case 7:
                return _parseEscapedName(this._quadLength, this._pending32, this._pendingBytes);
            case 8:
                return _finishFieldWithEscape();
            case 9:
                return _finishAposName(this._quadLength, this._pending32, this._pendingBytes);
            case 10:
                return _finishUnquotedName(this._quadLength, this._pending32, this._pendingBytes);
            default:
                switch (i3) {
                    case 12:
                        return _startValue(getNextUnsignedByteFromBuffer());
                    case 13:
                        return _startValueExpectComma(getNextUnsignedByteFromBuffer());
                    case 14:
                        return _startValueExpectColon(getNextUnsignedByteFromBuffer());
                    case 15:
                        return _startValueAfterComma(getNextUnsignedByteFromBuffer());
                    case 16:
                        return _finishKeywordToken(AbstractJsonLexerKt.NULL, this._pending32, JsonToken.VALUE_NULL);
                    case 17:
                        return _finishKeywordToken("true", this._pending32, JsonToken.VALUE_TRUE);
                    case 18:
                        return _finishKeywordToken("false", this._pending32, JsonToken.VALUE_FALSE);
                    case 19:
                        return _finishNonStdToken(this._nonStdTokenType, this._pending32);
                    default:
                        switch (i3) {
                            case 22:
                                return _finishNumberPlus(getNextUnsignedByteFromBuffer());
                            case 23:
                                return _finishNumberMinus(getNextUnsignedByteFromBuffer());
                            case 24:
                                return _finishNumberLeadingZeroes();
                            case 25:
                                return _finishNumberLeadingNegZeroes();
                            case 26:
                                return _finishNumberIntegralPart(this._textBuffer.getBufferWithoutReset(), this._textBuffer.getCurrentSegmentSize());
                            default:
                                switch (i3) {
                                    case 30:
                                        return _finishFloatFraction();
                                    case 31:
                                        return _finishFloatExponent(true, getNextUnsignedByteFromBuffer());
                                    case 32:
                                        return _finishFloatExponent(false, getNextUnsignedByteFromBuffer());
                                    default:
                                        switch (i3) {
                                            case 40:
                                                return _finishRegularString();
                                            case 41:
                                                int _decodeSplitEscaped = _decodeSplitEscaped(this._quoted32, this._quotedDigits);
                                                if (_decodeSplitEscaped < 0) {
                                                    return JsonToken.NOT_AVAILABLE;
                                                }
                                                this._textBuffer.append((char) _decodeSplitEscaped);
                                                return this._minorStateAfterSplit == 45 ? _finishAposString() : _finishRegularString();
                                            case 42:
                                                this._textBuffer.append((char) _decodeUTF8_2(this._pending32, getNextSignedByteFromBuffer()));
                                                return this._minorStateAfterSplit == 45 ? _finishAposString() : _finishRegularString();
                                            case 43:
                                                return !_decodeSplitUTF8_3(this._pending32, this._pendingBytes, getNextSignedByteFromBuffer()) ? JsonToken.NOT_AVAILABLE : this._minorStateAfterSplit == 45 ? _finishAposString() : _finishRegularString();
                                            case 44:
                                                return !_decodeSplitUTF8_4(this._pending32, this._pendingBytes, getNextSignedByteFromBuffer()) ? JsonToken.NOT_AVAILABLE : this._minorStateAfterSplit == 45 ? _finishAposString() : _finishRegularString();
                                            case 45:
                                                return _finishAposString();
                                            default:
                                                switch (i3) {
                                                    case 50:
                                                        return _finishErrorToken();
                                                    case 51:
                                                        return _startSlashComment(this._pending32);
                                                    case 52:
                                                        return _finishCComment(this._pending32, true);
                                                    case 53:
                                                        return _finishCComment(this._pending32, false);
                                                    case 54:
                                                        return _finishCppComment(this._pending32);
                                                    case 55:
                                                        return _finishHashComment(this._pending32);
                                                    default:
                                                        VersionUtil.throwInternal();
                                                        return null;
                                                }
                                        }
                                }
                        }
                }
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0038, code lost:
        _reportInvalidEOF(": was expecting closing '*/' for comment", com.fasterxml.jackson.core.JsonToken.NOT_AVAILABLE);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0043, code lost:
        return _eofAsNextToken();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x004c, code lost:
        return _valueComplete(com.fasterxml.jackson.core.JsonToken.VALUE_NUMBER_FLOAT);
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final com.fasterxml.jackson.core.JsonToken _finishTokenWithEOF() throws java.io.IOException {
        /*
            r4 = this;
            com.fasterxml.jackson.core.JsonToken r0 = r4._currToken
            int r1 = r4._minorState
            r2 = 3
            if (r1 == r2) goto L_0x009e
            r2 = 12
            if (r1 == r2) goto L_0x0099
            r2 = 50
            if (r1 == r2) goto L_0x0094
            switch(r1) {
                case 16: goto L_0x0089;
                case 17: goto L_0x007d;
                case 18: goto L_0x0072;
                case 19: goto L_0x0069;
                default: goto L_0x0012;
            }
        L_0x0012:
            r2 = 0
            switch(r1) {
                case 24: goto L_0x0062;
                case 25: goto L_0x0062;
                case 26: goto L_0x004d;
                default: goto L_0x0016;
            }
        L_0x0016:
            switch(r1) {
                case 30: goto L_0x0044;
                case 31: goto L_0x0031;
                case 32: goto L_0x0046;
                default: goto L_0x0019;
            }
        L_0x0019:
            switch(r1) {
                case 52: goto L_0x0038;
                case 53: goto L_0x0038;
                case 54: goto L_0x003f;
                case 55: goto L_0x003f;
                default: goto L_0x001c;
            }
        L_0x001c:
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            java.lang.String r2 = ": was expecting rest of token (internal state: "
            r1.<init>(r2)
            int r2 = r4._minorState
            java.lang.String r3 = ")"
            java.lang.String r1 = A.a.m(r1, r3, r2)
            com.fasterxml.jackson.core.JsonToken r2 = r4._currToken
            r4._reportInvalidEOF(r1, r2)
            return r0
        L_0x0031:
            java.lang.String r0 = ": was expecting fraction after exponent marker"
            com.fasterxml.jackson.core.JsonToken r1 = com.fasterxml.jackson.core.JsonToken.VALUE_NUMBER_FLOAT
            r4._reportInvalidEOF(r0, r1)
        L_0x0038:
            java.lang.String r0 = ": was expecting closing '*/' for comment"
            com.fasterxml.jackson.core.JsonToken r1 = com.fasterxml.jackson.core.JsonToken.NOT_AVAILABLE
            r4._reportInvalidEOF(r0, r1)
        L_0x003f:
            com.fasterxml.jackson.core.JsonToken r4 = r4._eofAsNextToken()
            return r4
        L_0x0044:
            r4._expLength = r2
        L_0x0046:
            com.fasterxml.jackson.core.JsonToken r0 = com.fasterxml.jackson.core.JsonToken.VALUE_NUMBER_FLOAT
            com.fasterxml.jackson.core.JsonToken r4 = r4._valueComplete(r0)
            return r4
        L_0x004d:
            com.fasterxml.jackson.core.util.TextBuffer r0 = r4._textBuffer
            int r0 = r0.getCurrentSegmentSize()
            boolean r1 = r4._numberNegative
            if (r1 == 0) goto L_0x0059
            int r0 = r0 + -1
        L_0x0059:
            r4._intLength = r0
            com.fasterxml.jackson.core.JsonToken r0 = com.fasterxml.jackson.core.JsonToken.VALUE_NUMBER_INT
            com.fasterxml.jackson.core.JsonToken r4 = r4._valueComplete(r0)
            return r4
        L_0x0062:
            java.lang.String r0 = "0"
            com.fasterxml.jackson.core.JsonToken r4 = r4._valueCompleteInt(r2, r0)
            return r4
        L_0x0069:
            int r0 = r4._nonStdTokenType
            int r1 = r4._pending32
            com.fasterxml.jackson.core.JsonToken r4 = r4._finishNonStdTokenWithEOF(r0, r1)
            return r4
        L_0x0072:
            int r0 = r4._pending32
            com.fasterxml.jackson.core.JsonToken r1 = com.fasterxml.jackson.core.JsonToken.VALUE_FALSE
            java.lang.String r2 = "false"
            com.fasterxml.jackson.core.JsonToken r4 = r4._finishKeywordTokenWithEOF(r2, r0, r1)
            return r4
        L_0x007d:
            int r0 = r4._pending32
            com.fasterxml.jackson.core.JsonToken r1 = com.fasterxml.jackson.core.JsonToken.VALUE_TRUE
            java.lang.String r2 = "true"
            com.fasterxml.jackson.core.JsonToken r4 = r4._finishKeywordTokenWithEOF(r2, r0, r1)
            return r4
        L_0x0089:
            int r0 = r4._pending32
            com.fasterxml.jackson.core.JsonToken r1 = com.fasterxml.jackson.core.JsonToken.VALUE_NULL
            java.lang.String r2 = "null"
            com.fasterxml.jackson.core.JsonToken r4 = r4._finishKeywordTokenWithEOF(r2, r0, r1)
            return r4
        L_0x0094:
            com.fasterxml.jackson.core.JsonToken r4 = r4._finishErrorTokenWithEOF()
            return r4
        L_0x0099:
            com.fasterxml.jackson.core.JsonToken r4 = r4._eofAsNextToken()
            return r4
        L_0x009e:
            com.fasterxml.jackson.core.JsonToken r4 = r4._eofAsNextToken()
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.fasterxml.jackson.core.json.async.NonBlockingUtf8JsonParserBase._finishTokenWithEOF():com.fasterxml.jackson.core.JsonToken");
    }

    public JsonToken _reportErrorToken(String str) throws IOException {
        _reportError("Unrecognized token '%s': was expecting %s", this._textBuffer.contentsAsString(), _validJsonTokenList());
        return JsonToken.NOT_AVAILABLE;
    }

    public JsonToken _startAposString() throws IOException {
        int i3 = this._inputPtr;
        char[] emptyAndGetCurrentSegment = this._textBuffer.emptyAndGetCurrentSegment();
        int[] iArr = _icUTF8;
        int min = Math.min(this._inputEnd, emptyAndGetCurrentSegment.length + i3);
        int i4 = 0;
        while (i3 < min) {
            byte byteFromBuffer = getByteFromBuffer(i3) & 255;
            if (byteFromBuffer == 39) {
                this._inputPtr = i3 + 1;
                this._textBuffer.setCurrentLength(i4);
                return _valueComplete(JsonToken.VALUE_STRING);
            } else if (iArr[byteFromBuffer] != 0) {
                break;
            } else {
                i3++;
                emptyAndGetCurrentSegment[i4] = (char) byteFromBuffer;
                i4++;
            }
        }
        this._textBuffer.setCurrentLength(i4);
        this._inputPtr = i3;
        return _finishAposString();
    }

    public JsonToken _startFalseToken() throws IOException {
        byte byteFromBuffer;
        int i3 = this._inputPtr;
        if (i3 + 4 < this._inputEnd) {
            int i4 = i3 + 1;
            if (getByteFromBuffer(i3) == 97) {
                int i5 = i3 + 2;
                if (getByteFromBuffer(i4) == 108) {
                    int i6 = i3 + 3;
                    if (getByteFromBuffer(i5) == 115) {
                        int i7 = i3 + 4;
                        if (getByteFromBuffer(i6) == 101 && ((byteFromBuffer = getByteFromBuffer(i7) & 255) < 48 || byteFromBuffer == 93 || byteFromBuffer == 125)) {
                            this._inputPtr = i7;
                            return _valueComplete(JsonToken.VALUE_FALSE);
                        }
                    }
                }
            }
        }
        this._minorState = 18;
        return _finishKeywordToken("false", 1, JsonToken.VALUE_FALSE);
    }

    /* JADX WARNING: Removed duplicated region for block: B:59:0x00f7  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.fasterxml.jackson.core.JsonToken _startFloat(char[] r7, int r8, int r9) throws java.io.IOException {
        /*
            r6 = this;
            r0 = 57
            r1 = 48
            r2 = 0
            r3 = 46
            if (r9 != r3) goto L_0x005f
            int r9 = r7.length
            if (r8 < r9) goto L_0x0012
            com.fasterxml.jackson.core.util.TextBuffer r7 = r6._textBuffer
            char[] r7 = r7.expandCurrentSegment()
        L_0x0012:
            int r9 = r8 + 1
            r7[r8] = r3
            r8 = r9
            r9 = r2
        L_0x0018:
            int r3 = r6._inputPtr
            int r4 = r6._inputEnd
            if (r3 < r4) goto L_0x002e
            com.fasterxml.jackson.core.util.TextBuffer r7 = r6._textBuffer
            r7.setCurrentLength(r8)
            r7 = 30
            r6._minorState = r7
            r6._fractLength = r9
            com.fasterxml.jackson.core.JsonToken r7 = com.fasterxml.jackson.core.JsonToken.NOT_AVAILABLE
            r6._currToken = r7
            return r7
        L_0x002e:
            byte r3 = r6.getNextSignedByteFromBuffer()
            if (r3 < r1) goto L_0x0049
            if (r3 <= r0) goto L_0x0037
            goto L_0x0049
        L_0x0037:
            int r4 = r7.length
            if (r8 < r4) goto L_0x0040
            com.fasterxml.jackson.core.util.TextBuffer r7 = r6._textBuffer
            char[] r7 = r7.expandCurrentSegment()
        L_0x0040:
            int r4 = r8 + 1
            char r3 = (char) r3
            r7[r8] = r3
            int r9 = r9 + 1
            r8 = r4
            goto L_0x0018
        L_0x0049:
            r3 = r3 & 255(0xff, float:3.57E-43)
            if (r9 != 0) goto L_0x0061
            com.fasterxml.jackson.core.json.JsonReadFeature r4 = com.fasterxml.jackson.core.json.JsonReadFeature.ALLOW_TRAILING_DECIMAL_POINT_FOR_NUMBERS
            com.fasterxml.jackson.core.JsonParser$Feature r4 = r4.mappedFeature()
            boolean r4 = r6.isEnabled((com.fasterxml.jackson.core.JsonParser.Feature) r4)
            if (r4 != 0) goto L_0x0061
            java.lang.String r4 = "Decimal point not followed by a digit"
            r6._reportUnexpectedNumberChar(r3, r4)
            goto L_0x0061
        L_0x005f:
            r3 = r9
            r9 = r2
        L_0x0061:
            r6._fractLength = r9
            r9 = 101(0x65, float:1.42E-43)
            if (r3 == r9) goto L_0x006b
            r9 = 69
            if (r3 != r9) goto L_0x00fc
        L_0x006b:
            int r9 = r7.length
            if (r8 < r9) goto L_0x0074
            com.fasterxml.jackson.core.util.TextBuffer r7 = r6._textBuffer
            char[] r7 = r7.expandCurrentSegment()
        L_0x0074:
            int r9 = r8 + 1
            char r3 = (char) r3
            r7[r8] = r3
            int r3 = r6._inputPtr
            int r4 = r6._inputEnd
            if (r3 < r4) goto L_0x008f
            com.fasterxml.jackson.core.util.TextBuffer r7 = r6._textBuffer
            r7.setCurrentLength(r9)
            r7 = 31
            r6._minorState = r7
            r6._expLength = r2
            com.fasterxml.jackson.core.JsonToken r7 = com.fasterxml.jackson.core.JsonToken.NOT_AVAILABLE
            r6._currToken = r7
            return r7
        L_0x008f:
            byte r3 = r6.getNextSignedByteFromBuffer()
            r4 = 45
            r5 = 32
            if (r3 == r4) goto L_0x00a0
            r4 = 43
            if (r3 != r4) goto L_0x009e
            goto L_0x00a0
        L_0x009e:
            r8 = r9
            goto L_0x00c6
        L_0x00a0:
            int r4 = r7.length
            if (r9 < r4) goto L_0x00a9
            com.fasterxml.jackson.core.util.TextBuffer r7 = r6._textBuffer
            char[] r7 = r7.expandCurrentSegment()
        L_0x00a9:
            int r8 = r8 + 2
            char r3 = (char) r3
            r7[r9] = r3
            int r9 = r6._inputPtr
            int r3 = r6._inputEnd
            if (r9 < r3) goto L_0x00c2
            com.fasterxml.jackson.core.util.TextBuffer r7 = r6._textBuffer
            r7.setCurrentLength(r8)
            r6._minorState = r5
            r6._expLength = r2
            com.fasterxml.jackson.core.JsonToken r7 = com.fasterxml.jackson.core.JsonToken.NOT_AVAILABLE
            r6._currToken = r7
            return r7
        L_0x00c2:
            byte r3 = r6.getNextSignedByteFromBuffer()
        L_0x00c6:
            if (r3 < r1) goto L_0x00f3
            if (r3 > r0) goto L_0x00f3
            int r2 = r2 + 1
            int r9 = r7.length
            if (r8 < r9) goto L_0x00d5
            com.fasterxml.jackson.core.util.TextBuffer r7 = r6._textBuffer
            char[] r7 = r7.expandCurrentSegment()
        L_0x00d5:
            int r9 = r8 + 1
            char r3 = (char) r3
            r7[r8] = r3
            int r8 = r6._inputPtr
            int r3 = r6._inputEnd
            if (r8 < r3) goto L_0x00ee
            com.fasterxml.jackson.core.util.TextBuffer r7 = r6._textBuffer
            r7.setCurrentLength(r9)
            r6._minorState = r5
            r6._expLength = r2
            com.fasterxml.jackson.core.JsonToken r7 = com.fasterxml.jackson.core.JsonToken.NOT_AVAILABLE
            r6._currToken = r7
            return r7
        L_0x00ee:
            byte r3 = r6.getNextSignedByteFromBuffer()
            goto L_0x009e
        L_0x00f3:
            r7 = r3 & 255(0xff, float:3.57E-43)
            if (r2 != 0) goto L_0x00fc
            java.lang.String r9 = "Exponent indicator not followed by a digit"
            r6._reportUnexpectedNumberChar(r7, r9)
        L_0x00fc:
            int r7 = r6._inputPtr
            int r7 = r7 + -1
            r6._inputPtr = r7
            com.fasterxml.jackson.core.util.TextBuffer r7 = r6._textBuffer
            r7.setCurrentLength(r8)
            r6._expLength = r2
            com.fasterxml.jackson.core.JsonToken r7 = com.fasterxml.jackson.core.JsonToken.VALUE_NUMBER_FLOAT
            com.fasterxml.jackson.core.JsonToken r6 = r6._valueComplete(r7)
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.fasterxml.jackson.core.json.async.NonBlockingUtf8JsonParserBase._startFloat(char[], int, int):com.fasterxml.jackson.core.JsonToken");
    }

    public JsonToken _startFloatThatStartsWithPeriod() throws IOException {
        this._numberNegative = false;
        this._intLength = 0;
        return _startFloat(this._textBuffer.emptyAndGetCurrentSegment(), 0, 46);
    }

    public JsonToken _startNegativeNumber() throws IOException {
        this._numberNegative = true;
        if (this._inputPtr >= this._inputEnd) {
            this._minorState = 23;
            JsonToken jsonToken = JsonToken.NOT_AVAILABLE;
            this._currToken = jsonToken;
            return jsonToken;
        }
        int nextUnsignedByteFromBuffer = getNextUnsignedByteFromBuffer();
        int i3 = 2;
        if (nextUnsignedByteFromBuffer <= 48) {
            if (nextUnsignedByteFromBuffer == 48) {
                return _finishNumberLeadingNegZeroes();
            }
            _reportUnexpectedNumberChar(nextUnsignedByteFromBuffer, "expected digit (0-9) to follow minus sign, for valid numeric value");
        } else if (nextUnsignedByteFromBuffer > 57) {
            if (nextUnsignedByteFromBuffer == 73) {
                return _finishNonStdToken(3, 2);
            }
            _reportUnexpectedNumberChar(nextUnsignedByteFromBuffer, "expected digit (0-9) to follow minus sign, for valid numeric value");
        }
        char[] emptyAndGetCurrentSegment = this._textBuffer.emptyAndGetCurrentSegment();
        emptyAndGetCurrentSegment[0] = SignatureVisitor.SUPER;
        emptyAndGetCurrentSegment[1] = (char) nextUnsignedByteFromBuffer;
        int i4 = this._inputPtr;
        if (i4 >= this._inputEnd) {
            this._minorState = 26;
            this._textBuffer.setCurrentLength(2);
            this._intLength = 1;
            JsonToken jsonToken2 = JsonToken.NOT_AVAILABLE;
            this._currToken = jsonToken2;
            return jsonToken2;
        }
        byte byteFromBuffer = getByteFromBuffer(i4);
        while (true) {
            if (byteFromBuffer < 48) {
                if (byteFromBuffer == 46) {
                    this._intLength = i3 - 1;
                    this._inputPtr++;
                    return _startFloat(emptyAndGetCurrentSegment, i3, byteFromBuffer);
                }
            } else if (byteFromBuffer <= 57) {
                if (i3 >= emptyAndGetCurrentSegment.length) {
                    emptyAndGetCurrentSegment = this._textBuffer.expandCurrentSegment();
                }
                int i5 = i3 + 1;
                emptyAndGetCurrentSegment[i3] = (char) byteFromBuffer;
                int i6 = this._inputPtr + 1;
                this._inputPtr = i6;
                if (i6 >= this._inputEnd) {
                    this._minorState = 26;
                    this._textBuffer.setCurrentLength(i5);
                    JsonToken jsonToken3 = JsonToken.NOT_AVAILABLE;
                    this._currToken = jsonToken3;
                    return jsonToken3;
                }
                byteFromBuffer = getByteFromBuffer(i6) & 255;
                i3 = i5;
            } else if (byteFromBuffer == 101 || byteFromBuffer == 69) {
                this._intLength = i3 - 1;
                this._inputPtr++;
                return _startFloat(emptyAndGetCurrentSegment, i3, byteFromBuffer);
            }
        }
        this._intLength = i3 - 1;
        this._textBuffer.setCurrentLength(i3);
        return _valueComplete(JsonToken.VALUE_NUMBER_INT);
    }

    public JsonToken _startNullToken() throws IOException {
        byte byteFromBuffer;
        int i3 = this._inputPtr;
        if (i3 + 3 < this._inputEnd) {
            int i4 = i3 + 1;
            if (getByteFromBuffer(i3) == 117) {
                int i5 = i3 + 2;
                if (getByteFromBuffer(i4) == 108) {
                    int i6 = i3 + 3;
                    if (getByteFromBuffer(i5) == 108 && ((byteFromBuffer = getByteFromBuffer(i6) & 255) < 48 || byteFromBuffer == 93 || byteFromBuffer == 125)) {
                        this._inputPtr = i6;
                        return _valueComplete(JsonToken.VALUE_NULL);
                    }
                }
            }
        }
        this._minorState = 16;
        return _finishKeywordToken(AbstractJsonLexerKt.NULL, 1, JsonToken.VALUE_NULL);
    }

    public JsonToken _startNumberLeadingZero() throws IOException {
        int i3 = this._inputPtr;
        if (i3 >= this._inputEnd) {
            this._minorState = 24;
            JsonToken jsonToken = JsonToken.NOT_AVAILABLE;
            this._currToken = jsonToken;
            return jsonToken;
        }
        int i4 = i3 + 1;
        byte byteFromBuffer = getByteFromBuffer(i3) & 255;
        if (byteFromBuffer < 48) {
            if (byteFromBuffer == 46) {
                this._inputPtr = i4;
                this._intLength = 1;
                char[] emptyAndGetCurrentSegment = this._textBuffer.emptyAndGetCurrentSegment();
                emptyAndGetCurrentSegment[0] = '0';
                return _startFloat(emptyAndGetCurrentSegment, 1, byteFromBuffer);
            }
        } else if (byteFromBuffer <= 57) {
            return _finishNumberLeadingZeroes();
        } else {
            if (byteFromBuffer == 101 || byteFromBuffer == 69) {
                this._inputPtr = i4;
                this._intLength = 1;
                char[] emptyAndGetCurrentSegment2 = this._textBuffer.emptyAndGetCurrentSegment();
                emptyAndGetCurrentSegment2[0] = '0';
                return _startFloat(emptyAndGetCurrentSegment2, 1, byteFromBuffer);
            } else if (!(byteFromBuffer == 93 || byteFromBuffer == 125)) {
                _reportUnexpectedNumberChar(byteFromBuffer, "expected digit (0-9), decimal point (.) or exponent indicator (e/E) to follow '0'");
            }
        }
        return _valueCompleteInt(0, SchemaSymbols.ATTVAL_FALSE_0);
    }

    public JsonToken _startPositiveNumber(int i3) throws IOException {
        this._numberNegative = false;
        char[] emptyAndGetCurrentSegment = this._textBuffer.emptyAndGetCurrentSegment();
        emptyAndGetCurrentSegment[0] = (char) i3;
        int i4 = this._inputPtr;
        if (i4 >= this._inputEnd) {
            this._minorState = 26;
            this._textBuffer.setCurrentLength(1);
            JsonToken jsonToken = JsonToken.NOT_AVAILABLE;
            this._currToken = jsonToken;
            return jsonToken;
        }
        byte byteFromBuffer = getByteFromBuffer(i4) & 255;
        int i5 = 1;
        while (true) {
            if (byteFromBuffer < 48) {
                if (byteFromBuffer == 46) {
                    this._intLength = i5;
                    this._inputPtr++;
                    return _startFloat(emptyAndGetCurrentSegment, i5, byteFromBuffer);
                }
            } else if (byteFromBuffer <= 57) {
                if (i5 >= emptyAndGetCurrentSegment.length) {
                    emptyAndGetCurrentSegment = this._textBuffer.expandCurrentSegment();
                }
                int i6 = i5 + 1;
                emptyAndGetCurrentSegment[i5] = (char) byteFromBuffer;
                int i7 = this._inputPtr + 1;
                this._inputPtr = i7;
                if (i7 >= this._inputEnd) {
                    this._minorState = 26;
                    this._textBuffer.setCurrentLength(i6);
                    JsonToken jsonToken2 = JsonToken.NOT_AVAILABLE;
                    this._currToken = jsonToken2;
                    return jsonToken2;
                }
                byteFromBuffer = getByteFromBuffer(i7) & 255;
                i5 = i6;
            } else if (byteFromBuffer == 101 || byteFromBuffer == 69) {
                this._intLength = i5;
                this._inputPtr++;
                return _startFloat(emptyAndGetCurrentSegment, i5, byteFromBuffer);
            }
        }
        this._intLength = i5;
        this._textBuffer.setCurrentLength(i5);
        return _valueComplete(JsonToken.VALUE_NUMBER_INT);
    }

    public JsonToken _startString() throws IOException {
        int i3 = this._inputPtr;
        char[] emptyAndGetCurrentSegment = this._textBuffer.emptyAndGetCurrentSegment();
        int[] iArr = _icUTF8;
        int min = Math.min(this._inputEnd, emptyAndGetCurrentSegment.length + i3);
        int i4 = 0;
        while (true) {
            if (i3 >= min) {
                break;
            }
            byte byteFromBuffer = getByteFromBuffer(i3) & 255;
            if (iArr[byteFromBuffer] == 0) {
                i3++;
                emptyAndGetCurrentSegment[i4] = (char) byteFromBuffer;
                i4++;
            } else if (byteFromBuffer == 34) {
                this._inputPtr = i3 + 1;
                this._textBuffer.setCurrentLength(i4);
                return _valueComplete(JsonToken.VALUE_STRING);
            }
        }
        this._textBuffer.setCurrentLength(i4);
        this._inputPtr = i3;
        return _finishRegularString();
    }

    public JsonToken _startTrueToken() throws IOException {
        byte byteFromBuffer;
        int i3 = this._inputPtr;
        if (i3 + 3 < this._inputEnd) {
            int i4 = i3 + 1;
            if (getByteFromBuffer(i3) == 114) {
                int i5 = i3 + 2;
                if (getByteFromBuffer(i4) == 117) {
                    int i6 = i3 + 3;
                    if (getByteFromBuffer(i5) == 101 && ((byteFromBuffer = getByteFromBuffer(i6) & 255) < 48 || byteFromBuffer == 93 || byteFromBuffer == 125)) {
                        this._inputPtr = i6;
                        return _valueComplete(JsonToken.VALUE_TRUE);
                    }
                }
            }
        }
        this._minorState = 17;
        return _finishKeywordToken("true", 1, JsonToken.VALUE_TRUE);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:13:0x001b, code lost:
        if (r4 != 44) goto L_0x005f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x002a, code lost:
        if (r2._parsingContext.inArray() == false) goto L_0x005f;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.fasterxml.jackson.core.JsonToken _startUnexpectedValue(boolean r3, int r4) throws java.io.IOException {
        /*
            r2 = this;
            r3 = 39
            if (r4 == r3) goto L_0x0053
            r3 = 73
            r0 = 1
            if (r4 == r3) goto L_0x004e
            r3 = 78
            if (r4 == r3) goto L_0x0048
            r3 = 93
            if (r4 == r3) goto L_0x0024
            r3 = 125(0x7d, float:1.75E-43)
            if (r4 == r3) goto L_0x005f
            r3 = 43
            if (r4 == r3) goto L_0x001e
            r3 = 44
            if (r4 == r3) goto L_0x002d
            goto L_0x005f
        L_0x001e:
            r3 = 2
            com.fasterxml.jackson.core.JsonToken r2 = r2._finishNonStdToken(r3, r0)
            return r2
        L_0x0024:
            com.fasterxml.jackson.core.json.JsonReadContext r3 = r2._parsingContext
            boolean r3 = r3.inArray()
            if (r3 != 0) goto L_0x002d
            goto L_0x005f
        L_0x002d:
            com.fasterxml.jackson.core.json.JsonReadContext r3 = r2._parsingContext
            boolean r3 = r3.inRoot()
            if (r3 != 0) goto L_0x005f
            int r3 = r2._features
            int r1 = FEAT_MASK_ALLOW_MISSING
            r3 = r3 & r1
            if (r3 == 0) goto L_0x005f
            int r3 = r2._inputPtr
            int r3 = r3 - r0
            r2._inputPtr = r3
            com.fasterxml.jackson.core.JsonToken r3 = com.fasterxml.jackson.core.JsonToken.VALUE_NULL
            com.fasterxml.jackson.core.JsonToken r2 = r2._valueComplete(r3)
            return r2
        L_0x0048:
            r3 = 0
            com.fasterxml.jackson.core.JsonToken r2 = r2._finishNonStdToken(r3, r0)
            return r2
        L_0x004e:
            com.fasterxml.jackson.core.JsonToken r2 = r2._finishNonStdToken(r0, r0)
            return r2
        L_0x0053:
            int r3 = r2._features
            int r0 = FEAT_MASK_ALLOW_SINGLE_QUOTES
            r3 = r3 & r0
            if (r3 == 0) goto L_0x005f
            com.fasterxml.jackson.core.JsonToken r2 = r2._startAposString()
            return r2
        L_0x005f:
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            java.lang.String r0 = "expected a valid value "
            r3.<init>(r0)
            java.lang.String r0 = r2._validJsonValueList()
            r3.append(r0)
            java.lang.String r3 = r3.toString()
            r2._reportUnexpectedChar(r4, r3)
            r2 = 0
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.fasterxml.jackson.core.json.async.NonBlockingUtf8JsonParserBase._startUnexpectedValue(boolean, int):com.fasterxml.jackson.core.JsonToken");
    }

    public void endOfInput() {
        this._endOfInput = true;
    }

    public abstract byte getByteFromBuffer(int i3);

    public abstract byte getNextSignedByteFromBuffer();

    public abstract int getNextUnsignedByteFromBuffer();

    public final boolean needMoreInput() {
        return this._inputPtr >= this._inputEnd && !this._endOfInput;
    }

    public JsonToken nextToken() throws IOException {
        int i3 = this._inputPtr;
        if (i3 >= this._inputEnd) {
            if (this._closed) {
                return null;
            }
            return this._endOfInput ? this._currToken == JsonToken.NOT_AVAILABLE ? _finishTokenWithEOF() : _eofAsNextToken() : JsonToken.NOT_AVAILABLE;
        } else if (this._currToken == JsonToken.NOT_AVAILABLE) {
            return _finishToken();
        } else {
            this._numTypesValid = 0;
            this._tokenInputTotal = this._currInputProcessed + ((long) i3);
            this._binaryValue = null;
            int nextUnsignedByteFromBuffer = getNextUnsignedByteFromBuffer();
            switch (this._majorState) {
                case 0:
                    return _startDocument(nextUnsignedByteFromBuffer);
                case 1:
                    return _startValue(nextUnsignedByteFromBuffer);
                case 2:
                    return _startFieldName(nextUnsignedByteFromBuffer);
                case 3:
                    return _startFieldNameAfterComma(nextUnsignedByteFromBuffer);
                case 4:
                    return _startValueExpectColon(nextUnsignedByteFromBuffer);
                case 5:
                    return _startValue(nextUnsignedByteFromBuffer);
                case 6:
                    return _startValueExpectComma(nextUnsignedByteFromBuffer);
                default:
                    VersionUtil.throwInternal();
                    return null;
            }
        }
    }

    public JsonToken _startPositiveNumber() throws IOException {
        this._numberNegative = false;
        if (this._inputPtr >= this._inputEnd) {
            this._minorState = 22;
            JsonToken jsonToken = JsonToken.NOT_AVAILABLE;
            this._currToken = jsonToken;
            return jsonToken;
        }
        int nextUnsignedByteFromBuffer = getNextUnsignedByteFromBuffer();
        int i3 = 2;
        if (nextUnsignedByteFromBuffer <= 48) {
            if (nextUnsignedByteFromBuffer == 48) {
                if (!isEnabled(JsonReadFeature.ALLOW_LEADING_PLUS_SIGN_FOR_NUMBERS.mappedFeature())) {
                    _reportUnexpectedNumberChar(43, "JSON spec does not allow numbers to have plus signs: enable `JsonReadFeature.ALLOW_LEADING_PLUS_SIGN_FOR_NUMBERS` to allow");
                }
                return _finishNumberLeadingPosZeroes();
            }
            _reportUnexpectedNumberChar(nextUnsignedByteFromBuffer, "expected digit (0-9) to follow plus sign, for valid numeric value");
        } else if (nextUnsignedByteFromBuffer > 57) {
            if (nextUnsignedByteFromBuffer == 73) {
                return _finishNonStdToken(2, 2);
            }
            _reportUnexpectedNumberChar(nextUnsignedByteFromBuffer, "expected digit (0-9) to follow plus sign, for valid numeric value");
        }
        if (!isEnabled(JsonReadFeature.ALLOW_LEADING_PLUS_SIGN_FOR_NUMBERS.mappedFeature())) {
            _reportUnexpectedNumberChar(43, "JSON spec does not allow numbers to have plus signs: enable `JsonReadFeature.ALLOW_LEADING_PLUS_SIGN_FOR_NUMBERS` to allow");
        }
        char[] emptyAndGetCurrentSegment = this._textBuffer.emptyAndGetCurrentSegment();
        emptyAndGetCurrentSegment[0] = SignatureVisitor.EXTENDS;
        emptyAndGetCurrentSegment[1] = (char) nextUnsignedByteFromBuffer;
        int i4 = this._inputPtr;
        if (i4 >= this._inputEnd) {
            this._minorState = 26;
            this._textBuffer.setCurrentLength(2);
            this._intLength = 1;
            JsonToken jsonToken2 = JsonToken.NOT_AVAILABLE;
            this._currToken = jsonToken2;
            return jsonToken2;
        }
        byte byteFromBuffer = getByteFromBuffer(i4);
        while (true) {
            if (byteFromBuffer < 48) {
                if (byteFromBuffer == 46) {
                    this._intLength = i3 - 1;
                    this._inputPtr++;
                    return _startFloat(emptyAndGetCurrentSegment, i3, byteFromBuffer);
                }
            } else if (byteFromBuffer <= 57) {
                if (i3 >= emptyAndGetCurrentSegment.length) {
                    emptyAndGetCurrentSegment = this._textBuffer.expandCurrentSegment();
                }
                int i5 = i3 + 1;
                emptyAndGetCurrentSegment[i3] = (char) byteFromBuffer;
                int i6 = this._inputPtr + 1;
                this._inputPtr = i6;
                if (i6 >= this._inputEnd) {
                    this._minorState = 26;
                    this._textBuffer.setCurrentLength(i5);
                    JsonToken jsonToken3 = JsonToken.NOT_AVAILABLE;
                    this._currToken = jsonToken3;
                    return jsonToken3;
                }
                byteFromBuffer = getByteFromBuffer(i6) & 255;
                i3 = i5;
            } else if (byteFromBuffer == 101 || byteFromBuffer == 69) {
                this._intLength = i3 - 1;
                this._inputPtr++;
                return _startFloat(emptyAndGetCurrentSegment, i3, byteFromBuffer);
            }
        }
        this._intLength = i3 - 1;
        this._textBuffer.setCurrentLength(i3);
        return _valueComplete(JsonToken.VALUE_NUMBER_INT);
    }
}
