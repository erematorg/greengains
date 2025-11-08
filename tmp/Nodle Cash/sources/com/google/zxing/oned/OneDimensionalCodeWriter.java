package com.google.zxing.oned;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.Writer;
import com.google.zxing.common.BitMatrix;
import java.util.Collection;
import java.util.Map;
import java.util.regex.Pattern;

public abstract class OneDimensionalCodeWriter implements Writer {
    private static final Pattern NUMERIC = Pattern.compile("[0-9]+");

    public static int appendPattern(boolean[] zArr, int i3, int[] iArr, boolean z2) {
        int i4 = 0;
        for (int i5 : iArr) {
            int i6 = 0;
            while (i6 < i5) {
                zArr[i3] = z2;
                i6++;
                i3++;
            }
            i4 += i5;
            z2 = !z2;
        }
        return i4;
    }

    public static void checkNumeric(String str) {
        if (!NUMERIC.matcher(str).matches()) {
            throw new IllegalArgumentException("Input should only contain digits 0-9");
        }
    }

    private static BitMatrix renderResult(boolean[] zArr, int i3, int i4, int i5) {
        int length = zArr.length;
        int i6 = i5 + length;
        int max = Math.max(i3, i6);
        int max2 = Math.max(1, i4);
        int i7 = max / i6;
        int i8 = (max - (length * i7)) / 2;
        BitMatrix bitMatrix = new BitMatrix(max, max2);
        int i9 = 0;
        while (i9 < length) {
            if (zArr[i9]) {
                bitMatrix.setRegion(i8, 0, i7, max2);
            }
            i9++;
            i8 += i7;
        }
        return bitMatrix;
    }

    public abstract boolean[] encode(String str);

    public boolean[] encode(String str, Map<EncodeHintType, ?> map) {
        return encode(str);
    }

    public int getDefaultMargin() {
        return 10;
    }

    public Collection<BarcodeFormat> getSupportedWriteFormats() {
        return null;
    }

    public final BitMatrix encode(String str, BarcodeFormat barcodeFormat, int i3, int i4) {
        return encode(str, barcodeFormat, i3, i4, (Map<EncodeHintType, ?>) null);
    }

    public BitMatrix encode(String str, BarcodeFormat barcodeFormat, int i3, int i4, Map<EncodeHintType, ?> map) {
        if (str.isEmpty()) {
            throw new IllegalArgumentException("Found empty contents");
        } else if (i3 < 0 || i4 < 0) {
            throw new IllegalArgumentException("Negative size is not allowed. Input: " + i3 + 'x' + i4);
        } else {
            Collection<BarcodeFormat> supportedWriteFormats = getSupportedWriteFormats();
            if (supportedWriteFormats == null || supportedWriteFormats.contains(barcodeFormat)) {
                int defaultMargin = getDefaultMargin();
                if (map != null) {
                    EncodeHintType encodeHintType = EncodeHintType.MARGIN;
                    if (map.containsKey(encodeHintType)) {
                        defaultMargin = Integer.parseInt(map.get(encodeHintType).toString());
                    }
                }
                return renderResult(encode(str, map), i3, i4, defaultMargin);
            }
            throw new IllegalArgumentException("Can only encode " + supportedWriteFormats + ", but got " + barcodeFormat);
        }
    }
}
