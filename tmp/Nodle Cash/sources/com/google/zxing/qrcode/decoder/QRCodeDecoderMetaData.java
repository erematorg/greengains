package com.google.zxing.qrcode.decoder;

import com.google.zxing.ResultPoint;

public final class QRCodeDecoderMetaData {
    private final boolean mirrored;

    public QRCodeDecoderMetaData(boolean z2) {
        this.mirrored = z2;
    }

    public void applyMirroredCorrection(ResultPoint[] resultPointArr) {
        if (this.mirrored && resultPointArr != null && resultPointArr.length >= 3) {
            ResultPoint resultPoint = resultPointArr[0];
            resultPointArr[0] = resultPointArr[2];
            resultPointArr[2] = resultPoint;
        }
    }

    public boolean isMirrored() {
        return this.mirrored;
    }
}
