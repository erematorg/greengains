package com.fasterxml.jackson.core.io;

import com.fasterxml.jackson.core.util.ByteArrayBuilder;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;

public final class JsonStringEncoder {
    private static final byte[] HB = CharTypes.copyHexBytes(true);
    private static final char[] HC = CharTypes.copyHexChars(true);
    static final int MAX_BYTE_BUFFER_SIZE = 32000;
    static final int MAX_CHAR_BUFFER_SIZE = 32000;
    static final int MIN_BYTE_BUFFER_SIZE = 24;
    static final int MIN_CHAR_BUFFER_SIZE = 16;
    private static final int SURR1_FIRST = 55296;
    private static final int SURR1_LAST = 56319;
    private static final int SURR2_FIRST = 56320;
    private static final int SURR2_LAST = 57343;
    private static final JsonStringEncoder instance = new JsonStringEncoder();

    private int _appendByte(int i3, int i4, ByteArrayBuilder byteArrayBuilder, int i5) {
        byteArrayBuilder.setCurrentSegmentLength(i5);
        byteArrayBuilder.append(92);
        if (i4 < 0) {
            byteArrayBuilder.append(117);
            if (i3 > 255) {
                byte[] bArr = HB;
                byteArrayBuilder.append(bArr[i3 >> 12]);
                byteArrayBuilder.append(bArr[(i3 >> 8) & 15]);
                i3 &= 255;
            } else {
                byteArrayBuilder.append(48);
                byteArrayBuilder.append(48);
            }
            byte[] bArr2 = HB;
            byteArrayBuilder.append(bArr2[i3 >> 4]);
            byteArrayBuilder.append(bArr2[i3 & 15]);
        } else {
            byteArrayBuilder.append((byte) i4);
        }
        return byteArrayBuilder.getCurrentSegmentLength();
    }

    private int _appendNamed(int i3, char[] cArr) {
        cArr[1] = (char) i3;
        return 2;
    }

    private int _appendNumeric(int i3, char[] cArr) {
        cArr[1] = AbstractJsonLexerKt.UNICODE_ESC;
        char[] cArr2 = HC;
        cArr[4] = cArr2[i3 >> 4];
        cArr[5] = cArr2[i3 & 15];
        return 6;
    }

    private static int _convert(int i3, int i4) {
        if (i4 < 56320 || i4 > 57343) {
            throw new IllegalArgumentException("Broken surrogate pair: first char 0x" + Integer.toHexString(i3) + ", second 0x" + Integer.toHexString(i4) + "; illegal combination");
        }
        return (i4 - 56320) + ((i3 - 55296) << 10) + 65536;
    }

    private static void _illegal(int i3) {
        throw new IllegalArgumentException(UTF8Writer.illegalSurrogateDesc(i3));
    }

    public static int _initialByteBufSize(int i3) {
        return Math.min(Math.max(24, i3 + 6 + (i3 >> 1)), 32000);
    }

    public static int _initialCharBufSize(int i3) {
        return Math.min(Math.max(16, Math.min((i3 >> 3) + 6, 1000) + i3), 32000);
    }

    private char[] _qbuf() {
        char[] cArr = new char[6];
        cArr[0] = AbstractJsonLexerKt.STRING_ESC;
        cArr[2] = '0';
        cArr[3] = '0';
        return cArr;
    }

    public static JsonStringEncoder getInstance() {
        return instance;
    }

