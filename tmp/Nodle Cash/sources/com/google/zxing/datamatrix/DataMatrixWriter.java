package com.google.zxing.datamatrix;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.Dimension;
import com.google.zxing.EncodeHintType;
import com.google.zxing.Writer;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.datamatrix.encoder.DefaultPlacement;
import com.google.zxing.datamatrix.encoder.ErrorCorrection;
import com.google.zxing.datamatrix.encoder.HighLevelEncoder;
import com.google.zxing.datamatrix.encoder.MinimalEncoder;
import com.google.zxing.datamatrix.encoder.SymbolInfo;
import com.google.zxing.datamatrix.encoder.SymbolShapeHint;
import com.google.zxing.qrcode.encoder.ByteMatrix;
import java.nio.charset.Charset;
import java.util.Map;

public final class DataMatrixWriter implements Writer {
    private static BitMatrix convertByteMatrixToBitMatrix(ByteMatrix byteMatrix, int i3, int i4) {
        BitMatrix bitMatrix;
        int width = byteMatrix.getWidth();
        int height = byteMatrix.getHeight();
        int max = Math.max(i3, width);
        int max2 = Math.max(i4, height);
        int min = Math.min(max / width, max2 / height);
        int i5 = (max - (width * min)) / 2;
        int i6 = (max2 - (height * min)) / 2;
        if (i4 < height || i3 < width) {
            bitMatrix = new BitMatrix(width, height);
            i5 = 0;
            i6 = 0;
        } else {
            bitMatrix = new BitMatrix(i3, i4);
        }
        bitMatrix.clear();
        int i7 = 0;
        while (i7 < height) {
            int i8 = i5;
            int i9 = 0;
            while (i9 < width) {
                if (byteMatrix.get(i9, i7) == 1) {
                    bitMatrix.setRegion(i8, i6, min, min);
                }
                i9++;
                i8 += min;
            }
            i7++;
            i6 += min;
        }
        return bitMatrix;
    }

    private static BitMatrix encodeLowLevel(DefaultPlacement defaultPlacement, SymbolInfo symbolInfo, int i3, int i4) {
        int symbolDataWidth = symbolInfo.getSymbolDataWidth();
        int symbolDataHeight = symbolInfo.getSymbolDataHeight();
        ByteMatrix byteMatrix = new ByteMatrix(symbolInfo.getSymbolWidth(), symbolInfo.getSymbolHeight());
        int i5 = 0;
        for (int i6 = 0; i6 < symbolDataHeight; i6++) {
            if (i6 % symbolInfo.matrixHeight == 0) {
                int i7 = 0;
                for (int i8 = 0; i8 < symbolInfo.getSymbolWidth(); i8++) {
                    byteMatrix.set(i7, i5, i8 % 2 == 0);
                    i7++;
                }
                i5++;
            }
            int i9 = 0;
            for (int i10 = 0; i10 < symbolDataWidth; i10++) {
                if (i10 % symbolInfo.matrixWidth == 0) {
                    byteMatrix.set(i9, i5, true);
                    i9++;
                }
                byteMatrix.set(i9, i5, defaultPlacement.getBit(i10, i6));
                int i11 = i9 + 1;
                int i12 = symbolInfo.matrixWidth;
                if (i10 % i12 == i12 - 1) {
                    byteMatrix.set(i11, i5, i6 % 2 == 0);
                    i9 += 2;
                } else {
                    i9 = i11;
                }
            }
            int i13 = i5 + 1;
            int i14 = symbolInfo.matrixHeight;
            if (i6 % i14 == i14 - 1) {
                int i15 = 0;
                for (int i16 = 0; i16 < symbolInfo.getSymbolWidth(); i16++) {
                    byteMatrix.set(i15, i13, true);
                    i15++;
                }
                i5 += 2;
            } else {
                i5 = i13;
            }
        }
        return convertByteMatrixToBitMatrix(byteMatrix, i3, i4);
    }

