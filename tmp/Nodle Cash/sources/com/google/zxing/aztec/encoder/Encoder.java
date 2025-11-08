package com.google.zxing.aztec.encoder;

import A.a;
import androidx.camera.camera2.internal.C0118y;
import androidx.constraintlayout.core.state.b;
import com.google.zxing.common.BitArray;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.reedsolomon.GenericGF;
import com.google.zxing.common.reedsolomon.ReedSolomonEncoder;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

public final class Encoder {
    public static final int DEFAULT_AZTEC_LAYERS = 0;
    public static final int DEFAULT_EC_PERCENT = 33;
    private static final int MAX_NB_BITS = 32;
    private static final int MAX_NB_BITS_COMPACT = 4;
    private static final int[] WORD_SIZE = {4, 6, 6, 8, 8, 8, 8, 8, 8, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12};

    private Encoder() {
    }

    private static int[] bitsToWords(BitArray bitArray, int i3, int i4) {
        int[] iArr = new int[i4];
        int size = bitArray.getSize() / i3;
        for (int i5 = 0; i5 < size; i5++) {
            int i6 = 0;
            for (int i7 = 0; i7 < i3; i7++) {
                i6 |= bitArray.get((i5 * i3) + i7) ? 1 << ((i3 - i7) - 1) : 0;
            }
            iArr[i5] = i6;
        }
        return iArr;
    }

    private static void drawBullsEye(BitMatrix bitMatrix, int i3, int i4) {
        for (int i5 = 0; i5 < i4; i5 += 2) {
            int i6 = i3 - i5;
            int i7 = i6;
            while (true) {
                int i8 = i3 + i5;
                if (i7 > i8) {
                    break;
                }
                bitMatrix.set(i7, i6);
                bitMatrix.set(i7, i8);
                bitMatrix.set(i6, i7);
                bitMatrix.set(i8, i7);
                i7++;
            }
        }
        int i9 = i3 - i4;
        bitMatrix.set(i9, i9);
        int i10 = i9 + 1;
        bitMatrix.set(i10, i9);
        bitMatrix.set(i9, i10);
        int i11 = i3 + i4;
        bitMatrix.set(i11, i9);
        bitMatrix.set(i11, i10);
        bitMatrix.set(i11, i11 - 1);
    }

    private static void drawModeMessage(BitMatrix bitMatrix, boolean z2, int i3, BitArray bitArray) {
        int i4 = i3 / 2;
        int i5 = 0;
        if (z2) {
            while (i5 < 7) {
                int i6 = (i4 - 3) + i5;
                if (bitArray.get(i5)) {
                    bitMatrix.set(i6, i4 - 5);
                }
                if (bitArray.get(i5 + 7)) {
                    bitMatrix.set(i4 + 5, i6);
                }
                if (bitArray.get(20 - i5)) {
                    bitMatrix.set(i6, i4 + 5);
                }
                if (bitArray.get(27 - i5)) {
                    bitMatrix.set(i4 - 5, i6);
                }
                i5++;
            }
            return;
        }
        while (i5 < 10) {
            int i7 = (i5 / 5) + (i4 - 5) + i5;
            if (bitArray.get(i5)) {
                bitMatrix.set(i7, i4 - 7);
            }
            if (bitArray.get(i5 + 10)) {
                bitMatrix.set(i4 + 7, i7);
            }
            if (bitArray.get(29 - i5)) {
                bitMatrix.set(i7, i4 + 7);
            }
            if (bitArray.get(39 - i5)) {
                bitMatrix.set(i4 - 7, i7);
            }
            i5++;
        }
    }

    public static AztecCode encode(String str) {
        return encode(str.getBytes(StandardCharsets.ISO_8859_1));
    }

    private static BitArray generateCheckWords(BitArray bitArray, int i3, int i4) {
        ReedSolomonEncoder reedSolomonEncoder = new ReedSolomonEncoder(getGF(i4));
        int i5 = i3 / i4;
        int[] bitsToWords = bitsToWords(bitArray, i4, i5);
        reedSolomonEncoder.encode(bitsToWords, i5 - (bitArray.getSize() / i4));
        BitArray bitArray2 = new BitArray();
        bitArray2.appendBits(0, i3 % i4);
        for (int appendBits : bitsToWords) {
            bitArray2.appendBits(appendBits, i4);
        }
        return bitArray2;
    }