    /* JADX WARNING: Removed duplicated region for block: B:45:0x00df  */
    /* JADX WARNING: Removed duplicated region for block: B:55:0x00e8 A[SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public byte[] encodeAsUTF8(java.lang.String r11) {
        /*
            r10 = this;
            int r10 = r11.length()
            int r0 = _initialByteBufSize(r10)
            byte[] r1 = new byte[r0]
            r2 = 0
            r3 = 0
            r4 = r2
            r5 = r4
        L_0x000e:
            if (r4 >= r10) goto L_0x00f4
            int r6 = r4 + 1
            char r4 = r11.charAt(r4)
        L_0x0016:
            r7 = 127(0x7f, float:1.78E-43)
            if (r4 > r7) goto L_0x003f
            if (r5 < r0) goto L_0x002b
            if (r3 != 0) goto L_0x0022
            com.fasterxml.jackson.core.util.ByteArrayBuilder r3 = com.fasterxml.jackson.core.util.ByteArrayBuilder.fromInitial(r1, r5)
        L_0x0022:
            byte[] r0 = r3.finishCurrentSegment()
            int r1 = r0.length
            r5 = r2
            r9 = r1
            r1 = r0
            r0 = r9
        L_0x002b:
            int r7 = r5 + 1
            byte r4 = (byte) r4
            r1[r5] = r4
            if (r6 < r10) goto L_0x0035
            r5 = r7
            goto L_0x00f4
        L_0x0035:
            int r4 = r6 + 1
            char r5 = r11.charAt(r6)
            r6 = r4
            r4 = r5
            r5 = r7
            goto L_0x0016
        L_0x003f:
            if (r3 != 0) goto L_0x0045
            com.fasterxml.jackson.core.util.ByteArrayBuilder r3 = com.fasterxml.jackson.core.util.ByteArrayBuilder.fromInitial(r1, r5)
        L_0x0045:
            if (r5 < r0) goto L_0x004d
            byte[] r1 = r3.finishCurrentSegment()
            int r0 = r1.length
            r5 = r2
        L_0x004d:
            r7 = 2048(0x800, float:2.87E-42)
            if (r4 >= r7) goto L_0x005e
            int r7 = r5 + 1
            int r8 = r4 >> 6
            r8 = r8 | 192(0xc0, float:2.69E-43)
            byte r8 = (byte) r8
            r1[r5] = r8
        L_0x005a:
            r5 = r4
            r4 = r6
            goto L_0x00dd
        L_0x005e:
            r7 = 55296(0xd800, float:7.7486E-41)
            if (r4 < r7) goto L_0x00be
            r7 = 57343(0xdfff, float:8.0355E-41)
            if (r4 <= r7) goto L_0x0069
            goto L_0x00be
        L_0x0069:
            r7 = 56319(0xdbff, float:7.892E-41)
            if (r4 <= r7) goto L_0x0071
            _illegal(r4)
        L_0x0071:
            if (r6 < r10) goto L_0x0076
            _illegal(r4)
        L_0x0076:
            int r7 = r6 + 1
            char r6 = r11.charAt(r6)
            int r4 = _convert(r4, r6)
            r6 = 1114111(0x10ffff, float:1.561202E-39)
            if (r4 <= r6) goto L_0x0088
            _illegal(r4)
        L_0x0088:
            int r6 = r5 + 1
            int r8 = r4 >> 18
            r8 = r8 | 240(0xf0, float:3.36E-43)
            byte r8 = (byte) r8
            r1[r5] = r8
            if (r6 < r0) goto L_0x0099
            byte[] r1 = r3.finishCurrentSegment()
            int r0 = r1.length
            r6 = r2
        L_0x0099:
            int r5 = r6 + 1
            int r8 = r4 >> 12
            r8 = r8 & 63
            r8 = r8 | 128(0x80, float:1.794E-43)
            byte r8 = (byte) r8
            r1[r6] = r8
            if (r5 < r0) goto L_0x00af
            byte[] r0 = r3.finishCurrentSegment()
            int r1 = r0.length
            r5 = r2
            r9 = r1
            r1 = r0
            r0 = r9
        L_0x00af:
            int r6 = r5 + 1
            int r8 = r4 >> 6
            r8 = r8 & 63
            r8 = r8 | 128(0x80, float:1.794E-43)
            byte r8 = (byte) r8
            r1[r5] = r8
            r5 = r4
            r4 = r7
            r7 = r6
            goto L_0x00dd
        L_0x00be:
            int r7 = r5 + 1
            int r8 = r4 >> 12
            r8 = r8 | 224(0xe0, float:3.14E-43)
            byte r8 = (byte) r8
            r1[r5] = r8
            if (r7 < r0) goto L_0x00cf
            byte[] r1 = r3.finishCurrentSegment()
            int r0 = r1.length
            r7 = r2
        L_0x00cf:
            int r5 = r7 + 1
            int r8 = r4 >> 6
            r8 = r8 & 63
            r8 = r8 | 128(0x80, float:1.794E-43)
            byte r8 = (byte) r8
            r1[r7] = r8
            r7 = r5
            goto L_0x005a
        L_0x00dd:
            if (r7 < r0) goto L_0x00e8
            byte[] r0 = r3.finishCurrentSegment()
            int r1 = r0.length
            r7 = r2
            r9 = r1
            r1 = r0
            r0 = r9
        L_0x00e8:
            int r6 = r7 + 1
            r5 = r5 & 63
            r5 = r5 | 128(0x80, float:1.794E-43)
            byte r5 = (byte) r5
            r1[r7] = r5
            r5 = r6
            goto L_0x000e
        L_0x00f4:
            if (r3 != 0) goto L_0x00fb
            byte[] r10 = java.util.Arrays.copyOfRange(r1, r2, r5)
            return r10
        L_0x00fb:
            byte[] r10 = r3.completeAndCoalesce(r5)
            return r10
        */
        throw new UnsupportedOperationException("Method not decompiled: com.fasterxml.jackson.core.io.JsonStringEncoder.encodeAsUTF8(java.lang.String):byte[]");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x0030, code lost:
        r7 = _appendNumeric(r7, r6);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0035, code lost:
        r7 = _appendNamed(r10, r6);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0039, code lost:
        r10 = r8 + r7;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x003c, code lost:
        if (r10 <= r1.length) goto L_0x0055;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x003e, code lost:
        r10 = r1.length - r8;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0040, code lost:
        if (r10 <= 0) goto L_0x0045;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0042, code lost:
        java.lang.System.arraycopy(r6, 0, r1, r8, r10);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0045, code lost:
        if (r5 != null) goto L_0x004b;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0047, code lost:
        r5 = com.fasterxml.jackson.core.util.TextBuffer.fromInitial(r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x004b, code lost:
        r1 = r5.finishCurrentSegment();
        r7 = r7 - r10;
        java.lang.System.arraycopy(r6, r10, r1, 0, r7);
        r8 = r7;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x0055, code lost:
        java.lang.System.arraycopy(r6, 0, r1, r8, r7);
        r8 = r10;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:6:0x0020, code lost:
        if (r6 != null) goto L_0x0026;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:7:0x0022, code lost:
        r6 = _qbuf();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x0026, code lost:
        r9 = r7 + 1;
        r7 = r13.charAt(r7);
        r10 = r2[r7];
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x002e, code lost:
        if (r10 >= 0) goto L_0x0035;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public char[] quoteAsString(java.lang.String r13) {
        /*
            r12 = this;
            int r0 = r13.length()
            int r1 = _initialCharBufSize(r0)
            char[] r1 = new char[r1]
            int[] r2 = com.fasterxml.jackson.core.io.CharTypes.get7BitOutputEscapes()
            int r3 = r2.length
            r4 = 0
            r5 = 0
            r7 = r4
            r8 = r7
            r6 = r5
        L_0x0014:
            if (r7 >= r0) goto L_0x0075
        L_0x0016:
            char r9 = r13.charAt(r7)
            if (r9 >= r3) goto L_0x005b
            r10 = r2[r9]
            if (r10 == 0) goto L_0x005b
            if (r6 != 0) goto L_0x0026
            char[] r6 = r12._qbuf()
        L_0x0026:
            int r9 = r7 + 1
            char r7 = r13.charAt(r7)
            r10 = r2[r7]
            if (r10 >= 0) goto L_0x0035
            int r7 = r12._appendNumeric(r7, r6)
            goto L_0x0039
        L_0x0035:
            int r7 = r12._appendNamed(r10, r6)
        L_0x0039:
            int r10 = r8 + r7
            int r11 = r1.length
            if (r10 <= r11) goto L_0x0055
            int r10 = r1.length
            int r10 = r10 - r8
            if (r10 <= 0) goto L_0x0045
            java.lang.System.arraycopy(r6, r4, r1, r8, r10)
        L_0x0045:
            if (r5 != 0) goto L_0x004b
            com.fasterxml.jackson.core.util.TextBuffer r5 = com.fasterxml.jackson.core.util.TextBuffer.fromInitial(r1)
        L_0x004b:
            char[] r1 = r5.finishCurrentSegment()
            int r7 = r7 - r10
            java.lang.System.arraycopy(r6, r10, r1, r4, r7)
            r8 = r7
            goto L_0x0059
        L_0x0055:
            java.lang.System.arraycopy(r6, r4, r1, r8, r7)
            r8 = r10
        L_0x0059:
            r7 = r9
            goto L_0x0014
        L_0x005b:
            int r10 = r1.length
            if (r8 < r10) goto L_0x0069
            if (r5 != 0) goto L_0x0064
            com.fasterxml.jackson.core.util.TextBuffer r5 = com.fasterxml.jackson.core.util.TextBuffer.fromInitial(r1)
        L_0x0064:
            char[] r1 = r5.finishCurrentSegment()
            r8 = r4
        L_0x0069:
            int r10 = r8 + 1
            r1[r8] = r9
            int r7 = r7 + 1
            if (r7 < r0) goto L_0x0073
            r8 = r10
            goto L_0x0075
        L_0x0073:
            r8 = r10
            goto L_0x0016
        L_0x0075:
            if (r5 != 0) goto L_0x007c
            char[] r12 = java.util.Arrays.copyOfRange(r1, r4, r8)
            return r12
        L_0x007c:
            r5.setCurrentLength(r8)
            char[] r12 = r5.contentsAsArray()
            return r12
        */
        throw new UnsupportedOperationException("Method not decompiled: com.fasterxml.jackson.core.io.JsonStringEncoder.quoteAsString(java.lang.String):char[]");
    }

    /* JADX WARNING: Removed duplicated region for block: B:17:0x003f  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public byte[] quoteAsUTF8(java.lang.String r12) {
        /*
            r11 = this;
            int r0 = r12.length()
            int r1 = _initialByteBufSize(r0)
            byte[] r1 = new byte[r1]
            r2 = 0
            r3 = 0
            r4 = r2
            r5 = r4
        L_0x000e:
            if (r4 >= r0) goto L_0x0106
            int[] r6 = com.fasterxml.jackson.core.io.CharTypes.get7BitOutputEscapes()
        L_0x0014:
            char r7 = r12.charAt(r4)
            r8 = 127(0x7f, float:1.78E-43)
            if (r7 > r8) goto L_0x003d
            r9 = r6[r7]
            if (r9 == 0) goto L_0x0021
            goto L_0x003d
        L_0x0021:
            int r8 = r1.length
            if (r5 < r8) goto L_0x002f
            if (r3 != 0) goto L_0x002a
            com.fasterxml.jackson.core.util.ByteArrayBuilder r3 = com.fasterxml.jackson.core.util.ByteArrayBuilder.fromInitial(r1, r5)
        L_0x002a:
            byte[] r1 = r3.finishCurrentSegment()
            r5 = r2
        L_0x002f:
            int r8 = r5 + 1
            byte r7 = (byte) r7
            r1[r5] = r7
            int r4 = r4 + 1
            if (r4 < r0) goto L_0x003b
            r5 = r8
            goto L_0x0106
        L_0x003b:
            r5 = r8
            goto L_0x0014
        L_0x003d:
            if (r3 != 0) goto L_0x0043
            com.fasterxml.jackson.core.util.ByteArrayBuilder r3 = com.fasterxml.jackson.core.util.ByteArrayBuilder.fromInitial(r1, r5)
        L_0x0043:
            int r7 = r1.length
            if (r5 < r7) goto L_0x004b
            byte[] r1 = r3.finishCurrentSegment()
            r5 = r2
        L_0x004b:
            int r7 = r4 + 1
            char r9 = r12.charAt(r4)
            if (r9 > r8) goto L_0x005f
            r1 = r6[r9]
            int r5 = r11._appendByte(r9, r1, r3, r5)
            byte[] r1 = r3.getCurrentSegment()
        L_0x005d:
            r4 = r7
            goto L_0x000e
        L_0x005f:
            r6 = 2047(0x7ff, float:2.868E-42)
            if (r9 > r6) goto L_0x0072
            int r4 = r5 + 1
            int r6 = r9 >> 6
            r6 = r6 | 192(0xc0, float:2.69E-43)
            byte r6 = (byte) r6
            r1[r5] = r6
            r5 = r9 & 63
            r5 = r5 | 128(0x80, float:1.794E-43)
            goto L_0x00f6
        L_0x0072:
            r6 = 55296(0xd800, float:7.7486E-41)
            if (r9 < r6) goto L_0x00d3
            r6 = 57343(0xdfff, float:8.0355E-41)
            if (r9 <= r6) goto L_0x007d
            goto L_0x00d3
        L_0x007d:
            r6 = 56319(0xdbff, float:7.892E-41)
            if (r9 <= r6) goto L_0x0085
            _illegal(r9)
        L_0x0085:
            if (r7 < r0) goto L_0x008a
            _illegal(r9)
        L_0x008a:
            int r4 = r4 + 2
            char r6 = r12.charAt(r7)
            int r6 = _convert(r9, r6)
            r7 = 1114111(0x10ffff, float:1.561202E-39)
            if (r6 <= r7) goto L_0x009c
            _illegal(r6)
        L_0x009c:
            int r7 = r5 + 1
            int r8 = r6 >> 18
            r8 = r8 | 240(0xf0, float:3.36E-43)
            byte r8 = (byte) r8
            r1[r5] = r8
            int r5 = r1.length
            if (r7 < r5) goto L_0x00ad
            byte[] r1 = r3.finishCurrentSegment()
            r7 = r2
        L_0x00ad:
            int r5 = r7 + 1
            int r8 = r6 >> 12
            r8 = r8 & 63
            r8 = r8 | 128(0x80, float:1.794E-43)
            byte r8 = (byte) r8
            r1[r7] = r8
            int r7 = r1.length
            if (r5 < r7) goto L_0x00c0
            byte[] r1 = r3.finishCurrentSegment()
            r5 = r2
        L_0x00c0:
            int r7 = r5 + 1
            int r8 = r6 >> 6
            r8 = r8 & 63
            r8 = r8 | 128(0x80, float:1.794E-43)
            byte r8 = (byte) r8
            r1[r5] = r8
            r5 = r6 & 63
            r5 = r5 | 128(0x80, float:1.794E-43)
            r10 = r7
            r7 = r4
        L_0x00d1:
            r4 = r10
            goto L_0x00f6
        L_0x00d3:
            int r4 = r5 + 1
            int r6 = r9 >> 12
            r6 = r6 | 224(0xe0, float:3.14E-43)
            byte r6 = (byte) r6
            r1[r5] = r6
            int r5 = r1.length
            if (r4 < r5) goto L_0x00e4
            byte[] r1 = r3.finishCurrentSegment()
            r4 = r2
        L_0x00e4:
            int r5 = r4 + 1
            int r6 = r9 >> 6
            r6 = r6 & 63
            r6 = r6 | 128(0x80, float:1.794E-43)
            byte r6 = (byte) r6
            r1[r4] = r6
            r4 = r9 & 63
            r4 = r4 | 128(0x80, float:1.794E-43)
            r10 = r5
            r5 = r4
            goto L_0x00d1
        L_0x00f6:
            int r6 = r1.length
            if (r4 < r6) goto L_0x00fe
            byte[] r1 = r3.finishCurrentSegment()
            r4 = r2
        L_0x00fe:
            int r6 = r4 + 1
            byte r5 = (byte) r5
            r1[r4] = r5
            r5 = r6
            goto L_0x005d
        L_0x0106:
            if (r3 != 0) goto L_0x010d
            byte[] r11 = java.util.Arrays.copyOfRange(r1, r2, r5)
            return r11
        L_0x010d:
            byte[] r11 = r3.completeAndCoalesce(r5)
            return r11
        */
        throw new UnsupportedOperationException("Method not decompiled: com.fasterxml.jackson.core.io.JsonStringEncoder.quoteAsUTF8(java.lang.String):byte[]");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x002b, code lost:
        if (r6 != null) goto L_0x0031;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:11:0x002d, code lost:
        r6 = _qbuf();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0031, code lost:
        r9 = r7 + 1;
        r7 = r13.charAt(r7);
        r10 = r2[r7];
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0039, code lost:
        if (r10 >= 0) goto L_0x0040;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x003b, code lost:
        r7 = _appendNumeric(r7, r6);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0040, code lost:
        r7 = _appendNamed(r10, r6);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0044, code lost:
        r10 = r8 + r7;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0047, code lost:
        if (r10 <= r1.length) goto L_0x0060;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0049, code lost:
        r10 = r1.length - r8;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x004b, code lost:
        if (r10 <= 0) goto L_0x0050;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x004d, code lost:
        java.lang.System.arraycopy(r6, 0, r1, r8, r10);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x0050, code lost:
        if (r4 != null) goto L_0x0056;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x0052, code lost:
        r4 = com.fasterxml.jackson.core.util.TextBuffer.fromInitial(r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x0056, code lost:
        r1 = r4.finishCurrentSegment();
        r7 = r7 - r10;
        java.lang.System.arraycopy(r6, r10, r1, 0, r7);
        r8 = r7;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x0060, code lost:
        java.lang.System.arraycopy(r6, 0, r1, r8, r7);
        r8 = r10;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public char[] quoteAsString(java.lang.CharSequence r13) {
        /*
            r12 = this;
            boolean r0 = r13 instanceof java.lang.String
            if (r0 == 0) goto L_0x000b
            java.lang.String r13 = (java.lang.String) r13
            char[] r12 = r12.quoteAsString((java.lang.String) r13)
            return r12
        L_0x000b:
            int r0 = r13.length()
            int r1 = _initialCharBufSize(r0)
            char[] r1 = new char[r1]
            int[] r2 = com.fasterxml.jackson.core.io.CharTypes.get7BitOutputEscapes()
            int r3 = r2.length
            r4 = 0
            r5 = 0
            r6 = r4
            r7 = r5
            r8 = r7
        L_0x001f:
            if (r7 >= r0) goto L_0x0080
        L_0x0021:
            char r9 = r13.charAt(r7)
            if (r9 >= r3) goto L_0x0066
            r10 = r2[r9]
            if (r10 == 0) goto L_0x0066
            if (r6 != 0) goto L_0x0031
            char[] r6 = r12._qbuf()
        L_0x0031:
            int r9 = r7 + 1
            char r7 = r13.charAt(r7)
            r10 = r2[r7]
            if (r10 >= 0) goto L_0x0040
            int r7 = r12._appendNumeric(r7, r6)
            goto L_0x0044
        L_0x0040:
            int r7 = r12._appendNamed(r10, r6)
        L_0x0044:
            int r10 = r8 + r7
            int r11 = r1.length
            if (r10 <= r11) goto L_0x0060
            int r10 = r1.length
            int r10 = r10 - r8
            if (r10 <= 0) goto L_0x0050
            java.lang.System.arraycopy(r6, r5, r1, r8, r10)
        L_0x0050:
            if (r4 != 0) goto L_0x0056
            com.fasterxml.jackson.core.util.TextBuffer r4 = com.fasterxml.jackson.core.util.TextBuffer.fromInitial(r1)
        L_0x0056:
            char[] r1 = r4.finishCurrentSegment()
            int r7 = r7 - r10
            java.lang.System.arraycopy(r6, r10, r1, r5, r7)
            r8 = r7
            goto L_0x0064
        L_0x0060:
            java.lang.System.arraycopy(r6, r5, r1, r8, r7)
            r8 = r10
        L_0x0064:
            r7 = r9
            goto L_0x001f
        L_0x0066:
            int r10 = r1.length
            if (r8 < r10) goto L_0x0074
            if (r4 != 0) goto L_0x006f
            com.fasterxml.jackson.core.util.TextBuffer r4 = com.fasterxml.jackson.core.util.TextBuffer.fromInitial(r1)
        L_0x006f:
            char[] r1 = r4.finishCurrentSegment()
            r8 = r5
        L_0x0074:
            int r10 = r8 + 1
            r1[r8] = r9
            int r7 = r7 + 1
            if (r7 < r0) goto L_0x007e
            r8 = r10
            goto L_0x0080
        L_0x007e:
            r8 = r10
            goto L_0x0021
        L_0x0080:
            if (r4 != 0) goto L_0x0087
            char[] r12 = java.util.Arrays.copyOfRange(r1, r5, r8)
            return r12
        L_0x0087:
            r4.setCurrentLength(r8)
            char[] r12 = r4.contentsAsArray()
            return r12
        */
        throw new UnsupportedOperationException("Method not decompiled: com.fasterxml.jackson.core.io.JsonStringEncoder.quoteAsString(java.lang.CharSequence):char[]");
    }

    /* JADX WARNING: Removed duplicated region for block: B:45:0x00df  */
    /* JADX WARNING: Removed duplicated region for block: B:55:0x00e8 A[SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public byte[] encodeAsUTF8(java.lang.CharSequence r11) {
        /*
            r10 = this;
            int r10 = r11.length()
            int r0 = _initialByteBufSize(r10)
            byte[] r1 = new byte[r0]
            r2 = 0
            r3 = 0
            r4 = r2
            r5 = r4
        L_0x000e:
            if (r4 >= r10) goto L_0x00f4
            int r6 = r4 + 1
            char r4 = r11.charAt(r4)
        L_0x0016:
            r7 = 127(0x7f, float:1.78E-43)
            if (r4 > r7) goto L_0x003f
            if (r5 < r0) goto L_0x002b
            if (r3 != 0) goto L_0x0022
            com.fasterxml.jackson.core.util.ByteArrayBuilder r3 = com.fasterxml.jackson.core.util.ByteArrayBuilder.fromInitial(r1, r5)
        L_0x0022:
            byte[] r0 = r3.finishCurrentSegment()
            int r1 = r0.length
            r5 = r2
            r9 = r1
            r1 = r0
            r0 = r9
        L_0x002b:
            int r7 = r5 + 1
            byte r4 = (byte) r4
            r1[r5] = r4
            if (r6 < r10) goto L_0x0035
            r5 = r7
            goto L_0x00f4
        L_0x0035:
            int r4 = r6 + 1
            char r5 = r11.charAt(r6)
            r6 = r4
            r4 = r5
            r5 = r7
            goto L_0x0016
        L_0x003f:
            if (r3 != 0) goto L_0x0045
            com.fasterxml.jackson.core.util.ByteArrayBuilder r3 = com.fasterxml.jackson.core.util.ByteArrayBuilder.fromInitial(r1, r5)
        L_0x0045:
            if (r5 < r0) goto L_0x004d
            byte[] r1 = r3.finishCurrentSegment()
            int r0 = r1.length
            r5 = r2
        L_0x004d:
            r7 = 2048(0x800, float:2.87E-42)
            if (r4 >= r7) goto L_0x005e
            int r7 = r5 + 1
            int r8 = r4 >> 6
            r8 = r8 | 192(0xc0, float:2.69E-43)
            byte r8 = (byte) r8
            r1[r5] = r8
        L_0x005a:
            r5 = r4
            r4 = r6
            goto L_0x00dd
        L_0x005e:
            r7 = 55296(0xd800, float:7.7486E-41)
            if (r4 < r7) goto L_0x00be
            r7 = 57343(0xdfff, float:8.0355E-41)
            if (r4 <= r7) goto L_0x0069
            goto L_0x00be
        L_0x0069:
            r7 = 56319(0xdbff, float:7.892E-41)
            if (r4 <= r7) goto L_0x0071
            _illegal(r4)
        L_0x0071:
            if (r6 < r10) goto L_0x0076
            _illegal(r4)
        L_0x0076:
            int r7 = r6 + 1
            char r6 = r11.charAt(r6)
            int r4 = _convert(r4, r6)
            r6 = 1114111(0x10ffff, float:1.561202E-39)
            if (r4 <= r6) goto L_0x0088
            _illegal(r4)
        L_0x0088:
            int r6 = r5 + 1
            int r8 = r4 >> 18
            r8 = r8 | 240(0xf0, float:3.36E-43)
            byte r8 = (byte) r8
            r1[r5] = r8
            if (r6 < r0) goto L_0x0099
            byte[] r1 = r3.finishCurrentSegment()
            int r0 = r1.length
            r6 = r2
        L_0x0099:
            int r5 = r6 + 1
            int r8 = r4 >> 12
            r8 = r8 & 63
            r8 = r8 | 128(0x80, float:1.794E-43)
            byte r8 = (byte) r8
            r1[r6] = r8
            if (r5 < r0) goto L_0x00af
            byte[] r0 = r3.finishCurrentSegment()
            int r1 = r0.length
            r5 = r2
            r9 = r1
            r1 = r0
            r0 = r9
        L_0x00af:
            int r6 = r5 + 1
            int r8 = r4 >> 6
            r8 = r8 & 63
            r8 = r8 | 128(0x80, float:1.794E-43)
            byte r8 = (byte) r8
            r1[r5] = r8
            r5 = r4
            r4 = r7
            r7 = r6
            goto L_0x00dd
        L_0x00be:
            int r7 = r5 + 1
            int r8 = r4 >> 12
            r8 = r8 | 224(0xe0, float:3.14E-43)
            byte r8 = (byte) r8
            r1[r5] = r8
            if (r7 < r0) goto L_0x00cf
            byte[] r1 = r3.finishCurrentSegment()
            int r0 = r1.length
            r7 = r2
        L_0x00cf:
            int r5 = r7 + 1
            int r8 = r4 >> 6
            r8 = r8 & 63
            r8 = r8 | 128(0x80, float:1.794E-43)
            byte r8 = (byte) r8
            r1[r7] = r8
            r7 = r5
            goto L_0x005a
        L_0x00dd:
            if (r7 < r0) goto L_0x00e8
            byte[] r0 = r3.finishCurrentSegment()
            int r1 = r0.length
            r7 = r2
            r9 = r1
            r1 = r0
            r0 = r9
        L_0x00e8:
            int r6 = r7 + 1
            r5 = r5 & 63
            r5 = r5 | 128(0x80, float:1.794E-43)
            byte r5 = (byte) r5
            r1[r7] = r5
            r5 = r6
            goto L_0x000e
        L_0x00f4:
            if (r3 != 0) goto L_0x00fb
            byte[] r10 = java.util.Arrays.copyOfRange(r1, r2, r5)
            return r10
        L_0x00fb:
            byte[] r10 = r3.completeAndCoalesce(r5)
            return r10
        */
        throw new UnsupportedOperationException("Method not decompiled: com.fasterxml.jackson.core.io.JsonStringEncoder.encodeAsUTF8(java.lang.CharSequence):byte[]");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x0028, code lost:
        r5 = _appendNumeric(r5, r4);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:11:0x002d, code lost:
        r5 = _appendNamed(r7, r4);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0031, code lost:
        r10.append(r4, 0, r5);
        r5 = r6;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:6:0x0018, code lost:
        if (r4 != null) goto L_0x001e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:7:0x001a, code lost:
        r4 = _qbuf();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x001e, code lost:
        r6 = r5 + 1;
        r5 = r9.charAt(r5);
        r7 = r0[r5];
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x0026, code lost:
        if (r7 >= 0) goto L_0x002d;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void quoteAsString(java.lang.CharSequence r9, java.lang.StringBuilder r10) {
        /*
            r8 = this;
            int[] r0 = com.fasterxml.jackson.core.io.CharTypes.get7BitOutputEscapes()
            int r1 = r0.length
            int r2 = r9.length()
            r3 = 0
            r4 = 0
            r5 = r3
        L_0x000c:
            if (r5 >= r2) goto L_0x003d
        L_0x000e:
            char r6 = r9.charAt(r5)
            if (r6 >= r1) goto L_0x0036
            r7 = r0[r6]
            if (r7 == 0) goto L_0x0036
            if (r4 != 0) goto L_0x001e
            char[] r4 = r8._qbuf()
        L_0x001e:
            int r6 = r5 + 1
            char r5 = r9.charAt(r5)
            r7 = r0[r5]
            if (r7 >= 0) goto L_0x002d
            int r5 = r8._appendNumeric(r5, r4)
            goto L_0x0031
        L_0x002d:
            int r5 = r8._appendNamed(r7, r4)
        L_0x0031:
            r10.append(r4, r3, r5)
            r5 = r6
            goto L_0x000c
        L_0x0036:
            r10.append(r6)
            int r5 = r5 + 1
            if (r5 < r2) goto L_0x000e
        L_0x003d:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.fasterxml.jackson.core.io.JsonStringEncoder.quoteAsString(java.lang.CharSequence, java.lang.StringBuilder):void");
    }
}
