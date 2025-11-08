package com.google.zxing.pdf417.detector;

import com.google.zxing.ResultPoint;
import com.google.zxing.common.BitMatrix;
import java.util.List;

public final class PDF417DetectorResult {
    private final BitMatrix bits;
    private final List<ResultPoint[]> points;
    private final int rotation;

    public PDF417DetectorResult(BitMatrix bitMatrix, List<ResultPoint[]> list, int i3) {
        this.bits = bitMatrix;
        this.points = list;
        this.rotation = i3;
    }

    public BitMatrix getBits() {
        return this.bits;
    }

    public List<ResultPoint[]> getPoints() {
        return this.points;
    }

    public int getRotation() {
        return this.rotation;
    }

    public PDF417DetectorResult(BitMatrix bitMatrix, List<ResultPoint[]> list) {
        this(bitMatrix, list, 0);
    }
}
