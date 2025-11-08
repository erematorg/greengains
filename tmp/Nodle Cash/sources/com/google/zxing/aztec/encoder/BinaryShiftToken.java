package com.google.zxing.aztec.encoder;

import com.google.zxing.common.BitArray;
import kotlin.text.Typography;

final class BinaryShiftToken extends Token {
    private final int binaryShiftByteCount;
    private final int binaryShiftStart;

    public BinaryShiftToken(Token token, int i3, int i4) {
        super(token);
        this.binaryShiftStart = i3;
        this.binaryShiftByteCount = i4;
    }

    public void appendTo(BitArray bitArray, byte[] bArr) {
        int i3 = this.binaryShiftByteCount;
        for (int i4 = 0; i4 < i3; i4++) {
            if (i4 == 0 || (i4 == 31 && i3 <= 62)) {
                bitArray.appendBits(31, 5);
                if (i3 > 62) {
                    bitArray.appendBits(i3 - 31, 16);
                } else if (i4 == 0) {
                    bitArray.appendBits(Math.min(i3, 31), 5);
                } else {
                    bitArray.appendBits(i3 - 31, 5);
                }
            }
            bitArray.appendBits(bArr[this.binaryShiftStart + i4], 8);
        }
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("<");
        sb.append(this.binaryShiftStart);
        sb.append("::");
        sb.append((this.binaryShiftStart + this.binaryShiftByteCount) - 1);
        sb.append(Typography.greater);
        return sb.toString();
    }
}
