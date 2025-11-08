package com.google.zxing.aztec;

import com.google.zxing.ResultPoint;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.DetectorResult;

public final class AztecDetectorResult extends DetectorResult {
    private final boolean compact;
    private final int errorsCorrected;
    private final int nbDatablocks;
    private final int nbLayers;

    public AztecDetectorResult(BitMatrix bitMatrix, ResultPoint[] resultPointArr, boolean z2, int i3, int i4) {
        this(bitMatrix, resultPointArr, z2, i3, i4, 0);
    }

    public int getErrorsCorrected() {
        return this.errorsCorrected;
    }

    public int getNbDatablocks() {
        return this.nbDatablocks;
    }

    public int getNbLayers() {
        return this.nbLayers;
    }

    public boolean isCompact() {
        return this.compact;
    }

    public AztecDetectorResult(BitMatrix bitMatrix, ResultPoint[] resultPointArr, boolean z2, int i3, int i4, int i5) {
        super(bitMatrix, resultPointArr);
        this.compact = z2;
        this.nbDatablocks = i3;
        this.nbLayers = i4;
        this.errorsCorrected = i5;
    }
}
