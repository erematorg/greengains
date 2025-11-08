package com.appsamurai.storyly.exoplayer2.extractor.extractor.ts;

import com.appsamurai.storyly.exoplayer2.common.util.Assertions;
import java.util.Arrays;

final class NalUnitTargetBuffer {
    private boolean isCompleted;
    private boolean isFilling;
    public byte[] nalData;
    public int nalLength;
    private final int targetType;

    public NalUnitTargetBuffer(int i3, int i4) {
        this.targetType = i3;
        byte[] bArr = new byte[(i4 + 3)];
        this.nalData = bArr;
        bArr[2] = 1;
    }

    public void appendToNalUnit(byte[] bArr, int i3, int i4) {
        if (this.isFilling) {
            int i5 = i4 - i3;
            byte[] bArr2 = this.nalData;
            int length = bArr2.length;
            int i6 = this.nalLength;
            if (length < i6 + i5) {
                this.nalData = Arrays.copyOf(bArr2, (i6 + i5) * 2);
            }
            System.arraycopy(bArr, i3, this.nalData, this.nalLength, i5);
            this.nalLength += i5;
        }
    }

    public boolean endNalUnit(int i3) {
        if (!this.isFilling) {
            return false;
        }
        this.nalLength -= i3;
        this.isFilling = false;
        this.isCompleted = true;
        return true;
    }

    public boolean isCompleted() {
        return this.isCompleted;
    }

    public void reset() {
        this.isFilling = false;
        this.isCompleted = false;
    }

    public void startNalUnit(int i3) {
        boolean z2 = true;
        Assertions.checkState(!this.isFilling);
        if (i3 != this.targetType) {
            z2 = false;
        }
        this.isFilling = z2;
        if (z2) {
            this.nalLength = 3;
            this.isCompleted = false;
        }
    }
}
