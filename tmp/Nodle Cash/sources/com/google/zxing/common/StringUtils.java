package com.google.zxing.common;

import com.google.zxing.DecodeHintType;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.charset.UnsupportedCharsetException;
import java.util.Map;

public final class StringUtils {
    private static final boolean ASSUME_SHIFT_JIS;
    private static final Charset EUC_JP;
    public static final String GB2312 = "GB2312";
    public static final Charset GB2312_CHARSET;
    private static final Charset PLATFORM_DEFAULT_ENCODING = Charset.defaultCharset();
    public static final String SHIFT_JIS = "SJIS";
    public static final Charset SHIFT_JIS_CHARSET;

    static {
        Charset charset;
        Charset charset2;
        Charset charset3 = null;
        try {
            charset = Charset.forName(SHIFT_JIS);
        } catch (UnsupportedCharsetException unused) {
            charset = null;
        }
        SHIFT_JIS_CHARSET = charset;
        try {
            charset2 = Charset.forName(GB2312);
        } catch (UnsupportedCharsetException unused2) {
            charset2 = null;
        }
        GB2312_CHARSET = charset2;
        try {
            charset3 = Charset.forName("EUC_JP");
        } catch (UnsupportedCharsetException unused3) {
        }
        EUC_JP = charset3;
        Charset charset4 = SHIFT_JIS_CHARSET;
        ASSUME_SHIFT_JIS = (charset4 != null && charset4.equals(PLATFORM_DEFAULT_ENCODING)) || (charset3 != null && charset3.equals(PLATFORM_DEFAULT_ENCODING));
    }

    private StringUtils() {
    }

