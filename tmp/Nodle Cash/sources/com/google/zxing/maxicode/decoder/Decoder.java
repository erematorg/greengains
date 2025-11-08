package com.google.zxing.maxicode.decoder;

import com.google.common.base.Ascii;
import com.google.zxing.ChecksumException;
import com.google.zxing.DecodeHintType;
import com.google.zxing.FormatException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.DecoderResult;
import com.google.zxing.common.reedsolomon.GenericGF;
import com.google.zxing.common.reedsolomon.ReedSolomonDecoder;
import com.google.zxing.common.reedsolomon.ReedSolomonException;
import java.util.Map;

public final class Decoder {
    private static final int ALL = 0;
    private static final int EVEN = 1;
    private static final int ODD = 2;
    private final ReedSolomonDecoder rsDecoder = new ReedSolomonDecoder(GenericGF.MAXICODE_FIELD_64);

    private int correctErrors(byte[] bArr, int i3, int i4, int i5, int i6) throws ChecksumException {
        int i7 = i4 + i5;
        int i8 = i6 == 0 ? 1 : 2;
        int[] iArr = new int[(i7 / i8)];
        for (int i9 = 0; i9 < i7; i9++) {
            if (i6 == 0 || i9 % 2 == i6 - 1) {
                iArr[i9 / i8] = bArr[i9 + i3] & 255;
            }
        }
        try {
            int decodeWithECCount = this.rsDecoder.decodeWithECCount(iArr, i5 / i8);
            for (int i10 = 0; i10 < i4; i10++) {
                if (i6 == 0 || i10 % 2 == i6 - 1) {
                    bArr[i10 + i3] = (byte) iArr[i10 / i8];
                }
            }
            return decodeWithECCount;
        } catch (ReedSolomonException unused) {
            throw ChecksumException.getChecksumInstance();
        }
    }

    public DecoderResult decode(BitMatrix bitMatrix) throws ChecksumException, FormatException {
        return decode(bitMatrix, (Map<DecodeHintType, ?>) null);
    }

    public DecoderResult decode(BitMatrix bitMatrix, Map<DecodeHintType, ?> map) throws FormatException, ChecksumException {
        int i3;
        byte[] bArr;
        byte[] readCodewords = new BitMatrixParser(bitMatrix).readCodewords();
        int correctErrors = correctErrors(readCodewords, 0, 10, 10, 0);
        byte b3 = readCodewords[0] & Ascii.SI;
        if (b3 == 2 || b3 == 3 || b3 == 4) {
            byte[] bArr2 = readCodewords;
            i3 = correctErrors + correctErrors(bArr2, 20, 84, 40, 1) + correctErrors(bArr2, 20, 84, 40, 2);
            bArr = new byte[94];
        } else if (b3 == 5) {
            byte[] bArr3 = readCodewords;
            i3 = correctErrors + correctErrors(bArr3, 20, 68, 56, 1) + correctErrors(bArr3, 20, 68, 56, 2);
            bArr = new byte[78];
        } else {
            throw FormatException.getFormatInstance();
        }
        System.arraycopy(readCodewords, 0, bArr, 0, 10);
        System.arraycopy(readCodewords, 20, bArr, 10, bArr.length - 10);
        DecoderResult decode = DecodedBitStreamParser.decode(bArr, b3);
        decode.setErrorsCorrected(Integer.valueOf(i3));
        return decode;
    }
}
