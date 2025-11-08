package com.google.zxing.aztec;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.Writer;
import com.google.zxing.aztec.encoder.AztecCode;
import com.google.zxing.aztec.encoder.Encoder;
import com.google.zxing.common.BitMatrix;
import java.nio.charset.Charset;
import java.util.Map;

public final class AztecWriter implements Writer {
    private static BitMatrix renderResult(AztecCode aztecCode, int i3, int i4) {
        BitMatrix matrix = aztecCode.getMatrix();
        if (matrix != null) {
            int width = matrix.getWidth();
            int height = matrix.getHeight();
            int max = Math.max(i3, width);
            int max2 = Math.max(i4, height);
            int min = Math.min(max / width, max2 / height);
            int i5 = (max - (width * min)) / 2;
            int i6 = (max2 - (height * min)) / 2;
            BitMatrix bitMatrix = new BitMatrix(max, max2);
            int i7 = 0;
            while (i7 < height) {
                int i8 = 0;
                int i9 = i5;
                while (i8 < width) {
                    if (matrix.get(i8, i7)) {
                        bitMatrix.setRegion(i9, i6, min, min);
                    }
                    i8++;
                    i9 += min;
                }
                i7++;
                i6 += min;
            }
            return bitMatrix;
        }
        throw new IllegalStateException();
    }

    public BitMatrix encode(String str, BarcodeFormat barcodeFormat, int i3, int i4) {
        return encode(str, barcodeFormat, i3, i4, (Map<EncodeHintType, ?>) null);
    }

    public BitMatrix encode(String str, BarcodeFormat barcodeFormat, int i3, int i4, Map<EncodeHintType, ?> map) {
        Charset charset = null;
        int i5 = 33;
        int i6 = 0;
        if (map != null) {
            EncodeHintType encodeHintType = EncodeHintType.CHARACTER_SET;
            if (map.containsKey(encodeHintType)) {
                charset = Charset.forName(map.get(encodeHintType).toString());
            }
            EncodeHintType encodeHintType2 = EncodeHintType.ERROR_CORRECTION;
            if (map.containsKey(encodeHintType2)) {
                i5 = Integer.parseInt(map.get(encodeHintType2).toString());
            }
            EncodeHintType encodeHintType3 = EncodeHintType.AZTEC_LAYERS;
            if (map.containsKey(encodeHintType3)) {
                i6 = Integer.parseInt(map.get(encodeHintType3).toString());
            }
        }
        return encode(str, barcodeFormat, i3, i4, charset, i5, i6);
    }

    private static BitMatrix encode(String str, BarcodeFormat barcodeFormat, int i3, int i4, Charset charset, int i5, int i6) {
        if (barcodeFormat == BarcodeFormat.AZTEC) {
            return renderResult(Encoder.encode(str, i5, i6, charset), i3, i4);
        }
        throw new IllegalArgumentException("Can only encode AZTEC, but got " + barcodeFormat);
    }
}
