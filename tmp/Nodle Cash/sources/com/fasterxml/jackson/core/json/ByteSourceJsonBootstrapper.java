package com.fasterxml.jackson.core.json;

import A.a;
import com.fasterxml.jackson.core.JsonEncoding;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.core.format.InputAccessor;
import com.fasterxml.jackson.core.format.MatchStrength;
import com.fasterxml.jackson.core.io.IOContext;
import com.fasterxml.jackson.core.io.MergedStream;
import com.fasterxml.jackson.core.io.UTF32Reader;
import com.fasterxml.jackson.core.sym.ByteQuadsCanonicalizer;
import com.fasterxml.jackson.core.sym.CharsToNameCanonicalizer;
import java.io.ByteArrayInputStream;
import java.io.CharConversionException;
import java.io.DataInput;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

public final class ByteSourceJsonBootstrapper {
    public static final byte UTF8_BOM_1 = -17;
    public static final byte UTF8_BOM_2 = -69;
    public static final byte UTF8_BOM_3 = -65;
    private boolean _bigEndian = true;
    private final boolean _bufferRecyclable;
    private int _bytesPerChar;
    private final IOContext _context;
    private final InputStream _in;
    private final byte[] _inputBuffer;
    private int _inputEnd;
    private int _inputPtr;

    public ByteSourceJsonBootstrapper(IOContext iOContext, InputStream inputStream) {
        this._context = iOContext;
        this._in = inputStream;
        this._inputBuffer = iOContext.allocReadIOBuffer();
        this._inputPtr = 0;
        this._inputEnd = 0;
        this._bufferRecyclable = true;
    }

    private boolean checkUTF16(int i3) {
        if ((65280 & i3) == 0) {
            this._bigEndian = true;
        } else if ((i3 & 255) != 0) {
            return false;
        } else {
            this._bigEndian = false;
        }
        this._bytesPerChar = 2;
        return true;
    }

    private boolean checkUTF32(int i3) throws IOException {
        if ((i3 >> 8) == 0) {
            this._bigEndian = true;
        } else if ((16777215 & i3) == 0) {
            this._bigEndian = false;
        } else if ((-16711681 & i3) == 0) {
            reportWeirdUCS4("3412");
        } else if ((i3 & -65281) != 0) {
            return false;
        } else {
            reportWeirdUCS4("2143");
        }
        this._bytesPerChar = 4;
        return true;
    }

    private boolean handleBOM(int i3) throws IOException {
        if (i3 == -16842752) {
            reportWeirdUCS4("3412");
        } else if (i3 == -131072) {
            this._inputPtr += 4;
            this._bytesPerChar = 4;
            this._bigEndian = false;
            return true;
        } else if (i3 == 65279) {
            this._bigEndian = true;
            this._inputPtr += 4;
            this._bytesPerChar = 4;
            return true;
        } else if (i3 == 65534) {
            reportWeirdUCS4("2143");
        }
        int i4 = i3 >>> 16;
        if (i4 == 65279) {
            this._inputPtr += 2;
            this._bytesPerChar = 2;
            this._bigEndian = true;
            return true;
        } else if (i4 == 65534) {
            this._inputPtr += 2;
            this._bytesPerChar = 2;
            this._bigEndian = false;
            return true;
        } else if ((i3 >>> 8) != 15711167) {
            return false;
        } else {
            this._inputPtr += 3;
            this._bytesPerChar = 1;
            this._bigEndian = true;
            return true;
        }
    }

    public static MatchStrength hasJSONFormat(InputAccessor inputAccessor) throws IOException {
        if (!inputAccessor.hasMoreBytes()) {
            return MatchStrength.INCONCLUSIVE;
        }
        byte nextByte = inputAccessor.nextByte();
        if (nextByte == -17) {
            if (!inputAccessor.hasMoreBytes()) {
                return MatchStrength.INCONCLUSIVE;
            }
            if (inputAccessor.nextByte() != -69) {
                return MatchStrength.NO_MATCH;
            }
            if (!inputAccessor.hasMoreBytes()) {
                return MatchStrength.INCONCLUSIVE;
            }
            if (inputAccessor.nextByte() != -65) {
                return MatchStrength.NO_MATCH;
            }
            if (!inputAccessor.hasMoreBytes()) {
                return MatchStrength.INCONCLUSIVE;
            }
            nextByte = inputAccessor.nextByte();
        }
        int skipSpace = skipSpace(inputAccessor, nextByte);
        if (skipSpace < 0) {
            return MatchStrength.INCONCLUSIVE;
        }
        if (skipSpace == 123) {
            int skipSpace2 = skipSpace(inputAccessor);
            return skipSpace2 < 0 ? MatchStrength.INCONCLUSIVE : (skipSpace2 == 34 || skipSpace2 == 125) ? MatchStrength.SOLID_MATCH : MatchStrength.NO_MATCH;
        } else if (skipSpace == 91) {
            int skipSpace3 = skipSpace(inputAccessor);
            return skipSpace3 < 0 ? MatchStrength.INCONCLUSIVE : (skipSpace3 == 93 || skipSpace3 == 91) ? MatchStrength.SOLID_MATCH : MatchStrength.SOLID_MATCH;
        } else {
            MatchStrength matchStrength = MatchStrength.WEAK_MATCH;
            if (skipSpace == 34) {
                return matchStrength;
            }
            if (skipSpace <= 57 && skipSpace >= 48) {
                return matchStrength;
            }
            if (skipSpace != 45) {
                return skipSpace == 110 ? tryMatch(inputAccessor, "ull", matchStrength) : skipSpace == 116 ? tryMatch(inputAccessor, "rue", matchStrength) : skipSpace == 102 ? tryMatch(inputAccessor, "alse", matchStrength) : MatchStrength.NO_MATCH;
            }
            int skipSpace4 = skipSpace(inputAccessor);
            return skipSpace4 < 0 ? MatchStrength.INCONCLUSIVE : (skipSpace4 > 57 || skipSpace4 < 48) ? MatchStrength.NO_MATCH : matchStrength;
        }
    }

