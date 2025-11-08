package com.google.zxing.oned;

import androidx.browser.trusted.c;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.Writer;
import com.google.zxing.common.BitMatrix;
import java.util.Map;
import org.apache.xerces.impl.xs.SchemaSymbols;

public final class UPCAWriter implements Writer {
    private final EAN13Writer subWriter = new EAN13Writer();

    public BitMatrix encode(String str, BarcodeFormat barcodeFormat, int i3, int i4) {
        return encode(str, barcodeFormat, i3, i4, (Map<EncodeHintType, ?>) null);
    }

    public BitMatrix encode(String str, BarcodeFormat barcodeFormat, int i3, int i4, Map<EncodeHintType, ?> map) {
        if (barcodeFormat == BarcodeFormat.UPC_A) {
            return this.subWriter.encode(c.a(SchemaSymbols.ATTVAL_FALSE_0, str), BarcodeFormat.EAN_13, i3, i4, map);
        }
        throw new IllegalArgumentException("Can only encode UPC-A, but got " + barcodeFormat);
    }
}
