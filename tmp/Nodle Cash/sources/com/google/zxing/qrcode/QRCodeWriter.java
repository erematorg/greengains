package com.google.zxing.qrcode;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.Writer;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import com.google.zxing.qrcode.encoder.ByteMatrix;
import com.google.zxing.qrcode.encoder.Encoder;
import com.google.zxing.qrcode.encoder.QRCode;
import java.util.Map;

public final class QRCodeWriter implements Writer {
    private static final int QUIET_ZONE_SIZE = 4;

    private static BitMatrix renderResult(QRCode qRCode, int i3, int i4, int i5) {
        ByteMatrix matrix = qRCode.getMatrix();
        if (matrix != null) {
            int width = matrix.getWidth();
            int height = matrix.getHeight();
            int i6 = i5 * 2;
            int i7 = width + i6;
            int i8 = i6 + height;
            int max = Math.max(i3, i7);
            int max2 = Math.max(i4, i8);
            int min = Math.min(max / i7, max2 / i8);
            int i9 = (max - (width * min)) / 2;
            int i10 = (max2 - (height * min)) / 2;
            BitMatrix bitMatrix = new BitMatrix(max, max2);
            int i11 = 0;
            while (i11 < height) {
                int i12 = 0;
                int i13 = i9;
                while (i12 < width) {
                    if (matrix.get(i12, i11) == 1) {
                        bitMatrix.setRegion(i13, i10, min, min);
                    }
                    i12++;
                    i13 += min;
                }
                i11++;
                i10 += min;
            }
            return bitMatrix;
        }
        throw new IllegalStateException();
    }

    public BitMatrix encode(String str, BarcodeFormat barcodeFormat, int i3, int i4) throws WriterException {
        return encode(str, barcodeFormat, i3, i4, (Map<EncodeHintType, ?>) null);
    }

    public BitMatrix encode(String str, BarcodeFormat barcodeFormat, int i3, int i4, Map<EncodeHintType, ?> map) throws WriterException {
        if (str.isEmpty()) {
            throw new IllegalArgumentException("Found empty contents");
        } else if (barcodeFormat != BarcodeFormat.QR_CODE) {
            throw new IllegalArgumentException("Can only encode QR_CODE, but got " + barcodeFormat);
        } else if (i3 < 0 || i4 < 0) {
            throw new IllegalArgumentException("Requested dimensions are too small: " + i3 + 'x' + i4);
        } else {
            ErrorCorrectionLevel errorCorrectionLevel = ErrorCorrectionLevel.L;
            int i5 = 4;
            if (map != null) {
                EncodeHintType encodeHintType = EncodeHintType.ERROR_CORRECTION;
                if (map.containsKey(encodeHintType)) {
                    errorCorrectionLevel = ErrorCorrectionLevel.valueOf(map.get(encodeHintType).toString());
                }
                EncodeHintType encodeHintType2 = EncodeHintType.MARGIN;
                if (map.containsKey(encodeHintType2)) {
                    i5 = Integer.parseInt(map.get(encodeHintType2).toString());
                }
            }
            return renderResult(Encoder.encode(str, errorCorrectionLevel, map), i3, i4, i5);
        }
    }
}