    private void reportWeirdUCS4(String str) throws IOException {
        throw new CharConversionException(a.l("Unsupported UCS-4 endianness (", str, ") detected"));
    }

    private static int skipSpace(InputAccessor inputAccessor) throws IOException {
        if (!inputAccessor.hasMoreBytes()) {
            return -1;
        }
        return skipSpace(inputAccessor, inputAccessor.nextByte());
    }

    public static int skipUTF8BOM(DataInput dataInput) throws IOException {
        int readUnsignedByte = dataInput.readUnsignedByte();
        if (readUnsignedByte != 239) {
            return readUnsignedByte;
        }
        int readUnsignedByte2 = dataInput.readUnsignedByte();
        if (readUnsignedByte2 == 187) {
            int readUnsignedByte3 = dataInput.readUnsignedByte();
            if (readUnsignedByte3 == 191) {
                return dataInput.readUnsignedByte();
            }
            throw new IOException("Unexpected byte 0x" + Integer.toHexString(readUnsignedByte3) + " following 0xEF 0xBB; should get 0xBF as part of UTF-8 BOM");
        }
        throw new IOException("Unexpected byte 0x" + Integer.toHexString(readUnsignedByte2) + " following 0xEF; should get 0xBB as part of UTF-8 BOM");
    }

    private static MatchStrength tryMatch(InputAccessor inputAccessor, String str, MatchStrength matchStrength) throws IOException {
        int length = str.length();
        for (int i3 = 0; i3 < length; i3++) {
            if (!inputAccessor.hasMoreBytes()) {
                return MatchStrength.INCONCLUSIVE;
            }
            if (inputAccessor.nextByte() != str.charAt(i3)) {
                return MatchStrength.NO_MATCH;
            }
        }
        return matchStrength;
    }

    public JsonParser constructParser(int i3, ObjectCodec objectCodec, ByteQuadsCanonicalizer byteQuadsCanonicalizer, CharsToNameCanonicalizer charsToNameCanonicalizer, int i4) throws IOException {
        int i5 = i4;
        int i6 = this._inputPtr;
        JsonEncoding detectEncoding = detectEncoding();
        int i7 = this._inputPtr - i6;
        if (detectEncoding != JsonEncoding.UTF8 || !JsonFactory.Feature.CANONICALIZE_FIELD_NAMES.enabledIn(i5)) {
            return new ReaderBasedJsonParser(this._context, i3, constructReader(), objectCodec, charsToNameCanonicalizer.makeChild(i4));
        }
        return new UTF8StreamJsonParser(this._context, i3, this._in, objectCodec, byteQuadsCanonicalizer.makeChild(i5), this._inputBuffer, this._inputPtr, this._inputEnd, i7, this._bufferRecyclable);
    }

