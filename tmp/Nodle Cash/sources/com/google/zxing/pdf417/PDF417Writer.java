package com.google.zxing.pdf417;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.Writer;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.pdf417.encoder.Compaction;
import com.google.zxing.pdf417.encoder.Dimensions;
import com.google.zxing.pdf417.encoder.PDF417;
import java.lang.reflect.Array;
import java.nio.charset.Charset;
import java.util.Map;

public final class PDF417Writer implements Writer {
    private static final int DEFAULT_ERROR_CORRECTION_LEVEL = 2;
    private static final int WHITE_SPACE = 30;

    private static BitMatrix bitMatrixFromBitArray(byte[][] bArr, int i3) {
        int i4 = i3 * 2;
        BitMatrix bitMatrix = new BitMatrix(bArr[0].length + i4, bArr.length + i4);
        bitMatrix.clear();
        int height = (bitMatrix.getHeight() - i3) - 1;
        int i5 = 0;
        while (i5 < bArr.length) {
            byte[] bArr2 = bArr[i5];
            for (int i6 = 0; i6 < bArr[0].length; i6++) {
                if (bArr2[i6] == 1) {
                    bitMatrix.set(i6 + i3, height);
                }
            }
            i5++;
            height--;
        }
        return bitMatrix;
    }

    private static BitMatrix bitMatrixFromEncoder(PDF417 pdf417, String str, int i3, int i4, int i5, int i6, boolean z2) throws WriterException {
        boolean z3;
        pdf417.generateBarcodeLogic(str, i3, z2);
        byte[][] scaledMatrix = pdf417.getBarcodeMatrix().getScaledMatrix(1, 4);
        if ((i5 > i4) != (scaledMatrix[0].length < scaledMatrix.length)) {
            scaledMatrix = rotateArray(scaledMatrix);
            z3 = true;
        } else {
            z3 = false;
        }
        int min = Math.min(i4 / scaledMatrix[0].length, i5 / scaledMatrix.length);
        if (min <= 1) {
            return bitMatrixFromBitArray(scaledMatrix, i6);
        }
        byte[][] scaledMatrix2 = pdf417.getBarcodeMatrix().getScaledMatrix(min, min * 4);
        if (z3) {
            scaledMatrix2 = rotateArray(scaledMatrix2);
        }
        return bitMatrixFromBitArray(scaledMatrix2, i6);
    }

    private static byte[][] rotateArray(byte[][] bArr) {
        int length = bArr[0].length;
        int[] iArr = new int[2];
        iArr[1] = bArr.length;
        iArr[0] = length;
        byte[][] bArr2 = (byte[][]) Array.newInstance(Byte.TYPE, iArr);
        for (int i3 = 0; i3 < bArr.length; i3++) {
            int length2 = (bArr.length - i3) - 1;
            for (int i4 = 0; i4 < bArr[0].length; i4++) {
                bArr2[i4][length2] = bArr[i3][i4];
            }
        }
        return bArr2;
    }

    public BitMatrix encode(String str, BarcodeFormat barcodeFormat, int i3, int i4, Map<EncodeHintType, ?> map) throws WriterException {
        if (barcodeFormat == BarcodeFormat.PDF_417) {
            PDF417 pdf417 = new PDF417();
            boolean z2 = false;
            int i5 = 30;
            int i6 = 2;
            if (map != null) {
                EncodeHintType encodeHintType = EncodeHintType.PDF417_COMPACT;
                if (map.containsKey(encodeHintType)) {
                    pdf417.setCompact(Boolean.parseBoolean(map.get(encodeHintType).toString()));
                }
                EncodeHintType encodeHintType2 = EncodeHintType.PDF417_COMPACTION;
                if (map.containsKey(encodeHintType2)) {
                    pdf417.setCompaction(Compaction.valueOf(map.get(encodeHintType2).toString()));
                }
                EncodeHintType encodeHintType3 = EncodeHintType.PDF417_DIMENSIONS;
                if (map.containsKey(encodeHintType3)) {
                    Dimensions dimensions = (Dimensions) map.get(encodeHintType3);
                    pdf417.setDimensions(dimensions.getMaxCols(), dimensions.getMinCols(), dimensions.getMaxRows(), dimensions.getMinRows());
                }
                EncodeHintType encodeHintType4 = EncodeHintType.MARGIN;
                if (map.containsKey(encodeHintType4)) {
                    i5 = Integer.parseInt(map.get(encodeHintType4).toString());
                }
                EncodeHintType encodeHintType5 = EncodeHintType.ERROR_CORRECTION;
                if (map.containsKey(encodeHintType5)) {
                    i6 = Integer.parseInt(map.get(encodeHintType5).toString());
                }
                EncodeHintType encodeHintType6 = EncodeHintType.CHARACTER_SET;
                if (map.containsKey(encodeHintType6)) {
                    pdf417.setEncoding(Charset.forName(map.get(encodeHintType6).toString()));
                }
                EncodeHintType encodeHintType7 = EncodeHintType.PDF417_AUTO_ECI;
                if (map.containsKey(encodeHintType7) && Boolean.parseBoolean(map.get(encodeHintType7).toString())) {
                    z2 = true;
                }
            }
            int i7 = i6;
            return bitMatrixFromEncoder(pdf417, str, i7, i3, i4, i5, z2);
        }
        throw new IllegalArgumentException("Can only encode PDF_417, but got " + barcodeFormat);
    }

    public BitMatrix encode(String str, BarcodeFormat barcodeFormat, int i3, int i4) throws WriterException {
        return encode(str, barcodeFormat, i3, i4, (Map<EncodeHintType, ?>) null);
    }
}
