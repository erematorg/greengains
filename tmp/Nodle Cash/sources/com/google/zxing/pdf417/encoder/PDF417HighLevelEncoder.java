package com.google.zxing.pdf417.encoder;

import A.a;
import com.fasterxml.jackson.dataformat.cbor.CBORConstants;
import com.google.common.base.Ascii;
import com.google.common.primitives.SignedBytes;
import com.google.zxing.WriterException;
import com.google.zxing.common.CharacterSetECI;
import com.google.zxing.common.ECIInput;
import com.google.zxing.common.MinimalECIInput;
import io.nodle.cash.substrate.SubstrateHelper;
import java.math.BigInteger;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import okio.Utf8;
import org.web3j.tx.ChainId;

final class PDF417HighLevelEncoder {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static final int BYTE_COMPACTION = 1;
    private static final Charset DEFAULT_ENCODING = StandardCharsets.ISO_8859_1;
    private static final int ECI_CHARSET = 927;
    private static final int ECI_GENERAL_PURPOSE = 926;
    private static final int ECI_USER_DEFINED = 925;
    private static final int LATCH_TO_BYTE = 924;
    private static final int LATCH_TO_BYTE_PADDED = 901;
    private static final int LATCH_TO_NUMERIC = 902;
    private static final int LATCH_TO_TEXT = 900;
    private static final byte[] MIXED;
    private static final int NUMERIC_COMPACTION = 2;
    private static final byte[] PUNCTUATION = new byte[128];
    private static final int SHIFT_TO_BYTE = 913;
    private static final int SUBMODE_ALPHA = 0;
    private static final int SUBMODE_LOWER = 1;
    private static final int SUBMODE_MIXED = 2;
    private static final int SUBMODE_PUNCTUATION = 3;
    private static final int TEXT_COMPACTION = 0;
    private static final byte[] TEXT_MIXED_RAW = {48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 38, Ascii.CR, 9, 44, 58, 35, 45, 46, 36, 47, 43, SubstrateHelper.NODLE_SUBSTRATE_ID, 42, 61, 94, 0, 32, 0, 0, 0};
    private static final byte[] TEXT_PUNCTUATION_RAW = {59, 60, ChainId.ETHEREUM_CLASSIC_TESTNET, SignedBytes.MAX_POWER_OF_TWO, 91, 92, 93, 95, CBORConstants.BYTE_EMPTY_STRING, 126, 33, Ascii.CR, 9, 44, 58, 10, 45, 46, 36, 47, 34, 124, 42, 40, 41, Utf8.REPLACEMENT_BYTE, 123, 125, 39, 0};

    /* renamed from: com.google.zxing.pdf417.encoder.PDF417HighLevelEncoder$1  reason: invalid class name */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$google$zxing$pdf417$encoder$Compaction;

        /* JADX WARNING: Can't wrap try/catch for region: R(6:0|1|2|3|4|(3:5|6|8)) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001d */
        static {
            /*
                com.google.zxing.pdf417.encoder.Compaction[] r0 = com.google.zxing.pdf417.encoder.Compaction.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$com$google$zxing$pdf417$encoder$Compaction = r0
                com.google.zxing.pdf417.encoder.Compaction r1 = com.google.zxing.pdf417.encoder.Compaction.TEXT     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = $SwitchMap$com$google$zxing$pdf417$encoder$Compaction     // Catch:{ NoSuchFieldError -> 0x001d }
                com.google.zxing.pdf417.encoder.Compaction r1 = com.google.zxing.pdf417.encoder.Compaction.BYTE     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = $SwitchMap$com$google$zxing$pdf417$encoder$Compaction     // Catch:{ NoSuchFieldError -> 0x0028 }
                com.google.zxing.pdf417.encoder.Compaction r1 = com.google.zxing.pdf417.encoder.Compaction.NUMERIC     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.zxing.pdf417.encoder.PDF417HighLevelEncoder.AnonymousClass1.<clinit>():void");
        }
    }

    public static final class NoECIInput implements ECIInput {
        String input;

        public /* synthetic */ NoECIInput(String str, AnonymousClass1 r2) {
            this(str);
        }

        public char charAt(int i3) {
            return this.input.charAt(i3);
        }

        public int getECIValue(int i3) {
            return -1;
        }

        public boolean haveNCharacters(int i3, int i4) {
            return i3 + i4 <= this.input.length();
        }

        public boolean isECI(int i3) {
            return false;
        }

        public int length() {
            return this.input.length();
        }

        public CharSequence subSequence(int i3, int i4) {
            return this.input.subSequence(i3, i4);
        }

        public String toString() {
            return this.input;
        }

        private NoECIInput(String str) {
            this.input = str;
        }
    }