    public BitMatrix encode(String str, BarcodeFormat barcodeFormat, int i3, int i4) {
        return encode(str, barcodeFormat, i3, i4, (Map<EncodeHintType, ?>) null);
    }

    public BitMatrix encode(String str, BarcodeFormat barcodeFormat, int i3, int i4, Map<EncodeHintType, ?> map) {
        Dimension dimension;
        Dimension dimension2;
        String str2;
        if (str.isEmpty()) {
            throw new IllegalArgumentException("Found empty contents");
        } else if (barcodeFormat != BarcodeFormat.DATA_MATRIX) {
            throw new IllegalArgumentException("Can only encode DATA_MATRIX, but got " + barcodeFormat);
        } else if (i3 < 0 || i4 < 0) {
            throw new IllegalArgumentException("Requested dimensions can't be negative: " + i3 + 'x' + i4);
        } else {
            SymbolShapeHint symbolShapeHint = SymbolShapeHint.FORCE_NONE;
            Charset charset = null;
            if (map != null) {
                SymbolShapeHint symbolShapeHint2 = (SymbolShapeHint) map.get(EncodeHintType.DATA_MATRIX_SHAPE);
                if (symbolShapeHint2 != null) {
                    symbolShapeHint = symbolShapeHint2;
                }
                dimension2 = (Dimension) map.get(EncodeHintType.MIN_SIZE);
                if (dimension2 == null) {
                    dimension2 = null;
                }
                dimension = (Dimension) map.get(EncodeHintType.MAX_SIZE);
                if (dimension == null) {
                    dimension = null;
                }
            } else {
                dimension2 = null;
                dimension = null;
            }
            boolean z2 = false;
            if (map != null) {
                EncodeHintType encodeHintType = EncodeHintType.DATA_MATRIX_COMPACT;
                if (map.containsKey(encodeHintType) && Boolean.parseBoolean(map.get(encodeHintType).toString())) {
                    EncodeHintType encodeHintType2 = EncodeHintType.GS1_FORMAT;
                    if (map.containsKey(encodeHintType2) && Boolean.parseBoolean(map.get(encodeHintType2).toString())) {
                        z2 = true;
                    }
                    EncodeHintType encodeHintType3 = EncodeHintType.CHARACTER_SET;
                    if (map.containsKey(encodeHintType3)) {
                        charset = Charset.forName(map.get(encodeHintType3).toString());
                    }
                    str2 = MinimalEncoder.encodeHighLevel(str, charset, z2 ? 29 : -1, symbolShapeHint);
                    SymbolInfo lookup = SymbolInfo.lookup(str2.length(), symbolShapeHint, dimension2, dimension, true);
                    DefaultPlacement defaultPlacement = new DefaultPlacement(ErrorCorrection.encodeECC200(str2, lookup), lookup.getSymbolDataWidth(), lookup.getSymbolDataHeight());
                    defaultPlacement.place();
                    return encodeLowLevel(defaultPlacement, lookup, i3, i4);
                }
            }
            if (map != null) {
                EncodeHintType encodeHintType4 = EncodeHintType.FORCE_C40;
                if (map.containsKey(encodeHintType4) && Boolean.parseBoolean(map.get(encodeHintType4).toString())) {
                    z2 = true;
                }
            }
            str2 = HighLevelEncoder.encodeHighLevel(str, symbolShapeHint, dimension2, dimension, z2);
            SymbolInfo lookup2 = SymbolInfo.lookup(str2.length(), symbolShapeHint, dimension2, dimension, true);
            DefaultPlacement defaultPlacement2 = new DefaultPlacement(ErrorCorrection.encodeECC200(str2, lookup2), lookup2.getSymbolDataWidth(), lookup2.getSymbolDataHeight());
            defaultPlacement2.place();
            return encodeLowLevel(defaultPlacement2, lookup2, i3, i4);
        }
    }
}
