package com.google.zxing.qrcode.encoder;

import A.a;
import androidx.compose.runtime.b;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitArray;
import com.google.zxing.common.CharacterSetECI;
import com.google.zxing.common.StringUtils;
import com.google.zxing.common.reedsolomon.GenericGF;
import com.google.zxing.common.reedsolomon.ReedSolomonEncoder;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import com.google.zxing.qrcode.decoder.Mode;
import com.google.zxing.qrcode.decoder.Version;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

public final class Encoder {
    private static final int[] ALPHANUMERIC_TABLE = {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 36, -1, -1, -1, 37, 38, -1, -1, -1, -1, 39, 40, -1, 41, 42, 43, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 44, -1, -1, -1, -1, -1, -1, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, -1, -1, -1, -1, -1};
    static final Charset DEFAULT_BYTE_MODE_ENCODING = StandardCharsets.ISO_8859_1;

    /* renamed from: com.google.zxing.qrcode.encoder.Encoder$1  reason: invalid class name */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$google$zxing$qrcode$decoder$Mode;

        /* JADX WARNING: Can't wrap try/catch for region: R(8:0|1|2|3|4|5|6|(3:7|8|10)) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001d */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0028 */
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
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.zxing.qrcode.encoder.Encoder.AnonymousClass1.<clinit>():void");
        }
    }

    private Encoder() {
    }

    public static void append8BitBytes(String str, BitArray bitArray, Charset charset) {
        for (byte appendBits : str.getBytes(charset)) {
            bitArray.appendBits(appendBits, 8);
        }
    }

    public static void appendAlphanumericBytes(CharSequence charSequence, BitArray bitArray) throws WriterException {
        int length = charSequence.length();
        int i3 = 0;
        while (i3 < length) {
            int alphanumericCode = getAlphanumericCode(charSequence.charAt(i3));
            if (alphanumericCode != -1) {
                int i4 = i3 + 1;
                if (i4 < length) {
                    int alphanumericCode2 = getAlphanumericCode(charSequence.charAt(i4));
                    if (alphanumericCode2 != -1) {
                        bitArray.appendBits((alphanumericCode * 45) + alphanumericCode2, 11);
                        i3 += 2;
                    } else {
                        throw new WriterException();
                    }
                } else {
                    bitArray.appendBits(alphanumericCode, 6);
                    i3 = i4;
                }
            } else {
                throw new WriterException();
            }
        }
    }

    public static void appendBytes(String str, Mode mode, BitArray bitArray, Charset charset) throws WriterException {
        int i3 = AnonymousClass1.$SwitchMap$com$google$zxing$qrcode$decoder$Mode[mode.ordinal()];
        if (i3 == 1) {
            appendNumericBytes(str, bitArray);
        } else if (i3 == 2) {
            appendAlphanumericBytes(str, bitArray);
        } else if (i3 == 3) {
            append8BitBytes(str, bitArray, charset);
        } else if (i3 == 4) {
            appendKanjiBytes(str, bitArray);
        } else {
            throw new WriterException("Invalid mode: " + mode);
        }
    }

    private static void appendECI(CharacterSetECI characterSetECI, BitArray bitArray) {
        bitArray.appendBits(Mode.ECI.getBits(), 4);
        bitArray.appendBits(characterSetECI.getValue(), 8);
    }

    /* JADX WARNING: Removed duplicated region for block: B:18:0x003e A[LOOP:0: B:5:0x0011->B:18:0x003e, LOOP_END] */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x004d A[SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void appendKanjiBytes(java.lang.String r6, com.google.zxing.common.BitArray r7) throws com.google.zxing.WriterException {
        /*
            java.nio.charset.Charset r0 = com.google.zxing.common.StringUtils.SHIFT_JIS_CHARSET
            if (r0 == 0) goto L_0x005e
            byte[] r6 = r6.getBytes(r0)
            int r0 = r6.length
            int r0 = r0 % 2
            if (r0 != 0) goto L_0x0056
            int r0 = r6.length
            int r0 = r0 + -1
            r1 = 0
        L_0x0011:
            if (r1 >= r0) goto L_0x0055
            byte r2 = r6[r1]
            r2 = r2 & 255(0xff, float:3.57E-43)
            int r3 = r1 + 1
            byte r3 = r6[r3]
            r3 = r3 & 255(0xff, float:3.57E-43)
            int r2 = r2 << 8
            r2 = r2 | r3
            r3 = 33088(0x8140, float:4.6366E-41)
            r4 = -1
            if (r2 < r3) goto L_0x002d
            r5 = 40956(0x9ffc, float:5.7392E-41)
            if (r2 > r5) goto L_0x002d
        L_0x002b:
            int r2 = r2 - r3
            goto L_0x003c
        L_0x002d:
            r3 = 57408(0xe040, float:8.0446E-41)
            if (r2 < r3) goto L_0x003b
            r3 = 60351(0xebbf, float:8.457E-41)
            if (r2 > r3) goto L_0x003b
            r3 = 49472(0xc140, float:6.9325E-41)
            goto L_0x002b
        L_0x003b:
            r2 = r4
        L_0x003c:
            if (r2 == r4) goto L_0x004d
            int r3 = r2 >> 8
            int r3 = r3 * 192
            r2 = r2 & 255(0xff, float:3.57E-43)
            int r3 = r3 + r2
            r2 = 13
            r7.appendBits(r3, r2)
            int r1 = r1 + 2
            goto L_0x0011
        L_0x004d:
            com.google.zxing.WriterException r6 = new com.google.zxing.WriterException
            java.lang.String r7 = "Invalid byte sequence"
            r6.<init>((java.lang.String) r7)
            throw r6
        L_0x0055:
            return
        L_0x0056:
            com.google.zxing.WriterException r6 = new com.google.zxing.WriterException
            java.lang.String r7 = "Kanji byte size not even"
            r6.<init>((java.lang.String) r7)
            throw r6
        L_0x005e:
            com.google.zxing.WriterException r6 = new com.google.zxing.WriterException
            java.lang.String r7 = "SJIS Charset not supported on this platform"
            r6.<init>((java.lang.String) r7)
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.zxing.qrcode.encoder.Encoder.appendKanjiBytes(java.lang.String, com.google.zxing.common.BitArray):void");
    }

    public static void appendLengthInfo(int i3, Version version, Mode mode, BitArray bitArray) throws WriterException {
        int characterCountBits = mode.getCharacterCountBits(version);
        int i4 = 1 << characterCountBits;
        if (i3 < i4) {
            bitArray.appendBits(i3, characterCountBits);
            return;
        }
        throw new WriterException(i3 + " is bigger than " + (i4 - 1));
    }

    public static void appendModeInfo(Mode mode, BitArray bitArray) {
        bitArray.appendBits(mode.getBits(), 4);
    }

    public static void appendNumericBytes(CharSequence charSequence, BitArray bitArray) {
        int length = charSequence.length();
        int i3 = 0;
        while (i3 < length) {
            int charAt = charSequence.charAt(i3) - '0';
            int i4 = i3 + 2;
            if (i4 < length) {
                bitArray.appendBits(b.a(charSequence.charAt(i3 + 1) - '0', 10, charAt * 100, charSequence.charAt(i4) - '0'), 10);
                i3 += 3;
            } else {
                i3++;
                if (i3 < length) {
                    bitArray.appendBits((charAt * 10) + (charSequence.charAt(i3) - '0'), 7);
                    i3 = i4;
                } else {
                    bitArray.appendBits(charAt, 4);
                }
            }
        }
    }

    private static int calculateBitsNeeded(Mode mode, BitArray bitArray, BitArray bitArray2, Version version) {
        return bitArray2.getSize() + mode.getCharacterCountBits(version) + bitArray.getSize();
    }

    private static int calculateMaskPenalty(ByteMatrix byteMatrix) {
        return MaskUtil.applyMaskPenaltyRule1(byteMatrix) + MaskUtil.applyMaskPenaltyRule2(byteMatrix) + MaskUtil.applyMaskPenaltyRule3(byteMatrix) + MaskUtil.applyMaskPenaltyRule4(byteMatrix);
    }

    private static int chooseMaskPattern(BitArray bitArray, ErrorCorrectionLevel errorCorrectionLevel, Version version, ByteMatrix byteMatrix) throws WriterException {
        int i3 = Integer.MAX_VALUE;
        int i4 = -1;
        for (int i5 = 0; i5 < 8; i5++) {
            MatrixUtil.buildMatrix(bitArray, errorCorrectionLevel, version, i5, byteMatrix);
            int calculateMaskPenalty = calculateMaskPenalty(byteMatrix);
            if (calculateMaskPenalty < i3) {
                i4 = i5;
                i3 = calculateMaskPenalty;
            }
        }
        return i4;
    }

    public static Mode chooseMode(String str) {
        return chooseMode(str, (Charset) null);
    }

    private static Version chooseVersion(int i3, ErrorCorrectionLevel errorCorrectionLevel) throws WriterException {
        for (int i4 = 1; i4 <= 40; i4++) {
            Version versionForNumber = Version.getVersionForNumber(i4);
            if (willFit(i3, versionForNumber, errorCorrectionLevel)) {
                return versionForNumber;
            }
        }
        throw new WriterException("Data too big");
    }

    public static QRCode encode(String str, ErrorCorrectionLevel errorCorrectionLevel) throws WriterException {
        return encode(str, errorCorrectionLevel, (Map<EncodeHintType, ?>) null);
    }

    public static byte[] generateECBytes(byte[] bArr, int i3) {
        int length = bArr.length;
        int[] iArr = new int[(length + i3)];
        for (int i4 = 0; i4 < length; i4++) {
            iArr[i4] = bArr[i4] & 255;
        }
        new ReedSolomonEncoder(GenericGF.QR_CODE_FIELD_256).encode(iArr, i3);
        byte[] bArr2 = new byte[i3];
        for (int i5 = 0; i5 < i3; i5++) {
            bArr2[i5] = (byte) iArr[length + i5];
        }
        return bArr2;
    }

    public static int getAlphanumericCode(int i3) {
        int[] iArr = ALPHANUMERIC_TABLE;
        if (i3 < iArr.length) {
            return iArr[i3];
        }
        return -1;
    }

    public static void getNumDataBytesAndNumECBytesForBlockID(int i3, int i4, int i5, int i6, int[] iArr, int[] iArr2) throws WriterException {
        if (i6 < i5) {
            int i7 = i3 % i5;
            int i8 = i5 - i7;
            int i9 = i3 / i5;
            int i10 = i9 + 1;
            int i11 = i4 / i5;
            int i12 = i11 + 1;
            int i13 = i9 - i11;
            int i14 = i10 - i12;
            if (i13 != i14) {
                throw new WriterException("EC bytes mismatch");
            } else if (i5 == i8 + i7) {
                if (i3 != ((i12 + i14) * i7) + ((i11 + i13) * i8)) {
                    throw new WriterException("Total bytes mismatch");
                } else if (i6 < i8) {
                    iArr[0] = i11;
                    iArr2[0] = i13;
                } else {
                    iArr[0] = i12;
                    iArr2[0] = i14;
                }
            } else {
                throw new WriterException("RS blocks mismatch");
            }
        } else {
            throw new WriterException("Block ID too large");
        }
    }

    public static BitArray interleaveWithECBytes(BitArray bitArray, int i3, int i4, int i5) throws WriterException {
        int i6 = i3;
        int i7 = i4;
        int i8 = i5;
        if (bitArray.getSizeInBytes() == i7) {
            ArrayList arrayList = new ArrayList(i8);
            int i9 = 0;
            int i10 = 0;
            int i11 = 0;
            for (int i12 = 0; i12 < i8; i12++) {
                int[] iArr = new int[1];
                int[] iArr2 = new int[1];
                getNumDataBytesAndNumECBytesForBlockID(i3, i4, i5, i12, iArr, iArr2);
                int i13 = iArr[0];
                byte[] bArr = new byte[i13];
                bitArray.toBytes(i9 * 8, bArr, 0, i13);
                byte[] generateECBytes = generateECBytes(bArr, iArr2[0]);
                arrayList.add(new BlockPair(bArr, generateECBytes));
                i10 = Math.max(i10, i13);
                i11 = Math.max(i11, generateECBytes.length);
                i9 += iArr[0];
            }
            if (i7 == i9) {
                BitArray bitArray2 = new BitArray();
                for (int i14 = 0; i14 < i10; i14++) {
                    Iterator it = arrayList.iterator();
                    while (it.hasNext()) {
                        byte[] dataBytes = ((BlockPair) it.next()).getDataBytes();
                        if (i14 < dataBytes.length) {
                            bitArray2.appendBits(dataBytes[i14], 8);
                        }
                    }
                }
                for (int i15 = 0; i15 < i11; i15++) {
                    Iterator it2 = arrayList.iterator();
                    while (it2.hasNext()) {
                        byte[] errorCorrectionBytes = ((BlockPair) it2.next()).getErrorCorrectionBytes();
                        if (i15 < errorCorrectionBytes.length) {
                            bitArray2.appendBits(errorCorrectionBytes[i15], 8);
                        }
                    }
                }
                if (i6 == bitArray2.getSizeInBytes()) {
                    return bitArray2;
                }
                StringBuilder o3 = a.o(i6, "Interleaving error: ", " and ");
                o3.append(bitArray2.getSizeInBytes());
                o3.append(" differ.");
                throw new WriterException(o3.toString());
            }
            throw new WriterException("Data bytes does not match offset");
        }
        throw new WriterException("Number of bits and data bytes does not match");
    }

    public static boolean isOnlyDoubleByteKanji(String str) {
        byte[] bytes = str.getBytes(StringUtils.SHIFT_JIS_CHARSET);
        int length = bytes.length;
        if (length % 2 != 0) {
            return false;
        }
        for (int i3 = 0; i3 < length; i3 += 2) {
            byte b3 = bytes[i3] & 255;
            if ((b3 < 129 || b3 > 159) && (b3 < 224 || b3 > 235)) {
                return false;
            }
        }
        return true;
    }

    private static Version recommendVersion(ErrorCorrectionLevel errorCorrectionLevel, Mode mode, BitArray bitArray, BitArray bitArray2) throws WriterException {
        return chooseVersion(calculateBitsNeeded(mode, bitArray, bitArray2, chooseVersion(calculateBitsNeeded(mode, bitArray, bitArray2, Version.getVersionForNumber(1)), errorCorrectionLevel)), errorCorrectionLevel);
    }

    public static void terminateBits(int i3, BitArray bitArray) throws WriterException {
        int i4 = i3 * 8;
        if (bitArray.getSize() <= i4) {
            for (int i5 = 0; i5 < 4 && bitArray.getSize() < i4; i5++) {
                bitArray.appendBit(false);
            }
            int size = bitArray.getSize() & 7;
            if (size > 0) {
                while (size < 8) {
                    bitArray.appendBit(false);
                    size++;
                }
            }
            int sizeInBytes = i3 - bitArray.getSizeInBytes();
            for (int i6 = 0; i6 < sizeInBytes; i6++) {
                bitArray.appendBits((i6 & 1) == 0 ? 236 : 17, 8);
            }
            if (bitArray.getSize() != i4) {
                throw new WriterException("Bits size does not equal capacity");
            }
            return;
        }
        throw new WriterException("data bits cannot fit in the QR Code" + bitArray.getSize() + " > " + i4);
    }

    public static boolean willFit(int i3, Version version, ErrorCorrectionLevel errorCorrectionLevel) {
        return version.getTotalCodewords() - version.getECBlocksForLevel(errorCorrectionLevel).getTotalECCodewords() >= (i3 + 7) / 8;
    }

    private static Mode chooseMode(String str, Charset charset) {
        Charset charset2 = StringUtils.SHIFT_JIS_CHARSET;
        if (charset2 != null && charset2.equals(charset) && isOnlyDoubleByteKanji(str)) {
            return Mode.KANJI;
        }
        boolean z2 = false;
        boolean z3 = false;
        for (int i3 = 0; i3 < str.length(); i3++) {
            char charAt = str.charAt(i3);
            if (charAt >= '0' && charAt <= '9') {
                z3 = true;
            } else if (getAlphanumericCode(charAt) == -1) {
                return Mode.BYTE;
            } else {
                z2 = true;
            }
        }
        if (z2) {
            return Mode.ALPHANUMERIC;
        }
        if (z3) {
            return Mode.NUMERIC;
        }
        return Mode.BYTE;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:56:0x013a, code lost:
        if (com.google.zxing.qrcode.encoder.QRCode.isValidMaskPattern(r8) != false) goto L_0x013e;
     */
    /* JADX WARNING: Removed duplicated region for block: B:21:0x0047 A[SYNTHETIC, Splitter:B:21:0x0047] */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x0057  */
    /* JADX WARNING: Removed duplicated region for block: B:29:0x0075  */
    /* JADX WARNING: Removed duplicated region for block: B:48:0x00da  */
    /* JADX WARNING: Removed duplicated region for block: B:49:0x00df  */
    /* JADX WARNING: Removed duplicated region for block: B:53:0x0122  */
    /* JADX WARNING: Removed duplicated region for block: B:59:0x0140  */
    /* JADX WARNING: Removed duplicated region for block: B:9:0x001f  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static com.google.zxing.qrcode.encoder.QRCode encode(java.lang.String r6, com.google.zxing.qrcode.decoder.ErrorCorrectionLevel r7, java.util.Map<com.google.zxing.EncodeHintType, ?> r8) throws com.google.zxing.WriterException {
        /*
            r0 = 0
            r1 = 1
            if (r8 == 0) goto L_0x001c
            com.google.zxing.EncodeHintType r2 = com.google.zxing.EncodeHintType.GS1_FORMAT
            boolean r3 = r8.containsKey(r2)
            if (r3 == 0) goto L_0x001c
            java.lang.Object r2 = r8.get(r2)
            java.lang.String r2 = r2.toString()
            boolean r2 = java.lang.Boolean.parseBoolean(r2)
            if (r2 == 0) goto L_0x001c
            r2 = r1
            goto L_0x001d
        L_0x001c:
            r2 = r0
        L_0x001d:
            if (r8 == 0) goto L_0x0037
            com.google.zxing.EncodeHintType r3 = com.google.zxing.EncodeHintType.QR_COMPACT
            boolean r4 = r8.containsKey(r3)
            if (r4 == 0) goto L_0x0037
            java.lang.Object r3 = r8.get(r3)
            java.lang.String r3 = r3.toString()
            boolean r3 = java.lang.Boolean.parseBoolean(r3)
            if (r3 == 0) goto L_0x0037
            r3 = r1
            goto L_0x0038
        L_0x0037:
            r3 = r0
        L_0x0038:
            java.nio.charset.Charset r4 = DEFAULT_BYTE_MODE_ENCODING
            if (r8 == 0) goto L_0x0045
            com.google.zxing.EncodeHintType r5 = com.google.zxing.EncodeHintType.CHARACTER_SET
            boolean r5 = r8.containsKey(r5)
            if (r5 == 0) goto L_0x0045
            r0 = r1
        L_0x0045:
            if (r0 == 0) goto L_0x0055
            com.google.zxing.EncodeHintType r1 = com.google.zxing.EncodeHintType.CHARACTER_SET     // Catch:{ UnsupportedCharsetException -> 0x0055 }
            java.lang.Object r1 = r8.get(r1)     // Catch:{ UnsupportedCharsetException -> 0x0055 }
            java.lang.String r1 = r1.toString()     // Catch:{ UnsupportedCharsetException -> 0x0055 }
            java.nio.charset.Charset r4 = java.nio.charset.Charset.forName(r1)     // Catch:{ UnsupportedCharsetException -> 0x0055 }
        L_0x0055:
            if (r3 == 0) goto L_0x0075
            com.google.zxing.qrcode.decoder.Mode r0 = com.google.zxing.qrcode.decoder.Mode.BYTE
            java.nio.charset.Charset r1 = DEFAULT_BYTE_MODE_ENCODING
            boolean r1 = r4.equals(r1)
            r3 = 0
            if (r1 == 0) goto L_0x0063
            r4 = r3
        L_0x0063:
            com.google.zxing.qrcode.encoder.MinimalEncoder$ResultList r6 = com.google.zxing.qrcode.encoder.MinimalEncoder.encode(r6, r3, r4, r2, r7)
            com.google.zxing.common.BitArray r1 = new com.google.zxing.common.BitArray
            r1.<init>()
            r6.getBits(r1)
            com.google.zxing.qrcode.decoder.Version r6 = r6.getVersion()
            goto L_0x00ec
        L_0x0075:
            com.google.zxing.qrcode.decoder.Mode r1 = chooseMode(r6, r4)
            com.google.zxing.common.BitArray r3 = new com.google.zxing.common.BitArray
            r3.<init>()
            com.google.zxing.qrcode.decoder.Mode r5 = com.google.zxing.qrcode.decoder.Mode.BYTE
            if (r1 != r5) goto L_0x008d
            if (r0 == 0) goto L_0x008d
            com.google.zxing.common.CharacterSetECI r0 = com.google.zxing.common.CharacterSetECI.getCharacterSetECI(r4)
            if (r0 == 0) goto L_0x008d
            appendECI(r0, r3)
        L_0x008d:
            if (r2 == 0) goto L_0x0094
            com.google.zxing.qrcode.decoder.Mode r0 = com.google.zxing.qrcode.decoder.Mode.FNC1_FIRST_POSITION
            appendModeInfo(r0, r3)
        L_0x0094:
            appendModeInfo(r1, r3)
            com.google.zxing.common.BitArray r0 = new com.google.zxing.common.BitArray
            r0.<init>()
            appendBytes(r6, r1, r0, r4)
            if (r8 == 0) goto L_0x00cc
            com.google.zxing.EncodeHintType r2 = com.google.zxing.EncodeHintType.QR_VERSION
            boolean r4 = r8.containsKey(r2)
            if (r4 == 0) goto L_0x00cc
            java.lang.Object r2 = r8.get(r2)
            java.lang.String r2 = r2.toString()
            int r2 = java.lang.Integer.parseInt(r2)
            com.google.zxing.qrcode.decoder.Version r2 = com.google.zxing.qrcode.decoder.Version.getVersionForNumber(r2)
            int r4 = calculateBitsNeeded(r1, r3, r0, r2)
            boolean r4 = willFit(r4, r2, r7)
            if (r4 == 0) goto L_0x00c4
            goto L_0x00d0
        L_0x00c4:
            com.google.zxing.WriterException r6 = new com.google.zxing.WriterException
            java.lang.String r7 = "Data too big for requested version"
            r6.<init>((java.lang.String) r7)
            throw r6
        L_0x00cc:
            com.google.zxing.qrcode.decoder.Version r2 = recommendVersion(r7, r1, r3, r0)
        L_0x00d0:
            com.google.zxing.common.BitArray r4 = new com.google.zxing.common.BitArray
            r4.<init>()
            r4.appendBitArray(r3)
            if (r1 != r5) goto L_0x00df
            int r6 = r0.getSizeInBytes()
            goto L_0x00e3
        L_0x00df:
            int r6 = r6.length()
        L_0x00e3:
            appendLengthInfo(r6, r2, r1, r4)
            r4.appendBitArray(r0)
            r0 = r1
            r6 = r2
            r1 = r4
        L_0x00ec:
            com.google.zxing.qrcode.decoder.Version$ECBlocks r2 = r6.getECBlocksForLevel(r7)
            int r3 = r6.getTotalCodewords()
            int r4 = r2.getTotalECCodewords()
            int r3 = r3 - r4
            terminateBits(r3, r1)
            int r4 = r6.getTotalCodewords()
            int r2 = r2.getNumBlocks()
            com.google.zxing.common.BitArray r1 = interleaveWithECBytes(r1, r4, r3, r2)
            com.google.zxing.qrcode.encoder.QRCode r2 = new com.google.zxing.qrcode.encoder.QRCode
            r2.<init>()
            r2.setECLevel(r7)
            r2.setMode(r0)
            r2.setVersion(r6)
            int r0 = r6.getDimensionForVersion()
            com.google.zxing.qrcode.encoder.ByteMatrix r3 = new com.google.zxing.qrcode.encoder.ByteMatrix
            r3.<init>(r0, r0)
            r0 = -1
            if (r8 == 0) goto L_0x013d
            com.google.zxing.EncodeHintType r4 = com.google.zxing.EncodeHintType.QR_MASK_PATTERN
            boolean r5 = r8.containsKey(r4)
            if (r5 == 0) goto L_0x013d
            java.lang.Object r8 = r8.get(r4)
            java.lang.String r8 = r8.toString()
            int r8 = java.lang.Integer.parseInt(r8)
            boolean r4 = com.google.zxing.qrcode.encoder.QRCode.isValidMaskPattern(r8)
            if (r4 == 0) goto L_0x013d
            goto L_0x013e
        L_0x013d:
            r8 = r0
        L_0x013e:
            if (r8 != r0) goto L_0x0144
            int r8 = chooseMaskPattern(r1, r7, r6, r3)
        L_0x0144:
            r2.setMaskPattern(r8)
            com.google.zxing.qrcode.encoder.MatrixUtil.buildMatrix(r1, r7, r6, r8, r3)
            r2.setMatrix(r3)
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.zxing.qrcode.encoder.Encoder.encode(java.lang.String, com.google.zxing.qrcode.decoder.ErrorCorrectionLevel, java.util.Map):com.google.zxing.qrcode.encoder.QRCode");
    }
}