    public static BitArray generateModeMessage(boolean z2, int i3, int i4) {
        BitArray bitArray = new BitArray();
        if (z2) {
            bitArray.appendBits(i3 - 1, 2);
            bitArray.appendBits(i4 - 1, 6);
            return generateCheckWords(bitArray, 28, 4);
        }
        bitArray.appendBits(i3 - 1, 5);
        bitArray.appendBits(i4 - 1, 11);
        return generateCheckWords(bitArray, 40, 4);
    }

    private static GenericGF getGF(int i3) {
        if (i3 == 4) {
            return GenericGF.AZTEC_PARAM;
        }
        if (i3 == 6) {
            return GenericGF.AZTEC_DATA_6;
        }
        if (i3 == 8) {
            return GenericGF.AZTEC_DATA_8;
        }
        if (i3 == 10) {
            return GenericGF.AZTEC_DATA_10;
        }
        if (i3 == 12) {
            return GenericGF.AZTEC_DATA_12;
        }
        throw new IllegalArgumentException(a.k("Unsupported word size ", i3));
    }

    public static BitArray stuffBits(BitArray bitArray, int i3) {
        BitArray bitArray2 = new BitArray();
        int size = bitArray.getSize();
        int i4 = (1 << i3) - 2;
        int i5 = 0;
        while (i5 < size) {
            int i6 = 0;
            for (int i7 = 0; i7 < i3; i7++) {
                int i8 = i5 + i7;
                if (i8 >= size || bitArray.get(i8)) {
                    i6 |= 1 << ((i3 - 1) - i7);
                }
            }
            int i9 = i6 & i4;
            if (i9 == i4) {
                bitArray2.appendBits(i9, i3);
            } else if (i9 == 0) {
                bitArray2.appendBits(i6 | 1, i3);
            } else {
                bitArray2.appendBits(i6, i3);
                i5 += i3;
            }
            i5--;
            i5 += i3;
        }
        return bitArray2;
    }

    private static int totalBitsInLayer(int i3, boolean z2) {
        return b.A(i3, 16, z2 ? 88 : 112, i3);
    }

    public static AztecCode encode(String str, int i3, int i4) {
        return encode(str.getBytes(StandardCharsets.ISO_8859_1), i3, i4, (Charset) null);
    }

    public static AztecCode encode(String str, int i3, int i4, Charset charset) {
        return encode(str.getBytes(charset != null ? charset : StandardCharsets.ISO_8859_1), i3, i4, charset);
    }

    public static AztecCode encode(byte[] bArr) {
        return encode(bArr, 33, 0, (Charset) null);
    }

    public static AztecCode encode(byte[] bArr, int i3, int i4) {
        return encode(bArr, i3, i4, (Charset) null);
    }