    static {
        int i3 = 0;
        byte[] bArr = new byte[128];
        MIXED = bArr;
        Arrays.fill(bArr, (byte) -1);
        int i4 = 0;
        while (true) {
            byte[] bArr2 = TEXT_MIXED_RAW;
            if (i4 >= bArr2.length) {
                break;
            }
            byte b3 = bArr2[i4];
            if (b3 > 0) {
                MIXED[b3] = (byte) i4;
            }
            i4++;
        }
        Arrays.fill(PUNCTUATION, (byte) -1);
        while (true) {
            byte[] bArr3 = TEXT_PUNCTUATION_RAW;
            if (i3 < bArr3.length) {
                byte b4 = bArr3[i3];
                if (b4 > 0) {
                    PUNCTUATION[b4] = (byte) i3;
                }
                i3++;
            } else {
                return;
            }
        }
    }

    private PDF417HighLevelEncoder() {
    }

    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0025, code lost:
        r2 = r2 + 1;
     */
    /* JADX WARNING: Removed duplicated region for block: B:6:0x0011 A[LOOP:1: B:6:0x0011->B:13:0x0029, LOOP_START, PHI: r2 r3 
      PHI: (r2v1 int) = (r2v0 int), (r2v5 int) binds: [B:5:0x000f, B:13:0x0029] A[DONT_GENERATE, DONT_INLINE]
      PHI: (r3v1 int) = (r3v0 int), (r3v4 int) binds: [B:5:0x000f, B:13:0x0029] A[DONT_GENERATE, DONT_INLINE]] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static int determineConsecutiveBinaryCount(com.google.zxing.common.ECIInput r6, int r7, java.nio.charset.Charset r8) throws com.google.zxing.WriterException {
        /*
            if (r8 != 0) goto L_0x0004
            r8 = 0
            goto L_0x0008
        L_0x0004:
            java.nio.charset.CharsetEncoder r8 = r8.newEncoder()
        L_0x0008:
            int r0 = r6.length()
            r1 = r7
        L_0x000d:
            if (r1 >= r0) goto L_0x0064
            r2 = 0
            r3 = r1
        L_0x0011:
            r4 = 13
            if (r2 >= r4) goto L_0x002b
            boolean r5 = r6.isECI(r3)
            if (r5 != 0) goto L_0x002b
            char r3 = r6.charAt(r3)
            boolean r3 = isDigit(r3)
            if (r3 == 0) goto L_0x002b
            int r2 = r2 + 1
            int r3 = r1 + r2
            if (r3 < r0) goto L_0x0011
        L_0x002b:
            if (r2 < r4) goto L_0x002f
            int r1 = r1 - r7
            return r1
        L_0x002f:
            if (r8 == 0) goto L_0x0061
            char r2 = r6.charAt(r1)
            boolean r2 = r8.canEncode(r2)
            if (r2 == 0) goto L_0x003c
            goto L_0x0061
        L_0x003c:
            char r6 = r6.charAt(r1)
            com.google.zxing.WriterException r7 = new com.google.zxing.WriterException
            java.lang.StringBuilder r8 = new java.lang.StringBuilder
            java.lang.String r0 = "Non-encodable character detected: "
            r8.<init>(r0)
            r8.append(r6)
            java.lang.String r0 = " (Unicode: "
            r8.append(r0)
            r8.append(r6)
            r6 = 41
            r8.append(r6)
            java.lang.String r6 = r8.toString()
            r7.<init>((java.lang.String) r6)
            throw r7
        L_0x0061:
            int r1 = r1 + 1
            goto L_0x000d
        L_0x0064:
            int r1 = r1 - r7
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.zxing.pdf417.encoder.PDF417HighLevelEncoder.determineConsecutiveBinaryCount(com.google.zxing.common.ECIInput, int, java.nio.charset.Charset):int");
    }

    private static int determineConsecutiveDigitCount(ECIInput eCIInput, int i3) {
        int length = eCIInput.length();
        int i4 = 0;
        if (i3 < length) {
            while (i3 < length && !eCIInput.isECI(i3) && isDigit(eCIInput.charAt(i3))) {
                i4++;
                i3++;
            }
        }
        return i4;
    }

    private static int determineConsecutiveTextCount(ECIInput eCIInput, int i3) {
        int length = eCIInput.length();
        int i4 = i3;
        while (i4 < length) {
            int i5 = 0;
            while (i5 < 13 && i4 < length && !eCIInput.isECI(i4) && isDigit(eCIInput.charAt(i4))) {
                i5++;
                i4++;
            }
            if (i5 >= 13) {
                return (i4 - i3) - i5;
            }
            if (i5 <= 0) {
                if (eCIInput.isECI(i4) || !isText(eCIInput.charAt(i4))) {
                    break;
                }
                i4++;
            }
        }
        return i4 - i3;
    }

    private static void encodeBinary(byte[] bArr, int i3, int i4, int i5, StringBuilder sb) {
        int i6;
        if (i4 == 1 && i5 == 0) {
            sb.append(913);
        } else if (i4 % 6 == 0) {
            sb.append(924);
        } else {
            sb.append(901);
        }
        if (i4 >= 6) {
            char[] cArr = new char[5];
            i6 = i3;
            while ((i3 + i4) - i6 >= 6) {
                long j2 = 0;
                for (int i7 = 0; i7 < 6; i7++) {
                    j2 = (j2 << 8) + ((long) (bArr[i6 + i7] & 255));
                }
                for (int i8 = 0; i8 < 5; i8++) {
                    cArr[i8] = (char) ((int) (j2 % 900));
                    j2 /= 900;
                }
                for (int i9 = 4; i9 >= 0; i9--) {
                    sb.append(cArr[i9]);
                }
                i6 += 6;
            }
        } else {
            i6 = i3;
        }
        while (i6 < i3 + i4) {
            sb.append((char) (bArr[i6] & 255));
            i6++;
        }
    }

    public static String encodeHighLevel(String str, Compaction compaction, Charset charset, boolean z2) throws WriterException {
        ECIInput eCIInput;
        CharacterSetECI characterSetECI;
        if (!str.isEmpty()) {
            if (charset == null && !z2) {
                int i3 = 0;
                while (i3 < str.length()) {
                    if (str.charAt(i3) <= 255) {
                        i3++;
                    } else {
                        throw new WriterException("Non-encodable character detected: " + str.charAt(i3) + " (Unicode: " + str.charAt(i3) + "). Consider specifying EncodeHintType.PDF417_AUTO_ECI and/or EncodeTypeHint.CHARACTER_SET.");
                    }
                }
            }
            StringBuilder sb = new StringBuilder(str.length());
            if (z2) {
                eCIInput = new MinimalECIInput(str, charset, -1);
            } else {
                eCIInput = new NoECIInput(str, (AnonymousClass1) null);
                if (charset == null) {
                    charset = DEFAULT_ENCODING;
                } else if (!DEFAULT_ENCODING.equals(charset) && (characterSetECI = CharacterSetECI.getCharacterSetECI(charset)) != null) {
                    encodingECI(characterSetECI.getValue(), sb);
                }
            }
            int length = eCIInput.length();
            int i4 = AnonymousClass1.$SwitchMap$com$google$zxing$pdf417$encoder$Compaction[compaction.ordinal()];
            if (i4 == 1) {
                encodeText(eCIInput, 0, length, sb, 0);
            } else if (i4 != 2) {
                if (i4 != 3) {
                    int i5 = 0;
                    int i6 = 0;
                    int i7 = 0;
                    while (i5 < length) {
                        while (i5 < length && eCIInput.isECI(i5)) {
                            encodingECI(eCIInput.getECIValue(i5), sb);
                            i5++;
                        }
                        if (i5 >= length) {
                            break;
                        }
                        int determineConsecutiveDigitCount = determineConsecutiveDigitCount(eCIInput, i5);
                        if (determineConsecutiveDigitCount >= 13) {
                            sb.append(902);
                            encodeNumeric(eCIInput, i5, determineConsecutiveDigitCount, sb);
                            i5 += determineConsecutiveDigitCount;
                            i6 = 0;
                            i7 = 2;
                        } else {
                            int determineConsecutiveTextCount = determineConsecutiveTextCount(eCIInput, i5);
                            if (determineConsecutiveTextCount >= 5 || determineConsecutiveDigitCount == length) {
                                if (i7 != 0) {
                                    sb.append(900);
                                    i6 = 0;
                                    i7 = 0;
                                }
                                i6 = encodeText(eCIInput, i5, determineConsecutiveTextCount, sb, i6);
                                i5 += determineConsecutiveTextCount;
                            } else {
                                int determineConsecutiveBinaryCount = determineConsecutiveBinaryCount(eCIInput, i5, z2 ? null : charset);
                                if (determineConsecutiveBinaryCount == 0) {
                                    determineConsecutiveBinaryCount = 1;
                                }
                                byte[] bytes = z2 ? null : eCIInput.subSequence(i5, i5 + determineConsecutiveBinaryCount).toString().getBytes(charset);
                                if ((!(bytes == null && determineConsecutiveBinaryCount == 1) && (bytes == null || bytes.length != 1)) || i7 != 0) {
                                    if (z2) {
                                        encodeMultiECIBinary(eCIInput, i5, i5 + determineConsecutiveBinaryCount, i7, sb);
                                    } else {
                                        encodeBinary(bytes, 0, bytes.length, i7, sb);
                                    }
                                    i6 = 0;
                                    i7 = 1;
                                } else if (z2) {
                                    encodeMultiECIBinary(eCIInput, i5, 1, 0, sb);
                                } else {
                                    encodeBinary(bytes, 0, 1, 0, sb);
                                }
                                i5 += determineConsecutiveBinaryCount;
                            }
                        }
                    }
                } else {
                    sb.append(902);
                    encodeNumeric(eCIInput, 0, length, sb);
                }
            } else if (z2) {
                encodeMultiECIBinary(eCIInput, 0, eCIInput.length(), 0, sb);
            } else {
                byte[] bytes2 = eCIInput.toString().getBytes(charset);
                encodeBinary(bytes2, 0, bytes2.length, 1, sb);
            }
            return sb.toString();
        }
        throw new WriterException("Empty message not allowed");
    }

    private static void encodeMultiECIBinary(ECIInput eCIInput, int i3, int i4, int i5, StringBuilder sb) throws WriterException {
        int min = Math.min(i4 + i3, eCIInput.length());
        int i6 = i3;
        while (true) {
            if (i6 >= min || !eCIInput.isECI(i6)) {
                int i7 = i6;
                while (i7 < min && !eCIInput.isECI(i7)) {
                    i7++;
                }
                int i8 = i7 - i6;
                if (i8 > 0) {
                    encodeBinary(subBytes(eCIInput, i6, i7), 0, i8, i6 == i3 ? i5 : 1, sb);
                    i6 = i7;
                } else {
                    return;
                }
            } else {
                encodingECI(eCIInput.getECIValue(i6), sb);
                i6++;
            }
        }
    }

    private static void encodeNumeric(ECIInput eCIInput, int i3, int i4, StringBuilder sb) {
        StringBuilder sb2 = new StringBuilder((i4 / 3) + 1);
        BigInteger valueOf = BigInteger.valueOf(900);
        BigInteger valueOf2 = BigInteger.valueOf(0);
        int i5 = 0;
        while (i5 < i4) {
            sb2.setLength(0);
            int min = Math.min(44, i4 - i5);
            StringBuilder sb3 = new StringBuilder("1");
            int i6 = i3 + i5;
            sb3.append(eCIInput.subSequence(i6, i6 + min));
            BigInteger bigInteger = new BigInteger(sb3.toString());
            do {
                sb2.append((char) bigInteger.mod(valueOf).intValue());
                bigInteger = bigInteger.divide(valueOf);
            } while (!bigInteger.equals(valueOf2));
            for (int length = sb2.length() - 1; length >= 0; length--) {
                sb.append(sb2.charAt(length));
            }
            i5 += min;
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:70:0x010b A[EDGE_INSN: B:70:0x010b->B:58:0x010b ?: BREAK  , SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:76:0x000f A[SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static int encodeText(com.google.zxing.common.ECIInput r16, int r17, int r18, java.lang.StringBuilder r19, int r20) throws com.google.zxing.WriterException {
        /*
            r0 = r16
            r1 = r18
            r2 = r19
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>(r1)
            r4 = 0
            r5 = r20
            r6 = r4
        L_0x000f:
            int r7 = r17 + r6
            boolean r8 = r0.isECI(r7)
            if (r8 == 0) goto L_0x0021
            int r7 = r0.getECIValue(r7)
            encodingECI(r7, r2)
            int r6 = r6 + 1
            goto L_0x000f
        L_0x0021:
            char r8 = r0.charAt(r7)
            r9 = 26
            r10 = 32
            r11 = 27
            r12 = 28
            r13 = 29
            r14 = 2
            r15 = 1
            if (r5 == 0) goto L_0x00d4
            if (r5 == r15) goto L_0x0099
            if (r5 == r14) goto L_0x004c
            boolean r7 = isPunctuation(r8)
            if (r7 == 0) goto L_0x0047
            byte[] r7 = PUNCTUATION
            byte r7 = r7[r8]
            char r7 = (char) r7
            r3.append(r7)
            goto L_0x0107
        L_0x0047:
            r3.append(r13)
        L_0x004a:
            r5 = r4
            goto L_0x000f
        L_0x004c:
            boolean r9 = isMixed(r8)
            if (r9 == 0) goto L_0x005c
            byte[] r7 = MIXED
            byte r7 = r7[r8]
            char r7 = (char) r7
            r3.append(r7)
            goto L_0x0107
        L_0x005c:
            boolean r9 = isAlphaUpper(r8)
            if (r9 == 0) goto L_0x0066
            r3.append(r12)
            goto L_0x004a
        L_0x0066:
            boolean r9 = isAlphaLower(r8)
            if (r9 == 0) goto L_0x0071
            r3.append(r11)
        L_0x006f:
            r5 = r15
            goto L_0x000f
        L_0x0071:
            int r7 = r7 + 1
            if (r7 >= r1) goto L_0x008c
            boolean r9 = r0.isECI(r7)
            if (r9 != 0) goto L_0x008c
            char r7 = r0.charAt(r7)
            boolean r7 = isPunctuation(r7)
            if (r7 == 0) goto L_0x008c
            r5 = 25
            r3.append(r5)
            r5 = 3
            goto L_0x000f
        L_0x008c:
            r3.append(r13)
            byte[] r7 = PUNCTUATION
            byte r7 = r7[r8]
            char r7 = (char) r7
            r3.append(r7)
            goto L_0x0107
        L_0x0099:
            boolean r7 = isAlphaLower(r8)
            if (r7 == 0) goto L_0x00ac
            if (r8 != r10) goto L_0x00a5
            r3.append(r9)
            goto L_0x0107
        L_0x00a5:
            int r8 = r8 + -97
            char r7 = (char) r8
            r3.append(r7)
            goto L_0x0107
        L_0x00ac:
            boolean r7 = isAlphaUpper(r8)
            if (r7 == 0) goto L_0x00bc
            r3.append(r11)
            int r8 = r8 + -65
            char r7 = (char) r8
            r3.append(r7)
            goto L_0x0107
        L_0x00bc:
            boolean r7 = isMixed(r8)
            if (r7 == 0) goto L_0x00c8
            r3.append(r12)
        L_0x00c5:
            r5 = r14
            goto L_0x000f
        L_0x00c8:
            r3.append(r13)
            byte[] r7 = PUNCTUATION
            byte r7 = r7[r8]
            char r7 = (char) r7
            r3.append(r7)
            goto L_0x0107
        L_0x00d4:
            boolean r7 = isAlphaUpper(r8)
            if (r7 == 0) goto L_0x00e7
            if (r8 != r10) goto L_0x00e0
            r3.append(r9)
            goto L_0x0107
        L_0x00e0:
            int r8 = r8 + -65
            char r7 = (char) r8
            r3.append(r7)
            goto L_0x0107
        L_0x00e7:
            boolean r7 = isAlphaLower(r8)
            if (r7 == 0) goto L_0x00f2
            r3.append(r11)
            goto L_0x006f
        L_0x00f2:
            boolean r7 = isMixed(r8)
            if (r7 == 0) goto L_0x00fc
            r3.append(r12)
            goto L_0x00c5
        L_0x00fc:
            r3.append(r13)
            byte[] r7 = PUNCTUATION
            byte r7 = r7[r8]
            char r7 = (char) r7
            r3.append(r7)
        L_0x0107:
            int r6 = r6 + 1
            if (r6 < r1) goto L_0x000f
            int r0 = r3.length()
            r1 = r4
        L_0x0110:
            if (r4 >= r0) goto L_0x0129
            int r6 = r4 % 2
            if (r6 == 0) goto L_0x0122
            int r1 = r1 * 30
            char r6 = r3.charAt(r4)
            int r6 = r6 + r1
            char r1 = (char) r6
            r2.append(r1)
            goto L_0x0126
        L_0x0122:
            char r1 = r3.charAt(r4)
        L_0x0126:
            int r4 = r4 + 1
            goto L_0x0110
        L_0x0129:
            int r0 = r0 % r14
            if (r0 == 0) goto L_0x0133
            int r1 = r1 * 30
            int r1 = r1 + r13
            char r0 = (char) r1
            r2.append(r0)
        L_0x0133:
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.zxing.pdf417.encoder.PDF417HighLevelEncoder.encodeText(com.google.zxing.common.ECIInput, int, int, java.lang.StringBuilder, int):int");
    }

    private static void encodingECI(int i3, StringBuilder sb) throws WriterException {
        if (i3 >= 0 && i3 < 900) {
            sb.append(927);
            sb.append((char) i3);
        } else if (i3 < 810900) {
            sb.append(926);
            sb.append((char) ((i3 / 900) - 1));
            sb.append((char) (i3 % 900));
        } else if (i3 < 811800) {
            sb.append(925);
            sb.append((char) (810900 - i3));
        } else {
            throw new WriterException(a.k("ECI number not in valid range from 0..811799, but was ", i3));
        }
    }

    private static boolean isAlphaLower(char c3) {
        return c3 == ' ' || (c3 >= 'a' && c3 <= 'z');
    }

    private static boolean isAlphaUpper(char c3) {
        return c3 == ' ' || (c3 >= 'A' && c3 <= 'Z');
    }

    private static boolean isDigit(char c3) {
        return c3 >= '0' && c3 <= '9';
    }

    private static boolean isMixed(char c3) {
        return MIXED[c3] != -1;
    }

    private static boolean isPunctuation(char c3) {
        return PUNCTUATION[c3] != -1;
    }

    private static boolean isText(char c3) {
        return c3 == 9 || c3 == 10 || c3 == 13 || (c3 >= ' ' && c3 <= '~');
    }

    public static byte[] subBytes(ECIInput eCIInput, int i3, int i4) {
        byte[] bArr = new byte[(i4 - i3)];
        for (int i5 = i3; i5 < i4; i5++) {
            bArr[i5 - i3] = (byte) (eCIInput.charAt(i5) & 255);
        }
        return bArr;
    }
}
