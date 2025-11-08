package com.google.zxing.pdf417.decoder;

import com.google.zxing.FormatException;
import com.google.zxing.common.DecoderResult;
import com.google.zxing.common.ECIStringBuilder;
import com.google.zxing.pdf417.PDF417ResultMetadata;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.List;

final class DecodedBitStreamParser {
    private static final int AL = 28;
    private static final int AS = 27;
    private static final int BEGIN_MACRO_PDF417_CONTROL_BLOCK = 928;
    private static final int BEGIN_MACRO_PDF417_OPTIONAL_FIELD = 923;
    private static final int BYTE_COMPACTION_MODE_LATCH = 901;
    private static final int BYTE_COMPACTION_MODE_LATCH_6 = 924;
    private static final int ECI_CHARSET = 927;
    private static final int ECI_GENERAL_PURPOSE = 926;
    private static final int ECI_USER_DEFINED = 925;
    private static final BigInteger[] EXP900;
    private static final int LL = 27;
    private static final int MACRO_PDF417_OPTIONAL_FIELD_ADDRESSEE = 4;
    private static final int MACRO_PDF417_OPTIONAL_FIELD_CHECKSUM = 6;
    private static final int MACRO_PDF417_OPTIONAL_FIELD_FILE_NAME = 0;
    private static final int MACRO_PDF417_OPTIONAL_FIELD_FILE_SIZE = 5;
    private static final int MACRO_PDF417_OPTIONAL_FIELD_SEGMENT_COUNT = 1;
    private static final int MACRO_PDF417_OPTIONAL_FIELD_SENDER = 3;
    private static final int MACRO_PDF417_OPTIONAL_FIELD_TIME_STAMP = 2;
    private static final int MACRO_PDF417_TERMINATOR = 922;
    private static final int MAX_NUMERIC_CODEWORDS = 15;
    private static final char[] MIXED_CHARS = "0123456789&\r\t,:#-.$/+%*=^".toCharArray();
    private static final int ML = 28;
    private static final int MODE_SHIFT_TO_BYTE_COMPACTION_MODE = 913;
    private static final int NUMBER_OF_SEQUENCE_CODEWORDS = 2;
    private static final int NUMERIC_COMPACTION_MODE_LATCH = 902;
    private static final int PAL = 29;
    private static final int PL = 25;
    private static final int PS = 29;
    private static final char[] PUNCT_CHARS = ";<>@[\\]_`~!\r\t,:\n-.$/\"|*()?{}'".toCharArray();
    private static final int TEXT_COMPACTION_MODE_LATCH = 900;

    /* renamed from: com.google.zxing.pdf417.decoder.DecodedBitStreamParser$1  reason: invalid class name */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$google$zxing$pdf417$decoder$DecodedBitStreamParser$Mode;

