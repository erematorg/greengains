package com.google.zxing;

import com.google.zxing.common.BitMatrix;
import java.util.Map;

public interface Writer {
    BitMatrix encode(String str, BarcodeFormat barcodeFormat, int i3, int i4) throws WriterException;

    BitMatrix encode(String str, BarcodeFormat barcodeFormat, int i3, int i4, Map<EncodeHintType, ?> map) throws WriterException;
}
