package com.fasterxml.jackson.core;

import android.support.v4.media.session.a;
import androidx.camera.camera2.internal.C0118y;
import com.fasterxml.jackson.core.util.ByteArrayBuilder;
import java.io.Serializable;
import java.util.Arrays;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;

public final class Base64Variant implements Serializable {
    public static final int BASE64_VALUE_INVALID = -1;
    public static final int BASE64_VALUE_PADDING = -2;
    private static final int INT_SPACE = 32;
    protected static final char PADDING_CHAR_NONE = '\u0000';
    private static final long serialVersionUID = 1;
    private final transient int[] _asciiToBase64;
    private final transient byte[] _base64ToAsciiB;
    private final transient char[] _base64ToAsciiC;
    private final int _maxLineLength;
    final String _name;
    private final char _paddingChar;
    private final PaddingReadBehaviour _paddingReadBehaviour;
    private final boolean _writePadding;

    public enum PaddingReadBehaviour {
        PADDING_FORBIDDEN,
        PADDING_REQUIRED,
        PADDING_ALLOWED
    }

    public Base64Variant(String str, String str2, boolean z2, char c3, int i3) {
        int[] iArr = new int[128];
        this._asciiToBase64 = iArr;
        char[] cArr = new char[64];
        this._base64ToAsciiC = cArr;
        this._base64ToAsciiB = new byte[64];
        this._name = str;
        this._writePadding = z2;
        this._paddingChar = c3;
        this._maxLineLength = i3;
        int length = str2.length();
        if (length == 64) {
            str2.getChars(0, length, cArr, 0);
            Arrays.fill(iArr, -1);
            for (int i4 = 0; i4 < length; i4++) {
                char c4 = this._base64ToAsciiC[i4];
                this._base64ToAsciiB[i4] = (byte) c4;
                this._asciiToBase64[c4] = i4;
            }
            if (z2) {
                this._asciiToBase64[c3] = -2;
            }
            this._paddingReadBehaviour = z2 ? PaddingReadBehaviour.PADDING_REQUIRED : PaddingReadBehaviour.PADDING_FORBIDDEN;
            return;
        }
        throw new IllegalArgumentException(C0118y.c(length, "Base64Alphabet length must be exactly 64 (was ", ")"));
    }

    public void _reportBase64EOF() throws IllegalArgumentException {
        throw new IllegalArgumentException(missingPaddingMessage());
    }

    public void _reportBase64UnexpectedPadding() throws IllegalArgumentException {
        throw new IllegalArgumentException(unexpectedPaddingMessage());
    }

    public void _reportInvalidBase64(char c3, int i3, String str) throws IllegalArgumentException {
        String str2;
        if (c3 <= ' ') {
            str2 = "Illegal white space character (code 0x" + Integer.toHexString(c3) + ") as character #" + (i3 + 1) + " of 4-char base64 unit: can only used between units";
        } else if (usesPaddingChar(c3)) {
            str2 = "Unexpected padding character ('" + getPaddingChar() + "') as character #" + (i3 + 1) + " of 4-char base64 unit: padding only legal as 3rd or 4th character";
        } else if (!Character.isDefined(c3) || Character.isISOControl(c3)) {
            str2 = "Illegal character (code 0x" + Integer.toHexString(c3) + ") in base64 content";
        } else {
            str2 = "Illegal character '" + c3 + "' (code 0x" + Integer.toHexString(c3) + ") in base64 content";
        }
        if (str != null) {
            str2 = a.n(str2, ": ", str);
        }
        throw new IllegalArgumentException(str2);
    }

    public boolean acceptsPaddingOnRead() {
        return this._paddingReadBehaviour != PaddingReadBehaviour.PADDING_FORBIDDEN;
    }

    public byte[] decode(String str) throws IllegalArgumentException {
        ByteArrayBuilder byteArrayBuilder = new ByteArrayBuilder();
        decode(str, byteArrayBuilder);
        return byteArrayBuilder.toByteArray();
    }

    public int decodeBase64Byte(byte b3) {
        if (b3 < 0) {
            return -1;
        }
        return this._asciiToBase64[b3];
    }

    public int decodeBase64Char(char c3) {
        if (c3 <= 127) {
            return this._asciiToBase64[c3];
        }
        return -1;
    }

    public String encode(byte[] bArr) {
        return encode(bArr, false);
    }

    public byte encodeBase64BitsAsByte(int i3) {
        return this._base64ToAsciiB[i3];
    }

    public char encodeBase64BitsAsChar(int i3) {
        return this._base64ToAsciiC[i3];
    }