    /* JADX WARNING: Removed duplicated region for block: B:148:0x0117 A[SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:62:0x00b3  */
    /* JADX WARNING: Removed duplicated region for block: B:75:0x00cd  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.nio.charset.Charset guessCharset(byte[] r21, java.util.Map<com.google.zxing.DecodeHintType, ?> r22) {
        /*
            r0 = r21
            r1 = r22
            if (r1 == 0) goto L_0x001b
            com.google.zxing.DecodeHintType r2 = com.google.zxing.DecodeHintType.CHARACTER_SET
            boolean r3 = r1.containsKey(r2)
            if (r3 == 0) goto L_0x001b
            java.lang.Object r0 = r1.get(r2)
            java.lang.String r0 = r0.toString()
            java.nio.charset.Charset r0 = java.nio.charset.Charset.forName(r0)
            return r0
        L_0x001b:
            int r1 = r0.length
            r2 = 2
            r3 = 1
            r4 = 0
            if (r1 <= r2) goto L_0x0034
            byte r1 = r0[r4]
            r5 = -2
            r6 = -1
            if (r1 != r5) goto L_0x002b
            byte r7 = r0[r3]
            if (r7 == r6) goto L_0x0031
        L_0x002b:
            if (r1 != r6) goto L_0x0034
            byte r1 = r0[r3]
            if (r1 != r5) goto L_0x0034
        L_0x0031:
            java.nio.charset.Charset r0 = java.nio.charset.StandardCharsets.UTF_16
            return r0
        L_0x0034:
            int r1 = r0.length
            java.nio.charset.Charset r5 = SHIFT_JIS_CHARSET
            if (r5 == 0) goto L_0x003b
            r5 = r3
            goto L_0x003c
        L_0x003b:
            r5 = r4
        L_0x003c:
            int r6 = r0.length
            r7 = 3
            if (r6 <= r7) goto L_0x0054
            byte r6 = r0[r4]
            r8 = -17
            if (r6 != r8) goto L_0x0054
            byte r6 = r0[r3]
            r8 = -69
            if (r6 != r8) goto L_0x0054
            byte r6 = r0[r2]
            r8 = -65
            if (r6 != r8) goto L_0x0054
            r6 = r3
            goto L_0x0055
        L_0x0054:
            r6 = r4
        L_0x0055:
            r2 = r4
            r9 = r2
            r10 = r9
            r11 = r10
            r12 = r11
            r13 = r12
            r14 = r13
            r15 = r14
            r16 = r15
            r17 = r16
            r18 = r17
            r8 = r5
            r5 = r3
        L_0x0065:
            if (r9 >= r1) goto L_0x006e
            if (r3 != 0) goto L_0x0072
            if (r8 != 0) goto L_0x0072
            if (r5 == 0) goto L_0x006e
            goto L_0x0072
        L_0x006e:
            r19 = r1
            goto L_0x0120
        L_0x0072:
            byte r7 = r0[r9]
            r0 = r7 & 255(0xff, float:3.57E-43)
            if (r5 == 0) goto L_0x0084
            if (r10 <= 0) goto L_0x0087
            r7 = r7 & 128(0x80, float:1.794E-43)
            if (r7 != 0) goto L_0x0082
            r19 = r1
        L_0x0080:
            r5 = 0
            goto L_0x00ad
        L_0x0082:
            int r10 = r10 + -1
        L_0x0084:
            r19 = r1
            goto L_0x00ad
        L_0x0087:
            r19 = r1
            r1 = r7 & 128(0x80, float:1.794E-43)
            if (r1 == 0) goto L_0x00ad
            r1 = r7 & 64
            if (r1 != 0) goto L_0x0092
            goto L_0x0080
        L_0x0092:
            int r1 = r10 + 1
            r20 = r7 & 32
            if (r20 != 0) goto L_0x009c
            int r12 = r12 + 1
        L_0x009a:
            r10 = r1
            goto L_0x00ad
        L_0x009c:
            int r1 = r10 + 2
            r20 = r7 & 16
            if (r20 != 0) goto L_0x00a5
            int r13 = r13 + 1
            goto L_0x009a
        L_0x00a5:
            int r10 = r10 + 3
            r1 = r7 & 8
            if (r1 != 0) goto L_0x0080
            int r14 = r14 + 1
        L_0x00ad:
            r1 = 160(0xa0, float:2.24E-43)
            r7 = 127(0x7f, float:1.78E-43)
            if (r3 == 0) goto L_0x00cb
            if (r0 <= r7) goto L_0x00b9
            if (r0 >= r1) goto L_0x00b9
            r3 = 0
            goto L_0x00cb
        L_0x00b9:
            r1 = 159(0x9f, float:2.23E-43)
            if (r0 <= r1) goto L_0x00cb
            r1 = 192(0xc0, float:2.69E-43)
            if (r0 < r1) goto L_0x00c9
            r1 = 215(0xd7, float:3.01E-43)
            if (r0 == r1) goto L_0x00c9
            r1 = 247(0xf7, float:3.46E-43)
            if (r0 != r1) goto L_0x00cb
        L_0x00c9:
            int r16 = r16 + 1
        L_0x00cb:
            if (r8 == 0) goto L_0x0117
            if (r11 <= 0) goto L_0x00df
            r1 = 64
            if (r0 < r1) goto L_0x00dd
            if (r0 == r7) goto L_0x00dd
            r1 = 252(0xfc, float:3.53E-43)
            if (r0 <= r1) goto L_0x00da
            goto L_0x00dd
        L_0x00da:
            int r11 = r11 + -1
            goto L_0x0117
        L_0x00dd:
            r8 = 0
            goto L_0x0117
        L_0x00df:
            r1 = 128(0x80, float:1.794E-43)
            if (r0 == r1) goto L_0x00dd
            r1 = 160(0xa0, float:2.24E-43)
            if (r0 == r1) goto L_0x00dd
            r7 = 239(0xef, float:3.35E-43)
            if (r0 <= r7) goto L_0x00ec
            goto L_0x00dd
        L_0x00ec:
            if (r0 <= r1) goto L_0x0101
            r1 = 224(0xe0, float:3.14E-43)
            if (r0 >= r1) goto L_0x0101
            int r2 = r2 + 1
            int r0 = r18 + 1
            if (r0 <= r15) goto L_0x00fe
            r15 = r0
            r18 = r15
        L_0x00fb:
            r17 = 0
            goto L_0x0117
        L_0x00fe:
            r18 = r0
            goto L_0x00fb
        L_0x0101:
            r1 = 127(0x7f, float:1.78E-43)
            if (r0 <= r1) goto L_0x0114
            int r11 = r11 + 1
            int r0 = r17 + 1
            if (r0 <= r4) goto L_0x0111
            r4 = r0
            r17 = r4
        L_0x010e:
            r18 = 0
            goto L_0x0117
        L_0x0111:
            r17 = r0
            goto L_0x010e
        L_0x0114:
            r17 = 0
            goto L_0x010e
        L_0x0117:
            int r9 = r9 + 1
            r0 = r21
            r1 = r19
            r7 = 3
            goto L_0x0065
        L_0x0120:
            if (r5 == 0) goto L_0x0125
            if (r10 <= 0) goto L_0x0125
            r5 = 0
        L_0x0125:
            if (r8 == 0) goto L_0x012a
            if (r11 <= 0) goto L_0x012a
            r8 = 0
        L_0x012a:
            if (r5 == 0) goto L_0x0135
            if (r6 != 0) goto L_0x0132
            int r12 = r12 + r13
            int r12 = r12 + r14
            if (r12 <= 0) goto L_0x0135
        L_0x0132:
            java.nio.charset.Charset r0 = java.nio.charset.StandardCharsets.UTF_8
            return r0
        L_0x0135:
            if (r8 == 0) goto L_0x0143
            boolean r0 = ASSUME_SHIFT_JIS
            if (r0 != 0) goto L_0x0140
            r0 = 3
            if (r15 >= r0) goto L_0x0140
            if (r4 < r0) goto L_0x0143
        L_0x0140:
            java.nio.charset.Charset r0 = SHIFT_JIS_CHARSET
            return r0
        L_0x0143:
            if (r3 == 0) goto L_0x0158
            if (r8 == 0) goto L_0x0158
            r0 = 2
            if (r15 != r0) goto L_0x014c
            if (r2 == r0) goto L_0x0152
        L_0x014c:
            int r0 = r16 * 10
            r1 = r19
            if (r0 < r1) goto L_0x0155
        L_0x0152:
            java.nio.charset.Charset r0 = SHIFT_JIS_CHARSET
            goto L_0x0157
        L_0x0155:
            java.nio.charset.Charset r0 = java.nio.charset.StandardCharsets.ISO_8859_1
        L_0x0157:
            return r0
        L_0x0158:
            if (r3 == 0) goto L_0x015d
            java.nio.charset.Charset r0 = java.nio.charset.StandardCharsets.ISO_8859_1
            return r0
        L_0x015d:
            if (r8 == 0) goto L_0x0162
            java.nio.charset.Charset r0 = SHIFT_JIS_CHARSET
            return r0
        L_0x0162:
            if (r5 == 0) goto L_0x0167
            java.nio.charset.Charset r0 = java.nio.charset.StandardCharsets.UTF_8
            return r0
        L_0x0167:
            java.nio.charset.Charset r0 = PLATFORM_DEFAULT_ENCODING
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.zxing.common.StringUtils.guessCharset(byte[], java.util.Map):java.nio.charset.Charset");
    }

    public static String guessEncoding(byte[] bArr, Map<DecodeHintType, ?> map) {
        Charset guessCharset = guessCharset(bArr, map);
        return guessCharset.equals(SHIFT_JIS_CHARSET) ? SHIFT_JIS : guessCharset.equals(StandardCharsets.UTF_8) ? "UTF8" : guessCharset.equals(StandardCharsets.ISO_8859_1) ? "ISO8859_1" : guessCharset.name();
    }
}
