package com.google.zxing.aztec.encoder;

import com.google.zxing.common.BitArray;

abstract class Token {
    static final Token EMPTY = new SimpleToken((Token) null, 0, 0);
    private final Token previous;

    public Token(Token token) {
        this.previous = token;
    }

    public final Token add(int i3, int i4) {
        return new SimpleToken(this, i3, i4);
    }

    public final Token addBinaryShift(int i3, int i4) {
        return new BinaryShiftToken(this, i3, i4);
    }

    public abstract void appendTo(BitArray bitArray, byte[] bArr);

    public final Token getPrevious() {
        return this.previous;
    }
}