    public Reader constructReader() throws IOException {
        JsonEncoding encoding = this._context.getEncoding();
        int bits = encoding.bits();
        if (bits == 8 || bits == 16) {
            InputStream inputStream = this._in;
            if (inputStream == null) {
                inputStream = new ByteArrayInputStream(this._inputBuffer, this._inputPtr, this._inputEnd);
            } else if (this._inputPtr < this._inputEnd) {
                inputStream = new MergedStream(this._context, inputStream, this._inputBuffer, this._inputPtr, this._inputEnd);
            }
            return new InputStreamReader(inputStream, encoding.getJavaName());
        } else if (bits == 32) {
            IOContext iOContext = this._context;
            return new UTF32Reader(iOContext, this._in, this._inputBuffer, this._inputPtr, this._inputEnd, iOContext.getEncoding().isBigEndian());
        } else {
            throw new RuntimeException("Internal error");
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:11:0x005b, code lost:
        if (checkUTF16((r1[r4 + 1] & 255) | ((r1[r4] & 255) << 8)) != false) goto L_0x005d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:7:0x003e, code lost:
        if (checkUTF16(r1 >>> 16) != false) goto L_0x005d;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.fasterxml.jackson.core.JsonEncoding detectEncoding() throws java.io.IOException {
        /*
            r7 = this;
            r0 = 4
            boolean r1 = r7.ensureLoaded(r0)
            r2 = 2
            r3 = 1
            if (r1 == 0) goto L_0x0041
            byte[] r1 = r7._inputBuffer
            int r4 = r7._inputPtr
            byte r5 = r1[r4]
            int r5 = r5 << 24
            int r6 = r4 + 1
            byte r6 = r1[r6]
            r6 = r6 & 255(0xff, float:3.57E-43)
            int r6 = r6 << 16
            r5 = r5 | r6
            int r6 = r4 + 2
            byte r6 = r1[r6]
            r6 = r6 & 255(0xff, float:3.57E-43)
            int r6 = r6 << 8
            r5 = r5 | r6
            int r4 = r4 + 3
            byte r1 = r1[r4]
            r1 = r1 & 255(0xff, float:3.57E-43)
            r1 = r1 | r5
            boolean r4 = r7.handleBOM(r1)
            if (r4 == 0) goto L_0x0031
            goto L_0x005d
        L_0x0031:
            boolean r4 = r7.checkUTF32(r1)
            if (r4 == 0) goto L_0x0038
            goto L_0x005d
        L_0x0038:
            int r1 = r1 >>> 16
            boolean r1 = r7.checkUTF16(r1)
            if (r1 == 0) goto L_0x0084
            goto L_0x005d
        L_0x0041:
            boolean r1 = r7.ensureLoaded(r2)
            if (r1 == 0) goto L_0x0084
            byte[] r1 = r7._inputBuffer
            int r4 = r7._inputPtr
            byte r5 = r1[r4]
            r5 = r5 & 255(0xff, float:3.57E-43)
            int r5 = r5 << 8
            int r4 = r4 + r3
            byte r1 = r1[r4]
            r1 = r1 & 255(0xff, float:3.57E-43)
            r1 = r1 | r5
            boolean r1 = r7.checkUTF16(r1)
            if (r1 == 0) goto L_0x0084
        L_0x005d:
            int r1 = r7._bytesPerChar
            if (r1 == r3) goto L_0x0081
            if (r1 == r2) goto L_0x0077
            if (r1 != r0) goto L_0x006f
            boolean r0 = r7._bigEndian
            if (r0 == 0) goto L_0x006c
            com.fasterxml.jackson.core.JsonEncoding r0 = com.fasterxml.jackson.core.JsonEncoding.UTF32_BE
            goto L_0x0086
        L_0x006c:
            com.fasterxml.jackson.core.JsonEncoding r0 = com.fasterxml.jackson.core.JsonEncoding.UTF32_LE
            goto L_0x0086
        L_0x006f:
            java.lang.RuntimeException r7 = new java.lang.RuntimeException
            java.lang.String r0 = "Internal error"
            r7.<init>(r0)
            throw r7
        L_0x0077:
            boolean r0 = r7._bigEndian
            if (r0 == 0) goto L_0x007e
            com.fasterxml.jackson.core.JsonEncoding r0 = com.fasterxml.jackson.core.JsonEncoding.UTF16_BE
            goto L_0x0086
        L_0x007e:
            com.fasterxml.jackson.core.JsonEncoding r0 = com.fasterxml.jackson.core.JsonEncoding.UTF16_LE
            goto L_0x0086
        L_0x0081:
            com.fasterxml.jackson.core.JsonEncoding r0 = com.fasterxml.jackson.core.JsonEncoding.UTF8
            goto L_0x0086
        L_0x0084:
            com.fasterxml.jackson.core.JsonEncoding r0 = com.fasterxml.jackson.core.JsonEncoding.UTF8
        L_0x0086:
            com.fasterxml.jackson.core.io.IOContext r7 = r7._context
            r7.setEncoding(r0)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.fasterxml.jackson.core.json.ByteSourceJsonBootstrapper.detectEncoding():com.fasterxml.jackson.core.JsonEncoding");
    }

    public boolean ensureLoaded(int i3) throws IOException {
        int i4;
        int i5 = this._inputEnd - this._inputPtr;
        while (i5 < i3) {
            InputStream inputStream = this._in;
            if (inputStream == null) {
                i4 = -1;
            } else {
                byte[] bArr = this._inputBuffer;
                int i6 = this._inputEnd;
                i4 = inputStream.read(bArr, i6, bArr.length - i6);
            }
            if (i4 < 1) {
                return false;
            }
            this._inputEnd += i4;
            i5 += i4;
        }
        return true;
    }

    private static int skipSpace(InputAccessor inputAccessor, byte b3) throws IOException {
        while (true) {
            byte b4 = b3 & 255;
            if (b4 != 32 && b4 != 13 && b4 != 10 && b4 != 9) {
                return b4;
            }
            if (!inputAccessor.hasMoreBytes()) {
                return -1;
            }
            b3 = inputAccessor.nextByte();
        }
    }

    public ByteSourceJsonBootstrapper(IOContext iOContext, byte[] bArr, int i3, int i4) {
        this._context = iOContext;
        this._in = null;
        this._inputBuffer = bArr;
        this._inputPtr = i3;
        this._inputEnd = i3 + i4;
        this._bufferRecyclable = false;
    }
}