    public int encodeBase64Chunk(int i3, char[] cArr, int i4) {
        char[] cArr2 = this._base64ToAsciiC;
        cArr[i4] = cArr2[(i3 >> 18) & 63];
        cArr[i4 + 1] = cArr2[(i3 >> 12) & 63];
        int i5 = i4 + 3;
        cArr[i4 + 2] = cArr2[(i3 >> 6) & 63];
        int i6 = i4 + 4;
        cArr[i5] = cArr2[i3 & 63];
        return i6;
    }

    public int encodeBase64Partial(int i3, int i4, char[] cArr, int i5) {
        char[] cArr2 = this._base64ToAsciiC;
        cArr[i5] = cArr2[(i3 >> 18) & 63];
        int i6 = i5 + 2;
        cArr[i5 + 1] = cArr2[(i3 >> 12) & 63];
        if (usesPadding()) {
            int i7 = i5 + 3;
            cArr[i6] = i4 == 2 ? this._base64ToAsciiC[(i3 >> 6) & 63] : this._paddingChar;
            int i8 = i5 + 4;
            cArr[i7] = this._paddingChar;
            return i8;
        } else if (i4 != 2) {
            return i6;
        } else {
            cArr[i6] = this._base64ToAsciiC[(i3 >> 6) & 63];
            return i5 + 3;
        }
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null || obj.getClass() != Base64Variant.class) {
            return false;
        }
        Base64Variant base64Variant = (Base64Variant) obj;
        return base64Variant._paddingChar == this._paddingChar && base64Variant._maxLineLength == this._maxLineLength && base64Variant._writePadding == this._writePadding && base64Variant._paddingReadBehaviour == this._paddingReadBehaviour && this._name.equals(base64Variant._name);
    }

    public int getMaxLineLength() {
        return this._maxLineLength;
    }

    public String getName() {
        return this._name;
    }

    public byte getPaddingByte() {
        return (byte) this._paddingChar;
    }

    public char getPaddingChar() {
        return this._paddingChar;
    }

    public int hashCode() {
        return this._name.hashCode();
    }

    public String missingPaddingMessage() {
        return String.format("Unexpected end of base64-encoded String: base64 variant '%s' expects padding (one or more '%c' characters) at the end. This Base64Variant might have been incorrectly configured", new Object[]{getName(), Character.valueOf(getPaddingChar())});
    }

    public PaddingReadBehaviour paddingReadBehaviour() {
        return this._paddingReadBehaviour;
    }

    public Object readResolve() {
        Base64Variant valueOf = Base64Variants.valueOf(this._name);
        boolean z2 = this._writePadding;
        boolean z3 = valueOf._writePadding;
        return (z2 == z3 && this._paddingChar == valueOf._paddingChar && this._paddingReadBehaviour == valueOf._paddingReadBehaviour && this._maxLineLength == valueOf._maxLineLength && z2 == z3) ? valueOf : new Base64Variant(valueOf, this._name, z2, this._paddingChar, this._paddingReadBehaviour, this._maxLineLength);
    }

    public boolean requiresPaddingOnRead() {
        return this._paddingReadBehaviour == PaddingReadBehaviour.PADDING_REQUIRED;
    }

    public String toString() {
        return this._name;
    }

    public String unexpectedPaddingMessage() {
        return A.a.l("Unexpected end of base64-encoded String: base64 variant '", getName(), "' expects no padding at the end while decoding. This Base64Variant might have been incorrectly configured");
    }

    public boolean usesPadding() {
        return this._writePadding;
    }

    public boolean usesPaddingChar(char c3) {
        return c3 == this._paddingChar;
    }

    public Base64Variant withPaddingAllowed() {
        return withReadPadding(PaddingReadBehaviour.PADDING_ALLOWED);
    }

    public Base64Variant withPaddingForbidden() {
        return withReadPadding(PaddingReadBehaviour.PADDING_FORBIDDEN);
    }

    public Base64Variant withPaddingRequired() {
        return withReadPadding(PaddingReadBehaviour.PADDING_REQUIRED);
    }

    public Base64Variant withReadPadding(PaddingReadBehaviour paddingReadBehaviour) {
        return paddingReadBehaviour == this._paddingReadBehaviour ? this : new Base64Variant(this, paddingReadBehaviour);
    }

    public Base64Variant withWritePadding(boolean z2) {
        if (z2 == this._writePadding) {
            return this;
        }
        return new Base64Variant(this, this._name, z2, this._paddingChar, this._maxLineLength);
    }

    public int decodeBase64Char(int i3) {
        if (i3 <= 127) {
            return this._asciiToBase64[i3];
        }
        return -1;
    }

    public String encode(byte[] bArr, boolean z2) {
        int length = bArr.length;
        StringBuilder sb = new StringBuilder((length >> 2) + length + (length >> 3));
        if (z2) {
            sb.append('\"');
        }
        int maxLineLength = getMaxLineLength() >> 2;
        int i3 = length - 3;
        int i4 = 0;
        while (i4 <= i3) {
            int i5 = i4 + 2;
            byte b3 = bArr[i4 + 1] & 255;
            i4 += 3;
            encodeBase64Chunk(sb, ((b3 | (bArr[i4] << 8)) << 8) | (bArr[i5] & 255));
            maxLineLength--;
            if (maxLineLength <= 0) {
                sb.append(AbstractJsonLexerKt.STRING_ESC);
                sb.append('n');
                maxLineLength = getMaxLineLength() >> 2;
            }
        }
        int i6 = length - i4;
        if (i6 > 0) {
            int i7 = i4 + 1;
            int i8 = bArr[i4] << 16;
            if (i6 == 2) {
                i8 |= (bArr[i7] & 255) << 8;
            }
            encodeBase64Partial(sb, i8, i6);
        }
        if (z2) {
            sb.append('\"');
        }
        return sb.toString();
    }

    public boolean usesPaddingChar(int i3) {
        return i3 == this._paddingChar;
    }

    public void decode(String str, ByteArrayBuilder byteArrayBuilder) throws IllegalArgumentException {
        int length = str.length();
        int i3 = 0;
        while (i3 < length) {
            int i4 = i3 + 1;
            char charAt = str.charAt(i3);
            if (charAt > ' ') {
                int decodeBase64Char = decodeBase64Char(charAt);
                if (decodeBase64Char < 0) {
                    _reportInvalidBase64(charAt, 0, (String) null);
                }
                if (i4 >= length) {
                    _reportBase64EOF();
                }
                int i5 = i3 + 2;
                char charAt2 = str.charAt(i4);
                int decodeBase64Char2 = decodeBase64Char(charAt2);
                if (decodeBase64Char2 < 0) {
                    _reportInvalidBase64(charAt2, 1, (String) null);
                }
                int i6 = (decodeBase64Char << 6) | decodeBase64Char2;
                if (i5 >= length) {
                    if (!requiresPaddingOnRead()) {
                        byteArrayBuilder.append(i6 >> 4);
                        return;
                    }
                    _reportBase64EOF();
                }
                int i7 = i3 + 3;
                char charAt3 = str.charAt(i5);
                int decodeBase64Char3 = decodeBase64Char(charAt3);
                if (decodeBase64Char3 < 0) {
                    if (decodeBase64Char3 != -2) {
                        _reportInvalidBase64(charAt3, 2, (String) null);
                    }
                    if (!acceptsPaddingOnRead()) {
                        _reportBase64UnexpectedPadding();
                    }
                    if (i7 >= length) {
                        _reportBase64EOF();
                    }
                    i3 += 4;
                    char charAt4 = str.charAt(i7);
                    if (!usesPaddingChar(charAt4)) {
                        _reportInvalidBase64(charAt4, 3, "expected padding character '" + getPaddingChar() + "'");
                    }
                    byteArrayBuilder.append(i6 >> 4);
                } else {
                    int i8 = (i6 << 6) | decodeBase64Char3;
                    if (i7 >= length) {
                        if (!requiresPaddingOnRead()) {
                            byteArrayBuilder.appendTwoBytes(i8 >> 2);
                            return;
                        }
                        _reportBase64EOF();
                    }
                    i3 += 4;
                    char charAt5 = str.charAt(i7);
                    int decodeBase64Char4 = decodeBase64Char(charAt5);
                    if (decodeBase64Char4 < 0) {
                        if (decodeBase64Char4 != -2) {
                            _reportInvalidBase64(charAt5, 3, (String) null);
                        }
                        if (!acceptsPaddingOnRead()) {
                            _reportBase64UnexpectedPadding();
                        }
                        byteArrayBuilder.appendTwoBytes(i8 >> 2);
                    } else {
                        byteArrayBuilder.appendThreeBytes((i8 << 6) | decodeBase64Char4);
                    }
                }
            } else {
                i3 = i4;
            }
        }
    }

    public void encodeBase64Chunk(StringBuilder sb, int i3) {
        sb.append(this._base64ToAsciiC[(i3 >> 18) & 63]);
        sb.append(this._base64ToAsciiC[(i3 >> 12) & 63]);
        sb.append(this._base64ToAsciiC[(i3 >> 6) & 63]);
        sb.append(this._base64ToAsciiC[i3 & 63]);
    }

    public void encodeBase64Partial(StringBuilder sb, int i3, int i4) {
        sb.append(this._base64ToAsciiC[(i3 >> 18) & 63]);
        sb.append(this._base64ToAsciiC[(i3 >> 12) & 63]);
        if (usesPadding()) {
            sb.append(i4 == 2 ? this._base64ToAsciiC[(i3 >> 6) & 63] : this._paddingChar);
            sb.append(this._paddingChar);
        } else if (i4 == 2) {
            sb.append(this._base64ToAsciiC[(i3 >> 6) & 63]);
        }
    }

    public int encodeBase64Chunk(int i3, byte[] bArr, int i4) {
        byte[] bArr2 = this._base64ToAsciiB;
        bArr[i4] = bArr2[(i3 >> 18) & 63];
        bArr[i4 + 1] = bArr2[(i3 >> 12) & 63];
        int i5 = i4 + 3;
        bArr[i4 + 2] = bArr2[(i3 >> 6) & 63];
        int i6 = i4 + 4;
        bArr[i5] = bArr2[i3 & 63];
        return i6;
    }

    public int encodeBase64Partial(int i3, int i4, byte[] bArr, int i5) {
        byte[] bArr2 = this._base64ToAsciiB;
        bArr[i5] = bArr2[(i3 >> 18) & 63];
        int i6 = i5 + 2;
        bArr[i5 + 1] = bArr2[(i3 >> 12) & 63];
        if (usesPadding()) {
            byte b3 = (byte) this._paddingChar;
            int i7 = i5 + 3;
            bArr[i6] = i4 == 2 ? this._base64ToAsciiB[(i3 >> 6) & 63] : b3;
            int i8 = i5 + 4;
            bArr[i7] = b3;
            return i8;
        } else if (i4 != 2) {
            return i6;
        } else {
            bArr[i6] = this._base64ToAsciiB[(i3 >> 6) & 63];
            return i5 + 3;
        }
    }

    public String encode(byte[] bArr, boolean z2, String str) {
        int length = bArr.length;
        StringBuilder sb = new StringBuilder((length >> 2) + length + (length >> 3));
        if (z2) {
            sb.append('\"');
        }
        int maxLineLength = getMaxLineLength() >> 2;
        int i3 = length - 3;
        int i4 = 0;
        while (i4 <= i3) {
            int i5 = i4 + 2;
            byte b3 = bArr[i4 + 1] & 255;
            i4 += 3;
            encodeBase64Chunk(sb, ((b3 | (bArr[i4] << 8)) << 8) | (bArr[i5] & 255));
            maxLineLength--;
            if (maxLineLength <= 0) {
                sb.append(str);
                maxLineLength = getMaxLineLength() >> 2;
            }
        }
        int i6 = length - i4;
        if (i6 > 0) {
            int i7 = i4 + 1;
            int i8 = bArr[i4] << 16;
            if (i6 == 2) {
                i8 |= (bArr[i7] & 255) << 8;
            }
            encodeBase64Partial(sb, i8, i6);
        }
        if (z2) {
            sb.append('\"');
        }
        return sb.toString();
    }

    public Base64Variant(Base64Variant base64Variant, String str, int i3) {
        this(base64Variant, str, base64Variant._writePadding, base64Variant._paddingChar, i3);
    }

    public Base64Variant(Base64Variant base64Variant, String str, boolean z2, char c3, int i3) {
        this(base64Variant, str, z2, c3, base64Variant._paddingReadBehaviour, i3);
    }

    private Base64Variant(Base64Variant base64Variant, String str, boolean z2, char c3, PaddingReadBehaviour paddingReadBehaviour, int i3) {
        int[] iArr = new int[128];
        this._asciiToBase64 = iArr;
        char[] cArr = new char[64];
        this._base64ToAsciiC = cArr;
        byte[] bArr = new byte[64];
        this._base64ToAsciiB = bArr;
        this._name = str;
        byte[] bArr2 = base64Variant._base64ToAsciiB;
        System.arraycopy(bArr2, 0, bArr, 0, bArr2.length);
        char[] cArr2 = base64Variant._base64ToAsciiC;
        System.arraycopy(cArr2, 0, cArr, 0, cArr2.length);
        int[] iArr2 = base64Variant._asciiToBase64;
        System.arraycopy(iArr2, 0, iArr, 0, iArr2.length);
        this._writePadding = z2;
        this._paddingChar = c3;
        this._maxLineLength = i3;
        this._paddingReadBehaviour = paddingReadBehaviour;
    }

    private Base64Variant(Base64Variant base64Variant, PaddingReadBehaviour paddingReadBehaviour) {
        this(base64Variant, base64Variant._name, base64Variant._writePadding, base64Variant._paddingChar, paddingReadBehaviour, base64Variant._maxLineLength);
    }
}
