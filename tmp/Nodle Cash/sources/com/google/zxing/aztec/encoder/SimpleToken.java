package com.google.zxing.aztec.encoder;

import com.google.zxing.common.BitArray;
import kotlin.text.Typography;

final class SimpleToken extends Token {
    private final short bitCount;
    private final short value;

    public SimpleToken(Token token, int i3, int i4) {
        super(token);
        this.value = (short) i3;
        this.bitCount = (short) i4;
    }

    public void appendTo(BitArray bitArray, byte[] bArr) {
        bitArray.appendBits(this.value, this.bitCount);
    }

    public String toString() {
        short s3 = this.value;
        short s4 = this.bitCount;
        short s5 = (s3 & ((1 << s4) - 1)) | (1 << s4);
        return "<" + Integer.toBinaryString((1 << this.bitCount) | s5).substring(1) + Typography.greater;
    }
}