    public static AztecCode encode(byte[] bArr, int i3, int i4, Charset charset) {
        int i5;
        int i6;
        boolean z2;
        BitArray bitArray;
        int i7;
        int i8;
        int i9 = i4;
        BitArray encode = new HighLevelEncoder(bArr, charset).encode();
        int size = ((encode.getSize() * i3) / 100) + 11;
        int size2 = encode.getSize() + size;
        int i10 = 32;
        int i11 = 4;
        if (i9 != 0) {
            z2 = i9 < 0;
            i5 = Math.abs(i4);
            if (z2) {
                i10 = 4;
            }
            if (i5 <= i10) {
                i7 = totalBitsInLayer(i5, z2);
                i6 = WORD_SIZE[i5];
                int i12 = i7 - (i7 % i6);
                bitArray = stuffBits(encode, i6);
                if (bitArray.getSize() + size > i12) {
                    throw new IllegalArgumentException("Data to large for user specified layer");
                } else if (z2 && bitArray.getSize() > i6 * 64) {
                    throw new IllegalArgumentException("Data to large for user specified layer");
                }
            } else {
                throw new IllegalArgumentException(C0118y.c(i9, "Illegal value ", " for layers"));
            }
        } else {
            BitArray bitArray2 = null;
            int i13 = 0;
            int i14 = 0;
            while (i13 <= 32) {
                boolean z3 = i13 <= 3;
                int i15 = z3 ? i13 + 1 : i13;
                int i16 = totalBitsInLayer(i15, z3);
                if (size2 <= i16) {
                    if (bitArray2 == null || i14 != WORD_SIZE[i15]) {
                        int i17 = WORD_SIZE[i15];
                        i14 = i17;
                        bitArray2 = stuffBits(encode, i17);
                    }
                    int i18 = i16 - (i16 % i14);
                    if ((!z3 || bitArray2.getSize() <= i14 * 64) && bitArray2.getSize() + size <= i18) {
                        bitArray = bitArray2;
                        i6 = i14;
                        z2 = z3;
                        i5 = i15;
                        i7 = i16;
                    }
                }
                i13++;
                i11 = 4;
            }
            throw new IllegalArgumentException("Data too large for an Aztec code");
        }
        BitArray generateCheckWords = generateCheckWords(bitArray, i7, i6);
        int size3 = bitArray.getSize() / i6;
        BitArray generateModeMessage = generateModeMessage(z2, i5, size3);
        int i19 = (i5 * 4) + (z2 ? 11 : 14);
        int[] iArr = new int[i19];
        int i20 = 2;
        if (z2) {
            for (int i21 = 0; i21 < i19; i21++) {
                iArr[i21] = i21;
            }
            i8 = i19;
        } else {
            int i22 = i19 / 2;
            i8 = (((i22 - 1) / 15) * 2) + i19 + 1;
            int i23 = i8 / 2;
            for (int i24 = 0; i24 < i22; i24++) {
                int i25 = (i24 / 15) + i24;
                iArr[(i22 - i24) - 1] = (i23 - i25) - 1;
                iArr[i22 + i24] = i25 + i23 + 1;
            }
        }
        BitMatrix bitMatrix = new BitMatrix(i8);
        int i26 = 0;
        int i27 = 0;
        while (i26 < i5) {
            int i28 = ((i5 - i26) * i11) + (z2 ? 9 : 12);
            int i29 = 0;
            while (i29 < i28) {
                int i30 = i29 * 2;
                int i31 = 0;
                while (i31 < i20) {
                    if (generateCheckWords.get(i27 + i30 + i31)) {
                        int i32 = i26 * 2;
                        bitMatrix.set(iArr[i32 + i31], iArr[i32 + i29]);
                    }
                    if (generateCheckWords.get((i28 * 2) + i27 + i30 + i31)) {
                        int i33 = i26 * 2;
                        bitMatrix.set(iArr[i33 + i29], iArr[((i19 - 1) - i33) - i31]);
                    }
                    if (generateCheckWords.get((i28 * 4) + i27 + i30 + i31)) {
                        int i34 = (i19 - 1) - (i26 * 2);
                        bitMatrix.set(iArr[i34 - i31], iArr[i34 - i29]);
                    }
                    if (generateCheckWords.get((i28 * 6) + i27 + i30 + i31)) {
                        int i35 = i26 * 2;
                        bitMatrix.set(iArr[((i19 - 1) - i35) - i29], iArr[i35 + i31]);
                    }
                    i31++;
                    i20 = 2;
                }
                i29++;
                i20 = 2;
            }
            i27 += i28 * 8;
            i26++;
            i11 = 4;
            i20 = 2;
        }
        drawModeMessage(bitMatrix, z2, i8, generateModeMessage);
        if (z2) {
            drawBullsEye(bitMatrix, i8 / 2, 5);
        } else {
            int i36 = i8 / 2;
            drawBullsEye(bitMatrix, i36, 7);
            int i37 = 0;
            int i38 = 0;
            while (i38 < (i19 / 2) - 1) {
                for (int i39 = i36 & 1; i39 < i8; i39 += 2) {
                    int i40 = i36 - i37;
                    bitMatrix.set(i40, i39);
                    int i41 = i36 + i37;
                    bitMatrix.set(i41, i39);
                    bitMatrix.set(i39, i40);
                    bitMatrix.set(i39, i41);
                }
                i38 += 15;
                i37 += 16;
            }
        }
        AztecCode aztecCode = new AztecCode();
        aztecCode.setCompact(z2);
        aztecCode.setSize(i8);
        aztecCode.setLayers(i5);
        aztecCode.setCodeWords(size3);
        aztecCode.setMatrix(bitMatrix);
        return aztecCode;
    }
}
