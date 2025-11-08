package com.google.zxing.qrcode.decoder;

import com.google.zxing.DecodeHintType;
import com.google.zxing.FormatException;
import com.google.zxing.common.BitSource;
import com.google.zxing.common.CharacterSetECI;
import com.google.zxing.common.StringUtils;
import java.util.Collection;
import java.util.Map;

final class DecodedBitStreamParser {
    private static final char[] ALPHANUMERIC_CHARS = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ $%*+-./:".toCharArray();
    private static final int GB2312_SUBSET = 1;

    /* renamed from: com.google.zxing.qrcode.decoder.DecodedBitStreamParser$1  reason: invalid class name */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$google$zxing$qrcode$decoder$Mode;

        /* JADX WARNING: Can't wrap try/catch for region: R(20:0|1|2|3|4|5|6|7|8|9|10|11|12|13|14|15|16|17|18|(3:19|20|22)) */
        /* JADX WARNING: Can't wrap try/catch for region: R(22:0|1|2|3|4|5|6|7|8|9|10|11|12|13|14|15|16|17|18|19|20|22) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:11:0x003e */
        /* JADX WARNING: Missing exception handler attribute for start block: B:13:0x0049 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:15:0x0054 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:17:0x0060 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:19:0x006c */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001d */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0028 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:9:0x0033 */
        static {
            /*
                com.google.zxing.qrcode.decoder.Mode[] r0 = com.google.zxing.qrcode.decoder.Mode.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$com$google$zxing$qrcode$decoder$Mode = r0
                com.google.zxing.qrcode.decoder.Mode r1 = com.google.zxing.qrcode.decoder.Mode.NUMERIC     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = $SwitchMap$com$google$zxing$qrcode$decoder$Mode     // Catch:{ NoSuchFieldError -> 0x001d }
                com.google.zxing.qrcode.decoder.Mode r1 = com.google.zxing.qrcode.decoder.Mode.ALPHANUMERIC     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = $SwitchMap$com$google$zxing$qrcode$decoder$Mode     // Catch:{ NoSuchFieldError -> 0x0028 }
                com.google.zxing.qrcode.decoder.Mode r1 = com.google.zxing.qrcode.decoder.Mode.BYTE     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                int[] r0 = $SwitchMap$com$google$zxing$qrcode$decoder$Mode     // Catch:{ NoSuchFieldError -> 0x0033 }
                com.google.zxing.qrcode.decoder.Mode r1 = com.google.zxing.qrcode.decoder.Mode.KANJI     // Catch:{ NoSuchFieldError -> 0x0033 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0033 }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0033 }
            L_0x0033:
                int[] r0 = $SwitchMap$com$google$zxing$qrcode$decoder$Mode     // Catch:{ NoSuchFieldError -> 0x003e }
                com.google.zxing.qrcode.decoder.Mode r1 = com.google.zxing.qrcode.decoder.Mode.TERMINATOR     // Catch:{ NoSuchFieldError -> 0x003e }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x003e }
                r2 = 5
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x003e }
            L_0x003e:
                int[] r0 = $SwitchMap$com$google$zxing$qrcode$decoder$Mode     // Catch:{ NoSuchFieldError -> 0x0049 }
                com.google.zxing.qrcode.decoder.Mode r1 = com.google.zxing.qrcode.decoder.Mode.FNC1_FIRST_POSITION     // Catch:{ NoSuchFieldError -> 0x0049 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0049 }
                r2 = 6
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0049 }
            L_0x0049:
                int[] r0 = $SwitchMap$com$google$zxing$qrcode$decoder$Mode     // Catch:{ NoSuchFieldError -> 0x0054 }
                com.google.zxing.qrcode.decoder.Mode r1 = com.google.zxing.qrcode.decoder.Mode.FNC1_SECOND_POSITION     // Catch:{ NoSuchFieldError -> 0x0054 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0054 }
                r2 = 7
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0054 }
            L_0x0054:
                int[] r0 = $SwitchMap$com$google$zxing$qrcode$decoder$Mode     // Catch:{ NoSuchFieldError -> 0x0060 }
                com.google.zxing.qrcode.decoder.Mode r1 = com.google.zxing.qrcode.decoder.Mode.STRUCTURED_APPEND     // Catch:{ NoSuchFieldError -> 0x0060 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0060 }
                r2 = 8
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0060 }
            L_0x0060:
                int[] r0 = $SwitchMap$com$google$zxing$qrcode$decoder$Mode     // Catch:{ NoSuchFieldError -> 0x006c }
                com.google.zxing.qrcode.decoder.Mode r1 = com.google.zxing.qrcode.decoder.Mode.ECI     // Catch:{ NoSuchFieldError -> 0x006c }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x006c }
                r2 = 9
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x006c }
            L_0x006c:
                int[] r0 = $SwitchMap$com$google$zxing$qrcode$decoder$Mode     // Catch:{ NoSuchFieldError -> 0x0078 }
                com.google.zxing.qrcode.decoder.Mode r1 = com.google.zxing.qrcode.decoder.Mode.HANZI     // Catch:{ NoSuchFieldError -> 0x0078 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0078 }
                r2 = 10
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0078 }
            L_0x0078:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.zxing.qrcode.decoder.DecodedBitStreamParser.AnonymousClass1.<clinit>():void");
        }
    }

    private DecodedBitStreamParser() {
    }

    /* JADX WARNING: type inference failed for: r0v3 */
    /* JADX WARNING: type inference failed for: r0v4 */
    /* JADX WARNING: type inference failed for: r0v5 */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:53:0x0125  */
    /* JADX WARNING: Removed duplicated region for block: B:54:0x0127  */
    /* JADX WARNING: Removed duplicated region for block: B:56:0x012a  */
    /* JADX WARNING: Removed duplicated region for block: B:57:0x012c  */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static com.google.zxing.common.DecoderResult decode(byte[] r21, com.google.zxing.qrcode.decoder.Version r22, com.google.zxing.qrcode.decoder.ErrorCorrectionLevel r23, java.util.Map<com.google.zxing.DecodeHintType, ?> r24) throws com.google.zxing.FormatException {
        /*
            r0 = r22
            com.google.zxing.common.BitSource r7 = new com.google.zxing.common.BitSource
            r9 = r21
            r7.<init>(r9)
            java.lang.StringBuilder r8 = new java.lang.StringBuilder
            r1 = 50
            r8.<init>(r1)
            java.util.ArrayList r10 = new java.util.ArrayList
            r11 = 1
            r10.<init>(r11)
            r1 = 0
            r2 = -1
            r13 = r1
            r14 = r13
            r15 = r14
            r16 = r2
            r17 = r16
            r18 = 0
        L_0x0021:
            int r1 = r7.available()     // Catch:{ IllegalArgumentException -> 0x0140 }
            r6 = 4
            if (r1 >= r6) goto L_0x002c
            com.google.zxing.qrcode.decoder.Mode r1 = com.google.zxing.qrcode.decoder.Mode.TERMINATOR     // Catch:{ IllegalArgumentException -> 0x0140 }
        L_0x002a:
            r5 = r1
            goto L_0x0035
        L_0x002c:
            int r1 = r7.readBits(r6)     // Catch:{ IllegalArgumentException -> 0x0140 }
            com.google.zxing.qrcode.decoder.Mode r1 = com.google.zxing.qrcode.decoder.Mode.forBits(r1)     // Catch:{ IllegalArgumentException -> 0x0140 }
            goto L_0x002a
        L_0x0035:
            int[] r1 = com.google.zxing.qrcode.decoder.DecodedBitStreamParser.AnonymousClass1.$SwitchMap$com$google$zxing$qrcode$decoder$Mode     // Catch:{ IllegalArgumentException -> 0x0140 }
            int r2 = r5.ordinal()     // Catch:{ IllegalArgumentException -> 0x0140 }
            r2 = r1[r2]     // Catch:{ IllegalArgumentException -> 0x0140 }
            r4 = 3
            r3 = 2
            switch(r2) {
                case 5: goto L_0x005b;
                case 6: goto L_0x00f4;
                case 7: goto L_0x00ea;
                case 8: goto L_0x00c7;
                case 9: goto L_0x00b0;
                case 10: goto L_0x0096;
                default: goto L_0x0042;
            }     // Catch:{ IllegalArgumentException -> 0x0140 }
        L_0x0042:
            int r2 = r5.getCharacterCountBits(r0)     // Catch:{ IllegalArgumentException -> 0x0140 }
            int r2 = r7.readBits(r2)     // Catch:{ IllegalArgumentException -> 0x0140 }
            int r19 = r5.ordinal()     // Catch:{ IllegalArgumentException -> 0x0140 }
            r1 = r1[r19]     // Catch:{ IllegalArgumentException -> 0x0140 }
            if (r1 == r11) goto L_0x008b
            if (r1 == r3) goto L_0x0080
            if (r1 == r4) goto L_0x0069
            if (r1 != r6) goto L_0x0064
            decodeKanjiSegment(r7, r8, r2)     // Catch:{ IllegalArgumentException -> 0x0140 }
        L_0x005b:
            r20 = r3
            r19 = r4
            r12 = r5
            r3 = r11
            r11 = r6
            goto L_0x00fd
        L_0x0064:
            com.google.zxing.FormatException r0 = com.google.zxing.FormatException.getFormatInstance()     // Catch:{ IllegalArgumentException -> 0x0140 }
            throw r0     // Catch:{ IllegalArgumentException -> 0x0140 }
        L_0x0069:
            r1 = r7
            r19 = r2
            r2 = r8
            r20 = r3
            r3 = r19
            r19 = r4
            r4 = r18
            r12 = r5
            r5 = r10
            r11 = r6
            r6 = r24
            decodeByteSegment(r1, r2, r3, r4, r5, r6)     // Catch:{ IllegalArgumentException -> 0x0140 }
        L_0x007d:
            r3 = 1
            goto L_0x00fd
        L_0x0080:
            r1 = r2
            r20 = r3
            r19 = r4
            r12 = r5
            r11 = r6
            decodeAlphanumericSegment(r7, r8, r1, r13)     // Catch:{ IllegalArgumentException -> 0x0140 }
            goto L_0x007d
        L_0x008b:
            r1 = r2
            r20 = r3
            r19 = r4
            r12 = r5
            r11 = r6
            decodeNumericSegment(r7, r8, r1)     // Catch:{ IllegalArgumentException -> 0x0140 }
            goto L_0x007d
        L_0x0096:
            r20 = r3
            r19 = r4
            r12 = r5
            r11 = r6
            int r1 = r7.readBits(r11)     // Catch:{ IllegalArgumentException -> 0x0140 }
            int r2 = r12.getCharacterCountBits(r0)     // Catch:{ IllegalArgumentException -> 0x0140 }
            int r2 = r7.readBits(r2)     // Catch:{ IllegalArgumentException -> 0x0140 }
            r3 = 1
            if (r1 != r3) goto L_0x00fd
            decodeHanziSegment(r7, r8, r2)     // Catch:{ IllegalArgumentException -> 0x0140 }
            goto L_0x00fd
        L_0x00b0:
            r20 = r3
            r19 = r4
            r12 = r5
            r3 = r11
            r11 = r6
            int r1 = parseECIValue(r7)     // Catch:{ IllegalArgumentException -> 0x0140 }
            com.google.zxing.common.CharacterSetECI r18 = com.google.zxing.common.CharacterSetECI.getCharacterSetECIByValue(r1)     // Catch:{ IllegalArgumentException -> 0x0140 }
            if (r18 == 0) goto L_0x00c2
            goto L_0x00fd
        L_0x00c2:
            com.google.zxing.FormatException r0 = com.google.zxing.FormatException.getFormatInstance()     // Catch:{ IllegalArgumentException -> 0x0140 }
            throw r0     // Catch:{ IllegalArgumentException -> 0x0140 }
        L_0x00c7:
            r20 = r3
            r19 = r4
            r12 = r5
            r3 = r11
            r11 = r6
            int r1 = r7.available()     // Catch:{ IllegalArgumentException -> 0x0140 }
            r2 = 16
            if (r1 < r2) goto L_0x00e5
            r1 = 8
            int r2 = r7.readBits(r1)     // Catch:{ IllegalArgumentException -> 0x0140 }
            int r1 = r7.readBits(r1)     // Catch:{ IllegalArgumentException -> 0x0140 }
            r17 = r1
            r16 = r2
            goto L_0x00fd
        L_0x00e5:
            com.google.zxing.FormatException r0 = com.google.zxing.FormatException.getFormatInstance()     // Catch:{ IllegalArgumentException -> 0x0140 }
            throw r0     // Catch:{ IllegalArgumentException -> 0x0140 }
        L_0x00ea:
            r20 = r3
            r19 = r4
            r12 = r5
            r3 = r11
            r11 = r6
            r13 = r3
            r15 = r13
            goto L_0x00fd
        L_0x00f4:
            r20 = r3
            r19 = r4
            r12 = r5
            r3 = r11
            r11 = r6
            r13 = r3
            r14 = r13
        L_0x00fd:
            com.google.zxing.qrcode.decoder.Mode r1 = com.google.zxing.qrcode.decoder.Mode.TERMINATOR     // Catch:{ IllegalArgumentException -> 0x0140 }
            if (r12 != r1) goto L_0x013d
            if (r18 == 0) goto L_0x010f
            if (r14 == 0) goto L_0x0107
            r15 = r11
            goto L_0x0119
        L_0x0107:
            if (r15 == 0) goto L_0x010c
            r0 = 6
        L_0x010a:
            r15 = r0
            goto L_0x0119
        L_0x010c:
            r15 = r20
            goto L_0x0119
        L_0x010f:
            if (r14 == 0) goto L_0x0114
            r15 = r19
            goto L_0x0119
        L_0x0114:
            if (r15 == 0) goto L_0x0118
            r0 = 5
            goto L_0x010a
        L_0x0118:
            r15 = r3
        L_0x0119:
            com.google.zxing.common.DecoderResult r0 = new com.google.zxing.common.DecoderResult
            java.lang.String r1 = r8.toString()
            boolean r2 = r10.isEmpty()
            if (r2 == 0) goto L_0x0127
            r11 = 0
            goto L_0x0128
        L_0x0127:
            r11 = r10
        L_0x0128:
            if (r23 != 0) goto L_0x012c
            r12 = 0
            goto L_0x0131
        L_0x012c:
            java.lang.String r2 = r23.toString()
            r12 = r2
        L_0x0131:
            r8 = r0
            r9 = r21
            r10 = r1
            r13 = r16
            r14 = r17
            r8.<init>(r9, r10, r11, r12, r13, r14, r15)
            return r0
        L_0x013d:
            r11 = r3
            goto L_0x0021
        L_0x0140:
            com.google.zxing.FormatException r0 = com.google.zxing.FormatException.getFormatInstance()
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.zxing.qrcode.decoder.DecodedBitStreamParser.decode(byte[], com.google.zxing.qrcode.decoder.Version, com.google.zxing.qrcode.decoder.ErrorCorrectionLevel, java.util.Map):com.google.zxing.common.DecoderResult");
    }

    private static void decodeAlphanumericSegment(BitSource bitSource, StringBuilder sb, int i3, boolean z2) throws FormatException {
        while (i3 > 1) {
            if (bitSource.available() >= 11) {
                int readBits = bitSource.readBits(11);
                sb.append(toAlphaNumericChar(readBits / 45));
                sb.append(toAlphaNumericChar(readBits % 45));
                i3 -= 2;
            } else {
                throw FormatException.getFormatInstance();
            }
        }
        if (i3 == 1) {
            if (bitSource.available() >= 6) {
                sb.append(toAlphaNumericChar(bitSource.readBits(6)));
            } else {
                throw FormatException.getFormatInstance();
            }
        }
        if (z2) {
            for (int length = sb.length(); length < sb.length(); length++) {
                if (sb.charAt(length) == '%') {
                    if (length < sb.length() - 1) {
                        int i4 = length + 1;
                        if (sb.charAt(i4) == '%') {
                            sb.deleteCharAt(i4);
                        }
                    }
                    sb.setCharAt(length, 29);
                }
            }
        }
    }

    private static void decodeByteSegment(BitSource bitSource, StringBuilder sb, int i3, CharacterSetECI characterSetECI, Collection<byte[]> collection, Map<DecodeHintType, ?> map) throws FormatException {
        if (i3 * 8 <= bitSource.available()) {
            byte[] bArr = new byte[i3];
            for (int i4 = 0; i4 < i3; i4++) {
                bArr[i4] = (byte) bitSource.readBits(8);
            }
            sb.append(new String(bArr, characterSetECI == null ? StringUtils.guessCharset(bArr, map) : characterSetECI.getCharset()));
            collection.add(bArr);
            return;
        }
        throw FormatException.getFormatInstance();
    }

    private static void decodeHanziSegment(BitSource bitSource, StringBuilder sb, int i3) throws FormatException {
        if (StringUtils.GB2312_CHARSET == null) {
            throw FormatException.getFormatInstance();
        } else if (i3 * 13 <= bitSource.available()) {
            byte[] bArr = new byte[(i3 * 2)];
            int i4 = 0;
            while (i3 > 0) {
                int readBits = bitSource.readBits(13);
                int i5 = (readBits % 96) | ((readBits / 96) << 8);
                int i6 = i5 + (i5 < 2560 ? 41377 : 42657);
                bArr[i4] = (byte) ((i6 >> 8) & 255);
                bArr[i4 + 1] = (byte) (i6 & 255);
                i4 += 2;
                i3--;
            }
            sb.append(new String(bArr, StringUtils.GB2312_CHARSET));
        } else {
            throw FormatException.getFormatInstance();
        }
    }

    private static void decodeKanjiSegment(BitSource bitSource, StringBuilder sb, int i3) throws FormatException {
        if (StringUtils.SHIFT_JIS_CHARSET == null) {
            throw FormatException.getFormatInstance();
        } else if (i3 * 13 <= bitSource.available()) {
            byte[] bArr = new byte[(i3 * 2)];
            int i4 = 0;
            while (i3 > 0) {
                int readBits = bitSource.readBits(13);
                int i5 = (readBits % 192) | ((readBits / 192) << 8);
                int i6 = i5 + (i5 < 7936 ? 33088 : 49472);
                bArr[i4] = (byte) (i6 >> 8);
                bArr[i4 + 1] = (byte) i6;
                i4 += 2;
                i3--;
            }
            sb.append(new String(bArr, StringUtils.SHIFT_JIS_CHARSET));
        } else {
            throw FormatException.getFormatInstance();
        }
    }

    private static void decodeNumericSegment(BitSource bitSource, StringBuilder sb, int i3) throws FormatException {
        while (i3 >= 3) {
            if (bitSource.available() >= 10) {
                int readBits = bitSource.readBits(10);
                if (readBits < 1000) {
                    sb.append(toAlphaNumericChar(readBits / 100));
                    sb.append(toAlphaNumericChar((readBits / 10) % 10));
                    sb.append(toAlphaNumericChar(readBits % 10));
                    i3 -= 3;
                } else {
                    throw FormatException.getFormatInstance();
                }
            } else {
                throw FormatException.getFormatInstance();
            }
        }
        if (i3 == 2) {
            if (bitSource.available() >= 7) {
                int readBits2 = bitSource.readBits(7);
                if (readBits2 < 100) {
                    sb.append(toAlphaNumericChar(readBits2 / 10));
                    sb.append(toAlphaNumericChar(readBits2 % 10));
                    return;
                }
                throw FormatException.getFormatInstance();
            }
            throw FormatException.getFormatInstance();
        } else if (i3 != 1) {
        } else {
            if (bitSource.available() >= 4) {
                int readBits3 = bitSource.readBits(4);
                if (readBits3 < 10) {
                    sb.append(toAlphaNumericChar(readBits3));
                    return;
                }
                throw FormatException.getFormatInstance();
            }
            throw FormatException.getFormatInstance();
        }
    }

    private static int parseECIValue(BitSource bitSource) throws FormatException {
        int readBits = bitSource.readBits(8);
        if ((readBits & 128) == 0) {
            return readBits & 127;
        }
        if ((readBits & 192) == 128) {
            return bitSource.readBits(8) | ((readBits & 63) << 8);
        }
        if ((readBits & 224) == 192) {
            return bitSource.readBits(16) | ((readBits & 31) << 16);
        }
        throw FormatException.getFormatInstance();
    }

    private static char toAlphaNumericChar(int i3) throws FormatException {
        char[] cArr = ALPHANUMERIC_CHARS;
        if (i3 < cArr.length) {
            return cArr[i3];
        }
        throw FormatException.getFormatInstance();
    }
}