        /* JADX WARNING: Can't wrap try/catch for region: R(14:0|1|2|3|4|5|6|7|8|9|10|11|12|14) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:11:0x003e */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001d */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0028 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:9:0x0033 */
        static {
            /*
                com.google.zxing.pdf417.decoder.DecodedBitStreamParser$Mode[] r0 = com.google.zxing.pdf417.decoder.DecodedBitStreamParser.Mode.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$com$google$zxing$pdf417$decoder$DecodedBitStreamParser$Mode = r0
                com.google.zxing.pdf417.decoder.DecodedBitStreamParser$Mode r1 = com.google.zxing.pdf417.decoder.DecodedBitStreamParser.Mode.ALPHA     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = $SwitchMap$com$google$zxing$pdf417$decoder$DecodedBitStreamParser$Mode     // Catch:{ NoSuchFieldError -> 0x001d }
                com.google.zxing.pdf417.decoder.DecodedBitStreamParser$Mode r1 = com.google.zxing.pdf417.decoder.DecodedBitStreamParser.Mode.LOWER     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = $SwitchMap$com$google$zxing$pdf417$decoder$DecodedBitStreamParser$Mode     // Catch:{ NoSuchFieldError -> 0x0028 }
                com.google.zxing.pdf417.decoder.DecodedBitStreamParser$Mode r1 = com.google.zxing.pdf417.decoder.DecodedBitStreamParser.Mode.MIXED     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                int[] r0 = $SwitchMap$com$google$zxing$pdf417$decoder$DecodedBitStreamParser$Mode     // Catch:{ NoSuchFieldError -> 0x0033 }
                com.google.zxing.pdf417.decoder.DecodedBitStreamParser$Mode r1 = com.google.zxing.pdf417.decoder.DecodedBitStreamParser.Mode.PUNCT     // Catch:{ NoSuchFieldError -> 0x0033 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0033 }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0033 }
            L_0x0033:
                int[] r0 = $SwitchMap$com$google$zxing$pdf417$decoder$DecodedBitStreamParser$Mode     // Catch:{ NoSuchFieldError -> 0x003e }
                com.google.zxing.pdf417.decoder.DecodedBitStreamParser$Mode r1 = com.google.zxing.pdf417.decoder.DecodedBitStreamParser.Mode.ALPHA_SHIFT     // Catch:{ NoSuchFieldError -> 0x003e }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x003e }
                r2 = 5
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x003e }
            L_0x003e:
                int[] r0 = $SwitchMap$com$google$zxing$pdf417$decoder$DecodedBitStreamParser$Mode     // Catch:{ NoSuchFieldError -> 0x0049 }
                com.google.zxing.pdf417.decoder.DecodedBitStreamParser$Mode r1 = com.google.zxing.pdf417.decoder.DecodedBitStreamParser.Mode.PUNCT_SHIFT     // Catch:{ NoSuchFieldError -> 0x0049 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0049 }
                r2 = 6
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0049 }
            L_0x0049:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.zxing.pdf417.decoder.DecodedBitStreamParser.AnonymousClass1.<clinit>():void");
        }
    }

    public enum Mode {
        ALPHA,
        LOWER,
        MIXED,
        PUNCT,
        ALPHA_SHIFT,
        PUNCT_SHIFT
    }

    static {
        BigInteger[] bigIntegerArr = new BigInteger[16];
        EXP900 = bigIntegerArr;
        bigIntegerArr[0] = BigInteger.ONE;
        BigInteger valueOf = BigInteger.valueOf(900);
        bigIntegerArr[1] = valueOf;
        int i3 = 2;
        while (true) {
            BigInteger[] bigIntegerArr2 = EXP900;
            if (i3 < bigIntegerArr2.length) {
                bigIntegerArr2[i3] = bigIntegerArr2[i3 - 1].multiply(valueOf);
                i3++;
            } else {
                return;
            }
        }
    }

    private DecodedBitStreamParser() {
    }

    /* JADX WARNING: Removed duplicated region for block: B:28:0x0050  */
    /* JADX WARNING: Removed duplicated region for block: B:33:0x0064  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static int byteCompaction(int r11, int[] r12, int r13, com.google.zxing.common.ECIStringBuilder r14) throws com.google.zxing.FormatException {
        /*
            r0 = 0
            r1 = r0
        L_0x0002:
            r2 = r12[r0]
            if (r13 >= r2) goto L_0x0086
            if (r1 != 0) goto L_0x0086
        L_0x0008:
            r2 = r12[r0]
            r3 = 927(0x39f, float:1.299E-42)
            if (r13 >= r2) goto L_0x001c
            r4 = r12[r13]
            if (r4 != r3) goto L_0x001c
            int r2 = r13 + 1
            r2 = r12[r2]
            r14.appendECI(r2)
            int r13 = r13 + 2
            goto L_0x0008
        L_0x001c:
            r4 = 1
            if (r13 >= r2) goto L_0x0083
            r2 = r12[r13]
            r5 = 900(0x384, float:1.261E-42)
            if (r2 < r5) goto L_0x0027
            goto L_0x0083
        L_0x0027:
            r6 = 0
            r2 = r0
        L_0x002a:
            r8 = 900(0x384, double:4.447E-321)
            long r6 = r6 * r8
            int r8 = r13 + 1
            r13 = r12[r13]
            long r9 = (long) r13
            long r6 = r6 + r9
            int r2 = r2 + r4
            r13 = 5
            if (r2 >= r13) goto L_0x0042
            r9 = r12[r0]
            if (r8 >= r9) goto L_0x0042
            r9 = r12[r8]
            if (r9 < r5) goto L_0x0040
            goto L_0x0042
        L_0x0040:
            r13 = r8
            goto L_0x002a
        L_0x0042:
            if (r2 != r13) goto L_0x0064
            r13 = 924(0x39c, float:1.295E-42)
            if (r11 == r13) goto L_0x0050
            r13 = r12[r0]
            if (r8 >= r13) goto L_0x0064
            r13 = r12[r8]
            if (r13 >= r5) goto L_0x0064
        L_0x0050:
            r13 = r0
        L_0x0051:
            r2 = 6
            if (r13 >= r2) goto L_0x0062
            int r2 = 5 - r13
            int r2 = r2 * 8
            long r2 = r6 >> r2
            int r2 = (int) r2
            byte r2 = (byte) r2
            r14.append((byte) r2)
            int r13 = r13 + 1
            goto L_0x0051
        L_0x0062:
            r13 = r8
            goto L_0x0002
        L_0x0064:
            int r8 = r8 - r2
        L_0x0065:
            r13 = r12[r0]
            if (r8 >= r13) goto L_0x0062
            if (r1 != 0) goto L_0x0062
            int r13 = r8 + 1
            r2 = r12[r8]
            if (r2 >= r5) goto L_0x0077
            byte r2 = (byte) r2
            r14.append((byte) r2)
            r8 = r13
            goto L_0x0065
        L_0x0077:
            if (r2 != r3) goto L_0x0081
            int r8 = r8 + 2
            r13 = r12[r13]
            r14.appendECI(r13)
            goto L_0x0065
        L_0x0081:
            r1 = r4
            goto L_0x0065
        L_0x0083:
            r1 = r4
            goto L_0x0002
        L_0x0086:
            return r13
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.zxing.pdf417.decoder.DecodedBitStreamParser.byteCompaction(int, int[], int, com.google.zxing.common.ECIStringBuilder):int");
    }

    public static DecoderResult decode(int[] iArr, String str) throws FormatException {
        ECIStringBuilder eCIStringBuilder = new ECIStringBuilder(iArr.length * 2);
        int textCompaction = textCompaction(iArr, 1, eCIStringBuilder);
        PDF417ResultMetadata pDF417ResultMetadata = new PDF417ResultMetadata();
        while (textCompaction < iArr[0]) {
            int i3 = textCompaction + 1;
            int i4 = iArr[textCompaction];
            if (i4 != MODE_SHIFT_TO_BYTE_COMPACTION_MODE) {
                switch (i4) {
                    case 900:
                        textCompaction = textCompaction(iArr, i3, eCIStringBuilder);
                        break;
                    case 901:
                        textCompaction = byteCompaction(i4, iArr, i3, eCIStringBuilder);
                        break;
                    case 902:
                        textCompaction = numericCompaction(iArr, i3, eCIStringBuilder);
                        break;
                    default:
                        switch (i4) {
                            case MACRO_PDF417_TERMINATOR /*922*/:
                            case BEGIN_MACRO_PDF417_OPTIONAL_FIELD /*923*/:
                                throw FormatException.getFormatInstance();
                            case BYTE_COMPACTION_MODE_LATCH_6 /*924*/:
                                break;
                            case ECI_USER_DEFINED /*925*/:
                                textCompaction += 2;
                                continue;
                            case ECI_GENERAL_PURPOSE /*926*/:
                                textCompaction += 3;
                                continue;
                            case ECI_CHARSET /*927*/:
                                textCompaction += 2;
                                eCIStringBuilder.appendECI(iArr[i3]);
                                continue;
                            case 928:
                                textCompaction = decodeMacroBlock(iArr, i3, pDF417ResultMetadata);
                                continue;
                            default:
                                textCompaction = textCompaction(iArr, textCompaction, eCIStringBuilder);
                                continue;
                        }
                        textCompaction = byteCompaction(i4, iArr, i3, eCIStringBuilder);
                        break;
                }
            } else {
                textCompaction += 2;
                eCIStringBuilder.append((char) iArr[i3]);
            }
        }
        if (!eCIStringBuilder.isEmpty() || pDF417ResultMetadata.getFileId() != null) {
            DecoderResult decoderResult = new DecoderResult((byte[]) null, eCIStringBuilder.toString(), (List<byte[]>) null, str);
            decoderResult.setOther(pDF417ResultMetadata);
            return decoderResult;
        }
        throw FormatException.getFormatInstance();
    }

    private static String decodeBase900toBase10(int[] iArr, int i3) throws FormatException {
        BigInteger bigInteger = BigInteger.ZERO;
        for (int i4 = 0; i4 < i3; i4++) {
            bigInteger = bigInteger.add(EXP900[(i3 - i4) - 1].multiply(BigInteger.valueOf((long) iArr[i4])));
        }
        String bigInteger2 = bigInteger.toString();
        if (bigInteger2.charAt(0) == '1') {
            return bigInteger2.substring(1);
        }
        throw FormatException.getFormatInstance();
    }

    public static int decodeMacroBlock(int[] iArr, int i3, PDF417ResultMetadata pDF417ResultMetadata) throws FormatException {
        int i4;
        if (i3 + 2 <= iArr[0]) {
            int[] iArr2 = new int[2];
            int i5 = 0;
            while (i5 < 2) {
                iArr2[i5] = iArr[i3];
                i5++;
                i3++;
            }
            String decodeBase900toBase10 = decodeBase900toBase10(iArr2, 2);
            if (decodeBase900toBase10.isEmpty()) {
                pDF417ResultMetadata.setSegmentIndex(0);
            } else {
                try {
                    pDF417ResultMetadata.setSegmentIndex(Integer.parseInt(decodeBase900toBase10));
                } catch (NumberFormatException unused) {
                    throw FormatException.getFormatInstance();
                }
            }
            StringBuilder sb = new StringBuilder();
            while (r8 < iArr[0] && r8 < iArr.length && (i4 = iArr[r8]) != MACRO_PDF417_TERMINATOR && i4 != BEGIN_MACRO_PDF417_OPTIONAL_FIELD) {
                sb.append(String.format("%03d", new Object[]{Integer.valueOf(i4)}));
                i3 = r8 + 1;
            }
            if (sb.length() != 0) {
                pDF417ResultMetadata.setFileId(sb.toString());
                int i6 = iArr[r8] == BEGIN_MACRO_PDF417_OPTIONAL_FIELD ? r8 + 1 : -1;
                while (r8 < iArr[0]) {
                    int i7 = iArr[r8];
                    if (i7 == MACRO_PDF417_TERMINATOR) {
                        r8++;
                        pDF417ResultMetadata.setLastSegment(true);
                    } else if (i7 == BEGIN_MACRO_PDF417_OPTIONAL_FIELD) {
                        switch (iArr[r8 + 1]) {
                            case 0:
                                ECIStringBuilder eCIStringBuilder = new ECIStringBuilder();
                                r8 = textCompaction(iArr, r8 + 2, eCIStringBuilder);
                                pDF417ResultMetadata.setFileName(eCIStringBuilder.toString());
                                break;
                            case 1:
                                ECIStringBuilder eCIStringBuilder2 = new ECIStringBuilder();
                                r8 = numericCompaction(iArr, r8 + 2, eCIStringBuilder2);
                                try {
                                    pDF417ResultMetadata.setSegmentCount(Integer.parseInt(eCIStringBuilder2.toString()));
                                    break;
                                } catch (NumberFormatException unused2) {
                                    throw FormatException.getFormatInstance();
                                }
                            case 2:
                                ECIStringBuilder eCIStringBuilder3 = new ECIStringBuilder();
                                r8 = numericCompaction(iArr, r8 + 2, eCIStringBuilder3);
                                try {
                                    pDF417ResultMetadata.setTimestamp(Long.parseLong(eCIStringBuilder3.toString()));
                                    break;
                                } catch (NumberFormatException unused3) {
                                    throw FormatException.getFormatInstance();
                                }
                            case 3:
                                ECIStringBuilder eCIStringBuilder4 = new ECIStringBuilder();
                                r8 = textCompaction(iArr, r8 + 2, eCIStringBuilder4);
                                pDF417ResultMetadata.setSender(eCIStringBuilder4.toString());
                                break;
                            case 4:
                                ECIStringBuilder eCIStringBuilder5 = new ECIStringBuilder();
                                r8 = textCompaction(iArr, r8 + 2, eCIStringBuilder5);
                                pDF417ResultMetadata.setAddressee(eCIStringBuilder5.toString());
                                break;
                            case 5:
                                ECIStringBuilder eCIStringBuilder6 = new ECIStringBuilder();
                                r8 = numericCompaction(iArr, r8 + 2, eCIStringBuilder6);
                                try {
                                    pDF417ResultMetadata.setFileSize(Long.parseLong(eCIStringBuilder6.toString()));
                                    break;
                                } catch (NumberFormatException unused4) {
                                    throw FormatException.getFormatInstance();
                                }
                            case 6:
                                ECIStringBuilder eCIStringBuilder7 = new ECIStringBuilder();
                                r8 = numericCompaction(iArr, r8 + 2, eCIStringBuilder7);
                                try {
                                    pDF417ResultMetadata.setChecksum(Integer.parseInt(eCIStringBuilder7.toString()));
                                    break;
                                } catch (NumberFormatException unused5) {
                                    throw FormatException.getFormatInstance();
                                }
                            default:
                                throw FormatException.getFormatInstance();
                        }
                    } else {
                        throw FormatException.getFormatInstance();
                    }
                }
                if (i6 != -1) {
                    int i8 = r8 - i6;
                    if (pDF417ResultMetadata.isLastSegment()) {
                        i8--;
                    }
                    if (i8 > 0) {
                        pDF417ResultMetadata.setOptionalData(Arrays.copyOfRange(iArr, i6, i8 + i6));
                    }
                }
                return r8;
            }
            throw FormatException.getFormatInstance();
        }
        throw FormatException.getFormatInstance();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:21:0x0052, code lost:
        r9 = 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x0053, code lost:
        r7 = r4;
        r4 = r3;
        r3 = r9;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x005e, code lost:
        r14 = r4;
        r4 = r3;
        r3 = r7;
        r7 = r14;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:38:0x0089, code lost:
        r9 = 0;
        r14 = r4;
        r4 = r3;
        r3 = r14;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:40:0x0090, code lost:
        r9 = 0;
        r3 = r2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:46:0x00a4, code lost:
        r7 = (char) r7;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:4:0x0023, code lost:
        r7 = r4;
        r4 = r3;
        r3 = 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:65:0x00e2, code lost:
        if (r3 == 0) goto L_0x00e7;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:66:0x00e4, code lost:
        r0.append(r3);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:67:0x00e7, code lost:
        r6 = r6 + 1;
        r3 = r4;
        r4 = r7;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:7:0x002e, code lost:
        r7 = r4;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static com.google.zxing.pdf417.decoder.DecodedBitStreamParser.Mode decodeTextCompaction(int[] r15, int[] r16, int r17, com.google.zxing.common.ECIStringBuilder r18, com.google.zxing.pdf417.decoder.DecodedBitStreamParser.Mode r19) {
        /*
            r0 = r18
            r1 = 0
            r5 = r17
            r2 = r19
            r3 = r2
            r4 = r3
            r6 = r1
        L_0x000a:
            if (r6 >= r5) goto L_0x00ed
            r7 = r15[r6]
            int[] r8 = com.google.zxing.pdf417.decoder.DecodedBitStreamParser.AnonymousClass1.$SwitchMap$com$google$zxing$pdf417$decoder$DecodedBitStreamParser$Mode
            int r9 = r3.ordinal()
            r8 = r8[r9]
            r9 = 32
            r10 = 26
            r11 = 29
            r12 = 913(0x391, float:1.28E-42)
            r13 = 900(0x384, float:1.261E-42)
            switch(r8) {
                case 1: goto L_0x00c1;
                case 2: goto L_0x00a0;
                case 3: goto L_0x0076;
                case 4: goto L_0x0058;
                case 5: goto L_0x0043;
                case 6: goto L_0x0028;
                default: goto L_0x0023;
            }
        L_0x0023:
            r7 = r4
            r4 = r3
            r3 = r1
            goto L_0x00e2
        L_0x0028:
            if (r7 >= r11) goto L_0x0031
            char[] r3 = PUNCT_CHARS
            char r3 = r3[r7]
        L_0x002e:
            r7 = r4
            goto L_0x00e2
        L_0x0031:
            if (r7 == r11) goto L_0x0040
            if (r7 == r13) goto L_0x0040
            if (r7 == r12) goto L_0x0038
            goto L_0x003e
        L_0x0038:
            r3 = r16[r6]
            char r3 = (char) r3
            r0.append((char) r3)
        L_0x003e:
            r3 = r1
            goto L_0x002e
        L_0x0040:
            com.google.zxing.pdf417.decoder.DecodedBitStreamParser$Mode r3 = com.google.zxing.pdf417.decoder.DecodedBitStreamParser.Mode.ALPHA
            goto L_0x0023
        L_0x0043:
            if (r7 >= r10) goto L_0x0049
            int r7 = r7 + 65
            char r3 = (char) r7
            goto L_0x002e
        L_0x0049:
            if (r7 == r10) goto L_0x004e
            if (r7 == r13) goto L_0x0050
            r9 = r1
        L_0x004e:
            r3 = r4
            goto L_0x0053
        L_0x0050:
            com.google.zxing.pdf417.decoder.DecodedBitStreamParser$Mode r3 = com.google.zxing.pdf417.decoder.DecodedBitStreamParser.Mode.ALPHA
        L_0x0052:
            r9 = r1
        L_0x0053:
            r7 = r4
            r4 = r3
            r3 = r9
            goto L_0x00e2
        L_0x0058:
            if (r7 >= r11) goto L_0x0064
            char[] r8 = PUNCT_CHARS
            char r7 = r8[r7]
        L_0x005e:
            r14 = r4
            r4 = r3
            r3 = r7
            r7 = r14
            goto L_0x00e2
        L_0x0064:
            if (r7 == r11) goto L_0x0072
            if (r7 == r13) goto L_0x0072
            if (r7 == r12) goto L_0x006b
            goto L_0x0023
        L_0x006b:
            r7 = r16[r6]
            char r7 = (char) r7
            r0.append((char) r7)
            goto L_0x0023
        L_0x0072:
            com.google.zxing.pdf417.decoder.DecodedBitStreamParser$Mode r2 = com.google.zxing.pdf417.decoder.DecodedBitStreamParser.Mode.ALPHA
            r3 = r2
            goto L_0x0023
        L_0x0076:
            r8 = 25
            if (r7 >= r8) goto L_0x007f
            char[] r8 = MIXED_CHARS
            char r7 = r8[r7]
            goto L_0x005e
        L_0x007f:
            if (r7 == r13) goto L_0x009d
            if (r7 == r12) goto L_0x0096
            switch(r7) {
                case 25: goto L_0x0093;
                case 26: goto L_0x0053;
                case 27: goto L_0x008e;
                case 28: goto L_0x009d;
                case 29: goto L_0x0087;
                default: goto L_0x0086;
            }
        L_0x0086:
            goto L_0x0052
        L_0x0087:
            com.google.zxing.pdf417.decoder.DecodedBitStreamParser$Mode r4 = com.google.zxing.pdf417.decoder.DecodedBitStreamParser.Mode.PUNCT_SHIFT
        L_0x0089:
            r9 = r1
            r14 = r4
            r4 = r3
            r3 = r14
            goto L_0x0053
        L_0x008e:
            com.google.zxing.pdf417.decoder.DecodedBitStreamParser$Mode r2 = com.google.zxing.pdf417.decoder.DecodedBitStreamParser.Mode.LOWER
        L_0x0090:
            r9 = r1
            r3 = r2
            goto L_0x0053
        L_0x0093:
            com.google.zxing.pdf417.decoder.DecodedBitStreamParser$Mode r2 = com.google.zxing.pdf417.decoder.DecodedBitStreamParser.Mode.PUNCT
            goto L_0x0090
        L_0x0096:
            r7 = r16[r6]
            char r7 = (char) r7
            r0.append((char) r7)
            goto L_0x0052
        L_0x009d:
            com.google.zxing.pdf417.decoder.DecodedBitStreamParser$Mode r2 = com.google.zxing.pdf417.decoder.DecodedBitStreamParser.Mode.ALPHA
            goto L_0x0090
        L_0x00a0:
            if (r7 >= r10) goto L_0x00a6
            int r7 = r7 + 97
        L_0x00a4:
            char r7 = (char) r7
            goto L_0x005e
        L_0x00a6:
            if (r7 == r13) goto L_0x00be
            if (r7 == r12) goto L_0x00b7
            switch(r7) {
                case 26: goto L_0x0053;
                case 27: goto L_0x00b4;
                case 28: goto L_0x00b1;
                case 29: goto L_0x00ae;
                default: goto L_0x00ad;
            }
        L_0x00ad:
            goto L_0x0052
        L_0x00ae:
            com.google.zxing.pdf417.decoder.DecodedBitStreamParser$Mode r4 = com.google.zxing.pdf417.decoder.DecodedBitStreamParser.Mode.PUNCT_SHIFT
            goto L_0x0089
        L_0x00b1:
            com.google.zxing.pdf417.decoder.DecodedBitStreamParser$Mode r2 = com.google.zxing.pdf417.decoder.DecodedBitStreamParser.Mode.MIXED
            goto L_0x0090
        L_0x00b4:
            com.google.zxing.pdf417.decoder.DecodedBitStreamParser$Mode r4 = com.google.zxing.pdf417.decoder.DecodedBitStreamParser.Mode.ALPHA_SHIFT
            goto L_0x0089
        L_0x00b7:
            r7 = r16[r6]
            char r7 = (char) r7
            r0.append((char) r7)
            goto L_0x0052
        L_0x00be:
            com.google.zxing.pdf417.decoder.DecodedBitStreamParser$Mode r2 = com.google.zxing.pdf417.decoder.DecodedBitStreamParser.Mode.ALPHA
            goto L_0x0090
        L_0x00c1:
            if (r7 >= r10) goto L_0x00c6
            int r7 = r7 + 65
            goto L_0x00a4
        L_0x00c6:
            if (r7 == r13) goto L_0x00df
            if (r7 == r12) goto L_0x00d7
            switch(r7) {
                case 26: goto L_0x0053;
                case 27: goto L_0x00d4;
                case 28: goto L_0x00d1;
                case 29: goto L_0x00ce;
                default: goto L_0x00cd;
            }
        L_0x00cd:
            goto L_0x0052
        L_0x00ce:
            com.google.zxing.pdf417.decoder.DecodedBitStreamParser$Mode r4 = com.google.zxing.pdf417.decoder.DecodedBitStreamParser.Mode.PUNCT_SHIFT
            goto L_0x0089
        L_0x00d1:
            com.google.zxing.pdf417.decoder.DecodedBitStreamParser$Mode r2 = com.google.zxing.pdf417.decoder.DecodedBitStreamParser.Mode.MIXED
            goto L_0x0090
        L_0x00d4:
            com.google.zxing.pdf417.decoder.DecodedBitStreamParser$Mode r2 = com.google.zxing.pdf417.decoder.DecodedBitStreamParser.Mode.LOWER
            goto L_0x0090
        L_0x00d7:
            r7 = r16[r6]
            char r7 = (char) r7
            r0.append((char) r7)
            goto L_0x0052
        L_0x00df:
            com.google.zxing.pdf417.decoder.DecodedBitStreamParser$Mode r2 = com.google.zxing.pdf417.decoder.DecodedBitStreamParser.Mode.ALPHA
            goto L_0x0090
        L_0x00e2:
            if (r3 == 0) goto L_0x00e7
            r0.append((char) r3)
        L_0x00e7:
            int r6 = r6 + 1
            r3 = r4
            r4 = r7
            goto L_0x000a
        L_0x00ed:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.zxing.pdf417.decoder.DecodedBitStreamParser.decodeTextCompaction(int[], int[], int, com.google.zxing.common.ECIStringBuilder, com.google.zxing.pdf417.decoder.DecodedBitStreamParser$Mode):com.google.zxing.pdf417.decoder.DecodedBitStreamParser$Mode");
    }

    private static int numericCompaction(int[] iArr, int i3, ECIStringBuilder eCIStringBuilder) throws FormatException {
        int[] iArr2 = new int[15];
        boolean z2 = false;
        int i4 = 0;
        while (true) {
            int i5 = iArr[0];
            if (i3 >= i5 || z2) {
                return i3;
            }
            int i6 = i3 + 1;
            int i7 = iArr[i3];
            if (i6 == i5) {
                z2 = true;
            }
            if (i7 < 900) {
                iArr2[i4] = i7;
                i4++;
            } else {
                if (!(i7 == 900 || i7 == 901 || i7 == ECI_CHARSET || i7 == 928)) {
                    switch (i7) {
                        case MACRO_PDF417_TERMINATOR /*922*/:
                        case BEGIN_MACRO_PDF417_OPTIONAL_FIELD /*923*/:
                        case BYTE_COMPACTION_MODE_LATCH_6 /*924*/:
                            break;
                    }
                }
                z2 = true;
                if ((i4 % 15 == 0 || i7 == 902 || z2) && i4 > 0) {
                    eCIStringBuilder.append(decodeBase900toBase10(iArr2, i4));
                    i4 = 0;
                }
            }
            i3 = i6;
            eCIStringBuilder.append(decodeBase900toBase10(iArr2, i4));
            i4 = 0;
        }
        return i3;
    }

    private static int textCompaction(int[] iArr, int i3, ECIStringBuilder eCIStringBuilder) throws FormatException {
        int i4 = iArr[0];
        int[] iArr2 = new int[((i4 - i3) * 2)];
        int[] iArr3 = new int[((i4 - i3) * 2)];
        Mode mode = Mode.ALPHA;
        boolean z2 = false;
        int i5 = 0;
        while (i3 < iArr[0] && !z2) {
            int i6 = i3 + 1;
            int i7 = iArr[i3];
            if (i7 < 900) {
                iArr2[i5] = i7 / 30;
                iArr2[i5 + 1] = i7 % 30;
                i5 += 2;
            } else if (i7 == MODE_SHIFT_TO_BYTE_COMPACTION_MODE) {
                iArr2[i5] = MODE_SHIFT_TO_BYTE_COMPACTION_MODE;
                i3 += 2;
                iArr3[i5] = iArr[i6];
                i5++;
            } else if (i7 != ECI_CHARSET) {
                if (i7 != 928) {
                    switch (i7) {
                        case 900:
                            iArr2[i5] = 900;
                            i5++;
                            break;
                        case 901:
                        case 902:
                            break;
                        default:
                            switch (i7) {
                                case MACRO_PDF417_TERMINATOR /*922*/:
                                case BEGIN_MACRO_PDF417_OPTIONAL_FIELD /*923*/:
                                case BYTE_COMPACTION_MODE_LATCH_6 /*924*/:
                                    break;
                            }
                    }
                }
                z2 = true;
            } else {
                Mode decodeTextCompaction = decodeTextCompaction(iArr2, iArr3, i5, eCIStringBuilder, mode);
                i3 += 2;
                eCIStringBuilder.appendECI(iArr[i6]);
                int i8 = iArr[0];
                if (i3 <= i8) {
                    i5 = 0;
                    mode = decodeTextCompaction;
                    iArr3 = new int[((i8 - i3) * 2)];
                    iArr2 = new int[((i8 - i3) * 2)];
                } else {
                    throw FormatException.getFormatInstance();
                }
            }
            i3 = i6;
        }
        decodeTextCompaction(iArr2, iArr3, i5, eCIStringBuilder, mode);
        return i3;
    }
}
